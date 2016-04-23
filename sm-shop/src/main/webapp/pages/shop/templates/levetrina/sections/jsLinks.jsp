<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


  
    
      <script src="<c:url value="/resources/templates/levetrina/js/bootstrap.min.js" />"></script>
  <script src="<c:url value="/resources/templates/levetrina/js/jquery.scrollUp.min.js" />"></script>
<script src="<c:url value="/resources/templates/levetrina/js/price-range.js" />"></script>
<script src="<c:url value="/resources/templates/levetrina/js/jquery.prettyPhoto.js" />"></script>
<script src="<c:url value="/resources/templates/levetrina/js/main.js" />"></script>


        <script type="text/javascript">

            $('#product-tab a:first').tab('show');
            $('#product-tab a').click(function (e) {
            		e.preventDefault();
            		$(this).tab('show');
            }) 
          
			$('#recommended-item-carousel').addClass('carousel slide');
			$('#recommended-item-carousel').append(' <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev"><i class="fa fa-angle-left"></i></a><a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next"><i class="fa fa-angle-right"></i></a>	')
	        
	        $('#recommended-item-carousel').carousel();
	
            
        </script>
     
