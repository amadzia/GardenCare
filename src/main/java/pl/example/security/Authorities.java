package pl.example.security;

import org.springframework.security.core.GrantedAuthority;
import pl.example.domain.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by PC on 2017-06-26.
 */

@Entity
public class Authorities implements GrantedAuthority {

    private static final long serialVersionUID = 4893108108051170037L;

    @Id
    @GeneratedValue
    private Long id;

    private String authority;

    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return null;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
