package com.mercure.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercure.entity.ProjectMemberEntity;
import com.mercure.model.ProejcetMemberFile;
import com.mercure.service.ProjectMemberService;
import com.mercure.utils.CFileUtil;

@RestController
@RequestMapping("/member")
public class ProejctMemberController {
	
	@Autowired
	ProjectMemberService pms;
	
	@PostMapping("/add")
	public ResponseEntity<?> applyProject(@ModelAttribute ProejcetMemberFile pmf) {
		ProjectMemberEntity pme = new ProjectMemberEntity();
		if (pmf.getPortfolioUrl()!=null) {
			pme.setPortfolioURL(pmf.getPortfolioUrl());
		} else if (!pmf.getPortfolioFile().isEmpty()) {
			pme.setPortfolioFile(CFileUtil.uploadFile(pmf.getPortfolioFile()));
			
		}
		else {
			return ResponseEntity.status(403).build();
		}
		pme.setAppliedPosition(pmf.getAppliedPosition());
		pme.setUserName(pmf.getUserName());
		pme.setAuth("wait");
		pme.setComments(pmf.getComments());
		pme.setName(pmf.getName());
		pme.setPid(pmf.getPid());
		pme.setUid(pmf.getUid());
		pme.setWebSocket(pmf.getWebsocket());
		pme= pms.appliedProject(pme);
		return ResponseEntity.status(200).body(pme);
	}
	@GetMapping()
	public ResponseEntity<?> getProjectMembers(@RequestParam(value="pid",required = false) String pid,@RequestParam(value="uid",required = false) String uid){
//		Optional<Integer> optionPid
		List<ProjectMemberEntity> result= pms.getProjecetMembers(pid,uid);
		return ResponseEntity.status(200).body(result);
	}

}
