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

	

	
	
</script>
	
				
<div class="tabbable">


					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">


								<div class="sm-ui-component">
								
								
								<c:if test="${promotion.id!=null && promotion.id>0}">
									<c:set value="${promotion.id}" var="id" scope="request"/>
									
									<jsp:include page="/pages/admin/promotion/promotion-menu.jsp" />
								</c:if>	
								
								
				<h3>
					<c:choose>
						<c:when test="${promotion.id!=null && promotion.id>0}">
								<s:message code="label.promotion.edit" text="Edit product" /> <c:out value="${promotion.id}"/>
						</c:when>
						<c:otherwise>
								<s:message code="label.promotion.add" text="Create product" />
						</c:otherwise>
					</c:choose>
					
				</h3>	
				<br/>
				
				

      					<c:url var="promotionSave" value="/admin/promotion/saveConditionTab.html"/>
                        <form:form method="POST" enctype="multipart/form-data" commandName="promotion" action="${promotionSave}">

                            <form:errors path="*" cssClass="alert alert-error" element="div" />
                            <div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
                            <div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>

                       
						<form:hidden path="id" />
                 	

                  		
                    	<div class="control-group">
                        	<label><s:message code="label.promotion.target.gender" text="Manufacturer"/></label>
                          	<div class="controls">
                          		      <form:select   path="promotionRule.targetGender">
                          		      <form:option value="-1"><s:message code="label.promotion.target.select"/></form:option>
                          		      <form:option value="M,F"><s:message code="label.promotion.target.select.1"/></form:option>
                          		      <form:option value="M"><s:message code="label.promotion.target.select.2"/></form:option>
                          		      <form:option value="F"><s:message code="label.promotion.target.select.3"/></form:option>
                          		       </form:select>
	                                  <span class="help-inline"></span>
                          	</div>
                    	</div>
	                  	<div class="control-group">
                        	<label><s:message code="label.promotion.age" text="Manufacturer"/></label>
                          	<div class="controls">
                          		      <form:select items="${promotionTragetAges}" itemValue="id" itemLabel="name"  path="promotionRule.promotionTragetAge"/> 
	                                  <span class="help-inline"></span>
                          	</div>
                    	</div>

					
                    	<div class="control-group" style="height: 600px;">
                    	<label><s:message code="label.promotion.countries" text="Manufacturer"/></label>
                          	<div class="controls">
                    		<c:set value="/admin/promotion/countries/paging.html?id=${promotion.id}" var="pagingUrl" scope="request"/>
				 			<c:set value="/admin/promotion/countries/update.html?id=${promotion.id}" var="updateUrl" scope="request"/>
				 			<c:set value="/admin/promotion/conditionTab.html?id=${promotion.id}" var="refreshUrl" scope="request"/>
				 			<c:set var="entityId" value="code" scope="request"/>
				 			<c:set var="listId" value="countryId" scope="request"/>
							<c:set var="componentTitleKey" value="label.promotion.countries" scope="request"/>
				 			<c:set var="gridHeader" value="/pages/admin/promotion/promotion-countries-gridHeader.jsp" scope="request"/>
							<c:set var="canRemoveEntry" value="false" scope="request"/>

            	 			<jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 			<!-- End listing grid include -->
							</div>
                    	
                    	</div>
                    	<br><br>
                    	<div class="control-group">
                    	<label><s:message code="label.promotion.countries"></s:message></label>
                    	<div class="controls">
                    	<c:set value="/admin/promotion/customers/paging.html?id=${promotion.id}" var="pagingUrl" scope="request"/>
				 			<c:set value="/admin/promotion/customers/update.html?id=${promotion.id}" var="updateUrl" scope="request"/>
				 			<c:set value="/admin/promotion/conditionTab.html?id=${promotion.id}" var="refreshUrl" scope="request"/>
				 			<c:set var="entityId" value="code" scope="request"/>
				 			<c:set var="listId" value="customersId" scope="request"/>
							<c:set var="componentTitleKey" value="label.promotion.countries" scope="request"/>
				 			<c:set var="gridHeader" value="/pages/admin/promotion/promotion-customers-gridHeader.jsp" scope="request"/>
							<c:set var="canRemoveEntry" value="false" scope="request"/>

            	 			<jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
                    	</div>
                    	
                    	</div>
                    	<c:if test="${promotion.promotionType.id!=4 && promotion.promotionType.id!=6 }">
                    	<div class="control-group">
                        	<label><s:message code="label.product.manufacturer" text="Manufacturer"/></label>
                          	<div class="controls">
						 		<form:select items="${manufacturers}" itemValue="id" itemLabel="descriptions[0].name"  path="promotionRule.brandsId"/> <span class="help-inline"></span>
                          	</div>
                    	</div>
                    	</c:if>
                  		
						
                    	<div class="control-group">
                        	<label>Categories</label>
                          	<div class="controls">
						 		<form:select items="${categories}" itemValue="id" itemLabel="descriptions[0].name"  path="promotionRule.categoriesId"/> <span class="help-inline"></span>
                          	</div>
                    	</div>
                    	<div class="control-group">
                    	<label>Products</label>
                    	<div class="controls">
                    	<c:set value="/admin/promotion/featured/paging.html?id=${promotion.id}" var="pagingUrl" scope="request" />
				<c:set value="/admin/promotion/featured/addItem.html?id=${promotion.id}" var="removeUrl" scope="request" />
				<c:set value="/admin/promotion/conditionTab.html?id=${promotion.id}" var="refreshUrl" scope="request" />
				<c:set var="entityId" value="categoryId" scope="request"/>
				<c:set var="componentTitleKey" value="label.categories.title" scope="request" />
				<c:set var="canRemoveEntry" value="true" scope="request" />
				<c:set var="listId" value="productsId" scope="request"/>
				<c:set var="gridHeader" value="/pages/admin/products/product-categories-gridHeader.jsp" scope="request"/>
				<jsp:include page="/pages/admin/components/list.jsp"></jsp:include>
                    	</div>
                    	</div>
                    	
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