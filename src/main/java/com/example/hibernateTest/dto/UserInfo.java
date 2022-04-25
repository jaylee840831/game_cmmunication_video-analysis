package com.example.hibernateTest.dto;

import java.io.IOException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

//import lombok.Data;
//
//@Data
@Entity
@Table(name="user_info")
public class UserInfo {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="sex")
	private Integer sex;
	@Column(name="height")
	private Integer height;
	@Column(name="weight")
	private Integer weight;
	@Column(name="position")
	private Integer position;
	@Column(name="image",
	        columnDefinition = "bytea")
	private byte[] image;
//	@OneToMany(mappedBy = "userInfo",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	private List<GameInfo>gameInfoList;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(MultipartFile multipartFile) {
		try {
			byte [] byteArr=multipartFile.getBytes();
			this.image = byteArr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public List<GameInfo> getGameInfoList() {
//		return gameInfoList;
//	}
//	public void setGameInfoList(List<GameInfo> gameInfoList) {
//		this.gameInfoList = gameInfoList;
//	}
	
	
}

