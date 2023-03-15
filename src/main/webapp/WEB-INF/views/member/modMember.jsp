<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import = "java.util.*"
    isELIgnored="false"
    %>
<%@taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberInfo</title>
</head>
<body>
	<form action="${contextPath}/member/updateMember.do" method="post" >
		<table border="1" align="center" width="80%">
			<tr align="center" bgcolor="">
				<td><b>아이디</b></td>
				<td><input type="text" name="id" value="${member.id}" disabled="disabled"></td>
			</tr>
			<tr align="center" bgcolor="">
				<td><b>비밀번호</b></td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr align="center" bgcolor="">
				<td><b>이름</b></td>
				<td><input type="text" name="name" value="${member.name}"></td>
			</tr>
			<tr align="center" bgcolor="">
				<td><b>이메일</b></td>
				<td><input type="email" name="email" value="${member.email}"></td>
			</tr>
			<tr align="center" bgcolor="">
				<td colspan="2">
					<input type="submit" value="수정하기">
					<input type="reset" value="처음으로">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>
