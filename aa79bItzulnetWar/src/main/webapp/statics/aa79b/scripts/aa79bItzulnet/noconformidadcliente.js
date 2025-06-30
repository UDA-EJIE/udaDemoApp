var elFormulario;
var horasReales;
var esHoraOblig = false;
function formConsulta() {
	if (ejecutarTareaConsulta) {
		$.ajax({
			url : '/aa79bItzulnetWar/ejecuciontarea/tareas/findTarea/'+ idTarea,
			success : function(data, textStatus, jqXHR) {
				var newData = JSON.parse(data);
				$("#horasTarea_form").val(newData.ejecucionTareas.horasTarea);
				$("#idTarea_form").val(newData.tipoTarea.id015);
				if (isNotEmpty(newData.observacionesTareas)){
					$("#contenido_form").val(newData.observacionesTareas.obsvEjecucion);
				}
				if (newData.ejecucionTareas.indRealizada == 'N') {
					$("#tarea_realizada_filter").bootstrapSwitch('setState',false);
				} else {
					$("#tarea_realizada_filter").bootstrapSwitch('setState',true);
				}
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('Error recuperando datos del paso');
			}
		});
	}
}

function comprobarCambiosFormulario() {
	if (elFormulario !== $("#ejecutarTareaDialog_form").rup_form("formSerialize")) {
		$.rup_messages("msgConfirm", {
			title: $.rup.i18n.app.mensajes.cambiosEnPantalla,
			message: $.rup.i18n.app.mensajes.cambiosEnPantallaEjecucionTareaMensaje,
			OKFunction: function(){
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
				setTimeout(function() {
					$("#ejecutarTareaDialog").rup_dialog("close");
					}, 200);
			},
			CANCELFunction: function(e){
				return false;
			}
		});
		return false;
	}
	return true;
}

jQuery(function($) {
	formConsulta();

	$("#anyo_form").val(anyoExpediente);
	$("#numExp_form").val(idExpediente);
	$("#idTarea_form").val(idTarea);
	$("#idTipoTarea_form").val(idTipoTarea);
	
	if (horasObligatorias === 'S') {
		esHoraOblig = true;
	}

	$("#confirmartarea").confirmar_tarea({
		esHoraOblig : esHoraOblig,
		tieneHora : true,
		modalSelector : "ejecutarTareaDialog"
	});

	$("#tarea_realizada_filter").bootstrapSwitch().bootstrapSwitch(
			'setSizeClass', 'switch-small').bootstrapSwitch('setOffLabel',
			$.rup.i18n.app.comun.no).bootstrapSwitch('setOnLabel',
			$.rup.i18n.app.comun.si);


	function finalizarTarea() {
		if (validarObservaciones()) {
			if ($("#confirmartarea").length) {
				$("#confirmartarea").remove();
				$("#confirmartarea").rup_dialog('destroy');
				$("#divTareasExpedientes").append('<div id="confirmartarea" style="display:none"></div>');
			}

			$("#confirmartarea").confirmar_tarea({
				esHoraOblig : esHoraOblig,
				tieneHora : true,
				modalSelector : "ejecutarTareaDialog"
			});
			$("#confirmartarea").confirmar_tarea("open");
			elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
		}
	}

	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			id : "volver",
			css : "fa fa-arrow-left",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
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
					finalizarTarea();
				}
			}
		} ]
	});
	function validarObservaciones() {
		if ($("#tarea_realizada_filter").bootstrapSwitch('state')) {
			$("#contenido_form").rules("remove", "required");
		} else {
			$("#contenido_form").rules("add", "required");
		}
		return $("#ejecutarTareaDialog_form").valid();
	}

	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});

	$("#ejecutarTareaDialog_form").rup_form({
		url : "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		dataType : "json",
		feedback : $('#ejecutarTareaDialog_feedback'),
		type : "POST",
		validate : {
			"indNoConformidad" : {required : true},
			showFieldErrorAsDefault : false,
			showErrorsInFeedback : true,
			showFieldErrorsInFeedback : false
		},
		success : function() {
			elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			// Cerrar el diálogo y mostrar feedback
			ejecutarTareaReturn(false, "ejecutarTareaDialog",
					tablaSelector, null,
					"tareasExpedientes_feedback");
			desbloquearPantalla();
		}, 
		error : function(error) {
			// mostrar feedback de error y no cerrar la pestaña
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null,
					null, null);
			desbloquearPantalla();
		}
	});
	
	elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
});
