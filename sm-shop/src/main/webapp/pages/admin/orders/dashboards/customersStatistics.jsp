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
	
	$(document).ready(function(e) {
	
		$(function() {
			$('#startDate').datepicker( {
			    changeMonth: true,
			    changeYear: true,
			    showButtonPanel: true,
			    dateFormat: 'MM yy',
			    onClose: function(dateText, inst) { 
			        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
			        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
			        $(this).datepicker('setDate', new Date(year, month, 1));
			    }
			});
			});
	
	$('#submit').click(function(event){
		url="/sm-shop/admin/orders/customersStatistics.html?startDate="+$('#startDate').val();
		
		
    	window.location = url;
     });
	$("#filter").change(function(){
	val=$(this).val();
	var curr = new Date();
    day = curr.getDay();
   
 
	 
	});
	});
	function getLastWeek(){
    var today = new Date();
    var lastWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 7);
    return lastWeek ;
}
</script>
<div class="tabbable">

 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">
								<h3><s:message code="label.customer.Statistics.title" text="Categories" /></h3>	
								<br/>
							 
								
					<div class="control-group">
	                        <label><s:message code="label.promotion.endDate" text="Date available"/></label>
	                        <div class="controls">
	                        		 <input id="startDate" name="startDate" value="${startDate}" class="small" type="text"  > 
	                            
	                       
	                        </div>
	                  	</div>
	                  	<div class="form-actions">
                            <div class="pull-right">
                                    <button type="submit" class="btn btn-success" id="submit"><s:message code="label.generic.search" text="Submit"/></button>
                            </div>
                              <br></br>
                    <br></br><br></br>
                   </div>	
                   
                   
                  
								
				 <!-- Listing grid include -->
				 <c:set value="/admin/orders/customersStatistics/paging.html?startDate=${startDate}" var="pagingUrl" scope="request"/>
				
				
				
				 <c:set var="entityId" value="id" scope="request"/>
<%-- 				  <c:set var="groupByEntity" value="categoryName" scope="request"/>
 --%>				   <%-- <c:set var="showGridSummary" value="true" scope="request"/> --%>
				 <c:set var="componentTitleKey" value="label.customer.Statistics.title" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/orders/dashboards/customersStatistics-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="false" scope="request"/>
				
            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			      			     
			      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     