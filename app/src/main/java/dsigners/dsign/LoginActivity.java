package dsigners.dsign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dropbox.core.android.Auth;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Clicking the sign in button makes the user confirm their Dropbox account
        Button SignInButton = (Button) findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.startOAuth2Authentication(LoginActivity.this, getString(R.string.APP_KEY));
            }
        });
    }

    //After the user confirms Dropbox account, this will retrieve the token
    @Override
    protected void onResume() {
        super.onResume();
        getAccessToken();
    }

    public void getAccessToken() {
        String accessToken = Auth.getOAuth2Token(); //generate Access Token
        if (accessToken != null) {
            //Store accessToken in SharedPreferences
            SharedPreferences prefs = getSharedPreferences("com.example.dropboxsdktest", Context.MODE_PRIVATE);
            prefs.edit().putString("access-token", accessToken).commit();

            //Proceed to MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
