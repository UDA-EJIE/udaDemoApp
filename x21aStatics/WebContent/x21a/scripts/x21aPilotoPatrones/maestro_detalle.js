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
	
	$("#GRID_comarca").rup_grid({
		hasMaint: true,
		width: 950,
		url: "../experimental/comarca",
		pagerName: "pager_comarca",
		rowNum: "10",
		sortorder: "asc",
		sortname: "code",
		colNames: [
			"code",
			"codeProvincia",
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
				edittype: "text"
			},
			{ name: "provincia.codeProvincia",
				label: "codeProvincia",
				index: "codeProvincia",
				width: "150",
				editable: true,
				edittype: "text"
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
			}
        ]
	});

	$("#GRID_localidad").rup_grid({
		hasMaint: true,
		width: 950,
		url: "../experimental/localidad",
		pagerName: "pager_localidad",
		rowNum: "10",
		sortorder: "asc",
		sortname: "code",
		colNames: [
			"code",
			"codeComarca",
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
				edittype: "text"
			},
			{ name: "comarca.codeComarca",
				label: "codeComarca",
				index: "codeComarca",
				width: "150",
				editable: true,
				edittype: "text"
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
			}
        ]
		
	});
	
	$("#comarca").rup_maint({
		jQueryGrid: "GRID_comarca",
		primaryKey: "code",
		modelObject: "Comarca",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
	});

	$("#localidad").rup_maint({
		jQueryGrid: "GRID_localidad",
		primaryKey: "code;comarca.codeComarca",
		modelObject: "Localidad",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchFormDetalle",
		showMessages: true,
		parent:"comarca"
	});
	
});