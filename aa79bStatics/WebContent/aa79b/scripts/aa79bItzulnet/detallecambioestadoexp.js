var initFormCambioEstado = '';
var mostrarMsgRechExp = false;

/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function volverABusqCambioEstExp(mostrarMsgOk){
	volverABusquedaCambioEstado();
	
	expedientesSeleccionados = [];
	$("#busquedaGeneral").trigger('reloadGrid');
	
	if (mostrarMsgOk){
		$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
	}
}

function volverABusquedaCambioEstado(){
	$("#divDetalleCambioEstExp").detach();
	$("#divBusquedaGeneral").append("<div id='divBusquedaGeneralCapa'></div>");
	$("#divBusquedaGeneralCapa").html(capaPestGeneral);
}

/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function fncEstadosCombo(){
	if ( $("#estadoExp_filter_table-menu").length ){
		$("#estadoExp_filter_table-menu").remove();
	}
	$("#estadoExp_filter_table").rup_combo({
		 source : "/aa79bItzulnetWar/estadosexpediente/findAllEstadosExpWithFase/"+estadoExp+"/"+faseExp+"/"+tipoExp,
		 sourceParam : {
			 value: "id",
			 label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
		 }
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,orderedByValue: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		},onLoadSuccess: function(){
			var id = $(this).attr("id");
			$("#"+id).rup_combo("select",0);
			fncFasesCombo();
			$("#"+id).rup_combo("setRupValue","");
		}
		,select: function(){
			$("#divDatosAnulacionExp").hide();
			$("#divDatosRechazoExp").hide();
			mostrarMsgRechExp = false;
			eliminarValidacionesCamposAnulacion();
			eliminarValidacionesCamposRechExp();
		}
	});
}

function fncFasesCombo(){
	if ( $("#faseExp_filter_table-menu").length ){
		$("#faseExp_filter_table-menu").remove();
	}
	$("#faseExp_filter_table").rup_combo({
		parent: ["estadoExp_filter_table"],
		source: "/aa79bItzulnetWar/fasesexpediente/remoteCambioEstadoExp/"+tipoExp,
		sourceParam : {
			 value: "id",
			 label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,select: function(){
			if ($("#estadoExp_filter_table").rup_combo("getRupValue") === datosExp.estadoExp.anulado 
					&& $(this).rup_combo("getRupValue") === datosExp.faseExp.anulado){
				$("#divDatosAnulacionExp").show();
				$("#divDatosRechazoExp").hide();
				mostrarMsgRechExp = false;
				validarCamposAnulacion();
				eliminarValidacionesCamposRechExp();
			} else if ($("#estadoExp_filter_table").rup_combo("getRupValue") === datosExp.estadoExp.rechazado 
					&& $(this).rup_combo("getRupValue") === datosExp.faseExp.rechazado){
				$("#divDatosAnulacionExp").hide();
				$("#divDatosRechazoExp").show();
				mostrarMsgRechExp = true;
				eliminarValidacionesCamposAnulacion();
				validarCamposRechExp();
			} else {
				$("#divDatosAnulacionExp").hide();
				$("#divDatosRechazoExp").hide();
				mostrarMsgRechExp = false;
				eliminarValidacionesCamposAnulacion();
				eliminarValidacionesCamposRechExp();
			}
		}
	});
}

function creaComboMotivosRechazo(){
	if ( $("#motivoRechazo-menu").length ){
		$("#motivoRechazo-menu").remove();
	}
	$("#motivoRechazo").rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/motivosrechazo/mostrarMotivosRechazo",
		sourceParam:{
			value : "id013",
			label : $.rup.lang === 'es' ? "descEs013" : "descEu013"
		}
		,blank: ""
		,width: "100%"
		,rowStriping: true
		,open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,change: function(){					
			fnCambioMotivoRechazo($(this).attr("id"));
		}
	});
}

function fnCambioMotivoRechazo(id){
	if (datosRechazoExp.motivosRechazo.otros === $("#"+id).rup_combo("getRupValue")){
		validarCamposRechazoExp();
	} else {
		$('#detalleCambioEstExp_feedback').rup_feedback('close');
		eliminarMensajesValidacion();
		eliminarValidacionesCamposRechazoExp();
	}
}

function validarCamposRechazoExp(){
	$("#descRechazo").rules("add", "required");
}

function eliminarValidacionesCamposRechazoExp(){
	$("#descRechazo").rules("remove", "required");
}

function validarCamposRechExp(){
	$("#motivoRechazo").rules("add", "required");
}

function eliminarValidacionesCamposRechExp(){
	$("#motivoRechazo").rules("remove", "required");
}

function fnComboMotivosAnulacion(id){
	if ($("#"+id+"-menu").length){
		$("#"+id+"-menu").remove();
	}
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/administracion/datosmaestros/motivosanulacion/findMotivosAnulacionAlta",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descEs012"
					: "descEu012",
			value: "id012"
		}
		,blank: ""
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,change: function(){					
			fnCambioMotivoAnulacion($(this).attr("id"));
		}
	});
}

function fnCambioMotivoAnulacion(id){
	if (datosAnulacionExp.motivosAnulacion.otros === $("#"+id).rup_combo("getRupValue")){
		validarCampos();
	} else {
		$('#detalleCambioEstExp_feedback').rup_feedback('close');
		eliminarMensajesValidacion();
		eliminarValidacionesCampos();
	}
}

function validarCampos(){
	$("#observaciones_detail_table").rules("add", "required");
}

function eliminarValidacionesCampos(){
	$("#observaciones_detail_table").rules("remove", "required");
}

function validarCamposAnulacion(){
	$("#idMotivosAnulacion_detail_table").rules("add", "required");
}

function eliminarValidacionesCamposAnulacion(){
	$("#idMotivosAnulacion_detail_table").rules("remove", "required");
}

function cambiarEstadoExp(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	$('#detalleCambioEstExp_feedback').rup_feedback('close');
	
	var EstadosExpediente = {
		"id": $("#estadoExp_filter_table").val()
	};
	
	var FasesExpediente = {
		"id": $("#faseExp_filter_table").val()
	};
	
	var BitacoraExpediente = {
		"estadoExp": EstadosExpediente,
		"faseExp": FasesExpediente
	};
	
	var Expediente = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente,
        "bitacoraExpediente": BitacoraExpediente
    };
	
	// Anulación del expediente
	var observacionesAnulExp = {
		"obsvAnulacion": $("#observaciones_detail_table").val()
	};
	
	var anulacionExpediente = 
    {
        "idMotivoAnulacion": $("#idMotivosAnulacion_detail_table").val(), 
        "descMotivoAnulacion": $("#idMotivosAnulacion_detail_table option:selected").text(),
        "observacionesAnulExp": observacionesAnulExp
    };
	
	// Rechazo del expediente
	var ObservacionRechazo = 
    {
        "obsvRechazo068": $('#descRechazo').val()
    };
	var RechazoExpediente = 
    {
        "idMotivoRechazo": $('#motivoRechazo').rup_combo("getRupValue"),
        "motivoRechazoDesc": $('#motivoRechazo').rup_combo("label"),
        "observacionesRechazo": ObservacionRechazo
    };
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/servicios/cambioEstadoExpediente/cambiarEstadoExp'
        ,dataType: 'json'
        ,contentType: 'application/json'
        ,data: $.toJSON({
	   		"anulacionExpediente" : anulacionExpediente,
	   		"rechazoExp":RechazoExpediente,
	   		"expediente":Expediente
	   	})
	   	,cache: false 
        ,success:function(){
        	volverABusqCambioEstExp(true);
	        desbloquearPantalla();
        }
		,error: function(error){
			$('#detalleCambioEstExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});

}

function obtenerDatosLeyenda(idEstadoExp,idFaseExp,descFase){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	$('#detalleCambioEstExp_feedback').rup_feedback('close');
	
	$.ajax({
        type: 'GET' 
        ,url: '/aa79bItzulnetWar/servicios/cambioEstadoExpediente/findConfLeyenda/'+idEstadoExp+'/'+idFaseExp
        ,dataType: 'json'
        ,contentType: 'application/json'
	   	,cache: false 
        ,success:function(data){
        	loadLeyenda(data,descFase);
	        desbloquearPantalla();
        }
		,error: function(error){
			$('#detalleCambioEstExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
}

function loadLeyenda(data,descFase){
	
	$("#divConfLeyenda").remove();
	
	if (data !== null
			&& data.length > 0) {
		var item = '<div id="divConfLeyenda"> \
			  <div class="triangulo_sup"></div> \
			  <div class="bitacora-modal"> \
			    <div class="modal-header"> \
			      <h2>'+ descFase + '</H2> \
			      <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">+</a> \
			    </div> \
			    <div class="tabla-mods">';
		for (var i = 0; i < data.length; i++) {
			var leyenda = data[i];
			item += '<div class="row body-mods rowLeyenda">';
			
			if (leyenda.nivel === 2){
				item += '<div class="col-md-11 leyenda-nivel2">';
			} else if (leyenda.nivel === 3){
				item += '<div class="col-md-10 leyenda-nivel3">';
			} else {
				item += '<div class="col-md-12 negrita leyenda-nivel1">';
			}
			
			item += leyenda.texto +'</div> \
		      </div>';
		}	
		
		item += '</div> \
		  </div> \
			<div class="ui-widget-overlay ui-front" style="z-index: 100;"></div> \
		</div>';
		
		var itemDiv = $(item);
		itemDiv.prependTo("#divDetalleCambioEstExp");
	}
	$(".close-button").each(function() {
		$(this).on("click", function() {
			$("#divConfLeyenda").remove();
		});
	});
	
}

function serializeForm(){
	initFormCambioEstado = $("#detalleCambioEstExp_filter_form").rup_form("formSerialize");
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
		
	$('#detalleCambioEstExp_feedback').rup_feedback({
		block : false
	});
	
	$("#detalleCambioEstExp_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverABusqCambioEstExp"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if (initFormCambioEstado !== $("#detalleCambioEstExp_filter_form").rup_form("formSerialize")){
						$.rup_messages("msgConfirm", {
	         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	         				OKFunction: function(){
	         					volverABusqCambioEstExp(false);
	         				}
	         			});
					} else {
						volverABusqCambioEstExp(false);
					}
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,id: "detalleCambioEstExp"	
				,css: "fa fa-floppy-o"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if ($("#detalleCambioEstExp_filter_form").valid()){
						
							$.rup_messages("msgConfirm", {
								title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
								message: $.rup.i18nParse($.rup.i18n.app,"mensajes.cambioEstadoExpediente"),
								OKFunction: function(){
									cambiarEstadoExp();
								}
							});
						
						
					}
				}
			}
		]
	});
	
	fncEstadosCombo();
	creaComboMotivosRechazo();
	fnComboMotivosAnulacion('idMotivosAnulacion_detail_table');
	
	$("#divDatosAnulacionExp").hide();
	$("#divDatosRechazoExp").hide();
	
	$("#detalleCambioEstExp_filter_form").rup_validate({
		feedback: $('#detalleCambioEstExp_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"bitacoraExpediente.estadoExp.id": { required: true },
			"bitacoraExpediente.faseExp.id": { required: true, validateFaseExp : true },
			"observacionesAnulExp.obsvAnulacion": { maxlength: 4000 },
			"descRechazo": { maxlength: 4000 }
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	$('a.confLeyenda').click(function(event){
		event.preventDefault();	
		var id =  $(this).data("id");
		var estadoFase = id.split('_');
		var idEstadoExp = estadoFase[0];
		var idFaseExp = estadoFase[1];
		obtenerDatosLeyenda(idEstadoExp,idFaseExp,$('#descFase'+id).val());
	});
	
	// Validacion de la fase del expediente
	jQuery.validator
			.addMethod(
					"validateFaseExp",
					function(value, element, params) {

						var faseExpValida = true;
						
						if ($("#estadoExp_filter_table").rup_combo("getRupValue") === datosExp.estadoExp.cerrado 
								&& value === datosExp.faseExp.pdteRevFacturacion){
							
							$.ajax({
								type : "GET",
								url : "/aa79bItzulnetWar/servicios/cambioEstadoExpediente/comprobarExpFacturado/"+anyoExpediente+'/'+idExpediente,
								dataType : 'json',
								contentType : 'application/json',
								async : false,
								cache : false,
								success : function(data) {
									if (data !== 0) {
										faseExpValida = false;
										$.validator.messages.validateFaseExp = $.rup.i18nParse($.rup.i18n.app,"validaciones.expFacturado");
									} else {
										$('#faseExp_filter_table').removeClass('error');
										$('#faseExp_filter_table-error').remove();
									}
								}
							});
							
							return faseExpValida;
							
						} else if ($("#estadoExp_filter_table").rup_combo("getRupValue") === datosExp.estadoExp.anulado 
								&& value === datosExp.faseExp.anulado){
							
							$.ajax({
								type : "GET",
								url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findAllCountSinEjecutar/"+anyoExpediente+'/'+idExpediente,
								dataType : 'json',
								contentType : 'application/json',
								async : false,
								cache : false,
								success : function(data) {
									if (data !== 0) {
										faseExpValida = false;
										$.validator.messages.validateFaseExp = $.rup.i18nParse($.rup.i18n.app,"validaciones.tareasFinalizadas");
									} else {
										$('#faseExp_filter_table').removeClass('error');
										$('#faseExp_filter_table-error').remove();
									}
								}
							});
							
							return faseExpValida;
							
						} else {
							$('#faseExp_filter_table').removeClass('error');
							$('#faseExp_filter_table-error').remove();
							return faseExpValida;
						}

					}, $.validator.messages.validateFaseExp);
	
	llamadasFinalizadas("serializeForm");
	
});