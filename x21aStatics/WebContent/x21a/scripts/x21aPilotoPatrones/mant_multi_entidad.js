jQuery(function($){
	$("#GRID_localidad").rup_grid({
		
		url: "../localidad",
		hasMaint: true,
		width: 650,
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "code",
		colNames: [ "code", "descEs", "descEu", "css", "provincia", "comarca"],
		colModel: [
		    //label: etiqueta del detalle
		    
			{ name: "code", index: "code", editable: true, 
				rupType: "integer"
			},
			{ name: "descEs", index: "desc"+$.rup_utils.capitalizedLang(), editable: true },
			
			{ name: "descEu", index: "desc"+$.rup_utils.capitalizedLang(), editable: true },
			{ name: "css", index: "css", editable: true },
			
//			//Definición para combo normal
//			{ name: "ejie", index: "ejie", editable: true,
//				editoptions: { //Define parseo 
//					value:{"1":"Sí","0":"No"} 
//				}, 
//				formatter: "select", //Aplicar parseo
//				edittype: "select" //Aplica combo en detalle
//			},
//			Definición para rup_combo 
			{ name:"comarca.provincia.code", jsonmap: "comarca.provincia.code", index: "comarca.provincia.descEs", editable: true,
				editoptions: {
					source : "../provincia",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					i18nId: "GRID_localidad	##ejie",
					width: 180,
					blank: ""
				},
				formatter: "rup_combo",
				rupType: "combo"},
			{ name:"comarca.code", jsonmap: "comarca.code", index: "comarca.descEs", editable: true,
				editoptions: {
					parent: [ "detailForm_localidad_comarca_provincia_code" ],
					source : "../comarca",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					i18nId: "GRID_localidad##ejie",
					width: 180,
					blank: ""
				},
				formatter: "rup_combo",
				rupType: "combo"
			}
        ]
	});
	

	$("#localidad").rup_maint({
		jQueryGrid: "GRID_localidad",
		primaryKey: "code",
		modelObject: "Localidad",
		detailButtons: $.rup.maint.detailButtons.SAVE_REPEAT,
		searchForm: "searchForm",
		showMessages: true
	});
	
//	$('#'+$.rup_utils.escapeId("a.provincia_search")).rup_combo({
	$('#provincia_search').rup_combo({
			source : "../provincia",
			sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
			i18nId: "GRID_localidad##ejie",
			width: 180,
			blank: ""
		});
	
	$('#comarca_search').rup_combo({
		parent: [ "provincia_search" ],
		source : "../comarca",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		i18nId: "GRID_localidad##ejie",
		width: 180,
		blank: ""
	});
	
	
});