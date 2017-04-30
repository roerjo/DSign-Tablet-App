package dsigners.dsign;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by eric on 2/22/17.
 */

public class UploadTask extends AsyncTask {

    private DbxClientV2 dbxClient;
    private File file;
    private Context context;
    private String next;
    private WifiManager wifi;

    UploadTask(DbxClientV2 dbxClient, File file, Context context, String next) {
        this.dbxClient = dbxClient;
        this.file = file;
        this.context = context.getApplicationContext();
        this.next = next;
    }

    @Override
    protected Object doInBackground(Object[] params) {


        try {

            InputStream inputStream = new FileInputStream(file);
            dbxClient.files().uploadBuilder("/Apps/D'Sign Sync/Sync/" + file.getName()) //Path in the user's Dropbox to save the file.
                    .withMode(WriteMode.OVERWRITE) //always overwrite existing file
                    .uploadAndFinish(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        wifi = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (SyncSettings.getInstance().getOfflineMode()) {

            wifi.setWifiEnabled(false);

            AlarmUtility.alarmCancel(context);
        } else {

            wifi.setWifiEnabled(true);

            Integer syncRate = SyncSettings.getInstance().getSyncRate();

            AlarmUtility.alarmCancel(context);
            AlarmUtility.alarmStart(context, syncRate);
        }


        if (next.equals("main")) {
            //Redirect to Main page
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
