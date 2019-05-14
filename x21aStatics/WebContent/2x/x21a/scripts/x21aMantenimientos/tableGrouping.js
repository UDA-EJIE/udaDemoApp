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
	
	$("#table").rup_jqtable({
		url: "../jqGridUsuario",
		colNames: ["id","nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja","rol"],
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
				edittype: "checkbox",
				formatter: "checkbox",
				align: "center",
				editoptions: {
					value:"1:0"
				}
				, formoptions:{rowpos:5, colpos:1}
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
			},
			{ name: "rol", index: "rol", editable:true,
				rupType: "combo",
				editoptions: {
					source : [
					   {label: "---", value:""},
					   {label: $.rup.i18n.app["GRID_simple##rol"]["administrador"], value:"administrador"},
					   {label: $.rup.i18n.app["GRID_simple##rol"]["desarrollador"], value:"desarrollador"},
					   {label: $.rup.i18n.app["GRID_simple##rol"]["espectador"], value:"espectador"},
					   {label: $.rup.i18n.app["GRID_simple##rol"]["informador"], value:"informador"},
					   {label: $.rup.i18n.app["GRID_simple##rol"]["manager"], value:"manager"}
					]
				}
				, formoptions:{rowpos:3, colpos:2}
			}
        ],
	    grouping:true,
	    groupingView : {
	    	groupField : ['nombre'],
			groupSummary : [true],
			showSummaryOnHide : [true],
			groupText : ['<b>{0} - {1} Elemento(s)</b>']
	    },
	    usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
//        	"multiselection",
        	"fluid",
        	"filter",
        	"search",
        	"multifilter"
        ],
        editOptions:{
        	fillDataMethod:"clientSide"
        },
        primaryKey: "id",
        sortname: 'id',
        validate:{
			rules:{
				"nombre":{required:true},
				"apellido1":{required:true}
			}
		}, multifilter:{ idFilter:"formGrouping",labelSize:255}
	});
	
	
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