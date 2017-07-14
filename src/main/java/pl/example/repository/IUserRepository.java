package pl.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.domain.User;

/**
 * Created by PC on 2017-06-27.
 */
public interface IUserRepository extends JpaRepository<User, Long> {

    //  query is performed by Spring Data API
    User findByUsername(String username);

}
