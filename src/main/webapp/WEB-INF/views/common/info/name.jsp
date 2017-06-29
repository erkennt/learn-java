<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>ニックネーム変更</title>
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
					action="/CardGame2/common/info/name/update"
					method="POST" modelAttribute="Name">
					<tr>
						<th colspan=2 style="text-align: center">ニックネームの変更</th>
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
						<th colspan=2 style="text-align: center">
							<button type="submit" class="btn btn-primary">変更</button>
						</th>
					</tr>
				</form:form>
			</table>

			<c:url var="Url" value="/common/game" />
			<form:form action="${Url}" method="post">
			<button type="submit" type="button" class="btn btn-primary">キャンセル</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>

		</div>
	</div>
</body>
</html>