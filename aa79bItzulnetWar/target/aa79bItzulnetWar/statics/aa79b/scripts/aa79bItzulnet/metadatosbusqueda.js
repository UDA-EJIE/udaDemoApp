jQuery(function($){	 
	
	$('#metadatosBusqueda_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	$("#metadatosBusqueda").rup_table({
		url: "../metadatosbusqueda",
		toolbar:{
			id: "metadatosBusqueda_toolbar"
			, defaultButtons:{
				add:true,
				edit:true,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
		},
		colNames: [
			txtid,
			txtdescEu,
			txtdescEs,			
			txtestado,
			""
		],
		colModel: [
			{ 	name: "id", 
			 	label: "label.id",
			 	index: "ID_019",
				align: "right", 
				width: 30, 
				isNumeric: true,
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEu", 
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
			{ 	name: "descEs", 
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
			{ 	name: $.rup.lang === 'es' ? "descEstadoEs"
					: "descEstadoEu", 
			 	index: $.rup.lang === 'es' ? "ESTADODESCES"
						: "ESTADODESCEU",
				label:"label.estado",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{
				name:"estado",
				index:"ESTADO_019",
				hidden:true
			}
        ],
        model:"MetadatosBusqueda",
        usePlugins:[
			"formEdit",
			"feedback",
        	"toolbar",
        	"responsive",
        	"filter",
        	"report"
         	],
		primaryKey: "id",
		sortname: "ID_019",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../metadatosbusqueda/xlsxReport", click:
								function(event){
						 		if(!jQuery("#metadatosBusqueda_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../metadatosbusqueda/pdfReport", click:
								function(event){
						 		if(!jQuery("#metadatosBusqueda_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
        formEdit:{
        	detailForm: "#metadatosBusqueda_detail_div",
        	validate:{ 
         		showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false,
    			rules:{
    				"estado":{required: true},
    				"descEs":{required: true},
    				"descEu":{required: true}
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
	   			 ajaxEditOptions:{ contentType: 'application/json'}
	   		 },
	   		 addOptions: {
	   			 mtype: "POST",
	   			 reloadAfterSubmit : true,
	   			 ajaxEditOptions:{contentType: 'application/json'}
	   		 }
	     },
	     filter:{clearSearchFormMode:"reset" }
	});
	var estadoOption = {
			loadFromSelect: true
			,width: "150"
			,ordered: false	
			,rowStriping: true
	};
	jQuery('#estado_filter_table').rup_combo(estadoOption);
	jQuery('#estado_detail_table').rup_combo(estadoOption);
	
	setFocusFirstInput("#metadatosBusqueda_filter_form");
    jQuery("#metadatosBusqueda_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#metadatosBusqueda_detail_form");
    });
});