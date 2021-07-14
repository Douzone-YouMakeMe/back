package com.ymm.back.controller;

import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.ProjectMember;
import com.ymm.back.domain.tables.User;
import com.ymm.back.domain.tables.records.ProjectMemberRecord;
import com.ymm.back.dto.MemberDTO;
import com.ymm.back.dto.ProjectMemberDTO;
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

import java.util.Map;

import static org.jooq.impl.DSL.defaultValue;
import static org.jooq.impl.DSL.notExists;

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
    // 신규생성 07.08
    // 프로젝트 신청서 정보 받아오기 by 유저가 신청한 곳. user_id. id 파라미터로 가져오기
    // 2021.07.12
    // 수정 : join절 잘못된 부분 변경 : where m.user_id=p.user_id
    // -> p.id=m.project_id
    // localhost:8080/member/by-user/15
    @GetMapping("/by-user/{id}")
    public ResponseEntity<?> selectMemberOfProjectbyUserId(@PathVariable("id") int id){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        Project project = Project.PROJECT;
        /*List<com.ymm.back.domain.tables.pojos.Work>*/
        // select distinct * from project p, project_member m where m.user_id=p.user_id AND p.user_id={id};
        // 원문: select distinct * from project join project_member on project.id=project_member.project_id and project_member.user_id=23;
        //select distinct * from project p , project_member m where p.id=m.project_id AND m.userId={id};
        //var result = dslContext.select().distinctOn().from(member).join(project)on(member).and(project.USER_ID.eq(id)).fetchInto(ProjectMemberDTO.class);
        // .distinctOn()
        //var result = dslContext.select().from(project).join(member).on(project.ID.equal(member.PROJECT_ID)).and(member.USER_ID.eq(id)).fetchInto(ProjectMemberDTO.class);

        var result = dslContext.select(project.ID.as("projectId"),project.USER_ID.as("ownerUserId"),project.NAME,project.CONTENTS,project.VIEW_COUNT,project.THUMBNAIL,project.DESCRIPTION.as("projectDescription"),project.AUTHORITY,project.TOTAL,project.TYPE,project.URL,project.TO,project.STARTED_TIME,project.CREATE_TIME,project.UPDATE_TIME,project.FINISHED_TIME,
                member.ID,member.USER_ID,member.STATUS,member.APPLIED_POSITION,member.COMMENTS,member.PORTFOLIO_FILE,member.PORTFOLIO_URL,member.DESCRIPTION,member.AUTH,member.APPLIED_TIME,member.CREATE_TIME,member.UPDATE_TIME)
                .distinctOn().from(project).join(member).on(project.ID.equal(member.PROJECT_ID)).and(member.USER_ID.eq(id)).fetchInto(ProjectMemberDTO.class);
        //var result2 = dslContext.select().distinctOn().from(project).join(member).on(project.ID.equal(member.PROJECT_ID)).and(member.USER_ID.eq(id)).fetch();
        //System.out.println(result2);
        //List<Record> aaa = result;
//        for(int i=0;i<result.size();i++){
//            aaa.set(i, result.get(i));
//            //aaa.set(result.get(i));
//            //aaa = result.get(i);
//        }
        //var result = dslContext.select().from(member).where(member.USER_ID.eq(id)).fetchInto(ProjectMemberP.class);
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
        String fileUrl="";
        if(input.getPortfolioFile() != null){
            fileUrl = fileUploadService.uploadImage(input.getPortfolioFile());
        }

        try {
            var sql = dslContext.insertInto(member,member.USER_ID,member.PROJECT_ID,member.APPLIED_POSITION,member.COMMENTS,member.PORTFOLIO_FILE,member.PORTFOLIO_URL, member.STATUS, member.DESCRIPTION, member.APPLIED_TIME)
                    // 지원서 제출 시, pending으로 status 고정. status 변경은 patch에서만.
                    //.columns(member.USER_ID,member.PROJECT_ID,member.APPLIED_POSITION,member.COMMENTS,member.PORTFOLIO_FILE,member.PORTFOLIO_URL, member.STATUS)
                    .values(input.getUserId(),input.getProjectId(),input.getAppliedPosition(),input.getComments(),fileUrl,input.getPortfolioUrl(), "pending", input.getDescription(), input.getAppliedTime())
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
     *
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
        if(input.getAppliedTime() != null)
            record.set(member.APPLIED_TIME, input.getAppliedTime());
        if(input.getAppliedPosition() != null)
            record.set(member.APPLIED_POSITION, input.getAppliedPosition());
        if(input.getComments() != null)
            record.set(member.COMMENTS,input.getComments());
        if(input.getPortfolioUrl() != null)
            record.set(member.PORTFOLIO_URL,input.getPortfolioUrl());
        if(input.getDescription() != null)
            record.set(member.DESCRIPTION,input.getDescription());

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
     * // 그기능은 전체 구조 변경 필요.
     * 2021.07.12 수정: 'total'은 이제부터 총원수, 'to'는 현재 모집정원 숫자로 변경되어 컬럼이 추가됩니다.
     */
    //@PutMapping("/status/{id}")
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
//            var to = dslContext.select().from(project)
//                    .where(project.ID.eq(input.getProjectId())).fetchInto(ProjectP.class).get(0).getTo();
            // 그리고 to를 하나 까버린다.
            //System.out.println("지금 티오몇명입네까?: "+total);
//            var totalCut = dslContext.update(project)
//                    .set(project.TO, (to-1))
//                    .where(project.ID.eq(input.getProjectId()))
//                    .execute();
//            flagOfQuery = totalCut;
            //System.out.println("토탈 인원티오가 까졌습니까?: "+totalCut);
        } else if(input.getStatus().equalsIgnoreCase("rejected")) {
            var sql = dslContext.update(member)
                    .set(member.STATUS, input.getStatus())
                    .where(member.ID.eq(id))
                    .execute();
            flagOfQuery = sql;
        }
        if(flagOfQuery == 0){
            return ResponseEntity.status(400).body("승인여부 등록 실패. 관리자에게 문의 바랍니다.");
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
    public ResponseEntity<?> deleteMember(@PathVariable("id") int id){
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        String result ="";
        var deleted = 0;
        deleted = dslContext.deleteFrom(member).where(member.ID.eq(id)).execute();
        if(deleted==1){
            result = "프로젝트 탈퇴 및 삭제 성공.";
        } else {
            return ResponseEntity.status(400).body("이미 삭제된 멤버 입니다.");
        }

        return ResponseEntity.status(200).body(result);
    }

//    /**
//     * 프로젝트 탈퇴 로직.
//     * 프로젝트를 탈퇴할 때, projectMember.id를 가지고 탈퇴 들어간다.
//     * 하지만, 스토리보드 상으로는 user.password를 입력해야 탈퇴가 된다.
//     * 그렇다면...
//     * input: projectMember.id, user.password
//     * process: select user.password from user
//     * where member.user_id 한 다음, equals ==true면 결정!
//     * output: success or fail
//     *  (T/F) deleteMember(projectMember.id, user.password)
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteMember(@PathVariable("id") int id, @RequestBody UserP input){
//        ProjectMember member = ProjectMember.PROJECT_MEMBER;
//        Project project = Project.PROJECT;
//        User user = User.USER;
//        String result ="";
//        var deleted = 0;
//        // 유저아이디를 project_member를 통해 조회.
//        var user_id = dslContext.select(DSL.field("user_id"))
//                .from(member).where(member.ID.eq(id)).fetchInto(ProjectMemberP.class).get(0).getUserId();
//        //System.out.println("유저아이디 조회되었스므니까?: "+user_id);
//        var pw = dslContext.select(DSL.field("password"))
//                .from(user).where(user.ID.eq(user_id)).fetchInto(UserP.class).get(0).getPassword();
//        //System.out.println("패스워드 나왔습니까?: "+pw);
//        //System.out.println("그래서 입력한 패스워드는 무엇입네까?: "+input.getPassword());
//        var flag = input.getPassword().equals(pw);
//        //System.out.println("그래서 대조하니 맞습니까? "+flag);
//        if(flag){
//            deleted = dslContext.deleteFrom(member).where(member.ID.eq(id)).execute();
//        } else {
//            return ResponseEntity.status(401).body("비밀번호가 틀렸습니다. 탈퇴 및 삭제 실패.");
//        }
//        if(deleted==1){
//            result = "프로젝트 탈퇴 및 삭제 성공.";
//        }
//
//        return ResponseEntity.status(201).body(result);
//    }

    //@ResponseBody

    /**
     * 기획상 추가사항 발생.
     * 프로젝트 멤버와 유저를, project_id 기준으로 합쳐서 받아옵시다.
     * status='approved'된 유저들만 받아오므로 이것이 details 화면에 들어감.
     * @return
     */
    // localhost:8080/member?projectId=1
    @GetMapping
    public ResponseEntity<?> getUserAndMemberByProjectId(@RequestParam Integer projectId) {
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        User user = User.USER;
        //select * from user u, project_member m where u.id = m.user_id AND m.project_id = 1;
        var joined = dslContext.select().from(user,member)
                .where(user.ID.eq(member.USER_ID)).and(member.PROJECT_ID.eq(projectId)).and(member.STATUS.eq("approved")).fetchInto(MemberDTO.class);
        //System.out.println(joined);
        return ResponseEntity.status(200).body(joined);
    }

    /**
     * 07.12
     * 추가사항.
     * 프로젝트 멤버와 유저를, project_id 기준으로 합쳐서 받아옵시다.
     * 이번에는 status 관계 없이 전체를 받아온다.
     * -> 추가. status=='rejected'는 뺀다.
     * @return
     */
    // localhost:8080/member/all?projectId={해당프로젝트id}
    @GetMapping("/all")
    public ResponseEntity<?> getAllMemberByProjectId(@RequestParam Integer projectId) {
        ProjectMember member = ProjectMember.PROJECT_MEMBER;
        User user = User.USER;
        //select * from user u, project_member m where u.id = m.user_id AND m.project_id = 1;
//        System.out.println(dslContext.select().from(user,member)
//                .where(user.ID.eq(member.USER_ID)).and(member.PROJECT_ID.eq(projectId)).fetchInto(MemberDTO.class));
//        System.out.println(dslContext.select().from(user,member)
//                .where(user.ID.eq(member.USER_ID)).and(member.PROJECT_ID.eq(projectId)).and(member.STATUS.eq("rejected")).fetchInto(MemberDTO.class) );
        var joined = dslContext.select().from(user,member)
                .where(user.ID.eq(member.USER_ID)).and(member.PROJECT_ID.eq(projectId))
                .andNot(member.STATUS.eq("rejected"))
//                .andNotExists(dslContext.select().from(user,member)
//                        .where(user.ID.eq(member.USER_ID)).and(member.STATUS.eq("rejected")))
                .fetchInto(MemberDTO.class);
        System.out.println(joined);
        return ResponseEntity.status(200).body(joined);
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
