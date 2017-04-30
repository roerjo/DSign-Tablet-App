package dsigners.dsign;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.core.DbxException;
import com.dropbox.core.util.Collector;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;

/**
 * Created by eric on 2/22/17.
 */

public class DownloadTask extends AsyncTask<FileMetadata, Void, File> {

    private DbxClientV2 dbxClient;
    private File file;
    private Context context;
    private String next;
    private WifiManager wifi;


    DownloadTask(DbxClientV2 dbxClient, File file, Context context, String next) {
        this.dbxClient = dbxClient;
        this.file = file;
        this.context = context.getApplicationContext();
        this.next = next;
    }

    @Override
    protected File doInBackground(FileMetadata... params) {

        wifi = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifi.setWifiEnabled(true);

        try {

            // Download from Dropbox
            OutputStream outputStream = new FileOutputStream("/mnt/sdcard/DSign/SyncData.json");
            dbxClient.files().download("/Apps/D'Sign Sync/Sync/SyncData.json").download(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(File o) {
        super.onPostExecute(o);

        ConvertToJSON.toSettings();

        AlarmUtility.alarmCancel(context);

        Integer syncRate = SyncSettings.getInstance().getSyncRate();

        AlarmUtility.alarmStart(context, syncRate);

        if (next.equals("settings")) {
            //Redirect to settings page
            Intent myIntent1 = new Intent(context,
                    SettingsActivity.class);
            myIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent1);
        }
        else if (next.equals("main")) {
            //Redirect to main page
            Intent myIntent2 = new Intent(context,
                    MainActivity.class);
            myIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent2);
        }

    }

}

