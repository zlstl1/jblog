<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="navigation">
			<h2>카테고리</h2>
				<ul>
					<c:forEach items="${requestScope.categoryList}" var="categoryVo">
						<li><a href="${pageContext.request.contextPath}/${requestScope.id}?cateNo=${categoryVo.cateNo}">${categoryVo.cateName}</a></li>
					</c:forEach>
				</ul>
		</div>