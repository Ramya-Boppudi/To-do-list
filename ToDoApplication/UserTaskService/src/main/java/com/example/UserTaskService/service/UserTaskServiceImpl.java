package com.example.UserTaskService.service;

import com.example.UserTaskService.domain.User;
import com.example.UserTaskService.domain.Task;
import com.example.UserTaskService.exception.TaskAlreadyExistsException;
import com.example.UserTaskService.exception.UserAlreadyExistsException;
import com.example.UserTaskService.exception.UserNotFoundException;
import com.example.UserTaskService.exception.TaskNotFoundException;

import com.example.UserTaskService.proxy.UserProxy;
import com.example.UserTaskService.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserTaskServiceImpl implements IUserTaskService{
    private UserTaskRepository userTaskRepository;
    private UserProxy userProxy;
    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository,UserProxy userProxy) {
        this.userTaskRepository = userTaskRepository;
        this.userProxy=userProxy;
    }

    // method to register a user
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (userTaskRepository.findById(user.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User obj=userTaskRepository.save(user);

        if(obj.getUserEmail()!=null){
            userProxy.saveUser(user);
        }
        return userTaskRepository.findById(user.getUserEmail()).get();
    }

    // method to save user task
    @Override
    public User saveUserTaskToList(Task task, String email) throws UserNotFoundException {
        if (userTaskRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userTaskRepository.findById(email).get();
        if (user.getTaskList() == null) {
            user.setTaskList(Arrays.asList(task));
        } else {
            List<Task> tasks = user.getTaskList();
            tasks.add(task);
            user.setTaskList(tasks);
        }
        return userTaskRepository.save(user);
    }

    // method to delete task from list
    @Override
    public User deleteUserTasksFromList(String email, String taskId) throws UserNotFoundException,TaskNotFoundException {
        boolean taskIdIsPresent = false;
        if (userTaskRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userTaskRepository.findById(email).get();
        List<Task> tasks = user.getTaskList();
        taskIdIsPresent = tasks.removeIf(x -> x.getTaskId().equals(taskId));
        if (!taskIdIsPresent) {
            throw new UserNotFoundException();
        }
        user.setTaskList(tasks);
        return userTaskRepository.save(user);
    }

    // method to get all task from list by userId
    @Override
    public List<Task> getAllUserTasksFromList(String email) throws UserNotFoundException {
        if (userTaskRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(email).get().getTaskList();
    }

    // method to update a user task by useremail
    @Override
    public Task updateUserTask(String email, Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException {
        User isTobeUpdatedUser = userTaskRepository.findById(email).get();
        System.out.println(isTobeUpdatedUser);
        User existingUser = null;
        List<Task> existingTasks = new ArrayList<>();
        if (userTaskRepository.findById(email).isPresent()) {
            //existingUser = userTaskRepository.findById(email).get();
            existingTasks = isTobeUpdatedUser.getTaskList();

            String TaskId = task.getTaskId();
            for (int i = 0; i < existingTasks.size(); i++) {
                if (existingTasks.get(i).getTaskId().equals(task.getTaskId())) {

                    if (task.getCategory() != null) {
                        existingTasks.get(i).setCategory(task.getCategory());
                    }

                    if ( task.getDueDate()!=null) {
                        existingTasks.get(i).setDueDate(task.getDueDate());
                    }

                    if (task.getPriority()!= null) {
                        existingTasks.get(i).setPriority(task.getPriority());
                    }
                    if (task.getStatus()!= null) {
                        existingTasks.get(i).setStatus(task.getStatus());
                    }
                    if (task.getTaskName()!= null) {
                        existingTasks.get(i).setTaskName(task.getTaskName());
                    }
                    if (task.getTaskDesc()!= null) {
                        existingTasks.get(i).setTaskDesc(task.getTaskDesc());
                    }

                }
            }
        }

        userTaskRepository.save(isTobeUpdatedUser);

        return task;
    }

    // method to get task by useremail
    @Override
    public Task getTaskById(String email, String taskId) throws UserNotFoundException, TaskNotFoundException {
        if(userTaskRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User object=userTaskRepository.findById(email).get();
        List<Task> Tlist=object.getTaskList();
        Task t=null;
        if(Tlist.size()==0 || Tlist==null){
            throw  new TaskNotFoundException();
        }
        //for(var i=0;i<Tlist.size();i++){
            //if(Tlist.get(i).getTaskId().equals(taskId)){
               // t= Tlist.get(i);
           // }
        //}
        for(Task tt:Tlist){
            if(tt.getTaskId().equals(taskId)){
                t=tt;
            }
        }
        return  t;
    }
}


