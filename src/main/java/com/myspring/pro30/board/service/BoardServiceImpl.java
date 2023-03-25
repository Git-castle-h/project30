package com.myspring.pro30.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.interfaces.BoardDAO;
import com.myspring.pro30.board.interfaces.BoardService;
import com.myspring.pro30.board.vo.ArticleVO;

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
	
	@Override
	public ArticleVO viewArticle(int articleNO) throws Exception{
		return boardDAO.selectArticle(articleNO);
	}
	
	@Override
	public void modArticle(Map articleMap)  throws DataAccessException {
		boardDAO.updateArticle(articleMap);
	}
}
