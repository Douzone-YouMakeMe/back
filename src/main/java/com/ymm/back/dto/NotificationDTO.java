package com.ymm.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class NotificationDTO {
    private int fromUserId;

    private String senderName;

    private String message;

    private String lastMessageDate;

    private String groupUrl;

    private int groupId;
}
