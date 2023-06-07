package start;

import contest.domain.EngineCapacity;
import contest.domain.Race;
import contest.services.rest.ServiceException;
import org.springframework.web.client.RestClientException;
import rest.race.RacesClient;


public class StartRestRaces {
    private final static RacesClient racesClient = new RacesClient();

    public static void main(String[] args) {
        Race race = new Race("Race0001", EngineCapacity.EngineCapacity500Cc);

        try {
            show(() -> System.out.println("Added race: " + racesClient.create(race)));

            show(() -> {
                Race[] res = racesClient.getAll();
                System.out.println("All races:");
                for (Race r : res) {
                    System.out.println(r.getId() + ": " + r.getName() + ", " + r.getRequiredEngineCapacity());
                }
            });

            show(() -> {
                Race res = racesClient.getById("1");
                System.out.println("Get race with id 1: \n" + res.getId() + ": " +
                        res.getName() + res.getRequiredEngineCapacity());
            });

            show(() -> {
                Race[] res = racesClient.getAll();
                Integer id = res[res.length - 1].getId();

                Race newRace = new Race("RACE111", EngineCapacity.EngineCapacity250Cc);

                System.out.println("Updated race: " + race + " to " + racesClient.update(id.toString(), newRace));
            });

            show(() -> System.out.println("Deleted race: " + racesClient.delete("RACE111")));

        } catch (RestClientException ex) {
            System.out.println("Exception ... " + ex.getMessage());
        }
    }


    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            System.out.println("Service exception" + e);
        }
    }
}
