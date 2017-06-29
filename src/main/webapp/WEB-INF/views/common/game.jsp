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
<title>メニュー</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>

<script type="text/javascript">
<!--メッセージの変更を行うJavascript-->
	function MouseOutMessage() {
		document.getElementById("message").value = "ボタンの説明が表示されます";
	}
	function MouseOverMessage1() {

		document.getElementById("message").value = "前のカードと同じ数を引かないようにするゲームです";
	}
	function MouseOverMessage2() {

		document.getElementById("message").value = "次のカードが前のカードより大きいか小さいかを当てるゲームです";
	}
	function MouseOverMessage3() {

		document.getElementById("message").value = "名前やパスワードの変更ができます";
	}
	function MouseOverMessage4() {

		document.getElementById("message").value = "ゲームを終了します";
	}
</script>
<body>

	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<div>ようこそ ${users.getNickName()} さん</div>
			<div>現在の所持金： ${users.getAsset() }</div>
			<div>
				<input id="message" type="text" size="80" value="ボタンの説明が表示されます"
					readonly />
			</div>
			<br>
			<table class="table table-bordered table-hover table-responsive">

				<tr>
					<th><div>メニュー</div></th>

				</tr>
				<tr>
					<th><div>

							<form action="/CardGame2/common/game/donuts/main" method="post">
								<button type="submit" class="btn btn-primary btn-block"
									onMouseOver="MouseOverMessage1();"
									onMouseOut="MouseOutMessage();">ドーナツゲーム</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div></th>
				</tr>
				<tr>
					<th><div>
							<form action="/CardGame2/common/game/highlow/main" method="post">
								<button type="submit" class="btn btn-primary btn-block"
									onMouseOver="MouseOverMessage2();"
									onMouseOut="MouseOutMessage();">High & Low</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div></th>
				</tr>
				<tr>
					<th><div>
					<form action="/CardGame2/common/info/info" method="post">
							<button type="submit" class="btn btn-primary btn-block"
								onMouseOver="MouseOverMessage3();"
								onMouseOut="MouseOutMessage();">登録情報の変更</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div></th>
				</tr>

			</table>
			<br>
			<div>
				<c:url var="logoutUrl" value="/common/logout" />
				<form action="${logoutUrl}" method="post">
					<button type="submit" class="btn btn-primary btn-block"
						value="logout" onMouseOver="MouseOverMessage4();"
								onMouseOut="MouseOutMessage();">ログアウト</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>


			</div>
		</div>
	</div>

</body>
</html>