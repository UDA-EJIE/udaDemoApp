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

function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/2/'+idFichero);
}

function inicializarDialogo() {

	$.rup_date({
		from : "fechaInicioTarea_detail_table",
		to : "fechaFinTarea_detail_table",
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

	$("#crearConfigurar_filter_form").rup_validate(
			{
				feedback : $('#crearConfigurar_feedback'),
				liveCheckingErrors : false,
				block : false,
				delay : 3000,
				gotoTop : true,
				rules : {
					"tipoTarea.id015" : {
						required : true
					},
					"orden" : {
						required : true,
						maxlength : 2,
						integer : true
					},
					"fechaInicio" : {
						required : true,
						date : true
					},
					"horaInicio" : {
						required : true,
						hora : true,
						maxlength : 5
					}
					,
					"fechaFin" : {
						date : true,
						fechaHastaMayorPorId : "fechaInicioTarea_detail_table"
					},
					"horaFin" : {
						hora : true,
						maxlength : 5,
						horaFechaHastaMayorPorId : [
								"fechaInicioTarea_detail_table",
								"fechaFinTarea_detail_table",
								"horaInicioTarea_detail_table" ]
					},
					"observaciones" : {
						maxlength : 2000
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
		url : '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/findConfTarea/' + idTarea,
		dataType : 'json',
		contentType : 'application/json',
		cache : false,
		success : function(data) {
			if (data !== null) {
				if (data.idTarea != null) {
					$('#idTarea_detail_table').val(data.idTarea);
				}

				if (data.tipoTarea != null) {
					var descTipoTarea = $.rup.lang === 'es' ? data.tipoTarea.descEs015
							: data.tipoTarea.descEu015;

					fnModificarTipoTareaCombo(data.tipoTarea.id015,
							descTipoTarea);

					$('#tipoTareaTrabajo_detail_table').rup_combo(
							"setRupValue", data.tipoTarea.id015);
				}

				$('#ordenTarea_detail_table').val(data.orden);
				$('#fechaInicioTarea_detail_table').val(data.fechaInicio);
				$('#horaInicioTarea_detail_table').val(data.horaInicio);
				$('#fechaFinTarea_detail_table').val(data.fechaFin);
				$('#horaFinTarea_detail_table').val(data.horaFin);
				
				
				$('#observacionesTarea_detail_table')
						.val(data.observaciones);
				if (data.personaAsignada != null) {
					$('#personaAsignadaTarea_detail_table').val(
							data.personaAsignada.nombreCompleto);
					$('#dniAsignacion').val(
							data.personaAsignada.dni);
					if (data.personaAsignada.dni != null){
						validarCamposAdicionales();
					}
				}
				if (data.documentoEntrada != null) {
					var destinoUpload = '0';
					$('#idFichero_'+destinoUpload).val(data.documentoEntrada.idFichero);
					$('#nombre_'+destinoUpload).val(data.documentoEntrada.nombre);
					$('#extensionDoc_'+destinoUpload).val(data.documentoEntrada.extension);
					$('#tamanoDoc_'+destinoUpload).val(data.documentoEntrada.tamano);
					$('#contentType_'+destinoUpload).val(data.documentoEntrada.contentType);
					$('#oidFichero_'+destinoUpload).val('');
					$('#encriptado_'+destinoUpload).val(data.documentoEntrada.encriptado);
					
					if (data.documentoEntrada.idFichero != null){
						var enlace = '<a href="#" onclick="descargarDocumentoGeneral('+ data.documentoEntrada.idFichero +')" class="descargarDocTarea" data-idtrabajo="'+trabajoSeleccionado.idTrabajo+'" >'+data.documentoEntrada.nombre+'</a> ('+conversionKB(data.documentoEntrada.tamano)+' KB.)';
						$('#enlaceDescargaDetalle_'+destinoUpload).html(enlace);
					}
				}
				
				
				if (data.estadoEjecucion == datosTareas.estadoEjecucion.ejecutada) {
					modificarTareaConsulta = true;
					dialogoSoloConsultaConfiguracionTareas(true,
							"crearConfigurarDialog");
					initFormTarea = '';
					$('#btnGuardar').hide();
					$('#divLinkEliminar').hide();
					$('#divLinkAutoasignar').hide();
					$('#divLinkRecursoInterno').hide();
					$('#recurExter').hide();
				} else {
					modificarTareaConsulta = false;
					dialogoSoloConsultaConfiguracionTareas(false,
							"crearConfigurarDialog");
					initFormTarea = $("#crearConfigurar_filter_form")
							.rup_form("formSerialize");
					$('#tipoTareaTrabajo_detail_table').rup_combo('disable');
					$('#ordenTarea_detail_table').removeAttr('disabled');
					inicializarDatosTarea();
				}
				$('#nombreFicheroInfo_0').attr('readonly', true);
			}
			desbloquearPantalla();
		},
		error : function() {
			desbloquearPantalla();
		}
	});

}
function inicializarDatosTarea() {
	$('#personaAsignadaTarea_detail_table').attr('readonly', 'readonly');
	$('#btnGuardar').show();
	$('#divLinkEliminar').show();
	$('#divLinkAutoasignar').show();
}

function limpiarDatosFormTarea() {
	$("#crearConfigurar_filter_form").rup_form("clearForm");
	$("[id^='checkDocumento']").removeClass('jstree-checked').addClass(
			'jstree-unchecked');
}

function cerrarModalCrearTarea() {
	$("#crearConfigurarDialog").rup_dialog('close');
	$('#tipoTareaTrabajo_detail_table').rup_combo("select", 0);
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

function validarCamposAdicionales() {
	$("#fechaFinTarea_detail_table").rules("add", "required");
	$("#horaFinTarea_detail_table").rules("add", "required");

	$("label[for=fechaFinTarea_detail_table]").removeClass("dosPuntos").addClass(
			"asteriscoDosPuntos");
}

function eliminarValidacionesCamposAdicionales() {
	$("#fechaFinTarea_detail_table").rules("remove", "required");
	$("#horaFinTarea_detail_table").rules("remove", "required");

	$("label[for=fechaFinTarea_detail_table]").removeClass("asteriscoDosPuntos")
			.addClass("dosPuntos");
}


function inicializarAyudas() {
	
	new Cleave('#fechaInicioTarea_detail_table', {
	    date: true,
	    datePattern: ['Y', 'm', 'd']
	});
	
	new Cleave('#horaInicioTarea_detail_table', {
	    time: true,
	    timePattern: ['h', 'm']
	});
	
	new Cleave('#fechaFinTarea_detail_table', {
	    date: true,
	    datePattern: ['Y', 'm', 'd']
	});
	
	new Cleave('#horaFinTarea_detail_table', {
	    time: true,
	    timePattern: ['h', 'm']
	});
}

function fnCrearTipoTareaCombo() {
	$('#tipoTareaTrabajo_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea?indVisibleTrabajo015=S",
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
		}
	});
}

function fnModificarTipoTareaCombo(idTipoTarea, descTipoTarea) {
	$('#tipoTareaTrabajo_detail_table').rup_combo({
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


function fnTienePersonaAsignada(){
	return $('#personaAsignadaTarea_detail_table').val() != "";
}

function fnInicializarCampoOrden() {
	if($('#ordenTarea_detail_table').val() == '0'){
		$('#ordenTarea_detail_table').val('');
	}
	$('#ordenTarea_detail_table').removeAttr('disabled');
}

function fnDeshabilitarCampoOrden() {
	$('#ordenTarea_detail_table').val('0');
	$('#ordenTarea_detail_table').attr('disabled', 'disabled');
}

function fnInicializarDatosTipoTarea() {
	$('#tipRev').attr('hidden', true);
	$('#recurExter').attr('hidden', true);
}

function comprobarExtensionValida( nombreFichero){
	var extension = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	var extensionOK = false;
	var jsonObject = 
	{
		"formato011": extension
	};	
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/comprobarextension'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: jsonObject	
	     ,async: false
	     ,cache: false
	   	 ,success:function(extensionValida){
	   		if (extensionValida > 0) {
	   			extensionOK =  true;
	   		}
	   	 }
   	 	,error: function(){
	   		$('#crearConfigurar_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		//scrollToFeedback();
	   		extensionOK =  false;
	   	 }
	 });			
	return extensionOK;	
}
function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function vaciarFileYDesbloquear(){
	$("#fichero").val('');
	desbloquearPantalla();
}

function scrollToFeedback(){
	$('html,body').animate({
	    scrollTop: $("#crearConfigurar_feedback").offset().top - 250
	}, 500);
}

function fnLimpiarCampos() {
	$('#fechaFinTarea_detail_table').val('');
	$('#horaFinTarea_detail_table').val('');
	$('#fechaFinTarea_detail_table').rup_date("disable");	
	$('#horaFinTarea_detail_table').attr('disabled','disabled');	
	fnLimpiarAsignacion();
	$('#observacionesTarea_detail_table').val('');
	mostrarMensajeFechaFinExcedeFechaFinExp(false);
}

function fnAutoasignarTarea() {

	$.ajax({
		type : 'GET',
		url : '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/autoasignartareas',
		dataType : 'json',
		contentType : 'application/json',
		async : false,
		success : function(data) {
			if (data !== null) {
				fnLimpiarCamposLote();
				$('#dniAsignacion').val(data.dni);
				$('#personaAsignadaTarea_detail_table').val(data.nombre);
				validarCamposAdicionales();
			}
		},
		error : function(error) {
			$('#crearConfigurar_feedback').rup_feedback(
					"set",
					$.rup.i18nParse($.rup.i18n.app,
							"mensajes.errorLlamadaAjax"), "error");
		}
	});

}

function fnLimpiarAsignacion() {
	fnLimpiarCamposPersona();
	eliminarValidacionesCamposAdicionales();
}

function fnLimpiarCamposPersona() {
	$("#personaAsignadaTarea_detail_table").val("");
	$("#dniAsignacion").val("");
}

function fnLimpiarCamposLote() {
	$("#idLote").val("");
	$("#loteAsignado_detail_table").val("");
	$("#tipoEntidadLote").val("");
	$("#idEntidadLote").val("");
	$('#recursoAsignacion').val("");
}




function callbackCrearConfigTarea() {
	if (ids != null) {
		codTarea = ids;
		fnObtenerDatosTarea(ids);
	} else {
		codTarea = '';
		documentosSelect = '';
	}
			
	$("#crearConfigurarDialog").rup_dialog('open');
    
	$("#idTrabajoTarea_detail_table").text(trabajoSeleccionado.idTrabajo);
	$("#descTrabajoTarea_detail_table").text(trabajoSeleccionado.descTrabajo);
	$("#fechaInicioTrabajo_detail_table").text(trabajoSeleccionado.fechaInicio);
	$("#fechaFinPrevistaTrabajo_detail_table").text(trabajoSeleccionado.fechaFinPrevista);
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

	$("#recursoInterno_detail_table")
		.click(
				function() {
					// Es necesario eliminar buscadorPersonas para que
					// buscadorPersonasIZO que carga
					// los datos del personal IZO se cargue correctamente
					$("#buscadorPersonasIZO").remove();
					$("#crearConfigurarDiv")
							.append(
									'<div id="buscadorPersonasIZO" style="display:none"></div>');
					$("#buscadorPersonasIZO").buscador_personas({
						destino : 'I',
						multiselect : false,
						callback : seleccionarPersona
					});
					$("#buscadorPersonasIZO").buscador_personas("open");
                    recursoExterno = false;
				});

	

	function seleccionarPersona(obj, personas) {
		if (obj != null && obj.length > 0) {
			for (var i = 0; i < obj.length; i++) {
				var persona = obj[i];

				$("#personaAsignadaTarea_detail_table").val(persona.nombreCompleto);
				$("#dniAsignacion").val(persona.dni);
				validarCamposAdicionales();

			}
		}

	}



	$("#autoasignar_detail_table").click(function(event) {
		event.preventDefault();
        event.stopImmediatePropagation(); 
		fnAutoasignarTarea();
        recursoExterno = false;
        validarCamposAdicionales();
	});

	$("#eliminar_detail_table").click(function(event) {
		event.preventDefault();
        event.stopImmediatePropagation(); 
		fnLimpiarAsignacion();
	});
	



	$("[id^='pidButton']").unbind("click");
	$("[id^='pidButton']").on("click", function() {
		if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
		else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
		else $("#idBotonUpload").val("0");
		$('#fichero').click();
	});
	$('#subidaFicheroPid_form').rup_form({
		url: "/aa79bItzulnetWar/ficheros/subidaFichero"
		, dataType: "json"
		, beforeSend: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
			return "skip";
		}
		, success: function(archivoPIF){
			if (archivoPIF.error === null){ 
				//Subida correcta
				$.ajax({
				   	 type: 'POST'
				   	 ,url: '/aa79bItzulnetWar/ficheros/pasoPIFaPID'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(archivoPIF)			
				   	 ,success:function(data){
				   		if (data.error === null){ 
					   		var destinoUpload = $("#idBotonUpload").val();
					   		$('#nombreFicheroInfo_'+destinoUpload).val(data.nombre);
							$('#nombre_'+destinoUpload).val(data.nombre);
							$('#extensionDoc_'+destinoUpload).val(data.extension);
							$('#tamanoDoc_'+destinoUpload).val(data.tamano);
							$('#contentType_'+destinoUpload).val(data.contentType);
							$('#oidFichero_'+destinoUpload).val(data.oid);
							$('#encriptado_'+destinoUpload).val(data.encriptado);
							
							var enlace = '<span>'+data.nombre+'</span> ('+conversionKB(data.tamano)+' KB.) ';
							$('#enlaceDescargaDetalle_'+destinoUpload).html(enlace);
				   		}else{
							$('#crearConfigurar_feedback').rup_feedback("set", data.error, "error");
							scrollToFeedback();
						}
				   		vaciarFileYDesbloquear();
				   	 }
			   	 	,error: function(){
				   		$('#crearConfigurar_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   		scrollToFeedback();
				   		vaciarFileYDesbloquear();
				   	 }
				 });
			}else{
				$('#crearConfigurar_feedback').rup_feedback("set", archivoPIF.error, "error");
				scrollToFeedback();
				vaciarFileYDesbloquear();
			}
		 }
		, error: function(archivoPIF){
			$('#crearConfigurar_feedback').rup_feedback("set", archivoPIF.error, "error");
			scrollToFeedback();
			vaciarFileYDesbloquear();
		}
	});
	
	
	
	// CAMBIO SOLUCION PIF/PID
	// Ahora el requerir encriptado depende solo del indConfidencialidad...
	
	$("#fichero").change(function(){
		$('#crearConfigurar_feedback').rup_feedback("hide");
		if ($("#fichero").val() !== ''){
			//no permitir zip en el 2o fich
			var laExtension  = $("#fichero").val().substring($("#fichero").val().lastIndexOf('.')+1, $("#fichero").val().length);
			if (comprobarExtensionValida($('#fichero').val())){
				$('#reqEncriptado').val("I"); //Indiferente
				$("#subidaFicheroPid_form").submit();
	    	}else{ 
	    		$('#crearConfigurar_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	    		scrollToFeedback();
	    	}
		}
	});

	llamadasFinalizadas("callbackCrearConfigTarea");

});