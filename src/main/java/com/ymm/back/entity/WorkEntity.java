package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "work", schema = "douzone", catalog = "")
public class WorkEntity {
    private int id;
    private String name;
    private String manager;
    private String status;
    private String color;
    private Timestamp startedAt;
    private Timestamp finishedAt;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Collection<CommentEntity> commentsById;
    private Collection<HashtagEntity> hashtagsById;
    private ProjectEntity projectByProjectId;
    private ProjectMemberEntity projectMemberByMemberId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "manager", nullable = true, length = 70)
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "color", nullable = true, length = 32)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "started_at", nullable = true)
    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    @Basic
    @Column(name = "finished_at", nullable = true)
    public Timestamp getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Timestamp finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkEntity that = (WorkEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(manager, that.manager) && Objects.equals(status, that.status) && Objects.equals(color, that.color) && Objects.equals(startedAt, that.startedAt) && Objects.equals(finishedAt, that.finishedAt) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manager, status, color, startedAt, finishedAt, createTime, updateTime);
    }

    @OneToMany(mappedBy = "workByWorkId")
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "workByWorkId")
    public Collection<HashtagEntity> getHashtagsById() {
        return hashtagsById;
    }

    public void setHashtagsById(Collection<HashtagEntity> hashtagsById) {
        this.hashtagsById = hashtagsById;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectEntity getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(ProjectEntity projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    public ProjectMemberEntity getProjectMemberByMemberId() {
        return projectMemberByMemberId;
    }

    public void setProjectMemberByMemberId(ProjectMemberEntity projectMemberByMemberId) {
        this.projectMemberByMemberId = projectMemberByMemberId;
    }
}
