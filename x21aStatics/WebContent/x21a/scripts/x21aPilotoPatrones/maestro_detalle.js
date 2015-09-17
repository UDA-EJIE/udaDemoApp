jQuery(function($){
	
	$("#GRID_comarca").rup_grid({
		hasMaint: true,
		width: "600",
		url: "../experimental/comarca",
		pagerName: "pager_comarca",
		rowNum: "3",
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
		width: "600",
		url: "../experimental/localidad",
		pagerName: "pager_localidad",
		rowNum: "3",
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
	
	$("#EJIE_MAINT_comarca").rup_maint({
		jQueryGrid: "GRID_comarca",
		primaryKey: "code",
		modelObject: "Comarca",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
	});

	$("#EJIE_MAINT_localidad").rup_maint({
		jQueryGrid: "GRID_localidad",
		primaryKey: "code;codeComarca",
		modelObject: "Localidad",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true,
		parent:"EJIE_MAINT_comarca"
	});
	
});