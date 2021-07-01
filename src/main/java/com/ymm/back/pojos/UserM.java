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
public class UserM {

    private Integer       id;
    private String        name;
    private String        email;
    private String        password;
    private String        tel;
    private String        profile;
    private String        jobTitle;
    private String        role;
    private String        wstoken;
    private String        jwt;
    private Integer       roleId;
    private Boolean       credentialsNonExpired;
    private Boolean       accountNonLocked;
    private Boolean       enabled;
    private Boolean       accountNonExpired;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
