package com.ymm.back.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import com.ymm.back.dto.MessageDTO;
import com.ymm.back.dto.NotificationDTO;
import com.ymm.back.entity.MessageEntity;
import com.ymm.back.entity.ProjectEntity;
import com.ymm.back.repository.MessageRepository;
import com.ymm.back.utils.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
	@Autowired
    MessageRepository messageRepository;
	@Autowired
	UserService userService;
	@Autowired
	ProjectService projecetService;
	@Autowired
	ProjectMemberService pms;
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

    public MessageEntity createAndSaveMessage(int userId, int groupId, String type, String data) {
        MessageEntity msg = new MessageEntity(userId, groupId, type, data);
        return messageRepository.save(msg);
    }

    public void flush() {
        messageRepository.flush();
    }

    public MessageEntity save(MessageEntity messageEntity) {
        return messageRepository.saveAndFlush(messageEntity);
    }

    public List<MessageEntity> findByPID(int pid) {
        return messageRepository.findAllByPID(pid);
    }

    public MessageEntity findLastMessage(int pid) {
        return messageRepository.findLastMessageByPID(pid);
    }

    /**
     * Create a MessageDTO
     * Sent with user's initials
     *
     * @param id       of the message saved in DB
     * @param userId   int value for user ID
     * @param date     String of message sending date

     * @param message  string for the message content
     * @return a {@link MessageDTO}
     */
    //* @param group_id int value for group ID

    public MessageDTO createMessageDTO(int id, String type, int userId, String date, int pid, String message) {
        colors.putIfAbsent(userId, getRandomColor());
        String str = userService.findUsernameById(userId);
        String messageToReturn = null;
        String initials = str;
        String sender = str;
        if (type.equals(MessageTypeEnum.TEXT.toString())) {
            messageToReturn = message;
        }
        return new MessageDTO(id, type, messageToReturn, userId, pid, null, sender, date, initials, colors.get(userId));
    }

    @Transactional
    public List<Integer> createNotificationList(int userId, int pid) {
        
        List<Integer> toSend = new ArrayList<>();
        ProjectEntity optionalGroupEntity = projecetService.getProjectOne(pid);
        if (optionalGroupEntity!=null) {
//            groupEntity.getUserEntities().stream().filter(user -> user.getId() != userId).forEach(userEntity -> toSend.add(userEntity.getId()));
        		pms.getProjecetMembers(Integer.toString(pid), "").forEach(projectMemberEntity->toSend.add(projectMemberEntity.getId()));;
//            groupEntity.getUserEntities().forEach(userEntity -> toSend.add(userEntity.getId()));
        }
        return toSend;
    }

    public NotificationDTO createNotificationDTO(MessageEntity msg) {
        NotificationDTO notificationDTO = new NotificationDTO();
        
        notificationDTO.setGroupId(msg.getProjectMemberFk());
        
        if (msg.getType().equals(MessageTypeEnum.TEXT.toString())) {
            notificationDTO.setMessage(msg.getMessage());
        }
        notificationDTO.setFromUserId(msg.getProjectFk());
        
        notificationDTO.setLastMessageDate("");
        
        notificationDTO.setSenderName(userService.findUsernameById(msg.getProjectMemberFk()));
        
        return notificationDTO;
    }

}
