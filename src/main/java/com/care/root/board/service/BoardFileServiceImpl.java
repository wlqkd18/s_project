package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	
	@Override
	public String getMessage(String msg, String url) {
		String message = 
				"<script>alert('"+msg+"');";
		message += "location.href='"+url+"'";
		message += "</script>";
		return message;
	}

	@Override
	public String saveFile(MultipartFile file) {
		SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar calendar = Calendar.getInstance();
		String sysFileName = simpl.format(calendar.getTime());
		sysFileName += file.getOriginalFilename();
		
		File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
		
		try {
			file.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}

	@Override
	public void deleteImage(String name) {
		File d = new File(IMAGE_REPO+"/"+name);
		d.delete();
	}

}
