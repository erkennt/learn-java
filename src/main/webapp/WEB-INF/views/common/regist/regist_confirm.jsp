<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<head>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" /><body>
	<div style="width: 100%; marg
in: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<div>入力した情報に誤りが無いか確認してください</div>
			<table class="table table-bordered table-hover table-responsive">
				<form:form action="/CardGame2/regist/temp" method="POST"
					modelAttribute="UM">
					<tr>

						<th colspan=2 style="text-align: center">入力内容</th>
					</tr>
					<tr>
						<th>ログインID</th>
						<td>${UM.getUserId()}</td>
					</tr>
					<tr>
						<th>Password</th>
						<td>************</td>
					</tr>
					<tr>
						<th>ニックネーム</th>
						<td>${UM.getNickName()}</td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td>${UM.getMailAddress()}</td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>${UM.getYears()}年${UM.getMonth()}月${UM.getDays()}日</td>
					</tr>
					<tr>
						<th colspan=2 style="text-align: center">
				<button type="submit" class="btn btn-primary">登録</button>
						</th>

					</tr>
				</form:form>
			</table>
			<form:form action="/CardGame2/regist" method="POST"
				modelAttribute="UM">
				<button type="submit" class="btn btn-primary">キャンセル</button>
			</form:form>
		</div>
	</div>
</body>
</html>