<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <script>
	function imgChoice(choiceNum){
		var theForm =  document.getElementById("worldcupForm");
		var not_choice = document.getElementById("not_choice");
		
		if (choiceNum == 1) {
			not_choice.value = ${worldcupList[1].num};
		} else {
			not_choice.value = ${worldcupList[0].num};
		}
		theForm.action = "deleteData.do";
		theForm.submit();
	}
	</script>

    <link href="css/world.css" rel="stylesheet">
</head>
<body>
	<form method="post" action="deleteData.do" id="worldcupForm">
	
    <div class="wrap">
    <div class="background">
    <div>
        <div class="title">이상형 월드컵</div>
        <div>
    
        </div>
    </div>
        <div class="imgbox">
       
            <div class="imgwrap" onclick="imgChoice(1)" id="image1">
                <span class="name">${worldcupList[0].name}</span>
                <img src="images/${worldcupList[0].pictureurl}" alt="image1">
            </div>
            <div class="imgwrap" onclick="imgChoice(2)" id="image2">
                <span class="name">${worldcupList[1].name}</span>
                <img src="images/${worldcupList[1].pictureurl}" alt="image1">
            </div>
            <div class="vs">vs</div>
        </div>
    </div>
</div>
	<input type="hidden" name="not_choice" id="not_choice" value="0">
	
</form>
</body>
</html>