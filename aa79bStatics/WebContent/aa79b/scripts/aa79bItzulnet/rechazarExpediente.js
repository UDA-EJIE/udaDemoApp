
//function volverADetalleExpediente(){
//	$("#divRechazarExpedientes").detach();
//	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
//	$("#divDetalleExpediente").html(capaDetalleExpediente);
//}

function creaComboMotivosRechazo(){
	if ($('#motivoRechazo-menu').length){
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
			jQuery('#motivoRechazo-menu').width(jQuery('#motivoRechazo-button').innerWidth());
		}
	});
}

function rechExpediente(){	
	
	var motivoRechazo = $('#motivoRechazo').rup_combo("getRupValue");
	var motivoRechazoDesc = $('#motivoRechazo').rup_combo("label");
	var descRechazo = $('#descRechazo').val();
	
	var Expediente = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente
    };
	var ObservacionRechazo = 
    {
        "obsvRechazo068": descRechazo
    };
	var RechazoExpediente = 
    {
        "idMotivoRechazo": motivoRechazo,
        "motivoRechazoDesc": motivoRechazoDesc,
        "observacionesRechazo": ObservacionRechazo
    };
	
    jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/rechazarExp/P",
		dataType: "json",
		contentType: 'application/json', 
		data: jQuery.toJSON({
	   		"expediente":Expediente,
	   		"rechazoExp":RechazoExpediente,
	   	}),
		cache: false,
		success: function (data) {
			$("#divPlanificacionCapa").detach();
			$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
			if (typeof(desdeConsultaExpedientes) != "undefined"){
				//volver a consulta de expedientes
 				$("#divPlanificacionCapa").html(capaConsultaExp);
 				$("#consultaExpedientes").rup_table("filter");
 				if (data === -1){
 					$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.faltaDirEmailRechExp, "ok");
 				} else if (data === -2){
 					$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.errorEnvioEmail, "ok");
 				} else {
 					$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado, "ok");
 				}
 			}else if(typeof(desdeConsultaPlanifExpedientes) != "undefined"){
 				//volver a consulta planificacion de expedientes
 				$("#divConsultaPlanificacionExp").html(capaPestGeneralConsultaPlanif);
 				$("#consultaPlanificacionExp").rup_table("filter");
 				if (data === -1){
 					$('#consultaPlanificacionExp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.faltaDirEmailRechExp, "ok");
 				} else if (data === -2){
 					$('#consultaPlanificacionExp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.errorEnvioEmail, "ok");
 				} else {
 					$('#consultaPlanificacionExp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado, "ok");
 				}
 			}else{
 				//volver a planificacion
 				$("#divPlanificacionCapa").html(capaPestGeneral);
 				clean();
 				if(pestDatos){
 					$("#busquedaDatos").rup_table("filter");
 				} else {
 					$("#busquedaGeneral").rup_table("filter");
 				}
 				
 				if (data === -1){
 					$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.faltaDirEmailRechExp, "ok");
 				} else if (data === -2){
 					$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.errorEnvioEmail, "ok");
 				} else {
 					$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado, "ok");
 				}
 			}
			
	     },
	     error: function (){
	    	 $('#rechazarExpedientes_feedback').rup_feedback("set", "ErrorAlGuardar", "error");
	     }
    });
	
}

jQuery(function($){
	bitacoraUpdate(false);
	$('#rechazarExpedientes_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	 
	creaComboMotivosRechazo();

	$("#rechazarExpform").rup_validate({
		feedback: $('#rechazarExpedientes_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"motivoRechazo": {required: true},
			"descRechazo": {validateMotivoRech: true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false
	});

	jQuery.validator.addMethod("validateMotivoRech", function(valor) {
		
		var motivoRechazo = $('#motivoRechazo').val();
		var descRechazo = $('#descRechazo').val();
		
		if(motivoRechazo === "1" && descRechazo === ""){
			return false;
		}else{
			return true;
		}
	},$.rup.i18n.app.mensajes.errorDescVacio);
	
	$("#rechazarExpedientes_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();	
//	                    volverADetalleExpediente();
	                    volverADetalleExpedienteDesdeAccionesPlanif('divRechazarExpedientes');
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(e){
			    	e.stopImmediatePropagation();
			    	if($("#rechazarExpform").valid()){
				    	$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.app,"rup_table.rechExp"),
							OKFunction: function(){
								rechExpediente();
							}
						});
			    	}
			    }
			}
		]
	});
});