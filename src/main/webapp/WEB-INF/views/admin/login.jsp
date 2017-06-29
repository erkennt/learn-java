<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>管理者用</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />

</head>

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-md-push-3 col-md-6">
			<div class="form-wrap">
				<div class="text-center">
					<h1>Administrator</h1>
				</div>
				<br />
				<form name="f" action="<c:url value='/admin/login'/>"
					method="post">
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
					</div>
					<button id="btn-login" class="btn btn-primary btn-block">ログイン</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>

	</div>
</div>
</body>
</html>