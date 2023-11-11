<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
	<h1>상품 등록 - 관리자 페이지</h1>
	<form method="post" enctype="multipart/form-data" action="makeWorld.do">
	<table>
		<tr>
			<th>이름</th>
			<td> <input type="text" name="name" id="name" size="50"> </td>
		</tr>
		<tr>
			<th>사 &nbsp;진</th>
			<td>
				<input type="file" name="pictureurl" id="prod_image"><br>
				(주의사항: 이미지를 변경하고자할 때만 선택하세요)
			</td>
		</tr>
	</table>
	<br>	
	<div id="buttons">
		<input type="submit" value="등록" onclick="return">
		<input type="reset" value="취소">
	</div>
	</form>

	<script type="text/javascript" src="script/product.js"></script>
</div>
</body>
</html>