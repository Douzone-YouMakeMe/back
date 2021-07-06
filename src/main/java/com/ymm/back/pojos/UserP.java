/*
 * This file is generated by jOOQ.
 */
package com.ymm.back.pojos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymm.back.domain.tables.pojos.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserP implements Serializable {

    private static final long serialVersionUID = -2130805960;

    private Integer       id;
    private String        name;
    private String        email;
    private String        password;
    private String        tel;
    private String        profile;
    private String        jobTitle;
    private String        role;
    private String        wstoken;
    private String        jwt;
    private Integer       roleId;
    private Boolean       credentialsNonExpired;
    private Boolean       accountNonLocked;
    private Boolean       enabled;
    private Boolean       accountNonExpired;
    private String        color;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public UserP() {}

    public UserP(UserP value) {
        this.id = value.id;
        this.name = value.name;
        this.email = value.email;
        this.password = value.password;
        this.tel = value.tel;
        this.profile = value.profile;
        this.jobTitle = value.jobTitle;
        this.role = value.role;
        this.wstoken = value.wstoken;
        this.jwt = value.jwt;
        this.roleId = value.roleId;
        this.credentialsNonExpired = value.credentialsNonExpired;
        this.accountNonLocked = value.accountNonLocked;
        this.enabled = value.enabled;
        this.accountNonExpired = value.accountNonExpired;
        this.color = value.color;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public UserP(
            Integer       id,
            String        name,
            String        email,
            String        password,
            String        tel,
            String        profile,
            String        jobTitle,
            String        role,
            String        wstoken,
            String        jwt,
            Integer       roleId,
            Boolean       credentialsNonExpired,
            Boolean       accountNonLocked,
            Boolean       enabled,
            Boolean       accountNonExpired,
            String        color,
            LocalDateTime createTime,
            LocalDateTime updateTime
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.profile = profile;
        this.jobTitle = jobTitle;
        this.role = role;
        this.wstoken = wstoken;
        this.jwt = jwt;
        this.roleId = roleId;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.color = color;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWstoken() {
        return this.wstoken;
    }

    public void setWstoken(String wstoken) {
        this.wstoken = wstoken;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
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
        final UserP other = (UserP) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        }
        else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (tel == null) {
            if (other.tel != null)
                return false;
        }
        else if (!tel.equals(other.tel))
            return false;
        if (profile == null) {
            if (other.profile != null)
                return false;
        }
        else if (!profile.equals(other.profile))
            return false;
        if (jobTitle == null) {
            if (other.jobTitle != null)
                return false;
        }
        else if (!jobTitle.equals(other.jobTitle))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        }
        else if (!role.equals(other.role))
            return false;
        if (wstoken == null) {
            if (other.wstoken != null)
                return false;
        }
        else if (!wstoken.equals(other.wstoken))
            return false;
        if (jwt == null) {
            if (other.jwt != null)
                return false;
        }
        else if (!jwt.equals(other.jwt))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        }
        else if (!roleId.equals(other.roleId))
            return false;
        if (credentialsNonExpired == null) {
            if (other.credentialsNonExpired != null)
                return false;
        }
        else if (!credentialsNonExpired.equals(other.credentialsNonExpired))
            return false;
        if (accountNonLocked == null) {
            if (other.accountNonLocked != null)
                return false;
        }
        else if (!accountNonLocked.equals(other.accountNonLocked))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        }
        else if (!enabled.equals(other.enabled))
            return false;
        if (accountNonExpired == null) {
            if (other.accountNonExpired != null)
                return false;
        }
        else if (!accountNonExpired.equals(other.accountNonExpired))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        }
        else if (!color.equals(other.color))
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
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.tel == null) ? 0 : this.tel.hashCode());
        result = prime * result + ((this.profile == null) ? 0 : this.profile.hashCode());
        result = prime * result + ((this.jobTitle == null) ? 0 : this.jobTitle.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = prime * result + ((this.wstoken == null) ? 0 : this.wstoken.hashCode());
        result = prime * result + ((this.jwt == null) ? 0 : this.jwt.hashCode());
        result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
        result = prime * result + ((this.credentialsNonExpired == null) ? 0 : this.credentialsNonExpired.hashCode());
        result = prime * result + ((this.accountNonLocked == null) ? 0 : this.accountNonLocked.hashCode());
        result = prime * result + ((this.enabled == null) ? 0 : this.enabled.hashCode());
        result = prime * result + ((this.accountNonExpired == null) ? 0 : this.accountNonExpired.hashCode());
        result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.updateTime == null) ? 0 : this.updateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserP (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(email);
        sb.append(", ").append(password);
        sb.append(", ").append(tel);
        sb.append(", ").append(profile);
        sb.append(", ").append(jobTitle);
        sb.append(", ").append(role);
        sb.append(", ").append(wstoken);
        sb.append(", ").append(jwt);
        sb.append(", ").append(roleId);
        sb.append(", ").append(credentialsNonExpired);
        sb.append(", ").append(accountNonLocked);
        sb.append(", ").append(enabled);
        sb.append(", ").append(accountNonExpired);
        sb.append(", ").append(color);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}
