jQuery(function($){
	
	$('#tiposDocumento_feedback').rup_feedback({
		block : false
	});

	$("#tiposDocumento").rup_table({
		
		url: "../tiposdocumento",
		toolbar: {
			 id: "tiposDocumento_toolbar"
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
			txtorden,
			txtdescEu,
			txtdescEs,
			txttipoRelevancia,
			txtindFacturable,
			txtindConfidencial,
			txtindRecargoUrgencia,
			txtIndActMemoria,
			txtestado,
			""
		],
		colModel: [
			{ 	name: "id", 
			 	label: "label.id",
			 	index: "ID",
				align: "right", 
				width: 15, 
				isNumeric: true,
				fixed: false
			},
			{ 	name: "orden", 
			 	label: "label.orden",
			 	index: "ORDEN",
				align: "right", 
				width: 25, 
				isNumeric: true,
				fixed: false,
				formatter: function (cellval, opts, rowObject, action) {
					return cellval ? cellval : "";
				}
			},
			{ 	name: "descEu", 
			 	label: "label.descEu",
			 	index: "DESCNORMEU",
				align: "", 
				width: 140, 
				fixed: false
			},
			{ 	name: "descEs", 
			 	label: "label.descEs",
			 	index: "DESCNORMES",
				align: "", 
				width: 140, 
				fixed: false
			},
			{ 	name: "tipoRelevanciaDesc", 
			 	label: "label.tipoRelevancia",
			 	index: "DESCRELEVANCIA",
				align: "", 
				width: 80, 
				ruptype: "combo"
			},
			{ 	name: "indFacturableDesc", 
			 	label: "label.indFacturable",
			 	index: "INDFACTURABLEDESC",
				align: "", 
				width: 70, 
				fixed: false
			},
			{ 	name: "indConfidencialDesc", 
			 	label: "label.indConfidencial",
			 	index: "INDCONFIDENCIALDESC",
				align: "", 
				width: 50,  
				fixed: false
			},
			{ 	name: "indRecargoUrgenciaDesc", 
			 	label: "label.indRecargoUrgencia",
			 	index: "INDRECARGOURGENCIADESC",
				align: "", 
				width: 90,  
				fixed: false
			},
			{ 	name: "indActMemoriaDesc", 
			 	label: "label.indActMemoria",
			 	index: "INDACTMEMORIADESC",
				align: "", 
				width: 90,  
				fixed: false
			},
			{ 	name : "estadoDesc",
			 	label: "label.estado",
			 	index : "ESTADODESC",
				align: "left", 
				width: 50, 
				ruptype: "combo"
			},
			{
				name : "tipoRelevancia.idTipoRelevancia",
				hidden: true
			}
        ],

        model:"TiposDocumento",
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
							url: "../tiposdocumento/xlsxReport", click:
								function(event){
						 		if(!jQuery("#tiposDocumento_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../tiposdocumento/pdfReport", click:
								function(event){
						 		if(!jQuery("#tiposDocumento_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]
				}
			]
		},
        formEdit:{
        	detailForm: "#tiposDocumento_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
         		showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false,
         		rules:{
    				"id":{
						required: true,
						integer: true,
						maxlength: 2,
						min: 0
    				},
					"orden":{
						integer: true,
						maxlength: 3,
					},
    				"tipoRelevancia.idTipoRelevancia":{
						required: true,
						comboBaja: true
    				},
					"descEu":{
						required: true,
						maxlength: 50
    				},
    				"descEs":{
						required: true,
						maxlength: 50
    				},
    				"estado":{
						required: true
    				}, 
    				"indActMemoria":{
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
				},
				beforeShowForm : function(){
					$('#advertencia_detail_table_container').show();
				}
			 },
			 addOptions: {
				 mtype: "POST",
				 ajaxEditOptions:{
					 contentType: 'application/json'
				},
				beforeShowForm : function(){
					
					$('#advertencia_detail_table_container').hide();
					$('#idTipoRelevancia_detail_table').rup_combo("destroy");
					$("#idTipoRelevancia_detail_table").remove();
					$("#idTipoRelevancia_detail_table_label").remove();
					$("#idTipoRelevancia_documentos_combo").remove();
					
					var documentoTr = $('<div id="idTipoRelevancia_documentos_combo"><select name="tipoRelevancia.idTipoRelevancia" id="idTipoRelevancia_detail_table" class="form-control"></select></div>');
					documentoTr.appendTo("#selectTipoRelevancia");
					
					creaComboRelevanciaEstadoAlta('idTipoRelevancia_detail_table');
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
	jQuery('#estado_filter_table').rup_combo(estadoOption);
	jQuery('#estado_detail_table').rup_combo(estadoOption);
	
	creaComboRelevanciaEstadoAlta('idTipoRelevancia_detail_table');
	
	jQuery("#tiposDocumento").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		jQuery('#indFacturable_detail_table').bootstrapSwitch('setState', jQuery('#indFacturable_detail_table').is(':checked'));
		jQuery('#indConfidencial_detail_table').bootstrapSwitch('setState', jQuery('#indConfidencial_detail_table').is(':checked'));
		jQuery('#indRecargoUrgencia_detail_table').bootstrapSwitch('setState', jQuery('#indRecargoUrgencia_detail_table').is(':checked'));
		jQuery('#indActMemoria_detail_table').bootstrapSwitch('setState', jQuery('#indActMemoria_detail_table').is(':checked'));
	});
	
	$("#tiposDocumento").on("rupTable_afterFormFillDataServerSide", function(event, savedData, newData){ 
		jQuery('#indFacturable_detail_table').bootstrapSwitch('setState', jQuery('#indFacturable_detail_table').is(':checked'));
		jQuery('#indConfidencial_detail_table').bootstrapSwitch('setState', jQuery('#indConfidencial_detail_table').is(':checked'));
		jQuery('#indRecargoUrgencia_detail_table').bootstrapSwitch('setState', jQuery('#indRecargoUrgencia_detail_table').is(':checked'));
		
		creaComboRelevanciaById($("#tiposDocumento").rup_table("getCol", $("#tiposDocumento").rup_table("getSelectedRows"),"tipoRelevancia.idTipoRelevancia"));
	});
	
	function creaComboRelevanciaById(idTipoRelevancia){
		$('#idTipoRelevancia_detail_table').rup_combo("destroy");
		$("#idTipoRelevancia_detail_table").remove();
		$("#idTipoRelevancia_detail_table_label").remove();
		$("#idTipoRelevancia_documentos_combo").remove();
		
		var documentoTr = $('<div id="idTipoRelevancia_documentos_combo"><select name="tipoRelevancia.idTipoRelevancia" id="idTipoRelevancia_detail_table" class="form-control"></select></div>');
		documentoTr.appendTo("#selectTipoRelevancia");
		
		$('#idTipoRelevancia_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/documentos/" + idTipoRelevancia,
			sourceParam :{
				label: $.rup.lang == 'es' ? "descRelevanciaEs"
						: "descRelevanciaEu", 
				value: "idTipoRelevancia",
				style:"css"
			}
			,width: "170"
			,ordered: false	
			,rowStriping: true
			,open: function() {
				$("#idTipoRelevancia_detail_table-menu").width($("#idTipoRelevancia_detail_table-button").width());
			}
			,onLoadSuccess: function(){
		    	var descripcion = $("#tiposDocumento").rup_table("getCol", $("#tiposDocumento").rup_table("getSelectedRows"),"tipoRelevanciaDesc");
				$('#idTipoRelevancia_detail_table').rup_combo("selectLabel", descripcion);
		    }
		});
	}
	
});