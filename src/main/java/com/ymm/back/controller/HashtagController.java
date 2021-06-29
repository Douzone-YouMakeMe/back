package com.ymm.back.controller;

import com.ymm.back.domain.tables.Hashtag;
import com.ymm.back.domain.tables.Work;
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
@RequestMapping("/hashtag")
public class HashtagController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public HashtagController(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }

    // 전채 해시태그 모두 가져오기
    @GetMapping
    public List<Map<String, Object>> selectHashtagAll(){
        String sql = dslContext.selectFrom(DSL.table("hashtag")).getSQL();
        return jdbcTemplate.queryForList(sql);
    }

    // work가 가진 hashtag들 파라미터로 가져오기
    //localhost:8080/work/?id=1
    @GetMapping("/{id}")
    public String selectHashtagOne(@PathVariable("id") int id){
        Hashtag tag = Hashtag.HASHTAG;
        //System.out.println("패스 파라미터 입니다: "+id);
        //System.out.println("sql문 입니다: "+dslContext.select().from(DSL.table("project")).where(project.ID.equal(id)).getSQL());
//        System.out.println(dslContext.selectFrom(DSL.table("project")).where(project.ID.eq(1)).getSQL());
        Result<Record> sql = dslContext.select().from(tag).where(tag.WORK_ID.equal(id)).fetch();
        System.out.println(sql.get(0)); //하나만 받아온다

        return "";
    }
    //해시태그 만들기. 어느 작업의 해시태그인지 workId가 중요!
    @PostMapping
    public String insertHashtag(@RequestBody com.ymm.back.domain.tables.pojos.Hashtag input){
        Hashtag tag = Hashtag.HASHTAG;
        String result="";
        int sql = dslContext.insertInto(tag)
                .columns(tag.WORK_ID,tag.NAME)
                .values(input.getWorkId(),input.getName())
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return result;
    }
    // 해시태그 삭제는 path, 경로에 받은 해당 태그 아이디로만 단순 delete 친다.
    @DeleteMapping("/{id}")
    public String deleteHashtag(@PathVariable("id") int id){
        Hashtag tag = Hashtag.HASHTAG;
        String result="";
        int sql = dslContext.deleteFrom(tag)
                .where(tag.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "삭제 성공!";
        } else {
            result = "그런거 없습니다. 정확한 정보로 삭제요청 부탁";
        }
        return result;
    }


}
