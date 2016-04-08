<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
						<c:when test="${customerComplaint.id!=null && customerComplaint.id>0}">
								<s:message code="label.complaints.editcategory" text="Edit category" /> <c:out value="${customerComplaint.id}"/>
						</c:when>
						<c:otherwise>
								<s:message code="label.complaints.createcategory" text="Create category" />
						</c:otherwise>
					</c:choose>
					
				</h3>	
				

				<c:url var="complaintSave" value="/admin/customercomplaints/save.html"/>


				<form:form method="POST" commandName="customerComplaint" action="${complaintSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								


      			  
      			 
      			  
      			  	
				 
                  
                  
                  
                
                                    
                  <div class="control-group">
                        <label>Complaints </label>
                        <div class="controls">
                        			<form:textarea path="notes" readonly="true"/>
                        			
                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label>Reason </label>
                        <div class="controls">
                        			
                        			${customerComplaint.customerComplaintReason.englishName}
                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label>Customer Email </label>
                        <div class="controls">
                        			${customerComplaint.customer.emailAddress}
                        			
                        </div>
                  </div>
                 
                  <div class="control-group">
	                        <label>Complaint Date</label>
	                        <div class="controls">
	                        <fmt:formatDate value="${customerComplaint.complaintsDate}" pattern="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" var="startFormattedDate" />
	                       
	                        		${startFormattedDate}
	                        </div>
	                  	</div>
                  
                   <div class="control-group">
	                        <label>Complaint Date</label>
	                        <div class="controls">
	                        		   <form:select   path="status">
	                        		   <form:option value="-1">Please Select Reason</form:option>
	                        		    <form:option value="solved">Solved</form:option>
	                        		    <form:option value="UnderInvestigation">Under Investigation</form:option>
	                        		   </form:select>
	                                 <span class="help-inline"><form:errors path="status" cssClass="error" /></span>
	                        </div>
	                  	</div>
                   <div class="control-group">
	                        <label>Closing Date</label>
	                        <div class="controls">
	                        <fmt:formatDate value="${customerComplaint.closingDate}" pattern="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" var="closingDate" />
	                       
	                        		 <input id="closingDate" name="closingDate" value="${closingDate}" class="small" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
	                                 $('#closingDate').datepicker();
	                                 </script>
	                                 <span class="help-inline"><form:errors path="closingDate" cssClass="error" /></span>
	                        </div>
	                  	</div>
                  
                <div class="control-group">
                        <label>Closing Reason </label>
                        <div class="controls">
                        			<form:textarea path="closingReason" />
                        	<span class="help-inline"><form:errors path="closingReason" cssClass="error" /></span>		
                        </div>
                  </div>
                  
                  <form:hidden path="id" />
			
			      <div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  			

                  		</div>

            	 </div>
 
            	 </form:form>
	      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     