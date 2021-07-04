package com.ymm.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    private int id;

    private String type;

    private String message;

    private int projectMemberId;

    private int projectId;

    private String groupUrl;

    private String sender;

    private String time;

    private String initials;

    private String color;
}