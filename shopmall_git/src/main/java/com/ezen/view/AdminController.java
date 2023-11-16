package com.ezen.view;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.biz.admin.AdminService;
import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.dto.SalesQuantity;
import com.ezen.biz.order.OrderService;
import com.ezen.biz.product.ProductService;
import com.ezen.biz.qna.QnaService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private QnaService qnaService;
	
	@GetMapping(value="/admin_login_form")
	public String adminLogiinView() {
		
		return "admin/main";
	}
	
	
	@PostMapping(value="/admin_login")
	public String adminLoginAction(AdminVO vo, Model model) {
		// (1) adminCheck 호출
		int result = adminService.adminCheck(vo);
		String message = "";

		//(2) 조회 결과가 1이면 "admin_product_list" 요청
		if(result == 1) {
			model.addAttribute("admin", adminService.getAdmin(vo.getId()));
			
			return "redirect:/admin_product_list";
		}else {
			if(result == 0) {
				//(3) 조회결과가 0또는 -1 이면 "비밀번호를 확인하세요",
				//		"아이디를 확인하세요" 를 message에 저장
				model.addAttribute("message", "비밀번호를 확인하세요.");
				
			}else if(result == -1) {
			model.addAttribute("message", "아이디를 확인하세요.");
			
			}
			return "admin/main";
		}
	}
	
	/*
	 * 페이징 처리를 하지 않은 상품 목록
	 */
	/*
	@RequestMapping(value="/admin_product_list")
	public String adminProductList(Model model, @RequestParam(value="key", defaultValue="") String name) {
		List<ProductVO> prodList = productService.getlistProduct(name);
		
		model.addAttribute("productListSize", prodList.size());
		model.addAttribute("productList", prodList);
		
		return "admin/product/productList";
		
	}
	*/
	
	
	@RequestMapping(value= "/admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue="") String name,
									Model model, Criteria criteria) {
		// (1) 페이지별 상품 목록 조회
		List<ProductVO> prodList = productService.getListProductWithPaging(criteria, name);
		
		// (2) 화면에 표시할 페이지 버튼의 정보 설정
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria); 	// 화면에서 올라온 criteria 를 넣어준다
		// 총 게시글의 수 설정
		pageMaker.setTotalCount(productService.countProductList(name));
		
		model.addAttribute("productListSize", prodList.size());
		model.addAttribute("productList", prodList);
		model.addAttribute("pageMaker", pageMaker);	// 페이지 번호 표시용 데이터
		
		return "admin/product/productList";
		
	}
	
	/*
	 * 상품 등록 화면 출력
	 */
	@PostMapping(value="/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		String[] kindList = {"힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일"};

		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
		
	}
	
	/*
	 * 상품 등록 기능구현
	 */
	@PostMapping(value="/admin_product_write")
	public String adminProductWriteAction(ProductVO vo, HttpSession session,
											@RequestParam(value="product_image") MultipartFile uploadFile) {
		// 이미지 파일이 업로드 되었는지 확인
		if(!uploadFile.isEmpty()) { 	// 파일이 업로드 됨
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);	//Image 는 String 타입이어서 이렇게 넣어준다
		
			// 업로드 이미지 저장
			// getServletContext() - 프로젝트 관련 정보 리턴	배포된 프로젝트의 실제위치
			String imagePath = 
					session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
			System.out.println("imagePath=" + imagePath);
			
			// 이미지 데이터 이동	
			// transferTo 는 예외처리가 필요함
			try {
				uploadFile.transferTo(new File(imagePath + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// 입력한 상품 정보 저장
		productService.insertProduct(vo);
		
		return "redirect:admin_product_list";
	}
	
	
	/*
	 * 상품 상세조회
	 */
	@RequestMapping(value="/admin_product_detail")
	public String adminProductDetail(Criteria criteria, ProductVO vo, Model model) {
		String[] kindList = {"", "힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일"};
		ProductVO product = productService.getProduct(vo);	// 커맨드파라메타에는 pseq 가 저장돼있음 getProduct 의 리턴값은 vo의 값이됨
	
		model.addAttribute("productVO", product);
		int index = Integer.parseInt(product.getKind());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("criteria", criteria);
		
		return "admin/product/productDetail";
	}
	/*
	 * 상품 수정
	 */
	@PostMapping(value="/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = {"힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일"};	// 상품분류 리스트를 출력함
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
		
	}
	
	@PostMapping(value="/admin_product_update")
	public String adminProductUpdate(ProductVO vo, HttpSession session,
							@RequestParam(value="product_image") MultipartFile uploadFile,
							@RequestParam(value="nonmakeImg") String org_image) {
		// 이미지 파일이 업로드 되었는지 확인
		if(!uploadFile.isEmpty()) { 	// 파일이 업로드 됨
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);	//Image 는 String 타입이어서 이렇게 넣어준다
		
			// 업로드 이미지 저장
			// getServletContext() - 프로젝트 관련 정보 리턴	배포된 프로젝트의 실제위치
			String imagePath = 
					session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
			System.out.println("imagePath=" + imagePath);
			
			// 이미지 데이터 이동	
			// transferTo 는 예외처리가 필요함
			try {
				uploadFile.transferTo(new File(imagePath + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}else { //기존 이미지 사용
			vo.setImage(org_image);
		}
		// 베스트 상품, 신규상품 설정
		System.out.println("useyn= " + vo.getUseyn());
		System.out.println("bestyn= " + vo.getBestyn());
		if(vo.getUseyn() == null) {
			vo.setUseyn("n");
		}else {
			vo.setUseyn("y");
		}
		
		if(vo.getBestyn() == null) {
			vo.setBestyn("n");
		}else {
			vo.setBestyn("y");
		}
		
		productService.updateProduct(vo);
		
		return "redirect:admin_product_list";
	}
	
	@GetMapping(value="/admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete();	// 세션 해지
		
		return "admin/main";
	}
	/*
	 * 전체 주문 내역
	 */
	@GetMapping(value="/admin_order_list")
	public String adminOrderList(@RequestParam(value="key", defaultValue="")String mname,
								Criteria criteria, Model model) {
		
		List<OrderVO> orderList = orderService.getListOrderWithPaging(criteria, mname);
		
		// (2) 화면에 표시할 페이지 버튼의 정보 설정
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria); 	// 화면에서 올라온 criteria 를 넣어준다
		// 총 게시글의 수 설정
		pageMaker.setTotalCount(productService.countProductList(mname));
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("productList", orderList.size());
		model.addAttribute("pageMaker", pageMaker);	// 페이지 번호 표시용 데이터
		
		return "admin/order/orderList";
	}
	
	/*
	 * 주문 완료 처리(입금 확인)
	 */
	@GetMapping(value="/admin_order_save")
	public String adminOrderSave(@RequestParam(value="result") int[] odseq) {
		
		for(int i=0; i<odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		
		return "redirect:admin_order_list";
	}
	
	/*
	 * 전체 Qna 게시글 목록 조회 처리
	 */
	@GetMapping(value="admin_qna_list")
	public String adminQnaList(Model model) {
		List<QnaVO> qnaList = qnaService.getListAllQna();
		
		model.addAttribute("qnaList", qnaList);
		
		return "admin/qna/qnaList";
	}
	
	/*
	 * Qna 클릭 후 상세화면
	 */
	@PostMapping(value="/admin_qna_detail")
	public String admonQnaDetail(QnaVO vo, Model model) {
		QnaVO qna = qnaService.getQna(vo.getQseq());
		
		model.addAttribute("qnaVO", qna);
		
		return "admin/qna/qnaDetail";
	}
	/*
	 * QnA 게시글 답변 작성 처리
	 */
	@PostMapping(value="/admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo) {
		
		
		qnaService.updateQna(vo);
		
		return "redirect:admin_qna_list";
	}
	
	/*
	 * 차트를 표시할 JSP 화면 출력
	 */
	@GetMapping(value="/admin_sales_record_form")
	public String adminSalesRecordForm() {
		
		return "admin/order/salesRecords";
	}
	
	@GetMapping(value="/sales_record_chart")
	@ResponseBody	// 화면 대신 데이터를 return 한다는 뜻
	public List<SalesQuantity> salesRecordChart(){
		List<SalesQuantity> listSales = orderService.getProductSales();
		
		return listSales;
	}
	
	
	
	
	
	
	
	
}
