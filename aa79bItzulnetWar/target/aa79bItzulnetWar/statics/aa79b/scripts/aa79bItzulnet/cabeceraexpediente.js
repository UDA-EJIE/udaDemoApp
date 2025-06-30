function cabeceraUpdate(data) {
	$("#cabecera_numExpediente").text(data.anyoNumExpConcatenado);
	$("#cabecera_tituloExpediente").text(data.titulo);
	$("#cabecera_tipoExpediente").text($.rup.i18nParse($.rup.i18n.app,"tipo_expediente."+data.idTipoExpediente));
	$("#cabecera_solicitante").text(data.gestorExpediente.entidad.descAmpEu);
	$("#cabecera_contacto").text(data.gestorExpediente.solicitante.nombreCompleto);
	if(!data.gestorExpediente.solicitante.solicitanteVinculado){
		$("#cabecera_contacto").css("text-decoration","line-through");
	}
	
	if(data.gestorExpediente.solicitante.gestorExpedientesVIP === 'S'){
		$("#cabecera_esVip").removeClass("collapse");		
	}else{
		$("#cabecera_esVip").addClass("collapse");
	}
}

jQuery(function($) {
	$("#cabecera_esVip").addClass("collapse");
});

	
	/*SUB STICKY HEADER*/
	$(document).scroll(function(){
		var substicky = $('#subcabecera'),
		scroll = $(window).scrollTop();
		if (!$('#detalleExpediente').hasClass( "collapsed" )){ //Si el detalle no esta oculto
			if (scroll >= 79) {
				substicky.addClass('substicky');
				$("#bitacora_expediente").addClass('subcabeceraspace');
		
			}
			else {substicky.removeClass('substicky');
			$("#bitacora_expediente").removeClass('subcabeceraspace');}
		} 
		
		
	  });
	
		/*END SUB STICKY HEADER*/