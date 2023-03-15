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
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
	<script>
		window.addEventListener('load',function(){
			
			let _idOverlapped = document.querySelector('._idOverlapped');
			let submitBtn = document.querySelector('#_submit');
			
			let contextPath = document.querySelector('#contextPath').value;
			
			_idOverlapped.addEventListener('click',function(){
				
				let _id = document.querySelector('._id');
				console.log(_id);
				console.log(_id.value);
				$.ajax({
					type:'post',
					async:false,
					url:contextPath+"/member/idOverlapped.do",
					data:{id:_id.value},
					success:function(data,textStatus){
						console.log(data);
						console.log(typeof(data));
						if(data == 'usable'){
							alert('사용할 수 있는 id 입니다.');
							submitBtn.disabled = false;
						}else{
							alert('사용할 수 없는 id 입니다.');
							submitBtn.disabled = true;
						}
					},
					error:function(data,textStatus){
						console.log("error");
					},
					complete:function(data,textStatus){
						console.log('id_verification');
					}
					
				});
				
			});
			
		});
	</script>

	<form action="${contextPath}/member/addMember.do" method="post" >
		<input id="contextPath" type="hidden" value="${contextPath}">
		<table  align="center" >
			<tr align="center">
				<td></td>
				<td><h1>회원가입</h1></td>
			</tr>
			<tr align="left" bgcolor="">
				<td><b>아이디</b></td>
				<td><input  class="_id" type="text" name="id"><button type="button" class="_idOverlapped">중복확인</button></td>
			</tr>
			<tr align="left" bgcolor="">
				<td><b>비밀번호</b></td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr align="left" bgcolor="">
				<td><b>이름</b></td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr align="left" bgcolor="">
				<td><b>이메일</b></td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr align="left" bgcolor="">
				<td></td>
				<td>
					<input id="_submit" type="submit" value="가입하기" disabled="disabled">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>
