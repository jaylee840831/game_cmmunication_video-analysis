package com.example.hibernateTest.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hibernateTest.dto.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

	public UserInfo findByusername(String s);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE UserInfo SET username= :name2,sex= :sex,height= :height,weight= :weight,position= :position,image= :image WHERE username=:name")
	void updateByusername(@Param("name")String name,@Param("name2")String name2,@Param("sex")int sex,@Param("height")int height,@Param("weight")int weight,@Param("position")int position,@Param("image")byte[]image);
	
	
}
