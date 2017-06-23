package pl.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.domain.GardenCareToDo;

/**
 * Created by PC on 2017-06-22.
 */
public interface IGardenCareToDoRepository extends JpaRepository<GardenCareToDo, Long> {

}
