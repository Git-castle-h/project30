package com.myspring.pro30.member.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro30.member.vo.MemberVO;

public interface MemberController {
	
	
	ModelAndView listMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView addMember(MemberVO memberVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView removeMember(String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView modIntro(String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView modMember(String pwd,HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView modError(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView updateMember(MemberVO membeVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView searchMember(MemberVO memberVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	void idOverlapped(String id,HttpServletRequest request, HttpServletResponse response) throws Exception;

	

}
