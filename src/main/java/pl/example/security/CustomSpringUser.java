package pl.example.security;

import org.springframework.security.core.userdetails.UserDetails;
import pl.example.domain.GardenCareToDo;
import pl.example.domain.User;

import java.util.Set;

/**
 * Created by PC on 2017-06-27.
 */
public class CustomSpringUser extends User implements UserDetails {

    private static final long serialVersionUID = -8466609548764662342L;

    public CustomSpringUser() {
    }

    public CustomSpringUser(User user) {
        super(user);
    }

    @Override
    public Set<GardenCareToDo> getGardenCareToDos() {
        return super.getGardenCareToDos();
    }

    @Override

    public Set<Authorities> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
