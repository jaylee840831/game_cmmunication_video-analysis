package com.example.hibernateTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hibernateTest.details.NewUserDetail;
import com.example.hibernateTest.dto.GameInfo;
import com.example.hibernateTest.dto.UserInfo;
import com.example.hibernateTest.dto.Users;
import com.example.hibernateTest.repository.GameInfoRepository;
import com.example.hibernateTest.repository.UserInfoRepository;
import com.example.hibernateTest.repository.UsersRepository;

@Service("newuserDetailService")
public class NewUserDetailService implements UserDetailsService{
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	GameInfoRepository gameInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
		
		Users users=findUser(s);
		System.out.println(users);
		if(users==null) {
			throw new UsernameNotFoundException("用戶不存在");
		}
		
		users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));

		return new NewUserDetail(users);
	}
	
	//user的帳密
	public Users findUser(String s) {
		return usersRepository.findByusername(s);
	}
	
	public void updateUser(String s,String s2) {
		usersRepository.updateByusername(s,s2);
	}
	
	//user的個人資料
	public UserInfo findUserInfo(String s) {
		return userInfoRepository.findByusername(s);
	}

	public List<UserInfo> findAllUserInfo() {
		return userInfoRepository.findAll();
	}
	
	public void updateUserInfo(String name,UserInfo users) {
		if(users.getUsername()==null) {
			users.setUsername("");
		}
		if(users.getSex()==null) {
			users.setSex(0);;
		}
		if(users.getHeight()==null) {
			users.setHeight(0);;
		}
		if(users.getWeight()==null) {
			users.setWeight(0);;
		}
		if(users.getPosition()==null) {
			users.setPosition(0);
		}
		userInfoRepository.updateByusername(name,users.getUsername(),users.getSex(),users.getHeight(),users.getWeight(),users.getPosition(),users.getImage());
	}
	
	//game info
	public void updateGameInfo(String s,String s2) {
		gameInfoRepository.updateByusername(s,s2);
	}
}
