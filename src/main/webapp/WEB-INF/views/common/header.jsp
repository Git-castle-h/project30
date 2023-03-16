<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import = "java.util.*"
    isELIgnored="false"
    %>
<%@taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="tiles" uri ="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상단부</title>
</head>
<body>

	<header class="header">
		<div class="logo">
			<a href="${contextPath }/member/loginForm.do">
				<img alt="dog" src="${contextPath }/resources/image/dog.jfif">
			</a>
		</div>
		<div class="h_banner">
			<h1><font size=30>스프링실습 홈페이지</font></h1>
		</div>
		<div class="isLogOn">
			<c:choose>
				<c:when test="${isLogOn == true && member!=null}">
					<h3>환영합니다. ${member.name}님</h3>
					<a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>
				</c:when>
				<c:otherwise>
					<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>



</body>
</html>