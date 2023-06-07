package app.client.gui;

import app.model.User;
import app.services.AppException;
import app.services.IAppServices;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    public TextField textFieldUsername;

    @FXML
    public TextField textFieldPassword;

    private IAppServices server;
    private MainController mainController;
    private Parent parent;

    public void setServer(IAppServices server) {
        this.server = server;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void handleLogin(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        if (username.equals("") || password.equals("")) {
            MessageAlert.showErrorMessage(null, "Enter username and password!");
            return;
        }

        try {
            User user = server.loginUser(username, password, mainController);

            if (user != null) {
                showMainWindow(user);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        } catch (AppException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    public void showMainWindow(User user) {
        Stage appStage = new Stage();
        appStage.setTitle("User:" + user.getUsername());

        appStage.setScene(new Scene(parent));

        appStage.initModality(Modality.WINDOW_MODAL);
        appStage.setOnCloseRequest(Event::consume);
//        appStage.setOnHiding(event -> handleLogout());
//
        mainController.setUser(user);
        mainController.setStage(appStage);
//        mainController.initData();

        appStage.show();
    }
}
