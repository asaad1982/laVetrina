<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>




<div class="tabbable">


	<jsp:include page="/common/adminTabs.jsp" />



	<div class="tab-content">

		<div class="tab-pane active" id="catalogue-section">





			<div class="sm-ui-component">
			
		 
					 
    
    
    
    <form:form modelAttribute="fileBean" method="post" enctype="multipart/form-data" action="/sm-shop/admin/customer/upload.html">
					        <form:errors path="*" cssClass="alert alert-error" element="div" />
					         <div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
                            <div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>
					        
					        <form:label for="fileData" path="fileData">Select file</form:label><br/><br/>
					        <form:input path="fileData" type="file"/><form:errors path="fileData"/>
					        
        <button type="submit" class="btn btn-success">Import</button>
    </form:form>
		 
			 
				<h3><s:message code="label.customer.list" text="Customer list" /></h3> 
				<br/><br/>

				 <!-- Listing grid include -->
				 <c:set value="/admin/customers/page.html" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/admin/customers/remove.html" var="removeUrl" scope="request"/>
				 <c:set value="/admin/customers/customer.html" var="editUrl" scope="request"/>
				 <c:set var="entityId" value="id" scope="request"/>
				 <c:set var="componentTitleKey" value="label.customer.list" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/customers/customers-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="false" scope="request"/>

            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			
			





			</div>











		</div>


	</div>

</div>