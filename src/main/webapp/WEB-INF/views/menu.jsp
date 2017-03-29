<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>メニュー</title>
</head>

<c:choose>

	<c:when test="${session == null}">
		<c:redirect url="/" />
	</c:when>
	<c:when test="${session.getStatus() == Boolean.FALSE}">
		<div>本登録が完了していません。</div>
		<div>ご指定のメールアドレスに送信された仮登録メールから、登録を完了させてください。</div>
	</c:when>

	<c:otherwise>

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
			<div style="width: auto; margin: 0 0;">
				<div align="center">
					<div>ようこそ ${session.getNickName()} さん</div>
					<div>現在の所持金： ${session.getAsset() }</div>
					<div>
						<input id="message" type="text" size="80" value="ボタンの説明が表示されます"
							readonly>
					</div>
					<br>
					<table border="1"
						style="width: 150px; height: 200px; border-spacing: 0; border-collapse: collapse;">
						<tr>
							<th>メニュー</th>
						</tr>
						<tr>
							<th><form:form action="/CardGame/game/donuts/main"
									method="POST" modelAttribute="Game">
									<form:hidden path="GameType" value="Donuts" />
									<input id="donuts" type="submit" value="ドーナツゲーム"
										onMouseOver="MouseOverMessage1();"
										onMouseOut="MouseOutMessage();">
								</form:form></th>
						</tr>
						<tr>
							<th><form:form action="/CardGame/game/highlow/main" method="POST" modelAttribute="Game">
									<input id="highlow" type="submit" value="High&Low"
										onMouseOver="MouseOverMessage2();"
										onMouseOut="MouseOutMessage();">
								</form:form></th>
						</tr>
						<tr>
							<th><input type="button" onclick="" value="登録情報の変更"
								onMouseOver="MouseOverMessage3();"
								onMouseOut="MouseOutMessage();"></th>
						</tr>

					</table>
					<br>
					<form:form action="/CardGame/menu/logout" method="POST">
						<input type="submit" value="ログアウト"
							onMouseOver="MouseOverMessage4();"
							onMouseOut="MouseOutMessage();">
					</form:form>
				</div>
			</div>
	</c:otherwise>
</c:choose>

</body>
</html>