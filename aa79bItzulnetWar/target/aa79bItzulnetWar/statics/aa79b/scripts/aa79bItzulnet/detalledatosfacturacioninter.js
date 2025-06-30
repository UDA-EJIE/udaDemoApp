var expedienteCalculo;

var elFormulario;
var idEntidadAsocAnterior = "";
var datosFormulario = '';
var importeBase;
var importeIva;
var contacto;
var importeTotalFactura;
var importeBasefactura;
var contactoSinVincular = false;

var configCombo = {
	loadFromSelect: true	
	,ordered: false	
	,rowStriping: true
	,open: function(){
		var id = $(this).attr("id");
        $("#"+id+"-menu").width($("#"+id+"-button").width());
    }
};

/*
 * *************************
 * FUNCIONALIDADES PANTALLA - INICIO
 * *************************
 */

function volverARevisionDatosFacturacion(){
	$("#divDetalleRevDatosFactu").detach();
	$("#divBusquedaGeneral").append("<div id='divBusquedaGeneralCapa'></div>");
	$("#divBusquedaGeneralCapa").html(capaPestGeneral);
}

/*
 * *************************
 * FUNCIONALIDADES PANTALLA - FIN
 * *************************
 */

function calcImporteIvaEntidad(elImporteBaseEntidad,elPorIva ){
	var valor =  ((elImporteBaseEntidad * elPorIva) / 100).toFixed(2);
	if(isNaN(valor)){
		valor = 0;
	}else{
		valor = parseFloat(valor);
	}
	return valor;
}


function calcularImportesGeneral(){
	importeIva = 0.0;
	importeTotalFactura = 0.0;
	importeBaseFactura = 0.0;
	var importeBaseEntidad;
	var importeIvaEntidad;
	var porIva = $("#iva").getImporte();
	var porFactura;
	var iva;
	contacto = $("#contactofacturacionexp").rup_table("getRowData");	
	$("#contactofacturacionexp").rup_table('getSelectedRows');	
	for(var i=0;i<contacto.length;i++){
		porFactura = contacto[i].porFactura058;
		iva = contacto[i]["entidadSolicitante.iva"];
		importeBaseEntidad = parseFloat(importeFacturarPorcentaje(importeBase,porFactura));
		importeIvaEntidad = 0;
		if(iva === "S"){
			importeIvaEntidad = parseFloat(calcImporteIvaEntidad(importeBaseEntidad,porIva));
		}
		importeEntidad = importeIvaEntidad + importeBaseEntidad;
		
		$('#importeBaseEntidadAux').setImporte(importeBaseEntidad);
		$('#importeIvaEntidadAux').setImporte(importeIvaEntidad);
		$('#importeEntidadAux').setImporte(importeEntidad);

		$("#contactofacturacionexp").rup_table("setRowData", contacto[i].id058, {
			"datosFacturacionExpediente.importeBase":$('#importeBaseEntidadAux').val(),
			"datosFacturacionExpediente.importeIva":$('#importeIvaEntidadAux').val(),
			"datosFacturacionExpediente.importeTotal":$('#importeEntidadAux').val()
		});
		
		importeTotalFactura = importeTotalFactura + importeEntidad;
		importeBaseFactura = importeBaseFactura + importeBaseEntidad;
		importeIva = importeIva + importeIvaEntidad;
		
	}
			$("#importeBaseTotalCalculo").setImporte(importeBaseFactura);
			$("#importeBaseTotalCalculo").append("€");
			$("#importeIVAAplicadoTotalCalculo").setImporte(importeIva);
			$("#importeIVAAplicadoTotalCalculo").append("€");
			$("#totalCalculo").setImporte(importeTotalFactura);
			$("#totalCalculo").append("€");
			// Actualizamos valores de los campos ocultos
			$("#importeBaseVal").setImporte(importeBaseFactura);
			$("#importeIVAAplicadoVal").setImporte(importeIva);
			$("#importeTotalVal").setImporte(importeTotalFactura);
}

function guardarDatosEntidades(){
	var listaExpedientes = new Array();
	contacto = $("#contactofacturacionexp").rup_table("getRowData");	
	for(var i=0;i<contacto.length;i++){
		
		var Direccion = 
		{
    		"direccion": contacto[i]["entidadSolicitante.direccion.direccion"],
    		"dirNora": contacto[i]["entidadSolicitante.direccion.dirNora"]
    		
		};
		
		var Entidad = 
	    {
			"descAmpEu": contacto[i]["entidadSolicitante.descAmpEu"],
    		"direccion": Direccion,
    		"facturable": contacto[i]["entidadSolicitante.facturable"],
    		"facturableDesc": contacto[i]["entidadSolicitante.facturableDesc"],
    		"iva": contacto[i]["entidadSolicitante.iva"],
    		"ivaDesc": contacto[i]["entidadSolicitante.ivaDesc"],
	    };
		
		var DatosFacturacionExpediente = 
	    {
	        "importeBase": parseDecimal(contacto[i]["datosFacturacionExpediente.importeBase"]),
	        "importeIva": parseDecimal(contacto[i]["datosFacturacionExpediente.importeIva"]),
	        "importeTotal": parseDecimal(contacto[i]["datosFacturacionExpediente.importeTotal"])
	    };
		
		var IvaPrecios = 
		{
			"porIva": contacto[i]["ordenPrecios.ivaVigente.porIva"],
		};
		
		var OrdenPrecios = 
	    {
	        "ivaVigente": IvaPrecios,
	    };

		listaExpedientes.push( {
			"id058": contacto[i].id058,
    		"anyo": contacto[i].anyo,
    		"numExp": contacto[i].numExp,
    		"tipoEntidadAsoc058": contacto[i].tipoEntidadAsoc058,
    		"idEntidadAsoc058": contacto[i].idEntidadAsoc058,
    		"dniContacto058": contacto[i].dniContacto058,
    		"porFactura058": contacto[i].porFactura058,
    		"entidadSolicitante": Entidad,
    		"ordenPrecios": OrdenPrecios,
    		"datosFacturacionExpInter": DatosFacturacionExpediente
		});
	}
	
	jQuery.ajax({
	    	type: "POST",
	    	url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detallecontactofacturacion/finRevContactosEntidadesExpInter",
	    	dataType: "json",
	    	contentType: 'application/json',
	    	data: JSON.stringify({
	    		"listaExpedientes" : JSON.stringify(listaExpedientes)
	    	}),
	    	cache: false,
	    	success: function (data){
	    		desbloquearPantalla();
	    		volverARevisionDatosFacturacion();
				$("#busquedaGeneral").rup_table("filter");		
	    		$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.finalizadaLaRevision, "ok");
	    	},
	    	error: function (){
	    		$('#detalleRevDatosFactu_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
	    		desbloquearPantalla();
	    	}
    });
}

function eliminarContactoFacturacion(){
	var selectedRows = $("#contactofacturacionexp").rup_table('getSelectedRows');			
	var idDoc = selectedRows[0];
				
	jQuery.ajax({
    	type: "DELETE",
    	url: "/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/"+idDoc,
    	dataType: "json",
    	contentType: 'application/json',
    	cache: false,
    	success:function(){
    		$('#contactofacturacionexp').rup_table('filter');
    		$("#contactofacturacionexp_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_table.feedback.deletedOK"), "ok");
    		calcularImportesGeneral();
        }, 
   	 	error: function(){
   	 		$("#contactofacturacionexp_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
   	 	}
    });
}

function creaComboContacto(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
	if ( $('#dniContacto058_detail_table-button').length   ){
		$('#dniContacto058_detail_table').rup_combo("clear");
	}
	
	$('#dniContacto058_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/solicitante/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
		sourceParam : {
			value: "dni",
			label : "nombreCompleto" 
		},
	    rowStriping: true,
	    blank: "",
		open: function(){
			var id = $(this).attr("id");
	        $("#"+id+"-menu").width($("#"+id+"-button").width());
	    },
	    onLoadSuccess: function(){ 
	    	if (typeof(valorSeleccionar) !== "undefined"){
	    		$("#dniContacto058_detail_table").rup_combo("select", valorSeleccionar+'');
	    		elFormulario = $("#excepcionfacturacion_detail_form").rup_form("formSerialize");
	    	}
	    }
	});	
}

function validarExcepcionNueva(){
	$("div.error").remove();
	eliminarMensajesValidacion();
	
	var datos = jQuery("#contactofacturacionexp_detail_form").rup_form().formToJson();
	var error = [true];
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/validarContactoFacturacion'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(datos)			
	     ,async: false
	   	 ,success:function(codigoVuelta){
	   		switch (codigoVuelta){
	   		 case 1: $("#contactofacturacionexp_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.existeExcepcion, "error");	
	   		 		error =  [false];
	   		 		break;
	   		 case 2: $("#contactofacturacionexp_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSupera100, "error");	
	   		 		error =  [false];
	   		 		break;
	   		 }
	   	 }
   	 	,error: function(){
	   		$('#contactofacturacionexp_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		error =  [false];
	   	 }
	 });
	return error;
}

function serializeForm(){
	datosFormulario = $("#detalleRevDatosFactu_filter_form").serialize();
}

/*
 * *************************
 * CARGA INICIAL - INICIO
 * *************************
 */

function obtenerDetalleExpFact(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	$('#detalleRevDatosFactu_feedback').rup_feedback('close');
	
	$.ajax({

        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/comprobarDatosFactExpInter/' + anyoExpediente + '/' + idExpediente
        ,dataType: 'json' 
        ,async: false 
        ,success:function(data){
        	
        	if (data != null) {
        		if (data === 0) {
        			realizarCalculo();
            	} else {
            		// El expediente tiene datos de facturación asociados
            		obtenerDatosCalculoExpedienteFacturacion();
            	}
        	}
        	
        	desbloquearPantalla();
        	
        }
		,error: function(error){
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
	
}

function realizarCalculo(){
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/realizarCalculoInterpretacion/' + anyoExpediente +'/' + idExpediente
        ,dataType: 'json'
        ,contentType: 'application/json'
        ,cache: false 
        ,success:function(){
        	obtenerDatosCalculoExpedienteFacturacion();
        }
		,error: function(error){
			$('#detalleRevDatosFactu_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   	}
	});
}

function obtenerDatosCalculoExpedienteFacturacion(){
	$.rup_ajax({
		type: 'GET'
		,dataType: "json"
		,contentType: 'application/json'
	   	,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/obtenerDatosCalculoExpFactInter/' + anyoExpediente +'/' + idExpediente
	   	,success:function(data, textStatus, jqXHR){
	   		if(data){
	   			expedienteCalculo = data;
	   			volcarDatosCampos();
	   		}
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		$('#detalleRevDatosFactu_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   	}
	 });
}

function volcarDatosCampos(){
	//fecha solicitud
	$('#fechaHoraSolicitud').text(expedienteCalculo.fechaHoraAlta);
	$("#observacionesFacturacion").val(expedienteCalculo.obsvFacturacion);
	//expFacturable
	$('#indFacturable').bootstrapSwitch('setState', true);
	if(!$('#indFacturable')[0].disabled){
		$('#indFacturable').bootstrapSwitch('toggleDisabled',true,true);
	}
	//tituloOrdenPreciosPublicos
	$('#tituloOrdenPreciosPublicos').text(expedienteCalculo.titOrdenPreciosPublicos);
	//tipo peticionario
	$('#tipoPeticionario').text(expedienteCalculo.expedienteInterpretacion.tipoPeticionarioDescEu);
	//tipo acto
	$('#tipoActo').text(expedienteCalculo.expedienteInterpretacion.tipoActoDescEu);
	//programada
	deshabilitarCheckBox('interpProgramada');
	if(expedienteCalculo.expedienteInterpretacion.indProgramada === datos.activo.si){
		$('#interpProgramada').bootstrapSwitch('setState', true);
	}else{
		$('#interpProgramada').bootstrapSwitch('setState', false);
	}
	//presupuesto
	deshabilitarCheckBox('interpPresupuesto');
	if(expedienteCalculo.expedienteInterpretacion.indPresupuesto === datos.activo.si){
		$('#interpPresupuesto').bootstrapSwitch('setState', true);
	}else{
		$('#interpPresupuesto').bootstrapSwitch('setState', false);
	}
	//direccion
	$('#direccionInterp').text(expedienteCalculo.expedienteInterpretacion.dirNora.txtDirec);
	//en la cae
	deshabilitarCheckBox('enCae');
	$('#enCae').bootstrapSwitch('setState', expedienteCalculo.datosFacturacionInterpretacion.cae);
	//num interpretes
	$('#numInterpretes').val(expedienteCalculo.datosFacturacionInterpretacion.numInterpretes);
	//horas reales
	$('#horasReales').val(expedienteCalculo.datosFacturacionInterpretacion.horasInterpretacion);
	//iva
	$('#iva').text(expedienteCalculo.datosFacturacionInterpretacion.tipoIva);
	//precio minimo interprete
	$('#precioMinimoInterp').text(expedienteCalculo.datosFacturacionInterpretacion.precioMinimoInterpRealizEur);
	//tipo facturacion acto
	// mostrar los precios publicos acorde con el tipo de facturacion y si no lo tiene, por el tipo de acto v3.9.50
	var tipoFactInter = "";
	if(expedienteCalculo.datosFacturacionInterpretacion.tipoFacturacion != null
			&& expedienteCalculo.datosFacturacionInterpretacion.tipoFacturacion != ""){
			tipoFactInter = expedienteCalculo.datosFacturacionInterpretacion.tipoFacturacion;
		}else{
			tipoFactInter = expedienteCalculo.expedienteInterpretacion.indActoFacturable;
		}
	if(datos.tipoFacturacion.euroHoraInterprete === tipoFactInter){
		//facturacion por horas
		$('#precioInterpreteFieldset').hide();
		$('#precioHoraInterprete').text(expedienteCalculo.datosFacturacionInterpretacion.precioHoraInterpreteEur);
		// Asignación de datos a campos ocultos
		$('#precioHoraInterpreteInter').val(expedienteCalculo.datosFacturacionInterpretacion.precioHoraInterprete);
	}else{
		//facturacion por interprete
		$('#precioHoraFieldset').hide();
		$('#precioJornadaCompleta').text(expedienteCalculo.datosFacturacionInterpretacion.precioJornadaCompletaEur);
		$('#precioMediaJornada').text(expedienteCalculo.datosFacturacionInterpretacion.precioMediaJornadaEur);
		$('#precioHoraFraccionAdic').text(expedienteCalculo.datosFacturacionInterpretacion.precioHoraFraccAdicEur);
		// Asignación de datos a campos ocultos
		$('#precioJornadaCompletaInter').val(expedienteCalculo.datosFacturacionInterpretacion.precioJornadaCompleta);
		$('#precioMediaJornadaInter').val(expedienteCalculo.datosFacturacionInterpretacion.precioMediaJornada);
		$('#precioHoraAdicInter').val(expedienteCalculo.datosFacturacionInterpretacion.precioHoraFraccAdic);
	}
	//importe base
	$('#importeBaseTotalCalculo').text(expedienteCalculo.datosFacturacionInterpretacion.importeBaseEur);
	//importeIVAAplicadoTotalCalculo
	$('#importeIVAAplicadoTotalCalculo').text(expedienteCalculo.datosFacturacionInterpretacion.importeIvaEur);
	//totalCalculo
	$('#totalCalculo').text(expedienteCalculo.datosFacturacionInterpretacion.importeTotalEur);
	// Asignación de datos a campos ocultos
	$('#tipoFacturacionInter').val(tipoFactInter);
	$('#tipoIvaInter').val(expedienteCalculo.datosFacturacionInterpretacion.tipoIva);
	$('#precioMinInter').val(expedienteCalculo.datosFacturacionInterpretacion.precioMinimoInterpRealiz);
	$('#jornadaCompletaHorasDesde').val(expedienteCalculo.datosFacturacionInterpretacion.jornadaCompletaHorasDesde);
	$('#jornadaCompletaHorasHasta').val(expedienteCalculo.datosFacturacionInterpretacion.jornadaCompletaHorasHasta);
	$('#mediaJornadaHorasDesde').val(expedienteCalculo.datosFacturacionInterpretacion.mediaJornadaHorasDesde);
	$('#mediaJornadaHorasHasta').val(expedienteCalculo.datosFacturacionInterpretacion.mediaJornadaHorasHasta);

	$("#importeBaseVal").val(expedienteCalculo.datosFacturacionInterpretacion.importeBase);
	$("#importeIVAAplicadoVal").val(expedienteCalculo.datosFacturacionInterpretacion.importeIva);
	importeBase = $("#importeBaseVal").getImporte();
	importeIva = $("#importeIVAAplicadoVal").getImporte();
	var precioMinimo = $('#precioMinInter').getImporte();
	$("#importeTotalVal").val(expedienteCalculo.datosFacturacionInterpretacion.importeTotal);
	var importeTotal = $("#importeTotalVal").getImporte();
	var numInterpretes = expedienteCalculo.datosFacturacionInterpretacion.numInterpretes;
	var porcentajeIva = expedienteCalculo.datosFacturacionInterpretacion.tipoIva;
	
	if (importeTotal < precioMinimo){
		importeTotal = precioMinimo;
		importeBase = importeTotal * 100 / (100 + porcentajeIva);
		importeIva = importeTotal - importeBase;
	}

	// Actualizamos valores de los campos ocultos
	$("#importeBaseVal").setImporte(importeBase);
	$("#importeIVAAplicadoVal").setImporte(importeIva);
	$("#importeTotalVal").setImporte(importeTotal);
	
}

/*
 * *************************
 * CARGA INICIAL - FIN
 * *************************
 */

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function validarFormatoHoras(input){
	if(/[^0-9:,]/.test(input.value))input.value = input.value.replace(/[^0-9:,]/g,'');
}

function calculoImportesInterpretacion(numInterpretes, horas){
	importeBase = 0.0;
	importeIva = 0.0;
	var importeTotal = 0.0;
	var porcentajeIva = $("#tipoIvaInter").getImporte();
	var tipoFacturacion = $('#tipoFacturacionInter').val();
	var precioMinimo = $('#precioMinInter').getImporte();
	var jornadaCompletaHorasDesde = $('#jornadaCompletaHorasDesde').getImporte();
	var jornadaCompletaHorasHasta = $('#jornadaCompletaHorasHasta').getImporte();
	var mediaJornadaHorasDesde = $('#mediaJornadaHorasDesde').getImporte();
	var mediaJornadaHorasHasta = $('#mediaJornadaHorasHasta').getImporte();
	var hora = horas.split(',');
	var numHoras = 0;
	var numMinutos = 0;
	
	for (var i = 0; i < hora.length; i++) {
		numHoras = numHoras + parseInt(hora[i].split(':')[0]);
		numMinutos = numMinutos + parseInt(hora[i].split(':')[1]);
	}
	
	if (numMinutos != 0){
		numHoras = numHoras + Math.floor(numMinutos/60);
		numMinutos = numMinutos % 60;
		if (numMinutos > 0){
			numHoras = numHoras + 1;
		}
	}
	
	if(datos.tipoFacturacion.euroInterprete === tipoFacturacion){
		
		var precioJornadaCompleta = $('#precioJornadaCompletaInter').getImporte();
		var precioMediaJornada = $('#precioMediaJornadaInter').getImporte();
		var precioHoraAdic = $('#precioHoraAdicInter').getImporte();
		
		// Miramos cuantas jornadas completas enteras hay según el rango de jornadas completas definido.
		var nJornadasCompletas = Math.floor(numHoras/jornadaCompletaHorasHasta);
		
		if (nJornadasCompletas === 0){
			// Comprobamos si son horas dentro del rango de jornada completa
			if (numHoras >= jornadaCompletaHorasDesde && numHoras <= jornadaCompletaHorasHasta){
				nJornadasCompletas = 1;
				numHoras = 0; // No quedan horas pendientes de tarificar
			}
		} else {
			numHoras = numHoras - (nJornadasCompletas * jornadaCompletaHorasHasta);
		}
		
		// Miramos cuantas medias jornadas hay
		var nMediasJornadas = Math.floor(numHoras/mediaJornadaHorasHasta);
		
		if (nMediasJornadas === 0){
			// Comprobamos si son horas dentro del rango de jornada media
			if (numHoras >= mediaJornadaHorasDesde && numHoras <= mediaJornadaHorasHasta){
				nMediasJornadas = 1;
				numHoras = 0; // No quedan horas pendientes de tarificar
			}
		} else {
			numHoras = numHoras - (nMediasJornadas * mediaJornadaHorasHasta);
		}
		
		importeTotal = importeTotal + ( (nJornadasCompletas * precioJornadaCompleta) + (nMediasJornadas * precioMediaJornada) + (numHoras * precioHoraAdic) );
		
	} else {
		var precioHoraInterprete = $('#precioHoraInterpreteInter').getImporte();
		
		importeTotal = importeTotal + (numHoras * precioHoraInterprete);
	}
	if (importeTotal < precioMinimo){
		importeTotal = precioMinimo;
	}
	
	importeBase = (importeTotal * 100 / (100 + porcentajeIva)).toFixed(3);
	
	calcularImportesGeneral();

	
}

function comprobarNumInterpretesHoras(numInterpretes, horas){
	var camposValidos = true;
	
	if (numInterpretes > 0 && horas === ""){
		camposValidos = false;
	} else if (horas != ""){
		var hora = horas.split(',');
		
		if (numInterpretes == 0 && hora.length === 1){
			if ((hora[0].split(':')[0] == "0" || hora[0].split(':')[0] == "00")
					&& hora[0].split(':')[1] == "00"){
				camposValidos = true;
			} else {
				camposValidos = false;
			}
		} else if (numInterpretes != hora.length){
			camposValidos = false;
		}
	}
	
	return camposValidos;
}

function comprobarHorasInterpretes(horas){
	var camposValidos = true;
	
	if (horas != ""){
		var hora = horas.split(',');
		
		for (var i = 0; i < hora.length; i++) {
			if (validarHora(hora[i]) === ''){
				camposValidos = false;
			}
		}
	}
	
	return camposValidos;
}

function comprobarHorasInterpretesMayoresQueCero(horas){
	var camposValidos = true;
	
	if (horas != ""){
		var hora = horas.split(',');
		
		for (var i = 0; i < hora.length; i++) {
			if (!validarHoraYMayorQueCero(hora[i])){
				camposValidos = false;
			}
		}
	}
	
	return camposValidos;
}



function vaciarCamposImportes(){
	$("#importeBaseTotalCalculo").text('');
	$("#importeIVAAplicadoTotalCalculo").text('');
	$("#totalCalculo").text('');
	
	var contactoFact = $("#contactofacturacionexp").rup_table("getRowData");	
	$("#contactofacturacionexp").rup_table('getSelectedRows');	
	for(var i=0;i<contactoFact.length;i++){
		$("#contactofacturacionexp").rup_table("setRowData", contactoFact[i].id058, {
			"datosFacturacionExpediente.importeBase": "",
			"datosFacturacionExpediente.importeIva": "",
			"datosFacturacionExpediente.importeTotal": ""
		});
	}
}

function mostrarErrorHoraIncorrecta(){
	$("#horasReales").addClass('error');
	$("#horasRealesDiv").append('<label class="error" id="horasReales-error" for="horasReales">'+ $.rup.i18nParse($.rup.i18n.app,"validaciones.horaIncorrecta") +'</label>');
}

function mostrarErrorNumInterpretes(){
	$("#numInterpretes").addClass('error');
	$("#numInterpretesDiv").append('<label class="error" id="numInterpretes-error" for="numInterpretes">'+ $.rup.i18nParse($.rup.i18n.app,"validaciones.numInterpretesHoras") +'</label>');
}

function eliminarMensajesError(){
	$("#numInterpretes").removeClass('error');
	$("[id='numInterpretes-error']").remove();
	$("#horasReales").removeClass('error');
	$("[id='horasReales-error']").remove();
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	
	$('#detalleRevDatosFactu_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	
	$('#contactofacturacionexp_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	
	obtenerDetalleExpFact();
	
	$("#anyo_filtro_fact").val(anyoExpediente);
	$("#numExp_filtro_fact").val(idExpediente);
	$("#anyo").val(anyoExpediente);
	$("#numExp").val(idExpediente);
		
	$("#detalleRevDatosFactu_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			css : "fa fa-arrow-left",
			id : "volver",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				if (datosFormulario != $("#detalleRevDatosFactu_filter_form").serialize()){
					$.rup_messages("msgConfirm", {
         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
         				OKFunction: function(){
         					volverARevisionDatosFacturacion();
         				}
         			});
				} else {
					volverARevisionDatosFacturacion();
				}
			}
		},{
			i18nCaption : $.rup.i18n.app.boton.finRevDatosFact,
			css: "fa fa-floppy-o", 
			id : "finRevDatosFact",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				if($("#detalleRevDatosFactu_filter_form").valid()){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					$('#detalleRevDatosFactu_filter_form').submit();
				}
			}
		} ]
	});
	
	$('#detalleRevDatosFactu_filter_form').rup_form({
		url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detalledatosfacturacioninter/finRevDatosFact'
		, type: "POST"
		, dataType: "json"
	    , contentType: "application/json"
		, cache: false
		, success: function(data){
			guardarDatosEntidades();
		}
	});
	
	$("#detalleRevDatosFactu_filter_form").rup_validate({
		feedback: $('#detalleRevDatosFactu_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"expediente.expedienteInterpretacion.datosFacturacion.numInterpretes" : {
				required : true,
				maxlength : 2,
				integer : true,
				validarNumInterpretesHoras: true,
				min: 1
			},
			"expediente.expedienteInterpretacion.datosFacturacion.horasInterpretacion" : {
				required : true,
				maxlength : 150,
				validarHorasInterpretes: true
			}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
		
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
			.bootstrapSwitch()
			.bootstrapSwitch('setSizeClass', 'switch-small')
			.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
			.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	$("#contactofacturacionexp").rup_table({
		url: "/aa79bItzulnetWar/contactofacturacionexpediente/entidadesContactosAFacturarInter",
		toolbar: {
			 id: "contactofacturacionexp_toolbar"			 
			 
			 ,buttons:[
				{obj: {
					i18nCaption: $.rup.i18n.base.rup_table.del.caption
	 				,css: "fa fa-trash-o"
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
	   		 			if(!$('#contactofacturacionexp').rup_table("isSelected")){
							e.preventDefault();
							$.rup_messages("msgAlert", {
								message: $.rup.i18n.app.comun.warningSeleccion
							});	
							return false;
						 }else{
							 $.rup_messages("msgConfirm", {
	             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
	             				OKFunction: function(){
	             					eliminarContactoFacturacion();
	             				}
	             			});
						 }
		 				}	
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.actualizarDatosEntidadContacto
					,css: "fa fa-refresh"
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
		 					$('#contactofacturacionexp').rup_table('filter');
		 				}	
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.configEntidades
					,css:"fa fa-building-o"
					,right:true
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
		 					window.open(
		 					urlConfigurarEntidad,
	 						  '_blank' 
	 						);
		 				}	
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.configPersonas
					,css:"fa fa-users"
					,right:true
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
		 					window.open(
		 							urlConfigurarPersonal,
	 						  '_blank' 
	 						);
		 				}	
				}
			]
			,defaultButtons:{
				add : true
				,edit : true
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : false
			 }
			
		},			
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			txtEntidadContactoFact,			
			"",
			txtDirPostal,	
			"",
			"",
			txtFacturable,
			txtPorFacturable,
			"",
			txtIVA,
			txtImporteBase,			
			txtImporteIva,		
			txtTotal			
			
		],		
		colModel: [
			{ 	name: "id058", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "anyo", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "numExp", 
			 	label: "label.dni",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tipoEntidadAsoc058", 
			 	label: "tipoEntidadAsoc058",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "idEntidadAsoc058", 
			 	label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "dniContacto058", 
			 	label: "label.contacto",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.descAmpEu", 
				label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "entidadAsociadaYContacto", 
			 	label: "label.entidadAsociada",
			 	index: "ENTIDADDESCAMP",
				align: "", 
				width: "250", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				},
				formatter: function (cellvalue, options, rowObject) {
					if(!rowObject.contactoVinculado && rowObject.dniContacto058){
						contactoSinVincular = true;
					}
					return cellvalue;
				}
			},
			{ 	name: "entidadSolicitante.direccion.dirNora", 
				label: "label.direccionPostal",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.direccion.direccion", 
				label: "label.direccionPostal",
				index: "DIRECCION",
				align: "", 
				width: "300",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "entidadSolicitante.facturable", 
			 	label: "label.entidadFacturable",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "ordenPrecios.ivaVigente.porIva", 
			 	label: "label.aplicaIva",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.facturableDesc", 
				label: "label.facturable",
				index: "FACTURABLEDESC",
				align: "", 
				width: "100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "porFactura058", 
				label: "label.porFact",
				index: "PORFACTURA058",
				width: "50",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.iva", 
				label: "label.iva",
				align: "center", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.ivaDesc", 
			 	label: "label.iva",
			 	index: "IVADESC",
				align: "center", 
				width: "50",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "datosFacturacionExpediente.importeBase", 
				label: "label.importeBase",
				index: "IMPORTEBASE",
				align: "center", 
				width: "200",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "datosFacturacionExpediente.importeIva", 
				label: "label.importeIVA",
				index: "IMPORTEIVA",
				align: "center", 
				width: "150",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "datosFacturacionExpediente.importeTotal", 
				label: "label.totalMayus",
				index: "IMPORTETOTAL",
				align: "center", 
				width: "150",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
	    ],
	    title: false,
	    shrinkToFit:false,
	    loadComplete: function(){ 
	        calcularImportesGeneral();
	        if (contactoSinVincular){
	    		$("#labelContactoNoVinculado").show();
	    		contactoSinVincular = false;
	    	}else{
	    		$("#labelContactoNoVinculado").hide();
	    	}
	        $('td[grid_tooltip]').each(function(){
	        $(this).attr('title', $(this).attr('grid_tooltip'));
	        $(this).tooltip();
	        }); 
	    },
	    model:"ContactoFacturacionExpediente",
	    usePlugins:[
			"formEdit",
	    	"feedback",
			"toolbar",
	    	"responsive",
	    	"fluid",
	    	"filter"
	     	],
		primaryKey: ["id058"],
		sortname: "id058",
		sortorder: "asc",
		loadOnStartUp: true,
	    formEdit:{
	    	detailForm: "#contactofacturacionexp_detail_div",
			fillDataMethod: "clientSide",
	     	validate:{ 
				rules:{
					"id058":{
						required: false
						},
					"tipoEntidadAsoc058":{
						required: true
						},
					"idEntidadAsoc058":{
						required: true
						},
					"idEntidadAsoc058_autocomplete":{
						required: true
						},
					"dniContacto058":{
						required: false
						},
					"porFactura058":{ required: true, maxlength: 3, integer: true, range: [0, 100] }
					},
					showErrorsInFeedback: true,
		     		showFieldErrorsInFeedback: false
			},
			addEditOptions:{
				 width:800,
				 fillDataMethod: "clientSide",
				 reloadAfterSubmit: true,
				 beforeSubmit: function(){	
					 return validarExcepcionNueva();
				 }
			 },
			 editOptions: {				
				beforeShowForm: function(){	
					$('#anyo058_detail_table').val(anyoExpediente);
					$('#numExp058_detail_table').val(idExpediente);
					jQuery('input[name=tipoEntidadFact]:first').click();
					var id = $('#contactofacturacionexp').rup_table("getSelectedRows");
					if(id !== ''){
						//OJO, este campo que crea UDA es el que marca el ID que viaja al controller
						$('#id_g').val(id); 
						$('#id058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "id058"));
						$('#tipoEntidadAsoc058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "tipoEntidadAsoc058"));
						$('#idEntidadAsoc058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "idEntidadAsoc058"));    					
						$('#porFactura058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "porFactura058"));
	 					$('#idEntidadAsociada_label').val($("#contactofacturacionexp").rup_table("getCol", id, "entidadSolicitante.descAmpEu"));
						$("#idEntidadAsociada").rup_autocomplete("set", $('#tipoEntidadAsoc058_detail_table').val()+"_"+$('#idEntidadAsoc058_detail_table').val(), $("#contactofacturacionexp").rup_table("getCol", id, "entidadSolicitante.descAmpEu"));
						idEntidadAsocAnterior = $('#tipoEntidadAsoc058_detail_table').val()+"_"+$('#idEntidadAsoc058_detail_table').val();
						
						creaComboContacto($('#tipoEntidadAsoc058_detail_table').val(),$('#idEntidadAsoc058_detail_table').val(), $("#contactofacturacionexp").rup_table("getCol", id, "dniContacto058"));
					}
					elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
					$("#contactofacturacionexp").rup_table("updateSavedData", function(savedData){
	  					savedData["anyo"]=$('#anyo058_detail_table').val();
	  					savedData["numExp"]=$('#numExp058_detail_table').val();
	  					savedData["tipoEntidadFact"]=$('input:radio[name="tipoEntidadFact"]:checked').val();		      					
	  					savedData["tipoEntidadAsoc058"]=$('#tipoEntidadAsoc058_detail_table').val();
	  					savedData["idEntidadAsoc058"]=$('#idEntidadAsoc058_detail_table').val();   
	  					savedData["idEntidadAsoc058_autocomplete_label"]=$('#idEntidadAsociada_label').val();    
	  					savedData["idEntidadAsoc058_autocomplete"]=$('#idEntidadAsociada').val();   
	  					
	  					savedData["id058"]=$('#id058_detail_table').val();   
	  					savedData["porFactura058"]=$('#porFactura058_detail_table').val();   
	  				});
	  				return true;
	  			}				 
			 },
			 addOptions: {								 		
				 beforeShowForm: function(){
					$('#anyo058_detail_table').val(anyoExpediente);
					$('#numExp058_detail_table').val(idExpediente);
					
					jQuery('input[name=tipoEntidadFact]:first').click();
					$("#dniContacto058_detail_table").rup_combo("disableChild");
					idEntidadAsocAnterior = "";
					$('#porFactura058_detail_table').val('100');
					elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
					$("#contactofacturacionexp").rup_table("updateSavedData", function(savedData){
	  					savedData["anyo"]=$('#anyo058_detail_table').val();
	  					savedData["numExp"]=$('#numExp058_detail_table').val();
	  					savedData["dniContacto058"]=$('#dniContacto058_detail_table').val();
	  					savedData["dniContacto058_label"]='';
	  					savedData["tipoEntidadFact"]=$('input:radio[name="tipoEntidadFact"]:checked').val();		      					
	  					savedData["tipoEntidadAsoc058"]='';
	  					savedData["idEntidadAsoc058"]='';
	  					savedData["idEntidadAsoc058_autocomplete_label"]='';   
	  					savedData["idEntidadAsoc058_autocomplete"]='';
	  					savedData["porFactura058"]=$('#porFactura058_detail_table').val();
	  				});					
	   				return true;
	   			}
			 }
	    }
	});
		
	creaComboContacto('A','0');
		
	//Autocomplete Entidad asociada
	$('#idEntidadAsociada').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestEntidadesConContactosSolicitantes",
		sourceParam : {
			value: "codigoCompleto",
			label : "descAmp" + $.rup_utils.capitalizedLang()
		},
		menuMaxHeight: "300px",
		getText: false,
		open : function() {
			 var tam = parseFloat(jQuery('#idEntidadAsociada').css("padding-left"))+ parseFloat(jQuery('#idEntidadAsociada').css("padding-right"));
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadAsociada').innerWidth());
			$("#idEntidadAsociada_menu").css("z-index", $("#contactofacturacionexp_detail_div").parent().css("z-index")+1);
			$("#idEntidadAsociada_menu").removeClass("ui-front");
		}
	});	
	
	$("#idEntidadAsociada_label").on("rupAutocomplete_change", function(event, data){
		if (idEntidadAsocAnterior !== $('#idEntidadAsociada').rup_autocomplete('getRupValue')){
			var datosEntAsociada = $('#idEntidadAsociada').rup_autocomplete('getRupValue').split("_");	
			$('#idEntidadAsoc058_detail_table').val(datosEntAsociada[1]);
			$('#tipoEntidadAsoc058_detail_table').val(datosEntAsociada[0]);		
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
			}else{
				creaComboContacto(datosEntAsociada[0],datosEntAsociada[1]);
				idEntidadAsocAnterior = $('#idEntidadAsociada').rup_autocomplete('getRupValue');
			}
		}else{
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
				idEntidadAsocAnterior = "";
			}
		}
	});
	
	jQuery('input[name=tipoEntidadFact]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadAsociada").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadAsociada").rup_autocomplete("set", "", "");
		$('#idEntidadAsociada_label').removeData("tmp.loadObjects.term");
		$('#idEntidadAsoc058_detail_table').val('');
		$('#tipoEntidadAsoc058_detail_table').val('');	
		$("#dniContacto058_detail_table").rup_combo("disableChild");
		idEntidadAsocAnterior = "";
	});
	jQuery('input[name=tipoEntidadFact]:first').click();
	
	jQuery("#contactofacturacionexp_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#contactofacturacionexp_detail_form");
	});
	
	jQuery('#contactofacturacionexp_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	
	$("#numInterpretes").on("blur", function() {
		eliminarMensajesError();
		
		if (!comprobarNumInterpretesHoras($(this).val(), $("#horasReales").val())){
			mostrarErrorNumInterpretes();
			vaciarCamposImportes();
		} else if (!comprobarHorasInterpretes($("#horasReales").val())) {
			mostrarErrorHoraIncorrecta();
			vaciarCamposImportes();
		} else {
			calculoImportesInterpretacion($(this).val(), $("#horasReales").val());
		}
	});
	
	$("#horasReales").on("blur", function() {
		eliminarMensajesError();
		
		if (!comprobarHorasInterpretes($(this).val())){
			mostrarErrorHoraIncorrecta();
			vaciarCamposImportes();
		} else if (!comprobarNumInterpretesHoras($("#numInterpretes").val(), $(this).val())) {
			mostrarErrorNumInterpretes();
			vaciarCamposImportes();
		} else {
			calculoImportesInterpretacion($("#numInterpretes").val(), $(this).val());
		}
	});
	
	jQuery.validator.addMethod("validarNumInterpretesHoras", function(value, element, params) {
		var numInterpretes = parseInt(value);
		var horas = $('#horasReales').val();
		
		return comprobarNumInterpretesHoras(numInterpretes, horas);

	},$.rup.i18n.app.validaciones.numInterpretesHoras);
	
	jQuery.validator.addMethod("validarHorasInterpretes", function(value, element, params) {
		
		return comprobarHorasInterpretesMayoresQueCero(value);

	},$.rup.i18n.app.validaciones.horaIncorrecta);
	
	llamadasFinalizadas("serializeForm");
	
});