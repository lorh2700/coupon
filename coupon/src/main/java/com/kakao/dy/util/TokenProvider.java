package com.kakao.dy.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kakao.dy.common.BaseConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {
	
	/** 토큰 암호 키 */
	private String secretKey = "kakao";
	
	/** Access 토큰 만료 	밀리초 */
	@Value("${token.expire-sec.access}")
	private long accessTokenSc;
	
	/** Refresh 토큰 만료 	밀리초 */
	@Value("${token.expire-sec.refresh}")
	private long refreshTokenSc;
		
	/**
	 * 토큰 발급
	 * 
	 * @param tokenType
	 * @param claims
	 * @return
	 */
	public String generateToken(String tokenType, Claims claims) {
		return  Jwts.builder()			
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setSubject(tokenType)
				.setExpiration(generateExpirationDate(tokenType))				
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	/**
	 * 토큰 재발급
	 * 
	 * @param token
	 * @return
	 */
	public String refreshToken(String token) {
		Claims claims 	 = getClaimsFromExpiredToken(token);
		String tokenType = claims.getSubject();
		return generateToken(tokenType, claims);
	}
	
	public String getUserName(String token) {
		Claims claims = getClaimsFromToken(token);
		String userName = String.valueOf(claims.get("user_id")); 
		return userName;
	}
	
	/**
	 * 클레임 정보 조회
	 * 
	 * @param token
	 * @return
	 */
	public Claims getClaimsFromToken(String token) {		
		return Jwts.parser()
                 .setSigningKey(secretKey)
                 .parseClaimsJws(token)
                 .getBody();
	}
	
	/**
	 * 만료된 토큰으로 부터 클레임 정보 조회
	 * 
	 * @param token
	 * @return
	 */
	public Claims getClaimsFromExpiredToken(String token) {
		Claims claims 	 = null;
		try {
			claims = getClaimsFromToken(token);
		} catch (ExpiredJwtException e) {
			claims = e.getClaims();
		}		
		return claims;
	}
	
	/**
	 * 토큰 유형 조회
	 * 
	 * @param token
	 * @return
	 */
	public String getTokenTypeFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	
	/**
	 * 토큰 발급 일자 조회
	 * 
	 * @param token
	 * @return
	 */
	public Date getIssuedDateFromToken(String token) {
		return getClaimsFromToken(token).getIssuedAt();
	}
	
	/**
	 * 토큰의 만료 일자 조회
	 * 
	 * @param token
	 * @return
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token).getExpiration();
	}
	
	/**
	 * 토큰 만료 여부
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenExpired(String token) {
		boolean result = false;
		try {
			result = getExpirationDateFromToken(token).before(new Date());
		} catch (ExpiredJwtException e) {
			result = true;
		}
		return result;
	}
		
	/**
	 * 만료 시간 생성
	 * 
	 * @param tokenType
	 * @return
	 */
	private Date generateExpirationDate(String tokenType) {
		long expireMs = BaseConstants.ACSS_TOKEN.equals(tokenType) ? accessTokenSc : refreshTokenSc;
		return new Date(new Date().getTime() + (1000 * expireMs));
	}
	
	private long getLong(Object obj) {
		if (obj instanceof Number) {
        	return ((Number)obj).longValue();
        } else if (obj instanceof java.sql.Timestamp) {
        	return ((java.sql.Timestamp)obj).getTime();
        } else if (obj instanceof String) {        	
        	return Long.parseLong((String)obj);        	
        }
		return 0;
	}
	
}
