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
jQuery(function($){
	
	var tableColNames = [
	                     "Id",
			           "Nombre",
			           "Primer apellido",
			           "Segundo apellido",
			           "Ejie",
			           "Fecha alta",
			           "Fecha baja",
			           "Rol"
			],
		tableColModels = [
			{ name: "id", editable:true,  index: "id", width: 80},
			{ name: "nombre", editable:true,index: "nombre"},
			{ name: "apellido1", editable:true, index: "apellido1"},
			{ name: "apellido2",  editable:true, index: "apellido2"},
			{ name: "ejie", editable:true, index: "ejie", width: 60},
			{ name: "fechaAlta", editable:true,  index: "fecha_alta", width: 120},
			{ name: "fechaBaja", editable:true, index: "fecha_baja", width: 120},
			{ name: "rol", editable:true, index: "rol", width: 140}
        ];
	
	
	jQuery("#table").rup_table({
		url: "../cache",
		colNames: tableColNames,
		colModel: tableColModels,
        primaryKey:["id"],
        usePlugins:[
        	"feedback",
        	"fluid",
        	"inlineEdit"
        ],
        rowNum:10, 
        rowList:[10,20,30], 
        sortname: 'id'
        
	});
	
	
	jQuery("#btnRecargarTabla").on("click", function(){
		jQuery("#table").rup_table("reloadGrid");
	});
	
});