package com.kakao.dy.api.user;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	 * @throws Exception
	 */
	
	@PostMapping(value="/login")	
	public ResponseEntity<Object> authLogin(@RequestBody Login login) {
		return userService.login(login);
    }
	
}
