package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "project_member", schema = "douzone", catalog = "")
public class ProjectMemberEntity {
    private int id;
    private String status;
    private String appliedPosition;
    private String comments;
    private String portfolioFile;
    private String portfolioUrl;
    private String description;
    private String auth;
    private Timestamp appliedTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Collection<AgendaEntity> agendaById;
    private Collection<CommentEntity> commentsById;
    private Collection<MessageEntity> messagesById;
    private UserEntity userByUserId;
    private ProjectEntity projectByProjectId;
    private Collection<VotingEntity> votingsById;
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
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "applied_position", nullable = true, length = 45)
    public String getAppliedPosition() {
        return appliedPosition;
    }

    public void setAppliedPosition(String appliedPosition) {
        this.appliedPosition = appliedPosition;
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
    @Column(name = "portfolio_file", nullable = true, length = 200)
    public String getPortfolioFile() {
        return portfolioFile;
    }

    public void setPortfolioFile(String portfolioFile) {
        this.portfolioFile = portfolioFile;
    }

    @Basic
    @Column(name = "portfolio_url", nullable = true, length = 255)
    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "auth", nullable = true, length = 30)
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Basic
    @Column(name = "applied_time", nullable = true)
    public Timestamp getAppliedTime() {
        return appliedTime;
    }

    public void setAppliedTime(Timestamp appliedTime) {
        this.appliedTime = appliedTime;
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
        ProjectMemberEntity that = (ProjectMemberEntity) o;
        return id == that.id && Objects.equals(status, that.status) && Objects.equals(appliedPosition, that.appliedPosition) && Objects.equals(comments, that.comments) && Objects.equals(portfolioFile, that.portfolioFile) && Objects.equals(portfolioUrl, that.portfolioUrl) && Objects.equals(description, that.description) && Objects.equals(auth, that.auth) && Objects.equals(appliedTime, that.appliedTime) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, appliedPosition, comments, portfolioFile, portfolioUrl, description, auth, appliedTime, createTime, updateTime);
    }

    @OneToMany(mappedBy = "projectMemberByMemberId")
    public Collection<AgendaEntity> getAgendaById() {
        return agendaById;
    }

    public void setAgendaById(Collection<AgendaEntity> agendaById) {
        this.agendaById = agendaById;
    }

    @OneToMany(mappedBy = "projectMemberByMemberId")
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "projectMemberByMemberId")
    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    public ProjectEntity getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(ProjectEntity projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @OneToMany(mappedBy = "projectMemberByMemberId")
    public Collection<VotingEntity> getVotingsById() {
        return votingsById;
    }

    public void setVotingsById(Collection<VotingEntity> votingsById) {
        this.votingsById = votingsById;
    }

    @OneToMany(mappedBy = "projectMemberByMemberId")
    public Collection<WorkEntity> getWorksById() {
        return worksById;
    }

    public void setWorksById(Collection<WorkEntity> worksById) {
        this.worksById = worksById;
    }
}
