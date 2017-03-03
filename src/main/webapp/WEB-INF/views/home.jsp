<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%
//get table data
List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("data");
String message = (String)request.getAttribute("message");

%>

<h2>  Show table data </h2>

<table border="1">
  <tr>
    <th>id</th>
    <th>name</th>
  </tr>

<%
for(int i = 0;i < list.size();i++){
	String id = list.get(i).get("id").toString();
	String name = list.get(i).get("name").toString();
	out.println("<tr>");
	out.println("<td>"+id+"</td><td>"+name+"</td>");
	out.println("</tr>");
}
%>
</table>

<br/>

<h2>  Update </h2>
<h3> ${message} </h3>
<form method="Post" action="./UpdateController">
<select name="upId">
<%
for(int i = 0;i < list.size();i++){
	out.println("  <option value=\""+Integer.valueOf(list.get(i).get("id").toString())+"\">"
+Integer.valueOf(list.get(i).get("id").toString())+"</option>");
}
%>
</select>
<input type="text" size="5" maxlength="4" name="upName" />
<input type="submit" value="送信" />

</form>

<h2>  Insert </h2>


<h2>  Delete </h2>


</body>
</html>