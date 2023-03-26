package com.myspring.pro30.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro30.board.interfaces.BoardController;
import com.myspring.pro30.board.interfaces.BoardService;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;
import com.myspring.pro30.member.vo.MemberVO;

@Controller("boardController")
@RequestMapping("/board")
public class BoardControllerImpl implements BoardController{
	
//	private static final String CURR_IMAGE_REPO_PATH = "C:\\board\\article_image";
	private static final String ARTICLE_IMAGE_REPO = "D:\\eclipse\\eclipse_2021_06\\workspace\\pro30\\src\\main\\webapp\\resources\\uploadFile";
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	
	@Override
	@RequestMapping(value="/listArticle.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String viewName = (String)request.getAttribute("viewName");
		Map articleMap = new HashMap<String,Object>();
		List articleList = boardService.listArticle();
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		int section = Integer.parseInt((_section == null)? "1": _section);
		int pageNum = Integer.parseInt((_pageNum == null)? "1": _pageNum);
		
		Map paSec = new HashMap<String,Integer>();
		paSec.put("section",section);
		paSec.put("pageNum",pageNum);
		
		List articlePage = boardService.listPage(paSec);
		
		int totArticle = articleList.size();
		
		
		articleMap.put("articlePage",articlePage);
		articleMap.put("section",section);
		articleMap.put("pageNum",pageNum);
		articleMap.put("totArticle", totArticle);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleMap", articleMap);
		return mav;
		
	}
	@Override
	@RequestMapping(value="/addArticle.do",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addArticle(
				MultipartHttpServletRequest multipartRequest,
				HttpServletResponse response
			)throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName = null;
		Map<String,Object>articleMap = new HashMap<String,Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name= (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		

		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getId();
		articleMap.put("parentNO", 0);
		articleMap.put("id", id);
		
		
		List<String> fileList = upload(multipartRequest);
		List<ImageVO> imageFileList = new ArrayList<ImageVO>();
		
		System.out.println(fileList.size());
		System.out.println(imageFileList.size());
		
		if(fileList!= null && fileList.size()!=0) {
			for(String fileName : fileList) {
				ImageVO imageVO = new ImageVO();
				imageVO.setImageFileName(fileName);
				imageFileList.add(imageVO);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		

		
		try {
			int articleNO = boardService.addArticle(articleMap);
			if(imageFileList != null && imageFileList.size()!=0) {
				for(ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}
			message = "<script>"
					+ "alert('새글을 추가했습니다.');"
					+ "location.href='"
					+ multipartRequest.getContextPath()
					+ "/board/listArticle.do'"
					+ "</script>";
			resEnt = new ResponseEntity(message,responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			if(imageFileList != null && imageFileList.size()!=0) {
				
				for(ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					srcFile.delete();
				}
			
			}
			message ="<script>"
					+ "alert('오류가 발생했습니다. 다시 시도해주세요');"
					+ "location.href='"
					+ multipartRequest.getContextPath()
					+ "/board/articleForm.do'"
					+ "</script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			e.printStackTrace();
					
		}
		return resEnt;
	}
	
	@RequestMapping(value="/*Form.do",method=RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/viewArticle.do",method = {RequestMethod.GET})
	public ModelAndView viewArticle (
			@RequestParam("articleNO")int articleNO,
			HttpServletRequest request,
			HttpServletResponse response
			)throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		Map articleMap = boardService.viewArticle(articleNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("articleMap",articleMap);
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/modArticle.do", method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> modArticle(MultipartHttpServletRequest request, HttpServletResponse response)throws Exception {
			
		request.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		String imageFileName = null;
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			articleMap.put(name, value);
		}
		
		List<String> fileList = upload(request);
		List<ImageVO>imageFileList = new ArrayList<ImageVO>();
		
		if(fileList != null && fileList.size() != 0) {
			for(String fileName: fileList) {
				ImageVO imageVO = new ImageVO();
				imageVO.setImageFileName(fileName);
				imageFileList.add(imageVO);
			}
			articleMap.put("imageFileList", imageFileList);
		}

		String articleNO = (String)articleMap.get("articleNO");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.modArticle(articleMap);
			if(imageFileList != null && imageFileList.size()!=0) {
				
				for(ImageVO imageVO: imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					String originalFileName = (String)articleMap.get("originalFileName");
					File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
					oldFile.delete();
				}
			}
			      message = "<script>";
				  message += " alert('수정이 완료되었습니다.');";
				  message += " location.href='"+request.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
				  message +=" </script>";
				  System.out.println(message);
					resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);

		}catch(Exception e) {
			if(imageFileList != null && imageFileList.size()!=0) {
				for(ImageVO imageVO : imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					srcFile.delete();
				}
			}
				message = "<script>";
				message += " alert('오류발생');";
			 	message += " location.href='"+request.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			 	message +=" </script>";
			 	System.out.println(message);
				resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	@Override
	@RequestMapping(value={"/removeArticle.do"},method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity removeArticle(
			@RequestParam("articleNO") int articleNO,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws Exception{
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			boardService.removeArticle(articleNO);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			FileUtils.deleteDirectory(destDir);
			message = "<script>"
					+ "alert('삭제되었습니다.');"
					+ "location.href='"+request.getContextPath()+"/board/listArticle.do'"
					+ "</script>";
			
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			message = "<script>"
					+ "alert('오류발생.');"
					+ "location.href='"+request.getContextPath()+"/board/listArticle.do'"
					+ "</script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			e.printStackTrace();
		}
		
		
		return resEnt;
	}
	
	
	
//	파일 업로드
	private List<String> upload(MultipartHttpServletRequest request)throws Exception{
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = request.getFileNames();
		while(fileNames.hasNext()) {
			
			String fileName = fileNames.next();
			MultipartFile mFile = request.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if(originalFileName!=null && originalFileName !=""){
			fileList.add(originalFileName);
			}
			File file = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+fileName);
			if(mFile.getSize()!=0) {
				if(!file.exists()) {
				file.getParentFile().mkdirs();
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+originalFileName));
				}
			}
			
		}
		return fileList;
	}
	
	
	
	
}
