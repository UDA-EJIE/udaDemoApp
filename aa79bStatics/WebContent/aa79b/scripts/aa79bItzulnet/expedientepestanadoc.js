var initDocumentoForm;
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};
var idrelevancia;
//El tipo de expediente
var tipoExpediente;
//var expedienteConfidencial = 'N';
var tipoDoc = '';
var capaItzulpena = '';
var capaItzulpenaContacto = '';
var capaBerrikusketa = '';

function recalcularFechaPropuesta(){
	var jsonObjectFechaProp = 
	{			
		"numPalIzo": $('#numTotalPalIzo_exp').val(),
		"indPresupuesto": ($('#indPresupuesto_exp').is(':checked')?'S':'N'),
		"tipoExp": tipoExpediente,
		"indVip": isGestorVip
	};		
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/calcularFechaPropuesta'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: jsonObjectFechaProp			 	
		     ,cache: false
		     ,async: false
		   	 ,success:function(data){		
		   		if(origen==='A'){
		   			$('#fechaFinalProp_exp').val(data);
		   		}else if(origen === 'C'){
		   			
		   			
		   			var aFechaHora = data.split(" ");
		   			var aHoraMinSeg = aFechaHora[1].split(":");
		   			var fechaValorCampo = aFechaHora[0].split("/");
		   			
		   			if("" != $('#fechaFinalSolic_exp').val()){
		   			
			   			var aFechaHoraSolic = $('#fechaFinalSolic_exp').val().split(" ");
			   			var aHoraMinSegSolic = aFechaHoraSolic[1].split(":");
			   			var fechaValorCampoSolic = aFechaHoraSolic[0].split("/");
			   			//comprobar si la fecha es mayor
			   			
			   			if ($.rup_utils.capitalizedLang() == "Es") {
			   				var fechaValorCampoFormat = new Date(fechaValorCampo[2],
			   						fechaValorCampo[1] - 1, fechaValorCampo[0],
			   						aHoraMinSeg[0], aHoraMinSeg[1]);
			   				var fechaSolicFormat = new Date(fechaValorCampoSolic[2],
			   						fechaValorCampoSolic[1] - 1, fechaValorCampoSolic[0],
			   						aHoraMinSegSolic[0], aHoraMinSegSolic[1]);
			   			} else {
			   				var fechaValorCampoFormat = new Date(fechaValorCampo[0],
			   						fechaValorCampo[1] - 1, fechaValorCampo[2],
			   						aHoraMinSeg[0], aHoraMinSeg[1]);
			   				var fechaSolicFormat = new Date(fechaValorCampoSolic[0],
			   						fechaValorCampoSolic[1] - 1, fechaValorCampoSolic[2], aHoraMinSegSolic[0],
			   						aHoraMinSegSolic[1]);
			   			}

			   			if (fechaSolicFormat.getTime() <= fechaValorCampoFormat
			   							.getTime()) {
		   				
			   				$('#fechaFinalIzo_exp').val(aFechaHora[0]);
			   				var aHoraMinSeg = aFechaHora[1].split(":");
			   				$('#horaFinalIzo_exp').val(aHoraMinSeg[0]+":"+aHoraMinSeg[1]);
			   				$('#fechaFinalIzo_exp').addClass( "reqSubsanacion" );
				   			$('#horaFinalIzo_exp').addClass( "reqSubsanacion" );
			   			}else{
			   				$('#fechaFinalIzo_exp').val(aFechaHoraSolic[0]);
			   				var aHoraMinSeg = aFechaHoraSolic[1].split(":");
			   				$('#horaFinalIzo_exp').val(aHoraMinSeg[0]+":"+aHoraMinSeg[1]);
			   				$('#fechaFinalIzo_exp').removeClass( "reqSubsanacion" );
				   			$('#horaFinalIzo_exp').removeClass( "reqSubsanacion" );
			   			}
		   				
		   			}else{
		   				$('#fechaFinalIzo_exp').val(aFechaHora[0]);
		   				var aHoraMinSeg = aFechaHora[1].split(":");
		   				$('#horaFinalIzo_exp').val(aHoraMinSeg[0]+":"+aHoraMinSeg[1]);
		   				$('#fechaFinalIzo_exp').removeClass( "reqSubsanacion" );
			   			$('#horaFinalIzo_exp').removeClass( "reqSubsanacion" );
		   			}
		   			
		   		}
		   	 }
	   	 	,error: function(){		   	 	
		   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCalcFPropuesta, "error");
		   	 }
		 });
	
}
function volcarFechaSegunNumPalabrasIZO(){
	if("" != $("#numTotalPalIzo_exp").val() && 0 < parseInt($("#numTotalPalIzo_exp").val())){
		if(parseInt($("#numTotalPalIzo_exp").val()) > parseInt($("#numTotalPalSolic_exp").val())){
			recalcularFechaPropuesta();
		}else{
			if($('#fechaFinalSolic_exp').val()){
				var aFechaHora = $("#fechaFinalSolic_exp").val().split(" ");
	   			$('#fechaFinalIzo_exp').val(aFechaHora[0]);
	   			$('#fechaFinalIzo_exp').removeClass( "reqSubsanacion" );
	   			var aHoraMinSeg = aFechaHora[1].split(":");
	   			$('#horaFinalIzo_exp').val(aHoraMinSeg[0]+":"+aHoraMinSeg[1]);
	   			$('#horaFinalIzo_exp').removeClass( "reqSubsanacion" );
			}else{
				$('#fechaFinalIzo_exp').val("");
				$('#fechaFinalIzo_exp').removeClass( "reqSubsanacion" );
				$('#horaFinalIzo_exp').val("");
				$('#horaFinalIzo_exp').removeClass( "reqSubsanacion" );
			}
		}
	}else{
		$('#fechaFinalIzo_exp').val("");
		$('#fechaFinalIzo_exp').removeClass( "reqSubsanacion" );
		$('#horaFinalIzo_exp').val("");
		$('#horaFinalIzo_exp').removeClass( "reqSubsanacion" );
	}
}
function deshabilitarFormulario(){
	if(esTecnico === 'C'){
		$('#datosExpedienteTradRev_form input').attr('readonly', 'readonly');
		$('#datosExpedienteTradRev_form textarea').attr('readonly', 'readonly');
		$('#datosExpedienteTradRev_form select').attr('readonly', 'readonly');
		$("#datosExpedienteTradRev_form :checkbox").attr("disabled", "true");
		$('#datosExpedienteTradRev_form input').attr("disabled", "true");
		$('#datosExpedienteTradRev_form textarea').attr("disabled", "true");
		$('#datosExpedienteTradRev_form select').attr("disabled", "true");
		$('#datosExpedienteTradRev_button_save').hide();
		$('#finalizarAltaOficioTradRev').hide();
		$('#datosExpedienteTradRev_link_cancel').hide();
		$('.document-content-right').hide();
		$('.documento-second').hide();
		$('#fechaFinalIzo_exp').rup_date("disable");
	}
}

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function pintarDocumentosSub(Expediente){
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '../expediente/comprobarDocumentosSeleccionados'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: jQuery.toJSON({
	   		  "expediente":Expediente
	   	 })		
	     ,async: false
	   	 ,success:function(data){
	   		var docuSelect;
	   		
		   	for(var i=0;i<data.length;i++){
		   		if(data[i].bitacoraExpediente.subsanacionExp.docuSelecSub != null){
			   		docuSelect = data[i].bitacoraExpediente.subsanacionExp.docuSelecSub.documentosExpediente.idDoc;
			   		$("div[id=filaDoc"+ docuSelect +"]").children().addClass( "reqSubsanacion" );
			   		$("div[id=filaDoc"+ docuSelect +"]").addClass( "reqSubsanacion" );
		   		}
			}
	   	 }
	});
}

function descargarFicheroTarea(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);//88  2
}

jQuery(function($){
	//A lo bruto, elimino TODO el contenido de la otra pestaña
	$('#capaPestanaCompletaAlta').remove();
	eliminarMensajes();
	datosFormulario = '';
	
	if (origen==='C'){
		$('#finalizarAltaOficioTradRev').hide();
		$('#capaFechaFinalProp').hide();
	}else{
		$('#datosExpedienteTradRev_button_save').hide();
	}
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		});
	
	
	$('#fechaFinalIzo_exp').rup_date();

	$("#finalizarAltaOficioTradRev").click(function() {
		MsgFinalizarAltaOficio();
	});
	$("#finalizarAltaOficioInterpret").click(function() {
		MsgFinalizarAltaOficio();
	});
	
	$('#datosExpedienteTradRev_feedback').rup_feedback(opcionesFeedbacks);
	$('#documentosexpediente_detail_feedback').rup_feedback(opcionesFeedbacks);
	$('#datosExpedienteTradRev_feedback').rup_feedback(opcionesFeedbacks);
	
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
		
		pintarDocumentosSub(Expediente);
	} 
	
	
	function cargaDatosExpTradRev(calcularFechaIZO){
		//Carga la info de la T53
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/'+anyoExp+"/"+numExp
		 	 ,dataType: 'json' 	 	 	
		 	 ,cache: false
		 	 ,async: false
		   	 ,success:function(data){
		   		if (data !== null){    //existe el registro			   			
			   		$('#anyo_exp').val(anyoExp);
			   		$('#numExp_exp').val(numExp);
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
			   		
			   		$('#fechaFinalSolic_exp').val(data.fechaFinalSolic);
			   		$('#fechaFinalProp_exp').val(data.fechaFinalProp);
			   		
			   		if(data.indPresupuesto === 'S'){
			   			$('#indPresupuesto_exp').bootstrapSwitch('setState', true);
			   		}else{
			   			$('#indPresupuesto_exp').bootstrapSwitch('setState', false);
			   		}
			   		if ( data.numTotalPalIzo < $('#palPresupuesto').val() ){	// cambiar a palPresupuesto
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
			   		if(calcularFechaIZO){
			   			volcarFechaSegunNumPalabrasIZO();
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
	   		}	else $('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
	   	 }
  	 	,error: function(){	  	 		
  	 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	   	 }
	 });
	
	
	function accFinalizarAltaOficio(){
		var Expediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };

   		jQuery.ajax({
			type: "POST",
			url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/finAltaOficio",
			dataType: "json",
			contentType: 'application/json', 
			data: jQuery.toJSON({"expediente":Expediente}),
			cache: false,
			async: false,
			success: function () {
				eliminarMensajes();
				
				//Utilizamos msgConfirm ya que el evento beforeClose de msgOk no funciona
				$.rup_messages("msgConfirm", { 
						title: $.rup.i18n.app.boton.finAltaOficio,
						message: $.rup.i18n.app.mensajes.altaOficioOk + '<br><br>' + $.rup.i18n.app.label.numExpediente + ': ' + $("#anyo_exp").val().substr(2,4) + '/' + rellenarConCeros("numExp_exp", 6),
						OKFunction : function(){
							window.location.href = "/aa79bItzulnetWar"
						}
				});
				//Retocamos msgConfirm para que sea idéntico a msgOk
				$(".ui-dialog-titlebar-close").hide();
				$(".rup-enlaceCancelar").remove();
				$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-ok");
				
				//Actualizar bitácora + cabecera
				bitacoraUpdate(true);
			}
   			,error: function(){		   	 	
		   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
		   	}
	    });
	}
	
	function finalizarAltaOficioTradRev(){	
		
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
				"fechaFinalProp": $('#fechaFinalProp_exp').val()
			};		
	    
	    if ($("#datosExpedienteTradRev_form").valid()){
				$.ajax({
				   	 type: 'PUT'
				   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/guardarParcial'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(jsonObject)			 	
				     ,cache: false
				     ,async: false
				   	 ,success:function(){			   		
				   		 
				   		var Expediente = 
					    {
					        "anyo": anyoExpediente, 
					        "numExp": idExpediente
					    };
					    $.ajax({
						   	 type: 'POST'
						   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/comprobarRequisitosDocu'
						 	 ,dataType: 'json'
						 	 ,contentType: 'application/json' 
						     ,data: jQuery.toJSON({"expediente":Expediente})		
						     ,async: false
						   	 ,success:function(data){
						   		 if(data == 0){ //Documentos correctos
						   			 accFinalizarAltaOficio();
						   		 } else if(data == 1){ //Falta la documentacion obligatoria
						   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.docuIncorrecto, "error");
						   		 } else if(data == 2){ //No se ha determinado el numero totla de palabras IZO
							   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.numPalabrasIZODocIncorrecto, "error");
						   		 }else if(data == 4){ //No se ha determinado la fecha final IZO
								   		$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.fechaFinalIZOIncorrecta, "error");
						   		 }else if(data == 5){ //Exped NO confidencial con ficheros encriptados
						   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorDocsEncriptadosFinAlta, "error");
						   		 }else if(data == 6){ //Exped confidencial con ficheros NO encriptados
						   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorDocsNoEncriptadosFinAlta, "error");
						   		 }else{ //No se ha determinado el numero de palabras IZO de todos los documentos
						   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.numPalabrasIZOIncorrecto, "error");
						   		 }
						   	 }
						 }); 
				   		
				   	 }
			   	 	,error: function(){		   	 	
				   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
				   	 }
				 });
			}	  
	}
	
	function MsgFinalizarAltaOficio(){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.app,"boton.finAltaOficio"),
			message: $.rup.i18nParse($.rup.i18n.app,"mensajes.finalizarAltaOficio"),
			OKFunction: function(){
				setTimeout(function() {
					if (tipoExpediente !== 'I'){
						finalizarAltaOficioTradRev();
					}else{
						accFinalizarAltaOficio();
					}
				}, 100);
			}
		});
	}
	
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
		laFila+= '</p><p class="document-fileAndIcon">';
		if ($('#claseDocumento056_detail_table').rup_combo('value') === "2"){
			laFila+= '<div  class="displayAsTable" style="padding-top: 3px;"><p class="document-tipo" style="display: table-cell; padding-right: 5px;">'+labelDocumentoOrigen+' - '+'</p>';
		}	
		laFila+='<p class="document-fileAndIcon"><a href="#" class="document-eusk descargarDoc iconSameLine" data-id="'+elIdInsertado+'">';
		laFila+= $('#nombreFicheroInfo_0').val() != '' ? $('#nombreFicheroInfo_0').val() : $('#nombre_0').val();
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
		laFila+= '</div><div class="col-md-1  documento-second"><span class="ico-ficha-right">';
		laFila+= '<i class="fa fa-eye'+(($('#indVisible056_detail_table').bootstrapSwitch('state')===true)?"":"-slash")+'" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelVisible+'</span>';
		laFila+= '</div>';
		
		
		laFila+= '<div class="col-md-1 documento-second"><div><a class="verDocumento"  href="#" data-id="'+elIdInsertado+'" ><span class="ico-ficha-right"><i class="fa fa-search" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelVer+'</span></a></div></div>';
		laFila+= '<div class="col-md-1 documento-second"><div style="border-left: 1px solid #d9dce0;"><a class="eliminarDocumento"  href="#" data-id="'+elIdInsertado+'"><span class="ico-ficha-right"><i class="fa fa-times" aria-hidden="true"></i></span><span class="txt-ficha-right">'+labelEliminar+'</span></a></div></div>';
		
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
   			if (origen==='C') $('#capaNumPalSolic').show();
			else $('#capaNumPalSolic').hide();
   			$('.capaDetContacto').show();
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
				   			ocultaFila(elIdDoc);
				   			$("#datosExpedienteTradRev_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.borradoCorrecto"), "ok");
				   			if (data.llamadaPL === true){
				   				cargaDatosExpTradRev(origen === 'C');
				   			}
				   			//Actualizar bitácora + cabecera
							bitacoraUpdate(true);
				   		}else{
				   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorBorrandoDatos, "error");
				   		}
				   		desbloquearPantalla();
				   	 }
			  	 	,error: function(){	  	 		
			  	 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
				   		desbloquearPantalla();
				   	 }
				 });
			}
		});
	}
	
	function descargarDocumento(elIdDoc){			
		window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/1/'+elIdDoc);
	}
	
	function asociarEventosBotonesTabla(){
		$( "a.verDocumento").unbind( "click" );
		$( "a.eliminarDocumento").unbind( "click" );
		$( "a.descargarDoc").unbind( "click" );
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
		$('a.descargarDocXliff').click(function(event){
			event.preventDefault();	
			var elIdDoc = $(this).data("id");
			descargarFicheroTarea(elIdDoc);
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
						$('#nombre_1').val(data.ficheros[1].nombre);
						anadeFila(existeFila,data.ficheros[0].idDocInsertado,data.ficheros[1].idDocInsertado );
					}else{
						anadeFila(existeFila,data.ficheros[0].idDocInsertado,null );
					}
					$("#documentosexpediente_detail_div").rup_dialog("close");
					$("#datosExpedienteTradRev_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
					if (data.llamadaPL === true){
		   				cargaDatosExpTradRev(origen === 'C');
		   			}
					//Actualizar bitácora + cabecera
					bitacoraUpdate(true);
				}
				desbloquearPantalla();
			}else{
				desbloquearPantalla();
				$("#documentosexpediente_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" data es null"), "error");		
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
		if ($("#documentosexpediente_detail_form").valid()){		
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
	
	function crearCmbTipoDocumento(){
		if($('#tipoDocumento056_detail_table-menu').length){
			$('#tipoDocumento056_detail_table-menu').remove();
			$("#tipoDocumento056_detail_table").rup_combo("clear");
		}
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
	
	
	function crearClaseDocumentoCombo(){
		if($('#claseDocumento056_detail_table-menu').length){
			$('#claseDocumento056_detail_table-menu').remove();
		}
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
				}else{
					//no permitir que añadan docs de trabajo...
					$("#claseDocumento056_detail_table option[value='4']").remove();
					$("#claseDocumento056_detail_table").rup_combo("refresh");
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
						if (origen==='C') $('#capaNumPalSolic').show();
						else $('#capaNumPalSolic').hide();
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
						if (origen==='C') $('#capaNumPalSolic').show();
						else $('#capaNumPalSolic').hide();
						$('.capaDetContacto').show();
						$("#tipoDocumento056_detail_table").rules("add", "required");
						$("#indVisible056_detail_table").bootstrapSwitch('setState', true);
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
						$('#capaFichero_0').hide();
						$('#capaFichero_1').hide();
						$('#legendFichero_0').hide();
						$('#capaFichero_0').addClass("noBorder");
						$('#legendFichero_1').hide();
						$('#capaFicheroShow').hide();
						$('#capaDetTipo').hide();
						$('.capaDetContacto').hide();
						$('#tipoDocumento056_detail_table').rup_combo('select','');
						tipoDoc = '';
						$('#capaNumPalSolic').hide();
						$('#capaFichero_1 input').val('');
						$('#numPalIzo056_detail_table').val('');
						$('#numPalSolic056_detail_table').val('');
						$("#tipoDocumento056_detail_table").rules("remove", "required");
						
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
	

	
	
// EXPEDIENTE de tipo REVISION o TRADUCCION ................................................................................................................
	
	if (tipoExpediente !== 'I'){
		cargaDatosExpTradRev(false);
		if (tipoExpediente === 'R') $('#capaRequierePpto').hide();
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
		   		if(esTecnico === "C"){
		   			$('#idRelevancia_exp').rup_combo("disable");
		   		}else{
		   			$('#idRelevancia_exp').rup_combo("enable");
		   		}
			}
		});
		
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
			cargaDatosExpTradRev(false);
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
				"indPresupuesto": ($('#indPresupuesto_exp').is(':checked')?'S':'N')
			};		
			if ($("#datosExpedienteTradRev_form").valid()){
				$.ajax({
				   	 type: 'PUT'
				   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/guardarParcial'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(jsonObject)			 	
				     ,async: false
				   	 ,success:function(){			   		
				   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
				   		datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
				   		//Actualizar bitácora + cabecera
						bitacoraUpdate(true);
						cargaDatosExpTradRev(false);
				   	 }
			   	 	,error: function(){		   	 	
				   		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
				   	 }
				 });
			}		
		});	
		if (origen==='A'){
			
			$("#numTotalPalIzo_exp").on("change", function() {
				recalcularFechaPropuesta();
			});
			$('#indPresupuesto_exp').on('switch-change', function() {
				recalcularFechaPropuesta();
			});
		}else if(origen === 'C'){
			$("#numTotalPalIzo_exp").on("change", function() {
				volcarFechaSegunNumPalabrasIZO();
			});
		}
		
		
		
	}else{  // EXPEDIENTE de tipo INTERPRETACION ................................................................................................................
		$('#datosExpedienteTradRev_div').hide();
		$('#capaFinalizarExpOficioInterpretacion').show();
		$('#anyo_exp').val(anyoExp);
   		$('#numExp_exp').val(numExp);
   		$('#capaDetClasificacion').hide();
		$('#capaDetTipo').hide();
		$('#capaNumPalSolic').hide();
		$('.capaDetContacto').hide();
		$('#reqEncriptado').val('N');
		crearCmbTipoDocumento();
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
					$("#fichero").val('');
			}else if (comprobarExtensionValida($('#fichero').val())){
				$("#subidaFicheroPid_form").submit();
	    	}else{ 
	    		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	    	}
		}
	});
	
	deshabilitarFormulario();
	
	desbloquearPantalla();
		
});