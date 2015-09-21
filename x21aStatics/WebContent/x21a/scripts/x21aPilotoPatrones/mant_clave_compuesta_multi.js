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
	$("#GRID_compuesta").rup_grid({
		
		hasMaint: true,
		width: 950,
		url: "../multipk",
		pagerName: "pager",
		multiselect: true,
		rowNum: "10",
		sortorder: "asc",
		sortname: "ida",
		colNames: [
			"ida",
			"idb",
			"nombre",
			"apellido1",
			"apellido2"
		],
		colModel: [
			{ name: "ida",
				label: "ida",
				index: "ida",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "idb",
				label: "idb",
				index: "idb",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "nombre",
				label: "nombre",
				index: "nombre",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "apellido1",
				label: "apellido1",
				index: "apellido1",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "apellido2",
				label: "apellido2",
				index: "apellido2",
				width: "150",
				editable: true,
				edittype: "text"
			}
        ]
		
	});
	

	$("#compuesta").rup_maint({
		jQueryGrid: "GRID_compuesta",
		primaryKey: "ida;idb",
		modelObject: "MultiPk",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
				
	});
});