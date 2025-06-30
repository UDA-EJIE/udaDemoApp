var valorInicialTipoExpediente;
var hayCambiosExpInterpretacion = false;
var hayCambiosExpTradRev = false;
var comprobarCambiosExpTradRev = true;
var comprobarCambiosExpInter = true;
var consultaExp = false;
var bopv = false;
var initForm = '';
var listaTiposDoc;
var tramitagune = false;
function deshabilitarYCtrolCambios(){
	deshabilitarControlesCapa('capaPestanaCompletaAlta');
	initForm = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
	datosFormulario = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
}

function modificarBotonesToolbar(estadoExp) {

	if(estadoExp.id === expedCerrado){
		
		$("[id=detalleExpedientePlanificacion_toolbar##rechazar]").prop('disabled', true);
		$("[id=detalleExpedientePlanificacion_toolbar##rechazar]").addClass('ui-button-disabled ui-state-disabled');
	} else {
		
		$("[id=detalleExpedientePlanificacion_toolbar##rechazar]").prop('disabled', false);
		$("[id=detalleExpedientePlanificacion_toolbar##rechazar]").removeClass('ui-button-disabled ui-state-disabled');
	}

	if (estadoExp.id === expedEnCurso) {
		
		$("[id=detalleExpedientePlanificacion_toolbar##fechaHoraEntrega]").prop('disabled', false);
		$("[id=detalleExpedientePlanificacion_toolbar##fechaHoraEntrega]").removeClass('ui-button-disabled ui-state-disabled');
	} else {
		
		$("[id=detalleExpedientePlanificacion_toolbar##fechaHoraEntrega]").prop('disabled', true);
		$("[id=detalleExpedientePlanificacion_toolbar##fechaHoraEntrega]").addClass('ui-button-disabled ui-state-disabled');
	}
}

function crearComboModoInterpretacion(id){
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/administracion/datosmaestros/modosinterpretacion",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descEs"
					: "descEu",
			value: "id"
		}
		,blank: ""
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,select: function(){
			hayCambiosExpInterpretacion = true;
		}
	});
}

function crearComboTipoActo(id){
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/administracion/datosmaestros/tiposinterpretacion",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descEs008"
					: "descEu008",
			value: "id008"
		}
		,blank: ""
		,width: "170"
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,select: function(){
			hayCambiosExpInterpretacion = true;
		},
		onLoadSuccess: function() {
			if (esPresupuestoVisibleParaUsuario){
				// Deshabilitar el campo Tipo de acto
				$("#tipoActo").rup_combo('disable');
			}
			
	   		datosFormulario = $("#datosgeneralesexpedienteform").serialize();
		}
	});
}

function crearComboIdiomaDestino(id){
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/idiomadestino",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descIdiomaEs"
					: "descIdiomaEu",
			value: "idIdioma"
		}
		,width: "170"
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,select: function(){
			hayCambiosExpTradRev = true;
		},
		onLoadSuccess: function() {
	   		
	   		$('#'+id).rup_combo("enable");
	   		datosFormulario = $("#datosgeneralesexpedienteform").serialize();
		}
	});
}

function crearComboTipoPeticionario(id){
	var tipoPeticionarioOption = {
			loadFromSelect: true
			,width: "200"
			,ordered: false	
			,rowStriping: true
		};
	$('#'+id).rup_combo(tipoPeticionarioOption);
}

function msgConfirmTipoExpediente(){
	$.rup_messages("msgConfirm", {
		title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
		OKFunction: function(){
			inicializarDatos();
			hayCambiosExpInterpretacion = false;
			hayCambiosExpTradRev = false;
		},
		CANCELFunction: function(){
			$('#idTipoExpediente').rup_combo("setRupValue", valorInicialTipoExpediente);
		}
	});
}

function msgConfirmCambioTipoExpediente(){
	if (valorInicialTipoExpediente !== "" && valorInicialTipoExpediente !== $('#idTipoExpediente').rup_combo("getRupValue")){
		
		if (hayCambiosExpInterpretacion && valorInicialTipoExpediente === datosExp.tipoExp.interpretacion){
			msgConfirmTipoExpediente();
		} else if (hayCambiosExpTradRev && valorInicialTipoExpediente !== datosExp.tipoExp.interpretacion){
			msgConfirmTipoExpediente();
		} else {
			inicializarDatos();
		}
		
	} else if (valorInicialTipoExpediente !== $('#idTipoExpediente').rup_combo("getRupValue")){
		inicializarDatos();
	}
}

function activarUrlBOE(){
	$('#urlBoe').prop('readonly', false);
	$('#urlBoe').prop('disabled', false );
	$('#urlBoe').rules( "add", {  url: true,  maxlength: 250 });
}
function desactivarUrlBOE(){
	$('#urlBoe').val('');
	$('#urlBoe').prop('readonly', true);
	$('#urlBoe').prop('disabled', true );
	$('#urlBoe').rules("remove");
	eliminarMensajesValidacionPorCapa('capaUrlBOE');
}

function inicializarDatos(){
	if ($('#idTipoExpediente').rup_combo("getRupValue") !== ""){
		
		if ($('#idTipoExpediente').rup_combo("getRupValue") === datosExp.tipoExp.interpretacion){
			
			limpiarCamposExpTradRev();
			
			inicializarDatosExpInter();
			
			$('#indProgramada').bootstrapSwitch('setState', false);
			$('#indPresupuesto').bootstrapSwitch('setState', false);
			
			comprobarCambiosExpInter = true;
			comprobarCambiosExpTradRev = false;
			hayCambiosExpTradRev = false;
			
		} else {
			limpiarCamposExpInterpretacion();
			
			inicializarDatosExpTradRev();
			
			comprobarCambiosExpTradRev = true;
			if (valorInicialTipoExpediente !== $('#idTipoExpediente').rup_combo("getRupValue")){
				hayCambiosExpInterpretacion = false;
				limpiarCamposExpTradRev();
			}
			
			comprobarCambiosExpInter = false;
			hayCambiosExpInterpretacion = false;
		}
		
	} else {
		ocultarDiv('divExpedienteInterpretacion');
		ocultarDiv('divExpedienteTradRev');
		ocultarDiv('divDocumentosExpediente');
		
		ocultarLinkObservaciones();
		
		limpiarCamposExpTradRev();
		limpiarCamposExpInterpretacion();
		
		validarCampos();
		
		comprobarCambiosExpInter = true;
		comprobarCambiosExpTradRev = true;
	}
	
	valorInicialTipoExpediente = $('#idTipoExpediente').rup_combo("getRupValue");
}

function crearComboTipoExpediente(){
	var tipoExpedienteOption = {
			loadFromSelect: true
			,width: "200"
			,ordered: false	
			,rowStriping: true
			,change: function(){					
				msgConfirmCambioTipoExpediente();
			}
		};
	$('#idTipoExpediente').rup_combo(tipoExpedienteOption);
	valorInicialTipoExpediente = $('#idTipoExpediente').rup_combo("getRupValue");
}

function obtenerListaTiposDoc(){
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta'
	 	 ,dataType: 'json'
		 ,ordered: false	
	 	 ,contentType: 'application/json' 
	     ,cache: false
	   	 ,success:function(data){
	   		if (!isEmpty(data)) {
	   			listaTiposDoc = data;
	   		}
	   	 }
  	 	,error: function(){
	   		$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   	 }
	 });
}

function mostrarDatosCorredaccion(){
	mostrarDiv('divDatosCorredacccion');
}

function ocultarDatosCorredaccion(){
	ocultarDiv('divDatosCorredacccion');
}

function validarCampos(){
	$("#tipoEntidadSolicitanteDesc").rules("add", "required");
	$("#nombreEntidadSolicitante").rules("add", "required");
	$("#nombreGestor").rules("add", "required");
}

function validarCamposExpInterpretacion(){
	$("#modoInterpretacion").rules("add", "required");
	$("#tipoActo").rules("add", "required");
	$("#tipoPeticionario").rules("add", "required");
	$("#fechaIni").rules("add", "required");
	$("#horaIni").rules("add", "required");
	$("#fechaFin").rules("add", "required");
	$("#horaFin").rules("add", "required");
	$("#lugarInterpretacion").rules("add", "required");
	
	$("#indPublicarBopv").rules("remove", "required");
	$("#idiomaDestino").rules("remove", "required");
	$("#indConfidencial").rules("remove", "required");
	
}

function validarCamposExpTradRev(){
	$("#modoInterpretacion").rules("remove", "required");
	$("#tipoActo").rules("remove", "required");
	$("#tipoPeticionario").rules("remove", "required");
	$("#fechaIni").rules("remove", "required");
	$("#horaIni").rules("remove", "required");
	$("#fechaFin").rules("remove", "required");
	$("#horaFin").rules("remove", "required");
	$("#lugarInterpretacion").rules("remove", "required");
	
	$("#indPublicarBopv").rules("add", "required");
	$("#idiomaDestino").rules("add", "required");
	$("#indConfidencial").rules("add", "required");
}

function limpiarCamposExpInterpretacion(){
	$("#modoInterpretacion").val("");
	$("#tipoActo").val("");
	$("#tipoPeticionario").val("");
	$("#fechaIni").val("");
	$("#horaIni").val("");
	$("#fechaFin").val("");
	$("#horaFin").val("");
	$("#lugarInterpretacion").val("");
	$('#lugarInterpretacion').change();
	$("#personaContacto").val("");
	$("#emailContacto").val("");
	$("#telefonoContacto").val("");
	$("#observacionesExpInter").val("");
	$("#observacionesExpInter").change();
	
	comprobarCambiosExpTradRev = false;
	hayCambiosExpTradRev = false;
	$('#indProgramada').bootstrapSwitch('setState', false);
	$('#indPresupuesto').bootstrapSwitch('setState', false);
	$('#reqEncriptado').val('N');
}

function limpiarCamposExpTradRev(){
	$("#idiomaDestino").val("");
	$("#texto").val("");
	$("#tipoRedaccion").val("");
	$("#participantes").val("");
	$("#refTramitagune").val("");
		
	if ("" !== $("#observacionesExpTradRev").val()){
		$("#observacionesExpTradRev").val("");
		$("#observacionesExpTradRev").change();
	}
	
	comprobarCambiosExpInter = false;
	hayCambiosExpInterpretacion = false;
	$('#indPublicarBopv').bootstrapSwitch('setState', false);
	$('#indPrevistoBopv').bootstrapSwitch('setState', false);
	$('#indConfidencial').bootstrapSwitch('setState', false);
	$('#indCorredaccion').bootstrapSwitch('setState', false);
	$('#reqEncriptado').val('N');
	
	$("#personaContacto056_0").val('');
	$("#emailContacto056_0").val('');
	$("#direccionContacto056_0").val('');
	$("#entidadContacto056_0").val('');
	$("#oidFichero056_0").val('');
	//$("#nombreFicheroInfo_0").val('');
	$("#indEncriptado056_0").val('');
	
	
	$("#personaContacto056_1").val('');
	$("#emailContacto056_1").val('');
	$("#direccionContacto056_1").val('');
	$("#entidadContacto056_1").val('');
	$("#oidFichero056_1").val('');
	$("#nombreFicheroInfo_1").val('');
	$("#indEncriptado056_1").val('');
}

function mostrarDetalleObservaciones(tipo){
	mostrarDiv('divDetalleObservaciones'+tipo);
}

function ocultarDetalleObservaciones(tipo){
	ocultarDiv('divDetalleObservaciones'+tipo);
}

function mostrarLinkObservaciones(tipo){
	$('#observaciones_mostrarLink_div'+tipo).removeClass('oculto');
	$('#observaciones_mostrarLink_div'+tipo).addClass('inline');
}

function ocultarLinkObservaciones(tipo){
	$('#observaciones_mostrarLink_div'+tipo).addClass('oculto');
	$('#observaciones_mostrarLink_div'+tipo).removeClass('inline');
}

function mostrarSeccionObservaciones(tipo){
	$('#divSeccionObservaciones'+tipo).removeClass('oculto');
}

function ocultarSeccionObservaciones(tipo){
	$('#divSeccionObservaciones'+tipo).addClass('oculto');
}


function inicializarDatosExpInter(){
	mostrarDiv('divExpedienteInterpretacion');
	ocultarDiv('divExpedienteTradRev');
	ocultarDiv('divDocumentosExpediente');
	
	fnFechaDesdeHasta("fechaIni", "fechaFin");
	
	crearComboModoInterpretacion('modoInterpretacion');
	crearComboTipoActo('tipoActo');
	crearComboTipoPeticionario('tipoPeticionario');
	
	validarCamposExpInterpretacion();
}


function inicializarDatosExpTradRev(){
	ocultarDiv('divExpedienteInterpretacion');
	mostrarDiv('divExpedienteTradRev');
	mostrarDiv('divDocumentosExpediente');
	
	crearComboIdiomaDestino('idiomaDestino');
	
	var parentIndCorredaccion = $('#indCorredaccion').parent().parent();
	if ($('#idTipoExpediente').rup_combo("getRupValue") === datosExp.tipoExp.revision){
		parentIndCorredaccion.addClass("disabled");
		$('#indCorredaccion').bootstrapSwitch('setState', false);
		$('#indCorredaccion').attr('disabled','disabled');
		ocultarDatosCorredaccion();
	} else {
		parentIndCorredaccion.removeClass("disabled");
		$('#indCorredaccion').removeAttr('disabled');
	}
	
	$("[id^='pidButton']").unbind("click");
	$("[id^='pidButton']").on("click", function() {
		if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
		else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
		else $("#idBotonUpload").val("0");
		$('#fichero').click();
	});
	validarCamposExpTradRev();
}

function deshabilitarFormulario(){
	$("[id='detalleExpediente_toolbar##"+"rechazar"+"']").show();
	$("[id='detalleExpediente_toolbar##"+"requerirSub"+"']").show();
	$("[id='detalleExpediente_toolbar##"+"oficio"+"']").show();
	$("[id='detalleExpediente_toolbar##"+"otrosToolbarExpediente"+"']").show();
	deshabilitarSubsanacion();
}

function deshabilitarSubsanacion(){
	if(esSubsanado === 'N'){
		$('#detalleSubsanacion_button_save').show();
		$('#detalleSubsanacion_link_cancel').hide();
		$('#detFechaLimite').rup_date("enable");
	}else if(esSubsanado === 'S'){
		$('#detalleSubsanacion_button_save').show();
		$('#detalleSubsanacion_link_cancel').show();
		$('#detFechaLimite').rup_date("disable");
	}
}

//function comprobarFicherosEncriptados(){
//	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
//	$.ajax({
//	   	 type: 'GET'
//	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/comprobarencriptados/'+anyoExpediente+"/"+idExpediente
//	 	 ,dataType: 'json' 	 	 	
//	 	 ,cache: false
//	 	 ,async: true
//	   	 ,success:function(data){
//	   		if (data !== null){    //existe el registro			   			
//		   		if (data > 0){
//		   			$.rup_messages("msgError", { 
//						title: $.rup.i18n.base.rup_ajax.errorTitle,
//						message: $.rup.i18n.app.mensajes.errorDocsNoEncriptados
//		   				});	
//		   			$('#indConfidencial').bootstrapSwitch('setState', false);
//		   		}
//	   		}else{
//	   			$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
//	   		}	
//	   		desbloquearPantalla();
//	   	 }
//	 	,error: function(){	  	 		
//	 		$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
//	 		desbloquearPantalla();
//	   	 }
//	 });
//}



jQuery(function($){
	
	//A lo bruto, elimino TODO el contenido de la otra pestaña
	$('#capaPestanaCompletaDoc').remove();
	eliminarMensajes();
	datosFormularioDoc = '';
	eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
	
	$('#datosgeneralesexpedienteform_feedback').rup_feedback({
		block : false, delay: 3000, gotoTop: false
	});
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
//	$('#indConfidencial').on('switch-change', function(event, state) {
//		if (state.value){ //Activando
//			comprobarFicherosEncriptados();
//		}
//	});
	
	$('#fechaAlta').rup_date();
	
	obtenerDatosExpediente();
	
	if (visualizarObservaciones){
		$("#observacionesVisibles").val(true);
	} else {
		$("#observacionesVisibles").val(false);
	}
		
	function obtenerDatosExpediente(){
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/datosgeneralesexpediente/datosexpediente/"+idExpediente+"/"+anyoExpediente+"/"+origen,
			dataType: "json", 
			cache: false,
			success:function(data){
				consultaExp = true;
				setExpedienteData(data);
				deshabilitarComboTipoExp();
				gestionarDatosGestor(data.gestorExpediente);
				modificarBotonesToolbar(data.bitacoraExpediente.estadoExp);
				
				$('#datosgeneralesexpedienteform_button_save').text($.rup.i18nParse($.rup.i18n.base,"rup_global.save"));
				
				$("#tabsExpediente").rup_tabs("selectTab",{
					idTab: "tabsExpediente",
					position: 0
				});
				
				$('#indSolicitadoTramitagune').bootstrapSwitch('toggleDisabled',	true, true);
				if(data.origen === 'W' && (data.aplicacionOrigen === "r02t" || data.aplicacionOrigen === "R02T")){
					$('#indSolicitadoTramitagune').bootstrapSwitch('setState', true);
					tramitagune = true;
				}else{
					$('#indSolicitadoTramitagune').bootstrapSwitch('setState', false);
					tramitagune = false;
					$('#indSolicitadoTramitagune').attr('disabled','disabled');
				}
				
				comprobarFormularios = true;
				
				
				bitacoraUpdate(false);
				
				deshabilitarFormulario();
				
				
				if(!$('#indConfidencial')[0].disabled){
					$('#indConfidencial').bootstrapSwitch('toggleDisabled',true,true);
				}
				initForm = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
				datosFormulario = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
				desbloquearPantalla();
			}
		});
	}
	
	function getId(obj){
		return obj == null ? "" : obj.id;
	}
	
	function getObservaciones(obj){
		return obj == null ? "" : obj.observaciones;
	}
	function getObservacionesOid(obj){
		return obj == null ? "" : obj.oidFichero;
	}
	function getObservacionesExtension(obj){
		return obj == null ? "" : obj.extension;
	}
	function getObservacionesContentType(obj){
		return obj == null ? "" : obj.contentType;
	}
	function getObservacionesNombre(obj){
		return obj == null ? "" : obj.nombre;
	}
	function getObservacionesTamano(obj){
		return obj == null ? "" : obj.tamano;
	}
	function deshabilitarComboTipoExp(){
		$('#idTipoExpediente').rup_combo("disable");
	}
	
	function setDatosTipoExpediente(idTipoExpediente){
		if (idTipoExpediente != null){
			$('#idTipoExpediente').rup_combo("setRupValue", idTipoExpediente);
			deshabilitarComboTipoExp();
		}
	}
	
	function setDatosTecnico(tecnico){
		if (tecnico != null){
			$('#nombreApellidosTecnico').val(tecnico.nombreCompleto);
			$('#dniTecnico').val(tecnico.dni);
		}
	}
	
	function setDatosGestorExpediente(gestorExpediente){
		if (gestorExpediente != null){
			$("#codigoEntidadSolicitante").val(gestorExpediente.entidad.codigo);
			$("#tipoEntidadSolicitante").val(gestorExpediente.entidad.tipo);
			$("#descripcionTipoEntidad option[value="+ gestorExpediente.entidad.tipo +"]").attr("selected",true);
			$("#tipoEntidadSolicitanteDesc").val($("#descripcionTipoEntidad option:selected").text());
			$("#nombreEntidadSolicitante").val($.rup.lang === 'es' ? gestorExpediente.entidad.descAmpEs : gestorExpediente.entidad.descAmpEu);
			if (gestorExpediente.solicitante != null){
				$("#dniSolicitante").val(gestorExpediente.solicitante.dni);
				$("#nombreGestor").val(gestorExpediente.solicitante.nombreCompleto);
				if (  'S' === gestorExpediente.solicitante.grupoBoletin ){
					$("#gestor_EHAATaldea_div").removeClass("oculto");
				}
				$("#gestorExpedientesVIP").val(gestorExpediente.solicitante.gestorExpedientesVIP);
				mostrarDivGestorVIP();
			}
		}
	}
	
	function setDatosExpInter(expedienteInterpretacion, idTipoExpediente){
		if (expedienteInterpretacion != null && idTipoExpediente === datosExp.tipoExp.interpretacion){
			
			fechaIniInter=expedienteInterpretacion.fechaIni;
			horaIniInter=expedienteInterpretacion.horaIni;
			fechaFinInter=expedienteInterpretacion.fechaFin;
			horaFinInter=expedienteInterpretacion.horaFin;
			
			if (expedienteInterpretacion.modoInterpretacion != null){
				$('#modoInterpretacion').rup_combo("setRupValue", expedienteInterpretacion.modoInterpretacion);
			}
			if (expedienteInterpretacion.tipoActo != null){
				$('#tipoActo').rup_combo("setRupValue", expedienteInterpretacion.tipoActo);
			}
			if (expedienteInterpretacion.tipoPeticionario != null){
				$('#tipoPeticionario').rup_combo("setRupValue", expedienteInterpretacion.tipoPeticionario);
			}
			$('#fechaIni').val(expedienteInterpretacion.fechaIni);
			$('#horaIni').val(expedienteInterpretacion.horaIni);
			$('#fechaFin').val(expedienteInterpretacion.fechaFin);
			$('#horaFin').val(expedienteInterpretacion.horaFin);
			$('#personaContacto').val(expedienteInterpretacion.personaContacto);
			$('#emailContacto').val(expedienteInterpretacion.emailContacto);
			$('#telefonoContacto').val(expedienteInterpretacion.telefonoContacto);
			if (expedienteInterpretacion.dirNora != null){
				$('#dirNora').val(expedienteInterpretacion.dirNora.dirNora);
				$('#noraId').val(expedienteInterpretacion.dirNora.codNora);
				$('#tipoNora').val(expedienteInterpretacion.dirNora.tipoNora);
				$('#paisId').val(expedienteInterpretacion.dirNora.pais.id);
				$('#provinciaId').val(getId(expedienteInterpretacion.dirNora.provincia));
				$('#municipioId').val(getId(expedienteInterpretacion.dirNora.municipio));
				$('#localidadId').val(getId(expedienteInterpretacion.dirNora.localidad));
				$('#calleId').val(getId(expedienteInterpretacion.dirNora.calle));
				$('#portalId').val(getId(expedienteInterpretacion.dirNora.portal));
				$('#cp').val(expedienteInterpretacion.dirNora.codPostal);
				$('#escalera').val(expedienteInterpretacion.dirNora.escalera);
				$('#piso').val(expedienteInterpretacion.dirNora.piso);
				$('#mano').val(expedienteInterpretacion.dirNora.mano);
				$('#puerta').val(expedienteInterpretacion.dirNora.puerta);
				$('#aproxPostal').val(expedienteInterpretacion.dirNora.aprox);
				$('#lugarInterpretacion').val(expedienteInterpretacion.dirNora.txtDirec);
			}
			
			if(expedienteInterpretacion.indProgramada === 'S'){
				$('#indProgramada').bootstrapSwitch('setState', true);
			}else{
				$('#indProgramada').bootstrapSwitch('setState', false);
			}
			if(expedienteInterpretacion.indPresupuesto === 'S'){
				$('#indPresupuesto').bootstrapSwitch('setState', true,true);
			}else{
				$('#indPresupuesto').bootstrapSwitch('setState', false,true);
			}
			
			//$('#observacionesExpInter').val(getObservaciones(expedienteInterpretacion.observaciones));
			
			$('#indObservacionesInterpretacion').val(expedienteInterpretacion.indObservaciones);
			gestionarObservacionesInterpretacion();
		}
	}
	function descargarDocumentoObservaciones(elAnyo,elNumExp){			
		window.open('/aa79bItzulnetWar/ficheros/descargarDocumentoObservaciones/'+elAnyo+'/'+elNumExp);
	}
	function setDatosExpTradRev(expedienteTradRev, idTipoExpediente){
		if (expedienteTradRev != null 
				&& (idTipoExpediente === datosExp.tipoExp.traduccion || idTipoExpediente === datosExp.tipoExp.revision)){
			
			if (expedienteTradRev.idIdioma != null){
				$('#idiomaDestino').rup_combo("setRupValue", expedienteTradRev.idIdioma);
				idiomaDestino = expedienteTradRev.idIdioma;
			}
			//Para ejectareas corredaccion
			fechaFinalIZO = expedienteTradRev.fechaFinalIzo;
			horaFinalIZO = expedienteTradRev.horaFinalIzo;
			expedienteConfidencial = expedienteTradRev.indConfidencial;
			esCorredaccion = expedienteTradRev.indCorredaccion;
			
			$('#texto').val(expedienteTradRev.texto);
			$('#tipoRedaccion').val(expedienteTradRev.tipoRedaccion);
			$('#participantes').val(expedienteTradRev.participantes);
			$('#refTramitagune').val(expedienteTradRev.refTramitagune);
			
			if(expedienteTradRev.indPublicarBopv === 'S'){
				$('#indPublicarBopv').bootstrapSwitch('setState', true);
				bopv = true;
			}else{
				$('#indPublicarBopv').bootstrapSwitch('setState', false);
				bopv = false;
			}
			
			if(expedienteTradRev.indPrevistoBopv === 'S'){
				$('#indPrevistoBopv').bootstrapSwitch('setState', true);
			}else{
				$('#indPrevistoBopv').bootstrapSwitch('setState', false);
			}
			
			if(expedienteTradRev.indConfidencial === 'S'){
				$('#indConfidencial').bootstrapSwitch('setState', true, true);
			}else{
				$('#indConfidencial').bootstrapSwitch('setState', false, true);
			}
			
			if (expedienteTradRev.indCorredaccion === 'S'){
				mostrarDatosCorredaccion();
				$('#indCorredaccion').bootstrapSwitch('setState', true);
			} else {
				ocultarDatosCorredaccion();
				$('#indCorredaccion').bootstrapSwitch('setState', false);
			}
			
				
			$('#indObservacionesExpTradRev').val(expedienteTradRev.indObservaciones);
			gestionarObservaciones();

			if (esPresupuestoVisibleParaUsuario){
				var capaPadreSitch = $('#indPublicarBopv').parent().parent();
	   			capaPadreSitch.addClass("disabled");
	   			$('#indPublicarBopv').prop( {"disabled": true });
			}
			
		}
	}
	
	function setCondicionesPresupuestoVisibleParaUsuario(){
		// Deshabilitar el switch de presupuesto para exp interpret....
		var parentIndPresupuesto = $("#indPresupuesto").parent().parent();
		$("#indPresupuesto").attr('disabled','disabled');
		parentIndPresupuesto.addClass("disabled");
	}
	
	function setDatosExpedientesRelacionados(listExpRelacionados){
		if (listExpRelacionados.length > 0){
			mostrarDiv('divMostrarExpdientesRelacionadosPlanificacion');
			for(let i = 0; i < listExpRelacionados.length; i++){
				var anyoNumExp = concatenarAnyoNumExp(listExpRelacionados[i].anyoExpRel, listExpRelacionados[i].numExpRel);
				var url = "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/"+listExpRelacionados[i].anyoExpRel+"/"+listExpRelacionados[i].numExpRel;
				if(i == 0){
					$('#listExpRelacionados').append("<a id=expRel_"+i+" class='exp-rel'>"+anyoNumExp+"</a>");
				}else{
					$('#listExpRelacionados').append("<a>, </a>");
					$('#listExpRelacionados').append("<a id=expRel_"+i+" class='exp-rel'>"+anyoNumExp+"</a>");
				}	
				$("#expRel_"+i).attr('onClick', "window.open('"+url+"')");
			}
		}else{
			ocultarDiv('divMostrarExpdientesRelacionadosPlanificacion');
		}
	}
	
	function setExpedienteData(data){
		if (data != null){
			$('#fechaAlta').val(data.fechaAlta);
			$('#horaAlta').val(data.horaAlta);
			$('#anyoExpediente').val(data.anyo);
			$('#numeroExpediente').val(data.numExp);
			$('#tituloExpediente').val(data.titulo);
			$('#fechaAlta').rup_date("disable");
			$('#horaAlta').attr('disabled','disabled');
			//vble Global planif
			tipoExp = data.idTipoExpediente;
			//si exp interpretacion y estado en curso, habilitamos enlace para modificar direccion nora
			if(estado === expedEnCurso && datosExp.tipoExp.interpretacion == tipoExp){
				$('#enlaceBuscarDireccionNora').show();
				$('#buscar_direccion_nora').on("click", function (){
					buscarDireccion();
				});
			}
			setDatosTipoExpediente(data.idTipoExpediente);
			setDatosTecnico(data.tecnico);
			setDatosGestorExpediente(data.gestorExpediente);
			setDatosExpInter(data.expedienteInterpretacion, data.idTipoExpediente);
			
			esPresupuestoVisibleParaUsuario = data.presupuestoVisibleUsuario;
			setDatosExpTradRev(data.expedienteTradRev, data.idTipoExpediente);
			setDatosExpedientesRelacionados(data.listaExpedientesRelacionados);

			tieneTareasEjecutadas = data.tieneTareasEjecutadas;
			
			//if (data.idTipoExpediente === datosExp.tipoExp.traduccion && (codSolicitanteBOE === data.gestorExpediente.entidad.codigo && 'B'=== data.gestorExpediente.entidad.tipo)){
			if (data.idTipoExpediente === datosExp.tipoExp.traduccion ){
				$('#capaBoe').show();
				if (data.expedienteTradRev.indPublicadoBoe === 'S'){
					$('#indPublicadoBoe').bootstrapSwitch('setState', true);
					activarUrlBOE();
				} else {
					$('#indPublicadoBoe').bootstrapSwitch('setState', false);
					desactivarUrlBOE();
				}
				$('#urlBoe').val(data.expedienteTradRev.urlBoe);
			}
			
			if (data.idTipoExpediente === datosExp.tipoExp.interpretacion){
				
				if (esPresupuestoVisibleParaUsuario){
					setCondicionesPresupuestoVisibleParaUsuario();
				}
				if (tieneTareasEjecutadas){
					$('#notaTareasEjecutadasInter').show();
					setCondicionesPresupuestoVisibleParaUsuario();
				}
				$("[id=detalleExpedientePlanificacion_toolbar##fechaHoraEntrega]").hide();
			} else {
				
				$("[id=detalleExpedientePlanificacion_toolbar##fechaHoraEntrega]").show();
			}
			esTradosConRequerimiento = data.tradosConRequerimiento;
		}
	}
	
	//Formulario detalle
	$("#datosgeneralesexpedienteform").rup_form({
		url: "/aa79bItzulnetWar/datosgeneralesexpediente/addExpediente/"+origen,
		dataType: "json",
		feedback:$("#datosgeneralesexpedienteform_feedback"),
		type: "POST",
		beforeSubmit: function(){
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"));
			return true;
		},
		success: function(data){
			idExpediente = data.numExp;
			anyoExpediente = data.anyo;
			
			if (data.idTipoExpediente === datosExp.tipoExp.interpretacion){
				fechaIniInter=data.expedienteInterpretacion.fechaIni;
				horaIniInter=data.expedienteInterpretacion.horaIni;
				fechaFinInter=data.expedienteInterpretacion.fechaFin;
				horaFinInter=data.expedienteInterpretacion.horaFin;
			}
			
			desbloquearPantalla();
			$("#datosgeneralesexpedienteform_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
			if ($('#nombreFicheroInfo_2').length && !isEmpty($('#nombreFicheroInfo_2').val())){  
				var enlace = '<a href="#" class="descargarDocObservaciones" data-anyo="'+anyoExpediente+'" data-numexp="'+idExpediente+'" >'+$('#nombreFicheroInfo_2').val()+'</a>';	   			
	   			$('#enlaceDescargaDetalle_2').html(enlace);
	   			$('a.descargarDocObservaciones').click(function(event){
	   				event.preventDefault();	
	   				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	   				var elAnyo = $(this).data("anyo");
	   				var elNumExp = $(this).data("numexp");
	   				descargarDocumentoObservaciones(elAnyo,elNumExp);
	   				desbloquearPantalla();
	   			});
	   			$('#nombreFicheroInfo_2').val('');
	   			$('#rutaPif_2').val('');
	   			$('#oidFichero056_2').val(data.expedienteTradRev.observaciones.oidFichero);
			}
			if ($('#idTipoExpediente').rup_combo("getRupValue") === datosExp.tipoExp.interpretacion){
				$("#lugarInterpretacion").attr('disabled', true);
			}
			//Actualizar bitácora + cabecera
			bitacoraUpdate(false);
			$('body').scrollTo('#tabsExpediente');
			datosFormulario = $("#datosgeneralesexpedienteform").serialize();
		},
		error: function(){
			$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
			desbloquearPantalla();
		},
		validate:{ 
			rules:{
				"idTipoExpediente":{ required: true },
				"fechaAlta": { required: true, date: true },
				"horaAlta": { required: true, hora: true, maxlength: 5 },
				"titulo":{ required: true, maxlength: 250 },
				"expedienteInterpretacion.fechaIni": { date: true },
				"expedienteInterpretacion.horaIni": { hora: true, maxlength: 5 },
				"expedienteInterpretacion.fechaFin": { date: true, fechaHastaMayor: "expedienteInterpretacion.fechaIni" },
				"expedienteInterpretacion.horaFin": { hora: true, maxlength: 5, horaFechaHastaMayor: ["expedienteInterpretacion.fechaIni","expedienteInterpretacion.fechaFin","expedienteInterpretacion.horaIni"] },
				"expedienteInterpretacion.personaContacto":{ maxlength: 125 },				
				"expedienteInterpretacion.emailContacto":{ email: true, maxlength: 250 },
				"expedienteInterpretacion.telefonoContacto":{digits:true, maxlength: 15 },
				"expedienteTradRev.texto": { maxlength: 250 },
				"expedienteTradRev.tipoRedaccion": { maxlength: 250 },
				"expedienteTradRev.participantes": { maxlength: 2000 },
				"expedienteTradRev.refTramitagune": { maxlength: 250 },
				"expedienteInterpretacion.observaciones.observaciones":{ maxlength: 4000 },
				"expedienteTradRev.observaciones.observaciones":{ maxlength: 4000 },
				/*JOSE*/
				"documentosExpediente[0].numPalIzo":{  maxlength: 6,integer: true},		
				"documentosExpediente[0].titulo":{ maxlength: 256},				
				"documentosExpediente[0].ficheros[0].contacto.persona":{ maxlength: 150},				
				"documentosExpediente[0].ficheros[0].contacto.email":{ maxlength: 256, email:true},				
				"documentosExpediente[0].ficheros[0].contacto.direccion":{ maxlength: 256},				
				"documentosExpediente[0].ficheros[0].contacto.entidad":{  maxlength: 150},				
				"documentosExpediente[0].ficheros[0].contacto.telefono":{digits:true, maxlength: 15},	
				"documentosExpediente[0].ficheros[1].contacto.persona":{ maxlength: 150},				
				"documentosExpediente[0].ficheros[1].contacto.email":{ maxlength: 256, email:true},				
				"documentosExpediente[0].ficheros[1].contacto.direccion":{ maxlength: 256},				
				"documentosExpediente[0].ficheros[1].contacto.entidad":{ maxlength: 150},				
				"documentosExpediente[0].ficheros[1].contacto.telefono":{digits:true, maxlength: 15}		
				
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
			showFieldErrorsInFeedback: false
		}
	});
	
	crearComboTipoExpediente();
	
	inicializarDatos();
	
	
	
	function seleccionarPersona(obj){
		if(obj!=null && obj.length>0){
			for(var i=0; i<obj.length; i++){
				var persona = obj[i];
				
				$("#codigoEntidadSolicitante").val(persona.entidad.codigo);
				$("#tipoEntidadSolicitante").val(persona.entidad.tipo);
				$("#descripcionTipoEntidad option[value="+ persona.entidad.tipo +"]").attr("selected",true);
				$("#tipoEntidadSolicitanteDesc").val($("#descripcionTipoEntidad option:selected").text());
				$("#nombreEntidadSolicitante").val($.rup.lang === 'es' ? persona.entidad.descAmpEs : persona.entidad.descAmpEu);
				$("#dniSolicitante").val(persona.dni);
				$("#nombreGestor").val(persona.nombreCompleto);
				
//				inicializarComboTipoPeticionario();
				
				if (persona.solicitante != null){
					$("#gestorExpedientesVIP").val(persona.solicitante.gestorExpedientesVIP);
					mostrarDivGestorVIP();
				}
				
			}
		}
		
	}
	
	function mostrarDivGestorVIP(){
		if ($("#gestorExpedientesVIP").val() === 'S'){
			$("#gestorExpedientesVIP_div").removeClass('oculto');
		} else {
			$("#gestorExpedientesVIP_div").addClass('oculto');
		}
	}
	

	// Si tipo expediente <> interpretacion
	$('#indPublicarBopv').on('change', function(event, state) {
		
		var parentIndPrevistoBopv = $("#indPrevistoBopv").parent().parent();
		if (!event.target.checked){
			$("#indPrevistoBopv").removeAttr('disabled');
			parentIndPrevistoBopv.removeClass("disabled");
		} else {
			$('#indPrevistoBopv').bootstrapSwitch('setState', false);
			$("#indPrevistoBopv").attr('disabled','disabled');
			parentIndPrevistoBopv.addClass("disabled");
		}
	});
	$('#indPublicadoBoe').on('switch-change', function(event, state) {
		if (state.value){ //Activando
			activarUrlBOE();
		}else{
			desactivarUrlBOE();
		}
	});
	
	$('#indCorredaccion').on('change', function(event, state) {
		if (event.target.checked){
			mostrarDatosCorredaccion();
			$("#texto").rules("add", "required");
			$("#tipoRedaccion").rules("add", "required");
			$("#participantes").rules("add", "required");
			esCorredaccion = 'S';
		} else {
			ocultarDatosCorredaccion();
			$("#texto").rules("remove", "required");
			$("#tipoRedaccion").rules("remove", "required");
			$("#participantes").rules("remove", "required");
			esCorredaccion = 'N';
		}
	});
	
	$("#datosgeneralesexpedienteform_button_save").on("click", function (){
		$("#tipoEntidadSolicitanteDesc").removeAttr('disabled');
		$("#nombreEntidadSolicitante").removeAttr('disabled');
		$("#nombreGestor").removeAttr('disabled');
		if ($('#idTipoExpediente').rup_combo("getRupValue") === datosExp.tipoExp.interpretacion){
			$("#lugarInterpretacion").removeAttr('disabled');
		}
		$("#datosgeneralesexpedienteform").submit();
    });
	
	$("#datosgeneralesexpediente_cancel_cancelButton").on("click", function (){
		if (!consultaExp || 
				(consultaExp && initForm !== $("#datosgeneralesexpedienteform").rup_form("formSerialize"))){
			comprobarCambiosFormulario();
		}
    });
	
	function comprobarCambiosFormulario(){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
			OKFunction: function(){
				obtenerDatosExpediente();
			}
		});
	}
		
	$("#divExpedienteInterpretacion").find(":input").on("change", function() {
		if(comprobarCambiosExpInter){
			hayCambiosExpInterpretacion = true;			
		}
	});
		
	$("#divExpedienteTradRev").find(":input:not('input#indPublicarBopv,input#indPrevistoBopv,input#indConfidencial,input#indCorredaccion,select#idiomaDestino')").on("change", function() {
		if (comprobarCambiosExpTradRev){
			hayCambiosExpTradRev = true;
		}
	});
	
	$('#indPublicarBopv').on('switch-change', function(event, state) {
		if (comprobarCambiosExpTradRev){
			if (state.value){
				bopv = true;
				hayCambiosExpTradRev = true;
			} else {
				bopv = false;
			}
		}
	});
	
	$('#indPrevistoBopv').on('switch-change', function(event, state) {
		if (comprobarCambiosExpTradRev){
			if (state.value){
				hayCambiosExpTradRev = true;
			}
		}
	});
	
//	$('#indConfidencial').on('switch-change', function(event, state) {
//		if (comprobarCambiosExpTradRev){
//			if (state.value){
//				hayCambiosExpTradRev = true;
//			}
//		}
//	});
	
	$('#indCorredaccion').on('switch-change', function(event, state) {
		if (comprobarCambiosExpTradRev){
			if (state.value){
				hayCambiosExpTradRev = true;
			}
		}
	});
	
	$('#lugarInterpretacion').on("change", function() {
		auto_grow(this);
	});
	
	$("[id^='observaciones']").on("change", function() {
		auto_grow(this);
	});
	
	$("[id^='observaciones']").on("keyup", function() {
		auto_grow(this);
	});
	
	$.validator.addMethod("horaMayor", function(value) {
		
		var fechaIni = $('#fechaIni').val();
		var fechaFin = $('#fechaFin').val();
		
		var esHoraMayor = true;
		
		if (fechaIni === fechaFin){
			fechaFin = fechaFin.split("/");
			fechaIni = fechaIni.split("/");
			
			var horaIni = $('#horaIni').val().split(":");
			var horaFin = $('#horaFin').val().split(":");
			
			var fechaFinFormat=new Date(fechaFin[2],fechaFin[1]-1,fechaFin[0],horaFin[0],horaFin[1]);
			var fechaIniFormat=new Date(fechaIni[2],fechaIni[1]-1,fechaIni[0],horaIni[0],horaIni[1]);
			
			if(fechaFin == ""  || fechaIni == "" || fechaFinFormat.getTime() > fechaIniFormat.getTime()){
				esHoraMayor = true;
			}else{
				esHoraMayor = false;
			}
		}
		
		return esHoraMayor;
		
	}, $.rup.i18n.app.validaciones.esHoraMayorQueIni);
		
	if(detalleSub && subrayado){
		var Expediente = new Object();
	    Expediente.anyo=anyoExpediente;
	    Expediente.numExp=idExpediente;
	    
		$.ajax({
  		   	 type: 'POST'
  		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/expediente/comprobarCamposSeleccionados'
  		 	 ,dataType: 'json'
  		 	 ,contentType: 'application/json' 
  		     ,data: jQuery.toJSON({
  		   		  "expediente":Expediente
  		   	 })		
  		     ,async: false
  		   	 ,success:function(data){
  		   		var campoSelect;
	   		   	for(var i=0;i<data.length;i++){
	   		   		campoSelect = data[i].bitacoraExpediente.subsanacionExp.camposSelecSub.camposSubsanacion.nameaa79078;
	   		   		$("label[for="+ campoSelect +"]:first").addClass( "reqSubsanacion" );
				}
  		   	 }
	   	});
	}
	
	$('#btnEliminarObserv').on("click", function (){
		$('#nombreFicheroInfo_2').val('');
		$('#nombre_2').val('');
		$('#tamanoDoc056_2').val('');
		$('#extensionDoc056_2').val('');
		$('#contentType056_2').val('');
		$('#rutaPif_2').val('');
		$('#oidFichero056_2').val('');
		$('#enlaceDescargaDetalle_2').html('');
		$('#btnEliminarObserv').hide();
		//Si además está en el PID
	});
	
	
	$("#observaciones_mostrarLink_div").on("click", function (){
		obtenerObservacionesExpediente();
		mostrarDetalleObservaciones('');
		$("#observacionesVisibles").val(true);
		visualizarObservaciones = true;
		ocultarLinkObservaciones('');
		var focalizar = $("#observaciones_mostrarLink_div").offset().top;
		$('html,body').animate({scrollTop: focalizar}, 1000);
		datosFormulario = $("#datosgeneralesexpedienteform").serialize();
	});
	$("#observaciones_mostrarLink_divI").on("click", function (){
		obtenerObservacionesExpediente('I');
		mostrarDetalleObservaciones('I');
		$("#observacionesVisibles").val(true);
		visualizarObservaciones = true;
		ocultarLinkObservaciones('I');
		var focalizar = $("#observaciones_mostrarLink_divI").offset().top;
		$('html,body').animate({scrollTop: focalizar}, 1000);
		datosFormulario = $("#datosgeneralesexpedienteform").serialize();
	});
	
	function gestionarObservaciones(){
			if ($("#observacionesVisibles").val() === 'true'){
				mostrarDetalleObservaciones('');
				ocultarLinkObservaciones('');
				obtenerObservacionesExpediente('');
			} else {
				ocultarDetalleObservaciones('');
				if ($('#indObservacionesExpTradRev').val() === 'S'){
					mostrarLinkObservaciones('');
					mostrarSeccionObservaciones('');
				} else {
					ocultarLinkObservaciones('');
					ocultarSeccionObservaciones('');
				}
			}
	}
	function gestionarObservacionesInterpretacion(){
		if ($("#observacionesVisibles").val() === 'true'){
			mostrarDetalleObservaciones('I');
			ocultarLinkObservaciones('I');
			obtenerObservacionesExpediente('I');
		} else {
			ocultarDetalleObservaciones('I');
			if ($('#indObservacionesInterpretacion').val() === 'S'){
				mostrarLinkObservaciones('I');
				mostrarSeccionObservaciones('I');
			} else {
				ocultarLinkObservaciones('I');
				ocultarSeccionObservaciones('I');
			}
		}
}
	function obtenerObservacionesExpediente(tipo){
		
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/datosgeneralesexpediente/observacionesexpediente/"+idExpediente+"/"+anyoExpediente,
			dataType: "json",
			async: false,
			cache: false,
			success:function(data){
				if (tipo=='I'){
					asignarObservacionesInterpretacion(data);
				}else{
					asignarObservaciones(data);
				}
				datosFormulario = $("#datosgeneralesexpedienteform").serialize();
			}
		});
	}
	
	function asignarObservaciones(observaciones){
		$('#observacionesExpTradRev').val(getObservaciones(observaciones));
		
		$('#oidFichero056_2').val(getObservacionesOid(observaciones));
		$('#extensionDoc056_2').val(getObservacionesExtension(observaciones));
		$('#contentType056_2').val(getObservacionesContentType(observaciones));
		$('#nombre_2').val(getObservacionesNombre(observaciones));
		$('#tamanoDoc056_2').val(getObservacionesTamano(observaciones));
		
		if( $('#oidFichero056_2').val() !== '' ){
			var enlace = '<a href="#" class="descargarDocObservaciones" data-anyo="'+anyoExpediente+'" data-numexp="'+idExpediente+'" >'+getObservacionesNombre(observaciones)+'</a>';	   			
   			$('#enlaceDescargaDetalle_2').html(enlace);
   			$('a.descargarDocObservaciones').click(function(event){
   				event.preventDefault();	
   				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
   				var elAnyo = $(this).data("anyo");
   				var elNumExp = $(this).data("numexp");
   				descargarDocumentoObservaciones(elAnyo,elNumExp);
   				desbloquearPantalla();
   			});
   			$('#btnEliminarObserv').show();
		}
		$('#nombreFicheroInfo_2').hide();
		$('#capaBtnPID').hide();
		$('#btnEliminarObserv').hide();
		$('#observacionesExpTradRev').attr('disabled', 'disabled');
		$('#observacionesExpTradRev').css('overflow', 'auto');
		$('#observacionesExpTradRev').css('cursor', 'default');
		
	}
	function asignarObservacionesInterpretacion(observaciones){
		$('#observacionesInterpretacion').val(getObservaciones(observaciones));
		$('#observacionesInterpretacion').attr('disabled', 'disabled');
		$('#observacionesInterpretacion').css('overflow', 'auto');
		$('#observacionesInterpretacion').css('cursor', 'default');
	}
	
	function gestionarDatosGestor(gestorExpediente){
			mostrarDiv("divDatosContactoGestor");
			if (gestorExpediente.solicitante != null && gestorExpediente.solicitante.datosContacto != null){
				$("#telefonoFijoGestor").val(gestorExpediente.solicitante.datosContacto.telfijo031);
				$("#telefonoMovilGestor").val(gestorExpediente.solicitante.datosContacto.telmovil031);
				$("#telefonoEmailGestor").val(gestorExpediente.solicitante.datosContacto.email031);
			}
	}
	
	if (tipoExp === datosExp.tipoExp.interpretacion){
		$('#indPresupuesto').on('switch-change', function(event, state) {
			if (state.value){
				// Activando indicador de presupuesto
				$.ajax({
					type : "GET",
					url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findAllCountTareasAsignadas/"+anyoExpediente+"/"+idExpediente,
					dataType : 'json',
					contentType : 'application/json',
					async : false,
					cache : false,
					success : function(data) {
						
						if (data !== 0) {
							
							$.rup_messages("msgAlert", {
								title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
								message: $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.expTareasAsignadas"), labelGestTrabajoInter)
							});
							
							$('#indPresupuesto').bootstrapSwitch('setState', false);
							
						}
						
					},
					error: function(){
						$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
				   	}
				});
			}
		});
	}
	
	if( estado === expedCerrado){
		llamadasFinalizadas('deshabilitarYCtrolCambios');
	}
});