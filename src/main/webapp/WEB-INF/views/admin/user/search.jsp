<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:url value="/admin/user/search/" var="pagedLink">
	<c:param name="p" value="~" />
</c:url>
<c:set var="pageSizeList" value="$[5, 20, 50]" />

<html>
<head>
<title>ユーザー検索</title>
<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/Bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" />

<script type="text/javascript">
<!--
window.onload = onLoad;

function onLoad() {
	var f = document.getElementById("Search");
	var s = document.getElementById("PageSizeList");
	for(var i = 0; i < s.length; i++){

		if(s.options[i].value == f.pageSize.value){
			s.options[i].selected = true;
		}
	}

}


function onClick(e) {
	var f = document.getElementById("Search");
	f.action = e.href;
	f.submit();
}
function changePageSize(e) {
	var f = document.getElementById("Search");
	var s = document.getElementById("PageSizeList");
	//alert(s.options[s.selectedIndex].value);
	f.pageSize.value = s.options[s.selectedIndex].value
	f.submit();
}
	-->
</script>

</head>
<body>
	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 30%;">
			<table class="table table-bordered"
				style="table-layout: fixed; text-align: center">
				<form:form action="/CardGame2/admin/user/search/0" method="POST"
					modelAttribute="Search">
					<tr>
						<th colspan=2 style="text-align: center">検索</th>
					</tr>
					<tr>
						<th><form:label path="UserId" value="${Search.getUserId()}">ログインID</form:label></th>
						<td>
							<div>
								<form:input path="UserId" />

							</div>

							<div>
								<form:errors path="UserId" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="NickName"
								value="${Search.getNickName()}">ニックネーム</form:label></th>
						<td>
							<div>
								<form:input path="NickName" />
							</div>
							<div>
								<form:errors path="NickName" cssStyle="color:red" />
							</div>
						</td>
					</tr>
					<tr>
						<th><form:label path="MailAddress"
								value="${Search.getMailAddress()}">メールアドレス</form:label></th>
						<td><div>
								<form:input path="MailAddress" />
							</div>
							<div>
								<form:errors path="MailAddress" cssStyle="color:red" />
							</div></td>
					</tr>
					<tr>
						<th colspan=2 style="text-align: center">
							<button type="submit" class="btn btn-primary">検索</button>
						</th>
					</tr>
					<form:hidden path="pageSize" />
				</form:form>
			</table>
		</div>
	</div>

	<div style="width: 100%; margin: 0% 0%;">
		<div class="container" align="center" style="width: 70%;">
			<div class="pull-right">
				表示件数
				<select id="PageSizeList"  onchange="changePageSize(this)">
					<option value="5">5件</option>
					<option value="20">20件</option>
					<option value="50">50件</option>
				</select>
			</div>
			<c:if test="${pagedListHolder != null}">
				<tg:pagenation pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
				<table
					class="table table-bordered table-hover table-striped table-responsive">
					<tr>
						<th>ID</th>
						<th>ニックネーム</th>
						<th>EMail</th>
					</tr>
					<c:forEach items="${pagedListHolder.pageList}" var="users">
						<tr>
							<td><a class="btn btn-link pull-left" href="/CardGame2/admin/user/edit/${users.userId}">${users.userId}</a></td>
							<td>${users.nickName}</td>
							<td>${users.mailAddress}</td>

						</tr>
					</c:forEach>
				</table>
				<tg:pagenation pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
			</c:if>
			<form:form action="/CardGame2/admin/menu" method="get">
				<button type="submit" class="btn btn-default">キャンセル</button>
			</form:form>
		</div>

	</div>

</body>
</html>