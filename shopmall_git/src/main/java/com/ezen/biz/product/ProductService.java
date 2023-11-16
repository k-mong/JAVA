package com.ezen.biz.product;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

public interface ProductService {

	List<ProductVO> getNewProductList();

	List<ProductVO> getBestProductList();

	ProductVO getProduct(ProductVO vo);
	
	List<ProductVO> getProductListByKind(String kind);
	
	// °ü¸®ÀÚ
	public int countProductList(String name);
	
	public List<ProductVO> getlistProduct(String name);
	
	public void insertProduct(ProductVO vo);
	
	public void updateProduct(ProductVO vo);
	
	public List<ProductVO> getListProductWithPaging(Criteria criteria, String name);
}