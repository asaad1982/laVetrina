<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>


<section id="cart_items" style="padding: 15px 0;">
<c:url value="/shop/cart/removeShoppingCartItem.html"
	var="removeShoppingCartItemUrl" />



<div id="main-content" class="container">



<h2 class="title text-center"><s:message code="label.cart.revieworder" text="Review your order" /></h2>
<div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error.shoppingcart.update" text="An error occurred while updating the shopping cart"/></div>
<br/>
<div class="table-responsive cart_info">
<table id="mainCartTable" class="table table-condensed">

	<c:if test="${not empty cart}">
	   <c:choose>
	     <c:when test="${not empty cart.shoppingCartItems}">

			<c:forEach items="${cart.shoppingCartItems}" var="shoppingCartItem"
				varStatus="itemStatus">
				<c:if test="${itemStatus.index eq 0}">
					<thead>
						<tr class="cart_menu">
							<th colspan="2" width="55%"><s:message code="label.generic.item.title" text="Item"/></th>
							<th colspan="2" width="15%"><s:message code="label.quantity" text="Quantity"/></th>
							<th width="15%"><s:message code="label.generic.price" text="Price"/></th>
							<th width="15%"><s:message code="label.order.total" text="Total"/></th>
						</tr>
					</thead>
					<tbody>
				</c:if>
				<form:form action="${updateShoppingCartItemUrl}"
					id="shoppingCartLineitem_${shoppingCartItem.id}">
					<tr>
						<td  class="cart_product">
							<c:if test="${shoppingCartItem.image!=null}">
								<img  src="<c:url value="${shoppingCartItem.image}"/>">
							</c:if>
						</td>

						<td style="border-left:none;" class="cart_description">
								<h4>${shoppingCartItem.name}</h4>
								<c:if test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
									<br/>
									<ul>
										<c:forEach items="${shoppingCartItem.shoppingCartAttributes}" var="option">
										<li>${option.optionName} - ${option.optionValue}</li>
										</c:forEach>
									</ul>
								</c:if>
						
						</td>
						<td class="cart_quantity_button">
							<input type="text" class="input-small" placeholder="<s:message code="label.quantity" text="Quantity"/>"
							value="${shoppingCartItem.quantity}" name="quantity" id="${shoppingCartItem.id}" <c:if test="${shoppingCartItem.productVirtual==true}">readonly</c:if>>
						</td>
						

						<td class="cart_quantity_button"><strong>${shoppingCartItem.price}</strong></td>
						<td class="cart_total"><strong>${shoppingCartItem.subTotal}</strong></td>
						<td style="border-left:none;"class="cart_delete"><a class="cart_quantity_delete" href="#"
								onclick="javascript:updateLineItem('${shoppingCartItem.id}','${removeShoppingCartItemUrl}');">&times;</a>
						</td>

						<input type="hidden" name="lineItemId" id="lineItemId"
							value="${shoppingCartItem.id}"/>


					</tr>
				</form:form>


			</c:forEach>
			<c:forEach items="${cart.totals}" var="total">
				<tr class="subt">
					<td colspan="2">&nbsp;</td>
					<td colspan="3"><strong><s:message code="${total.code}" text="label [${total.code}] not found"/></strong></td>
					<td><strong><sm:monetary value="${total.value}" /></strong></td>
				</tr>
			</c:forEach>

		</c:when>
		 <c:otherwise>
		   <tr><td><s:message code="cart.empty" text="Your Shopping cart is empty" /></td></tr>
		 </c:otherwise>
	   </c:choose>
	</c:if>


	</tbody>
</table></div>
<c:if test="${not empty cart}">
	<c:if test="${not empty cart.shoppingCartItems}">
		<div class="pull-right">
			<div class="form-actions">
				<button type="button" class="btn" onClick="javascript:updateCart('#mainCartTable');"><s:message code="label.order.recalculate" text="Racalculate"/></button>
				<button id="checkoutButton" type="submit" class="btn btn-primary"><s:message code="label.cart.placeorder" text="Place your order" /></button>
			</div>
		</div>
	</c:if>
</c:if>

</div>
<c:if test="${empty cart}">
<!-- load cart with cookie -->
<script>
  $(document).ready(function(){
		var cartCode=getCartCode();
		if(cartCode!=null) {
			console.log('cart code ' + cartCode);
			location.href='<c:url value="/shop/cart/shoppingCartByCode.html" />?shoppingCartCode=' + cartCode;
		}

   });
</script>
</c:if>

<script>
  $(document).ready(function(){		
	    $('.quantity').numeric();
	    $("input[type='text']").keypress(function(e){
	        if (e.which == 13){
	        	e.preventDefault();	        	
	        }
	    });
	    $('#checkoutButton').click(function(e) {
	    	location.href='<c:url value="/shop/order/checkout.html"/>';
	    });
   });
</script>
</section>

