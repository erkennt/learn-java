<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
<title>ユーザー登録</title>
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<div>ユーザー情報を入力してください</div>
			<div>${msg}</div>
			<table
				class="table table-bordered table-hover table-responsive">
				<form:form action="/CardGame2/regist/add" method="POST"
					modelAttribute="UM">
					<tr>
						<th colspan=2 style="text-align: center">新規登録</th>
					</tr>
					<tr>
						<th><form:label path="UserId" value="${UM.getUserId()}">ログインID</form:label></th>
						<td>
							<div>
								<form:input path="UserId" class="form-control"/>
							</div>

							<div>
								<form:errors path="UserId" cssStyle="color:red" />
							</div>
							<div>半角英数字で2～10文字以内で入力してください</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="Password" value="${UM.getPassword()}">Password</form:label></th>
						<td><div>
								<form:password path="Password" class="form-control"/>
							</div>


							<div>
								<form:errors path="Password" cssStyle="color:red" />
							</div>
							<div>半角英数字で6～20文字以内で入力してください</div></td>
					</tr>
					<tr>
						<th><form:label path="ConfirmPassword" value="${UM.getConfirmPassword()}">確認</form:label></th>
						<td><div>
								<form:password path="ConfirmPassword" class="form-control"/>
							</div>


							<div>
								<form:errors path="ConfirmPassword" cssStyle="color:red" />
							</div>
							<div>もう一度入力してください</div></td>
					</tr>
					<tr>
						<th><form:label path="NickName" value="${UM.getNickName()}">ニックネーム</form:label></th>
						<td>
							<div>
								<form:input path="NickName" class="form-control"/>
							</div>
							<div>
								<form:errors path="NickName" cssStyle="color:red" />
							</div>
							<div>ゲーム内で使用する名前です</div>
							<div>2～10文字以内で入力してください</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="MailAddress"
								value="${UM.getMailAddress()}">メールアドレス</form:label></th>
						<td><div>
								<form:input path="MailAddress" class="form-control"/>
							</div>
							<div>
								<form:errors path="MailAddress" cssStyle="color:red" />
							</div>
							<div>入力したアドレスに仮登録メールが送られます</div></td>
					</tr>
					<tr>
						<th><form:label path="Birthday" value="${UM.getBirthday()}">生年月日</form:label></th>
						<td><c:if test="${!empty Years}">
								<div class="form-inline">
									<form:select path="Years" class="form-control">
										<form:option value="">--</form:option>
										<form:options items="${Years}" />
									</form:select>
									<form:select path="Month" class="form-control">
										<form:option value="">--</form:option>
										<form:options items="${Month}" />
									</form:select>
									<form:select path="Days" class="form-control">
										<form:option value="">--</form:option>
										<form:options items="${Days}" />
									</form:select>
								</div>
								<div>
									<div>
										<form:errors path="Years" cssStyle="color:red" />
									</div>
									<div>
										<form:errors path="Month" cssStyle="color:red" />
									</div>
									<div>
										<form:errors path="Days" cssStyle="color:red" />
									</div>

								</div>

							</c:if></td>
					</tr>
					<tr>
						<th colspan=2 style="text-align: center">
						<button type="submit" class="btn btn-primary">登録確認</button></th>

					</tr>
				</form:form>
			</table>
			<c:url var="Url" value="/" />
			<form action="${Url}" method="get">
				<button type="submit" class="btn btn-primary">戻る</button>
			</form>

		</div>
	</div>
</body>
</html>