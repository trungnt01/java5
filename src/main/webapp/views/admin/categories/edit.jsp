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

	<form:form modelAttribute="category_model" action="${ pageContext.request.contextPath }/admin/categories/update/${ category_model.id }"
		 method="post" >
		<div class="mt-3">
			<label>Tên danh mục</label>
			<form:input type="text" name="name" path="name" cssClass="form-control"/>
			<br>
			<form:errors path="name" element="span" cssClass="text-danger" />
		</div>

		<button class="btn btn-primary">Cập nhật</button>
	</form:form>
</div>
</body>
</html>