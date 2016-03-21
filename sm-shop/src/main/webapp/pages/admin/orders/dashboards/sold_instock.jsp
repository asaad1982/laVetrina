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
      text: "Sold/Instock"   
      },
      axisY:{
        title:"Coal (mn tonnes)"   
      },
        theme: "theme3",
      animationEnabled: true,
      data: [
      {        
        type: "stackedColumn",
        toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}peace",
        name: "Instock",
        showInLegend: "true",
        dataPoints: [
       
 
        
  <c:forEach var="entry" items="${stock}" varStatus="status">
		   
	    
        
		{y: <c:out value='${entry.quantity}'/>, label: "<c:out value='${entry.name}'/>"},


</c:forEach>
        
        ]
      },  {        
        type: "stackedColumn",
        toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}peace",
        name: "Sold",
        showInLegend: "true",
        dataPoints: [
      
                     
                     <c:forEach var="entry" items="${stock}" varStatus="status">
                   		   
                   	    
                           
                   		{y: <c:out value='${entry.quantity_ordered}'/>, label: "<c:out value='${entry.name}'/>"},


                   </c:forEach>
        ]
      }            
      ]
      ,
      legend:{
        cursor:"pointer",
        itemclick: function(e) {
          if (typeof (e.dataSeries.visible) ===  "undefined" || e.dataSeries.visible) {
	          e.dataSeries.visible = false;
          }
          else
          {
            e.dataSeries.visible = true;
          }
          chart.render();
        }
      }
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