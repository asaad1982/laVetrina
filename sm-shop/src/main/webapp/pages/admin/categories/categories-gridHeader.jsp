<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



					
        			{title:"<s:message code="label.entity.name" text="Name"/>", name:"name",canEdit:false},
        			{title:"<s:message code="label.entity.code" text="Code"/>", name:"code",canEdit:false},
        			{title:"<s:message code="label.category.parentcategory" text="Code"/>", name:"parentCategory", canFilter:false,canEdit:false},
        			{title:"<s:message code="label.entity.visible" text="Visible"/>", name:"visible", canFilter:false,valueMap:{true:"Enabled", false:"Disabled"},canEdit:false},
        			{title:"<s:message code="label.entity.order" text="Visible"/>", name:"sortOrder",canFilter:false,canEdit:false },
        			
        			{title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false,canSort:false, canReorder:false}
					