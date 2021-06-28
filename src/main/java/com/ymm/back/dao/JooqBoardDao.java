package com.ymm.back.dao;

//import com.example.joqdemo.domain.Jooq;
//import com.example.joqdemo.domain.tables.JooqBoard;

import org.springframework.stereotype.Repository;

@Repository
public class JooqBoardDao {

//    private final DSLContext dslContext;
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JooqBoardDao(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
//        this.dslContext = dslContext;
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int/*List<Map<String, Object>>*/ list() {
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