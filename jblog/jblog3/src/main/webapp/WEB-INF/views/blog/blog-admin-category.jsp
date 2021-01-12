<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	
		<!-- header -->
		<c:import url="/WEB-INF/views/blog/blog-template-header.jsp" />
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a
					href="${pageContext.request.contextPath }/${authUser.id}/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a
					href="${pageContext.request.contextPath }/${authUser.id}/write">글작성</a></li>
					
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
					<c:forEach items='${map.list }' var='vo' varStatus='loop'>
					<tr>
						<td>${vo.no }</td>
						<td>${vo.name }</td>
						<td>${map.countPost[loop.index] }</td>
						<td>${vo.desc }</td>
						
						<td>
						<c:if test="${map.countPost[loop.index] == 0 }">
						<a href="${pageContext.request.contextPath }/${authUser.id}/deletecat?no=${vo.no }"><ion-icon name="trash-outline"></ion-icon></a>
						</c:if>
						</td>
					</tr>
					</c:forEach>
					
					
					
									  
				</table>
      	
      			<form action="${pageContext.request.contextPath }/${authUser.id}/addcat" method="post">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td>
		      			<input type="hidden" name="id" value="${authUser.id}">
		      			<input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	</form>
			</div>
		</div>
		<!-- footer -->
		<c:import url="/WEB-INF/views/blog/blog-template-footer.jsp" />
	</div>
	
	
	
	
	
		<!-- 이모티콘 -->
	<!-- https://ionicons.com/usage#chevron-forward-outline -->
	<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
	
	
</body>
</html>