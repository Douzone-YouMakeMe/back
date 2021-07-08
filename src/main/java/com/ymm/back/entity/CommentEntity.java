package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "douzone", catalog = "")
public class CommentEntity {
    private int id;
    private String comments;
    private Timestamp createTime;
    private Timestamp updateTime;
    private ProjectMemberEntity projectMemberByMemberId;
    private WorkEntity workByWorkId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = 255)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        CommentEntity that = (CommentEntity) o;
        return id == that.id && Objects.equals(comments, that.comments) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comments, createTime, updateTime);
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    public ProjectMemberEntity getProjectMemberByMemberId() {
        return projectMemberByMemberId;
    }

    public void setProjectMemberByMemberId(ProjectMemberEntity projectMemberByMemberId) {
        this.projectMemberByMemberId = projectMemberByMemberId;
    }

    @ManyToOne
    @JoinColumn(name = "work_id", referencedColumnName = "id", nullable = false)
    public WorkEntity getWorkByWorkId() {
        return workByWorkId;
    }

    public void setWorkByWorkId(WorkEntity workByWorkId) {
        this.workByWorkId = workByWorkId;
    }
}
