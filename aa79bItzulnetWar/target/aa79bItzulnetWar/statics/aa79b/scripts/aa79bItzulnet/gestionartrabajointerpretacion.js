var esHoraOblig = false;
var elFormulario;
var presupuestoInterCalculado = false;
var idRequerimiento;
function cargarDatosInicio() {
	$.ajax({
		url : '/aa79bItzulnetWar/ejecuciontarea/findTareaInterpretacion/' + idTarea,
		success : function(data, textStatus, jqXHR) {
			var newData = JSON.parse(data);
			idRequerimiento = newData.idRequerimiento;
			// Se comprueba que se requiera presupuesto para mostrar o no el div de presupuesto
			if (newData.indPresupuesto == "N" ) {
				$("#presupuesto_div").css("display", "none");
				$("#checkReqPresupuesto").bootstrapSwitch('setState', false);
			} else {
				$("#checkReqPresupuesto").bootstrapSwitch('setState', true);
			}
			
			// Se setean los valores que vienen cargados por defecto
			$("#fechaIni_form").text(newData.fechaIni);
			$("#horaIni_form").text(newData.horaIni);
			$("#fechaFin_form").text(newData.fechaFin);
			$("#horaFin_form").text(newData.horaFin);
			
			if('S'.localeCompare(newData.indVisible)==0){
				$('#checkVisible').bootstrapSwitch('setState', true);
			}else{
				$('#checkVisible').bootstrapSwitch('setState', false);
			}
			if(newData.fechaLimite){
				$('#fechaLimite').val(newData.fechaLimite);
				$('#horaLimite').val(newData.horaLimite);
				$('#minFechaLimiteAceptacion').val(newData.fechaLimite);
				$('#minHoraLimiteAceptacion').val(newData.horaLimite);
			}
			if(newData.fechaLimiteSeleccionable){
				$('#fechaLimSeleccionable').val(newData.fechaLimiteSeleccionable);
				$('#horaLimSeleccionable').val(newData.horaLimiteSeleccionable);
				$('#fechaLimite').rup_date("option","maxDate", newData.fechaLimiteSeleccionable);
			}
			
			if (ejecutarTareaConsulta) {
				$("#numInterpretes_form").val(newData.numInterpretes);
				$("#horasPrevistasInterpretacion_form").val(newData.horasPrevistasInterpretacion);
//				$("#importe_form").val(addDecimalsFormat(newData.presupuesto,2,true));
				$("#importe_form").val(newData.presupuesto);
				$("#contenido_form").val(newData.observaciones);
//				$("#fechaLimite").val(newData.fechaLimite);
//				$("#horaLimite").val(newData.horaLimite);
				mostrarEnlaceVer();
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			}/*else{
				$.ajax({
					url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularHorasPrevistasInter/'+anyoExpediente+'/'+idExpediente,
					success : function(data) {
						$("#horasPrevistasInterpretacion_form").val(data.replace(/\"/g, ""));
						elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
					},
					error : function(e, XMLHttpRequest, textStatus, errorThrown) {
						alert('Error recuperando datos del paso 1');
					}
				});
			}*/
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('Error recuperando datos del paso 2');
		}
	});
}

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
			}
		});
		return false;
	}
	return true;
}


function calcularPresupuestoInter(){
	if ( isNotEmpty($('#horasPrevistasInterpretacion_form').val() ) && isNotEmpty($('#numInterpretes_form').val() ) ){
		
		var gestionInterpretacion = {
			"numInterpretes": $('#numInterpretes_form').val(),
			"horasPrevistasInterpretacion": $('#horasPrevistasInterpretacion_form').val()
		};
		var jsonObject = 
		{
		    "idTarea": idTarea,
			"anyo": anyoExpediente, 
		    "numExp": idExpediente,
		    "gestionInterpretacion": gestionInterpretacion
		};
		$.ajax({
			url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularImportePresupuestoInter/',
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json',
			data: $.toJSON(jsonObject),
			async: false,
			cache: false,
			success : function(data) {
				if (data !== -1){
					presupuestoInterCalculado = true;
//					$("#importe_form").val(addDecimalsFormat(data,2,true));
					$("#importe_form").setImporte(data);
					mostrarEnlaceVer();
				}else{
					presupuestoInterCalculado = false;
					$('#ejecutarTareaDialog_feedback').rup_feedback("set",$.rup.i18n.app.mensajes.errorcalculoPptoInterp, "error");
				}
			},
			error : function(e, XMLHttpRequest, textStatus, errorThrown) {
				presupuestoInterCalculado = false;
			}
		});
		
	}	
}


function finalizarTarea() {
	if(!('none'.localeCompare($("#presupuesto_div").css('display'))==0)){
		//si entra aqui el check de 'visible' es visible --> para finalizar la tarea debe estar a Si
		if(!$('#checkVisible').bootstrapSwitch('state')){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set",$.rup.i18n.app.mensajes.tareaTradosCheckVisibleATrue, "error");
			return false;
		}
	}
	if(!"".localeCompare($('#fechaLimite').val())==0){
		$("#fechaLimite").rules("add", { "fechaMayorOIgualQueValorCampo": $('#minFechaLimiteAceptacion').val()});
		$("#horaLimite").rules("add", { "horaFechaMayorOIgualQueValorCampo":  [$('#minHoraLimiteAceptacion').val() , 'fechaLimite', 'minFechaLimiteAceptacion' ] });
		$("#fechaLimite").rules("add", { "fechaMenorOIgualQueValorCampo": $('#fechaLimSeleccionable').val()}); 
		$("#horaLimite").rules("add", { "horaFechaMenorOIgualQueValorCampo":  [$('#horaLimSeleccionable').val() , 'fechaLimite', 'fechaLimSeleccionable'] }); 
	}
	
	if ($("#ejecutarTareaDialog_form").valid()){
		if (presupuestoInterCalculado){
			if ($("#confirmartarea").length) {
				$("#confirmartarea").remove();
				$("#confirmartarea").rup_dialog('destroy');
				$("#divTareasExpedientes").append(
						'<div id="confirmartarea" style="display:none"></div>');
			}
		
			$("#confirmartarea").confirmar_tarea({
				esHoraOblig : esHoraOblig,
				tieneHora : true,
				modalSelector : "ejecutarTareaDialog"
			});
			$("#confirmartarea").confirmar_tarea("open");
			elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
		}else{
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorcalculoPptoInterp, "error");
		}
	}else{
		$("#fechaLimite").rules("remove", "fechaMenorOIgualQueValorCampo");
		$("#horaLimite").rules("remove", "horaFechaMenorOIgualQueValorCampo");
	}
}

function mostrarEnlaceVer() {
	if ($("#checkReqPresupuesto").bootstrapSwitch('state')) {
		$("#enlaceVerPresupuesto").show();
	} else {
		$("#enlaceVerPresupuesto").hide();
	}
}

function verPresupuesto() {
	window.open("/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerDocumentoPresupuestoInterpretacion/"+idExpediente+'/'+anyoExpediente+'/'+idRequerimiento);
}

jQuery(function($) {
	cargarDatosInicio();
	
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

	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			css : "fa fa-arrow-left",
			id : "volver",
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
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	$("#checkReqPresupuesto").bootstrapSwitch().bootstrapSwitch('setSizeClass','switch-small').bootstrapSwitch('setOffLabel',
			$.rup.i18n.app.comun.no).bootstrapSwitch('setOnLabel',$.rup.i18n.app.comun.si);

	$("#checkVisible").bootstrapSwitch().bootstrapSwitch('setSizeClass','switch-small').bootstrapSwitch('setOffLabel',
			$.rup.i18n.app.comun.no).bootstrapSwitch('setOnLabel',$.rup.i18n.app.comun.si);
	
	
	$("#ejecutarTareaDialog_form").rup_form({
		url : "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		feedback : $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault : false,
		showErrorsInFeedback : true,
		showFieldErrorsInFeedback : false,
		dataType : "json",
		type : "POST",
//		beforeSubmit: function(){
//			$(".decimal").each(function(){
//				$(this).val(removeDecimalsFormat($(this).val()));
//			});
//			return true;
//		},
		validate : {
			rules:{ 
				"gestionInterpretacion.numInterpretes" : {required : true, maxlength: 2, integer: true},
				"gestionInterpretacion.horasPrevistasInterpretacion" : {required : true, maxlength: 10,hora:true},
				"gestionInterpretacion.indVisible" :  {required : true},
				"gestionInterpretacion.presupuesto": {required : true, maxlength: 9, customDecimal: ["0", "999999,99", 0, 999999.99]},
				"subsanacion.fechaLimite": {required: true, date: true},
				"subsanacion.horaLimite": {required: true, hora: true, maxlength:5}
			},
			showFieldErrorAsDefault : false,
			showErrorsInFeedback : true,
			showFieldErrorsInFeedback : false
		},
		success : function() {
//			$(".decimal").each(function(){
//				$(this).val(addDecimalsFormat($(this).val(),2));
//			});
			elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			// Cerrar el diálogo, mostrar feedback y recargar la
			// pantalla o la tabla
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
	
	
	$('#fechaLimite').rup_date({
		minDate: new Date()
	});
	
	$('#numInterpretes_form').on("blur", function() {
		calcularPresupuestoInter();
	});
	$('#horasPrevistasInterpretacion_form').on("blur", function() {
		calcularPresupuestoInter();
	});
	
	$("#checkPresupuesto .has-switch").css("float","left");
	
});