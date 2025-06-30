var expedienteCalculo;
var contactoSinVincular = false;
/*
 * *************************
 * FUNCIONALIDADES PANTALLA - INICIO
 * *************************
 */

function volverAConfeccionarFactura(){
	$("#calculoExpFacturacion").detach();
	$("#divFacturacionCapa").html("<div id='divFacturacionGeneral'></div>");
	$("#divFacturacionGeneral").html(capaConfeccFactura);
}


function ajustarPantallaCalculo(){
	if(typeof(modoDetalle) !== "undefined"){
		$('#calculoExpFacturacion').removeClass("col-md-12");
		if($('#calculoExpedienteFacturacionMain_toolbar').length){
			$('#calculoExpedienteFacturacionMain_toolbar').removeClass("row");
			$('#calculoExpedienteFacturacionMain_toolbar').attr("style","width: 100%!important");
		}
		$('#calculoExpedienteFacturacion_div').addClass("marginLeft0");
		$('#calculoExpedienteFacturacion_div').addClass("marginRight0");
	}else if($('#calculoExpedienteFacturacionMain_toolbar').length){
		$('#calculoExpedienteFacturacionMain_toolbar').attr('style', 'width: auto!important');
		$('#calculoExpedienteFacturacionMain_toolbar').css("margin-right","-15px");
		$('#calculoExpedienteFacturacionMain_toolbar').css("margin-top","10px");
		$('#calculoExpedienteFacturacionMain_toolbar').addClass("row");
	}
	/*adaptar fieldsets*/
	if($('#numPalabrasFact_filter_fieldset').length && $('#numPalabrasExp_filter_fieldset').length){
		if($('#numPalabrasFact_filter_fieldset').height()>$('#numPalabrasExp_filter_fieldset').height()){
			$('#numPalabrasExp_filter_fieldset').height($('#numPalabrasFact_filter_fieldset').height());
		}else if($('#numPalabrasExp_filter_fieldset').height()>$('#numPalabrasFact_filter_fieldset').height()){
			$('#numPalabrasFact_filter_fieldset').height($('#numPalabrasExp_filter_fieldset').height())
		}
	}
}

/*
 * *************************
 * FUNCIONALIDADES PANTALLA - FIN
 * *************************
 */

/*
 * *************************
 * CARGA INICIAL - INICIO
 * *************************
 */

function obtenerDatosCalculoExpedienteFacturacion(){
	$.rup_ajax({
		type: 'GET'
		,dataType: "json"
		,contentType: 'application/json'
	   	,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/obtenerDatosCalculoExpedienteFacturacion/' + anyoDetalleCalculo +'/' + numExpDetalleCalculo + '/' + tipoExpediente 
	   	,success:function(data, textStatus, jqXHR){
	   		if(data){
	   			expedienteCalculo = data;
	   			ocultarMostrarCampos();
	   			volcarDatosCampos();
	   		}
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
//	   		$('#confeccionarFactExp_feedback').rup_feedback("set", "error obteniendo datos", "error");
	   	}
	 });
}

function volcarDatosCampos(){
	//fecha solicitud
	$('#fechaHoraSolicitud').text(expedienteCalculo.fechaHoraAlta)
	//expFacturable
	$('#checkExpFacturable').bootstrapSwitch('setState', true);
	if(!$('#checkExpFacturable')[0].disabled){
		$('#checkExpFacturable').bootstrapSwitch('toggleDisabled',true,true);
	}
	//tituloOrdenPreciosPublicos
	$('#tituloOrdenPreciosPublicos').text(expedienteCalculo.titOrdenPreciosPublicos)
	
	if(tiposExpediente.tradRev.localeCompare(tipoExpediente)==0	){
		
		
		//traduccion revision
		//fecha entrega izo
		$('#fechaEntregaIzo').text(expedienteCalculo.expedienteTradRev.fechaHoraFinalIZO);
		//bopv
		if("S".localeCompare(expedienteCalculo.expedienteTradRev.indPublicarBopv)==0){
			$('#bopv').text($.rup.i18n.app.comun.si);
		}else{
			$('#bopv').text($.rup.i18n.app.comun.no);
		}
		//idioma
		$('#idiomaRevDest').text(expedienteCalculo.expedienteTradRev.idIdiomaDescEu);
		//relevancia
		$('#relevancia').text(expedienteCalculo.expedienteTradRev.relevanciaDescEu);
		//recargoDificultad
		if("S".localeCompare(expedienteCalculo.expedienteTradRev.indDificil)==0){
			$('#recargoDificultad').bootstrapSwitch('setState', true);
		}else{
			$('#recargoDificultad').bootstrapSwitch('setState', false);
		}
		if(!$('#recargoDificultad')[0].disabled){
			$('#recargoDificultad').bootstrapSwitch('toggleDisabled',true,true);
		}
		//recargoUrgencia
		if("S".localeCompare(expedienteCalculo.expedienteTradRev.indUrgente)==0){
			$('#recargoUrgencia').bootstrapSwitch('setState', true);
		}else{
			$('#recargoUrgencia').bootstrapSwitch('setState', false);
		}
		if(!$('#recargoUrgencia')[0].disabled){
			$('#recargoUrgencia').bootstrapSwitch('toggleDisabled',true,true);
		}
		//tarifaPalabra
		$('#tarifaPalabra').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.tarifaPalEur + " €");
		//iva
		$('#iva').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.porcentajeIva);
		//recargo especial dificultad
		if(expedienteCalculo.porRecargoDif){
			$('#porcRecargoPorDificultad').text(expedienteCalculo.porRecargoDif);
		}else{
			$('#porcRecargoPorDificultad').text("-");
		}
		//recargo porcentaje urgencia
		if(expedienteCalculo.porRecargoUrg){
			$('#porcRecargoPorUrgencia').text(expedienteCalculo.porRecargoUrg);
		}else{
			$('#porcRecargoPorUrgencia').text("-");
		}
		//precioMinimoTradRev
		$('#precioMinimoTradRev').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.precioMinimoEur);
		//num palabras a partir  del cual se aplica tarificacion
		$('#numPalExpTarificacion').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.numTotalPalFacturar);
		//fact rango concor 0 84
		$('#porcFactPalabraConcor084').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.numPalConcor084);
		//fact rango concor 85 94
		$('#porcFactPalabraConcor8594').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.numPalConcor8594);
		//fact rango concor 95 100
		$('#porcFactPalabraConcor95100').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.numPalConcor95100);
		//num total pal solic
		$('#numTotPalabrasSolic').text(expedienteCalculo.expedienteTradRev.numTotalPalSolic);
		//num total pal izo
		$('#numTotPalabrasIzo').text(expedienteCalculo.expedienteTradRev.numTotalPalIzo);
		//xml trados
		if(expedienteCalculo.expedienteTradRev.tradosExp && expedienteCalculo.expedienteTradRev.tradosExp.numTotalPal>0){
			//num total pal xml
			$('#numTotPalabrasXml').text(expedienteCalculo.expedienteTradRev.tradosExp.numTotalPal);
			//rango concor 0 84 xml
			$('#xmlConcor084').text(expedienteCalculo.expedienteTradRev.tradosExp.numPalConcor084);
			//rango concor 85 94 xml
			$('#xmlConcor8594').text(expedienteCalculo.expedienteTradRev.tradosExp.numPalConcor8594);
			//rango concor 95 100 xml
			$('#xmlConcor95100').text(expedienteCalculo.expedienteTradRev.tradosExp.numPalConcor95100);
		}else{
			//num total pal xml
			$('#numTotPalabrasXml').text("-");
			//rango concor 0 84 xml
			$('#xmlConcor084').text("-");
			//rango concor 85 94 xml
			$('#xmlConcor8594').text("-");
			//rango concor 95 100 xml
			$('#xmlConcor95100').text("-");
		}
		//num total pal fact cliente
		$('#numTotPalabras').text(expedienteCalculo.datosFacturacionCliente.numTotalPal);
		//rango concor 0 84 fact cliente
		$('#numTotPalabrasConcor084').text(expedienteCalculo.datosFacturacionCliente.numPalConcor084);
		//rango concor 85 94 fact cliente
		$('#numTotPalabrasConcor8594').text(expedienteCalculo.datosFacturacionCliente.numPalConcor8594);
		//rango concor 95 100 fact cliente
		$('#numTotPalabrasConcor95100').text(expedienteCalculo.datosFacturacionCliente.numPalConcor95100);
		//importe base
		$('#importeBaseTotalCalculo').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.importeBaseEur);
		//importeIVAAplicadoTotalCalculo
		$('#importeIVAAplicadoTotalCalculo').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.importeIvaEur);
		//totalCalculo
		$('#totalCalculo').text(expedienteCalculo.expedienteTradRev.datosFacturacionExpediente.importeTotalEur);
	}else{
		//interpretacion
		//tipo peticionario
		$('#tipoPeticionario').text(expedienteCalculo.expedienteInterpretacion.tipoPeticionarioDescEu);
		//tipo acto
		$('#tipoActo').text(expedienteCalculo.expedienteInterpretacion.tipoActoDescEu);
		//programada
		if("S".localeCompare(expedienteCalculo.expedienteInterpretacion.indProgramada)==0){
			$('#interpProgramada').bootstrapSwitch('setState', true);
		}else{
			$('#interpProgramada').bootstrapSwitch('setState', false);
		}
		if(!$('#interpProgramada')[0].disabled){
			$('#interpProgramada').bootstrapSwitch('toggleDisabled',true,true);
		}
		//presupuesto
		if("S".localeCompare(expedienteCalculo.expedienteInterpretacion.indPresupuesto)==0){
			$('#interpPresupuesto').bootstrapSwitch('setState', true);
		}else{
			$('#interpPresupuesto').bootstrapSwitch('setState', false);
		}
		if(!$('#interpPresupuesto')[0].disabled){
			$('#interpPresupuesto').bootstrapSwitch('toggleDisabled',true,true);
		}
		//direccion
		$('#direccionInterp').text(expedienteCalculo.expedienteInterpretacion.dirNora.txtDirec);
		//en la cae
		$('#enCae').bootstrapSwitch('setState', expedienteCalculo.datosFacturacionInterpretacion.cae);
		if(!$('#enCae')[0].disabled){
			$('#enCae').bootstrapSwitch('toggleDisabled',true,true);
		}
		//num interpretes
		$('#numInterpretes').text(expedienteCalculo.datosFacturacionInterpretacion.numInterpretes);
		//horas reales
		$('#horasReales').text(expedienteCalculo.datosFacturacionInterpretacion.horasInterpretacion);
		//iva
		$('#iva').text(expedienteCalculo.datosFacturacionInterpretacion.tipoIva);
		//precio minimo interprete
		$('#precioMinimoInterp').text(expedienteCalculo.datosFacturacionInterpretacion.precioMinimoInterpRealizEur);
		//tipo facturacion acto
		// mostrar los precios publicos acorde con el tipo de facturacion y si no lo tiene, por el tipo de acto v3.9.50
		var tipoFactInter = "";
		if(expedienteCalculo.datosFacturacionInterpretacion.tipoFacturacion != null
			&& expedienteCalculo.datosFacturacionInterpretacion.tipoFacturacion != ""){
			tipoFactInter = expedienteCalculo.datosFacturacionInterpretacion.tipoFacturacion;
		}else{
			tipoFactInter = expedienteCalculo.expedienteInterpretacion.indActoFacturable;
		}
		if('H'.localeCompare(tipoFactInter)==0){
			//facturacion por horas
			$('#precioInterpreteFieldset').hide();
			$('#precioHoraInterprete').text(expedienteCalculo.datosFacturacionInterpretacion.precioHoraInterpreteEur);
		}else{
			//facturacion por interprete
			$('#precioHoraFieldset').hide();
			$('#precioJornadaCompleta').text(expedienteCalculo.datosFacturacionInterpretacion.precioJornadaCompletaEur);
			$('#precioMediaJornada').text(expedienteCalculo.datosFacturacionInterpretacion.precioMediaJornadaEur);
			$('#precioHoraFraccionAdic').text(expedienteCalculo.datosFacturacionInterpretacion.precioHoraFraccAdicEur);
		}
		//importe base
		$('#importeBaseTotalCalculo').text(expedienteCalculo.datosFacturacionInterpretacion.importeBaseEur);
		//importeIVAAplicadoTotalCalculo
		$('#importeIVAAplicadoTotalCalculo').text(expedienteCalculo.datosFacturacionInterpretacion.importeIvaEur);
		//totalCalculo
		$('#totalCalculo').text(expedienteCalculo.datosFacturacionInterpretacion.importeTotalEur);
	}
	
	
	
}

function ocultarMostrarCampos(){
	if(tiposExpediente.tradRev.localeCompare(tipoExpediente)==0	){
		//traduccion revision
		$('#datosExpFacturableInterp01').hide();
		$('#datosExpFacturableInterp02').hide();
		$('#datosExpFacturableInterp03').hide();
		$('#datosExpFacturableInterp04').hide();
		$('#datosExpFacturableInterp05').hide();
		$('#datosExpFacturableInterp06').hide();
	}else{
		//interpretacion
		$('#divFechaIzoYBopv').hide();
		$('#divIdiomaRevDest').hide();
		$('#datosExpFacturableTradRev01').hide();
		$('#divTarifaPalabra').hide();
		$('#datosExpFacturableTradRev02').hide();
		$('#datosExpFacturableTradRev03').hide();
		$('#datosExpFacturableTradRev04').hide();
		$('#datosExpFacturableTradRev05').hide();
	}
}

/*
 * *************************
 * CARGA INICIAL - FIN
 * *************************
 */


/*
 * *************************
 * COMPONENTES - INICIO
 * *************************
 */

function fncToolbarCalculoExpFact(){
	$('#calculoExpedienteFacturacionMain_toolbar').rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverCalculoExpFact"
				,css: "fa fa-arrow-left"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			if (typeof(detalleModoConsulta) != "undefined"){
		 				volverADetalleConsulta();
		 			}else{
			 			volverAConfeccionarFactura();
		 			}
				}
			}
		]
	})
}

function fncCheckBoxRecargoDificultad(){
	jQuery('#recargoDificultad')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}
function fncCheckBoxRecargoUrgencia(){
	jQuery('#recargoUrgencia')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}
function fncCheckBoxInterpProgramada(){
	jQuery('#interpProgramada')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}
function fncCheckBoxInterpPresupuesto(){
	jQuery('#interpPresupuesto')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}
function fncCheckBoxInterpEnCae(){
	jQuery('#enCae')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}

function fncCheckBoxCalculoExpFact(){
	jQuery('#checkExpFacturable')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	if(tiposExpediente.tradRev.localeCompare(tipoExpediente)==0	){
		//tradrev
		fncCheckBoxRecargoDificultad();
		fncCheckBoxRecargoUrgencia();
	}else{
		//interpretacion
		fncCheckBoxInterpProgramada();
		fncCheckBoxInterpPresupuesto();
		fncCheckBoxInterpEnCae();
	}
}

/*
 * *************************
 * COMPONENTES - FIN
 * *************************
 */

/*
 * *************************
 * FORMATTER - INICIO
 */
 /*
 *  FORMATTER - FIN
 */

jQuery(function($){
	fncToolbarCalculoExpFact();
	fncCheckBoxCalculoExpFact();
	obtenerDatosCalculoExpedienteFacturacion();
	$("#calculoExpedienteFacturacion").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/calculoExpedienteFacturacionTabla/"+anyoDetalleCalculo+"/"+numExpDetalleCalculo+"/"+tipoExpediente,
		colNames: [
			"","",
			txtEntidadContactoFact,
			$.rup.i18n.app.label.direccionPostal,
			$.rup.i18n.app.label.facturable,
			$.rup.i18n.app.label.porcFactura,
			$.rup.i18n.app.label.porcFactura,
			$.rup.i18n.app.label.iva,
			$.rup.i18n.app.label.nPalabras,
			$.rup.i18n.app.label.importeBase,
			$.rup.i18n.app.label.importeIva,
			$.rup.i18n.app.label.totalMayus,
			$.rup.i18n.app.label.importeBase,
			$.rup.i18n.app.label.importeIva,
			$.rup.i18n.app.label.totalMayus,
			$.rup.i18n.app.label.numFactura
		],
		colModel: [
			{
				name: "anyo",
				hidden: true
			},
			{
				name: "anyo",
				hidden: true
			},
			{ 	name: "entidadContactoFacturaCell", 
				label: "label.entidadAsociada",
				align: "left", 
				index: "DESCENTIDADES",
				width: "250", 
				editable: true, 
				hidden: false, 
				resizable: true,
				sortable: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				},
				formatter: function (cellvalue, options, rowObject) {
					if(!rowObject.gestorExpediente.solicitante.solicitanteVinculado && rowObject.gestorExpediente.solicitante.dni){
						contactoSinVincular = true;
					}
					return cellvalue;
				}
			},
			{ 	name: "entidadContactoFactura.entidad.direccion.txtProvincia", 
				label: "label.anyoNumExpConcatenado",
				align: "left", 
				index: "NOMBRE",
				width: "170", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					if(rowObject.gestorExpediente.solicitante.dni && rowObject.gestorExpediente.solicitante.solicitanteVinculado){
						return '<p class="noMargin txtTablaTarea pr1">'+obtenerDireccionFormateada(rowObject.gestorExpediente.entidad.direccion)+'</p>';
					}else if(rowObject.entidadContactoFactura.entidad.direccion.txtCalle){
						return '<p class="noMargin txtTablaTarea pr1">'+obtenerDireccionFormateada(rowObject.entidadContactoFactura.entidad.direccion)+'</p>';
					}else{
						return "-";
					}
				}
			},
			{ 	name: "entidadContactoFactura.entidad.facturable", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "FACTURABLE",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					if("S".localeCompare(cellvalue)==0){
						return $.rup.i18n.app.comun.si;
					}
					return $.rup.i18n.app.comun.no;
				}
			},
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.porcentajeFacturacion", 
				label: "label.anyoNumExpConcatenado",
				align: "left", 
				index: "PORCENTAJEFACTURACION",
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					return cellvalue + "%";
				}
			},
			{ 	name: "datosFacturacionInterpretacion.porcentajeFacturacion", 
				label: "label.anyoNumExpConcatenado",
				align: "left", 
				index: "PORCENTAJEFACTURACION",
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					return cellvalue + "%";
				}
			},
			{ 	name: "indIva", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "IVA",
				width: "50", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					if("S".localeCompare(cellvalue)==0){
						return $.rup.i18n.app.comun.si;
					}
					return $.rup.i18n.app.comun.no;
				}
			},
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "NUMTOTALPAL",
				width: "180", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.importeBase", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEBASE",
				width: "160", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.importeIva", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEIVA",
				width: "150", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.importeTotal", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTETOTAL",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionInterpretacion.importeBase", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEBASE",
				width: "160", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionInterpretacion.importeIva", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTEIVA",
				width: "150", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionInterpretacion.importeTotal", 
				label: "label.anyoNumExpConcatenado",
				align: "right", 
				index: "IMPORTETOTAL",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "idFactura", 
				label: "label.idfactura",
				align: "right", 
				index: "IDFACTURA",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue){
						return cellvalue;
					}else{
						return "-";
					}
				}
			}
        ],
        model:"ExpedienteFacturacion",
        usePlugins:[
       		 "filter"
         	],
        primaryKey: ["anyo", "numExp"],
		loadOnStartUp: true,
		sortname : "IMPORTETOTAL",
		sortorder : "desc",
		title: false,
		loadComplete: function(data){ 
	    	if(tiposExpediente.tradRev.localeCompare(tipoExpediente)==0	){
	    		//trad/rev
				$("#calculoExpedienteFacturacion").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.porcentajeFacturacion");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeBase");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeIva");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeTotal");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "datosFacturacionInterpretacion.importeBase");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "datosFacturacionInterpretacion.importeIva");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "datosFacturacionInterpretacion.importeTotal");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "datosFacturacionInterpretacion.porcentajeFacturacion");
	    	}else{
	    		//interpretacion
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.porcentajeFacturacion");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeBase");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeIva");
				$("#calculoExpedienteFacturacion").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeTotal");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "datosFacturacionInterpretacion.importeBase");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "datosFacturacionInterpretacion.importeIva");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "datosFacturacionInterpretacion.importeTotal");
				$("#calculoExpedienteFacturacion").rup_table("showCol", "datosFacturacionInterpretacion.porcentajeFacturacion");
	    	}
	    	
	    	if (contactoSinVincular){
	    		$("#labelContactoNoVinculado").show();
	    		contactoSinVincular = false;
	    	}else{
	    		$("#labelContactoNoVinculado").hide();
	    	}
	    	
	    	$('#calculoExpedienteFacturacion_pager').hide();
		}
	});
	if (typeof(detalleModoConsulta) != "undefined"){
		$('[id=calculoExpFacturacion] .container').hide();
	}
	llamadasFinalizadas("ajustarPantallaCalculo");
});
