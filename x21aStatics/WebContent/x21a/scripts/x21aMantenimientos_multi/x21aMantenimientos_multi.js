jQuery(function($){
	$("#GRID_multi").rup_grid({
		
		hasMaint: true,
		width: "600",
		url: "../usuario",
		pagerName: "pager",
		multiselect: true,
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
		colNames: [
			"id",
			"nombre",
			"apellido1",
			"apellido2",
			"ejie",
			"fechaAlta",
			"fechaBaja"
		],
		colModel: [
			{ name: "id",
				label: "id",
				index: "id",
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
			{ name: "ejie",
				label: "ejie",
				index: "ejie",
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
			},
			{ name: "fechaBaja",
				label: "fechaBaja",
				index: "fechaBaja",
				width: "150",
				editable: true,
				edittype: "text"
			}
        ]
		
	});
	

	$("#EJIE_MAINT_multi").rup_maint({
		jQueryGrid: "GRID_multi",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
				
	});
});