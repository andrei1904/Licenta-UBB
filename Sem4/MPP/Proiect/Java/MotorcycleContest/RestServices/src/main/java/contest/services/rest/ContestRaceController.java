package contest.services.rest;

import contest.domain.Race;
import contest.persistence.interfaces.IRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import contest.persistence.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/contest/races")
public class ContestRaceController {
    @Autowired
    private IRaceRepository raceRepository;

    @RequestMapping( method=RequestMethod.GET)
    public Race[] getAll(){
        List<Race> races = raceRepository.findAll();
        return races.toArray(new Race[0]);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String id){

        Race race = raceRepository.filterById(Integer.parseInt(id));
        if (race == null)
            return new ResponseEntity<String>("Race not found",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Race>(race, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Race create(@RequestBody Race race){
        raceRepository.save(race);
        return race;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Race update(@PathVariable int id, @RequestBody Race race) {
        System.out.println("Updating race ..." + id);
        raceRepository.update(id, race);
        return race;
    }

    @RequestMapping(value="/{name}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String name){
        System.out.println("Deleting race ... " + name);
        try {
            raceRepository.delete(name);
            return new ResponseEntity<Race>(HttpStatus.OK);
        }catch (RepositoryException ex){
            System.out.println("Ctrl Delete race exception");
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String raceError(RepositoryException e) {
        return e.getMessage();
    }

}
