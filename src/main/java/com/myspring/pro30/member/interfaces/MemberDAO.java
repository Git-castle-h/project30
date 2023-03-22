package com.myspring.pro30.member.interfaces;

import java.util.List;

import com.myspring.pro30.member.vo.MemberVO;


public interface MemberDAO {

	List selectAllMemberList() throws Exception;
	int insertMember(MemberVO memberVO) throws Exception;
	int deleteMember(String id) throws Exception;
	MemberVO searchMemberById(String id) throws Exception;
	List<MemberVO> searchMemberByPwd(String pwd) throws Exception;
	int updateMember(MemberVO memberVO) throws Exception;
	List<MemberVO> searchMember(MemberVO memberVO) throws Exception;
	MemberVO loginById(MemberVO memberVO)throws Exception;

	
}
