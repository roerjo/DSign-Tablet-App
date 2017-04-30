package dsigners.dsign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String ACCESS_TOKEN;
    ImageButton settings, sync;
    String pinPassed;
    Button preset1, preset2, preset3, preset4, preset5, preset6, defaultMsg, schedule;
    String pinSet = SyncSettings.getInstance().getPin();
    String syncName = SyncSettings.getInstance().getName();
    String syncRoom = SyncSettings.getInstance().getRoom().toString();
    String syncDefaultMsg = SyncSettings.getInstance().getDefaultMsg();
    String syncPMsg1 = SyncSettings.getInstance().getMsg1();
    String syncPMsg2 = SyncSettings.getInstance().getMsg2();
    String syncPMsg3 = SyncSettings.getInstance().getMsg3();
    String syncPMsg4 = SyncSettings.getInstance().getMsg4();
    String syncPMsg5 = SyncSettings.getInstance().getMsg5();
    String syncPMsg6 = SyncSettings.getInstance().getMsg6();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Redirect to login page if no access token
        if (!tokenExists()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        //Retrieve the stored access token to access Dropbox on download
        ACCESS_TOKEN = retrieveAccessToken();


        //Retrieve and set the name, room, and message
        TextView mainName = (TextView)findViewById(R.id.mainName);
        TextView mainRoom = (TextView)findViewById(R.id.MainRoom);
        final TextView mainMessage = (TextView)findViewById(R.id.mainMessage);
        mainName.setText(syncName);
        mainRoom.setText("Rm: " + syncRoom);
        mainMessage.setText(syncDefaultMsg);

        //Retrieve and set the preset messages click listeners
        preset1 = (Button)findViewById(R.id.preset1);
        preset2 = (Button)findViewById(R.id.preset2);
        preset3 = (Button)findViewById(R.id.preset3);
        preset4 = (Button)findViewById(R.id.preset4);
        preset5 = (Button)findViewById(R.id.preset5);
        preset6 = (Button)findViewById(R.id.preset6);
        defaultMsg = (Button)findViewById(R.id.defaultMsg);

        preset1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainMessage.setText(syncPMsg1);
            }
        });
        preset2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainMessage.setText(syncPMsg2);
            }
        });
        preset3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainMessage.setText(syncPMsg3);
            }
        });
        preset4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainMessage.setText(syncPMsg4);
            }
        });
        preset5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainMessage.setText(syncPMsg5);
            }
        });
        preset6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainMessage.setText(syncPMsg6);
            }
        });
        defaultMsg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {               mainMessage.setText(syncDefaultMsg);
            }
        });

        //Set click listeners
        settings = (ImageButton)findViewById(R.id.settings);
        schedule = (Button)findViewById(R.id.schedule);
        sync = (ImageButton)findViewById(R.id.sync);

        settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {

                    //Bring up alert box to ask for PIN
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("PIN");
                    alertDialog.setMessage("Enter PIN");

                    final EditText input = new EditText(MainActivity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);

                    //If user clicks OK...
                    alertDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    //Check the PIN to makes sure it matches
                                    pinPassed = input.getText().toString();
                                    if (pinPassed.compareTo(pinSet) == 0) {

                                            //Let the user know the password accepted
                                            Toast.makeText(getApplicationContext(),
                                                    "Password Matched", Toast.LENGTH_SHORT).show();

                                            //Download the json file
                                            download("settings");
                                    }
                                    //If bad PIN...
                                    else {
                                            //Let the user know the PIN is don't match
                                            Toast.makeText(getApplicationContext(),
                                                    "Wrong Password!", Toast.LENGTH_SHORT).show();
                                    }
                                    }

                            });
                    //If the user hits the Cancel button, do nothing
                    alertDialog.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    alertDialog.show();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        //Redirect to Schedule page
        schedule.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CalendarWebView.class);
                startActivity(intent);
            }
        });

        //Perform a sync
        sync.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                download("main");

            }
        });

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

    //Makes sure the user has a Dropbox access token stored locally
    private boolean tokenExists() {
        SharedPreferences prefs = getSharedPreferences("com.example.dropboxsdktest", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        return accessToken != null;
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
