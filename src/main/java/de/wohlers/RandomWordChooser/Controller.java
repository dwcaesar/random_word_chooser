package de.wohlers.RandomWordChooser;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Controller {

    public TextField output;
    public ListView<String> inputList;
    public TextField inputField;
    public StackPane stackPane;
    private ObservableList<String> data = FXCollections.observableArrayList();
    private SecureRandom secureRandom = new SecureRandom();
    private Persistor persistor = new Persistor();
    private Stage primaryStage;


    public void chooseWordFromList(ActionEvent actionEvent) {
        if (!data.isEmpty()) {
            int itemIndex = (int) Math.floor(secureRandom.nextDouble() * data.size());
            output.setText(data.get(itemIndex));
        }
    }

    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
        inputList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inputList.setItems(data);
        onLoad(new ActionEvent());
    }

    public void addValue(ActionEvent actionEvent) { new EmptyStackException();
        String newValue = inputField.getText();

        if (!newValue.isEmpty() && !data.contains(newValue)) {
            data.add(newValue);
        }
        inputField.clear();
    }

    public void removeItem(Event event) {
        ArrayList<String> selectedItems = new ArrayList<>(inputList.getSelectionModel().getSelectedItems());
        if (event instanceof KeyEvent) {
            KeyCode keyCode = ((KeyEvent) event).getCode();
            if (keyCode.equals(KeyCode.DELETE) || keyCode.equals(KeyCode.BACK_SPACE)) {
                selectedItems.forEach(data::remove);
            }
        } else if (event instanceof ActionEvent) {
            selectedItems.forEach(data::remove);
        }
    }

    public void onLoad(ActionEvent actionEvent) {
        data.setAll(persistor.load());
        if (primaryStage.getScene() != null) {
            showMessage("Daten geladen");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON","*.json"));
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.showOpenDialog(primaryStage.getOwner());
        }
    }

    private void showMessage(String message) {
        Label textField = new Label(message);
        textField.setBackground(new Background(new BackgroundFill(Paint.valueOf(Color.LIGHTGRAY.toString()), new CornerRadii(3), new Insets(-2))));
        stackPane.getChildren().add(textField);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(e -> stackPane.getChildren().remove(textField));
        pauseTransition.play();
    }

    public void onSave(ActionEvent actionEvent) {
        persistor.save(data);
        if (primaryStage.getScene() != null) {
            showMessage("Daten gespeichert");
        }
    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void clearList(ActionEvent actionEvent) {
        data.clear();
    }
}