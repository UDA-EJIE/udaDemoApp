var esHoraOblig = false;
var cambios;
var oid = '';
var idFichero;
function descargarFicheroTarea(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);//88  2
}

/**
 */
function eliminarDocumentoSalida(){
	$.ajax({
	   	 type: 'POST'		   	 
	   	 ,url: '/aa79bItzulnetWar/documentosgeneral/eliminarDocumentoSalidaTareaTrabajo/'+idFichero + '/' + idTarea + "/" + oid
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,async: false
	     ,cache: false
	   	 ,success:function(ret){
			if (ret){
				// documento salida eliminado
				// vaciamos el formulario correspondiente
		   		$('#idFicheroSalida_ejec_tar_trabajo_form').val("");
		   		$('#nombreSalida_ejec_tar_trabajo_form').val("");
		   		$('#tituloSalida_ejec_tar_trabajo_form').val("");
		   		$('#indEncriptadoSalida_ejec_tar_trabajo_form').val("");
		   		$('#oidSalida_ejec_tar_trabajo_form').val("");
				// llamamos a la funcion para actualizar el documento de salida
				// en este caso para borrar el documento y el enlace eliminar
				comprobarDocumentoSalida();
				mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.mensajes.eliminadoCorrecto, "ok");
			} else {
				mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			}
	   		desbloquearPantalla();
	   	 }
 	 	,error: function(){
 	 		desbloquearPantalla();
			mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		extensionOK =  false;
	   	 }
	 });
}

/**
 */
function eliminarFicheroTarea(elIdFichero){
	// mostramos modal de confirmacion de eliminar documento salida
	$.rup_messages("msgConfirm", {
		title: $.rup.i18n.app.comun.eliminar,
		message: $.rup.i18n.app.mensajes.preguntaEliminarFichero,
		OKFunction: function(){
			oid = $('#oidSalida_ejec_tar_trabajo_form').val();
			idFichero = elIdFichero;
			// obtenemos los datos del documento a eliminar y tras bloquear pantalla llamamos a funcion correspondiente
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"), eliminarDocumentoSalida);
			
		},
		CANCELFunction: function(e){
			return false;
		}
	});
}

/**
 */
function inicializarToolbarEjecucionTareaTrabajo(){
	$("#ejecutarTareaTrabajoDialog_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volver"	
				,css: "fa fa-arrow-left"
				,click : 
					 function(e){
					 	e.preventDefault();
	                    e.stopImmediatePropagation();
	                	$("#ejecutarTareaTrabajoDialog").rup_dialog("close");
					}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.finalizarTarea
				,css: "fa fa-save"
				,click : 
					 function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
						// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
						if (comprobarTareaTrabajoEjecutada(idTarea, $('#idTrabajo_ejec_tar_trabajo_form').val())){
							if ($("#ejecutarTareaTrabajoDialog_form").valid()){
								if($("#confirmartarea").length){
					   				$("#confirmartarea").remove();
					   				$("#confirmartarea").rup_dialog('destroy');		
					   				$("#divTareasExpedientes").append('<div id="confirmartarea" style="display:none"></div>');
					   			}
								$("#confirmartarea").confirmar_tarea({
									esHoraOblig:esHoraOblig,
									tieneHora: true,
									modalSelector: "ejecutarTareaTrabajoDialog",
									idTrabajo: $('#idTrabajo_ejec_tar_trabajo_form').val()
								});
				   				$("#confirmartarea").confirmar_tarea("open");
							}
						}
					}
			}
		]
	});
	$('#ejecutarTareaTrabajoDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
}

/**
 */
function inicializarFormEjecucionTareaTrabajo(){
	$("#ejecutarTareaTrabajoDialog_form").rup_form({
		url: "/aa79bItzulnetWar/ejecuciontarea/finalizarTareaTrabajo",
		feedback: $('#ejecutarTareaTrabajoDialog_feedback'),
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false,
		dataType: "json",
		type: "POST",
		beforeSubmit: function(){
			$("#idTarea_form").val(idTarea);
			$("#idTipoTarea_form").val(idTipoTarea);
		},
		success: function(){
			//Cerrar el dialogo, mostrar feedback y recargar datos de tarea
			$("#ejecutarTareaTrabajoDialog").rup_dialog("close");
			actualizarDatosTareaEnPantalla();
			mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
		},
		error: function(error){
			//mostrar feedback de error y no cerrar la pestaña
			mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18nParse($.rup.i18n.base, "rup_message.error"), "error");
			desbloquearPantalla();
		},
		validate:{ 
			rules:{
				"observacionesTareas.obsvEjecucion":{ maxlength : 2000 }
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
			showFieldErrorsInFeedback: false
		}
	});
}

/* PID PIF - INICIO */
/**
 */
function comprobarExtensionValida( nombreFichero){
	tipoErrorDoc = 0;
	var extensionAdjunto = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	var extensionOK = false;
	if(comprobarExtensionConBBDD(extensionAdjunto, "ejecutarTareaTrabajoDialog_feedback")){
		extensionOK =  true;
	}else{
		//el documento no esta admitido
		tipoErrorDoc = 2;
	}
	return extensionOK;	
}

/**
 */
function inicializarFormularioPifPid(){
$("#fichero").change(function(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	if ($("#fichero").val() !== ''){
		if (comprobarExtensionValida($('#fichero').val())){
			$("#subidaFicheroPidTareaTrabajo_form").submit();
    	}else{ 
    		if(tipoErrorDoc === 1){
    			//documento adjunto debe tener la misma extension que opriginal (zip)
    			mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.mensajes.zipObligatorio, "error");
    		}else{
    			mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
    		}
			$("#fichero").val('');
    		desbloquearPantalla();
    	}
	}
});

/**
 */
$('#subidaFicheroPidTareaTrabajo_form').rup_form({
	url: "/aa79bItzulnetWar/ficheros/subidaFichero"
	, dataType: "json"
	, beforeSend: function () {
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
		return "skip";
	}
	, success: function(archivoPIF){
		if (archivoPIF.error === null){ //Subida correcta
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/ficheros/pasoPIFaPID'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(archivoPIF)			
			     ,beforeSend: function () {
			    	 	desbloquearPantalla();
						bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardandoDoc"));
					}
			   	 ,success:function(data){
			   		if (data.error === null){ 
			   			//insercion en bbdd 
			   			var jsonObjectFichero ={
			   					"contentType" : data.contentType,
			   					"encriptado" : data.encriptado,
			   					"extension" : data.extension,
			   					"nombre" : data.nombre,
			   					"oid" : data.oid,
			   					"tamano" : data.tamano
			   			} 
			   			
			   			let subidaTarea = {
			   					"idDocOriginal":$('#idFicheroSalida_ejec_tar_trabajo_form').val(),
			   					"idTarea": $('#idTarea_ejec_tar_trabajo_form').val(),
			   					"fichero": jsonObjectFichero
			   			}
						// guardamos el documento directamente en bbdd asociado a la tarea
			   			$.ajax({
			   				type: 'POST'
			   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaDocumentoSalidaTareaTrabajo'
			   			 	 ,dataType: 'json'
			   			 	 ,contentType: 'application/json' 
			   			     ,data: $.toJSON(subidaTarea)		
			   			     ,success: function (data){
			   			    	 mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
								 $('#idFicheroSalida_ejec_tar_trabajo_form').val(data.idFichero);
								 $('#nombreSalida_ejec_tar_trabajo_form').val(data.nombre);
								 $('#indEncriptadoSalida_ejec_tar_trabajo_form').val(data.encriptado);
								 $('#oidSalida_ejec_tar_trabajo_form').val(data.oid);
			   			    	 comprobarDocumentoSalida();
			   					 desbloquearPantalla();
			   			     },error: function (e){
			   			    	desbloquearPantalla();
			   			    	mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   			     }
			   			});
			   			
			   			
			   		}else{
			   			desbloquearPantalla();
			   			alert("subida incorrecta al pid: " + data.error);
					}
			   	 }
		   	 	,error: function(){
		   	 		desbloquearPantalla();
		   	 		mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   	 }
			 });
		}else{//subida incorrecta
   			desbloquearPantalla();
   			mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", archivoPIF.error, "error");
		}
		$("#fichero").val(""); 
	 }
	, error: function(archivoPIF){
		desbloquearPantalla();
		mostrarMensajeFeedback("ejecutarTareaTrabajoDialog_feedback", archivoPIF.error, "error");
	}
});
}
/**
 */
function comprobarCambiosFormulario(){
	return true;
}
/* PID PIF - FIN */

/**
 * vaciamos la capa y despues comprobamos si hay documento de salida; si lo hay, pintamos
 */
function comprobarDocumentoSalida(){
	// vaciamos la capa
	$(".docSalidaTareaTrabajo").empty();
	if (ejecutarTareaConsulta) {
		// si modo consulta ocultamos el boton de subir fichero
		$('#capaBtnPID').hide();
	} 
	if ($('#idFicheroSalida_ejec_tar_trabajo_form').val()){
		// hay documento de tarea;  lo pintamos
		var indEncriptado = $('#indEncriptadoSalida_ejec_tar_trabajo_form').val();
		var laFila = '<div class="displayInline">';
		 	laFila += '<p class="document-fileAndIcon"><a href="#" class="document-eusk iconSameLine" onClick="descargarFicheroTarea('+ $('#idFicheroSalida_ejec_tar_trabajo_form').val() +')" data-id="'+$('#idFicheroSalida_ejec_tar_trabajo_form').val()+'">';
 			laFila += ''+$('#nombreSalida_ejec_tar_trabajo_form').val()+'</a>';
			laFila+= ' <span class="ico-ficha-encriptado" title="'+ ((indEncriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((indEncriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
			laFila+='</p>';	
			laFila+='</div>';	
			laFila+= '<div class="displayInline pl10">';
			if (ejecutarTareaConsulta){
				// si modo consulta ajustamos estilos
				$(".docSalidaTareaTrabajo").removeClass('pl10');
			} else {
				// si no esta en modo consulta, mostramos boton de subida de fichero y enlace de eliminar fichero
//				$('#capaBtnPID').show();
		        laFila+='<a href="#" id="eliminar_documento_salida" class="document-eusk" onClick="eliminarFicheroTarea('+ $('#idFicheroSalida_ejec_tar_trabajo_form').val() +')">';		
		        laFila+=$.rup.i18n.app.comun.eliminar;		
		        laFila+='</a>';	
			}		
			laFila+='</div>';	
		 $(".docSalidaTareaTrabajo").append(laFila);	
	} else if (ejecutarTareaConsulta){
		// si no hay documento y modo consulta, ocultamos label de documento
		$('#labelIdFicheroSalida_ejec_tar_trabajo_form').hide();
	}
}

jQuery(function($) {
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}
	cambios = false;
	inicializarToolbarEjecucionTareaTrabajo();
	inicializarFormEjecucionTareaTrabajo();
	comprobarDocumentoSalida();
	$("#pidButton_ficheroSubida").on("click", function() {
		$('#fichero').click();
	});
	inicializarFormularioPifPid();
	
});