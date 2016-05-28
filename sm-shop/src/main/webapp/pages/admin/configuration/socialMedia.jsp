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
  					
  					
  					<div class="tab-pane active" id="socialMedia-conf"> 
								<div class="sm-ui-component">
								<h3><s:message code="label.socialMediaconfig.options" text="Social Media Configuration Options" /></h3>	
								<br/>
								

							<c:url var="saveSocialMediaConfiguration" value="/admin/configuration/saveSocialConfiguration.html"/>
							<form:form method="POST" commandName="configuration" action="${saveSocialMediaConfiguration}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    

                  					
                  					<div class="control-group">
                        				<label><s:message code="label.socialMediaconfig.shareDiscountNumber" text="Discount Number of share"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" path="shareDiscountNumber" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="shareDiscountNumber" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.socialMediaconfig.discountPercent" text="Discount Percentage"/></label>
                        				<div class="controls">
											<form:input  cssClass="input-large" path="discountPercent"  />
                        				</div>
	                                	<span class="help-inline"><form:errors path="discountPercent" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		
	                        		<table>
	                        		<tr>
	                        		
	                        		
	                        		
	                        		
	                        		<td> 
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.socialMediaconfig.shareDiscountFrequency" text="Number of Interval unit"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" path="shareDiscountFrequency" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="shareDiscountFrequency" cssClass="error" /></span>
	                        		</div>
	                        		</td>
	                        		
	                        		
	                        		<td> 
	                        		<div class="control-group">
                        				<label><s:message code="label.socialMediaconfig.shareDiscountIntervalUnit" text="Interval unit"/></label>
                        				<div class="controls">
											<form:select   cssClass="input-large" path="shareDiscountIntervalUnit">
											<form:option value="1">week</form:option>
											<form:option value="2">month</form:option>
											</form:select>
											<!--  form:input cssClass="input-large" path="shareDiscountIntervalUnit" /-->
                        				</div>
	                                	<span class="help-inline"><form:errors path="shareDiscountIntervalUnit" cssClass="error" /></span>
	                        		</div>
	                        		</td>
	                        		</tr>
	                        		
	                        		</table>
	                        		
	                        		<!--div class="form-actions"-->
                  						<!--div class="pull-right"-->
                  							<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  						<!--/div-->
            	 					<!--/div-->
					                  

            	 			</form:form>
							
							


      			     
    


   					</div>


  					</div>

				</div>