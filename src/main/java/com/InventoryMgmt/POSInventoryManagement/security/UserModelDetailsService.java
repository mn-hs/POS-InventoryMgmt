package com.InventoryMgmt.POSInventoryManagement.security;

import com.InventoryMgmt.POSInventoryManagement.dao.JdbcUserDao;
import com.InventoryMgmt.POSInventoryManagement.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);
    private final JdbcUserDao userDao;

    public UserModelDetailsService(JdbcUserDao userDao){this.userDao = userDao;}
    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        log.debug("Authenticating user '{}'", login);
        String lowercaseLogin = login.toLowerCase();
        try {
            return createSpringSecurityUser(lowercaseLogin, userDao.findByUsername(lowercaseLogin));
        } catch (UserNotActivatedException e) {
            throw new RuntimeException(e);
        }
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) throws UserNotActivatedException {
        if (!user.isActivated()){
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), grantedAuthorities);
    }
}
