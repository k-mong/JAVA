package com.ezen.biz.dto;


import java.util.Date;

import lombok.Data;

@Data
public class CartVO {
	private int cseq;
	private String id;
	private int pseq;
	private String mname;	// 사용자 이름 user테이블에서 정보 갖고옴  
	private String pname;	// 상품 이름 product 테이블에서 갖고옴
	private int quantity;
	private int price2;
	private Date indate;
}
