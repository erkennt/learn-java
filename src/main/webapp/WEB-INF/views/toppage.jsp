<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>CardGame</title>
</head>
<body>

	<form:form action="./login" method="POST" modelAttribute="UM">


		<table border="1" style="float: left;">
			<tr>
				<th colspan=5>ログイン</th>
			</tr>
			<tr>
				<th>ID</th>
				<td><form:input path="UserId" /></td>
				<th>PASS</th>
				<td><form:input path="Password" /></td>
				<td><input type="submit" value="ログイン" /></td>
			</tr>
		</table>
	</form:form>

	<form:form action="./test" method="POST" modelAttribute="UM">
		<table border="1" style="float: left;">
			<tr>
				<th>テストプレイ</th>
			</tr>
			<tr>
				<td><input type="submit" value="テストプレイ" /></td>
			</tr>
		</table>
	</form:form>


	<form:form action="./regist" method="POST" modelAttribute="UM">
		<table border="1">
			<tr>
				<th colspan=4>新規作成</th>
			</tr>
			<tr>
				<td><input type="submit" value="新規作成" /></td>
			</tr>
		</table>
	</form:form>
	<!-- ログインに失敗したときのメッセージ -->
	${msgR} ${support}

	<br>
	<h2>ゲーム状況</h2>
</body>
</html>