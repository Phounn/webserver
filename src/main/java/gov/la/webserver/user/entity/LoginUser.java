package gov.la.webserver.user.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;



@Getter
public class LoginUser implements UserDetails {
    final User user;
    public LoginUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return user.getAccount().getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount().getUsername();
    }

}
