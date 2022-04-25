package com.example.hibernateTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernateTest.dto.UserInfo;
import com.example.hibernateTest.model.MessageModel;
import com.example.hibernateTest.service.NewUserDetailService;
import com.example.hibernateTest.storage.UserStorage;

@RestController
public class MessageController {
	
	@Autowired
	NewUserDetailService newUserDetailService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to,MessageModel message) {
		System.out.println("handling send message: "+message+" to: "+to);
		simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);
//		boolean isExits=UserStorage.getInstance().getUsers().contains(to);
//		if(isExits) {
//			simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);
//		}
	}
}
