<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/jblog.css">
</head>

<body>



	<div class="center-content">
	<img src="${pageContext.request.contextPath }/assets/images/logo2.jpg"><br>

	<!-- header -->
	<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="search-form" action="${pageContext.request.contextPath }/search" method="get">
			<fieldset>
				<input type="text" name="keyword" /> <input type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title" checked="checked"> <label>블로그 제목</label> 
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>
				<input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="blog-post"> <label>블로그 글</label>
			</fieldset>
		</form>

	
<c:choose>
<c:when test="${empty map.keyCounter}">
</c:when>
<c:otherwise>

${map.keyCounter }
	<c:choose>
	
	<c:when test="${map.keyCounter == 0}">
		결과를 찾을수 없습니다.
	</c:when>
	
	<c:otherwise>
	
	<table>
	<tr>
	<td> 결과 </td>
	</tr>
	
	<!-- 블로그 타이틀 로 검색 -->
	<c:if test="${map.keyCounter == 1}">
	<tr>
	<td> 번호 </td><td> id </td><td> title </td><td> 보기 </td>
	</tr>
	
	<c:forEach items='${map.list }' var='vo' varStatus='loop'>
	<tr>
		<td>${loop.index }</td>
		<td>${vo.id }</td>
		<td>${vo.title }</td>
		<td>
		<a href="${pageContext.request.contextPath }/${vo.id}">보기</a>
		</td>
	</tr>
	</c:forEach>
	
	</c:if>
	
	<!-- TODO 카테고리 번호 수정 -->
	<!-- 블로그 카테고리 로 검색 -->
	<c:if test="${map.keyCounter == 3}">
	<tr>
	<td> 번호 </td><td> id </td><td> name </td><td> desc </td><td> reg_date </td><td>보기</td>
	</tr>
	
	<c:forEach items='${map.list }' var='vo' varStatus='loop'>
	<tr>
		<td>${loop.index }</td>
		<td>${vo.id }</td>
		<td>${vo.name }</td>
		<td>${vo.desc }</td>
		<td>${vo.regDate }</td>
		<td>
		<a href="${pageContext.request.contextPath }/${vo.id}/${vo.no}">보기</a>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	
	<!-- 블로그 유저/아이디 로 검색 -->
	<c:if test="${map.keyCounter == 5}">
	<tr>
	<td> 번호 </td><td> id </td><td> name </td><td>보기</td>
	</tr>
	
	<c:forEach items='${map.list }' var='vo' varStatus='loop'>
	<tr>
		<td>${loop.index }</td>
		<td>${vo.id }</td>
		<td>${vo.name }</td>
		<td>
		<a href="${pageContext.request.contextPath }/${vo.id}">보기</a>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	
	<!-- TODO 보기 기능 수정 -->
	<!-- 블로그 포스트 로 검색 -->
	<c:if test="${map.keyCounter == 7}">
	<tr>
	<td> 번호 </td><td> id </td><td>no</td><td>title</td><td> contents </td><td>reg_date</td><td>보기</td>
	</tr>
	
	<c:forEach items='${map.list }' var='vo' varStatus='loop'>
	<tr>
		<td>${loop.index }</td>
		<td>${vo.id }</td>
		<td>${vo.no }</td>
		<td>${vo.title }</td>
		<td>${vo.contents }</td>
		<td>${vo.regDate }</td>
		<td>
		 <a href="${pageContext.request.contextPath }/${vo.id}/${vo.categoryNo}/${vo.no}">보기</a>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	
	</table>
	
	</c:otherwise>
	</c:choose>
</c:otherwise>
</c:choose>

		
	
		
	</div>
</body>
</html>