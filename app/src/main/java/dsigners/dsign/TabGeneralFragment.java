package dsigners.dsign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;


public class TabGeneralFragment extends Fragment {

    //Retrieve the settings for this fragment
    String syncName = SyncSettings.getInstance().getName();
    String syncRmNum = SyncSettings.getInstance().getRoom().toString();
    String syncSyncRate = SyncSettings.getInstance().getSyncRate().toString();
    String syncPIN = SyncSettings.getInstance().getPin();
    Boolean syncOfflineMode = SyncSettings.getInstance().getOfflineMode();
    Boolean syncWeekendMode = SyncSettings.getInstance().getWeekendMode();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_general, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Set all the settings that are on this fragment
        EditText editName = (EditText) view.findViewById(R.id.editName);
        EditText editRoom = (EditText) view.findViewById(R.id.editRoomNum);
        EditText editSync = (EditText) view.findViewById(R.id.syncRate);
        ToggleButton offlineMode = (ToggleButton) view.findViewById(R.id.toggleOffline);
        EditText pinSet = (EditText) view.findViewById(R.id.pinSet);
        EditText pinConfirm = (EditText) view.findViewById(R.id.pinConfirm);
        ToggleButton weekendMode = (ToggleButton) view.findViewById(R.id.weekendToggle);



        editName.setText(syncName);
        editRoom.setText(syncRmNum);
        editSync.setText(syncSyncRate);
        offlineMode.setChecked(syncOfflineMode);
        weekendMode.setChecked(syncWeekendMode);
        pinSet.setText(syncPIN);
        pinConfirm.setText(syncPIN);

        offlineMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SyncSettings.getInstance().setOfflineMode(true);
                } else {
                    SyncSettings.getInstance().setOfflineMode(false);
                }
            }
        });
        weekendMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SyncSettings.getInstance().setWeekendMode(true);
                } else {
                    SyncSettings.getInstance().setWeekendMode(false);
                }
            }
        });




    }
}
