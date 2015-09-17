jQuery(function($){
	$("#GRID_multi").rup_grid({
		multiselect: true,
		
		url: "../usuario",
		hasMaint: true,
		width: 650,
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja" ],
		colModel: [
		    //label: etiqueta del detalle
		    
			{ name: "id", index: "id", editable: true, 
				rupType: "integer", 
				key: true 
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
					source : [
						   {i18nCaption: "0", value:"0"},
						   {i18nCaption: "1", value:"1"}
					]
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
			{ name: "fechaAlta",  index: "fechaAlta", editable: true,
				rupType: "datepicker"
			},
			{ name: "fechaBaja", index: "fechaBaja", editable: true,
				rupType: "datepicker" 
			}
        ]
	});
	

	$("#multi").rup_maint({
		jQueryGrid: "GRID_multi",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
	});
	
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "GRID_multi##ejie",
		width: 120
	});
	
	$("#fechaAlta_search").rup_date();
	$("#fechaBaja_search").rup_date();
});