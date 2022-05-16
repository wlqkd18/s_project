package com.care.root.board.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardService {
	
	public void boardAllList(Model model, int num);
	public String writeSave(MultipartHttpServletRequest mul);
	public void contentView2(int writeNo, Model model);
	public String modify(MultipartHttpServletRequest mul);
	public String boardDelete(int writeNo, String imageFileName);
	
	public void addReply(BoardRepDTO dto);
	public List<BoardRepDTO> getRepList(int write_group);
}
