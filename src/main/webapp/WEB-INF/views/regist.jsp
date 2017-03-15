<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>ユーザー登録</title>
</head>
<body>


	<div style="width: auto; margin: 0 0;">
		<div align="center">
			<div>ユーザー情報を入力してください</div>
			<div>${msg}</div>
			<table border="1"
				style="border-spacing: 0; border-collapse: collapse;">
				<form:form action="/CardGame/regist/add" method="POST"
					modelAttribute="UM">
					<tr>
						<th colspan=2>新規登録</th>
					</tr>
					<tr>
						<th><form:label path="UserId" value="${UM.getUserId()}">ログインID</form:label></th>
						<td>
							<div>
								<form:input path="UserId" />
							</div>

							<div>半角英数字で2～10文字以内で入力してください</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="Password" value="${UM.getPassword()}">Password</form:label></th>
						<td><div>
								<form:input path="Password" />
							</div>

							<div>半角英数字で6～20文字以内で入力してください</div></td>
					</tr>
					<tr>
						<th><form:label path="NickName" value="${UM.getNickName()}">ニックネーム</form:label></th>
						<td>
							<div>
								<form:input path="NickName" />
							</div>

							<div>ゲーム内で使用する名前です</div>
							<div>2～10文字以内で入力してください</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="MailAddress"
								value="${UM.getMailAddress()}">メールアドレス</form:label></th>
						<td><div>
								<form:input path="MailAddress" />
							</div>

							<div>入力したアドレスに仮登録メールが送られます</div></td>
					</tr>
					<tr>
						<th><form:label path="Birthday" value="${UM.getBirthday()}">生年月日</form:label></th>
						<td>
							<div>
								準備中
								<%-- <form:input path="Birthday"/> --%>
							</div>

						</td>
					</tr>
					<tr>
						<th colspan=2><input type="submit" value="登録確認" /></th>

					</tr>
				</form:form>
			</table>
			<form:form action="/CardGame" method="POST" modelAttribute="none">
				<input type="submit" value="キャンセル" />
			</form:form>
		</div>
	</div>

</body>
</html>