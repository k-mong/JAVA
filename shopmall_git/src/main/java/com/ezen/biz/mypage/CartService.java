package com.ezen.biz.mypage;

import java.util.List;

import com.ezen.biz.dto.CartVO;

public interface CartService {

	/*
	 * ��ٱ��� ���
	 */
	void insertCart(CartVO vo);

	/*
	 * ��ٱ��� ���
	 */
	List<CartVO> getListCart(String id);

	/*
	 * ��ٱ��� ���
	 */
	void deleteCart(int sceq);

	public void updateCart(int cseq);
}