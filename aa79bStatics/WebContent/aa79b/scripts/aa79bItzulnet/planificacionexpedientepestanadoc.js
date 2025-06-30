var initDocumentoForm;
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};
var idrelevancia;
//El tipo de expediente
var tipoExpediente;
var expedienteConfidencial = 'N';
var tipoDoc = '';
var capaItzulpena = '';
var capaItzulpenaContacto = '';
var capaBerrikusketa = '';


function deshabilitarYCtrolCambios(){
	if (tipoExpediente !== 'I'){
		deshabilitarControlesCapa('datosExpedienteTradRev_div');
		datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
	}else{
		deshabilitarControlesCapa('datosInterpretacion_div');
		datosFormularioDoc = $("#datosInterpretacion_form").serialize();		
	}
}

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

// v.3.9.40 documentos de trabajo pueden llevar tramos de concor
// si se rellena uno, se requieren los tres campos
function anyadirValidacionParaDocsTipoTrabajo(){
	if($("#claseDocumento056_detail_table").rup_combo("getRupValue") == "4"
		&& ($("#numPalConcor084DocTrabajo").val() != "" 
					|| $("#numPalConcor8594DocTrabajo").val() != ""
						|| $("#numPalConcor9599DocTrabajo").val() != ""
					|| $("#numPalConcor100DocTrabajo").val() != "")){
		$('#numPalConcor084DocTrabajo').rules("add", "required");
		$('#numPalConcor8594DocTrabajo').rules("add", "required");
		$('#numPalConcor9599DocTrabajo').rules("add", "required");
		$('#numPalConcor100DocTrabajo').rules("add", "required");
	}else{
		$('#numPalConcor084DocTrabajo').rules("remove", "required");
		$('#numPalConcor8594DocTrabajo').rules("remove", "required");
		$('#numPalConcor9599DocTrabajo').rules("remove", "required");
		$('#numPalConcor100DocTrabajo').rules("remove", "required");
		$("#numPalConcor084DocTrabajo").removeClass("no-padding-left error")
		$("#numPalConcor8594DocTrabajo").removeClass("no-padding-left error")
		$("#numPalConcor9599DocTrabajo").removeClass("no-padding-left error")
		$("#numPalConcor100DocTrabajo").removeClass("no-padding-left error")
	}
	return true;
}

jQuery(function($){
	
	//A lo bruto, elimino TODO el contenido de la otra pestaña
	$('#capaPestanaCompletaAlta').remove();
	eliminarMensajes();
	datosFormulario = '';
	
	$('#capaFechaFinalProp').hide();
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	$('#fechaFinalIzo_exp').rup_date();
	$('#fechaLimite').rup_date();
	$('#negocNuevaFechaLimite').rup_date();
	
	$('#nuevaFechaIZO').rup_date();
	$('#nuevaHoraIZO').prop( {"readonly": true, "disabled": true });
	$('#nuevaFechaIZO').rup_date("disable");

	$('#datosExpedienteTradRev_feedback').rup_feedback(opcionesFeedbacks);
	$('#documentosexpediente_detail_feedback').rup_feedback(opcionesFeedbacks);
	$('#pestanyaDocPlanif_feedback').rup_feedback(opcionesFeedbacks);
	$('#datosInterpretacion_feedback').rup_feedback(opcionesFeedbacks);
	
	$('#fechaFinalIzo_button_modif').hide();
	if(esPresupuestoVisibleParaUsuario){
		$('#notaPptoVisible').show();
	}
	
	if (tipoExpediente === datosExp.tipoExp.traduccion && 'none'.localeCompare($("#notaPptoVisible").css('display'))==0 && tieneTareasEjecutadas){
		$('#notaTareasEjecutadas').show();
	}
	
	function deshabilitarTramosConcor(){
		$('input[id^="numPalConcor"]').prop( "readonly", true );
		$('input[id^="numPalConcor"]').prop( "disabled", true );
	}
	function cargaDatosExpTradRev(){
		//Carga la info de la T53
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/planificacion/'+anyoExpediente+"/"+idExpediente
		 	 ,dataType: 'json' 	 	 	
		 	 ,cache: false
		 	 ,async: false
		   	 ,success:function(data){
		   		if (data !== null){    //existe el registro		
			   		$('#anyo_exp').val(anyoExpediente);
			   		$('#numExp_exp').val(idExpediente);
			   		$('#numTotalPalIzo_exp').val(data.numTotalPalIzo);
			   		$('#numTotalPalSolic_exp').val(data.numTotalPalSolic);
			   		$('#fechaFinalIzo_exp').val(data.fechaFinalIzo);
			   		$('#horaFinalIzo_exp').val(data.horaFinalIzo);		   		
			   		idrelevancia = data.idRelevancia;
			   		if ($('#idRelevancia_exp').val() !== ''){
			   			$('#idRelevancia_exp').rup_combo("select", ''+idrelevancia);
			   		}
			   		
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
			   		if ( data.numTotalPalIzo < $('#palPresupuesto').val() || tieneTareasEjecutadas ){ 	// cambiar a palPresupuesto
			   			var parentIndPresupuesto = $('#indPresupuesto_exp').parent().parent();
			   			parentIndPresupuesto.addClass("disabled");
						$('#indPresupuesto_exp').attr('disabled','disabled');
			   		}else{
			   			var parentIndPresupuesto = $('#indPresupuesto_exp').parent().parent();
			   			parentIndPresupuesto.removeClass("disabled");
						$('#indPresupuesto_exp').removeAttr('disabled');
			   		}
			   		expedienteConfidencial = data.indConfidencial;
			   		$('#reqEncriptado').val(expedienteConfidencial);
			   		crearCmbTipoDocumento();
			   		if (!isEmpty(data.datosFacturacionExpediente)){
			   			$('#numTotalPalFacturar').val(data.datosFacturacionExpediente.numTotalPalFacturar);
			   			if ( !isEmpty(data.datosFacturacionExpediente.numPalConcor084) || !isEmpty(data.datosFacturacionExpediente.numPalConcor8594) || !isEmpty(data.datosFacturacionExpediente.numPalConcor95100) ){
			   				$('#numPalConcor084').val(data.datosFacturacionExpediente.numPalConcor084);
			   				$('#numPalConcor8594').val(data.datosFacturacionExpediente.numPalConcor8594);
			   				$('#numPalConcor95100').val(data.datosFacturacionExpediente.numPalConcor95100);
			   			}else{
			   				deshabilitarTramosConcor();
			   			}
			   		}else{
			   			$('#numTotalPalFacturar').val(data.numTotalPalIzo);
			   			deshabilitarTramosConcor();
			   		}
			   		if (!isEmpty(data.aceptacionPresupuesto)){
			   			$('#fechaLimite').val(data.aceptacionPresupuesto.fechaLimite);
				   		$('#horaLimite').val(data.aceptacionPresupuesto.horaLimite);		   	
				   		if (!isEmpty(data.aceptacionPresupuesto.estado)){
				   			$('#estadoPpto').rup_combo("select", ''+data.aceptacionPresupuesto.estado);
				   			if (data.aceptacionPresupuesto.estado != "1"){
				   				$('#horaLimite').prop( {"readonly": true, "disabled": true });
					   			$('#fechaLimite').rup_date("disable");
					   			$('#estadoPpto').rup_combo("disable");
				   			}
				   		}
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
			   		if (esPresupuestoVisibleParaUsuario){
			   			$('#numTotalPalIzo_exp').prop( {"readonly": true, "disabled": true });
			   			$('#idRelevancia_exp').rup_combo("disable");
			   			
			   			var capaPadreSitch = $('#indFacturable_exp').parent().parent();
			   			capaPadreSitch.addClass("disabled");
			   			$('#indFacturable_exp').prop( {"disabled": true });
			   			
			   			capaPadreSitch = $('#indUrgente_exp').parent().parent();
			   			capaPadreSitch.addClass("disabled");
			   			$('#indUrgente_exp').prop( {"disabled": true });
			   			
			   			capaPadreSitch = $('#indPresupuesto_exp').parent().parent();
			   			capaPadreSitch.addClass("disabled");
			   			$('#indPresupuesto_exp').prop( {"disabled": true });
			   			
			   			capaPadreSitch = $('#indDificil_exp').parent().parent();
			   			capaPadreSitch.addClass("disabled");
			   			$('#indDificil_exp').prop( {"disabled": true });
			   			
			   			$('#horaFinalIzo_exp').prop( {"readonly": true, "disabled": true });
			   			$('#fechaFinalIzo_exp').rup_date("disable");
			   			
			   			$('#numTotalPalFacturar').prop( {"readonly": true, "disabled": true });
			   			$('#numPalConcor084').prop( {"readonly": true, "disabled": true });
			   			$('#numPalConcor8594').prop( {"readonly": true, "disabled": true });
			   			$('#numPalConcor95100').prop( {"readonly": true, "disabled": true });
			   			$('.ocultarSiPptoVisible').hide();
			   		}
			   		if (esTradosConRequerimiento){
			   			$('#capaAceptacionPresupesto').show();
			   		}
			   		datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
			   		
			   		comprobarFormularioDoc = true;
		   		}else{
		   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
		   		}	
		   	 }
		 	,error: function(){	  	 		
		 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		   	 }
		 });
	}
	
	
	function cargaDatosEstadoPptoInterpretacion(){
		//leer info
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/expedienteinterpretacion/planificacion/datosEstadoPresupuesto/'+anyoExpediente+"/"+idExpediente
		 	 ,dataType: 'json' 	 	 	
		 	 ,cache: false
		 	 ,async: false
		   	 ,success:function(aceptacionPresupuesto){
		   		 //cargar info pantalla
	   		 	if (aceptacionPresupuesto !== null && !isEmpty(aceptacionPresupuesto)){    //existe el registro		
					$('#fechaLimiteAceptacionInterp').val(aceptacionPresupuesto.fechaLimite);
			   		$('#horaLimiteAceptacionInterp').val(aceptacionPresupuesto.horaLimite);		   	
			   		if (!isEmpty(aceptacionPresupuesto.estado)){
			   			$('#estadoPptoAceptacionInterp').rup_combo("select", ''+aceptacionPresupuesto.estado);
			   			if (aceptacionPresupuesto.estado != "1"){
			   				$('#horaLimiteAceptacionInterp').prop( {"readonly": true, "disabled": true });
				   			$('#fechaLimiteAceptacionInterp').rup_date("disable");
				   			$('#estadoPptoAceptacionInterp').rup_combo("disable");
			   			}
			   		}
			   	}else{
		   			$('#datosInterpretacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
		   		}	
		   	 }
		 	,error: function(){	  	 		
		 		$('#datosInterpretacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		   	 }
		 });
	}
	
	
	
	//Para obtener el tipo de expediente T51
	$.ajax({
	   	 type: 'GET'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/'+anyoExpediente+"/"+idExpediente
	 	 ,dataType: 'json' 	 	 	
	 	 ,cache: false
	 	 ,async: false
	   	 ,success:function(data){
	   		if ((data !== null)&&(data.idTipoExpediente!=null)){			   			
	   			tipoExpediente = data.idTipoExpediente;		   		
	   		}	else $('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
	   	 }
  	 	,error: function(){	  	 		
  	 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	   	 }
	 });
	
		
	function ocultaFila(elIdDoc){
		$('#filaDoc'+elIdDoc).fadeOut(2000, function(elIdDoc){eliminaFila(elIdDoc)} );
	}
	function eliminaFila(elIdDoc){
		$('#filaDoc'+elIdDoc).remove();
	}
	
	function anadeFila( existeFila, elIdInsertado, elIdDoc2 ){
		var laFila;
		if (!existeFila){
			laFila = '<div class="row documento-all" id="filaDoc'+elIdInsertado+'">';
		}
		laFila = '<div class="col-md-9 documento-first"><p class="document-tit">';
		var laFila = '<div class="row documento-all" id="filaDoc'+elIdInsertado+'"><div class="col-md-9 documento-first"><p class="document-tit">' + labelTipo + ': ';
		laFila+= $('#claseDocumento056_detail_table').rup_combo('label');
		laFila+= '</p>';
		if ($('#claseDocumento056_detail_table').rup_combo('value') === "2"){
			laFila+= '<div  class="displayAsTable" style="padding-top: 3px;"><p class="document-tipo" style="display: table-cell; padding-right: 5px;">'+labelDocumentoOrigen+' - '+'</p>';
		}	
		laFila+= '<p class="document-fileAndIcon"><a href="#" class="document-eusk descargarDoc iconSameLine" data-id="'+elIdInsertado+'">';
		laFila+= $('#nombreFicheroInfo_0').val();
		laFila+= ' <span class="documento-archivo">('+ conversionKB($('#tamanoDoc056_0').val())+' KB.) </span></a>';
		//ENCRIPTADO
		laFila+= ' <span class="ico-ficha-encriptado" title="'+ (($('#indEncriptado056_0').val()==='S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ (($('#indEncriptado056_0').val()==='S')?"":"un") +'lock" aria-hidden="true"></i></span></p>';
		if ($('#claseDocumento056_detail_table').rup_combo('value') === "2"){
			laFila+= '</div>';
		}
		if (elIdDoc2!=null){
			laFila+= '<div class="displayAsTable" style="padding-top: 3px;"><p class="document-tipo" style="display: table-cell; padding-right: 5px;">'+labelDocumentoRevisar+' - '+'</p>';
			laFila+= '<p class="document-fileAndIcon"><a href="#" class="document-cast descargarDoc iconSameLine" data-id="'+elIdDoc2+'">';
			laFila+=$('#nombre_1').val();
			laFila+= ' <span class="documento-archivo">('+conversionKB($('#tamanoDoc056_1').val())+' KB.) </span></a>';
			//ENCRIPTADO
			laFila+= ' <span class="ico-ficha-encriptado" title="'+(($('#indEncriptado056_1').val()==='S')?labelEncrip:labelNoEncrip)+'"><i class="fa fa-'+(($('#indEncriptado056_1').val()==='S')?"":"un")+'lock" aria-hidden="true"></i></span></p></div>';
		}
		if (($('#claseDocumento056_detail_table').rup_combo('value') === "1")|| ($('#claseDocumento056_detail_table').rup_combo('value') === "2")){
			if($('#titulo056_detail_table').val()!=='' &&
				$('#titulo056_detail_table').val() != ($('#nombreFicheroInfo_0').val().substring( 0, $('#nombreFicheroInfo_0').val().lastIndexOf (".") ))){
				laFila+= '<p class="document-tipo"> '+labelTituloAlt+": ";
				laFila+= $('#titulo056_detail_table').val()+'</p>';
			}
			laFila+= '<p class="document-tipo">'+labelTipo+": ";
			if (($('#tipoDocumento056_detail_table').rup_combo('label')).indexOf("(") !== -1) {
				laFila+= ($('#tipoDocumento056_detail_table').rup_combo('label')).substring(0,($('#tipoDocumento056_detail_table').rup_combo('label')).indexOf("("));
			}else{
				laFila+= $('#tipoDocumento056_detail_table').rup_combo('label');
			}
			laFila+= '</p><p class="document-izo">'+labelNumPalIzo+": "+$('#numPalIzo056_detail_table').val()+'</p>';
			
			if ($('#numPalSolic056_detail_table').val() != '' ){
				laFila+= '<p class="document-izo">'+labelNumPalSolic+": "+$('#numPalSolic056_detail_table').val()+'</p>';
			} else{
				laFila+= '<p class="document-izo">'+labelNumPalSolic+": "+$('#numPalIzo056_detail_table').val()+'</p>';
			}
			
		}
		if ($('#claseDocumento056_detail_table').rup_combo('value') === "4"){
			laFila+= '<p class="document-izo">'+labelNumPalIzo+": "+$('#numPalIzo056_detail_table').val()+'</p>';
		}
		laFila+= '</div><div class="col-md-1  documento-second"><span class="ico-ficha-right">';
		laFila+= '<i class="fa fa-eye'+(($('#indVisible056_detail_table').bootstrapSwitch('state')===true)?"":"-slash")+'" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelVisible+'</span>';
		laFila+= '</div>';
		
		
		laFila+= '<div class="col-md-1 documento-second"><div style="border-left: 1px solid #d9dce0;"><a class="verDocumento"  href="#" data-id="'+elIdInsertado+'" ><span class="ico-ficha-right"><i class="fa fa-search" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelVer+'</span></a></div></div>';
		if ( !existeFila || !esPresupuestoVisibleParaUsuario || $('#claseDocumento056_detail_table').rup_combo('value') === "3"){
			laFila+= '<div class="col-md-1 documento-second"><div style="border-left: 1px solid #d9dce0;"><a class="eliminarDocumento"  href="#" data-id="'+elIdInsertado+'"><span class="ico-ficha-right"><i class="fa fa-times" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelEliminar+'</span></a></div></div>';
		}
		
		
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
	
	

	function verDocumento(elIdDoc){
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		$("#documentosexpediente_detail_div").rup_dialog("open");
		$("#documentosexpediente_detail_form").rup_form('clearForm', true);
		clearValidation('#documentosexpediente_detail_form');		
		$("#documentosexpediente_detail_feedback").rup_feedback("close");
		
		$('#claseDocumento056_detail_table').rup_combo("disable");
		$("#nombreFicheroInfo_0").rules("remove", "required");
		$("#nombreFicheroInfo_1").rules("remove", "required");
		$("#titulo056_detail_table").rules("add", "required");
		
		//Carga la info de la T56
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/'+elIdDoc+'/'+origen
		 	 ,dataType: 'json' 	 	 	
		 	 ,cache: false
		 	 ,async: false
		   	 ,success:function(data){
		   		if ((data !== null)&&(data.numExp!=null)){    
		   			//existe el registro		
		   			var enlace = '<a href="#" class="descargarDoc" data-id="'+data.ficheros[0].idDocInsertado+'">'+data.ficheros[0].nombre+'</a> ('+conversionKB(data.ficheros[0].tamano)+' KB.) ';
		   			enlace+= (data.ficheros[0].encriptado ==='S')?' <span class="ico-ficha-encriptado" title="'+labelEncrip+'"><i class="fa fa-lock" aria-hidden="true"></i></span>':' <span class="ico-ficha-encriptado" title="'+labelNoEncrip+'"><i class="fa fa-unlock" aria-hidden="true"></i></span>';
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
						$('#indVisible056_detail_table').bootstrapSwitch('setState', true);
					}else{
						$('#indVisible056_detail_table').bootstrapSwitch('setState', false);
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
					// V3.9.40
					$('#numPalConcor084DocTrabajo').val(data.numPalConcor084);
					$('#numPalConcor8594DocTrabajo').val(data.numPalConcor8594);
					$('#numPalConcor9599DocTrabajo').val(data.numPalConcor9599);
					$('#numPalConcor100DocTrabajo').val(data.numPalConcor100);
//			   		$('#capaVersiones_0').html('');
			   		
			   		if (data.ficheros.length > 1){
			   			$('#capaFichero_1').show();
			   			var enlace2 = '<a href="#" class="descargarDoc" data-id="'+data.ficheros[1].idDocInsertado+'">'+data.ficheros[1].nombre+'</a> ('+conversionKB(data.ficheros[1].tamano)+' KB.) ';
			   			enlace2+= (data.ficheros[1].encriptado ==='S')?' <span class="ico-ficha-encriptado" title="'+labelEncrip+'"><i class="fa fa-lock" aria-hidden="true"></i></span>':' <span class="ico-ficha-encriptado" title="'+labelNoEncrip+'"><i class="fa fa-unlock" aria-hidden="true"></i></span>';
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
//				   		$('#capaVersiones_1').html('');
			   		}else{
			   			//para ficheros de revisión con un zip en el primer doc.
			   			$('#capaFichero_1').hide();
			   		}
			   		asociarEventosBotonesTabla();
			   		initDocumentoForm = $("#documentosexpediente_detail_form").rup_form("formSerialize");
		   		}else{
		   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");;
		   		}	
		   	 }
	  	 	,error: function(){	  	 		
	  	 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		   	 }
		 });
		
		// Expediente cerrado
		if( estado === expedCerrado){
			$('#documentosexpediente_detail_div input').prop("readonly",true);
			$('[id^=pidButton_').hide();
			$('[id^=nombreFicheroInfo_').hide();
			$('#tipoDocumento056_detail_table').rup_combo("disable");
			if(!$('#indVisible056_detail_table')[0].disabled){
				$('#indVisible056_detail_table').bootstrapSwitch('toggleDisabled',true,true);
			}
			
			$('#documentosexpediente_detail_button_save').hide();
			initDocumentoForm = $("#documentosexpediente_detail_form").rup_form("formSerialize");
		}
		
		 desbloquearPantalla();
	}
	
	function anadirDocumento(){	
		$("#documentosexpediente_detail_div").rup_dialog("open");
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		$("#documentosexpediente_detail_form").rup_form('clearForm', true);
		clearValidation('#documentosexpediente_detail_form');		
		$("#documentosexpediente_detail_feedback").rup_feedback("close");
		$('#anyo056_detail_table').val($('#anyo_exp').val());
   		$('#numExp056_detail_table').val($('#numExp_exp').val());
   		
   		$('#indVisible056_detail_table').bootstrapSwitch('setState', true);
   		$('#enlaceDescargaDetalle_0').html('');
   		$('#enlaceDescargaDetalle_1').html('');
//   		$('#capaVersiones_0').html('');
//   		$('#capaVersiones_1').html('');
   		$('#pidButton_0').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
   		$('#pidButton_1').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
		$('#claseDocumento056_detail_table').rup_combo("enable");
   		
   		$("#nombreFicheroInfo_0").rules("add", "required");
		$("#nombreFicheroInfo_1").rules("add", "required");
		$("#titulo056_detail_table").rules("remove", "required");
   		
   		if (tipoExpediente === 'I'){
   			$('#capaDetClasificacion').hide();
   			$('#capaDetTipo').hide();
   			$('#capaNumPalSolic').hide();
   			$('.capaDetContacto').hide();
   			$("#indVisible056_detail_table").bootstrapSwitch('setState', false);
   		} else{
   			$('#capaDetClasificacion').show();
   			$('#capaDetTipo').show();
   			$('#capaNumPalSolic').show();
   			$('.capaDetContacto').show();
   			if (esPresupuestoVisibleParaUsuario){
   	   			$('#claseDocumento056_detail_table').rup_combo("disable");
   	   			$('#claseDocumento056_detail_table').rup_combo('select','3');
   			}else{
   				$('#claseDocumento056_detail_table').rup_combo("enable");
   			}
   			if ( $('#claseDocumento056_detail_table').rup_combo("getRupValue") == '2'){
   				$('#capaFichero_1').show();
   			}
   		}
   		initDocumentoForm = $("#documentosexpediente_detail_form").rup_form("formSerialize");
   		desbloquearPantalla();
	}
	
	
	function borrarDocumento(elIdDoc){	
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.caption"),
			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
			OKFunction: function(){
				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"));
				$.ajax({
				   	 type: 'DELETE'
				   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/eliminar/'+elIdDoc+'/'+origen
				 	 ,dataType: 'json' 	 	 	
				 	 ,cache: false
				 	 ,async: true
				   	 ,success:function(data){
				   		if (data !== null){	
				   			if (isEmpty(data.ficheros[0].error)){
				   				ocultaFila(elIdDoc);
					   			$("#datosExpedienteTradRev_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.borradoCorrecto"), "ok");
					   			if (data.llamadaPL === true){
					   				cargaDatosExpTradRev();
					   			}
					   			//Actualizar bitácora + cabecera
								bitacoraUpdate(true);
				   			}else{
				   				//Hay referencias al doc en las tareas
				   				$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorBorrarFichUtilizadoTareas, "error");
				   				
				   			}
				   	
				   		}else{
				   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorBorrandoDatos, "error");
				   		}
				   		desbloquearPantalla();
				   	 }
			  	 	,error: function(jqXHR,error, errorThrown){	  
			  	 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
				   		desbloquearPantalla();
				   	 }
				 });
			}
		});
	}
	
	function descargarDocumento(elIdDoc){			
		window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+elIdDoc);
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
		$('a.eliminarDocumento').click(function(event){
			event.preventDefault();	
			var elIdDoc = $(this).data("id");
			borrarDocumento(elIdDoc);
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
	
	asociarEventosBotonesTabla();
	
	$('#btnNuevoDoc').click(function(event){
		event.preventDefault();
		anadirDocumento();
	});
	
	
	function validaNumPalabrasMaxExp(){
		var datos = jQuery("#documentosexpediente_detail_form").rup_form("formToJson");
		var error = true;
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/validaNumPalabrasMaxExp'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(datos)			
		     ,async: false
		   	 ,success:function(vueltaOk){
		   		if (!vueltaOk){
		   			error =  false;
		   			$("#documentosexpediente_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.numPalMaximoExcedido, "error");
		   		}
		   	 }
	   	 	,error: function(){
		   		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  false;
		   	 }
		 });
		return error;
	}
	
	//FORMULARIO DE DETALLE
	$("#documentosexpediente_detail_form").rup_form({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/"+origen,
		dataType: "json",			
		feedback:$("#documentosexpediente_detail_feedback"),		
		type: "POST",
		beforeSubmit: function(){ 
			var vuelta = validaNumPalabrasMaxExp();
			if (vuelta){
				bloquearPantalla();
			}
			return vuelta;
		},
		success: function(data){
			if (data != null){
				//error de extensión no permitida
				if (( data.ficheros[0].error != null) || ((data.ficheros[1]!=null)&&(data.ficheros[1].error != null) )){
					if ( data.ficheros[0].error != null){
						$("#documentosexpediente_detail_feedback").rup_feedback("set", data.ficheros[0].error, "error");
						$('#nombreFicheroInfo_0').val('');
					}
					if ((data.ficheros[1]!=null)&&(data.ficheros[1].error != null) ){
						$("#documentosexpediente_detail_feedback").rup_feedback("set", data.ficheros[1].error, "error");
						$('#nombreFicheroInfo_0').val('');
						$('#nombreFicheroInfo_1').val('');
					}
				}else{ //archivos permitidos
					var existeFila = false;
					if ( $('#filaDoc'+data.ficheros[0].idDocInsertado).length ){ //existe fila y no hay upload
						existeFila = true;
					}else{ //doc nuevo o ha habido upload
						eliminaFila($('#idDoc056_detail_table').val());
					}
					$('#idDoc056_detail_table').val(data.ficheros[0].idDocInsertado);
					$('#idDocInsertado_0').val(data.ficheros[0].idDocInsertado);
					if (data.ficheros[1]!=null){
						$('#idDocInsertado_1').val(data.ficheros[1].idDocInsertado);	
						anadeFila(existeFila,data.ficheros[0].idDocInsertado,data.ficheros[1].idDocInsertado );
					}else{
						anadeFila(existeFila,data.ficheros[0].idDocInsertado,null );
					}
					
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
				$("#documentosexpediente_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" data es null"), "error");		
			}
		},
		error: function(){
			desbloquearPantalla();
			$("#documentosexpediente_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" error no success"), "error");
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
				
				"ficheros[1].contacto.persona":{ maxlength: 150},				
				"ficheros[1].contacto.email":{ maxlength: 256, email:true},				
				"ficheros[1].contacto.direccion":{ maxlength: 256},				
				"ficheros[1].contacto.entidad":{ maxlength: 150},				
				"ficheros[1].contacto.telefono":{digits:true, maxlength: 15},				
				"ficheros[1].oid":{ required:true},
				"ficheros[1].nombreUpload":{ required:true}
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
	
	$("#documentosexpediente_detail_button_save").button().click(function(){
		if (anyadirValidacionParaDocsTipoTrabajo() && $("#documentosexpediente_detail_form").valid()){		
			$("#documentosexpediente_detail_form").submit();
		}
    });
	
	$("#documentosexpediente_detail_link_cancel").button().click(function(){
		if (initDocumentoForm !== $("#documentosexpediente_detail_form").rup_form("formSerialize")){
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
	
		
	//Elementos del detalle de doc
	function deshabilitarCamposDetalle(){
		$('#tipoDocumento056_detail_table').rup_combo("disable");
		$('#numPalIzo056_detail_table').prop( {"readonly": true, "disabled": true });
		
	}
	function crearCmbTipoDocumento(){
		if ( !$('#tipoDocumento056_detail_table-menu').length ){
			$('#tipoDocumento056_detail_table').rup_combo({
				source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta/"+expedienteConfidencial,
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
			        jQuery('#tipoDocumento056_detail_table-menu').width(jQuery('#tipoDocumento056_detail_table-button').innerWidth());
			    }
				,onLoadSuccess: function() {
					crearClaseDocumentoCombo();
				}
			});
		}
	}
	
	
	function crearClaseDocumentoCombo(){
		$('#claseDocumento056_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/clasificaciondocumentos/tipoexpediente/"+tipoExpediente,
			sourceParam :{
				label: $.rup.lang === 'es' ? "descEs075":"descEu075", 
						value: "id075",
						style:"css"
			}
			,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#claseDocumento056_detail_table-menu').width(jQuery('#claseDocumento056_detail_table-button').innerWidth());
			}
			,onLoadSuccess: function() {
				if (tipoExpediente === 'I'){
					$('#claseDocumento056_detail_table').rup_combo('select','');
					$('#claseDocumento056_detail_table').rules("remove", "required");
				}
				if (esPresupuestoVisibleParaUsuario){
					deshabilitarCamposDetalle();
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

						$('#capaPidButton_0').hide();
						$('#capaPidButton_0').hide();
						$('#capaDivPid_0').hide();
						$('#capaFichero_0').show();
						$('#capaFichero_0').removeClass("noBorder");
						$('#capaFichero_1').hide();
						$('#legendFichero_0').hide();
						$('#legendFichero_1').hide();
						$('#capaDetTipo').show();
						$('#capaRangos').hide();
						$("#numPalConcor084DocTrabajo").val("");
						$("#numPalConcor8594DocTrabajo").val("");
						$("#numPalConcor9599DocTrabajo").val("");
						$("#numPalConcor100DocTrabajo").val("");
						$('#capaSoloTipo').show();
						$('#capaNumPalSolic').show();
						
						$('.capaDetContacto').show();
						$("#tipoDocumento056_detail_table").rules("add", "required");
						$("#indVisible056_detail_table").bootstrapSwitch('setState', true);
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
						$('#capaFichero_0').show();
						$('#capaFichero_0').removeClass("noBorder");
						$('#capaFichero_1').show();
						$('#legendFichero_0').show();
						$('#legendFichero_1').show();
						$('#capaPidButton_0').show();
						$('#capaDivPid_0').show();
						$('#capaFicheroShow').hide();
						$('#capaDetTipo').show();
						$('#capaRangos').hide();
						$("#numPalConcor084DocTrabajo").val("");
						$("#numPalConcor8594DocTrabajo").val("");
						$("#numPalConcor9599DocTrabajo").val("");
						$("#numPalConcor100DocTrabajo").val("");
						$('#capaSoloTipo').show();
						$('#capaNumPalSolic').show();
						
						$('.capaDetContacto').show();
						$("#tipoDocumento056_detail_table").rules("add", "required");
						$("#indVisible056_detail_table").bootstrapSwitch('setState', true);
						break;
					case '4': //Trabajo
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
						$('#capaFichero_0').hide();
						$('#capaFichero_1').hide();
						$('#legendFichero_0').hide();
						$('#legendFichero_1').hide();
						$('#capaFicheroShow').hide();
						$('#capaDetTipo').show();
						$('#capaRangos').show();
						$('#capaSoloTipo').hide();
						$('#capaNumPalSolic').hide();
						$('.capaDetContacto').hide();
						$('#tipoDocumento056_detail_table').rup_combo('select','');
						tipoDoc = '';
						$('#capaFichero_1 input').val('');
						$("#tipoDocumento056_detail_table").rules("remove", "required");
						$("#indVisible056_detail_table").bootstrapSwitch('setState', false);
						$("#indVisible056_detail_table_div").hide();
						break;
					default: 
						//Apoyo
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
						$('#capaFichero_0').hide();
						$('#capaFichero_1').hide();
						$('#legendFichero_0').hide();
						$('#capaFichero_0').addClass("noBorder");
						$('#legendFichero_1').hide();
						$('#capaFicheroShow').hide();
						$('#capaDetTipo').hide();
						$('#capaRangos').hide();
						$("#numPalConcor084DocTrabajo").val("");
						$("#numPalConcor8594DocTrabajo").val("");
						$("#numPalConcor9599DocTrabajo").val("");
						$("#numPalConcor100DocTrabajo").val("");
						$('#capaNumPalSolic').hide();
						$('.capaDetContacto').hide();
						$('#tipoDocumento056_detail_table').rup_combo('select','');
						tipoDoc = '';
						$('#capaFichero_1 input').val('');
						$('#numPalIzo056_detail_table').val('');
						$('#numPalSolic056_detail_table').val('');
						$("#tipoDocumento056_detail_table").rules("remove", "required");
						$("#indVisible056_detail_table_div").show();
						$("#indVisible056_detail_table_div").show();
						$("#indVisible056_detail_table").bootstrapSwitch('setState', false);
						
						break;
				}
				
				$("[id^='pidButton']").unbind("click");
				$("[id^='pidButton']").click(function(event) {
					if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
					else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
					else $("#idBotonUpload").val("0");
					$('#fichero').click();
				});
				
			}
		});
	}
	
	
	
	
	
	var configCombo = {
			loadFromSelect: true
			,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		    }
		};
	
// EXPEDIENTE de tipo REVISION o TRADUCCION ................................................................................................................
	
	if (tipoExpediente !== 'I'){
		
		if( $('#idRelevancia_exp-menu').length ){
			$('#idRelevancia_exp-menu').remove();
		}
		$('#idRelevancia_exp').rup_combo({
			source : "/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/estado/"+"A",
			sourceParam :{
				label: $.rup.lang === 'es' ? "descRelevanciaEs"
						: "descRelevanciaEu", 
				value: "idTipoRelevancia",
				style:"css"
			}
			,width: "auto"
			,ordered: true	
			,rowStriping: true
			,open: function(){
		        jQuery('#idRelevancia_exp-menu').width(jQuery('#idRelevancia_exp-button').innerWidth());
		    },
			onLoadSuccess: function() {
		   		$('#idRelevancia_exp').rup_combo("select", ''+idrelevancia);
		   		datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
		   		comprobarFormularioDoc = true;
		   		if(esPresupuestoVisibleParaUsuario){
		   			$('#idRelevancia_exp').rup_combo("disable");
		   		}else{
		   			$('#idRelevancia_exp').rup_combo("enable");
		   		}
			}
		});
		
		
		if( $('#estadoPpto-menu').length ){
			$('#estadoPpto-menu').remove();
		}
		$("#estadoPpto").rup_combo(configCombo);	
		
		if( $('#negocNuevaEstadoPpto-menu').length ){
			$('#negocNuevaEstadoPpto-menu').remove();
		}
		$("#negocNuevaEstadoPpto").rup_combo(configCombo);	
	 
		
		//Formulario de la T53
		$("#datosExpedienteTradRev_form").rup_validate({
			feedback: $('#datosExpedienteTradRev_feedback'),
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
		function limpiarRecargar53(){
			clearValidation('#datosExpedienteTradRev_form');
			cargaDatosExpTradRev();
		}
		$("#datosExpedienteTradRev_link_cancel").button().click(function(){
			if (comprobarFormulariosPestanas()){
				limpiarRecargar53();
			} else {						
				$.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
					OKFunction: function(){
						limpiarRecargar53();
					}
				});
			}
		});
		
		$("#datosExpedienteTradRev_button_save").click(function() {
			var jsonDatosAceptPpto = 
			{
				"fechaLimite": $('#fechaLimite').val(),
				"horaLimite": $('#horaLimite').val(),
				"estado": $('#estadoPpto').val(),
				
			};
			var jsonDatosAceptFechaHora = 
			{
					"fechaLimite": $('#negocNuevaFechaLimite').val(),
					"horaLimite": $('#negocNuevaHoraLimite').val(),
					"estado": $('#negocNuevaEstadoPpto').val(),
					
			};
			var jsonDatosFact = 
			{
				"anyo": $('#anyo_exp').val(),
				"numExp": $('#numExp_exp').val(),
				"numTotalPalFacturar": $('#numTotalPalFacturar').val(),
				"numPalConcor084": $('#numPalConcor084').val(),
				"numPalConcor8594": $('#numPalConcor8594').val(), 
				"numPalConcor95100": $('#numPalConcor95100').val()	
			};
			var jsonObject = 
			{
				"anyo": $('#anyo_exp').val(),
				"numExp": $('#numExp_exp').val(),
				"numTotalPalIzo": $('#numTotalPalIzo_exp').val(),
				"fechaFinalIzo": $('#fechaFinalIzo_exp').val(),
				"horaFinalIzo":  $('#horaFinalIzo_exp').val(), 
				"idRelevancia":	$('#idRelevancia_exp').rup_combo("getRupValue"),		    					
				"indFacturable": ($('#indFacturable_exp').is(':checked')?'S':'N'),	    				    					
				"indUrgente": ($('#indUrgente_exp').is(':checked')?'S':'N'),
				"indPresupuesto": ($('#indPresupuesto_exp').is(':checked')?'S':'N'),
				"indDificil": ($('#indDificil_exp').is(':checked')?'S':'N'),
				"datosFacturacionExpediente": jsonDatosFact,
				"aceptacionPresupuesto": jsonDatosAceptPpto,
				"aceptacionFechaHora": jsonDatosAceptFechaHora
			};		
			
			if(esPresupuestoVisibleParaUsuario){
				$("#fechaLimite").rules("add", "required");
				$("#horaLimite").rules("add", "required");
			}
			
			if ($("#datosExpedienteTradRev_form").valid()){
				$.ajax({
				   	 type: 'PUT'
				   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/planificacion/guardarParcial'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(jsonObject)			 	
				     ,async: false
				   	 ,success:function(){			   		
				   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
				   		datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
				   		//Actualizar bitácora + cabecera
				   		limpiarRecargar53();
						bitacoraUpdate(true);
						
				   	 }
			   	 	,error: function(){		   	 	
				   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
				   	 }
				 });
			}		
		});	
		cargaDatosExpTradRev();
		if (tipoExpediente === 'R') $('#capaRequierePpto').hide();
		if (esPresupuestoVisibleParaUsuario){
			$('#fechaFinalIzo_button_modif').show();
		}
		
	}else{  // EXPEDIENTE de tipo INTERPRETACION ................................................................................................................
		$('#datosExpedienteTradRev_div').hide();
		$('#anyo_exp').val(anyoExpediente);
   		$('#numExp_exp').val(idExpediente);
   		$('#capaDetClasificacion').hide();
		$('#capaDetTipo').hide();
		$('#capaNumPalSolic').hide();
		$('.capaDetContacto').hide();
		$('#fechaFinalIzo_button_modif').hide();
		$('#reqEncriptado').val('N');
		crearCmbTipoDocumento();
		if (esPresupuestoVisibleParaUsuario){
			$('#fechaLimiteAceptacionInterp').rup_date();
			$("#estadoPptoAceptacionInterp").rup_combo(configCombo);	
			
			//Formulario de la aceptacion de ppto
			$("#datosInterpretacion_form").rup_validate({
				feedback: $('#datosInterpretacion_feedback'),
				liveCheckingErrors: false,				
				block:false,
				delay: 3000,
				gotoTop: true, 
				rules:{},
				showFieldErrorAsDefault: false,
				showErrorsInFeedback: true,
		 		showFieldErrorsInFeedback: false
			});		
			
			function limpiarRecargarAceptPpto(){
				clearValidation('#datosInterpretacion_form');
				cargaDatosEstadoPptoInterpretacion();
			}
			$("#datosInterpretacion_link_cancel").button().click(function(){
				if (comprobarFormulariosPestanas()){
					limpiarRecargarAceptPpto();
				} else {						
					$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
						message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
						OKFunction: function(){
							limpiarRecargarAceptPpto();
						}
					});
				}
			});
			
			$("#datosInterpretacion_button_save").click(function() {
				var jsonDatosAceptPpto = 
				{
					"fechaLimite": $('#fechaLimiteAceptacionInterp').val(),
					"horaLimite": $('#horaLimiteAceptacionInterp').val(),
					"estado": $('#estadoPptoAceptacionInterp').val(),
					
				};
				var jsonObject = 
				{
					"anyo": anyoExpediente,
					"numExp": idExpediente,
					"aceptacionPresupuesto": jsonDatosAceptPpto
				};		
				
				if(esPresupuestoVisibleParaUsuario){
					$("#fechaLimiteAceptacionInterp").rules("add", "required");
					$("#horaLimiteAceptacionInterp").rules("add", "required");
				}
				
				if ($("#datosInterpretacion_form").valid()){
					$.ajax({
					   	 type: 'PUT'
					   	 ,url: '/aa79bItzulnetWar/expedienteinterpretacion/planificacion/guardarDatosEstadoPresupuesto'
					 	 ,dataType: 'json'
					 	 ,contentType: 'application/json' 
					     ,data: $.toJSON(jsonObject)			 	
					     ,async: false
					   	 ,success:function(){	
					   		$('#datosInterpretacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
					   		datosFormularioDoc = $("#datosInterpretacion_form").serialize();
					   		//Actualizar bitácora + cabecera
					   		limpiarRecargarAceptPpto();
							bitacoraUpdate(true);
							
					   	 }
				   	 	,error: function(){		   	 	
					   		$('#datosInterpretacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
					   	 }
					 });
				}		
			});	
			cargaDatosEstadoPptoInterpretacion();
			
			$('#datosInterpretacion_div').show();
		}
		
		datosFormularioDoc = '';
		comprobarFormularioDoc = false;
	}
	

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
		   		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		extensionOK =  false;
		   	 }
		 });			
		return extensionOK;	
	}
	
	function vaciarFileYDesbloquear(){
		$("#fichero").val('');
		desbloquearPantalla();
	}
	
	
	$("#fechaFinalIzo_button_modif").click(function(){
		comprobarEstadoFase(mostrarModificarFechaEntrega, $.rup.i18n.app.label.modificarFechaEntrega);
    });
	
	function mostrarModificarFechaEntrega(){
		$.rup_ajax({
			url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/modificarFechaEntrega/maint/' + anyoExpediente + '/' + idExpediente  
				,success:function(data, textStatus, jqXHR){
					capaDetalleExpediente = $('#divDetalleExpediente').detach();
					$("#divCapaDetalle").append(data);
				},
				error: function (XMLHttpRequest, textStatus, errorThrown){
					alert('Error recuperando datos del paso');
				}
		});
	}
	
	
	$('#subidaFicheroPid_form').rup_form({
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
					   		var destinoUpload = $("#idBotonUpload").val();
					   		$('#nombreFicheroInfo_'+destinoUpload).val(data.nombre);
							$('#nombre_'+destinoUpload).val(data.nombre);
							$('#extensionDoc056_'+destinoUpload).val(data.extension);
							$('#tamanoDoc056_'+destinoUpload).val(data.tamano);
							$('#contentType056_'+destinoUpload).val(data.contentType);
							$('#oidFichero056_'+destinoUpload).val(data.oid);
							$('#indEncriptado056_'+destinoUpload).val(data.encriptado);
							var enlace = '<span>'+data.nombre+'</span> ('+conversionKB(data.tamano)+' KB.) ';
							enlace+=' <span class="ico-ficha-encriptado" title="'+((data.encriptado==='S')?labelEncrip:labelNoEncrip)+'"><i class="fa fa-'+((data.encriptado==='S')?"":"un")+'lock" aria-hidden="true"></i></span>';
							$('#enlaceDescargaDetalle_'+destinoUpload).html(enlace);
							
							//miro la extension por si hay que ocultar el 2do fieldset en revisión con un zip en el 1o
							var laExtension  = $("#fichero").val().substring($("#fichero").val().lastIndexOf('.')+1, $("#fichero").val().length);
							if ( $('#claseDocumento056_detail_table').val() === '2' ){
								if ( destinoUpload==0 && laExtension.toLowerCase() === "zip" ){
									$('#capaFichero_1').hide();
									//vacío todos los campos del segundo fichero por si hubiesen metido algo antes de añadir el zip
									$('#nombreFicheroInfo_1').val('');
									$('#nombre_1').val('');
									$('#extensionDoc056_1').val('');
									$('#tamanoDoc056_1').val('');
									$('#contentType056_1').val('');
									$('#oidFichero056_1').val('');
									$('#indEncriptado056_1').val('');
									$('#enlaceDescargaDetalle_1').html('');
								}else{
									$('#capaFichero_1').show();
									if ( isEmpty($('#oidFichero056_1').val()) ){
										$("#nombreFicheroInfo_1").rules("add", "required");
									}
									
								}
							}
						}else{
							$('#documentosexpediente_detail_feedback').rup_feedback("set", data.error, "error");		
						}
				   		vaciarFileYDesbloquear();
				   	 }
				   	 , error: function(data){
						$('#documentosexpediente_detail_feedback').rup_feedback("set", data.error, "error");
						vaciarFileYDesbloquear();
					 }
				});
			}else{
				$('#documentosexpediente_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
				vaciarFileYDesbloquear();
			}	
		}
		, error: function(archivoPIF){
			$('#documentosexpediente_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
			vaciarFileYDesbloquear();
		}			
	});
	
	$("#fichero").change(function(){
		$('#documentosexpediente_detail_feedback').rup_feedback("hide");
		if ($("#fichero").val() !== ''){
			//no permitir zip en el 2o fich
			var laExtension  = $("#fichero").val().substring($("#fichero").val().lastIndexOf('.')+1, $("#fichero").val().length);
			if (( $("#idBotonUpload").val() === '1' ) &&  (laExtension.toLowerCase() === "zip")){
					$.rup_messages("msgError", { 
	    				title: $.rup.i18nParse($.rup.i18n.app,"label.aviso"),
	    				message: $.rup.i18nParse($.rup.i18n.app,"mensajes.msjErrorZIP")
	    			});
//					$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.msjErrorZIP"), "error");
					$("#fichero").val('');
			}else if (comprobarExtensionValida($('#fichero').val())){
				$("#subidaFicheroPid_form").submit();
	    	}else{ 
	    		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	    	}
		}
	});
	
	if (tipoExpediente === datosExp.tipoExp.traduccion){
		$('#indPresupuesto_exp').on('switch-change', function(event, state) {
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
								message: $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.expTareasAsignadas"), labelProyTrados)
							});
							
							$('#indPresupuesto_exp').bootstrapSwitch('setState', false);
							
						}
					},
					error: function(){
						$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
				   	}
				});
			}
		});
	}
	
	// Expediente cerrado
	if( estado === expedCerrado){
		$('#capaAcciones').hide();
		$('.columnaEliminar').hide();
		$('#capaDocumentos').removeClass("col-md-10");
		$('#capaDocumentos').addClass("col-md-12");
		$('.documento-first').removeClass("col-md-9");
		$('.documento-first').addClass("col-md-10");
		$('#fechaFinalIzo_button_modif').hide();
		
		llamadasFinalizadas('deshabilitarYCtrolCambios');
	}
	
	desbloquearPantalla();
});