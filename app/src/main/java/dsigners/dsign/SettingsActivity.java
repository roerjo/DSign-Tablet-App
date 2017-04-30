package dsigners.dsign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.File;


public class SettingsActivity extends AppCompatActivity {

    private String ACCESS_TOKEN;
    Button save;
    Button cancel;
    TextView roomNumber;
    String syncRoom = SyncSettings.getInstance().getRoom().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //sync Room Number
        roomNumber = (TextView)findViewById(R.id.roomNum);
        roomNumber.setText("Rm " + syncRoom);

        //Create the tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("General"));
        tabLayout.addTab(tabLayout.newTab().setText("Preset Messages"));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TabsPagerAdapter adapter = new TabsPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //If no token stored redirect to login page
        if (!tokenExists()) {
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        //Access the device Wifi settings
        //final WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        //Retrieve the access token to pass to the upload function
        ACCESS_TOKEN = retrieveAccessToken();

        //Access the Save and Cancel buttons
        save = (Button)findViewById(R.id.save);
        cancel = (Button)findViewById(R.id.cancel);


        //Set the click listener on the Save button
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //Grab both passed PINS
                EditText pinS = (EditText)findViewById(R.id.pinSet);
                EditText pinC = (EditText)findViewById(R.id.pinConfirm);
                String pinSet = pinS.getText().toString();
                String pinConfirm = pinC.getText().toString();

                //If the PINS match, then allow to continue
                if (pinSet.compareTo(pinConfirm) == 0) {

                    //Since the PINS match set the PIN in settings
                    SyncSettings.getInstance().setPin(pinSet);

                    //Set all the other settings
                    setSettings();

                    //Convert the settings to a json file
                    ConvertToJSON.toJSON();

                    //Upload json to Dropbox
                    upload("main");
                }
                //If the PINS don't match, don't allow the settings to be set and uploaded
                else {
                    Toast.makeText(getApplicationContext(),
                            "PINs do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //If Cancel is hit, redirect to Main page without saving settings
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    //Saves all the settings to the SyncSetting instance
    private void setSettings() {

        EditText name = (EditText)findViewById(R.id.editName);
        EditText room = (EditText)findViewById(R.id.editRoomNum);
        EditText syncRate = (EditText)findViewById(R.id.syncRate);
        EditText defaultMsg = (EditText)findViewById(R.id.editMessageDefault);
        EditText preset1 = (EditText)findViewById(R.id.editMessage1);
        EditText preset2 = (EditText)findViewById(R.id.editMessage2);
        EditText preset3 = (EditText)findViewById(R.id.editMessage3);
        EditText preset4 = (EditText)findViewById(R.id.editMessage4);
        EditText preset5 = (EditText)findViewById(R.id.editMessage5);
        EditText preset6 = (EditText)findViewById(R.id.editMessage6);

        SyncSettings.getInstance().setName(name.getText().toString());
        SyncSettings.getInstance().setRoom(Integer.parseInt(room.getText().toString()));
        SyncSettings.getInstance().setSyncRate(Integer.parseInt(syncRate.getText().toString()));
        SyncSettings.getInstance().setDefaultMsg(defaultMsg.getText().toString());
        SyncSettings.getInstance().setMsg1(preset1.getText().toString());
        SyncSettings.getInstance().setMsg2(preset2.getText().toString());
        SyncSettings.getInstance().setMsg3(preset3.getText().toString());
        SyncSettings.getInstance().setMsg4(preset4.getText().toString());
        SyncSettings.getInstance().setMsg5(preset5.getText().toString());
        SyncSettings.getInstance().setMsg6(preset6.getText().toString());

    }

    //Passes necessary info to the Dropbox client for file upload
    private void upload(String next) {

        //Select file to upload
        File file = new File("/sdcard/DSign/SyncData.json");

        //Initialize UploadTask
        try {
            //The get() blocks code from executing until the upload is complete
            new UploadTask(DropboxClient.getClient(ACCESS_TOKEN), file, getApplicationContext(), next).execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean tokenExists() {
        SharedPreferences prefs = getSharedPreferences("com.example.dropboxsdktest", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        return accessToken != null;
    }

    private String retrieveAccessToken() {
        //check if ACCESS_TOKEN is stored on previous app launches
        SharedPreferences prefs = getSharedPreferences("com.example.dropboxsdktest", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        if (accessToken == null) {
            return null;
        } else {
            return accessToken;
        }
    }
}

