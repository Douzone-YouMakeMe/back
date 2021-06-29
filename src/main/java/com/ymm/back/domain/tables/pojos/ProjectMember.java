/*
 * This file is generated by jOOQ.
 */
package com.ymm.back.domain.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProjectMember implements Serializable {

    private static final long serialVersionUID = 241489962;

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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public ProjectMember() {}

    public ProjectMember(ProjectMember value) {
        this.id = value.id;
        this.userId = value.userId;
        this.projectId = value.projectId;
        this.name = value.name;
        this.status = value.status;
        this.appliedPosition = value.appliedPosition;
        this.comments = value.comments;
        this.portfolioFile = value.portfolioFile;
        this.portfolioUrl = value.portfolioUrl;
        this.description = value.description;
        this.auth = value.auth;
        this.roleId = value.roleId;
        this.websocket = value.websocket;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public ProjectMember(
        Integer       id,
        Integer       userId,
        Integer       projectId,
        String        name,
        String        status,
        String        appliedPosition,
        String        comments,
        String        portfolioFile,
        String        portfolioUrl,
        String        description,
        String        auth,
        Integer       roleId,
        String        websocket,
        LocalDateTime createTime,
        LocalDateTime updateTime
    ) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.name = name;
        this.status = status;
        this.appliedPosition = appliedPosition;
        this.comments = comments;
        this.portfolioFile = portfolioFile;
        this.portfolioUrl = portfolioUrl;
        this.description = description;
        this.auth = auth;
        this.roleId = roleId;
        this.websocket = websocket;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppliedPosition() {
        return this.appliedPosition;
    }

    public void setAppliedPosition(String appliedPosition) {
        this.appliedPosition = appliedPosition;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPortfolioFile() {
        return this.portfolioFile;
    }

    public void setPortfolioFile(String portfolioFile) {
        this.portfolioFile = portfolioFile;
    }

    public String getPortfolioUrl() {
        return this.portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return this.auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getWebsocket() {
        return this.websocket;
    }

    public void setWebsocket(String websocket) {
        this.websocket = websocket;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProjectMember other = (ProjectMember) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!userId.equals(other.userId))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        }
        else if (!projectId.equals(other.projectId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (appliedPosition == null) {
            if (other.appliedPosition != null)
                return false;
        }
        else if (!appliedPosition.equals(other.appliedPosition))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        }
        else if (!comments.equals(other.comments))
            return false;
        if (portfolioFile == null) {
            if (other.portfolioFile != null)
                return false;
        }
        else if (!portfolioFile.equals(other.portfolioFile))
            return false;
        if (portfolioUrl == null) {
            if (other.portfolioUrl != null)
                return false;
        }
        else if (!portfolioUrl.equals(other.portfolioUrl))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (auth == null) {
            if (other.auth != null)
                return false;
        }
        else if (!auth.equals(other.auth))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        }
        else if (!roleId.equals(other.roleId))
            return false;
        if (websocket == null) {
            if (other.websocket != null)
                return false;
        }
        else if (!websocket.equals(other.websocket))
            return false;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        }
        else if (!createTime.equals(other.createTime))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        }
        else if (!updateTime.equals(other.updateTime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.projectId == null) ? 0 : this.projectId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.appliedPosition == null) ? 0 : this.appliedPosition.hashCode());
        result = prime * result + ((this.comments == null) ? 0 : this.comments.hashCode());
        result = prime * result + ((this.portfolioFile == null) ? 0 : this.portfolioFile.hashCode());
        result = prime * result + ((this.portfolioUrl == null) ? 0 : this.portfolioUrl.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.auth == null) ? 0 : this.auth.hashCode());
        result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
        result = prime * result + ((this.websocket == null) ? 0 : this.websocket.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.updateTime == null) ? 0 : this.updateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ProjectMember (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(projectId);
        sb.append(", ").append(name);
        sb.append(", ").append(status);
        sb.append(", ").append(appliedPosition);
        sb.append(", ").append(comments);
        sb.append(", ").append(portfolioFile);
        sb.append(", ").append(portfolioUrl);
        sb.append(", ").append(description);
        sb.append(", ").append(auth);
        sb.append(", ").append(roleId);
        sb.append(", ").append(websocket);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}
