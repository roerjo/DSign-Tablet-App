package dsigners.dsign;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


/**
 * Created by eric on 3/29/17.
 */

public class ConvertToJSON {

    public static void toJSON() {
        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray jsonArr = new JSONArray();

            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(System.currentTimeMillis());

            jsonArr.put(SyncSettings.getInstance().getMsg1());
            jsonArr.put(SyncSettings.getInstance().getMsg2());
            jsonArr.put(SyncSettings.getInstance().getMsg3());
            jsonArr.put(SyncSettings.getInstance().getMsg4());
            jsonArr.put(SyncSettings.getInstance().getMsg5());
            jsonArr.put(SyncSettings.getInstance().getMsg6());

            jsonObj.put("LastUpdated", currentDateTime);
            jsonObj.put("DisplayedName", SyncSettings.getInstance().getName());
            jsonObj.put("RoomNumber", SyncSettings.getInstance().getRoom());
            jsonObj.put("MessageOfTheDay", SyncSettings.getInstance().getDefaultMsg());
            jsonObj.put("PresetMessages", jsonArr);
            jsonObj.put("SyncRate", SyncSettings.getInstance().getSyncRate());
            jsonObj.put("HTMLCalendarLink", SyncSettings.getInstance().getWebLink());
            jsonObj.put("ICalCalendarLink", SyncSettings.getInstance().getICalLink());

            String data = jsonObj.toString();

            Writer output;
            File file = new File("/mnt/sdcard/DSign/SyncData.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(data);
            output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toSettings() {
        try {
            File file = new File("/mnt/sdcard/DSign/SyncData.json");
            FileInputStream input = new FileInputStream(file);
            FileChannel fc = input.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

            String data = Charset.defaultCharset().decode(bb).toString();

            JSONObject jsonObj = new JSONObject(data);
            JSONArray jsonArr = jsonObj.getJSONArray("PresetMessages");
            int length = jsonArr.length();

            String[] preset = new String[6];

            for (int i = 0; i < length; i++) {
                preset[i] = jsonArr.getString(i);
            }

            SyncSettings.getInstance().setName(jsonObj.getString("DisplayedName"));
            SyncSettings.getInstance().setRoom(jsonObj.getInt("RoomNumber"));
            SyncSettings.getInstance().setSyncRate(jsonObj.getInt("SyncRate"));
            SyncSettings.getInstance().setDefaultMsg(jsonObj.getString("MessageOfTheDay"));
            SyncSettings.getInstance().setWebLink(jsonObj.getString("HTMLCalendarLink"));
            SyncSettings.getInstance().setICalLink(jsonObj.getString("ICalCalendarLink"));
            SyncSettings.getInstance().setMsg1(preset[0]);
            SyncSettings.getInstance().setMsg2(preset[1]);
            SyncSettings.getInstance().setMsg3(preset[2]);
            SyncSettings.getInstance().setMsg4(preset[3]);
            SyncSettings.getInstance().setMsg5(preset[4]);
            SyncSettings.getInstance().setMsg6(preset[5]);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
