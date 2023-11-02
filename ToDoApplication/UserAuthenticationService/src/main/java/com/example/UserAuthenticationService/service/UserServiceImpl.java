package com.example.UserAuthenticationService.service;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.example.UserAuthenticationService.exception.InvalidCredentialsException;
import com.example.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  IUserService{

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        //to save user in db
        if (userRepository.findById(user.getUserEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }

        System.out.println(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserEmailAndPassword(String useremail, String password) throws InvalidCredentialsException {
        //to validate user
        User loggedInUser = userRepository.findByUserEmailAndUserPassword(useremail, password);
        if (loggedInUser == null) {
            throw new InvalidCredentialsException();
        }
        return loggedInUser;
    }

}
