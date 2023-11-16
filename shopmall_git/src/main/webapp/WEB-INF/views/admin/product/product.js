/**
 * 
 */

/*
 * 상품명으로 상품목록 조회요청
 */
function go_search(){
	var theForm = document.getElementById("prod_form");
	
	theForm.action = "admin_product_list";
	theForm.submit();
}

/*
 * 상품 일련번호로 상품 상세정보 조회 요청
 */
function go_detail(pseq){
	var theForm = document.getElementById("prod_form");
	
	theForm.action = "admin_product_detail?pseq=" + pseq;
	theForm.submit();
}

/*
 * 상품 수정 화면 및 데이터 요청
 */
function go_mod(){
	var theForm = document.getElementById("detail_form");
	
	theForm.action = "admin_product_update_form";
	theForm.submit();
}

/*
 * 상품 등록화면 표시
 */
function go_wrt(){
	var theForm = document.getElementById("prod_form");
	
	theForm.action = "admin_product_write_form";
	theForm.submit();
}

/*
 * price3(순익) 필드 입력
 */
function go_ab(){
	// 판매가
	var price2 = document.getElementById("price2").value;
	// 원가
	var price1 = document.getElementById("price1").value;
	// 순익
	var price3 = price2 - price1;
	
	document.getElementById("price3").value = price3;
}

/*
 * 상품 등록 요청
 */
function go_save(){
	var theForm = document.getElementById("write_form");
	var kind = document.getElementById("kind");
	var name = document.getElementById("name");
	var price1 = document.getElementById("price1");
	var price2 = document.getElementById("price2");
	var price3 = document.getElementById("price3");
	var content = document.getElementById("content");
	
	if (kind.value == "") {
		alert("상품종류를 입력해 주세요!");
		kind.focus();
		return false;
	}else if(name.value == ""){
		alert("상품명을 입력해 주세요!");
		name.focus();
		return false;
	}else if(price1.value == ""){
		alert("상품원가를 입력해 주세요!");
		price1.focus();
		return false;
	}else if(price2.value == ""){
		alert("판매가를 입력해 주세요!");
		price2.focus();
		return false;
	}else if(price3.value == ""){
		alert("순수익값을 입력해 주세요!");
		price3.focus();
		return false;
	}else if(content.value == ""){
		alert("상품설명을 입력해 주세요!");
		content.focus();
		return false;
	}else{
		
		theForm.action = "admin_product_write";
		theForm.enctype = "multipart/form-data";
		theForm.submit();
	}
}
/*
 * 상품 리스트에서 "전체보기" 버튼처리
 */
function go_total(){
	var theForm = document.getElementById("prod_form");
	theForm.action = "admin_product_list";
	theForm.submit();
}
/*
 * 상품 상세보기 화면에서 "목록" 버튼처리
 */
function go_list(){
	var theForm = document.getElementById("detail_form");
	theForm.action = "admin_product_list";
	theForm.submit();
}


/*
 * 원가, 판매가 를 입력할 때 숫자만 입력하게 해줌
 */
function NumFormat(t){
	var s = t.value;
	s = s.replace(/\D/g, '');	// 숫자가 아닌경우 제거
	
	t.value = s;
	return t;
}

/*
 * 상품정보 수정 요청
 */
function go_mod_save(){
	var kind = document.getElementById("kind");
	var name = document.getElementById("name");
	var price1 = document.getElementById("price1");
	var price2 = document.getElementById("price2");
	var price3 = document.getElementById("price3");
	var content = document.getElementById("content");
	
	if (kind.value == "") {
		alert("상품종류를 입력해 주세요!");
		kind.focus();
		return false;
	}else if(name.value == ""){
		alert("상품명을 입력해 주세요!");
		name.focus();
		return false;
	}else if(price1.value == ""){
		alert("상품원가를 입력해 주세요!");
		price1.focus();
		return false;
	}else if(price2.value == ""){
		alert("판매가를 입력해 주세요!");
		price2.focus();
		return false;
	}else if(price3.value == ""){
		alert("순수익값을 입력해 주세요!");
		price3.focus();
		return false;
	}else if(content.value == ""){
		alert("상품설명을 입력해 주세요!");
		content.focus();
		return false;
	}else{
		var theForm = document.getElementById("update_form");
		theForm.enctype = "multipart/form-data";
		theForm.action = "admin_product_update";
		
		theForm.submit();
	}
}

 



