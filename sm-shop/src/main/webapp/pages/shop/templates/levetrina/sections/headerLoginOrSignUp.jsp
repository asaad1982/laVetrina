
<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<header id="header" class="big-header">
	<!--header-->
	<div class="header_top">
		<!--header_top-->
		<div class="header-top-color">
			<img
				src="<c:url value="/resources/templates/levetrina/images/color-brand.jpg" />">
		</div>
		<div class="container">
			<div class="row">
				<div class=" logo-section-header">
					<div class="logo pull-left">
						<a href="index.html"><img
							src="<c:url value="/resources/templates/levetrina/images/logo.png" />"
							alt="" /></a>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">

				<div class="col-sm-12 header-big-nav">
					<div class="header-icons col-md-12">
						<div class="social-icons pull-right">
							<div class="search_box">
								<input type="text" placeholder="Search">
							</div>
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li></li>
							</ul>
							<div class="btn-group pull-right">
								<div class="btn-group">
									<button type="button"
										class="btn btn-default dropdown-toggle usa"
										data-toggle="dropdown">
										عربي <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li class="lang-style"><a href="#">English</a></li>
										<li class="lang-style"><a href="#">عربي</a></li>
									</ul>
								</div>
							</div>

						</div>
					</div>
					<div class="header-menu-nav col-md-12">


						<div class="shop-menu pull-right">

							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-user"></i> Account</a></li>
								<li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li>
								<li><a href="checkout.html"><i class="fa fa-crosshairs"></i>
										Checkout</a></li>
								<li><a href="cart.html"><i class="fa fa-shopping-cart"></i>
										Cart</a></li>
								<li>
                                        <sec:authorize access="hasRole('AUTH_CUSTOMER')"> <form class="form-signin mg-btm" action="<c:url value='/shop/customer/j_spring_security_logout' />" method='POST'> <sec:authentication property="principal.username" />  <button type="submit" class="btn btn-default">Logout</button></form></sec:authorize> <sec:authorize access="!hasRole('AUTH_CUSTOMER')"> <a href="<c:url value='/shop/customer/loginOrSignUp.html' />" > Login <i class="fa fa-lock"></i> </a> </sec:authorize>
                                       
								</li>
							</ul>
						</div>

						<div class="call-to-actions-btn pull-right">
							<button type="button" class="btn offers-btn-style">HOT
								OFFERS !</button>
							<button type="button" class="btn call-btn-style">
								<img src="<c:url value="/resources/templates/levetrina/images/tele.png" />" />CALL US
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header_top-->
</header>
<!--/header-->