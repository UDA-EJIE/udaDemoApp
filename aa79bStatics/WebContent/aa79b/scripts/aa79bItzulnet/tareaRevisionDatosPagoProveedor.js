var esHoraOblig = false;
var numTotalPalIZOSuma = 0;
var numTotalPalIZOTarea = 0;
var numTotalPalSolicSuma = 0;
var palTrados;
var numTotalPalTrados = 0;
var numPalConcor084090 = 0;
var numPalConcor8594090 = 0;
var numPalConcor95100090 = 0;
var numTotalTradosPalDocu = 0;
var numPalTradosConcor084090 = 0;
var numPalTradosConcor8594090 = 0;
var numPalTradosConcor95100090 = 0;
var indFacturacion;
var indAlbaran;
var IT;
var IF;
var IA;
var IR;
var ITP;
var IBP;
var IVA;
var A1;
var B1;
var B2;
var B3;
var C1;
var D1;
var D2;
var F1;
var F2;
var G1;
var H1;
var IC;
var PC1;
var I1;
var importePrevistoLote;

/*
 * UTILIDADES - INICIO
 */
/**
 * Comprueba si hay cambios en la modal. Si no los hay, se cierra la modal. 
 */

function formConsulta() {
	if (ejecutarTareaConsulta) {
		$.ajax({
			url : '/aa79bItzulnetWar/ejecuciontarea/tareas/findTarea/' + idTarea,
			success : function(data, textStatus, jqXHR) {
				var newData = JSON.parse(data);
				$("#horasTarea_form").val(newData.ejecucionTareas.horasTarea);
				$("#idTarea_form").val(newData.tipoTarea.id015);
				findData();
				elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert('Error recuperando datos del paso');
			}
		});
	} else {
		findData();
	}
}

function cargarSwitchRecargos(data) {
	
	if(data.indRecargoFormato && data.indRecargoFormato.trim() === "S") {
		$('#recargaPorFormatoSwitch').bootstrapSwitch('setState', true);
		$("#sobreNumPal").attr("disabled", false);
	} else {
		$('#recargaPorFormatoSwitch').bootstrapSwitch('setState', false);
		$("#sobreNumPal").attr("disabled", true);
	}
	
	if(data.indPenalizacion && data.indPenalizacion.trim() === "S"){
		$('#penalizacionPorRetrasoSwitch').bootstrapSwitch('setState', true);
		$("#porcentajePenalizacion").attr("disabled", false);
	} else {
		$('#penalizacionPorRetrasoSwitch').bootstrapSwitch('setState', false);
		$("#porcentajePenalizacion").attr("disabled", true);
	}
	
	if(data.indRecargoApremio && data.indRecargoApremio.trim() === "S"){
		$('#recargoPorSwitch').bootstrapSwitch('setState', true);
		$("#porcentajeRecarga").attr("disabled", false);
	} else {
		$('#recargoPorSwitch').bootstrapSwitch('setState', false);
		$("#porcentajeRecarga").attr("disabled", true);
	}
	
	if(data.numPalRecargoFormato){
		$("#sobreNumPal").val(data.numPalRecargoFormato);	
	}
	
	if(data.porPenalizacion){
		$("#porcentajePenalizacion").val(data.porPenalizacion);
	}			
	
	if(data.porRecargoApremio){
		$("#porcentajeRecarga").val(data.porRecargoApremio);
	}
}

function calcularImporteDisponible(){
	var importeTotalDisponible = 0;
	importeTotalDisponible += $("#importeAsig").getImporte();
	importeTotalDisponible -= $("#importeTotalConsum").getImporte();
	importeTotalDisponible -= $("#importeTotalPre").getImporte();
	$("#importeTotalDisponible").setImporte(importeTotalDisponible);
	$("#importeTotalDisponible").append(" €");
//	$("#importeTotalDisponible").text(addDecimalsFormat((parseDecimal($("#importeAsig").text()) - (parseDecimal($("#importeTotalConsum").text()) + parseDecimal($("#importeTotalPre").text()))).toFixed(2),2,true));
}

function comprobarCambiosFormulario(){
	// comprobar cambios formulario - validar
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

function porRecargaChange(){
	valor = parseInt($("#porcentajeRecarga").val());
	if(valor!= '' && valor < 0 || valor > 100){
		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajeMenorCien, "error");
		$("#porcentajeRecarga").addClass("error");
	} else {
		$("#porcentajeRecarga").removeClass("error");
		actualizarImportes();	
	}
}

function sobreNumPalChange(){
	actualizarImportes();
}

function porPenalChange(){
	valor = parseInt($("#porcentajePenalizacion").val());
	if(valor!= '' && valor < 0 || valor > 100){
		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajeMenorCien, "error");
		$("#porcentajePenalizacion").addClass("error");
	} else {
		$("#porcentajePenalizacion").removeClass("error");
		actualizarImportes();	
	}
}

function actualizarImportes(){
	importeRealizacionTarea();
	importeRecargoFormato();
	importeRecargoApremio();
	importePenalizacionRetraso();
	importePenalizacionCalidad();
	importeTotalPagar();	
	$("#importeTotalPre").setImporte(importePrevistoLote + $("#total").getImporte());
	$("#importeTotalPre").append(" €");
	calcularImporteDisponible();
	importeBasePagar();
	importeCorrespondienteIVA();
}

function importeRealizacionTarea(){
	A1 = parseFloat($("#numtotalPal").val());
	B1 = parseFloat($("#palHoraConcor084").val());
	B2 = parseFloat($("#palHoraConcor8594").val());
	B3 = parseFloat($("#palHoraConcor95100").val());
	C1 = $("#valImportePalabraIVA").getImporte();
	E1 = parseFloat($("#tareaRevision").text());
	D1 = parseFloat($("#porcentajePago8594").text());
	D2 = parseFloat($("#porcentajePago95100").text());
	if(isNaN(A1)){
		A1 = 0;
	}
	if(isNaN(B1)){
		B1 = 0;
	}
	if(isNaN(B2)){
		B2 = 0;
	}
	if(isNaN(B3)){
		B3 = 0;
	}
	if(isNaN(C1)){
		C1 = 0;
	}
	if(isNaN(E1)){
		E1 = 0;
	}
	if(isNaN(D1)){
		D1 = 0;
	}
	if(isNaN(D2)){
		D2 = 0;
	}
	
	if($("#tipoTareaId").val() === "T"){
		if(A1 != 0 && B1 === 0 && B2 === 0 && B3 === 0){
			IT = A1 * C1;
		} else {
			IT = (B1 * C1) + (B2 * C1 * (D1/100)) + (B3 * C1 * (D2/100));
		}
	} else {
		IT = A1 * C1 * (E1/100);
	}
	
	if(isNaN(IT)){
		IT = 0;
	} else {
		IT = parseFloat(IT.toFixed(2));
	}
	
	$("#importeRealizarTareaVal").setImporte(IT);
	$("#importeRealizarTarea").setImporte(IT);
}

function importeRecargoFormato(){
	F1 = parseFloat($("#sobreNumPal").val());
	if(isNaN(F1)){
		F1 = 0;
	}
	
	C1 = $("#valImportePalabraIVA").getImporte();
	F2 = parseFloat($("#formato").text());
	
	IF = F1 * C1 * (F2/100);
	$("#importeRecargoPorFormatoVal").setImporte(IF);
	$("#importeRecargoPorFormato").setImporte(IF);
}

function importeRecargoApremio(){
	G1 = parseFloat($("#porcentajeRecarga").val());
	
	if(isNaN(G1)){
		G1 = 0;
	}
	
	IA = IT * (G1/100);
	$("#importeRecargoPorApremioVal").setImporte(IA);
	$("#importeRecargoPorApremio").setImporte(IA);
}

function importePenalizacionRetraso(){
	H1 = parseFloat($("#porcentajePenalizacion").val());
	
	if(isNaN(H1)){
		H1 = 0;
	}
	
	IR = (IT) * (H1/100);
	$("#importePenalPorRetrasoVal").setImporte(IR);
	$("#importePenalPorRetrasoLabel").setImporte(IR);
	
	if(IR != 0){
		$("#importePenalPorRetrasoLabel").setImporte(-IR);
	}
	
}

function importePenalizacionCalidad(){
	PC1 = parseFloat($("#porPenalizacionCalidad").val());
	
	if(isNaN(PC1)){
		PC1 = 0;
	}
	
	IC = (IT) * (PC1/100);
	
	var numberEpsilon = Number.EPSILON;
	if(isNaN(Number.EPSILON)){
		numberEpsilon = 2.220446049250313e-16;
	}
	IC = Math.round((IC + numberEpsilon) * 100) / 100
	$("#importePenalPorCalidadVal").setImporte(IC);
	$("#importePenalPorCalidadLabel").setImporte(IC);
	
	if(IC != 0){
		$("#importePenalPorCalidadLabel").setImporte(-IC);
	}
	
}

function importeTotalPagar(){
	ITP = (IT + IF + IA) - IR - IC;
	
	if(isNaN(ITP)){
		ITP = 0;
	} else {
		ITP = parseFloat(ITP.toFixed(2));
	}
	
	$("#totalVal").setImporte(ITP);
	$("#total").setImporte(ITP);
}

function importeBasePagar(){
	I1 = parseFloat($("#ivaAplic").val());
	
	IBP = ITP * 100/(100 + I1);
	
	if(isNaN(IBP)){
		IBP = 0;
	} else {
		IBP = parseFloat(IBP.toFixed(2));
	}
	
	$("#importeBaseVal").setImporte(IBP);
	$("#importeBase").setImporte(IBP);
}

function importeCorrespondienteIVA(){
	IVA = ITP - IBP;
	
	if(isNaN(IVA)){
		IVA = 0;
	} else {
		IVA = parseFloat(IVA.toFixed(2));
	}
	
	$("#importeIVAVal").setImporte(IVA);
	$("#importeIVA").setImporte(IVA);
}

function findData(){
	$.ajax({
	  	 type: 'GET'
	  	 ,url: '/aa79bItzulnetWar/ejecuciontarea/datosPagoProveedor/'+idTarea+'/'+anyoExpediente+'/'+idExpediente
		 ,dataType: 'json'
		 ,contentType: 'application/json' 	
	     ,async: false
	     ,cache: false 
	  	 ,success:function(data){
	  		 palTrados = data.palTrados; // palTrados
	  		 var tradosTareas = data.tarea.datosTareaTrados;
	  		 if(tradosTareas != null){
	  			 numTotalPalTrados = tradosTareas.numTotalPal;
	  			 numPalConcor084090 = tradosTareas.numPalConcor084;
	  			 numPalConcor8594090 = tradosTareas.numPalConcor8594;
	  			 numPalConcor95100090 = tradosTareas.numPalConcor95100;
	  		 }
	  		 
	  		 var documentosTareasList = data.tarea.documentosExpediente;
	  		 for(var i=0;i < documentosTareasList.length; i++){
	  			if(documentosTareasList[i].claseDocumento == claseDocuEnum.traduccion || documentosTareasList[i].claseDocumento == claseDocuEnum.revision
	  					|| documentosTareasList[i].claseDocumento == claseDocuEnum.trabajo){
		  			numTotalPalIZOSuma = documentosTareasList[i].numPalIzo + numTotalPalIZOSuma;
		  			numTotalPalSolicSuma = documentosTareasList[i].numPalSolic + numTotalPalSolicSuma;
		  			numTotalTradosPalDocu = documentosTareasList[i].tradosExp.numTotalPal + numTotalTradosPalDocu;
		  			numPalTradosConcor084090 = documentosTareasList[i].tradosExp.numPalConcor084090 + numPalTradosConcor084090;
		  			numPalTradosConcor8594090 = documentosTareasList[i].tradosExp.numPalConcor8594090 + numPalTradosConcor8594090;
		  			numPalTradosConcor95100090 = documentosTareasList[i].tradosExp.numPalConcor95100090 + numPalTradosConcor95100090;
	  			}
	  		 }
	  		 
  			if(numTotalPalIZOSuma < palTrados){

  				$("#numtotalPal").val(numTotalPalIZOSuma);
	  			$("#numTotalPalSol").text(numTotalPalIZOSuma);
	  			$("#palHoraConcor084").prop('readonly', true);
	  			$("#palHoraConcor8594").prop('readonly', true);
	  			$("#palHoraConcor95100").prop('readonly', true);
			} else {
					 
				if(numTotalTradosPalDocu === 0){
					
					$("#numtotalPal").val(numTotalPalIZOSuma);
		  			$("#numTotalPalSol").text(numTotalPalIZOSuma);
				} else {
					
					$("#numtotalPal").val(numTotalTradosPalDocu);
		  			$("#numTotalPalSol").text(numTotalTradosPalDocu);
				}
	  			
				if(numPalConcor084090 != null){
					$("#palHoraConcor084").val(numPalConcor084090);
		  			$("#numPalSolicConcor084").text(numPalConcor084090);
				}
				
				if(numPalConcor8594090 != null){
					$("#palHoraConcor8594").val(numPalConcor8594090);
		  			$("#numPalSolicConcor8594").text(numPalConcor8594090);
				}
				
				if(numPalConcor95100090 != null){
					$("#palHoraConcor95100").val(numPalConcor95100090);
		  			$("#numPalSolicConcor95100").text(numPalConcor95100090);
				}
			}

  			if(data.tarea.datosPagoProveedores){
	  			cargarSwitchRecargos(data.tarea.datosPagoProveedores);
	  			
	  			$("#numtotalPal").val(data.tarea.datosPagoProveedores.numTotalPal);
	  			$("#palHoraConcor084").val(data.tarea.datosPagoProveedores.numPalConcor084);
	  			$("#palHoraConcor8594").val(data.tarea.datosPagoProveedores.numPalConcor8594);
	  			$("#palHoraConcor95100").val(data.tarea.datosPagoProveedores.numPalConcor95100);
	  			
	  			$("#importeRealizarTareaVal").val(data.tarea.datosPagoProveedores.importeTareaEur);
	  			$("#importeRealizarTarea").val(data.tarea.datosPagoProveedores.importeTarea);
	  			$("#importeRecargoPorFormatoVal").val(data.tarea.datosPagoProveedores.importeRecargoFormatoEur);
	  			$("#importeRecargoPorFormato").val(data.tarea.datosPagoProveedores.importeRecargoFormato);
	  			$("#importeRecargoPorApremioVal").val(data.tarea.datosPagoProveedores.importeRecargoApremioEur);
	  			$("#importeRecargoPorApremio").val(data.tarea.datosPagoProveedores.importeRecargoApremio);
	  			$("#importePenalPorRetrasoVal").val(data.tarea.datosPagoProveedores.importePenalizacion);
	  			$("#importePenalPorRetrasoLabel").val(data.tarea.datosPagoProveedores.importePenalizacionEur);
	  			
	  			if($("#importePenalPorRetrasoVal").getImporte() != 0){
	  				$("#importePenalPorRetrasoLabel").val("- " + data.tarea.datosPagoProveedores.importePenalizacion);
	  			}

	  			if (data.tarea.datosPagoProveedores.nivelCalidad != null) {
	  				$("#calidad_combo").rup_combo("setRupValue", data.tarea.datosPagoProveedores.nivelCalidad);
	  			}
	  			
	  			$("#porPenalizacionCalidad").val(data.tarea.datosPagoProveedores.porPenalizacionCalidad);
	  			$("#importePenalPorCalidadVal").val(data.tarea.datosPagoProveedores.importePenalCalidad);
	  			$("#importePenalPorCalidadLabel").val(data.tarea.datosPagoProveedores.importePenalCalidadEur);
	  			
	  			if($("#importePenalPorCalidadVal").getImporte() != 0){
	  				$("#importePenalPorCalidadLabel").val("- " + data.tarea.datosPagoProveedores.importePenalCalidad);
	  			}
	  			
	  			$("#importeBaseVal").val(data.tarea.datosPagoProveedores.importeBaseEur);
	  			$("#importeBase").val(data.tarea.datosPagoProveedores.importeBase);
	  			$("#importeIVAVal").val(data.tarea.datosPagoProveedores.importeIvaEur);
	  			$("#importeIVA").val(data.tarea.datosPagoProveedores.importeIva);
	  			$("#totalVal").val(data.tarea.datosPagoProveedores.importeTotalEur);
	  			$("#total").val(data.tarea.datosPagoProveedores.importeTotal);

	  			indAlbaran = data.tarea.datosPagoProveedores.indAlbaran;
	  			if(indAlbaran.trim() != "S"){
	  				$('#asociableAAlbaranSwitch').bootstrapSwitch('setState', false);
	  				$('#asociableAAlbaranSwitch').val('N');
	  			}else{
	  				$('#asociableAAlbaranSwitch').bootstrapSwitch('setState', true);
	  				$('#asociableAAlbaranSwitch').val('S');
	  			}
  			}
  			
  			if(typeof asignadoProveedores === 'undefined' && !ejecutarTareaConsulta){
  				$('#asociableAAlbaranSwitch').bootstrapSwitch('setState', true);
  				$('#asociableAAlbaranSwitch').val('S');
  			}
  			
  			
	  		rellenarPestanaInfo(data);
	  		actualizarImportes();
	  		desbloquearPantalla();
	  	 }
	});
}

function rellenarPestanaInfo(data){
	indFacturacion = data.tarea.indFacturacion;
	if(indFacturacion.trim() === "Sí"){
		
		$('#facturableSwitch').bootstrapSwitch('setState', true);
		$("#facturable").text($.rup.i18n.app.comun.si);
	}else{ 
		
		$('#facturableSwitch').bootstrapSwitch('setState', false);
		$("#facturable").text($.rup.i18n.app.comun.no);
	}
	
	if(ejecutarTareaConsulta){
		$('#tabsDatosPagoProveedor input').prop('readonly', true);
    	$("#tabsDatosPagoProveedor [type='checkbox']").prop('disabled',true);
	}	
		
	$("#numPalSolicitado").text(numTotalPalSolicSuma);
	$("#numTotalPalIzo").text(numTotalPalIZOSuma);
	$("#numPalXmlTrados").text(numTotalTradosPalDocu);
	$("#tramosConcor1").text(numPalTradosConcor084090);
	$("#tramosConcor2").text(numPalTradosConcor8594090);
	$("#tramosConcor3").text(numPalTradosConcor95100090);
	
	$("#lote").text(data.tarea.lotes.nombreLote);
	if(data.tarea.lotes.empresasProveedoras.cif != null){
		$("#cif").text(data.tarea.lotes.empresasProveedoras.cif);
	}
	$("#tipoTareaId").val(data.idTipoExpediente);
	
	if($("#tipoTareaId").val() === "R"){
		
		$("#numPalConcorDiv").hide();
		$("#numPalSolicConcorDiv").hide();
		$("#porcentaje_pago_filter_fieldset").hide();
		$("#tradosInfoRow").hide();
	}
	
	if($.rup.lang === 'es'){
		
		$("#nombreEmpresa").text(data.tarea.lotes.empresasProveedoras.descEs);
		$("#tipoTarea").text(data.tipoExpedienteDescEs);
		$("#idiomaDestino").text(data.expedienteTradRev.idIdiomaDescEs);
	} else {
		
		$("#nombreEmpresa").text(data.tarea.lotes.empresasProveedoras.descEu);
		$("#tipoTarea").text(data.tipoExpedienteDescEu);
		$("#idiomaDestino").text(data.expedienteTradRev.idIdiomaDescEu);
	}
	
	$("#fechaHoraEntregaIzo").text(data.expedienteTradRev.fechaFinalIzo);
	$("#vigenciaLote").text(data.tarea.lotes.fechaInicio + " - " + data.tarea.lotes.fechaFin);
	$("#iva").text(data.tarea.lotes.iva + "%");
	$("#ivaAplic").val(data.tarea.lotes.iva);
	$("#importePalabraIVA").text(data.tarea.lotes.importePalabra + "€");
	$("#valImportePalabraIVA").val(data.tarea.lotes.importePalabra);
	$("#porcentajePago8594").text(data.tarea.lotes.porPagoPalConcor8594);
	$("#porcentajePago95100").text(data.tarea.lotes.porPagoPalConcor95100);
	$("#formato").text(data.tarea.lotes.porRecargoFormato + "%");
	$("#apremio").text(data.tarea.lotes.porRecargoApremio+ "%");
	$("#retraso").text(data.tarea.lotes.porPenalizacion+ "%");
	$("#tareaRevision").text(data.tarea.lotes.porRevision+ "%");
	$("#tareaRevisionAplic").val(data.tarea.lotes.porRevision);

	//$("#sobreNumPal").attr('max', data.tarea.lotes.porRecargoFormato);
	$("#porcentajePenalizacion").attr('max', data.tarea.lotes.porPenalizacion);
	$("#porcentajeRecarga").attr('max', data.tarea.lotes.porRecargoApremio);
	
	$("#importeAsig").text(data.tarea.lotes.importeAsignadoEur);
	$("#importeTotalConsum").text(data.tarea.lotes.importeConsumidoEur);
	
	$.ajax({
	  	 type: 'GET'
	  	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/calcularImportePrevistoLote/'+idTarea+'/'+data.tarea.lotes.idLote
		 ,dataType: 'json'
		 ,contentType: 'application/json' 	
	     ,async: false
	  	 ,success:function(data){
	  		importePrevistoLote = data;
	  		$("#importeTotalPre").setImporte(importePrevistoLote + $("#total").getImporte());
	  		$("#importeTotalPre").append(" €");
	  		calcularImporteDisponible();
	  	 }
	});
	
	cargarEventosSwitchChange();
}

function finalizarTarea(){
	if($("#confirmartarea").length){
		$("#confirmartarea").remove();
		$("#confirmartarea").rup_dialog('destroy');		
		$("#divTareasExpedientes").append('<div id="confirmartarea" style="display:none"></div>');
	}
	
	$("#confirmartarea").confirmar_tarea({esHoraOblig:esHoraOblig , tieneHora: true, modalSelector: "ejecutarTareaDialog"});
	$("#confirmartarea").confirmar_tarea("open");
}

function guardarTarea(){
	var datos = new Object();
	datos = $("#ejecutarTareaDialog_form").rup_form().formToJson();
	
	jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/ejecuciontarea/datosPagoProveedor/guardarTarea",
		dataType: "json",
		contentType: 'application/json', 
		data: $.toJSON(datos),
		cache: false,
		success: function (data) {
			if (data === 1) {
				$("#ejecutarTareaDialog").rup_dialog("close");
				$('#asignadoProveedor_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.guardadoCorrecto"), "ok");
				$('#asignadoProveedor').trigger('reloadGrid');
			}
		},
		error: function (){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_message.tituloError"), "error");
		}
    });
	
}

$("#facturableSwitch").bootstrapSwitch()
.bootstrapSwitch('setSizeClass', 'switch-small')
.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);

$("#recargaPorFormatoSwitch").bootstrapSwitch()
.bootstrapSwitch('setSizeClass', 'switch-small')
.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);

$("#recargoPorSwitch").bootstrapSwitch()
.bootstrapSwitch('setSizeClass', 'switch-small')
.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);

$("#penalizacionPorRetrasoSwitch").bootstrapSwitch()
.bootstrapSwitch('setSizeClass', 'switch-small')
.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);

$("#asociableAAlbaranSwitch").bootstrapSwitch()
.bootstrapSwitch('setSizeClass', 'switch-small')
.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);

$("#calidad_combo").rup_combo({
	source: "/aa79bItzulnetWar/administracion/datosmaestros/nivelesdecalidad",
    sourceParam : {
        value: "nivel",
        label : "descRango",
        style : "css"
    }
	,width: "100%"
	,rowStriping: true
	,ordered: false
	,disabled: ejecutarTareaConsulta /* || (tipoExp === "R" && typeof asignadoProveedores === 'undefined') ? true : false*/
	,open: function(){
		jQuery('#calidad_combo-menu').width(jQuery('#calidad_combo-button').innerWidth());
	}
	,select: function(){
		var nvlCalidad = $("#calidad_combo").rup_combo("getRupValue");
		var tabla = $("#penalizacionesPorCalidad").rup_table("getRowData", nvlCalidad);
		$("#porPenalizacionCalidad").val(tabla.porPenalizacion);
		actualizarImportes();
	}
});

$("#asociableAAlbaranSwitch").on('switch-change', function(event, state) {
    if (state.value){ //Activando
    	$('#asociableAAlbaranSwitch').val('S');
    }else{
    	$('#asociableAAlbaranSwitch').val('N');
    }
});

$("#facturableSwitch").on('switch-change', function(event, state) {
    if (state.value){ //Activando
    	
    	$('#tabsDatosPagoProveedor input').prop('readonly', false);
    	if(numTotalPalIZOSuma < palTrados){
    		$("#palHoraConcor084").prop('readonly', true);
  			$("#palHoraConcor8594").prop('readonly', true);
  			$("#palHoraConcor95100").prop('readonly', true);
    	}
    	$("#tabsDatosPagoProveedor [type='checkbox']").prop('disabled',false);
    	$("#indFacturacion_form").val("S");
    }else{
    	
    	$('#recargaPorFormatoSwitch').bootstrapSwitch('setState', false);
    	$('#penalizacionPorRetrasoSwitch').bootstrapSwitch('setState', false);
    	$('#recargoPorSwitch').bootstrapSwitch('setState', false);
    	$('#tabsDatosPagoProveedor input').prop('readonly', true);
    	$("#tabsDatosPagoProveedor [type='checkbox']").prop('disabled',true);
    	$('#asociableAAlbaranSwitch').bootstrapSwitch('setState', false);
    	$('#asociableAAlbaranSwitch').val('N');
    	$("#indFacturacion_form").val("N");
    	$("#numtotalPal").val(0);
    	$("#palHoraConcor084").val(0);
		$("#palHoraConcor8594").val(0);
		$("#palHoraConcor95100").val(0);
		actualizarImportes();
    }
});

function cargarEventosSwitchChange(){

	$("#recargaPorFormatoSwitch").on('switch-change', function(event, state) {
		if (state.value){ //Activando
			$("#sobreNumPal").attr("disabled", false);
			$("#sobreNumPal").rules("add", "required");
		}else{
			$("#sobreNumPal").attr("disabled", true);
			$("#sobreNumPal").rules("remove", "required");
			$("#sobreNumPal").val("");
		}
		sobreNumPalChange();
	});
	$("#recargoPorSwitch").on('switch-change', function(event, state) {
		if (state.value){ //Activando
			$("#porcentajeRecarga").attr("disabled", false);
			$("#porcentajeRecarga").rules("add", "required");
		}else{
			$("#porcentajeRecarga").attr("disabled", true);
			$("#porcentajeRecarga").rules("remove", "required");
			$('#porcentajeRecarga').val("");
		}
		porRecargaChange();
	});
	$("#penalizacionPorRetrasoSwitch").on('switch-change', function(event, state) {
		if (state.value){ //Activando
			$("#porcentajePenalizacion").attr("disabled", false);
			$("#porcentajePenalizacion").rules("add", "required");
		}else{
			$("#porcentajePenalizacion").attr("disabled", true);
			$("#porcentajePenalizacion").rules("remove", "required");
			$('#porcentajePenalizacion').val("");
		}
		porPenalChange();
	});
}

/*
 * UTILIDADES - FIN
 */

jQuery(function($){
	
	$("#anyo_filter").val(anyoExpediente);
	$("#numExp_filter").val(idExpediente);
	$("#idTarea_filter").val(idTarea);
	$("#anyo_form").val(anyoExpediente);
	$("#numExp_form").val(idExpediente);
	$("#idTarea_form").val(idTarea);

	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	formConsulta();
	
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}
	
	$("#tabsDatosPagoProveedor").rup_tabs({
		tabs : [
			{i18nCaption: $.rup.i18n.app.label.pestDatosPago, layer: "#pestDatosPago"},
			{i18nCaption: $.rup.i18n.app.label.pestInfoAdicional, layer: "#pestInfoAdicional"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});
	
	$("#confirmartarea").confirmar_tarea({esHoraOblig:esHoraOblig , tieneHora: true, modalSelector: "ejecutarTareaDialog"});
	
	//Ejemplo de toolbar: volver y finalizar tarea
	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volver"
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
	                    e.preventDefault();
	                    e.stopImmediatePropagation();
	                    $("#ejecutarTareaDialog").rup_dialog("close");
	                }
			}
			,{
				i18nCaption:  $.rup.i18n.app.boton.finalizarTarea
				,css: "fa fa-save"
				,id: "finalizar"
				,click : 
					function(e){
	                    e.preventDefault();
	                    e.stopImmediatePropagation();
						// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
						if (comprobarTareaNoEjecutada(idTarea)){
		                    if($("#ejecutarTareaDialog_form").valid()){
		        				finalizarTarea();
		        			}
						}
	                }
			}
			,{
				i18nCaption:  $.rup.i18nParse($.rup.i18n.app.boton,"guardar")
				,css: "fa fa-save"
				,id: "guardar"
				,click : 
					function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if($("#ejecutarTareaDialog_form").valid()){
						guardarTarea();
					}
					
				}
			}
		]
	});
	
	$("#palabrasPorDocumento").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/datosTablaTotalPalabras",
		colNames: [
			$.rup.i18n.app.label.documento,
			$.rup.i18n.app.label.solicitadas,
			$.rup.i18n.app.label.IZO,
			$.rup.i18n.app.label.xmlTrados,
			$.rup.i18n.app.label.tramosConcor1,
			$.rup.i18n.app.label.tramosConcor2,
			$.rup.i18n.app.label.tramosConcor3
		],
		colModel: [
				{ 	name: "titulo", 
				 	label: "label.documentos",
				 	index: "TITULO056",
					align: "left", 
					width: "340", 
					editable: true, 
					fixed: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "numPalSolic", 
				 	label: "label.solicitadas",
				 	index: "NUMPALSOLIC056",
					align: "left", 
					width: "100", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "numPalIzo", 
				 	label: "label.IZO",
				 	index: "NUMPALIZO056",
					align: "left", 
					width: "50", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},				
				{ 	name: "tradosExp.numTotalPal", 
				 	label: "label.xmlTrados",
				 	index: "NUMTOTALPAL091",
					align: "left", 
					width: "100", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "tradosExp.numPalConcor084090", 
					label: "comun.tramosConcor1",
					index: "NUMPALCONCOR084091",
					align: "left", 
					width: "80", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "tradosExp.numPalConcor8594090", 
					label: "comun.tramosConcor2",
					index: "NUMPALCONCOR8594091",
					align: "left", 
					width: "80", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "tradosExp.numPalConcor95100090", 
					label: "comun.tramosConcor3",
					index: "NUMPALCONCOR95100091",
					align: "left", 
					width: "80", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				}
        ],
        model:"DocumentosExpediente",
        sortname: "d2.ID_DOC_056",
		sortorder: "asc",
        usePlugins:[
        	 "fluid",
      		 "responsive",
       		 "filter"
        ],
        primaryKey: ["idDoc"],
		loadOnStartUp: true,
		multiselect:false,
		beforeSelectRow: function (){
			return false;
		},
		rowNum: 5,
		loadComplete: function(data){ 
			if($("#tipoTareaId").val() === 'R'){
				$("#palabrasPorDocumento").rup_table("hideCol", "tradosExp.numTotalPal");
				$("#palabrasPorDocumento").rup_table("hideCol", "tradosExp.numPalConcor084090");
				$("#palabrasPorDocumento").rup_table("hideCol", "tradosExp.numPalConcor8594090");
				$("#palabrasPorDocumento").rup_table("hideCol", "tradosExp.numPalConcor95100090");
			}else{
				$("#palabrasPorDocumento").rup_table("showCol", "tradosExp.numTotalPal");
				$("#palabrasPorDocumento").rup_table("showCol", "tradosExp.numPalConcor084090");
				$("#palabrasPorDocumento").rup_table("showCol", "tradosExp.numPalConcor8594090");
				$("#palabrasPorDocumento").rup_table("showCol", "tradosExp.numPalConcor95100090");
			}
			habilitarPager('palabrasPorDocumento');
			$("#palabrasPorDocumento_pager").find('td.pagControls select').hide();
	    },
	});
	
	$("#penalizacionesPorCalidad").rup_table({
		url: "/aa79bItzulnetWar/administracion/datosmaestros/nivelesdecalidad/getAll",
		colNames: [
			txtNivel,
			txtNivelDeCalidad,
			txtPorPenalizacion
		],
		colModel: [
			{ 	name: "nivel", 
			 	label: "label.nivel",
			 	index: "NIVEL",
				align: "center", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			
			{ 	name: "rangoIni",
			 	label: "label.nivelDeCalidad",
			 	index: "RANGOINI",
				align: "center", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter : function(cellvalue,
						options, rowObject) {
					return rowObject.rangoIni
								+ " - "
								+ rowObject.rangoFin;

				}
			},
			{ 	name : "porPenalizacion", 
			 	index: "PORPENALIZACION", 
				label: "label.porcentajePenalizacion",
				align: "center", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true,
				sortable: true
			}
        ],
        model:"NivelesDeCalidad",
        sortname: "NIVEL",
		sortorder: "asc",
        usePlugins:[
        	 "fluid",
      		 "responsive",
       		 "filter"
        ],
        loadComplete: function(){ 
        	$("#penalizacionesPorCalidad_pager").hide();
        },
        primaryKey: ["nivel"],
		loadOnStartUp: true,
		multiselect:false,
		beforeSelectRow: function (){
			return false;
		}
	});

	
	$("#ejecutarTareaDialog_form").rup_form({
		url: "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		dataType: "json",
		type: "POST",
		beforeSubmit: function(){
			$("#idTarea_form").val(idTarea);
			$("#idTipoTarea_form").val(idTipoTarea);
		},
		success: function(){
			//Cerrar el diálogo, mostrar feedback y recargar la pantalla o la tabla
			elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
			ejecutarTareaReturn(false, "ejecutarTareaDialog", tablaSelector, null, "tareasExpedientes_feedback");
			desbloquearPantalla();
		},
		error: function(error){
			//mostrar feedback de error y no cerrar la pestaña
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null, null, null);
			desbloquearPantalla();
		}		
	});
	
	$("#palabrasPorDocumento_filter_div").hide();
	
	$("#numtotalPal").change(function(){
		actualizarImportes();	
	});
	$("#palHoraConcor084").change(function(){
		actualizarImportes();		
	});
	$("#palHoraConcor8594").change(function(){
		actualizarImportes();	
	});
	$("#palHoraConcor95100").change(function(){
		actualizarImportes();	
	});
	$("#sobreNumPal").change(function(){
		sobreNumPalChange();	
	});
	$("#porcentajeRecarga").change(function(){
		porRecargaChange();
	});
	$("#porcentajePenalizacion").change(function(){
		porPenalChange();
	});	
	
	Number.prototype.countDecimals = function () {
	    if(Math.floor(this.valueOf()) === this.valueOf()) return 0;
	    return this.toString().split(".")[1].length || 0; 
	}

	jQuery.validator.addMethod("validateConcordanciaMayorNumPal", function(value, element, params) {
		var sumaConcordancia = B1 + B2 + B3;
		if($("#indFacturacion_form").val() === "S"){
			if(A1 != sumaConcordancia && sumaConcordancia > 0){
				return false;  
			}else{
				return true;
			}
		} else {
			return true;
		}
	},$.rup.i18n.app.mensajes.concordanciaMayorNumPal);
	
	jQuery.validator.addMethod("validMenorNumPal", function(valor) {  
		if ($('#recargaPorFormatoSwitch').bootstrapSwitch('state') && parseFloat($('#sobreNumPal').val()) > parseFloat($('#numtotalPal').val())) {
			return false;
		} else {
			return true;
		}
	},$.rup.i18n.app.validaciones.valorMayorQueNumPal);

	jQuery.validator.addMethod("validateMaxPercentage", function(valor) {  
		if (valor!= '' && parseFloat(valor) < 0 || parseFloat(valor) > parseFloat(100)) {
	        return false;
	    } else {
			return true;
		}
	},$.rup.i18n.app.validaciones.porcentajeMenorCien);
	
	$("#ejecutarTareaDialog_form").rup_validate({
		feedback: $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: false,
		showFieldErrorsInFeedback: false,
		rules:{
			"datosPagoProveedores.numTotalPal": {validateConcordanciaMayorNumPal:true},
			"datosPagoProveedores.indRecargoApremio": {validateMaxPercentage:true},
			"datosPagoProveedores.porRecargoApremio": {validateMaxPercentage:true},
			"datosPagoProveedores.numPalRecargoFormato": {validMenorNumPal:true}
		}
	});
	
	if (typeof asignadoProveedores === 'undefined') {
		$('[id="ejecutarTareaDialog_toolbar##guardar"]').hide();
	} else {
		$('[id="ejecutarTareaDialog_toolbar##finalizar"]').hide();
	}
	
	elFormulario = $("#ejecutarTareaDialog_form").rup_form("formSerialize");
	
 });
