package com.ymm.back.repository;

import java.util.List;

import com.ymm.back.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
	@Query(value="SELECT * FROM message WHERE project_fk=:pid" ,nativeQuery = true)
	List<MessageEntity> findAllByPID(@Param(value="pid") int pid);
	@Query(value="SELECT * FROM message WHERE project_fk=:pid ORDER BY id DESC LIMIT 1",nativeQuery = true)
	MessageEntity findLastMessageByPID(@Param(value="pid")int pid);
}
