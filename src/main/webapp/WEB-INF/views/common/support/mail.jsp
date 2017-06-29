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
</head>
<title>パスワードの変更</title>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			入力したメールアドレス宛てにメールを送信しました。 記載された案内に従ってください。

		<c:url var="url" value="/support" />
			<form action="${url}" method="get">
				<button type="submit" class="btn btn-primary" value="back">戻る</button>
			</form>
		</div>
	</div>
</body>
</html>