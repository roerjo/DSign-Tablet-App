package dsigners.dsign;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by eric on 4/5/17.
 */

public class yourActivityRunOnStartup extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {


            //Integer syncRate = SyncSettings.getInstance().getSyncRate();
            //AlarmUtility.alarmStart(context.getApplicationContext(), syncRate);

            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
