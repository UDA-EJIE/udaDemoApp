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