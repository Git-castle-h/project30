package com.myspring.pro30.board.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.board.vo.ArticleVO;

public interface BoardService {
	
	List listArticle();
	List listPage(Map paSec);
	int addArticle(Map articleMap)throws Exception;
	ArticleVO viewArticle(int articleNO)throws Exception;
	void modArticle(Map articleMap)throws DataAccessException;
	void removeArticle(int articleNO) throws DataAccessException;
}
