package com.kakao.dy.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kakao.dy.common.BaseConstants;
import com.kakao.dy.core.handler.AuthenticationException;
import com.kakao.dy.util.TokenProvider;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter implements AsyncHandlerInterceptor {
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// Request Header에서 접근토큰 취득
		String accessToken = request.getHeader(BaseConstants.ACSS_TOKEN);
		if (accessToken == null)
			throw new MissingServletRequestParameterException(BaseConstants.ACSS_TOKEN, "String");
		
		// 접근 토큰이 만료되면 오류 발생 
		if (tokenProvider.isTokenExpired(accessToken))
			throw new AuthenticationException(HttpStatus.UNAUTHORIZED.toString());
		return true;
	}
}
