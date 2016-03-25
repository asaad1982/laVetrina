<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>				
				


 <link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
	<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.formatCurrency-1.4.0.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
	<script src="<c:url value="/resources/js/adminFunctions.js" />"></script>
	<script type="text/javascript">
	
 
 
</script>
<div class="tabbable">

 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">
							 
								<br/>
							 
								
				 
	                 	
                   
                   
                  
								
				 <!-- Listing grid include -->
				 <c:set value="/admin/orders/availabilityCategory/paging.html" var="pagingUrl" scope="request"/>
				
				
				
				 <c:set var="entityId" value="id" scope="request"/>
<%-- 				  <c:set var="groupByEntity" value="categoryName" scope="request"/>
 --%>				   <%-- <c:set var="showGridSummary" value="true" scope="request"/> --%>
				 <c:set var="componentTitleKey" value="label.sales.title" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/orders/dashboards/availabilityCategory-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="false" scope="request"/>
				
            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			      			     
			      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     