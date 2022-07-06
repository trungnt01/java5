<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
 
	
	<div class="container">
		<section class="hero">
	        <div class="container">
	            <div class="row">
	                <div class="col-3">
	                    <div class="hero__categories">
	                        <div class="hero__categories__all">
	                            <i class="fa fa-bars"></i>
	                            <span>Danh mục</span>
	                        </div>
	                        <ul>
	                        	<c:forEach var="category" items="${lstCategory}">
		                            <li><a href="#">${ category.name }</a></li>
	                        	</c:forEach>
	                            
	                        </ul>
	                    </div>
	                </div>
	                <div class="col-9">
	                    <div class="hero__search">
	                        <div class="hero__search__form">
	                            <form action="#" method="get">
	                                 
	                                <input type="text" placeholder="Nhập tên sản phẩm?">
	                                <button type="submit" class="site-btn">SEARCH</button>
	                            </form>
	                        </div>
	                    </div>
	                    
	                    <div class="mt-5">
						    <div class="row">
						    	<c:forEach var="product" items="${ pageData.content }">
						    	
							    	<div class="col-lg-3 col-md-4 col-sm-6 mix vegetables fastfood">
							            <div class="featured__item">
							                <div class="featured__item__pic set-bg" data-setbg="${ pageContext.request.contextPath }/img/featured/feature-2.jpg">
							                    <ul class="featured__item__pic__hover">
							                        <%--  <li><a href="#"><i class="fa fa-heart"></i></a></li>
							                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> --%>
							                    	<form  action="${ pageContext.request.contextPath }/cart/store" method="post">
							                    		<input type="hidden" name="productId" value="${ product.id }">
							                    		
							                    			<button ><i class="fa fa-shopping-cart"></i>
							                    			<a href="${ pageContext.request.contextPath }/orders/create/${ product.id}">Mua</a>
							                    		
							                    	</form>    
							                    	
							                    </ul>
							                </div>
							                <div class="featured__item__text">
							                    <h6><a href="#">${ product.name }</a></h6>
							                    <h5>${ product.price }</h5>
							                </div>
							            </div>
							        </div>
						    	
						    	</c:forEach>
						    </div>
					    	
					    	
					    </div>
					    
	                </div>
	            </div>
	        </div>
	    </section>
	    
	    
	    
	</div>
 