jQuery(function($){
	
	$('#tiposinterpretacion_feedback').rup_feedback({
		block : false
	});

	$("#tiposinterpretacion").rup_table({
		
		url: "../tiposinterpretacion",
		
		toolbar: {
			 id: "tiposinterpretacion_toolbar"
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
			txtTipoFactura,
			txtestado		
		],
		colModel: [
			{ 	name: "id008", 
			 	label: "label.id",
			 	index: "ID008",
				align: "right", 
				width: 30, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},

			{ 	name: "descEu008", 
			 	label:  "label.descEu",
			 	index: "DESCNORMEU",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			
			{ 	name: "descEs008",
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
			{ 	name : $.rup.lang == 'es' ? "descFacturaEs"
					: "descFacturaEu", 
			 	index:  $.rup.lang == 'es' ? "DESCFACTURAES"
						: "DESCFACTURAEU", 
				label: "label.facturacion",
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
			}
       ],

       model:"TiposInterpretacion",
       usePlugins:[
       	"formEdit",
       	"feedback",
		"toolbar",
       	"responsive",
       	"filter",
       	"report"
        	],
		primaryKey: "id008",
		sortname: "id008",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../tiposinterpretacion/xlsxReport", click:
								function(event){
						 		if(!jQuery("#tiposinterpretacion_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../tiposinterpretacion/pdfReport", click:
								function(event){
						 		if(!jQuery("#tiposinterpretacion_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
       formEdit:{
       	detailForm: "#tiposinterpretacion_detail_div",
        	validate:{ 
        		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
   			rules:{
   				/*"id008":{
						required: false
   					},*/
   				"descEs008":{
						required: true
   					},
   				"descEu008":{
						required: true
   					},/*
   				"estado008":{
						required: false
   					},
   				"tipoFacturacion008":{
						required: false
   				}*/
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
jQuery('#estado008_filter_table').rup_combo(estadoOption);
jQuery('#estado008_detail_table').rup_combo(estadoOption);

jQuery('#tipoFacturacion008_filter_table').rup_combo(estadoOption);
jQuery('#tipoFacturacion008_detail_table').rup_combo(estadoOption);

setFocusFirstInput("#tiposinterpretacion_filter_form");
jQuery("#tiposinterpretacion_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
    setFocusFirstInput("#tiposinterpretacion_detail_form");
});
});