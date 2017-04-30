package dsigners.dsign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class TabMessagesFragment extends Fragment {

    //Retrieve the current messages
    String syncdefaultMsg = SyncSettings.getInstance().getDefaultMsg();
    String syncpreset1 = SyncSettings.getInstance().getMsg1();
    String syncpreset2 = SyncSettings.getInstance().getMsg2();
    String syncpreset3 = SyncSettings.getInstance().getMsg3();
    String syncpreset4 = SyncSettings.getInstance().getMsg4();
    String syncpreset5 = SyncSettings.getInstance().getMsg5();
    String syncpreset6 = SyncSettings.getInstance().getMsg6();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_messages, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Set messages the are saved in the current settings instance
        EditText defaultMsg = (EditText) view.findViewById(R.id.editMessageDefault);
        EditText preset1 = (EditText) view.findViewById(R.id.editMessage1);
        EditText preset2 = (EditText) view.findViewById(R.id.editMessage2);
        EditText preset3 = (EditText) view.findViewById(R.id.editMessage3);
        EditText preset4 = (EditText) view.findViewById(R.id.editMessage4);
        EditText preset5 = (EditText) view.findViewById(R.id.editMessage5);
        EditText preset6 = (EditText) view.findViewById(R.id.editMessage6);

        defaultMsg.setText(syncdefaultMsg);
        preset1.setText(syncpreset1);
        preset2.setText(syncpreset2);
        preset3.setText(syncpreset3);
        preset4.setText(syncpreset4);
        preset5.setText(syncpreset5);
        preset6.setText(syncpreset6);

    }
}
