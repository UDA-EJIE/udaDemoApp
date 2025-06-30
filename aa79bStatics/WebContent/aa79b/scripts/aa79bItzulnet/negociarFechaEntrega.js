function mostrarFeedbackNegociar(){
	if($("#capaPestanaCompletaAlta").length){
		//pestanya datu orokorrak
		mostrarMensajeFeedback("datosgeneralesexpedienteform_feedback",$.rup.i18n.app.mensajes.fechasGuardadas , "ok");
	}else{
		//pestanya lan eta dokumentazio datuak
		mostrarMensajeFeedback("pestanyaDocPlanif_feedback",$.rup.i18n.app.mensajes.fechasGuardadas , "ok");
	}
}

jQuery(function($){
	$('#anyoExpediente_filter_table').val(anyoExpediente);
	$('#numExpediente_filter_table').val(idExpediente);
	
	$('#negociarFechaEntrega_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	jQuery('#fechaEntregaNueva').rup_date({		
		labelMaskId : "fecha-mask",
		minDate: new Date()
	});	
	
	jQuery('#fechaLimite').rup_date({		
		labelMaskId : "fecha-mask",
		minDate: new Date()
	});	
	
	$("#negociarFechaEntregaform").rup_form({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/negociarFechaEntrega/guardar",
		dataType: "json",
		type: "POST",
		success: function(){
			bitacoraUpdate(false);
			volverADetalleExpedienteDesdeAccionesPlanif('divNegociarFechaEntrega');
			bitacoraUpdate(true);
			llamadasFinalizadas("mostrarFeedbackNegociar");
			desbloquearPantalla();
		}		
	});
	 
	$.validator.messages.horaFechaHastaMayor = $.rup.i18nParse($.rup.i18n.app,"mensajes.horaEntregaMayorLimite");
	$.validator.messages.fechaHastaMayor = $.rup.i18nParse($.rup.i18n.app,"mensajes.fechaEntregaMayorLimite");
	
	$("#negociarFechaEntregaform").rup_validate({
		feedback: $('#negociarFechaEntrega_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"fechaEntrega": {required: true, date: true, fechaHastaMayor:"fechaLimite"},
			"horaEntrega": {required: true, hora: true, maxlength: 5, horaFechaHastaMayor: ["fechaLimite","fechaEntrega","horaLimite"] },
			"fechaLimite": {required: true, date: true, fechaMayorNow: true},
			"horaLimite": {required: true, hora: true, maxlength: 5, horaFechaMayorNow: ['fechaLimite'], },
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false
	});
	
	$("#negociarFechaEntrega_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();	
	                    volverADetalleExpedienteDesdeAccionesPlanif('divNegociarFechaEntrega');
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(e){
			    	e.preventDefault();
			    	e.stopImmediatePropagation();
			    	if($("#negociarFechaEntregaform").valid()){
			    		bloquearPantalla();
			    		$("#negociarFechaEntregaform").submit();
			    	}
			    }
			}
		]
	});
});