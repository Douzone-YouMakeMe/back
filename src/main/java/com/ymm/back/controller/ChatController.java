package com.ymm.back.controller;

import com.ymm.back.domain.tables.Message;
import com.ymm.back.domain.tables.records.MessageRecord;
import com.ymm.back.dto.MessageDTO;
import com.ymm.back.pojos.MessageP;
import com.ymm.back.s3.FileUploadService;
import com.ymm.back.service.MessageService;
import com.ymm.back.utils.JwtUtil;
import com.ymm.back.utils.MessageTypeEnum;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/chat")
public class ChatController {
    @Autowired
    MessageService messageService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;
    private final FileUploadService fileUploadService;
    @Autowired
    public ChatController(DSLContext dslContext, JdbcTemplate jdbcTemplate, FileUploadService fileUploadService) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping
    public ResponseEntity<?> addChatList(@RequestBody MessageP me){
        Message message = Message.MESSAGE; //이건 table type
        //System.out.println(me);
        //messageService.save(me);
        var sql = dslContext.insertInto(message)
                .set(message.MEMBER_ID, me.getMemberId())
                .set(message.PROJECT_ID, me.getProjectId())
                .set(message.ID, me.getId())
                .set(message.MESSAGE_, me.getMessage())
                .set(message.TYPE, me.getType())
                .execute();
        return ResponseEntity.status(200).build();
    }
    @GetMapping
    public String testRoute(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("authorization");

        if (StringUtils.isEmpty(requestTokenHeader)) {

            return null;
        }
        return jwtUtil.getUserNameFromJwtToken(requestTokenHeader.substring(7));
    }
    @MessageMapping("/message/text/{projectMemberId}/project/{projectId}")
    @SendTo("/topic/{projectId}")
    public MessageRecord wsMessageMapping(@DestinationVariable int projectMemberId, @DestinationVariable int projectId, MessageDTO messageDTO) {
        Message message = Message.MESSAGE; //이건 table type
        MessageRecord record = new MessageRecord();
        record.set(message.PROJECT_ID, projectId);
        record.set(message.MEMBER_ID, projectMemberId);
        record.set(message.TYPE, MessageTypeEnum.TEXT.toString());
        record.set(message.MESSAGE_, messageDTO.getMessage());
        //이게 1이면 성공적으로 메세지를 보내기는 한 것입니다. db에도 메세지 저장됨.
        // 지금은 테스트로 status="approved" 생략. 나중에 where절 추가 후 exception 처리.
        var sql = dslContext.insertInto(message).set(record).execute();
        System.out.println(sql +" "+record);
        // 일단, record 안에는 그 기록(=msg)이 들어있고, 그거를 담아서 알림을 보내는 기능이 있다.

        //NotificationDTO notificationDTO = messageService.createNotificationDTO(msg);
        List<Integer> toSend = messageService.createNotificationList(projectMemberId, projectId);
        System.out.println(record);


        toSend.forEach(toProjectMemberId -> messagingTemplate.convertAndSend("/topic/notification/" + toProjectMemberId, record));
        return record;
//        return messageService.createMessageDTO(msg.getId(), msg.getType(), msg.getProjectMemberFk(), "2021-06-27", msg.getProjectFk(), msg.getMessage());
    }


//    @SubscribeMapping("/group/get/{pid}")
//    public List<MessageEntity> getGroupMessage(@DestinationVariable int projectId) {
//        List<MessageEntity> messages=new ArrayList<MessageEntity>();
//        messages=messageService.findByProjectId(projectId);
//        return messages;
//    }



}
