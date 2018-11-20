/*
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
jQuery(function(a){a("#limpiar").click(function(){limpiarFiltros()});a("#visorLT").bind("click",function(b){mostrarVisor();b.preventDefault();b.stopImmediatePropagation()});a("#visorFormulario").click(function(b){mostrarFormulario();b.preventDefault();b.stopImmediatePropagation()})});var noraapi_provincias=new NORA.provincia({asynchronous:false,baseUrl:"http://www1.geo.jakina.ejiedes.net/t17iApiJSWar",onSuccess:function(a){var d=a.responseText.evalJSON(true);for(var c=0;c<d.data.length;c++){var b=d.data[c].descripcionOficial;var e=d.data[c].id;jQuery("#comboProvinciasAPI").append(jQuery("<option/>").val(e).html(b))}jQuery("#comboProvinciasAPI").rup_combo("refresh")}}),noraapi_municipios=new NORA.municipio({asynchronous:false,baseUrl:"http://www1.geo.jakina.ejiedes.net/t17iApiJSWar",onSuccess:function(a){if(jQuery("#comboProvinciasAPI").rup_combo("index")===0){jQuery("#comboMunicipiosAPI").rup_combo("disable")}jQuery("#comboMunicipiosAPI").get(0).options.length=1;var b=a.responseText.evalJSON(true);jQuery.each(b.data,function(c,d){jQuery("#comboMunicipiosAPI").append(jQuery("<option/>").val(d.id).html(d.descripcionOficial))});jQuery("#comboMunicipiosAPI").rup_combo("refresh")}}),noraapi_calle=new NORA.calle({asynchronous:false,baseUrl:"http://www1.geo.jakina.ejiedes.net/t17iApiJSWar",onSuccess:function(a){var c=a.responseText.evalJSON(true);var b=[];jQuery.each(c.data,function(d,e){b[d]={value:e.id,label:e.descripcionOficial}});jQuery("#autocompleteAPI_label").rup_autocomplete("option","source",b);jQuery("#autocompleteAPI_label").rup_autocomplete("enable")}});function getAllProvincias(){noraapi_provincias.getByDesc("",{})}function getComunidadProvincia(){return noraapi_provincias.getByDesc("",{responseWithParents:1,idProvincia:jQuery("#comboProvinciasAPI").val()})}function findByNameMunicipio(){noraapi_municipios.getByDesc("",{responseWithParents:1,provinciaId:jQuery("#comboProvinciasAPI").val()})}function findByNameCalle(a){noraapi_calle.getByDesc((a)?a:"",{responseWithParents:1,provinciaId:jQuery("#comboProvinciasAPI").val(),municipioId:jQuery("#comboMunicipiosAPI").val()})}getAllProvincias();