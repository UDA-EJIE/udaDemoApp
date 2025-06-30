jQuery(function($){

	$('#motivosrechazo_feedback').rup_feedback({
		block : false
	});
	
	$("#motivosrechazo").rup_table({
		
		url: "../motivosrechazo",
		toolbar: {
			 id: "motivosrechazo_toolbar"
				 ,defaultButtons:{
					 add : true
					,edit : true
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : true
					
				 }
		},
		colNames: [
				txtid,
				txtdescEu,
				txtdescEs,
				txtestado,
				
			
		],
		colModel: [
			{	name: "id013", 
			 	label: "label.id",
			 	index: "ID013",
				align: "right", 
				width: 30, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name: "descEu013", 
			 	label: "label.descEu",
			 	index: "DESCNORMEU",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEs013", 
			 	label: "label.descEs",
			 	index: "DESCNORMES",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true,
				sortable: true
			},
			{ 	name : $.rup.lang == 'es' ? "descEstadoEs"
					: "descEstadoEu", 
			 	index:  $.rup.lang == 'es' ? "ESTADODESCES"
						: "ESTADODESCEU", 
				label: "label.estado",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
        ],

        model:"Motivosrechazo",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter",
        	"report"
         	],
		primaryKey: ["id013"],
		sortname:"id013",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../motivosrechazo/xlsxReport", click:
								function(event){
						 		if(!jQuery("#motivosrechazo_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../motivosrechazo/pdfReport", click:
								function(event){
						 		if(!jQuery("#motivosrechazo_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
        formEdit:{
        	detailForm: "#motivosrechazo_detail_div",
         	validate:{ 
         		showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false,
    			rules:{
    				"id013":{
						required: false
    					},
    				"estado013":{
						required: true
    					},
    				"descEs013":{
						required: true
    					},
    				"descEu013":{
						required: true
    					}
    				}
    		},
    		addEditOptions:{
   			 width:800,
   			 fillDataMethod: "serverSide",
   			 reloadAfterSubmit: true
   		 },
   		editOptions: {
   			 mtype: "POST",
   			 fillDataMethod: "serverSide",
   			 ajaxEditOptions:{
   				 contentType: 'application/json'
   			}
   		 },
   		 addOptions: {
   			 mtype: "POST",
   			 ajaxEditOptions:{
   				 contentType: 'application/json'
   			}
   		 }
        },
        filter:{
			clearSearchFormMode:"reset"
	    	,validate:{
	    		rules:{
    				
    			}
	    	}
		 }
	});
	
	var estadoOption = {
			loadFromSelect: true
			,width: "150"
			,ordered: false	
			,rowStriping: true
	};
	jQuery('#estado013_filter_table').rup_combo(estadoOption);
	jQuery('#estado013_detail_table').rup_combo(estadoOption);
	
	
	setFocusFirstInput("#motivosrechazo_filter_form");
    jQuery("#motivosrechazo_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#motivosrechazo_detail_form");
    });
	});