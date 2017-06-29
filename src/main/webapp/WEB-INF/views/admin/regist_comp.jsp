<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>管理者登録完了</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 99%;">
			<div>管理者ユーザーを登録しました。
			ログインしてゲームの設定をしてください。	</div>

<form:form
							action="/CardGame2/admin" method="POST"
							modelAttribute="Admin">
							<button type="submit" class="btn btn-primary">ログイン</button>

						</form:form>
		</div>
	</div>
</body>
</html>