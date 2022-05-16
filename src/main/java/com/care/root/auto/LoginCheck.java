package com.care.root.auto;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.session.name.MemberSession;

public class LoginCheck extends HandlerInterceptorAdapter
								implements MemberSession{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute(LOGIN) == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String str = "<script>";
			str += "alert('로그인 후 글쓰기가 가능합니다');";
			str += "location.href='/root/member/login';";
			str += "</script>";
			out.print(str);
			return false;
		}
		return true;
	}
	
}













