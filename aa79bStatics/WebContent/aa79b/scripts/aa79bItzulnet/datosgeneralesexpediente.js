var valorInicialTipoExpediente;
var hayCambiosExpInterpretacion = false;
var hayCambiosExpTradRev = false;
var comprobarCambiosExpTradRev = true;
var comprobarCambiosExpInter = true;
var consultaExp = false;
var initForm = '';
var idTipoExpediente;
//var listaTiposDoc;
var buscadorPersonasCreado = false;
var cambioIdTipoExpediente = false;

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
			if(idExpediente === '' && anyoExpediente === ''){
				// Por defecto, idioma = euskera (2)
				$('#'+id).rup_combo("setRupValue", datosExp.idioma.euskera);
			}
	   		
	   		if(esTecnico === "C"){
	   			$('#'+id).rup_combo("disable");
	   		}else{
	   			$('#'+id).rup_combo("enable");
	   		}
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

function cambiarTipoExpedientePL() {
	
	var Expediente = 
    {
        "anyo": anyoExpediente,
        "numExp": idExpediente,
        "idTipoExpediente": $('#idTipoExpediente').rup_combo("getRupValue")
    };
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/datosgeneralesexpediente/cambiarTipoExpediente'
        ,dataType: 'json'
        ,contentType: 'application/json'
        ,data: $.toJSON({
	   		"expediente":Expediente
	   	})
	   	,cache: false 
        ,success:function(){
        	cambioIdTipoExpediente = true;
        	$("#datosgeneralesexpedienteform_button_save").click();
        	valorInicialTipoExpediente = $('#idTipoExpediente').rup_combo("getRupValue");
	        desbloquearPantalla();
        }
		,error: function(error){
			$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
}

function msgConfirmCambioTipoExpedienteNew(){
	if (valorInicialTipoExpediente != $('#idTipoExpediente').rup_combo("getRupValue")) {
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.app,"comun.atencion"),
			message: $.rup.i18nParse($.rup.i18n.app,"mensajes.msgCambioTipoExpediente"),
			OKFunction: function(){
				cambiarTipoExpedientePL();
			},
			CANCELFunction: function(e){
				$('#idTipoExpediente').rup_combo("setRupValue", valorInicialTipoExpediente);
			}
		});
	}
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
}

function crearComboTipoExpediente(){
	var comboSource = [];
	if (idTipoExpediente === datosExp.tipoExp.traduccion 
			|| idTipoExpediente === datosExp.tipoExp.revision) {
		comboSource =[
			{
				label: txtTipoExpedienteTraduccion,
				value: datosExp.tipoExp.traduccion
			},
			{
				label: txtTipoExpedienteRevision,
				value: datosExp.tipoExp.revision
			}
		];
		
	} else {
		comboSource =[
			{
				label: txtTipoExpedienteInterpretacion,
				value: datosExp.tipoExp.interpretacion
			},
			{
				label: txtTipoExpedienteTraduccion,
				value: datosExp.tipoExp.traduccion
			},
			{
				label: txtTipoExpedienteRevision,
				value: datosExp.tipoExp.revision
			}
		];
	}
	
	if ($('#idTipoExpediente-button').length){
		$('#idTipoExpediente').rup_combo("clear");
	}
	
    $('#idTipoExpediente').rup_combo({
    	source : comboSource,
		width : "200",
		ordered : false,
		rowStriping : true,
  		select: function(){	
  			if (anyoExpediente != '' && idExpediente != '') {
  				msgConfirmCambioTipoExpedienteNew();
  			} else {
  				msgConfirmCambioTipoExpediente();
  			}
  		},
  		onLoadSuccess: function(){
  			inicializarDatos();
  		}
  	});
}

//function obtenerListaTiposDoc(){
//	$.ajax({
//	   	 type: 'GET'		   	 
//	   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta'
//	 	 ,dataType: 'json'
//	 	 ,contentType: 'application/json' 
//	     ,cache: false
//	   	 ,success:function(data){
//	   		if (!isEmpty(data)) {
//	   			listaTiposDoc = data;
//	   		}
//	   	 }
//  	 	,error: function(){
//	   		$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
//	   	 }
//	 });
//}
//if ( origen==='A' && !consultaExp ){
//	obtenerListaTiposDoc();
//}

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
	$("#tipoEntidadSolicitanteDesc").rules("add", "required");
	$("#nombreEntidadSolicitante").rules("add", "required");
	$("#nombreGestor").rules("add", "required");
	
	$("#indPublicarBopv").rules("remove", "required");
	$("#idiomaDestino").rules("remove", "required");
	$("#indConfidencial").rules("remove", "required");
	
	/*JOSE*/
	if (!consultaExp){
		$("#claseDocumento056_detail_table").rules("remove", "required");
		$("#tipoDocumento056_detail_table").rules("remove", "required");
		$("#numPalIzo056_detail_table").rules("remove", "required");
		//$("#titulo056_detail_table").rules("remove", "required");
		
//		$("#personaContacto056_0").rules("remove", "required");
//		$("#emailContacto056_0").rules("remove", "required");
//		$("#direccionContacto056_0").rules("remove", "required");
//		$("#entidadContacto056_0").rules("remove", "required");
		$("#oidFichero056_0").rules("remove", "required");
		$("#nombreFicheroInfo_0").rules("remove", "required");
//		$("#personaContacto056_1").rules("remove", "required");
//		$("#emailContacto056_1").rules("remove", "required");
//		$("#direccionContacto056_1").rules("remove", "required");
//		$("#entidadContacto056_1").rules("remove", "required");
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
	$("#tipoEntidadSolicitanteDesc").rules("add", "required");
	$("#nombreEntidadSolicitante").rules("add", "required");
	$("#nombreGestor").rules("add", "required");
	if (!consultaExp){
		$("#claseDocumento056_detail_table").rules("add", "required");
		$("#tipoDocumento056_detail_table").rules("add", "required");
		$("#numPalIzo056_detail_table").rules("add", "required");
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
	// Por defecto, idioma = euskera (2)
	$("#idiomaDestino").rup_combo("setRupValue", datosExp.idioma.euskera);
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

function mostrarDetalleObservaciones(){
	mostrarDiv('divDetalleObservaciones');
}

function ocultarDetalleObservaciones(){
	ocultarDiv('divDetalleObservaciones');
}

function mostrarLinkObservaciones(){
	$('#observaciones_mostrarLink_div').removeClass('oculto');
	$('#observaciones_mostrarLink_div').addClass('inline');
}

function ocultarLinkObservaciones(){
	$('#observaciones_mostrarLink_div').addClass('oculto');
	$('#observaciones_mostrarLink_div').removeClass('inline');
}

function mostrarSeccionObservaciones(){
	$('#divSeccionObservaciones').removeClass('oculto');
}

function ocultarSeccionObservaciones(){
	$('#divSeccionObservaciones').addClass('oculto');
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
		var txtPais = O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.descripcionOficial.toUpperCase();
		var txtProvinciaExt = O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase();
		var txtLocalidadExt = O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase();
		var txtCalleExt = O_O.portal.calle.descripcionOficial.toUpperCase();
		var txtCPExt = O_O.portal.codigoPostal.toUpperCase();
		
		// ES EXTRANJERO
		$('#pais').val(txtPais);
		$('#paisId').val(O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.id);
		$('#portalId').val('0');
		$('#provincia').val(txtProvinciaExt);
		$('#localidad').val(txtLocalidadExt);
		$('#calle').val(txtCalleExt);
		$('#cp').val(txtCPExt);
		
		var txtLugarInterpretacion = txtCalleExt + ", " + txtLocalidadExt + ", " + txtProvinciaExt + " " + txtCPExt + ". " + txtPais;
		$('#lugarInterpretacion').val(txtLugarInterpretacion);
		$('#lugarInterpretacion').change();
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
	
	validarCamposExpInterpretacion();
}

function inicializarComboTipoPeticionario(){
	if (!consultaExp && $('#idTipoExpediente').rup_combo("getRupValue") === datosExp.tipoExp.interpretacion) {
		if ($('#tipoEntidadSolicitante').val() === datosExp.tipoEntidad.entidad || $('#tipoEntidadSolicitante').val() === datosExp.tipoEntidad.departamento) {
			$('#tipoPeticionario').rup_combo("setRupValue", datosExp.tipoPeticionario.administracionPublica);
		} else if ($('#tipoEntidadSolicitante').val() === datosExp.tipoEntidad.empresa){
			$('#tipoPeticionario').rup_combo("setRupValue", datosExp.tipoPeticionario.particular);
		} else {
			$('#tipoPeticionario').rup_combo("setRupValue", "");
		}
	}
}

function inicializarDatosExpTradRev(){
	ocultarDiv('divExpedienteInterpretacion');
	mostrarDiv('divExpedienteTradRev');
	mostrarDiv('divDocumentosExpediente');
	
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
	$('#indVisible056_detail_table').bootstrapSwitch('setState', true);
	/*JOSE*/
	if (!consultaExp){
		creaComboClaseDocumento($('#idTipoExpediente').rup_combo("getRupValue"));
	}else{
		$("[id^='pidButton']").unbind("click");
		$("[id^='pidButton']").on("click", function() {
			if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
			else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
			else $("#idBotonUpload").val("0");
			$('#fichero').click();
		});
	}
	/*JOSE*/		
	validarCamposExpTradRev();
}

function deshabilitarFormulario(){
	if(esTecnico === 'C'){
		$('#datosgeneralesexpedienteform input').attr('readonly', 'readonly');
		$('#datosgeneralesexpedienteform textarea').attr('readonly', 'readonly');
		$('#datosgeneralesexpedienteform select').attr('readonly', 'readonly');
		$("#datosgeneralesexpedienteform :checkbox").attr("disabled", "true");
		$('#datosgeneralesexpedienteform input').attr("disabled", "true");
		$('#datosgeneralesexpedienteform textarea').attr("disabled", "true");
		$('#datosgeneralesexpedienteform select').attr("disabled", "true");
		$('#datosgeneralesexpedienteform_button_save').hide();
		$('#datosgeneralesexpediente_cancel_cancelButton').hide();
		
		$("[id='detalleExpediente_toolbar##"+"rechazar"+"']").hide();
		$("[id='detalleExpediente_toolbar##"+"requerirSub"+"']").hide();
		$("[id='detalleExpediente_toolbar##"+"oficio"+"']").hide();
		$("[id='detalleExpediente_toolbar##"+"otrosToolbarExpediente"+"']").hide();
		
	}else{
		$("[id='detalleExpediente_toolbar##"+"rechazar"+"']").show();
		$("[id='detalleExpediente_toolbar##"+"requerirSub"+"']").show();
		$("[id='detalleExpediente_toolbar##"+"oficio"+"']").show();
		$("[id='detalleExpediente_toolbar##"+"otrosToolbarExpediente"+"']").show();
		
	}
	deshabilitarSubsanacion();
}

function deshabilitarSubsanacion(){
	if(esTecnico === 'C'){
		$('#detalleSubsanacion_button_save').hide();
		$('#detalleSubsanacion_link_cancel').hide();
		$('#detFechaLimite').rup_date("disable");
	}else if(esSubsanado === 'N'){
		$('#detalleSubsanacion_button_save').show();
		$('#detalleSubsanacion_link_cancel').hide();
		$('#detFechaLimite').rup_date("enable");
	}else if(esSubsanado === 'S'){
		$('#detalleSubsanacion_button_save').show();
		$('#detalleSubsanacion_link_cancel').show();
		$('#detFechaLimite').rup_date("disable");
	}
}

function comprobarFicherosEncriptados( requiereEncriptado){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	$.ajax({
	   	 type: 'GET'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/comprobarencriptados/'+anyoExpediente+"/"+idExpediente+"/"+requiereEncriptado
	 	 ,dataType: 'json' 	 	 	
	 	 ,cache: false
	 	 ,async: true
	   	 ,success:function(data){
	   		if (data !== null){    
	   			//existe el registro			   			
		   		if (data > 0 || !isEmpty($('#oidFichero056_2').val()) ){
		   			var msgAviso;
		   			if (origen === 'C'){
		   				msgAviso = $.rup.i18n.app.mensajes.errorCambioConfidencialSubsanar;
		   			}else{
		   				msgAviso = ( requiereEncriptado === 'N' )?$.rup.i18n.app.mensajes.errorDocsNoEncriptados:$.rup.i18n.app.mensajes.errorDocsEncriptados;
		   			}
		   			$.rup_messages("msgError", { 
						title: $.rup.i18n.base.rup_ajax.errorTitle,
						message: msgAviso
		   				});	
		   			if ( requiereEncriptado === 'N' ){
		   				$('#indConfidencial').bootstrapSwitch('setState', false, true);
		   			}else{
		   				$('#indConfidencial').bootstrapSwitch('setState', true, true);
		   			}
		   			
		   		}
	   		}else{
	   			$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
	   		}	
	   		desbloquearPantalla();
	   	 }
	 	,error: function(){	  	 		
	 		$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	 		desbloquearPantalla();
	   	 }
	 });
}
function fncEliminarObs(){
	$('#nombreFicheroInfo_2').val('');
	$('#nombre_2').val('');
	$('#tamanoDoc056_2').val('');
	$('#extensionDoc056_2').val('');
	$('#contentType056_2').val('');
	$('#rutaPif_2').val('');
	$('#oidFichero056_2').val('');
	$('#enlaceDescargaDetalle_2').html('');
	$('#btnEliminarObserv').hide();
}

function vaciarCamposFicherosAlta(){
	
	$('#btnEliminarObserv').click();
	$('#nombreFicheroInfo_0').val('');
	$('#nombre_0').val('');
	$('#extensionDoc056_0').val('');
	$('#tamanoDoc056_0').val('');
	$('#contentType056_0').val('');
	$('#oidFichero056_0').val('');
	$('#indEncriptado056_0').val('');
	$('#enlaceDescargaDetalle_0').html('');
	$('#nombreFicheroInfo_1').val('');
	$('#nombre_1').val('');
	$('#extensionDoc056_1').val('');
	$('#tamanoDoc056_1').val('');
	$('#contentType056_1').val('');
	$('#oidFichero056_1').val('');
	$('#indEncriptado056_1').val('');
	$('#enlaceDescargaDetalle_1').html('');
	fncEliminarObs();
}

jQuery(function($){

	//A lo bruto, elimino TODO el contenido de la otra pestaña
	$('#capaPestanaCompletaDoc').remove();
	eliminarMensajes();
	datosFormularioDoc = '';
	eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
	
	$('#datosgeneralesexpedienteform_feedback').rup_feedback({
		block : false, delay: 3000, gotoTop: true
	});
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	if (idExpediente !== ''){
		$('#indConfidencial').on('switch-change', function(event, state) {
			comprobarFicherosEncriptados( (state.value?"N":"S")  );
		});
	}else{
		//alta, docs en la 1a pestaña
		$('#indConfidencial').on('switch-change', function(event, state) {
			if (state.value){ 
				//Activando
				
				if (($('#indEncriptado056_0').val() === 'N' ) || ($('#indEncriptado056_1').val() === 'N'|| !isEmpty($('#oidFichero056_2').val()) )){
					
					$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"comun.atencion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorDocsNoEncriptadosConfirm"),
						OKFunction: function(){
							vaciarCamposFicherosAlta();
							$('#reqEncriptado').val("S");
							crearCmbTipoDocumento();
						},
						CANCELFunction: function(e){
							$('#indConfidencial').bootstrapSwitch('setState', false, true);
						}
					});
				}else{
		   			//si está visible la zona de documentos de esta pestaña, recargar el combo tiposDocumento de esta pantalla 
//		   			if ($('#divDocumentosExpediente').hasClass("in")){
		   			crearCmbTipoDocumento();
//		   			}
		   		}
				
			}else{
				if (($('#indEncriptado056_0').val() === 'S' ) || ($('#indEncriptado056_1').val() === 'S'|| !isEmpty($('#oidFichero056_2').val()) )){
					
					$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"comun.atencion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorDocsEncriptadosConfirm"),
						OKFunction: function(){
							vaciarCamposFicherosAlta();
							$('#reqEncriptado').val("N");	
							crearCmbTipoDocumento();
						},
						CANCELFunction: function(e){
							$('#indConfidencial').bootstrapSwitch('setState', true, true);
						}
					});
				}else{
					crearCmbTipoDocumento();
				}
			}
		});
	}

	$('#fechaAlta').rup_date();
	crearComboIdiomaDestino('idiomaDestino');
	
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
				
				idTipoExpediente = data.idTipoExpediente;
				
				crearComboTipoExpediente();
								
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
				idTipoExpediente = data.idTipoExpediente;
				crearComboTipoExpediente();
				
				setExpedienteData(data);
				ocultarDiv('divDocumentosExpediente');	
				
				
				if (data.idTipoExpediente === datosExp.tipoExp.interpretacion) {
					deshabilitarComboTipoExp();
				}
				
				gestionarDatosGestor(data.gestorExpediente);
				$('#datosgeneralesexpedienteform_button_save').text($.rup.i18nParse($.rup.i18n.base,"rup_global.save"));
				
				$("#tabsExpediente").rup_tabs("selectTab",{
					idTab: "tabsExpediente",
					position: 0
				});
				
				$('#indSolicitadoTramitagune').bootstrapSwitch('toggleDisabled',	true, true);
				if(data.origen === 'W' && (data.aplicacionOrigen === "r02t" || data.aplicacionOrigen === "R02T")){
					$('#indSolicitadoTramitagune').bootstrapSwitch('setState', true);
				}else{
					$('#indSolicitadoTramitagune').bootstrapSwitch('setState', false);
					$('#indSolicitadoTramitagune').attr('disabled','disabled');
				}
				
				comprobarFormularios = true;
				
				initForm = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
				datosFormulario = $("#datosgeneralesexpedienteform").rup_form("formSerialize");
				
				if(data.tecnico.estado === 'C'){
					esTecnico = "C";
				}else{
					esTecnico = "";
				}
				
				bitacoraUpdate(false);
				
				deshabilitarFormulario();
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
			valorInicialTipoExpediente = $('#idTipoExpediente').rup_combo("getRupValue");
			if (idTipoExpediente === datosExp.tipoExp.interpretacion) {
				deshabilitarComboTipoExp();
			} 
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
				$('#indPresupuesto').bootstrapSwitch('setState', true);
			}else{
				$('#indPresupuesto').bootstrapSwitch('setState', false);
			}
			
			$('#observacionesExpInter').val(getObservaciones(expedienteInterpretacion.observaciones));
			
			$("#observacionesExpInter").each(function() {
		        $(this).height($(this).prop('scrollHeight'));
		    });
			
			$("#observacionesExpInter").on("click", function() {
				auto_grow(this);
			});
			
			$("#observacionesExpInter").click();
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
			}
			$('#texto').val(expedienteTradRev.texto);
			$('#tipoRedaccion').val(expedienteTradRev.tipoRedaccion);
			$('#participantes').val(expedienteTradRev.participantes);
			$('#refTramitagune').val(expedienteTradRev.refTramitagune);
			
			if(expedienteTradRev.indPublicarBopv === 'S'){
				$('#indPublicarBopv').bootstrapSwitch('setState', true);
			}else{
				$('#indPublicarBopv').bootstrapSwitch('setState', false);
			}
			
			if(expedienteTradRev.indPrevistoBopv === 'S'){
				$('#indPrevistoBopv').bootstrapSwitch('setState', true);
			}else{
				$('#indPrevistoBopv').bootstrapSwitch('setState', false);
			}
			
			if(expedienteTradRev.indConfidencial === 'S'){
				$('#indConfidencial').bootstrapSwitch('setState', true, true);
				expedienteConfidencial = 'S';
				if ( origen === 'C' && !$('#indConfidencial')[0].disabled) {
						$('#indConfidencial').bootstrapSwitch('toggleDisabled',	true, true);
					
				}
			}else{
				expedienteConfidencial = 'N';
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
			
			$("#observacionesExpTradRev").each(function() {
		        $(this).height($(this).prop('scrollHeight'));
		    });
			
			$("#observacionesExpTradRev").on("click", function() {
				auto_grow(this);
			});
			
			if (origen==='A'){
				asignarObservaciones(expedienteTradRev.observaciones);
			}
			
			$("#observacionesExpTradRev").click();
		}
	}
	
	function setDatosExpedientesRelacionados(listExpRelacionados){
		if (listExpRelacionados.length > 0){
			mostrarDiv('divMostrarExpdientesRelacionados');
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
			ocultarDiv('divMostrarExpdientesRelacionados');
		}
	}
	
	function setExpedienteData(data){
		if (data != null){
			$('#fechaAlta').val(data.fechaAlta);
			$('#horaAlta').val(data.horaAlta);
			$('#anyoExpediente').val(data.anyo);
			$('#numeroExpediente').val(data.numExp);
			$('#tituloExpediente').val(data.titulo);
			
			if (origen==='C'){
				$('#fechaAlta').rup_date("disable");
				$('#horaAlta').attr('disabled','disabled');
			}
			
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
//		beforeSubmit: function(){
//			var vuelta = true;
//			//comprobar en el doc actual
//			if ( origen==='A' && !consultaExp ){
//				var tipoDocAux = listaTiposDoc.filter( function (x){ return x.id == $('#tipoDocumento056_detail_table').rup_combo('value') } );
//				if	( (!($.isEmptyObject(tipoDocAux))  ) && (tipoDocAux[0].indConfidencial === "S")){
//					
//					if ( $('#indEncriptado056_0').val() == 'N' || (!isEmpty($('#indEncriptado056_1').val()) && $('#indEncriptado056_1').val() == 'N' ) ){
//						$("#datosgeneralesexpedienteform_feedback").rup_feedback("set",$.rup.i18n.app.mensajes.tipoDocConfidencialFich, "error");
//						vuelta = false;
//					}else{
//						$('#indConfidencial').bootstrapSwitch('setState', true);
//					}
//				}
//			}
//			
//			if (vuelta){
//				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"));
//			}
//			return vuelta;
//			
//		},
		success: function(data){
			idExpediente = data.numExp;
			anyoExpediente = data.anyo;

			if (consultaExp) {
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
				
				//Actualizar bitácora + cabecera
				bitacoraUpdate(false);
				
				$('body').scrollTo('#tabsExpediente');
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
				bitacoraUpdate(false);
				mostrarDiv('cabecera_altaExpedientes');
				
				$('#divDocumentosExpediente').remove();
				
				desbloquearPantalla();
			}
			
//			if (data.expedienteTradRev != null) {
//				
//				if(data.expedienteTradRev.indConfidencial === 'S'){
//					
//					indConfidencial = true;
//				} else {
//					
//					indConfidencial = false;
//				}
//			}
			
			datosFormulario = $("#datosgeneralesexpedienteform").serialize();
			
			if (cambioIdTipoExpediente) {
				$("#tabsExpediente").rup_tabs("selectTab",{
					idTab: "tabsExpediente",
					position: 1
				});
			}
			
			cambioIdTipoExpediente = false;
			desbloquearPantalla();
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
			isGestorVip = 'S';
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
		if (!event.target.checked){
			$("#indPrevistoBopv").removeAttr('disabled');
			parentIndPrevistoBopv.removeClass("disabled");
		} else {
			$('#indPrevistoBopv').bootstrapSwitch('setState', false);
			$("#indPrevistoBopv").attr('disabled','disabled');
			parentIndPrevistoBopv.addClass("disabled");
		}
		
	});
	
	$('#indCorredaccion').on('change', function(event, state) {
		if (event.target.checked){
			mostrarDatosCorredaccion();
			$("#texto").rules("add", "required");
			$("#tipoRedaccion").rules("add", "required");
			$("#participantes").rules("add", "required");
			//si expediente de traduccion y indCorredaccion, no es necesario adjuntar documento - eliminamos validaciones 
			//de campos correspondientes, eliminamos mensajes de error de validacion y ocultamos la capa 
			if($('#idTipoExpediente').rup_combo("getRupValue")==='T'){
				$("#numPalIzo056_detail_table").rules("remove", "required");
				$("#oidFichero056_0").rules("remove", "required");
				$("#nombreFicheroInfo_0").rules("remove", "required");
				$("#tipoDocumento056_detail_table").rules("remove", "required");
				eliminarMensajesValidacionPorCapa("divDocumentosExpediente");
				$("#divDocumentosExpediente").hide();
			}
		} else {
			ocultarDatosCorredaccion();
			$("#texto").rules("remove", "required");
			$("#tipoRedaccion").rules("remove", "required");
			$("#participantes").rules("remove", "required");
			if (!consultaExp){
				//anyadimos validaciones a campos requeridos de documentacion y mostramos la capa
				if(!$("#numPalIzo056_detail_table")[0].required){
					$("#numPalIzo056_detail_table").rules("add", "required");
				}
				if(!$("#oidFichero056_0")[0].required){
					$("#oidFichero056_0").rules("add", "required");
				}
				if(!$("#nombreFicheroInfo_0")[0].required){
					$("#nombreFicheroInfo_0").rules("add", "required");
				}
				if(!$("#tipoDocumento056_detail_table")[0].required){
					$("#tipoDocumento056_detail_table").rules("add", "required");
				}
				$("#divDocumentosExpediente").show();
			}
		}
	});
	
	$("#datosgeneralesexpedienteform_button_save").on("click", function (event){
		$("#tipoEntidadSolicitanteDesc").removeAttr('disabled');
		$("#nombreEntidadSolicitante").removeAttr('disabled');
		$("#nombreGestor").removeAttr('disabled');
		if ($('#idTipoExpediente').rup_combo("getRupValue") === datosExp.tipoExp.interpretacion){
			$("#lugarInterpretacion").removeAttr('disabled');
		}
		$("#datosgeneralesexpedienteform").submit();
    });
	
	$("#datosgeneralesexpediente_cancel_cancelButton").on("click", function (){
		if(idExpediente === '' && anyoExpediente === ''){
			$("#datosgeneralesexpedienteform").rup_form("clearForm");
			//28082018-Incidencia ITZUL-1238
			clearValidation("#datosgeneralesexpedienteform");
			$("span[id^='enlaceDescargaDetalle']").html('');
			$("#datosgeneralesexpedienteform_feedback").rup_feedback("close");
			obtenerDatosExpedienteAlta();
		} else {
			
			if (!consultaExp || 
					(consultaExp && initForm !== $("#datosgeneralesexpedienteform").rup_form("formSerialize"))){
				comprobarCambiosFormulario();
			}
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
				hayCambiosExpTradRev = true;
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
	
	$('#indConfidencial').on('switch-change', function(event, state) {
		if (comprobarCambiosExpTradRev){
			if (state.value){
				hayCambiosExpTradRev = true;
			}
		}
	});
	
	$('#indCorredaccion').on('switch-change', function(event, state) {
		if (comprobarCambiosExpTradRev){
			if (state.value){
				hayCambiosExpTradRev = true;
			}
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
	
	$("[id^='observaciones']").on("click", function() {
		auto_grow(this);
	});
		
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
	
	
	$('#btnEliminarObserv').on("click", function (){
		fncEliminarObs();
	});
	
	
	$("#observaciones_mostrarLink_div").on("click", function (){
		obtenerObservacionesExpediente();
		mostrarDetalleObservaciones();
		$("#observacionesVisibles").val(true);
		visualizarObservaciones = true;
		ocultarLinkObservaciones();
				
		var focalizar = $("#observaciones_mostrarLink_div").offset().top;
		$('html,body').animate({scrollTop: focalizar}, 1000);
		
		datosFormulario = $("#datosgeneralesexpedienteform").serialize();
	});
	
	function gestionarObservaciones(){
		if (origen==='C'){
			if ($("#observacionesVisibles").val() === 'true'){
				obtenerObservacionesExpediente();
				mostrarDetalleObservaciones();
				ocultarLinkObservaciones();
			} else {
				ocultarDetalleObservaciones();
				if ($('#indObservacionesExpTradRev').val() === 'S'){
					mostrarLinkObservaciones();
					mostrarSeccionObservaciones();
				} else {
					ocultarLinkObservaciones();
					ocultarSeccionObservaciones();
				}
			}
		} else {
			mostrarDetalleObservaciones();
			ocultarLinkObservaciones();
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
	
	$(document).ajaxStop(function() {
		$("#observacionesExpTradRev").click();
		$("#observacionesExpInter").click();
	});
	
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
		if (origen==='C'){
			$('#nombreFicheroInfo_2').hide();
			$('#capaBtnPID').hide();
			$('#btnEliminarObserv').hide();
			$('#observacionesExpTradRev').attr('disabled', 'disabled');
			$('#observacionesExpTradRev').css('overflow', 'auto');
			$('#observacionesExpTradRev').css('cursor', 'default');
		
		}
		$("#observacionesExpTradRev").each(function() {
	        $(this).height($(this).prop('scrollHeight'));
	    });
	}
	
	function gestionarDatosGestor(gestorExpediente){
		if (consultaExp){
			$("#gestor_modificarLink").hide();
			mostrarDiv("divDatosContactoGestor");
			if (gestorExpediente.solicitante != null && gestorExpediente.solicitante.datosContacto != null){
				$("#telefonoFijoGestor").val(gestorExpediente.solicitante.datosContacto.telfijo031);
				$("#telefonoMovilGestor").val(gestorExpediente.solicitante.datosContacto.telmovil031);
				$("#telefonoEmailGestor").val(gestorExpediente.solicitante.datosContacto.email031);
			}
		}
	}
	
	desbloquearPantalla();
	
});