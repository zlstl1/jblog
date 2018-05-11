<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/blogHeader.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				
					<!-- post 내용 -->
					<c:choose>
						<c:when test="${!empty requestScope.mainPost}">
								<h4>${mainPost.postTitle}</h4>
								<p>${mainPost.postContent}</p>
						</c:when>
						<c:otherwise>
							<!-- 등록된 글이 없는경우 -->
							<h4>등록된 글이 없습니다.</h4>
							<p>
								
							<p>
						</c:otherwise>
					</c:choose>	
					
				</div>
				
				<ul class="blog-list">
					<c:forEach items="${requestScope.postList}" var="postVo">
						<li>
							<a href="${pageContext.request.contextPath}/${requestScope.id}?cateNo=${postVo.cateNo}&postNo=${postVo.postNo}">${postVo.postTitle}</a> 
							<span>${postVo.regDate}</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/extra.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>