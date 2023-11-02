package com.example.UserAuthenticationService.service;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.example.UserAuthenticationService.exception.InvalidCredentialsException;

public interface IUserService {
    User saveUser(User user) throws UserAlreadyExistsException;
    User getUserByUserEmailAndPassword(String useremail, String password) throws InvalidCredentialsException;
}
