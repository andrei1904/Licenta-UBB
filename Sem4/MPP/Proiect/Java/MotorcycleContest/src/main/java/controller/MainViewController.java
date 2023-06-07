package controller;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AllService;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MainViewController {
    ObservableList<RaceDTO> modelRace = FXCollections.observableArrayList();
    ObservableList<ParticipantDTO> modelParticipant = FXCollections.observableArrayList();
    ObservableList<String> modelTeam = FXCollections.observableArrayList();
    ObservableList<String> modelParticipantTeam = FXCollections.observableArrayList();
    ObservableList<String> modelEngine = FXCollections.observableArrayList();
    ObservableList<Race> modelAvailableRaces = FXCollections.observableArrayList();

    @FXML
    private TableView<RaceDTO> tableViewRaces;

    @FXML
    private TableColumn<Integer, RaceDTO> tableColumnRaceName;

    @FXML
    private TableColumn<Integer, RaceDTO> tableColumnEngine;

    @FXML
    private TableColumn<Integer, RaceDTO> tableColumnNoParticipants;


    @FXML
    private TableView<ParticipantDTO> tableViewParticipants;

    @FXML
    private TableColumn<Integer, ParticipantDTO> tableColumnName;

    @FXML
    private TableColumn<Integer, ParticipantDTO> tableColumnEngineCapacity;

    @FXML
    private ComboBox<String> comboBoxTeam;


    @FXML
    private ComboBox<String> comboBoxParticipantTeam;

    @FXML
    private TextField textFieldParticipantName;

    @FXML
    private ComboBox<String> comboBoxParticipantEngine;

    @FXML
    private ComboBox<Race> comboBoxAvailableRaces;

    @FXML
    private Button buttonLogout;


    private AllService service;

    public void setService(AllService service) {
        this.service = service;

        init();
        initRaces();
        initTeams();
        initEngineCapacity();
    }

    private void init() {
        tableColumnRaceName.setCellValueFactory(new PropertyValueFactory<>("raceName"));
        tableColumnEngine.setCellValueFactory(new PropertyValueFactory<>("requiredEngineCapacity"));
        tableColumnNoParticipants.setCellValueFactory(new PropertyValueFactory<>("numberOfParticipants"));
        tableViewRaces.setItems(modelRace);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnEngineCapacity.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        tableViewParticipants.setItems(modelParticipant);

        comboBoxTeam.setItems(modelTeam);
        comboBoxParticipantTeam.setItems(modelParticipantTeam);
        comboBoxParticipantEngine.setItems(modelEngine);
        comboBoxAvailableRaces.setItems(modelAvailableRaces);
    }

    private void initRaces() {
        List<RaceDTO> races = service.getDTORaces();
        modelRace.setAll(races);
    }

    private void initTeams() {
        List<String> teams = service.getTeamsNames();
        modelTeam.setAll(teams);
        modelParticipantTeam.setAll(teams);
    }

    private void initEngineCapacity() {
        List<String> engine = new ArrayList<>();
        for (EngineCapacity engineCapacity : EngineCapacity.values()) {
            engine.add(engineCapacity.name());
        }
        modelEngine.setAll(engine);
    }


    public void handleSearch() {
        String team = comboBoxTeam.getSelectionModel().getSelectedItem();

        if (team == null) {
            MessageAlert.showErrorMessage(null, "Select a team!");
            return;
        }

        List<ParticipantDTO> participants = service.getDTOParticipants(team);
        modelParticipant.setAll(participants);
    }


    public void handleAddEntry() {
        String name = textFieldParticipantName.getText();
        String team = comboBoxParticipantTeam.getSelectionModel().getSelectedItem();
        String engineCapacity = comboBoxParticipantEngine.getSelectionModel().getSelectedItem();
        Race race = comboBoxAvailableRaces.getSelectionModel().getSelectedItem();

        if (name == null || team == null || engineCapacity == null || race == null) {
            MessageAlert.showErrorMessage(null, "Input error!");
            return;
        }

        Participant participant = service.addParticipant(name, team, engineCapacity);
        service.addEntry(participant, race);

        MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info",
                "Participant was succesfully added!");
        initRaces();
    }

    public void handleSearchRaces() {
        String engine = comboBoxParticipantEngine.getSelectionModel().getSelectedItem();
        if (engine != null) {
            List<Race> races = new ArrayList<>(service.raceByEngineCapacity(EngineCapacity.valueOf(engine)));
            modelAvailableRaces.setAll(races);

        } else {
            modelAvailableRaces.removeAll();
        }
    }

    public void handleLogout() {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();

        stage.close();
    }
}
