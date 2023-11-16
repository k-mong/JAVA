package com.ezen.biz.qna;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

public interface QnaService {

	/*
	 * id�� ��ü QnA ��� ��ȸ
	 */
	List<QnaVO> getlistQna(String id);

	/*
	 * �Ϸù�ȣ �� �Խñ� �� �� ��ȸ
	 */
	QnaVO getQna(int qseq);

	/*
	 * �Խñ� ���
	 */
	void insertQna(QnaVO vo);
	
	public List<QnaVO> getListAllQna();
	
	public void updateQna(QnaVO vo);

}