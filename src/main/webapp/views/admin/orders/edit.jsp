<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="container">
	
		<hr>
		<div class="mb-4">
			<label>Mã hóa đơn: <b> ${ orderModel.id } </b> </label> <br>
			<label>Ngày tạo: <b> ${ orderModel.createDate }</b> </label>
		</div>
		<hr>
		
		<div class=" row">
			
			<div class="col-8">
				<form action="${ pageContext.request.contextPath }/admin/orders/create" method="get">
					<div class="row">
						<div class="input-group mb-3 col-9"> 
							 <span class="input-group-text" id="inputGroup-sizing-default">Thêm sản phẩm</span>
							 <input name="productName" id="productName" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
						</div>
								<div class="col-auto">
									 <button class="btn btn-primary btn-small" >Tìm</button>
								</div>			
							 	<div class="col-auto">
									<!-- Button trigger modal -->
									<button onclick="return scanner(${ orderModel.id });" type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal">
									  Quét
									</button>
									
									<!-- Modal -->
									<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  	<div class="modal-dialog modal-dialog-scrollable" role="document">
									    	<div class="modal-content">
									      		<div class="modal-header">
									        		<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
									        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									          			<span aria-hidden="true">&times;</span>
									        		</button>
									      		</div>
										      	<div class="modal-body">
										      		<div class="container">
												        <div class="row">
												            <div class="">
												                <video id="preview" width="100%"></video>
												            </div>
												            <div class="">
												                <input type="text" readonly="readonly" name="text" id="text" placeholder="result scan qr code" class="form-control">
												            </div>
												        </div>
												    </div> 
										      	</div>
										    </div>
									  	</div>
									</div>
									<!-- end modal -->
								</div>		 
						</div>
					</form>
								
				
				
				<section class="shoping-cart spad">
			        <div class="container">
			            <div class="row">
			                <div class="col-lg-12">
			                    <div class="shoping__cart__table">
			                        <table>
			                            <thead>
			                                <tr>
			                                    <th class="shoping__product">Products</th>
			                                    <th>Price</th>
			                                    <th>Quantity</th>
			                                    <th>Total</th>
			                                    <th></th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                            	<c:forEach var="item" items="${ lstOrderDetail }" varStatus="index">
			                            	
				                                <tr>
				                                    <td class="shoping__cart__item">
				                                        <img src="${ pageContext.request.contextPath }/img/cart/cart-1.jpg" alt="">
				                                        <h5>${ item.product.name }</h5>
				                                    </td>
				                                    <td class="shoping__cart__price">
				                                        ${ item.price }
				                                    </td>
				                                    <td class="shoping__cart__quantity">
				                                        <div class="quantity">
				                                            <div class="pro-qty">
				                                            	<a onclick="return down(${index.count});" id="down"><span class="dec qtybtn">-</span></a>
				                                                <input onchange="return change(${index.count});" id="quantity${ index.count }" type="text" value="${ item.quantity }">
				                                                <a id="up" onclick="return up(${index.count});"><span class="inc qtybtn">+</span></a>
				                                           
				                                            </div>
				                                        </div>
				                                    </td>
				                                    <td class="shoping__cart__total">
				                                    	<label id="total_label${ index.count }">
				                                        	${ item.price * item.quantity }
				                                        </label>
				                                    </td>
				                                    <td class="shoping__cart__item__close">
				                                        <a href="#">
				                                        	<span class="icon_close"></span>
			                                        	</a>
				                                    </td>
				                                    <input type="hidden" id="id_order" value="${ item.order.id }">
							    					<input type="hidden" id="price_product${ index.count }" value="${ item.product.price } ">
							    					<input type="hidden" id="index" value="${index.count}">
				                                </tr>
			                            	</c:forEach>
			                            </tbody>
			                        </table>
			                    </div>
			                </div>
			            </div>
			           
			        </div>
			    </section>
			</div>
		
			<div class="col-4">
			
				
				<div class="">
					<form:form action="${ pageContext.request.contextPath }/admin/orders/edit/${ order.id }" modelAttribute="orderModel" method="post">	
						<div>
							<h2>Thông tin khách hàng</h2>	  
						</div>
						<div>
							<label>Tên khách hàng</label>
							<form:input path="customerName" cssClass="form-control"/>
							<form:errors path="customerName" element="span" cssClass="text-danger" />
						</div>
						<div class="mt-3 ">
							<label>Thông tin liên lạc</label>
							<form:input path="address" cssClass="form-control"/>
							<form:errors path="address" element="span" cssClass="text-danger" />
						</div>
						<label class="mt-3 mb-3">Tổng tiền</label>
						<div class="row ">
							<div class="col-6">
								<label>${ total }</label>
							</div>
							<div class=" col-6 d-flex justify-content-end  mb-3">
								<button class="btn btn-primary btn-sml">Cập nhật</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		
		</div>
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		function down(index) {
			var price_product = $('#price_product'+index).val();
			var total = document.getElementById("total_label"+index);
			var quantity = $('#quantity'+index).val();
		<%--	var index = $('#index').val(); --%>
			
			 $.ajax({
				url: 'http://localhost:8080/demo_spring/admin/orders/updateList',
				type : 'get',
				data: {        
	             upOrDown: 'down',
	             quantity: quantity,
	             index: index
	         	},
				success: function(data){
					$('#quantity'+index).val(data);				
					
					total.innerHTML = data*price_product  ;
					console.log(data*price_product);
					console.log(data);
				},
				error: function(error){ 
					alert('Có lỗi xảy ra, vui lòng thử lại :)');
				}		 
			}) 
		}
		
		function up(index) {
			var price_product = $('#price_product'+index).val();
			var total = document.getElementById("total_label"+index);
			var quantity = $('#quantity'+index).val();
			
			
			 $.ajax({
				url: 'http://localhost:8080/demo_spring/admin/orders/updateList',
				type : 'get',
				data: {
	             upOrDown: 'up',
	             quantity: quantity,
	             index: index
	         	},
				success: function(data){
					$('#quantity'+index).val(data);				
					
					total.innerHTML = data*price_product ;
				},
				error: function(error){ 
					alert('Có lỗi xảy ra, vui lòng thử lại :)');
				}		 
			})
		}
		
		function change(index) {
			 
			var price_product = $('#price_product'+index).val();
			var total = document.getElementById("total_label"+index);
			var quantity = $('#quantity'+index).val();
			
			 $.ajax({
				url: 'http://localhost:8080/demo_spring/admin/orders/updateList',
				type : 'get',
				data: {
	             upOrDown: 'change',
	             quantity: quantity,
	             index: index
	         	},
				success: function(data){
					$('#quantity'+index).val(data);				
					
					total.innerHTML = data*price_product ;
				},
				error: function(error){ 
					alert('Có lỗi xảy ra, vui lòng thử lại :)');
				}		 
			})
		}
	</script>
	
	<script type="text/javascript">
		
		function scanner(idOrder) {
	        let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });

	        Instascan.Camera.getCameras().then(function (cameras) {
	            
	        	if (cameras.length > 0) {
	                scanner.start(cameras[0]);
	            } else {
	                console.error('No cameras found.');
	            }
	        	
	        }).catch(function (e) {
	            console.error(e);
	        });
	
	        scanner.addListener('scan', function (content) {
	            document.getElementById('text').value=content;
	            console.log(content);
	
	            $.ajax({
					url: 'http://localhost:8080/demo_spring/admin/orders/update/scan',
					type : 'get',
					data: {        
						productId: content,
						idOrder: idOrder
		         	},
					success: function(data){
						console.log(data);
					},
					error: function(error){ 
						alert('Có lỗi xảy ra, vui lòng thử lại :)');
					}		 
				})
	            
	        }); 
		}
		
      </script>
</body>
</html>