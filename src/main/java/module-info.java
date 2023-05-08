module Software_II {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main to javafx.fxml;
    exports main;
    exports controller;
    opens controller to javafx.fxml;
    opens model to javafx.fxml;
    exports model;


}