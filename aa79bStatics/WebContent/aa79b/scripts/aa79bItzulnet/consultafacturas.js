
var tipoEntidadAsoc = '-1';
var idEntidadAsoc = '-1';

function descargarFactura(refLiquidacion){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumentoFactura/'+refLiquidacion);
}

function sinRefLiquidacion(){
	$.rup_messages("msgAlert", {
 		title: $.rup.i18nParse($.rup.i18n.app,"label.consultafacturas"),
		message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinRefLiquidacion"),
	});	
}

function enlaceDetalleFactura(refLiquidacion){
	window.open(urlFacturacion.replace('{referencia}',refLiquidacion).replace('{idioma}',$.rup.lang),
	  '_blank' 
	);
}

function cargarAutocompleteEntidades(){
	$('#nombreEntidad_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/suggestEntidadesFacturas",
		sourceParam : {
			value: "codigoCompleto",
			label : "desc" + $.rup_utils.capitalizedLang()
		},
		getText: false,
		open : function(event, object, arg) {
			 var tam = parseFloat($('#nombreEntidad_filter_table').css("padding-left"))+ parseFloat($('#nombreEntidad_filter_table').css("padding-right"));
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", $('#nombreEntidad_filter_table').innerWidth());
		},
		change: function(){
			if ($('#nombreEntidad_filter_table').val() != ''){
				var comp = $('#nombreEntidad_filter_table').val().split("_");
				
				cargarAutocompleteContacto(comp[0] , comp[1]);
			}else{
				cargarAutocompleteContacto('-1','-1');
			}
		}
	});
}

function cargarAutocompleteContacto(tipoEntidadAsoc, idEntidadAsoc){
	if ( $('#nombreApellidosContacto_filter_table_label').length   ){
		 //destruimos y volvemos a crear el autocomplete
      	$('#nombreApellidosContacto_filter_table').rup_autocomplete("destroy");
      	$("#nombreApellidosContacto_filter_table").remove();
      	$('#nombreApellidosContacto_filter_table_label').remove();
      	var autocompleteContacto = $('<input type="text" id="nombreApellidosContacto_filter_table" class="form-control" name="persona.dniContacto" maxlength="120" />');
      	autocompleteContacto.appendTo("#autocompleteContactoContainer");
	}

	$('#nombreApellidosContacto_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/suggestContactoFacturas/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
		sourceParam : {
			value: "dni",
			label : "nombreCompleto" 
		},
		getText: false,
		width: "100%",
		open : function() {
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("max-width", $('#nombreApellidosContacto_filter_table').width());
		}
	});	
}

jQuery(function($) {
	cargarAutocompleteEntidades();
	cargarAutocompleteContacto('-1','-1');
	
	fnFechaDesdeHasta("fechaEmisionDesde_filter", "fechaEmisionHasta_filter");
	
	$('#estadoFactura_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/estadosFactura/false",
		sourceParam : {
			value: "indEstadoFactura",
			label : $.rup.lang === 'es' ? "estadoFacturaDescEs"
					: "estadoFacturaDescEu"
		},
		blank:"",
		ordered : false,
		rowStriping : true,
		open : function() {
			jQuery('#estadoFactura_filter_table-menu').width(jQuery('#estadoFactura_filter_table-button').innerWidth());
		}
	});
	
	$('#estadoEFactura_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/estadosFactura/true",
		sourceParam : {
			value: "indEstadoFactura",
			label : $.rup.lang === 'es' ? "estadoFacturaDescEs"
					: "estadoFacturaDescEu"
		},
		blank:"",
		ordered : false,
		rowStriping : true,
		open : function() {
			jQuery('#estadoEFactura_filter_table-menu').width(jQuery('#estadoEFactura_filter_table-button').innerWidth());
		}
	});
	
	$('#busquedaFact_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#busquedaFact_filter_form").rup_validate({
		feedback: $('#busquedaFact_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"fechaEmisionIni": {date: true},
			"fechaEmisionFin": {date: true, fechaHastaMayor: "fechaEmisionIni"}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	$("#busquedaFact").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas",
		toolbar: {
			id: "busquedaFact_toolbar"
				,newButtons : [      
				       
						{obj : {
							i18nCaption: $.rup.i18n.app.comun.eliminar
							,css: "fa fa-trash-o"
							,index: 0
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(e){
			                    e.preventDefault();
			                    e.stopImmediatePropagation();
			                    confirmEliminarFactura();
							}					 
						}
					]
			,defaultButtons:{
				add : false
				,edit : false
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : false
			}
   	 	}, 
	   	 colNames: [
	   		"",
	   		$.rup.i18nParse($.rup.i18n.app, "label.numFactura"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.refLiquidacion"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.entidadContactoAFacturar"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.centroOrganicoAFacturar"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.importe"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.fecEmision"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.estado"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.estadoInci"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.codError"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.estadoEFactura"),
	   		$.rup.i18nParse($.rup.i18n.app, "label.fecPago")
	   	 ],
	   	 colModel:[
	   		{ 	name: "idFactura", 
				hidden: true
			},
	   		{ 	name: "codConcatenado", 
			 	label: "comun.numFactura",
			 	index: "CODCONCATENADO",
				align: "center", 
				hidden: false,
				sortable: true,
				width: "100",
				isNumeric: true,
				formatter: function(cellvalue,options, rowObject){
					if(rowObject.idLiquidacion != null && rowObject.codConcatenado != 9999999999){
						return '<b><a href="#" onclick="descargarFactura('+rowObject.idLiquidacion+')">' + cellvalue + '</a></b>';
					} else {
						return '<b><a href="#" onclick="sinRefLiquidacion()">' + cellvalue + '</a></b>';
					}
				}
			},
   		 	{ 	name: "idLiquidacion", 
			 	label: "comun.refLiquidacion",
			 	index: "IDLIQUIDACION",
				align: "center", 
				hidden: false,
				sortable: true,
				width: "105",
				isNumeric: true,
				formatter: function(cellvalue,options, rowObject){
					if(cellvalue != null){
						return '<b><a href="#" onclick="enlaceDetalleFactura('+cellvalue+')">' + rowObject.idLiquidacion + '</a></b>';
					} else {
						return '';
					}				
				}
			},
			{ 	name: $.rup.lang === 'es' ? "persona.contactoDescEs" : "persona.contactoDescEu", 
				label: "label.entidadContactoFacturar",
				index: $.rup.lang === 'es' ? "DESCAMPES" : "DESCAMPEU",
				align: "left",
				width: "300"
			},
			{ 	name: $.rup.lang === 'es' ? "persona.centroOrganicoDescEs" : "persona.centroOrganicoDescEu", 
				label: "label.centroOrganicoFacturar",
				index: $.rup.lang === 'es' ? "CENTROORGANICODESCES" : "CENTROORGANICODESCEU",
				align: "left",
				width: "300"
			},
			{ 	name: "datosFacturacionInterpretacion.importeTotal", 
				label: "label.importe",
				index: "IMPORTETOTAL",
				align: "right",
				width: "100",
				isNumeric: true
			},
			{ 	name: "fechaEmision", 
				label: "label.fecEmision",
				index: "FECHAEMISION",
				align: "center",
				width: "140",
				isDate: true
			},
			{ 	name: $.rup.lang === 'es' ? "datosFacturacionInterpretacion.estadoFactura.estadoFacturaDescEs"
					: "datosFacturacionInterpretacion.estadoFactura.estadoFacturaDescEu", 
				label: "label.estado",
				index: $.rup.lang === 'es' ? "ESTADOFACTURADESCES":"ESTADOFACTURADESCEU",
				align: "center",
				width: "140",
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "datosFacturacionInterpretacion.estadoIncidenciaFactura.estadoIncidenciaDescEs"
					: "datosFacturacionInterpretacion.estadoIncidenciaFactura.estadoIncidenciaDescEu", 
					label: "label.estadoIncidencia",
					index: $.rup.lang === 'es' ? "ESTADOINCIDENCIADESCES":"ESTADOINCIDENCIADESCEU",
					align: "center",  
					hidden: false,
					sortable: true,
					width: "150",
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
			},
			{ 	name: $.rup.lang === 'es' ? "datosFacturacionInterpretacion.estadoErrorFactura.estadoErrorDescEs"
					: "datosFacturacionInterpretacion.estadoErrorFactura.estadoErrorDescEu", 
					label: "label.codError",
					index: $.rup.lang === 'es' ? "ESTADOERRORDESCES":"ESTADOERRORDESCEU",
					align: "center",  
					hidden: false,
					sortable: true,
					width: "150",
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}					
			},
			{ 	name: $.rup.lang === 'es' ? "datosFacturacionInterpretacion.estadoFacturaE.estadoEFacturaDescEs"
					: "datosFacturacionInterpretacion.estadoFacturaE.estadoEFacturaDescEu", 
					label: "label.estadoEFactura",
					index: $.rup.lang === 'es' ? "ESTADOEFACTURADESCES":"ESTADOEFACTURADESCEU",
					align: "center",  
					hidden: false,
					sortable: true,
					width: "150",
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}			
			},
			{ 	name:"datosFacturacionInterpretacion.fechaPago",
					label: "label.fecPago",
					index: "FECHAPAGO",
					align: "center",  
					hidden: false,
					sortable: true,
					width: "150"					
			}
	   	 ],
	   	 model: "Facturas",
	   	 usePlugins:[
        	"feedback",
			"toolbar",
        	"fluid",
        	"filter",
        	"report",
	   	 ],
	   	 primaryKey:["idFactura"],
	   	 sortname: "CODCONCATENADO",
	   	 sortorder: "desc",
	   	 shrinkToFit: false,
	   	 loadOnStartUp: true,
	   	 report : {
			buttons : [{
				id : "report_consultaFacturas",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_consultaFacturas",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/xlsxReport",
						click : function(event) {
						}
					},
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_consultaFacturas", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/pdfReport", 
						click : function(event){
						}
					} 
				]}
			]
		 },
		 filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$('[id^="fecha"][id$="error"]').remove();
				if(!$("#busquedaFact_filter_form").valid()){
					return false;
				}
				$("#busquedaFact").rup_table("resetSelection");
				$("#busquedaFact").rup_table("hideFilterForm");
			}
		 }
	});
	
	$("#busquedaFact_filter_cleanLinkModificado").on("click", function(){
		eliminarMensajesValidacion();
		$("#busquedaFact_feedback").rup_feedback("close");
		$("#busquedaFact").rup_table("cleanFilterForm");
		$("#busquedaFact").rup_table("filter");
	});
	
});


function confirmEliminarFactura(){
	var selectedRows = $("#busquedaFact").rup_table('getSelectedRows');
	if (isEmpty(selectedRows)){
		$.rup_messages("msgAlert", {
			title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	 }else{
		 var referencia = $("#busquedaFact").rup_table("getCol", selectedRows, "idLiquidacion");
		 if (referencia === ""){
			 $.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
					OKFunction: function(){
						eliminarFactura(selectedRows[0]);
					}
				});
		 }else{
			 $('#busquedaFact_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.facturaNoEliminable, "error");
		 }
	 }
}

function eliminarFactura(idFactura){
	$.ajax({
        type: 'DELETE' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/'+idFactura
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,beforeSend: function() {
        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"));
        }
        ,success:function(){
        	$("#busquedaFact").trigger('reloadGrid');
        	$("#busquedaFact").rup_table("resetSelection");
        	$('#busquedaFact_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.borradoCorrecto, "ok");
        	desbloquearPantalla();
        }
		,error: function(error){
			$('#busquedaFact_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
			desbloquearPantalla();
	   	 }
	});
}
