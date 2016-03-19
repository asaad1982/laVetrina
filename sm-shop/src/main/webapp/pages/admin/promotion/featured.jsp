<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>				
				
<script>
	

	
</script>


<div class="tabbable">

  					
					<c:set value="${promotionId}" var="id" scope="request"/>
					<jsp:include page="/pages/admin/promotion/promotion-menu.jsp" />



  					<div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">

								
								


								<div class="sm-ui-component">
								
				<h3>
						<s:message code="label.product.offer.meassage" text="Offer's Product" />
				</h3>	
				<br/>
				<div class="alert alert-info">
					<s:message code="label.product.featured.meassage" text="Drag and drop product from product list to Offers items box"/>
				</div>			
		
      			<!-- Listing grid include -->
				 <c:set value="/admin/products/paging.html" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/promotion/featured/paging.html?id=${promotionId}" var="containerFetchUrl" scope="request"/>
				 <c:set value="/admin/promotion/featured/removeItem.html" var="containerRemoveUrl" scope="request"/>
				 <c:set value="FEATURED" var="removeEntity" scope="request"/>
				 <c:set value="/admin/promotion/featured/addItem.html" var="containerAddUrl" scope="request"/>
				
				 <c:set value="/admin/promotion/editPromotion.html?id=${promotionId}" var="editUrl" scope="request"/>
				 <c:set value="/admin/promotion/featured/list.html?id=${promotionId}" var="reloadUrl" scope="request"/>
				 <c:set var="componentTitleKey" value="menu.catalogue-featured" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/promotion/featured-gridHeader.jsp" scope="request"/>
				 <c:set var="gridHeaderContainer" value="/pages/admin/promotion/featured-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>

            	 <jsp:include page="/pages/admin/components/product-container.jsp"></jsp:include> 
				 <!-- End listing grid include -->


      					</div>
      					

      			     
      			     <c:url var="countriesPromotionSave" value="/admin/promotion/saveCountries.html"/>
						<form:form method="GET"  action="${countriesPromotionSave}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								
					                  <div class="form-actions">
                  						<div class="pull-right">
                  							<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  						</div>

            	 					</div>
            	 			</form:form>

      			     
      			     
    


   					</div>


  					</div>

				</div>