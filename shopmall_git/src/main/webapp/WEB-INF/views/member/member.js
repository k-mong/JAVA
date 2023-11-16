/**
 *  회원처리 관련 자바스크립트
 */

/*
** 약관 동의 여부 확인
*/
function go_next() {
	var agree = document.getElementsByClassName("agree");
	var theForm = document.getElementById("join");
	
	if (agree[0].checked == true) {
		// 회원가입 화면 표시 요청
		theForm.action = "join_form";
		theForm.submit();	// Controller로 요청 전송
	} else if (agree[1].checked == true) {
		alert("약관에 동의해 주셔야 합니다.");
	}
}

/*
 * ID 중복 확인
 */
function idcheck() {
	var id = document.getElementById("id");
	
	// 아이디 입력창에 아이디 입력 확인
	if (id.value == "") {
		alert("아이디를 입력해 주세요!");
		id.focus();
		return false;
	}
	
	// id가 입력되었으면 id중복 확인 윈도우 오픈
	var url = "id_check_form?id=" + id.value;
	window.open(url, "_blank_", "toolbar=no, " +
			"menubar=no, scrollbars=no, resizable=yes, width=450, height=250");
}

/*
 * 회원 가입시, 필수입력 항목 확인
 */
function go_save() {
	var id = document.getElementById("id");
	var reid = document.getElementById("reid");
	var pwd = document.getElementById("pwd");
	var pwdCheck = document.getElementById("pwdCheck");
	var name = document.getElementById("name");
	var email = document.getElementById("email");
	
	if (id.value == "") {
		alert("아이디를 입력해 주세요.");
		id.focus();
		return false;
	} else if (id.value != reid.value) {
		alert("아이디 중복체크를 해주세요.")
		id.focus();
		return false;
	} else if (pwd.value == "") {
		alert("비밀번호를 입력해 주세요.");
		pwd.focus();
		return false;
	} else if (pwd.value != pwdCheck.value) {
		alert("비밀번호가 일치하지 않습니다.")
		pwd.focus();
		return false;
	} else if (name.value == "") {
		alert("이름을 입력해 주세요.");
		name.focus();
		return false;
	} else if (email.value == "") {
		alert("이메일을 입력해 주세요.");
		email.focus();
		return false;
	} else {
		// 회원가입 요청
		var theForm = document.getElementById("join");
		theForm.action = "join";
		theForm.submit();
	}
}

/*
 * 우편번호, 주소찾기 창 오픈
 */
function post_zip(){
	var url = "find_zip_num";
	
	window.open(url, "_blank_", "toolbar=no, " +
	"menubar=no, scrollbars=no, resizable=yes, width=500, height=350");
}

/*
 * 아이디 비밀번호 찾기 화면 요청
 * MemberController 의 findIdFormView 에서 처리함
 */
function find_id_form(){
	var url = "find_id_form";
	
	window.open(url, "_blank_", "toolbar=no, " +
	"menubar=no, scrollbars=no, resizable=yes, width=500, height=450");
}

/*
 * 아이디 찾기 요청
 */
function findMemberId(){
	var name = document.getElementById("name");
	var email = document.getElementById("email");
	
	if(name.value == ""){
		alert("이름을 입력해 주세요.");
		name.focus();
		return false;
	}else if(email.value == ""){
		alert("이메일을 입력해 주세요.");
		email.focus();
		return false;
	}else{
		var theForm = document.getElementById("findId");
		theForm.action = "find_id";
		theForm.submit();
	}
}
	
/*
 * 비밀번호 찾기 요청
 */
function findPassword(){
	var id = document.getElementById("id2");
	var name = document.getElementById("name2");
	var email = document.getElementById("email2");
	
	if(id.value == ""){
		alert("아이디를 입력해 주세요.");
		id.focus();
		return false;
	}else if(name.value == ""){
		alert("이름을 입력해 주세요.");
		name.focus();
		return false;
	}else if(email.value == ""){
		alert("이메일을 입력해 주세요.");
		email.focus();
		return false;
	}else{
		var theForm = document.getElementById("findPW");
		theForm.action = "find_pwd";
		theForm.submit();
	}
}

/*
 * 비밀번호 변경
 */
function changePassword(){
	var pwd = document.getElementById("pwd");
	var pwdCheck = document.getElementById("pwdcheck");
	
	if(pwd.value == ""){
		alert("비밀번호를 입력하세요");
		pwd.focus();
		
		return false;
	}else if(pwd.value != pwdCheck.value){
		alert("비밀번호가 맞지 않습니다. 다시 입력해 주세요");
		pwdCheck.focus();
		
		return false;
	}else{
		var theForm = document.getElementById("pwd_form");
		theForm.action = "change_pwd";
		theForm.submit();
	}
}



	



