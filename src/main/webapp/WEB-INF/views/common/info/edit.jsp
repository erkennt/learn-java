<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	function updatePassword() {
		var f = document.getElementById("Users");
		f.action = g.action;
		f.submit();
	}
	-->
</script>

</head>
<body>

	<form id="pass"
		action="/CardGame2/common/info/ChangePassword"
		method="POST"></form>

	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 60%;">
			<div>${up_msg}</div>
			<table class="table table-bordered"
				style="table-layout: fixed; text-align: center">
				<form:form action="/CardGame2/admin/user/edit/${userId}/update"
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
							<div>
								<form:input path="NickName" />
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
						<th><form:label path="MailAddress"
								value="${Users.getMailAddress()}">メールアドレス</form:label></th>
						<td><div>
								<form:input path="MailAddress" />
							</div>
							<div>
								<form:errors path="MailAddress" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th><form:label path="Asset" value="${Users.getAsset()}">資産</form:label></th>
						<td><div>
								${Users.getAsset()}
							</div>
							<div>
								<form:errors path="Asset" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th colspan=2 style="text-align: center">
							<button type="submit" class="btn btn-primary">更新</button>
						</th>
					</tr>
				</form:form>
			</table>
						<form:form action="/CardGame2/admin/user/search" method="get">
				<button type="submit" class="btn btn-default">戻る</button>
			</form:form>
		</div>
	</div>
</body>
</html>