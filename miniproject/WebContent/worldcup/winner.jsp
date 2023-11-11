<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <link href="css/winner.css" rel="stylesheet">
</head>
<body>
 <div class="wrap">
    <div class="background">
    <div>
        <div class="title">우승자</div>
    </div>
        <div class="imgbox">
            <div class="imgwrap" onclick="location.href='webPage.jsp'" id="image1">
                <span class="name">${worldcupList[0].name}</span>
                <img src="images/${worldcupList[0].pictureurl}" alt="image1">
                	<p style = 'color: red';>클릭하면 메인으로 돌아갑니다.</p>
            </div>
         </div>
      </div>
  </div>
</body>
</html>