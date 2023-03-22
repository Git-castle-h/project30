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
<title>글 상세보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	<script type ="text/javascript">
	
		function fn_enable(obj){
			document.getElementById("i_title").disabled=false;
			document.getElementById("i_content").disabled=false;
			if(document.getElementById("i_imageFileName")!= null){
				document.getElementById("i_imageFileName").disabled=false;	
			}
			document.getElementById("tr_btn_modify").style.display="contents";
			document.getElementById("tr_btn").style.display="none";
		}
		
		function fn_modify_article(obj){
			obj.action = "${contextPath}/board/modArticle.do";
			obj.submit();
		}
		
		function fn_remove_article(url,articleNO){
			let form = document.createElement("form");
			form.setAttribute("method","post");
			form.setAttribute("action",url);
			let articleNOInput = document.createElement("input");
			articleNOInput.setAttribute("type","hidden");
			articleNOInput.setAttribute("name","articleNO");
			articleNOInput.setAttribute("value",articleNO);
			form.appendChild(articleNOInput);
			document.body.appendChild(form);
			form.submit();
		}
	
		function fn_reply_form(url,parentNO){
			let form = document.createElement("form");
			form.setAttribute("method","post");
			form.setAttribute("action",url);
			let parentNOInput = document.createElement("input");
			parentNOInput.setAttribute("type","hidden");
			parentNOInput.setAttribute("name","parentNO");
			parentNOInput.setAttribute("value",parentNO);
			form.appendChild(parentNOInput);
			document.body.appendChild(form);
			form.submit();
		}
		
		function readURL(input){
			if(input.files && input.files[0]){
				var reader = new FileReader();
				reader.onload =function(e){
					$('#preview').attr('src',e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}

		}	
		
		function backToList(obj){
			obj.action = "${contextPath}/board/listArticle.do";
			obj.submit();
		}
	</script>


</head>
<body>
	<form name="frmArticle" method="post" action ="${contextPath}" enctype="multipart/form-data">
		<table border="0" align="center">
			<colgroup>
				<col width="150">
				<col width="150">
				<col width="150">
				<col width="150">
				<col width="20%">
			</colgroup>
			<tr>
				<td  align="center" bgcolor="#FF9933">
					글번호
				</td>
				<td>
					<input type="text" value="${article.articleNO }" disabled/>
					<input type="hidden" name="articleNO" value="${article.articleNO }"/>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">
					작성자 아이디
				</td>
				<td>
					<input type="text" value="${article.id }" name="writer" disabled/>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">글제목</td>
				<td> <input type="text" value="${article.title}" name="title" id="i_title" disabled></td>
			</tr>
			<tr>
				<td align="center" bgcolor="#FF9933">글내용</td>
				<td>
					<textarea row="20" cols="60" name="content" id ="i_content" disabled>${article.content}</textarea>
				</td>
			</tr>
			<c:if test="${not empty article.imageFileName && article.imageFileName != null }">
				<tr>
					<td  align="center" bgcolor="#FF9933" rowspan ="2">이미지</td>
					<td>
						<input type="hidden" name="originalFileName" value ="${article.imageFileName }"/>
						<img src ="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" id="preview"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);"/>
					</td>
				</tr>
			</c:if>
				<tr>
					<td align="center" bgcolor="#FF9933">
						등록일자
					</td>
					<td>
						<input type="text" value="<fmt:formatDate value ="${article.writeDate }"/>" disabled/>
					</td>
				</tr>
				<tr id="tr_btn_modify" style="display:none">
					<td colspan="2" align="center">
						<input type="button" value="수정반영하기" onClick="fn_modify_article(this.form)"/>
						<input type="button" value="취소" onClick="backToList(this.form)"/>
					</td>
				</tr>
				<tr id="tr_btn">
					<td colspan="2" align="center">
						<input type="button" value="수정하기" onClick="fn_enable(this.form)"/>
						<input type="button" value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do',${article.articleNO })"/>
						<input type="button" value="답글쓰기" onClick ="fn_reply_form('${contextPath}/board/replyForm.do',${article.articleNO})"/>
						<input type="button" value="취소" onClick="backToList(this.form)">
					</td>
				</tr>
		</table>
	</form>
	

</body>
</html>
