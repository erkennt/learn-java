<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>ユーザー登録確認</title>
</head>
<body>

	<div style="width: auto; margin: 0 0;">
		<div align="center">
			<div>入力した情報に誤りが無いか確認してください</div>
			<table border="1"
				style="border-spacing: 0; border-collapse: collapse;">
				<form:form action="/CardGame/regist/complete" method="POST"
					modelAttribute="UM">
					<tr>
						<th colspan=2>入力内容</th>
					</tr>
					<tr>
						<th>ログインID</th>
						<td>${UM.getUserId()}<form:hidden path="UserId" /></td>
					</tr>
					<tr>
						<th>Password</th>
						<td>${UM.getPassword()}<form:hidden path="Password" /></td>
					</tr>
					<tr>
						<th>ニックネーム</th>
						<td>${UM.getNickName()}<form:hidden path="NickName" /></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td>${UM.getMailAddress()}<form:hidden path="MailAddress" /></td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>${UM.getBirthday()}<%-- <form:hidden path="Birthday"/> --%></td>
					</tr>
					<tr>
						<th colspan=2><input type="submit" value="登録" /></th>

					</tr>
				</form:form>
			</table>
			<form:form action="/CardGame/regist" method="POST"
				modelAttribute="UM">
				<form:hidden path="UserId" />
				<form:hidden path="Password" />
				<form:hidden path="NickName" />
				<form:hidden path="MailAddress" />
				<%-- <form:hidden path="Birthday" /> --%>
				<input type="submit" value="キャンセル" />
			</form:form>
		</div>
	</div>
</body>
</html>