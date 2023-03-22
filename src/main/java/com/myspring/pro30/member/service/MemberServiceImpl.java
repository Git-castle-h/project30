package com.myspring.pro30.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.pro30.member.interfaces.MemberDAO;
import com.myspring.pro30.member.interfaces.MemberService;
import com.myspring.pro30.member.vo.MemberVO;


@Service("memberService")	
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List<MemberVO> listMember() throws Exception {
		
		List<MemberVO> memberList = null;
		memberList = memberDAO.selectAllMemberList();
		return memberList;
	}

	@Override
	public int addMember(MemberVO memberVO) throws Exception {

		return memberDAO.insertMember(memberVO);
	}

	@Override
	public int removeMember(String id) throws Exception {

		return memberDAO.deleteMember(id);
	}

	@Override
	public MemberVO searchMemberById(String id) throws Exception {
		
		return memberDAO.searchMemberById(id);
	}

	@Override
	public List<MemberVO> searchMemberByPwd(String pwd) throws Exception {
		
		return memberDAO.searchMemberByPwd(pwd);
	}
	
	@Override
	public int updateMember(MemberVO memberVO) throws Exception {
		
		return memberDAO.updateMember(memberVO);
	}

	@Override
	public List<MemberVO> searchMember(MemberVO memberVO)throws Exception  {
		return memberDAO.searchMember(memberVO);
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws Exception{
		return memberDAO.loginById(memberVO);
	}
	
	
	@Override
	public boolean idOverlapped(String id) throws Exception {
		
		MemberVO memberVO = memberDAO.searchMemberById(id); 
		System.out.println(id);
		System.out.println(memberVO);
		boolean result = false;
		if(memberVO != null){
			System.out.println("memberVO true");
			result = true;
		}else {
			System.out.println("memberVO null");
			result = false;
		}
		
		return result;
		
	}
	
	
	
	
}
