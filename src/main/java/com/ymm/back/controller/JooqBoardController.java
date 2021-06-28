package com.ymm.back.controller;

//import com.example.joqdemo.service.JooqBoardService;
import com.ymm.back.service.JooqBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jooq_board")
public class JooqBoardController {

    private final JooqBoardService jooqBoardService;

    @Autowired
    public JooqBoardController(JooqBoardService jooqBoardService) {
        this.jooqBoardService = jooqBoardService;
    }

//    @GetMapping("/list")
//    public int/*Callable<Map<String, Object>>*/ list() {
//
//        //Map<String, Object> result = new HashMap<>();
//        int a = jooqBoardService.list();
//
//        //result.put("list", jooqBoardService.list());
//
//        return a;//() -> result;
//
//    }

}