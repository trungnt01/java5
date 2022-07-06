<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="col-8 offset-2">
	<form action="${pageContext.request.contextPath}/orders/store/${ product.id }" method="post">
                           
	    <div class="form-group mt-3">
	    	<label>Sản phẩm</label>		
	    	<input type="text" class="form-control" value="${ product.name }" disabled="disabled">				
	    </div>
	    <div class="form-group mt-3">
	    	<label>Số lượng</label>
	    	<input type="number" name="quantity" class="form-control">
	    </div>
	    <div class="form-group mt-3">
	    	<label>Giá</label>
	    	<input type="text" class="form-control" name="price" disabled="disabled" value="${product.price}">
	    </div>
   		<div class="form-group mt-3">
	    	<label>Địa chỉ</label>
	    	<input type="text" name="address" class="form-control">
	    </div>   						        
       <div class="mt-3">
           <button class="btn btn-primary">Mua hàng</button>
           <a class="btn btn-secondary" href="${pageContext.request.contextPath}/home">Hủy</a>
       </div>	        
	</form>
</div>

</body>
</html>