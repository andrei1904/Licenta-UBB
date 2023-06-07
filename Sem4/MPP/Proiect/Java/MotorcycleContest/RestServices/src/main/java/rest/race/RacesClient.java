package rest.race;

import contest.domain.Race;
import contest.services.rest.ServiceException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RacesClient {
    public static final String URL = "http://localhost:8080/contest/races";

    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Race[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Race[].class));
    }

    public Race getById(String id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Race.class));
    }

    public Race create(Race race) {
        return execute(() -> restTemplate.postForObject(URL, race, Race.class));
    }

    public Race update(String id, Race race) {
        return execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, id), race);
            return race;
        });
    }

    public String delete(String name) {
        return execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, name));
            return name;
        });
    }

}
