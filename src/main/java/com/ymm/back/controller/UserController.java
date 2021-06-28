package com.example.joqdemo.controller;

import com.example.joqdemo.domain.tables.User;
import com.example.joqdemo.domain.tables.records.UserRecord;
import com.example.joqdemo.service.UserService;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<Map<String, Object>> selectUser() {

        //Map<String, Object> result = new HashMap<>();
       // int a = jooqBoardService.list();
        List<Map<String, Object>> result= userService.selectUser();

        //result.put("list", jooqBoardService.list());
        //() -> result;
        return result;

    }
    @GetMapping("/list")
    public List<Map<String, Object>> getList(){
        List<Map<String, Object>> result= userService.getList();
        return result;
    }
    @PostMapping("/a")
    public ResponseEntity<User> insertUser(){
        ResponseEntity<User> result= userService.insertUser();
        return result;
    }
    @PatchMapping("/change")
    public String updateUser(){
        String result = userService.updateUser();
        return result;
    }
    @DeleteMapping("/out")
    public String deleteUser(){
        String result = userService.deleteUser();
        return result;
    }

}
