package com.ymm.back.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name="message")
public class MessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="message")
    private String message;
	@Column(name="project_member_fk")
	private int projectMemberFk;
	@Column(name ="project_fk")
	private int projectFk;
	@Column(name="type")
	private String type;
	@Column(name="created_at")
	private Timestamp createdAt;
	public MessageEntity(int projecetMemberFk, int projectFk,String type, String message) {
		this.projectMemberFk=projecetMemberFk;
		this.projectFk=projectFk;
		this.type=type;
		this.message=message;
	}
	
	
	
}
