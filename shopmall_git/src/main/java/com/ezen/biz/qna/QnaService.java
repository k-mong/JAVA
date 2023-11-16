package com.ezen.biz.qna;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

public interface QnaService {

	/*
	 * id별 전체 QnA 목록 조회
	 */
	List<QnaVO> getlistQna(String id);

	/*
	 * 일련번호 별 게시글 한 건 조회
	 */
	QnaVO getQna(int qseq);

	/*
	 * 게시글 등록
	 */
	void insertQna(QnaVO vo);
	
	public List<QnaVO> getListAllQna();
	
	public void updateQna(QnaVO vo);

}