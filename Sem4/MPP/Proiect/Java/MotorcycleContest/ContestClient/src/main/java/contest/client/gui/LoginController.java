package contest.client.gui;

import contest.domain.User;
import contest.services.ContestException;
import contest.services.IContestServices;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;


    private IContestServices server;
    private MainViewController mainCtrl;

    Parent mainContestParent;

    public void setServer(IContestServices server) {
        this.server = server;
    }

    public void setMainController(MainViewController mainViewController) {
        this.mainCtrl = mainViewController;
    }

    public void setParent(Parent parent) {
        mainContestParent = parent;
    }

    public void pressLogin(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        User crtUser = new User(username, password);

        try {
            server.login(crtUser, mainCtrl);

            Stage stage = new Stage();
            stage.setTitle("MotorcycleContest window for " + crtUser.getUsername());
            stage.setScene(new Scene(mainContestParent));

            stage.setOnCloseRequest(event -> {
                mainCtrl.logout();
                System.exit(0);
            });

            stage.setOnCloseRequest(Event::consume);
            stage.setResizable(false);
            stage.show();
            mainCtrl.setUser(crtUser);
            mainCtrl.setData();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        } catch (ContestException e) {
            MessageAlert.showErrorMessage(null, "Authentification failed!");
        }
    }

    public void pressCancel() {
        System.exit(0);
    }
}
