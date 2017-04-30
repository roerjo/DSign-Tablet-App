package dsigners.dsign;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScheduleActivity extends AppCompatActivity {
    Button sunday;
    Button monday;
    Button tuesday;
    Button wednesday;
    Button thursday;
    Button friday;
    Button main;
    Button roomNumber;
    String syncRoom = SyncSettings.getInstance().getRoom().toString();
    Boolean setSundayBool = SyncSettings.getInstance().getWeekendMode();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        sunday = (Button)findViewById(R.id.sunday);
        monday = (Button)findViewById(R.id.monday);
        tuesday = (Button)findViewById(R.id.tuesday);
        wednesday = (Button)findViewById(R.id.wednesday);
        thursday = (Button)findViewById(R.id.thursday);
        friday = (Button)findViewById(R.id.friday);
        main = (Button)findViewById(R.id.Main);
        roomNumber = (Button)findViewById(R.id.ScheduleRoomNumber);
        roomNumber.setText("Rm " + syncRoom);

        if (setSundayBool == false) {
            sunday.setVisibility(View.GONE);
        }
        else if (setSundayBool == true) {
            sunday.setVisibility(View.VISIBLE);
            sunday.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(ScheduleActivity.this, DayOfTheWeek.class);
                    startActivity(intent);
                }
            });
        }
        monday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ScheduleActivity.this, DayOfTheWeek.class);
                startActivity(intent);
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ScheduleActivity.this, DayOfTheWeek.class);
                startActivity(intent);
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ScheduleActivity.this, DayOfTheWeek.class);
                startActivity(intent);
            }
        });
        thursday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ScheduleActivity.this, DayOfTheWeek.class);
                startActivity(intent);
            }
        });
        friday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ScheduleActivity.this, DayOfTheWeek.class);
                startActivity(intent);
            }
        });
        main.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ScheduleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    // Link from schedule page to main page
    protected void goToMain(View x) {

        Intent intent = new Intent(ScheduleActivity.this, MainActivity.class);
        startActivity(intent);

    }
    //Link from day Tab to DayOfTheWeek Page






}