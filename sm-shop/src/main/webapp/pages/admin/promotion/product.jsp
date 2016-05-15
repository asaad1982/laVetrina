<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="false" %>			
<script type="text/javascript">
var priceFormatMessage = '<s:message code="message.price.cents" text="Wrong format" />';
</script>

    <link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
	<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.formatCurrency-1.4.0.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
	<script src="<c:url value="/resources/js/adminFunctions.js" />"></script>
	
	
	
<script type="text/javascript">

	
	$(function(){
		$('#sku').alphanumeric();
		$('#productPriceAmount').numeric({allow:"."});
		$('#quantity').numeric();
		$('#ordermin').numeric();
		$('#ordermax').numeric();
		$('#order').numeric();
		$('#weight').numeric({allow:"."});
		$('#width').numeric({allow:"."});
		$('#length').numeric({allow:"."});
		$('#hight').numeric({allow:"."});
	
	});

	function loadProducts(category) {

		var categoryValue = -1;
		if (category != null && category != -1) {
			categoryValue = category.value;
			if (categoryValue != null && categoryValue != '') {
				var url = "/sm-shop/admin/promotion/loadProducts?categoryId=" + categoryValue;
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

	
	
</script>
	
				
<div class="tabbable">


					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">


								<div class="sm-ui-component">
								
								
								<c:if test="${cartPromotion.promotion.id!=null && cartPromotion.promotion.id>0}">
									<c:set value="${cartPromotion.promotion.id}" var="id" scope="request"/>
									
									<jsp:include page="/pages/admin/promotion/promotion-menu.jsp" />
								</c:if>	
								
								
				<h3>
					Cross Selling 
					
				</h3>	
				<br/>
				
				

      					<c:url var="promotionSave" value="/admin/promotion/saveupSellingPromotion.html"/>
                        <form:form method="POST" enctype="multipart/form-data" commandName="upSellingPromotion" action="${promotionSave}">

                            <form:errors path="*" cssClass="alert alert-error" element="div" />
                            <div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
                            <div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>

                       
						<form:hidden path="id" />
                 	

                  		
                  		<div class="control-group">
						<label class="required"><s:message
								code="label.wholeSale.categoryName" text="Code" /></label> 
								<div class="controls">
								<select
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
					</div></div>


						<div class="control-group">
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
								
							</div>
						</div>
					</div>
					
					<div class="control-group">
                        	<label>Conditions</label>
                          	<div class="controls">
                          		      <form:select   path="conditions" cssClass="highlight">
                          		      <form:option value="-1"><s:message code="label.promotion.select.status"/></form:option>
                          		      <form:option value="1">Price</form:option>
                          		      <form:option value="2">Weight</form:option>
                          		      <form:option value="3">Item number</form:option>
                          		       </form:select>
	                                  <span class="help-inline"><form:errors path="conditions" cssClass="error" /></span>
                          	</div>
                    	</div>
                    	<div class="control-group">
                        	<label>Factor</label>
                          	<div class="controls">
                          		      <form:input cssClass="input-large highlight"  path="factor" maxlength="6"/>
                                          <span class="help-inline"><form:errors path="factor" cssClass="error" /></span>
                          	</div>
                    	</div>
                    	
	                  	
                    	</div>
                    	<div class="control-group">
                        	<label>Value</label>
                          	<div class="controls">
                          		      <form:input cssClass="input-large highlight"  path="value" maxlength="6"/>
                                          <span class="help-inline"><form:errors path="value" cssClass="error" /></span>
                          	</div>
                    	</div>
                    	
                    
                  	
                       <form:hidden path="promotion.id"/>

                   <div class="form-actions">
                            <div class="pull-right">
                                    <button type="submit" class="btn btn-success"><s:message code="button.label.submit2" text="Submit"/></button>
                            </div>
                   </div>
                   
                   

                   


                   

                 

 

 

                                   

                        </form:form>
                        
                  
              
                   
                   
                   
                   
                   	    
                        
      				</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>