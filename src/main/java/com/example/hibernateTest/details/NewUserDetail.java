package com.example.hibernateTest.details;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.hibernateTest.dto.Users;
import com.example.hibernateTest.service.NewUserDetailService;

/**
 * 實作UserDetails，目的是想在login之後，修改user name
 */

public class NewUserDetail implements UserDetails{
	
	@Autowired
	NewUserDetailService newUserDetailService;
	
	private static final long serialVersionUID = 1L;
	private Users users;
	
	public NewUserDetail(Users users) {
		this.users=users;
	}
	
	@Override
	public Collection<? extends GrantedAuthority>getAuthorities(){
		//添加使用者權限
		List<GrantedAuthority>auths=AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_user,ROLE_visiter");
		return auths;
	}
	
	@Override
	public String getPassword() {
		return users.getPassword();
	};

	@Override
	public String getUsername() {
		return users.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	};

	@Override
	public boolean isAccountNonLocked() {
		return true;
	};

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	};

	@Override
	public boolean isEnabled() {
		return true;
	};
	
	public void setUserName(String name) {
		this.users.setUsername(name);
	}
}
