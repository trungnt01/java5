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
	
	<section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                	<th> &nbsp</th>
                                    <th class="shoping__product">Products</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="cart" items="${ pageData.content }">
                            	
	                                <tr>
	                            <%--     	<td><input type="checkbox" class="form-control" name="check" id="check"></td>--%>
	                                    <td class="shoping__cart__item">
	                                        <img src="${ pageContext.request.contextPath }/img/cart/cart-1.jpg" alt="">
	                                        <h5>${ cart.product.name }</h5>
	                                    </td>
	                                    <td class="shoping__cart__price">
	                                        ${ cart.product.price }
	                                    </td>
	                                    <td class="shoping__cart__quantity">
	                                        <div class="quantity">
	                                            <div class="pro-qty">
	                                            	<a onclick="return down(${ cart.id });" id="down"><span class="dec qtybtn">-</span></a>
	                                                <input onchange="return change(${ cart.id });" id="quantity${ cart.id }" type="text" value="${ cart.quantity }">
	                                                <a id="up" onclick="return up(${ cart.id });"><span class="inc qtybtn">+</span></a>
	                                                
	                                            </div>
	                                        </div>
	                                    </td>
	                                    <td class="shoping__cart__total">
	                                    	<label id="total_label${ cart.id }">
	                                        	${ cart.product.price * cart.quantity }
	                                        </label>VND
	                                    </td>
	                                    <td class="shoping__cart__item__close">
	                                        <a href="${ pageContext.request.contextPath }/cart/delete/${cart.id}">
	                                        	<span class="icon_close"></span>
                                        	</a>
	                                    </td>
	                                    <input type="hidden" id="id_cart" value="${ cart.id }">
					    				<input type="hidden" id="price_product${ cart.id }" value="${ cart.product.price } ">
					    				
	                                </tr>
                            	</c:forEach>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                
                <div class="col-lg-6">
                     
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <ul>
                            <li>Total <span>${ total } VND</span></li>
                        </ul>
                        <form action="${ pageContext.request.contextPath }/orders/store" method="post">
                        	<button class="primary-btn">Mua</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		function down(cartId) {
			 
			var price_product = $('#price_product'+cartId).val();
			var total = document.getElementById("total_label"+cartId);
			 
			 $.ajax({
				url: 'cart/update/'+ cartId,
				type : 'post',
				data: {        
	             cartId: cartId,
	             upOrDown: 'down'
	         	},
				success: function(data){
					$('#quantity'+cartId).val(data);				
					console.log(data);
					
					total.innerHTML = data*price_product ;
					console.log(data*price_product);
				},
				error: function(error){ 
					alert('Có lỗi xảy ra, vui lòng thử lại :)');
				}		 
			})
		}
		
		function up(cartId) {
			var price_product = $('#price_product'+cartId).val();
			var total = document.getElementById("total_label"+cartId);
			 $.ajax({
				url: 'cart/update/'+ cartId,
				type : 'post',
				data: {
	             cartId: cartId,
	             upOrDown: 'up'
	         	},
				success: function(data){
					$('#quantity'+cartId).val(data);		
					console.log(data);
					total.innerHTML = data*price_product ;
					console.log(data*price_product);
				},
				error: function(error){ 
					alert('Có lỗi xảy ra, vui lòng thử lại :)');
				}		 
			})
		}
	</script>
</body>
</html>