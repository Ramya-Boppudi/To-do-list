package com.example.UserTaskService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Task Already Exist")
public class TaskAlreadyExistsException extends Exception{
}
