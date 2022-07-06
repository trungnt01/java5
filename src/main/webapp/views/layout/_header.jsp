<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Header Section Begin -->
    <header class="header">
        <div style="height: 50px"></div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header_logo">
                        <a href="${ pageContext.request.contextPath }/home"><img src="${ pageContext.request.contextPath }/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="${ pageContext.request.contextPath }/home">Home</a></li>
                            <li><a href="#">Product</a></li>
                            <c:if test="${ sessionScope.currentUser.admin == 1 }">
	                            <li class="nav-item dropdown">
							       	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							          	Quản Lý
							        </a>
							        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
							          	<a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/accounts">Quản Lý Tài Khoản</a>
							          	<a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/products">Quản Lý Sản Phẩm</a>
							          	<a class="dropdown-item" href="${ pageContext.request.contextPath }/admin/categories">Quản Lý Danh Mục</a>
							          	<a class="dropdown-item" href="#">Quản Lý Đơn Hàng</a>
							          	<div class="dropdown-divider"></div>
							          	
							        </div>
							     </li>
							     
                             </c:if>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                        	<c:if test="${ empty sessionScope.currentUser }">
	                        	<a href="${ pageContext.request.contextPath }/login">Đăng nhập</a>
                        	</c:if>
                        
                        	<c:if test="${ !empty sessionScope.currentUser }">
	                            <a href="${ pageContext.request.contextPath }/cart"><i class="fa fa-shopping-bag"></i> <span></span></a>
								<a href="${ pageContext.request.contextPath }/profile">${ sessionScope.currentUser.fullname }</a>
	                        	<a href="${ pageContext.request.contextPath }/logout">Đăng xuất</a>
                        	</c:if>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->