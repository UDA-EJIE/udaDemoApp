jQuery(function($){
	
	$('#modosInterpretacion_feedback').rup_feedback({
		block : false
	});

	$("#modosInterpretacion" +
			"").rup_table({
		
		url: "../modosinterpretacion",
		toolbar: {
			 id: "modosInterpretacion_toolbar"
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

        model:"ModosInterpretacion",
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
							url: "../modosinterpretacion/xlsxReport", click:
								function(event){
						 		if(!jQuery("#modosInterpretacion_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../modosinterpretacion/pdfReport", click:
								function(event){
						 		if(!jQuery("#modosInterpretacion_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
        formEdit:{
        	detailForm: "#modosInterpretacion_detail_div",
         	validate:{
         		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
    			rules:{
    				"id":{
						required: false
    					},
    				"descEs":{
						required: true
    					},
    				"descEu":{
						required: true
    					},
    				"estado":{
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

jQuery('#estado014_filter_table').rup_combo({
	source : [
		   {i18nCaption: "alta", value:"A"},
		   {i18nCaption: "baja", value:"B"}
	      ]
			,blank: ""
			,width: "170"
			,ordered: false	
			,rowStriping: true
});

jQuery('#estado014_detail_table').rup_combo({
	source : [
		   {i18nCaption: "alta", value:"A"},
		   {i18nCaption: "baja", value:"B"}
	      ]
			,width: "170"
			,ordered: false	
			,rowStriping: true
});

var estadoOption = {
		loadFromSelect: true
		,width: "150"
		,ordered: false	
		,rowStriping: true
	};
jQuery('#estado014_filter_table').rup_combo(estadoOption);
jQuery('#estado014_detail_table').rup_combo(estadoOption);

setFocusFirstInput("#modosInterpretacion_filter_form");
jQuery("#modosInterpretacion_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
    setFocusFirstInput("#modosInterpretacion_detail_form");
});
});