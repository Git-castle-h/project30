<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"
    %>
<%@taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="tiles" uri ="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<tiles:insertAttribute name="title"/>
</title>
<link href="${contextPath}<tiles:getAsString name="css"/>" rel="stylesheet">
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<div id="container">
			<tiles:insertAttribute name="side"/>
			<div class="body">
				<tiles:insertAttribute name="body"/>
			</div>
	</div>	
	<tiles:insertAttribute name="footer"/>
</body>
</html>