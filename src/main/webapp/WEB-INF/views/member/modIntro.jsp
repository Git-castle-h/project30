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
<title>비밀번호 확인</title>
</head>
<body>
	<form action="${contextPath}/member/modMember.do" method="post" >
		<table border="1" align="center" width="80%">
			<tr align="center" bgcolor="">
				<td colspan="2">비밀번호 확인이 필요합니다.</td>
			</tr>
			<tr align="center" bgcolor="">
				<td><b>비밀번호</b></td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="확인하기"/></td>
			</tr>
		</table>
		
	</form>
</body>
</html>