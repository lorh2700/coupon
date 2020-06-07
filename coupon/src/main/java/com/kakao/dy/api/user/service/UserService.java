package com.kakao.dy.api.user.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashMap;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kakao.dy.api.user.dao.UserDao;
import com.kakao.dy.api.user.vo.Login;
import com.kakao.dy.common.BaseConstants;
import com.kakao.dy.util.TokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

@Service
public class UserService {
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	UserDao userDao;

	public ResponseEntity<Object> login(Login login) throws AuthException {
		
		HashMap<String, String> param = new HashMap<>();
		param.put("user_id", login.getUser_id());
		
		String pwd = userDao.authLogin(param);
		
		if(!pwd.equals(login.getPwd())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		HashMap<String, String> result = new HashMap<>();
		result = generateToken(login);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	private HashMap<String, String> generateToken(Login login) {
		
		Claims claims = new DefaultClaims();														// 토큰 생성 시 Custom Data
		claims.put("user_id", login.getUser_id());				
		String svcAcsToken 	= tokenProvider.generateToken(BaseConstants.ACSS_TOKEN, claims);		// Access Token Generate (API 접근 토큰)

		// 갱신토큰 생성
		String svcRfsToken 	= tokenProvider.generateToken(BaseConstants.RFSH_TOKEN, claims);		// Refresh Token Generate (API 접근 토큰 만료 시 갱신해주는 토큰)
		
		HashMap result = new HashMap();																
		result.put(BaseConstants.ACSS_TOKEN, 	svcAcsToken);
		result.put(BaseConstants.RFSH_TOKEN, 	svcRfsToken);
		return result;
	}

}
