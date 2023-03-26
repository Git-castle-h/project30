package com.myspring.pro30.board.service;

import java.util.HashMap;
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
import com.myspring.pro30.board.vo.ImageVO;

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
		int articleNO = boardDAO.insertArticle(articleMap);
		try {
			articleMap.get("imageFileList");
			articleMap.put("articleNO", articleNO);
			boardDAO.insertImage(articleMap);	
		} catch (Exception e) {
			System.out.println("no images");
		}
		
		return articleNO;
	}
	
	@Override
	public Map viewArticle(int articleNO) throws Exception{
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
	
	@Override
	public void modArticle(Map articleMap)  throws DataAccessException {
		boardDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNO) throws DataAccessException {
		boardDAO.deleteArticle(articleNO);
	}
}
