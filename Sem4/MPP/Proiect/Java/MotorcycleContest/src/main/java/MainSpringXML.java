import controller.LoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AllService;

public class MainSpringXML extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
        Pane myPane = loader.load();

        LoginController ctrl = loader.getController();
        ctrl.setService(getAllService());

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);

        primaryStage.show();
    }

    static AllService getAllService(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("MotorcycleContestApp.xml");
        return context.getBean(AllService.class);
    }
}
