package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO userCheck(String id);
	public ArrayList<MemberDTO> memberInfo();
	public int register(MemberDTO dto);
	
	public void keepLogin(Map<String, Object> map);
	
	public MemberDTO getUserSessionId(String sid);
	public void delete(String id);
	public void modify(MemberDTO dto) ;
	public int idChk(String id);
}













