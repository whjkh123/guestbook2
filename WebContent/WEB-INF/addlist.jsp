<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


<%@ page import="com.javaex.vo.GuestBookVo"%>
<%@ page import="java.util.List"%>

<%
	List<GuestBookVo> gList = (List<GuestBookVo>) request.getAttribute("GuestList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/guestbook2/gbc" method="post">

		<table border="1">

			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>

			<tr>
				<td colspan="4"><textarea rows="10" cols="50" name="content"></textarea></td>



			</tr>

			<tr>
				<td colspan="4"><button type="submit">확인</button></td>



			</tr>

		</table>

		<input type="hidden" name="action" value="add"> <br>

	</form>

	<%
		for (int i = 0; i < gList.size(); i++) {
	%>

	<table border="1">

		<tr>
			<td><%=gList.get(i).getNo()%></td>
			<td><%=gList.get(i).getName()%></td>
			<td><%=gList.get(i).getReg_date()%></td>
			<td><a
				href="/guestbook2/gbc?action=dform&no=<%=gList.get(i).getNo()%>">삭제</a></td>
		</tr>

		<tr>
			<td colspan="4"><%=gList.get(i).getContent()%></td>



		</tr>

	</table>

	<br>

	<%
		}
	%>

</body>
</html>