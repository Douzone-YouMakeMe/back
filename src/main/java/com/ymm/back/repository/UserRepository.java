package com.ymm.back.repository;

import com.ymm.back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	@Query(value = "SELECT * FROM user WHERE u_name = :name OR email =:mail" ,nativeQuery=true)
	UserEntity getUserByNameOrEMail(@Param(value = "name") String name, @Param(value = "mail") String mail);

	@Query(value = "SELECT u.u_name FROM user u WHERE u.wstoken = :token", nativeQuery = true)
	String getUsernameWithWsToken(@Param(value = "token") String token);

	@Query(value = "SELECT u.u_name FROM user u WHERE u.id = :userId", nativeQuery = true)
	String getUsernameByUserId(@Param(value = "userId") int id);
}
