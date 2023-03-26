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
<title>글쓰기창</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
	<h1 style ="text-align:center">새 글 쓰기</h1>
	<form name ="articleForm" method="post" action="${contextPath}/board/addArticle.do" enctype ="multipart/form-data">
		
		<table border="0" align ="center">
		
			<tr>
				<td align="right">글제목: </td>
				<td colspan ="2"><input type="text" size="67" maxlength="500" name="title"></td>
			</tr>
			<tr>
				<td align="right">글내용:</td>
				<td colspan="2">
					<textarea name="content" rows="10" cols="65" maxlength="4000"></textarea>
				</td>
			</tr>
			<tr>
				<td align ="right">이미지파일 첨부: <input type="button" value="파일추가" onClick="fn_addFile()"/></td>
				<td colspan="2">
					<div id="d_file">
						<input type="file" name="imageFileName" data-id="0" onchange ="readURL(this);">
						<img class="imgPreview" src ="#" width="200" height="200"/>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="text" size="20" maxlength="100" name="id" value="${member.name}" readonly/>
				</td>
				<td colspan="2">
					<input type="submit" value="글쓰기"/>
					<input type="button" value="목록보기" onclick="backToList(this.form)"/>
				</td>
			</tr>
		</table>
		
	</form>
</body>
<script type="text/javascript"> 
	let d_file = document.querySelector("#d_file");
	let preview = document.querySelectorAll(".imgPreview");
	console.log(preview);
	let cnt =0;
	function readURL(input){

		let d_id = input.getAttribute("data-id");
		console.log(d_id);
		let reader = new FileReader();
		reader.addEventListener("load",function(e){
			preview[d_id].setAttribute("src", e.target.result);
		});
		reader.readAsDataURL(input.files[0]);
		
		/* if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload =function(e){
				$('#preview').attr('src',e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		} */

	}	
	function backToList(obj){
		obj.action = "${contextPath}/board/listArticle.do";
		obj.submit();
	}
	
	function fn_addFile(){
		cnt++;
		let div = document.createElement("div");
		let input = document.createElement("input");
		let img = document.createElement("img");
		
		input.setAttribute("type","file");
		input.setAttribute("data-id",cnt);
		input.setAttribute("onChange","readURL(this);");
		input.setAttribute("name","file"+cnt);
	
		img.setAttribute("class","imgPreview");
		img.setAttribute("src","#");
		img.setAttribute("width","200");
		img.setAttribute("height","200");

		div.append(input);
		div.append(img);
		d_file.append(div);
		preview = document.querySelectorAll(".imgPreview");
	}
</script>
</html>