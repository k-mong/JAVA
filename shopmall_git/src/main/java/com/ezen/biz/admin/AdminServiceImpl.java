package com.ezen.biz.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dto.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	/*
	 * 관리자 인증
	 */
	@Override
	public int adminCheck(AdminVO vo) {
		String pwd = adminDao.adminCheck(vo.getId());
		
		if(pwd.equals(vo.getPwd())) {
			return 1;
		}else if (pwd == null){
			return -1;
		}else {
			return 0;
		}
	}
	@Override
	public AdminVO getAdmin(String id) {

		return adminDao.getAdmin(id);
	}
	
	
	
	
	
	
	

}
