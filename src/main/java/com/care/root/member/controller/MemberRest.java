package com.care.root.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@RestController
@RequestMapping("member")
public class MemberRest {
	@Autowired
	MemberMapper mapper;
	
	@GetMapping(value="idChk/{userId}", 
			produces = "application/json; charset=utf-8")
	public int idChk(@PathVariable String userId) {
		int result = 0;
		if(userId.length() > 6) {
			result = mapper.idChk(userId);
		}else {
			return -1;
		}
		return result;
	}
}





