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
<title>ゲームの管理</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>

<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<table
				class="table table-bordered table-hover table-striped table-responsive">

				<tr>
					<th style="text-align: center">各種設定</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/admin/game/setting" method="post">
							<button type="submit" class="btn btn-primary">ゲームの設定</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form action="/CardGame2/admin/game/data" method="post">
							<button type="submit" class="btn btn-primary">ゲーム情報の操作</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
			</table>
			<br>
			<div>
				<c:url var="Url" value="/admin/menu" />
				<form action="${Url}" method="get">
					<button type="submit" class="btn btn-primary">戻る</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>