package com.example.hibernateTest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.hibernateTest.details.NewUserDetail;
import com.example.hibernateTest.dto.UserInfo;
import com.example.hibernateTest.dto.Users;
import com.example.hibernateTest.service.NewUserDetailService;


@Controller
public class MainController {
	
	@Autowired
	NewUserDetailService newUserDetailService;
	
	@GetMapping("/index")
	public String index(Model model) {
			
			return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
			
			return "login";
	}
	
	@GetMapping("/login.html")
	public String login2(Model model) {
			
			return "login";
	}
	
	//user資料頁面
	@GetMapping("/person")
	public String person(Model model) {
			
			return "person";
	}
	
	//更新user資料
	@PostMapping("/person")
	public String postuserInfo(Model model,@ModelAttribute UserInfo userInfo,Authentication authentication,@AuthenticationPrincipal NewUserDetail newUserDetail){
		if(!userInfo.getUsername().equals("")) {
			int sex=userInfo.getSex()!=null?userInfo.getSex():0;
			int height=userInfo.getHeight()!=null?userInfo.getHeight():0;
			int weight=userInfo.getWeight()!=null?userInfo.getWeight():0;
			int position=userInfo.getPosition()!=null?userInfo.getPosition():0;
			int image=userInfo.getImage()!=null?userInfo.getImage().length:0;
			
			System.out.println("姓名 "+userInfo.getUsername()+" 性別 "+sex+" 身高 "+height
					+" 體重 "+weight+" 位置 "+position+" image size "+image);
			
			String username="";
			Users users=null;
			
			// 取得user的資訊
			if(authentication!=null) {
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				//取得user的權限種類
	//			System.out.println("User has authorities: " + userDetails.getAuthorities());
				//取得user的名稱
				username=userDetails.getUsername();
			}
			users=newUserDetailService.findUser(username);
	
			
			//更新user資料
			newUserDetailService.updateUserInfo(users.getUsername(),userInfo);
			//更新球局發起人名稱(有發起球局的user才需更新)
			newUserDetailService.updateGameInfo(users.getUsername(),userInfo.getUsername());
			//更新user帳戶名稱
			newUserDetailService.updateUser(users.getUsername(),userInfo.getUsername());
			newUserDetail.setUserName(userInfo.getUsername());
		}
		
		return "person";
	}
	
	//聊天室
	@GetMapping("/chat")
	public String chat(Model model) {
		return "chat";
	}
	
	//球局
	@GetMapping("/ballgame")
	public String ballgame(Model model) {
		
		return "ballgame";
	}
}

