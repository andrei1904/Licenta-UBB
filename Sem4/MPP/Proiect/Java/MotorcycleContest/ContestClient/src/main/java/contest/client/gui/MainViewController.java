package contest.client.gui;

import contest.domain.*;
import contest.services.ContestException;
import contest.services.IContestObserver;
import contest.services.IContestServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MainViewController extends UnicastRemoteObject implements IContestObserver, Serializable {
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
    private Button buttonAddParticipant;


    private IContestServices server;
    private User user;

    public MainViewController() throws RemoteException {
        super();
    }

    public MainViewController(IContestServices server) throws RemoteException {
        super();
        this.server = server;
        init();
    }

    public void setServer(IContestServices s) {
        this.server = s;
        init();
    }

    public void setUser(User user) {
        this.user = user;
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

    void logout() {
        try {
            server.logout(user, this);
        } catch (ContestException e) {
            System.out.println("Logout error " + e);
        }
    }

    public void handleLogout(ActionEvent actionEvent) {
        logout();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        System.exit(0);
    }

    public void setData() {
        setRaces();
        setTeams();
        setEngineCapacity();
    }

    private void setRaces() {
        try {
            RaceDTO[] races = server.getRaces();
            modelRace.setAll(races);

        } catch (ContestException e) {
            System.out.println("Show Races error: " + e);
        }
    }

    public void setTeams() {
        try {
            String[] teamsNames = server.getTeamsNames();
            modelTeam.setAll(teamsNames);
            modelParticipantTeam.setAll(teamsNames);

        } catch (ContestException e) {
            System.out.println("Set Teams error: " + e);
        }
    }

    private void setEngineCapacity() {
        List<String> engine = new ArrayList<>();
        for (EngineCapacity engineCapacity : EngineCapacity.values()) {
            engine.add(engineCapacity.name());
        }
        modelEngine.setAll(engine);
    }

    public void handleSearchRaces() {
        String engine = comboBoxParticipantEngine.getSelectionModel().getSelectedItem();

        if (engine != null) {
            try {
                modelAvailableRaces.setAll(
                        server.getRacesByEngineCapacity(EngineCapacity.valueOf(engine)));
            } catch (ContestException e) {
                System.out.println("Search Races error: " + e);
            }

        } else {
            modelAvailableRaces.removeAll();
        }
    }

    public void handleSearch() {
        String team = comboBoxTeam.getSelectionModel().getSelectedItem();

        if (team == null) {
            MessageAlert.showErrorMessage(null, "Select a team!");
            return;
        }

        try {
            ParticipantDTO[] participants = server.getTeamMembers(team);
            modelParticipant.setAll(participants);
        } catch (ContestException e) {
            System.out.println("Search Members error: " + e);
        }
    }

    public void handleAddEntry() {
        buttonAddParticipant.setDisable(true);
        String name = textFieldParticipantName.getText();
        String team = comboBoxParticipantTeam.getSelectionModel().getSelectedItem();
        String engineCapacity = comboBoxParticipantEngine.getSelectionModel().getSelectedItem();
        Race race = comboBoxAvailableRaces.getSelectionModel().getSelectedItem();

        if (name == null || team == null || engineCapacity == null || race == null) {
            buttonAddParticipant.setDisable(false);
            MessageAlert.showErrorMessage(null, "Input error!");
            return;
        }

        try {
            server.addParticipantEntry(name, team, engineCapacity,
                    race.getName());

            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info",
                    "Participant: " + name +"  was succesfully added!");
        } catch (ContestException e) {
            MessageAlert.showErrorMessage(null, "Error!");
            buttonAddParticipant.setDisable(false);
        }
    }

    @Override
    public void participantEntryAdded(String raceName) throws ContestException {
        List<RaceDTO> raceDTOS = new ArrayList<>();
        for (RaceDTO raceDTO : modelRace) {
            if (raceDTO.getRaceName().equals(raceName)) {
                raceDTO.setNumberOfParticipants(
                        raceDTO.getNumberOfParticipants() + 1
                );
            }
            raceDTOS.add(raceDTO);
        }
        modelRace.setAll(raceDTOS);
        System.out.println("New Participant...");
        buttonAddParticipant.setDisable(false);
    }
}
