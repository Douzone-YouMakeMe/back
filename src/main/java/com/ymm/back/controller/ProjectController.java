package com.ymm.back.controller;


import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.ProjectMember;
import com.ymm.back.domain.tables.User;
import com.ymm.back.domain.tables.records.ProjectRecord;
import com.ymm.back.pojos.ProjectM;
import com.ymm.back.pojos.ProjectP;
import com.ymm.back.s3.FileUploadService;
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

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    private final FileUploadService fileUploadService;
    @Autowired
    public ProjectController(DSLContext dslContext, JdbcTemplate jdbcTemplate, FileUploadService fileUploadService) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
        this.fileUploadService = fileUploadService;
    }
    // 프로젝트 정보 모두 가져오기
    @GetMapping
    public List<Map<String, Object>> selectProjectAll(){
        String sql = dslContext.selectFrom(DSL.table("project")).getSQL();
        return jdbcTemplate.queryForList(sql);
    }
    // 프로젝트 단일 정보 가져오기
    //localhost:8080/project/1
    @GetMapping("/{id}")
    public ResponseEntity<?> selectProjectOne(@PathVariable("id") int id){
        Project project = Project.PROJECT;
        var sql = dslContext.select().from(project).where(project.ID.eq(id)).fetchInto(ProjectP.class);
        /* //조회수 증가 람다식. 익명함수의 파라미터 문제로 변환중.
        // update projectboard set view_count=IFNULL(view_count,0)+1
        // 서브쿼리가 차라리 문장이 덜 길어지것다... 그냥 서브쿼리로 변경.
        ViewCount viewCountUp=()->{
            return dslContext.select(DSL.field("view_count")).from(project).where(project.ID.eq(id)).fetchInto(ProjectP.class).get(0).getViewCount()+1;
        };
        */
        //System.out.println(dslContext.select().from(project).where(project.ID.eq(id)).fetchInto(ProjectP.class).get(0).getViewCount());
        //int count = dslContext.select(project.VIEW_COUNT).from(project).where(project.ID.eq(id)).fetchInto(ProjectP.class).get(0).getViewCount();
        var countUp = dslContext.update(project)
                .set(project.VIEW_COUNT,
                        dslContext.select(project.VIEW_COUNT).from(project).where(project.ID.eq(id)).fetchInto(ProjectP.class).get(0).getViewCount()+1)
                .where(project.ID.eq(id))
                .execute();

        return ResponseEntity.status(200).body(sql);
    }
    // user_id가 속한 리스트를 모두 받아오기
    //localhost:8080/project/?id=1
    @GetMapping("/user/{id}")
    public ResponseEntity<?> selectProjectOne2(@PathVariable("id") int id){
        Project project = Project.PROJECT;
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        //SELECT * FROM project WHERE project.id IN (SELECT project_member.project_id FROM project_member WHERE project_member.user_id=15 AND status="approved");
        // var sub = dslContext.select(member.PROJECT_ID).from(member).where(member.USER_ID.eq(id).and(member.STATUS.eq("approved")));
        //var sql2 = dslContext.select().fro
        var sql = dslContext.select().from(project).where(project.ID.in(
                dslContext.select(member.PROJECT_ID).from(member)
                        .where(member.USER_ID.eq(id).and(member.STATUS.eq("approved"))).fetchInto(String.class))).fetchInto(ProjectP.class);
        //System.out.println(sql);

        //.where(project.USER_ID.eq(id)).fetchInto(ProjectP.class);

        return ResponseEntity.status(200).body(sql);
    }

    // 프로젝트 만들기! multipart/form
    // 필수 파라미터는 만드는 사람의 userId와, thumbnail 2가지이다.
    // UI 안내하기! total 설정 안하고 누르면 기본 정원수가 5명이라고 설정합니다!
    // localhost:8080/project
    @PostMapping
    public ResponseEntity<?> insertProject(@ModelAttribute ProjectM input){
        Project project = Project.PROJECT;
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        String result="";
        String thumbnail="";
        if(input.getThumbnail() != null){
            // 썸네일 있으면 집어넣기
            thumbnail = fileUploadService.uploadImage(input.getThumbnail());
            //System.out.println(thumbnail);
        }
        int total= 1;
        if(input.getTotal() == null){
            total = 5;
        } else {
            total = input.getTotal();
        }
        var sql = dslContext.insertInto(project)
                .columns(project.USER_ID,project.NAME,project.STARTED_TIME,project.CONTENTS,project.VIEW_COUNT,project.THUMBNAIL,project.DESCRIPTION,project.AUTHORITY,project.TOTAL,project.FINISHED_TIME) //project.NAME ,project.FINISHED_AT
                .values(input.getUserId(), input.getName(),input.getStartedTime(),input.getContents(),0,thumbnail,input.getDescription(),input.getAuthority(),total,input.getFinishedTime())
                .execute();
        // 프로젝트 ID 를 프로젝트 작성자의 것으로 받아서 찾은다음,
        var selectPid = dslContext.select(project.ID).from(project).orderBy(project.ID.desc()).limit(1).fetchInto(Integer.class).get(0);
        // 첫번째 프로젝트 지원자이자, 소우쥬로서 넣는다.
        var sql2 = dslContext.insertInto(member,member.USER_ID,member.PROJECT_ID, member.STATUS)
                // 지원서 제출 시, pending으로 status 고정. status 변경은 patch에서만.
                //.columns(member.USER_ID,member.PROJECT_ID,member.APPLIED_POSITION,member.COMMENTS,member.PORTFOLIO_FILE,member.PORTFOLIO_URL, member.STATUS)
                .values(input.getUserId(), selectPid, "approved")
                .execute();
        if(sql==1){
            result = "프로젝트가 생성되었습니다.";
        } else {
            return ResponseEntity.status(400).body("올바른 데이터를 입력해서 생성해 주세요.");
        }
        return ResponseEntity.status(201).body(result);
    }
    //프로젝트 정보 변경
    @PatchMapping("/{id}")
    public String updateProject(@PathVariable("id") int id, @ModelAttribute ProjectM input){
        Project project = Project.PROJECT;
        String result="";
        ProjectRecord record = dslContext.newRecord(project);
        if(input.getThumbnail() != null /*&& !input.getThumbnail().isEmpty() */){
            record.set(project.THUMBNAIL, fileUploadService.uploadImage(input.getThumbnail()));
        }
        if(input.getName() != null)
            record.set(project.NAME,input.getName());
        if(input.getDescription() != null)
            record.set(project.DESCRIPTION, input.getDescription());
        if(input.getContents() != null)
            record.set(project.CONTENTS, input.getContents());
        if(input.getTotal() != null)
            record.set(project.TOTAL, input.getTotal());
        if(input.getStartedTime() != null)
            record.set(project.STARTED_TIME, input.getStartedTime());
        if(input.getFinishedTime() != null)
            record.set(project.FINISHED_TIME, input.getFinishedTime());
        if(input.getContents() != null)
            record.set(project.CONTENTS, input.getContents());
        //
        int sql = dslContext.update(project)
                .set(record)
                .where(project.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "성공!";
        } else {
            result = "그런거 없습니다.";
        }
        return result;
    }
    // 프로젝트 삭제
    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable("id") int id){
        Project project = Project.PROJECT;
        User user = User.USER;
        String result="";
        int sql = dslContext.deleteFrom(project)
                .where(project.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "삭제 성공!";
        } else {
            result = "해당 프로젝트가 없습니다. 정확한 정보로 삭제요청을 발송해 주세요.";
        }
        return result;
    }


}
