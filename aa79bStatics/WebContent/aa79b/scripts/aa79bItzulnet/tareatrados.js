var horasReales;
var esHoraOblig = false;
var cambios = false;
var fechaNegociacionEntregaIzoInicial;
var fechaLimiteAceptacionInicial;
var fechaLimitePropuestaSeleccionable;
var horaLimitePropuestaSeleccionable;
var indicePresupuesto = false;
var idRequerimiento;
var idFichero;

/*
 * UTILIDADES - INICIO
 */

function clickPidButtonFinalXml(){
	$('#fichero').click();
}

function clickDescargarXml(){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}


function cargarpestanyaTarea(data){
	idRequerimiento = data.idRequerimiento;
	if('S'.localeCompare(data.indPresupuesto)==0){
		indicePresupuesto = true;
		//presupuesto si
		$('#checkReqPresupuesto').bootstrapSwitch('setState', true);
		//pestanya tarea
		if(data.datosTareaTrados){
			if(data.datosTareaTrados.presupuesto){
				$('#importePresupuesto').val(data.datosTareaTrados.presupuesto);
				$('#presupuesto_form').val(data.datosTareaTrados.presupuesto);
			}
			if('S'.localeCompare(data.datosTareaTrados.indVisible)==0){
				$('#checkVisible').bootstrapSwitch('setState', true);
			}else{
				$('#checkVisible').bootstrapSwitch('setState', false);
			}
			if(data.datosTareaTrados.fechaEntrega){
				$('#fechaNegociacionEntregaIzo').val((data.datosTareaTrados.fechaEntrega).replace(/-/g, '/'));
				$('#horaNegociacionEntregaIzo').val(data.datosTareaTrados.horaEntrega);
			}
		}
		//si requiere presupuesto, precargado con configurado por izo 01 o valor de campo
		if(data.fechaLimite){
			$('#fechaLimiteAceptacion').val((data.fechaLimite).replace(/-/g, '/'));
			$('#horaLimiteAceptacion').val(data.horaLimite);
			$('#minFechaLimiteAceptacion').val((data.fechaLimite).replace(/-/g, '/'));
			$('#minHoraLimiteAceptacion').val(data.horaLimite);
		}
		if(data.fechaLimiteSeleccionable){
			$('#fechaLimSeleccionable').val(data.fechaLimiteSeleccionable.replace(/-/g, '/'));
			$('#horaLimSeleccionable').val(data.horaLimiteSeleccionable);
			$('#fechaLimiteAceptacion').rup_date("option","maxDate", data.fechaLimiteSeleccionable.replace(/-/g, '/'));
		}
		cargarpestanyaDetallePresupuesto(data);
	}else{
		indicePresupuesto = false;
		//presupuesto no
		$('#checkReqPresupuesto').bootstrapSwitch('setState', false);
		$('#fieldsetPresupuesto').hide();
		
		deshabilitarPestana('tabsModalTrados', 1);
	}
	
	if(!$('#checkReqPresupuesto')[0].disabled){
		$('#checkReqPresupuesto').bootstrapSwitch('toggleDisabled',true,true);
	}
	$('#nombreFicheroXml').val(data.datosTareaTrados.nombreFicheroTrados);
	$('#idFicheroTradosXml').val(data.datosTareaTrados.idFicheroTrados);
	idFichero = data.datosTareaTrados.idFicheroTrados;
	if (idFichero != null) {
		$("#descargarFicheroXml").show();
	}
	cargarPestanyaInformacionFichero(data);

	if(!data.posibleNegociarNuevaFechaEntregaIzo){
		$('#fechaNegociacionEntregaIzo').val("");
		$('#horaNegociacionEntregaIzo').val("");
		$('#divFechaNegociacionEntregaIzo')[0].style.display = 'none'; 
		if('N'.localeCompare(data.indPresupuesto)==0){
			//si no indPresupuesto y no es posible negociar nueva fecha de entrega izo, no se muestra
			//fecha limite aceptacion
			$('#fechaLimiteAceptacion').val("");
			$('#horaLimiteAceptacion').val("");
			$('#divFechaLimiteAceptacion')[0].style.display = 'none';
		}
	}
	
	
	$('#indPresupuesto').val(data.indPresupuesto);
	$('#indPresupuestoExpTareaTrad').val(data.indPresupuesto);
	
}

function cargarpestanyaDetallePresupuesto(data){
	//pestanya Detalle Presupuesto
	$('#tituloTareaTrados')[0].textContent = data.titulo;
	$('#numTotalPalTareaTrados')[0].textContent = data.datosTareaTrados.numTotalPal;
	if(data.datosTareaTrados.presupuesto){
		$('#presupuestoTareaTrados')[0].textContent = data.datosTareaTrados.presupuesto+ "€";
	}else{
		$('#presupuestoTareaTrados')[0].textContent = 0+ "€";
	}
	
	$('#fechaHoraEntregaIzoTareaTrados')[0].textContent = data.fechaFinalIzo + ' ' + data.horaFinalIzo;
	//tabla 1
	$('#numExpTareaTrados')[0].textContent = data.numExpFormated;
	$('#tipoExpTareaTrados')[0].textContent = data.tipoExpedienteDescEu;
	$('#fechaSolicitudExpTareaTrados')[0].textContent = data.fechaAlta + ' ' + data.horaAlta;
	$('#idiomaDestinoExpTareaTrados')[0].textContent = data.idioma.descIdiomaEu;
	$('#relevanciaTextosExpTareaTrados')[0].textContent = data.relevancia.descRelevanciaEu;
	$('#bopvExpTareaTrados')[0].textContent = data.publicarBopvDescEu;
	if(data.datosTareaTrados.tarifaPal){
		$('#tarifaPalabraExpTareaTrados')[0].textContent = data.datosTareaTrados.tarifaPal + "€";
	}else{
		$('#tarifaPalabraExpTareaTrados')[0].textContent = 0+"€";
	}
	//tabla 2
	$('#numTotalPalExpTareaTrados')[0].textContent = data.datosTareaTrados.numTotalPal;
	$('#084ExpTareaTrados')[0].textContent = data.datosTareaTrados.numPalConcor084;
	$('#8594ExpTareaTrados')[0].textContent = data.datosTareaTrados.numPalConcor8594;
	$('#95100ExpTareaTrados')[0].textContent = data.datosTareaTrados.numPalConcor95100;
	$('#apremioExpTareaTrados')[0].textContent = data.urgenteDescEu;
	$('#dificultadExpTareaTrados')[0].textContent = data.dificultadDescEu;
	
	if(data.datosTareaTrados.importeBase){
		$('#importeBaseExpTareaTrados')[0].textContent = data.datosTareaTrados.importeBase+ "€";
	}else{
		$('#importeBaseExpTareaTrados')[0].textContent = 0+ "€";
	}

	if(data.datosTareaTrados.importeIva){
		$('#porcentajeIvaExpTareaTrados')[0].textContent = data.datosTareaTrados.importeIva+ "€";
	}else{
		$('#porcentajeIvaExpTareaTrados')[0].textContent = 0+ "€";
	}
	
	if(data.datosTareaTrados.presupuesto){
		$('#totalExpTareaTrados')[0].textContent = data.datosTareaTrados.presupuesto+ "€";
	}else{
		$('#totalExpTareaTrados')[0].textContent = 0+ "€";
	}
	
}

function cargarPestanyaInformacionFichero(data){
	if(data.datosTareaTrados.idFicheroTrados){
		mostrarEnlaceVer();
		habilitarPestana('tabsModalTrados', 2);
		//pestanya informacion del fichero
		
		
		$('#fechaHoraEjExpTareaTrados')[0].textContent = data.datosTareaTrados.fechaProyecto + ' ' + data.datosTareaTrados.horaProyecto;
		$('#nombreProyExpTareaTrados')[0].textContent = data.datosTareaTrados.nombreProyecto;
		
		
		//pintar metadatos
//		$('#metadatosExpTareaTrados')[0].innerHTML = ""; 
//		var htmlMetadatos = "";
//		for(var i = 0; i < data.lMetadatos.length; i++){
//			htmlMetadatos+="<label class='col-md-12'>"+data.lMetadatos[i].descEu+"</label>"
//		}
//		var htmlDiv = $(htmlMetadatos);
//		htmlDiv.appendTo("#metadatosExpTareaTrados");
		
		$('#numTotalPalSolicExpTareaTrados')[0].textContent = data.numPalSolic;
		$('#numTotalPalIzoExpTareaTrados')[0].textContent = data.numPalIzo;
		$('#numTotalPalXmlExpTareaTrados')[0].textContent = data.datosTareaTrados.numTotalPal;
		$('#concor084ExpTareaTrados')[0].textContent = data.datosTareaTrados.numPalConcor084;
		$('#concor8594ExpTareaTrados')[0].textContent = data.datosTareaTrados.numPalConcor8594;
		$('#concor95100ExpTareaTrados')[0].textContent = data.datosTareaTrados.numPalConcor95100;

		//pintar documentos
		$('#listaDocumentosExpTareaTrados')[0].innerHTML = ""; 
		var htmlDocumentos = "";
		htmlDocumentos+= "<table style='width:100%' class=' tablaConBorde tablaLineasImpares '>";
			htmlDocumentos+= "<tr>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.documento+"</th>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.solicitadas+"</th>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.IZO+"</th>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.total+"</th>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.tramosConcor1+"</th>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.tramosConcor2+"</th>";
				htmlDocumentos+= "<th>"+$.rup.i18n.app.label.tramosConcor3+"</th>";
			htmlDocumentos+= "</tr>";
							
		for(var j = 0; j < data.lDocsTarea.length; j++){
			htmlDocumentos+= " <tr>";
			htmlDocumentos+= "<td id='docNombreFicheroExpTareaTrados"+j+"'>"+data.lDocsTarea[j].nombreFichero+"</td>";
			htmlDocumentos+= "<td id='docNumPalSolicExpTareaTrados"+j+"'>"+data.lDocsTarea[j].numPalSolic+"</td>";
			htmlDocumentos+= "<td id='docNumPalIzoExpTareaTrados"+j+"'>"+data.lDocsTarea[j].numPalIzo+"</td>";
			htmlDocumentos+= "<td id='docNumTotalPalExpTareaTrados"+j+"'>"+data.lDocsTarea[j].numTotalPal+"</td>";
			htmlDocumentos+= "<td id='doc084ExpTareaTrados"+j+"'>"+data.lDocsTarea[j].numPalConcor084+"</td>";
			htmlDocumentos+= "<td id='doc8594ExpTareaTrados"+j+"'>"+data.lDocsTarea[j].numPalConcor8594+"</td>";
			htmlDocumentos+= "<td id='doc95100ExpTareaTrados"+j+"'>"+data.lDocsTarea[j].numPalConcor95100+"</td>";
			htmlDocumentos+= "</tr>";
		}
		htmlDocumentos+= "</table>";
		var htmlDiv2 = $(htmlDocumentos);
		htmlDiv2.appendTo('#listaDocumentosExpTareaTrados');
		
		if(data.palXmlSupPalIzoSupDesfaseAsumible){
			//si num palabras de xml es superior a num palabras IZO y esa diferencia es superior al desfase
			//asumible, se muestra icono de advertencia
			$('#desfaseNoAsumibleIcon')[0].classList.remove("oculto");
		}
	}else{
		deshabilitarPestana('tabsModalTrados', 2);
	}
	
}

function cargarDatosTareaTrados(){
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/cargaInicialTareaTrados/'+anyoExpediente+'/'+idExpediente+'/'+idTarea
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,async: true
	     ,cache: false
	   	 ,success:function(data){
	   		if(!data.errorMsg){
	   			cargarpestanyaTarea(data);
	   		}else{
	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.errorMsg, "error");
	   		}
	   	 }
  	 	,error: function(){
  	 		mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   	 }
	 });		
}

function crearCheckboxPestanaTarea(){
	jQuery('#checkReqPresupuesto')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	jQuery('#checkVisible')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}

function escucharCambiosEnPantalla(){
	$('#horaNegociacionEntregaIzo')[0].onkeyup = function(){
	    cambios = true;
	}
	$('#horaLimiteAceptacion')[0].onkeyup = function(){
		cambios = true;
	}
}

function cambiosTrue(){
	cambios = true;
}

/**
 * Comprueba si hay cambios en la modal. Si no los hay, se cierra la modal. 
 */
function comprobarCambiosFormulario(){
	// comprobar cambios formulario - validar
	if(cambios){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18n.app.mensajes.cambiosEnPantalla,
			message: $.rup.i18n.app.mensajes.cambiosEnPantallaEjecucionTareaMensaje,
			OKFunction: function(){
				cambios = false;
				$('#fechaNegociacionEntregaIzo').val('');
				$('#fechaLimiteAceptacion').val('');
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

function finalizarTarea(){
	if(!$('#idFicheroTradosXml').val()){
		mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.faltaFicheroXmlTrados, "error");
		return false;
	}
	if($('#checkVisible').bootstrapSwitch('state')){
		$('#indVisible_form').val('S');
	}else{
		$('#indVisible_form').val('N');
	}
	//validaciones de fechas y horas 
	eliminarMensajesValidacion();
	if(indicePresupuesto){
		$('#importePresupuesto').rules("add", "required");
		if((!"".localeCompare($('#fechaNegociacionEntregaIzo').val())==0) || !('none'.localeCompare($("#divCheckVisible").css('display'))==0)){
			//si llega hasta aqui se comprueba que si se ha introducido una fecha y hora valida de negociacion o si 
			//el check de presupuesto visible esta a Si --> el campo de fecha hora limite de acpetacion debe estar informado correctamente
			//comprobamos que la hora introducida es correcta
			$("#fechaLimiteAceptacion").rules("add", 'required');
			$("#fechaLimiteAceptacion").rules("add","date");
			$("#fechaLimiteAceptacion").rules("add", { "fechaMenorOIgualQueValorCampo": $('#fechaLimSeleccionable').val()});
			$("#horaLimiteAceptacion").rules("add", 'required'); 
			$("#horaLimiteAceptacion").rules("add", 'hora');
			$("#horaLimiteAceptacion").rules("add", { "horaFechaMenorOIgualQueValorCampo":  [$('#horaLimSeleccionable').val() , 'fechaLimiteAceptacion', 'fechaLimSeleccionable' ] }); 
			if($('#minFechaLimiteAceptacion').val()){
				$("#fechaLimiteAceptacion").rules("add", { "fechaMayorOIgualQueValorCampo": $('#minFechaLimiteAceptacion').val()});
				$("#horaLimiteAceptacion").rules("add", { "horaFechaMayorOIgualQueValorCampo":  [$('#minHoraLimiteAceptacion').val() , 'fechaLimiteAceptacion', 'minFechaLimiteAceptacion' ] });
			}else{
				//no requiere presupuesto y no tiene fecha limite
				// guardada, no se propone ninguna fecha limite, pero se valida que la fecha introducida sea mayor que la actual
				$("#fechaLimiteAceptacion").rules("add","fechaMayorNow");
				$("#horaLimiteAceptacion").rules("add", {'horaFechaMayorNow': ['subsanacion.fechaLimite']});
			}
			//si se ha introducido una fecha nueva de negociacion de izo, comprobar que no supera la limite
			if(!"".localeCompare($('#fechaNegociacionEntregaIzo').val())==0){
				$("#fechaNegociacionEntregaIzo").rules("add","date");
				$("#fechaNegociacionEntregaIzo").rules("add","fechaMayorNow");
				$("#horaNegociacionEntregaIzo").rules("add", 'required');
				$("#horaNegociacionEntregaIzo").rules("add", 'hora');
				$("#horaNegociacionEntregaIzo").rules("add", {'horaFechaMayorNow': ['fechaEntrega']});
				//fecha limite debe ser menor que fecha propuesta de IZO
				$("#fechaLimiteAceptacion").rules("add", { "fechaMenorOIgualQueValorCampoAux": $('#fechaNegociacionEntregaIzo').val()});
				$("#horaLimiteAceptacion").rules("add", { "horaFechaMenorQueValorCampo":  [$('#horaNegociacionEntregaIzo').val() , 'fechaLimiteAceptacion', 'fechaNegociacionEntregaIzo' ] });
			}
		}
	}
	
	
	if($('#ejecutarTareaDialog_form').valid()){
		if(indicePresupuesto && !('none'.localeCompare($("#divCheckVisible").css('display'))==0)){
			//si entra aqui el check de 'visible' es visible --> para finalizar la tarea debe estar a Si
			if(!$('#checkVisible').bootstrapSwitch('state')){
				mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.tareaTradosCheckVisibleATrue, "error");
				return false;
			}
		}
		cambios = false;
		//ejecutar tarea
		$("#ejecutarTareaDialog_form").submit();
		
	}else{
		if(indicePresupuesto){
			$("#fechaLimiteAceptacion").rules("remove", 'required');
			$("#horaLimiteAceptacion").rules("remove", 'required');
			$("#fechaLimiteAceptacion").rules("remove", "fechaMenorOIgualQueValorCampo");
			$("#fechaLimiteAceptacion").rules("remove", "fechaMayorOIgualQueValorCampo");
			$("#horaLimiteAceptacion").rules("remove", "horaFechaMayorOIgualQueValorCampo");
			$("#horaLimiteAceptacion").rules("remove", "horaFechaMenorOIgualQueValorCampo");
			$("#fechaLimiteAceptacion").rules("remove", "fechaMenorOIgualQueValorCampo");
			$("#horaLimiteAceptacion").rules("remove", "horaFechaMenorQueValorCampo");
			$("#fechaNegociacionEntregaIzo").rules("remove", 'required');
			$("#horaNegociacionEntregaIzo").rules("remove", 'required');
			$('#importePresupuesto').rules("remove", "required");
		}
		return false;
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
	window.open("/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerDocumentoPresupuestoTraduccion/"+idExpediente+'/'+anyoExpediente+'/'+idRequerimiento);
}

/*
 * UTILIDADES - FIN
 */

jQuery(function($){
	
	$("#idTareaFichero").val(idTarea);
	$("#anyoExpTareaTrad").val(anyoExpediente);
	$("#numExpTareaTrad").val(idExpediente);
	
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}

	//Ejemplo de toolbar: volver y finalizar tarea
	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,id:"volver"
				,click : 
					function(e){
	                    e.preventDefault();
	                    e.stopImmediatePropagation();
	                    $("#ejecutarTareaDialog").rup_dialog("close");
	                }
			}
			,{
				i18nCaption: $.rup.i18n.app.boton.finalizarTarea
				,css: "fa fa-save"
				,click : 
					function(e){
	                    e.preventDefault();
	                    e.stopImmediatePropagation();
						// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
						if (comprobarTareaNoEjecutada(idTarea)){
	                    	finalizarTarea();
						}
	                }
			}
		]
	});
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		delay: 7000
	});
	
	$("#ejecutarTareaDialog_form").rup_form({
		url: "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		dataType: "json",
		type: "POST",
		beforeSubmit: function(){
			$("#anyo_form").val(anyoExpediente);
			$("#numExp_form").val(idExpediente);
			$("#idTarea_form").val(idTarea);
			$("#idTipoTarea_form").val(idTipoTarea);
		},
		success: function(){
			//Cerrar el diálogo, mostrar feedback y recargar la pantalla o la tabla
			ejecutarTareaReturn(false, "ejecutarTareaDialog", tablaSelector, null, "tareasExpedientes_feedback");
			desbloquearPantalla();
		},
		error: function(error){
			//mostrar feedback de error y no cerrar la pestaña
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null, null, null);
			desbloquearPantalla();
		}			
	});
	
	$("#ejecutarTareaDialog_form").rup_validate({
		feedback: $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: false,
		showFieldErrorsInFeedback: false,
		rules:{
			"datosTareaTrados.fechaEntrega": {required:false,date:true},
			"subsanacion.fechaLimite": {required:false,date:true}
		}
	});
	
	$("#fichero").change(function(){
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		if ($("#fichero").val() !== ''){
			$("#subidaFicheroTrados_form").submit();
		}
	});
	
	$('#subidaFicheroTrados_form').rup_form({
		url: "/aa79bItzulnetWar/xmlTrados/subidaFichero"
		, dataType: "json"
		, beforeSend: function () {
			//bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			//Ponemos esto para que no falle el submit del formulario con fichero en Chrome
			return "skip";
		}
		, success: function(data){
			if(data.errorMsg){
				//si error al adjuntar fichero xml, se muestra el mensaje en feedback
				mostrarMensajeFeedback("ejecutarTareaDialog_feedback", data.errorMsg, "error");
			}else{
				cargarpestanyaTarea(data);
				mostrarEnlaceVer();
				if(data.palXmlSupPalIzoSupDesfaseAsumible){
					//si num palabras de xml es superior a num palabras IZO y esa diferencia es superior al desfase
					//asumible, se muestra icono de advertencia
					mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.atencionPalXmlSupPalIzoSupDesfaseAsumible, "alert");
				}
				cambios = false;
			}
			$("#fichero").val(""); 
			desbloquearPantalla();
		 }
		, error: function(data){
			desbloquearPantalla();
		}
	});
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalTrados").rup_tabs({
		tabs : [
			{i18nCaption: labelPestanaTradosTarea, layer:"#pestanaTradosTarea"},
			{i18nCaption: labelPestanaTradosDetallePresupuesto, layer:"#pestanaDetallePresupuesto"},
			{i18nCaption: labelPestanaTradosInforFichero, layer:"#pestanaInforFichero"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'},
			{opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		]
	});
	
	/* COMBOS CHECKBOX DATE*/
	crearCheckboxPestanaTarea();
	$("#fechaNegociacionEntregaIzo").rup_date({
		minDate: new Date()
	});
	$("#fechaLimiteAceptacion").rup_date({
		minDate: new Date()	
	});
	
	cargarDatosTareaTrados();
	
	escucharCambiosEnPantalla();
	
	if(ejecutarTareaConsulta){
		//ocultar los botones de adjuntar archivo
		ocultarBotonesPid();
	}
	
	$("#checkPresupuesto .has-switch").css("float","left");
	
 });
