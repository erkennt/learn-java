<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<html>
<head>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<title>パスワードの変更</title>
<body>
	<c:if test="${msg_up != null}" var="flg">
		<div style="width: 100%; margin: 0% 0%;">
			<div class="container" align="center" style="width: 50%;">
				${msg_up }</div>
		</div>
	</c:if>


	<c:if test="${msg_up == null}" var="flg">

		<div style="width: 100%; margin: 0% 0%;">
			<div class="container" align="center" style="width: 50%;">
				<p>${msg}</p>
				<h2>パスワードの変更</h2>
				<h3>
					ユーザー名 :
					<c:out value="${Users.getNickName()}" />
				</h3>
				<form:form action="/CardGame2/support/password/update" method="POST"
					modelAttribute="ChangePassword">
					<div class="form-group">
						<div class="form-group">
							<label for="password1" class="sr-only">新しいパスワード</label>
							<form:password path="Password1" class="form-control"
								placeholder="新しいパスワードを入力して下さい" />
							<form:errors path="Password1" cssStyle="color:red" />
						</div>
						<div class="form-group">
							<label for="password2" class="sr-only">確認</label>
							<form:password path="Password2" class="form-control"
								placeholder="もう一度入力してください" />
							<form:errors path="Password2" cssStyle="color:red" />
						</div>
					</div>
					<button id="btn-login" class="btn btn-primary btn-block">変更する</button>
					<input type="hidden" name="parameter" value="<c:out value='${parameter}'/>" />

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form:form>
				<br>
				<div>
					<c:url var="Url" value="/" />
					<form action="${Url}" method="get">
						<button type="submit" class="btn btn-primary">戻る</button>
					</form>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>