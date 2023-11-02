package com.example.UserTaskService.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;


@Document // Used for mapping to a document in a MongoDB database

public class Task {
    @Id //indicates that taskId field will be used as the primary key when saving and retrieving Task in a database
    private String taskId;
    private String taskName;
    private String taskDesc;

    private String category;
    private Date createdDate;
    private Date dueDate;
    private String priority;
    private String Status;

    // parameterized constructor, used to create instances of the Task

    public Task(String taskId, String taskName, String taskDesc, String category, Date createdDate, Date dueDate, String priority, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.category = category;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.priority = priority;
        Status = status;
    }

    public Task() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", dueDate=" + dueDate +
                ", priority='" + priority + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}