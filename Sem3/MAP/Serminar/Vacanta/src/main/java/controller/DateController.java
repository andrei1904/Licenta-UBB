package controller;

import domain.SpecialOffer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AllService;
import utils.observer.Observable;

import java.util.List;

public class DateController {
    private AllService service;
    Stage stage;

    ObservableList<SpecialOffer> modelSpecialOffer = FXCollections.observableArrayList();

    public void setService(AllService service, Stage stage) {
        this.service = service;
        this.stage = stage;
    }

    @FXML
    DatePicker datePickerFrom;

    @FXML
    DatePicker datePickerTo;

    @FXML
    TableView<SpecialOffer> tableViewOffers;

    @FXML
    TableColumn<SpecialOffer, String> tableColumnStart;

    @FXML
    TableColumn<SpecialOffer, String> tableColumnEnd;

    @FXML
    TableColumn<SpecialOffer, String> tableColumnPercents;

    void init() {
        tableColumnStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableColumnEnd.setCellValueFactory(new PropertyValueFactory<>("startEnd"));
        tableColumnPercents.setCellValueFactory(new PropertyValueFactory<>("percents"));
        tableViewOffers.setItems(modelSpecialOffer);
    }

    void initModel() {
        List<SpecialOffer> specialOffers;
    }

}
