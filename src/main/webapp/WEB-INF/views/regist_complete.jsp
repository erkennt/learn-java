<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>登録完了</title>
</head>
<body>

ようこそ${UM.getNickName() }さん！<br>

<br>
登録が完了しました。
いますぐ始めますか？<br>
	<br>
	<form:form action="/CardGame/login" method="POST" modelAttribute="UM">
		<form:hidden path="UserId" />
		<form:hidden path="Password" />
		<input type="submit" value="開始">
	</form:form>

	<form:form action="/CardGame" method="POST" modelAttribute="none">
		<input type="submit" value="キャンセル" />
	</form:form>

</body>
</html>