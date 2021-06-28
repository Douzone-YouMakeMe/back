package com.ymm.back.service;

import java.util.List;

import com.ymm.back.entity.ProjectMemberEntity;
import com.ymm.back.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectMemberService {
	
	@Autowired
	ProjectMemberRepository pmr;
	
	public ProjectMemberEntity appliedProject(ProjectMemberEntity pme){
		return pmr.saveAndFlush(pme);
	}
	
	public List<ProjectMemberEntity> getProjecetMembers(String pid, String uid){
		return pmr.getProjecetMeberByUidORPid(pid,uid);
	}
}
