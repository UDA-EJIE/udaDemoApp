/*
 * *************************
 * FUNCIONALIDADES PANTALLA - INICIO
 * *************************
 */


function volverAConfeccionarFactura(){
	$("#divBorradorFactura").detach();
	$("#divFacturacionCapa").html("<div id='divFacturacionGeneral'></div>");
	$("#divFacturacionGeneral").html(capaConfeccFactura);
}

/*
 * *************************
 * FUNCIONALIDADES PANTALLA - FIN
 * *************************
 */

/*
 * *************************
 * COMPONENTES - INICIO
 * *************************
 */

function fncToolbarBorradorFactura(){
		$("#borradorFacturaMain_toolbar").rup_toolbar({
			buttons:[
				{
					i18nCaption: $.rup.i18n.app.boton.volver
					,id:"borradorVolver"
					,css: "fa fa-arrow-left"
					,click: function(event){
						event.preventDefault();
			 			event.stopImmediatePropagation();
			 			volverAConfeccionarFactura();
					}
				},
				{
					i18nCaption: $.rup.i18n.app.boton.generarFactura
					,id:"generarFactura"
					,rigth:true
					,css:"fa fa-file-text-o"
					,click: function(event){
						event.preventDefault();
			 			event.stopImmediatePropagation();
			 			generarFactura();
					}
				}
			]
		});
	}

/*
 * *************************
 * COMPONENTES - FIN
 * *************************
 */

/*
 * *************************
 * CARGA INICIAL - INICIO
 * *************************
 */

function completarCampos(){
	$('#idTipoExpedienteFilter').val(tipoExpediente);
	$('#tipoEntidadFilter').val(tipoEntidadSeleccionada);
	$('#idEntidadFilter').val(idEntidadSeleccionada);
	$('#dniContactoFilter').val(dniContactoSeleccionado);
	$('#cif').text(cif);
	$('#descEuEntidad').text(entidad);
	$('#domicilio').text(domicilio);
	if (dniContactoSeleccionado){
		$('#contacto').text(contacto);
		$("#capacontacto").show();
	}else{
		$("#contacto").text('');
		$("#capacontacto").hide();
	}
	
	$('#cantidadExp').text(expSeleccionados.length);
	$('#totalOne').text(importeTotal);
	$('#importeBase').text(importeBaseTotal);
	$('#importeIva').text(importeIvaTotal);
	$('#totalTwo').text(importeTotal);
}

/*
 * *************************
 * CARGA INICIAL - FIN
 * *************************
 */

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function generarFactura(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	$('#borradorFactura_feedback').rup_feedback('close');
	
	var factura = 
    {
        "tipoEntidadAsoc": tipoEntidadSeleccionada, 
        "idEntidadAsoc": idEntidadSeleccionada,
        "dniContacto": dniContactoSeleccionado,
        "tipoExpFactura": tipoExp
    };
	
	var datosFacturacion = 
    {
        "importeBase": $('#importeBase').text(), 
        "importeIva": $('#importeIva').text(),
        "importeTotal": $('#totalTwo').text()
    };
	
	
	//var expedientesSeleccionados = $("#borradorFactura").rup_table("getDataIDs");
	//var listFacturasExp = new Array();
	var anyoNumExp;
	var anyoNumExpediente;
	var anyo;
	var numExp;
		
	/*for (var i = 0; i < expedientesSeleccionados.length; i++) {
		anyoNumExp = expedientesSeleccionados[i];
		
		anyoNumExpediente = anyoNumExp.split("~");	
		anyo = anyoNumExpediente[0];
		numExp = anyoNumExpediente[1];
		
		listFacturasExp.push( {
    		"anyo": anyo,
    		"numExp": numExp
    	} );
	}*/
	
	var listFacturasExp = new Array();
	for(var i=0; i<expSeleccionados.length;i++){
		var item = expSeleccionados[i];
		listFacturasExp.push({ 
	        "anyo" : item.anyo,
	        "numExp"  : item.numExp
	    });
	}
	
	
    $.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/generarFactura",
		dataType: "json",
		contentType: 'application/json', 
		data: $.toJSON({
	   		"factura" : factura,
	   		"listFacturasExp" : JSON.stringify(listFacturasExp),
	   		"datosFacturacion" : datosFacturacion
	   	}),
		cache: false,
		success: function (data) {
			
			if (data === 1) {
				$('#borradorFactura_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.noExistePartidaPresupuestaria"), "error");
			} else {
				//Utilizamos msgConfirm ya que el evento beforeClose de msgOk no funciona
				$.rup_messages("msgConfirm", { 
						title: $.rup.i18n.app.label.aviso,
						message: $.rup.i18n.app.mensajes.msgGenerarFactura,
						OKFunction : function(){
							// Refrescamos la búsqueda de facturación de expedientes
							volverACapaGeneral();
							$("#busquedaFactExp").rup_table("filter");
							$("#busquedaFactExp").rup_table("resetSelection");
							
							// Se abre la consulta de facturas en una nueva ventana
							window.open('/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/maint');
						}
				});
				//Retocamos msgConfirm para que sea idéntico a msgOk
				$(".ui-dialog-titlebar-close").hide();
				$(".rup-enlaceCancelar").remove();
				$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-ok");
			}
			
			desbloquearPantalla();
	    },
	    error: function (){
	    	$('#borradorFactura_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
	    	desbloquearPantalla();
	    }
    });
	
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	
	$('#borradorFactura_feedback').rup_feedback({
		block : false
	});
	
	fncToolbarBorradorFactura();
	completarCampos();
	$( "button[id^='borradorFacturaMain_toolbar'][id $=generarFactura]" ).addClass( "dcha" );
	var jsonObject = [];
	for(var i=0; i<expSeleccionados.length;i++){
		var item = expSeleccionados[i];
		jsonObject.push({ 
	        "anyo" : item.anyo,
	        "numExp"  : item.numExp
	    });
	}
	
	
	$("#borradorFactura").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/borradorFacturaTabla/"+$.toJSON(jsonObject),
		colNames: [
			"","","",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.relevancia,//tradRev - inicio
			$.rup.i18n.app.label.idioma,
			$.rup.i18n.app.label.tarifa,
			$.rup.i18n.app.label.numTotPalFacturar,
			$.rup.i18n.app.label.urgencia,
			$.rup.i18n.app.label.dificultad,
			$.rup.i18n.app.label.impBase,
			$.rup.i18n.app.label.impIva,
			$.rup.i18n.app.label.totalMayus,//tradRev - fin
			$.rup.i18n.app.label.fecSolicitud,//interpretacion - inicio
			$.rup.i18n.app.label.tipoActo,
			$.rup.i18n.app.label.numInterpretes,
			$.rup.i18n.app.label.horasRealesInterprete,
			$.rup.i18n.app.label.impBase,
			$.rup.i18n.app.label.impIva,
			$.rup.i18n.app.label.totalMayus//interpretacion - fin
		],
		colModel: [
			{
				name: "anyo",
				hidden: true
			},
			{
				name: "numExp",
				hidden: true
			},
			{
				name: "expedienteTradRev.datosFacturacionExpediente.importeUrgencia",
				hidden: true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "ANYONUMEXPCONCATENADO",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					tipoExp = rowObject.idTipoExpediente;
					anyoExpediente = rowObject.anyo;
					idExpediente = rowObject.numExp;
						return '<b style="display: block;">' + cellvalue + '</b>';
				}
			}
			,
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "IDTIPOEXPEDIENTE",
				width: "45", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,//tradRev - inicio
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.relevanciaDescEs"
					:"expedienteTradRev.relevanciaDescEu", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "IDRELEVANCIA",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.idIdiomaDescEs"
					:"expedienteTradRev.idIdiomaDescEu", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "IDIDIOMA",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.tarifaPal", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "TARIFAPAL",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "NUMTOTALPAL",
				width: "180", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "expedienteTradRev.indUrgente", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "INDURGENTE",
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if('S'.localeCompare(cellvalue)==0){
						return $.rup.i18n.app.comun.si + " (" + rowObject.expedienteTradRev.datosFacturacionExpediente.importeUrgencia + "€)";
					}
					return $.rup.i18n.app.comun.no;
				}
			}
			,
			{ 	name: "expedienteTradRev.indDificil", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "INDDIFICIL",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if('S'.localeCompare(cellvalue)==0){
						return $.rup.i18n.app.comun.si + " (" + rowObject.expedienteTradRev.datosFacturacionExpediente.importeDificultad + "€)";
					}
					return $.rup.i18n.app.comun.no;
				}
			}
			,
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.importeBase", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEBASE",
				width: "110", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.importeIva", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEIVA",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.importeTotal", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTETOTAL",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}//tradRev -  fin
			,//interpretacion - inicio
			{ 	name: "fechaAlta", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "FECHAALTA",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue){
						return cellvalue + ' ' + rowObject.horaAlta;
					}else{
						return "-";
					}
				}
			}
			,
			{ 	name: $.rup.lang === 'es' ?"expedienteInterpretacion.tipoActoDescEs"
					:"expedienteInterpretacion.tipoActoDescEu", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "TIPOACTO",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "datosFacturacionInterpretacion.numInterpretes", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "NUMINTERPRETES",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue){
						return cellvalue;
					}else{
						return "-";
					}
				}
			}
			,
			{ 	name: "datosFacturacionInterpretacion.horasInterpretacion", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "NUMINTERPRETES",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue){
						return cellvalue;
					}else{
						return "-";
					}
				}
			}
			,
			{ 	name: "datosFacturacionInterpretacion.importeBase", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEBASE",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue){
						return cellvalue;
					}else{
						return "-";
					}
				}
			}
			,
			{ 	name: "datosFacturacionInterpretacion.importeIva", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEIVA",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue){
						return cellvalue;
					}else{
						return "-";
					}
				}
			}
			,
			{ 	name: "datosFacturacionInterpretacion.importeTotal", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTETOTAL",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue){
						return cellvalue;
					}else{
						return "-";
					}
				}
			}
        ],
        model:"ExpedienteFacturacion",
        usePlugins:[
       		 "filter",
       		 "responsive"
         	],
        primaryKey: ["anyo", "numExp"],
		loadOnStartUp: true,
		sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "asc",
		title: false,
		beforeSelectRow: function (){
			return false;
		},
		loadComplete: function(data){ 
	    	if("1".localeCompare(tipoExpediente)==0	){
	    		//trad/rev
    			$("#borradorFactura").rup_table("showCol", $.rup.lang === 'es' ?"expedienteTradRev.relevanciaDescEs":"expedienteTradRev.relevanciaDescEu");
				$("#borradorFactura").rup_table("showCol", $.rup.lang === 'es' ?"expedienteTradRev.idIdiomaDescEs":"expedienteTradRev.idIdiomaDescEu");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.tarifaPal");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.indUrgente");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.indDificil");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeBase");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeIva");
				$("#borradorFactura").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeTotal");
				$("#borradorFactura").rup_table("showCol", "calculoTradRev");
				$("#borradorFactura").rup_table("hideCol", "fechaAlta");
				$("#borradorFactura").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteInterpretacion.tipoActoDescEs":"expedienteInterpretacion.tipoActoDescEu");
				$("#borradorFactura").rup_table("hideCol", "datosFacturacionInterpretacion.numInterpretes");
				$("#borradorFactura").rup_table("hideCol", "datosFacturacionInterpretacion.horasInterpretacion");
				$("#borradorFactura").rup_table("hideCol", "datosFacturacionInterpretacion.importeBase");
				$("#borradorFactura").rup_table("hideCol", "datosFacturacionInterpretacion.importeIva");
				$("#borradorFactura").rup_table("hideCol", "datosFacturacionInterpretacion.importeTotal");
				$("#borradorFactura").rup_table("hideCol", "calculoInter");
	    	}else{
	    		//interpretacion
	    		$("#borradorFactura").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteTradRev.relevanciaDescEs":"expedienteTradRev.relevanciaDescEu");
				$("#borradorFactura").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteTradRev.idIdiomaDescEs":"expedienteTradRev.idIdiomaDescEu");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.tarifaPal");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.indUrgente");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.indDificil");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeBase");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeIva");
				$("#borradorFactura").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeTotal");
				$("#borradorFactura").rup_table("hideCol", "calculoTradRev");
				$("#borradorFactura").rup_table("showCol", "fechaAlta");
				$("#borradorFactura").rup_table("showCol", $.rup.lang === 'es' ?"expedienteInterpretacion.tipoActoDescEs":"expedienteInterpretacion.tipoActoDescEu");
				$("#borradorFactura").rup_table("showCol", "datosFacturacionInterpretacion.numInterpretes");
				$("#borradorFactura").rup_table("showCol", "datosFacturacionInterpretacion.horasInterpretacion");
				$("#borradorFactura").rup_table("showCol", "datosFacturacionInterpretacion.importeBase");
				$("#borradorFactura").rup_table("showCol", "datosFacturacionInterpretacion.importeIva");
				$("#borradorFactura").rup_table("showCol", "datosFacturacionInterpretacion.importeTotal");
				$("#borradorFactura").rup_table("showCol", "calculoInter");
	    	}
		}
	});
	
});
