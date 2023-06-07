import config.ApplicationContext;
import controller.LoginViewController;
import domain.Client;
import domain.Flight;
import domain.Ticket;
import domain.validators.ClientValidator;
import domain.validators.FlightValidator;
import domain.validators.TicketValidator;
import domain.validators.Validator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repo.Repository;
import repo.file.ClientFile;
import repo.file.FlightFile;
import repo.file.TicketFile;
import service.AllService;
import service.ClientService;
import service.FlightService;
import service.TicketService;

import java.io.IOException;

public class MainFX extends Application {
    AllService allService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fileClients = ApplicationContext.getPROPERTIES().getProperty("data.clients");
        String fileFlights = ApplicationContext.getPROPERTIES().getProperty("data.flights");
        String fileTickets = ApplicationContext.getPROPERTIES().getProperty("data.tickets");
        // validator
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Flight> flightValidator = new FlightValidator();
        Validator<Ticket> ticketValidator = new TicketValidator();
        // repo
        ClientFile clientRepo = new ClientFile(clientValidator, fileClients);
        FlightFile flightRepo = new FlightFile(flightValidator, fileFlights);
        TicketFile ticketRepo = new TicketFile(ticketValidator, fileTickets);
        // service
        ClientService clientService = new ClientService(clientRepo);
        FlightService flightService = new FlightService(flightRepo);
        TicketService ticketService = new TicketService(ticketRepo);

        allService = new AllService(clientService, flightService, ticketService);

        initView(primaryStage);
        primaryStage.show();
    }

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("views/loginView.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));

         LoginViewController controller = loader.getController();
         controller.setService(allService);
    }
}
