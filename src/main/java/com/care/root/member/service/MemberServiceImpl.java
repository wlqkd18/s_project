package com.care.root.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl 
implements MemberService{
	BCryptPasswordEncoder encoder = 
							new BCryptPasswordEncoder();
	public int register(MemberDTO dto) {
		dto.setPw( encoder.encode(dto.getPw()) );
		try {
			return mapper.register(dto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Autowired MemberMapper mapper;
	@Override
	public int userCheck(HttpServletRequest req) {
		MemberDTO dto =
				mapper.userCheck(req.getParameter("id"));
		// select * from membership where id=#{id}
		if(dto != null) {//데이터가 존재한다
		if( encoder.matches(req.getParameter("pwd"), dto.getPw() ) || dto.getPw().equals(req.getParameter("pwd")) ) {
				//로그인 성공
				return 0;
			}
		}
		//로그인 실패인 경우 return 1;
		return 1;
	}
	public void memberInfo(Model model) {
		model.addAttribute(
				"memberList",mapper.memberInfo());
	}

	public void info(Model model, String id) {
		model.addAttribute("info",mapper.userCheck(id) );
	}
	public void keepLogin(String sessionId,
							java.sql.Date limitDate,
							String id) {
		Map<String, Object> map = 
						new HashMap<String, Object>();
		map.put("sessionId",sessionId);
		map.put("limitDate",limitDate);
		map.put("id",id);
		mapper.keepLogin(map);
	}
	public MemberDTO getUserSessionId(String sid) {
		return mapper.getUserSessionId(sid);
	}
	public void delete(String id) {
		mapper.delete(id);
	}
	public void getMember(Model model,String id) {
		MemberDTO dto = mapper.userCheck(id);
		//024578/서울/상세주소
		String[] addr = dto.getAddr().split("/");
		//addr[0]="024578", [1]="서울", [3]="상세주소"
		model.addAttribute("info", dto);
		model.addAttribute("addr",addr);
	}
	public void modify(MemberDTO dto) {
		MemberDTO d = mapper.userCheck(dto.getId());
		
		if(!d.getPw().equals(dto.getPw())) {
			System.out.println("비밀번호가 변경 되었다면");
		//aaa -> asd123ofiej23
			dto.setPw( encoder.encode(dto.getPw()) );
		}
		
		mapper.modify(dto);
	}
}













