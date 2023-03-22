package com.myspring.pro30;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller("homeController")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/home.do"}, method= {RequestMethod.GET})
	public ModelAndView home(
			HttpServletRequest request,
			HttpServletResponse response,
			Locale locale
			) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);	
		String formattedDate = dateFormat.format(date);
		System.out.println("date:"+formattedDate);
		
		mav.addObject("serverTime",formattedDate);
		System.out.println(viewName);
		
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping(value = "/main.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(Locale locale, Model model) {
		return "main";
	}
	
}
