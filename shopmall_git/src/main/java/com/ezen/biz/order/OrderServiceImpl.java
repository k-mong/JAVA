package com.ezen.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.OrderDAO;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.SalesQuantity;
import com.ezen.biz.mypage.CartService;

import utils.Criteria;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CartService cartService;
	
	@Override
	public int getMaxOseq() {

		return orderDao.selectMaxOseq();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		// (1) 신규 주문번호 생성
		int oseq = orderDao.selectMaxOseq();
		
		// (2) 신규주문 주문테이블에 저장
		vo.setOseq(oseq);
		orderDao.insertOrder(vo);
		
		// (3) 장바구니 목록을 읽어 주문 상세테이블에 저장
		List<CartVO> cartList = cartService.getListCart(vo.getId());
		
		for(CartVO cart : cartList) {	// cartList에 하나씩 꺼내서 cart에 넣는다
			OrderVO order = new OrderVO();
			
			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());
			
			insertOrderDetail(order);
			
			// 장바구니 테이블 업데이트(result를 '2'(처리))
			cartService.updateCart(cart.getCseq());
		}
		
		return oseq;
	}

	@Override
	public void insertOrderDetail(OrderVO vo) {

		orderDao.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> getListOrderById(OrderVO vo) {

		return orderDao.listOrderById(vo);
	}
	
	@Override
	public List<Integer> getSeqOrdering(OrderVO vo){
		
		return orderDao.getSeqOrdering(vo);
	}

	@Override
	public List<OrderVO> getlistOrder(String mname) {
		
		return orderDao.listOrder(mname);
	}

	@Override
	public void updateOrderResult(int odseq) {
		
		orderDao.updateOrderResult(odseq);
		
	}

	@Override
	public int countOrderList(String mname) {
		
		return orderDao.countOrderList(mname);
	}

	@Override
	public List<OrderVO> getListOrderWithPaging(Criteria criteria, String name) {
		
		return orderDao.listOrderWithPaging(criteria, name);
	}

	@Override
	public List<SalesQuantity> getProductSales() {

		return orderDao.getProductSales();
	}

	

}
