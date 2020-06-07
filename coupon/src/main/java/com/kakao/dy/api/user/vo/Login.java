package com.kakao.dy.api.user.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class Login {

	@NotEmpty
	private String user_id;
	
	@NotEmpty
	private String pwd;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
