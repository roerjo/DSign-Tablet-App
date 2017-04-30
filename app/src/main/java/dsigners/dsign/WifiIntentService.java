package dsigners.dsign;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;


public class WifiIntentService extends IntentService {

    private String ACCESS_TOKEN;

    public WifiIntentService() {
        super("WifiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            ACCESS_TOKEN = retrieveAccessToken();

            download("main");

            Log.d("WifiIntentService", "Service Running");

        }
    }

    //Passes the necessary info to the Dropbox client to download the json file
    private void download(String next) {

        //Select file to download
        File file = new File("/sdcard/DSign/SyncData.json");

        //Initialize DownloadTask
        try {
            //The get() at the end blocks code from executing until the download is complete
            new DownloadTask(DropboxClient.getClient(ACCESS_TOKEN), file, getApplicationContext(), next).execute().get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Retrieves the token so it can be passed to the Dropbox client
    private String retrieveAccessToken() {
        SharedPreferences prefs = getSharedPreferences("com.example.dropboxsdktest", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        if (accessToken == null) {
            return null;
        } else {
            return accessToken;
        }
    }

}
