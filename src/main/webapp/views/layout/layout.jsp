<%@ page language="java" contentType="text/html; charset=UTF-8" session="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head> 
	<meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Assignment</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" type="text/css">
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
</head>
<body>
	<div class="">
		<!-- header-->
		<div>
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
		<!-- end header-->
		
		<div class="row">
			
			<!-- contents -->
			<div class="col-12">
				<tiles:insertAttribute name="contents"></tiles:insertAttribute>
			</div>
			<!-- end contents -->
		</div>
		
		<!-- footer -->
		<div class="col-10">
			<tiles:insertAttribute name="footers"></tiles:insertAttribute>
		</div>
		<!-- end footers -->
	</div>
	
	<!-- Js Plugins -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>  
    <script src="${ pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/popper.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/jquery.nice-select.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/jquery.slicknav.js"></script>
    <script src="${ pageContext.request.contextPath }/js/mixitup.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/owl.carousel.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/main.js"></script>
    <script src="${ pageContext.request.contextPath }/js/instascan.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>