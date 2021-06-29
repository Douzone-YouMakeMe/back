package com.ymm.back.controller;


import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 주의 : POST 메소드의 @RequestBody input 객체들은 pojos로만 받는게 고정이다...
 * Spring 공식 어노테이션 라이브러리 포함된건 Jackson이기 때문.
 */

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
    // 프로젝트 정보 모두 가져오기
    @GetMapping
    public List<Map<String, Object>> selectProjectAll(){
        String sql = dslContext.selectFrom(DSL.table("project")).getSQL();
        return jdbcTemplate.queryForList(sql);
    }
    // 프로젝트 단일 정보 가져오기
    //localhost:8080/project/?id=1
    @GetMapping("/{id}")
    public String selectProjectOne(@PathVariable("id") int id){
        Project project = Project.PROJECT;
        //System.out.println("패스 파라미터 입니다: "+id);
        //System.out.println("sql문 입니다: "+dslContext.select().from(DSL.table("project")).where(project.ID.equal(id)).getSQL());
//        System.out.println(dslContext.selectFrom(DSL.table("project")).where(project.ID.eq(1)).getSQL());
        Result<Record> sql = dslContext.select().from(project).where(project.USER_ID.eq(id)).fetch();
        System.out.println(sql.get(0)); //하나만 받아온다

        return "";
    }
    // 프로젝트 만들기!
    @PostMapping
    public String insertProject(@RequestBody com.ymm.back.domain.tables.pojos.Project input){

        Project project = Project.PROJECT;
        int sql = dslContext.insertInto(project)
                .columns(project.USER_ID,project.NAME,project.FINISHED_AT,project.CONTENTS,project.VIEW_COUNT,project.THUMBNAIL,project.DESCRIPTION,project.AUTHORITY,project.TOTAL,project.FINISHED_TIME) //project.NAME ,project.FINISHED_AT
                .values(input.getUserId(), input.getName(),input.getFinishedAt(),input.getContents(),input.getViewCount(),input.getThumbnail(),input.getDescription(),input.getAuthority(),input.getTotal(),input.getFinishedTime())
                .execute();

//        String q = dslContext.insertInto(project)
//                .columns(project.USER_ID,project.NAME) //,project.FINISHED_AT
//                .values(input.getUserId(), input.getName()).getSQL();
//        System.out.println(q);
        return "성공";//ResponseEntity.status(200).build();
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
