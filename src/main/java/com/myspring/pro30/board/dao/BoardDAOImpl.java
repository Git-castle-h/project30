package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.interfaces.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	public List<ArticleVO> selectAllArticleList() {
		
		List<ArticleVO> articleList = sqlSession.selectList("mapper.board.selectAllArticleList");
		
		return articleList;
	};
	
	public List<ArticleVO> selectPage(Map paSec) {
		
		List<ArticleVO> articlePage = sqlSession.selectList("mapper.board.selectPage",paSec);
		
		return articlePage;
	};
	
}
