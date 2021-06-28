package com.ymm.back.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ymm.back.dto.MessageDTO;
import com.ymm.back.dto.NotificationDTO;
import com.ymm.back.entity.MessageEntity;
import com.ymm.back.service.MessageService;
import com.ymm.back.service.ProjectService;
import com.ymm.back.utils.JwtUtil;
import com.ymm.back.utils.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class ChatController {

	@Autowired
    ProjectService projectService;
	@Autowired
    MessageService messageService;
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping
    public ResponseEntity<?> addChatList(@RequestBody MessageEntity me){
    	System.out.println(me);
    	messageService.save(me);
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
	
	@MessageMapping("/message/text/{userId}/project/{pid}")
    @SendTo("/topic/{pid}")
    public MessageEntity wsMessageMapping(@DestinationVariable int userId, @DestinationVariable int pid, MessageDTO messageDTO) {
        MessageEntity messageEntity = new MessageEntity(userId, pid, MessageTypeEnum.TEXT.toString(), messageDTO.getMessage());
        MessageEntity msg = messageService.save(messageEntity);
        
        NotificationDTO notificationDTO = messageService.createNotificationDTO(msg);
        
        List<Integer> toSend = messageService.createNotificationList(userId, pid);
        System.out.println(msg);
        toSend.forEach(toUserId -> messagingTemplate.convertAndSend("/topic/notification/" + toUserId, msg));
        return msg;
//        return messageService.createMessageDTO(msg.getId(), msg.getType(), msg.getProjectMemberFk(), "2021-06-27", msg.getProjectFk(), msg.getMessage());
    }
	
	
	@SubscribeMapping("/group/get/{pid}")
	public List<MessageEntity> getGroupMessage(@DestinationVariable int pid) {
		List<MessageEntity> messages=new ArrayList<MessageEntity>();
		messages=messageService.findByPID(pid);
		return messages;
	}
	
	
	
	
	
}
