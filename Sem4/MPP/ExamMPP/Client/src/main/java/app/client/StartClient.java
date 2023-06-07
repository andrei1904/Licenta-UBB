package app.client;

import app.client.controllers.LoginController;
import app.client.controllers.MainController;
import app.services.IServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("classpath:client.xml");
        IServices server = (IServices) factory.getBean("appService");

        FXMLLoader loginLoader = new FXMLLoader(
                getClass().getResource("/views/loginView.fxml"));
        Parent loginRoot = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        loginController.setService(server);

        FXMLLoader mainLoader = new FXMLLoader(
                getClass().getResource("/views/mainView.fxml"));
        Parent mainRoot = mainLoader.load();
        MainController mainController = mainLoader.getController();

        loginController.setController(mainController);
        loginController.setParent(mainRoot);

        Scene scene = new Scene(loginRoot);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
