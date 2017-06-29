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
<title>ゲームの設定</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 50%;">
			<h2>Settings</h2>
			<p>${up_msg }</p>
			<table
				class="table table-bordered table-hover table-striped table-responsive">
				<tr>
					<th style="text-align: center">待機時間(秒)</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form class="form-inline"
							action="/CardGame2/admin/game/setting/wait/donuts" method="post">
							<label>ドーナツ</label>
							<div class="form-group">
								<input type="text" name="wait_d" id="wait_d" value="${wait_d }"
									class="form-control" placeholder="3桁までの数値が入力できます" maxlength='3'
									pattern="^([1-9]\d*|0)(\.\d+)?$" />
							</div>
							<button type="submit" class="btn btn-primary">更新</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<form class="form-inline"
							action="/CardGame2/admin/game/setting/wait/high" method="post">
							<label>High & Low</label>
							<div class="form-group">
								<input type="text" name="wait_h" id="wait_h" value="${wait_h }"
									class="form-control" placeholder="3桁までの数値が入力できます" maxlength='3'
									pattern="^([1-9]\d*|0)(\.\d+)?$" />
							</div>
							<button type="submit" class="btn btn-primary">更新</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>

				<tr>
					<th style="text-align: center">初期の所持金</th>
				</tr>
				<tr>
					<th style="text-align: center">
						<form class="form-inline"
							action="/CardGame2/admin/game/setting/asset" method="post">
							<div class="form-group">
								<input type="text" name="asset" id="asset" value="${asset}"
									class="form-control" placeholder="15桁までの数値が入力できます"
									maxlength='15' pattern="^[0-9]+$" />
							</div>
							<button type="submit" class="btn btn-primary"
								onMouseOut="MouseOutMessage();">更新</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</th>
				</tr>
			</table>
			<br>
			<div>
				<c:url var="Url" value="/admin/game" />
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