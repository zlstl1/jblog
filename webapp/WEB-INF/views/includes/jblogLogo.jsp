<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

	<!-- 메인해더 -->
	<a href="${pageContext.request.contextPath}">
		<img class="logo" src="${pageContext.request.contextPath}/assets/images/logo.jpg">
	</a>
	
	<ul class="menu">
		<c:choose>
			<c:when test="${empty sessionScope.authUser}">
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<!-- 로그인 후 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}">내블로그</a></li> 
			</c:otherwise>
		</c:choose>
 	</ul>