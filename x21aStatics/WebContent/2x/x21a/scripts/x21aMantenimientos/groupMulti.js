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
	var contador1 = 0;
	var contador2 = 0;
	
	$("#GRID_group_multi").rup_grid({
		multiselect: true,
		
		url: "/x21aMantenimientosWar/usuario",
		hasMaint: true,
		width: 850,
		pagerName: "group_multi_pager",
		rowNum: "10",
		sortorder: "asc",
		pagerInline: false,
		sortname: "id",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja" ],
		colModel: [
		    //label: etiqueta del detalle
		    
			{ name: "id", index: "id",
				editable: true, 
				rupType: "integer", 
				key: true,
				summaryType: "count",
				summaryTpl: $.rup.i18nParse($.rup.i18n.app.gridTree,"total")+" {0}"
			},
			{ name: "nombre", index: "nombre", editable: true },
			{ name: "apellido1", index: "apellido1", editable: true },
			{ name: "apellido2", index: "apellido2", editable: true },
			
//			//Definición para combo normal
//			{ name: "ejie", index: "ejie", editable: true,
//				editoptions: { //Define parseo 
//					value:{"1":"Sí","0":"No"} 
//				}, 
//				formatter: "select", //Aplicar parseo
//				edittype: "select" //Aplica combo en detalle
//			},
//			Definición para rup_combo 
			{ name: "ejie", index: "ejie", editable: true,
				editoptions: {
					i18nId : "grid##ejie",
					source : [
						   {i18nCaption: "0", value:"0"},
						   {i18nCaption: "1", value:"1"}
					]
				},
				formatter: "rup_combo",
				rupType: "combo",
				summaryType:resumenEspecifico,
				summaryTpl: $.rup.i18nParse($.rup.i18n.app.gridTree,"gana")+" {0}"
			},
			{ name: "fechaAlta",  index: "fechaAlta", editable: true,
				rupType: "datepicker", summaryType: maxFecha
			},
			{ name: "fechaBaja", index: "fechaBaja", editable: true,
				rupType: "datepicker", summaryType: minFecha 
			}
        ],
	    grouping:true,
	    groupingView : {
	    	groupField : ['apellido1'],
			groupSummary : [true],
			showSummaryOnHide : [true]
	    }
	});
	

	$("#groupMulti").rup_maint({
		jQueryGrid: "GRID_group_multi",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "groupMultiSearchForm",
		showMessages: true
	});
	
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "grid##ejie",
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