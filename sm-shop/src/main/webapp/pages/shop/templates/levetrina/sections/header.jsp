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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<script src="<c:url value="/resources/js/hogan.js" />"></script>
<script src="<c:url value="/resources/js/typeahead.min.js" />"></script>
<script type="text/javascript">

$(document).ready(function() { 

	$('#searchField').typeahead({
		name: 'shopizer-search',
		<c:if test="${requestScope.CONFIGS['useDefaultSearchConfig'][requestScope.LANGUAGE.code]==true}">
		  <c:if test="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]!=null}">
		prefetch: '<c:out value="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]}"/>',
		  </c:if>
	    </c:if>


	    remote: {
    		url: '<c:url value="/services/public/search/${requestScope.MERCHANT_STORE.code}/${requestScope.LANGUAGE.code}/autocomplete.html"/>?q=%QUERY',
        	filter: function (parsedResponse) {
            	// parsedResponse is the array returned from your backend
            	console.log(parsedResponse);

            	// do whatever processing you need here
            	return parsedResponse;
        	}
    	},
		template: [
		'<p class="name">{{name}}</p>',
		'<p class="description">{{description}}</p>'
		].join(''),
		engine: Hogan
		});
	
	
	

});

</script>
<header id="header" class="big-header"><!--header-->
		<div class="header_top"><!--header_top-->
            <div class="header-top-color">
                <img src="<c:url value="/resources/templates/levetrina/images/color-brand.jpg" />">
             </div>
            	<div class="container">
				<div class="row">
					<div class=" logo-section-header">
					   <div class="logo pull-left">
							<a href="index.html"><c:choose>
	                		<c:when test="${not empty requestScope.MERCHANT_STORE.storeLogo}">
	                			<img class="logoImage" src="<sm:storeLogo/>"/>
	                		</c:when>
	                		<c:otherwise>
	                			<h1>
	                			<a href="<c:url value="/shop/"/>">
	                				<c:out value="${requestScope.MERCHANT_STORE.storename}"/>
	                			</a>  
	                			</h1>
	                		</c:otherwise>
	                	  </c:choose></a>
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
                                             <div id="searchGroup">
											<form id="searchForm" class="form-inline" method="post" action="<c:url value="/shop/search/search.html"/>">
												<input id="searchField" class="tt-query" name="q" type="text" placeholder="<s:message code="label.search.searchQuery" text="Search query" />" autocomplete="off" spellcheck="false" dir="auto" value="<c:out value="${q}"/>">
												<button id="searchButton" class="btn" type="submit"><s:message code="label.generic.search" text="Search" /></button>
											</form>
										</div>
                                        </div>
                                    <ul class="nav navbar-nav">
                                    
                                    	<c:if test="${requestScope.CONFIGS['facebook_page_url'] != null || requestScope.CONFIGS['twitter_handle'] != null}">
							             
							             <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
							                   <li>  <a href="<c:out value="${requestScope.CONFIGS['facebook_page_url']}"/>"><i class="fa fa-facebook"></i></a></li>
							             </c:if>
							             <c:if test="${requestScope.CONFIGS['twitter_handle'] != null}">
							                    <li>  <a href="<c:out value="${requestScope.CONFIGS['twitter_handle']}"/>"><i class="fa fa-twitter "></i></a></li>
							             </c:if>
						            </c:if>
                                    	
                                       
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                       <li>
                                      
                                     </li>
                                    </ul>
                                         <div class="btn-group pull-right">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                                    عربي
                                                    <span class="caret"></span>
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
                                        <li><a href="<c:url value="/shop/order/checkout.html"/>"><i class="fa fa-crosshairs"></i> Checkout</a></li>
                                        <c:if test="${not fn:contains(requestScope['javax.servlet.forward.servlet_path'], 'order') && not fn:contains(requestScope['javax.servlet.forward.servlet_path'], 'cart')}">
                                        <li class="fa fa-shopping-cart">
                                        
                                        
                                        <div id="miniCart" style="padding-top: 8px;padding-bottom:10px;" class="btn-group pull-right">
            					&nbsp;&nbsp;&nbsp;
            					<i class="icon-shopping-cart icon-black"></i>
            					<a style="box-shadow:none;color:FF8C00;" href="#" data-toggle="dropdown" class="open noboxshadow dropdown-toggle" id="open-cart"><s:message code="label.mycart" text="My cart"/></a>
								<jsp:include page="/pages/shop/common/cart/minicartinfo.jsp" />
            				
		            			<ul class="dropdown-menu minicart" id="minicartComponent">
		              				<li>
										<jsp:include page="/pages/shop/common/cart/minicart.jsp" />
		              				</li>	
		            			</ul>
					                 </div>
                                        </li>
                                        </c:if>
                                        <li><c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
					<sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
						<!-- logged in user -->
						<c:if test="${requestScope.CUSTOMER!=null}">
							<ul class="logon-box pull-right">
							<li id="fat-menu" class="dropdown">
							<a class="dropdown-toggle noboxshadow" data-toggle="dropdown" href="#">
							   <s:message code="label.generic.welcome" text="Welcome" /> 
							   <c:if test="${not empty requestScope.CUSTOMER.billing.firstName}">
							       <c:out value="${sessionScope.CUSTOMER.billing.firstName}"/>
							   </c:if><b class="caret"></b>
							 </a>
								<ul class="dropdown-menu">
									<li>
										<a onClick="javascript:location.href='<c:url value="/shop/customer/dashboard.html" />';" href="#"><i class="fa fa-user"></i><s:message code="label.customer.myaccount" text="My account"/></a>
									</li>
									<li class="divider"></li>
									<li>
										<a onClick="javascript:location.href='<c:url value="/shop/customer/j_spring_security_logout" />';" href="#"><i class="fa fa-power-off"></i><s:message code="button.label.logout" text="Logout"/></a>
									</li>
								</ul>
							</li>
							</ul>
						</c:if>
					</sec:authorize>
					<sec:authorize access="!hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
						<!-- no dual login -->
						<ul class="logon-box pull-right">
							<li>
								<s:message code="label.security.loggedinas" text="You are logged in as"/> [<sec:authentication property="principal.username"/>]. 
							</li>
						</ul>
					</sec:authorize>
					<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
					<!-- login box -->
					<ul class="pull-right" style="list-style-type: none;padding-top: 8px;z-index:500000;">
					  <li id="fat-menu" class="dropdown">
					    <a href="#" id="signinDrop" role="button" class="dropdown-toggle noboxshadow" data-toggle="dropdown"><s:message code="button.label.signin" text="Signin" /><b class="caret"></b></a>
					
					
							<div id="signinPane" class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
								<div id="loginError" class="alert alert-error" style="display:none;"></div>
								<!-- form id must be login, form fields must be userName, password and storeCode -->
								<form id="login" method="post" accept-charset="UTF-8">
									<div class="control-group">
	                        				<s:message code="label.username" text="Username"  var="username"/>
												<input id="signin_userName" style="margin-bottom: 15px;" type="text" name="userName" size="30" required="required" placeholder='${username }' class="form-control"/>
											
									</div>
									<div class="control-group">
	                        					<s:message code="label.password" text="Password" var="password"/>
												<input id="signin_password" style="margin-bottom: 15px;" type="password" name="password" size="30" required="required" placeholder='${password }' class="form-control"/>
											
									</div>
									<input id="signin_storeCode" name="storeCode" type="hidden" value="<c:out value="${requestScope.MERCHANT_STORE.code}"/>"/>					 
									<button type="submit" style="width:100%" class="btn btn-large" id="login-button"><s:message code="button.label.login" text="Login" /></button>
									
								</form>
								<a onClick="javascript:location.href='<c:url value="/shop/customer/registration.html" />';" href="" role="button" class="" data-toggle="modal"><s:message code="label.register.notyetregistered" text="Not yet registered ?" /></a>
							</div>
					  </li>
					</ul>
					</sec:authorize>
					</c:if></li>
                                    </ul>
						              </div>
                                
                                 <div class="call-to-actions-btn pull-right">
                                     <button type="button" class="btn offers-btn-style">HOT OFFERS !</button>
                                      <button  type="button" onclick='location.href="<c:url value="/shop/store/contactus.html"/>"' class="btn call-btn-style" >
                                            <img src="<c:url value="/resources/templates/levetrina/images/tele.png" />"><s:message code="label.contactus.menu"/></button>
                                </div>
                            </div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
	
    </header>

			