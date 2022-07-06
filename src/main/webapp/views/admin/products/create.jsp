<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css ">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/templatemo-style.css ">
</head>
<body>
<div class="col-8 offset-2">

	<div class="d-flex justify-content-end">
		
		<a type="button" class="primary-btn" data-toggle="modal" data-target="#modalExcel">
		  Thêm từ Excel
		</a>
		
		<!-- Modal -->
		<div class="modal fade" id="modalExcel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  	<div class="modal-dialog" role="document">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<h5 class="modal-title" id="exampleModalLabel">Thêm sản phẩm từ excel</h5>
		        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          			<span aria-hidden="true">&times;</span>
		        		</button>
		      		</div>
			      	<div class="modal-body">
			      		 <form action="${ pageContext.request.contextPath }/admin/products/storeExcel" method="post" 
			      		 		enctype="multipart/form-data">
							<div class="mt-3">
								<label>Vui lòng chọn file excel</label>
								<input type="file" name="product_file" class="form-control"> 
							</div>
							<button class="primary-btn mt-3">Thêm Sản Phẩm</button>			
						</form>
			      	</div>
			    </div>
		  	</div>
		</div>
		
	</div>
	
	<form:form
		action="${ pageContext.request.contextPath }/admin/products/store"
		method="post" modelAttribute="product_model">
		<div class="mt-3">
			<label>Ảnh</label>
			<form:input type="file" name="image" path="image"  />
			<br>
			<form:errors path="image" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>Tên sản phẩm</label>
			<form:input type="text" name="name" path="name" cssClass="form-control"/>
			<br>
			<form:errors path="name" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>Giá</label>
			<form:input type="text" name="price" path="price" cssClass="form-control"/>
			<br>
			<form:errors path="price" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>Loại hàng</label>
			<form:select  name="categoryId" path="categoryId" cssClass="form-control">
				<c:forEach var="category" items="${ lstCategory }">
					<form:option value="${ category.id }">${ category.name }</form:option>
				</c:forEach>
			</form:select>
			<br>
			<form:errors path="categoryId" element="span" cssClass="text-danger" />
		</div>

		<div class="mt-3">
			<label>Trạng thái</label> 
			<form:radiobutton path="available" name="available" value="1" cssClass="form-check-input" checked="checked" label="Có sẵn" />
			<form:radiobutton path="available" name="available" value="0" cssClass="form-check-input" label="Không còn" />
			<form:errors path="available" element="span" cssClass="text-danger" />
		</div>

		<button class="btn btn-primary">Save</button>
	</form:form>
</div>
</body>
</html>