
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
jQuery(function($){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente
		};
		
	jQuery.ajax({
				type : "POST",
				url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/bitacoraexpediente/getCabeceraBitacora",
				dataType : "json",
				contentType : 'application/json',
				data : $.toJSON(jsonObj),
				cache : false,
				success : function(data) {
	
					//Se setean los datos de la cabecera
					cabeceraUpdate(data);
				}
	});
});