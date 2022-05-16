package com.care.root.member.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int userCheck(HttpServletRequest req);
	public void memberInfo(Model model);
	public void info(Model model, String id);
	public int register(MemberDTO dto);
	
	public void keepLogin(String sessionId,
							java.sql.Date limitDate,
							String id);
	public MemberDTO getUserSessionId(String sid);
	public void delete(String id);
	public void getMember(Model model,String id);
	public void modify(MemberDTO dto);
}












