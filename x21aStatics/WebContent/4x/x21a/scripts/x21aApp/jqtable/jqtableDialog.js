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
		
    // No pueden resolverse resources i18n de rup hasta que haya terminado de cargarlos
    confLoaded.then(function() {
	
    	$("#tableDialog").rup_jqtable({
    		url: "../jqGridUsuario",
    		colNames: tableColNames,
    		colModel: tableColModels,
            primaryKey:["id"],
            loadOnStartUp: true,
            usePlugins:[
    			"formEdit",
            	"feedback",
    			"toolbar",
            	"contextMenu",
            	"responsive",
            	"filter",
            	"search",
            	"report"
    //        	,"multifilter"
            ],
            rowNum:10, 
            rowList:[10,20,30], 
            sortname: 'id',
            core:{
            	operations:{
            		"nuevaOpcion1": {
    					name: "Nueva Opcion 1", 
    					icon: "rup-icon rup-icon-new", 
    					enabled: function(){
    						return true;
    					},
    					callback: function(key, options){
    						alert("Nueva Opcion 1");		
    					}
    				},
    				"nuevaOpcion2": {
    					name: "Nueva Opcion 2", 
    					icon: "rup-icon rup-icon-new", 
    					enabled: function(){
    						return true;
    					},
    					callback: function(key, options){
    						alert("Nueva Opcion 1");		
    					}
    				},
    				"nuevaOpcion3": {
    					name: "Nueva Opcion 3", 
    					icon: "rup-icon rup-icon-new", 
    					enabled: function(){
    						return true;
    					},
    					callback: function(key, options){
    						alert("Nueva Opcion 1");		
    					}
    				}
            	}
            },
            toolbar:{
            	defaultButtons:{
    	        	nuevaOpcion1:true,
    	        	nuevaOpcion2:true,
    	        	nuevaOpcion3:true
            	}
            },
            contextMenu:{
            	defaultRowOperations:{
            		nuevaOpcion1:true,
    	        	nuevaOpcion2:true,
    	        	nuevaOpcion3:true
            	}
            },
            formEdit:{
            	detailForm: "#tableDialog_detail_div",
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
            }
            ,report:{
    			buttons:[
    				{id:"reports", i18nCaption:"Informes", 
    					buttons:[
    						{ i18nCaption:"CSV", css:"csv", 
    							url: "../jqGridUsuario/csvReport"
    						},
    						{ i18nCaption:"XLS", css:"xls", 
    							url: "../jqGridUsuario/xlsReport"
    						},
    						{ i18nCaption:"XLXS", css:"xls",
    							url: "../jqGridUsuario/xlsxReport" 
    						},
    						{ i18nCaption:"ODS", css:"ods", 
    							url: "../jqGridUsuario/odsReport"
    						},
    						{ i18nCaption:"PDF", css:"pdf", 
    							url: "../jqGridUsuario/pdfReport"
    						},
    						{ i18nCaption:"PDF_inLine", css:"pdf", 
    							url: "../jqGridUsuario/pdfReport"
    							, isInline:true
    						}
    					 ]}
    			]
    		}, multifilter:{ idFilter:"dialog",labelSize:255}
    	});
    });
	
	jQuery("#tableDialog_layer_div").rup_dialog({
		type: $.rup.dialog.DIV,
		autoOpen: false,
		width: 1200
	});
	
	jQuery("#btnTablaDialog_div").on("click", function(event){
		jQuery("#tableDialog_layer_div").rup_dialog("open");
	});
	
	jQuery("#btnTablaDialog_ajax").on("click", function(event){
		jQuery("#tableDialog_layer_div").rup_dialog({
			type: $.rup.dialog.AJAX,
			url:"./dialogAjax",
			autoOpen:true,
			width: 1200
		});
	});
	
	
	
});