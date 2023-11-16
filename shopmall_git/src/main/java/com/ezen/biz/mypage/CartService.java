package com.ezen.biz.mypage;

import java.util.List;

import com.ezen.biz.dto.CartVO;

public interface CartService {

	/*
	 * 장바구니 담기
	 */
	void insertCart(CartVO vo);

	/*
	 * 장바구니 목록
	 */
	List<CartVO> getListCart(String id);

	/*
	 * 장바구니 취소
	 */
	void deleteCart(int sceq);

	public void updateCart(int cseq);
}