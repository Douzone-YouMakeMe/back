package com.ymm.back.repository;

import java.util.List;

import com.ymm.back.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
	@Query(value="SELECT * FROM project WHERE user_fk=:uid" , nativeQuery=true)
	List<ProjectEntity> getProjectByUserFk(@Param(value="uid") int uid);
	
	ProjectEntity findById(int id);
	
	
}
