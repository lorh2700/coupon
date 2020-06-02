package com.kakao.dy.api.user.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class Login {

	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String pwd;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
