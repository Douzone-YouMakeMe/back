package com.ymm.back.repository;

import java.util.List;

import com.ymm.back.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, Integer> {
	
	@Query(value = "SELECT * FROM project_member WHERE project_fk=:pid OR user_fk=:uid",nativeQuery = true)
	List<ProjectMemberEntity> getProjecetMeberByUidORPid(@Param(value="pid") String pid,@Param(value="uid") String uid);
}
