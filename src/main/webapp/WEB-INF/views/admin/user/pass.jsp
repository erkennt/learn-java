<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>パスワード変更</title>
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
				<form:form
					action="/CardGame2/admin/user/edit/${userId}/UpdatePassword"
					method="POST" modelAttribute="New">
					<tr>
						<th colspan=2 style="text-align: center">パスワードの変更</th>
					</tr>
					<tr>
						<th><form:label path="NewPassword"
								value="${New.getNewPassword()}">パスワード</form:label></th>
						<td>
							<div>
								<form:password path="NewPassword" />
							</div>
							<div>
								<form:errors path="NewPassword" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="ConfNewPassword"
								value="${New.getConfNewPassword()}">確認</form:label></th>
						<td><div>
								<form:password path="ConfNewPassword" />
							</div>
							<div>
								<form:errors path="ConfNewPassword" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th colspan=2 style="text-align: center">
							<button type="submit" class="btn btn-primary">変更</button>
						</th>
					</tr>
				</form:form>
			</table>

			<c:url var="Url" value="/admin/user/edit/${userId }" />
			<form:form action="${Url}" method="post">
				<button type="submit" class="btn btn-primary">戻る</button>
			</form:form>
		</div>
	</div>
</body>
</html>