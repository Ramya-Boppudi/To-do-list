package com.example.UserTaskService.service;

import com.example.UserTaskService.domain.User;
import com.example.UserTaskService.domain.Task;
import com.example.UserTaskService.exception.TaskAlreadyExistsException;
import com.example.UserTaskService.exception.UserAlreadyExistsException;
import com.example.UserTaskService.exception.UserNotFoundException;
import com.example.UserTaskService.exception.TaskNotFoundException;

import java.util.List;
public interface IUserTaskService {
        User registerUser(User user) throws UserAlreadyExistsException;
        User saveUserTaskToList(Task task, String email) throws UserNotFoundException;
        User deleteUserTasksFromList(String email,String id) throws UserNotFoundException, TaskNotFoundException;
        List<Task> getAllUserTasksFromList(String email) throws UserNotFoundException;
        Task updateUserTask(String email,Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException;

        Task getTaskById(String email,String id) throws UserNotFoundException,TaskNotFoundException;
}
