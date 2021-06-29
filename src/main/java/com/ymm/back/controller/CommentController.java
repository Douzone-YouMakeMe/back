package com.ymm.back.controller;


import com.ymm.back.domain.tables.Comment;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 주의 : POST 메소드의 @RequestBody input 객체들은 pojos로만 받는게 고정이다...
 * Spring 공식 어노테이션 라이브러리 포함된건 Jackson이기 때문.
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CommentController(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }
    // 코멘트 단 것들 모두 가져오기
    @GetMapping
    public List<Map<String, Object>> selectCommentAll(){
        String sql = dslContext.selectFrom(DSL.table("comment")).getSQL();
        return jdbcTemplate.queryForList(sql);
    }
    // 어떤 코멘트인지 파라미터로 검색해서 가져오기
    //localhost:8080/work/?id=1
    @GetMapping("/{id}")
    public ResponseEntity<?> selectCommentOne(@PathVariable("id") int id){
        Comment comment = Comment.COMMENT;
        //System.out.println("패스 파라미터 입니다: "+id);
        //System.out.println("sql문 입니다: "+dslContext.select().from(DSL.table("project")).where(project.ID.equal(id)).getSQL());
//        System.out.println(dslContext.selectFrom(DSL.table("project")).where(project.ID.eq(1)).getSQL());
        List<com.ymm.back.domain.tables.pojos.Comment> sql = dslContext.select().from(comment).where(comment.ID.eq(id)).fetchInto(com.ymm.back.domain.tables.pojos.Comment.class);
        //System.out.println(sql.get(0)); //하나만 받아온다

        return ResponseEntity.status(200).body(sql);
    }
    @PostMapping
    public String insertComment(@RequestBody com.ymm.back.domain.tables.pojos.Comment input){
        Comment comment = Comment.COMMENT;
        String result="";
        int sql = dslContext.insertInto(comment)
                .columns()
                .values()
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return result;
    }



}
