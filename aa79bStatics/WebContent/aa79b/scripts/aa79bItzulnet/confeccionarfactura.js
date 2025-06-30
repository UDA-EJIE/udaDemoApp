var capaConfeccFactura = "";
var datosFacturacion;
var countSelectedRows = 0;
var expSeleccionados = [];
var importeBaseTotal = parseFloat("0");
var importeIvaTotal = parseFloat("0");
var importeTotal = parseFloat("0");
var expSinImportes = 0;
var cif;
var entidad;
var contacto;
var anyoDetalleCalculo = 0;
var numExpDetalleCalculo = 0;
var porcentajeIva = 0;

/*
 * *************************
 * FUNCIONALIDADES PANTALLA - INICIO
 * *************************
 */

function obtenerExpedientesSeleccionados(){
	bloquearPantalla();
	var selectedTableOptions = $("#confeccionarFactExp").rup_table("getSelectedIds");
	if(!selectedTableOptions.selectedAll && typeof(selectedTableOptions.selectedIds) === "undefined"){
		// no hay seleccionado ninguno de la lista
		resetearValoresTotales();
		desbloquearPantalla();
		expSeleccionados = [];
	}else{
		var tableModel = 
	    {
	        "ids": typeof(selectedTableOptions.selectedIds) !== "undefined"?selectedTableOptions.selectedIds:null, 
	        "selectAll": selectedTableOptions.selectedAll?1:0,
	        "idTipoExpediente": $('#idTipoExpedienteFilter').val(),
	        "codEntidad":$('#idEntidadFilter').val(),
	        "tipoEntidad":$('#tipoEntidadFilter').val(),
	        "dniSolicitante":$('#dniContactoFilter').val()
	    };
		$.ajax({
	        type: 'POST' 
	        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/obtenerExpedientesSeleccionados/'+tipoExpediente
	        ,data: $.toJSON(tableModel)
	        ,dataType: 'json'
	        ,contentType: 'application/json'
	        ,cache: false 
	        ,success:function(data){
	        	if(data && data.length>0){
	        		expSeleccionados = [];
	        		for(var i=0;i<data.length;i++){
	        			if(data[i].importeBase != null){
	        				expSeleccionados.push({ 
		        		        "anyo" : data[i].anyo,
		        		        "numExp"  : data[i].numExp
		        		    });
	        			}else{
	        				expSeleccionados = [];
	        				desbloquearPantalla();
	        				mostrarMensajeFeedback("confeccionarFactExp_feedback", $.rup.i18n.app.mensajes.expedientesInterSinCalculoFactura, "error");
	        				return false;
	        			}
	        		}
	        	}
	        	
	        	irABorrador();
	        	
	        }
			,error: function(error){
				expSeleccionados = [];
				resetearValoresTotales();
				mostrarMensajeFeedback("confeccionarFactExp_feedback", $.rup.i18n.app.mensajes.errorObteniendoDatosExpedientes, "error");
				desbloquearPantalla();
		   	}
		});
	}
}

function calcularImportesTabla(){
	bloquearPantalla();
	var selectedTableOptions = $("#confeccionarFactExp").rup_table("getSelectedIds");
	if(!selectedTableOptions.selectedAll && typeof(selectedTableOptions.selectedIds) === "undefined"){
		// no hay seleccionado ninguno de la lista
		resetearValoresTotales();
		desbloquearPantalla();
		expSeleccionados = [];
	}else{
		var tableModel = 
	    {
	        "ids": typeof(selectedTableOptions.selectedIds) !== "undefined"?selectedTableOptions.selectedIds:null, 
	        "selectAll": selectedTableOptions.selectedAll?1:0,
	        "idTipoExpediente": $('#idTipoExpedienteFilter').val(),
	        "codEntidad":$('#idEntidadFilter').val(),
	        "tipoEntidad":$('#tipoEntidadFilter').val(),
	        "dniSolicitante":$('#dniContactoFilter').val()
	    };
		$.ajax({
	        type: 'POST' 
	        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/obtenerImportesExpedientes/'+tipoExpediente
	        ,data: $.toJSON(tableModel)
	        ,dataType: 'json'
	        ,contentType: 'application/json'
	        ,cache: false 
	        ,success:function(data){
	        	actualizarValoresTotales(data.importeBase,data.importeIva,data.importeTotal);
	        	desbloquearPantalla();
	        }
			,error: function(error){
				alert("error al obtener importes");
				resetearValoresTotales();
				desbloquearPantalla();
		   	}
		});
	}
}

function mensajeTablaInterpretacion(elTipoDeExpediente){
	if("0".localeCompare(elTipoDeExpediente)==0){
		//mostrar mensaje tabla interpretacion
		$('#mensajeTablaInterpretacion').text($.rup.i18n.app.mensajes.tablaConfeccionarInterpretacion);
	}
}

function irABorrador(){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/borradorfactura/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaConfeccFactura = $('#divConfeccionarFactExp').detach();
	   		 $("#divFacturacionCapa").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
	   		desbloquearPantalla();
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
}

function irDetalleCalculo(elAnyo, elNumExp){
	anyoDetalleCalculo = elAnyo;
	numExpDetalleCalculo = elNumExp;
	//para la cabecera
	idExpediente = elNumExp;
	anyoExpediente = elAnyo;
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/calculoexpedientefacturacion/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaConfeccFactura = $('#divConfeccionarFactExp').detach();
	   		 $("#divFacturacionCapa").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
	   		desbloquearPantalla();
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
}

function realizarCalculo(elAnyo, elNumExp){
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/realizarCalculoInterpretacion/' + elAnyo +'/' + elNumExp
        ,dataType: 'json'
        ,contentType: 'application/json'
        ,cache: false 
        ,success:function(){
        	$("#confeccionarFactExp").trigger("reloadGrid");
        	calcularImportesTabla();
        	$('#confeccionarFactExp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calculoCorrectoExpInterp + concatenarAnyoNumExp(elAnyo,elNumExp), "ok");
        }
		,error: function(error){
 	 		$('#confeccionarFactExp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCalculoCorrectoExpInterp + concatenarAnyoNumExp(elAnyo,elNumExp), "error");
	   	}
	});
}


function actualizarValoresTotales(importeBase,importeIva, importeTot){
	$('#importeBaseTotal').text(importeBase);
	importeBaseTotal = importeBase;
	$('#importeIVAAplicadoTotal').text(importeIva);
	importeIvaTotal = importeIva;
	$('#total').text(importeTot);
	importeTotal = importeTot;
}

function resetearValoresTotales(){
	$('#importeBaseTotal').text("0,00");
	$('#importeIVAAplicadoTotal').text("0,00");
	$('#total').text("0,00");
	importeBaseTotal = "0,00";
	importeIvaTotal = "0,00";
	importeTotal = "0,00"
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
function obtenerDatosConfeccionarFactura(){
	if(!dniContactoSeleccionado){
		dniContactoSeleccionado = "-1";
	}
	$.rup_ajax({
		type: 'GET'
		,dataType: "json"
		,contentType: 'application/json'
	   	,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/obtenerDatosConfeccionarFactura/' + tipoEntidadSeleccionada +'/' + idEntidadSeleccionada + '/' + dniContactoSeleccionado 
	   	,success:function(data, textStatus, jqXHR){
	   		if(data){
	   			datosFacturacion = data;
	   			if(datosFacturacion.datosEntidad.cif){
	   				$('#cif').text(datosFacturacion.datosEntidad.cif);
	   			}else{
	   				$('#cif').text("-");
	   			}
	   			cif = $('#cif').text();
	   			$('#descEuEntidad').text(datosFacturacion.datosEntidad.descEu);
	   			entidad = $('#descEuEntidad').text();
	   			if (dniContactoSeleccionado && dniContactoSeleccionado != "-1"){
	   				$('#domicilio').text(obtenerDireccionFormateada(datosFacturacion.dirFacturacContacto));
	   				if (datosFacturacion.datosContacto){
	   					$("#contacto").text(datosFacturacion.datosContacto.nombreApellidos);
	   				}
	   				$("#capacontacto").show();
	   			}else {
	   				if(datosFacturacion.datosEntidad.direccion.txtCalle){
	   					$('#domicilio').text(obtenerDireccionFormateada(datosFacturacion.datosEntidad.direccion));
	   				}else{
	   					$('#domicilio').text(" - ");
	   				}
	   				$("#contacto").text('');
	   				$("#capacontacto").hide();
	   			}
	   			contacto = $('#contacto').text();
	   			domicilio = $('#domicilio').text();
	   			// Porcentaje de iva asociado a la orden de precios públicos
	   			porcentajeIva = datosFacturacion.porcentajeIva;
	   		}
	   		inicializarRupTableConfecctionarFactura();
	   		
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		$('#confeccionarFactExp_feedback').rup_feedback("set", "error obteniendo datos", "error");
	   	}
	 });
}

function inicializarRupTableConfecctionarFactura(){
	$("#confeccionarFactExp").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/confeccionarFactura",
		colNames: [
			"","","","","",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.boe,
			$.rup.i18n.app.label.relevancia,//tradRev - inicio
			$.rup.i18n.app.label.idioma,
			$.rup.i18n.app.label.tarifa,
			$.rup.i18n.app.label.numTotPalFacturar,
			$.rup.i18n.app.label.urgencia,
			$.rup.i18n.app.label.dificultad,
			$.rup.i18n.app.label.impBase,
			$.rup.i18n.app.label.impIva,
			$.rup.i18n.app.label.totalMayus,
			$.rup.i18n.app.label.calculo,//tradRev - fin
			$.rup.i18n.app.label.fecSolicitud,//interpretacion - inicio
			$.rup.i18n.app.label.tipoActo,
			$.rup.i18n.app.label.numInterpretes,
			$.rup.i18n.app.label.horasRealesInterprete,
			$.rup.i18n.app.label.impBase,
			$.rup.i18n.app.label.impIva,
			$.rup.i18n.app.label.totalMayus,
			$.rup.i18n.app.label.calculo,//interpretacion - fin
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
			{ 	name: "expedienteTradRev.datosFacturacionExpediente.porcentajeIva", 
				hidden: true
			},
			{ 	name: "datosFacturacionInterpretacion.tipoIva", 
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
					//anyoExpediente = rowObject.anyo;
					//idExpediente = rowObject.numExp;
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
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.indPublicadoBoeDescEs"
					: "expedienteTradRev.indPublicadoBoeDescEu", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "INDPUBLICADOBOE",
				width: "45", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue == null){
						return '-';
					}else{
						return cellvalue;
					}
				}
			}
			,
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.relevanciaDescEs"
					:"expedienteTradRev.relevanciaDescEu", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "IDRELEVANCIA",
				width: "90", 
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
				label: "label.numTotPalFacturar",
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
			}
			,
			{ 	name: "calculoTradRev", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					return "<b style='display: block;'><a href='#' onclick='irDetalleCalculo("+rowObject.anyo+","+rowObject.numExp+")'>"+$.rup.i18n.app.label.ver+"</a></b>";
				}
			}//tradRev -  fin
			,//interpretacion - inicio
			{ 	name: "fechaAlta", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "FECHAALTA",
				width: "120", 
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
					}else if(rowObject.datosFacturacionInterpretacion && rowObject.datosFacturacionInterpretacion.importeTotal){
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
				index: "HORASINTERPRETACION",
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
					}else if(rowObject.datosFacturacionInterpretacion && rowObject.datosFacturacionInterpretacion.importeTotal){
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
			,
			{ 	name: "calculoInter", 
				label: "label.anyoNumExpConcatenado",
				align: "center", 
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function(cellvalue, options, rowObject){
					if(rowObject.datosFacturacionInterpretacion){
						if(rowObject.datosFacturacionInterpretacion.importeTotal){
							return "<b style='display: block;'><a href='#' onclick='irDetalleCalculo("+rowObject.anyo+","+rowObject.numExp+")'>"+$.rup.i18n.app.label.ver+"</a></b>";
						}else{
							return "<b style='display: block;'><a href='#' onclick='realizarCalculo("+rowObject.anyo+","+rowObject.numExp+")'>"+$.rup.i18n.app.label.calcular+"</a></b>";
						}
					}else{
						return "-";
					}
				}
			}
				
	    ],
	    model:"ExpedienteFacturacion",
	    usePlugins:[
	   		 "filter",
	   		 "multiselection",
	   		 "fluid"
	     	],
	    primaryKey: ["anyo", "numExp"],
		loadOnStartUp: true,
		sortname : tiposExpediente.tradRev.localeCompare(tipoExpediente)==0?"INDPUBLICADOBOE":"ANYONUMEXPCONCATENADO",
		sortorder : tiposExpediente.tradRev.localeCompare(tipoExpediente)==0?"desc":"asc",
		shrinkToFit:false,
		multiplePkToken:"-",
		multiselection:{
			headerContextMenu:{
			selectAllPage: false,
			deselectAllPage: false,
			separator: false
			}
		},
		filter:{
			beforeFilter: function(){
				$("#confeccionarFactExp").rup_table("resetSelection");
			}
		},
		title: false,
		loadComplete: function(data){ 
			if(tiposExpediente.tradRev.localeCompare(tipoExpediente)==0	){
	    		//trad/rev
				$("#confeccionarFactExp").rup_table("showCol", $.rup.lang === 'es' ?"expedienteTradRev.indPublicadoBoeDescEs":"expedienteTradRev.indPublicadoBoeDescEu");
				$("#confeccionarFactExp").rup_table("showCol", $.rup.lang === 'es' ?"expedienteTradRev.relevanciaDescEs":"expedienteTradRev.relevanciaDescEu");
				$("#confeccionarFactExp").rup_table("showCol", $.rup.lang === 'es' ?"expedienteTradRev.idIdiomaDescEs":"expedienteTradRev.idIdiomaDescEu");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.tarifaPal");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.indUrgente");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.indDificil");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeBase");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeIva");
				$("#confeccionarFactExp").rup_table("showCol", "expedienteTradRev.datosFacturacionExpediente.importeTotal");
				$("#confeccionarFactExp").rup_table("showCol", "calculoTradRev");
				$("#confeccionarFactExp").rup_table("hideCol", "fechaAlta");
				$("#confeccionarFactExp").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteInterpretacion.tipoActoDescEs":"expedienteInterpretacion.tipoActoDescEu");
				$("#confeccionarFactExp").rup_table("hideCol", "datosFacturacionInterpretacion.numInterpretes");
				$("#confeccionarFactExp").rup_table("hideCol", "datosFacturacionInterpretacion.horasInterpretacion");
				$("#confeccionarFactExp").rup_table("hideCol", "datosFacturacionInterpretacion.importeBase");
				$("#confeccionarFactExp").rup_table("hideCol", "datosFacturacionInterpretacion.importeIva");
				$("#confeccionarFactExp").rup_table("hideCol", "datosFacturacionInterpretacion.importeTotal");
				$("#confeccionarFactExp").rup_table("hideCol", "datosFacturacionInterpretacion.tipoIva");
				$("#confeccionarFactExp").rup_table("hideCol", "calculoInter");
	    	}else{
	    		//interpretacion
	    		$("#confeccionarFactExp").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteTradRev.indPublicadoBoeDescEs":"expedienteTradRev.indPublicadoBoeDescEu");
	    		$("#confeccionarFactExp").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteTradRev.relevanciaDescEs":"expedienteTradRev.relevanciaDescEu");
				$("#confeccionarFactExp").rup_table("hideCol", $.rup.lang === 'es' ?"expedienteTradRev.idIdiomaDescEs":"expedienteTradRev.idIdiomaDescEu");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.tarifaPal");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.porcentajeIva");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.numPalFacturarConTramos");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.indUrgente");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.indDificil");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeBase");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeIva");
				$("#confeccionarFactExp").rup_table("hideCol", "expedienteTradRev.datosFacturacionExpediente.importeTotal");
				$("#confeccionarFactExp").rup_table("hideCol", "calculoTradRev");
				$("#confeccionarFactExp").rup_table("showCol", "fechaAlta");
				$("#confeccionarFactExp").rup_table("showCol", $.rup.lang === 'es' ?"expedienteInterpretacion.tipoActoDescEs":"expedienteInterpretacion.tipoActoDescEu");
				$("#confeccionarFactExp").rup_table("showCol", "datosFacturacionInterpretacion.numInterpretes");
				$("#confeccionarFactExp").rup_table("showCol", "datosFacturacionInterpretacion.horasInterpretacion");
				$("#confeccionarFactExp").rup_table("showCol", "datosFacturacionInterpretacion.importeBase");
				$("#confeccionarFactExp").rup_table("showCol", "datosFacturacionInterpretacion.importeIva");
				$("#confeccionarFactExp").rup_table("showCol", "datosFacturacionInterpretacion.importeTotal");
				$("#confeccionarFactExp").rup_table("showCol", "calculoInter");
	    	}
		},
		onSelectRow : function(id, status){
			calcularImportesTabla();
			
//			if(status){
//				//expediente seleccionado
//				var ids = $("#confeccionarFactExp").rup_table("getSelectedRows");
//				
//				if (ids.length > 0){
//					//Obtenemos el id del último expediente seleccionado
//					var anyoNumExpSel = ids[ids.length - 1];
//					var porIvaExp;
//					if("1".localeCompare(tipoExpediente)==0){
//						porIvaExp = $("#confeccionarFactExp").rup_table("getCol", anyoNumExpSel, "expedienteTradRev.datosFacturacionExpediente.porcentajeIva");
//					} else {
//						porIvaExp = $("#confeccionarFactExp").rup_table("getCol", anyoNumExpSel, "datosFacturacionInterpretacion.tipoIva");
//					}
//					
//					if(porcentajeIva == porIvaExp){
//						anyadirYSumar(id, tipoExpediente);
//					} else {
//						$.rup_messages("msgAlert", {
//							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
//							message: $.rup.i18n.app.mensajes.ivaConfeccionFactura
//						});	
//						$("#confeccionarFactExp").rup_table("setSelection", anyoNumExpSel, false);
//						return false;
//					}
//				}
		},onSelectAll : function(id, status){
			calcularImportesTabla();
		}, onSelectAllRows : function(id,status){
			calcularImportesTabla();
		}
	});
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

function fncFeedbackConfeccionarFactura(){
	 $('#confeccionarFactExp_feedback').rup_feedback({
  		block : false,
 		delay: 6000
  	});
}

function fncToolbarFacturacionExpedientes(){
	$("#confeccionarFactExp_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id:"confeccionarFactura"
				,css: "fa fa-arrow-left"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			volverACapaGeneral();
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.verBorrador
				,id:"confeccionarFactura"
					,css: "fa fa-file"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			 if(!$('#confeccionarFactExp').rup_table("isSelected")){
							$.rup_messages("msgAlert", {
								title: $.rup.i18n.app.label.aviso,
								message: $.rup.i18n.app.comun.warningSeleccion
							});	
							return false;
						 }else{
							 if("0".localeCompare(tipoExpediente)==0 && expSinImportes > 0){
								 //interpretacion - expediente(s) sin importes
								 $.rup_messages("msgAlert", {
			             				title: $.rup.i18n.app.label.aviso,
			             				message: $.rup.i18n.app.mensajes.expedientesSinImporte,
			             				OKFunction: function(){
			             					
			             				}
			             			});
								 return false;
							 }
							 obtenerExpedientesSeleccionados();
						 }
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

jQuery(function($){
	bloquearPantalla();
	fncToolbarFacturacionExpedientes();
	obtenerDatosConfeccionarFactura();
	fncFeedbackConfeccionarFactura();
	$('#idTipoExpedienteFilter').val(tipoExpediente);
	$('#tipoEntidadFilter').val(tipoEntidadSeleccionada);
	$('#idEntidadFilter').val(idEntidadSeleccionada);
	$('#dniContactoFilter').val(dniContactoSeleccionado);
	
	
	resetearValoresTotales();
	mensajeTablaInterpretacion(tipoExpediente);
	desbloquearPantalla();
});
