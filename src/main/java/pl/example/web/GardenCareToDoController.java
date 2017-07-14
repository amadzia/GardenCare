package pl.example.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.example.domain.GardenCareToDo;
import pl.example.domain.User;
import pl.example.repository.IGardenCareToDoRepository;
import pl.example.repository.IUserRepository;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by PC on 2017-06-22.
 */

@RestController
@RequestMapping("/gardencaretodo/api")
public class GardenCareToDoController {

    @Autowired
    private IGardenCareToDoRepository iGardenCareToDoRepository;

    @Autowired
    private IUserRepository iUserRepository;

//    CRUD operations

//    CREATE (we use Spring Data to save an object)

    @RequestMapping(value = "", method = RequestMethod.POST)

    public ResponseEntity<GardenCareToDo> create(@AuthenticationPrincipal User user, @RequestBody GardenCareToDo gardenCareToDo) {

        user = iUserRepository.findByUsername(user.getUsername());
        gardenCareToDo.setUser(user);
        user.getGardenCareToDos().add(gardenCareToDo);

        iUserRepository.save(user);

        GardenCareToDo savedGardenCareToDo = iGardenCareToDoRepository.findByTaskAndUser(gardenCareToDo.getTask(), user);


        return new ResponseEntity<GardenCareToDo>(savedGardenCareToDo, HttpStatus.OK);

    }

//    READ all objects from database

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<GardenCareToDo>> getGardenCareToDos(@AuthenticationPrincipal User user) {

        user = iUserRepository.findByUsername(user.getUsername());
        Set<GardenCareToDo> gardenCareToDos = user.getGardenCareToDos();

        TreeSet<GardenCareToDo> sortedGardenCareToDos = new TreeSet<GardenCareToDo>(gardenCareToDos);

        return new ResponseEntity<Collection<GardenCareToDo>>(sortedGardenCareToDos, HttpStatus.OK);
    }

//    READ object by id

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<GardenCareToDo> getGardenCareToDo(@PathVariable Long id) {
        GardenCareToDo gardenCareToDo = iGardenCareToDoRepository.findOne(id);

        if (gardenCareToDo == null) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<GardenCareToDo>(gardenCareToDo, HttpStatus.OK);
        }
    }


//    UPDATE by id

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<GardenCareToDo> updateGardenCareToDo(@PathVariable Long id, @RequestBody GardenCareToDo gardenCareToDo) {

        GardenCareToDo savedGardenCareToDo = iGardenCareToDoRepository.findOne(id);

        if (savedGardenCareToDo == null) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
        }

        BeanUtils.copyProperties(gardenCareToDo, savedGardenCareToDo, "id");

        savedGardenCareToDo = iGardenCareToDoRepository.save(savedGardenCareToDo);

        return new ResponseEntity<GardenCareToDo>(savedGardenCareToDo, HttpStatus.OK);
    }

//    DELETE

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GardenCareToDo> deleteGardenCareToDo(@PathVariable Long id) {
        GardenCareToDo gardenCareToDo = iGardenCareToDoRepository.findOne(id);

        if (gardenCareToDo == null) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
        }

        try {
            iGardenCareToDoRepository.delete(gardenCareToDo);
        } catch (Exception e) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
    }

    public void setiGardenCareToDoRepository(IGardenCareToDoRepository iGardenCareToDoRepository) {
        this.iGardenCareToDoRepository = iGardenCareToDoRepository;
    }

    public void setiUserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }
}
