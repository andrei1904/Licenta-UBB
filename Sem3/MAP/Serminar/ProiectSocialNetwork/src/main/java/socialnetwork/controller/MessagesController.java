package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import socialnetwork.domain.DtoPrieten;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.AllService;
import javafx.scene.control.*;
import socialnetwork.utils.events.ChangeEvent;
import socialnetwork.utils.observer.Observer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MessagesController implements Observer<ChangeEvent<Prietenie>> {
    private AllService service;
    Stage stage;
    Utilizator user;
    ObservableList<DtoPrieten> model = FXCollections.observableArrayList();

    @FXML
    TableView<DtoPrieten> tableView;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnFirstName;

    @FXML
    TableColumn<DtoPrieten, String> tableColumnLastName;

    @FXML
    TextField textFieldName;

    @FXML
    TextArea textAreaMessage;

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
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView.setRowFactory(tableView2 -> {
            final TableRow<DtoPrieten> row = new TableRow<>();

            row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                final int index = row.getIndex();

                if (index >= 0 && index < tableView.getItems().size()) {
                    if (tableView.getSelectionModel().isSelected(index))
                        tableView.getSelectionModel().clearSelection(index);
                    else
                        tableView.getSelectionModel().select(index);

                    event.consume();
                }
            });
            return row;
        });
    }

    private void initModel() {
        List<DtoPrieten> utilizatori = service.getPrieteniUser(user.getId().intValue());
        model.setAll(utilizatori);
    }

    public void handleSendMessage() {
        List<DtoPrieten> list = tableView.getSelectionModel().getSelectedItems();
        List<Integer> to = list.stream()
                .map(DtoPrieten::getId)
                .collect(Collectors.toList());

        String text = textAreaMessage.getText();
        textAreaMessage.setText("");

        if (!text.equals("")) {
            service.sendMessageToMore(user.getId().intValue(), to, text);
        }
    }

    @Override
    public void update() {
        initModel();
    }
}
