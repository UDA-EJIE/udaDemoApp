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
        primaryKey:["id"],
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter",
        	"search",
        	"report"
        ],
        rowNum:10, 
        rowList:[10,20,30], 
        sortname: 'id',
        core:{
        	operations:{
                "operacion1": {
                            name: "Operación 1", 
                            icon: "rup-icon rup-icon-new", 
                            enabled: function(){
                                 return true;
                            },
                            callback: function(key, options){
                                 alert("Operación 1");           
                            }
	              },
	              "operacion2": {
	                    name: "Operación 2", 
	                    icon: "rup-icon rup-icon-new", 
	                    enabled: function(){
	                         return true;
	                    },
	                    callback: function(key, options){
	                         alert("Operación 1");           
	                    }
	              }
          }
        },
        toolbar:{
        	showOperations:{
	    		operacion2:false
        	}
        },
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
        filter:{
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	}
        },
        search:{
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	}
        },
        report: options_table_report
	});
	
});