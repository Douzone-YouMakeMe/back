package com.ymm.back.controller;

import com.ymm.back.domain.tables.Work;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 주의 : POST 메소드의 @RequestBody input 객체들은 pojos로만 받는게 고정이다...
 * Spring 공식 어노테이션 라이브러리 포함된건 Jackson이고, jooq Record는 serialize에서 충돌나기 때문.
 */

@RestController
@RequestMapping("/work")
public class WorkController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public WorkController(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }
    // 작업 정보 모두 가져오기
    @GetMapping
    public List<Map<String, Object>> selectWorkAll(){
        var sql = dslContext.selectFrom(DSL.table("work")).getSQL();
        return jdbcTemplate.queryForList(sql);
    }
    // 작업 단일 정보 파라미터로 가져오기
    //localhost:8080/work/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> selectWorkOne(@PathVariable("id") int id){
        Work work = Work.WORK;
        /*List<com.ymm.back.domain.tables.pojos.Work>*/
        var sql = dslContext.select().from(work).where(work.ID.eq(id)).fetchInto(WorkP.class);
        return ResponseEntity.status(200).body(sql);//jdbcTemplate.queryForList(sql).get(0);
    }
    @PostMapping
    public ResponseEntity<?> insertWork(@RequestBody WorkP input){
        Work work = Work.WORK;
        String result="";
        int sql = dslContext.insertInto(work)
                .columns(work.CREATE_TIME,work.FINISHED_AT,work.MEMBER_ID,work.PROJECT_ID,work.MANAGER,work.NAME,work.STARTED_AT,work.STATUS,work.UPDATE_TIME)
                .values(input.getCreateTime(),input.getFinishedAt(),input.getMemberId(),input.getProjectId(),input.getManager(),input.getName(),input.getStartedAt(),input.getStatus(),input.getUpdateTime())
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return ResponseEntity.status(200).body(result);
    }
    @PatchMapping
    public ResponseEntity<?> updateWork(@RequestBody WorkP input){
        Work work = Work.WORK;
        String result="";
        // jackson은 구리다... 날짜 강제변경 어노테이션 ㄱ. 게다가 로컬 변수는 안되니 VO 테이블 전체에 붙여야됨.
        //@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
        //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime finishedAt = input.getFinishedAt();
        int sql = dslContext.update(work)
                .set(work.MANAGER,input.getManager())
                .set(work.NAME,input.getName())
                .set(work.STATUS,input.getStatus())
                .set(work.FINISHED_AT, finishedAt)
                .where(work.ID.eq(input.getId()))
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return ResponseEntity.status(200).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") int id){
        Work work = Work.WORK;
        String result="";
        int sql = dslContext.deleteFrom(work)
                .where(work.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "삭제 성공!";
        } else {
            result = "그런거 없습니다. 정확한 정보로 삭제요청 부탁";
        }
        return ResponseEntity.status(200).body(result);
    }


}
