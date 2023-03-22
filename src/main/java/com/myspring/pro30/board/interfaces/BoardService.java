package com.myspring.pro30.board.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

public interface BoardService {
	
	List listArticle();
	List listPage(Map paSec);
	int addArticle(Map articleMap)throws Exception;
}
