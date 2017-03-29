<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>メニュー</title>
</head>

<script type="text/javascript">
<!--メッセージの変更を行うJavascript-->
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

	function MouseOutMessage() {
		document.getElementById("message").value = "ボタンの説明が表示されます";
	}
</script>



<body>
	${session }

	<div style="width: auto; margin: 0 0;">
		<div align="center">
			<div>現在の所持金： ${session.getAsset() }</div>

			<div>
				<input id="message" type="text" size="80" value="ボタンの説明が表示されます"
					readonly>
			</div>
			<table border="1"
				style="border-spacing: 0; border-collapse: collapse;">
				<tr>
					<th>メニュー</th>
				</tr>
				<tr>
					<td><form:form action="/CardGame/game/donuts/main"
							method="POST" modelAttribute="Game">
							<form:hidden path="GameType" value="Donuts" />
							<input id="donuts" type="submit" value="ドーナツゲーム"
								onMouseOver="MouseOverMessage1();"
								onMouseOut="MouseOutMessage();">
						</form:form></td>
				</tr>
				<tr>

					<td><form:form action="/CardGame/menu/logout" method="POST"
							modelAttribute="Game">
							<form:hidden path="GameType" value="donuts" />
							<input id="highlow" type="submit" value="High&Low"
								onMouseOver="MouseOverMessage2();"
								onMouseOut="MouseOutMessage();">
						</form:form></td>
				</tr>
				<tr>
					<td><input type="button" onclick="" value="登録情報の変更"
						onMouseOver="MouseOverMessage3();" onMouseOut="MouseOutMessage();"></td>
				</tr>

			</table>

			<form:form action="/CardGame/menu/logout" method="POST">
				<input type="submit" value="ログアウト"
					onMouseOver="MouseOverMessage4();" onMouseOut="MouseOutMessage();">
			</form:form>
		</div>
	</div>
</body>
</html>