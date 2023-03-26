package com.myspring.pro30.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.interfaces.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;

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
	public void insertImage(Map articleMap) throws DataAccessException {
		
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int articleNO = (Integer)articleMap.get("articleNO");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
		}
		
		sqlSession.insert("mapper.board.insertNewImage",imageFileList);
		
	}
	
	private int selectNewImageFileNO()throws DataAccessException{
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}
	
	@Override
	public ArticleVO selectArticle(int articleNO)throws Exception{
		ArticleVO article =sqlSession.selectOne("mapper.board.selectArticle",articleNO);
		return article;
	};	
	
	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException {
		List<ImageVO> imageFileList =sqlSession.selectList("mapper.board.selectImageFileList",articleNO);
		return imageFileList;
	}
	
	private int selectNewArticleNO()throws Exception{
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		String _articleNO = (String)articleMap.get("articleNO");
		int articleNO = Integer.parseInt(_articleNO);
		if(imageFileList != null && imageFileList.size()!=0) {
			sqlSession.delete("mapper.board.deleteImage",articleNO);
			int imageFileNO = selectNewImageFileNO();
			for(ImageVO imageVO : imageFileList) {
				imageVO.setImageFileNO(++imageFileNO);
				imageVO.setArticleNO(articleNO);
			}
			sqlSession.insert("mapper.board.insertNewImage",imageFileList);
		}
		
		
		sqlSession.update("mapper.board.updateArticle",articleMap);
	}
	
	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle",articleNO);
	}
	
}
