<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="java.util.*"%>
<%@ page import="learn.second.domain.model.Cards"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>ドーナツゲーム</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/JavaScript">
function set2fig(num) {
   // 桁数が1桁だったら先頭に0を加えて2桁に調整する
   var ret;
   if( num < 10 ) { ret = "0" + num; }
   else { ret = num; }
   return ret;
}
function showClock2() {
   var abc = Date.parse(document.getElementById('NextDraw').value);
   var next = new Date(abc);
   var now = new Date();
   var diff = next.getTime() - now.getTime();
   var diffSec = 0
   if(diff > 0){
   diffSec = Math.floor(diff / (1000));
   }
   var nowTime = new Date();
   var nowHour = set2fig( nowTime.getHours() );
   var nowMin  = set2fig( nowTime.getMinutes() );
   var nowSec  = set2fig( nowTime.getSeconds());
   var msg = "待ち時間は" + diffSec + "秒です。";
   document.getElementById("waitTime").innerHTML = msg;
}
window.onload = function(){
	showClock2();	}
setInterval('showClock2()',1000);
</script>
</head>
<body>
<input type="hidden" name="NextDraw" value="${NextDraw}" id="NextDraw">
<p id="waitTime"></p>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 100%;">

			<table
				class="table table-bordered table-responsive"style="width: 99%;">
			<tr>
				<td width="40%" align="center">
					<div>
						<c:choose>
							<c:when test="${empty NewCard}">
								<img src="<c:url value='/resources/img/ura.gif' />">
							</c:when>
							<c:otherwise>
								<img
									src="<c:url value='/resources/img/${NewCard.getValue()}.gif' />">
							</c:otherwise>
						</c:choose>
					</div>
					<div>${resultMessage}</div> <br>
					<div>
						<c:choose>
							<c:when test="${DrawState == 0}">
								<form:form action="/CardGame2/common/game/donuts/draw" method="POST"
									modelAttribute="Game">
										<div class="form-group">
											<div class="form-inline">
									掛け金 :
									<form:select path="BetType" class="form-control">
										<form:options items="${BetTypeList}" />
									</form:select></div></div>
											<button type="submit" class="btn btn-primary">カードを引く</button>
								</form:form>
							</c:when>
							<c:when test="${DrawState == 1}">
							次の人がカードを引くまでお待ちください
							</c:when>
							<c:when test="${DrawState == 2}">
							待機中の為カードを引くことができません<br>
							しばらくお待ちください
							</c:when>
						</c:choose>
					</div>
					<div>
						<img
							src="<c:url value='/resources/img/${ CurrentCard.getValue() }.gif' />">
					</div>

					<div>前回のカードです</div>
				</td>
				<td style="margin: 1%; padding: 1%;">
					<div>＜これまでに引かれたカードです＞</div>
					<div>
							<table
				class="table table-bordered table-responsive"style="width: 99%;">
							<tr>
								<td width="99%" style="padding: 1%;">
									<div>
										<c:forEach var="cardList" items="${CardList}">
											<img
												src="<c:url value='/resources/img/${ cardList.getValue() }.gif' />">
										</c:forEach>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<br> <br><table
				class="table table-bordered table-responsive"style="width: 99%;">
			<tr>
				<td>
					<div>最近のゲーム内容</div>
					<div>
						<c:forEach var="logList" items="${GameLogsList}">
							<c:out value="${logList.getMessage()}" />
							<br>
						</c:forEach>
					</div>
				</td>
			</tr>

		</table>

		<br>
		<form:form action="/CardGame2/common/game" method="POST">
			<button type="submit" class="btn btn-primary" value="back">戻る</button>
		</form:form>

	</div>
	</div>
</body>
</html>