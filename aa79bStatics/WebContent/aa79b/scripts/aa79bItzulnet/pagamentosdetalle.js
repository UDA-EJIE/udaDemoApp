var pdteConfirmarCambio = false;

var configCombo = {
	loadFromSelect : true,
	width : "100%",
	ordered : false,
	rowStriping : true,
	open : function() {
		var id = $(this).attr("id");
		$("#" + id + "-menu").width($("#" + id + "-button").width());
	}
};

function pagamentosRealizarPago(){
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
			$("#altera_estado").rup_dialog("close");
			pdteConfirmarCambio = false;
			if (res == "true") {
				
				volverACapaPagamentos();
				$('#pagamentos_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.estadoAlbaranModificado, "ok");
				$("#pagamentos").rup_table("filter");
				
			}else{
				$('#pagamentosdetalle_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				$("#estado_filter_table").rup_combo("setRupValue", idEstado);
			}
		}
	});
}

function alteraEstado(obj) {
	pdteConfirmarCambio = true;
	if (obj.value != idEstado && obj.value == estadoAlbaranPagado) {
		var properties = {
			type : $.rup.dialog.TEXT,
			autoOpen : true,
			modal : true,
			resizable : true,
			width : "600px",
			title : $.rup.i18nParse($.rup.i18n.app, "label.estadoAlbaran"),
			close: function(event, ui) { 
				if (pdteConfirmarCambio){
					$("#estado_filter_table").rup_combo("setRupValue", idEstado);
				}
				$("#altera_estado").rup_dialog("destroy");
				$("#altera_estado").html('');
				pdteConfirmarCambio = false;
			},
			message : 
					"<div id ='dialogoEstado_feedback'></div>"
					+ $.rup.i18nParse($.rup.i18n.app, "label.modEstadoAlbaran")
					+ "<strong>"+obj.options[obj.selectedIndex].text
					+ "</strong>. <br><br>"
					+ $.rup.i18nParse($.rup.i18n.app, "label.rellenarDatos")+" <br> <br> "
					+ " <div class='padding10'> <div class='col-xs-6'>"
					+ "<div class='form-group'> "
					+ "<label class='control-label' data-i18n='label.reffact'> "
					+ txtReferencia
					+ "* <div> <input type='text' id='reffactura' name='reffactura' style='width: 180px' maxlength='20'> </div>"
					+ "</div> </div>"
					+ " <div class='col-xs-6'>"
					+ "<div class='form-group'> <label class='control-label col-xs-12' data-i18n='label.imppagado'> "
					+ txtImpPag
					+ "*<div><input class ='form-control decimal' type='text' id='imppag' name='imppag' style='width: 100px' maxlength='9'  data-decim='2'> ("+ $.rup.i18nParse($.rup.i18n.app, "label.importePrevisto")
					+ ": "+$("#importeTotal").val() + ")</div> </div> </div>"+$.rup.i18nParse($.rup.i18n.app, "label.deseaContinuar")+" </div>",
			buttons : [{
				text : "Aceptar",
				click : function() {
					if ( isEmpty($("#reffactura").val()) ||  isEmpty($("#imppag").val())){
						$('#dialogoEstado_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorValidacionestadoAlbaranPagado, "error");
					}else if($('#imppag').val() != $("#importeTotal").val().replace("€", "", "gi").trim()){
						$.rup_messages("msgConfirm", {
	         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         				message: $.rup.i18n.app.mensajes.pagoProveedoresImportesDiferentesMessage,
	         				OKFunction: function(){
	         					pagamentosRealizarPago();
	         				}
	         			});
					}else{
						pagamentosRealizarPago();
					}
				}
			},{
				text : "Cancelar",
				click : function() {
					
					$("#altera_estado").rup_dialog("close");
				},
				btnType : $.rup.dialog.LINK
			} ]
		}
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
			close: function(event, ui) { 
				if (pdteConfirmarCambio){
					$("#estado_filter_table").rup_combo("setRupValue", idEstado);
				}
				$("#altera_estado").rup_dialog("destroy");
				$("#altera_estado").html('');
				pdteConfirmarCambio = false;
			},
			message : $.rup.i18nParse($.rup.i18n.app, "label.modEstadoAlbaran")
					+ "<strong>"+obj.options[obj.selectedIndex].text
					+ "</strong>. <br>" +$.rup.i18nParse($.rup.i18n.app, "label.deseaContinuar"),
			buttons : [{
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
							pdteConfirmarCambio = false;
							$("#altera_estado").rup_dialog("close");
							if (res == "true") {
								idEstado = obj.value;
								$('#pagamentosdetalle_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.estadoAlbaranModificado, "ok");
							}else{
								$('#pagamentosdetalle_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
								$("#estado_filter_table").rup_combo("setRupValue", idEstado);
							}
						}
					});
				}

			},{
				text : "Cancelar",
				click : function() {
					$("#altera_estado").rup_dialog("close");
				},
				btnType : $.rup.dialog.LINK
			}]
		}
		$("#altera_estado").rup_dialog(properties);
		$('#dialogoEstado_feedback').rup_feedback({
			block : false,
			delay:3000
		});
	}
}


jQuery(function($) {
	
	if (idAlbaranTxt == "0" ){
		$('#capaAlbaranyLote').hide();
	}
	$("#pagamentosdetalle_toolbar").rup_toolbar({
		buttons : [ {
			id : "volver",
			i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.volver"),
			css : "fa fa-arrow-left",
			click : function(event) {
				event.preventDefault();
				event.stopImmediatePropagation();
				volverACapaPagamentos();
			}
		}]
	});

	$("#tareasdetalle_toolbar").rup_toolbar({
		buttons : [ {
			id : "volver",
			i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.volver"),
			css : "fa fa-arrow-left",
			click : function(event) {
				event.preventDefault();
				event.stopImmediatePropagation();
				volverACapaPagamentos();
			}
		}]
	});

	$('#pagamentosdetalle_feedback').rup_feedback({
		block : false,
		delay:3000
	});

	$('#tareasdetalle_feedback').rup_feedback({
		block : false,
		delay:3000
	});

	$("#tableTareas").rup_table({
		url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/tareasdetalle",
		toolbar : {
			id : "pagamentosdetalle_toolbar",
			defaultButtons : {
				add : false,
				edit : false,
				cancel : false,
				save : false,
				clone : false,
				"delete" : false,
				filter : false
			}
		},
		colNames : [ txtNumExp, txtTarea, txtNumPalavras, txtPrcIva,
				txtImpPalavra, txtImpTarea,
				txtImpRecargoFormato, txtImpRecargoPremio,
				txtImpPenalRetraso, txtImpPenalCalidad, txtTotal, txtImpBase,
				txtImpIva

		],
		colModel : [
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
					sortable : true
				},
				{
					name : "idTarea",
					label : "label.tarea",
					align : "",
					width : 70,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					formatter : function(cellvalue,
							options, rowObject) {
						if (rowObject.idTipTarea == _IDTRADUZIR) {
							return _TIP_TAREA_TRADUZIR
									+ "-"
									+ rowObject.idTarea;
						} else {
							return _TIP_TAREA_REVIZAR + "-"
									+ rowObject.idTarea;
						}
					}
				},
				{
					name : "numPalConTramos",
					label : "label.numpalavras",
					index : "numTotalPal",
					align : "right",
					width : 180,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},

				{
					name : "ivaAplic",
					label : "label.percIVA",
					align : "right",
					width : 50,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "importePalAplic",
					label : "label.imppalabra",
					align : "right",
					width : 110,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "importeTarea",
					label : "label.impTarea",
					align : "right",
					width : 100,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "descImporteRecargoFormato",
					label : "label.impRecargoFormato",
					index: "IMPORTERECARGOFORMATO",
					align : "right",
					width : 160,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true

				},
				{
					name : "descImporteRecargoApremio",
					label : "label.impRecargoPremio",
					index: "IMPORTERECARGOAPREMIO",
					align : "right",
					width : 150,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "descImportePenalizacion",
					label : "label.impPenalRetraso",
					index: "IMPORTEPENALIZACION",
					align : "right",
					width : 100,
					classes: 'rojoImportant',
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "descImportePenalizacionCalidad",
					label : "label.impPenalCalidad",
					index: "IMPPENALCALIDAD",
					align : "right",
					width : 100,
					classes: 'rojoImportant',
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "importeTotal",
					label : "label.total",
					align : "right",
					width : 100,
					classes: 'negrita',
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "importeBase",
					label : "label.impBase",
					align : "right",
					width : 100,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "importeIva",
					label : "label.impIva",
					align : "right",
					width : 100,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				} ],

		model : "DatosPagoProveedores",
		usePlugins : [ "toolbar","filter", "report","fluid"],
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
					url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/excelReportTareas",
					click : function(event) {
						if (!$("#tableTareas_filter_form").valid()) {
							event.preventDefault();
							event.stopImmediatePropagation();
						}
					}
				} ]
			} ]
		},
	    shrinkToFit: false,
		primaryKey : "idTarea",
		loadOnStartUp : true,
		loadComplete: function(){
			$("[id='pagamentosdetalle_toolbar##reports-mbutton-container']").addClass("oculto");
			
			$("[id='pagamentosdetalle_toolbar##reports']").unbind("click");
			$("[id='pagamentosdetalle_toolbar##reports'] > i").addClass("fa fa-file-excel-o")
			$("[id='pagamentosdetalle_toolbar##reports']").on("click", function(){
				$("[id='pagamentosdetalle_toolbar##reports-mbutton-group##EXCEL']").click();
			});
			
			
		}
	});
      
	if ( $('#estado_filter_table-menu').length ){
		$('#estado_filter_table-menu').remove();
	}
    $('#estado_filter_table').rup_combo({
    	loadFromSelect : true,
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
});