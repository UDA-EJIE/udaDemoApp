var horasReales;
var esHoraOblig = false;
var elFormulario;
var abiertoObservaciones = false;

function comprobarSiActualizarTareasInterpretacionYaEjecutadasYFinalizar(){
	if(tiposTarea.interpretacion == idTipoTarea){
		var ejecucionTareas = {
            "horasTarea" : $("#horasRealesTarea_form").val(),
			"porUsoEuskera" : $("#porUsoEu_detail_table").val()
        };
        var jsonObject = {
            "anyo" : anyoExpediente,
            "numExp" : idExpediente,
			"idTarea" : idTarea,
			"ejecucionTareas" : ejecucionTareas
        };
		
		$.ajax({
			type : 'POST',
			url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/actualizarTareasPrevInterpretacion',
			dataType : 'json',
			contentType : 'application/json',
    		data : $.toJSON(jsonObject),
			async : false,
			success : function(data) {
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
				// Cerrar el diálogo, mostrar feedback y recargar la
				// pantalla o la tabla
				ejecutarTareaReturn(false, "ejecutarTareaDialog",
						tablaSelector, null,
						"tareasExpedientes_feedback");
				desbloquearPantalla();
			 },
			error: function(e){
				mostrarMensajeFeedback("ejecutarTareaDialog_feedback",$.rup.i18n.app.mensajes.errorActualizarTareasInterAnteriores,"error");
			}
		});
	}else{
		elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
		// Cerrar el diálogo, mostrar feedback y recargar la
		// pantalla o la tabla
		ejecutarTareaReturn(false, "ejecutarTareaDialog",
				tablaSelector, null,
				"tareasExpedientes_feedback");
		desbloquearPantalla();
	}
}

function comprobacionesIniciales() {
	
	if (ejecutarTareaConsulta) {
		// si modo consulta obtenemos los datos de la tarea
		$.ajax({
			url : '/aa79bItzulnetWar/ejecuciontarea/tareas/findTarea/' + idTarea,
			success : function(data, textStatus, jqXHR) {
				var newData = JSON.parse(data);
				$("#horasRealesTarea_form").val(newData.ejecucionTareas.horasTarea);
				$("#porUsoEu_detail_table").val(newData.ejecucionTareas.porUsoEuskera);				
				if (newData.tipoTarea.id015 == datosTareas.tiposTarea.interpretar){
					if (newData.ejecucionTareas.indObservaciones === datos.activo.si){
						$("#linkObservaciones").addClass('inline');
						
						$('#linkObservaciones').popover({
					        title: tituloObservaciones,
							content: newData.observacionesTareas.obsvEjecucion,
					        placement: 'bottom',
					        trigger: 'manual'
					    }).on("show.bs.popover", function() {
                            $($(this).data("bs.popover").getTipElement()).addClass('interpretacion');
                        }).on('shown.bs.popover', function () {
                        	abiertoObservaciones = true;
                        }).on('hidden.bs.popover', function () {
                        	abiertoObservaciones = false;
                        });
						
						$('#linkObservaciones').on("click", function(){
							if (abiertoObservaciones){
								$('#linkObservaciones').popover('hide');
							}else{
								$('#linkObservaciones').popover('show');
							}
						});
						
					} else {
						$("#linkObservaciones").addClass('oculto');
					}
				}
				$("#idTarea_form").val(newData.tipoTarea.id015);
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('Error recuperando datos del paso');
			}
		});
	} 

	if(tiposTarea.interpretacion == idTipoTarea){
		// buscamos si se ha ejecutado alguna tarea anterior de interpretacion v3.9.50
		 var jsonObject = {
				"anyo":anyoExpediente,
				"numExp":idExpediente,
				"idTarea":idTarea
		};
		$.ajax({
			type : 'POST',
			url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findEjecTareasPrevInterpretacion',
			dataType : 'json',
			contentType : 'application/json',
    		data : $.toJSON(jsonObject),
			async : false,
			cache : false,
			success : function(data) {
				if(data){
					$("#horasRealesTarea_form").val(data.ejecucionTareas.horasTarea);
					$("#porUsoEu_detail_table").val(data.ejecucionTareas.porUsoEuskera);
					$('#usuariosEjecTareasPrevLabel').text($.format($.rup.i18nParse($.rup.i18n.app, "label.usuariosEjecTareasPrev"), data.ejecucionTareas.usuariosPrevEjecTarea));
					$('#usuariosEjecTareasPrev').show();		
				}
			 },
			error: function(e){
				mostrarMensajeFeedback("ejecutarTareaDialog_feedback",$.rup.i18n.app.mensajes.errorObtenerInfoTareasInterAnteriores,"error");
			}
		});
	}
}

/*
 * UTILIDADES - INICIO
 */
/**
 * Comprueba si hay cambios en la modal. Si no los hay, se cierra la modal.
 */
function comprobarCambiosFormulario() {
	if (elFormulario !== $("#ejecutarTareaDialog_form").rup_form("formSerialize")) {
		$.rup_messages("msgConfirm", {
			title: $.rup.i18n.app.mensajes.cambiosEnPantalla,
			message: $.rup.i18n.app.mensajes.cambiosEnPantallaEjecucionTareaMensaje,
			OKFunction : function() {
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
				setTimeout(function() {
					$("#ejecutarTareaDialog").rup_dialog("close");
				}, 200);
			},
			CANCELFunction : function(e) {
				return false;
			}
		});
		return false;
	}
	return true;
}


function finalizarTarea() {
	if ($("#ejecutarTareaDialog_form").valid()){
		if ($("#confirmartarea").length) {
			$("#confirmartarea").remove();
			$("#confirmartarea").rup_dialog('destroy');
			$("#divTareasExpedientes").append(
					'<div id="confirmartarea" style="display:none"></div>');
		}

		$("#confirmartarea").confirmar_tarea({
			esHoraOblig : esHoraOblig,
			tieneHora : false,
			documentos : false,
			modalSelector : "ejecutarTareaDialog"
		});
		$("#confirmartarea").confirmar_tarea("open");
		elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
	}
}


/*
 * UTILIDADES - FIN
 */

jQuery(function($) {
	comprobacionesIniciales();
	$("#anyo_form").val(anyoExpediente);
	$("#numExp_form").val(idExpediente);
	$("#idTarea_form").val(idTarea);
	$("#idTipoTarea_form").val(idTipoTarea);
	
	if (horasObligatorias === 'S') {
		esHoraOblig = true;
	}

	$("#confirmartarea").confirmar_tarea({
		esHoraOblig : esHoraOblig,
		tieneHora : false,
		documentos : false,
		modalSelector : "ejecutarTareaDialog"
	});

	// Ejemplo de toolbar: volver y finalizar tarea
	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			id : "volver",
			css : "fa fa-arrow-left",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				ocultarObservaciones(e);
				$("#ejecutarTareaDialog").rup_dialog("close");
			}
		}, {
			i18nCaption : $.rup.i18n.app.boton.finalizarTarea,
			css: "fa fa-save", 
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
				if (comprobarTareaNoEjecutada(idTarea)){
					ocultarObservaciones(e);
					finalizarTarea();
				}
			}
		} ]
	});

	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});

	$("#ejecutarTareaDialog_form").rup_form({
		url : "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		feedback : $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault : false,
		showErrorsInFeedback : true,
		showFieldErrorsInFeedback : false,
		dataType : "json",
		type : "POST",
		validate :{ 
			rules:{ 
				"ejecucionTareas.horasTarea" : {required : true, maxlength: 10,hora:true},
				"ejecucionTareas.porUsoEuskera" : { required: true, maxlength: 3, integer: true, range: [0, 100]}
			},
			showFieldErrorAsDefault : false,
			showErrorsInFeedback : true,
			showFieldErrorsInFeedback : false
		},
		success : function() {
			// actualizar tareas interpretacion anteriores en caso de que se haya ejecutado una tarea de interpretacion
			// si no es el caso, finalizar  v3.9.50
			comprobarSiActualizarTareasInterpretacionYaEjecutadasYFinalizar();
		},
		error : function(error) {
			// mostrar feedback de error y no cerrar la pestaña
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null,
					null, null);
			desbloquearPantalla();
		}
	});
	//No coje las validaciones definidas en el form...
//	$("#horasRealesTarea_form").rules("add", "required");
//	$("#horasRealesTarea_form").rules("add", "hora");
	elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
	
	$(document).on('click', function (e) {
		ocultarObservaciones(e);
	});
	
});

function ocultarObservaciones(e){
	$('[data-toggle="popover"],[data-original-title]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
       
		if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
			if (abiertoObservaciones){
				(($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
			}
				
        }
    });
}