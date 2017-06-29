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
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<title>ユーザーサポート</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>

<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>ユーザーサポート</h2>
			<table
				class="table table-bordered table-hover table-striped table-responsive">

				<tr>
					<th style="text-align: center">メニュー</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/support/userid" method="post">
							<button type="submit" class="btn btn-primary"
								onMouseOver="MouseOverMessage1();"
								onMouseOut="MouseOutMessage();">IDの確認</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<p>ログインIDをお忘れの方</p>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/support/password" method="post">
							<button type="submit" class="btn btn-primary"
								onMouseOver="MouseOverMessage2();"
								onMouseOut="MouseOutMessage();">パスワードの確認</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<p>パスワードをお忘れの方</p>
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