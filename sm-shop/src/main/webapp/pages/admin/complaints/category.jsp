<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
				

<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>


	<script type="text/javascript">
	

	
$('#englishName').keypress(function (e) {
        if (e.which !== 0 &&
            !e.ctrlKey && !e.metaKey && !e.altKey) {
            alert(String.fromCharCode(e.which));
        }
    });

	
	
	
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
						<c:when test="${category.id!=null && category.id>0}">
								<s:message code="edit.complaints" text="Edit category" /> <c:out value="${category.id}"/>
						</c:when>
						<c:otherwise>
								<s:message code="add.complaints" text="Create category" />
						</c:otherwise>
					</c:choose>
					
				</h3>	
				

				<c:url var="categorySave" value="/admin/complaints/save.html"/>


				<form:form method="POST" commandName="category" action="${categorySave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								


      			  
      			 
      			  
      			  	
				 
                  
                  
                  
                
                                    
                  <div class="control-group">
                        <label>English Name </label>
                        <div class="controls">
                        			<form:input path="englishName" cssClass="highlight" maxlength="100"/>
                        			<span class="help-inline"><form:errors path="englishName" cssClass="error" /></span>
                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label>Arabic Name </label>
                        <div class="controls">
                        			<form:input path="arabicName" cssClass="highlight" maxlength="100"/>
                        			<span class="help-inline"><form:errors path="arabicName" cssClass="error" /></span>
                        </div>
                  </div>
                  
                 <div class="control-group">
                        <label>Status</label>
                        <div class="controls">
                        			<form:checkbox path="available" cssClass="highlight" maxlength="100"/>
                        			<span class="help-inline"><form:errors path="available" cssClass="error" /></span>
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