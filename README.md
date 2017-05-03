# D-Sign-Tablet-App
Senior Project Tablet App

Responsiblities:

Eric Johnson:
- Main Activity and Settings Activity Layouts: Completed
- Ability to upload and download from Dropbox: Completed
- User defined sync schedule with Dropbox: Completed
- Settings Activity PIN protected: Completed
- Name, Room #, and Preset Messages update on Main Activity: Completed
- Enable/Disable Wifi for syncing: Incomplete
- Ability to display image instead of text for main message: Incomplete

Justin Rhea:
- Design schedule page layout
- Set up WebView of Google Calendar on Schedule page (Active on the Tablet)
- Display warning attempts when Pin code entered is wrong
- Design day of week page to show each day of the week with Monday-Friday (default) and Sunday-Friday (Weekend toggle is on). In this page, user will be able to swipe to the specific day or select the tab the day want to view (*)
- Day of week page show each day in detail (*)
*(Not active on the Tablet, but still saved in the project)

Our senior project consisted of developing an app that would act as a nameplate for an office. Students and employees are often frustrated when they go to an office of a professor or co-worker and find that they are not there. This app allows professors or office workers to have an updatable nameplate that can display messages as to their whereabouts or other such pertinent information. It also has a page with a dedicated schedule that is linked to Google calendars.

The project consists of two apps linked via Dropbox. 

The app contained within this repository was designed to sit on an Android e-ink tablet that acts as an office nameplate. The main page displays the room number, the office owner's name, and message of the owner's choosing. There are six links at the top of the page to change the displayed message as the owner enters or leaves the office. In the top right is a link to the settings page. At the bottom are navigation links consisting of a link to the schedule page and link to the main page.

![Alt text](/screenshots/dSignMain.png)

The settings page is PIN protected to keep unauthorized users from making changes to the sign.

![Alt text](/screenshots/dSignPin.png)

The settings page consists of two tabs. The first (General Settings) allows the user to update the name, room number, set a PIN for the app, change the six preset messages, enable an offline mode (save tablet battery), set which hours of their schedule they would like to have shown on the schedule page, and enable weekend mode that shows weekend days on the schedule.

![Alt_text](/screenshots/dSignGenSettings.png)

The second tab (Preset Messages) allows the user to change the six preset messages available on the Main Activity.

![Alt_text](/screenshots/dSignMesSettings.png)


The schedule page allows students to view the current week of the professor's schedule.  On the schedule page, there are buttons on the labeled days that can navigate the user to the day of the week page to show the message of the day. The schedule page can be updated through the Google Calendar from the professor's end.  


A mobile app was developed by another part of our project team using Xarmarin. The idea behind that part of the project is to have an app on the nameplate owners phone, so that they can update the sign from anywhere with an internet connection. 
