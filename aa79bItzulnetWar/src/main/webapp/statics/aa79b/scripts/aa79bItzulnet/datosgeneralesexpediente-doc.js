var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}
function scrollToFeedbackDocumentosExpediente(){
	$('html,body').animate({
	    scrollTop: $("#documentos_expediente_trad_rev_filter_fieldset legend").offset().top - 250
	}, 500);
}
function creaComboClaseDocumento(elTipo){
	$('#claseDocumento056_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/clasificaciondocumentos/tipoexpediente/"+$('#idTipoExpediente').rup_combo("getRupValue"),
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
		,onLoadSuccess: function(){ 
			$('#claseDocumento056_detail_table').rup_combo('disable');
		}
		,change: function(){
			switch (elTipo){
				case 'T': //Traducción, esto mas lo del 1
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
					$('#capaFichero_1').hide();
					$('#legendFichero_0').hide();
					$('#legendFichero_1').hide();
					$('#capaDetTipo').show();
					if (origen==='C') $('#capaNumPalSolic').show();
					else $('#capaNumPalSolic').hide();
					$('.capaDetContacto').show();
					$("#tipoDocumento056_detail_table").rules("add", "required");
					break;
				case 'R':
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
					$("#oidFichero056_1").rules("add", "required");
					$("#nombreFicheroInfo_1").rules("add", "required");
					break;
				default: //Apoyo, no se va  adar en esta parte
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
					$('#legendFichero_1').hide();
					$('#capaFicheroShow').hide();
					$('#capaDetTipo').hide();
					$('#capaNumPalSolic').hide();
					$('.capaDetContacto').hide();
					$('#tipoDocumento056_detail_table').rup_combo('select','');
					$('#capaFichero_1 input').val('');
					$('#numPalIzo056_detail_table').val('');
					$("#tipoDocumento056_detail_table").rules("remove", "required");
					break;
			}
			
			$("[id^='pidButton']").unbind("click");
			$("[id^='pidButton']").on("click", function() {
				if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
				else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
				else $("#idBotonUpload").val("0");
				$('#fichero').click();
			});
			switch (elTipo){
				case 'T':
					$('#claseDocumento056_detail_table').rup_combo('setRupValue',clasificacionDocTraduccion);
					break;
				case 'R':
					$('#claseDocumento056_detail_table').rup_combo('setRupValue',clasificacionDocRevision);
					break;
			}
		}
		
	});
}

function crearCmbTipoDocumento(){
	var tmpConfid = $('#indConfidencial').bootstrapSwitch('state')?"S":"N";
	//Elementos del detalle de doc
	$('#tipoDocumento056_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta/"+tmpConfid,
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
		,onLoadSuccess: function(){
			$('#tipoDocumento056_detail_table').rup_combo('select','');
			datosFormulario = $("#datosgeneralesexpedienteform").serialize();
		}
	});
	
}


var capaItzulpena = '';
var capaItzulpenaContacto = '';
var capaBerrikusketa = '';
var capaVersionesApoyo = '';

jQuery(function($){
	//podría comprobar if(!consultaExp)
	$('#documentosexpediente_detail_feedback').rup_feedback(opcionesFeedbacks);
	
	crearCmbTipoDocumento();
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
		   		scrollToFeedbackDocumentosExpediente();
		   		extensionOK =  false;
		   	 }
		 });			
		return extensionOK;	
	}
	
	
	function vaciarFileYDesbloquear(){
		$("#fichero").val('');
		desbloquearPantalla();
	}
	
	/* PID PIF - INICIO */
	$('#subidaFicheroPid_form').rup_form({
		url: "/aa79bItzulnetWar/ficheros/subidaFichero"
		, dataType: "json"
		, beforeSend: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
			return "skip";
		}
		, success: function(archivoPIF){
			if (archivoPIF.error === null){ 
				//Subida correcta
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
							$('#enlaceDescargaDetalle_'+destinoUpload).html(enlace);
							if (destinoUpload == "2"){
								$('#btnEliminarObserv').show();
							}
							
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
								}else if(destinoUpload!=2){
									//comprobamos que el documento subido no es de observaciones para no mostrar la capa en caso de que este oculta(zip)
									$('#capaFichero_1').show();
									if ( isEmpty($('#oidFichero056_1').val()) ){
										$("#nombreFicheroInfo_1").rules("add", "required");
									}
								}
							}
							
							
				   		}else{
				   			if ( $("#idBotonUpload").val() != 2){
								$('#documentosexpediente_detail_feedback').rup_feedback("set", data.error, "error");
								scrollToFeedbackDocumentosExpediente();
							}else{
								$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", data.error, "error");
							}
						}
				   		vaciarFileYDesbloquear();
				   	 }
			   	 	,error: function(){
				   		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   		scrollToFeedbackDocumentosExpediente();
				   		vaciarFileYDesbloquear();
				   	 }
				 });
			}else{
				if ( $("#idBotonUpload").val() != 2){
					$('#documentosexpediente_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
					scrollToFeedbackDocumentosExpediente();
				}else{
					$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", archivoPIF.error, "error");
				}	
				vaciarFileYDesbloquear();
			}
		 }
		, error: function(archivoPIF){
			if ( $("#idBotonUpload").val() != 2){
				$('#documentosexpediente_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
				scrollToFeedbackDocumentosExpediente();
			}else{
				$('#datosgeneralesexpedienteform_feedback').rup_feedback("set", archivoPIF.error, "error");
			}
			vaciarFileYDesbloquear();
		}
	});
	
	
	
	// CAMBIO SOLUCION PIF/PID
	// Ahora el requerir encriptado depende solo del indConfidencialidad...
	
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
//				if ($("#idBotonUpload").val() =='2'){
//					$('#reqEncriptado').val('N');
//					$("#subidaFicheroPid_form").submit();
//					$('#reqEncriptado').val( ($('#indConfidencial').bootstrapSwitch('state')?"S":"N") );	
//				}else{
					$('#reqEncriptado').val( ($('#indConfidencial').bootstrapSwitch('state')?"S":"N") );
					$("#subidaFicheroPid_form").submit();
//				}
	    	}else{ 
	    		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	    		scrollToFeedbackDocumentosExpediente();
	    	}
		}
	});
	desbloquearPantalla();
	/* PID PIF - FIN */
	
});