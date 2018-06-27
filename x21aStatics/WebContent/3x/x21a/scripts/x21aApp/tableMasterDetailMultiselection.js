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
	
	$("#GRID_comarca").rup_table({
		url: "../jqGridComarca",
		pagerName: "pager_comarca",
		rowNum: "10",
		sortorder: "asc",
		sortname: "code",
		colNames: [
			"code",
			"descEs",
			"descEu",
			"css",
			"Provincia",
			"Provincia"
		],
		colModel: [
			{ name: "code",
				label: "code",
				index: "code",
				width: "150",
				editable: true,
				edittype: "text",
				key:true
			},
			{ name: "descEs",
				label: "descEs",
				index: "descEs",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "descEu",
				label: "descEu",
				index: "descEu",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "css",
				label: "css",
				index: "css",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "provincia.code",
				label: "provincia.code",
				index: "provincia.code",
				editable: true,
				hidden: true,
				edittype: "text",
				rupType: "combo",
				editoptions: {
					source : "../jqGridComarca/provincia",
					sourceParam : {label:"descEs", value:"code"},
					blank : "",
					edithidden:true
				},
				editrules:{
					edithidden:true
				}
			},
			{ name: "provincia.descEs",
				label: "provincia.descEs",
				index: "provincia.descEs",
				editable: false
			}
        ],
        usePlugins:[
 			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"responsive",
        	"filter",
        	"search",
        	"multifilter"
        ],
        editOptions:{
        	fillDataMethod:"clientSide"
        },
        fluid:{
        	baseLayer: "#comarca"
        },
        rowNum:10, 
        rowList:[10,20,30], 
        pager: "#pager_comarca", 
        primaryKey: ["code"],
//        multiselect: true,
        feedback:{
        	id:"#tableFeedback_comarca"
        },
        filter: {
        	id:"searchForm_comarca",
        	filterButtonId:"filterButton_comarca",
        	cleanLinkId:"cleanLink_comarca",
        	collapsableLayerId: "FIELDSET_SEARCH_comarca",
        	collapseButtonId: "toggle_search_form_comarca",
        	collapseLabelId: "toggle_search_form_label_comarca",
        	filterCriteriasId: "filter_params_comarca",
        	showHidden: true
        },
        toolbar: {
        	id: "toolbar_comarca"
        },
        masterDetail:{
        	detail: "#GRID_localidad"
        }
	});

	$("#GRID_localidad").rup_table({
		url: "../jqGridLocalidad",
		pagerName: "pager_localidad",
		rowNum: "10",
		sortorder: "asc",
		sortname: "code",
		colNames: [
			"code",
//			"codeComarca",
			"descEs",
			"descEu",
			"css"
		],
		colModel: [
			{ name: "code",
				label: "code",
				index: "code",
				width: "150",
				editable: true,
				edittype: "text",
				key:true
			},
//			{ name: "comarca.codeComarca",
//				label: "codeComarca",
//				index: "codeComarca",
//				width: "150",
//				editable: true,
//				edittype: "text"
//			},
			{ name: "descEs",
				label: "descEs",
				index: "descEs",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "descEu",
				label: "descEu",
				index: "descEu",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "css",
				label: "css",
				index: "css",
				width: "150",
				editable: true,
				edittype: "text"
			}
        ],
        usePlugins:["search", "formEdit", "masterDetail"],
        loadOnStartUp:false,
        editOptions:{
        	fillDataMethod:"clientSide"
        },
        fluid:{
        	baseLayer: "#localidad"
        },
        rowNum:10, 
        rowList:[10,20,30], 
        pager: "#pager_localidad", 
        primaryKey: "code",
        sortname: "code",
//        multiselect: true,
        feedback:{
        	id:"#tableFeedback_localidad"
        },
        filter: {
        	id:"searchForm_localidad",
        	filterButtonId:"filterButton_localidad",
        	cleanLinkId:"cleanLink_localidad",
        	collapsableLayerId: "FIELDSET_SEARCH_localidad",
        	collapseButtonId: "toggle_search_form_localidad",
        	collapseLabelId: "toggle_search_form_label_localidad",
        	filterCriteriasId: "filter_params_localidad",
        	showHidden: true
        },
        masterDetail:{
        	master:"#GRID_comarca",
        	masterPrimaryKey:"comarca.code"
        },
        toolbar: {
        	id: "toolbar_localidad"
        }
		
	});
});