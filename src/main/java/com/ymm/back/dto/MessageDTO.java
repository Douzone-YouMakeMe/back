package com.ymm.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private int id;

    private String type;

    private String message;

    private int userId;

    private int pid;

    private String groupUrl;

    private String sender;

    private String time;

    private String initials;

    private String color;
}
