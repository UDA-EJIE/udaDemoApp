nora_response();
	
function buscarDireccion(){
	var tipo=jQuery('#tipoNora').val();
	var nora = {t17_nora_i18n: $.rup.lang,
			t17_nora_tipo:3,
			title: $.rup.i18nParse($.rup.i18n.app,"label.searchAddress"),
			url: SERVIDOR_NORA +"/sidl/sidljQuery/dynamic_modal_form3.html"};
	if(tipo==0){
		jQuery.extend(nora, {
			t17_nora_portal_id: jQuery("#portalId").val(),
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	} else if(tipo==1 || tipo==2){
		jQuery.extend(nora, {
			t17_nora_localidad_id: jQuery("#localidadId").val(),
			t17_nora_calle: jQuery("#calle").val(),
			t17_nora_portal_numero: jQuery("#portal").val(),
			t17_nora_portal_cp: jQuery("#cp").val(),
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	} else if(tipo==3){
		jQuery.extend(nora, {
			t17_nora_pais_id: jQuery("#paisId").val(),
			t17_nora_provincia: jQuery("#provincia").val(),
			t17_nora_municipio: jQuery("#localidad").val(),
			t17_nora_calle: jQuery("#calle").val(),
			t17_nora_portal_cp: jQuery("#cp").val()});
	} else{
		jQuery.extend(nora, {
			t17_nora_escalera: jQuery("#escalera").val(),
			t17_nora_piso: jQuery("#piso").val(),
			t17_nora_mano: jQuery("#mano").val(),
			t17_nora_puerta: jQuery("#puerta").val(),
			t17_nora_aprox_postal: jQuery("#aproxPostal").val()});
	}
	openNoraModalDynamicForm(nora);
}

function confirm_t17i_form(O_O) {
	//Aquí se setearán los valores provenientes del formulario de NORA
	$('#tipoNora').val(O_O.tipo);
	limpiarCamposDireccion();

	var tipo=$('#tipoNora').val();
	if(tipo==3){
		// ES EXTRANJERO
		$('#pais').val(O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.descripcionOficial.toUpperCase());
		$('#paisId').val(O_O.portal.calle.localidad[0].municipio.provincia.autonomia.pais.id);
		$('#portalId').val('0');
		$('#provincia').val(O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase());
		$('#localidad').val(O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase());
		$('#calle').val(O_O.portal.calle.descripcionOficial.toUpperCase());
		$('#cp').val(O_O.portal.codigoPostal.toUpperCase());
	} else {
		// ES NACIONAL
		//el pais lo vamos a dejar vacio
		// CODIGO
		if (tipo==0) {
			$('#portalId').val(O_O.portal.id);
			$('#localidadId').val(O_O.portal.calle.localidad[0].id);
		} else {
			$('#localidadId').val(O_O.portal.calle.localidad[0].id);
		}
		// TH
		$('#provinciaId').val(O_O.portal.calle.localidad[0].municipio.provincia.id);
		$('#provincia').val(O_O.portal.calle.localidad[0].municipio.provincia.descripcionOficial.toUpperCase());
		// MUN
		$('#municipioId').val(O_O.portal.calle.localidad[0].municipio.id);
		$('#municipio').val(O_O.portal.calle.localidad[0].municipio.descripcionOficial.toUpperCase());
		// LOC
		$('#localidad').val(O_O.portal.calle.localidad[0].descripcionOficial.toUpperCase());
		// CALLE
		if (O_O.portal.calle.descripcionSecundaria == undefined || O_O.portal.calle.descripcionSecundaria == "" ){
			$('#calle').val(O_O.portal.calle.descripcionOficial.toUpperCase());
		} else {
			$('#calle').val(O_O.portal.calle.descripcionOficial.toUpperCase() + " | " + O_O.portal.calle.descripcionSecundaria.toUpperCase());
		}
		// PORTAL
		if (tipo == '0') {
			var numero = O_O.portal.numero.toUpperCase();
			var bis = O_O.portal.bis;
			var acepcion = O_O.portal.acepcion;
			var bloque = O_O.portal.bloque;
			
			var portal = numero;
			
			// BIS
			if (bis!=''){
				if (bis=='Y'){
					bis = 'BIS';
				}
				portal += " Bis: " + bis;
			}
			
			// BLOQUE
			if(bloque!=''){
				portal += " Bloque:" + bloque;
			}
			
			// ACEPCION
			if (acepcion!=''){
			    portal += " - " + acepcion;		        
			}
			$('#portal').val(portal);
		} else {
			$('#portal').val(O_O.portal.numero.toUpperCase());
		}
		// CP
		$('#cp').val(O_O.portal.codigoPostal.toUpperCase());
		// RESTO
		$('#escalera').val(O_O.escalera.toUpperCase());
		$('#piso').val(O_O.piso.toUpperCase());
		$('#mano').val(O_O.mano.toUpperCase());
		$('#puerta').val(O_O.puerta.toUpperCase());
		$('#aproxPostal').val(O_O.aprox_postal.toUpperCase());	
	}
}
	
function limpiarCamposDireccion(){
	jQuery('#escalera').val("");
	jQuery('#piso').val("");
	jQuery('#mano').val("");
	jQuery('#puerta').val("");
	jQuery('#portal').val("");
	jQuery('#portalId').val("");
	jQuery('#aproxPostal').val("");
	jQuery('#localidad').val("");
	jQuery('#localidadId').val("");
	jQuery('#municipio').val("");
	jQuery('#municipioId').val("");
	jQuery('#pais').val("");
	jQuery('#paisId').val("");
	jQuery('#provinciaId').val("");
	jQuery('#provincia').val("");
	jQuery('#calle').val("");
	jQuery('#calleId').val("");
	jQuery('#cp').val("");	
}

jQuery(function($) {
	$('#ubicacionNora_form').rup_form({
		url: "/aa79bItzulnetWar/pruebas/nora/guardarUbic"
		, cache: false
		, dataType: "json"
		, success: function(data){
			alert("Todo ok");
		 }
		, error: function(data){
			alert("Error");
		}
	});
	
	$('#form_button_save').on("click", function(){
		$('#ubicacionNora_form').submit();
	});
	
	var twice = true;
	$("#direccion_toolbar").rup_toolbar({
		buttons:[
			{
			 i18nCaption: "Buscar dirección",
			 css:"buscar",
		     click:
				function(){
		    	 	twice = !twice;
		    	 	if(twice){
		    	 		buscarDireccion();
		    	 	}
				}
			}
		]
	});
	
});