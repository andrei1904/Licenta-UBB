package app.services.rest;

import app.model.UserGameDto;
import app.model.UserWordDto;
import app.persistence.interfaces.IMoveRepository;
import app.persistence.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan("MoveRepository")
@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping("/game")
public class RestController {

    @Autowired
    private IMoveRepository moveRepository;

    private static final String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public UserWordDto[] getChosenWordsForGame(@PathVariable Integer gameId) {
        UserWordDto[] res = moveRepository.getChosenWordsForGame(gameId);
        return res;
    }


    @RequestMapping(value = "/{gameId}/{username}", method = RequestMethod.GET)
    public UserGameDto[] getLettersForUser(@PathVariable Integer gameId, @PathVariable String username) {
        UserGameDto[] res = moveRepository.getDetailsUserGame(username, gameId);
        return res;
    }
}
