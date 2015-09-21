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
        model:"Usuario",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter",
        	"search",
        	"multiselection"
        ],
        primaryKey: ["id"],
        sortname: 'id',
        formEdit:{
        	detailForm: "#table_detail_div",
        	validate:{
    			rules:{
    				"nombre":{required:true},
    				"apellido1":{required:true},
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
    		}
        },
		multiselection: {
//			headerContextMenu: {
//				items: {
//		            "edit": {name: "Edit", icon: "edit", accesskey: "e"},
//		            "cut": {name: "Cut", icon: "cut", accesskey: "c"},
//		            // first unused character is taken (here: o)
//		            "copy": {name: "Copy", icon: "copy", accesskey: "c o p y"},
//		            // words are truncated to their first letter (here: p)
//		            "paste": {name: "Paste", icon: "paste", accesskey: "cool paste"},
//		            "delete": {name: "Delete", icon: "delete"},
//		            "sep1": "---------",
//		            "quit": {name: "Quit", icon: "quit"}
//		        }
//			}
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