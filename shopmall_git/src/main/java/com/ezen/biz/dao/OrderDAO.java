package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.SalesQuantity;

import utils.Criteria;

@Repository
public class OrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public int selectMaxOseq() {
		
		return mybatis.selectOne("OrderMapper.selectMaxOseq");
	}
	
	
	public void insertOrder(OrderVO vo) {
		
		mybatis.insert("OrderMapper.insertOrder", vo);
	}
	
	
	public void insertOrderDetail(OrderVO vo) {
		
		mybatis.insert("OrderMapper.insertOrderDetail", vo);
	}

	
	
	public List<OrderVO> listOrderById(OrderVO vo){
		
		return mybatis.selectList("OrderMapper.listOrderById", vo);
	}
	
	public List<Integer> getSeqOrdering(OrderVO vo){
		
		return mybatis.selectList("OrderMapper.selectSeqOrdering", vo);
	}
	
	public List<OrderVO> listOrder(String mname){
		
		return mybatis.selectList("OrderMapper.listOrder", mname);
	}
	
	public void updateOrderResult(int odseq) {
		
		mybatis.update("OrderMapper.updateOrderResult", odseq);
	}
	
	
	public int countOrderList(String mname) {
		
		return mybatis.selectOne("OrderMapper.countOrderList", mname);
	}
	
	
	public List<OrderVO> listOrderWithPaging(Criteria criteria, String name){
		Map<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("mname", name);
		
		return mybatis.selectList("OrderMapper.listOrderWithPaging", map);
	}
	
	/*
	 * 제품별 실적 조회
	 */
	public List<SalesQuantity> getProductSales(){
		
		return mybatis.selectList("OrderMapper.listProductSales");
	}
	
	
	
}
