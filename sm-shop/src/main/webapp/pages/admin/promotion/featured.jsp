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
								
								
								<c:if test="${cartPromotion.promotion.id!=null && cartPromotion.promotion.id>0}">
									<c:set value="${cartPromotion.promotion.id}" var="id" scope="request"/>
									
									<jsp:include page="/pages/admin/promotion/promotion-menu.jsp" />
								</c:if>	
								
								
				<h3>
					Cross Selling 
					
				</h3>	
				<br/>
				
				

      					<c:url var="promotionSave" value="/admin/promotion/saveCrossSellingPromotion.html"/>
                        <form:form method="POST" enctype="multipart/form-data" commandName="bundlePromotion" action="${promotionSave}">

                            <form:errors path="*" cssClass="alert alert-error" element="div" />
                            <div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
                            <div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>

                       
						<form:hidden path="id" />
                 	

                  		
                  		<div class="control-group">
                        	<label>Bundled Products</label>
                          	<div class="controls">
                          		      <form:select items="${relationships}" itemValue="id" itemLabel="code"  path="productRelationship.id" cssClass="required"/> 
	                                  <span class="help-inline"></span>
                          	</div>
                    	</div>
                  	
	                  	
                    	</div>
                    	<div class="control-group">
                        	<label>Bundle Price</label>
                          	<div class="controls">
                          		      <form:input cssClass="input-large highlight"  path="bundlePrice" maxlength="6"/>
                                          <span class="help-inline"><form:errors path="bundlePrice" cssClass="error" /></span>
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