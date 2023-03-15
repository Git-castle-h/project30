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
<title>Insert title here</title>
</head>
<script>
	
	window.addEventListener("load",function(){
		let delMember= document.querySelectorAll(".delMember");
		
		delMember.forEach(function(e){
			let del = e.getAttribute("data-value");
			let id = e.getAttribute("data-id");
			e.addEventListener("click",function(){
				if(confirm(id+' 회원정보를 삭제하시겠습니까?')){
					location.href= del;
				}else{
					console.log("회원정보 삭제 취소")
				}
			});
		});	
	});
	
</script>

<body>
	<div style="padding-bottom:0.5em">
		<form action="${contextPath}/member/searchMember.do">
			아이디: <input type="id" name="id"/>
			이름: <input type="text" name="name"/>
			이메일: <input type="email" name="email">
			<input type="submit" value="검색"/>
		</form>
	</div>
	<table border="1" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>수정</th>
			<th>삭제</th> 
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr align="center">
				<td>${member.id}</td>
				<td>${member.pwd}</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.joinDate}</td>
				<td><a href="${contextPath}/member/modIntro.do?id=${member.id}">수정</a></td>
				<td><button class="delMember" data-id="${member.id}" data-value="${contextPath}/member/removeMember.do?id=${member.id}" >삭제</button></td>
				
			</tr>
		</c:forEach>
	</table>
	<div style="text-align:center; width:80%">
		<a href="${contextPath}/member/memberForm.do">회원가입</a>
	</div>
	
</body>
</html>