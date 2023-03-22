package com.myspring.pro30.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro30.board.interfaces.BoardController;
import com.myspring.pro30.board.interfaces.BoardService;
import com.myspring.pro30.board.vo.ArticleVO;

@Controller("boardController")
@RequestMapping("/board")
public class BoardControllerImpl implements BoardController{
	
	private static final String CURR_IMAGE_REPO_PATH = "C:\\board\\article_image";
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
	
}
