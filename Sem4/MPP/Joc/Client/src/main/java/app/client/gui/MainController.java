package app.client.gui;

import app.model.User;
import app.services.AppException;
import app.services.IAppObserver;
import app.services.IAppServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MainController extends UnicastRemoteObject implements IAppObserver, Serializable {
    @FXML
    Button buttonStart;

    @FXML
    Button buttonLogout;

    @FXML
    Label labelOtherPlayers;

    private IAppServices server;
    private User user;
    private Stage stage;

    public MainController() throws RemoteException {
        super();
    }

    public MainController(IAppServices server) throws RemoteException {
        super();
        this.server = server;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setServer(IAppServices server) {
        this.server = server;
    }

    public void handleLogout() {
        try {
            server.logoutUser(user.getUsername(), this);

            stage.hide();
            System.exit(0);
        } catch (AppException e) {
            MessageAlert.showErrorMessage(stage, e.getMessage());
        }
    }

    public void handleStart() {
        try {
            boolean res = server.startGame(user.getUsername());

            buttonStart.setDisable(true);

            if (!res) {
                labelOtherPlayers.setText("Waiting for others!");
            }

        } catch (AppException e) {
            MessageAlert.showErrorMessage(stage, e.getMessage());
        }
    }

    @Override
    public synchronized void gameStarted(List<String> participants) {
        StringBuilder text = new StringBuilder("Other players: ");
        for (String x : participants) {
            if (!x.equals(user.getUsername())) {
                text.append(x);
                text.append(" ");
            }
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelOtherPlayers.setText(text.toString());
                buttonLogout.setDisable(true);
            }
        });
    }
}
