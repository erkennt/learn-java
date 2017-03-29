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
				<form:form action="/CardGame/regist/temp" method="POST"
					modelAttribute="UM">
					<tr>
						<th colspan=2>入力内容</th>
					</tr>
					<tr>
						<th>ログインID</th>
						<td>${UM.getUserId()}</td>
					</tr>
					<tr>
						<th>Password</th>
						<td>${UM.getPassword()}</td>
					</tr>
					<tr>
						<th>ニックネーム</th>
						<td>${UM.getNickName()}</td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td>${UM.getMailAddress()}</td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>${UM.getYears()}年${UM.getMonth()}月${UM.getDays()}日</td>
					</tr>
					<tr>
						<th colspan=2><input type="submit" value="登録" /></th>

					</tr>
				</form:form>
			</table>
			<form:form action="/CardGame/regist" method="POST"
				modelAttribute="UM">
				<input type="submit" value="キャンセル" />
			</form:form>
		</div>
	</div>
</body>
</html>