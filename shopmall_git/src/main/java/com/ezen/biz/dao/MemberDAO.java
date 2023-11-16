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
	 * ȸ�� ����(�α���)
	 * id�� �������� ������ -1 ����
	 * ��й�ȣ�� Ʋ����� 0 ����
	 * ���� ������� ��� 1 ����
	 */
	public int loginID(MemberVO vo) {
		int result = -1;	// ���� ��� ����
		
		// ���̺� ����� ��й�ȣ ��ȸ
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
	 * ȸ��ID�� �������� ȸ������ ��ȸ
	 */
	public MemberVO getMember(String id) {
		
		return mybatis.selectOne("MemberMapper.getMember", id);
	}
	
	/*
	 * ȸ�� ���翩�� ��ȸ
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
	 * ȸ�� ���� ����
	 */
	public void insertMember(MemberVO memberVO) {
		
		mybatis.insert("MemberMapper.insertMember", memberVO);
	}
	
	/*
	 * �� �̸����� �ּ� ã��
	 */
	public List<AddressVO> selectAddressByDong(String dong){
		
		return mybatis.selectList("MemberMapper.selectAddressByDong", dong);
	}
	
	/*
	 * �̸��� �̸��Ϸ� ���̵� ã��
	 */
	public String slelctIdByNameAndEmail(MemberVO vo) {
		
		return mybatis.selectOne("MemberMapper.slelctIdByNameAndEmail", vo);
	}
	
	/*
	 * ���̵�� �̸� �̸��Ϸ� ��й�ȣ ã��
	 */
	public String slelctPwdByIdNameEmail(MemberVO vo) {
		
		return mybatis.selectOne("MemberMapper.slelctPwdByIdNameEmail", vo);
	}
	
	/*
	 * ��й�ȣ ����
	 */
	public void changePassword(MemberVO vo) {
		
		mybatis.update("MemberMapper.changePwd", vo);
	}
	/*
	 * ȸ�� ��ü ���
	 */
	public List<MemberVO> listMember(String mname) {
		
		return mybatis.selectList("MemberMapper.listMember", mname);
	}
	
}
