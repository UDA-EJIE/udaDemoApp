var initDocumentoForm;
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};
var idrelevancia;
var tipoExpediente;

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function inicializarDialogDocumentosExpedienteConsulta(){
	$("#documentosexpediente_detail_div").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: true,
        width: 950,
        title: $.rup.i18n.app.label.infoDocumento
	});
}

function crearObjetosIzoConsultaDetalleDoc(){
	if("I" != tipoExp && $('#datosPresupuestoPestanaDocConsultaEstadoPpto').length){
		$('#datosPresupuestoPestanaDocConsultaEstadoPpto').rup_combo({
			loadFromSelect: true
			,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
			},onLoadSuccess: function() {
				$('#datosPresupuestoPestanaDocConsultaEstadoPpto').rup_combo("disable");
			}
	    });
		$('#datosPresupuestoPestanaDocConsultaFechaLimite').rup_date();
	}else if($('#datosPresupuestoPestanaDocConsultaEstadoPptoInter').length){
		$('#datosPresupuestoPestanaDocConsultaEstadoPptoInter').rup_combo({
			loadFromSelect: true
			,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
			},onLoadSuccess: function() {
				$('#datosPresupuestoPestanaDocConsultaEstadoPptoInter').rup_combo("disable");
			}
	    });
		$('#datosPresupuestoPestanaDocConsultaFechaLimiteInter').rup_date();
	}
}

function vaciarFileYDesbloquearConsulta(){
	$("#ficheroConsulta").val('');
	desbloquearPantalla();
}

function conversionKBConsulta( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function descargarDocumento(elIdDoc){		
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+1+'/'+elIdDoc);//56  1 
}
function descargarDocGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}
function descargarFicheroXliff(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);//88  2
}

function asociarEventosBotonesTabla(){
	$( "a.verDocumento").unbind( "click" );
	$( "a.eliminarDocumento").unbind( "click" );
	$( "a.descargarDoc").unbind( "click" );
	$( "a.descargarDocGeneral").unbind( "click" );
	$( "a.descargarDocXliff").unbind( "click" );
	
	$('a.verDocumento').click(function(event){
		event.preventDefault();	
		var elIdDoc = $(this).data("id");
		verDocumento(elIdDoc);
	});
	$('a.descargarDoc').click(function(event){
		event.preventDefault();	
		var elIdDoc = $(this).data("id");
		descargarDocumento(elIdDoc);
	});
	$('a.descargarDocGeneral').click(function(event){
		event.preventDefault();	
		var elIdDoc = $(this).data("id");
		descargarDocGeneral(elIdDoc);
	});
	$('a.descargarDocXliff').click(function(event){
		event.preventDefault();	
		var elIdDoc = $(this).data("id");
		descargarFicheroXliff(elIdDoc);
	});
}

function crearClaseDocumentoCombo(){
//	tipo = 'T';
	tipo = tipoExpediente;
	if($('#claseDocumentoCombo_detail_table-menu').length){
		$('#claseDocumentoCombo_detail_table-menu').remove();
	}
		
	$('#claseDocumentoCombo_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/clasificaciondocumentos/tipoexpediente/"+tipo,
		sourceParam :{
			label: $.rup.lang === 'es' ? "descEs075":"descEu075", 
					value: "id075",
					style:"css"
		}
		,width: "170"
		,ordered: true	
		,async: false
		,rowStriping: true
		,open: function(){
			jQuery('#claseDocumentoCombo_detail_table-menu').width(jQuery('#claseDocumentoCombo_detail_table-button').innerWidth());
		}
		,onLoadSuccess: function() {
			if (tipoExpediente === 'I'){
				$('#claseDocumentoCombo_detail_table').rules("remove", "required");
			}else{
				$("#claseDocumentoCombo_detail_table option[value='1']").remove();
				$("#claseDocumentoCombo_detail_table option[value='2']").remove();
				$("#claseDocumentoCombo_detail_table option[value='4']").remove();
				$("#claseDocumentoCombo_detail_table").rup_combo("refresh");		
			}

		}
	});
}



function anadeFila( existeFila, elIdInsertado, elIdDoc2 ){
	var laFila = '<div class="row documento-all" id="filaDoc'+elIdInsertado+'"><div class="documento-first" style="border-radius: 8px;"><p class="document-tit">' + labelTipo +': ';
	laFila+= $('#claseDocumentoCombo_detail_table').rup_combo('label');
	laFila+= '</p><p class="document-fileAndIcon"><a href="#" class="document-eusk descargarDoc iconSameLine" data-id="'+elIdInsertado+'">';
	laFila+=  $('#nombreFicheroInfoConsulta').val();
	laFila+= ' <span class="documento-archivo">('+ conversionKB($('#tamanoDoc056Consulta').val())+' KB.) </span></a>';
	//ENCRIPTADO
	laFila+= ' <span class="ico-ficha-encriptado" title="'+ (($('#indEncriptado056Consulta').val()==='S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ (($('#indEncriptado056Consulta').val()==='S')?"":"un") +'lock" aria-hidden="true"></i></span>';
	laFila+= '</p></div>';
	if (!existeFila){
		laFila+= '</div>';
	}
	
	var eltoFila = $(laFila);
	
	if (!existeFila){
		eltoFila.appendTo('#capaDocumentos');
	}else{
		$('#filaDoc'+elIdInsertado).html(eltoFila);
	}
	asociarEventosBotonesTabla();
}





jQuery(function($){
	datosFormulario = '';

	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	
	$('#fechaFinalIzo_exp').rup_date();

	$('#datosExpedienteConsulta_feedback').rup_feedback(opcionesFeedbacks);
	$('#tablaDocumentos_feedback').rup_feedback(opcionesFeedbacks);
	$('#documentosexpedienteConsulta_detail_feedback').rup_feedback(opcionesFeedbacks);
	
	function cargaDatosExpTradRev(){
		
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/planificacion/'+anyoExp+"/"+numExp
		 	 ,dataType: 'json' 	 	 	
		 	 ,cache: false
		 	 ,async: false
		   	 ,success:function(data){
		   		if (data !== null){
		   			//existe el registro			   			
			   		$('#anyo_exp').val(anyoExp);
			   		$('#numExp_exp').val(numExp);
			   		$('#numTotalPalIzo_exp').val(data.numTotalPalIzo);
			   		$('#fechaFinalIzo_exp').val(data.fechaFinalIzo);
			   		$('#horaFinalIzo_exp').val(data.horaFinalIzo);		   		
			   		idrelevancia = data.idRelevancia;
			   		if(data.indFacturable === 'S'){
						$('#indFacturable_exp').bootstrapSwitch('setState', true);
					}else{
						$('#indFacturable_exp').bootstrapSwitch('setState', false);
					}
			   		if(data.indUrgente === 'S'){
						$('#indUrgente_exp').bootstrapSwitch('setState', true);
					}else{
						$('#indUrgente_exp').bootstrapSwitch('setState', false);
					}
			   		if(data.indDificil === 'S'){
						$('#indDificil_exp').bootstrapSwitch('setState', true);
					}else{
						$('#indDificil_exp').bootstrapSwitch('setState', false);
					}
			   		
			   		$('#fechaFinalSolic_exp').val(data.fechaFinalSolic);
			   		$('#fechaFinalProp_exp').val(data.fechaFinalProp);
			   		
			   		if(data.indPresupuesto === 'S'){
			   			$('#indPresupuesto_exp').bootstrapSwitch('setState', true);
			   		}else{
			   			$('#indPresupuesto_exp').bootstrapSwitch('setState', false);
			   		}
			   		if ( data.numTotalPalSolic < $('#palPresupuesto').val() ){	// cambiar a palPresupuesto
			   			var parentIndPresupuesto = $('#indPresupuesto_exp').parent().parent();
			   			parentIndPresupuesto.addClass("disabled");
						$('#indPresupuesto_exp').attr('disabled','disabled');
			   		}
			   		if (!isEmpty(data.aceptacionFechaHora)){
			   			$('#negocNuevaFechaLimite').val(data.aceptacionFechaHora.fechaLimite);
			   			$('#negocNuevaHoraLimite').val(data.aceptacionFechaHora.horaLimite);
			   			$('#nuevaFechaIZO').val(data.aceptacionFechaHora.fechaEntrega);
			   			$('#nuevaHoraIZO').val(data.aceptacionFechaHora.horaEntrega);
			   			
			   			if (!isEmpty(data.aceptacionFechaHora.estado)){
			   				$('#negocNuevaEstadoPpto').rup_combo("select", ''+data.aceptacionFechaHora.estado);
			   				if (data.aceptacionFechaHora.estado != "1"){
			   					$('#negocNuevaHoraLimite').prop( {"readonly": true, "disabled": true });
			   					$('#negocNuevaFechaLimite').rup_date("disable");
			   					$('#negocNuevaEstadoPpto').rup_combo("disable");
			   				}
			   			}
			   			$('#capaAceptacionFechaFin').show();
			   		}
			   		
			   		datosFormularioDoc = $("#datosExpedienteConsulta_form").serialize();
			   		comprobarFormularioDoc = true;
		   		}else{
		   			$('#datosExpedienteConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
		   		}	
		   	 }
		 	,error: function(){	  	 		
		 		$('#datosExpedienteConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		   	 }
		 });
	}
	
	//Para obtener el tipo de expediente T51
	$.ajax({
	   	 type: 'GET'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/'+anyoExp+"/"+numExp
	 	 ,dataType: 'json' 	 	 	
	 	 ,cache: false
	 	 ,async: false
	   	 ,success:function(data){
	   		if ((data !== null)&&(data.idTipoExpediente!=null)){			   			
	   			tipoExpediente = data.idTipoExpediente;		   		
	   		}	else $('#datosExpedienteConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
	   	 }
  	 	,error: function(){	  	 		
  	 		$('#datosExpedienteConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	   	 }
	 });
	
	
	$('#nuevaFechaIZO').rup_date();
	$('#nuevaHoraIZO').prop( {"readonly": true, "disabled": true });
	$('#nuevaFechaIZO').rup_date("disable");
	$('#negocNuevaFechaLimite').rup_date();
	$('#negocNuevaFechaLimite').prop( {"readonly": true, "disabled": true });
	$('#negocNuevaFechaLimite').rup_date("disable");
	$("#negocNuevaEstadoPpto").rup_combo({
			loadFromSelect: true
			,width: "100%"
			,ordered: false	
			,disabled: true
			,rowStriping: true
			,open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
			}
	});
	
	asociarEventosBotonesTabla();
	
	$("#capaPestanaCompletaDoc :input").attr("disabled", true);
	$("#capaPestanaCompletaDoc :button").attr("disabled", true);
	
// EXPEDIENTE de tipo REVISION o TRADUCCION ................................................................................................................
	
	if (tipoExpediente !== 'I'){
		
		cargaDatosExpTradRev();
		if (tipoExpediente === 'R') $('#capaRequierePpto').hide();
		$('#idRelevancia_exp').rup_combo({
			source : "/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/estado/"+"A",
			sourceParam :{
				label: $.rup.lang === 'es' ? "descRelevanciaEs"
						: "descRelevanciaEu", 
				value: "idTipoRelevancia",
				style:"css"
			}
			,width: "170"
			,ordered: false	
			,disabled: true
			,rowStriping: true
			,open: function(){
		        jQuery('#idRelevancia_exp-menu').width(jQuery('#idRelevancia_exp-button').innerWidth());
		    },
			onLoadSuccess: function() {
		   		$('#idRelevancia_exp').rup_combo("select", ''+idrelevancia);
		   		datosFormularioDoc = $("#datosExpedienteConsulta_form").serialize();
		   		comprobarFormularioDoc = true;
			}
		});
		
		//Formulario de la T53
		$("#datosExpedienteConsulta_form").rup_validate({
			feedback: $('#datosExpedienteConsulta_feedback'),
			liveCheckingErrors: false,				
			block:false,
			delay: 3000,
			gotoTop: true, 
			rules:{
				"numTotalPalIzo": {required: true, integer: true, min: 0, maxlength:6},			
				"fechaFinalIzo": {required: true, date: true},
				"horaFinalIzo": {required: true, hora: true,maxlength:5},
				"anyo": {required: true},
				"numExp": {required: true}
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
	 		showFieldErrorsInFeedback: false
		});	
		$('#datosTradRevDivDocConsulta').show();
		
	}else{  // EXPEDIENTE de tipo INTERPRETACION ................................................................................................................
		$('#datosPresupuestoPestanaDocConsulta').show();
		$('#anyo_exp').val(anyoExp);
   		$('#numExp_exp').val(numExp);
		
		datosFormularioDoc = '';
		comprobarFormularioDoc = false;
	}
	
	inicializarDialogDocumentosExpedienteConsulta();
	
	
	
	
	
	$('#tipoDocumento').val(tipoDocApoyo);
		
	if(esTecnicoIzo){
		crearObjetosIzoConsultaDetalleDoc();
	}else{
		$('.document-content-right').hide();
		$('#capaDocumentos').removeClass("col-md-10");
		$('#capaDocumentos').addClass("col-md-12");
	}
	
	//Anadido para permitir añadir docs de referencia a los técnicos
	
	crearClaseDocumentoCombo();
	
	function anadirDocumento(){	
		$("#documentosexpediente_detail_div").rup_dialog("open");
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		$("#documentosexpedienteConsulta_detail_form").rup_form('clearForm', true);
		clearValidation('#documentosexpedienteConsulta_detail_form');		
		$("#documentosexpedienteConsulta_detail_feedback").rup_feedback("close");
		$('#anyo056Consulta_detail_table').val($('#anyo_exp').val());
   		$('#numExp056Consulta_detail_table').val($('#numExp_exp').val());
   		
   		$('#indVisible056Consulta_detail_table').bootstrapSwitch('setState', true);
   		$('#enlaceDescargaDetalleConsulta').html('');
   		$('#pidButtonConsulta').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
   		$('#claseDocumentoCombo_detail_table').rup_combo("enable");
   		$("#nombreFicheroInfoConsulta").rules("add", "required");
		$("#titulo056Consulta_detail_table").rules("remove", "required");
   		if (tipoExpediente === 'I'){
   			$('#capaDetClasificacionConsulta').hide();
   		} else{
   			$('#capaDetClasificacionConsulta').show();   			
   		}
   		initDocumentoForm = $("#documentosexpedienteConsulta_detail_form").rup_form("formSerialize");
   		desbloquearPantalla();
	}
	//FORMULARIO DE DETALLE
	$("#documentosexpedienteConsulta_detail_form").rup_form({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/"+origen,
		dataType: "json",			
		feedback:$("#documentosexpedienteConsulta_detail_feedback"),		
		type: "POST",
		beforeSubmit: function(){ 
				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"));
		},
		success: function(data){
			if (data != null){
				//error de extensión no permitida
				if ( data.ficheros[0].error != null){
						$("#documentosexpedienteConsulta_detail_feedback").rup_feedback("set", data.ficheros[0].error, "error");
						$('#nombreFicheroInfoConsulta').val('');
				}else{ //archivos permitidos
					var existeFila = false;
					$('#idDoc056_detail_table').val(data.ficheros[0].idDocInsertado);
					$('#idDocInsertadoConsulta').val(data.ficheros[0].idDocInsertado);
					
					anadeFila(existeFila,data.ficheros[0].idDocInsertado,null );
					
					$("#documentosexpediente_detail_div").rup_dialog("close");
					$("#datosExpedienteTradRev_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
					if (data.llamadaPL === true){
		   				cargaDatosExpTradRev();
		   			}
					//Actualizar bitácora + cabecera
					bitacoraUpdate(true);
				}
				desbloquearPantalla();
			}else{
				desbloquearPantalla();
				$("#documentosexpedienteConsulta_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" data es null"), "error");		
			}
			
			if(detalleSub && subrayado){
				var Expediente = new Object();
			    Expediente.anyo=anyoExpediente;
			    Expediente.numExp=idExpediente;
				pintarDocumentosSub(Expediente);
			}
		},
		error: function(){
			desbloquearPantalla();
			$("#documentosexpedienteConsulta_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" error no success"), "error");
		},
		validate:{ 
			rules:{
				"claseDocumento":{ required:true},	//EXPED rev o trad	
				"tipoDocumento":{ required:true}, //clase de doc rev o trad
				"numPalIzo":{ required:true, maxlength: 6,integer: true},	//clase de doc rev o trad		
				"titulo":{ maxlength: 256},				
					
				"ficheros[0].contacto.persona":{ maxlength: 150},				
				"ficheros[0].contacto.email":{ maxlength: 256, email:true},				
				"ficheros[0].contacto.direccion":{ maxlength: 256},				
				"ficheros[0].contacto.entidad":{ maxlength: 150},				
				"ficheros[0].contacto.telefono":{digits:true, maxlength: 15},				
				"ficheros[0].oid":{ required:true},
				"ficheros[0].nombreUpload":{ required:true},
				},
				showFieldErrorAsDefault: false,
		        showErrorsInFeedback: true,
		        showFieldErrorsInFeedback: false
		}
	});	
	
	
	$("#documentosexpediente_detail_div").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: true,
        width: 950,
//        showLoading:true,
        title: $.rup.i18n.app.label.infoDocumento
	});
	
	$("#documentosexpedienteConsulta_detail_button_save").button().click(function(){
		if ($("#documentosexpedienteConsulta_detail_form").valid()){		
			$("#documentosexpedienteConsulta_detail_form").submit();
		}
    });
	
	$("#documentosexpedienteConsulta_detail_link_cancel").button().click(function(){
		if (initDocumentoForm !== $("#documentosexpedienteConsulta_detail_form").rup_form("formSerialize")){
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					setTimeout(function() {
						$("#documentosexpediente_detail_div").rup_dialog("close");
						eliminarMensajes();
						}, 20);
					
				}
			});
		}else{
			$("#documentosexpediente_detail_div").rup_dialog("close");
		}
    });	
	
	
	
	// UPLOAD
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
		   		$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		extensionOK =  false;
		   	 }
		 });			
		return extensionOK;	
	}
	
	function vaciarFileYDesbloquear(){
		$("#ficheroConsulta").val('');
		desbloquearPantalla();
	}
	
	$('#subidaFicheroPidConsulta_form').rup_form({
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
				   	 ,success:function(data){
				   		if (data.error === null){ 
					   		var destinoUpload = $("#idBotonUploadConsulta").val();
					   		$('#nombreFicheroInfoConsulta').val(data.nombre);
							$('#nombreConsulta').val(data.nombre);
							$('#extensionDoc056Consulta').val(data.extension);
							$('#tamanoDoc056Consulta').val(data.tamano);
							$('#contentType056Consulta').val(data.contentType);
							$('#oidFichero056Consulta').val(data.oid);
							$('#indEncriptado056Consulta').val(data.encriptado);
							var enlace = '<span>'+data.nombre+'</span> ('+conversionKB(data.tamano)+' KB.) ';
							enlace+=' <span class="ico-ficha-encriptado" title="'+((data.encriptado==='S')?labelEncrip:labelNoEncrip)+'"><i class="fa fa-'+((data.encriptado==='S')?"":"un")+'lock" aria-hidden="true"></i></span>';
							$('#enlaceDescargaDetalleConsulta').html(enlace);
						}else{
							$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("set", data.error, "error");		
						}
				   		vaciarFileYDesbloquear();
				   	 }
				   	 , error: function(data){
						$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("set", data.error, "error");
						vaciarFileYDesbloquear();
					 }
				});
			}else{
				$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
				vaciarFileYDesbloquear();
			}	
		}
		, error: function(archivoPIF){
			$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
			vaciarFileYDesbloquear();
		}			
	});
	
	
	
	
	$("#ficheroConsulta").change(function(){
		$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("hide");
		if ($("#ficheroConsulta").val() !== ''){
			//no permitir zip en el 2o fich
			var laExtension  = $("#ficheroConsulta").val().substring($("#ficheroConsulta").val().lastIndexOf('.')+1, $("#ficheroConsulta").val().length);
			if (comprobarExtensionValida($('#ficheroConsulta').val())){
				$("#subidaFicheroPidConsulta_form").submit();
	    	}else{ 
	    		$('#documentosexpedienteConsulta_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	    	}
		}
	});
	
	
	
	
	$("[id^='pidButton']").unbind("click");
	$("[id^='pidButton']").click(function(event) {
		$("#idBotonUploadConsulta").val("0");
		$('#ficheroConsulta').click();
	});
	
	$('#btnNuevoDoc').click(function(event){
		event.preventDefault();
		anadirDocumento();
	});
});