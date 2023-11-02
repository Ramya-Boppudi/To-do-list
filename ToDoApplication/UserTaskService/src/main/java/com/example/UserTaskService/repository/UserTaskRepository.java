package com.example.UserTaskService.repository;

import com.example.UserTaskService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserTaskRepository extends MongoRepository<User, String> {

}

