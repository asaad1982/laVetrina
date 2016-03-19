<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



					
        			
        			
        			
        			{title:"Products", name:"productName", canEdit:false,canFilter:false},
        			{title:"Category", name:"categoryName", canEdit:false,canFilter:false},
        			{title:"Jan", name:"Jan", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"Feb", name:"Feb", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"Mar", name:"Mar",canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"April", name:"April", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"May", name:"May", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"June", name:"Jun", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"July", name:"July", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			
        			{title:"August", name:"Aug", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"September", name:"Sept", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"Oct", name:"Oct", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"Nov", name:"Nov", canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
         }},
        			{title:"Dec", name:"Dec",canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return "-";
        			 }},
        			{name:"Total",canFilter:false,
         				summaryFunction:"sum",recordSummaryFunction:"multiplier",
                        showGridSummary:true, showGroupSummary:true,
                        align:"right",
                      formatCellValue:function (value) {
                      
                      if (isc.isA.Number(value)) {
                       return value.toCurrencyString("$");
                     }
                     return value;
         }
        }
        		
        			