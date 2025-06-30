var configCombo = {
	loadFromSelect: true
	,width: "100%"
	,ordered: false	
	,rowStriping: true
	,open: function(){
		var id = $(this).attr("id");
        $("#"+id+"-menu").width($("#"+id+"-button").width());
    }
};

function finalizarCargaPantalla(){
	cargaValoresDefecto();
	$("#camposControlCalidad_filter_filterButton").click();
}

function cargaValoresDefecto(){
	$('#idSeccion_filter_table').rup_combo("select","2"); //inicialmente solo se va a trabajar la sección 2
	$('#idSeccion_filter_table').rup_combo("disable");
	$('#nombreCampo_filter_table').val('');
	$('#indVisible_filter_table').rup_combo("select",""); 
}

jQuery(function($) {

	$('#camposControlCalidad_feedback').rup_feedback({
		block : false
	});
	$("#indVisible_filter_table").rup_combo(configCombo);
	
	$("#idSeccion_filter_table").rup_combo({
		source : "../seccionescontroldecalidad/suggest",
		sourceParam : {
			label: "nombreEu",
			value: "id",
			style: "css"
		},
		blank: "",
		width: "100%",
		ordered: true,	
		rowStriping: true,
		open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		},
	   
	});
	$("#idSeccion_detail_table").rup_combo({
		source : "../seccionescontroldecalidad/suggest",
		sourceParam : {
			label: "nombreEu",
			value: "id",
			style: "css"
		},
		blank: "",
		width: "100%",
		ordered: true,	
		rowStriping: true,
		open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		},
	   
	});
	$("#tipoCampo_detail_table").rup_combo(configCombo);
	
	$("#idPeso_detail_table").rup_combo({
		source : "../pesosvaloracioncalidad/mostrarPesosValoracionCalidad",
		sourceParam : {
			label: "descEu",
			value: "id",
			style: "css"
		},
		blank: "",
		width: "100%",
		ordered: true,	
		rowStriping: true,
		open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		},
	   
	});
	
	
	
	
	
	
	$("#camposControlCalidad").rup_table(
			{

				url : "../camposcontrolcalidad",
				toolbar : {
					id : "camposControlCalidad_toolbar",
					defaultButtons : {
						add : true,
						edit : true,
						cancel : false,
						save : false,
						clone : false,
						"delete" : false,
						filter : true

					}
				},
				colNames: [ 
					txtId, 
					txtnombre,
					txttipocampo,
					txtvisible,
					txtseccion,
					txtOrden
					],
				
				colModel : [ {
					name : "idCampoControlCalidad",
					label : "label.idCampoControlCalidad",
					index : "ID0C1",
					align : "right",
					width : 10,
					isNumeric : true,
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				}
				, {
					name : "nombreCampo",
					label : "label.nombreCampo",
					index : "NOMBRECAMPO",
					align : "left",
					width : 200,
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				, {
					name : "tipoCampoEU",
					label : "label.tipoCampo",
					index : "TIPOCAMPOEU",
					align : "left",
					width : 50,
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				}, {
					name : "indVisible",
					label : "label.visible",
					index : "VISIBLE",
					align : "right",
					width : 10,
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject) {
						if('S'.localeCompare(cellvalue)==0){
							return $.rup.i18n.app.comun.si;
						}
						return $.rup.i18n.app.comun.no;
					}
				}, {
					name : "seccion.nombreEu",
					label : "label.seccion",
					index : "SECCIONEU",
					align : "right",
					width : 40,
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				}, {
					name : "orden",
					label : "label.orden",
					index : "ORDEN",
					align : "right",
					width : 10,
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				} 
				],

				model : "CamposControlCalidad",
				usePlugins : [
					"formEdit",
					"feedback",
					"toolbar",
					"filter",
					"responsive"
				],
//				feedback:{
//					config:{
//						block:false,
//						gotoTop: false
//					}
//				},
				primaryKey : "idCampoControlCalidad",
				sortname : "ID0C1",
				sortorder : "asc",
				loadOnStartUp : false,
				formEdit:{
		        	detailForm: "#camposControlCalidad_detail_div",
		         	validate:{ 
		         		showErrorsInFeedback: true,
		                showFieldErrorsInFeedback: false,
		    			rules:{
		    				"idCampoControlCalidad":{
								required: false
		    					},
							"nombreCampo":{
								required: true
		    					},
		    				"tipoCampo":{
								required: true
		    					},
							"indVisible":{
								required: true
		    					},
							"idSeccion":{
								required: true
		    					},
							"orden":{
								required: true
		    					},
							"peso.id":{
								required: true
		    					}
    					},
						errorPlacement:function(error, element){
							// posicion de mensaje de error de validacion
							if ( element.is("#idPeso_detail_table")) {
								// posicion cambiada para peso
								$('#idPeso_detail_table_container').append(error);
							}
							else {
								// posicion por defecto
								element.after( error ); 
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


	$("#camposControlCalidad").on("rupTable_afterFormFillDataServerSide", function(){     
        jQuery('#indVisible_detail_table').bootstrapSwitch('setState', jQuery('#indVisible_detail_table').is(':checked'));
        jQuery('#indObservaciones_detail_table').bootstrapSwitch('setState', jQuery('#indObservaciones_detail_table').is(':checked'));
        jQuery('#indObligatorio_detail_table').bootstrapSwitch('setState', jQuery('#indObligatorio_detail_table').is(':checked'));
        
    });
	
	jQuery("#camposControlCalidad").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		$('#idSeccion_detail_table').rup_combo("select","2"); //inicialmente solo se va a trabajar la sección 2
    	$('#idSeccion_detail_table').rup_combo("disable");
    	$('#tipoCampo_detail_table').rup_combo("select","1"); 
    	$('#tipoCampo_detail_table').rup_combo("disable");
    	$('#indObservaciones_detail_table').bootstrapSwitch('setState', true);
    	$("#indObservaciones_detail_table").attr('disabled','disabled');
    	$('#indObligatorio_detail_table').bootstrapSwitch('setState', true);
    	$("#indObligatorio_detail_table").attr('disabled','disabled');
    	
    	$('#notaNoOk_detail_table').attr("disabled", true);
    	$('#notaOk_detail_table').attr("disabled", true);
    	
    	$("#camposControlCalidad").rup_table("updateSavedData", function(savedData){
			savedData["seccion.id"]="2";
			savedData["tipoCampo"]="1";
			savedData["indObservaciones"]="S";
			savedData["indObligatorio"]="S";
		});
		
		jQuery("#camposControlCalidad").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
			// eliminar mensajes error de validacion
			$('#camposControlCalidad_detail_form div[generated="true"]').remove();
			// eliminar class error de elementos de formulario
			eliminarMensajesValidacion();
		});
		
    	
    	
    });
	
//	jQuery("#camposControlCalidad_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
//        setFocusFirstInput("#camposControlCalidad_detail_form");
//    });
	
	$("#camposControlCalidad_filter_limpiar").click(function(event){
		event.preventDefault();
		event.stopImmediatePropagation();	
		finalizarCargaPantalla();
	});

	setFocusFirstInput("#camposControlCalidad_filter_form");
	
	llamadasFinalizadas("finalizarCargaPantalla");
	
	// Mostrar la pantalla
	$('.aa79b-content').addClass('in');
	
});