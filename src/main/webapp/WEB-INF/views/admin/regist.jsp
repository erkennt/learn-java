<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>管理ユーザー登録</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 30%;">
			<div>${up_msg}</div>
			<table class="table table-bordered"
				style="table-layout: fixed; text-align: center">
				<form:form action="/CardGame2/admin_regist_add" method="POST"
					modelAttribute="Admin">
					<tr>
						<th colspan=2 style="text-align: center">管理ユーザーの登録</th>
					</tr>
					<tr>
						<th><form:label path="AdminId" value="${Admin.getAdminId()}">ID</form:label></th>
						<td>
							<div>
								<form:input path="AdminId" />
							</div>
							<div>
								<form:errors path="AdminId" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="Password"
								value="${Admin.getPassword()}">パスワード</form:label></th>
						<td><div>
								<form:input path="Password" />
							</div>
							<div>
								<form:errors path="Password" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th colspan=2 style="text-align: center">
							<button type="submit" class="btn btn-primary">登録</button> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						</th>
					</tr>
				</form:form>
			</table>
		</div>
	</div>
</body>
</html>