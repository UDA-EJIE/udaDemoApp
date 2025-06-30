var valorInicialTipoExpediente;
var hayCambiosExpInterpretacion = false;
var hayCambiosExpTradRev = false;
var comprobarCambiosExpTradRev = true;
var comprobarCambiosExpInter = true;
var consultaExp = false;
var initForm = '';
var tramitagune = false;
var buscadorPersonasCreado = false;

function activarUrlBOEDetCons(){
	$('#urlBoe').prop('readonly', false);
	$('#urlBoe').prop('disabled', false );
	$('#urlBoe').rules( "add", {  url: true,  maxlength: 250 });
}
function desactivarUrlBOEDetCons(){
	$('#urlBoe').val('');
	$('#urlBoe').prop('readonly', true);
	$('#urlBoe').prop('disabled', true );
	$('#urlBoe').rules("remove");
	eliminarMensajesValidacionPorCapa('capaUrlBOE');
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
		,disabled: true
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
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
		,disabled: true
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
		,select: function(){
			hayCambiosExpInterpretacion = true;
		},
		onLoadSuccess: function() {
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
		,disabled: true
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
		,select: function(){
			hayCambiosExpTradRev = true;
		},
		onLoadSuccess: function() {
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
		CANCELFunction: function(e){
			$('#idTipoExpediente').rup_combo("setRupValue", valorInicialTipoExpediente);
		}
	});
}

function msgConfirmCambioTipoExpediente(){
	if (valorInicialTipoExpediente !== "" && valorInicialTipoExpediente !== $('#idTipoExpediente').rup_combo("getRupValue")){
		
		if (hayCambiosExpInterpretacion && valorInicialTipoExpediente === "I"){
			msgConfirmTipoExpediente();
		} else if (hayCambiosExpTradRev && valorInicialTipoExpediente !== "I"){
			msgConfirmTipoExpediente();
		} else {
			inicializarDatos();
		}
		
	} else if (valorInicialTipoExpediente !== $('#idTipoExpediente').rup_combo("getRupValue")){
		inicializarDatos();
	}
}

function inicializarDatos(){
	
	$('#indProgramada').attr('disabled','disabled');
	$('#indPresupuesto').attr('disabled','disabled');
	$('#indPublicarBopv').attr('disabled','disabled');
	$('#indPrevistoBopv').attr('disabled','disabled');
	$('#indConfidencial').attr('disabled','disabled');
	$('#indCorredaccion').attr('disabled','disabled');
	$("#capaPestanaCompletaAlta :input").attr("disabled", true);
	$("#capaPestanaCompletaAlta :button").attr("disabled", true);
	
	if ($('#idTipoExpediente').rup_combo("getRupValue") !== ""){
		
		if ($('#idTipoExpediente').rup_combo("getRupValue") === "I"){
			
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
	
	/*JOSE*/
	if (!consultaExp){
		$("#claseDocumento056_detail_table").rules("remove", "required");
		$("#tipoDocumento056_detail_table").rules("remove", "required");
		$("#numPalIzo056_detail_table").rules("remove", "required");
		//$("#titulo056_detail_table").rules("remove", "required");
		$("#oidFichero056_0").rules("remove", "required");
		$("#nombreFicheroInfo_0").rules("remove", "required");
		$("#oidFichero056_1").rules("remove", "required");
		$("#nombreFicheroInfo_1").rules("remove", "required");
	}
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
	
	/*JOSE*/
	if (!consultaExp){
		$("#claseDocumento056_detail_table").rules("add", "required");
		$("#tipoDocumento056_detail_table").rules("add", "required");
		$("#numPalIzo056_detail_table").rules("add", "required");
		//$("#titulo056_detail_table").rules("add", "required");
		
		$("#oidFichero056_0").rules("add", "required");
		$("#nombreFicheroInfo_0").rules("add", "required");
		
		if($('#idTipoExpediente').rup_combo("getRupValue")==='R'){
			$("#oidFichero056_1").rules("add", "required");
			$("#nombreFicheroInfo_1").rules("add", "required");
		}else{
			$("#oidFichero056_1").rules("remove", "required");
			$("#nombreFicheroInfo_1").rules("remove", "required");
		}
	}
	
}

function limpiarCamposExpInterpretacion(){
	$("#modoInterpretacion").val("");
	$("#tipoActo").val("");
	$("#tipoPeticionario").val("A");
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
}

function limpiarCamposExpTradRev(){
	$("#idiomaDestino").val("");
	$("#texto").val("");
	$("#tipoRedaccion").val("");
	$("#participantes").val("");
	$("#refTramitagune").val("");
	$("#observacionesExpTradRev").val("");
	$("#observacionesExpTradRev").change();
	
	comprobarCambiosExpInter = false;
	hayCambiosExpInterpretacion = false;
	$('#indPublicarBopv').bootstrapSwitch('setState', false);
	$('#indPrevistoBopv').bootstrapSwitch('setState', false);
	$('#indConfidencial').bootstrapSwitch('setState', false);
	$('#indCorredaccion').bootstrapSwitch('setState', false);
}

function mostrarDetalleObservaciones(){
	mostrarDiv('divDetalleObservaciones');

	$("#observacionesExpTradRev").each(function() {
        $(this).height($(this).prop('scrollHeight'));
    });
}

function ocultarDetalleObservaciones(){
	ocultarDiv('divDetalleObservaciones');
}

function mostrarDivObservaciones(){
	mostrarDiv('divObservaciones');
}

function ocultarDivObservaciones(){
	ocultarDiv('divObservaciones');
}

function mostrarLinkObservaciones(){
	mostrarDiv('observaciones_mostrarLink_div');
}

function ocultarLinkObservaciones(){
	ocultarDiv('observaciones_mostrarLink_div');
}

function buscarDireccion(){
	var tipo=jQuery('#tipoNora').val();
	var nora = {t17_nora_i18n: $.rup.lang,
			t17_nora_tipo:3,
			title: $.rup.i18nParse($.rup.i18n.app,"label.searchAddress"),
			url: SERVIDOR_NORA +"/sidl/sidljQuery/dynamic_modal_form3.html"};
	if(tipo==0){
		jQuery.extend(nora, {
			t17_nora_portal_id: jQuery("#portalId").val(),
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	} else if(tipo==1 || tipo==2){
		jQuery.extend(nora, {
			t17_nora_localidad_id: jQuery("#localidadId").val(),
			t17_nora_calle: jQuery("#calle").val(),
			t17_nora_portal_numero: jQuery("#portal").val(),
			t17_nora_portal_cp: jQuery("#cp").val(),
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	} else if(tipo==3){
		jQuery.extend(nora, {
			t17_nora_pais_id: jQuery("#paisId").val(),
			t17_nora_provincia: jQuery("#provincia").val(),
			t17_nora_municipio: jQuery("#localidad").val(),
			t17_nora_calle: jQuery("#calle").val(),
			t17_nora_portal_cp: jQuery("#cp").val()});
	} else{
		jQuery.extend(nora, {
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	}
	openNoraModalDynamicForm(nora);
}

function confirm_t17i_form(O_O) {
	//Aquí se setearán los valores provenientes del formulario de NORA
	$('#tipoNora').val(O_O.tipo);
	limpiarCamposDireccion();

	var tipo=$('#tipoNora').val();
	if(tipo==3){
		// ES EXTRANJERO
		$('#pais').val(O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.descripcionOficial.toUpperCase());
		$('#paisId').val(O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.id);
		$('#portalId').val('0');
		$('#provincia').val(O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase());
		$('#localidad').val(O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase());
		$('#calle').val(O_O.portal.calle.descripcionOficial.toUpperCase());
		$('#cp').val(O_O.portal.codigoPostal.toUpperCase());
	} else {
		var txtCalle = "";
		var txtPortal = "";
		var txtEscalera = "";
		var txtPiso = "";
		var txtMano = "";
		var txtPuerta = "";
		var txtCodigoPostal = "";
		var txtLocalidad = "";
		var txtProvincia = "";
		// ES NACIONAL
		//el pais lo vamos a dejar vacio
		// CODIGO
		if (tipo==0) {
			$('#portalId').val(O_O.portal.id);
			$('#localidadId').val(O_O.portal.calle.localidad[0].id);
		} else {
			$('#localidadId').val(O_O.portal.calle.localidad[0].id);
		}
		// TH
		$('#provinciaId').val(O_O.portal.calle.localidad[0].municipio.provincia.id);
		txtProvincia = O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase();
		$('#provincia').val(txtProvincia);
		// MUN
		$('#municipioId').val(O_O.portal.calle.localidad[0].municipio.id);
		$('#municipio').val(O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase());
		// LOC
		txtLocalidad = O_O.portal.calle.localidad[0].descripcionOficial.toUpperCase();
		$('#localidad').val(txtLocalidad);
		// CALLE
		if (O_O.portal.calle.descripcionSecundaria == undefined || O_O.portal.calle.descripcionSecundaria == "" ){
			txtCalle = O_O.portal.calle.descripcionOficial.toUpperCase();
		} else {
			txtCalle = O_O.portal.calle.descripcionOficial.toUpperCase() + " | " + O_O.portal.calle.descripcionSecundaria.toUpperCase();
		}
		$('#calle').val(txtCalle);
		// PORTAL
		if (tipo == '0') {
			var numero = O_O.portal.numero.toUpperCase();
			var bis = O_O.portal.bis;
			var acepcion = O_O.portal.acepcion;
			var bloque = O_O.portal.bloque;
			
			var portal = numero;
			
			// BIS
			if (bis!=''){
				if (bis=='Y'){
					bis = 'BIS';
				}
				portal += " Bis: " + bis;
			}
			
			// BLOQUE
			if(bloque!=''){
				portal += " Bloque:" + bloque;
			}
			
			// ACEPCION
			if (acepcion!=''){
			    portal += " - " + acepcion;		        
			}
			
			txtPortal = portal;
		} else {
			txtPortal = O_O.portal.numero.toUpperCase();
		}
		$('#portal').val(txtPortal);
		// CP
		txtCodigoPostal = O_O.portal.codigoPostal.toUpperCase();
		$('#cp').val(txtCodigoPostal);
		// RESTO
		txtEscalera = O_O.escalera.toUpperCase();
		$('#escalera').val(txtEscalera);
		txtPiso = O_O.piso.toUpperCase();
		$('#piso').val(txtPiso);
		txtMano = O_O.mano.toUpperCase();
		$('#mano').val(txtMano);
		txtPuerta = O_O.puerta.toUpperCase();
		$('#puerta').val(txtPuerta);
		$('#aproxPostal').val(O_O.aprox_postal.toUpperCase());
		
		var txtLugarInterpretacion = txtCalle + ", " + txtPortal + ", " + txtEscalera + " " + txtPiso + " " + txtMano + " " + txtPuerta + ", " + txtCodigoPostal + " " + txtLocalidad + ", " + txtProvincia;
		$('#lugarInterpretacion').val(txtLugarInterpretacion);
		$('#lugarInterpretacion').change();
	}
}
	
function limpiarCamposDireccion(){
	jQuery('#escalera').val("");
	jQuery('#piso').val("");
	jQuery('#mano').val("");
	jQuery('#puerta').val("");
	jQuery('#portal').val("");
	jQuery('#portalId').val("");
	jQuery('#aproxPostal').val("");
	jQuery('#localidad').val("");
	jQuery('#localidadId').val("");
	jQuery('#municipio').val("");
	jQuery('#municipioId').val("");
	jQuery('#pais').val("");
	jQuery('#paisId').val("");
	jQuery('#provinciaId').val("");
	jQuery('#provincia').val("");
	jQuery('#calle').val("");
	jQuery('#calleId').val("");
	jQuery('#cp').val("");	
}

function inicializarDatosExpInter(){
	mostrarDiv('divExpedienteInterpretacion');
	ocultarDiv('divExpedienteTradRev');
	ocultarDiv('divDocumentosExpediente');
	
	fnFechaDesdeHasta("fechaIni", "fechaFin");
	
	crearComboModoInterpretacion('modoInterpretacion');
	crearComboTipoActo('tipoActo');
	crearComboTipoPeticionario('tipoPeticionario');
	
	inicializarComboTipoPeticionario();
	
	if (origen === 'C'){
		$('#tipoPeticionario').rup_combo('disable');
		$('#fechaIni').rup_date('disable');
		$('#fechaFin').rup_date('disable');
		$('#buscar_direccion_nora').addClass('oculto');
	}
	
	validarCamposExpInterpretacion();
}

function inicializarComboTipoPeticionario(){
	if (!consultaExp && $('#idTipoExpediente').rup_combo("getRupValue") === "I") {
		if ($('#tipoEntidadSolicitante').val() === 'L'){
			$('#tipoPeticionario').rup_combo("setRupValue", "P");
		} else {
			$('#tipoPeticionario').rup_combo("setRupValue", "A");
		}
	}
}

function inicializarDatosExpTradRev(){
	ocultarDiv('divExpedienteInterpretacion');
	mostrarDiv('divExpedienteTradRev');
	mostrarDiv('divDocumentosExpediente');
	
	crearComboIdiomaDestino('idiomaDestino');
	
	var parentIndCorredaccion = $('#indCorredaccion').parent().parent();
	if ($('#idTipoExpediente').rup_combo("getRupValue") === "T"){
		$('#indCorredaccion').bootstrapSwitch('setState', false);
		ocultarDatosCorredaccion();
	} 
	
	/*JOSE*/
	if (!consultaExp){
		creaComboClaseDocumento($('#idTipoExpediente').rup_combo("getRupValue"));
	}
	/*JOSE*/		
	validarCamposExpTradRev();
}

jQuery(function($){
	//A lo bruto, elimino  el contenido de la otra pestaña
	$('#capaPestanaCompletaDoc').remove();
	eliminarMensajes();
	datosFormularioDoc = '';
	eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
	
	$('#datosgeneralesexpedienteform_feedback').rup_feedback({
		block : false
	});
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	$('#fechaAlta').rup_date();
	
	if(idExpediente === '' && anyoExpediente === ''){
		obtenerDatosExpedienteAlta();
	}else{
		obtenerDatosExpediente();
	}
	
	if (origen==='C'){
		if (visualizarObservaciones){
			$("#observacionesVisibles").val(true);
		} else {
			$("#observacionesVisibles").val(false);
		}
	}	
	
	function obtenerDatosExpedienteAlta(){
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/datosgeneralesexpediente/datosexpediente",
			dataType: "json", 
			cache: false,
			success:function(data){
				$("#tabsExpediente").rup_tabs("disableTabs", {
					idTab : "tabsExpediente",
					position : 1
				});
				
				consultaExp = false;
				
				comprobarFormularios = false;
								
				setExpedienteData(data);
			}
		});
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
				ocultarDiv('divDocumentosExpediente');				
				deshabilitarComboTipoExp();
				mostrarDiv("divDatosContactoGestor");
				//mostrar datos de contacto
				if (data.gestorExpediente.solicitante != null && data.gestorExpediente.solicitante.datosContacto != null){
					$("#telefonoFijoGestor").val(data.gestorExpediente.solicitante.datosContacto.telfijo031);
					$("#telefonoMovilGestor").val(data.gestorExpediente.solicitante.datosContacto.telmovil031);
					$("#telefonoEmailGestor").val(data.gestorExpediente.solicitante.datosContacto.email031);
				}
				//si tradrev mostrar capa boe
				if (data.idTipoExpediente === 'T'){
					$('#capaBoe').show();
					if (data.expedienteTradRev.indPublicadoBoe === 'S'){
						$('#indPublicadoBoe').bootstrapSwitch('setState', true);
						activarUrlBOEDetCons();
					} else {
						$('#indPublicadoBoe').bootstrapSwitch('setState', false);
						desactivarUrlBOEDetCons();
					}
					$('#urlBoe').val(data.expedienteTradRev.urlBoe);
					$('#urlBoe').attr('disabled','disabled');
					
				}
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
				
				initForm = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
				datosFormulario = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
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
		if (expedienteInterpretacion != null && idTipoExpediente === "I"){
			
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
			fechaFinalIZO = expedienteInterpretacion.fechaFin;
			horaFinalIZO = expedienteInterpretacion.horaFin;
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
				$('#indPresupuesto').bootstrapSwitch('setState', true);
			}else{
				$('#indPresupuesto').bootstrapSwitch('setState', false);
			}
			
			$('#observacionesExpInter').val(getObservaciones(expedienteInterpretacion.observaciones));
			
		}
	}
	function descargarDocumentoObservaciones(elAnyo,elNumExp){			
		window.open('/aa79bItzulnetWar/ficheros/descargarDocumentoObservaciones/'+elAnyo+'/'+elNumExp);
	}
	function setDatosExpTradRev(expedienteTradRev, idTipoExpediente){
		if (expedienteTradRev != null 
				&& (idTipoExpediente === "T" || idTipoExpediente === "R")){
			
			if (expedienteTradRev.idIdioma != null){
				$('#idiomaDestino').rup_combo("setRupValue", expedienteTradRev.idIdioma);
				idiomaDestino = expedienteTradRev.idIdioma;
			}
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
				$('#indConfidencial').bootstrapSwitch('setState', true);
			}else{
				$('#indConfidencial').bootstrapSwitch('setState', false);
			}
			
			expedienteConfidencial = expedienteTradRev.indConfidencial;
			
			if (expedienteTradRev.indCorredaccion === 'S'){
				mostrarDatosCorredaccion();
				$('#indCorredaccion').bootstrapSwitch('setState', true);
			} else {
				ocultarDatosCorredaccion();
				$('#indCorredaccion').bootstrapSwitch('setState', false);
			}
			
			gestionarObservaciones();
			
			if (origen==='A'){
				asignarObservaciones(expedienteTradRev.observaciones);
			}
		}
	}
	
	function setExpedienteData(data){
		if (data != null){
			$('#fechaAlta').val(data.fechaAlta);
			$('#horaAlta').val(data.horaAlta);
			$('#anyoExpediente').val(data.anyo);
			$('#numeroExpediente').val(data.numExp);
			$('#tituloExpediente').val(data.titulo);
			
			setDatosTipoExpediente(data.idTipoExpediente);
			setDatosTecnico(data.tecnico);
			setDatosGestorExpediente(data.gestorExpediente);
			setDatosExpInter(data.expedienteInterpretacion, data.idTipoExpediente);
			setDatosExpTradRev(data.expedienteTradRev, data.idTipoExpediente);
			setDatosExpedientesRelacionados(data.listaExpedientesRelacionados);
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
		},
		success: function(data){
			idExpediente = data.numExp;
			anyoExpediente = data.anyo;
			
			if (consultaExp) {
				
				desbloquearPantalla();
				
				$.rup_messages("msgOK", {
        			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.feedback.modifyOK"),
        			title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
        		});
			} else {
				/*JOSE*/
				//CAMBIAR URL TAB DOC
				//Ponemos el enlace a la pestaña de el documento concreto 
				$("[id='#pestdocumentos']").attr('href', "/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/listado/"+anyoExpediente+"/"+idExpediente+"/"+origen)	
				$("#tabsExpediente").rup_tabs("enableTabs", {
					idTab : "tabsExpediente",
					position : 1
				});
				$("#tabsExpediente").rup_tabs("selectTab",{
					idTab: "tabsExpediente",
					position: 1
				});
				
				$("[id='expediente_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"boton.expedientesRelacionados")+"']").button("enable");
				$("[id='expediente_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"boton.contactoFacturacionExpediente")+"']").button("enable");
				
				//Actualizar bitácora + cabecera
				bitacoraUpdate(true);
				mostrarDiv('cabecera_altaExpedientes');
				
				$('#divDocumentosExpediente').remove();
				
				desbloquearPantalla();
			}
			
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
				"expedienteInterpretacion.horaFin": { hora: true, maxlength: 5, horaMayor: true },
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
	
	if (!buscadorPersonasCreado){
		$("#buscadorPersonas").buscador_personas({destino: 'S', multiselect: false, callback: seleccionarPersona});
		
		buscadorPersonasCreado = true;
	}
	
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
				
				inicializarComboTipoPeticionario();
				
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
	
	$("#gestor_modificarLink").on("click", function (event){
		event.preventDefault();
		$("#buscadorPersonas").buscador_personas("open");
	});

	// Si tipo expediente <> interpretacion
	$('#indPublicarBopv').on('change', function(event, state) {
		
		var parentIndPrevistoBopv = $("#indPrevistoBopv").parent().parent();
		if (event.target.checked){
			$('#indPrevistoBopv').bootstrapSwitch('setState', false);
		}
		
	});
	
	$('#indCorredaccion').on('change', function(event, state) {
		
		if (event.target.checked){
			mostrarDatosCorredaccion();
			$("#texto").rules("add", "required");
			$("#tipoRedaccion").rules("add", "required");
			$("#participantes").rules("add", "required");
		} else {
			ocultarDatosCorredaccion();
			$("#texto").rules("remove", "required");
			$("#tipoRedaccion").rules("remove", "required");
			$("#participantes").rules("remove", "required");
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
	
	$("#divExpedienteTradRev").find(":input").on("change", function() {
		if (comprobarCambiosExpTradRev){
			hayCambiosExpTradRev = true;
		}
	});
	
	$('#buscar_direccion_nora').on("click", function (){
		buscarDireccion();
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
  		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/comprobarCamposSeleccionados'
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
	
	$("#observaciones_mostrarLink_div").on("click", function (){
		obtenerObservacionesExpediente();
		mostrarDetalleObservaciones();
		$("#observacionesVisibles").val(true);
		visualizarObservaciones = true;
		ocultarLinkObservaciones();
	});
	
	function gestionarObservaciones(){
		if (origen==='C'){
			mostrarDivObservaciones();
			if ($("#observacionesVisibles").val() === 'true'){
				mostrarDetalleObservaciones();
				ocultarLinkObservaciones();
				obtenerObservacionesExpediente();
			} else {
				ocultarDetalleObservaciones();
				mostrarLinkObservaciones();
			}
		} else {
			ocultarDivObservaciones();
			mostrarDetalleObservaciones();
		}
	}

	function obtenerObservacionesExpediente(){
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/datosgeneralesexpediente/observacionesexpediente/"+idExpediente+"/"+anyoExpediente,
			dataType: "json",
			async: false,
			cache: false,
			success:function(data){
				asignarObservaciones(data);
				
				datosFormulario = $("#datosgeneralesexpedienteform").serialize();
			}
		});
	}
	
	function asignarObservaciones(observaciones){
		$('#observacionesExpTradRev').val(getObservaciones(observaciones));
		
		$('#oidFichero056_2').val(getObservacionesOid(observaciones));
		
		if( $('#oidFichero056_2').val() !== '' ){
			var enlace = '<a href="#" class="descargarDocObservaciones" data-anyo="'+anyoExpediente+'" data-numexp="'+idExpediente+'" >'+labelDescargar+'</a>';	   			
   			$('#enlaceDescargaDetalle_2').html(enlace);
   			$('a.descargarDocObservaciones').click(function(event){
   				event.preventDefault();	
   				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
   				var elAnyo = $(this).data("anyo");
   				var elNumExp = $(this).data("numexp");
   				descargarDocumentoObservaciones(elAnyo,elNumExp);
   				desbloquearPantalla();
   			});
			
		}
		if (origen==='C'){
			$('#nombreFicheroInfo_2').hide();
			$('#capaBtnPID').hide();
		}
	}
	
	function setDatosExpedientesRelacionados(listExpRelacionados){
		if (listExpRelacionados.length > 0){
			mostrarDiv('divMostrarExpdientesRelacionadosRel');
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
			ocultarDiv('divMostrarExpdientesRelacionadosRel');
		}
	}
	
});