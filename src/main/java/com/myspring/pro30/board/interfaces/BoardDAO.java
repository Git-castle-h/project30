package com.myspring.pro30.board.interfaces;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
	
	List selectAllArticleList();
	List selectPage(Map paSec);
	int insertArticle(Map articleMap)throws Exception;
}
