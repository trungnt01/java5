<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="col-8 offset-2">
	<h5>${ message}</h5>
	<form action="${ pageContext.request.contextPath }/login" method="post">
		<div class="mt-3">
			<label>Email</label>
			<input type="email" name="email" class="form-control">  
		</div>
		
		<div class="mt-3">
			<label>Mật khẩu</label>
			<input type="password" name="password" class="form-control">  
		</div>	
		<button class="btn btn-primary mt-3 mb-3">Đăng nhập</button>
	</form>
	
</div>
