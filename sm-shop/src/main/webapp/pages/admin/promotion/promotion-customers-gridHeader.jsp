<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



				        			{title:"<s:message code="label.entity.id" text="Id"/>", name:"id",canFilter:false},
        							{title:"<s:message code="label.customer.firstname" text="First name"/>", name:"firstName",canFilter:false},
        							{title:"<s:message code="label.customer.lastname" text="Last name"/>", name:"lastName",canFilter:true},
        							{title:"<s:message code="label.generic.email" text="Email"/>", name:"email",canFilter:true},
        							{title:"<s:message code="label.customer.country" text="Customer country"/>", name:"country",canFilter:false},
        							{title:"Gender", name:"gender",valueMap:{M:"Male", F:"Female"},canEdit:false},
        							{title:"Birth Date ", name:"birthDate", type: "datetime",canFilter:true,canEdit:false},
        							{title:"Age Range ", name:"ageRange",valueMap:{1:"20-30", 2:"30-40",3:"40-50",4:"50+"},canFilter:true,canEdit:false},
        							{title:"Interset ", name:"interset",canFilter:true,canEdit:false},
        							{title:"<s:message code="label.entity.enabled" text="Enabled"/>", name:"supported", type:"boolean", canEdit:true, canFilter:true}
        							