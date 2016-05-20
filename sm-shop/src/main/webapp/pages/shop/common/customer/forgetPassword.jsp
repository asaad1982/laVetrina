
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

<!-- requires functions.jsp -->
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/shop-customer.js" />"></script>
<script src="<c:url value="/resources/js/address.js" />"></script>

<c:set var="forgetPassword_url"
	value="${pageContext.request.contextPath}/shop/customer/forgetPassword.html" />

<div id="main-content" class="container" style="padding: 15px 0;">
	<div class="bg">
		<div class="row">
			<div class="col-sm-12">
				<h2 class="title text-center">
					<s:message code="label.forgetPassword" text="Contact us" />
				</h2>
				<form:form method="post" action="${forgetPassword_url}"
					id="registrationForm" commandName="customer">
					<form:errors path="*"
						cssClass="alert alert-error alert-danger form-group" element="div" />

					<c:if test="${not empty successMsgCode}">
						<div class="alert alert alert-success">
							<s:message code="${successMsgCode}" text="success" />
						</div>
					</c:if>

					<c:if test="${not empty msgCode}">
						<div class="alert alert-error alert-danger">
								<s:message code="${msgCode}" text="error" />
							</div>
					</c:if>
					
					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.forgetPassword.emailAddress" text="Code" /></label>
						<div class="controls">
							<s:message code="NotEmpty.customer.emailAddress"
								text="Email address is required" var="msgEmail" />
							<form:input path="emailAddress"
								cssClass="span8 required email form-control form-control-md"
								id="email" title="${msgEmail}" placeholder="${emailMsg}" />
<%-- 							<font color="red"> <form:errors path="emailAddress" --%>
<%-- 									cssClass="error" /></font> --%>
						</div>
					</div>

					<div class="form-actions">
						<button type="submit" class="btn btn-primary pull-right"><s:message code="btn.forgetPassword.send"
								text="code" /></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
