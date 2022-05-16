package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	
	public List<BoardDTO> boardAllList(@Param("s") int start, @Param("e") int end); //2개이상의 값을 넘겨줄때는 Param을 사용해야 한다.
	public int writeSave(BoardDTO dto);
	public BoardDTO contentView2(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	public int boardDelete(int writeNo);
	public void addReply(BoardRepDTO dto);
	public List<BoardRepDTO> getRepList(int write_group);
	
	public int boardCount();
}









