package com.ymm.back.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "douzone", catalog = "")
public class UserEntity {
    private int id;
    private String name;
    private String email;
    private String password;
    private String tel;
    private String profile;
    private String jobTitle;
    private String role;
    private String wstoken;
    private String jwt;
    private Integer roleId;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Collection<ProjectEntity> projectsById;
    private Collection<ProjectMemberEntity> projectMembersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 50)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "profile", nullable = true, length = 255)
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Basic
    @Column(name = "job_title", nullable = true, length = 45)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "ROLE", nullable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "wstoken", nullable = true, length = 255)
    public String getWstoken() {
        return wstoken;
    }

    public void setWstoken(String wstoken) {
        this.wstoken = wstoken;
    }

    @Basic
    @Column(name = "jwt", nullable = true, length = 255)
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Basic
    @Column(name = "role_id", nullable = true)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "credentials_non_expired", nullable = true)
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Basic
    @Column(name = "account_non_locked", nullable = true)
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "enabled", nullable = true)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "account_non_expired", nullable = true)
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
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
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(tel, that.tel) && Objects.equals(profile, that.profile) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(role, that.role) && Objects.equals(wstoken, that.wstoken) && Objects.equals(jwt, that.jwt) && Objects.equals(roleId, that.roleId) && Objects.equals(credentialsNonExpired, that.credentialsNonExpired) && Objects.equals(accountNonLocked, that.accountNonLocked) && Objects.equals(enabled, that.enabled) && Objects.equals(accountNonExpired, that.accountNonExpired) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, tel, profile, jobTitle, role, wstoken, jwt, roleId, credentialsNonExpired, accountNonLocked, enabled, accountNonExpired, createTime, updateTime);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ProjectEntity> getProjectsById() {
        return projectsById;
    }

    public void setProjectsById(Collection<ProjectEntity> projectsById) {
        this.projectsById = projectsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ProjectMemberEntity> getProjectMembersById() {
        return projectMembersById;
    }

    public void setProjectMembersById(Collection<ProjectMemberEntity> projectMembersById) {
        this.projectMembersById = projectMembersById;
    }
}
