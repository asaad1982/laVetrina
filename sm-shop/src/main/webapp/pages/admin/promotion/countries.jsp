<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>				
				
<script>
	

	
</script>


<div class="tabbable">

  					
 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">
  					
  					<c:set value="${promotionId}" var="id" scope="request"/>
					<jsp:include page="/pages/admin/promotion/promotion-menu.jsp" />
  					<div class="tab-pane active" id="shipping-config">


								<div class="sm-ui-component">
								<h3><s:message code="label.promotion.countries"/></h3>	
								<br/>


								<c:url var="countriesPromotionSave" value="/admin/promotion/saveCountries.html"/>


								
							
							
							<br/>
							

							<!-- Listing grid include -->
				 			<c:set value="/admin/promotion/countries/paging.html?id=${promotionId}" var="pagingUrl" scope="request"/>
				 			<c:set value="/admin/promotion/countries/update.html?id=${promotionId}" var="updateUrl" scope="request"/>
				 			<c:set value="/admin/promotion/promotionCounties.html?id=${promotionId}" var="refreshUrl" scope="request"/>
				 			<c:set var="entityId" value="code" scope="request"/>
							<c:set var="componentTitleKey" value="label.promotion.countries" scope="request"/>
				 			<c:set var="gridHeader" value="/pages/admin/promotion/promotion-countries-gridHeader.jsp" scope="request"/>
							<c:set var="canRemoveEntry" value="false" scope="request"/>

            	 			<jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 			<!-- End listing grid include -->
							


      					</div>
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