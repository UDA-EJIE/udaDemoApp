var horasReales;
var destinoUpload;
var esHoraOblig = false;
var indFicheroDoc = false;
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var cargaInicial = true;
/*
 * UTILIDADES - INICIO
 */
/**
 * Comprueba si hay cambios en la modal. Si no los hay, se cierra la modal.
 */

function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}

function cargarDatosInforme(datosInforme){
	if(ejecutarTareaConsulta){
		$('#divFicheroFileCorreciones').hide();
	}
	var enlace = '<a href="#" class="descargarFicheroFileCorreciones" >'+datosInforme.nombre+'</a>';	   	
	$('#enlaceDescargaDetalle_1').show();
		$('#enlaceDescargaDetalle_1').html(enlace);
		$('a.descargarFicheroFileCorreciones').click(function(event){
			event.preventDefault();	
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			descargarDocumentoGeneral(datosInforme.idFichero);
			desbloquearPantalla();
		});
}

function cargarDatosDocumento(datosDocumento){
	if(ejecutarTareaConsulta){
		$('#divDocumentoCorreciones').hide();
	}
	var enlace = '<a href="#" class="descargarDocumentoCorreciones">'+datosDocumento.nombre+'</a>';	   		
	$('#enlaceDescargaDetalle_2').show();
	$('#enlaceDescargaDetalle_2').html(enlace);
	$('a.descargarDocumentoCorreciones').click(function(event){
		event.preventDefault();	
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		descargarDocumentoGeneral(datosDocumento.idFichero);
		desbloquearPantalla();
	});
}

function actualizarValorIndCorrecciones(){
	if(!cargaInicial){
		var indCorrecciones = $('#ind_correcciones_filter').bootstrapSwitch('state')?'S':'N';
		$.rup_ajax({
			type: 'POST'               
	            ,url: '/aa79bItzulnetWar/documentosgeneral/actualizarValorIndCorreccionesBOE/' + idTarea + '/' + indCorrecciones
	          ,dataType: 'json'
	          ,contentType: 'application/json' 
	         ,async: false
	         ,cache: false
			,success : function(data) {
				if(1 == data){
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.indiceCorreccionesActualizado, "ok");
				}else{
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorActualizandoIndiceCorrecciones, "error");
				}
				
			},
			error : function() {
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorActualizandoIndiceCorrecciones, "error");
			}
		});
	}
}


function formConsulta(){
		$.rup_ajax({
			type: 'GET'               
	            ,url: '/aa79bItzulnetWar/documentosgeneral/consultaFicherosBOE/'+idTarea
	          ,dataType: 'json'
	          ,contentType: 'application/json' 
	         ,async: false
	         ,cache: false
			,success : function(data) {
				if(data){
					if(data.ficheroInforme.idFichero){
						cargarDatosInforme(data.ficheroInforme);
					}else{
						$('#nombreFicheroInfo_1').rules("add","required");
					}
					if(data.ficheroDoc.idFichero){
						cargarDatosDocumento(data.ficheroDoc);
					}else if(data.indCorreciones === "S"){
						$('#nombreFicheroInfo_2').rules("add","required");
					}
					if(data.indCorreciones === "S"){
						$('#ind_correcciones_filter').bootstrapSwitch('setState', true);
						$("#pidButtonDocumentoCorreciones").removeClass("no-obligatorio");
						
					}else{ 
						$('#ind_correcciones_filter').bootstrapSwitch('setState', false);
						$("#pidButtonDocumentoCorreciones").addClass("no-obligatorio");
					}	
					cargaInicial = false;
				}else{
					$('#nombreFicheroInfo_1').rules("add","required");
					
				}
				
			},
			error : function() {
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", "Error recuperando datos de tarea", "error");
			}
		});
}

function comprobarCambiosFormulario(){
	return true;
}

function finalizarTarea() {
	if ($("#confirmartarea").length) {
		$("#confirmartarea").remove();
		$("#confirmartarea").rup_dialog('destroy');
		$("#divTareasExpedientes").append(
				'<div id="confirmartarea" style="display:none"></div>');
	}

	$("#confirmartarea").confirmar_tarea({
		esHoraOblig : esHoraOblig,
		tieneHora : true,
		documentos : false,
		modalSelector : "ejecutarTareaDialog"
	});
	$("#confirmartarea").confirmar_tarea("open");
}

/*
 * UTILIDADES - FIN
 */

// Ekaitz
function comprobarExtensionValida(nombreFichero) {
	var extension = nombreFichero.substring(nombreFichero.lastIndexOf(".") + 1,
			nombreFichero.length);
	var extensionOK = false;
	
	var jsonObject = {
		"formato011" : extension
	};
	$.ajax({
				type : 'GET',
				url : '/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/comprobarextension',
				dataType : 'json',
				contentType : 'application/json',
				data : jsonObject,
				async : false,
				cache : false,
				success : function(extensionValida) {
					
					if (extensionValida > 0) {
						extensionOK = true;
					}
				},
				error : function() {
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
					extensionOK = false;
				}
			});
	return extensionOK;
}

function subirFichero(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));

	var laExtension  = $("#ficheroFinal").val().substring($("#ficheroFinal").val().lastIndexOf('.')+1, $("#ficheroFinal").val().length);
	
	if ( (destinoUpload === 2 && laExtension.toLowerCase() === extensionPDF)
		|| (destinoUpload === 1 && comprobarExtensionValida($('#ficheroFinal').val()) ) ) {
	
//	if (comprobarExtensionValida($('#ficheroFinal').val())){
		$("#subidaFicheroPidFinal_form").submit();
	}else{ 
		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
		desbloquearPantalla();
	}
	
}

$('#subidaFicheroPidFinal_form').rup_form({
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
				   			subidaTarea = {
				   					"idDocOriginal":0,
				   					"idTarea": idTarea,
				   					"idTablaIntermedia": tablaIntermedia.tabla86,
				   					"idIdiomaDest":$('#idiomaDest').val() ,
				   					"indFicheroDoc":indFicheroDoc ,
				   					"destinoUpload":destinoUpload ,
				   					"indCorrecciones":$('#ind_correcciones_filter').bootstrapSwitch('state')?'S':'N' ,
				   					"fichero": jsonObjectFichero
				   			}
				   			$.ajax({
				   				type: 'POST'
				   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroBOE'
				   			 	 ,dataType: 'json'
				   			 	 ,contentType: 'application/json' 
				   			     ,data: $.toJSON(subidaTarea)		
				   			     ,success: function (data){
				   			    	if(1 == destinoUpload){
				   						cargarDatosInforme(data);
				   						$('#nombreFicheroInfo_1').rules("remove","required");
				   					}
				   					if(2 == destinoUpload){
				   						cargarDatosDocumento(data);
				   						$('#nombreFicheroInfo_2').rules("remove","required");
				   					}
				   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");

				   					$("#fichero").val('');
				   					desbloquearPantalla();
				   			    	 
				   			     },error: function (data){
				   			    	desbloquearPantalla();
				   			    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
				   			     }
				   			});
				   		}else{
				   			desbloquearPantalla();
				   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
						}
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
				   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   	 }
				 });
			}else{//subida incorrecta
	   			desbloquearPantalla();
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", archivoPIF.error, "error");
			}
			
		 }
		, error: function(data){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
			desbloquearPantalla();
		}
});
// fin Ekaitz

jQuery(function($) {
	
	$("#ejecutarTareaDialog_form").rup_validate({
		feedback: $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: false,
		showFieldErrorsInFeedback: false,
		rules:{
//			"nombreFicheroInfo_1": {required:true}
		}
	});
	
	$("#ind_correcciones_filter").on('switch-change', function(event, state) {
	    if (state.value){ //Activando
	    	if ( $('#enlaceDescargaDetalle_2').html() == '' ){
	    		$("#nombreFicheroInfo_2").rules("add", "required");
	    	}
			$("#pidButtonDocumentoCorreciones").removeClass("no-obligatorio");
	    }else{
	    	$("#nombreFicheroInfo_2").rules("remove", "required");
	    	$("#pidButtonDocumentoCorreciones").addClass("no-obligatorio");
	    	
	    }
	    actualizarValorIndCorrecciones();
	});
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons : [ {
			id: "volver",			
			i18nCaption : $.rup.i18n.app.boton.volver,
			css : "fa fa-arrow-left",
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
					if($("#ejecutarTareaDialog_form").valid()){
						finalizarTarea();
					}
				}
				
			}
		} ]
	});

	if (horasObligatorias === 'S') {
		esHoraOblig = true;
	}
	
	$("#confirmartarea").confirmar_tarea({
		esHoraOblig : esHoraOblig,
		tieneHora : true,
		documentos : false,
		modalSelector : "ejecutarTareaDialog"
	});

	$("#ind_correcciones_filter").bootstrapSwitch().bootstrapSwitch(
			'setSizeClass', 'switch-small').bootstrapSwitch('setOffLabel',
			$.rup.i18n.app.comun.no).bootstrapSwitch('setOnLabel',
			$.rup.i18n.app.comun.si);
	$("#pidButtonDocumentoCorreciones").addClass("no-obligatorio");
	
	$("#ficheroFinal").change(function(){
		if ($("#ficheroFinal").val() !== ''){
			subirFichero();
		}
	});
	
	$("#pidButtonFileCorreciones").on("click", function() {
		destinoUpload = 1;
		$('#ficheroFinal').click();
	});
	
	$("#pidButtonDocumentoCorreciones").on("click", function() {
			destinoUpload = 2;
			$('#ficheroFinal').click();
	});
	
	$("#ejecutarTareaDialog_form").rup_form({
		url : "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		feedback : $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault : false,
		showErrorsInFeedback : true,
		showFieldErrorsInFeedback : false,
		dataType : "json",
		type : "POST",
		beforeSubmit : function() {
			$("#anyo_form").val(anyoExpediente);
			$("#numExp_form").val(idExpediente);
			$("#idTarea_form").val(idTarea);
			$("#idTipoTarea_form").val(idTipoTarea);
			
		},
		success : function() {
			ejecutarTareaConsulta = true;
			ejecutarTareaReturn(false, "ejecutarTareaDialog",
					tablaSelector, null,
					"tareasExpedientes_feedback");
			desbloquearPantalla();
		},
		error : function() {
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null,
					null, null);
			desbloquearPantalla();
		}
	});
	
	//Para obligar a que sean encriptados o no en función de la confidenciallidad del expediente
	$('#reqEncriptado').val(expedienteConfidencial);
		
	formConsulta();
});