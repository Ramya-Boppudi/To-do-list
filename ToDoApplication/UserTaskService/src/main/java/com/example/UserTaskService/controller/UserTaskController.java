package com.example.UserTaskService.controller;

import com.example.UserTaskService.domain.User;
import com.example.UserTaskService.domain.Task;
import com.example.UserTaskService.exception.TaskAlreadyExistsException;
import com.example.UserTaskService.exception.UserAlreadyExistsException;
import com.example.UserTaskService.exception.UserNotFoundException;
import com.example.UserTaskService.exception.TaskNotFoundException;
import com.example.UserTaskService.service.IUserTaskService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController // for creating RESTful controller
@RequestMapping("/api/v1")   // to specifies the base URL path for all the endpoints defined in this controller.
public class UserTaskController {
    private IUserTaskService userTaskService;
    private ResponseEntity<?> responseEntity; // field is used to hold the response entity returned from controller methods.
    @Autowired // to automatically inject the instance of IUserTaskService
    public UserTaskController(IUserTaskService userTaskService) {

        this.userTaskService = userTaskService;
    }

    // method for registering a user ,response code 201 if success
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(userTaskService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }

    // method for saving a user task to task list ,response code 201 if success
    @PostMapping("/user/saveTask")
    public ResponseEntity<?> saveUserTaskToList(@RequestBody Task task, HttpServletRequest request) throws UserNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String email = claims.getSubject();
            responseEntity = new ResponseEntity<>(userTaskService.saveUserTaskToList(task, email), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    // method for retrieving a user task from the task list ,response code 200 if success
    @GetMapping("/user/getAllTasks")
    public ResponseEntity<?> getAllUserTasksFromList(HttpServletRequest request) throws UserNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String email = claims.getSubject();
            responseEntity = new ResponseEntity<>(userTaskService.getAllUserTasksFromList(email), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    // method for deleting a user task from the task list ,response code 200 if success
    @DeleteMapping("/user/{taskId}")
    public ResponseEntity<?> deleteUserTasksFromList(@PathVariable String taskId, HttpServletRequest request) throws TaskNotFoundException, UserNotFoundException,TaskNotFoundException {
        Claims claims = (Claims) request.getAttribute("claims");
        String email = claims.getSubject();
        try {
            responseEntity = new ResponseEntity<>(userTaskService.deleteUserTasksFromList(email, taskId), HttpStatus.OK);
        } catch (UserNotFoundException | TaskNotFoundException m) {
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }

    // method for retrieving a specific task by its taskId ,response code 200 if success
    @GetMapping("/user/task/{taskId}")
    public ResponseEntity<?> GetTaskById(@PathVariable String taskId,HttpServletRequest request) throws TaskNotFoundException {

        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            responseEntity=new ResponseEntity<>(userTaskService.getTaskById(email,taskId),HttpStatus.OK);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }

    //  method for updating a task based on user id and task id, extract user id from claims ,return 200 status if user is saved else 500 status
    @PutMapping("/user/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody Task task, HttpServletRequest request)throws UserNotFoundException,TaskNotFoundException, TaskAlreadyExistsException {
        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            responseEntity=new ResponseEntity<>(userTaskService.updateUserTask(email,task),HttpStatus.OK);
        }catch (TaskNotFoundException e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }
}
