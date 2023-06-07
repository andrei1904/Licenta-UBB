package contest.client;

import contest.client.gui.*;
import contest.services.IContestServices;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;

public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");

        Properties clientProps = new Properties();

        try {
            clientProps.load(StartClient.class.getResourceAsStream("/contestclient.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatclient.properties " + e);
            return;
        }

        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IContestServices server=(IContestServices) factory.getBean("contestService");
            System.out.println("Obtained a reference to remote chat server");

            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource("login.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.<LoginController>getController();
            loginController.setServer(server);

            FXMLLoader cloader = new FXMLLoader(
                    getClass().getClassLoader().getResource("mainView.fxml"));
            Parent croot = cloader.load();

            MainViewController mainViewController = cloader.<MainViewController>getController();
            mainViewController.setServer(server);

            loginController.setMainController(mainViewController);
            loginController.setParent(croot);

            primaryStage.setTitle("Motorcycle Contest");
            primaryStage.setScene(new Scene(root));
//        primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setOnCloseRequest(Event::consume);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Contest Initialization  exception: " + e);
            e.printStackTrace();
        }
    }
}
