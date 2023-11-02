package com.example.UserAuthenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.example.UserAuthenticationService.exception.InvalidCredentialsException;
import com.example.UserAuthenticationService.security.SecurityTokenGenerator;
import com.example.UserAuthenticationService.service.IUserService;
import com.example.UserAuthenticationService.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class UserController {
    private IUserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            userService.saveUser(user);
        }
        catch (UserAlreadyExistsException ae){
            throw  new UserAlreadyExistsException();
        }

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws InvalidCredentialsException
    {
        User retrievedUser = userService.getUserByUserEmailAndPassword(user.getUserEmail(), user.getUserPassword());
        if (retrievedUser == null) {
            throw new InvalidCredentialsException();
        }

        String token = securityTokenGenerator.createToken(user);
        retrievedUser.setToken(token);
        return new ResponseEntity<User>(retrievedUser,HttpStatus.OK);
    }
}

