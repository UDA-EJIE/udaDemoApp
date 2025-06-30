jQuery(function($){
	$('#tiposrevision_feedback').rup_feedback({
		block : false
	});
	$("#tiposrevision").rup_table({		
		url: "../tiposrevision",
		toolbar: {
			 id: "tiposrevision_toolbar"
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
		colNames: [ txtid,
			txtdescEu,
			txtdescEs,	
			txtxpalabras,
			txtxrelrevtrad,
			txtestado
		],
		colModel: [
			{ 	name: "id018", 
			 	label: "label.id",
				align: "right", 
				width: 20, 
				fixed: false,
				isNumeric: true
			},
			{ 	name: "descEu018", 
			 	label: "label.descEu",
			 	index: "DESCNORMEU",
				align: "", 
				width: 150, 				
				fixed: false
			},
			{ 	name: "descEs018", 
			 	label: "label.descEs",
			 	index: "DESCNORMES",
				align: "", 
				width: 150, 				
				fixed: false
			},
			{ 	name: "porPalabrasRev018", 
				index : "PORPALABRASREV018",
				label: "label.xPalabras",
				align: "right", 
				width: 90, 				
				fixed: false,
				isNumeric: true
			},
			{ 	name: "porRelTradRev018", 
				index : "PORRELREVTRAD018",
				label: "label.relacionTareaRevTrad",
				align: "right", 
				width: 90, 				
				fixed: false,
				isNumeric: true
			},
			{ 	name: $.rup.lang === 'es' ? "estadoDescEs" : "estadoDescEu",
			 	index: $.rup.lang === 'es' ? "ESTADODESCES"	: "ESTADODESCEU",
			 	label: "label.estado",
				align: "", 
				width: 30, 
				isNumeric: true,
				fixed: false
			}
			
        ],
        model:"TiposRevision",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",        	
        	"responsive",
        	"filter",        	
        	"report"
         	],
		primaryKey: "id018",
		sortname: "id018",
		sortorder: "asc",
		loadOnStartUp: true,		
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../tiposrevision/xlsxReport", click:
								function(event){
						 		if(!jQuery("#tiposrevision_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../tiposrevision/pdfReport", click:
								function(event){
						 		if(!jQuery("#tiposrevision_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
			]
		},		
        formEdit:{
        	detailForm: "#tiposrevision_detail_div",        	
         	validate:{ 
    			rules:{
    				"id018":{
						required: true
    					},
    				"descEs018":{
						required: true,
						maxlength: 50
    					},
    				"descEu018":{
						required: true,
						maxlength: 50
    					},
    				"observEu018":{
						required: true,
						maxlength: 400
    					},
    				"observEs018":{
						required: true,
						maxlength: 400
    					},    				
    				"porPalabrasRev018":{
						required: true,
						integer: true,
						maxlength: 3,
						min: 0,
						max:100
    					},    				
    				"porRelTradRev018":{
						required: true,
						integer: true,
						maxlength: 3,
						min: 0,
						max:100
    					}
    				},
    				showErrorsInFeedback: true,
    	     		showFieldErrorsInFeedback: false
    		},    		
    		addEditOptions:{
				 width:800,
				 fillDataMethod: "serverSide",
				 reloadAfterSubmit: true
			 },
			 editOptions: {
				 mtype: "POST",
				 fillDataMethod: "serverSide",
				 reloadAfterSubmit : true,
				 ajaxEditOptions:{
					 contentType: 'application/json'
				}
			 },
			 addOptions: {
				 mtype: "POST",
				 reloadAfterSubmit : true,
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
	jQuery('#estado018_filter_table').rup_combo(estadoOption);
	jQuery('#estado018_detail_table').rup_combo(estadoOption);
	
	setFocusFirstInput("#tiposrevision_filter_form");
	jQuery("#tiposrevision_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#tiposrevision_detail_form");
	});
	
});