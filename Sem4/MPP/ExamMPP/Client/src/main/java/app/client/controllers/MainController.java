package app.client.controllers;

import app.model.User;
import app.model.UserPointsDto;
import app.model.UserWordDto;
import app.services.AppException;
import app.services.IObserver;
import app.services.IServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MainController extends UnicastRemoteObject implements IObserver, Serializable {
    ObservableList<UserPointsDto> modelResults = FXCollections.observableArrayList();
    ObservableList<UserWordDto> modelGame = FXCollections.observableArrayList();

    @FXML
    Button buttonLogout;

    @FXML
    Button buttonStartGame;

    @FXML
    TextField textFieldWord;

    @FXML
    TableView<UserPointsDto> tableViewResults;

    @FXML
    TableColumn<String, UserPointsDto> tableColumnResultsUsername;

    @FXML
    TableColumn<String, UserPointsDto> tableColumnPoints;

    @FXML
    TableView<UserWordDto> tableViewGame;

    @FXML
    TableColumn<String, UserWordDto> tableColumnGameUsername;

    @FXML
    TableColumn<String, UserWordDto> tableColumnWord;

    @FXML
    TextField textFieldLetter;

    @FXML
    Button buttonSendLetter;

    private User user;
    private Stage stage;
    private IServices service;

    public MainController() throws RemoteException {
    }

    public void initData() {
        buttonStartGame.setDisable(true);
        tableColumnGameUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        tableColumnResultsUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnPoints.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableViewGame.setItems(modelGame);
        tableViewResults.setItems(modelResults);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setService(IServices service) {
        this.service = service;
    }

    public void handleLogout() {
        try {
            service.logoutUser(user.getUsername(), this);

            stage.hide();
            System.exit(0);
        } catch (AppException | RemoteException e) {
            MessageAlert.showErrorMessage(stage, e.getMessage());
        }
    }

    @Override
    public void canStart() throws RemoteException, AppException {
        Platform.runLater(() ->{
            buttonStartGame.setDisable(false);
        });
    }

    @Override
    public void roundDone() throws RemoteException, AppException {
        Platform.runLater(() ->{
            buttonStartGame.setDisable(true);
            try {
                modelGame.setAll(service.getGame());
            } catch (RemoteException | AppException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void gameEnded() throws RemoteException, AppException {
        Platform.runLater(() ->{
            buttonLogout.setDisable(false);
            try {
                modelGame.setAll(service.getGame());
                modelResults.setAll(service.getResults());
            } catch (RemoteException | AppException e) {
                e.printStackTrace();
            }
        });
    }

    public void handleStartGame(ActionEvent actionEvent) {
        String word = textFieldWord.getText();

        if (word.equals("")) {
            MessageAlert.showErrorMessage(stage, "Enter a word!");
            return;
        }

        try {
            service.startGame(user, word);

            buttonLogout.setDisable(true);
            MessageAlert.showMessage(stage, Alert.AlertType.INFORMATION, "info", "Game will start!");
        } catch (RemoteException | AppException e) {
            e.printStackTrace();
        }
    }

    public void handleSendLetter(ActionEvent actionEvent) {
        String letter = textFieldLetter.getText();

        if (letter.equals("") || letter.length() > 1) {
            MessageAlert.showErrorMessage(stage, "Enter a letter!");
            return;
        }

        UserWordDto chosenUser = tableViewGame.getSelectionModel().getSelectedItem();
        if (chosenUser == null) {
            MessageAlert.showErrorMessage(stage, "Chose an user!");
            return;
        }

        if (chosenUser.getUsername().equals(user.getUsername())) {
            MessageAlert.showErrorMessage(stage, "Chose other user!");
            tableViewGame.getSelectionModel().clearSelection();
            return;
        }

        try {
            service.checkLetter(user, chosenUser.getUsername(), letter);
            MessageAlert.showMessage(stage, Alert.AlertType.INFORMATION, "info", "Letter sent!");

        } catch (RemoteException | AppException e) {
            e.printStackTrace();
        }
    }
}
