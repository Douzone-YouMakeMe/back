package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "voting", schema = "douzone", catalog = "")
public class VotingEntity {
    private int id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String choice;
    private AgendaEntity agendaByAgendaId;
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
    @Column(name = "choice", nullable = true, length = 45)
    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingEntity that = (VotingEntity) o;
        return id == that.id && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(choice, that.choice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, choice);
    }

    @ManyToOne
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    public AgendaEntity getAgendaByAgendaId() {
        return agendaByAgendaId;
    }

    public void setAgendaByAgendaId(AgendaEntity agendaByAgendaId) {
        this.agendaByAgendaId = agendaByAgendaId;
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
