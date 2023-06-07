package controller;

import domain.Nota;
import domain.NotaDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceManager;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotaController {

    int nrOfClicks = 0;
    ObservableList<NotaDto> modelGrade = FXCollections.observableArrayList();
    List<String> modelTema;
    private ServiceManager service;


    @FXML
    TableColumn<NotaDto, String> tableColumnName;
    @FXML
    TableColumn<NotaDto, String> tableColumnTema;
    @FXML
    TableColumn<NotaDto, Double> tableColumnNota;
    @FXML
    TableColumn<NotaDto, String> tableColumnProfesor;
    @FXML
    TableView<NotaDto> tableViewNote;
    @FXML
    TextField textFieldDisplayNumber;
    @FXML
    TextField textFieldTema;
    @FXML
    TextField textFieldNota;
    @FXML
    TextField textFieldNume;


    @FXML
    public void initialize() {
        System.out.println("ok0");
        tableColumnName.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("studentName"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("temaId"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<NotaDto, Double>("nota"));
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("profesor"));
        tableViewNote.setItems(modelGrade);

        textFieldNota.textProperty().addListener(e -> handleFilter());
        textFieldTema.textProperty().addListener(e -> handleFilter());
        textFieldNume.textProperty().addListener(e -> handleFilter());
    }

    private List<NotaDto> getNotaDTOList() {
        return service.findAllGrades()
                .stream()
                .map(x -> new NotaDto(x.getStudent().getName(), x.getTema().getId(),
                        x.getValue(), x.getProfesor()))
                .collect(Collectors.toList());
    }

    private void handleFilter() {
        Predicate<NotaDto> nameStartsWith = nota ->
                nota.getStudentName().startsWith(textFieldNume.getText());

        Predicate<NotaDto> tema = nota -> nota.getTemaId().startsWith(textFieldTema.getText());

        Predicate<NotaDto> nota = x -> {
            try {
                return x.getNota() > Double.parseDouble(textFieldNota.getText());
            } catch (NumberFormatException e) {
                return true;
            }
        };

        modelGrade.setAll(getNotaDTOList().stream()
                .filter(nameStartsWith.and(tema).and(nota))
                .collect(Collectors.toList()));
    }


    public void setService(ServiceManager service) {
        this.service = service;
        modelGrade.setAll(getNotaDTOList());
    }

    public void handleClickButtonCounter(ActionEvent actionEvent) {
        nrOfClicks += 1;
        textFieldDisplayNumber.setText(String.valueOf(nrOfClicks));
    }
}
