<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>				
				
 <link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>


	<script type="text/javascript">
	
	
	
	
	
	function validateCode() {
		$('#checkCodeStatus').html('<img src="<c:url value="/resources/img/ajax-loader.gif" />');
		$('#checkCodeStatus').show();
		var code = $("#code").val();
		var id = $("#id").val();
		checkCode(code,id,'<c:url value="/admin/categories/checkCategoryCode.html" />');
	}
	
	function callBackCheckCode(msg,code) {
		console.log(code);
		if(code==0) {
			$('.btn').removeClass('disabled');
		}
		if(code==9999) {

			$('#checkCodeStatus').html('<font color="green"><s:message code="message.code.available" text="This code is available"/></font>');
			$('#checkCodeStatus').show();
			$('.btn').removeClass('disabled');
		}
		if(code==9998) {

			$('#checkCodeStatus').html('<font color="red"><s:message code="message.code.exist" text="This code already exist"/></font>');
			$('#checkCodeStatus').show();
			$('.btn').addClass('disabled');
		}
		
	}
	
	
	</script>



<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								
								
				<h3>
					<c:choose>
						<c:when test="${emailNotification.id!=null && emailNotification.id>0}">
								<s:message code="label.notification.editNotification" text="Edit category" /> <c:out value="${emailNotification.eventName}"/>
								<c:set value="${emailNotification.id}" var="emailId" />
						</c:when>
						<c:otherwise>
								<s:message code="label.notification.addNotification" text="Create category" />
								<c:set value="0" var="emailId"/>
						</c:otherwise>
					</c:choose>
					
				</h3>	
				

				<c:url var="complaintSave" value="/admin/notification/save.html"/>


				<form:form method="POST" commandName="emailNotification" action="${complaintSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								


      			  
      			 
      			  
      			  	
				 
                  
                  
                  
                
                                    
                  <div class="control-group">
                        <label>Event Name </label>
                        <div class="controls">
                        			<form:textarea path="eventName" cssClass="highlight" />
                        			<span class="help-inline"><form:errors path="eventName" cssClass="error" /></span>
                        </div>
                  </div>
                  
                 
                 
                 
                  
                  
                   <div class="control-group">
	                        <label>Event Date</label>
	                        <div class="controls">
	                          <fmt:formatDate value="${emailNotification.eventDate}" pattern="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" var="startFormattedDate" />
	                       
	                        		 <input id="eventDate" name="eventDate" 
	                        		 
	                        		 value="${startFormattedDate}" class="small highlight" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
	                                 $('#eventDate').datepicker();
	                                 
	                                 </script>
	                                 <span class="help-inline"><form:errors path="eventDate" cssClass="error" /></span>
	                        </div>
	                  	</div>
                  
              		 <c:forEach items="${emailNotification.emailTemplates}" var="description" varStatus="status">
						<div class="control-group">
                        
                              <label class="required"><s:message code="label.notification.desc" text="Product description"/> (<c:out value="${description.language.code}"/>)</label>
                              <div class="controls">
                              		 
                              		 
                              	     <textarea cols="30" id="emailTemplates${status.index}.emailTemplate"  name="emailTemplates[${status.index}].emailTemplate">
                        				<c:out value="${description.emailTemplate}"/>
                        			 </textarea>
                              </div>
                              
                              
                              
                        <script type="text/javascript">
						//<![CDATA[

							CKEDITOR.replace('emailTemplates[${status.index}].emailTemplate',
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
                      

                       

                      

                        
                      

					
							<form:hidden path="emailTemplates[${status.index}].language.id" />
                        
						 <form:hidden path="emailTemplates[${status.index}].id" />
						<form:hidden path="emailTemplates[${status.index}].emailNotification.id" />
							

                 

                  </c:forEach>
                  <form:hidden path="id" />
					<c:set value="/admin/notificatin/customers/paging.html?id=${emailId}" var="pagingUrl" scope="request"/>
				 			<c:set value="/admin/notificatin/customers/update.html?id=${emailId }" var="updateUrl" scope="request"/>
				 			
				 			<c:set var="entityId" value="code" scope="request"/>
							<c:set var="componentTitleKey" value="menu.customer" scope="request"/>
				 			<c:set var="gridHeader" value="/pages/admin/promotion/promotion-customers-gridHeader.jsp" scope="request"/>
							<c:set var="canRemoveEntry" value="false" scope="request"/>

            	 			<jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 			<!-- End listing grid include -->
			      <div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  			

                  		</div>

            	 </div>
            	 <!-- Listing grid include -->
				 		
 
            	 </form:form>
	      			     
      					<br/>
							

							
							


      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>    			     