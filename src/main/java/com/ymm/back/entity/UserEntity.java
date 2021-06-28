package com.ymm.back.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails, Serializable {

	@Id
	private int id;
	@Column(name = "u_name")
	private String name;
	@Column(name = "email")
	private String mail;
	@Column(name = "password")
	private String password;
	@Column(name = "wstoken")
	private String wstoken;
	@Column(name = "tel")
	private String tel;
	@CreatedDate
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	@LastModifiedBy
	private Timestamp updatedAt;
	@Column(name = "profile")
	private String profile;
	@Column(name = "jwt")
	private String jwt;

	@Column(name = "account_non_expired")
	private boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "role_id")
	private int role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="user_fk")
	@JsonIgnore
	private List<ProjectEntity> projectList;

}
