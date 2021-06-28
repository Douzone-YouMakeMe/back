package com.ymm.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="project_member")
@Getter@Setter@ToString
public class ProjectMemberEntity {
	@Id
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="user_name")
	private String userName;
	@Column(name="applied_position")
	private String appliedPosition;
	@Column (name="comments")
	private String comments;
	@Column (name="portfolio_file")
	private String portfolioFile;
	@Column(name="portfolio_url")
	private String portfolioURL;
	@Column(name="auth")
	private String auth;
	@Column(name="user_fk")
	private int uid;
	@Column(name="project_fk")
	private int pid;
	@Column(name="websocket")
	private String webSocket;
	
}
