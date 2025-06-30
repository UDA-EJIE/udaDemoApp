var esHoraOblig = false;
var listaDocs;
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};
var capaItzulpena = '';
var capaItzulpenaContacto = '';
var capaBerrikusketa = '';
var capaVersionesApoyo = '';
var cambios;
var obsOblig;
function escucharCambiosEnPantalla(){
	$('#checkSubsCorrecta').on('change.bootstrapSwitch', function(e) {
		cambios = true;
		if($('#checkSubsCorrecta').is(':checked')){
			obsOblig = false;
			$('#motivoSubsIncorrecta').val('');
			$('#motivoSubsIncorrecta')[0].disabled=true;
		}else{
			obsOblig = true;
			$('#motivoSubsIncorrecta')[0].disabled=false;
		}
	});
	$('#motivoSubsIncorrecta')[0].onkeyup = function(){
	    cambios = true;
	}
}

function comprobarCambiosFormulario(){
	// comprobar cambios formulario - validar
	if(cambios){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18n.app.mensajes.cambiosEnPantalla,
			message: $.rup.i18n.app.mensajes.cambiosEnPantallaEjecucionTareaMensaje,
			OKFunction: function(){
				cambios = false;
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
	if(obsOblig){
		if("".localeCompare($('#motivoSubsIncorrecta').val())==0){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaEstudiarSubsDocuCampoObligatorioSubsIncorrecta, "error");
			return false;
		}
	}
	cambios = false;
	//comprobar si la subsanacion es correcta
	if($('[id="checkSubsCorrecta"]').is(':checked')){
		//correcta
		$('#estadoReqSubs').val(2);
	}else{
		//no correcta - rechazada
		$('#estadoReqSubs').val(3);
	}
	if($("#confirmartarea").length){
			$("#confirmartarea").remove();
			$("#confirmartarea").rup_dialog('destroy');		
			$("#divTareasExpedientes").append('<div id="confirmartarea" style="display:none"></div>');
		}
		
		$("#confirmartarea").confirmar_tarea({esHoraOblig:esHoraOblig , tieneHora: true, modalSelector: "ejecutarTareaDialog"});
		$("#confirmartarea").confirmar_tarea("open");
		
}

function deshabilitarDialogVerDocumento(){
	if($( "input[id^='personaContacto056_']" ).length){
		$( "input[id^='personaContacto056_']" ).attr('disabled','disabled');
		$( "input[id^='emailContacto056_']" ).attr('disabled','disabled');
		$( "input[id^='telefonoContacto056_']" ).attr('disabled','disabled');
		$( "input[id^='entidadContacto056_']" ).attr('disabled','disabled');
		$( "input[id^='direccionContacto056_']" ).attr('disabled','disabled');
		$('#tipoDocumento056_detail_table').rup_combo('disable');
		$('#numPalIzo056_detail_table').attr('disabled','disabled');
	}
	$('#titulo056_detail_table').attr('disabled','disabled');
	if(!$("#indVisible056_detail_table_m")[0].disabled){
		$("#indVisible056_detail_table_m").bootstrapSwitch('toggleDisabled',true,true);
	}
}

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function crearClaseDocumentoCombo(){
	$('#claseDocumento056_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/clasificaciondocumentos/tipoexpediente/"+tipoExp,
		sourceParam :{
			label: $.rup.lang === 'es' ? "descEs075":"descEu075", 
					value: "id075",
					style:"css"
		}
		,width: "170"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#claseDocumento056_detail_table-menu').width(jQuery('#claseDocumento056_detail_table-button').width());
		}
		,onLoadSuccess: function() {
			if (tipoExp === 'I'){
				$('#claseDocumento056_detail_table').rup_combo('select','');
				$('#claseDocumento056_detail_table').rules("remove", "required");
			}
		}
		,change: function(){
			switch (this.value){
				case '1': //Traducción, esto mas lo del 1
					if(capaItzulpena !== ''){
						$('#capaFicheroItzulpena').html(capaItzulpena);
						capaItzulpena = '';
					}
					if(capaItzulpenaContacto !== ''){
						$('#capaFicheroItzulpenaContacto').prepend(capaItzulpenaContacto);	
						capaItzulpenaContacto = '';
					}
					if(capaBerrikusketa === ''){
						capaBerrikusketa = $("#capaFicheroBerrikusketaDetach").detach();
					}
					if(capaVersionesApoyo !== ''){
						$('#capaFicheroItzulpenaContacto').append(capaVersionesApoyo);	
						capaVersionesApoyo = '';
					}
					$('#capaPidButton_0').hide();
					$('#capaPidButton_0').hide();
					$('#capaDivPid_0').hide();
					$('#capaFichero_0').show();
					$('#capaFichero_0').removeClass("noBorder");
					$('#capaFichero_1').hide();
					$('#legendFichero_0').hide();
					$('#legendFichero_1').hide();
					$('#capaDetTipo').show();
					if (origen==='C') $('#capaNumPalSolic').show();
					else $('#capaNumPalSolic').hide();
					$('.capaDetContacto').show();
					$('#reqEncriptado').val(expedienteConfidencial);
					break;
				case '2':
					if(capaItzulpena === ''){
						capaItzulpena = $("#capaFicheroItzulpenaDetach").detach();
					}
					if(capaItzulpenaContacto === ''){
						capaItzulpenaContacto = $("#capaFicheroItzulpenaContactoDetach").detach();
					}
					if(capaBerrikusketa !== ''){
						$('#capaFicheroBerrikusketa').html(capaBerrikusketa);	
						capaBerrikusketa = '';
					}
					if(capaVersionesApoyo === ''){
						capaVersionesApoyo = $("#versionesApoyo").detach();
					}
					$('#capaFichero_0').show();
					$('#capaFichero_0').removeClass("noBorder");
					$('#capaFichero_1').show();
					$('#legendFichero_0').show();
					$('#legendFichero_1').show();
					$('#capaPidButton_0').hide();
					$('#capaDivPid_0').hide();
					$('#capaFicheroShow').hide();
					$('#capaDetTipo').show();
					if (origen==='C') $('#capaNumPalSolic').show();
					else $('#capaNumPalSolic').hide();
					$('.capaDetContacto').show();
					$('#reqEncriptado').val(expedienteConfidencial);		
					break;
				default: //Apoyo
					if(capaItzulpena !== ''){
						$('#capaFicheroItzulpena').html(capaItzulpena);
						capaItzulpena = '';
					}
					if(capaItzulpenaContacto === ''){
						capaItzulpenaContacto = $("#capaFicheroItzulpenaContactoDetach").detach();
					}
					if(capaBerrikusketa === ''){
						capaBerrikusketa = $("#capaFicheroBerrikusketaDetach").detach();
					}
					if(capaVersionesApoyo !== ''){
						$('#capaFicheroItzulpenaContacto').append(capaVersionesApoyo);	
						capaVersionesApoyo = '';
					}
					$('#capaFichero_0').hide();
					$('#capaFichero_1').hide();
					$('#legendFichero_0').hide();
					$('#capaFichero_0').addClass("noBorder");
					$('#legendFichero_1').hide();
					$('#capaFicheroShow').hide();
					$('#capaDetTipo').hide();
					$('#capaNumPalSolic').hide();
					$('.capaDetContacto').hide();
					$('#tipoDocumento056_detail_table').rup_combo('select','');
					tipoDoc = '';
					$('#capaFichero_1 input').val('');
					$('#numPalIzo056_detail_table').val('');
					$('#numPalSolic056_detail_table').val('');
					$('#reqEncriptado').val("N");		
					break;
			}
			
			$("[id^='pidButton']").unbind("click");
			$("[id^='pidButton']").click(function(event) {
				if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
				else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
				else $("#idBotonUpload").val("0");
				$('#fichero').click();
			});
			
			if ((expedienteConfidencial ==='S') &&(this.value < 3)){
				$('#txtEncriptado').show();
			}else{
				$('#txtEncriptado').hide();
			}
			
		}
	});
}

function crearTipoDocumentoCombo(){
	if ( !$('#tipoDocumento056_detail_table-menu').length ){
		$('#tipoDocumento056_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta",
			sourceParam :{
				label: $.rup.lang === 'es' ? "descConRelevanciaEs":"descConRelevanciaEu", 
				value: "id",
				style:"css"
			}
			,blank: ""
			,width: "250"
			,ordered: false	
			,rowStriping: true
			,open: function(){
		        jQuery('#tipoDocumento056_detail_table-menu').width(jQuery('#tipoDocumento056_detail_table-button').width());
		    }
			,onLoadSuccess: function() {
				crearClaseDocumentoCombo();
			}
		});
	}
}
function crearCheckboxCorrecto(){
	jQuery('#checkSubsCorrecta')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
}
/**
 * ver documento en modal en modo consulta
 * @param codFila
 * @returns
 */
function verDocumento(elIdDoc){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	$("#documentosexpediente_detail_div").rup_dialog("open");
	$("#documentosexpediente_detail_form").rup_form('clearForm', true);
	clearValidation('#documentosexpediente_detail_form');		
	$("#ejecutarTareaDialog_feedback").rup_feedback("close");
	
	$('#claseDocumento056_detail_table').rup_combo("disable");
	$("#nombreFicheroInfo_0").rules("remove", "required");
	$("#nombreFicheroInfo_1").rules("remove", "required");
	$("#titulo056_detail_table").rules("add", "required");
	
	//Carga la info de la T56
	$.ajax({
	   	 type: 'GET'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/'+parseInt(elIdDoc)+'/'+origen
	 	 ,dataType: 'json' 	 	 	
	 	 ,cache: false
	 	 ,async: false
	   	 ,success:function(data){
	   		if ((data !== null)&&(data.numExp!=null)){    //existe el registro		
	   			var enlace = '<label data-id="'+data.ficheros[0].idDocInsertado+'">'+data.ficheros[0].nombre+' ('+data.ficheros[0].extension+' , '+conversionKB(data.ficheros[0].tamano)+' KB.) ';
	   			enlace+= (data.ficheros[0].encriptado ==='S')?' <span class="ico-ficha-encriptado" title="'+labelEncrip+'"><i class="fa fa-lock" aria-hidden="true"></i></span>':' <span class="ico-ficha-encriptado" title="'+labelNoEncrip+'"><i class="fa fa-unlock" aria-hidden="true"></i></span></label>';
	   			$('#enlaceDescargaDetalle_0').html(enlace);

		   		$('#idDoc056_detail_table').val(data.ficheros[0].idDocInsertado);
		   		$('#anyo056_detail_table').val(data.anyo);
		   		$('#numExp056_detail_table').val(data.numExp);
		   		$('#titulo056_detail_table').val(data.titulo);
		   		$('#numPalSolic056_detail_table').val(data.numPalSolic);
		   		$('#numPalIzo056_detail_table').val(data.numPalIzo);
		   		
		   		$('#claseDocumento056_detail_table').rup_combo('select',data.claseDocumento+"");
		   		$('#tipoDocumento056_detail_table').rup_combo('select',data.tipoDocumento+"");
		   		if(data.indVisible === 'S'){
					$('#indVisible056_detail_table_m').bootstrapSwitch('setState', true);
				}else{
					$('#indVisible056_detail_table_m').bootstrapSwitch('setState', false);
				}
		   		tipoDoc = data.tipoDocumento+"";
		   		$('#oidFichero056_0').val(data.ficheros[0].oid);		
		   		$('#nombre_0').val(data.ficheros[0].nombre);	
		   		$('#extensionDoc056_0').val(data.ficheros[0].extension);		   		
		   		$('#tamanoDoc056_0').val(data.ficheros[0].tamano);
		   		$('#contentType056_0').val(data.ficheros[0].contentType);
		   		$('#indEncriptado056_0').val(data.ficheros[0].encriptado);
		   		$('#idDocRel056_0').val(data.ficheros[0].idDocRel);		   		
		   		$('#idDocVersion056_0').val(data.ficheros[0].idDocVersion);
		   		$('#fechaAlta056_0').val(data.ficheros[0].fechaAlta);		   		
		   		$('#dniAlta056_0').val(data.ficheros[0].dniAlta);		 
		   		$('#emailContacto056_0').val(data.ficheros[0].contacto.email);		   		
		   		$('#personaContacto056_0').val(data.ficheros[0].contacto.persona);		   		
		   		$('#telefonoContacto056_0').val(data.ficheros[0].contacto.telefono);	
		   		$('#direccionContacto056_0').val(data.ficheros[0].contacto.direccion);		   		
		   		$('#entidadContacto056_0').val(data.ficheros[0].contacto.entidad);	
		   		$('#idDocInsertado_0').val(data.ficheros[0].idDocInsertado);	
		   		if ($('#oidFichero056_0').val() !== ''){
		   			$('#pidButton_0').text($.rup.i18nParse($.rup.i18n.app,"label.modifFichero"));
		   		}else{
		   			$('#pidButton_0').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
		   		}
		   		$('#capaVersiones_0').html('');
		   		
		   		if (data.ficheros.length > 1){
		   			var enlace2 = '<label  data-id="'+data.ficheros[1].idDocInsertado+'">'+data.ficheros[1].nombre+' ('+data.ficheros[1].extension+' , '+conversionKB(data.ficheros[1].tamano)+' KB.) ';
		   			enlace2+= (data.ficheros[1].encriptado ==='S')?' <span class="ico-ficha-encriptado" title="'+labelEncrip+'"><i class="fa fa-lock" aria-hidden="true"></i></span>':' <span class="ico-ficha-encriptado" title="'+labelNoEncrip+'"><i class="fa fa-unlock" aria-hidden="true"></i></span></label>';
		   			$('#enlaceDescargaDetalle_1').html(enlace2);

			   		$('#oidFichero056_1').val(data.ficheros[1].oid);		   		
			   		$('#nombre_1').val(data.ficheros[1].nombre);		   		
			   		$('#extensionDoc056_1').val(data.ficheros[1].extension);		   		
			   		$('#tamanoDoc056_1').val(data.ficheros[1].tamano);
			   		$('#contentType056_1').val(data.ficheros[1].contentType);
			   		$('#indEncriptado056_1').val(data.ficheros[1].encriptado);
			   		$('#idDocRel056_1').val(data.ficheros[1].idDocRel);		   		
			   		$('#idDocVersion056_1').val(data.ficheros[1].idDocVersion);
			   		$('#fechaAlta056_1').val(data.ficheros[1].fechaAlta);		   		
			   		$('#dniAlta056_1').val(data.ficheros[1].dniAlta);		 
			   		$('#emailContacto056_1').val(data.ficheros[1].contacto.email);		   		
			   		$('#personaContacto056_1').val(data.ficheros[1].contacto.persona);		   		
			   		$('#telefonoContacto056_1').val(data.ficheros[1].contacto.telefono);	
			   		$('#direccionContacto056_1').val(data.ficheros[1].contacto.direccion);		   		
			   		$('#entidadContacto056_1').val(data.ficheros[1].contacto.entidad);	
			   		$('#idDocInsertado_1').val(data.ficheros[1].idDocInsertado);
			   		if ($('#oidFichero056_1').val() !== ''){
			   			$('#pidButton_1').text($.rup.i18nParse($.rup.i18n.app,"label.modifFichero"));
			   		}else{
			   			$('#pidButton_1').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
			   		}
			   		$('#capaVersiones_1').html('');
		   		}
		   		asociarEventosBotonesTabla();
		   		initDocumentoForm = $("#documentosexpediente_detail_form").rup_form("formSerialize");
	   		}else{
	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
	   		}	
	   	 }
  	 	,error: function(){	  	 		
  	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	   	 }
	 });
	deshabilitarDialogVerDocumento();
	 desbloquearPantalla();
}

/**
 * descargar documento del pid
 * @param elIdDoc
 * @returns
 */
function descargarDocumento(elIdDoc){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/1/'+elIdDoc);
}

function obtenerDatosExpedienteSubsanacion(){
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/obtenerDatosTareaEstudioSubsanacion/'+anyoExpediente+'/'+idExpediente+'/'+idTarea
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,async: false
	     ,cache: false
	   	 ,success:function(data){
	   		 if(data){
	   			cambios = false;
	   			if(data.subsanacion.fechaReq && undefined!=data.subsanacion.fechaReq){
	   				if(data.subsanacion.horaReq){
	   					$('#fechaHoraRegistroReq')[0].textContent = data.subsanacion.fechaReq + ' ' + data.subsanacion.horaReq;
	   				}else{
	   					$('#fechaHoraRegistroReq')[0].textContent = data.subsanacion.fechaReq;
	   				}
	   			}
	   			if(data.subsanacion.fechaLimite && undefined!=data.subsanacion.fechaLimite){
	   				if(data.subsanacion.horaLimite){
	   					$('#fechaHoraLimiteSubs')[0].textContent = data.subsanacion.fechaLimite + ' ' + data.subsanacion.horaLimite;
	   				}else{
	   					$('#fechaHoraLimiteSubs')[0].textContent = data.subsanacion.fechaLimite;
	   				}
	   			}
	   			$('#detalleReq').val(data.subsanacion.detalle);
   				$('#detalleReq').attr('readonly', 'readonly');
   	   			$('#detalleReq').attr("disabled", "true");
	   			if(data.subsanacion.fechaSubsanacion && 'undefined'.localeCompare(data.subsanacion.fechaSubsanacion)){
	   				if(data.subsanacion.horaSubsanacion){
	   					$('#fechaHoraSubsanacion')[0].textContent = data.subsanacion.fechaSubsanacion + ' ' + data.subsanacion.horaSubsanacion;
	   				}else{
	   					$('#fechaHoraSubsanacion')[0].textContent = data.subsanacion.fechaSubsanacion;
	   				}
	   				
	   			}
	   			$('#idReq').val(data.subsanacion.id);
	   			//si entra aqui, la tarea ya esta ejecutada, por lo tanto estamos en modo consulta y
	   			//cargamos los valores de si es correcta o no la subsanacion
	   			if(1 != data.subsanacion.estado){
	   				if(2 == data.subsanacion.estado){
	   					//aceptada
	   					$('#checkSubsCorrecta').bootstrapSwitch('setState', true);
	   					$('#motivoSubsIncorrecta')[0].textContent = "";
	   					$('#motivoSubsIncorrecta')[0].disabled=true;
	   				}else if(3 == data.subsanacion.estado){
	   				//rechazada
	   					$('#checkSubsCorrecta').bootstrapSwitch('setState', false);
	   					$('#motivoSubsIncorrecta')[0].textContent = data.subsanacion.observRechazo;
	   				}
	   			}
	   			if(!ejecutarTareaConsulta){
	   				obsOblig = true;
	   				escucharCambiosEnPantalla();
	   			}
	   			listaDocs = data.documentosExpediente;
	   			cargarDocumentosJSONaPantalla();
	   		}
	   	 }
 	 	,error: function(){
 	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaEstudiarSubsDocuErrorObteniendoDatos, "error");
	   	 }
	 });		
}

function asociarEventosBotonesTabla(){
	$( "a.verDocumento").unbind( "click" );
	$( "a.descargarDoc").unbind( "click" );
	
	$('a.verDocumento').click(function(event){
		event.preventDefault();	
		var codFila =  $(this).data("id");
		verDocumento(codFila);
	});
	$('a.descargarDoc').click(function(event){
		event.preventDefault();	
		var elIdDoc =  $(this).data("id");
		descargarDocumento(elIdDoc);
	});
}

/**
 * anyade los documentos del expediente en a lista
 * @param data
 * @returns
 */
function anadeFilaJSON( data ){
		var codFila = data.idDoc+"_"+data.estado;
		var laFila;
		laFila = '<div class="row documento-all" id="filaDoc'+codFila+'" style="margin-bottom: 10px;">';
		
		laFila+= '<div class="col-md-11 documento-first"><p class="document-tit aa79bPEnDoc">';
		switch (''+data.claseDocumento){
			case "1": laFila+= traduccion; break;
			case "2": laFila+= revision; break;
			case "3": laFila+= apoyo; break;
			default: laFila+= interpretacion; break;
		}
		laFila+= '</p><a href="#" class="document-eusk descargarDoc" data-id="'+data.ficheros[0].idDocInsertado+'">';
		 
		if( !isEmpty(data.ficheros[1])){
			laFila+= labelDocumentoOrigen+' - ';
		}
		if( !isEmpty(data.titulo)){
			laFila+= data.titulo;
		}else{
			laFila+= data.ficheros[0].nombre;
		}
		laFila+= ' <span class="documento-archivo">('+labelFichero+' '+ data.ficheros[0].extension+' , '+data.ficheros[0].tamano+' KB.) </span>';
		laFila+= ' <span class="ico-ficha-encriptado" title="'+  ((data.ficheros[0].encriptado==='S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((data.ficheros[0].encriptado ==='S')?"":"un") +'lock" aria-hidden="true"></i></span></a>';
		
		if( !isEmpty(data.ficheros[1])){		
			laFila+= '<a href="#" class="document-cast descargarDoc" data-id="'+data.ficheros[1].idDocInsertado+'">'+labelDocumentoRevisar+' - ';
			
			if( !isEmpty(data.titulo)){
				laFila+= data.titulo;
			}else{
				laFila+= data.ficheros[1].nombre;
			}
			laFila+= ' <span class="documento-archivo">('+labelFichero+' '+data.ficheros[1].extension+' , '+data.ficheros[1].tamano+' KB.) </span>';
			//ENCRIPTADO
			laFila+= ' <span class="ico-ficha-encriptado" title="'+((data.ficheros[1].encriptado==='S')?labelEncrip:labelNoEncrip)+'"><i class="fa fa-'+((data.ficheros[1].encriptado==='S')?"":"un")+'lock" aria-hidden="true"></i></span></a>';
		}
		if ((data.claseDocumento == "1")|| (data.claseDocumento == "2")){
			laFila+= '<p class="document-tipo aa79bPEnDoc">'+labelTipo+": "+ data.tipoDocumentoDesc+'</p><p class="document-izo aa79bPEnDoc">'+labelNumPal+": "+data.numPalSolic+'</p>';;
		}
		laFila+= '</div>';
		laFila+= '<div class="col-md-1 documento-second"><div><a class="verDocumento"  href="#" data-id="'+codFila+'" ><span class="ico-ficha-right"><i class="fa fa-search" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelVer+'</span></a></div></div>';
		
		laFila+= '</div>';
		var eltoFila = $(laFila);
		eltoFila.appendTo('#capaDocumentosDetalle');
		asociarEventosBotonesTabla();
	}

/**
 * carga los documentos del expediente
 * @returns
 */
function cargarDocumentosJSONaPantalla(){
	$('#capaDocumentosDetalle').html('');
	listaDocs.forEach(function(unDoc) {
		if (unDoc.estado != 'E'){
			anadeFilaJSON(unDoc);
		}
	});
}

jQuery(function($) {
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}
	$("#ejecutarTareaDialog_form").rup_form({
		url: "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		feedback: $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false,
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
	
	$('#idTarea_filter').val(idTarea);
	
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
			},
			{
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
	
	idiomaOrigen = '';
	if (idiomaDestino == "1"){
		idiomaOrigen = 2;
		$('#idiomaDest').val(labelEU);
	}else{
		idiomaOrigen = 1;
		$('#idiomaDest').val(labelES);
	}
	
	obtenerDatosExpedienteSubsanacion();
	crearTipoDocumentoCombo();
	crearCheckboxCorrecto();
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#documentosexpediente_detail_div").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: true,
	    width: 950,
	    title: $.rup.i18n.app.label.infoDocumento
	});

	$('#documentosexpediente_detail_feedback').rup_feedback(opcionesFeedbacks);
	jQuery('#indVisible056_detail_table_m').each(function(index, element){
  		jQuery(element)
  		.bootstrapSwitch()
  		.bootstrapSwitch('setSizeClass', 'switch-small')
  		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
  		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
  		});
	
	
	if($('#checkSubsCorrecta').is(':checked')){
		$('#checkSubsCorrecta').bootstrapSwitch('setState', true);
	}else{
		$('#checkSubsCorrecta').bootstrapSwitch('setState', false);
	}
});