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
<title>メニュー</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>

<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>管理メニュー</h2>
			<table
				class="table table-bordered table-hover table-striped table-responsive">

				<tr>
					<th style="text-align: center">メニュー</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/admin/user/search" method="post">
							<button type="submit" class="btn btn-primary"
								onMouseOver="MouseOverMessage1();"
								onMouseOut="MouseOutMessage();">ユーザーの管理</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<p>ユーザーのデータを操作できます</p>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">
							<form action="/CardGame2/admin/game" method="post">
								<button type="submit" class="btn btn-primary"
									onMouseOver="MouseOverMessage2();"
									onMouseOut="MouseOutMessage();">ゲームの管理</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						<p>ゲームの設定を変更できます</p>
						</th>
				</tr>
				<tr>
					<th style="text-align: center">
							<form action="/CardGame2/admin/bulk" method="post">
								<button type="submit" class="btn btn-primary"
									onMouseOver="MouseOverMessage2();"
									onMouseOut="MouseOutMessage();">データの一括操作</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
							<p>データのバックアップや一括削除などが行えます</p>
						</th>
				</tr>

			</table>
			<br>
			<div>
				<c:url var="logoutUrl" value="/admin/logout" />
				<form action="${logoutUrl}" method="post">
					<button type="submit" class="btn btn-primary" value="logout">ログアウト</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>


			</div>
		</div>
	</div>
</body>
</html>