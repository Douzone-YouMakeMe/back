package com.ymm.back.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter@Setter@ToString
public class ProjectFile {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private String contents;
	
	private MultipartFile thumbnail;
	
	private String finishedAt;
	
	private int userId;
	
	private String authority;
	
	
}
