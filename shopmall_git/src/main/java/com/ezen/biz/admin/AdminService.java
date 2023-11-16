package com.ezen.biz.admin;

import java.util.List;

import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.MemberVO;

public interface AdminService {

	int adminCheck(AdminVO vo);

	AdminVO getAdmin(String id);

	
	
	
}