package com.care.root.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.auto.XSSClass;
import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	@Override
	public void boardAllList(Model model, int num) {
		
		int pageLetter = 3; //한페이지 당 보여질 개수
		int allCount = mapper.boardCount(); //글의 총 개수
		
		int repeat = allCount / pageLetter;
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		model.addAttribute("repeat", repeat);
		model.addAttribute("boardList", 
								mapper.boardAllList(start, end));
	}
	@Override
	public String writeSave(MultipartHttpServletRequest mul) {
		
		String[] strRep = new String[3];
		strRep[0] = mul.getParameter("title");
		strRep[1] = mul.getParameter("content");
		strRep[2] = mul.getParameter("id");
		
		strRep = XSSClass.replaceParamter(strRep);
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(strRep[0]);
		dto.setContent(strRep[1]);
		dto.setId(strRep[2]);
		
		MultipartFile file = mul.getFile("image_file_name");
		if(file.getSize() != 0) {
			dto.setImageFileName(bfs.saveFile(file));
		}else {
			dto.setImageFileName("nan");
		}
		int result = 0;
		result = mapper.writeSave(dto);
		String msg, url;
		if(result == 1) {
			msg = "새글이 추가되었습니다!!!";
			url ="/root/board/boardAllList";
		}else {
			msg = "문제가 발생했습니다!!!";
			url = "/root/board/writeForm";
		}
		
		return bfs.getMessage(msg,url);
	}
	
	@Override
	public void contentView2(int writeNo, Model model) {
		upHit(writeNo);
		model.addAttribute("info", mapper.contentView2(writeNo));
	}
	
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	@Override
	public String modify(MultipartHttpServletRequest mul) {
		
		String[] s = new String[2];
		s[0] = mul.getParameter("title");
		s[1] = mul.getParameter("content");
		
		s = XSSClass.replaceParamter(s);
		
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")));
		dto.setTitle(s[0]);
		dto.setContent(s[1]);
		
		MultipartFile file = mul.getFile("imageFileName");
		if(!file.isEmpty()) { //이미지가 있다면
			dto.setImageFileName(bfs.saveFile(file));
			bfs.deleteImage((String)mul.getParameter("originFileName"));
		}else {
			dto.setImageFileName(mul.getParameter("originFileName"));
		}
		int result = mapper.modify(dto);
		String msg, url;
		if(result == 1) {
			msg = "수정 성공!!!";
			url = "/root/board/contentView2?writeNo="+dto.getWriteNo();
		}else {
			msg = "수정 실패!!!";
			url = "/root/board/modify_form?writeNo="+dto.getWriteNo();
		}
		
		return bfs.getMessage(msg, url);
	}
	
	@Override
	public String boardDelete(int writeNo, String imageFileName) {
		
		int result = mapper.boardDelete(writeNo);
		String msg, url;
		
		if(result == 1) {
			bfs.deleteImage(imageFileName);
			msg = "삭제 성공";
			url = "/root/board/boardAllList";
		}else {
			msg = "삭제 실패";
			url = "/root/board/contentView2?writeNo="+writeNo;
		}
		return bfs.getMessage(msg, url);
	}
	@Override
	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
		
	}
	@Override
	public List<BoardRepDTO> getRepList(int write_group) {
		return mapper.getRepList(write_group);
	}

}










