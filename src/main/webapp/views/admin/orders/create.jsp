<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
 
	<div class="container mt-3 ">
	
		<div class="row">
			<div class="col-8 ">
				<div>
					<form action="${ pageContext.request.contextPath }/admin/orders/create" method="get">
						<div class="row">
							<div class="col-8">
								<input type="text" class="form-control" name="productName" id="productName">
							</div>
							<div class="col-auto">
								 <button class="btn btn-primary btn-small" >Tìm</button>
							</div>			
						 	<div class="col-auto">
								<!-- Button trigger modal -->
								<button onclick="return scanner();" type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal">
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
				</div>
				
				<div class="mt-3 mb-3">
					<table >
						<c:forEach var="product" items="${ lstProduct }">
							<tr>
								<div class="row">
									<div class="col-3">
										<label>Ảnh</label>
									</div>
									<div class="col-5">
										<label>${ product.name }</label>
									</div>
									<div class="col-2">
										<label>${ product.price }</label>
									</div>
									<div class="col-2">
									
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal${ product.id }">
										  ADD
										</button>
										
										<!-- Modal -->
										<div class="modal fade" id="modal${ product.id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
										  	<div class="modal-dialog" role="document">
										    	<div class="modal-content">
										      		<div class="modal-header">
										        		<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
										        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										          			<span aria-hidden="true">&times;</span>
										        		</button>
										      		</div>
											      	<div class="modal-body">
											      		<form action="${ pageContext.request.contextPath }/admin/orders/create/up" method="get">
											        		<label>Nhập số lượng</label>
											        		<input type="number" class="form-control" name="quantity">
											        		<input type="hidden" name="productId" value="${ product.id }">
												        	<button  class="btn btn-primary">ADD</button>
											      		</form>
											      	</div>
											    </div>
										  	</div>
										</div>
										
									</div>
								</div>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				
				<section class="shoping-cart spad ">
			        <div class="container">
			            <div class="row">
			                <div class="col-lg-12">
			                    <div class="shoping__cart__table">
			                        <table>
			                            <thead>
			                                <tr>
			                                    <th class="shoping__product">Sản phẩm</th>
			                                    <th>Giá</th>
			                                    <th>Số lượng</th>
			                                    <th>Total</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                            	<c:forEach var="orderDetail" items="${ lstOrderDetail }" varStatus="index">
                           	
				                                <tr>
				                                    <td class="shoping__cart__item">
				                                        <img src="${ pageContext.request.contextPath }/img/cart/cart-1.jpg" alt="">
				                                        <h5>${ orderDetail.product.name }</h5>
				                                    </td>
				                                    <td class="shoping__cart__price">
				                                        ${ orderDetail.product.price }
				                                    </td>
				                                    <td class="shoping__cart__quantity">
				                                        <div class="quantity">
				                                            <div class="pro-qty">
				                                            	<a href="${ pageContext.request.contextPath }/admin/orders/create/update?index=${index.count}&upOrDown=down">
				                                            		<span class="dec qtybtn">-</span>
				                                            	</a>
				                                                <input id="quantity" type="text" value="${ orderDetail.quantity }">
				                                                <a href="${ pageContext.request.contextPath }/admin/orders/create/update?index=${index.count}&upOrDown=up">
				                                                	<span class="inc qtybtn">+</span>
				                                                </a>
				                                                
				                                            </div>
				                                        </div>
				                                    </td>
				                                    <td class="shoping__cart__total">
				                                    	<label id="total_label">
				                                        	${ orderDetail.product.price * orderDetail.quantity }
				                                        </label>
				                                    </td>
				                                    <td class="shoping__cart__item__close">
				                                        <a href="${ pageContext.request.contextPath }/admin/orders/deleteProduct/${index.count}">
				                                        	<span class="icon_close"></span>
			                                        	</a>
				                                    </td>
				                                    
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
			
		
			<div class="col-4 border border-dark mt-3">
			
				 
				 <ul class="nav nav-tabs" id="myTab" role="tablist">
				  	<li class="nav-item">
				    	<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Tại quầy</a>
				 	</li>
				  	<li class="nav-item">
				    	<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Order</a>
				  	</li>
				</ul>
				<div class="tab-content" id="myTabContent">
				  <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
				  		<form:form action="${ pageContext.request.contextPath }/admin/orders/store" modelAttribute="order" method="post">	
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
									<input type="text" readonly="readonly" class="form-control" value="">
								</div>
								<div class=" col-6 d-flex justify-content-end  mb-3">
									<button class="btn btn-primary btn-sml">Thanh toán</button>
								</div>
							</div>
						</form:form>
				  </div>
				  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
				  		<form:form action="${ pageContext.request.contextPath }/admin/orders/store" modelAttribute="order" method="post">	
							<div>
								<h2>Thông tin khách hàng</h2>	  
							</div>
							<div class="row">
								<div class="">
									<form:select name="userId" path="userId" cssClass="form-control">
										<c:forEach var="account" items="${ lstAccount }">
											<form:option value="${ account.id }">${ account.fullname }</form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="">
									<a href="#" class="">+</a>
								</div>
							</div>
							<div class="mt-3 ">
								<label>Thông tin liên lạc</label>
								<form:input path="address" cssClass="form-control"/>
								<form:errors path="address" element="span" cssClass="text-danger" />
							</div>
							<label class="mt-3 mb-3">Tổng tiền</label>
							<div class="row ">
								<div class="col-6">
									<input type="text" readonly="readonly" class="form-control" value="">
								</div>
								<div class=" col-6 d-flex justify-content-end  mb-3">
									<button class="btn btn-primary btn-sml">Thanh toán</button>
								</div>
							</div>
						</form:form>
				  </div>
				</div>
			
			
				
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
		function scanner() {
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
					url: 'http://localhost:8080/demo_spring/admin/orders/create/scan',
					type : 'get',
					data: {        
						productId: content
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
      