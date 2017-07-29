package de.wohlers.RandomWordChooser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de.wohlers.RandomWordChooser/sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.init(primaryStage);
        primaryStage.setTitle("Random Word Chooser");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
