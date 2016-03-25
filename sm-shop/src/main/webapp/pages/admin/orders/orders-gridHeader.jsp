<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



	{title:"<s:message code="label.entity.id" text="Id"/>", name:"orderId"},
    {title:"<s:message code="label.customer.name" text="Customer name"/>", name:"customer"},
    {title:"<s:message code="label.customer.email2" text="Customer email"/>", name:"customerEmail"},
    {title:"<s:message code="label.order.total" text="Total"/>", name:"amount", canFilter:false},
    {title:"<s:message code="label.order.date" text="Date"/>", name:"date", canFilter:false},
     {title:"<s:message code="label.order.country" text="Country"/>", name:"country", canFilter:false},
    {title:"<s:message code="label.entity.status" text="Status"/>", name:"status", canFilter:true},
    {title:"<s:message code="label.order.payment" text="Payment"/>", name:"paymentModule", canFilter:false},
    {title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false,canSort:false, canReorder:false}
    
