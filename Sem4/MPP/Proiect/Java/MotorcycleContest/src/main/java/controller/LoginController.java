package controller;

import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private AllService service;

    public void setService(AllService service) {
        this.service = service;
    }

    public void clickToLogin(MouseEvent mouseEvent) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        User user = service.verifyUser(username, password);
        if (user != null) {
            showInterfaceDialog(user);
        } else {
            MessageAlert.showErrorMessage(null, "Wrong user!");
        }
    }

    private void showInterfaceDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/mainView.fxml"));

            AnchorPane root = loader.load();

            Stage userStage = new Stage();
            userStage.setTitle("User profile");
            userStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            userStage.setScene(scene);

            MainViewController mainViewController = loader.getController();
            mainViewController.setService(service);

            userStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
