package com.kakao.dy.api.user;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kakao.dy.api.user.service.UserService;
import com.kakao.dy.api.user.vo.Login;

@Controller("userController")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * 로그인 
	 * @param count
	 * @return
	 * @throws AuthException 
	 * @throws Exception
	 */
	@PostMapping(value="/login")	
	public ResponseEntity<Object> authLogin(@RequestBody Login login) throws AuthException {
		return userService.login(login);
    }
	
}
