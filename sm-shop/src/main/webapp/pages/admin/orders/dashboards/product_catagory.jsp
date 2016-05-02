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
				text: "Product/Category"
			},
	                animationEnabled: true,
			legend:{
				verticalAlign: "center",
				horizontalAlign: "right",
				fontSize: 14,
				fontFamily: "Helvetica"        
			},
			theme: "theme1",
			data: [
			{        
				type: "pie",       
				indexLabelFontFamily: "Garamond",       
				indexLabelFontSize: 14,
				indexLabel: "{label} {y}%",
				startAngle:-20,      
				showInLegend: true,
				toolTipContent:"{legendText} {y}%",
				dataPoints: [
		 
					
					<c:forEach var="entry" items="${map}" varStatus="status">
				   
				    
				    {  y: (<c:out value='${entry.value}'/>/<c:out value="${total}" />)*100, legendText:"<c:out value='${entry.key}'/>",exploded: true, label: "<c:out value='${entry.key}'/>" },
				    </c:forEach>
					
					
					
					
				]
			}
			]
		});
		chart.render();
	}
	
		isc.ListGrid.create({
		    ID:"countryList",
		    width:500, height:224,
		    data: countryData,
		    selectionType:"single",
		    fields:[
		        {name:"countryCode", title:"Flag", width:50, type:"image", imageURLPrefix:"flags/16/", imageURLSuffix:".png"},
		        {name:"countryName", title:"Country"},
		        {name:"capital", title:"Capital"},
		        {name:"continent", title:"Continent"}
		    ],
		    baseStyle:"simpleCell",
		    alternateRecordStyles: false,

		    showSelectionUnderCanvas:true,
		    animateSelectionUnder:true,
		    selectionUnderCanvasProperties:{
		        animateShowEffect:"fade",
		        animateFadeTime:1000,
		        backgroundColor:"#ffff40"
		    },

		    showRollUnderCanvas:true,
		    animateRollUnder:true,
		    rollUnderCanvasProperties:{
		        animateShowEffect:"fade",
		        animateFadeTime:1000,
		        backgroundColor:"#00ffff",
		        opacity:50
		    }
		});

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