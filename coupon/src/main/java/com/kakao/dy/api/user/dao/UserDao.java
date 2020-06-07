package com.kakao.dy.api.user.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kakao.dy.api.user.vo.Login;

@Repository("userDao")
public class UserDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public String authLogin(HashMap login) {
		return sqlSession.selectOne("authLogin", login);
	}

}
