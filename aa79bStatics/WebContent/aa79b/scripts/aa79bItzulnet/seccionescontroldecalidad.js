jQuery(function($){
	
	$('#seccionesControlDeCalidad_feedback').rup_feedback({
		block : false
	});

	$("#seccionesControlDeCalidad").rup_table({
		url: "../seccionescontroldecalidad",
		toolbar: {
			 id: "seccionesControlDeCalidad_toolbar"
				 ,defaultButtons:{
					 add : false
					,edit : true
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : true
				 }
		},
		colNames: [
			txtId,
			txtNombreSeccion,
			txtTipoSeccion,
			txtVisible,
			txtOrden		
		],
		colModel: [
			{ 	name: "id", 
			 	label: "label.id",
			 	index: "ID",
				align: "right", 
				width: 30, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},

			{ 	name: "nombreEu", 
			 	label: "label.nombreSeccion",
			 	index: "NOMBREEU",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			
			{ 	name : $.rup.lang === 'es' ? "tipoSeccionEs"
					: "tipoSeccionEu", 
			 	index:  $.rup.lang === 'es' ? "TIPOSECCIONES"
					: "TIPOSECCIONEU", 
			 	label: "label.tipoSeccion",
				align: "", 
				width: 50, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name : $.rup.lang === 'es' ? "descVisibleEs"
					: "descVisibleEu", 
			 	index:  $.rup.lang === 'es' ? "INDVISIBLEES"
					: "INDVISIBLEEU", 
				label: "label.visible",
				align: "", 
				width: 50, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true,
				sortable: true
			},
			{ 	name: "orden", 
				label: "label.orden",
			 	index: "ORDEN", 
				align: "", 
				width: 50, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
       ],

       model:"SeccionesControlDeCalidad",
       usePlugins:[
       	"formEdit",
       	"feedback",
		"toolbar",
       	"responsive",
       	"filter",
       	"report"
        	],
		primaryKey: "id",
		sortname: "id",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../seccionescontroldecalidad/xlsxReport", click:
								function(event){
						 		if(!jQuery("#seccionesControlDeCalidad_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../seccionescontroldecalidad/pdfReport", click:
								function(event){
						 		if(!jQuery("#seccionesControlDeCalidad_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
		formEdit:{
       	detailForm: "#seccionesControlDeCalidad_detail_div",
		fillDataMethod: "clientSide",
        	validate:{ 
        		showErrorsInFeedback: true,	
         		showFieldErrorsInFeedback: false,
   			rules:{
   					"tipoSeccion":{
   						required: true
					},
					"nombreEu":{
						required: true
					},
					"orden":{
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
	
	 jQuery("#seccionesControlDeCalidad_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
	        setFocusFirstInput("#seccionesControlDeCalidad_detail_form");
	    });
	    $("#seccionesControlDeCalidad").on("rupTable_afterFormFillDataServerSide", function(){     
	    	var id = $('#seccionesControlDeCalidad').rup_table("getSelectedRows");
	    	    	
	        jQuery('#indRespuesta_detail_table').bootstrapSwitch('setState', jQuery('#indRespuesta_detail_table').is(':checked'));
	        jQuery('#indObservaciones_detail_table').bootstrapSwitch('setState', jQuery('#indObservaciones_detail_table').is(':checked'));
	        jQuery('#indVisible_detail_table').bootstrapSwitch('setState', jQuery('#indVisible_detail_table').is(':checked'));
	        
	    });
});