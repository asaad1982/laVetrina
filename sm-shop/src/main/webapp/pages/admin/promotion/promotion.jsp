<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>

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
				
				

      					<c:url var="promotionSave" value="/admin/promotion/save.html"/>
                        <form:form method="POST" enctype="multipart/form-data" commandName="promotion" action="${promotionSave}">

                            <form:errors path="*" cssClass="alert alert-error" element="div" />
                            <div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
                            <div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>

                       
						<form:hidden path="id" />
                 	

                  		
                  		
                  		
                  		<div class="control-group">
	                        <label><s:message code="label.promotion.startDate" text="Date available"/></label>
	                        <div class="controls">
	                        		 <input id="startDate" name="startDate" value="${promotion.startDate}" class="small" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
	                                 $('#startDate').datepicker();
	                                 </script>
	                                 <span class="help-inline"><form:errors path="startDate" cssClass="error" /></span>
	                        </div>
	                  	</div>
	                  	
	                  	<div class="control-group">
	                        <label><s:message code="label.promotion.endDate" text="Date available"/></label>
	                        <div class="controls">
	                        		 <input id="endate" name="endate" value="${promotion.endate}" class="small" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
	                                 $('#endate').datepicker();
	                                 </script>
	                                 <span class="help-inline"><form:errors path="endate" cssClass="error" /></span>
	                        </div>
	                  	</div>
	                  	<div class="control-group">
                        	<label><s:message code="label.promotion.status" text="Manufacturer"/></label>
                          	<div class="controls">
                          		      <form:select   path="status">
                          		      <form:option value="-1"><s:message code="label.promotion.select.status"/></form:option>
                          		      <form:option value="Running"><s:message code="label.promotion.select.status.1"/></form:option>
                          		      <form:option value="Paused"><s:message code="label.promotion.select.status.2"/></form:option>
                          		      <form:option value="Closed"><s:message code="label.promotion.select.status.3"/></form:option>
                          		       </form:select>
	                                  <span class="help-inline"></span>
                          	</div>
                    	</div>
                    	
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
                          		      <form:select items="${promotionTragetAges}" itemValue="id" itemLabel="name"  path="promotionRule.promotionTragetAge.id"/> 
	                                  <span class="help-inline"></span>
                          	</div>
                    	</div>

						<div class="control-group">
                        	<label><s:message code="label.promotion.type" text="Manufacturer"/></label>
                          	<div class="controls">
                          		      <form:select items="${promotionTypes}" itemValue="id" itemLabel="name"  path="promotionType.id"/> 
	                                  <span class="help-inline"></span>
                          	</div>
                    	</div>
                    	<div class="control-group">
                        	<label><s:message code="label.product.manufacturer" text="Manufacturer"/></label>
                          	<div class="controls">
						 		<form:select items="${manufacturers}" itemValue="id" itemLabel="descriptions[0].name"  path="promotionRule.brands"/> <span class="help-inline"></span>
                          	</div>
                    	</div>

                  		 <c:forEach items="${promotion.promotionDescriptions}" var="description" varStatus="counter">

                 

                        <div class="control-group">

                              <label class="required"><s:message code="label.promotion.promotionname" text="Product name"/> (<c:out value="${description.languageName}"/>)</label>
                              <div class="controls">
                                          <form:input cssClass="input-large highlight" id="name${counter.index}" path="promotionDescriptions[${counter.index}].name"/>
                                          <span class="help-inline"><form:errors path="promotionDescriptions[${counter.index}].name" cssClass="error" /></span>
                              </div>

                       </div>

                      
                        
                       

                       


                        <div class="control-group">
                              <label class="required"><s:message code="label.promotion.promotiondesc" text="Product description"/> (<c:out value="${description.languageName}"/>)</label>
                              <div class="controls">
                              		 
                              		 
                              	     <textarea cols="30" id="promotionDescriptions${counter.index}.description" name="promotionDescriptions[${counter.index}].description">
                        				<c:out value="${promotionDescriptions[counter.index].description}"/>
                        			 </textarea>
                              </div>
                              
                              
                              
                        <script type="text/javascript">
						//<![CDATA[

							CKEDITOR.replace('promotionDescriptions[${counter.index}].description',
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
                      

                       

                      

                        
                      

                         <form:hidden path="promotionDescriptions[${counter.index}].languageId" />
                        
						 <form:hidden path="promotionDescriptions[${counter.index}].id" />
						

                 

                  </c:forEach>
                  		

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