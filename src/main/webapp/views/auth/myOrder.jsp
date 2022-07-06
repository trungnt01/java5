<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="container">
 			
	        <div class="">
	        <div>
	        <h2>
	        	Tất cả các đơn hàng
	        </h2>
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
	                            	<c:forEach var="order" items="${lstOrder}">
	                            	
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