<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        text: "Sales/Payment Method"    
      },
      animationEnabled: true,
      axisY: {
        title: "Sales"
      },
      legend: {
        verticalAlign: "bottom",
        horizontalAlign: "center"
      },
      theme: "theme3",
      data: [

      {        
        type: "column",  
        showInLegend: true, 
        legendMarkerColor: "grey",
        legendText: "Payment Method",
        dataPoints: [      
       
        
     <c:forEach var="entry" items="${map}" varStatus="status">
		   
	    
        
		{y: <c:out value='${entry.value}'/>, label: "<c:out value='${entry.key}'/>"},


</c:forEach>
        
        
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
      					

      			     
      			     
  <div id="chartContainer" style="height: 350px; width: 100%;">

      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     