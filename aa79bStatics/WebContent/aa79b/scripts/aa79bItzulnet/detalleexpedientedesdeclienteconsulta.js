/*
 * ****************************
 * FEEDBACK - INICIO
 * ****************************
 */
function crearFeedbackDetalleExpedienteDesdeClienteConsulta(){
	$('#detalleExpedienteDesdeClienteConsulta_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
}
/*
 * ****************************
 * FEEDBACK - FIN
 * ****************************
 */

/*
 * ****************************
 * TOOLBAR - INICIO
 * ****************************
 */
function crearToolbarDetalleExpedienteDesdeClienteConsulta(){
	$("#detalleExpedienteDesdeClienteConsulta_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    volverADetalleConsulta();
	                }
			}
		]
	});
}
/*
 * ****************************
 * TOOLBAR - FIN
 * ****************************
 */

/*
 * ****************************
 * INICIALIZAR - INICIO
 * ****************************
 */

function descargarDocumentoObservacionesConsulta(elAnyo,elNumExp){			
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumentoObservaciones/'+elAnyo+'/'+elNumExp);
}

function mostrarCamposInterpretacion(datosInterpretacion){
	$('#datosInterConsulta').show();
	$('#divSeguimientoExp').hide();
	if("S" == datosInterpretacion.indObservaciones){
		$('#detalleExpedienteDesdeClienteConsultaObservacionesExpInterDiv').show();
		$("#ficheroDetalleExpedienteDesdeClienteConsultaObservacionesExpInter_Link").on("click", function (){
			$("#detalleExpedienteDesdeClienteConsultaObservacionesExpInterDivText").show();
			$("#ficheroDetalleExpedienteDesdeClienteConsultaObservacionesExpInter_LinkDiv").hide();
		});
	}
	desbloquearPantalla();
}

function mostrarCamposTradRev(datosTradRev){
	$('#datosTradRevConsulta').show();
	$('#divSeguimientoExp').show();
	if("S" == datosTradRev.indCorredaccion){
		$('#detalleExpedienteDesdeClienteConsultaDatosCorredaccion').show();
	}
	if("S" == datosTradRev.indObservaciones){
		$('#detalleExpedienteDesdeClienteConsultaObservacionesTradRevContainer').show();
		$("#detalleExpedienteDesdeClienteConsultaObservaciones_mostrarLink").on("click", function (){
			$('#detalleExpedienteDesdeClienteConsultaDivDetalleObservaciones').show();
			$('#detalleExpedienteDesdeClienteConsultaObservaciones_mostrarLink_div').hide();
			if( $('#oidFicheroObservacionesTradRev').val() !== '' ){
				var enlace = '<a href="#" class="descargarDocObservacionesConsulta" data-anyo="'+anyoExpediente+'" data-numexp="'+idExpediente+'" >'+datosTradRev.observaciones.nombre+'</a>';	   			
	   			$('#detalleExpedienteDesdeClienteConsultaEnlaceDescargaDetalleTradRev').html(enlace);
	   			$('a.descargarDocObservacionesConsulta').click(function(event){
	   				event.preventDefault();	
	   				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	   				var elAnyo = $(this).data("anyo");
	   				var elNumExp = $(this).data("numexp");
	   				descargarDocumentoObservacionesConsulta(elAnyo,elNumExp);
	   				desbloquearPantalla();
	   			});
			}
		});
	}
	if(datosTradRev.tradosExp){
		$('#detalleExpedienteDesdeClienteConsultaDatosTrados').show();
	}else{
		$('#detalleExpedienteDesdeClienteNumPalIzo').show();
	}
	
	desbloquearPantalla();
}

function obtenerDatosDetalleExpedienteDesdeClienteConsulta(){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente
		};
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/consultas/obtenerDatosDetalleExpedienteDesdeClienteConsulta",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			if(data){
				var xhrArray = $.rup_utils.jsontoarray(data);
				$.rup_utils.populateForm(xhrArray, $("#detalleExpedienteDesdeClienteConsultaForm"));
				
				var estadoExp = data.bitacoraExpediente.estadoExp.id;
				
				if("I" == data.idTipoExpediente){
					//mostrar campos interpretacion
					mostrarCamposInterpretacion(data.expedienteInterpretacion);
					
					if(estadoExp == estadosExp.anulado || estadoExp == estadosExp.rechazado){
						$('#detalleExpedienteDesdeClienteMotivoRechazoAnulacionInter').show();
						$('#detalleExpedienteDesdeClienteObservacionesInter').show();
						$("#detalleExpedienteDesdeClienteConsultaObservaciones").each(function() {
					        $(this).height($(this).prop('scrollHeight'));
					    });						
					}
				}else{
					//mostrar campos tradRev
					mostrarCamposTradRev(data.expedienteTradRev);
					
					

					if(estadoExp == estadosExp.anulado || estadoExp == estadosExp.rechazado){
						$('#detalleExpedienteDesdeClienteMotivoRechazoAnulacion').show();
						$('#detalleExpedienteDesdeClienteConsultaMotivoRechazo').val($("#detalleExpedienteDesdeClienteConsultaMotivoRechazoInter").val())
						$('#detalleExpedienteDesdeClienteObservaciones').show();
						$('#detalleExpedienteDesdeClienteConsultaObservaciones').val($("#detalleExpedienteDesdeClienteConsultaObservaciones").val())
						$("#detalleExpedienteDesdeClienteConsultaObservaciones").each(function() {
					        $(this).height($(this).prop('scrollHeight'));
					    });						
					}
				}
				
				var estadoExp = data.bitacoraExpediente.estadoExp.id;

				if(estadoExp == estadosExp.anulado || estadoExp == estadosExp.rechazado){
					$('#detalleExpedienteDesdeClienteMotivoRechazoAnulacion').show();
					$('#detalleExpedienteDesdeClienteObservaciones').show();
					$("#detalleExpedienteDesdeClienteConsultaObservaciones").each(function() {
				        $(this).height($(this).prop('scrollHeight'));
				    });						
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
}
/*
 * ****************************
 * INICIALIZAR - FIN
 * ****************************
 */


jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	crearFeedbackDetalleExpedienteDesdeClienteConsulta();
	crearToolbarDetalleExpedienteDesdeClienteConsulta();
	obtenerDatosDetalleExpedienteDesdeClienteConsulta();
});