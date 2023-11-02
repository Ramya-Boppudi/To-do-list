package com.example.UserTaskService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document // Used for mapping to a document in a MongoDB database
public class User {
    @Id //indicates that userId field will be used as the primary key when saving and retrieving User in a database
    private String userEmail; // private fields which represent attributes or properties of a User
    private String userPassword;
    private String userName;
    private String userPhone;
    private List<Task> taskList;

    // default constructor
    // to create instances of the User class when it retrieves data from the MongoDB database.
    public User() {
    }

    // parameterized constructor, used to create instances of the User


    public User(String userEmail, String userPassword, String userName, String userPhone, List<Task> taskList) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.taskList = taskList;
    }

    //getter and setter methods allow controlled access to the fields,used for encapsulation and data binding.


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}