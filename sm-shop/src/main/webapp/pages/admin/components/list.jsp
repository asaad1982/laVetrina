<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				

			<script>
			      			     
							


								
								isc.RestDataSource.create({ 
									ID:"dataSource", 
									dataFormat:"json",  
									operationBindings:[ 
										{operationType:"fetch", dataProtocol:"postParams",dataURL: "<c:url value="${pagingUrl}" />"},
										{operationType:"remove", dataProtocol:"postParams",dataURL: "<c:url value="${removeUrl}" />"},
										{operationType:"update", dataProtocol:"postParams",dataURL: "<c:url value="${updateUrl}" />"},
									],
									transformResponse : function (dsResponse, dsRequest, jsonData) {
										
										var status = isc.XMLTools.selectObjects(jsonData, "/response/status");
										//alert('check status ' + status);
										if (status != 0) {
											if(status==9999) {//operation completed
												//reload
												<c:if test="${afterRemoveUrl!=null}">
													window.location='<c:url value="${afterRemoveUrl}" />';
												</c:if>
												<c:if test="${refreshUrl!=null}">
													window.location='<c:url value="${refreshUrl}" />';
												</c:if>
											} else {

												var msg = isc.XMLTools.selectObjects(jsonData, "/response/statusMessage");
												alert("! " + msg);
											}
										}
									}
								}); 
								

							  
							  isc.ListGrid.create({
    								ID: "entityList",
    								border:1,
    								dataSource: "dataSource",
    								showRecordComponents: true,    
    								showRecordComponentsByCell: true,
    								canRemoveRecords: <c:out value="${canRemoveEntry}" />,
    								autoFetchData: true,
    								showFilterEditor: true,
    								filterOnKeypress: false,
									dataFetchMode:"paged",
									canEdit:false,
									editByCell: true,
									editEvent: "click",
									<c:if test="${showGridSummary!=null && showGridSummary!='' && showGridSummary=='true'}">
									showGridSummary:true,
                                    showGroupSummary:true,
                                    </c:if>


    						      fields:[
    						      		<jsp:include page="${gridHeader}"></jsp:include>
    							],
    							autoFitData: "vertical",
    							showFilterEditor: true,
                                autoFitMaxRecords: 10,
    							selectionType: "multiple",
								removeData: function () {
									if (confirm('<s:message code="label.entity.remove.confirm" text="Do you really want to remove this record ?" />')) {
										return this.Super("removeData", arguments);
									}
								},
								fetchData: function () {
									return this.Super("fetchData", arguments);
								},
								updateData: function () {
									return this.Super("updateData", arguments);
								},
								
								<c:if test="${expandDetails!=null && expandDetails!=''}">
								canExpandRecords: true,
    							expansionMode: "detailField",
    							detailField: "<c:out value="${expandDetails}"/>",
    							</c:if>
    							

							    <c:if test="${groupByEntity!=null && groupByEntity!=''}">
							    groupStartOpen:"all",
							    groupByField: '<c:out value="${groupByEntity}"/>',
							    </c:if>
							    
							    <c:if test="${showGridSummary!=null && showGridSummary!='' && showGridSummary=='true'}">
							     showGridSummary:true,
                                 showGroupSummary:true,
							    </c:if>

    							createRecordComponent : function (record, colNum) {  
        							var fieldName = this.getFieldName(colNum);
        							if (fieldName == "buttonField") {  

	        						
	           						var button = isc.IButton.create({
	                						height: 18,
	                						width: 65,
	               					 		title: "<s:message code="label.entity.details" text="Details"/>",
	                						click : function () {
	                							
                								var url = '<c:url value="${editUrl}" />';
                								var queryString = '?id=' + record["<c:out value="${entityId}" />"];
                								<c:if test="${appendQueryStringToEdit!=null && appendQueryStringToEdit!=''}">
                									queryString = queryString + '&<c:out value="${appendQueryStringToEdit}" />' ;
                								</c:if>
                								var locationUrl = url + queryString;
                    							window.location= locationUrl;

	                						}
	            					});
	            					return button;  
            				
            					}

 
    						  }


								});
								
								
<c:if test="${gridSearch!=null}">
 isc.DynamicForm.create({
                    ID: "filterForm",
                    width: 300,
                    position:"relative",
                    operator: "and",
                    saveOnEnter: true,
                    dataSource: dataSource,
                    submit: function () {
                        entityList.filterData(filterForm.getValuesAsCriteria());
                    },
                    fields: [
                    <jsp:include page="${gridSearch}"></jsp:include>
                        
                    ]
                });
                isc.IButton.create({
                    title: "Filter",
                    position:"relative",
                    click: function () {
                        filterForm.submit();
                    }
                });
          
</c:if>

// Define application layout
// ---------------------------------------------------------------------

isc.HLayout.create({
    ID:"pageLayout",
    width: "980",
    height: "600",
    position:"relative",
    members:[
        isc.SectionStack.create({
            ID:"mainLayout",
            visibilityMode:"multiple",
            animateSections:true,
            sections:[
                {title:"<s:message code="${componentTitleKey}" text="{componentTitleKey} UNDEFINED"/>", autoShow:true, items:[entityList]}
            ]
        })
    ]
});

isc.Page.setEvent("load", "pageLayout.draw()");
			      			     
			 </script>
	      			     