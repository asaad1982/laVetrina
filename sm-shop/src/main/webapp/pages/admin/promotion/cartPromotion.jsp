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
					Cart Promotion
					
				</h3>	
				<br/>
				
				

      					<c:url var="promotionSave" value="/admin/promotion/saveCartPromotion.html"/>
                        <form:form method="POST" enctype="multipart/form-data" commandName="cartPromotion" action="${promotionSave}">

                            <form:errors path="*" cssClass="alert alert-error" element="div" />
                            <div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
                            <div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>

                       
						<form:hidden path="id" />
                 	

                  		
                  		<div class="control-group">
                        	<label>Coupon Code</label>
                          	<div class="controls">
                          		      <form:input cssClass="input-large highlight"  path="couponCode" maxlength="8"/>
                                          <span class="help-inline"><form:errors path="couponCode" cssClass="error" /></span>
                          	</div>
                    	</div>
                  	
	                  	<div class="control-group">
                        	<label>Coupon Discount Type</label>
                          	<div class="controls">
                          		      <form:select   path="couponDiscountType" cssClass="highlight">
                          		      <form:option value="-1"><s:message code="label.promotion.select.status"/></form:option>
                          		      <form:option value="1">Fixed Amount Discount</form:option>
                          		      <form:option value="2">Percentage</form:option>
                          		      
                          		       </form:select>
	                                  <span class="help-inline"><form:errors path="couponDiscountType" cssClass="error" /></span>
                          	</div>
                    	</div>
                    	<div class="control-group">
                        	<label>Coupon Discount Amount</label>
                          	<div class="controls">
                          		      <form:input cssClass="input-large highlight"  path="couponDiscountAmount" maxlength="6"/>
                                          <span class="help-inline"><form:errors path="couponDiscountAmount" cssClass="error" /></span>
                          	</div>
                    	</div>
                    	 <div class="control-group">
                        	<label>Free Shipping </label>
                          	<div class="controls">
                       <form:radiobutton path="freeShippingOption" value="true"/>Yes 
                       <form:radiobutton path="freeShippingOption" value="false"/>No </div></div>
                    
                  		<div class="control-group">
                              <label class="required">Email Message </label>
                              <div class="controls">
                              		 
                              		 
                              	     <textarea cols="30" id="emailMessageEn" name="emailMessageEn" >
                        				<c:out value="${cartPoromotion.emailMessageEn}"/>
                        			 </textarea>
                              </div>
                              
                              
                              
                        <script type="text/javascript">
						//<![CDATA[

							CKEDITOR.replace('emailMessageEn',
							{
								skin : 'office2003',
								toolbar : 
								[
									['Source','-','Save','NewPage','Preview'], 
									['Cut','Copy','Paste','PasteText','-','Print'], 
									['Undo','Redo','-','Find','-','SelectAll','RemoveFormat'], '/', 
									['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
									['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
									['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
									['Link','Unlink','Anchor'], 
									['Image','Flash','Table','HorizontalRule','SpecialChar','PageBreak'], '/', 
									['Styles','Format','Font','FontSize'], ['TextColor','BGColor'], 
									['Maximize', 'ShowBlocks'] 
								],
								
								filebrowserWindowWidth : '720',
        						filebrowserWindowHeight : '740',
								filebrowserImageBrowseUrl :    '<c:url value="/admin/content/fileBrowser.html"/>'
								

							});

						//]]>
						</script>
                              
                              
                              
                       </div>
                       
                          		<div class="control-group">
                              <label class="required">Email Message (Ar)</label>
                              <div class="controls">
                              		 
                              		 
                              	     <textarea cols="30" id="emailMessageAr" name="emailMessageAr">
                        				<c:out value="${cartPoromotion.emailMessageAr}"/>
                        			 </textarea>
                              </div>
                              
                              
                              
                        <script type="text/javascript">
						//<![CDATA[

							CKEDITOR.replace('emailMessageAr',
							{
								skin : 'office2003',
								toolbar : 
								[
									['Source','-','Save','NewPage','Preview'], 
									['Cut','Copy','Paste','PasteText','-','Print'], 
									['Undo','Redo','-','Find','-','SelectAll','RemoveFormat'], '/', 
									['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
									['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
									['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
									['Link','Unlink','Anchor'], 
									['Image','Flash','Table','HorizontalRule','SpecialChar','PageBreak'], '/', 
									['Styles','Format','Font','FontSize'], ['TextColor','BGColor'], 
									['Maximize', 'ShowBlocks'] 
								],
								
								filebrowserWindowWidth : '720',
        						filebrowserWindowHeight : '740',
								filebrowserImageBrowseUrl :    '<c:url value="/admin/content/fileBrowser.html"/>'
								

							});

						//]]>
						</script>
                              
                              
                              
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