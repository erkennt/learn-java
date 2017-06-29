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
<title>データの一括削除</title>
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>Delete</h2>
			<p>${up_msg }</p>
			<table
				class="table table-bordered table-hover table-striped table-responsive">
				<tr>
					<th style="text-align: center">Commonデータの削除</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form class="form-inline"
							action="/CardGame2/admin/bulk/delete/common" method="post">
							<button type="submit" class="btn btn-primary">実行</button><br>
							<label>ユーザー・ゲーム情報・ゲームログが削除されます</label><br>
							<input type="hidden" name="excel"
								 />
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>

				<tr>
					<th style="text-align: center">Adminデータの削除</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form class="form-inline"
							action="/CardGame2/admin/bulk/delete/admin" method="post">
							<button type="submit" class="btn btn-primary">実行</button><br>
							<label>管理ユーザー・ゲーム設定が削除されます</label><br>
							<label>ユーザーが存在しない場合に実行できます</label>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
			</table>
			<br>
			<div>
					<c:url var="Url" value="/admin/bulk" />
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