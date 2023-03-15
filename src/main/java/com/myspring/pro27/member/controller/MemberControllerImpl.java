package com.myspring.pro27.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro27.member.interfaces.MemberController;
import com.myspring.pro27.member.interfaces.MemberService;
import com.myspring.pro27.member.vo.MemberVO;


@Controller("memberContoller")
@RequestMapping("/member")
public class MemberControllerImpl  extends MultiActionController implements MemberController{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/listMember.do", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		
		logger.info("info : viewName = "+viewName);
		logger.debug("debug 레벨 : viewName = "+viewName);
		
		List<MemberVO> memberList = memberService.listMember();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("memberList",memberList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/addMember.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView addMember(
			@ModelAttribute("member")MemberVO memberVO,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		
		
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
		
	}
	
	@Override
	@RequestMapping(value="/removeMember.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView removeMember(
			@RequestParam("id") String id,	
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
			
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/modIntro.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView modIntro(
			@RequestParam("id")String id,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		String viewName = getViewName(request);
		request.setCharacterEncoding("utf-8");
		
		MemberVO memberVO = memberService.searchMemberById(id);
		HttpSession session = request.getSession();
		session.setAttribute("member", memberVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/modMember.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView modMember(
			@RequestParam("pwd")String pwd,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		ModelAndView mav =new ModelAndView();
		if(memberVO.getPwd().equals(pwd)){
			mav.setViewName(viewName);
		}else {
			mav.setViewName("redirect:/member/modError.do");
		}
		
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/modError.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView modError(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav =new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	

	@Override
	@RequestMapping(value="/updateMember.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateMember(
			@ModelAttribute("member")MemberVO memberVO,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		memberService.updateMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/searchMember.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchMember(
			@ModelAttribute("member")MemberVO memberVO,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		List<MemberVO> memberList = memberService.searchMember(memberVO);
		
		ModelAndView mav = new ModelAndView("/member/listMember");
		mav.addObject("memberList",memberList);
		return mav;
	}

	@RequestMapping(value="/memberForm.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/idOverlapped.do",  method= {RequestMethod.POST,RequestMethod.GET})
	public void idOverlapped(
			@RequestParam("id")String id,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		PrintWriter writer = response.getWriter();
		
		
		boolean result =memberService.idOverlapped(id);
		
		PrintWriter pw = response.getWriter();
		
		
		String isUsable;
		if(result == true) {
			System.out.println(result);
			isUsable="not_usable";
			
		}else {
			System.out.println(result);
			isUsable="usable";
		}
		
		try {
			pw.print(isUsable);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@RequestMapping(value="/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(
			@ModelAttribute("member") MemberVO member,
			RedirectAttributes rAttr,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/member/listMember.do");
		}else {
			rAttr.addAttribute("result","loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
		
	}
	
	@RequestMapping(value="/logout.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session =request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMember.do");
		return mav;
	}

	@RequestMapping(value="/*Form.do", method= {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView form(@RequestParam(value="result", required=false)String result,
			HttpServletRequest request, HttpServletResponse response
			)throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri==null||uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		
		int begin = 0;
		if(!(contextPath == null)||("".equals(contextPath))) {
			
			begin = contextPath.length();
		} 
		
		int end;
		
		if(uri.indexOf(";")!=-1) {
			end = uri.indexOf(";");
		}else if(uri.indexOf("?")!=-1) {
			end=uri.indexOf("?");
		}else {
			end =uri.length();
		}
		
		String fileName= uri.substring(begin,end);
		if(fileName.indexOf(".")!=-1) {
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("/",1),fileName.length());
		}
		
		return fileName;
	}




	
	
}
