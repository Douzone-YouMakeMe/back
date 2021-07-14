package com.ymm.back.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMemberDTO {
    //프로젝트
//    private Integer       projectId;
//    private Integer       ownerUserId;
//    private String        name;
//
//    private String        contents;
//    private Integer       viewCount;
//    private String        thumbnail;
//    private String        projectDescription;
//    private String        authority;
//    private Integer       total;
//    private String        url;
//    private String        type;
//    private Integer       to; //추가된 to
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime startedTime;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime projectCreateTime;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime projectUpdateTime;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime finishedTime;
//
//    //프로젝트멤버
//    private Integer       id;
//    private Integer       userId;
//    private String        status;
//    private String        appliedPosition;
//    private String        comments;
//    private String        portfolioFile;
//    private String        portfolioUrl;
//    private String        description;
//    private String        auth;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime appliedTime;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createTime;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime updateTime;


    //프로젝트
    @Column(table = "project", name = "projectId")
    private Integer       projectId;
    @Column(table = "project", name = "ownerUserId")
    private Integer       ownerUserId;
    @Column(table = "project", name = "name")
    private String        name;
    @Column(table = "project", name = "contents")
    private String        contents;
    @Column(table = "project", name = "viewCount")
    private Integer       viewCount;
    @Column(table = "project", name = "thumbnail")
    private String        thumbnail;
    @Column(table = "project", name = "description")
    private String        projectDescription;
    @Column(table = "project", name = "authority")
    private String        authority;
    @Column(table = "project", name = "total")
    private Integer       total;
    @Column(table = "project", name = "to")
    private Integer       to;
    @Column(table = "project", name = "url")
    private String        url;
    @Column(table = "project", name = "type")
    private String        type;
    @Column(table = "project", name = "started_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedTime;
    @Column(table = "project", name = "create_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectCreateTime;
    @Column(table = "project", name = "update_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectUpdateTime;
    @Column(table = "project", name = "finished_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishedTime;

    //프로젝트멤버
    @Column(table = "project_member", name = "ID")
    private Integer       id;
    @Column(table = "project_member", name = "user_id")
    private Integer       userId;
    @Column(table = "project_member", name = "status")
    private String        status;
    @Column(table = "project_member", name = "applied_position")
    private String        appliedPosition;
    @Column(table = "project_member", name = "comments")
    private String        comments;
    @Column(table = "project_member", name = "portfolio_file")
    private String        portfolioFile;
    @Column(table = "project_member", name = "portfolio_url")
    private String        portfolioUrl;
    @Column(table = "project_member", name = "description")
    private String        description;
    @Column(table = "project_member", name = "auth")
    private String        auth;
    @Column(table = "project_member", name = "applied_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appliedTime;
    @Column(table = "project_member", name = "create_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @Column(table = "project_member", name = "update_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;




}
