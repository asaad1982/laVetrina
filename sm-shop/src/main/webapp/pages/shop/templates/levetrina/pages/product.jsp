
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



<jsp:include
	page="/pages/shop/templates/bootstrap/sections/breadcrumb.jsp" />
	<section class="inner-shop">
	<div class="container"> <div class="row"> 
	<div class="col-sm-3">
<div class="left-sidebar">
	<h2>Category</h2>
	<div class="panel-group category-products" id="accordian">
		<!--category-productsr-->
		
		<c:forEach items="${categories}" var="category">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordian" onclick="getChildNode(${category.id})"
						href="#${category.description.name}"> <span class="badge pull-right"><i
							class="fa fa-plus"></i></span> ${category.description.name}
					</a>
				</h4>
			</div>

		</div>
		<div id="${category.description.name}" class="panel-collapse collapse">
				<div class="panel-body">
					
				</div>
			</div>
		
		</c:forEach>
		
			
		</div>

		
	
	<!--/category-products-->
	<c:if test="${fn:length(manufacturers) > 0}">
	<div class="brands_products">
		<!--brands_products-->
		<h2>Brands</h2>
		<div class="brands-name">
			<ul class="nav nav-pills nav-stacked">
			
				<c:forEach items="${manufacturers}" var="manufacturer">
					<li><a
						href="javascript:filterCategory('BRAND','${manufacturer.id}')">
							<span class="pull-right">${manufacturer.productCount}</span> <c:out
								value="${manufacturer.description.name}" />
					</a></li>
				</c:forEach>

			</ul>
		</div>
	</div></c:if>
	<!--/brands_products-->

	<div class="price-range">
		<!--price-range-->
		<h2>Price Range</h2>
		<div class="well">
			<div class="slider slider-horizontal" style="width: 174px;">
				<div class="slider-track">
					<div class="slider-selection"
						style="left: 41.6667%; width: 33.3333%;"></div>
					<div class="slider-handle round left-round" style="left: 41.6667%;"></div>
					<div class="slider-handle round" style="left: 75%;"></div>
				</div>
				<div class="tooltip top" style="top: -30px; left: 68px;">
					<div class="tooltip-arrow"></div>
					<div class="tooltip-inner">250 : 450</div>
				</div>
				<input type="text" class="span2" value="" data-slider-min="0"
					data-slider-max="600" data-slider-step="5"
					data-slider-value="[250,450]" id="sl2">
			</div>
			<br> <b>$ 0</b> <b class="pull-right">$ 600</b>
		</div>
	</div>
	<!--/price-range-->

	<div class="shipping text-center">
		<!--shipping-->
		<img src="images/ads.jpg" alt="">
	</div>
	<!--/shipping-->

</div></div>

<div class="col-sm-9 padding-right">

	<div itemscope class="product-details"
		itemtype="http://data-vocabulary.org/Product">
		<!-- Image column -->
		<div class="col-sm-5">
		<div id="img" class="view-product">
			<c:if test="${product.image!=null}">
				<span id="mainImg"><img
					id="im-<c:out value="${product.image.id}"/>"
					alt="<c:out value="${product.description.name}"/>"
					src="<c:url value="${product.image.imageUrl}"/>"
					data-zoom-image="<sm:shopProductImage imageName="${product.image.imageName}" sku="${product.sku}" size="LARGE"/>"></span>
				<script>
								$(function() {
									setImageZoom('im-<c:out value="${product.image.id}"/>');
								});	
							</script>
				
			</c:if>
		</div>
		
		<c:if test="${product.images!=null && fn:length(product.images)>1}">
					<ul id="imageGallery" class="thumbnails small">
						<c:forEach items="${product.images}" var="thumbnail">
							<li class="span2"><a href="#img" class="thumbImg"
								title="<c:out value="${thumbnail.imageName}"/>"><img
									id="im-<c:out value="${thumbnail.id}"/>"
									src="<c:url value="${thumbnail.imageUrl}"/>"
									data-zoom-image="<sm:shopProductImage imageName="${thumbnail.imageName}" sku="${product.sku}" size="LARGE"/>"
									alt="<c:url value="${thumbnail.imageName}"/>"></a></li>
						</c:forEach>
					</ul>
				</c:if>
		
		
		</div>

		<!-- Google rich snippets (http://blog.hubspot.com/power-google-rich-snippets-ecommerce-seo-ht) -->
		<!-- Product description column -->
		<div class="col-sm-7">
		<div class="product-information">
			<p class="lead">
				<strong>${product.description.name}</strong>
			</p>

		<address>
		<strong><s:message
						code="label.product.web.code" text="Product code" /></strong> <span
					itemprop="identifier" content="mpn:${product.sku}">${product.sku}</span><br>
				
			</address>
			<!-- product rating -->
			<jsp:include page="/pages/shop/common/catalog/rating.jsp" />


			
			<span itemprop="offerDetails" itemscope
				itemtype="http://data-vocabulary.org/Offer">
				<meta itemprop="seller"
					content="${requestScope.MERCHANT_STORE.storename}" />
				<meta itemprop="currency"
					content="<c:out value="${requestScope.MERCHANT_STORE.currency.code}" />" />
				<h3 id="productPrice">
					<c:choose>
						<c:when test="${product.discounted}">
							<del>
								<c:out value="${product.originalPrice}" />
							</del>&nbsp;<span class="specialPrice"><span itemprop="price"><c:out
										value="${product.finalPrice}" /></span></span>
						</c:when>
						<c:otherwise>
							<span itemprop="price"><c:out
									value="${product.finalPrice}" /></span>
						</c:otherwise>
					</c:choose>
				</h3> 
				<p>
				<jsp:include page="/pages/shop/common/catalog/addToCartProduct.jsp" />
			</p>
			</span>
			
			
			<c:if test="${not product.productVirtual}">
					<address>
						<strong><s:message code="label.product.web.available"
								text="Availability" /></strong> <span><c:choose>
								<c:when test="${product.quantity>0}">
									<span itemprop="availability" content="in_stock">${product.quantity}</span>
								</c:when>
								<c:otherwise>
									<span itemprop="availability" content="out_of_stock"><s:message
											code="label.product.outofstock" text="Out of stock" />
								</c:otherwise>
							</c:choose></span><br>
					</address>
				</c:if>
				<address>
				<strong><s:message code="label.product.brand" text="Brand" /></strong>
				<span itemprop="brand"><c:out
						value="${product.manufacturer.description.name}" /></span><br> 
				</address>
		</div>

	</div></div>
</div>
<div class="category-tab shop-details-tab">
	<div class="col-sm-12">

		<ul class="nav nav-tabs" id="productTabs">
			<li class="active"><a href="#description"><s:message
						code="label.productedit.productdesc" text="Product description" /></a></li>
			<c:if test="${attributes!=null}">
				<li><a href="#specifications"><s:message
							code="label.product.attribute.specifications"
							text="Specifications" /></a></li>
			</c:if>
			<li><a href="#reviews"><s:message
						code="label.product.customer.reviews" text="Customer reviews" /></a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="description">
				<c:out value="${product.description.description}" escapeXml="false" />
			</div>
			<div class="tab-pane" id="specifications">
				<!--  read only properties -->
				<c:if test="${attributes!=null}">
					<table>
						<c:forEach items="${attributes}" var="attribute"
							varStatus="status">
							<tr>
								<td><c:out value="${attribute.name}" /> :</td>
								<td><c:out value="${attribute.readOnlyValue.description}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<div class="tab-pane" id="reviews">

				<!-- reviews -->
				<jsp:include page="/pages/shop/common/catalog/reviews.jsp" />


			</div>
		</div>
		<br /> <br />
		<!-- Related items -->
		<c:if test="${relatedProducts!=null}">
			<h1>
				<s:message code="label.product.related.title" text="Related items" />
			</h1>
			<ul class="thumbnails product-list">
				<!-- Iterate over featuredItems -->
				<c:set var="ITEMS" value="${relatedProducts}" scope="request" />
				<jsp:include
					page="/pages/shop/templates/bootstrap/sections/productBox.jsp" />
			</ul>
		</c:if>


	</div>
</div></div></div>
</section>


<script>
			
			$(function () {
				$('#productTabs a:first').tab('show');
				$('#productTabs a').click(function (e) {
					e.preventDefault();
					$(this).tab('show');
				})
				$('.thumbImg').click(function (e) {
					img = $(this).find('img').clone();
					$('#mainImg').html(img);
					setImageZoom(img.attr('id'));
				})
			})

			<!-- lens plugin -->
			function setImageZoom(id) {
			    $('#' + id).elevateZoom({
		    			zoomType	: "lens",
		    			lensShape : "square",
		    			lensSize    : 240
		   		}); 
			}
			
			
		</script>

