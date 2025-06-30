function mostrarFeedbackModificar(){
	//pestanya lan eta dokumentazio datuak
	mostrarMensajeFeedback("pestanyaDocPlanif_feedback",$.rup.i18n.app.mensajes.fechasEntregaModificada , "ok");
}

jQuery(function($){
	$('#anyoExpediente_filter_table').val(anyoExpediente);
	$('#numExpediente_filter_table').val(idExpediente);
	
	$('#modificarFechaEntrega_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	jQuery('#fechaEntregaNueva').rup_date({		
		labelMaskId : "fecha-mask",
		minDate: new Date()
	});	
	
	
	$("#modificarFechaEntregaform").rup_form({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/modificarFechaEntrega/guardar",
		dataType: "json",
		type: "POST",
		success: function(){
			bitacoraUpdate(false);
			volverADetalleExpedienteDesdeAccionesPlanif('divModificarFechaEntrega');
			bitacoraUpdate(true);
			llamadasFinalizadas("mostrarFeedbackModificar");
			desbloquearPantalla();
		}		
	});
	 
	$("#modificarFechaEntregaform").rup_validate({
		feedback: $('#modificarFechaEntrega_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"fechaEntrega": {required: true, date: true},
			"horaEntrega": {required: true, hora: true, maxlength: 5}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false
	});
	
	$("#modificarFechaEntrega_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();	
	                    volverADetalleExpedienteDesdeAccionesPlanif('divModificarFechaEntrega');
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(e){
			    	e.preventDefault();
			    	e.stopImmediatePropagation();
			    	if($("#modificarFechaEntregaform").valid()){
			    		bloquearPantalla();
			    		$("#modificarFechaEntregaform").submit();
			    	}
			    }
			}
		]
	});
});