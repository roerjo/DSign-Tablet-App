package dsigners.dsign;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by eric on 4/17/17.
 */

public class AlarmUtility {

    public static void alarmStart(Context context, Integer syncRate) {

        long startNow = System.currentTimeMillis();

        AlarmManager alarm = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        // Construct an intent that will execute the AlarmReceiver
        Intent alarmIntent = new Intent(context.getApplicationContext(), WifiAlarmReceiver.class);

        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, startNow + (syncRate*60000), syncRate * 60000, pIntent);
    }

    public static void alarmCancel(Context context) {

        AlarmManager alarm = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        // Construct an intent that will execute the AlarmReceiver
        Intent alarmIntent = new Intent(context.getApplicationContext(), WifiAlarmReceiver.class);

        final PendingIntent pIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.cancel(pIntent);
    }

}
