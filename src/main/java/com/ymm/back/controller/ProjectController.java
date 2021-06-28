package com.ymm.back.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import com.ymm.back.entity.ProjectEntity;
import com.ymm.back.model.ProjectFile;
import com.ymm.back.service.ProjectService;
import com.ymm.back.utils.CFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	
	
	@PostMapping(path="/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createProject(@ModelAttribute ProjectFile pf){
		ProjectEntity pro=new ProjectEntity();
		pro.setViewCount(0);
		pro.setName(pf.getName());
		pro.setFinishedAt(pf.getFinishedAt());
		pro.setDescription(pf.getDescription());
		pro.setContents(pf.getContents());
		pro.setUserFK(pf.getUserId());
		pro.setAuthority(pf.getAuthority());
		pro.setThumbnail(CFileUtil.uploadImage(pf.getThumbnail()));
		
		try {
			proService.createProject(pro);
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
		
		return ResponseEntity.status(200).body("good");
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getProject(){
		List<ProjectEntity> pro=proService.getProject();
		
		
		return ResponseEntity.status(200).body(pro);
	}
	
	//projecet id로 받아오기
	@GetMapping("/{id}")
	public ResponseEntity<?> getPorjectOne(@PathVariable(value="id") int id){
		ProjectEntity pro=proService.getProjectOne(id);
		return ResponseEntity.status(200).body(pro);
	}
	
}
