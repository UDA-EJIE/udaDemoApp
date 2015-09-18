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
		width: "600",
		url: "../alumno",
		pagerName: "pager",
		multiselect: true,
		rowNum: "10",
		sortorder: "asc",
		sortname: "idA",
		colNames: [
			"idA",
			"idB",
			"nombre",
			"apellido1",
			"apellido2",
			"fechaAlta"
		],
		colModel: [
			{ name: "idA",
				label: "idA",
				index: "idA",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "idB",
				label: "idB",
				index: "idB",
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
			},
			{ name: "fechaAlta",
				label: "fechaAlta",
				index: "fechaAlta",
				width: "150",
				editable: true,
				edittype: "text"
			}
        ]
		
	});
	

	$("#compuesta").rup_maint({
		jQueryGrid: "GRID_compuesta",
		primaryKey: "idA;idB",
		modelObject: "Alumno",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
				
	});
});