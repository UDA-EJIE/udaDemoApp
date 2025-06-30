var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};

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
		,change: function(){
			switch (elTipo){
				case 'T': //Traducción, esto mas lo del 1
					$('#capaFichero_1').hide();
					$('#capaDetTipo').show();
					if (origen==='C') $('#capaNumPalSolic').show();
					else $('#capaNumPalSolic').hide();
					$('.capaDetContacto').show();
					$("#tipoDocumento056_detail_table").rules("add", "required");
					break;
				case 'R':
					$('#capaFichero_1').show();
					$('#capaDetTipo').show();
					if (origen==='C') $('#capaNumPalSolic').show();
					else $('#capaNumPalSolic').hide();
					$('.capaDetContacto').show();
					$("#tipoDocumento056_detail_table").rules("add", "required");
					$("#indVisible056_detail_table").bootstrapSwitch('setState', false);
					break;
				default: //Apoyo, no se va  adar en esta parte
					$('#capaFichero_1').hide();
					$('#capaDetTipo').hide();
					$('.capaDetContacto').hide();
					$('#tipoDocumento056_detail_table').rup_combo('select','');
					$('#capaFichero_1 input').val('');
					$('#numPalIzo056_detail_table').val('');
					$("#tipoDocumento056_detail_table").rules("remove", "required");
					break;
			}
			$('#claseDocumento056_detail_table').rup_combo('disable');
		}
		
	});
}




jQuery(function($){
	//podría comprobar if(!consultaExp)
	$('#documentosexpediente_detail_feedback').rup_feedback(opcionesFeedbacks);
	
	//Elementos del detalle de doc
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
	});
	
	
	
	
	//poner la validación en la 2ª hora (fin)
	$.validator.addMethod("comprobarExtension", function(){
		var extension = $('#fichero').val().substring( $('#fichero').val().lastIndexOf (".") + 1, $('#fichero').val().length );
		var error = false;
		var jsonObject = 
		{
			"formato011": extension
		};	
		
		$.ajax({
		   	 type: 'GET'		   	 
		   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/comprobarextension'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     //,data: $.toJSON(jsonObject)		
		     ,data: jsonObject	
		     ,async: false
		     ,cache: false
		   	 ,success:function(extensionValida){
		   		if (extensionValida === 0) {
		   			error =  false;
		   		} else {
		   			error =  true;
		   		}
		   	 }
	   	 	,error: function(){
		   		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  false;
		   	 }
		 });			
		return error;		

	}, $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"));	
	
	
	
	
	/* PID PIF - INICIO */
	$("[id^='pidButton']").on("click", function() {
		
		if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
		else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T55
		else $("#idBotonUpload").val("0");
		$("#subidaFicheroPid").rup_dialog("open");
	});
	
	$('#subidaFicheroPid_form').rup_form({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/subidaFichero"
		, dataType: "json"
		, validate: {
			feedback:$("#subidaFicheroPid_feedback"), 
			rules:{
				"fichero":{required: true, comprobarExtension: true}
				
	//VALIDACION AQUI- AJAx t11
			}
		}
		, beforeSend: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			//Ponemos esto para que no falle el submit del formulario con fichero en Chrome
			return "skip";
		}
		, success: function(data){
			var destinoUpload = $("#idBotonUpload").val();
			if (data.error === null){ //Subida correcta
				$('#nombreFicheroInfo_'+destinoUpload).val(data.nombre);
				$('#extensionDoc056_'+destinoUpload).val(data.extension);
				$('#tamanoDoc056_'+destinoUpload).val(data.tamano);
				$('#contentType056_'+destinoUpload).val(data.contentType);
				$('#rutaPif_'+destinoUpload).val(data.rutaPif);
				$('#indEncriptado056_'+destinoUpload).val(data.encriptado);
				
				var enlace=' <span class="ico-ficha-encriptado" title="'+((data.encriptado==='S')?labelEncrip:labelNoEncrip)+'"><i class="fa fa-'+((data.encriptado==='S')?"":"un")+'lock" aria-hidden="true"></i></span>';
				$('#enlaceDescargaDetalle_'+destinoUpload).html(enlace);
				$("#subidaFicheroPid").rup_dialog("close");
			}else{
				$('#subidaFicheroPid_feedback').rup_feedback("set", data.error, "error");		
			}
			desbloquearPantalla();
		 }
		, error: function(data){
			$('#subidaFicheroPid_feedback').rup_feedback("set", data.error, "error");
		}
	});
	
	$('#subidaFicheroPid_form').rup_validate({
		feedback:$("#subidaFicheroPid_feedback"),
		rules:{
		}
		, showErrorsInFeedback:false
	});
	
	$("#fichero").change(function(){
		$('#subidaFicheroPid_feedback').rup_feedback("hide");
		$("#subidaFicheroPid_form").submit();
	});
	
	$("#subidaFicheroPid_feedback").rup_feedback({
		block: false
	});
	$("#subidaFicheroPid").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        width: "700",
        title: "Subir nuevo fichero"
        ,buttons: [{
			text: "Cancelar",
			click: function () { 
				$("#subidaFicheroPid").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$("#divFichero").show();
			$("#subidaFicheroPid_form").validate().resetForm();
			$('#subidaFicheroPid_feedback').rup_feedback("hide");
		}
	});
	
	
	/* PID PIF - FIN */
	
	
});