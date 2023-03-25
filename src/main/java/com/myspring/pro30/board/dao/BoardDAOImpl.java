package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.interfaces.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ArticleVO> selectAllArticleList(){
		
		List<ArticleVO> articleList = sqlSession.selectList("mapper.board.selectAllArticleList");
		
		return articleList;
	};
	
	@Override
	public List<ArticleVO> selectPage(Map paSec){
		
		List<ArticleVO> articlePage = sqlSession.selectList("mapper.board.selectPage",paSec);
		
		return articlePage;
	};
	
	@Override
	public int insertArticle(Map articleMap)throws Exception{
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertArticle",articleMap);
		return articleNO;
	};
	
	@Override
	public ArticleVO selectArticle(int articleNO)throws Exception{
		ArticleVO article =sqlSession.selectOne("mapper.board.selectArticle",articleNO);
		return article;
	};	
	
	private int selectNewArticleNO()throws Exception{
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle",articleMap);
	}
	
	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle",articleNO);
	}
	
}
