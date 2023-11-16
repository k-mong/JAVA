package com.ezen.biz.member;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	/*
	 * ȸ��ID�� �������� ȸ������ ��ȸ
	 */
	MemberVO getMember(String id);

	/*
	 * ȸ�� ���翩�� ��ȸ
	 */
	int confirmID(String id);

	/*
	 * ȸ�� ���� ����
	 */
	void insertMember(MemberVO memberVO);
	
	/*
	 * �� �̸����� �ּ� ã��
	 */
	public List<AddressVO> selectAddressByDong(String dong);
	
	/*
	 * ȸ�� ����(�α���)
	 */
	public int loginID(MemberVO vo);

	/*
	 * ���̵� ã��
	 */
	public String getIdByNameAndEmail(MemberVO vo);

	/*
	 * ��й�ȣ ã��
	 */
	public String getPwdByIdNameEmail(MemberVO vo);
	

	/*
	 * ��й�ȣ ����
	 */
	public void changePassword(MemberVO vo);
	
	/*
	 * ȸ�� ��ü ���
	 */
	public List<MemberVO> getListMember(String mname);

}
