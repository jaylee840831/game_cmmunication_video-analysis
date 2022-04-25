package com.example.hibernateTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernateTest.dto.GameInfo;
import com.example.hibernateTest.repository.GameInfoRepository;

@Service
public class BallGameService {
	
	@Autowired
	GameInfoRepository gameInfoRepository;

	public List<GameInfo> findAllGameInfo(){
		return gameInfoRepository.findAll();
	}
	
	public GameInfo findGameInfo(String code) {
		Optional<GameInfo> gameInfo=gameInfoRepository.findById(code);
		if(gameInfo.isPresent()) {
			return gameInfo.get();
		}
		return null;
	}
}
