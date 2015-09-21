/*!
 * Copyright 2013 E.J.I.E., S.A.
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
		url: "../jqGridUsuarioJerarquia",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja"],
		colModel: [
			{ name: "id", index: "id", editable:true, hidden: true
				, formoptions:{rowpos:3, colpos:1}
			},
			{ name: "nombre", index: "nombre", editable:true
				, formoptions:{rowpos:4, colpos:1}
			},
			{ name: "apellido1", index: "apellido1", editable:true
				, formoptions:{rowpos:2, colpos:1}
			},
			{ name: "apellido2", index: "apellido2", editable:true
				, formoptions:{rowpos:1, colpos:1}
			},
			{ name: "ejie", index: "ejie", editable:true, width: 60,
				edittype: "checkbox",
				formatter: "checkbox",
				align: "center",
				editoptions: {
					value:"1:0"
				},
//				searchoptions:{
//					rupType: "combo",
//					source : [
//					   {label: "---", value:""},
//					   {label: "Si", value:"1"},
//					   {label: "No", value:"0"}
//					]
//				},
				formoptions:{rowpos:5, colpos:1}
			},
			{ name: "fechaAlta",  index: "fechaAlta", editable:true,
				rupType: "date",
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
				, formoptions:{rowpos:2, colpos:2}
			},
			{ name: "fechaBaja", index: "fechaBaja", editable:true,
				rupType: "date",
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
				, formoptions:{rowpos:1, colpos:2}
			}
        ],
        usePlugins:[
 			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter",
        	"search",
        	"jerarquia",
        	"multiselection"
        ],
        editOptions:{ fillDataMethod:"clientSide" },
        primaryKey: ["id"],
        sortname: 'nombre',
        filter: {
        	url:"../jqGridUsuarioJerarquia/jerarquia/filter",
        	childrenUrl:"../jqGridUsuarioJerarquia/jerarquiaChildren",
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	}
        }
        , jerarquia: {
        	//token: '###',
        	column: 'nombre',
        	resetEvents: {
        		click: ["table_filter_filterButton", "table_filter_cleanLink"],
        		keydown : [ function(event){ if (event.keyCode === 13) { return false; } }, "table_filter_form" ]
        	},
        	contextMenu : true //(default)
        }
        , multiselection: {
        	headerContextMenu: { 
//        		deselectAll : false,
//        		items : {
//        			"aaa" : {name: "custom_a"},
//        			"bbb" : {name: "custom_b"}
//        		}
//        		,
//        		callback : function(){
//        			alert('customHeader');
//        		}
        	},
        	rowContextMenu: { 
//        		select_child : false,
//        		items : {
//        			"ccc" : {name: "custom_c"},
//        			"ddd" : {name: "custom_d"}
//        		}
//        		,
//        		callback : function(){
//        			alert('customRow');
//        		}
        	}
        }
	});
});