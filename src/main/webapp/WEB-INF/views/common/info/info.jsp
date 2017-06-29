<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<c:set var="pageSizeList" value="$[5, 20, 50]" />

<html>
<head>
<title>登録情報の変更</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />

<script type="text/javascript">
<!--
	function updateNickkName() {
		var f = document.getElementById("Users");
		var g = document.getElementById("name");
		f.action = g.action;
		f.submit();
	}
function updatePassword() {
	var f = document.getElementById("Users");
	var g = document.getElementById("pass");
	f.action = g.action;
	f.submit();
}

function updateMailAddress() {
	var f = document.getElementById("Users");
	var g = document.getElementById("email");
	f.action = g.action;
	f.submit();
}
	-->
</script>

</head>
<body>

	<form id="name" action="/CardGame2/common/info/name"
		method="POST"></form>
	<form id="pass" action="/CardGame2/common/info/pass"
		method="POST"></form>
	<form id="email" action="/CardGame2/common/info/mail"
		method="POST"></form>

	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 60%;">
			<div>${up_msg}</div>
			<table class="table table-bordered"
				style="table-layout: fixed; text-align: center">
				<form:form action="/CardGame2/common/info/update"
					method="POST" modelAttribute="Users">
					<tr>
						<th colspan=2 style="text-align: center">登録情報の変更</th>
					</tr>
					<tr>
						<th><form:label path="UserId" value="${Users.getUserId()}">ログインID</form:label></th>
						<td>
							<div>${Users.getUserId()}</div>

							<div>
								<form:errors path="UserId" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="NickName"
								value="${Users.getNickName()}">ニックネーム</form:label></th>
						<td>
							<div><p>
								${Users.getNickName()}</p>
<p><button type="button" class="btn btn-primary"
										onClick="updateNickkName()">ニックネームの変更</button></p>

							</div>
							<div>
								<form:errors path="NickName" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="Password"
								value="${Users.getPassword()}">パスワード</form:label></th>
						<td>
							<div>
								<p>**************</p>
								<p>

									<button type="button" class="btn btn-primary"
										onClick="updatePassword()">パスワードの変更</button>
								</p>
							</div>
							<div>
								<form:errors path="NickName" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="Birthday"
								value="${Users.getBirthday()}">誕生日</form:label></th>
						<td>
							<div>
								<c:set var="birthday1"
									value="${ fn:split(Users.getBirthday(),' ') }" />
									${birthday1[0] }
							</div>
							<div>
								<form:errors path="UserId" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="MailAddress"
								value="${Users.getMailAddress()}">メールアドレス</form:label></th>
						<td><div>
						<p>${Users.getMailAddress()}</p>
						<p>
									<button type="button" class="btn btn-primary"
										onClick="updateMailAddress()">メールアドレスの変更</button></p>
							</div>
							<div>
								<form:errors path="MailAddress" cssStyle="color:red" />
							</div></td>
					</tr>
				</form:form>
			</table>
			<form:form action="/CardGame2/common/game" method="get">
				<button type="submit" class="btn btn-default">戻る</button>
			</form:form>
		</div>
	</div>
</body>
</html>