package com.example.hibernateTest.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hibernateTest.dto.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,String>{

	public Users findByusername(String s);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Users SET username= :name2 WHERE username= :name")
	void updateByusername(@Param("name")String name,@Param("name2")String name2);
}
