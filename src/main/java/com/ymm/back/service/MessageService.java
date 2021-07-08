package com.ymm.back.service;

import com.ymm.back.domain.tables.Project;
import com.ymm.back.domain.tables.ProjectMember;
import com.ymm.back.pojos.ProjectP;
import com.ymm.back.s3.FileUploadService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class MessageService {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    private final FileUploadService fileUploadService;
    @Autowired
    public MessageService(DSLContext dslContext, JdbcTemplate jdbcTemplate, FileUploadService fileUploadService) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
        this.fileUploadService = fileUploadService;
    }

    private static final String[] colorsArray =
            {
                    "#FFC194", "#D2FF94", "#9DFF9", "#94FFC1",
                    "#94FFF7", "#FFAFFA", "#FFAFD2", "#FFB4AF",
                    "#FFDCAF", "#FAFFAF", "#D2FFAF"
            };

    private static final Map<Integer, String> colors = new HashMap<>();

    public String getRandomColor() {
        return colorsArray[new Random().nextInt(colorsArray.length)];
    }

    @Transactional
    public List<Integer> createNotificationList(int memberId, int projectId) {
        Project project = Project.PROJECT;
        ProjectMember member = ProjectMember.PROJECT_MEMBER;

        List<Integer> toSend = new ArrayList<>();
        var optionalGroupEntity = dslContext.selectFrom(project).where(project.ID.eq(projectId)).fetchInto(ProjectP.class).get(0);

        if (optionalGroupEntity!=null) {
            var members = dslContext.selectFrom(member).where(member.PROJECT_ID.eq(projectId)).or(member.USER_ID.eq(memberId)).fetchInto(ProjectP.class);
            members.forEach(ProjectP->toSend.add(ProjectP.getId()));
        }
        return toSend;
    }


//            pms.getProjecetMembers(Integer.toString(pid), "").forEach(projectMemberEntity->toSend.add(projectMemberEntity.getId()));;
//            public List<ProjectMemberEntity> getProjecetMembers(String pid,String uid){
//                return pmr.getProjecetMeberByUidORPid(pid,uid);
//            }
//            @Query(value = "SELECT * FROM project_member WHERE project_fk=:pid OR user_fk=:uid",nativeQuery = true)
//            List<ProjectMemberEntity> getProjecetMeberByUidORPid(@Param(value="pid") String pid,@Param(value="uid") String uid);

}
