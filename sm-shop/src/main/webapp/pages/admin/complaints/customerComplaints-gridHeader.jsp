<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



					{title:"<s:message code="label.entity.id" text="Id"/>", name:"id", canFilter:false},
        			{title:"<s:message code="label.entity.complaints" text="Complaints "/>", name:"note",canEdit:false,editorType:"textArea"},
        			{title:"<s:message code="label.generic.email" text="Email"/>", name:"email",canEdit:false},
        			{title:"<s:message code="label.entity.reason" text="Customer Name"/>", name:"complaintReason",canEdit:false},
        			{title:"<s:message code="label.entity.status" text="status"/>", name:"status", valueMap:{under:"Under Processing", closed:"Closed"},canEdit:false},
        			{title:"<s:message code="label.entity.date" text="date"/>", name:"complaintDate", type: "datetime", showTitle: false,left: 200, top: 200,
        			
    editorType: "DateRangeItem",canFilter:true,canEdit:false},
        			{title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false,canSort:false, canReorder:false}
