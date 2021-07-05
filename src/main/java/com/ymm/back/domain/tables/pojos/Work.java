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
public class Work implements Serializable {

    private static final long serialVersionUID = -163286392;

    private Integer       id;
    private Integer       projectId;
    private Integer       memberId;
    private String        name;
    private String        status;
    private String        color;
    private String        description;
    private String        hashtag;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Work() {}

    public Work(Work value) {
        this.id = value.id;
        this.projectId = value.projectId;
        this.memberId = value.memberId;
        this.name = value.name;
        this.status = value.status;
        this.color = value.color;
        this.description = value.description;
        this.hashtag = value.hashtag;
        this.startedAt = value.startedAt;
        this.finishedAt = value.finishedAt;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public Work(
        Integer       id,
        Integer       projectId,
        Integer       memberId,
        String        name,
        String        status,
        String        color,
        String        description,
        String        hashtag,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        LocalDateTime createTime,
        LocalDateTime updateTime
    ) {
        this.id = id;
        this.projectId = projectId;
        this.memberId = memberId;
        this.name = name;
        this.status = status;
        this.color = color;
        this.description = description;
        this.hashtag = hashtag;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHashtag() {
        return this.hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
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
        final Work other = (Work) obj;
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
        if (description == null) {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (hashtag == null) {
            if (other.hashtag != null)
                return false;
        }
        else if (!hashtag.equals(other.hashtag))
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
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.hashtag == null) ? 0 : this.hashtag.hashCode());
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
        sb.append(", ").append(status);
        sb.append(", ").append(color);
        sb.append(", ").append(description);
        sb.append(", ").append(hashtag);
        sb.append(", ").append(startedAt);
        sb.append(", ").append(finishedAt);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}
