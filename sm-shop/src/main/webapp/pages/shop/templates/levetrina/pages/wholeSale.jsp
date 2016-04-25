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
 
<script>
function loadProducts(category) {

	var categoryValue = -1;
	if (category != null && category != -1) {
		categoryValue = category.value;
		if (categoryValue != null && categoryValue != '') {
			var url = "loadProducts?categoryId=" + categoryValue;
			$.ajax({
						type : "GET",
						url : url,
						success : function(data) {
							$('#productId').empty();
							$('#productId').append(new Option('<s:message code="label.wholeSale.chooseProduct" />',""));
							$.each(data, function(key, val) {
								$('#productId').append(new Option(val.name, val.id));
							});
						},
						dataType : 'json',
						cache : false,
						contentType : "application/json"
					});
		} else {
			$('#productId').empty();
			$('#productId').append(new Option('<s:message code="label.wholeSale.chooseProduct" />',""));
		}
	} else {
		$('#productId').empty();
		$('#productId').append(new Option('<s:message code="label.wholeSale.chooseProduct" />', ""));
	}
	return false;
}
</script>
<div id="main-content" class="container" style="padding: 15px 0;">
	<div class="bg">
	    	<div class="row">    		
	    		<div class="col-sm-12">
	    		<h2 class="title text-center"><s:message code="label.wholesale" text="Contact us"/></h2>
<c:url var="saleRequest" value="/shop/sale/wholeSale.html" />

<form:form action="${saleRequest}" method="POST" modelAttribute="saleRequest" id="saleRequest">

	<c:if test="${not empty successMsgCode}">
					<div class="alert-success">
						<s:message code="${successMsgCode}" text="success"/>
					</div>
	</c:if>

	<c:if test="${not empty msgCode}">
		<font color="red"><p class="alert-error">
			<s:message code="${msgCode}" text="error" />
		</p></font>
	</c:if>
	
	<div class="form-group col-md-6">
		<label class="required"><s:message code="label.wholeSale.customerName" text="Code" /></label>
		<div class="controls">
			<form:input id="customerName" path="customerName" cssClass="form-control"/>
			<div class="alert-error" id="customerName_error">
				<font color="red"><form:errors path="customerName" /></font>
			</div>
		</div>
	</div>
	
	<div class="form-group col-md-6">
		<label class="required"><s:message code="label.wholeSale.customerMobile" text="Code" /></label>
		<div class="controls">
			<form:input id="customerMobile" path="customerMobile" cssClass="form-control"/>
			<div class="alert-error" id="customerMobile_error">
				<font color="red"><form:errors path="customerMobile" /></font>
			</div>
		</div>
	</div>
	
	<div class="form-group col-md-6">
		<label class="required"><s:message code="label.wholeSale.customerEmail" text="Code" /></label>
		<div class="controls">
			<form:input id="customerEmail" path="customerEmail" cssClass="form-control"/>
			<div class="alert-error" id="customerEmail_error">
				<font color="red"><form:errors path="customerEmail" /></font>
			</div>
		</div>
	</div>

	<div class="form-group col-md-6">
		<label class="required"><s:message code="label.wholeSale.categoryName" text="Code" /></label>
		<div class="controls">
			<select id="categoryId" onchange="javascript:loadProducts(this);" style="width: 30%;" cssClass="form-control">
				<option value="-1">
					<s:message code="label.wholeSale.chooseCategory" text="Root" />
				</option>
				<c:forEach items="${categories}" var="cat" >
					<option value="${cat.id}">
						<c:out value="${cat.descriptions[0].name}" />
					</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="form-group col-md-6">
		<label class="required"><s:message code="label.wholeSale.productName" text="Code" /></label>
		<div class="controls">
			<form:select path="productId" id="productId" cssStyle="width: 30%;" cssClass="form-control">
				<form:option value="">
					<s:message code="label.wholeSale.chooseProduct" />
				</form:option>
				<form:options items="${products}" itemLabel="name" itemValue="id"></form:options>
			</form:select>
			<div class="alert-error" id="productId_error">
				<font color="red"><form:errors path="productId" /></font>
			</div>
		</div>
	</div>

	<div class="form-group col-md-6">
		<label class="required"><s:message code="label.wholeSale.quantity" text="Code" /></label>
		<div class="controls">
			<form:input id="quantity" path="quantity" cssClass="form-control"/>
			<div class="alert-error" id="quantity_error">
				<font color="red"><form:errors path="quantity" /></font>
			</div>
		</div>
	</div>

	<div class="form-actions">
		<button type="submit" class="btn btn-primary pull-right">
			<s:message code="btn.wholeSale.send" text="Submit" />
		</button>
	</div>

</form:form></div></div></div></div>

