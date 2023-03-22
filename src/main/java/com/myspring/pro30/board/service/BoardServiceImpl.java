package com.myspring.pro30.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.interfaces.BoardDAO;
import com.myspring.pro30.board.interfaces.BoardService;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List listArticle() {
		List articleList = boardDAO.selectAllArticleList();
		return articleList;
	};
	@Override
	public List listPage(Map paSec) {
		List articlePage = boardDAO.selectPage(paSec);
		return articlePage;
	};
	@Override
	public int addArticle(Map articleMap)throws Exception{
		return boardDAO.insertArticle(articleMap);
	}
	
	
	
}
