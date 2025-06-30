jQuery(function($){

	$('#motivosanulacion_feedback').rup_feedback({
		block : false
	});
	
	$("#motivosanulacion").rup_table({
		
		url: "../motivosanulacion",
		toolbar: {
			 id: "motivosanulacion_toolbar"
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
			txtestado
		],
		colModel: [
			{	name: "id012", 
			 	label: "label.id",
			 	index: "ID012",
				align: "right", 
				width: 30, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEu012", 
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
			{ 	name: "descEs012", 
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
        model:"MotivosAnulacion",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter",
        	"report"
         	],
		primaryKey: "id012",
		sortname: "id012",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../motivosanulacion/xlsxReport", click:
								function(event){
						 		if(!jQuery("#motivosanulacion_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../motivosanulacion/pdfReport", click:
								function(event){
						 		if(!jQuery("#motivosanulacion_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
        formEdit:{
        	detailForm: "#motivosanulacion_detail_div",
         	validate:{
         		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
    			rules:{
    				"id012":{
						required: false
    					},
    				"descEs012":{
						required: true
    					},
    				"descEu012":{
						required: true
    					},
    				"estado012":{
						required: false
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
	jQuery('#estado012_filter_table').rup_combo(estadoOption);
	jQuery('#estado012_detail_table').rup_combo(estadoOption);

	setFocusFirstInput("#motivosanulacion_filter_form");
    jQuery("#motivosanulacion_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#motivosanulacion_detail_form");
    });
	});