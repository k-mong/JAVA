package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.qna.QnaService;

@Repository
public class QnaDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	/*
	 * id�� ��ü QnA ��� ��ȸ
	 */
	public List<QnaVO> listQna(String id){
		return mybatis.selectList("QnaMapper.listQna", id);
	}
	
	/*
	 * �Ϸù�ȣ �� �Խñ� �� �� ��ȸ
	 */
	public QnaVO getQna(int qseq) {
		
		return mybatis.selectOne("QnaMapper.getQna", qseq);
	}
	
	/*
	 * �Խñ� ���
	 */
	public void insertQna(QnaVO vo) {
		
		mybatis.insert("QnaMapper.insertQna", vo);
	}
	
	public List<QnaVO> listAllQna(){
		return mybatis.selectList("QnaMapper.listAllQna");
	}
	
	public void updateQna(QnaVO qseq) {
		mybatis.update("QnaMapper.updateQna", qseq);
	}
}
