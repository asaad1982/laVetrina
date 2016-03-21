<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>				
				

<script src="<c:url value="/resources/js/canvasjs/canvasjs.min.js" />"></script>


	<script type="text/javascript">
window.onload = function () {
	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "Deals"
		},
		exportFileName: "Pie Chart",
		exportEnabled: true,
                animationEnabled: true,
		legend:{
			verticalAlign: "bottom",
			horizontalAlign: "center"
		},
      theme: "theme3",
		data: [
		{       
			type: "pie",
			showInLegend: true,
			toolTipContent: "{legendText}: <strong>{y}%</strong>",
			indexLabel: "{label} {y}%",
			dataPoints: [
			             
			             
			
				
				 
				   
				{  y: "<c:out value='${(totalQuantity/(totalQuantity+totalQuantity_ordered))*100}'/>"	, legendText: "Instock", exploded: true, label: "Instock" },
				{  y: "<c:out value='${(totalQuantity_ordered/(totalQuantity+totalQuantity_ordered))*100}'/>"	, legendText: "Sold", exploded: true, label: "Sold" },

				
				
			]
	}
	]
	});
	chart.render();
}
</script>
 

<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								
								
			 

				 
	      			     
      					</div>
      					

      			     
      			     
<div id="chartContainer" style="height: 350px; width: 100%;"></div>

      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     