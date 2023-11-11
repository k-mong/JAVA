<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap" rel="stylesheet">

	<link href="css/webPage.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="wrap">
		<div class="intro_bg">
			<div class="header">
				<div class="header_in">
					<div class="searchArea">
						<form>
							<input type="search" placeholder="Search">
							<span>검색</span>
						</form>
					</div>
					<div class="listArea">
					<ul class="nav">
						<li><a href="#">HOME</a></li>
						<li><a href="worldcup/insertForm.jsp">나만의 월드컵</a></li>
						<li><a href="#">게시판</a></li>
						<li><a class="atag" onclick="location.href='worldcup/loginForm.jsp'">Login</a></li>
					</ul>
					
				</div>
				</div>
			</div>
			
			<div class="intro_text">
			<h1>이상형 월드컵 사이트</h1>
			<h4>5팀(곽기민, 김홍준, 오규한)</h4>
			</div>
		</div>
	</div>
	
	<div class="card">
        <img src="images/worldcup1.jpg" alt="이미지 1" onclick="location.href='./main.jsp'">
        <h2>배우 월드컵</h2>
        <p>내가 생각하는 1등 배우는?</p>
    </div>

    <div class="card">
    	 <img src="images/drama.jpg" alt="이미지 1" onclick="location.href='./drama.jsp'">
        <h2>드라마 월드컵</h2>
        <p>내가 생각하는 1등 드라마는?</p>
    </div>

    <div class="card">
        <img src="이미지3.jpg" alt="이미지 3">
        <h2>카드 제목 3</h2>
        <p>이미지 카드 설명 3</p>
    </div>
	
</body>
</html>