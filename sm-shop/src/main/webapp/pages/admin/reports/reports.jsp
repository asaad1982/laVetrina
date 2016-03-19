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
	
	
	
	$('#submit').click(function(event){
		url="/sm-shop/admin/report/reports.html?startDate="+$('#startDate').val()+"&endDate="+$('#endate').val();
		
    	window.location = url;
     });
	$("#filter").change(function(){
	val=$(this).val();
	var curr = new Date();
    day = curr.getDay();
    if(val==1){
		var startDay = 0; 
	     
	    $("#startDate").val( curr.getTime().toISOString());
	     $("#endate").val( curr.getTime().toISOString());
	}
	else if(val==2){
		var startDay = 0; 
	    var weekStart = new Date(curr.getTime() - 60*60*24* day*1000); 
	    var weekEnd = new Date(curr.getTime() + 60 * 60 *24 * 6 * 1000); 
	    $("#startDate").val( weekStart.toISOString());
	     $("#endate").val( weekEnd.toISOString());
	}else if(val==3){
		var date = new Date();
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	    $("#startDate").val( firstDay.toISOString());
	     $("#endate").val( lastDay.toISOString());
	}else if(val==4){
		var thisYear = (new Date()).getFullYear();    
        var start = new Date("1/1/" + thisYear);
        var end = new Date("31/12/" + thisYear);
	    $("#startDate").val( start.toISOString());
	     $("#endate").val( end.toISOString());
	}
	if(val==6){
		var startDay = 0; 
	    var weekStart = new Date(curr.getTime() - 60*60*24* day*1000); 
	    var weekEnd = new Date(curr.getTime() + 60 * 60 *24 * 6 * 1000); 
	    $("#startDate").val( weekStart.toISOString());
	     $("#endate").val( weekEnd.toISOString());
	}else if(val==7){
		var date = new Date();
        var firstDay = new Date(date.getFullYear(), date.getMonth()-1, 1);
        var lastDay = new Date(date.getFullYear(), date.getMonth() , 0);
	    $("#startDate").val( firstDay.toISOString());
	     $("#endate").val( lastDay.toISOString());
	}else if(val==8){
		var prevYear = (new Date()).getFullYear()-1;    
        var prevstart = new Date("1/1/" + prevYear);
        var prevend = new Date("1/1/" + (new Date()).getFullYear());
	    $("#startDate").val( prevstart.toISOString());
	     $("#endate").val( prevend.toISOString());
	}
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
								<h3><s:message code="label.sales.title" text="Categories" /></h3>	
								<br/>
								<div class="control-group">
	                        <label><s:message code="label.promotion.endDate" text="Date available"/></label>
	                        <div class="controls">
	                        		 <select id="filter">
	                        		 <option value="1">This Day</option>
	                        		 <option value="2">This Week</option>
	                        		 <option value="3">This Month</option>
	                        		 <option value="4">This Year</option>
	                        		 <option value="-1">------------------</option>
	                        		 <option value="5">Yesterday</option>
	                        		 <option value="6">Previous Week</option>
	                        		 <option value="7">Previous Month</option>
	                        		 <option value="8">Previous Year</option>
	                        		
	                        		 
	                        		 </select>
	                       		
	                                
	                        </div>
	                  	</div>
								
					<div class="control-group">
	                        <label><s:message code="label.promotion.endDate" text="Date available"/></label>
	                        <div class="controls">
	                        		 <input id="startDate" name="startDate" value="${startDate}" class="small" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
	                                 $('#startDate').datepicker();
	                                 </script>
	                                 
	                       			-
	                        		 <input id="endate" name="endate"  class="small" value="${endDate}" type="text" data-date-format="<%=com.salesmanager.core.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                 <script type="text/javascript">
	                                 $('#endate').datepicker();
	                                 </script>
	                                
	                        </div>
	                  	</div>
	                  	<div class="form-actions">
                            <div class="pull-right">
                                    <button type="submit" class="btn btn-success" id="submit"><s:message code="label.generic.search" text="Submit"/></button>
                            </div>
                   </div>	
								
				 <!-- Listing grid include -->
				 <c:set value="/admin/report/paging.html?startDate=${startDate}&endDate=${endDate}" var="pagingUrl" scope="request"/>
				
				
				
				 <c:set var="entityId" value="id" scope="request"/>
				  <c:set var="groupByEntity" value="categoryName" scope="request"/>
				   <c:set var="showGridSummary" value="true" scope="request"/>
				 <c:set var="componentTitleKey" value="label.sales.title" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/reports/reports-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="false" scope="request"/>
				
            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			      			     
			      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     