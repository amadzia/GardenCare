package pl.example.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.example.domain.GardenCareToDo;
import pl.example.repository.IGardenCareToDoRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by PC on 2017-06-22.
 */

@RestController
@RequestMapping("/gardencaretodo/api")
public class GardenCareToDoController {

    private IGardenCareToDoRepository gardenCareToDoRepository;

//    CRUD operations

//    CREATE (we use Spring Data to save an object)

    @RequestMapping(value = "", method = RequestMethod.POST)

    public ResponseEntity<GardenCareToDo> create(@RequestBody GardenCareToDo gardenCareToDo) {

        GardenCareToDo savedGardenCareToDo = gardenCareToDoRepository.save(gardenCareToDo);

        return new ResponseEntity<GardenCareToDo>(savedGardenCareToDo, HttpStatus.OK);

    }

//    READ all objects from database

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<GardenCareToDo>> getGardenCareToDos() {
        List<GardenCareToDo> gardenCareToDos = gardenCareToDoRepository.findAll();

        return new ResponseEntity<Collection<GardenCareToDo>>(gardenCareToDos, HttpStatus.OK);
    }

//    READ object by id

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<GardenCareToDo> getGardenCareToDo(@PathVariable Long id) {
        GardenCareToDo gardenCareToDo = gardenCareToDoRepository.findOne(id);

        if (gardenCareToDo == null) {
            return new ResponseEntity<GardenCareToDo>(gardenCareToDo, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<GardenCareToDo>(gardenCareToDo, HttpStatus.OK);
        }
    }


//    UPDATE by id

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<GardenCareToDo> updateGardenCareToDo(@PathVariable Long id, @RequestBody GardenCareToDo gardenCareToDo) {
        GardenCareToDo savedGardenCareToDo = gardenCareToDoRepository.findOne(id);

        if (savedGardenCareToDo == null) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
        }

        BeanUtils.copyProperties(gardenCareToDo, savedGardenCareToDo, "id");

        savedGardenCareToDo = gardenCareToDoRepository.save(savedGardenCareToDo);

        return new ResponseEntity<GardenCareToDo>(savedGardenCareToDo, HttpStatus.OK);
    }

//    DELETE

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GardenCareToDo> deleteGardenCareToDo(@PathVariable Long id) {
        GardenCareToDo gardenCareToDo = gardenCareToDoRepository.findOne(id);

        if (gardenCareToDo == null) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
        }

        try {
            gardenCareToDoRepository.delete(gardenCareToDo);
        } catch (Exception e) {
            return new ResponseEntity<GardenCareToDo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<GardenCareToDo>(HttpStatus.NO_CONTENT);
    }


    @Autowired

    public void setGardenCareToDoRepository(IGardenCareToDoRepository gardenCareToDoRepository) {
        this.gardenCareToDoRepository = gardenCareToDoRepository;
    }
}
