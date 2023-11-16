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
	 * 로그인 화면 표시
	 */
	@GetMapping(value="/login_form")
	public String loginView() {
		
		return "member/login";
	}
	
	
	/*
	 * 사용자 인증(로그인):
	 * 			login.jsp 에서 id, pwd를 읽어서 사용자 인증
	 */
	@PostMapping(value="/login")
	public String loginAction(MemberVO vo, Model model) {
		MemberVO loginUser = null;
		int result = memberService.loginID(vo);
		
		if(result == 1) {	// 사용자 인증 성공
			// (1) 사용자 정보 조회
			loginUser = memberService.getMember(vo.getId());
			
			// (2) model과 세션 객체에 사용자 정보 저장 (모델에 저장하면 세션에 저장하는 @SessionAttributes)
			model.addAttribute("loginUser", loginUser);
			
			// url을 요청하는 redirect: 로그인에 성공했으로 메인페이지로
			return "redirect:/index";
			
		}else {		// 사용자 인증 실패
			// 로그인 실패하면 login_fail.jsp 파일을 실행해 준다
			return "member/login_fail";
		}
	}
	
	// @GetMapping(value="보내줄 곳")
	@GetMapping(value="/logout")
	public String logoutAction(SessionStatus status) {
		// session.invalidate(); // 로그인 유저 이름이 남아있음 사용 불가
		status.setComplete();	// 현제 세션 종료
		
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
		
		// 1: id 존재, -1: id 없음
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
	}
	
	@PostMapping(value="/id_check_form")
	public String idCheckAction(MemberVO vo, Model model) {
		
		// 1: id 존재, -1: id 없음
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
	}
	
	@PostMapping(value="/join")
	public String joinAction(MemberVO vo,
			@RequestParam(value="addr1") String addr1,
			@RequestParam(value="addr2") String addr2) {
		System.out.println("회원 가입: " + vo);

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
	 * ID, 비밀번호 찾기 화면 출력
	 * member.js 에서 받은 find_id_form 을 member/findIdAndPassword 화면을 출력해줌
	 */
	@GetMapping(value="/find_id_form")
	public String findIdFormView() {
		
		return "member/findIdAndPassword";
	}
	
	/*
	 * 아이디 찾기 처리
	 */
	@PostMapping(value="/find_id")
	public String findIdAction(MemberVO vo, Model model) {
		
		String id = memberService.getIdByNameAndEmail(vo);
		
		if(id != null) {	// 아이디 조회 성공
			model.addAttribute("message", 1);
			model.addAttribute("id", id);
		}else {
			model.addAttribute("message", -1);
		}
		
		return "member/findResult"; 
	}
	
	/*
	 * 비밀번호 찾기 처리
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
	 * 비밀번호 수정
	 */
	@PostMapping(value="/change_pwd")
	public String changePwdAction(MemberVO vo) {
		
		memberService.changePassword(vo);
		
		return "member/changePwdOk";
	}
	
	/*
	 * 회원 전체목록 조회
	 */
	@RequestMapping(value="/admin_member_list")
	public String adminMemberList(@RequestParam(value="key", defaultValue="") String mname, Model model) {
		
		List<MemberVO> memberList = memberService.getListMember(mname);
		
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberList";
		
	}
	
	
	
	
	
	
	
}
















