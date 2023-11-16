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
	 * id별 전체 QnA 목록 조회
	 */
	public List<QnaVO> listQna(String id){
		return mybatis.selectList("QnaMapper.listQna", id);
	}
	
	/*
	 * 일련번호 별 게시글 한 건 조회
	 */
	public QnaVO getQna(int qseq) {
		
		return mybatis.selectOne("QnaMapper.getQna", qseq);
	}
	
	/*
	 * 게시글 등록
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
