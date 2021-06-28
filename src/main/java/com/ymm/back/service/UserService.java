package com.example.joqdemo.service;

import com.example.joqdemo.dao.UserRepository;
import com.example.joqdemo.domain.tables.User;
import com.example.joqdemo.domain.tables.records.UserRecord;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Map<String, Object>> selectUser(){
        return userRepository.selectUser();
    }
    public List<Map<String, Object>> getList(){
        return userRepository.getList();
    }
    public ResponseEntity<User> insertUser(){
        return userRepository.insertUser();
    }
    public String updateUser(){
        return userRepository.updateUser();
    }
    public String deleteUser(){
        return userRepository.deleteUser();
    }

}
