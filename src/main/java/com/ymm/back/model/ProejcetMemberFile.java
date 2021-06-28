package com.ymm.back.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter@Setter@ToString
public class ProejcetMemberFile {
	private String name;
	private String userName;
	private String appliedPosition;
	private String comments;
	private String portfolioUrl;
	private MultipartFile portfolioFile;
	private int uid;
	private int pid;
	private String websocket;
	
	
}
