package com.example.hibernateTest.dto;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

//import lombok.Data;
//
//@Data
@Entity
@Table(name="users")
public class Users {
	@Id
	private String username;
	@Column(name="password")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
