<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import = "java.util.*"
    isELIgnored="false"
    %>
<%@taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="tiles" uri ="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<c:set var="result" value="${param.result}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
<c:choose>
	<c:when test="${result=='loginFailed'}">
		<script>
			window.addEventListener("load",function(){
				alert("아이디나 비밀번호가 틀립니다. 다시 로그인해주세요");
			})
		</script>
	</c:when>
</c:choose>
</head>
<body>
	<div class="loginBox">
		<form action="${contextPath}/member/login.do" method="post">
			<table>
				<tr>
					<td>아이디 : </td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>비밀번호 : </td>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인"></td>
				</tr>	
			</table>
		</form>
	</div>
</body>
</html>