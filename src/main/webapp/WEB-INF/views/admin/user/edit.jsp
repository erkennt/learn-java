<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<c:set var="pageSizeList" value="$[5, 20, 50]" />

<html>
<head>
<title>ユーザー検索</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />

<script type="text/javascript">
<!--
	function updatePassword() {
		var f = document.getElementById("Users");
		var g = document.getElementById("pass");
		f.action = g.action;
		f.submit();
	}
	function updateStatus() {
		var f = document.getElementById("Users");
		var g = document.getElementById("status");
		f.action = g.action;
		f.submit();
	}

	-->
</script>

</head>
<body>

	<form id="pass"
		action="/CardGame2/admin/user/edit/${userId}/ChangePassword"
		method="POST"></form>

	<form id="status"
		action="/CardGame2/admin/user/edit/${userId}/UpdateStatus"
		method="POST"></form>

	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 60%;">
			<div>${up_msg}</div>
			<table class="table table-bordered"
				style="table-layout: fixed; text-align: center">
				<form:form action="/CardGame2/admin/user/edit/${userId}/update"
					method="POST" modelAttribute="Users">
					<tr>
						<th colspan=2 style="text-align: center">詳細</th>
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
						<th><form:label path="Birthday"
								value="${Users.getBirthday()}">誕生日</form:label></th>
						<td><div>
								<c:if test="${!empty Years}">
									<div>
										<form:select path="Years">
											<form:option value="">--</form:option>
											<form:options path="${Users.getYears()}" items="${Years}" />
										</form:select>
										<form:select path="Month">
											<form:option value="">--</form:option>
											<form:options path="${Users.getMonth()}" items="${Month}" />
										</form:select>
										<form:select path="Days">
											<form:option value="">--</form:option>
											<form:options path="${Users.getDays()}" items="${Days}" />
										</form:select>
									</div>
									<div>
										<div>
											<form:errors path="Years" cssStyle="color:red" />
										</div>
										<div>
											<form:errors path="Month" cssStyle="color:red" />
										</div>
										<div>
											<form:errors path="Days" cssStyle="color:red" />
										</div>

									</div>

								</c:if>
							</div>
							<div>
								<form:errors path="Birthday" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th><form:label path="Asset" value="${Users.getAsset()}">資産</form:label></th>
						<td><div>
								<form:input path="Asset" />
							</div>
							<div>
								<form:errors path="Asset" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th><form:label path="Status" value="${Users.getStatus()}">ステータス</form:label></th>
						<td><div>
								<p>${statusMap.get(Users.getStatus())}</p>
								<form:hidden path="Status" />
								<p>
									<c:choose>
										<c:when test="${Users.getStatus() == '0'}">
											<button type="button" class="btn btn-success"
												onClick="updateStatus()">認証する</button>
										</c:when>
										<c:when test="${Users.getStatus() == '1'}">
											<button type="button" class="btn btn-danger"
												onClick="updateStatus()">アカウント停止</button>
										</c:when>
										<c:when test="${Users.getStatus() == '2'}">
											<button type="button" class="btn btn-info"
												onClick="updateStatus()">アカウント復旧</button>
										</c:when>
									</c:choose>
								</p>
							</div>
							<div>
								<form:errors path="Status" cssStyle="color:red" />
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