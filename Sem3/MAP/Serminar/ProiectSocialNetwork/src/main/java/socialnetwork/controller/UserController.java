package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import socialnetwork.domain.*;
import socialnetwork.service.AllService;
import socialnetwork.utils.events.ChangeEvent;
import socialnetwork.utils.observer.Observer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class UserController implements Observer<ChangeEvent<Prietenie>> {
    private AllService service;
    Stage stage;
    Utilizator user;
    ObservableList<DtoPrieten> model = FXCollections.observableArrayList();
    ObservableList<DtoPrieten> modelFriends = FXCollections.observableArrayList();
    ObservableList<String> modelMessages = FXCollections.observableArrayList();
    ObservableList<DtoPrieten> modelFriendsReports = FXCollections.observableArrayList();

    @FXML
    Label labelUserName;

    @FXML
    TableView<DtoPrieten> tableView;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnFirstName;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnLastName;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnDate;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldName1;

    @FXML
    TableView<DtoPrieten> tableViewFriends;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnName;

    @FXML
    TextArea textAreaMesaj;

    @FXML
    ListView<String> listViewMesaje;

    @FXML
    DatePicker datePickerFrom1;

    @FXML
    DatePicker datePickerFrom2;

    @FXML
    DatePicker datePickerTo1;

    @FXML
    DatePicker datePickerTo2;

    @FXML
    TableView<DtoPrieten> tableViewFriendsReports;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnNameReports;

    @FXML
    TextField textFieldNameReports;


    public void setService(AllService service, Stage stage, Utilizator user) {
        this.service = service;
        this.stage = stage;
        this.user = user;
        service.addPrietenieObserver(this);
        init();
        initModel();
        initDatePickers();
    }

    public void init() {
        labelUserName.setText("Welcome back, " + user.getLastName() + " " + user.getFirstName() + "!");

        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateString"));
        tableView.setItems(model);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableViewFriends.setItems(modelFriends);

        tableColumnNameReports.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableViewFriendsReports.setItems(modelFriendsReports);

        listViewMesaje.setItems(modelMessages);
    }

    private void initModel() {
        List<DtoPrieten> utilizatori = service.getPrieteniUser(user.getId().intValue());
        model.setAll(utilizatori);
        modelFriends.setAll(utilizatori);
        modelFriendsReports.setAll(utilizatori);
    }

    private void initDatePickers() {
        initDatePicker(datePickerFrom1);
        initDatePicker(datePickerFrom2);
        initDatePicker(datePickerTo1);
        initDatePicker(datePickerTo2);
    }

    private void initDatePicker(DatePicker datePicker) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) > 0);
            }
        });
    }


    public void handleAddMoreFriends() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../views/addFriendsView.fxml"));

            AnchorPane root = loader.load();

            Stage friendsStage = new Stage();
            friendsStage.setTitle("Users");
            friendsStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            friendsStage.setScene(scene);

            AdaugaPrieteniController adaugaPrieteniController = loader.getController();
            adaugaPrieteniController.setService(service, friendsStage, user);

            friendsStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteFriend() {
        DtoPrieten utilizator = tableView.getSelectionModel().getSelectedItem();


        if (utilizator != null) {
            service.deletePrietenie(user.getId().intValue(), utilizator.getId());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user was selected!");
            alert.setContentText("Select one user!");

            alert.showAndWait();
        }
    }

    public void initMessagesUtilizator(DtoPrieten utilizator) {
        List<Message> list = service.getMessages(user.getId().intValue(), utilizator.getId());
        List<String> mesaje = new ArrayList<>();

        if (list != null) {
            for (Message m : list) {
                String s = m.getFrom().getFirstName() + ":   " + m.getMessage();
                mesaje.add(s);
            }
            modelMessages.setAll(mesaje);
            listViewMesaje.scrollTo(modelMessages.size() - 1);
        }
    }

    @Override
    public void update() {
        initModel();
    }

    public void handleFriendRequests() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../views/friendRequestView.fxml"));

            AnchorPane root = loader.load();

            Stage friendsStage = new Stage();
            friendsStage.setTitle("Friend Requests");
            friendsStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            friendsStage.setScene(scene);

            CereriController adaugaPrieteniController = loader.getController();
            adaugaPrieteniController.setService(service, friendsStage, user);

            friendsStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleFilter(TextField textField, ObservableList<DtoPrieten> list) {
        String name = textField.getText();

        Predicate<DtoPrieten> byFirstNamePredicate = m -> m.getFirstName().contains(name);
        Predicate<DtoPrieten> byLastNamePredicate = m -> m.getLastName().contains(name);

        list.setAll(service.getPrieteniUser(user.getId().intValue())
                .stream()
                .filter(byFirstNamePredicate.or(byLastNamePredicate))
                .collect(Collectors.toList()));
    }

    public void handleFilterName() {
        handleFilter(textFieldName, model);
    }

    public void handleFilterNameMessage() {
        handleFilter(textFieldName1, modelFriends);
    }

    public void handleFilterNameReports() {
        handleFilter(textFieldNameReports, modelFriendsReports);
    }

    public void handleSendMessage() {
        DtoPrieten utilizator = tableViewFriends.getSelectionModel().getSelectedItem();
        String text = textAreaMesaj.getText();
        textAreaMesaj.setText("");

        if (utilizator != null) {
            if (!text.equals("")) {
                service.sendMessageToOne(user.getId().intValue(), utilizator.getId(), text);
            }
            initMessagesUtilizator(utilizator);
        } else {
            MessageAlert.showErrorMessage(stage, "Select a firend!");
        }

    }

    public void handleLoadMessages() {
        DtoPrieten utilizator = tableViewFriends.getSelectionModel().getSelectedItem();

        if (utilizator != null) {
            List<Message> list = service.getMessages(user.getId().intValue(), utilizator.getId());
            List<String> mesaje = new ArrayList<>();

            if (list != null) {
                for (Message m : list) {
                    String s = m.getFrom().getFirstName() + ":   " + m.getMessage();
                    mesaje.add(s);
                }
                modelMessages.setAll(mesaje);
                listViewMesaje.scrollTo(modelMessages.size() - 1);
            } else {
                modelMessages.setAll(mesaje);
            }
        }
    }

    public void handleSendMessageToMore() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../views/sendMessagesView.fxml"));

            AnchorPane root = loader.load();

            Stage messagesStage = new Stage();
            messagesStage.setTitle("Send Messages");
            messagesStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            messagesStage.setScene(scene);

            MessagesController messagesController = loader.getController();
            messagesController.setService(service, messagesStage, user);

            messagesStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleGeneratePdfFriendsMessages() {
        LocalDate dateFrom = datePickerFrom1.getValue();
        LocalDate dateTo = datePickerTo1.getValue();

        if (dateFrom == null) {
            MessageAlert.showErrorMessage(stage, "Select a \"from\" date!");
            return;
        }

        if (dateTo == null) {
            MessageAlert.showErrorMessage(stage, "Select a \"to\" date!");
            return;
        }

        if (dateFrom.compareTo(dateTo) > 0) {
            MessageAlert.showErrorMessage(stage, "Invalid dates!");
            return;
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);

        service.generatePdfFriendsMessages(dateFrom, dateTo, user, selectedDirectory);
        MessageAlert.showMessage(stage, Alert.AlertType.INFORMATION, "Succes", "The report was generated!");
    }

    public void handleGeneratePdfMessages() {
        LocalDate dateFrom = datePickerFrom2.getValue();
        LocalDate dateTo = datePickerTo2.getValue();

        if (dateFrom == null) {
            MessageAlert.showErrorMessage(stage, "Select a \"from\" date!");
            return;
        }

        if (dateTo == null) {
            MessageAlert.showErrorMessage(stage, "Select a \"to\" date!");
            return;
        }

        DtoPrieten prieten = tableViewFriendsReports.getSelectionModel().getSelectedItem();

        if (prieten == null) {
            MessageAlert.showErrorMessage(stage, "Select a friend!");
            return;
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);

        service.generatePdfMessages(dateFrom, dateTo, user, prieten.getId(), selectedDirectory);
        MessageAlert.showMessage(stage, Alert.AlertType.INFORMATION, "Succes", "The report was generated!");
    }
}
