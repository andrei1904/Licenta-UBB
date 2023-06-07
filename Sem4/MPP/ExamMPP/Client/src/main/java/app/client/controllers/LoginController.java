package app.client.controllers;

import app.model.User;
import app.services.AppException;
import app.services.IServices;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class LoginController {
    @FXML
    TextField textFieldUsername;

    @FXML
    TextField textFieldPassword;

    private IServices service;
    private Parent parent;
    private MainController controller;

    public void setService(IServices service) {
        this.service = service;
    }

    public void setController(MainController controller) {
        this.controller = controller;
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
            User user = service.loginUser(username, password, controller);
            showMainWindow(user);

            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

        } catch (RemoteException | AppException e) {
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

        controller.setUser(user);
        controller.setStage(appStage);
        controller.setService(service);
        controller.initData();

        appStage.show();
    }
}
