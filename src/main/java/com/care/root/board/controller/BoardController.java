package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired BoardService bs;
	@GetMapping("boardAllList")
	
	public String boardAllList(Model model, @RequestParam(value="num", required=false, defaultValue="1") int num) {
		//required = 해당하는 값이 없으면 0또는 false를 받겠다는 뜻
		//defaultValue = 기본값으로 1을 넘겨주겠다.
		bs.boardAllList(model, num);
		return "board/boardAllList";
	}
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	@PostMapping("writeSave")
	public void writeSave(MultipartHttpServletRequest mul,
							HttpServletResponse response,
							HttpServletRequest request) throws IOException {
		String message = bs.writeSave(mul);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		out.print(message);
	}
	
	@GetMapping("contentView2")
	public String contentView(@RequestParam int writeNo, Model model) {
		bs.contentView2(writeNo, model);
		return "board/contentView2";
	}
	
	@GetMapping("download")
	   public void dowonload(@RequestParam("file") String fileName,
	                              HttpServletResponse response) throws Exception {
	      //Content-disposition :파일 다운로드를 처리하는 HTTP헤더 중 하나다
	      //Content-disposition : attachment : attachment는 파일을 다운로드하여 브라우저로 표현하는 의미다
	      //fileName는 파일을 다운로드할때의 이름을 지정해 준다.
	      response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
	      File file = new File(BoardFileService.IMAGE_REPO + "\\" + fileName);
	      FileInputStream in = new FileInputStream(file);
	      //왼쪽의 값을 오른쪽으로 전달. (파일을 response(응답)스트림으로 저장한다.)
	      FileCopyUtils.copy(in, response.getOutputStream());
	      in.close(); 
	   }
	
	@GetMapping("modify_form")
	public String modifyForm(@RequestParam int writeNo, Model model) {
		bs.contentView2(writeNo, model);
		return "board/modify_form";
	}
	
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul, HttpServletResponse response) throws IOException {
		String message = bs.modify(mul);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
	
	@GetMapping("boardDelete")
	public void boardDelete(@RequestParam int writeNo, @RequestParam String imageFileName, HttpServletResponse response) throws IOException {
		String message = bs.boardDelete(writeNo, imageFileName);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}

	
}


















