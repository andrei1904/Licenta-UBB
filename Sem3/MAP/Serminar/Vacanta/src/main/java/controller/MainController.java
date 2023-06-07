package controller;

import domain.Hotel;
import domain.Location;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import service.AllService;

import java.io.IOException;
import java.util.List;

public class MainController {
    AllService service;

    ObservableList<Location> modelLocation = FXCollections.observableArrayList();
    ObservableList<Hotel> modelHotel = FXCollections.observableArrayList();

    public void setService(AllService service) {
        this.service = service;
        init();
        initModel();
    }

    public void closeStage() {
        Platform.exit();
    }

    @FXML
    ComboBox<Location> comboBoxLocation;

    @FXML
    TableView<Hotel> tableViewHotel;

    @FXML
    TableColumn<Hotel, String> tableColumnLocation;

    @FXML
    TableColumn<Hotel, String> tableColumnName;

    @FXML
    TableColumn<Hotel, String> tableColumnRoomNo;

    @FXML
    TableColumn<Hotel, String> tableColumnPricePerNight;

    @FXML
    TableColumn<Hotel, String> tableColumnType;


    public void init() {
        tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        tableColumnRoomNo.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        tableColumnPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableViewHotel.setItems(modelHotel);
        comboBoxLocation.setItems(modelLocation);

    }

    private void initModel() {
        List<Hotel> hotels = service.getAllHotels();
        List<Location> locations = service.getAllLocations();

        modelHotel.setAll(hotels);
        modelLocation.setAll(locations);
    }

    private void initModelHotels(double id) {
        List<Hotel> hotels = service.getHotelsFromLocation(id);
        modelHotel.setAll(hotels);
    }

    public void changeSelectionAction(ActionEvent actionEvent) {
        double locationId = comboBoxLocation.getSelectionModel().getSelectedItem().getId();
        initModelHotels(locationId);
    }

    public void openWindowAction(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/dateView.fxml"));

            AnchorPane root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(tableViewHotel.getSelectionModel().getSelectedItem().getHotelName());
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            DateController dateController = loader.getController();
            dateController.setService(service, stage);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
