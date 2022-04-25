package com.example.hibernateTest.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hibernateTest.dto.GameInfo;

@Repository
public interface GameInfoRepository extends JpaRepository<GameInfo, String>{
	
	@Transactional
	@Modifying
	@Query("UPDATE GameInfo SET username= :name2 WHERE username= :name")
	void updateByusername(@Param("name")String name,@Param("name2")String name2);
	
//	@Transactional
//	@Modifying
//	@Query(nativeQuery = true,value="UPDATE game_info SET username=u.username FROM game_info g INNER JOIN user_info u ON g.username = u.username WHERE g.username = u.username")
//	void updateByusername();
}
