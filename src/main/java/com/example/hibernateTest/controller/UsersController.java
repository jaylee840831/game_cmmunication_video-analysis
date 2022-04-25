package com.example.hibernateTest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernateTest.dto.UserInfo;
import com.example.hibernateTest.service.NewUserDetailService;
import com.example.hibernateTest.storage.UserStorage;

/*
 * 個人聊天訊息controller
 * */

@RestController
@CrossOrigin
public class UsersController {

	@Autowired
	NewUserDetailService newUserDetailService;
	
	/*------------------------個人聊天室-------------------------------------*/
	
	//註冊傳訊息對象
	@GetMapping("/registration/{username}")
	public ResponseEntity<Void>register(@PathVariable String username){
		UserStorage.getInstance().clearUser();
		System.out.println("handling register user request: "+username);
		try {
			UserStorage.getInstance().setUser(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	//取得全部用戶傳訊息對象
	@GetMapping("/fetchAllUser")
	public Map<String, byte[]>fetchAllUser(Authentication authentication){
		Map<String, byte[]> map= new TreeMap<String, byte[]>();
		
		String username="";
//		UserStorage.getInstance().clearUser();
		
		if(authentication!=null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			//取得user的權限種類
//			System.out.println("User has authorities: " + userDetails.getAuthorities());
			//取得user的名稱
			username=userDetails.getUsername();
		}
		
		List<UserInfo>userInfos=newUserDetailService.findAllUserInfo();
		try {
			for(UserInfo u:userInfos) {
				if(!username.equals(u.getUsername())) {				
//					UserStorage.getInstance().setUser(u.getUsername());
//					System.out.println("handling register user request: "+u.getUsername());
					map.put(u.getUsername(), u.getImage());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return map;
	}
}
