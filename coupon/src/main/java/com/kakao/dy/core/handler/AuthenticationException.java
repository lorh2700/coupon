package com.kakao.dy.core.handler;


public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 9177852020113338422L;
	
	private String errorCd;
			
	public AuthenticationException() {
		super();
	}
	
	public AuthenticationException(String errorCd) {
		this.errorCd = errorCd;
	}
	
	public AuthenticationException(String errorCd, Throwable throwable) {
		super(throwable);
		this.errorCd = errorCd;
	}
	
	public AuthenticationException(Throwable throwable) {
		super(throwable);
	}

	public String getErrorCd() {
		return errorCd;
	}

	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}
}
