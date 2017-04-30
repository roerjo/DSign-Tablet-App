package dsigners.dsign;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WifiAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, WifiIntentService.class);
        context.startService(i);
    }
}
