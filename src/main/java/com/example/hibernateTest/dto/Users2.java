package com.example.hibernateTest.dto;

public class Users2 {
	public String username;
	public String password;
	public String sex;
	public Integer height;
	public Integer weight;
	public Integer position;
	public byte[] image;
	
	public Users2(String username, String password, String sex, Integer height, Integer weight, Integer position,
			byte[] image) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.height = height;
		this.weight = weight;
		this.position = position;
		this.image = image;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
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
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
