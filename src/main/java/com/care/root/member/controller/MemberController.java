package com.care.root.member.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.session.name.MemberSession;

@Controller
@RequestMapping("member")
public class MemberController implements MemberSession{
	@Autowired
	MemberService ms;
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	@PostMapping("user_check")
	public String userCheck(HttpServletRequest req,
							RedirectAttributes rs) {
		int result = ms.userCheck(req);
		if(result == 0) {
			rs.addAttribute("id", req.getParameter("id"));
			rs.addAttribute("autoLogin", 
							req.getParameter("autoLogin"));
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	@RequestMapping("successLogin")
	public String successLogin(HttpServletRequest req,
								HttpSession session,
								HttpServletResponse response) {
		System.out.println("autoLogin" + req.getParameter("autoLogin") );
		System.out.println("id : "+req.getParameter("id"));
		session.setAttribute(LOGIN, req.getParameter("id"));
		
		if(req.getParameter("autoLogin") != null) {
			int limitTime = 60*60*24*90;// 90일
			Cookie loginCookie = 
				new Cookie("loginCookie",session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);
			response.addCookie(loginCookie);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date() );
			cal.add( Calendar.MONTH, 3 );
			
			java.sql.Date limitDate = 
				new java.sql.Date(cal.getTimeInMillis());
			ms.keepLogin(session.getId(), limitDate, 
									req.getParameter("id"));
		}
		
		return "member/successLogin";
	}
	@GetMapping("logout")
	public String logout(HttpSession session,
		HttpServletResponse response,
@CookieValue(value="loginCookie",required=false) Cookie loginCookie) {
		
		if(session.getAttribute(LOGIN) != null) {
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				loginCookie.setPath("/");
				response.addCookie(loginCookie);
				ms.keepLogin("nan",
				new java.sql.Date(System.currentTimeMillis()),
				(String)session.getAttribute(LOGIN));
			}
			session.invalidate();
		}
		return "redirect:/index";
	}
	@GetMapping("memberInfo")
	public String memberInfo(Model model) {
		ms.memberInfo(model);
		return "member/memberInfo";
	}
	@GetMapping("info")
	public String info(@RequestParam String id ,
										Model model) {
		ms.info(model, id);
		return "member/info";
	}
	@GetMapping("register_form")
	public String registerForm() {
		return "member/register";
	}
	@PostMapping("register")
	public String register(MemberDTO dto) {
		int result = ms.register(dto);
		if(result == 1)
			return "redirect:login";
		return "redirect:register_form";
	}
	@GetMapping("modify_form")
	public String modifyForm(@RequestParam String id,
							Model model) {
		ms.getMember(model, id);
		return "member/modify_form";
	}
	@PostMapping("modify")
	public String modify(MemberDTO dto) {
		ms.modify(dto);
		return "redirect:info?id="+dto.getId();
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam String id) {
		ms.delete(id);
		return "redirect:memberInfo";
	}
	
}































