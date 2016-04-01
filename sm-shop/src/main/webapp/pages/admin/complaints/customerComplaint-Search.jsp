{name: "note",
                         title: "Customer Name",
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
                         title: "Date From",
                         type: "date",
                         useTextField: true
                        },
                        {type: "blurb",
                         defaultValue: "<b>AND</b>"
                        },
                         {name: "endDate",
                         title: "Date To",
                         type: "date",
                         useTextField: true
                        },
                        {type: "blurb",
                         defaultValue: "<b>AND</b>"
                        },
                        {
        					name: "status", title: "<b>Complaint Reason</b>", editorType: "SelectItem",
                            
                           valueMap:{under:"Under Processing", closed:"Closed"}     
        
					        
    					},
                        {type: "blurb",
                         defaultValue: "<b>AND</b>"
                        },
    					{name: "reason",
                         title: "Complaint",
                         type: "text"
                        }