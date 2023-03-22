<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import = "java.util.*"
    isELIgnored="false"
    %>
<%@taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="articlePage" value="${articleMap.articlePage }"/>
<c:set var="totArticle" value="${articleMap.totArticle }"/>
<c:set var="section" value="${articleMap.section }"/>
<c:set var="pageNum" value="${articleMap.pageNum }"/>
<script>
	function backToList(obj){
		obj.action = "${contextPath}/board/listArticle.do";
		obj.submit();
	}
</script>
	<div class="container w800">
	<div class="tableWrap">
		<table class="mainTable"> 
			<tr class="head">
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${empty articlePage}">
					<tr height="10">	
						<td colspan="4">
							<p align="center">
								<b>
									<span style="font-size:9pt">등록된 글이 없습니다.</span>
								</b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${!empty articlePage}">
					<c:forEach var="article" items="${articlePage}" varStatus="articleNum">
						<tr align="center">
							<td width="5%">
								${articleNum.count}
							</td>
							<td width="10%">
								${article.id}
							</td>
							<td align="left" width="35%">
								<span style="padding-left:20px"></span>
								<c:choose>
									<c:when test="${article.level >1 }">
										<c:forEach begin ="1" end= "${article.level }" step="1">
											<span style="padding-left:20px"></span>
										</c:forEach>
										<span style="font-size:12px;">[답변]</span>
										<a class="cls1" href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">
											${article.title}
										</a>
									</c:when>
									<c:otherwise>
										<a class="cls1" href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">
											${article.title}
										</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td width="10%">
								<fmt:formatDate value="${article.writeDate }"/>
							</td>
						</tr>
					</c:forEach>
					
				</c:when>
			</c:choose>
		</table>

	<div class="tableBottom">
		<c:if test="${totArticle != null }">
			<c:choose>
				<c:when test="${totArticle > 100 }">
					<c:forEach var="page" begin ="1" end="10" step="1">
						<c:if test="${setcion >1 && page==1 }">
							<a class="pageBtn prev"  href="${context}/board/listArticle.do?section=${section-1 }&${section-1}&pageNum=${(section - 1)*10}">
							&nbsp;prev
							</a>
						</c:if>
						<a class="pageBtn" href="${contextPath}/board/listArticle.do?section=${section}&pageNum=${pageNum}">
							${(section-1)*10 + page}
						</a>
						<c:if test="${page}== 10">
							<a class="pageBtn next"  href="${contextPath}/board/listArticle.do?section=${section+1}&pageNum=${section*10+1}">
								&nbsp;next
							</a>
						</c:if>
					</c:forEach>
				</c:when>
				<c:when test ="${totArticle ==100 }">
					<c:forEach var="page" begin ="1" end="10" step="1">
						<a class="no-uline" href="#">${page}</a>
					</c:forEach>
				</c:when>
				<c:when test="${totArticle<100 }">
					<c:forEach var="page" begin="1" end="${totArticle/10 +1}" step="1">
						<c:choose>
							<c:when test="${page==pageNum }">
								<a class="sel-page" href="${contextPath }/board/listArticle.do?section=${section}&pageNum=${page}">${page}</a>
							</c:when>

							<c:otherwise>	
								<a class="no-uline" href="${contextPath}/board/listArticle.do?section=${section}&pageNum=${page}">
									${page}
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>
		<a class="writeBtn" href="${contextPath}/board/articleForm.do">글쓰기</a>
	</div>
	</div>
	</div>