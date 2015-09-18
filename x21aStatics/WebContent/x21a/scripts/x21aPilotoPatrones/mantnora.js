/*!
 * Copyright 2012 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

jQuery("#comboProvincias").rup_combo({
	source : "comboEnlazado/remoteEnlazadoProvincia",
	sourceParam : {label:"dsO", value:"id"},
	blank : "0"
});


jQuery("#comboMunicipios").rup_combo({
	parent : [ "comboProvincias" ],
	source : "comboEnlazado/remoteEnlazadoMunicipio",
	sourceParam : {label:"dsO", value:"id"},
	blank : "0",
	select: function(){
		if(jQuery("#comboMunicipios").val() !== '0'){
			jQuery("#autocomplete_label").rup_autocomplete('enable');
		}else{
			jQuery("#autocomplete_label").rup_autocomplete('disable');
		}
	}
});

jQuery("#autocomplete").rup_autocomplete({
	source : "autocomplete/calleRemote",
	sourceParam : {label:"dsO", value:"id"},
	minLength: 0,
	contains : true,
	extraParams: {
        provinciaId: function() {return jQuery("#comboProvincias").val();},
        municipioId: function() {return jQuery("#comboMunicipios").val();}
     }
});

jQuery("#comboProvinciasAPI").rup_combo({
	source : [],
	blank : "0"
});

jQuery("#comboMunicipiosAPI").rup_combo({
	parent : [ "comboProvinciasAPI" ],
	source : function() {
		findByNameMunicipio();
	},
	sourceParam : {
		label : "dsO",
		value : "id"
	},
	blank : "0",
	select : function() {
		jQuery("#autocompleteAPI_label").val("");
		jQuery("#autocompleteAPI_label").rup_autocomplete('disable');
		return findByNameCalle(jQuery("#autocompleteAPI_label").val());
	}
});

jQuery("#autocompleteAPI").rup_autocomplete({
	source : "",
	minLength: 0,
	contains : true
});
function limpiarFiltros() {
	$('autonomia_search').value = '';
	$('comarca_search').value = '';
	$('provincia_search').value = '';
	$('localidad_search').value = '';
	$('municipio_search').value = '';
	$('calle_search').value = '';
	$('portal_search').value = '';
	$('cp_search').value = '';
	$('escalera_search').value = '';
	$('piso_search').value = '';
	$('mano_search').value = '';
	$('puerta_search').value = '';
	$('aprox_postal_search').value = '';
}
function loadData(address) {
	limpiarFiltros();
	var direccion = null;
	if (address.data) {
		direccion = address.data;
	} else if (address.portal) {
		direccion = address.portal;
		$('escalera_search').value = address.escalera;
		$('piso_search').value = address.piso;
		$('mano_search').value = address.mano;
		$('puerta_search').value = address.puerta;
		$('aprox_postal_search').value = address.aprox_postal;
	}

	if (direccion.municipio) {
		$('autonomia_search').value = direccion.municipio.comarca.provincia.autonomia.descripcionOficial;
		$('comarca_search').value = direccion.municipio.comarca.descripcionOficial;
		$('provincia_search').value = direccion.municipio.provincia.descripcionOficial;
		$('localidad_search').value = direccion.descripcionOficial;
		$('municipio_search').value = direccion.municipio.descripcionOficial;
	} else {
		$('autonomia_search').value = direccion.calle.localidad[0].municipio.comarca.provincia.autonomia.descripcionOficial;
		$('comarca_search').value = direccion.calle.localidad[0].municipio.comarca.descripcionOficial;
		$('provincia_search').value = direccion.calle.localidad[0].municipio.provincia.descripcionOficial;
		$('localidad_search').value = direccion.calle.localidad[0].descripcionOficial;
		$('municipio_search').value = direccion.calle.localidad[0].municipio.descripcionOficial;
		$('calle_search').value = direccion.calle.descripcionOficial;
		$('portal_search').value = direccion.numero;
		$('cp_search').value = direccion.codigoPostal;
	}
}

function mostrarVisor() {
	$('id_capaVisor').style.visibility = '';
}
function esconderVisor() {
	$('id_capaVisor').style.visibility = 'hidden';
}
function mostrarFormulario() {
	$('id_capaFormulario').style.visibility = '';
}
function esconderFormulario() {
	$('id_capaFormulario').style.visibility = 'hidden';
}

