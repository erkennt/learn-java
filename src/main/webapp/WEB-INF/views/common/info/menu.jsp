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
<!-- Bootstrap core CSS -->
</head>
<title>登録情報の変更</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>

<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>登録情報の変更</h2>
			<table
				class="table table-bordered table-hover table-striped table-responsive">

				<tr>
					<th style="text-align: center">メニュー</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/common/info/name" method="get">
							<button type="submit" class="btn btn-primary"
								>ユーザー名の変更</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/common/info/password" method="get">
							<button type="submit" class="btn btn-primary"
								>パスワードの確認</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/common/info/mail" method="get">
							<button type="submit" class="btn btn-primary"
								>パスワードの確認</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
			</table>
			<br>
			<div>
				<c:url var="url" value="/" />
				<form action="${url}" method="get">
					<button type="submit" class="btn btn-primary" value="back">戻る</button>
				</form>

			</div>
		</div>
	</div>
</body>
</html>