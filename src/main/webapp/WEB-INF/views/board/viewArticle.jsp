<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*"
    isELIgnored="false"
    %>
<%@taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="article" value="${articleMap.article}"/>
<c:set var="imageFileList" value="${articleMap.imageFileList}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
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
			
					<tr class="imageTr" 
						<c:if test="${empty imageFileList or  imageFileList == 'null'}">	
							style="display:none"
						</c:if>	
					>
	
						<td  align="center" bgcolor="#FF9933">
							이미지
							 <input type="hidden" class="filePlus" value="파일추가" onClick="fn_addFile()"/>
						</td>
						<td id="d_file">
							<c:forEach var="item" items="${imageFileList}" varStatus="status" >
	
								<div>
									<input type="hidden" name="originalFileName" value ="${item.imageFileName }"/>
									<img src ="${contextPath}/download.do?imageFileName=${item.imageFileName}&articleNO=${item.articleNO}" class="imgPreview"/>
									<input type="hidden" name="imageFileName" data-id="${status.index}" class="i_imageFileName" disabled onchange="readURL(this);"/>
								</div>
	
							</c:forEach>
						</td>
					</tr>

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
	<script type ="text/javascript">
	let d_file = document.querySelector("#d_file");
	let preview = document.querySelectorAll(".imgPreview");
	let pLength = preview.length-1;
	let cnt = pLength;
	
		function fn_enable(obj){
			
			let i_imageFileName = document.querySelectorAll(".i_imageFileName");
			let imageTr = document.querySelector(".imageTr");
			
			
			document.getElementById("i_title").disabled=false;
			document.getElementById("i_content").disabled=false;
			
			if(i_imageFileName != null){
				
				i_imageFileName.forEach(function(e){
					e.setAttribute("type","file");
					e.disabled =false;
				});
				
			}
			
			imageTr.style.display="table-row";
			document.querySelector(".filePlus").setAttribute("type","button");
			
			document.getElementById("tr_btn_modify").style.display="contents";
			document.getElementById("tr_btn").style.display="none";
			
			
		}
		


		function fn_addFile(){
			cnt++;
			let d_file = document.querySelector("#d_file");
			let div = document.createElement("div");
			let input = document.createElement("input");
			let img = document.createElement("img");
			
			input.setAttribute("type","file");
			input.setAttribute("data-id",cnt);
			input.setAttribute("onChange","readURL(this);");
			input.setAttribute("name","file"+cnt);
		
			img.setAttribute("class","imgPreview");
			img.setAttribute("src","#");

			div.append(img);
			div.append(input);
			d_file.append(div);
			preview = document.querySelectorAll(".imgPreview");
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
			let d_id = input.getAttribute("data-id");
			console.log(d_id);
			let reader = new FileReader();
			reader.addEventListener("load",function(e){
				preview[d_id].setAttribute("src", e.target.result);
			});
			reader.readAsDataURL(input.files[0]);
		
		}	
		
		function backToList(obj){
			obj.action = "${contextPath}/board/listArticle.do";
			obj.submit();
		}
	</script>
</html>
