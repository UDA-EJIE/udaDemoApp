var horasReales;
var esHoraOblig = false;
var elFormulario="";
var cambios = false;

function formConsulta() {
	
	$.ajax({
			url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/datosTareaInterp/'+ idTarea,
			success : function(data, textStatus, jqXHR) {
				var newData = JSON.parse(data);
				
				if (newData.horasPrevistas != null) {
					$("#horasPrevistas_form").text(	newData.horasPrevistas + "h");
				}
				if (newData.horasRealesAsignadas != null) {
					$("#horasReales_form").text(newData.horasRealesAsignadas + "h");
				}
				$("#asignadoA_form").text(newData.nomPersonAsignado);
				$("#fechaInicio_form").text(newData.fechaIni);
				$("#fechaFin_form").text(newData.fechaFin);
				$("#horaInicio_form").text(newData.horaIni);
				$("#horaFin_form").text(newData.horaFin);
				if (ejecutarTareaConsulta) {
					$("#importeBase_form").val(newData.importeBase);
					$("#ivaTantoPorCiento_form").val(newData.porcentIVA);
					$("#ivaValor_form").val(newData.importeIVA);
					$("#total_form").val(newData.total);
				}
				$('#total_form').attr("disabled", "true");
				$('#total_form').attr("readnoly", "true");
				$('#ivaValor_form').attr("disabled", "true");
				$('#ivaValor_form').attr("readnoly", "true");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('Error recuperando datos de la interpretación');
			}
	});
}
/*
 * UTILIDADES - INICIO
 */
/**
 * Comprueba si hay cambios en la modal. Si no los hay, se cierra la modal.
 */
function escucharCambiosEnPantalla(){
	$('#importeBase_form')[0].onkeyup = function(){
	    cambios = true;
	}
	$('#ivaTantoPorCiento_form')[0].onkeyup = function(){
		cambios = true;
	}
	$('#ivaValor_form')[0].onkeyup = function(){
		cambios = true;
	}
	$('#importeBase_form').on("blur", function() {
		if (isNotEmpty($("#importeBase_form").val()) && $('#importeBase_form').valid()) {
			calcularImporteIVAySumaTotal();
		}
	});
	$('#ivaTantoPorCiento_form').on("blur", function() {
		if (isNotEmpty($("#ivaTantoPorCiento_form").val()) && $('#ivaTantoPorCiento_form').valid()) {
			calcularImporteIVAySumaTotal();
		}
	});
}

function comprobarCambiosFormulario() {
	if(cambios){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18n.app.mensajes.cambiosEnPantalla,
			message: $.rup.i18n.app.mensajes.cambiosEnPantallaEjecucionTareaMensaje,
			OKFunction : function() {
				cambios = false;
				setTimeout(function() {
					$("#ejecutarTareaDialog").rup_dialog("close");
				}, 20);
			},
			CANCELFunction : function(e) {
				return false;
			}
		});
		return false;
	} 
	return true;
}

function validarFormulario() {
	return $("#ejecutarTareaDialog_form").valid();
}

function finalizarTarea() {
	if (validarFormulario()) {
		if ($("#confirmartarea").length) {
			$("#confirmartarea").remove();
			$("#confirmartarea").rup_dialog('destroy');
			$("#divTareasExpedientes").append(
					'<div id="confirmartarea" style="display:none"></div>');
		}

		$("#confirmartarea").confirmar_tarea({
			esHoraOblig : esHoraOblig,
			tieneHora : true,
			documentos : false,
			modalSelector : "ejecutarTareaDialog"
		});
		$("#confirmartarea").confirmar_tarea("open");
		cambios = false;
	}
}


function calcularImporteIVAySumaTotal() {
	
	if ( isNotEmpty($("#importeBase_form").val()) && isNotEmpty($("#ivaTantoPorCiento_form").val()) ){
		if ($('#importeBase_form, #ivaTantoPorCiento_form').valid()) {
			var valorImporteBase = $("#importeBase_form").getImporte();
			var porcentajeIVA = $("#ivaTantoPorCiento_form").getImporte();
			
			var totalValorIVA = (valorImporteBase * porcentajeIVA) / 100;
			var total = valorImporteBase + totalValorIVA;
			
			$("#ivaValor_form").setImporte(totalValorIVA);
			$("#total_form").setImporte(total);
		}
	}
}
/*
 * UTILIDADES - FIN
 */

jQuery(function($) {
	$("#ejecutarTareaDialog").removeClass("ui-dialog-content ui-widget-content");
	$("#ejecutarTareaDialog").addClass("fondoCrema");

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
		documentos : false,
		modalSelector : "ejecutarTareaDialog"
	});

	// Ejemplo de toolbar: volver y finalizar tarea

	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});


	$("#ejecutarTareaDialog_form").rup_form(
			{
				url : "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
				feedback : $('#ejecutarTareaDialog_feedback'),
				showFieldErrorAsDefault : false,
				showErrorsInFeedback : true,
				showFieldErrorsInFeedback : false,
				dataType : "json",
				type : "POST",
				validate : {
					rules : {
						"datosPagoProveedoresInt.importeBase" : {
							customDecimal: ["0", "99.999,99", 0, 99999.99],
							required : true
						},
						"datosPagoProveedoresInt.importeIva" : {
							customDecimal: ["0", "99.999,99", 0, 99999.99],
							required : true
						},
						"datosPagoProveedoresInt.porIva" : {
							required : true,
							range: [0, 100]
						}
					},
					showFieldErrorAsDefault : false,
					showErrorsInFeedback : true,
					showFieldErrorsInFeedback : false
				},
				success : function() {
					cambios = false;
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
	escucharCambiosEnPantalla();
});