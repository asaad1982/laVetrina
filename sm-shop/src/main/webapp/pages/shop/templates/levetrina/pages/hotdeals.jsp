
<%
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script
	src="<c:url value="/resources/js/jquery.elevateZoom-3.0.8.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>
<script language="JavaScript" src="http://www.geoplugin.net/javascript.gp" type="text/javascript"></script>



<jsp:include
	page="/pages/shop/templates/bootstrap/sections/breadcrumb.jsp" />
	<section class="inner-shop">
	<div class="container"> <div class="row"> 
	<div class="col-sm-3">
<section id="advertisement" class="shop-banner">
		<div class="container padding-Ziro">
			<img src="<c:url value="/resources/templates/levetrina/images/hotoffer.jpg" />" alt="">
		</div>
</section>

<section>
		<div class="container">
			<div class="row">
				
				<div class="col-sm-12 ">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Offers Items</h2>
						<c:forEach items="${promotions}" var="promotion">  
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product1.jpg" alt="">
										<c:choose>
										<c:when test="${promotion.bundlePromotion!=null}">
										
										<h2>${promotion.bundlePromotion.bundlePrice }</h2>
										<p>${promotion.bundlePromotion.productRelationship.code }</p>
										<p>Bundle Contains:
										<c:forEach items="${promotion.bundlePromotion.productRelationships}" var="p"> 
										
										     ${p.relatedProduct.sku},
										</c:forEach>
										</p>
										</c:when>
										</c:choose> 
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
										<c:choose>
										<c:when test="${promotion.bundlePromotion!=null}">
										
											<h2>${promotion.bundlePromotion.bundlePrice }</h2>
											<p>${promotion.bundlePromotion.productRelationship.code }</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
											</c:when>
										</c:choose> 
										</div>
									</div>
								</div>
                                <img src="<c:url value="/resources/templates/levetrina/images/special-offer.png" />" class="new" alt="">
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href=""><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
										<li><a href=""><i class="fa fa-plus-square"></i>Add to compare</a></li>
									</ul>
								</div>
							</div>
						</div>
						
						</c:forEach>
						<ul class="pagination">
							<li class="active"><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">»</a></li>
						</ul>
					</div><!--features_items-->
				</div>
			</div>
		</div>
	</section>