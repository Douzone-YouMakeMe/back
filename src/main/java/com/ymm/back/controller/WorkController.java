package com.ymm.back.controller;

import com.ymm.back.domain.tables.Work;
import com.ymm.back.domain.tables.records.WorkRecord;
import com.ymm.back.pojos.WorkP;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 주의 : POST 메소드의 @RequestBody input 객체들은 pojos로만 받는게 고정이다...
 * Spring 공식 어노테이션 라이브러리 포함된건 Jackson이고, jooq Record는 serialize에서 충돌나기 때문.
 */
@CrossOrigin
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
    // 작업 정보들 모두 가져오기
    @GetMapping
    public ResponseEntity<?> selectWorkAll(){
        var sql = dslContext.selectFrom(DSL.table("work")).getSQL();
        //List<Map<String, Object>>
        return ResponseEntity.status(200).body(jdbcTemplate.queryForList(sql));
    }
    // 내 프로젝트가 속한 작업 정보 파라미터로 가져오기
    //localhost:8080/work/from-project/2
    @GetMapping(path = "/from-project/{id}")
    public ResponseEntity<?> selectWorkOfProject(@PathVariable("id") int id){
        Work work = Work.WORK;
        /*List<com.ymm.back.domain.tables.pojos.Work>*/
        var sql = dslContext.select().from(work).where(work.PROJECT_ID.eq(id)).fetchInto(WorkP.class);
        return ResponseEntity.status(200).body(sql);//jdbcTemplate.queryForList(sql).get(0);
    }
    // 작업에 대한 단일 정보 파라미터로 가져오기
    //localhost:8080/work/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> selectWorkOne(@PathVariable("id") int id){
        Work work = Work.WORK;
        /*List<com.ymm.back.domain.tables.pojos.Work>*/
        var sql = dslContext.select().from(work).where(work.ID.eq(id)).fetchInto(WorkP.class);
        return ResponseEntity.status(200).body(sql);//jdbcTemplate.queryForList(sql).get(0);
    }
    //업무 만들기
    // 주의: 시작시간 입력은 "startedAt": "2021-07-01 18:37:17" 처럼 전체를 입력해야 한다.
    @PostMapping
    public ResponseEntity<?> insertWork(@RequestBody WorkP input){
        Work work = Work.WORK;
        String result="";
        var sql = dslContext.insertInto(work)
                .columns(work.FINISHED_AT,work.MEMBER_ID,work.PROJECT_ID,work.MANAGER,work.NAME,work.STARTED_AT,work.COLOR)
                .values(input.getFinishedAt(),input.getMemberId(),input.getProjectId(),input.getManager(),input.getName(),input.getStartedAt(),input.getColor())
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return ResponseEntity.status(200).body(result);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateWork(@PathVariable("id") int id, @RequestBody WorkP input){
        Work work = Work.WORK;
        String result="";
        // jackson은 구리다... 날짜 강제변경 어노테이션 ㄱ. 게다가 로컬 변수는 안되니 VO 테이블 전체에 붙여야됨.
        //@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
        //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        //LocalDateTime finishedAt = input.getFinishedAt();
        var sql = dslContext.update(work)
                .set(work.MANAGER,input.getManager())
                .set(work.NAME,input.getName())
                .set(work.STATUS,input.getStatus())
                .set(work.FINISHED_AT, input.getFinishedAt())
                .set(work.COLOR, input.getColor())
                .where(work.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return ResponseEntity.status(200).body(result);
    }

    /**
     * localhost:8080/work/status
     * 다중 status 업데이트 ()
     * input: List(work.id, status)
     * process: status: started(할일), proceed(진행중), finished(완료)
     * output: 성공(200), 실패(400)
     * 주의: 어느 프로젝트의, 어느 멤버가 업데이트 쳤는지를 받지 않으므로
     * UI 비즈니스 로직으로 막아야함.
     * 하단에 예시 request body List문 첨부.
     */
    @PatchMapping(path = "/status")
    public ResponseEntity<?> updateWork2( @RequestBody List<WorkP> input){
        Work work = Work.WORK;
        String result="";
        WorkRecord record = new WorkRecord();

        try {
            for(int i=0;i<input.size();i++){
                //진행중으로 치면 상태만 변경되지만...
                if(input.get(i).getStatus().equalsIgnoreCase("proceed")){
                    record.set(work.STATUS, input.get(i).getStatus());
                    //완료를 치면 종료일까지 처리된다!
                } else if(input.get(i).getStatus().equalsIgnoreCase("finished")){
                    record.set(work.STATUS, input.get(i).getStatus());
                    //record.set(work.FINISHED_AT, LocalDateTime.now());
                } else {
                    record.set(work.STATUS, input.get(i).getStatus());
                }
                var sql = dslContext.update(work)
                        .set(record)
                        .where( work.ID.eq( input.get(i).getId() ) ).execute();
            }
            result = "업무 진행사항 변경이 완료되었습니다.";
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body("이미 처리된 업무가 포함되어 있습니다. 다시 시도해 주세요.");
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
            result = "정확한 정보로 삭제요청을 부탁드립니다.";
        }
        return ResponseEntity.status(200).body(result);
    }

    /*
    [
    {
        "id": "14",
        "status": "finished"
    },
    {
        "id": "15",
        "status": "started"
    },
    {
        "id": "16",
        "status": "finished"
    }
    ]
    * */

}
