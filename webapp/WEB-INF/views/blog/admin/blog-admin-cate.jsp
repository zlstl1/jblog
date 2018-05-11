<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp"></c:import>
				
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id=cateList>
		      			
					</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add" >
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>


<script type="text/javascript">
	$(document).ready(function() {
		fetchList();
	});
	
	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/getcategory",
			type : "post",
			//contentType : "application/json",
			//data : {id : id}, 

			dataType : "json",
			success : function(categoryList) {
				for (var i = 0; i < categoryList.length; i++) {
					render(categoryList[i], "up");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	$("#cateList").on("click",".deleteCategory",function(){
		var $this = $(this);
		var cateNo =$this.attr("id");
		
		$.ajax({

			url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/deletecategory",
			type : "post",
			/* contentType : "application/json", */
			data : {cateNo : cateNo}, 

			dataType : "json",
			success : function(cateNo) {
				if(cateNo!=0){
					$("[id=deleteRow"+cateNo+"]").remove();
				}else{
					alert("삭제할 수 없습니다.");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	$("#btnAddCate").on("click",function(){
		categoryVo = { 	cateName : $("input[name='name']").val(),
						description : $("input[name='desc']").val(),
		}

		$.ajax({

			url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/addcategory",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(categoryVo), 

			dataType : "json",
			success : function(categoryVo) {
				render(categoryVo, "up");
				$("[name=name]").val("");
				$("[name=desc]").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	function render(categoryVo, updown) {
		var str = "";

		str += "<tr id='deleteRow"+ categoryVo.cateNo +"'>";
		str += 	"<td>" + categoryVo.no + "</td>";
		str += 	"<td>" + categoryVo.cateName + "</td>";
		str += 	"<td>" + categoryVo.postTime + "</td>";
		str += 	"<td>" +categoryVo.description + "</td>";
		str += 	"<td class='deleteCategory' id="+ categoryVo.cateNo +"><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>";
		str += "</tr>"

		if (updown == "up") {
			$("#cateList").prepend(str);
		} else if (updown == "down") {
			$("#cateList").append(str);
		} else {
			console.log("오류")
		}
	}

</script>

</html>