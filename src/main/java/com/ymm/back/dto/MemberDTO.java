package com.ymm.back.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private String        name;
    private String        email;
    private String        password;
    private String        tel;
    public  String        profile;
    private String        jobTitle;
    private String        role;
    private String        wstoken;
    private String        jwt;
    private Integer       roleId;
    private Boolean       credentialsNonExpired;
    private Boolean       accountNonLocked;
    private Boolean       enabled;
    private Boolean       accountNonExpired;
    private String        color;

    private Integer       id;
    private Integer       userId;
    private Integer       projectId;
    private String        status;
    private String        appliedPosition;
    private String        comments;
    private String        portfolioFile;
    private String        portfolioUrl;
    private String        description;
    private String        auth;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appliedTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;



}
