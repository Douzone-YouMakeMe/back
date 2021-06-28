package com.ymm.back.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="project")
@Getter@Setter@ToString
public class ProjectEntity {
	@Id
	@Column(name = "id")   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="user_fk")
	private int userFK;
	@Column(name="contents")
	private String Contents;
	@Column(name="view_count")
	private int viewCount;
	@Column(name ="thumbnail")
	private String thumbnail;
	@Column(name="description")
	private String description;
	@Column(name="finished_at")
	private String finishedAt;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="updated_at")
	private Timestamp updatedAt;
	@Column(name="authority")
	private String authority;
	
	
	
}
