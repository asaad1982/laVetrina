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
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script>

	$(document).ready(function(){
		resetErrors();
	});

	function resetErrors(){
		$("#quantityEmptyError").hide();
		$("#quantityLetterError").hide();
		$("#productEmptyError").hide();
		
	}
	
	$(document).on('click', 'button.removebutton', function () {
		
		var product = $(this).closest('tr').find('td:eq(0)').text();
		var productId = $(this).parent().prev().prev().find("input[type='hidden']").val();
		var quantity = $(this).parent().prev().text();
	    $(this).closest('tr').remove();
	    populateProduct('delete', productId, quantity);
	    return false;
	});
	

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
							$('#productId').append(new Option('<s:message code="label.wholeSale.chooseProduct" />',	""));
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
				$('#productId').append(new Option('<s:message code="label.wholeSale.chooseProduct" />', ""));
			}
		} else {
			$('#productId').empty();
			$('#productId').append(new Option('<s:message code="label.wholeSale.chooseProduct" />',	""));
		}
		return false;
	}

	function addProduct() {

		if ($('#productId :selected').val() == '' || $('#productId :selected').val() == 0){
			resetErrors();
			$("#productEmptyError").show();
			
		} else if($('#quantity').val() == '' || $('#quantity').val() == 0){
			resetErrors();
			$("#quantityEmptyError").show();
			
		} else if($("#quantity").val() != null && $("#quantity").val() != '' && ! /^[0-9]+$/.test($("#quantity").val()) ){
			resetErrors();
			$("#quantityLetterError").show();
			
		} else {
			resetErrors();
			var productId = $('#productId').val();
			var quantity = $('#quantity').val();
			
			var rows = "";
			rows += "<tr><td>"
					+ $('#productId :selected').text()
					+ "<input type='hidden' id='pId' name='pId' value='" + productId + "' ></td><td>"
					+ quantity
					+ "</td><td><button class='btn removebutton'><img src='/sm-shop/resources/img/admin/remove.png'></button></td></tr>";
			rows += "";
			$(rows).appendTo("#productTable tbody");
			
			populateProduct('add', productId, quantity);
			
		}
	}
	
	function populateProduct(action, productId, quantity) {
		
		var url = "populateProduct?productId=" + productId + "&quantity=" + quantity + "&action=" + action;
		$.ajax({
				type : "GET",
				url : url,
				dataType : 'json',
				cache : false,
				contentType : "application/json"
			});
		
	}
</script>

<div id="main-content" class="container" style="padding: 15px 0;">
	<div class="bg">
		<div class="row">
			<div class="col-sm-12">
				<h2 class="title text-center">
					<s:message code="label.wholesale" text="Contact us" />
				</h2>
				<c:url var="submitSaleRequest" value="/shop/sale/wholeSale.html" />

				<form:form action="${submitSaleRequest}" method="POST"
					modelAttribute="saleRequestForm" id="saleRequestForm">

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
								code="label.wholeSale.customerName" text="Code" /></label>
						<div class="controls">
							<form:input id="customerName" path="customerName"
								cssClass="form-control" />
							<div class="alert-error" id="customerName_error">
								<font color="red"><form:errors path="customerName" /></font>
							</div>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.wholeSale.customerMobile" text="Code" /></label>
						<div class="controls">
							<form:input id="customerMobile" path="customerMobile"
								cssClass="form-control" />
							<div class="alert-error" id="customerMobile_error">
								<font color="red"><form:errors path="customerMobile" /></font>
							</div>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.wholeSale.customerEmail" text="Code" /></label>
						<div class="controls">
							<form:input id="customerEmail" path="customerEmail"
								cssClass="form-control" />
							<div class="alert-error" id="customerEmail_error">
								<font color="red"><form:errors path="customerEmail" /></font>
							</div>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message
								code="label.wholeSale.categoryName" text="Code" /></label> <select
							id="categoryId" onchange="javascript:loadProducts(this);"
							class="form-control">
							<option value="-1">
								<s:message code="label.wholeSale.chooseCategory" text="Root" />
							</option>
							<c:forEach items="${categories}" var="cat">
								<option value="${cat.id}">
									<c:out value="${cat.descriptions[0].name}" />
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message code="label.wholeSale.productName" text="Code" /></label>
						<div class="controls">
							<select id="productId" class="form-control">
								<option value="">
									<s:message code="label.wholeSale.chooseProduct" />
								</option>
								<c:forEach items="${products}" var="product">
									<option value="${product.id}">
										<c:out value="${product.name}" />
									</option>
								</c:forEach>
							</select>
							<div class="alert-error" id="productEmptyError">
								<font color="red"><s:message
								code="validation.wholeSale.productId.required"
								text="required" /></font>
							</div>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="required"><s:message code="label.wholeSale.quantity" text="Code" /></label>
						<div class="controls">
							<input id="quantity" class="form-control" />
							<div class="alert-error" id="quantityEmptyError">
								<font color="red"><s:message
										code="validation.wholeSale.quantity.required"
										text="Please Enter quantity" /></font>
							</div>
							<div class="alert-error" id="quantityLetterError">
								<font color="red"><s:message
										code="validation.wholeSale.quantity.invalidFormat"
										text="numbers only" /></font>
							</div>
						</div>
					</div>

					<div class="form-group col-md-6">
						<div class="controls">
							<table id="productTable"
								class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><s:message code="label.wholeSale.productName" /></th>
										<th><s:message code="label.wholeSale.quantity" /></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty  productSaleRequests}">
										<c:forEach items="${productSaleRequests}"	var="productSaleRequest">
											<tr>
												<td><c:out value="${productSaleRequest.productId}"></c:out></td>
												<td><c:out value="${productSaleRequest.quantity}"></c:out></td>
												<td><button class="btn removebutton"><img src="/sm-shop/resources/img/admin/remove.png"></button></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>

					<div class="form-group col-md-6">
						<div class="controls">
							<a class="btn btn-primary fa fa-shopping-cart" id="addProductBtn"
								href="javascript:addProduct()" > <s:message	code="btn.wholeSale.add" />
							</a>
						</div>
					</div>
					
					<div class="form-group col-md-12">
						<label class="required"><s:message code="label.wholeSale.messageBody" text="Code" /></label>
						<div class="controls">
							<form:textarea id="messageBody" path="messageBody" cssClass="form-control" />
							<div class="alert-error" id="messageBody_error">
								<font color="red"><form:errors path="messageBody" /></font>
							</div>
						</div>
					</div>


					<div class="form-actions">
						<button type="submit" class="btn btn-primary pull-right">
							<s:message code="btn.wholeSale.send" text="Submit" />
						</button>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</div>

