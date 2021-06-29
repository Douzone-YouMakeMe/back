package com.ymm.back.controller;


import com.ymm.back.domain.tables.Comment;
import com.ymm.back.pojos.CommentP;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 주의 : POST 메소드의 @RequestBody input 객체들은 pojos로만 받는게 고정이다...
 * Spring 공식 어노테이션 라이브러리 포함된건 Jackson이고, jooq Record는 serialize에서 충돌나기 때문.
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
        /*List<com.ymm.back.domain.tables.pojos.Comment>*/
        var result = dslContext.select().from(comment).where(comment.ID.eq(id)).fetchInto(CommentP.class);
        return ResponseEntity.status(200).body(result);
    }
    @PostMapping
    public ResponseEntity<?> insertComment(@RequestBody CommentP input){
        Comment comment = Comment.COMMENT;
        String result="";
        int sql = dslContext.insertInto(comment)
                .columns(comment.WORK_ID,comment.MEMBER_ID,comment.COMMENTS)
                .values(input.getWorkId(),input.getMemberId(),input.getComments())
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return ResponseEntity.status(200).body(result);
    }
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable("id") int id){
        Comment comment = Comment.COMMENT;
        String result="";
        int sql = dslContext.deleteFrom(comment)
                .where(comment.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "삭제 성공!";
        } else {
            result = "그런거 없습니다. 정확한 정보로 삭제요청 부탁";
        }
        return result;
    }



}
