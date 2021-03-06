<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<div class="tabbable">
   <jsp:include page="/common/adminTabs.jsp" />
	<div class="tab-content">
		<div class="tab-pane active" id="catalogue-section">
           <div class="sm-ui-component">
           
           
           			<c:if test="${promotion.id!=null && promotion.id>0}">
						<c:set value="${product.id}" var="promotionId" scope="request"/>
						<jsp:include page="/pages/admin/promotion/promotion-menu.jsp" />
					</c:if>	
           
           
				<h3>
					<s:message code="label.product.category.association" text="Associate to categories" />
				</h3>
				
				
			
			<c:url var="addCategory" value="/admin/promotion/addPromotionToCategories.html" />
			<form:form method="POST" enctype="multipart/form-data" commandName="promotion" action="${addCategory}">
			<form:errors path="*" cssClass="alert alert-error" element="div" />
			<div id="store.success" class="alert alert-success"	style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
					<s:message code="message.success" text="Request successfull" />
			</div>
			<br/>
			<strong><c:out value="${promotion.id}"/></strong>
			<br/><br/>
			
			<div class="control-group">
				<label><s:message code="label.productedit.categoryname" text="Category"/></label>
			  	<div class="controls">
	                        		<form:select path="id">
					  					<form:options items="${categories}" itemValue="id" itemLabel="descriptions[0].name"/>
				       				</form:select>
	                                <span class="help-inline"><form:errors path="id" cssClass="error" /></span>
				</div>
			</div>
			

			<input type="hidden" name="promotionId" value="${promotion.id}">
			<div class="form-actions">
                  		<div class="pull-right">
                  			<button type="submit" class="btn btn-success"><s:message code="label.generic.add" text="Add"/></button>
                  		</div>
            	 </div>
			
		  </form:form>
				
				
				
				<br />
				<!-- Listing grid include -->
				<c:set value="/admin/promotion-categories/paging.html?promotionId=${promotion.id}" var="pagingUrl" scope="request" />
				<c:set value="/admin/promotion-categories/remove.html?promotionId=${promotion.id}" var="removeUrl" scope="request" />
				<c:set value="/admin/promotion/displayPromotionToCategories.html?id=${promotion.id}" var="refreshUrl" scope="request" />
				<c:set var="entityId" value="categoryId" scope="request"/>
				<c:set var="componentTitleKey" value="label.categories.title" scope="request" />
				<c:set var="canRemoveEntry" value="true" scope="request" />
				<c:set var="gridHeader" value="/pages/admin/products/product-categories-gridHeader.jsp" scope="request"/>
				<jsp:include page="/pages/admin/components/list.jsp"></jsp:include>
				<!-- End listing grid include -->
			

		</div>
	   </div>
	</div>
</div>	