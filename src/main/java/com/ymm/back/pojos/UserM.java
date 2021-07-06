package com.ymm.back.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class UserM {

    private Integer       id;
    private String        name;
    private String        email;
    private String        password;
    private String        tel;
    public MultipartFile profile;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
