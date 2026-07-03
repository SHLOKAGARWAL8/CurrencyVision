package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String APP_TITLE = "Real-Time Currency Converter";

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("FXML = " + getClass().getResource("/fxml/MainView.fxml"));

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/MainView.fxml"));

            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(
                    getClass().getResource("/css/style.css").toExternalForm());

            stage.setTitle(APP_TITLE);

            try {
                stage.getIcons().add(
                        new Image(getClass().getResourceAsStream("/images/icon.png")));
            } catch (Exception ignored) {
            }

            stage.setScene(scene);

            stage.setMinWidth(1200);
            stage.setMinHeight(750);

            stage.centerOnScreen();

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}