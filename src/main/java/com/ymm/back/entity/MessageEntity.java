package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "douzone", catalog = "")
public class MessageEntity {
    private int id;
    private String message;
    private String type;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Collection<FileStorageEntity> fileStoragesById;
    private ProjectMemberEntity projectMemberByMemberId;
    private ProjectEntity projectByProjectId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 40)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        MessageEntity that = (MessageEntity) o;
        return id == that.id && Objects.equals(message, that.message) && Objects.equals(type, that.type) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, type, createTime, updateTime);
    }

    @OneToMany(mappedBy = "messageByMessageId")
    public Collection<FileStorageEntity> getFileStoragesById() {
        return fileStoragesById;
    }

    public void setFileStoragesById(Collection<FileStorageEntity> fileStoragesById) {
        this.fileStoragesById = fileStoragesById;
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    public ProjectMemberEntity getProjectMemberByMemberId() {
        return projectMemberByMemberId;
    }

    public void setProjectMemberByMemberId(ProjectMemberEntity projectMemberByMemberId) {
        this.projectMemberByMemberId = projectMemberByMemberId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectEntity getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(ProjectEntity projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }
}
