package com.ymm.back.service;

import java.util.List;

import com.ymm.back.entity.ProjectEntity;
import com.ymm.back.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	@Autowired
	ProjectRepository projectRepo;
	
	public void createProject(ProjectEntity entity) {
		ProjectEntity res =projectRepo.save(entity);
		projectRepo.flush();
		System.out.println(res.getId());
		
		
	}
	
	public List<ProjectEntity> getProject() {
		return projectRepo.findAll();
	}
	public ProjectEntity getProjectOne(int pid) {
		return projectRepo.findById(pid);
	}
}
