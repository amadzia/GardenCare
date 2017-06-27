package pl.example.domain;

import pl.example.security.Authorities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by PC on 2017-06-26.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<Authorities>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<GardenCareToDo> gardenCareToDos = new TreeSet<GardenCareToDo>();

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.authorities = user.authorities;
        this.gardenCareToDos = user.gardenCareToDos;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authorities> getAuthorities() {

        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Set<GardenCareToDo> getGardenCareToDos() {
        return gardenCareToDos;
    }

    public void setGardenCareToDos(Set<GardenCareToDo> gardenCareToDos) {
        this.gardenCareToDos = gardenCareToDos;
    }
}
