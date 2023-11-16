package com.ezen.biz.order;

import java.util.List;

import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.SalesQuantity;

import utils.Criteria;

public interface OrderService {
	
	int getMaxOseq();
	
	int insertOrder(OrderVO vo);
	
	void insertOrderDetail(OrderVO vo);
	
	public List<OrderVO> getListOrderById(OrderVO vo);
	
	public List<Integer> getSeqOrdering(OrderVO vo);

	public List<OrderVO> getlistOrder(String mname);
	
	public void updateOrderResult(int odseq);
	
	public int countOrderList(String mname);
	
	public List<OrderVO> getListOrderWithPaging(Criteria criteria, String name);
	
	public List<SalesQuantity> getProductSales();
}
