<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="java.util.*"%>
<%@ page import="model.CardsModel"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>ドーナツゲーム</title>
</head>
<body>
	<div align="center">
		<table border="1"
			style="border-spacing: 0; border-collapse: collapse; width: 99%;">
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
							次の人がカードを引くまでお待ちください
							</c:when>
							<c:when test="${DrawState == 1}">
								<form:form action="/CardGame/game/donuts/draw" method="POST"
									modelAttribute="Game">
									<form:hidden path="GameType" />
									<form:hidden path="WaitMinutes" />
									<form:hidden path="CurrentCardId" />
									<form:hidden path="LogCounts" />
									<input type="submit" value="カードを引く" />
								</form:form>
							</c:when>
							<c:when test="${DrawState == 2}">
							カードを引くまであと・・・
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
						<table border="1"
							style="border-spacing: 0; border-collapse: collapse; width: 99%;">
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
		<br> <br>
		<table border="1"
			style="border-spacing: 0; border-collapse: collapse; width: 99%;">
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
		<form:form action="/CardGame/menu" method="POST">
			<input type="submit" value="戻る" />
		</form:form>

	</div>
</body>
</html>