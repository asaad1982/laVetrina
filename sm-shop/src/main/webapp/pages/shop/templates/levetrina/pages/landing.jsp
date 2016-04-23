<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 




			<c:if test="${page!=null}">
			<div class="row-fluid">
          		    <div class="span12">
          			    <span id="homeText"><c:out value="${page.description}" escapeXml="false"/></span>
          		    </div>
			</div>
			</c:if>
			<section class="category-section">
    <div class="container">
			<div class="row">  
			
			<c:forEach items="${categories}" var="subCategory">  
			
          <a href="<c:url value="/shop/category/${subCategory.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${subCategory.id}"/>">
                <div class="col-sm-2 col-sm-12 category-1 category-home padding-Ziro">
               		<c:url var="imageURL" value="/static/${requestScope.MERCHANT_STORE.code}/IMAGE/${subCategory.categoryImage}" />
               		
                    <img src='${imageURL}' >
                    <div class="category-tittle">
                        <span>${subCategory.description.name}</span>
                    </div>

                </div>
                </a>
                  </c:forEach>
                
        </div>
        </div>
    
    </section>
			<br/>
			
			
			<sm:shopProductGroup groupName="FEATURED_ITEM"/>
			<sm:shopProductGroup groupName="SPECIALS"/>
			
			<c:if test="${requestScope.FEATURED_ITEM!=null || requestScope.SPECIALS!=null}" >
			
			<section class="featured-section">
		<div class="container">
			<div class="row">
			
				
				<div class="col-sm-12 padding-right">
				
				
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">featured products</h2>
						
						<div id="recommended-item-carousel" class="" data-ride="carousel">
							<div class="carousel-inner">
							
							
										
	                         				
										<c:forEach items="${requestScope.FEATURED_ITEM}" var="product">
										<div class="item">
										
									<div class="col-sm-3">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<c:if test="${product.image!=null}"><a href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html"><img src="<sm:shopProductImage imageName="${product.image.imageName}" sku="${product.sku}"/>" width=255 height=128/></a></c:if>
													<h2><c:choose>
															<c:when test="${product.discounted}">
																<del><c:out value="${product.originalPrice}" /></del>&nbsp;<span class="specialPrice"><c:out value="${product.finalPrice}" /></span>
															</c:when>
															<c:otherwise>
																<c:out value="${product.finalPrice}" />
															</c:otherwise>
														</c:choose></h2>
													<p>${product.description.name}</p>
													<a href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html<sm:breadcrumbParam productId="${product.id}"/>"><s:message code="button.label.view" text="Details" /></a> <c:choose><c:when test="${requestScope.FEATURED==true}"><c:if test="${requestScope.CONFIGS['displayAddToCartOnFeaturedItems']==true}">/ <a class="addToCart" href="#" productId="${product.id}"><s:message code="button.label.addToCart" text="Add to cart" /></a></c:if></c:when><c:otherwise>/ <a class="addToCart" href="#" productId="${product.id}"><s:message code="button.label.addToCart" text="Add to cart" /></a></c:otherwise></c:choose>
												</div>
												
											</div>
										</div>
									</div>
						
								
										 </div>	
										</c:forEach>  
										
									
										
										
								
							</div>
									
						</div>
					</div><!--/recommended_items-->
					
				</div>
			</div>
		</div>
	</section>
	
	
	</c:if>
	
			


			
		