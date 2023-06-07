import config.ApplicationContext;
import controller.MainController;
import domain.Hotel;
import domain.Location;
import domain.validators.HotelValidator;
import domain.validators.LocationValidator;
import domain.validators.Validator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import repo.Repository;
import repo.file.AbstractFileRepository;
import repo.file.HotelFile;
import repo.file.LocationFile;
import service.AllService;
import service.HotelService;
import service.LocationService;

import java.io.IOException;

public class MainFX extends Application {
    AllService allService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fileHotels = ApplicationContext.getPROPERTIES().getProperty("data.hotels");
        String fileLocations = ApplicationContext.getPROPERTIES().getProperty("data.locations");

        Validator<Hotel> hotelValidator = new HotelValidator();
        Validator<Location> locationValidator = new LocationValidator();

        // repo
        AbstractFileRepository<Double, Hotel> repoHotel = new HotelFile(hotelValidator, fileHotels);
        AbstractFileRepository<Double, Location> repoLocation = new LocationFile(locationValidator, fileLocations);

        // service
        HotelService hotelService = new HotelService(repoHotel);
        LocationService locationService = new LocationService(repoLocation);

        allService = new AllService(hotelService, locationService);

        initView(primaryStage);
        primaryStage.show();
    }

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("views/mainView.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));

         MainController controller = loader.getController();
         controller.setService(allService);
    }
}
