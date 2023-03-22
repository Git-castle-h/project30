package com.myspring.pro30.board.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardController{

	ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response)throws Exception;
	ResponseEntity addArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)throws Exception;
}
