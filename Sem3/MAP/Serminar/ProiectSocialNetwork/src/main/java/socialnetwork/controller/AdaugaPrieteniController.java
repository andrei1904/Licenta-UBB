package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import socialnetwork.domain.DtoPrieten;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.RepoException;
import socialnetwork.service.AllService;
import socialnetwork.utils.events.ChangeEvent;
import socialnetwork.utils.observer.Observer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AdaugaPrieteniController implements Observer<ChangeEvent<Prietenie>> {
    private AllService service;
    Stage stage;
    Utilizator user;
    ObservableList<Utilizator> model = FXCollections.observableArrayList();

    @FXML
    TableView<Utilizator> tableView;

    @FXML
    TableColumn<Utilizator, String> tableColumnFirstName;

    @FXML
    TableColumn<Utilizator, String> tableColumnLastName;

    @FXML
    TextField textFieldName;

    public void setService(AllService service, Stage stage, Utilizator user) {
        this.service = service;
        this.stage = stage;
        this.user = user;
        service.addPrietenieObserver(this);
        init();
        initModel();
    }

    public void init() {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableView.setItems(model);
    }

    private List<Utilizator> getNotFriends() {
        Iterable<Utilizator> utilizatori = service.getAllUsers();

        List<Utilizator> utilizatorList = StreamSupport.stream(utilizatori.spliterator(), false)
                .collect(Collectors.toList());
        List<DtoPrieten> prieteni = service.getPrieteniUser(user.getId().intValue());

        utilizatorList.removeIf(x -> (x.getId().equals(user.getId())));

        for (DtoPrieten prieten : prieteni) {
            utilizatorList.removeIf(x -> (x.getId().intValue() == prieten.getId()));
        }

        return utilizatorList;
    }

    private void initModel() {
        model.setAll(getNotFriends());
    }

    public void handleAddFriend() {
        Utilizator utilizator = tableView.getSelectionModel().getSelectedItem();

        if (utilizator != null) {
            try {
                service.addPrietenie(user.getId().intValue(), utilizator.getId().intValue());
                stage.close();
            } catch (RepoException e) {
                MessageAlert.showErrorMessage(null, "There is already one request!");
            }
        } else {
            MessageAlert.showErrorMessage(null, "No user was selected!");
        }
    }

    public void handleFilterName() {
        handleFilter();
    }

    public void handleFilter() {
        String name = textFieldName.getText();

        Predicate<Utilizator> byFirstNamePredicate = m -> m.getFirstName().contains(name);
        Predicate<Utilizator> byLastNamePredicate = m -> m.getLastName().contains(name);

        model.setAll(getNotFriends()
                .stream()
                .filter(byFirstNamePredicate.or(byLastNamePredicate))
                .collect(Collectors.toList()));
    }

    @Override
    public void update() {
        initModel();
    }
}
