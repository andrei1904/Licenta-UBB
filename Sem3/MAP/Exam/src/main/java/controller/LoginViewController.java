package controller;

import domain.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllService;
import javafx.scene.control.TextField;


import java.io.IOException;

public class LoginViewController {
    AllService service;

    public void setService(AllService service) {
        this.service = service;
    }

    @FXML
    TextField textFieldUsername;

    public void handleLogin() {
        String inputUsername = textFieldUsername.getText();

        if (service.existingUser(inputUsername)) {
            showUserInterface(service.getClient(inputUsername));
        } else {
            MessageAlert.showErrorMessage(null, "This username doesen't exist!\n");
        }
    }

    private void showUserInterface(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/mainView.fxml"));

            AnchorPane root = loader.load();

            Stage mainStage = new Stage();
            mainStage.setTitle(client.getName());
            mainStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            mainStage.setScene(scene);

            MainViewController mainViewController = loader.getController();
            mainViewController.setService(service, mainStage, client);

            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

