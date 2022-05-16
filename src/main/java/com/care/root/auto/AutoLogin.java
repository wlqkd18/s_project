package com.care.root.auto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.session.name.MemberSession;

public class AutoLogin extends HandlerInterceptorAdapter
								implements MemberSession{
	@Autowired
	MemberService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie loginCookie = 
				WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			MemberDTO dto = 
				ms.getUserSessionId(loginCookie.getValue());
			if(dto != null) {
				request.getSession().setAttribute(LOGIN,dto.getId());
			}
		}
		return true;
	}

	
	
}













