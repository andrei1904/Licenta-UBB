package socialnetwork.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.AllService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {
    private AllService service;


    @FXML
    TextField textFieldUserId;

    public void setService(AllService service) {
        this.service = service;
    }


    public void closeStage() {
        Platform.exit();
    }

    public void handleClickLogin() {
        int idUser = 0;

        try {
            idUser = Integer.parseInt(textFieldUserId.getText());
        } catch (NumberFormatException e) {
            textFieldUserId.setText("");

            MessageAlert.showErrorMessage(null,"Write an integer");
        }

        if (service.existaUser(idUser)) {
            showUserInterfaceDialog(service.getOneUser(idUser));
        } else {
            MessageAlert.showErrorMessage(null, "This user does not exist!");
        }
    }

    public void showUserInterfaceDialog(Utilizator user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../views/userView.fxml"));

            AnchorPane root = loader.load();

            Stage userStage = new Stage();
            userStage.setTitle("User profile");
            userStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            userStage.setScene(scene);

            UserController cererePrietenieController = loader.getController();
            cererePrietenieController.setService(service, userStage, user);

            userStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
