package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	/*
	 * 회원 인증(로그인)
	 * id가 존재하지 않으면 -1 리턴
	 * 비밀번호가 틀린경우 0 리턴
	 * 정상 사용자인 경우 1 이턴
	 */
	public int loginID(MemberVO vo) {
		int result = -1;	// 러턴 결과 저장
		
		// 테이블에 저장된 비밀번호 조회
		String pwd = mybatis.selectOne("MemberMapper.confirmID", vo.getId());
	
		if(pwd == null) {
			result = -1;
		}else if(pwd.equals(vo.getPwd())) {
			result = 1;
		}else {
			result = 0;
		}
		
		return result;
	}
	
	
	/*
	 * 회원ID를 조건으로 회원정보 조회
	 */
	public MemberVO getMember(String id) {
		
		return mybatis.selectOne("MemberMapper.getMember", id);
	}
	
	/*
	 * 회원 존재여부 조회
	 */
	public int confirmID(String id) {
		
		String pwd = mybatis.selectOne("MemberMapper.confirmID", id);
		
		if (pwd != null) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/*
	 * 회원 정보 저장
	 */
	public void insertMember(MemberVO memberVO) {
		
		mybatis.insert("MemberMapper.insertMember", memberVO);
	}
	
	/*
	 * 동 이름으로 주소 찾기
	 */
	public List<AddressVO> selectAddressByDong(String dong){
		
		return mybatis.selectList("MemberMapper.selectAddressByDong", dong);
	}
	
	/*
	 * 이름과 이메일로 아이디 찾기
	 */
	public String slelctIdByNameAndEmail(MemberVO vo) {
		
		return mybatis.selectOne("MemberMapper.slelctIdByNameAndEmail", vo);
	}
	
	/*
	 * 아이디와 이름 이메일로 비밀번호 찾기
	 */
	public String slelctPwdByIdNameEmail(MemberVO vo) {
		
		return mybatis.selectOne("MemberMapper.slelctPwdByIdNameEmail", vo);
	}
	
	/*
	 * 비밀번호 수정
	 */
	public void changePassword(MemberVO vo) {
		
		mybatis.update("MemberMapper.changePwd", vo);
	}
	/*
	 * 회원 전체 목록
	 */
	public List<MemberVO> listMember(String mname) {
		
		return mybatis.selectList("MemberMapper.listMember", mname);
	}
	
}
