/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

var codTarea = '';
var listaDocsTarea;
var listaDocsTradRev; 
var recursoExterno = false;
var apremio;
	
function mostrarBotonExcelInter() {
	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") === tipoTareaIntrepretar) {
		
		$('#ultimasInter').show();
	} else {
		
		$('#ultimasInter').hide();
	}
}

function inicializarDialogo() {

	$.rup_date({
		from : "fechaInicio_detail_table",
		to : "fechaFin_detail_table",
		fixFocusIE : false,
		onSelect : function(dateText, inst) {
			this.fixFocusIE = true;
		},
		onClose : function(dateText, inst) {
			this.fixFocusIE = true;
			if (($.browser.msie || (!!window.MSInputMethodContext && !!document.documentMode))
					&& !inst._keyEvent) {
				$("#" + inst.id).next().focus();
			}
			fnCalcularHorasPrevistasInterpretacion();

		},
		beforeShow : function(input, inst) {
			var result = $.browser.msie
					|| (!!window.MSInputMethodContext && !!document.documentMode) ? !this.fixFocusIE
					: true;
			this.fixFocusIE = false;
			return result;
		}
	});
	
	inicializarAyudas();
	fnCrearTipoTareaCombo();
	fnCrearTipoRevisionCombo();
	fncCambioTipTarea();
	
	 $("#recargoPorSwitch_tareaConfig").bootstrapSwitch()
   .bootstrapSwitch('setSizeClass', 'switch-small')
   .bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
   .bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);
	 
	$("#recargaPorFormatoSwitch_tareaConfig").bootstrapSwitch()
    .bootstrapSwitch('setSizeClass', 'switch-small')
    .bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
    .bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);
	//fnObtenerDocumentosExp();

	jQuery('#facturable_detail_table').each(
			function(index, element) {
				jQuery(element).bootstrapSwitch().bootstrapSwitch(
						'setSizeClass', 'switch-small').bootstrapSwitch(
						'setOnLabel', jQuery.rup.i18n.app.comun.si)
						.bootstrapSwitch('setOffLabel',
								jQuery.rup.i18n.app.comun.no);
			});
	jQuery('#revisarTrad_detail_table').each(
			function(index, element) {
				jQuery(element)
					.bootstrapSwitch()
					.bootstrapSwitch('setSizeClass', 'switch-small')
					.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
					.bootstrapSwitch('setOffLabel',jQuery.rup.i18n.app.comun.no);
			});
	jQuery('#mostrarNotas_detail_table').each(
			function(index, element) {
				jQuery(element).bootstrapSwitch().bootstrapSwitch(
						'setSizeClass', 'switch-small').bootstrapSwitch(
						'setOnLabel', jQuery.rup.i18n.app.comun.si)
						.bootstrapSwitch('setOffLabel',
								jQuery.rup.i18n.app.comun.no);
			});
	jQuery('#obligarXliff_detail_table').each(
			function(index, element) {
				jQuery(element)
					.bootstrapSwitch()
					.bootstrapSwitch('setSizeClass', 'switch-small')
					.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
					.bootstrapSwitch('setOffLabel',jQuery.rup.i18n.app.comun.no);
			});
	jQuery.validator.addMethod("validateMaxPercentage", function(valor) { 
		if ($("#recargoPorSwitch_tareaConfig").bootstrapSwitch('state') && parseFloat(valor) > parseFloat(apremio)) {
	        return false;
	    } else {
	    	return true;
	    }
	},$.rup.i18n.app.validaciones.porcentajeApremioMayorQueLote);

	$("#crearConfigurar_filter_form").rup_validate({
		feedback : $('#crearConfigurar_feedback'),
		liveCheckingErrors : false,
		block : false,
		delay : 3000,
		gotoTop : true,
		rules : {
			"tipoTarea.id015" : {
				required : true,
				validateTipoTarea : true
			},
			"orden" : {
				required : true,
				maxlength : 2,
				integer : true,
				validateOrden : true
			},
			"fechaIni" : {
				date : true
			},
			"horaIni" : {
				hora : true,
				maxlength : 5
			},
			"fechaFin" : {
				date : true,
				fechaHastaMayorPorId : "fechaInicio_detail_table",
				validateFechaFin : true
			},
			"horaFin" : {
				hora : true,
				maxlength : 5,
				horaFechaHastaMayorPorId : [
						"fechaInicio_detail_table",
						"fechaFin_detail_table",
						"horaInicio_detail_table" ]
			},
			"horasPrevistas" : {
				required : true,
				horasPrevistas : true,
				maxlength : 10
			},
			"observaciones" : {
				maxlength : 2000
			},
			"documentosSelect" : {
				validateSelectList : true
			},
			"datosPagoProveedores.porRecargoApremio" : {
				validateMaxPercentage : true
			}
		},
		showFieldErrorAsDefault : false,
		showErrorsInFeedback : true,
		showFieldErrorsInFeedback : false
	});

	initFormTarea = $("#crearConfigurar_filter_form").rup_form("formSerialize");

}

function fnObtenerDatosTarea(idTarea) {
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app, "comun.cargando"));

	// Se calculan las horas previstas para realizar la tarea de interpretación
	$.ajax({
		type : 'GET',
		url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findConfTarea/'
				+ idTarea,
		dataType : 'json',
		contentType : 'application/json',
		cache : false,
		success : function(data) {
			if (data !== null) {
				
				if(data.tipoTarea.id015 == datosTareas.tiposTarea.traducir|| data.tipoTarea.id015 == datosTareas.tiposTarea.tradEntregaClienteTraduccion 
						|| data.tipoTarea.id015 == datosTareas.tiposTarea.entregaClienteTraduccion){
					$('#mostrarNotasContainer').prop('hidden',false);
					$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
				}else if(tipoExp == datosExp.tipoExp.revision && data.tipoTarea.id015 == datosTareas.tiposTarea.revisar){
					$('#mostrarNotasContainer').prop('hidden',false);
					$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
				}else if(tipoExp == datosExp.tipoExp.revision && data.tipoTarea.id015 == datosTareas.tiposTarea.entregaClienteRevision){
					$('#mostrarNotasContainer').prop('hidden',false);
					$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
				}else{
					$('#mostrarNotasContainer').prop('hidden',true);
					$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
				}

				if(tipoExp == datosExp.tipoExp.revision && data.tipoTarea.id015 == datosTareas.tiposTarea.entregaClienteRevision){
                    $('#obligarXliffContainer').prop('hidden', false);
                    $('#obligarXliff_detail_table').bootstrapSwitch('setState', false);
				}else{
                    $('#obligarXliffContainer').prop('hidden', true);
                    $('#obligarXliff_detail_table').bootstrapSwitch('setState', false);
				}
				
				if (data.idTarea != null) {
					$('#idTarea_detail_table').val(data.idTarea);
				}

				if (data.tipoTarea != null) {
					var descTipoTarea = $.rup.lang === 'es' ? data.tipoTarea.descEs015
							: data.tipoTarea.descEu015;

					fnModificarTipoTareaCombo(data.tipoTarea.id015,descTipoTarea);

					$('#tipoTarea_detail_table').rup_combo("setRupValue", data.tipoTarea.id015);
				
					mostrarBotonExcelInter();
				}

				if (data.tipoRevision != null
						&& data.tipoRevision.id018 != null) {
					$('#tipoRevision_detail_table').rup_combo("setRupValue", data.tipoRevision.id018);
				}

				if (data.indFacturacion === 'S') {
					$('#facturable_detail_table').bootstrapSwitch(
							'setState', true);
				} else {
					$('#facturable_detail_table').bootstrapSwitch(
							'setState', false);
				}

				if (data.indMostrarNotasATrad === 'S') {
					$('#mostrarNotas_detail_table').bootstrapSwitch(
							'setState', true);
				} else {
					$('#mostrarNotas_detail_table').bootstrapSwitch(
							'setState', false);
				}
				
				if (data.indObligarXliff === 'S') {
					$('#obligarXliff_detail_table').bootstrapSwitch('setState', true);
				} else {
					$('#obligarXliff_detail_table').bootstrapSwitch('setState', false);
				}

				$('#orden_detail_table').val(data.orden);
				$('#fechaInicio_detail_table').val(data.fechaIni);
				$('#horaInicio_detail_table').val(data.horaIni);
				$('#fechaFin_detail_table').val(data.fechaFin);
				$('#horaFin_detail_table').val(data.horaFin);
				
				if (($('#fechaInicio_detail_table').val() === "" || $('#fechaFin_detail_table').val() === "" 
					|| $('#horaInicio_detail_table').val() === "" || $('#horaFin_detail_table').val() === "")
						&& $('#orden_detail_table').val() != "") {
					fnCalcularFechaIni();
				}
				
				$('#horaPrevista_detail_table').val(data.horasPrevistas);
				$('#observaciones_detail_table').val(data.observaciones);
				$('#recursoAsignacion').val(data.recursoAsignacion);

				if ($('#recursoAsignacion').val() === datosTareas.tipoRecurso.interno) {
					fnMostrarAsignadoAPersona();
					if (data.personaAsignada != null) {
						$('#personaAsignada_detail_table').val(data.personaAsignada.nombreCompleto);
						$('#dniAsignacion').val(data.personaAsignada.dni);
						if (data.personaAsignada.entidad != null) {
							$('#tipoEntidad').val(data.personaAsignada.entidad.tipo);
							$('#idEntidad').val(data.personaAsignada.entidad.codigo);
						}
					}
					$("#idLote").val('');
					$("#loteAsignado_detail_table").val('');
					$("#tipoEntidadLote").val('');
					$("#idEntidadLote").val('');
					validarCampos();
				} else if ($('#recursoAsignacion').val() === datosTareas.tipoRecurso.externo) {
					if (data.personaAsignada != null) {
						recursoExterno = true;
						var dniProveedor = data.personaAsignada.dni;

						if (dniProveedor != null && dniProveedor !== '') {
							fnMostrarAsignadoAPersona();
							$('#dniAsignacion').val(data.personaAsignada.dni);

							var nombre = data.personaAsignada.nombreCompleto;

							if (data.personaAsignada.entidad != null) {
								$('#tipoEntidad').val(data.personaAsignada.entidad.tipo);
								$('#idEntidad').val(data.personaAsignada.entidad.codigo);

								var descTipoEntidad = data.personaAsignada.entidad.tipoDesc;

								if (descTipoEntidad != null
										&& descTipoEntidad !== '') {
									nombre += " (" + descTipoEntidad + ")";
								}
							}

							$('#personaAsignada_detail_table').val(nombre);

							$('#idLote').val('');
							$('#tipoEntidadLote').val('');
							$('#idEntidadLote').val('');
							$('#loteAsignado_detail_table').val('');
							validarCampos();
						} else {
							fnMostrarAsignadoALote();
							if (data.lotes != null) {
								$('#idLote').val(data.lotes.idLote);
								$('#loteAsignado_detail_table').val(data.lotes.nombreLote);
							}
							$('#tipoEntidadLote').val('');
							$('#idEntidadLote').val('');
							$('#tipoEntidad').val('');
							$('#idEntidad').val('');
							$('#dniAsignacion').val('');
							$('#personaAsignada_detail_table').val('');
							validarCampos();
						}
					}
				} 

				fnIndReqCierre();

				
				if (data.estadoEjecucion == datosTareas.estadoEjecucion.ejecutada) {
					modificarTareaConsulta = true;
					dialogoSoloConsultaConfiguracionTareas(true,"crearConfigurarDialog");
					initFormTarea = '';
					$('#btnGuardar').hide();
					$('#divLinkEliminar').hide();
					$('#divLinkAutoasignar').hide();
					$('#divLinkRecursoInterno').hide();
					$('#recurExter').hide();
					
					if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisar
					|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteRevision
					|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisarTraduccionExterna 
					|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisarTraduccionInterna) {
						$('#tipRev').attr('hidden', false);
					}
					
				} else {
					modificarTareaConsulta = false;
					dialogoSoloConsultaConfiguracionTareas(false,"crearConfigurarDialog");
					initFormTarea = $("#crearConfigurar_filter_form").rup_form("formSerialize");
					$('#tipoTarea_detail_table').rup_combo('disable');

					if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.proyectoTrados) {
						fnInicializarDatosTipoTarea();
						$('#orden_detail_table').attr('disabled','disabled');
					} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.traducir
							|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.interpretar) {
						$('#tipRev').attr('hidden', true);
						$('#recurExter').attr('hidden', false);
						$('#orden_detail_table').removeAttr('disabled');
					} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisar) {
						$('#tipRev').attr('hidden', false);
						$('#recurExter').attr('hidden', false);
						$('#orden_detail_table').removeAttr('disabled');
					} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteRevision) {
						$('#tipRev').attr('hidden', false);
						$('#recurExter').attr('hidden', true);
					} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisarTraduccionExterna
							|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisarTraduccionInterna) {
						$('#tipRev').attr('hidden', false);
					} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.gestionInterpretacion) {
						fnInicializarDatosTipoTarea();
						$('#orden_detail_table').attr('disabled','disabled');
					} else {
						// ocultarCapasHoras();
						fnInicializarDatosTipoTarea();
						$('#orden_detail_table').removeAttr('disabled');
					}
					//mostrarOcultarHorasPrevistas();
					inicializarDatosTarea();
					validarObservaciones();

					if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.noConformidadProveedor) {
						// Ocultamos los links que permiten la
						// asignación de un recurso a la tarea de no
						// conformidad con el trabajo
						// del proveedor, ya que la no conformidad está
						// asignada al mismo Lote que realizó la tarea
						// original
						$('#divLinkEliminar').hide();
						$('#divLinkAutoasignar').hide();
						$('#divLinkRecursoInterno').hide();
						$('#recurExter').hide();
					}
					comprobarFechaHastaSuperaFechaFinExpediente();
				}
				inicializarValorSwitchRevisarTraduccion($('#recursoAsignacion').val(),modificarTareaConsulta,data.indReqRevision);
				// ocultarCapasHoras();
			}
			mostrarLote();
			desbloquearPantalla();
		},
		error : function() {
			desbloquearPantalla();
		}
	});
}

// persona asignada tarea diferente de persona asignada a tarea anterior de revision
function personaDistintaARevision(){
	return $('#dniAsignacion').val() &&
			 $('#asignadoTareaRevAnt_detail_table').val() && 
				$('#dniAsignacion').val() != $('#asignadoTareaRevAnt_detail_table').val();
}

function inicializarDatosTarea() {
	$('#personaAsignada_detail_table').attr('readonly', 'readonly');
	$('#loteAsignado_detail_table').attr('readonly', 'readonly');
	$('#btnGuardar').show();
	$('#divLinkEliminar').show();
	$('#divLinkAutoasignar').show();
	$('#divLinkRecursoInterno').show();
}

function limpiarDatosFormTarea() {
	$("#crearConfigurar_filter_form").rup_form("clearForm");
	$("[id^='checkDocumento']").removeClass('jstree-checked').addClass(
			'jstree-unchecked');
}

function cerrarModalCrearTarea() {
	$("#crearConfigurarDialog").rup_dialog('close');
	$('#tipoTarea_detail_table').rup_combo("select", 0);
	limpiarDatosFormTarea();
	$('#divDocumentosSeleccionados').css("height", "0px");
	$("#buscadorPersonas").remove();
	$("#buscadorPersonas").rup_dialog('destroy');
	$("#buscadorPersonasIZO").remove();
	$("#buscadorPersonasIZO").rup_dialog('destroy');
	$("#buscadorLotes").remove();
	$("#buscadorLotes").rup_dialog('destroy');
}

function ocultarCapasHoras() {
	$("#horasPrevistasTradRev").hide();
	$("#horasEstimadasInter").hide();
}

function validarCampos() {
	$("#orden_detail_table").rules("add", "required");
	$("#fechaInicio_detail_table").rules("add", "required");
	$("#horaInicio_detail_table").rules("add", "required");
	$("#fechaFin_detail_table").rules("add", "required");
	$("#horaFin_detail_table").rules("add", "required");

	$("label[for=fechaInicio_detail_table]").removeClass("dosPuntos").addClass(
			"asteriscoDosPuntos");
	$("label[for=fechaFin_detail_table]").removeClass("dosPuntos").addClass(
			"asteriscoDosPuntos");
	validarTipoRevision();
}

function eliminarValidacionesCampos() {
	$("#fechaInicio_detail_table").rules("remove", "required");
	$("#horaInicio_detail_table").rules("remove", "required");
	$("#fechaFin_detail_table").rules("remove", "required");
	$("#horaFin_detail_table").rules("remove", "required");

	$("label[for=fechaInicio_detail_table]").removeClass("asteriscoDosPuntos")
			.addClass("dosPuntos");
	$("label[for=fechaFin_detail_table]").removeClass("asteriscoDosPuntos")
			.addClass("dosPuntos");
}

function validarTipoRevision() {
	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisar 
			|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteRevision
			|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisarTraduccionExterna
			|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisarTraduccionInterna
			|| esTareaEntregaClienteTradYAsignadoDistintoAntRev()) {
		$("#tipoRevision_detail_table").rules("add", "required");
	} else {
		$("#tipoRevision_detail_table").rules("remove", "required");
	}
}

function validarObservaciones() {
	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.noConformidadCliente) {
		$("#observaciones_detail_table").rules("add", "required");
		$("label[for=observaciones_detail_table]").removeClass("dosPuntos").addClass(
		"asteriscoDosPuntos");
	} else {
		$("#observaciones_detail_table").rules("remove", "required");
		$("label[for=observaciones_detail_table]").removeClass("asteriscoDosPuntos").addClass(
		"dosPuntos");
	}
}

function asignarTipoRevDefecto(){
	$('#tipoRevision_detail_table').rup_combo("setRupValue", datosTareas.tiposRevision.sencilla);
}

function inicializarDatosTareaInterpretar() {
	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") === datosTareas.tiposTarea.interpretar) {
		$('#fechaInicio_detail_table').val(fechaIniInter);
		$('#horaInicio_detail_table').val(horaIniInter);
		$('#fechaFin_detail_table').val(fechaFinInter);
		$('#horaFin_detail_table').val(horaFinInter);
		comprobarFechaHastaSuperaFechaFinExpediente();
	}
}

function fnSwitchRevisionTrabajo(){
	var parentSwitchRevTrabajo = $("#revisarTrad_detail_table").parent().parent();
	$('#revisarTradContainer').bootstrapSwitch('setState', false);
	$("#revisarTrad_detail_table").attr('disabled','disabled');
	parentSwitchRevTrabajo.addClass("disabled");
	if($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.traducir){
		$('#revisarTradContainer').show();
	}else{
		$('#revisarTradContainer').hide();
	}
}

function cambiarValorSwitchRevisarTraduccion(disable, value){
	if($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.traducir){
		var parentSwitchRevTrabajo = $("#revisarTrad_detail_table").parent().parent();
		// habilitamos o deshabilitamos el switch
		if(disable){
			// asignamos el valor al switch y lo deshabilitamos
			$('#revisarTrad_detail_table').bootstrapSwitch('setState', value);
			$("#revisarTrad_detail_table").attr('disabled','disabled');
			parentSwitchRevTrabajo.addClass("disabled");
		}else{
			if($('#revisarTrad_detail_table').prop('disabled')){
				// si esta deshabilitado le asignamos el valor, si no, lo mantenemos
				$('#revisarTrad_detail_table').bootstrapSwitch('setState', value);
			}
			$("#revisarTrad_detail_table").removeAttr('disabled');
			parentSwitchRevTrabajo.removeClass("disabled");
		}
		$('#revisarTradContainer').show();
	} else {
		var parentSwitchRevTrabajo = $("#revisarTrad_detail_table").parent().parent();
		$('#revisarTradContainer').bootstrapSwitch('setState', false);
		$("#revisarTrad_detail_table").attr('disabled','disabled');
		parentSwitchRevTrabajo.addClass("disabled");
		$('#revisarTradContainer').hide();
	}
}

function inicializarValorSwitchRevisarTraduccion(recurso, modoConsulta, value){
	if($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.traducir){
		if(recurso === datosTareas.tipoRecurso.interno){
			$("#revisarTrad_detail_table").attr('disabled','disabled');
			cambiarValorSwitchRevisarTraduccion(false, value === 'S' ? true : false);
		} else if(recurso === datosTareas.tipoRecurso.externo){
			cambiarValorSwitchRevisarTraduccion(true, true);
		}else{
			cambiarValorSwitchRevisarTraduccion(true, false);
		}
	} else{
		cambiarValorSwitchRevisarTraduccion(true, false);
	}
}

function inicializarAyudas() {
	
	new Cleave('#fechaInicio_detail_table', {
	    date: true,
	    datePattern: ['Y', 'm', 'd']
	});
	
	new Cleave('#horaInicio_detail_table', {
	    time: true,
	    timePattern: ['h', 'm']
	});
	
	new Cleave('#fechaFin_detail_table', {
	    date: true,
	    datePattern: ['Y', 'm', 'd']
	});
	
	new Cleave('#horaFin_detail_table', {
	    time: true,
	    timePattern: ['h', 'm']
	});
}

function fnCrearTipoTareaCombo() {
	$('#tipoTarea_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/findTiposTareaManuales/"
				+ anyoExpediente
				+ "/"
				+ idExpediente
				+ "/"
				+ tipoExp,
		sourceParam : {
			value : "id015",
			label : $.rup.lang === 'es' ? "descEs015"
					: "descEu015",
		},
		blank : "",
		width : "auto",
		ordered : false,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#" + id + "-menu").width(
					$("#" + id + "-button").innerWidth());
		},
		select : function() {
			fncCambioTipTarea();
			fnDatosTipoTarea();
			fnMostrarDocumentos();
			validarObservaciones();
			validarTipoRevision();
			asignarTipoRevDefecto();
			inicializarDatosTareaInterpretar();
			ocultarCapasHoras();
			fnCalcularHorasPrevistasTraduccion();
			fnCalcularHorasPrevistasInterpretacion();
            mostrarLote();
			cambiarValorSwitchRevisarTraduccion(true, false);
		}
	});
}

function fnModificarTipoTareaCombo(idTipoTarea, descTipoTarea) {
	$('#tipoTarea_detail_table').rup_combo({
		source : [ {
			i18nCaption : descTipoTarea,
			value : idTipoTarea
		} ],
		width : "auto",
		ordered : false,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#" + id + "-menu").width($("#" + id + "-button").innerWidth());
		}
	});
}

function fnCrearTipoRevisionCombo() {
	$('#tipoRevision_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposrevision/findTiposRevisionAlta",
		sourceParam : {
			label : $.rup.lang === 'es' ? "descEs018"
					: "descEu018",
			value : "id018"
		},
		blank : "",
		width : "auto",
		ordered : false,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#" + id + "-menu").width(
					$("#" + id + "-button").innerWidth());
		},
		change : function(){
			if ($('#tipoRevision_detail_table').rup_combo("getRupValue")){
				fnCalcularHorasPrevistasTraduccion();
			} else {
				
			}
		}
	});
}

function fnCalcularFechaIni(){	
	var orden = $("#orden_detail_table").val();
	if( ""!=orden){
		
		var Expediente = {
				"anyo" : anyoExpediente,
				"numExp" : idExpediente
			};
		$.ajax({
			type : 'POST',
			url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularFechaIni',
			dataType : 'json',
			contentType : 'application/json',
			data : $.toJSON({
				"expediente" : Expediente,
				"idTipoTarea" : idTipoTarea,
				"orden": orden,
				"idTarea": codTarea
			}),
			cache : false,
			success : function(data) {
				var fechaIniCompleta = formatDate(data,$.rup.lang);
				$("#fechaInicio_detail_table").val(fechaIniCompleta.split(" ")[0]);
				$("#horaInicio_detail_table").val(fechaIniCompleta.split(" ")[1]);
				$('#fechaInicio_detail_table').rup_date("enable");		
				$('#horaInicio_detail_table').removeAttr('disabled');	
				fnCalcularHorasPrevistasTraduccion();
				
				desbloquearPantalla();

			},
			error : function() {
				desbloquearPantalla();
			}
		});
	}
}

function fnTienePersonaAsignada(){
	return $('#personaAsignada_detail_table').val() != "";
}
function fnTieneLoteAsignado(){
	return $('#loteAsignado_detail_table').val() != "";
}
function fnCalcularHorasPrevistasTraduccion() {
	var idTipoTarea = $('#tipoTarea_detail_table').rup_combo("getRupValue");
	if (idTipoTarea != '' && idTipoTarea != datosTareas.tiposTarea.interpretar) {
		// deben estar informados el orden, el recurso asignado a la tarea y tener documentos seleccionados
		$("#horaPrevista_detail_table_container").show();
		$("#horaPrevista_detail_table").val("");
		if($("#orden_detail_table").val() != '' 
			&& (fnTienePersonaAsignada() || fnTieneLoteAsignado())){
			// con el nuevo campo de porcentaje de relacion de tiempo entre tareas de revision y traduccion (v4.1.00)
			// si esta visible el combo de tipo de revision y si tiene valor, sigue con el proceso
			// si esta visible el combo de tipo de revision y si NO tiene valor, NO sigue con el proceso
			// si no esta visible sigue con el proceso
			if (!$('#tipRev').is(":visible") || ($('#tipRev').is(":visible") && $('#tipoRevision_detail_table').val())){
				bloquearPantalla($.rup.i18nParse($.rup.i18n.app, "comun.cargando"));

				if (idTipoTarea === datosTareas.tiposTarea.traducir
						|| idTipoTarea === datosTareas.tiposTarea.revisar) {
				
					$("#horasPrevistasTradRev").show();
					$("#horasEstimadasInter").hide();
				}else{
					$("#horasPrevistasTradRev").hide();
					$("#horasEstimadasInter").hide();
				}
		
				var Expediente = {
					"anyo" : anyoExpediente,
					"numExp" : idExpediente
				};
				
				var PersonaAsig = {
					"dni" : $('#dniAsignacion').val()
				};
				
				var Tareas = {
		            "idTarea" : $("#idTarea_detail_table").val(),
		            "personaAsignada" : PersonaAsig,
		            "orden" : $("#orden_detail_table").val(),
					"tipoRevision" : {}
		        };
				if ($('#tipoRevision_detail_table').val()){
					Tareas.tipoRevision.id018 = $('#tipoRevision_detail_table').val();
				}
				// Se calculan las horas previstas para realizar la tarea para un
				// recurso interno y externo
				$.ajax({
					type : 'POST',
					url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularHorasPrevistasTradRev',
					dataType : 'json',
					contentType : 'application/json',
					data : $.toJSON({
						"expediente" : Expediente,
						"documentosSelect" : documentosSelect!= ""?documentosSelect:'-1',
						"idTipoTarea" : idTipoTarea,
						"tareas" : Tareas
					}),
					cache : false,
					success : function(data) {
						if (data !== null) {
							if (data.horasIZO!= null){
								$("#horasPrevistasIZO").css("color", "black");
								$("#horasPrevistasIZO").text(data.horasIZO);
								$("#horasPrevistasProveedor").text(
										data.horasProveedor);
								// llamar CALCULAR_FECHA_FIN_TAREA
								var horasPrevistas;
								if(fnTienePersonaAsignada()){
									horasPrevistas = data.horasIZO
								}else if(fnTieneLoteAsignado()){
									horasPrevistas = data.horasProveedor
								}
								$("#horaPrevista_detail_table").val(horasPrevistas);
								// comprobamos que tienen valores los campos de fecha inicio 
								if($("#fechaInicio_detail_table").val() != ''
									&& $("#horaInicio_detail_table").val() != ''){
									var Tareas = {
										"fechaIni" : $("#fechaInicio_detail_table").val(),
										"horaIni" : $("#horaInicio_detail_table").val(),
										"horasPrevistas" : horasPrevistas
									};
									$.ajax({
										type : 'POST',
										url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularFechaFinTarea',
										dataType : 'json',
										contentType : 'application/json',
										data : $.toJSON({
											"tarea" : Tareas
										}),
										cache : false,
										success : function(data) {
											var fechaIniCompleta = formatDate(data,$.rup.lang);
											$("#fechaFin_detail_table").val(fechaIniCompleta.split(" ")[0]);
											$("#horaFin_detail_table").val(fechaIniCompleta.split(" ")[1]);
											$('#fechaFin_detail_table').rup_date("enable");		
											$('#horaFin_detail_table').removeAttr('disabled');	
											comprobarFechaHastaSuperaFechaFinExpediente();
											desbloquearPantalla();
							
										},
										error : function() {
											desbloquearPantalla();
										}
									});
								}
							}else{
								$("#horasPrevistasIZO").css("color", "red");
								$("#horasPrevistasIZO").text(txtRevisarNumPalabras);
								$("#horasPrevistasProveedor").text('');
							}
						}
		
						desbloquearPantalla();
		
					},
					error : function() {
						desbloquearPantalla();
					}
				});
			}					
		}
	} else{
		// seleccion vacia, inicializamos campos
		$("#horaPrevista_detail_table_container").show();
		$("#horaPrevista_detail_table").val("");
	}
}

function fnCalcularHorasPrevistasInterpretacion() {
	var idTipoTarea = $('#tipoTarea_detail_table').rup_combo("getRupValue");
	if (idTipoTarea === datosTareas.tiposTarea.interpretar) {
		$("#horasPrevistasTradRev").hide();
		$("#horasEstimadasInter").show();
			if ($('#fechaInicio_detail_table').val() !== ''
					&& $('#horaInicio_detail_table').val() !== ''
					&& validarHora($('#horaInicio_detail_table').val()) !== ''
					&& $('#fechaFin_detail_table').val() !== ''
					&& $('#horaFin_detail_table').val() !== ''
					&& validarHora($('#horaFin_detail_table').val()) !== '') {

				var Tareas = {
					"fechaIni" : $("#fechaInicio_detail_table").val(),
					"horaIni" : $("#horaInicio_detail_table").val(),
					"fechaFin" : $("#fechaFin_detail_table").val(),
					"horaFin" : $("#horaFin_detail_table").val()
				};

				// Se calculan las horas previstas para realizar la tarea de
				// interpretación
				$.ajax({
					type : 'POST',
					url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularHorasEntreFechas',
					dataType : 'json',
					contentType : 'application/json',
					data : $.toJSON({
						"tarea" : Tareas
					}),
					cache : false,
					success : function(data) {
						if (data !== null) {
							$("#horasEstimadas").text(data);
						}
					},
					error : function() {
					}
				});

			} else {
				$("#horasEstimadas").text("-");
			}
		}
}

function mostrarMensajeFechaFinExcedeFechaFinExp(mostrar){
	if(mostrar){
		if($('#fechaFin_detail_table-error').length === 0){
			$('#mensajeFechaFinMayorQueFechaFinExp').show();
		}
	} else {
		$('#mensajeFechaFinMayorQueFechaFinExp').hide();
	}
}

function comprobarFechaHastaSuperaFechaFinExpediente(){
	if ($('#tipoTarea_detail_table').rup_combo(
			"getRupValue") !== ''
			&& $('#horaFin_detail_table').val() !== ''
			&& $('#fechaFin_detail_table') !== '') {
		var tipoTarea = {
				"id015" : $('#tipoTarea_detail_table')
						.rup_combo("getRupValue")
			}

		var jsonObject = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente,
			"tipoTarea" : tipoTarea,
			"fechaFin" : $('#fechaFin_detail_table').val(),
			"horaFin" : $('#horaFin_detail_table').val()
		};

		$.ajax({
			type : "POST",
			url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarFechaFinTarea",
			dataType : 'json',
			contentType : 'application/json',
			data : $.toJSON(jsonObject),
			async : false,
			cache : false,
			success : function(data) {
				if (data === 1) {
					mostrarMensajeFechaFinExcedeFechaFinExp(true);
					fechaFinValida = false;
				}else{
					mostrarMensajeFechaFinExcedeFechaFinExp(false);
				}
			}
		});
	}
}

function fnInicializarCampoOrden() {
	if($('#orden_detail_table').val() == '0'){
		$('#orden_detail_table').val('');
	}
	$('#orden_detail_table').removeAttr('disabled');
}

function fnDeshabilitarCampoOrden() {
	$('#orden_detail_table').val('0');
	$('#orden_detail_table').attr('disabled', 'disabled');
}

function fnInicializarDatosTipoTarea() {
	$('#tipRev').attr('hidden', true);
	$('#recurExter').attr('hidden', true);
}

function fnMostrarAsignadoAPersona() {
	$("#asignadoAPersona").show();
	$("#asignadoALote").hide();
}

function fnMostrarAsignadoALote() {
	$("#asignadoAPersona").hide();
	$("#asignadoALote").show();
}

function mostrarOcultarHorasPrevistas(){
	var idTipoTarea = $('#tipoTarea_detail_table').rup_combo("getRupValue");
	if(idTipoTarea == datosTareas.tiposTarea.traducir
			|| idTipoTarea == datosTareas.tiposTarea.corredactar
			|| idTipoTarea == datosTareas.tiposTarea.revisar
			|| idTipoTarea == datosTareas.tiposTarea.entregaClienteTraduccion
			|| idTipoTarea == datosTareas.tiposTarea.entregaClienteRevision
			|| idTipoTarea == datosTareas.tiposTarea.revisarTraduccionExterna
			|| idTipoTarea == datosTareas.tiposTarea.tradEntregaClienteTraduccion
			|| idTipoTarea == ''){
		$("#horaPrevista_detail_table_container").show();
		$("#horaPrevista_detail_table").val("");
	}else{
		// inicializar horas previstas a 00:30 y ocultar el campo
		$("#horaPrevista_detail_table_container").hide();
		$("#horaPrevista_detail_table").val("00:30");
	}
	
	if (idTipoTarea === datosTareas.tiposTarea.traducir
			|| idTipoTarea === datosTareas.tiposTarea.revisar) {
	
		$("#horasPrevistasTradRev").show();
		$("#horasEstimadasInter").hide();
	}else if (idTipoTarea === datosTareas.tiposTarea.interpretar) {
		$("#horasPrevistasTradRev").hide();
		$("#horasEstimadasInter").show();
	}else{
		$("#horasPrevistasTradRev").hide();
		$("#horasEstimadasInter").hide();
	}
			
}


function inicializarTipoTarea() {
	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.proyectoTrados) {
		$('#tipoRevision_detail_table').rup_combo("select", 0);
		fnInicializarDatosTipoTarea();
		fnDeshabilitarCampoOrden();
	} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.traducir
			|| $('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.interpretar) {
		$('#tipoRevision_detail_table').rup_combo("select", 0);
		$('#tipRev').attr('hidden', true);
		$('#recurExter').attr('hidden', false);
		fnInicializarCampoOrden();
	} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.revisar) {
		$('#tipRev').attr('hidden', false);
		$('#recurExter').attr('hidden', false);
		fnInicializarCampoOrden();
	} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteRevision) {
		$('#tipRev').attr('hidden', false);
		$('#recurExter').attr('hidden', true);
	} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.gestionInterpretacion) {
		fnInicializarDatosTipoTarea();
		fnDeshabilitarCampoOrden();
	} else {
		// TODO 19 y asignado revision anterior diferente
		if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
			$('#tipRev').attr('hidden', false);	
		} else {
			$('#tipRev').attr('hidden', true);
		}
	
		// si traduccion o revision, mostrar switch Mostrar al traductor las notas de la petición. En otro caso ocultar y con valor a N.		
		
		
		// ocultarCapasHoras();
		fnInicializarDatosTipoTarea();
		fnInicializarCampoOrden();
	}
	mostrarOcultarHorasPrevistas();
	mostrarBotonExcelInter();
}

function fncCambioTipTarea() {
	fnMostrarAsignadoAPersona();
	inicializarTipoTarea();

	$('#indReqCierre_detail_table').val('');
	$('#facturable_detail_table').bootstrapSwitch('setState', false);
}

function fnDatosTipoTarea() {

	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") !== '') {
		var idTipoTarea = $('#tipoTarea_detail_table').rup_combo("getRupValue");

		$.ajax({

			type : 'GET',
			url : '/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/'
					+ idTipoTarea,
			dataType : 'json',
			contentType : 'application/json',
			async : false,
			cache : false,
			success : function(data) {

				if (data !== null) {

					if (data.indFacturable015 === 'S') {
						$('#facturable_detail_table').bootstrapSwitch(
								'setState', true);
					} else {
						$('#facturable_detail_table').bootstrapSwitch(
								'setState', false);
					}
					
					if(data.id015 == datosTareas.tiposTarea.traducir || data.id015 == datosTareas.tiposTarea.tradEntregaClienteTraduccion 
							|| data.id015 == datosTareas.tiposTarea.entregaClienteTraduccion){
						$('#mostrarNotasContainer').prop('hidden',false);
						$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
					}else if(tipoExp == datosExp.tipoExp.revision && data.id015 == datosTareas.tiposTarea.revisar){
						$('#mostrarNotasContainer').prop('hidden',false);
						$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
					}else if(tipoExp == datosExp.tipoExp.revision && data.id015 == datosTareas.tiposTarea.entregaClienteRevision){
						$('#mostrarNotasContainer').prop('hidden',false);
						$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
					}else{
						$('#mostrarNotasContainer').prop('hidden',true);
						$('#mostrarNotas_detail_table').bootstrapSwitch('setState', true);
					}

					if(tipoExp == datosExp.tipoExp.revision && data.id015 == datosTareas.tiposTarea.entregaClienteRevision){
						$('#obligarXliffContainer').prop('hidden', false);
						$('#obligarXliff_detail_table').bootstrapSwitch('setState', false);
					}else{
						$('#obligarXliffContainer').prop('hidden', true);
						$('#obligarXliff_detail_table').bootstrapSwitch('setState', false);
					}
					
					$('#indReqCierre_detail_table').val(data.indReqCierre015);

					fnLimpiarCampos();

				}

			}
		});
	}

}

function fnIndReqCierre() {

	if ($('#tipoTarea_detail_table').rup_combo("getRupValue") !== '') {
		var idTipoTarea = $('#tipoTarea_detail_table').rup_combo("getRupValue");

		$.ajax({

			type : 'GET',
			url : '/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/'
					+ idTipoTarea,
			dataType : 'json',
			contentType : 'application/json',
			async : false,
			success : function(data) {

				if (data !== null) {

					$('#indReqCierre_detail_table').val(data.indReqCierre015);

				}

			}
		});
	}

}

function fnDocumentosTarea(idTarea) {

	if (idTarea != '') {

		$.ajax({
			type : 'GET',
			url : '/aa79bItzulnetWar/documentosgeneral/findDocumentosTarea/'
					+ idTarea,
			dataType : 'json',
			contentType : 'application/json',
			async : false,
			success : function(data) {

				if (data !== null) {

					documentosSelect = '';

					for (var i = 0; i < data.length; i++) {

						if (documentosSelect === '') {
							documentosSelect += data[i].idDocOriginal;
						} else {
							documentosSelect += ',' + data[i].idDocOriginal;
						}

						$("[id='checkDocumento" + data[i].idDocOriginal + "']")
								.removeClass('jstree-unchecked').addClass(
										'jstree-checked');
					}
					// ocultarCapasHoras();

				}

			}
		});

	}

}

function fnLimpiarCampos() {
	$('#fechaFin_detail_table').val('');
	$('#horaFin_detail_table').val('');
	$('#fechaFin_detail_table').rup_date("disable");	
	$('#horaFin_detail_table').attr('disabled','disabled');	
	fnLimpiarAsignacion();
	$('#observaciones_detail_table').val('');
	mostrarMensajeFechaFinExcedeFechaFinExp(false);
}

function fnAutoasignarTarea() {

	$.ajax({
		type : 'GET',
		url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/autoasignartareas',
		dataType : 'json',
		contentType : 'application/json',
		async : false,
		success : function(data) {

			if (data !== null) {
				fnLimpiarCamposLote();
				fnMostrarAsignadoAPersona();
				$('#dniAsignacion').val(data.dni);
				$('#personaAsignada_detail_table').val(data.nombre);
				$('#idEntidad').val('');
				$('#tipoEntidad').val('');
				$('#recursoAsignacion').val(
						datosTareas.tipoRecurso.interno);
				validarCampos();
			}

		},
		error : function(error) {
			$('#reasignarTareas_feedback').rup_feedback(
					"set",
					$.rup.i18nParse($.rup.i18n.app,
							"mensajes.errorLlamadaAjax"), "error");
		}
	});

}

function fnLimpiarAsignacion() {
	fnLimpiarCamposPersona();
	fnLimpiarCamposLote();
	eliminarValidacionesCampos();
	validarCampos();
}

function fnLimpiarCamposPersona() {
	$("#personaAsignada_detail_table").val("");
	$("#tipoEntidad").val("");
	$("#idEntidad").val("");
	$("#dniAsignacion").val("");
	$('#recursoAsignacion').val("");
}

function fnLimpiarCamposLote() {
	$("#idLote").val("");
	$("#loteAsignado_detail_table").val("");
	$("#tipoEntidadLote").val("");
	$("#idEntidadLote").val("");
	$('#recursoAsignacion').val("");
}

function fnObtenerDocumentosExp() {

	var DocumentosExpediente = {
		"anyo" : anyoExpediente,
		"numExp" : idExpediente
	};

	$
			.ajax({
				type : "POST",
				url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/listaDocuAsociados",
				dataType : "json",
				contentType : 'application/json',
				data : $.toJSON({
					"docuExp" : DocumentosExpediente
				}),
				cache : false,
				success : function(data) {
					listaDocsTarea = data;
					listaDocsTradRev = listaDocsTarea.filter( function (x){ return (x.claseDocumento === 1  ||  x.claseDocumento === 2) });
					$("#listaDocuAsociados").rup_tree("destroy");
					$("#listaDocuAsociados > ul").empty();
					var sufijoIdioma = "";
					if (idiomaDestino == "1"){
						sufijoIdioma= labelES;
					}else{
						sufijoIdioma= labelEU;
					}
					
					// Carga del árbol
					for (var i = 0; i < data.length; i++) {
						$("#listaDocuAsociados > ul").append(
								"<li id='checkDocumento" + data[i].idDoc
										+ "' value = '" + data[i].idDoc + "'>");
						var inputClaseDoc = "<input type='hidden' id='claseDocumento"
								+ data[i].idDoc
								+ "' value='"
								+ data[i].claseDocumento + "'>";
						var descClaseDoc = "";
						if (data[i].claseDocumentoDesc != null
								&& data[i].claseDocumentoDesc !== "") {
							descClaseDoc = " (" + data[i].claseDocumentoDesc
									+ ")";
						}
						var nombreDocu = "<a href='#' value ='" + data[i].idDoc + "'>" + data[i].ficheros[0].nombre;
						nombreDocu = nombreDocu + descClaseDoc	+ "</a>";
						
						$("#listaDocuAsociados > ul > li").last().append(nombreDocu).append(inputClaseDoc);
						$("#listaDocuAsociados > ul").append("</li>");

						$("[id='checkDocumento" + data[i].idDoc + "'] > a").on('click',function() {
							if (modificarTareaConsulta) {
								return false;
							} else {
								var idDocSeleccionado = ($(this).parent()).val();
								var documentoSeleccionado = false;
								documentosSelect = '';
								if (($(this).parent()).hasClass(
										'jstree-unchecked')) {
									documentoSeleccionado = true;
								}

								// Recogemos los ids de los
								// documentos checkeados
								$('#docuAsociados ul li.jstree-checked').each(function() {
									if ($(this).val() !== idDocSeleccionado) {
										if (documentosSelect === '') {
											documentosSelect += $(this).val();
										} else {
											documentosSelect += ','+ $(this).val();
										}
									}

								});

								if (documentoSeleccionado) {

									if (documentosSelect === '') {
										documentosSelect += idDocSeleccionado;
									} else {
										documentosSelect += ','+ idDocSeleccionado;
									}
								}
								// ocultarCapasHoras();
								fnCalcularHorasPrevistasTraduccion();
							}

						});

					}
					$("#listaDocuAsociados").rup_tree({
						"checkbox" : {
							"enable" : true
						}
					}).on('loaded.jstree', function(evt, data) {

						fnDocumentosTarea(codTarea);

					});
					;

					setTimeout(
							function() {
								$(
										"#listaDocuAsociados > ul > li > ins.jstree-icon")
										.remove();
								$(
										"#listaDocuAsociados > ul > li > a > ins.jstree-icon")
										.remove();
							}, 500);
				}
			});
}

function fnMostrarDocumentos() {
	if (($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion)
			|| ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.tradEntregaClienteTraduccion)
			|| ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteRevision)) {
		var claseDoc;
		if (($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion)
				|| ($('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.tradEntregaClienteTraduccion)
				) {
			claseDoc = datosDocumentos.clasificacion.traduccion;
		} else {
			claseDoc = datosDocumentos.clasificacion.revision;
		}

		documentosSelect = '';

		// En caso de que el tipo de tarea seleccionada sea de entrega a cliente
		// (Traducción/Revisión)
		// Los documentos de trabajo no se visualizarán
		$('#docuAsociados ul li')
				.each(
						function() {

							if ($(this).children('input').val() === datosDocumentos.clasificacion.trabajo) {
								$(this).removeClass('jstree-checked').addClass(
										'jstree-unchecked');
								$(this).hide();
							} else if ($(this).children('input').val() === claseDoc) {
								$(this).removeClass('jstree-unchecked')
										.addClass('jstree-checked');

								if (documentosSelect === '') {
									documentosSelect += $(this).val();
								} else {
									documentosSelect += ',' + $(this).val();
								}

							} else {
								$(this).removeClass('jstree-checked').addClass(
										'jstree-unchecked');
							}

						});
	} else {
		$('#docuAsociados ul li').each(function() {
			$(this).show();
			$(this).removeClass('jstree-unchecked').addClass('jstree-checked');

			if (documentosSelect === '') {
				documentosSelect += $(this).val();
			} else {
				documentosSelect += ',' + $(this).val();
			}
		});
	}
}

function finInicializarConfigTarea(){
	$("#recargaPorFormatoSwitch_tareaConfig").on('switch-change', function(event, state) {
        if (state.value){ //Activando
            $("#sobreNumPal_tareaConfig").attr("disabled", false);
            $("#sobreNumPal_tareaConfig").rules("add", "required");
        }else{
            $("#sobreNumPal_tareaConfig").attr("disabled", true);
            $("#sobreNumPal_tareaConfig").rules("remove", "required");
            $("#sobreNumPal_tareaConfig").val("");
        }
    });
    
    $("#recargoPorSwitch_tareaConfig").on('switch-change', function(event, state) {
        if (state.value){ //Activando
            $("#porcentajeRecarga_tareaConfig").attr("disabled", false);
            $("#porcentajeRecarga_tareaConfig").rules("add", "required");
        }else{
            $("#porcentajeRecarga_tareaConfig").attr("disabled", true);
            $("#porcentajeRecarga_tareaConfig").rules("remove", "required");
            $('#porcentajeRecarga_tareaConfig').val("");
        }
        porRecargaChange();
    });
	
    $("#porcentajeRecarga_tareaConfig").change(function(){
		porRecargaChange();
	});
    
	fnObtenerDocumentosExp();
	if (ids != null) {
		codTarea = ids[0];
		fnObtenerDatosTarea(ids[0]);
	} else {
		codTarea = '';
		documentosSelect = '';
		cambiarValorSwitchRevisarTraduccion(true, false);
	}
	$('#fechaInicio_detail_table').rup_date("disable");
	$('#horaInicio_detail_table').attr('disabled','disabled');	
	$('#fechaFin_detail_table').rup_date("disable");	
	$('#horaFin_detail_table').attr('disabled','disabled');	
	
	$('#fechaInicio_detail_table').on("blur", function() {
		fnCalcularHorasPrevistasInterpretacion();
	});

	$('#horaInicio_detail_table').on("blur", function() {
		fnCalcularHorasPrevistasInterpretacion();
	});

	$('#fechaFin_detail_table').on("blur", function() {
		fnCalcularHorasPrevistasInterpretacion();
		comprobarFechaHastaSuperaFechaFinExpediente();
	});

	$('#horaFin_detail_table').on("blur", function() {
		fnCalcularHorasPrevistasInterpretacion();
		comprobarFechaHastaSuperaFechaFinExpediente();
	});
			
	$("#crearConfigurarDialog").rup_dialog('open');
    
	$('input[name="orden"]').on('blur', function() {
		if($("#orden_detail_table").val() == ""){
			// TODO eliminar datos de fecha ini, fecha fin y bloquearlas
			$("#fechaInicio_detail_table").val("");
			$("#horaInicio_detail_table").val("");		
			$("#fechaFin_detail_table").val("");
			$("#horaFin_detail_table").val("");	
			$("#horasPrevistasIZO").text("");	
			$("#horasPrevistasProveedor").text("");	
			
			$('#fechaInicio_detail_table').rup_date("disable");		
			$('#horaInicio_detail_table').attr('disabled','disabled');	
			$('#fechaFin_detail_table').rup_date("disable");			
			$('#horaFin_detail_table').attr('disabled','disabled');	
			mostrarMensajeFechaFinExcedeFechaFinExp(false);
		}else{
			if($("#fechaInicio_detail_table").val()==""){
				fnCalcularFechaIni();
			}
		}
	});

    $("#facturable_detail_table").on("change",function(){
        mostrarLote();
    });
    
	var jsonObj = {
			"anyo": anyoExpediente,
			"numExp": idExpediente
	}
	$.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/bitacoraexpediente/getCabeceraBitacora",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			$("#numExpediente_detail_table").text(data.anyoNumExpConcatenado);
			$("#tipoExpediente_detail_table").text($.rup.i18nParse($.rup.i18n.app,"tipo_expediente."+data.idTipoExpediente));
			$("#solicitante_detail_table").text(data.gestorExpediente.entidad.descEu);
			$("#contacto_detail_table").text(data.gestorExpediente.solicitante.nombreCompleto);
			if(!data.gestorExpediente.solicitante.solicitanteVinculado){
				$("#contacto_detail_table").css("text-decoration","line-through");
			}

			if (tipoExp === tipoExpedienteInter){
				$("#divplanificacionInfoInter").show();
    			$("#divplanificacionInfoTradRev").hide();
    			$('#fechaHoraIniInter_filter').text(fechaIniExpediente === null ? "" : fechaIniExpediente);
    			$('#fechaHoraFinInter_filter').text(fechaFinExpediente === null ? "" : fechaFinExpediente);
			} else {
				$("#divplanificacionInfoInter").hide();
    			$("#divplanificacionInfoTradRev").show();
    			
    			$('#numPalIZO_filter').text(numPalIZO_filter);
    			$('#trConcor084_filter').text(trConcor084_filter);
    			$('#trConcor8594_filter').text(trConcor8594_filter);
    			$('#trConcor9599_filter').text(trConcor9599_filter);
    			$('#trConcor100_filter').text(trConcor100_filter);
    			$('#grTrabajo_filter').text(grTrabajo);
    			$('#fechaFinExpediente_filter').text(fechaFinExpediente === null ? "" : fechaFinExpediente);
			}
			var jsonObjAux = {
				"anyo": anyoExpediente,
				"numExp": idExpediente,
				"idTarea": $('#idTarea_detail_table').val(),
				"orden": $('#orden_detail_table').val()
			}
			$.ajax({
				type : "POST",
				url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/getAsignadoTareaRevisionAnterior",
				dataType : "json",
				contentType : 'application/json',
				data : $.toJSON(jsonObjAux),
				cache : false,
				success : function(data) {
					if (data){
						$('#asignadoTareaRevAnt_detail_table').val(data.personaAsignada.dni);
						// tipo de tarea entregaclientetraduccion y 
						// la persona que la realiza es  distinta a la que realiza tarea anterior de revisarTraduccionExterna o revisarTraduccionInterna
						if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
								$('#tipRev').attr('hidden', false);	
								validarTipoRevision();
						}
					}
					desbloquearPantalla();
				},
				error : function(e){
					if (e){
						
					}
				}
			});
		}
	});
}

function callbackCrearConfigTarea() {
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"),finInicializarConfigTarea);
    
}

function esTareaEntregaClienteTradYAsignadoDistintoAntRev(){
	return $('#tipoTarea_detail_table').rup_combo("getRupValue") && 
			$('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion &&
			personaDistintaARevision();
}

function porRecargaChange(){
	var valor = parseInt($("#porcentajeRecarga_tareaConfig").val());
	if(valor!= '' && valor < 0 || valor > 100){
		$('#crearConfigurar_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajeMenorCien, "error");
		$("#porcentajeRecarga_tareaConfig").addClass("error");
	} else {
		$("#porcentajeRecarga_tareaConfig").removeClass("error");
	}
}

function mostrarLote() {
    if (($("#tipoTarea_detail_table").val() === datosTareas.tiposTarea.traducir
            || $("#tipoTarea_detail_table").val() === datosTareas.tiposTarea.revisar)
            && $("#facturable_detail_table").bootstrapSwitch('state')
            && recursoExterno) {
    	
    	configTareaProveedor = true;
        
        var lote = {
            "idLote" : $("#idLote").val()
        };
        
        var jsonObject = {
            "idTarea" : $("#idTarea_detail_table").val(),
            "lotes" : lote
        };

        $.ajax({
            type : "POST",
            url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/datosRecargoLote",
            dataType : 'json',
            contentType : 'application/json',
            data : $.toJSON(jsonObject),
            async : false,
            cache : false,
            success : function(data) {
                $("#formato_tareaConfig").text(data.lotes.porRecargoFormato + "%");
                $("#apremio_tareaConfig").text(data.lotes.porRecargoApremio + "%");
                apremio = data.lotes.porRecargoApremio;
                $("#sobreNumPal_tareaConfig").attr("disabled", true);
                $("#porcentajeRecarga_tareaConfig").attr("disabled", true);
                if (data.datosPagoProveedores != null) {
                    if (data.datosPagoProveedores.indRecargoFormato === "S") {
                        $("#recargaPorFormatoSwitch_tareaConfig").bootstrapSwitch('setState', true);
                        $("#sobreNumPal_tareaConfig").val(data.datosPagoProveedores.numPalRecargoFormato);
                    } else {
                        $("#recargaPorFormatoSwitch_tareaConfig").bootstrapSwitch('setState', false);
                    }
                    
                    if (data.datosPagoProveedores.indRecargoApremio === "S") {
                        $("#recargoPorSwitch_tareaConfig").bootstrapSwitch('setState', true);
                        $("#porcentajeRecarga_tareaConfig").val(data.datosPagoProveedores.porRecargoApremio);
                    } else {
                        $("#recargoPorSwitch_tareaConfig").bootstrapSwitch('setState', false);
                    }
	            } else {
	                $("#recargaPorFormatoSwitch_tareaConfig").bootstrapSwitch('setState', false);
	                $("#recargoPorSwitch_tareaConfig").bootstrapSwitch('setState', false);
	            }
                $("#configTareaProveedorDiv").show();
            }
        });
    } else {
        $("#configTareaProveedorDiv").hide();
        $("#formato_tareaConfig").text('');
        $("#apremio_tareaConfig").text('');
        $("#porcentajeRecarga_tareaConfig").val('');
        $("#sobreNumPal_tareaConfig").val('');
        $("#recargaPorFormatoSwitch_tareaConfig").bootstrapSwitch('setState', false);
        $("#recargoPorSwitch_tareaConfig").bootstrapSwitch('setState', false);
        configTareaProveedor = false;
    }
    
}


/*
 * **************************** UTILIDADES - FIN ****************************
 */

inicializarDialogo();

jQuery(function($) {
	jQuery.ajaxSetup({
		cache : false
	});
	
	$('#crearConfigurar_feedback').rup_feedback({
		block : false
	});

	$("#recursoInterno_detail_table").click(function() {
		// Es necesario eliminar buscadorPersonas para que
		// buscadorPersonasIZO que carga
		// los datos del personal IZO se cargue correctamente
		$("#buscadorPersonas").remove();
		$("#buscadorPersonasIZO").remove();
		$("#crearConfigurarDiv")
				.append(
						'<div id="buscadorPersonasIZO" style="display:none"></div>');
		$("#buscadorPersonasIZO").buscador_personas({
			destino : 'I',
			multiselect : false,
			anyo : anyoExpediente,
			numExp : idExpediente,
			callback : seleccionarPersona
		});
		$("#buscadorPersonasIZO").buscador_personas("open");
		$("#camposTipoEntidad").attr('hidden', true);
        recursoExterno = false;
        mostrarLote();
	});

	$("#recursoExterno_detail_table").click(function() {
		if ($("#tipoTarea_detail_table").val() === datosTareas.tiposTarea.interpretar) {
			// Es necesario eliminar buscadorPersonasIZO para
			// que el buscador de personas que carga
			// los datos de los proveedores se cargue
			// correctamente
			$("#buscadorPersonasIZO").remove();
			$("#buscadorPersonas").remove();
			$("#crearConfigurarDiv")
					.append(
							'<div id="buscadorPersonas" style="display:none"></div>');
			$("#buscadorPersonas").buscador_personas({
				destino : 'P',
				multiselect : false,
				callback : seleccionarProveedor
			});
			$("#buscadorPersonas").buscador_personas("open");
			$("#camposTipoEntidad").attr('hidden', false);
		} else if ($("#tipoTarea_detail_table").val() === datosTareas.tiposTarea.traducir
				|| $("#tipoTarea_detail_table").val() === datosTareas.tiposTarea.revisar) {
			$("#buscadorLotes").remove();
			$("#crearConfigurarDiv")
					.append(
							'<div id="buscadorLotes" style="display:none"></div>');
			$("#buscadorLotes").buscador_lotes({
				multiselect : false,
				anyo : anyoExpediente,
				numExp : idExpediente,
				documentos : documentosSelect,
				callback : seleccionarLotes
			});
			$("#buscadorLotes").buscador_lotes("open");
		}
	});

	function seleccionarPersona(obj, personas) {
		if (obj != null && obj.length > 0) {
			for (var i = 0; i < obj.length; i++) {
				var persona = obj[i];

				fnLimpiarCamposLote();
				fnMostrarAsignadoAPersona();
				cambiarValorSwitchRevisarTraduccion(false,false);
				$("#personaAsignada_detail_table").val(persona.nombreCompleto);
				$("#dniAsignacion").val(persona.dni);
				$("#tipoEntidad").val(persona.entidad.tipo);
				$("#idEntidad").val(persona.entidad.codigo);
				$('#recursoAsignacion').val(datosTareas.tipoRecurso.interno);
				validarCampos();
				fnCalcularHorasPrevistasTraduccion();
				if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
						$('#tipRev').attr('hidden', false);	
				}

			}
		}
		if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
			$('#tipRev').attr('hidden', false);	
		} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") && 
					$('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion){
			$('#tipoRevision_detail_table').rup_combo("select", 0);
			$('#tipRev').attr('hidden', true);
		}
		validarTipoRevision();
	}

	function seleccionarProveedor(obj, personas) {
		if (obj != null && obj.length > 0) {
			for (var i = 0; i < obj.length; i++) {
				var persona = obj[i];
				var nombre = persona.nombreCompleto;
				if (persona.entidad.descAmpEu !== null
						&& persona.entidad.descAmpEu !== "") {
					nombre += " (" + persona.entidad.descAmpEu + ")";
				}

				fnLimpiarCamposLote();
				cambiarValorSwitchRevisarTraduccion(true,true);
				fnMostrarAsignadoAPersona();
				$("#personaAsignada_detail_table").val(nombre);
				$("#dniAsignacion").val(persona.dni);
				$("#tipoEntidad").val(persona.entidad.tipo);
				$("#idEntidad").val(persona.entidad.codigo);
				$('#recursoAsignacion').val(datosTareas.tipoRecurso.externo);
				validarCampos();
				fnCalcularHorasPrevistasTraduccion();
			}
		}
        mostrarLote();
		if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
			$('#tipRev').attr('hidden', false);	
		} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") && 
					$('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion){
			$('#tipoRevision_detail_table').rup_combo("select", 0);
			$('#tipRev').attr('hidden', true);
		}
		validarTipoRevision()
	}

	function seleccionarLotes(obj, lotes) {
		if (obj != null && obj.length > 0) {
			for (var i = 0; i < obj.length; i++) {
				var lote = obj[i];
				var nombreLote = lote.nombreLote;
				if (lote["empresasProveedoras.descAmpEu"] !== null
						&& lote["empresasProveedoras.descAmpEu"] !== "") {
					nombreLote += " (" + lote["empresasProveedoras.descAmpEu"]
							+ ")";
				}

				fnLimpiarCamposPersona();
				cambiarValorSwitchRevisarTraduccion(true,true);
				fnMostrarAsignadoALote();
				$("#idLote").val(lote.idLote);
				$("#loteAsignado_detail_table").val(nombreLote);
				$("#tipoEntidadLote").val(lote["empresasProveedoras.tipo"]);
				$("#idEntidadLote").val(lote["empresasProveedoras.codigo"]);
				$('#recursoAsignacion').val(datosTareas.tipoRecurso.externo);
				validarCampos();

			}
            recursoExterno = true;
            mostrarLote();
			fnCalcularHorasPrevistasTraduccion();
        } else {
            
            recursoExterno = false;
            mostrarLote();
		}
		if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
			$('#tipRev').attr('hidden', false);	
		} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") && 
					$('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion){
			$('#tipoRevision_detail_table').rup_combo("select", 0);
			$('#tipRev').attr('hidden', true);
		}
		validarTipoRevision();
	}

	$("#autoasignar_detail_table").click(function(event) {
		event.preventDefault();
        event.stopImmediatePropagation(); 
		fnAutoasignarTarea();
        recursoExterno = false;
        mostrarLote();
		cambiarValorSwitchRevisarTraduccion(false,false);
		// si se modifica el tipo de recurso asignado, llamamos a la funcion correspondiente por si se debe volver a 
		// calcular las horas de traduccion.
		fnCalcularHorasPrevistasTraduccion();
		if (esTareaEntregaClienteTradYAsignadoDistintoAntRev()){
			$('#tipRev').attr('hidden', false);	
		} else if ($('#tipoTarea_detail_table').rup_combo("getRupValue") && 
					$('#tipoTarea_detail_table').rup_combo("getRupValue") == datosTareas.tiposTarea.entregaClienteTraduccion){
			$('#tipoRevision_detail_table').rup_combo("select", 0);
			$('#tipRev').attr('hidden', true);
		}
		validarTipoRevision();
	});

	$("#eliminar_detail_table").click(function(event) {
		event.preventDefault();
        event.stopImmediatePropagation(); 
		fnLimpiarAsignacion();
		cambiarValorSwitchRevisarTraduccion(true,false);
        recursoExterno = false;
        mostrarLote();
		// TODO mirar limpiar campos
	});
	
	$("#selectTodos").on("change",function() {
		if ($("#selectTodos").is(':checked')) {
			$("[id^='checkDocumento']").removeClass('jstree-unchecked')
					.addClass('jstree-checked');

			documentosSelect = '';

			// Recogemos los ids de todos los documentos
			$('#docuAsociados ul li').each(function() {

				if (documentosSelect === '') {
					documentosSelect += $(this).val();
				} else {
					documentosSelect += ',' + $(this).val();
				}

			});
		} else {
			$("[id^='checkDocumento']").removeClass('jstree-checked')
					.addClass('jstree-unchecked');
			documentosSelect = '';
		}

		fnCalcularHorasPrevistasTraduccion();
	});

	$("#linkDetalleExpediente").on('click',function(e) {
		var url = "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/"
				+ anyoExpediente + "/" + idExpediente;
		window.open(url);
	});

	// Validación de documentos seleccionados
	$.validator.addMethod("validateSelectList", function(valor) {

		var documentoSeleccionado = false;
		
		if (tipoExp === datosExp.tipoExp.interpretacion	) {
			documentoSeleccionado = true;
			$('#divDocumentosSeleccionados').css("height", "0px");
		} else if (documentosSelect) {
			
			var idTipoTarea = $('#tipoTarea_detail_table').rup_combo("getRupValue");
			var documentosSeleccionados = documentosSelect.split(',');
			var idDoc;
			var claseDocumento;

			if (idTipoTarea === datosTareas.tiposTarea.traducir
					|| idTipoTarea === datosTareas.tiposTarea.revisar 
					|| idTipoTarea === datosTareas.tiposTarea.noConformidadProveedor 
					|| idTipoTarea === datosTareas.tiposTarea.revisarTraduccionExterna) {
				
				for (var i = 0; i < documentosSeleccionados.length; i++) {
					
					idDoc = documentosSeleccionados[i];
					claseDocumento = $('#claseDocumento'+idDoc).val();

					if (claseDocumento === datosDocumentos.clasificacion.traduccion 
							|| claseDocumento === datosDocumentos.clasificacion.revision 
							|| claseDocumento === datosDocumentos.clasificacion.trabajo) {
						documentoSeleccionado = true;
					}
					
				}
				
				if (!documentoSeleccionado){
					$.validator.messages.validateSelectList = $.rup.i18nParse($.rup.i18n.app,"mensajes.seleccionDocTradRev");
					$('#divDocumentosSeleccionados').css("height", "auto");
				} else {
					$('#divDocumentosSeleccionados').css("height", "0px");
				}
				
			} else if (idTipoTarea === datosTareas.tiposTarea.entregaClienteTraduccion
					|| idTipoTarea === datosTareas.tiposTarea.tradEntregaClienteTraduccion
					|| idTipoTarea === datosTareas.tiposTarea.entregaClienteRevision
					|| esCorredaccion === 'S' ){
				// Deben estar seleccionados todos los docs de Trad/Rev...
				
				if ( esCorredaccion === 'S' ){
					if ( isEmpty(listaDocsTradRev) ){
						documentoSeleccionado = true;
					}
				}
				// Si es entregaClienteTraduccion, entregaClienteRevision o corredacción con docs de trad/rev
				if ( !documentoSeleccionado ){
					documentoSeleccionado = true;
					listaDocsTradRev.forEach(function(unDocTradRev) {
						var estaSeleccionadoDoc = documentosSeleccionados.filter(  function (x){ return (x == unDocTradRev.idDoc )  }  );
						if (isEmpty(estaSeleccionadoDoc)){
							documentoSeleccionado = false;
						}
					});
				}
				
				
				if (!documentoSeleccionado){
					$.validator.messages.validateSelectList = $.rup.i18nParse($.rup.i18n.app,"mensajes.seleccionTodosDocTradRev");
					$('#divDocumentosSeleccionados').css("height", "auto");
				} else {
					$('#divDocumentosSeleccionados').css("height", "0px");
				}
				
			} else {
				documentoSeleccionado = true;
				$('#divDocumentosSeleccionados').css("height", "0px");
			}
			
		} else if (esCorredaccion === 'S' ){
			documentoSeleccionado = true;
			$('#divDocumentosSeleccionados').css("height", "0px");
		}else {
			$('#divDocumentosSeleccionados').css("height", "auto");
			$.validator.messages.validateSelectList = $.rup.i18nParse($.rup.i18n.app,"mensajes.seleccionDocumentos");
		}

		return documentoSeleccionado;

	}, $.validator.messages.validateSelectList);

	// Validacion del orden de la tarea
	jQuery.validator
			.addMethod(
					"validateOrden",
					function(value, element, params) {

						var ordenValido = true;
						if ($('#tipoTarea_detail_table').rup_combo(
								"getRupValue") !== ''
								&& value !== '') {

							var tipoTarea = {
								"id015" : $('#tipoTarea_detail_table')
										.rup_combo("getRupValue")
							}

							var jsonObject = {
								"anyo" : anyoExpediente,
								"numExp" : idExpediente,
								"tipoTarea" : tipoTarea,
								"orden" : value,
								"idTarea" : $("#idTarea_detail_table").val()
							};

							$
									.ajax({
										type : "POST",
										url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarOrdenTareas",
										dataType : 'json',
										contentType : 'application/json',
										data : $.toJSON(jsonObject),
										async : false,
										cache : false,
										success : function(data) {
											if (data === 1) {
												ordenValido = false;
											} else {
												$('#orden_detail_table')
														.removeClass('error');
												$('#orden_detail_table-error')
														.remove();
											}

										}
									});

						} else {
							$('#orden_detail_table').removeClass('error');
							$('#orden_detail_table-error').remove();
						}
						return ordenValido;

					}, $.rup.i18n.app.validaciones.ordenTarea);

	// Validacion del tipo de la tarea
	jQuery.validator.addMethod("validateTipoTarea",function(value, element, params) {

		var tipoTareaValido = true;

		if ($('#tipoTarea_detail_table')
				.rup_combo("isDisabled")) {
			// En caso de que el rup_combo tipoTarea esté
			// deshabilitado, estamos modificando una tarea
			return tipoTareaValido;
		} else {
			if (value !== '') {
				var tipoTarea = {
					"id015" : value
				}
				var jsonObject = {
					"anyo" : anyoExpediente,
					"numExp" : idExpediente,
					"tipoTarea" : tipoTarea
				};

				$.ajax({
					type : "POST",
					url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarTipoTarea",
					dataType : 'json',
					contentType : 'application/json',
					data : $.toJSON(jsonObject),
					async : false,
					cache : false,
					success : function(data) {
						if (data === 1) {
							tipoTareaValido = false;
						} else {
							$('#tipoTarea_detail_table')
									.removeClass(
											'error');
							$(
									'#tipoTarea_detail_table-error')
									.remove();
						}
					}
				});
			} else {
				$('#tipoTarea_detail_table').removeClass('error');
				$('#tipoTarea_detail_table-error').remove();
			}
			return tipoTareaValido;
		}

	}, $.rup.i18n.app.validaciones.tipoTarea);

	// Validacion de fecha fin de la tarea
	jQuery.validator.addMethod("validateFechaFin",function(value, element, params) {

		var fechaFinValida = true;

		if ($('#tipoTarea_detail_table').rup_combo(
				"getRupValue") !== ''
				&& $('#horaFin_detail_table').val() !== ''
				&& value !== '') {
			var tipoTarea = {
				"id015" : $('#tipoTarea_detail_table').rup_combo("getRupValue")
			}

			var jsonObject = {
				"anyo" : anyoExpediente,
				"numExp" : idExpediente,
				"tipoTarea" : tipoTarea,
				"fechaFin" : value,
				"horaFin" : $('#horaFin_detail_table').val()
			};

			$.ajax({
				type : "POST",
				url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarFechaFinTarea",
				dataType : 'json',
				contentType : 'application/json',
				data : $.toJSON(jsonObject),
				async : false,
				cache : false,
				success : function(data) {
					if (data === 1) {
						fechaFinValida = false;
						mostrarMensajeFechaFinExcedeFechaFinExp(false);
					} else {
						$('#fechaFin_detail_table')
								.removeClass('error');
						$('#fechaFin_detail_table-error')
								.remove();
					}
				}
			});
		} else {
			$('#fechaFin_detail_table').removeClass('error');
			$('#fechaFin_detail_table-error').remove();
		}
		return fechaFinValida;
	}, $.rup.i18n.app.validaciones.fechaFin);

	// ocultarCapasHoras();
	$('#divDocumentosSeleccionados').css("height", "0px");

	if (tipoExp !== datosExp.tipoExp.interpretacion) {
		if (esCorredaccion === 'S') {
			$("label[for=documentacion_detail_table]").removeClass("asteriscoDosPuntos").removeClass("col-30por").addClass("dosPuntos");
		} else {
			$("label[for=documentacion_detail_table]").removeClass("dosPuntos").addClass("asteriscoDosPuntos").addClass("col-30por");
		}
	} else {
		$("label[for=documentacion_detail_table]").removeClass("asteriscoDosPuntos").removeClass("col-30por").addClass("dosPuntos");
	}
	
	$('#ultimasInter').on("click", function (){
		window.open("/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/mostrarUltimasTareasInterpretacion");
	});

	llamadasFinalizadas("finInicializarConfigTarea");

});
