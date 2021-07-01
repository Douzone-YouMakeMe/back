/*
 * This file is generated by jOOQ.
 */
package com.ymm.back.pojos;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkP implements Serializable {

    private static final long serialVersionUID = 1340040005;

    private Integer       id;
    private Integer       projectId;
    private Integer       memberId;
    private String        name;
    private String        manager;
    private String        status;
    private String        color;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public WorkP() {}

    public WorkP(WorkP value) {
        this.id = value.id;
        this.projectId = value.projectId;
        this.memberId = value.memberId;
        this.name = value.name;
        this.manager = value.manager;
        this.status = value.status;
        this.color = value.color;
        this.startedAt = value.startedAt;
        this.finishedAt = value.finishedAt;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public WorkP(
        Integer       id,
        Integer       projectId,
        Integer       memberId,
        String        name,
        String        manager,
        String        status,
        String        color,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        LocalDateTime createTime,
        LocalDateTime updateTime
    ) {
        this.id = id;
        this.projectId = projectId;
        this.memberId = memberId;
        this.name = name;
        this.manager = manager;
        this.status = status;
        this.color = color;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return this.manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getStartedAt() {
        return this.startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
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
        final WorkP other = (WorkP) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        }
        else if (!projectId.equals(other.projectId))
            return false;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        }
        else if (!memberId.equals(other.memberId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (manager == null) {
            if (other.manager != null)
                return false;
        }
        else if (!manager.equals(other.manager))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        }
        else if (!color.equals(other.color))
            return false;
        if (startedAt == null) {
            if (other.startedAt != null)
                return false;
        }
        else if (!startedAt.equals(other.startedAt))
            return false;
        if (finishedAt == null) {
            if (other.finishedAt != null)
                return false;
        }
        else if (!finishedAt.equals(other.finishedAt))
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
        result = prime * result + ((this.projectId == null) ? 0 : this.projectId.hashCode());
        result = prime * result + ((this.memberId == null) ? 0 : this.memberId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.manager == null) ? 0 : this.manager.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
        result = prime * result + ((this.startedAt == null) ? 0 : this.startedAt.hashCode());
        result = prime * result + ((this.finishedAt == null) ? 0 : this.finishedAt.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.updateTime == null) ? 0 : this.updateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Work (");

        sb.append(id);
        sb.append(", ").append(projectId);
        sb.append(", ").append(memberId);
        sb.append(", ").append(name);
        sb.append(", ").append(manager);
        sb.append(", ").append(status);
        sb.append(", ").append(color);
        sb.append(", ").append(startedAt);
        sb.append(", ").append(finishedAt);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}
