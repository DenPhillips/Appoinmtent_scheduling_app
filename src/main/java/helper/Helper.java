package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * class with helpful functions
 */
public class Helper {
    /**
     * variable for user that has logged in
     */
    public static User loggedInUser = null;
    /**
     * formatter to format display time
     */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  hh:mm a");

    /**
     * @param text
     * The name of the field
     * @return
     * an error alert
     */
    public static Alert Error(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR,text + " field is blank.");
        alert.setTitle("Blank");
        alert.showAndWait().filter(response -> response == ButtonType.OK);
        return alert;
    }

    /**
     * @return
     * the names of the months
     */
    public static ObservableList<String> Months() {
        ObservableList<String> getMonths = FXCollections.observableArrayList();
        getMonths.addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        return getMonths;
    }

    /**
     * @return
     * AM and PM
     */
    public static ObservableList<String> AMPM() {
        ObservableList<String> getAPM = FXCollections.observableArrayList();
        getAPM.add("AM");
        getAPM.add("PM");
        return getAPM;
    }

    /**
     * @return
     * numbers 01-12
     */
    public static ObservableList<String> hours() {
        ObservableList<String> get_hours = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            get_hours.add((String.format("%02d", i)));
        }
        return get_hours;
    }

    /**
     * @return
     * numbers 00-59
     */
    public static ObservableList<String> minutes() {
        ObservableList<String> get_minutes = FXCollections.observableArrayList();
        for (int i = 0; i < 60; i++) {
            get_minutes.add((String.format("%02d", i)));
        }
        return get_minutes;
    }

    /**
     * @param hour
     * @param min
     * @param date
     * @return
     * UTC time of local time
     */
    public static String UTCtime(String hour, String min, String date) {
        String Time = hour + ":" + min + ":00";
        String DateTime = date + " " + Time;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime sdt = LocalDateTime.parse(DateTime, format);
        return sdt.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).format(format);

    }

    /**
     * @param hour
     * @param min
     * @param date
     * @return
     * local time of open time est
     */
    public static int ESTOpen(String hour, String min, String date) {
        String Time = hour + ":" + min + ":00";
        String DateTime = date + " " + Time;
        String openESTTime = date + " " + "08:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDT = LocalDateTime.parse(DateTime, format);
        LocalDateTime openDT = LocalDateTime.parse(openESTTime, format);
        return localDT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).format(format).compareTo(openDT.atZone(ZoneId.systemDefault()).format(format));

    }

    /**
     * @param hour
     * @param min
     * @param date
     * @return
     * local time of closing time est
     */
    public static int ESTClose(String hour, String min, String date) {
        String Time = hour + ":" + min + ":00";
        String DateTime = date + " " + Time;
        String closeESTTime = date + " " + "22:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDT = LocalDateTime.parse(DateTime, format);
        LocalDateTime closeDT = LocalDateTime.parse(closeESTTime, format);
        return localDT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).format(format).compareTo(closeDT.atZone(ZoneId.systemDefault()).format(format));

    }


}
