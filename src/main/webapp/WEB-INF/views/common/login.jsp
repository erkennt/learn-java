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
<title>ログイン</title>

<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-md-push-3 col-md-6">
			<div class="form-wrap">
				<div class="text-center">
					<h1>CardGame</h1>
				</div>
				<br />
				<form name="f" action="<c:url value='/common/login'/>" method="post">
					<div class="form-group">
						<c:if test="${param.containsKey('error')}">
							<font color="red"> Your login attempt was not successful
								due to <br /> <br /> <c:out
									value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
							</font>
						</c:if>
						<div class="form-group">
							<label for="id" class="sr-only">ユーザーID</label> <input type="text"
								name="username" id="username" class="form-control"
								placeholder="ID を入力して下さい" />
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">Password</label> <input
								type="password" name="password" id="password"
								class="form-control" placeholder="Password を入力して下さい" />
						</div>
						<div class="pull-right">
							<a class="btn btn-link pull-right" href="/CardGame2/support">ID・パスワードを忘れた場合はこちら</a>
						</div>
					</div>
					<button id="btn-login" class="btn btn-primary btn-block">ログイン</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-link " href="/CardGame2/regist">登録</a>
			</div>
		</div>

		<div class="col-xs-12 col-md-push-3 col-md-6">
			<label>・ゲーム状況</label>
			<p>
				ドーナツゲーム :<br> <b><c:out
						value="${mapDonuts.get('Magnification')}" /></b> 連続正解中 / 最後のユーザー <b><c:out
						value="${mapDonuts.get('NickName')}" /></b>
			</p>
			<p>
				High and Low :<br> <b><c:out
						value="${mapHighLow.get('Magnification')}" /></b> 連続正解中 / 最後のユーザー <b><c:out
						value="${mapHighLow.get('NickName')}" /></b>
		</div>
	</div>
</div>




</body>
</html>