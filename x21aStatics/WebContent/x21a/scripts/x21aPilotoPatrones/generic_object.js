/*!
 * Copyright 2011 E.J.I.E., S.A.
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
jQuery(function($){
	
	var data = {};
	data["entidades"]= [
		{"departamentoProvincia": { 
			"provincia": {"code":"1"},
			"departamento": {"code":"1"}
		}}
	];
	data["data"] = { "label":"code", "value":"desc_es", "style":"css" };
	data = $.toJSON(data);

	$.ajax({
		url: "genericObject", 
		dataType:'json',
		contentType: 'application/json', 
		type:'POST', 
		data: data,
		success: function(data, textStatus, XMLHttpRequest){
			for (key in data) {
				$("#response").append("<label>"+data[key]["label"]+"</label> [<label>"+data[key]["value"]+"</label>]");
			}
  		},
  		error: function(XMLHttpRequest, textStatus, errorThrown){
			$.rup.errorGestor("Se ha producido un error en la prueba");
  		} 
	});
});