package dsigners.dsign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class CalendarWebView extends AppCompatActivity {
    Button mainActivity;
    String calendarLink = SyncSettings.getInstance().getWebLink();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_web_view);

        //setting WebView to display Calendar;
        WebView view = (WebView) findViewById(R.id.webViewer);
        WebSettings viewSettings = view.getSettings();
        //Enable javascripts
        viewSettings.setJavaScriptEnabled(true);
        view.setWebViewClient(new WebViewClient());
        //Load html file from internal storage from device
        view.loadUrl(calendarLink);


        //Navigate back to MainActivity
        mainActivity = (Button)findViewById(R.id.Main);
        mainActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CalendarWebView.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
