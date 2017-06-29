<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
<title>登録完了</title>
</head>
<body>

ようこそ${UM.getNickName() }さん！<br>

<br>
登録が完了しました。
トップページからログインしてください。

	<form:form action="/CardGame2" method="POST" modelAttribute="none">
	<button type="submit" class="btn btn-primary">トップページ</button>
	</form:form>

</body>
</html>