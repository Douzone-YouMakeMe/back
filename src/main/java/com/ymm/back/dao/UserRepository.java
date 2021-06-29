package com.ymm.back.dao;


import com.ymm.back.domain.tables.User;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class UserRepository {
//    private final DSLContext dslContext;
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public UserRepository(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
//        this.dslContext = dslContext;
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    // 유저정보 가져오기
//    public List<Map<String, Object>> selectUser(){
//        String sql = dslContext.selectFrom(DSL.table("user")).getSQL();
//        return jdbcTemplate.queryForList(sql);
//    }
//    // 유저 회원가입
//    public ResponseEntity<User> insertUser(){
//        User user = User.USER;
//        int sql = dslContext.insertInto(user)
//                .columns(user.NAME,user.EMAIL,user.JOB_TITLE,user.PASSWORD)
//                .values("가가가","이메일1","회사원","0000")
//                .execute();
//        String q = dslContext.insertInto(user)
//                .columns(user.NAME,user.EMAIL,user.JOB_TITLE,user.PASSWORD)
//                .values("가가가","이메일1","회사원","0000").getSQL();
//        System.out.println(q);
//        return ResponseEntity.status(200).build();
//    }
//    // 유저 개인정보 수정
//    public String updateUser(){
//        User user = User.USER;
//        String result="";
//        int sql = dslContext.update(user)
//                .set(user.EMAIL,"한메일")
//                .set(user.PASSWORD,"9999")
//                .where(user.EMAIL.eq("이메일1"))
//                .execute();
//        if(sql ==1){
//            result = "성공!";
//        } else {
//            result = "그런거 없습니다.";
//        }
//        return result;
//    }
//    // 유저 삭제
//    public String deleteUser(){
//        User user = User.USER;
//        String result="";
//        int sql = dslContext.deleteFrom(user)
//                .where(user.EMAIL.eq("한메일4"))
//                .execute();
//        if(sql ==1){
//            result = "성공!";
//        } else {
//            result = "그런거 없습니다.";
//        }
//        return result;
//    }
//
//
//
//    /** * jooq로 표쥰 sql 생성후 jdbctemplate 을 사용하여 데이터 불러오기 * * @return
//     * @return*/
//    public List<Map<String, Object>> getList() {
//        String sql = dslContext.select(DSL.field("id"), DSL.field("name"))
//                .from(DSL.table("user")).getSQL();
//        //log.debug("string sql = {}", sql);
//        System.out.println(jdbcTemplate.queryForList(sql));
//        return jdbcTemplate.queryForList(sql);
//    }




    /** * jooq로 데이터를 가져와 domain 객체로 바꿔주기 * @return */
    /*
    public List<Users> getListObject() {
        return dslContext.select(DSL.field("user_id"), DSL.field("user_name"))
                .from(DSL.table("users")).fetchInto(Users.class);
    }
    */

    //Result<Record> selected = dslContext.selectFrom(DSL.table("user")).getResult();
    //Result test = dslContext.select(field("name")).from(table("user")).getResult();
    //System.out.println(test);

    // .where(field("email").equal("함승균1"))
    // .fetch();
                /*
                .select(field("jooq_board.seq"), field("jooq_board.author"), field("jooq_board.content"), field("jooq_board.read_cnt"), field("jooq_board.add_date"), field("jooq_board.mod_date"))
                .from(table("jooq_board"))
                .where(field("jooq_board.seq").greaterThan(0))
                .limit(2)
                .offset(1)
                .getSQL();
        */

//    public List<Map<String, Object>> list() {
//
//        String sql = dslContext.select(field("jooq_board.seq"), field("jooq_board.author"), field("jooq_board.content"), field("jooq_board.read_cnt"), field("jooq_board.add_date"), field("jooq_board.mod_date"))
//                .from(table("jooq_board"))
//                .where(field("jooq_board.seq").greaterThan(0))
//                .limit(2)
//                .offset(1)
//                .getSQL();
//
//        System.out.println("sql : " + sql);
//
//        JooqBoard jooq_board = Jooq.JOOQ.JOOQ_BOARD;
//
//        int sql2 = dslContext.insertInto(jooq_board).columns(jooq_board.AUTHOR, jooq_board.CONTENT)
//                .values("aaa","bbb").execute();
//
//
//        return sql2;//jdbcTemplate.queryForList(sql, 0, 2, 1);
//
//    }



}
