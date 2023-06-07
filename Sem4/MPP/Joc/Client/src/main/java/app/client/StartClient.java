package app.client;

import app.client.gui.LoginController;
import app.client.gui.MainController;
import app.services.IAppServices;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            ApplicationContext factory =
                    new ClassPathXmlApplicationContext("classpath:appclient.xml");
            IAppServices server = (IAppServices) factory.getBean("appService");

            FXMLLoader loginLoader = new FXMLLoader(
                    getClass().getResource("/views/loginView.fxml"));
            Parent loginRoot = loginLoader.load();
            LoginController loginController = loginLoader.getController();
            loginController.setServer(server);

            FXMLLoader mainLoader = new FXMLLoader(
                    getClass().getResource("/views/mainView.fxml"));
            Parent mainRoot = mainLoader.load();
            MainController mainController = mainLoader.getController();
            mainController.setServer(server);

            loginController.setParent(mainRoot);
            loginController.setMainController(mainController);

            primaryStage.setTitle("App");
            primaryStage.setScene(new Scene(loginRoot));
//        primaryStage.initStyle(StageStyle.UTILITY);
//            primaryStage.setOnCloseRequest(Event::consume);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("App initialization exception: " + e);
            e.printStackTrace();
        }
    }
}
