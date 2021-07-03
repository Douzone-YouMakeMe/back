package com.ymm.back.controller;

import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.ProjectMember;
import com.ymm.back.domain.tables.User;
import com.ymm.back.domain.tables.records.ProjectMemberRecord;
import com.ymm.back.pojos.ProjectMemberM;
import com.ymm.back.pojos.ProjectMemberP;
import com.ymm.back.pojos.ProjectP;
import com.ymm.back.pojos.UserP;
import com.ymm.back.s3.FileUploadService;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import static org.jooq.impl.DSL.defaultValue;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class ProjectMemberController {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    private final FileUploadService fileUploadService;
    @Autowired
    public ProjectMemberController(DSLContext dslContext, JdbcTemplate jdbcTemplate, FileUploadService fileUploadService) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
        this.fileUploadService = fileUploadService;
    }
    // 해당 프로젝트의 소속 멤버들 검색. id === project_id
    // localhost:8080/member/project?id=1
    @GetMapping(path = "/project")
    public ResponseEntity<?> selectAllMemberOfProject(@RequestParam Map<String, ?> project){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        StringBuilder sb = new StringBuilder();
        project.entrySet().forEach(entry ->{
            sb.append(entry.getValue());
        });
        int projectId = Integer.parseInt(sb.toString());
        var result = dslContext.select().from(member)
                .where(member.PROJECT_ID.eq(projectId)).fetchInto(ProjectMemberP.class);
        if(result.isEmpty()){
            return ResponseEntity.status(404).body("해당 프로젝트는 존재하지 않습니다.");
        }
        return ResponseEntity.status(200).body(result);//jdbcTemplate.queryForList(sql).get(0);
    }

    // 프로젝트의 멤버 한명에 대한 정보 검색. id 파라미터로 가져오기
    //localhost:8080/member/{project_member.id}
    @GetMapping("/{id}")
    public ResponseEntity<?> selectMemberOfProject(@PathVariable("id") int id){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        /*List<com.ymm.back.domain.tables.pojos.Work>*/
        var result = dslContext.select().from(member).where(member.ID.eq(id)).fetchInto(ProjectMemberP.class);
        if(result.isEmpty()){
            return ResponseEntity.status(404).body("해당 프로젝트 멤버는 존재하지 않습니다.");
        }
        return ResponseEntity.status(200).body(result);//jdbcTemplate.queryForList(sql).get(0);
    }

    /**
     * 프로젝트에 지원서 제출!
     * POST / localhost:8080/member
     * model attribue url null chk
     * {
     *     "userId": "1",
     *     "projectId": "2", //이거 없는 프로젝트를 입력하면 404 인데도 500을 띄움에 유의.
     *     "PORTFOLIO_FILE": "디폴트 아이콘 아무거나 클라이언트에서 넣기."
     *     "total":
     *     // "POST는 put, patch와 달리 칼럼 3개가 필수입니다.
     *     // 아니면 Exception 처리 추가작업 필요"
     * }
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> insertMemberMulti(@ModelAttribute ProjectMemberM input){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        String result ="";
        /* //StringBuilder sb = new StringBuilder();
        input.entrySet().forEach(entry ->{
            sb.append(entry.getValue());
        });
        */
        //defaultValue(fileUploadService.uploadImage(input.getPortfolioFile()))
        try {
            var sql = dslContext.insertInto(member,member.USER_ID,member.PROJECT_ID,member.NAME,member.APPLIED_POSITION,member.COMMENTS,member.PORTFOLIO_FILE,member.PORTFOLIO_URL, member.STATUS)
                    // 지원서 제출 시, pending으로 status 고정. status 변경은 patch에서만.
                    //.columns(member.USER_ID,member.PROJECT_ID,member.NAME,member.APPLIED_POSITION,member.COMMENTS,member.PORTFOLIO_FILE,member.PORTFOLIO_URL, member.STATUS)
                    .values(input.getUserId(),input.getProjectId(),input.getName(),input.getAppliedPosition(),input.getComments(),fileUploadService.uploadImage(input.getPortfolioFile()),input.getPortfolioUrl(), "pending")
                    .execute();
            if(sql==1){
                result = "프로젝트 지원서가 제출되었습니다.";
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body("올바른 데이터를 입력해서 제출해 주세요.");
        }

        return ResponseEntity.status(201).body(result);
    }
    /**
     * 프로젝트멤버 정보 변경.
     * 주로 승인, 거절은 별도의 patch api로 하단에 후술.
     * localhost:8080/member/5
     * application/json 바디 예시
     * {
     *     "name": "",
     *     "appliedPosition": "아이 엠 그것"
     * }
     */
    @PutMapping(path = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateMember(@PathVariable("id") int id, @ModelAttribute ProjectMemberM input){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        String result ="";
        ProjectMemberRecord record = dslContext.newRecord(member);
        // 이름 참여가능일 직무 포트폴리오 2개 하고싶은말
        if(input.getPortfolioFile() != null)
            record.set(member.PORTFOLIO_FILE,fileUploadService.uploadImage(input.getPortfolioFile()));
        if(input.getName() != null)
            record.set(member.NAME,input.getName());
        if(input.getStartedTime() != null)
            record.set(member.STARTED_TIME, input.getStartedTime());
        if(input.getAppliedPosition() != null)
            record.set(member.APPLIED_POSITION, input.getAppliedPosition());
        if(input.getComments() != null)
            record.set(member.COMMENTS,input.getComments());
        if(input.getPortfolioUrl() != null)
            record.set(member.PORTFOLIO_URL,input.getPortfolioUrl());

        var sql = dslContext.update(member)
                .set(record)
                .where(member.ID.eq(id))
                .execute();
        if(sql ==1){
            result = "프로젝트 멤버 정보가 수정되었습니다.";
        } else {
            return ResponseEntity.status(400).body("허용되는 타입의 정보로 입력해서 수정해 주세요.");
        }
        return ResponseEntity.status(201).body(result);
    }

    /**
     * 프로젝트 멤버로 참여 approved, rejected 결정하기!
     * 신청한 상태면 status는 pending 상태.
     * PATCH / localhost:8080/member/{id}
     * 조건: 해당 로우의 id, project_id, user_id, 변경할 status
     * if(status == "approved"), then total-1 하고
     * else면 그대로 둔다.
     *  --> 06.30 담배타임중 요청으로 project_id, user_id 받아서 검증하는 로직 뺀다.
     *  그냥 프로젝트 멤버 신청서 id만 보고 status 변경 시도. (도중에 get project 하나 추가됨.)
     *  --> x. 다시 협의. projct_id 를 가지고 project의 total-1 하는 쿼리로 진행.
     *  input : projectMember.id(pathVariable), projectId, userId, status(approved, rejected)
     *  output : success or fail
     * // 주의 : approved로 이미 승인했는데, 다시 reject하는 기능은 없습니다!
     * // 그거 만드려면 api 갈아 엎어야됨.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") int id, @RequestBody ProjectMemberP input){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        Project project = Project.PROJECT;
        String result ="";
        int flagOfQuery = 0; //이게 1이 되면 승인각.
        if(input.getStatus().equalsIgnoreCase("approved")){
            var sql = dslContext.update(member)
                    .set(member.STATUS, input.getStatus())
                    .where(member.ID.eq(id))
                    .execute();
            //System.out.println("승인되었습니까? 1이면 성공: "+sql);
            flagOfQuery = sql;
            // 서브쿼리 떼어낸 버전.
            // 해당 프로젝트의 TO가 몇명인지 본다.
            var total = dslContext.select().from(project)
                    .where(project.ID.eq(input.getProjectId())).fetchInto(ProjectP.class).get(0).getTotal();
            // 그리고 토탈을 하나 까버린다.
            //System.out.println("지금 티오몇명입네까?: "+total);
            var totalCut = dslContext.update(project)
                    .set(project.TOTAL, (total-1))
                    .where(project.ID.eq(input.getProjectId()))
                    .execute();
            flagOfQuery = totalCut;
            //System.out.println("토탈 인원티오가 까졌습니까?: "+totalCut);
        } else if(input.getStatus().equalsIgnoreCase("rejected")) {
            var sql = dslContext.update(member)
                    .set(member.STATUS, input.getStatus())
                    .where(member.ID.eq(id))
                    .execute();
            flagOfQuery = sql;
        }
        if(flagOfQuery == 0){
            return ResponseEntity.status(400).body("승인 실패. 관리자에게 문의 바랍니다.");
        } else {
            result = "프로젝트 승인 결정을 성공적으로 마쳤습니다.";
        }

        return ResponseEntity.status(201).body(result);
    }

    /**
     * 프로젝트 탈퇴 로직.
     * 프로젝트를 탈퇴할 때, projectMember.id를 가지고 탈퇴 들어간다.
     * 하지만, 스토리보드 상으로는 user.password를 입력해야 탈퇴가 된다.
     * 그렇다면...
     * input: projectMember.id, user.password
     * process: select user.password from user
     * where member.user_id 한 다음, equals ==true면 결정!
     * output: success or fail
     *  (T/F) deleteMember(projectMember.id, user.password)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") int id, @RequestBody UserP input){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        Project project = Project.PROJECT;
        User user = User.USER;
        String result ="";
        var deleted = 0;
        // 유저아이디를 project_member를 통해 조회.
        var user_id = dslContext.select(DSL.field("user_id"))
                .from(member).where(member.ID.eq(id)).fetchInto(ProjectMemberP.class).get(0).getUserId();
        //System.out.println("유저아이디 조회되었스므니까?: "+user_id);
        var pw = dslContext.select(DSL.field("password"))
                .from(user).where(user.ID.eq(user_id)).fetchInto(UserP.class).get(0).getPassword();
        //System.out.println("패스워드 나왔습니까?: "+pw);
        //System.out.println("그래서 입력한 패스워드는 무엇입네까?: "+input.getPassword());
        var flag = input.getPassword().equals(pw);
        //System.out.println("그래서 대조하니 맞습니까? "+flag);
        if(flag){
            deleted = dslContext.deleteFrom(member).where(member.ID.eq(id)).execute();
        } else {
            return ResponseEntity.status(401).body("비밀번호가 틀렸습니다. 탈퇴 및 삭제 실패.");
        }
        if(deleted==1){
            result = "프로젝트 탈퇴 및 삭제 성공.";
        }

        return ResponseEntity.status(201).body(result);
    }




                /* //원본 서브쿼리 SQL문이다. 그냥 2개로 분리해서 사용해야 길이가 줄어들 듯...
            // .where(member.ID.eq(id).and(member.USER_ID.eq(input.getUserId())))
            var sql2 = dslContext.update(project)
                    .set(project.TOTAL, (
                            dslContext.select(DSL.field("total")).
                                    from(project).where(project.ID.eq("projct_id")).getResult().getValue(0,DSL.field("total"))-1   )    )
                    .where()
                    .execute();
            */

    // 프로젝트에서 멤버가 탈퇴하는 api. 일단 프로젝트 탈퇴하는 순간 total+1로 정원이 늘어난다에 유의.







}
