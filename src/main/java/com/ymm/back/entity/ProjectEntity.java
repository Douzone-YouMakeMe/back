package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "project", schema = "douzone", catalog = "")
public class ProjectEntity {
    private int id;
    private String name;
    private String contents;
    private Integer viewCount;
    private String thumbnail;
    private String description;
    private String authority;
    private Integer total;
    private String type;
    private String url;
    private Timestamp startedTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp finishedTime;
    private Collection<AgendaEntity> agendaById;
    private Collection<MessageEntity> messagesById;
    private UserEntity userByUserId;
    private Collection<ProjectMemberEntity> projectMembersById;
    private Collection<WorkEntity> worksById;

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
    @Column(name = "contents", nullable = true, length = 200)
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Basic
    @Column(name = "view_count", nullable = true)
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "thumbnail", nullable = true, length = 255)
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "authority", nullable = true, length = 30)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Basic
    @Column(name = "total", nullable = true)
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "started_time", nullable = true)
    public Timestamp getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Timestamp startedTime) {
        this.startedTime = startedTime;
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

    @Basic
    @Column(name = "finished_time", nullable = true)
    public Timestamp getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Timestamp finishedTime) {
        this.finishedTime = finishedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(contents, that.contents) && Objects.equals(viewCount, that.viewCount) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(description, that.description) && Objects.equals(authority, that.authority) && Objects.equals(total, that.total) && Objects.equals(type, that.type) && Objects.equals(url, that.url) && Objects.equals(startedTime, that.startedTime) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(finishedTime, that.finishedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents, viewCount, thumbnail, description, authority, total, type, url, startedTime, createTime, updateTime, finishedTime);
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<AgendaEntity> getAgendaById() {
        return agendaById;
    }

    public void setAgendaById(Collection<AgendaEntity> agendaById) {
        this.agendaById = agendaById;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<ProjectMemberEntity> getProjectMembersById() {
        return projectMembersById;
    }

    public void setProjectMembersById(Collection<ProjectMemberEntity> projectMembersById) {
        this.projectMembersById = projectMembersById;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<WorkEntity> getWorksById() {
        return worksById;
    }

    public void setWorksById(Collection<WorkEntity> worksById) {
        this.worksById = worksById;
    }
}
