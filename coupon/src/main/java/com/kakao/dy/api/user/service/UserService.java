package com.kakao.dy.api.user.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.kakao.dy.api.user.vo.Login;
import com.kakao.dy.common.TokenProvider;
import com.kakao.dy.common.BaseConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

public class UserService {
	
	@Autowired
	TokenProvider tokenProvider;

	public ResponseEntity<Object> login(Login login) {

		
		
		return null;
	}
	
	private HashMap<String, String> generateToken(HashMap info) {
		
		Claims claims = new DefaultClaims();														// 토큰 생성 시 Custom Data
		claims.put("user_id", 		"asad");				
		String svcAcsToken 	= tokenProvider.generateToken(BaseConstants.ACSS_TOKEN, claims);		// Access Token Generate (API 접근 토큰)
		
		// 갱신토큰 생성
		String svcRfsToken 	= tokenProvider.generateToken(BaseConstants.RFSH_TOKEN, claims);		// Refresh Token Generate (API 접근 토큰 만료 시 갱신해주는 토큰)
		
		HashMap result = new HashMap();																// 결과 정보를 입력하는 DataMap 
		result.put(BaseConstants.ACSS_TOKEN, 	svcAcsToken);
		result.put(BaseConstants.RFSH_TOKEN, 	svcRfsToken);
		return result;
	}

}
