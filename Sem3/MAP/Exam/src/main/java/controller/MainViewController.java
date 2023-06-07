package controller;

import domain.Client;
import domain.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AllService;
import utils.events.ChangeEvent;
import utils.observer.Observer;

import java.time.LocalDate;
import java.util.List;

public class MainViewController implements Observer<ChangeEvent<Flight>> {
    private AllService service;
    private Stage stage;
    private Client client;

    ObservableList<Flight> modelFlight = FXCollections.observableArrayList();
    ObservableList<String> modelFrom = FXCollections.observableArrayList();
    ObservableList<String> modelTo = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> comboBoxFrom;

    @FXML
    ComboBox<String> comboBoxTo;

    @FXML
    DatePicker datePicker;

    @FXML
    TableView<Flight> tableViewFlights;

    @FXML
    TableColumn<Flight, String> tableColumnId;

    @FXML
    TableColumn<Flight, String> tableColumnFrom;

    @FXML
    TableColumn<Flight, String> tableColumnTo;

    @FXML
    TableColumn<Flight, String> tableColumnDepartureTime;

    @FXML
    TableColumn<Flight, String> tableColumnLandingTime;

    @FXML
    TableColumn<Flight, String> tableColumnSeats;

    @FXML
    TableColumn<Flight, String> tableColumnAvailableSeats;


    public void setService(AllService service, Stage mainStage, Client client) {
        this.service = service;
        this.stage = mainStage;
        this.client = client;
        service.addFlightObserver(this);
        init();
        initModel();
    }

    private void init() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        tableColumnDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        tableColumnLandingTime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
        tableColumnSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        tableColumnAvailableSeats.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));

        tableViewFlights.setItems(modelFlight);

        comboBoxFrom.setItems(modelFrom);
        comboBoxTo.setItems(modelTo);
    }

    private void initModel() {
        service.setAvailableSeats();
        List<Flight> flights = service.getAllFlights();
        modelFlight.setAll(flights);

        List<String> from = service.getFromDestinations();
        List<String> to = service.getToDestinations();
        modelFrom.setAll(from);
        modelTo.setAll(to);
    }

    private void initFlightsModel(List<Flight> list) {
        modelFlight.setAll(list);
    }


    public void handleSelectDate() {
        String from = comboBoxFrom.getSelectionModel().getSelectedItem();
        String to = comboBoxTo.getSelectionModel().getSelectedItem();

        if (from == null || to == null) {
            MessageAlert.showErrorMessage(stage, "Select from and to destinations!\n");
            return;
        }

        LocalDate date = datePicker.getValue();

        if (date == null) {
            MessageAlert.showErrorMessage(stage, "Select a date!\n");
            return;
        }

        List<Flight> res = service.filterFlights(from, to, date);
        initFlightsModel(res);
    }

    public void handleChangeSelectionFrom() {
        String from = comboBoxFrom.getSelectionModel().getSelectedItem();
        String to = comboBoxTo.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();

        if (from != null && to != null && date != null)
            handleSelectDate();
    }

    public void handleChangeSelectionTo() {
        String from = comboBoxFrom.getSelectionModel().getSelectedItem();
        String to = comboBoxTo.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();

        if (from != null && to != null && date != null)
            handleSelectDate();
    }


    public void handlePurchaseTicket() {
        Flight flight = tableViewFlights.getSelectionModel().getSelectedItem();

        if (flight == null) {
            MessageAlert.showErrorMessage(stage, "Select a flight!\n");
            return;
        }

        service.addPurchase(client, flight);
        MessageAlert.showMessage(stage, Alert.AlertType.INFORMATION, "Confirmation",
                "You purchased the ticket!\n");
    }

    @Override
    public void update() {
        initModel();
    }
}
