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
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$('input[id=file]').change(function() {
		$('#file').val($(this).val());
	});
});
</script>

</head>
<title>データの一括操作</title>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>Settings</h2>
			<p>${up_msg }</p>
			<table
				class="table table-bordered table-hover table-striped table-responsive">
				<tr>
					<th style="text-align: center">データの出力</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form class="form-inline"
							action="/CardGame2/admin/bulk/export/all" method="post">
							<label>全てのデータをエクセルへ出力する</label>
							<button type="submit" class="btn btn-primary">ファイルをダウンロード</button>
							<input type="hidden" name="excel" /> <input type="hidden"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</th>
				</tr>

				<tr>
					<th style="text-align: center">一括削除</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form class="form-inline" action="/CardGame2/admin/bulk/delete"
							method="post">
							<label>全てのデータを削除する</label>
							<button type="submit" class="btn btn-primary">一括削除</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">一括登録</th>
				</tr>
				<tr>
					<th style="text-align: center">
					<label>エクセルからデータを登録</label>
						<form action="/CardGame2/admin/bulk/add/upload" method="post"
							enctype="multipart/form-data">
							<label>	<input type="file" name="file"></label> <input class="btn"
									type="submit" name="image" value="Upload" />
						</form></th>
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