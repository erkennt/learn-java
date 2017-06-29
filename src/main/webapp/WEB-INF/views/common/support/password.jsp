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
<title>ユーザーサポート Password確認</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>Passwordの確認</h2>
			<p>${error_msg}</p>
			<form:form action="/CardGame2/support/password/confirm" method="POST"
				modelAttribute="Support">
				<div class="form-group">
					<div class="form-group">
						<label for="id" class="sr-only">ユーザーID</label>
						<form:input path="UserId" class="form-control"
							placeholder="登録時のID を入力して下さい" />
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">メールアドレス</label>
						<form:input path="MailAddress" class="form-control"
							placeholder="登録時のメールアドレス を入力して下さい" />
					</div>
				</div>
				<button id="btn-login" class="btn btn-primary btn-block">確認する</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>
			<br>
			<div>
				<c:url var="url" value="/support" />
				<form action="${url}" method="get">
					<button type="submit" class="btn btn-primary" value="back">戻る</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>