package com.ezen.biz.member;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	/*
	 * 회원ID를 조건으로 회원정보 조회
	 */
	MemberVO getMember(String id);

	/*
	 * 회원 존재여부 조회
	 */
	int confirmID(String id);

	/*
	 * 회원 정보 저장
	 */
	void insertMember(MemberVO memberVO);
	
	/*
	 * 동 이름으로 주소 찾기
	 */
	public List<AddressVO> selectAddressByDong(String dong);
	
	/*
	 * 회원 인증(로그인)
	 */
	public int loginID(MemberVO vo);

	/*
	 * 아이디 찾기
	 */
	public String getIdByNameAndEmail(MemberVO vo);

	/*
	 * 비밀번호 찾기
	 */
	public String getPwdByIdNameEmail(MemberVO vo);
	

	/*
	 * 비밀번호 수정
	 */
	public void changePassword(MemberVO vo);
	
	/*
	 * 회원 전체 목록
	 */
	public List<MemberVO> getListMember(String mname);

}
