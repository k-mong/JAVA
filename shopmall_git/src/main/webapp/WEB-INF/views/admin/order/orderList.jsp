<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<script type="text/javascript">
  function go_order_save() {
    var count = 0;
    /* checkbox에 체크된 항목이 있는지 확인 */
    if (document.frm.result.length == undefined) {
      if (document.frm.result.checked == true) {
        count++;
      }
    } else {	// 체크박스에 여러개가 체크됨 -> 배열로 묶임
      for ( var i = 0; i < document.frm.result.length; i++) {
        if (document.frm.result[i].checked == true) {
          count++;
        }
      }
    }// 여기까지 체크박스에 체크가 돼있는지 확인
    if (count == 0) {
      alert("주문처리할 항목을 선택해 주세요.");
    } else {
      document.frm.action = "admin_order_save";	// 체크가 돼있으면 url로 보낸다
      document.frm.submit();
    }
  }
  
  function go_search() {
	var theForm = document.frm;
	theForm.action =  "admin_order_list";
	theForm.submit();
  }
</script>
<article>
<h1>주문리스트</h1>
<form name="frm" method="get">
  <table style="float: right;">
    <tr>
      <td>주문자 이름 <input type="text" name="key"> 
      <input class="btn" type="button" value="검색" onclick="go_search()">
      </td>
    </tr>
  </table>
  <br>
  <table id="orderList">
  <tr>
    <th>주문번호(처리여부)</th><th>주문자</th><th>상품명</th><th>수량</th>
    <th>우편번호</th><th>배송지</th><th>전화</th><th>주문일</th>
  </tr>
  <c:forEach items="${orderList}" var="orderVO">
  <tr>
    <td>
      <c:choose>
        <c:when test='${orderVO.result=="1"}'>
        <span style="font-weight: bold; color: blue">${orderVO.odseq}</span>
        (<input type="checkbox" name="result" value="${orderVO.odseq}"> 미처리)
        </c:when>
        <c:otherwise>
          <span style="font-weight: bold; color: red">${orderVO.odseq}</span>
          (<input type="checkbox" checked="checked" disabled="disabled">처리완료)
        </c:otherwise>
      </c:choose>
    </td>
    <td>${orderVO.mname}</td> <td>${orderVO.pname}</td>
    <td>${orderVO.quantity}</td> <td>${orderVO.zip_num}</td>
    <td>${orderVO.address}</td>  <td>${orderVO.phone}</td>
    <td><fmt:formatDate value="${orderVO.indate}" /></td>
  </tr>
  </c:forEach>
  </table>
  <input type="button" class="btn" style="width: 200px"
      value="주문처리(입금확인)" onClick="go_order_save()">
</form>
<%@ include file="../order_page_area.jsp" %>
</article>
<%@ include file="../footer.jsp"%>
</body>
</html>