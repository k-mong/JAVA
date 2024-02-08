package com.ezen.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.member.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	/*
	 * �α��� ȭ�� ǥ��
	 */
	@GetMapping(value="/login_form")
	public String loginView() {
		
		return "member/login";
	}
	
	
	/*
	 * ����� ����(�α���):
	 * 			login.jsp ���� id, pwd�� �о ����� ����
	 */
	@PostMapping(value="/login")
	public String loginAction(MemberVO vo, Model model) {
		MemberVO loginUser = null;
		int result = memberService.loginID(vo);
		
		if(result == 1) {	// ����� ���� ����
			// (1) ����� ���� ��ȸ
			loginUser = memberService.getMember(vo.getId());
			
			// (2) model�� ���� ��ü�� ����� ���� ���� (�𵨿� �����ϸ� ���ǿ� �����ϴ� @SessionAttributes)
			model.addAttribute("loginUser", loginUser);
			
			// url�� ��û�ϴ� redirect: �α��ο� ���������� ������������
			return "redirect:/index";
			
		}else {		// ����� ���� ����
			// �α��� �����ϸ� login_fail.jsp ������ ������ �ش�
			return "member/login_fail";
		}
	}
	
	// @GetMapping(value="������ ��")
	@GetMapping(value="/logout")
	public String logoutAction(SessionStatus status) {
		// session.invalidate(); // �α��� ���� �̸��� �������� ��� �Ұ�
		status.setComplete();	// ���� ���� ����
		
		return "member/login";
	}
	
	@GetMapping(value="/contract")
	public String contractView() {
		
		return "member/contract";
	}
	
	@PostMapping(value="/join_form")
	public String joinView() {
		
		return "member/join";
	}
	
	@GetMapping(value="/id_check_form")
	public String idCheckView(MemberVO vo, Model model) {
		
		// 1: id ����, -1: id ����
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
	}
	
	@PostMapping(value="/id_check_form")
	public String idCheckAction(MemberVO vo, Model model) {
		
		// 1: id ����, -1: id ����
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
	}
	
	@PostMapping(value="/join")
	public String joinAction(MemberVO vo,
			@RequestParam(value="addr1") String addr1,
			@RequestParam(value="addr2") String addr2) {
		System.out.println("ȸ�� ����: " + vo);

		vo.setAddress(addr1 + " " + addr2);
		
		memberService.insertMember(vo);
		
		return "member/login";
	}
	
	@GetMapping(value="find_zip_num")
	public String findZipNum(){
		
		return "member/findZipNum";
	}
	
	@PostMapping(value="find_zip_num")
	public String findZipNumAction(AddressVO vo, Model model) {
		
		List<AddressVO> addressList = memberService.selectAddressByDong(vo.getDong());
		
		model.addAttribute("addressList", addressList);
		
		return "member/findZipNum";
	}
	
	/*
	 * ID, ��й�ȣ ã�� ȭ�� ���
	 * member.js ���� ���� find_id_form �� member/findIdAndPassword ȭ���� �������
	 */
	@GetMapping(value="/find_id_form")
	public String findIdFormView() {
		
		return "member/findIdAndPassword";
	}
	
	/*
	 * ���̵� ã�� ó��
	 */
	@PostMapping(value="/find_id")
	public String findIdAction(MemberVO vo, Model model) {
		
		String id = memberService.getIdByNameAndEmail(vo);
		
		if(id != null) {	// ���̵� ��ȸ ����
			model.addAttribute("message", 1);
			model.addAttribute("id", id);
		}else {
			model.addAttribute("message", -1);
		}
		
		return "member/findResult"; 
	}
	
	/*
	 * ��й�ȣ ã�� ó��
	 */
	@PostMapping(value="/find_pwd")
	public String findPwdAction(MemberVO vo, Model model) {
		
		String pwd = memberService.getPwdByIdNameEmail(vo);
		
		if(pwd != null) {
			model.addAttribute("message", 1);
			model.addAttribute("id", vo.getId());
		}else {
			model.addAttribute("message", -1);
		}
		
		return "member/findPwdResult";
	}
	
	/*
	 * ��й�ȣ ����
	 */
	@PostMapping(value="/change_pwd")
	public String changePwdAction(MemberVO vo) {
		
		memberService.changePassword(vo);
		
		return "member/changePwdOk";
	}
	
	/*
	 * ȸ�� ��ü��� ��ȸ
	 */
	@RequestMapping(value="/admin_member_list")
	public String adminMemberList(@RequestParam(value="key", defaultValue="") String mname, Model model) {
		
		List<MemberVO> memberList = memberService.getListMember(mname);
		
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberList";
		
	}
	
	
	
	
	
	
	
}















