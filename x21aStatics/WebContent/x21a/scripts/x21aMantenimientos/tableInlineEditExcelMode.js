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
	
	$("#table").rup_table({
		url: "../jqGridUsuario",
		colNames: tableColNames,
		colModel: tableColModels,
		usePlugins:[
			"inlineEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter",
        	"search"
        ],
        editOptions:{
        	fillDataMethod:"clientSide"
        },
        primaryKey: "id",
        sortname: 'id',
        inlineEdit:{
        	autoEditRow: true
        },
        validate:{
			rules:{
				"nombre":{required:true},
				"apellido1":{required:true},
				"fechaAlta":{date:true},
				"fechaBaja":{date:true}
			}
		},
        filter:{
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	}
        }
	});
	
});