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

jQuery(function($) {
	
	if (idAlbaranTxt == "0" ){
		$('#capaAlbaranyLote').hide();
	}
	
	$("#albaranesdetalle_toolbar").rup_toolbar({
		buttons : [ {
			id : "volver",
			i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.volver"),
			css : "fa fa-arrow-left",
			click : function(event) {
				event.preventDefault();
				event.stopImmediatePropagation();
				volverACapaListadoAlbaranes();
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
				volverACapaListadoAlbaranes();
			}
		} ]
	});
	
	$('#albaranesdetalle_feedback').rup_feedback({
		block : false,
		delay:3000
	});

	$('#tareasdetalle_feedback').rup_feedback({
		block : false,
		delay:3000
	});

	$("#tableTareas").rup_table({
		url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/albaranes/tareasdetalle",
		toolbar : {
			id : "albaranesdetalle_toolbar",
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
				txtImpIva,
				txtFacturable,
				txtIndAlbaran

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
					align : "left",
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
					isNumeric: true,
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
					isNumeric: true,
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
					isNumeric: true,
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
					width : 140,
					isNumeric: true,
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
					width : 140,
					isNumeric: true,
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
					isNumeric: true,
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
					isNumeric: true,
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
					isNumeric: true,
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
					isNumeric: true,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : $.rup.lang == 'es'? "facturableDescEs": "facturableDescEu",
					label : "label.facturableTitle",
					align : "center",
					width : 100,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "descIndAlbaran",
					label : "label.facturableTitle",
					align : "center",
					width : 100,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					index:'INDALBARANDESCEU'
				} ],

		model : "DatosPagoProveedores",
		usePlugins : [ "toolbar","filter", "report", "fluid"],
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
			$("[id='albaranesdetalle_toolbar##reports-mbutton-container']").addClass("oculto");
			$("[id='albaranesdetalle_toolbar##reports']").unbind("click");
			$("[id='albaranesdetalle_toolbar##reports'] > i").addClass("fa fa-file-excel-o")
			$("[id='albaranesdetalle_toolbar##reports']").on("click", function(){
				$("[id='albaranesdetalle_toolbar##reports-mbutton-group##EXCEL']").click();
			});
		}
	});
      
	//jQuery('.ui-jqgrid-hbox').css({width:'100%'});
});