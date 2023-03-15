package com.myspring.pro27.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.pro27.member.interfaces.MemberDAO;
import com.myspring.pro27.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO)throws Exception{
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		return vo;
	}
	
	@Override
	public List selectAllMemberList() throws Exception {
		List<MemberVO> memberList = null;
		memberList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return memberList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws Exception {
	
		int result = sqlSession.insert("mapper.member.insertMember",memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws Exception {

		int result = sqlSession.delete("mapper.member.deleteMember",id);
		System.out.println(result);
		return result;
	}

	@Override
	public MemberVO searchMemberById(String id) throws Exception {
		MemberVO result = (MemberVO) sqlSession.selectOne("mapper.member.selectMemberById",id);
		return result;
	}
	
	@Override
	public List<MemberVO> searchMemberByPwd(String pwd) throws Exception {
		List<MemberVO> result = sqlSession.selectList("mapper.member.selectMemberByPwd",pwd);
		return result;
	}

	@Override
	public int updateMember(MemberVO memberVO) throws Exception {
		int result = sqlSession.update("mapper.member.updateMember",memberVO);
		return result;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO memberVO) throws Exception {
		List<MemberVO> result = sqlSession.selectList("mapper.member.searchMember",memberVO);
		return result;
	}
	
	
	
}
