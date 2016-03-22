{name: "note",
                         title: "Complaint",
                         type: "text"
                        },
                        {type: "blurb",
                         defaultValue: "<b>AND</b>"
                        },
                        {name: "email",
                         title: "Email Address",
                         type: "test"
                         
                        },
                        {type: "blurb",
                         defaultValue: "<b>AND</b>"
                        },
                        {name: "startDate",
                         title: "From Date",
                         type: "date",
                         useTextField: true
                        },
                         {name: "endDate",
                         title: "to Date",
                         type: "date",
                         useTextField: true
                        },
                        {
        					name: "status", title: "Select Item", editorType: "SelectItem", 
                            
                           valueMap:{under:"Under Processing", closed:"Closed"}     
        
					        
    					},
    					{name: "reason",
                         title: "Complaint Reason",
                         type: "text"
                        }