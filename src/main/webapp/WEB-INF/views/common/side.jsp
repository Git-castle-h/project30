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
<title>사이드 메뉴</title>
</head>
<body>
	<div class="side">
	<h1>사이드 메뉴</h1>
		<div class="menu">
			<a href="#" class="no-underline">회원관리</a>
			<a href="#" class="no-underline">게시판관리</a>
			<a href="#" class="no-underline">상품관리</a>
		</div>
	</div>
</body>
</html>