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
jQuery(function(jQuery){
	
	jQuery("#limpiar").click(function() {
		limpiarFiltros();
	});

	jQuery("#visorLT").bind("click", function(event) {
		mostrarVisor();
		event.preventDefault();
		event.stopImmediatePropagation();
	});
	
	jQuery("#visorFormulario").click(function(event) {
		mostrarFormulario();
		event.preventDefault();
		event.stopImmediatePropagation();
	});
});	
	
	var noraapi_provincias = new NORA.provincia({
			asynchronous : false,
			baseUrl : 'http://www1.geo.jakina.ejiedes.net/t17iApiJSWar',
			onSuccess : function(response) {
				var provincias = response.responseText.evalJSON(true);
				for ( var i = 0; i < provincias.data.length; i++) {
					var valor = provincias.data[i].descripcionOficial;
					var id = provincias.data[i].id;
					jQuery("#comboProvinciasAPI").append(jQuery('<option/>').val(id).html(valor));
				}
				jQuery("#comboProvinciasAPI").rup_combo('refresh');
			}
		}),
		noraapi_municipios = new NORA.municipio({
			asynchronous : false,
			baseUrl : 'http://www1.geo.jakina.ejiedes.net/t17iApiJSWar',
			onSuccess : function(response) {
				if (jQuery("#comboProvinciasAPI").rup_combo("index") === 0){
					jQuery("#comboMunicipiosAPI").rup_combo("disable");
				}	
				jQuery("#comboMunicipiosAPI").get(0).options.length = 1;
				var municipios = response.responseText.evalJSON(true);
				jQuery.each(municipios.data, function(position, val){
					jQuery("#comboMunicipiosAPI").append(jQuery('<option/>').val(val.id).html(val.descripcionOficial));
				});
				jQuery("#comboMunicipiosAPI").rup_combo('refresh');
			}
		}),
		noraapi_calle = new NORA.calle({
			asynchronous : false,
			baseUrl : 'http://www1.geo.jakina.ejiedes.net/t17iApiJSWar',
			onSuccess : function(response) {
				var callejero = response.responseText.evalJSON(true);
				
				var json = [];
				jQuery.each(callejero.data, function(position, val){
					json[position] = {
							value : val.id,
							label : val.descripcionOficial
						};
				});
				jQuery("#autocompleteAPI_label").rup_autocomplete( "option", "source", json);
				jQuery("#autocompleteAPI_label").rup_autocomplete('enable');
			}
		});
	function getAllProvincias() {
		noraapi_provincias.getByDesc("", {});
	}
	function getComunidadProvincia() {
		return noraapi_provincias.getByDesc("", {
			responseWithParents : 1,
			idProvincia : jQuery("#comboProvinciasAPI").val()
		});
	}
	function findByNameMunicipio() {
		noraapi_municipios.getByDesc("", {
			responseWithParents : 1,
			provinciaId : jQuery("#comboProvinciasAPI").val()
		});
	}
	function findByNameCalle(calle) {
		noraapi_calle.getByDesc((calle)?calle:"", {
			responseWithParents : 1,
			provinciaId : jQuery("#comboProvinciasAPI").val(),
			municipioId : jQuery("#comboMunicipiosAPI").val()
		});	
	}
	getAllProvincias();
