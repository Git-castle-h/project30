package com.myspring.pro30.board.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.board.vo.ArticleVO;

public interface BoardDAO {
	
	List selectAllArticleList();
	List selectPage(Map paSec);
	int insertArticle(Map articleMap)throws Exception;
	ArticleVO selectArticle(int articleNO)throws Exception;
	List selectImageFileList(int articleNO)throws DataAccessException;
	void updateArticle(Map articleMap) throws DataAccessException ;
	void deleteArticle(int articleNO) throws DataAccessException ;
	void insertImage(Map articleMap)throws DataAccessException;
	
}
