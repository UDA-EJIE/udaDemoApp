nora_response();
var estado;
var capaDetalleExpConsulta;
var detalleModoConsulta = true; 
var tipoExp;
var anyo;
var numExp;
var anyoDetalleCalculo;
var numExpDetalleCalculo;
var tipoExpediente;
var adaptarPantallaFacturacion = false;
var mostrarIconoGestorNoActivo = false;
/*
* ****************************
* NAVEGACION - INICIO
* ****************************
*/

//volver a capa desde donde se ha accedido al detalle - debera estar la variable y la funcion correspondiente
function volverAConsulta(){
	if(typeof(desdeConsultaPlanifExpedientes) !== "undefined" && desdeConsultaPlanifExpedientes){
		//consulta planificacion expedientes
		volverACapaGeneralConsultaPlanifExp();
	}else if(typeof(desdeConsultaExpedientes) !== "undefined"){
		//consulta expedientes
		volverACapaGeneralConsulta();
	}else if(typeof(desdeDesgloseTareasConsPlanif) !== "undefined"){
		//desgloseTareas de consulta planificacionExpedientes
		volverADesgloseTareasConsultaPlanif();
	}
	detalleModoConsulta = undefined;
	modoDetalle = undefined;
}

function ajustarPantallaDetalleExpedienteNuevaVentana(){
	$('#contenedorDetalle').addClass("container-fluid");
}

function inicializarFeedbackDetalleConsulta(){
	$('#detalleExpedienteConsulta_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
}

function comprobarEsSolicitud(){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente
	};
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/consultas/comprobarEsSolicitud",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			//si es mayor que cero es solicitud --> mostrar justificante pdf. si 0, ocultar justificantepdf
			if(data==0){
				accionBotonToolbar("detalleExpedienteConsulta_toolbar", null, "justificante", 0);
			}
			desbloquearPantalla();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			mostrarMensajeFeedback("detalleExpedienteConsulta_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
		}
	});
}


function comprobarDatosFacturacion(){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente,
			"idTipoExpediente": tipoExp
		};
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/consultas/comprobarDatosFacturacionExpediente",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			//si es mayor que cero obtener datos de facturacion
			if(data==0){
				accionBotonToolbar("detalleExpedienteConsulta_toolbar", null, "datosFacturacion", 0);
			}
			comprobarEsSolicitud();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			mostrarMensajeFeedback("detalleExpedienteConsulta_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
		}
	});
}

function comprobarDatosPagoAProveedores(){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente,
			"idTipoExpediente": tipoExp
		};
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/consultas/comprobarDatosPagoAProveedores",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			//si es mayor que cero obtener datos de pago a proveedores
			if(data==0){
				accionBotonToolbar("detalleExpedienteConsulta_toolbar", "otrosToolbarExpediente", "datosPagoAProveedores", 0);
			}
			comprobarDatosFacturacion();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			mostrarMensajeFeedback("detalleExpedienteConsulta_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
		}
	});
}

function ocultarBotonesToolbar(){
	accionBotonToolbar("detalleExpedienteConsulta_toolbar", null, "tareas", 0);
	accionBotonToolbar("detalleExpedienteConsulta_toolbar", null, "datosFacturacion", 0);
	accionBotonToolbar("detalleExpedienteConsulta_toolbar", "otrosToolbarExpediente", "datosPagoAProveedores", 0);
	accionBotonToolbar("detalleExpedienteConsulta_toolbar", "otrosToolbarExpediente", "expDesdeCliente", 0);
	comprobarEsSolicitud();
}

function funcionalidadTecnicoIzo(){
	comprobarDatosPagoAProveedores();
}
function mostrarConfigTarea(){
	cambiarCapaDesdeDetalleConsulta("/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/expedientetareas/maint");
}

function mostrarDatosFacturacion(){
	anyoDetalleCalculo = anyoExpediente;
	numExpDetalleCalculo = idExpediente;
	if("I" == tipoExp){
		tipoExpediente = 0;
	}else {
		tipoExpediente = 1; 
	}	
	adaptarPantallaFacturacion = true;
	cambiarCapaDesdeDetalleConsulta("/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/calculoexpedientefacturacion/maint");
	
}

function mostrarDatosPagoAProveedores(){
	cambiarCapaDesdeDetalleConsulta("/aa79bItzulnetWar/consultas/datospagoaproveedoresconsulta/maint");
}

function mostrarCategorizacionExpediente(){
	anyo = anyoExpediente;
	numExp = idExpediente;
	cambiarCapaDesdeDetalleConsulta("/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/categorizacionExpediente/maint");
}

function mostrarExpDesdeCliente(){
	cambiarCapaDesdeDetalleConsulta("/aa79bItzulnetWar/consultas/detalleexpedientedesdeclienteconsulta/maint");
}

function cambiarCapaDesdeDetalleConsulta(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaDetalleExpConsulta = $('#detalleExpedienteConsulta_div').detach();
	   		$("#detalleExpedienteConsultaContainer").html("<div id='detalleExpedienteConsulta_div'></div>");
	   		 $("#detalleExpedienteConsulta_div").html(data);
	   		 if(adaptarPantallaFacturacion){
		   		$("#detalleExpedienteConsulta_div").addClass("row");
	   			$("#calculoExpFacturacion").removeClass();
	   			$("#calculoExpFacturacion").addClass("col-md-12");
	   			adaptarPantallaFacturacion = false;
	   		 }
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
			desbloquearPantalla();
	   	 }
	 });
}

function volverADetalleConsulta(){
	$("#detalleExpedienteConsulta_div").detach();
	$("#detalleExpedienteConsultaContainer").html(capaDetalleExpConsulta);
	if ($('#datosTradRevDivDocConsulta').length){
		//está activa la pestaña de documentación
		//Comprobar si hay presupuesto visible para ee usuario (puede haber cambiado tras la ejecución de tareas)
				$("#tabsExpediente").rup_tabs("loadTab",{
					idTab: "tabsExpediente",
					position: 1
				});
	}else{
		//está activa la pestaña de datos generales
		$("#tabsExpediente").rup_tabs("loadTab",{
			idTab: "tabsExpediente",
			position: 0
		});
	}
}

function obtenerJustificanteSolicitud(){
	window.open("/aa79bItzulnetWar/consultas/detalle/pdfSolicitud?anyo="+anyoExpediente+"&numExp="+idExpediente);
}

/*
* ****************************
* NAVEGACION - FIN
* ****************************
*/

/*
* ****************************
* CABECERA - INICIO
* ****************************
*/
function cabeceraUpdate(data) {
	$("#cabecera_numExpediente").text(data.anyoNumExpConcatenado);
	$("#cabecera_tituloExpediente").text(data.titulo);
	$("#cabecera_tipoExpediente").text(data.tipoExpedienteDescEuPDF);
	$("#cabecera_solicitante").text(data.gestorExpediente.entidad.descAmpEu);
	$("#cabecera_contacto").text(data.gestorExpediente.solicitante.nombreCompleto);
	if(!data.gestorExpediente.solicitante.solicitanteVinculado){
		$("#cabecera_contacto").css("text-decoration","line-through");
	}
	if(data.gestorExpediente && data.gestorExpediente.solicitante && data.gestorExpediente.solicitante.estado){
		estado = data.gestorExpediente.solicitante.estado;
	}
	tipoExp = data.idTipoExpediente;
}

function fncCabecera(){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente
		};
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/bitacoraexpediente/getCabeceraBitacoraConEstadoGestor",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			//Se setean los datos de la cabecera
			cabeceraUpdate(data);
			estado = data.gestorExpediente.solicitante.solicitanteVinculado;
			fncBitacora();
			fncToolbar();
			jQuery.ajaxSetup({cache: false });
			fncPestanas();
			if(esTecnicoIzo){
				if(!estado){
					mostrarIconoGestorNoActivo = true;
				}
				llamadasFinalizadas('funcionalidadTecnicoIzo');
			}else{
				ocultarBotonesToolbar();
			}
		},error: function(XMLHttpRequest, textStatus, errorThrown){
			mostrarMensajeFeedback("detalleExpedienteConsulta_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
		}
	});
}
/*
* ****************************
* CABECERA - FIN
* ****************************
*/

/*
* ****************************
* BITACORA - INICIO
* ****************************
*/
function fncBitacora(){
	bitacoraUpdate(false);
}
/*
* ****************************
* BITACORA - FIN
* ****************************
*/

/*
 * ****************************
 * TOOLBAR - INICIO
 * ****************************
 */
function fncToolbar(){
	$("#detalleExpedienteConsulta_toolbar").rup_toolbar({
		buttons:[
			{
				id : "volver"
                ,i18nCaption: $.rup.i18n.app.boton.volver
                ,css: "fa fa-arrow-left"
                ,click : 
                function(e){
                	e.preventDefault();
		 			e.stopImmediatePropagation();
		 			volverAConsulta();
                }
			}
			,
			{
				id : "tareas"
                ,i18nCaption: $.rup.i18n.app.boton.configTarea
                ,css: "fa fa-list-ul"
                ,click : 
                function(e){
                	e.preventDefault();
		 			e.stopImmediatePropagation();
		 			mostrarConfigTarea();
                }
			}
			,
			{
				id : "datosFacturacion"
				,i18nCaption: $.rup.i18n.app.boton.datosFacturacion
                ,css: "fa fa-ticket"
                ,click : 
                function(e){
                	e.preventDefault();
		 			e.stopImmediatePropagation();
                	mostrarDatosFacturacion();
                }
			}
			,{
				id : "otrosToolbarExpediente",
				i18nCaption : $.rup.i18n.app.boton.otros,
				click: function(event){
					$("[id='detalleExpedienteConsulta_toolbar##otrosToolbarExpediente-mbutton-container']").show();
				},
                buttons : [{
                			id : "datosPagoAProveedores"
                			,i18nCaption: $.rup.i18n.app.boton.datosPagoAProveedores
			                ,css: "fa fa-credit-card"
			                ,click : function(e){
			                	e.preventDefault();
			                	e.stopImmediatePropagation();
			                	mostrarDatosPagoAProveedores();
				                }
					        }
					        ,{
					        	id : "categorizacionExpediente"
		                		,i18nCaption: $.rup.i18n.app.boton.categorizacionExpediente
				                ,css: "fa fa-tags"
			                    ,click : function(e){
			                    	e.preventDefault();
				                	e.stopImmediatePropagation();
			                    	mostrarCategorizacionExpediente();
			                    }
					        }
					        ,{
					        	id : "expDesdeCliente"
			                	,i18nCaption: $.rup.i18n.app.boton.expDesdeCliente
				                ,css: "fa fa-eye"
			                    ,click : function(e){
			                    	e.preventDefault();
				                	e.stopImmediatePropagation();
				                	bloquearPantalla();
			                    	mostrarExpDesdeCliente();
			                    }
					        }
			]},
			{
				id : "justificante"
				,i18nCaption: $.rup.i18n.app.boton.justificanteSolicitud
				,right: true
                ,css: "fa fa-file-pdf-o"
                ,click : 
                function(e){
                	e.preventDefault();
					e.stopImmediatePropagation();
                	obtenerJustificanteSolicitud();
                }
			}
		]
	});
	$("[id='detalleExpedienteConsulta_toolbar##otrosToolbarExpediente-mbutton-container']").addClass('centrado');
	
	//ocultar boton volver si estamos en modo nueva ventana
	if(modoDetalleEnum.nuevaVentana === modoDetalle){
		$("[id='detalleExpedienteConsulta_toolbar##volver']").hide();
	}
}
/*
 * ****************************
 * TOOLBAR - FIN
 * ****************************
 */

/*
* ****************************
* PESTANAS DATOS EXPEDIENTE - INICIO
* ****************************
*/
function fncPestanas(){
	$("#tabsExpediente").rup_tabs({
		tabs : [
			{i18nCaption: "pestdatosg", url:"/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/detalleExpediente/"+anyoExpediente+"/"+idExpediente+"?"+$.now()},
			{i18nCaption: "pestdocumentos", url:"/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/listadoConFactYPresup/"+anyoExpediente+"/"+idExpediente+"/"+origen+"/"+tipoExp+"?"+$.now()}
		]
	,cache: false
	,fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
		  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		 ] // show option
	,load: function(){
		if(mostrarIconoGestorNoActivo){
			if($('#iconoGestorNoActivo').length){
				$('#iconoGestorNoActivo')[0].classList.remove("oculto");
			}
		}
	}
	});
}
/*
* ****************************
* PESTANAS DATOS EXPEDIENTE - FIN
* ****************************
*/



jQuery(function($){
	bloquearPantalla();
	inicializarFeedbackDetalleConsulta();
	fncCabecera();
	if(modoDetalleEnum.nuevaVentana === modoDetalle){
		//si modo detalle en nueva ventana, ajustamos la pantalla para los paddings laterales
		ajustarPantallaDetalleExpedienteNuevaVentana();
	}
});