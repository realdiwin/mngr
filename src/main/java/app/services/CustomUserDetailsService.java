package app.services;

import app.dao.UserDAOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a User in the {@code Spring Security} terminology.
 */
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * This variable inject the {@link app.model.User User} model's DAO layer into this service.
     */
    @Autowired
    private UserDAOI userDAO;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        app.model.User domainUser = userDAO.findByName(username);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        GrantedAuthority authority = (GrantedAuthority) () -> "ROLE_USER";
        List<GrantedAuthority> authList = Collections.singletonList(authority);
        return new User(
                domainUser.getUsername(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authList
        );
    }
}
