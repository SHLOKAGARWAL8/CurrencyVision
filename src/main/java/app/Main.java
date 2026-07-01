package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/main.fxml"));

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm());

        stage.setTitle("Real-Time Currency Converter");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(750);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}