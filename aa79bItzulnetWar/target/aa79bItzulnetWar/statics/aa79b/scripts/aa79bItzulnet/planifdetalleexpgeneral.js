nora_response();
var esTecnico = "";
var esSubsanado = "";

var esPresupuestoVisibleParaUsuario = false;
var esTradosConRequerimiento = false;
var tieneTareasEjecutadas = false;
var estado;
var fechaIniExpediente;
var fechaFinExpediente;
var numPalIZO_filter;
var trConcor084_filter;
var trConcor8594_filter;
var trConcor9599_filter;
var trConcor100_filter;
var grTrabajo;
/* ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function ocultarMenuDesplegableBotonOtrosPlanif(){
	$("[id='detalleExpedientePlanificacion_toolbar##otrosToolbarExpediente-mbutton-container']").hide();
}

function irATareasExpedientes(){
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/expedientetareas/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaDetalleExpediente = $('#divDetalleExpediente').detach();
	   		$("#divCapaDetalle").append(data);
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
	
}

function mostrarRechazarExp(){
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/rechazarExpediente/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaDetalleExpediente = $('#divDetalleExpediente').detach();
	   		$("#divCapaDetalle").append(data);
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

function mostrarNegociarFechaEntrega(){
	$.rup_ajax({
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/negociarFechaEntrega/maint/' + anyoExpediente + '/' + idExpediente  
			,success:function(data, textStatus, jqXHR){
				capaDetalleExpediente = $('#divDetalleExpediente').detach();
				$("#divCapaDetalle").append(data);
			},
			error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
			}
	});
}

function mostrarExpRelacionados(){
	
	$.rup_ajax({
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/expedientesrelacionadosplanif/maint' 
			,success:function(data, textStatus, jqXHR){
		   		capaDetalleExpediente = $('#divDetalleExpediente').detach();
		   		$("#divCapaDetalle").append(data);
		   	},
			error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
			}
	});
}

function mostrarReceptoresAutorizados(){
	$.rup_ajax({
		async: true, cache: false,
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/receptoresautorizadosplanif/maint' 
			,success:function(data, textStatus, jqXHR){
		   		capaDetalleExpediente = $('#divDetalleExpediente').detach();
		   		$("#divCapaDetalle").append(data);
		   	},
			error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
			}
	});
}

function mostrarContactoFacturacion(){
	bloquearPantalla();
//	eliminarDialogs();
	$.rup_ajax({
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/contactoFacturacion/maint' 
			,success:function(data, textStatus, jqXHR){
		   		capaDetalleExpediente = $('#divDetalleExpediente').detach();
		   		$("#divCapaDetalle").append(data);
		   		desbloquearPantalla();
		   	 },
			error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
			}
	});
}

function mostrarCategorizacionExpediente(){
	$.rup_ajax({
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/categorizacionExpediente/maint' 
			,success:function(data, textStatus, jqXHR){
				ocultarMenuDesplegableBotonOtrosPlanif();
		   		capaDetalleExpediente = $('#divDetalleExpediente').detach();
		   		$("#divCapaDetalle").append(data);
			},
			error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
			}
	});
}

function funcionVolverCondicional(){
	//por si venimos de consulta de expedientes para volver al buscador del mismo
	if (typeof(desdeConsultaPlanifExpedientes) !== "undefined" && desdeConsultaPlanifExpedientes){
			volverACapaGeneralConsultaPlanifExp();
	}else if (typeof(desdeConsultaExpedientes) != "undefined"){
		volverACapaGeneralConsulta();
	}else{
		if(typeof(capaDesgloseTareas) != "undefined" && capaDesgloseTareas !== ""){
			volverACapaDesgloseTareas();
		}else if(typeof(capaDesgloseTareasConsultaPlanif) != "undefined" && capaDesgloseTareasConsultaPlanif !== ""){
			volverADesgloseTareasConsultaPlanif();						
		} else {
			volverACapaGeneral();				
		}
	}
}

function volverADetalleExpedienteDesdeAccionesPlanif( capaDetach ){
	bloquearPantalla();
	$("#"+capaDetach).detach();
	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
	$("#divDetalleExpediente").html(capaDetalleExpediente);
	//refresco la bitácora
	eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby='buscadorEtiquetasMasivo'],[aria-describedby='buscadorEtiquetasExpediente'],[aria-describedby='buscadorEtiquetasPlanificacion'],[aria-describedby='buscadorEtiquetasConsulta'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
	if ($('#capaPestanaCompletaAlta').length){
		//está activa la pestaña de datos generales
		$("#tabsExpediente").rup_tabs("loadTab",{
			idTab: "tabsExpediente",
			position: 0
		});
	}else{
		//está activa la pestaña de documentación
		$("#tabsExpediente").rup_tabs("loadTab",{
			idTab: "tabsExpediente",
			position: 1
		});
		
	}
	
}


function comprobarCambiosToolbar( laFuncionCallback ){
	if (!omitirComprobacionFormularios && comprobarFormularios && !comprobarFormulariosPestanas()){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
			OKFunction: function(){
				laFuncionCallback();
			}
		});  
	} else {
		laFuncionCallback();
	}
}

function comprobarEstadoFase(funcionCallback, titulo){
	$.rup_ajax({
		 type: "POST",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/negociarFechaEntrega/comprobarEstadoFase/' + anyoExpediente + '/' + idExpediente,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	 
			 if(data){
					
				$.rup_messages("msgAlert", {
					title: titulo,
					message: $.rup.i18n.app.mensajes.expPendienteTramitacionCliente
				});	
			} else {
					
				comprobarCambiosToolbar(funcionCallback);
			}
		 }
	 });
}

//function comprobarEstadoEnCurso(){
//	$.rup_ajax({
//		type: "POST",
//		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/negociarFechaEntrega/comprobarEstadoFase/' + anyoExpediente + '/' + idExpediente,
//		dataType: "json",
//		contentType: "application/json",
//		cache: false,	
//		success: function(data){	 
//			if(data){
//				
//				$.rup_messages("msgAlert", {
//					title: $.rup.i18n.app.boton.negociarNuevaFechaEntrega,
//					message: $.rup.i18n.app.mensajes.expPendienteTramitacionCliente
//				});	
//			} else {
//				
//				comprobarCambiosToolbar(mostrarNegociarFechaEntrega);
//			}
//		}
//	});
//}
/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

/*
 * ****************************
 * NORA - INICIO
 * ****************************
 */

function buscarDireccion(){
	var tipo=jQuery('#tipoNora').val();
	var nora = {t17_nora_i18n: $.rup.lang,
			t17_nora_tipo:3,
			title: $.rup.i18nParse($.rup.i18n.app,"label.searchAddress"),
			url: SERVIDOR_NORA +"/sidl/sidljQuery/dynamic_modal_form3.html"};
	if(tipo==0){
		jQuery.extend(nora, {
			t17_nora_portal_id: jQuery("#portalId").val(),
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	} else if(tipo==1 || tipo==2){
		jQuery.extend(nora, {
			t17_nora_localidad_id: jQuery("#localidadId").val(),
			t17_nora_calle: jQuery("#calle").val(),
			t17_nora_portal_numero: jQuery("#portal").val(),
			t17_nora_portal_cp: jQuery("#cp").val(),
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	} else if(tipo==3){
		jQuery.extend(nora, {
			t17_nora_pais_id: jQuery("#paisId").val(),
			t17_nora_provincia: jQuery("#provincia").val(),
			t17_nora_municipio: jQuery("#localidad").val(),
			t17_nora_calle: jQuery("#calle").val(),
			t17_nora_portal_cp: jQuery("#cp").val()});
	} else{
		jQuery.extend(nora, {
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	}
	openNoraModalDynamicForm(nora);
}

function confirm_t17i_form(O_O) {
	//Aquí se setearán los valores provenientes del formulario de NORA
	$('#tipoNora').val(O_O.tipo);
	limpiarCamposDireccion();

	var tipo=$('#tipoNora').val();
	if(tipo==3){
		// ES EXTRANJERO
		var txtPais = O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.descripcionOficial.toUpperCase();
		var txtProvinciaExt = O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase();
		var txtLocalidadExt = O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase();
		var txtCalleExt = O_O.portal.calle.descripcionOficial.toUpperCase();
		var txtCPExt = O_O.portal.codigoPostal.toUpperCase();
		
		// ES EXTRANJERO
		$('#pais').val(txtPais);
		$('#paisId').val(O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.id);
		$('#portalId').val('0');
		$('#provincia').val(txtProvinciaExt);
		$('#localidad').val(txtLocalidadExt);
		$('#calle').val(txtCalleExt);
		$('#cp').val(txtCPExt);
		
		var txtLugarInterpretacion = txtCalleExt + ", " + txtLocalidadExt + ", " + txtProvinciaExt + " " + txtCPExt + ". " + txtPais;
		$('#lugarInterpretacion').val(txtLugarInterpretacion);
		$('#lugarInterpretacion').change();
	} else {
		var txtCalle = "";
		var txtPortal = "";
		var txtEscalera = "";
		var txtPiso = "";
		var txtMano = "";
		var txtPuerta = "";
		var txtCodigoPostal = "";
		var txtLocalidad = "";
		var txtProvincia = "";
		// ES NACIONAL
		//el pais lo vamos a dejar vacio
		// CODIGO
		if (tipo==0) {
			$('#portalId').val(O_O.portal.id);
			$('#localidadId').val(O_O.portal.calle.localidad[0].id);
		} else {
			$('#localidadId').val(O_O.portal.calle.localidad[0].id);
		}
		// TH
		$('#provinciaId').val(O_O.portal.calle.localidad[0].municipio.provincia.id);
		txtProvincia = O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase();
		$('#provincia').val(txtProvincia);
		// MUN
		$('#municipioId').val(O_O.portal.calle.localidad[0].municipio.id);
		$('#municipio').val(O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase());
		// LOC
		txtLocalidad = O_O.portal.calle.localidad[0].descripcionOficial.toUpperCase();
		$('#localidad').val(txtLocalidad);
		// CALLE
		if (O_O.portal.calle.descripcionSecundaria == undefined || O_O.portal.calle.descripcionSecundaria == "" ){
			txtCalle = O_O.portal.calle.descripcionOficial.toUpperCase();
		} else {
			txtCalle = O_O.portal.calle.descripcionOficial.toUpperCase() + " | " + O_O.portal.calle.descripcionSecundaria.toUpperCase();
		}
		$('#calle').val(txtCalle);
		// PORTAL
		if (tipo == '0') {
			var numero = O_O.portal.numero.toUpperCase();
			var bis = O_O.portal.bis;
			var acepcion = O_O.portal.acepcion;
			var bloque = O_O.portal.bloque;
			
			var portal = numero;
			
			// BIS
			if (bis!=''){
				if (bis=='Y'){
					bis = 'BIS';
				}
				portal += " Bis: " + bis;
			}
			
			// BLOQUE
			if(bloque!=''){
				portal += " Bloque:" + bloque;
			}
			
			// ACEPCION
			if (acepcion!=''){
			    portal += " - " + acepcion;		        
			}
			
			txtPortal = portal;
		} else {
			txtPortal = O_O.portal.numero.toUpperCase();
		}
		$('#portal').val(txtPortal);
		// CP
		txtCodigoPostal = O_O.portal.codigoPostal.toUpperCase();
		$('#cp').val(txtCodigoPostal);
		// RESTO
		txtEscalera = O_O.escalera.toUpperCase();
		$('#escalera').val(txtEscalera);
		txtPiso = O_O.piso.toUpperCase();
		$('#piso').val(txtPiso);
		txtMano = O_O.mano.toUpperCase();
		$('#mano').val(txtMano);
		txtPuerta = O_O.puerta.toUpperCase();
		$('#puerta').val(txtPuerta);
		$('#aproxPostal').val(O_O.aprox_postal.toUpperCase());
		
		var txtLugarInterpretacion = txtCalle + ", " + txtPortal + ", " + txtEscalera + " " + txtPiso + " " + txtMano + " " + txtPuerta + ", " + txtCodigoPostal + " " + txtLocalidad + ", " + txtProvincia;
		$('#lugarInterpretacion').val(txtLugarInterpretacion);
		$('#lugarInterpretacion').change();
	}
}
	
function limpiarCamposDireccion(){
	jQuery('#escalera').val("");
	jQuery('#piso').val("");
	jQuery('#mano').val("");
	jQuery('#puerta').val("");
	jQuery('#portal').val("");
	jQuery('#portalId').val("");
	jQuery('#aproxPostal').val("");
	jQuery('#localidad').val("");
	jQuery('#localidadId').val("");
	jQuery('#municipio').val("");
	jQuery('#municipioId').val("");
	jQuery('#pais').val("");
	jQuery('#paisId').val("");
	jQuery('#provinciaId').val("");
	jQuery('#provincia').val("");
	jQuery('#calle').val("");
	jQuery('#calleId').val("");
	jQuery('#cp').val("");	
}



/*
 * ****************************
 * NORA - FIN
 * ****************************
 */


jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	
	/*****************************
	 * CREACION DE COMPONENTES 
	 * ***************************/
	
	
	
	$("#detalleExpedientePlanificacion_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverATareas"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
		 			e.stopImmediatePropagation();
		 			comprobarCambiosToolbar(funcionVolverCondicional);
				}
			}
			,{
				id: "rechazar",
				i18nCaption: $.rup.i18n.app.boton.rechazarExp
                ,css: "fa fa-times-circle"
                ,click : 
                function(e){
                	e.preventDefault();
		 			e.stopImmediatePropagation();
		 			comprobarCambiosToolbar(mostrarRechazarExp);
                }
			}
			,{
				id: "fechaHoraEntrega",
				i18nCaption: $.rup.i18n.app.boton.negociarNuevaFechaEntrega
				,css: "fa fa-calendar"
					,click : 
						function(e){
						e.preventDefault();
						e.stopImmediatePropagation();
						comprobarEstadoFase(mostrarNegociarFechaEntrega, $.rup.i18n.app.boton.negociarNuevaFechaEntrega);
					}
			}
			,{
                i18nCaption: $.rup.i18n.app.boton.configTarea
                ,css: "fa fa-list-ul"
                ,click : 
                function(e){
                	e.preventDefault();
		 			e.stopImmediatePropagation();
		 			comprobarCambiosToolbar(irATareasExpedientes);
		 			
                }
			}
			,{
				id : "otrosToolbarExpediente",
				i18nCaption : $.rup.i18n.app.boton.otros,
				click: function(event){
					$("[id='detalleExpedientePlanificacion_toolbar##otrosToolbarExpediente-mbutton-container']").show();
				},
                buttons : [{
                    i18nCaption: $.rup.i18n.app.boton.expedientesRelacionados
                    ,css: "fa fa-link"
                    ,click : function(e){
                    	e.preventDefault();
    		 			e.stopImmediatePropagation();
    		 			comprobarCambiosToolbar(mostrarExpRelacionados);
                    }
                }
                ,{
	                i18nCaption: $.rup.i18n.app.boton.receptoresAutorizados
	                ,css: "fa fa-users"
	                ,click : function(e){
	                	e.preventDefault();
    		 			e.stopImmediatePropagation();
    		 			comprobarCambiosToolbar(mostrarReceptoresAutorizados);
                	}
		        }
		        ,{
	                i18nCaption: $.rup.i18n.app.boton.contactoFacturacionExpediente
	                ,css: "fa fa-ticket"
	                ,click : function(e){
	                	e.preventDefault();
	                	e.stopImmediatePropagation();
	                	comprobarCambiosToolbar(mostrarContactoFacturacion);
	                }
		        }
		        ,{
	                i18nCaption: $.rup.i18n.app.boton.categorizacionExpediente
	                ,css: "fa fa-tags"
                    ,click : function(e){
                    	e.preventDefault();
	                	e.stopImmediatePropagation();
	                	comprobarCambiosToolbar(mostrarCategorizacionExpediente);
                    }
		        }
			]}
		]
	});
	
	$("[id='detalleExpedientePlanificacion_toolbar##otrosToolbarExpediente-mbutton-container']").addClass('centrado');
	
	$("#tabsExpediente").rup_tabs({
		tabs : [
			{i18nCaption: "pestdatosg", url:"/aa79bItzulnetWar/datosgeneralesexpediente/planificacion/datosexpedienteview?"+$.now()},
			{i18nCaption: "pestdocumentos", url:"/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/planificacion/listado/"+anyoExpediente+"/"+idExpediente+"/"+origen+"?"+$.now()}
		]
	,cache: false
	,select: function(e){
		eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
		if (!omitirComprobacionFormularios && comprobarFormularios && !comprobarFormulariosPestanas()){
			e.preventDefault();
            e.stopImmediatePropagation();
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					if ($('#capaPestanaCompletaAlta').length > 0){
						datosFormulario = $("#datosgeneralesexpedienteform").serialize();
						$("#tabsExpediente").rup_tabs("selectTab",{
							idTab: "tabsExpediente",
							position: 1
						});
						
					} else {
						datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
						$("#tabsExpediente").rup_tabs("selectTab",{
							idTab: "tabsExpediente",
							position: 0
						});
					}
				}
			});
		} else {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			omitirComprobacionFormularios = false;
		}
	}
//	,load: function(){
//		desbloquearPantalla();
//	}
	,fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
		  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		 ] 
	});

	/*****************************
	 * FIN CREACION DE COMPONENTES 
	 * ***************************/
	
});