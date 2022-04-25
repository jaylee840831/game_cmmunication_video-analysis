package com.example.hibernateTest.dto;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="game_info")
public class GameInfo {
	@Id
	private String code;
	@Column(name="username")
	private String username;
    @Column(name="game")
    private String game;
    @Column(name="court")
    private String court;
    @Column(name="notice")
    private String notice;
    @Column(name="time")
    private Date time;
//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name="username")
//    private UserInfo userInfo;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getCourt() {
		return court;
	}
	public void setCout(String court) {
		this.court = court;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
//	public UserInfo getUserInfo() {
//		return userInfo;
//	}
//	public void setUserInfo(UserInfo userInfo) {
//		this.userInfo = userInfo;
//	}
    
    
}
