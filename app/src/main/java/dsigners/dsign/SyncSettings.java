package dsigners.dsign;

/**
 * Created by eric on 3/28/17.
 */

public class SyncSettings {

    //Initialize settings on first start
    private String name = "John Doe";
    private int room = 100;
    private int syncRate = 5;
    private boolean offlineMode = false;
    private boolean weekendMode = false;
    private String iCalLink;
    private String webLink = "https://calendar.google.com/calendar/embed?src=kungfoounderscorenaweel%40gmail.com&ctz=America/New_York";

    private String defaultMsg = "Welcome! Come on in!";
    private String msg1 = "In meeting";
    private String msg2 = "At lunch";
    private String msg3 = "In lab";
    private String msg4 = "Out of the office";
    private String msg5 = "In class";
    private String msg6 = "Out for the day";
    private String pin = "1234";


    //Getters and setters for settings
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Integer getRoom() {return room;}
    public void setRoom(Integer room) {this.room = room;}
    public Integer getSyncRate() {return syncRate;}
    public void setSyncRate(Integer syncRate) {this.syncRate = syncRate;}
    public Boolean getOfflineMode() {return offlineMode;}
    public Boolean getWeekendMode() {return weekendMode;}
    public void setOfflineMode(Boolean offlineMode) {this.offlineMode = offlineMode;}
    public void setWeekendMode(Boolean weekendMode) {this.weekendMode = weekendMode;}
    public String getWebLink() {return webLink;}
    public void setWebLink(String webLink) {this.webLink = webLink;}
    public String getICalLink() {return iCalLink;}
    public void setICalLink(String iCalLink) {this.iCalLink = iCalLink;}

    public String getDefaultMsg() {return defaultMsg;}
    public void setDefaultMsg(String defaultMsg) {this.defaultMsg = defaultMsg;}
    public String getMsg1() {return msg1;}
    public void setMsg1(String msg1) {this.msg1 = msg1;}
    public String getMsg2() {return msg2;}
    public void setMsg2(String msg2) {this.msg2 = msg2;}
    public String getMsg3() {return msg3;}
    public void setMsg3(String msg3) {this.msg3 = msg3;}
    public String getMsg4() {return msg4;}
    public void setMsg4(String msg4) {this.msg4 = msg4;}
    public String getMsg5() {return msg5;}
    public void setMsg5(String msg5) {this.msg5 = msg5;}
    public String getMsg6() {return msg6;}
    public void setMsg6(String msg6) {this.msg6 = msg6;}
    public String getPin() {return pin;}
    public void setPin(String pin) {this.pin = pin;}

    //Create an instance to use for getting and setting
    private static final SyncSettings holder = new SyncSettings();
    public static SyncSettings getInstance() {return holder;}
}