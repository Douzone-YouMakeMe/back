package com.ymm.back.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMemberM {
    private Integer       id;
    private Integer       userId;
    private Integer       projectId;
    private String        name;
    private String        status;
    private String        appliedPosition;
    private String        comments;
    private String        portfolioFile;
    private String        portfolioUrl;
    private String        description;
    private String        auth;
    private Integer       roleId;
    private String        websocket;
    private String        color;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
