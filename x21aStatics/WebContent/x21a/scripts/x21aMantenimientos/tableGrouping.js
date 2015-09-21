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
	$("#GRID_simple").rup_table({
		url: "../table",
		colNames: ["id","nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja"],
		colModel: [
			{ name: "id", index: "id", editable:true
				, formoptions:{rowpos:3, colpos:1},
				summaryType: "count",
				summaryTpl: $.rup.i18nParse($.rup.i18n.app.gridTree,"total")+" {0}"
//				, formoptions:{colpos:1}
			},
			{ name: "nombre", index: "nombre", editable:true
				, formoptions:{rowpos:4, colpos:1}
//				, formoptions:{colpos:1}
			},
			{ name: "apellido1", index: "apellido1", editable:true
				, formoptions:{rowpos:2, colpos:1}
//				, formoptions:{colpos:1}
			},
			{ name: "apellido2", index: "apellido2", editable:true
				, formoptions:{rowpos:1, colpos:1}
//				, formoptions:{colpos:1}
			},
			{ name: "ejie", index: "ejie", editable:true,
				rupType: "combo",
				editoptions: {
					source : [
					   {label: $.rup.i18n.app["GRID_simple##ejie"]["0"], value:"0"},
					   {label: $.rup.i18n.app["GRID_simple##ejie"]["1"], value:"1"}
					]
				}
				, formoptions:{rowpos:3, colpos:2},
				summaryType:resumenEspecifico,
				summaryTpl: $.rup.i18nParse($.rup.i18n.app.gridTree,"gana")+" {0}"
//				, formoptions:{colpos:2}
			},
			{ name: "fechaAlta",  index: "fechaAlta", editable:true,
				rupType: "date",
//				validationrules:{required:true, date:true},
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
				, formoptions:{rowpos:2, colpos:2},
				summaryType: maxFecha
//				, formoptions:{colpos:2}
			},
			{ name: "fechaBaja", index: "fechaBaja", editable:true,
				rupType: "date",
//				validationrules:{date:true},
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
				, formoptions:{rowpos:1, colpos:2},
				summaryType: minFecha 
//				, formoptions:{colpos:2}
			}
        ],
	    grouping:true,
	    groupingView : {
	    	groupField : ['apellido1'],
			groupSummary : [true],
			showSummaryOnHide : [true],
			groupText : ['<b>{0} - {1} Elemento(s)</b>']
	    },
        usePlugins:["formEdit", "multiselection"],
        editOptions:{
        	fillDataMethod:"clientSide"
        },
        fluid:{
        	baseLayer: "#simple"
        },
        rowNum:10, 
        rowList:[10,20,30], 
        pager: "#pager", 
        primaryKey: "id",
        sortname: 'id',
//        multiselect: true,
        feedback:{
        	id:"#tableFeedback",
        },
        filter: {
        	id:"searchForm",
        	filterButtonId:"filterButton",
        	cleanLinkId:"cleanLink",
        	collapsableLayerId: "FIELDSET_SEARCH_GRID_simple",
        	collapseButtonId: "toggle_search_form",
        	collapseLabelId: "toggle_search_form_label",
        	filterCriteriasId: "filter_params"
        },
        toolbar: {
        	id: "toolbar"
        },
        validate:{
			rules:{
				"nombre":{required:true},
				"apellido1":{required:true}
			}
		}
	});
	
//	jQuery("#searchForm").jqGrid('filterGrid','#GRID_simple');
//	$("#toolbar").rup_toolbar({
//		width: 1000,
//		buttons:[
//			{i18nCaption:"nuevo", css:"nuevo", click: function(){
//				var gr = jQuery("#GRID_simple").jqGrid('getGridParam','selrow'); 
//				 if( gr != null ) jQuery("#GRID_simple").jqGrid('editGridRow',gr,{
//					 height:280,reloadAfterSubmit:false
//				 }); 
//			}},
//			 {i18nCaption:"editar", css:"editar", click: function(){
//				 var gr = jQuery("#GRID_simple").jqGrid('getGridParam','selrow'); 
//				 if( gr != null ) jQuery("#GRID_simple").jqGrid('editGridRow',gr,{
//					 height:280,reloadAfterSubmit:false
//				 }); 
//			 }},
//			 {i18nCaption:"borrar", css:"borrar", click: function(){
//				 jQuery("#GRID_simple").rup_table("deleteElement");
//			 }},
//   			{i18nCaption:"filtrar", css:"filtrar", click: function(){
//
//   			}}
//		]
//	});
	
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "GRID_simple##ejie",
		width: 120,
		blank: ""
	});

	$("#fechaAlta_search").rup_date();
	$("#fechaBaja_search").rup_date();
	
	
	//funciones propias asociada al resumen de agrupacion
	function resumenEspecifico(val, name, record){
		if (val === ""){
			contador1 = 0;
			contador2 = 0;
		}
		if (record[name] === "0"){
			contador1 = contador1+1;
		} else if (record[name] === "1"){
			contador2 = contador2+1;
		}
		
		if (contador1 < contador2){
			return ("1");
		} else if (contador1 > contador2){
			return ("0");			
		} else {
			return ("-");
		}
	}
	
	function maxFecha(val, name, record){
		//Controlar valores nulos
		if (record[name] === null){
			return val;
		} 
		if(val !== undefined && val !==""){
			return(compFechas(val,record[name],true));
		} else {
			return(record[name]);
		}
	}
	
	function minFecha(val, name, record){
		//Controlar valores nulos
		if (record[name] === null){
			return val;
		} 
		if(val !== undefined && val !==""){
			return(compFechas(val,record[name],false));
		} else {
			return(record[name]);
		}
	}
	
	function compFechas (fecha1, fecha2, sup){
		var fech1 = fecha1.split("/");
		var fech2 = fecha2.split("/");
		
		if (sup === true){
			if ($.rup.lang === "eu"){
				if(fech1[0] > fech2[0]){
					return(fecha1);
				} else if (fech1[0] < fech2[0]){
					return(fecha2);
				} else {
					if(fech1[1] > fech2[1]){
						return(fecha1);
					} else if (fech1[1] < fech2[1]){
						return(fecha2);
					} else {
						if(fech1[2] > fech2[2]){
							return(fecha1);
						} else if (fech1[2] < fech2[2]){
							return(fecha2);
						} else {
							return(fecha1);
						}
					}				
				}
			} else {
				if(fech1[2] > fech2[2]){
					return(fecha1);
				} else if (fech1[2] < fech2[2]){
					return(fecha2);
				} else {
					if(fech1[1] > fech2[1]){
						return(fecha1);
					} else if (fech1[1] < fech2[1]){
						return(fecha2);
					} else {
						if(fech1[0] > fech2[0]){
							return(fecha1);
						} else if (fech1[0] < fech2[0]){
							return(fecha2);
						} else {
							return(fecha1);
						}
					}				
				}
			}
		} else {
			if ($.rup.lang === "eu"){
				if(fech1[0] < fech2[0]){
					return(fecha1);
				} else if (fech1[0] > fech2[0]){
					return(fecha2);
				} else {
					if(fech1[1] < fech2[1]){
						return(fecha1);
					} else if (fech1[1] > fech2[1]){
						return(fecha2);
					} else {
						if(fech1[2] < fech2[2]){
							return(fecha1);
						} else if (fech1[2] > fech2[2]){
							return(fecha2);
						} else {
							return(fecha1);
						}
					}				
				}
			} else {
				if(fech1[2] < fech2[2]){
					return(fecha1);
				} else if (fech1[2] > fech2[2]){
					return(fecha2);
				} else {
					if(fech1[1] < fech2[1]){
						return(fecha1);
					} else if (fech1[1] > fech2[1]){
						return(fecha2);
					} else {
						if(fech1[0] < fech2[0]){
							return(fecha1);
						} else if (fech1[0] > fech2[0]){
							return(fecha2);
						} else {
							return(fecha1);
						}
					}				
				}
			}
		}
	}
	
});