<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>


<html>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<title>データの一括登録結果</title>
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<c:choose>
				<c:when test="${errorsMap.size() > 0}">
					<c:forEach var="key" items="${errorsMap.keySet()}"
						varStatus="status">
シート 「<b><c:out value="${key}" /></b>」 が登録に失敗した件数は <b><c:out
								value="${errorsMap.get(key).size()}" /> </b>件です。<br>
						<c:choose>
							<c:when test="${errorsMap.get(key).size() == 0}">全ての登録に成功しました。<br>
							</c:when>
							<c:when test="${errorsMap.get(key).size() >= 1}">
登録に失敗した行 : <br>
								<c:forEach var="row" items="${errorsMap.get(key)}"
									varStatus="status">
									<c:out value="${row}" />,
						</c:forEach>
								<br>
								<br>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
ファイルが正しくありません。<br>
選択したファイルに間違いがないか確認してください。
</c:otherwise>
			</c:choose>
			<c:url var="Url" value="/admin/bulk" />
			<form action="${Url}" method="get">
				<button type="submit" class="btn btn-primary">戻る</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>

</body>
</html>