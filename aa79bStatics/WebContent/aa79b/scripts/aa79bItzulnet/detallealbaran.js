
var selectedIdTarea;

function volverACapaListadoAlbaranes(){
	$("#divDetalleAlbaran").detach();
	$("#divActualizarAlbaranGeneral").html(capaPestGeneral);
	$("#actualizarAlbaran").rup_table("filter");
}

function destruyeDialogo(){
	$("#altera_estado").rup_dialog("close");
	$("#altera_estado").rup_dialog("destroy");
	$("#altera_estado").html('');
}

function alteraEstado(obj) {

	if (obj.value != idEstado && obj.value == estadoAlbaranPagado) {
		var properties = {
			type : $.rup.dialog.TEXT,
			autoOpen : true,
			modal : true,
			resizable : true,
			width : "600px",
			title : $.rup.i18nParse($.rup.i18n.app, "label.estadoAlbaran"),
			message : 
					"<div id ='dialogoEstado_feedback'></div>"
					+ $.rup.i18nParse($.rup.i18n.app, "label.modEstadoAlbaran")
					+ "<strong>"+obj.options[obj.selectedIndex].text
					+ "</strong>. <br><br>"
					+ $.rup.i18nParse($.rup.i18n.app, "label.rellenarDatos")+" <br> <br> "
					+ " <div class='padding10'> <div class='col-xs-6'>"
					+ "<div class='form-group form-group-sm'> "
					+ "<label class='control-label' data-i18n='label.reffact'> "
					+ txtReferencia
					+ "* <div> <input type='text' id='reffactura' name='reffactura' style='width: 150px' maxlength='20'> </div>"
					+ "</div> </div>"
					+ " <div class='col-xs-6'>"
					+ "<div class='form-group form-group-sm'> <label class='control-label col-xs-12' data-i18n='label.imppagado'> "
					+ txtImpPag
					+ "*<div><input class ='form-control decimal' type='text' id='imppag' name='imppag' style='width: 100px' maxlength='9'  data-decim='2'> ("+ $.rup.i18nParse($.rup.i18n.app, "label.importePrevisto")
					+ ": "+importePrevistoAlb + " &euro;)</div> </div> </div>"+$.rup.i18nParse($.rup.i18n.app, "label.deseaContinuar")+" </div>",
			buttons : [{
				text : "Cancelar",
				click : function() {
					$("#estado_filter_table").rup_combo("setRupValue", idEstado);
					destruyeDialogo();
					
				},
				btnType : $.rup.dialog.LINK
			}, {
				text : "Aceptar",
				click : function() {
					if ( isEmpty($("#reffactura").val()) ||  isEmpty($("#imppag").val())){
						$('#dialogoEstado_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorValidacionestadoAlbaranPagado, "error");
					}else{
					
						var referencia = $("#reffactura").val();
						var imppago = removeDecimalsFormat($("#imppag").val());
						var data = {
							"idalb" : parseInt(idAlbaranTxt),
							"referencia" : referencia,
							"imppago" : imppago,
							"idLote": parseInt(idLoteTxt)
						};
						$.ajax({
							type : "POST",
							url : '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/updateinfo',
							data : data,
							success : function(res) {
								destruyeDialogo();
								if (res == "true") {
									volverACapaListadoAlbaranes();
									$('#actualizarAlbaran_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.estadoAlbaranModificado, "ok");
									$("#actualizarAlbaran").trigger('reloadGrid');
									
								}else{
									$('#actualizarAlbaran_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
									$("#estado_filter_table").rup_combo("setRupValue", idEstado);
								}
							}
						});
					}
				}

			} ]
		}
		popup = true;
		$("#altera_estado").rup_dialog(properties);
		$('#dialogoEstado_feedback').rup_feedback({
			block : false,
			delay:3000
		});
		
	} else if (obj.value != idEstado ) {
		var properties = {
			type : $.rup.dialog.TEXT,
			autoOpen : true,
			modal : true,
			resizable : true,
			title : $.rup.i18nParse($.rup.i18n.app, "label.estadoAlbaran"),
			message : $.rup.i18nParse($.rup.i18n.app, "label.modEstadoAlbaran")
					+ "<strong>"+obj.options[obj.selectedIndex].text
					+ "</strong>. <br>" +$.rup.i18nParse($.rup.i18n.app, "label.deseaContinuar"),
			buttons : [{
				text : "Cancelar",
				click : function() {
					$("#estado_filter_table").rup_combo("setRupValue", idEstado);
					destruyeDialogo();
				},
				btnType : $.rup.dialog.LINK
			}, {
				text : "Aceptar",
				click : function() {
					data = {
						idalb : idAlbaranTxt,
						idestado : obj.value
					};
					$.ajax({
						type : "POST",
						url : '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/updateestado',
						data : data,
						success : function(res) {
							destruyeDialogo();
							if (res == "true") {
								volverACapaListadoAlbaranes();
								$('#actualizarAlbaran_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.estadoAlbaranModificado, "ok");
								$("#actualizarAlbaran").trigger('reloadGrid');
							}else{
								$('#actualizarAlbaran_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
								$("#estado_filter_table").rup_combo("setRupValue", idEstado);
							}
						}
					});
				}

			} ]
		}
		popup = true;
		$("#altera_estado").rup_dialog(properties);
	}
}

function desasociarTareas(){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/servicios/actualizacionalbaran/detalleAlbaran/desasociarTareas/'+selectedIdTarea,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	
			$("#tareasAlbaran").rup_table("filter");
			$('#detalleAlbaran_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.desasociarTareasCorrecto, "ok");
		}
	});
}

jQuery(function($){
	
	$('#detalleAlbaran_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$('#tareasAlbaran_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#detalleAlbaran_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id:"volver"
				,css: "fa fa-arrow-left"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			volverACapaListadoAlbaranes();
				}
			}			
		]
	});
	
	$("#tareasAlbaran_toolbar").rup_toolbar({
		buttons:[
			{i18nCaption: $.rup.i18n.app.boton.desasociarTareas
				,id:"desasociarTareas"
				,css: "fa fa-times"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			selectedIdTarea = "";
					var multiSelectedList = $("#tareasAlbaran").rup_table("getSelectedIds");
         			if(null != multiSelectedList.selectedIds && multiSelectedList.selectedIds.length > 0){
	                     for(var i = 0; i < multiSelectedList.selectedIds.length; i++){
	                    	 selectedIdTarea += multiSelectedList.selectedIds[i] + ",";
	                     }
         			}
		 			
		 			if(!$('#tareasAlbaran').rup_table("isSelected")){
						$.rup_messages("msgAlert", {
							title: $.rup.i18nParse($.rup.i18n.app,"boton.desasociarTareas"),
							message: $.rup.i18n.app.comun.warningSeleccion
						});	
						return false;
		 			} else {
		 				$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.app,"boton.desasociarTareas"),
							message: $.rup.i18nParse($.rup.i18n.app,"mensajes.desasociarTareas"),
	         				OKFunction: function(){
	         					desasociarTareas();
	         				}
						});	
		 			}
				}
			}		
			]
	});
	
//	findAlbaran();
	
	$("#tareasAlbaran").rup_table({
		url: "/aa79bItzulnetWar/servicios/actualizacionalbaran/detalleAlbaran/findTareasAlbaran",
		toolbar:{
			id: "detalleAlbaran_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
		},
		colNames: [
			txtNumExp,
			txtTarea,
			$.rup.i18n.app.label.numTotalPal,
			$.rup.i18n.app.label.porIva,
			$.rup.i18n.app.label.impPalabra + ' (€)',
			$.rup.i18n.app.label.impTarea + ' (€)',
			$.rup.i18n.app.label.impRecargoFormato,
			$.rup.i18n.app.label.impRecargoApremio,
			$.rup.i18n.app.label.impPenalRetraso,
			$.rup.i18n.app.label.impPenalCalidad,
			$.rup.i18n.app.label.totalMayus,
			$.rup.i18n.app.label.impBase,
			$.rup.i18n.app.label.impIva
		],
		colModel: [
			{
				name : "anyoNumExpConcatenado",
				label : "label.numExp",
				index : "ANYONUMEXPCONCATENADO",
				align : "center",
				width : 80,
				editable : true,
				fixed : false,
				hidden : false,
				resizable : true,
				sortable : false
			},
			{ 	name: "idTarea", 
			 	label: "label.idTarea",
			 	index: "IDTAREA",
			 	align: "left", 
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					var tipoTareaDesc;
					if($.rup.lang === 'es'){
						tipoTareaDesc = rowObject.tiposTarea.descEs015;
					} else {
						tipoTareaDesc = rowObject.tiposTarea.descEu015;
					}
					return tipoTareaDesc + '-' + rowObject.idTarea;
				}
			},
			{ 	name: "numPalConTramos", 
			 	label: "label.numpalavras",
			 	index: "NUMTOTALPAL",
			 	align: "right", 
			 	width: "180", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "ivaAplic",
			 	label: "label.percIVA",
			 	index: "IVAAPLIC",
			 	align: "right", 
				width: "50", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "importePalAplic", 
				label: "label.imppalabra",
				index: "IMPORTEPALAPLIC",
				align: "right", 
				width: "100", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "importeTarea", 
			 	label: "label.impTarea",
			 	index: "IMPORTETAREA",
			 	align: "right", 
			 	width: "100",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "importeRecargoFormato", 
				label: "label.impRecargoFormato",
				index: "IMPORTERECARGOFORMATO",
				align: "right", 
				width: "150",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue + '€ (' + rowObject.numPalRecargoFormato + ' ' + $.rup.i18nParse($.rup.i18n.app,"label.palabras") + ')';
				}
			},
			{ 	name: "importeRecargoApremio", 
				label: "label.impRecargoPremio",
				index: "IMPORTERECARGOAPREMIO",
				align: "right", 
				width: "150",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue + '€ (' + rowObject.porRecargoApremio + '%' + ')';
				}
			},
			{ 	name: "importePenalizacion", 
				label: "label.impPenalRetraso",
				index: "IMPORTEPENALIZACION",
				align: "right", 
				width: "100",
				classes: 'rojoImportant',
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue + '€ (' + rowObject.porPenalizacion + '%' + ')';
				}
			},		
			{
				name : "importePenalCalidad",
				label : "label.impPenalCalidad",
				index: "IMPPENALCALIDAD",
				align : "right",
				width : "100",
				classes: 'rojoImportant',
				editable : false,
				fixed : false,
				hidden : false,
				resizable : true,
				sortable : false,
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue + '€ (' + rowObject.porPenalizacionCalidad + '%' + ')';
				}
			},
			{ 	name: "importeTotal", 
				label: "label.totalMayus",
				index: "IMPORTETOTAL",
				align: "right", 
				classes: 'negrita',
				width: "100",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},				
			{ 	name: "importeBase", 
				label: "label.impBase",
				index: "IMPORTEBASE",
				align: "right", 
				width: "110",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},				
			{ 	name: "importeIva", 
				label: "label.impIva",
				index: "IMPORTEIVA",
				align: "right", 
				width: "100",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}				
        ],
        model:"DatosPagoProveedores",
        usePlugins:idEstado === "4"?[
        	"feedback",
        	"toolbar",
        	"filter",
        	"report"
         	]:[
        	"feedback",
        	"toolbar",
        	"filter",
        	"multiselection",
        	"report"
         	],
     	report : {
			buttons : [ {
				id : "reports",
				i18nCaption : $.rup.i18nParse($.rup.i18n.app, "comun.tareasfacturables"),
				css : "fa fa-file-excel-o",
				right : true,
				buttons : [ {
					i18nCaption : $.rup.i18nParse(
							$.rup.i18n.app, "tabla.excel"),
					divId : "reports",
					css : "fa fa-file-excel-o",
					url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/albaranes/excelReportTareas",
					click : function(event) {
						if (!$("#tareasAlbaran_filter_form").valid()) {
							event.preventDefault();
							event.stopImmediatePropagation();
						}
					}
				} ]
			} ]
		},
        loadComplete: function(){ 
        	$("#tareasAlbaran_filter_div").hide();
        	$("#tareasAlbaran_pager").hide();
        	$("[id='detalleAlbaran_toolbar##reports-mbutton-container']").addClass("oculto");
			$("[id='detalleAlbaran_toolbar##reports']").unbind("click");
			$("[id='detalleAlbaran_toolbar##reports']").on("click", function(){
				$("[id='detalleAlbaran_toolbar##reports-mbutton-group##EXCEL']").click();
			});
        },
        multiSort: true,
     	sortname : "IDTAREA",
		sortorder : "asc",
        primaryKey: ["idTarea"],
		loadOnStartUp: true,
		multiselect: idEstado === "4"?false:true,
		title: false,
		filter:{
			beforeFilter: function(){
				$("#tareasAlbaran").rup_table("resetSelection");
			}
		}
	});
	
	if ( $('#estado_filter_table-menu').length ){
		$('#estado_filter_table-menu').remove();
	}
	var comboSource = [];
	if(idEstado == estadoAlbaranPendienteEnviarIzo){
		comboSource =[
			{
				label: txtEstadoAlbaranPendienteEnviarIzo,
				value: estadoAlbaranPendienteEnviarIzo
			},
			{
				label: txtEstadoAlbaranEnviadoIzo,
				value: estadoAlbaranEnviadoIzo
			}
		];
	}else if(idEstado == estadoAlbaranEnviadoIzo){
		comboSource =[
			{
				label: txtEstadoAlbaranPendienteEnviarIzo,
				value: estadoAlbaranPendienteEnviarIzo
			},
			{
				label: txtEstadoAlbaranEnviadoIzo,
				value: estadoAlbaranEnviadoIzo
			},
			{
				label: txtEstadoAlbaranPagado,
				value: estadoAlbaranPagado
			}
		];
	}else{
		comboSource =[
			{
				label: txtEstadoAlbaranPagado,
				value: estadoAlbaranPagado
			}
		];
	}
	
    $('#estado_filter_table').rup_combo({
    	source : comboSource,
		width : "100%",
		ordered : false,
		rowStriping : true,
  		change: function(){	
  			alteraEstado(this);
  		}
  		,open: function(){
  			jQuery('#estado_filter_table-menu').width(jQuery('#estado_filter_table-button').innerWidth());
  		}
  	});
    
    $("#estado_filter_table").rup_combo("setRupValue", idEstado);
    if(idEstado === "4"){
    	 $("#estado_filter_table").rup_combo("disable");
    	 $('#tareasAlbaran_toolbar').hide();
    }
	jQuery('.ui-jqgrid-hbox').css({width:'100%'});
	
});