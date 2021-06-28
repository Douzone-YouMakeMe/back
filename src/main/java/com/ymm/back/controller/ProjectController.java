package com.example.joqdemo.controller;

import com.example.joqdemo.domain.tables.Project;
import com.example.joqdemo.domain.tables.User;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProjectController(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping
    // 프로젝트 정보 가져오기
    public List<Map<String, Object>> selectProject(){
        String sql = dslContext.selectFrom(DSL.table("project")).getSQL();
        return jdbcTemplate.queryForList(sql);
    }
    // 프로젝트 만들기!
    @PostMapping
    public ResponseEntity<Project> insertProject(@RequestBody Project input){
        Project project = Project.PROJECT;
        int sql = dslContext.insertInto(project)
                .columns(project.USER_ID) //project.NAME ,project.FINISHED_AT
                .values(input.USER_ID)
                .execute();
        String q = dslContext.insertInto(project)
                .columns(project.USER_ID,project.NAME) //,project.FINISHED_AT
                .values(5,"프로젝트99").getSQL();
        System.out.println(q);
        return ResponseEntity.status(200).build();
    }
    //프로젝트 정보 변경
    @PatchMapping
    public String updateProject(){
        Project project = Project.PROJECT;
        User user = User.USER;
        String result="";
        int sql = dslContext.update(project)
                .set(project.NAME,"변경된 프로젝트")
                .where(project.USER_ID.eq(5))
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return result;
    }
    // 프로젝트 삭제
    @DeleteMapping
    public String deleteProject(){
        Project project = Project.PROJECT;
        User user = User.USER;
        String result="";
        int sql = dslContext.deleteFrom(project)
                .where(project.USER_ID.eq(5))
                .execute();
        if(sql ==1){
            result = "삭제 성공!";
        } else {
            result = "그런거 없습니다. 정확한 정보로 삭제요청 부탁";
        }
        return result;
    }


}
