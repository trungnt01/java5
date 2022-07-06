<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
</head>
<body>

	<div class="col-10 offset-1 mt-3 mb-3">
	
		<div class=" d-flex justify-content-end fs-2 ">
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/categories/create">Thêm sản phẩm</a>				
		</div>	
	
		<div class="">
	   		<form action="${ pageContext.request.contextPath }/admin/categories" method="get">
	   			<div class="row">
	   				<div class="col-6">
	   					<label>Sắp xếp theo</label>
	   					<select name="sort_by" class="form-control">
	   						<option value="id">Mặc định</option>
	   						<option value="name">Tên</option>
	   					</select>
	   				</div>
	   				<div class="col-6">
	   					<label>Thứ tự</label>
	   					<select name="sort_direction" class="form-control">
	   						<option value="ASC">Tăng dần</option>
	   						<option value="DESC">Giảm dần</option>
	   					</select>
	   				</div>
	   			</div>
	   			<div>
	    			<button class="btn btn-primary mt-3">Lọc</button>
	    			<a class="btn btn-danger mt-3" type="reset" href="${ pageContext.request.contextPath }/admin/categories">Reset</a>
	   			</div>
	   		</form>
	   	</div>

		<table class="table mt-3 table-striped table-bordered ">
			<thead class="bg-light">	
				<td>id</td>
				<td>tên sản phẩm</td>
				<td colspan="2">Thao tac</td>
			</thead>			
			<tbody>
				<c:forEach var="category" items="${ pageData.content }">						
					<tr>
						<td>${ category.id }</td>																
						<td>${ category.name }</td>
						
						<td> 
							<a href="${pageContext.request.contextPath}/admin/categories/edit/${category.id }" class="btn btn-info col-auto">Sửa</a> 
						</td>	
						<td>
							<button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#moda${category.id }">Xóa</button>	
						
							<!--modal xóa-->
							<div class="modal fade" id="modal${ category.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											Xác nhận xóa người này, hành động này sẽ không thể hoàn tác
										</div>
										<div class="modal-footer">
											<a href="" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</a>
											<a href="${pageContext.request.contextPath}/admin/categories/delete/${category.id }" class="btn btn-danger">Xóa</a>
										</div>
									</div>
								</div>
							</div>
							<!-- end modal xóa-->
						</td>
					</tr>
				</c:forEach>
			</tbody> 			
		</table>
	</div>
</body>
</html>