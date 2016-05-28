<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>



              <div class="btn-group" style="z-index:400000;">
                    <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><s:message code="label.promotion.configure" text="Promotion definition"/> ... <span class="caret"></span></button>
                     <ul class="dropdown-menu">
				    	<li><a href="<c:url value="/admin/promotion/conditionTab.html" />?id=<c:out value="${id}"/>">Condition Tab</a></li>
				    
				    	<c:if test="${promotion.promotionType.id==1}"><li><a href="<c:url value="/admin/promotion/cartPromotion.html" />?id=<c:out value="${id}"/>">Cart Promotion</a></li></c:if>
				    	<c:if test="${promotion.promotionType.id==6}"><li><a href="<c:url value="/admin/promotion/featured/list.html" />?id=<c:out value="${id}"/>">Cross Selling</a></li></c:if>
				    	<c:if test="${promotion.promotionType.id==4}"><li><a href="<c:url value="/admin/promotion/displayUpSellingItems.html" />?id=<c:out value="${id}"/>">UP Selling</a></li></c:if>
				    	
				    	<li><a href="<c:url value="/admin/promotion/editPromotion.html" />?id=<c:out value="${id}"/>">Edit Offer</a></li>
				    	<li><a href="<c:url value="/admin/promotion/promotions.html" />">Offers</a></li>
                     </ul>
              </div><!-- /btn-group -->
			  <br/>