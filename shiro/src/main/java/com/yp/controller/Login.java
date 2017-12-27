package com.yp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yp.domain.User;

@Controller
@RequestMapping("/shiro")
public class Login {
	
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public String login(User user, HttpServletRequest request) {
		
		System.out.println("username=" + user.getUsername() + "----" + "password=" + user.getPassword()); 
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		
		Subject curUser = SecurityUtils.getSubject();
		try {
			curUser.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "login";
		}
		
		User authUser = (User)curUser.getPrincipal();
		
		request.getSession().setAttribute("user", authUser);
		
		return "success";
	}

}
