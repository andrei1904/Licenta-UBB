import config.ApplicationContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import repo.Repository;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fileName = ApplicationContext.getPROPERTIES().getProperty("data.x.csv");

        // repo

        // service

        initView(primaryStage);
        primaryStage.show();
    }

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("views/view.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));

        // Controller controller = controller.getController();
        // controller.setService(allService);
    }
}
