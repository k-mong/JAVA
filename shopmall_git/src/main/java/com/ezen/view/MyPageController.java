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

	public static final String NOT_PAYED = "1";	// �ݾ��� ���� ���� �ʾ�����
	public static final String PAYED = "2";	// �ݾ��� ���ҵ��� ��
	
	@Autowired	//������ ����
	private CartService cartService;
	@Autowired	// ��ü�� ����Ҷ� ������ ���� �ؾ���
	private OrderService orderService;	
	
	// insertcart ������ �޼ҵ� ����
	@PostMapping(value = "/cart_insert")
	public String insertCart(CartVO vo, HttpSession session) {
		// �α����� ������ ��ٱ��� ��⸦ ��� �� �� ����
		// ����ڰ� �α��� �Ǿ��ִ��� Ȯ�� : ���ǰ�ü�� loginUser�� ����Ǿ��ִ��� Ȯ�� HttpSession session �� �־��ش�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			return "redirect:/cart_list";
		}
	}
	
	@GetMapping(value="/cart_list")
	public String listCart(HttpSession session, Model model) {		// ��ȸ�� ������ ǥ���ϱ�����Model model ��ü �ʿ�
		// ����ڰ� �α��� �Ǿ��ִ��� Ȯ�� : ���ǰ�ü�� loginUser�� ����Ǿ��ִ��� Ȯ�� HttpSession session �� �־��ش�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
			
		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {
			List<CartVO> cartList = cartService.getListCart(loginUser.getId());
			
			// ��ٱ��� �Ѿ� ���
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
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) {	// cseq ���� ���� ���� int[]cseq �迭�� �־�.
		for(int i=0; i<cseq.length; i++) {
			System.out.print("������ cart ��ȣ:" + cseq[i] + " ");
			cartService.deleteCart(cseq[i]);
		}
		
		return "redirect:cart_list";
	}
	
	
	/*
	 * ��ٱ��� ���� �ֹ� ó��
	 * RedirectAttributes reAttr = redirect url ��û�� ������ ������ �����ϴ� ��ü
	 */
	@PostMapping(value="order_insert") 
	public String orderInsert(HttpSession session, OrderVO order, RedirectAttributes reAttr) {
		// (1) �α��� �Ǿ��ִ��� Ȯ��
		// ����ڰ� �α��� �Ǿ��ִ��� Ȯ�� : ���ǰ�ü�� loginUser�� ����Ǿ��ִ��� Ȯ�� HttpSession session �� �־��ش�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
					
		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {
			// (2) ��ٱ���(cart) ���̺��� �о� orders, order_detail ���̺� �ֹ� ����
			order.setId(loginUser.getId());
			
			int oseq = orderService.insertOrder(order);
		
			// (3) �ֹ���ȣ�� �Բ� �ֹ� ��� ǥ�� ��û
			// addAttribute() �� ���ΰ�ħ�� �ص� �����Ǵ� ������ ���ۿ� ���
			// addFalshAttribute()�� ���ΰ�ħ�� �ϸ� �����Ͱ� �������� ����.
			reAttr.addAttribute("oseq", oseq);	//get������� ��û��
			
			// �ֹ������� ��ȸ�� url ��û
			return "redirect:/order_list";	//get���
		}
	}
	
	/*
	 * �ֹ����
	 */
	@GetMapping(value="/order_list")
	public String orderListView(OrderVO vo, Model model, HttpSession session) {
		// (1) �α��� �Ǿ��ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {
		// (2) ��ٱ��Ͽ��� �ֹ� ó���� ���� ��ȸ
			//insert_order���� ���޵� oseq�� �̹� command ��ü�� ����Ǿ� ����.
			vo.setId(loginUser.getId());
			vo.setResult(NOT_PAYED);	// �ٸ���� �Ǵ� ���߿� ������ "1" ������ �� �� �־� static final �� ������

			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
		
		// (3) �ֹ� �Ѿ� ���
		int totalAmount = 0;
		for(OrderVO order : orderList) {
			totalAmount += order.getQuantity() * order.getPrice2();
		}
		
		// (4) �ֹ����� ȭ�� ǥ��
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/orderList";	// mypage �ȿ� orderList �� return gownj
		}
	}
	
	/*
	 * �������� �ֹ����� ��û ó��
	 */
	@GetMapping(value="/mypage")
	public String myPageView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {
			// (1) �������� �ֹ� ��ȣ ��� ��ȸ
			vo.setId(loginUser.getId());
			vo.setResult(NOT_PAYED);
			List<Integer> oseqList = orderService.getSeqOrdering(vo);
			
			// (2) ���� �� �ֹ���ȣ�� ���Ͽ� �ֹ����� ��ȸ�ϰ�
			// ������� ����
			List<OrderVO> summaryList = new ArrayList<>();	// �ֹ���� ��� ����
			for(int oseq : oseqList) {
				// (2-1) �� �ֹ���ȣ�� ���� �ֹ����� ��ȸ
				OrderVO order = new OrderVO();
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult(NOT_PAYED);
				
				List<OrderVO> orderList = orderService.getListOrderById(order);
				
				// (2-2) �ֹ���� ���� ���� - ȭ�鿡�� �ֹ��� �� �ٷ� ǥ���ϱ� ����
				OrderVO summary = new OrderVO();	// �� ���� �ֹ� ������� ����
				summary.setOseq(orderList.get(0).getOseq());	// �ֹ���ȣ�� ���� �͵� �߿� ù ��° �ֹ� ���� �� summary �� ����
				summary.setIndate(orderList.get(0).getIndate());
				// ��ǰ�� ��� ���� 2�� �̻��� �� ��?�� ����� ����
				if(orderList.size() >= 2) {
					summary.setPname(orderList.get(0).getPname() + "��"
							+ (orderList.size()-1) + "��");
				}else {
					summary.setPname(orderList.get(0).getPname());
				}
				
				// (2-3) �ֹ��� �հ�ݾ� ���
				int amount = 0;	// �ֹ� �հ�ݾ� ���� ����
				for(OrderVO item : orderList) {
					amount += item.getQuantity() * item.getPrice2();
				}
				summary.setPrice2(amount);	// �հ�ݾ� ����
				
				// �� �ֹ���� ������ ��� ����Ʈ�� �߶�
				summaryList.add(summary);
			}
			
			// (3) ȭ�鿡 ������ ������ ���� �� ȭ�� ǥ��
			model.addAttribute("orderList", summaryList);
			model.addAttribute("title", "�������� �ֹ�����");
		}
		
		return"mypage/mypage";
	}
	
	/*
	 * �ֹ� �� ��ȸ
	 */
	@GetMapping(value="order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {
			// (1) �ֹ���ȣ�� �������� �ֹ� ��� ��ȸ
			vo.setId(loginUser.getId());	// ȭ�鿡 �Է��� ������ �־� ��� ��
			vo.setResult("");	// �ֹ�ó����� : ��ó��, ó�� ��� ��ȸ
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
			
			// (2) �ֹ������� ����
			OrderVO orderDetail = new OrderVO();
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setMname(orderList.get(0).getMname());
			
			// (3) �ֹ��Ѿ� ���
			int totalAmount = 0;	// �ֹ� �հ�ݾ� ���� ����
			for(int i=0; i<orderList.size(); i++) {
				totalAmount += orderList.get(i).getQuantity() * orderList.get(i).getPrice2();
				// totalAmount �� orderList �ȿ� i �� ���� x orderList �ȿ� i �� �ݾ�
			}
			
			// (4) ȭ��ǥ�� �� ǥ�� �� ���� ����
			model.addAttribute("title", "My Page(�ֹ� �� ����)");
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", totalAmount);
			model.addAttribute("orderList", orderList);
			
			return "mypage/orderDetail";
		}
	}
	
	/*
	 * �� �ֹ� ���� ó��(ó��, ��ó�� ��� ����)
	 */
	@GetMapping("/order_all")
	public String orderAllView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");

		if(loginUser == null) {	// �α��� �ȵȰ��
			return "member/login";	// �α��� ȭ�� ǥ��
		}else {

			// (1) ��ü �ֹ���ȣ ��� ��ȸ
			vo.setId(loginUser.getId());	// ��ȸ�� ������ id �� ����´�
			vo.setResult("");	// ��� ��ȸ�ϱ� ���� ""
			List<Integer> oseqList = orderService.getSeqOrdering(vo);
			
			// (2) ���� �ֹ���ȣ ��� ������ ���Ͽ� �ֹ���� ��ȸ
			List<OrderVO> summaryList = new ArrayList<>();
			for(int oseq : oseqList) {
				OrderVO order = new OrderVO();
				order.setOseq(oseq);
				order.setId(loginUser.getId());
				order.setResult("");
				
				List<OrderVO> orderList = orderService.getListOrderById(order);
				
				// ������� ����
				OrderVO summary = new OrderVO();
				summary.setIndate(orderList.get(0).getIndate());
				summary.setOseq(orderList.get(0).getOseq());
				
				// ��ǰ�� �������
				if(orderList.size() >= 2) {
					summary.setPname(orderList.get(0).getPname() + "��"
							+ (orderList.size()-1) + "��");
				}else {
					summary.setPname(orderList.get(0).getPname());
				}
				
				// �ֹ��� �հ�ݾ� ���
				int amount = 0;	// �ֹ� �հ�ݾ� ���� ����
				for(OrderVO item : orderList) {
					amount += item.getQuantity() * item.getPrice2();
				}
				summary.setPrice2(amount);	// �հ�ݾ� ����
				
				// �ֹ� ��������� ��ฮ��Ʈ�� �߰�
				summaryList.add(summary);
			}/* End of for */
			
			// ȭ�鿡 ������ ����
			model.addAttribute("title", "�� �ֹ�����");
			model.addAttribute("orderList", summaryList);
			
			return "mypage/mypage";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
