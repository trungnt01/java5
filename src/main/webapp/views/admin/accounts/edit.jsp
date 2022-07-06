<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<form:form modelAttribute="account_model" action="${ pageContext.request.contextPath }/admin/accounts/update/${ account_model.id }" method="post" >
		<div class="mt-3">
			<label>Ảnh</label>
			<form:input type="file" name="photo" path="photo"  />
			<br>
			<form:errors path="photo" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>Tên người dùng</label>
			<form:input type="text" name="username" path="username" cssClass="form-control"/>
			<br>
			<form:errors path="username" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>Họ và tên</label>
			<form:input type="text" name="fullname" path="fullname" cssClass="form-control"/>
			<br>
			<form:errors path="fullname" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>Email</label>
			<form:input type="email" name="email" path="email" cssClass="form-control"/>
			<br>
			<form:errors path="email" element="span" cssClass="text-danger" />
		</div>
		<div class="form-group mt-3">		
			<label for="password">Mật khẩu</label>				
			<form:password value="*******" readonly="true" path="password" class="form-control" name="password" id="password"/> 
			<form:errors path="password" element="span" cssClass="text-danger"/>
		</div>	
		<div class="mt-3">
			<label>Trạng thái</label>
			<form:radiobutton path="activated" name="activated" value="1" cssClass="form-check-input" checked="checked" label="Hoạt động" />
			<form:radiobutton path="activated" name="activated" value="0" cssClass="form-check-input" label="Vô hiệu" />
			<form:errors path="activated" element="span" cssClass="text-danger" />
		</div>
		<div class="mt-3">
			<label>phân quyền</label>
			<form:radiobutton path="admin" name="admin" value="0" cssClass="form-check-input" checked="checked" label="User" />
			<form:radiobutton path="admin" name="admin" value="1" label="Admin" cssClass="form-check-input"/>
			<form:errors path="admin" element="span" cssClass="text-danger" />
		</div>

		<button class="btn btn-primary">Cập nhật</button>
	</form:form>
</div>
</body>
</html>