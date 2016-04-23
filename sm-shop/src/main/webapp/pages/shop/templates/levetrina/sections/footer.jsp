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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<footer id="footer"><!--Footer-->
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="companyinfo">
						<div class="logo pull-left footer-logo">
							<a href="<c:url value="/shop/"/>">
							<c:choose>
								<c:when test="${not empty requestScope.MERCHANT_STORE.storeLogo}">
	                			<img class="logoImage" src="<sm:storeLogo/>" width="100" height="100"/>
	                		</c:when>
	                		<c:otherwise>
	                			<h1>
	                			<a href="<c:url value="/shop/"/>">
	                				<c:out value="${requestScope.MERCHANT_STORE.storename}"/>
	                			</a>  
	                			</h1>
	                		</c:otherwise>
	                	  </c:choose>
							</a>
						  </div>
							
						</div>
					</div>
					<div class="col-sm-5">
				        <form action="#" class="searchform">
                            <p>subscribe to get our best offers !</p>
								<input type="text" placeholder="Your email address">
								<button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
								
							</form>
					</div>
					<div class="col-sm-3">
						<div class="address">
							<img src="<c:url value="/resources/templates/levetrina/images/map.png" />" alt="">
							<p>505 S Dummy ( Egypt )</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		 <div class="footer-top-color ">
                <img src="<c:url value="/resources/templates/levetrina/images/color-brand.jpg" />">
             </div>
	
		
	</footer>