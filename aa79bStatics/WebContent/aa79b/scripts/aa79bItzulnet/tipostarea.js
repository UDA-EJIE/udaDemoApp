jQuery(function($){
	$('#tiposTarea_feedback').rup_feedback({
		block : false
	});
	$("#tiposTarea").rup_table({
		url: "../tipostarea",
		toolbar: {
			 id: "tiposTarea_toolbar"
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
			"",
			txtgestion,
			txttiempogestion,
			txtcierre,
			txtestado
		],
		colModel: [
			{ 	name: "id015", 
			 	label: "label.id",
			 	index: "ID015",
				align: "right", 
				width: 30, 
				isNumeric: true,
				fixed: false
			},
			{ 	name: "descEu015", 
			 	label: "label.descEu",
			 	index: "DESCNORMEU",
				align: "", 
				width: 150, 
				fixed: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "descEs015", 
			 	label: "label.descEs",
			 	index: "DESCNORMES",
				align: "", 
				width: 150,
				fixed: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name : "indGestionAsoc015", 
				align: "", 
				fixed: false,
				hidden:true
			},
			{ 	name : $.rup.lang === 'es' ? "descGestionEs"
					: "descGestionEu", 
				 	index:  $.rup.lang === 'es' ? "INDGESTIONASOCES"
							: "INDGESTIONASOCEU", 
				align: "", 
				fixed: false,
				hidden:false
			},
			{ 	name : $.rup.lang === 'es' ? "descTiempoGestionEs"
					: "descTiempoGestionEu", 
				 	index:  $.rup.lang === 'es' ? "INDTIEMPOGESTIONES"
							: "INDTIEMPOGESTIONEU", 
				align: "", 
				fixed: false,
				hidden:false
			},
			{ 		name : $.rup.lang === 'es' ? "descCierreEs"
					: "descCierreEu", 
				 	index:  $.rup.lang === 'es' ? "INDREQCIERREES"
							: "INDREQCIERREEU", 
			 	label: "label.cierre",
				align: "", 
				width: 50,
				fixed: false
			},
			{ 	name : $.rup.lang === 'es' ? "descEstadoEs"
					: "descEstadoEu", 
			 	index:  $.rup.lang === 'es' ? "ESTADODESCES"
						: "ESTADODESCEU", 
				label: "label.estado",
				align: "", 
				width: 50, 
				fixed: false
			}
        ],
        model:"TiposTarea",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter",
        	"report"
         	],
		primaryKey: "id015",
		sortname: "id015",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../tipostarea/xlsxReport", click:
								function(event){
						 		if(!jQuery("#tiposTarea_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../tipostarea/pdfReport", click:
								function(event){
						 		if(!jQuery("#tiposTarea_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
       formEdit:{
       	detailForm: "#tiposTarea_detail_div",
			fillDataMethod: "clientSide",
        	validate:{ 
        		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
   			rules:{
   				"descEs015":{
						required: true
   					},
   				"descEu015":{
						required: true
   					},
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
	jQuery('#estado015_filter_table').rup_combo(estadoOption);
	jQuery('#estado015_detail_table').rup_combo(estadoOption);

		
	jQuery('#indReqCierre015_filter_table').rup_combo(estadoOption);
	jQuery('#indGestionAsociada015_filter_table').rup_combo(estadoOption);
	jQuery('#indVisibleTrabajo015_filter_table').rup_combo(estadoOption);
	
	//jQuery('#indTiempoGestion015_detail_table').rup_combo(estadoOption);
	
	
	setFocusFirstInput("#tiposTarea_filter_form");
    jQuery("#tiposTarea_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#tiposTarea_detail_form");
    });
    $("#tiposTarea").on("rupTable_afterFormFillDataServerSide", function(){     
    	var id = $('#tiposTarea').rup_table("getSelectedRows");
    	var isGestionAsoc = $("#tiposTarea").rup_table("getCol", id, "indGestionAsoc015");
    	
    	if(isGestionAsoc === "N") {
    		$("#divTiempoGestion").hide();
    		$("#divVisibleTrabajo").show();
    		if ($('#indReqCierre015_detail_table').prop( "disabled")){
    			$('#indReqCierre015_detail_table').bootstrapSwitch('toggleDisabled',true, true);
    			$('#indReqCierre015_detail_table').prop( "disabled", false );
    		}
    		$('#estado015_detail_table').rup_combo("enable");
    		$('#descEu015_detail_table').attr("disabled", false);
       	 	$('#descEs015_detail_table').attr("disabled", false);
    	} else {
    		if (!$('#indReqCierre015_detail_table').prop( "disabled")){
    			$('#indReqCierre015_detail_table').bootstrapSwitch('toggleDisabled',true, true);
    			$('#indReqCierre015_detail_table').prop( "disabled", true );
    		}
    		$('#estado015_detail_table').rup_combo("disable");
    		$('#descEu015_detail_table').attr("disabled", true);
       	 	$('#descEs015_detail_table').attr("disabled", true);
    		$("#divTiempoGestion").show();
    		$("#divVisibleTrabajo").hide();
    	}
    	
        jQuery('#indReqCierre015_detail_table').bootstrapSwitch('setState', jQuery('#indReqCierre015_detail_table').is(':checked'));
        jQuery('#indTiempoGestion015_detail_table').bootstrapSwitch('setState', jQuery('#indTiempoGestion015_detail_table').is(':checked'));
        jQuery('#indVisibleTrabajo015_detail_table').bootstrapSwitch('setState', jQuery('#indVisibleTrabajo015_detail_table').is(':checked'));
        
    });
    
    $('#tiposTarea').on("rupTable_beforeAddRow", function(){
    	$("#divTiempoGestion").hide();
    	$("#divVisibleTrabajo").show();
    	if ($('#indReqCierre015_detail_table').prop( "disabled")){
			$('#indReqCierre015_detail_table').bootstrapSwitch('toggleDisabled',true, true);
			$('#indReqCierre015_detail_table').prop( "disabled", false );
		}
    	 jQuery('#indReqCierre015_detail_table').bootstrapSwitch('setState', false);
    	 jQuery('#indTiempoGestion015_detail_table').bootstrapSwitch('setState', false);
    	 jQuery('#indVisibleTrabajo015_detail_table').bootstrapSwitch('setState', false);
    	 
    	 $('#descEu015_detail_table').attr("disabled", false);
    	 $('#descEs015_detail_table').attr("disabled", false);
	});
    
	});