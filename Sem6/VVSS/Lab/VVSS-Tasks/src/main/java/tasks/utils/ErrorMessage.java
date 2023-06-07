package tasks.utils;

import javafx.scene.control.Alert;

public class ErrorMessage {

    public void showError(String title, String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
}
