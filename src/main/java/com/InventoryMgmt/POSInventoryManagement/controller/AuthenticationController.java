package com.InventoryMgmt.POSInventoryManagement.controller;

import javax.validation.Valid;
import com.InventoryMgmt.POSInventoryManagement.dao.JdbcUserDao;
import com.InventoryMgmt.POSInventoryManagement.model.*;
import com.InventoryMgmt.POSInventoryManagement.security.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/POSInventoryMgmt/")
@CrossOrigin
public class AuthenticationController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private JdbcUserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,
                                    JdbcUserDao userDao){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);

        User user = userDao.findByUsername(loginDto.getUsername());
        return new LoginResponseDto(jwt, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public User register(@Valid @RequestBody RegisterUserDto newUser){
        try {
            User user = userDao.findByUsername(newUser.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists.");
        } catch (UsernameNotFoundException e) {
            User user = userDao.create(
                    new User(newUser.getUsername(), newUser.getEmployeeId(), newUser.getPassword(), newUser.getRole(), newUser.getName())
            );
            return user;
        }
    }
}
