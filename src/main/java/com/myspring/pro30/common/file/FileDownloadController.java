package com.myspring.pro30.common.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	private static final String ARTICLE_IMAGE_REPO = "D:\\eclipse\\eclipse_2021_06\\workspace\\pro30\\src\\main\\webapp\\resources\\uploadFile";
	
	@RequestMapping("/download.do")
	protected void download(
			@RequestParam("imageFileName") String imageFileName,
			@RequestParam("articleNO")String articleNO,
			HttpServletRequest request,
			HttpServletResponse response
			)throws Exception {
		OutputStream out = response.getOutputStream();
		String downFile = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\"+ imageFileName;
		File file = new File(downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(in);
		
		while(true) {
			int count = bis.read();
			if(count == -1) {
				break;
			}
			out.write(count);
		}
		
		
		bis.close();
		in.close();
		out.close();
		
	}
	
}
