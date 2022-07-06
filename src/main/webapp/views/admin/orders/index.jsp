<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css ">
</head>
<body>	
 	
 	<div class="container">
 	
	        <div class="">
	        <div class="d-flex justify-content-end mt-3 mb-3">
	        	<a class="btn btn-primary" href="${ pageContext.request.contextPath }/admin/orders/create">Tạo mới</a>
	        </div>
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="">
	                        <table class="table">
	                            <thead class="thead-light">
	                                <tr>
	                                    <th>OrderId</th>
	                                    <th>Customer Name</th>
	                                    <th>Created Date</th>
	                                    <th>Address</th>
	                                    <th>Total</th>
	                                    <th colspan="2">Acction</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach var="order" items="${pageData.content}">
	                            	
		                                <tr>
		                                    <td class="shoping__cart__item">
		                                        ${ order.id }
		                                    </td>
		                                    <td class="shoping__cart__total">	                                    	
	                                        	${ order.customerName }
		                                    </td>	                                    
		                                    <td class="shoping__cart__total">
	                                        	${ order.createDate }
		                                    </td>
		                                    
											<td class="shoping__cart__total">	                                    	
	                                        	${ order.address }
		                                    </td>
											
		                                    <td class="shoping__cart__total">
		                                    	<label>
		                                        	${ total } VNĐ
		                                        </label>
		                                    </td>
		                                    
		                                    <td>
		                                    	<a href="#" class="btn btn-primary btn-small">Xem</a>
		                                    	<a href="${ pageContext.request.contextPath }/admin/orders/edit/${order.id}" class="btn btn-success btn-small">Sửa</a>
		                                    	<a href="#" class="btn btn-danger btn-small">Xóa</a>
		                                    </td>
		                                </tr>
	                            	</c:forEach>
	                                
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
	            </div>           
	        </div>
 	</div>
	
</body>
</html>