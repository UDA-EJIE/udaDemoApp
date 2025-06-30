function obtenerDatosCorreccionesProv() {
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app, "comun.cargando"));
	
	$.ajax({
		type : 'GET',
		url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findDatosCorreccionesProv/' + idTarea,
		dataType : 'json',
		contentType : 'application/json',
		cache : false,
		success : function(data) {

			if (data !== null) {
				
				var linkDoc =	'<div id="capaDocCorreccionesProv" class="form-group no-padding col-xs-12">';	
				if (data.documentoTarea !== null && data.documentoTarea.documentoAdjunto !== null && data.documentoTarea.documentoAdjunto.idFichero != null){
					linkDoc+= '<a href="#" onclick="descargarDocumentoGeneral('+ data.documentoTarea.documentoAdjunto.idFichero +')" class="flotaIzda" >';
					linkDoc+= data.documentoTarea.documentoAdjunto.nombre;
					linkDoc+= ' <span class="ico-ficha-encriptado" title="'+ ((data.documentoTarea.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((data.documentoTarea.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
				}
				linkDoc+='</div>';
				
				$('#enlaceDescargaDetalle_0').append(linkDoc);

				$('#obsCorreccionProv_detail_table').val(data.observaciones);
				
			}

			desbloquearPantalla();
		},
		error : function() {
			desbloquearPantalla();
		}
	});
}

function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}

function comprobarCambiosFormulario(){
	return true;
}

jQuery(function($) {
	
	// Ejemplo de toolbar: volver y finalizar tarea
	$("#notificarCorreccionProv_detail_toolbar").empty();
	$("#notificarCorreccionProv_detail_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			id : "volver",
			css : "fa fa-arrow-left",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				$("#ejecutarTareaDialog").rup_dialog("close");
			}
		} ]
	});

	$('#notificarCorreccionProv_detail_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	obtenerDatosCorreccionesProv();
});
