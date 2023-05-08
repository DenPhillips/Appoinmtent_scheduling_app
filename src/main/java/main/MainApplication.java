package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/view/Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Scheduling Application");
        String lang = Locale.getDefault().getLanguage();
        if(lang == "fr"){
            stage.setTitle("Demande de planification");
        }
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();


    }
}