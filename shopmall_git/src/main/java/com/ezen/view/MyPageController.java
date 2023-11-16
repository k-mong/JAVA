package com.ezen.view;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.mypage.CartService;
import com.ezen.biz.order.OrderService;

@Controller
public class MyPageController {

	public static final String NOT_PAYED = "1";	// 금액이 지불 되지 않았을때
	public static final String PAYED = "2";	// 금액이 지불됐을 때
	
	@Autowired	//의존성 주입
	private CartService cartService;
	@Autowired	// 객체를 사용할때 의존성 주입 해야함
	private OrderService orderService;	
	
	// insertcart 에대한 메소드 구현
	@PostMapping(value = "/cart_insert")
	public String insertCart(CartVO vo, HttpSession session) {
		// 로그인이 됐을때 장바구니 담기를 사용 할 수 있음
		// 사용자가 로그인 되어있는지 확인 : 세션객체에 loginUser가 저장되었있는지 확인 HttpSession session 를 넣어준다
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			return "redirect:/cart_list";
		}
	}
	
	@GetMapping(value="/cart_list")
	public String listCart(HttpSession session, Model model) {		// 조회된 내용을 표시하기위해Model model 객체 필요
		// 사용자가 로그인 되어있는지 확인 : 세션객체에 loginUser가 저장되었있는지 확인 HttpSession session 를 넣어준다
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
			
		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {
			List<CartVO> cartList = cartService.getListCart(loginUser.getId());
			
			// 장바구니 총액 계산
			int totalAmount = 0;
			for(CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/cartList";
		}
	}
	
	@PostMapping(value="cart_delete")
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) {	// cseq 에서 들어온 것을 int[]cseq 배열에 넣어.
		for(int i=0; i<cseq.length; i++) {
			System.out.print("삭제할 cart 번호:" + cseq[i] + " ");
			cartService.deleteCart(cseq[i]);
		}
		
		return "redirect:cart_list";
	}
	
	
	/*
	 * 장바구니 내역 주문 처리
	 * RedirectAttributes reAttr = redirect url 요청시 전송할 데이터 저장하는 객체
	 */
	@PostMapping(value="order_insert") 
	public String orderInsert(HttpSession session, OrderVO order, RedirectAttributes reAttr) {
		// (1) 로그인 되어있는지 확인
		// 사용자가 로그인 되어있는지 확인 : 세션객체에 loginUser가 저장되었있는지 확인 HttpSession session 를 넣어준다
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
					
		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {
			// (2) 장바구니(cart) 테이블을 읽어 orders, order_detail 테이블에 주문 생성
			order.setId(loginUser.getId());
			
			int oseq = orderService.insertOrder(order);
		
			// (3) 주문번호와 함께 주문 목록 표시 요청
			// addAttribute() 는 새로고침을 해도 유지되는 데이터 전송에 사용
			// addFalshAttribute()는 새로고침을 하면 데이터가 유지되지 않음.
			reAttr.addAttribute("oseq", oseq);	//get방식으로 요청됨
			
			// 주문내역을 조회할 url 요청
			return "redirect:/order_list";	//get방식
		}
	}
	
	/*
	 * 주문목록
	 */
	@GetMapping(value="/order_list")
	public String orderListView(OrderVO vo, Model model, HttpSession session) {
		// (1) 로그인 되어있는지 확인
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {
		// (2) 장바구니에서 주문 처리한 내역 조회
			//insert_order에서 전달된 oseq는 이미 command 객체에 저장되어 있음.
			vo.setId(loginUser.getId());
			vo.setResult(NOT_PAYED);	// 다른사람 또는 나중에 봤을때 "1" 을보면 모를 수 있어 static final 로 선언함

			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
		
		// (3) 주문 총액 계산
		int totalAmount = 0;
		for(OrderVO order : orderList) {
			totalAmount += order.getQuantity() * order.getPrice2();
		}
		
		// (4) 주문내역 화면 표시
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/orderList";	// mypage 안에 orderList 로 return gownj
		}
	}
	
	/*
	 * 진행중인 주문내역 요청 처리
	 */
	@GetMapping(value="/mypage")
	public String myPageView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {
			// (1) 진행중인 주문 번호 목록 조회
			vo.setId(loginUser.getId());
			vo.setResult(NOT_PAYED);
			List<Integer> oseqList = orderService.getSeqOrdering(vo);
			
			// (2) 위의 각 주문번호에 대하여 주문정보 조회하고
			// 요약정보 생성
			List<OrderVO> summaryList = new ArrayList<>();	// 주문요약 목록 저장
			for(int oseq : oseqList) {
				// (2-1) 각 주문번호에 대한 주문내역 조회
				OrderVO order = new OrderVO();
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult(NOT_PAYED);
				
				List<OrderVO> orderList = orderService.getListOrderById(order);
				
				// (2-2) 주문요약 정보 생성 - 화면에서 주문을 한 줄로 표시하기 위함
				OrderVO summary = new OrderVO();	// 한 건의 주문 요약정보 저장
				summary.setOseq(orderList.get(0).getOseq());	// 주문번호가 같은 것들 중에 첫 번째 주문 정보 를 summary 에 저장
				summary.setIndate(orderList.get(0).getIndate());
				// 상품명 요약 생성 2건 이상일 때 외?건 출력을 위해
				if(orderList.size() >= 2) {
					summary.setPname(orderList.get(0).getPname() + "외"
							+ (orderList.size()-1) + "건");
				}else {
					summary.setPname(orderList.get(0).getPname());
				}
				
				// (2-3) 주문별 합계금액 계산
				int amount = 0;	// 주문 합계금액 저장 변수
				for(OrderVO item : orderList) {
					amount += item.getQuantity() * item.getPrice2();
				}
				summary.setPrice2(amount);	// 합계금액 저장
				
				// 각 주문요약 정보를 요약 리스트에 추라
				summaryList.add(summary);
			}
			
			// (3) 화면에 전송할 데이터 저장 및 화면 표시
			model.addAttribute("orderList", summaryList);
			model.addAttribute("title", "진행중인 주문내역");
		}
		
		return"mypage/mypage";
	}
	
	/*
	 * 주문 상세 조회
	 */
	@GetMapping(value="order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {
			// (1) 주문번호를 조건으로 주문 목록 조회
			vo.setId(loginUser.getId());	// 화면에 입력한 정보를 넣어 줘야 됨
			vo.setResult("");	// 주문처리결과 : 미처리, 처리 모두 조회
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
			
			// (2) 주문자정보 생성
			OrderVO orderDetail = new OrderVO();
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setMname(orderList.get(0).getMname());
			
			// (3) 주문총액 계산
			int totalAmount = 0;	// 주문 합계금액 저장 변수
			for(int i=0; i<orderList.size(); i++) {
				totalAmount += orderList.get(i).getQuantity() * orderList.get(i).getPrice2();
				// totalAmount 는 orderList 안에 i 의 수량 x orderList 안에 i 의 금액
			}
			
			// (4) 화면표시 및 표시 할 정보 전달
			model.addAttribute("title", "My Page(주문 상세 정보)");
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", totalAmount);
			model.addAttribute("orderList", orderList);
			
			return "mypage/orderDetail";
		}
	}
	
	/*
	 * 총 주문 내역 처리(처리, 미처리 모두 포함)
	 */
	@GetMapping("/order_all")
	public String orderAllView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");

		if(loginUser == null) {	// 로그인 안된경우
			return "member/login";	// 로그인 화면 표시
		}else {

			// (1) 전체 주문번호 목록 조회
			vo.setId(loginUser.getId());	// 조회할 유저의 id 를 갖고온다
			vo.setResult("");	// 모두 조회하기 위한 ""
			List<Integer> oseqList = orderService.getSeqOrdering(vo);
			
			// (2) 위의 주문번호 목록 각각에 대하여 주문목록 조회
			List<OrderVO> summaryList = new ArrayList<>();
			for(int oseq : oseqList) {
				OrderVO order = new OrderVO();
				order.setOseq(oseq);
				order.setId(loginUser.getId());
				order.setResult("");
				
				List<OrderVO> orderList = orderService.getListOrderById(order);
				
				// 요약정보 생성
				OrderVO summary = new OrderVO();
				summary.setIndate(orderList.get(0).getIndate());
				summary.setOseq(orderList.get(0).getOseq());
				
				// 상품명 요약정보
				if(orderList.size() >= 2) {
					summary.setPname(orderList.get(0).getPname() + "외"
							+ (orderList.size()-1) + "건");
				}else {
					summary.setPname(orderList.get(0).getPname());
				}
				
				// 주문별 합계금액 계산
				int amount = 0;	// 주문 합계금액 저장 변수
				for(OrderVO item : orderList) {
					amount += item.getQuantity() * item.getPrice2();
				}
				summary.setPrice2(amount);	// 합계금액 저장
				
				// 주문 요약정보를 요약리스트에 추가
				summaryList.add(summary);
			}/* End of for */
			
			// 화면에 데이터 전송
			model.addAttribute("title", "총 주문내역");
			model.addAttribute("orderList", summaryList);
			
			return "mypage/mypage";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
