jQuery(function($){
	$("#GRID_perfilUsuario").rup_grid({
		
		hasMaint: true,
		width: "1024",
		url: "../perfilusuario",
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
//		editable:true,
		colNames: [
			"id",
//			"idUsuario"
			"codeLocalidad",
			"codeComarca",
			"codeProvincia",
			"userId",
			"pass",
			"activo",
			"tipo",
			"descripcion",
			"fechaAlta",
			"fechaBaja",
			"horaEntrada"
//			"imagenPerfil"
		],
		colModel: [
			{ name: "id",
				label: "id",
				index: "id",
				width: "150",
				editable: true,
				edittype: "text",
				key: true 
				
			},
//			{ name: "usuario.id",
//				jsonmap:"usuario.id",
//				label: "idUsuario",
//				index: "idUsuario",
//				width: "150",
//				editable: true,
//				editoptions: {
//					source : "../usuario",
//					sourceParam : {label:"nombre", value:"id", style:"css"},
//					valueName: "usuario.id",
//					labelName: "usuario.apellido1"
//				},
////				formatter : function(value, options, rowData){
////					return rowData['usuario']['apellido1']+" "+rowData['usuario']['apellido2']+", "+rowData['usuario']['nombre'];
////				},
//				formatter: "rup_combo",
//				rupType: "autocomplete"
//			},
			{ name: "provincia.code",
				jsonmap:"provincia.code",
				label: "codeProvincia",
				index: "codeProvincia",
				width: "150",
				editable: true,
				editoptions: {
					source : "../provincia",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					width: 180,
					blank: ""
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
			{ name: "comarca.code",
				jsonmap:"comarca.code",
				label: "codeComarca",
				index: "codeComarca",
				width: "150",
				editable: true,
				editoptions: {
					source : "../comarca",
					parent: [ "provincia.code"],
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					width: 180,
					blank: ""
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
			{ name: "localidad.code",
				jsonmap:"localidad.code",
				label: "codeLocalidad",
				index: "codeLocalidad",
				width: "150",
				editable: true,
				editoptions: {
					source : "../localidad",
					parent: [ "comarca.code"],
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					width: 180,
					blank: ""
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
			{ name: "userId",
				label: "userId",
				index: "userId",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "pass",
				label: "pass",
				index: "pass",
				width: "150",
				editable: true,
				edittype: "password"
			},
			{ name: "activo",
				label: "activo",
				index: "activo",
				width: "150",
				editable: true,
				edittype: "checkbox",
				editoptions: {value:"S:N"}
			},
			{ name: "tipo",
				label: "tipo",
				index: "tipo",
				width: "150",
				editable: true,
				edittype: "text"
			},
			{ name: "descripcion",
				label: "descripcion",
				index: "descripcion",
				width: "150",
				editable: false,
				edittype: "textarea"
			},
			{ name: "fechaAlta",
				label: "fechaAlta",
				index: "fechaAlta",
				width: "150",
				editable: false,
				rupType: "date",
				formatter: "date",
				formatoptions:{newformat:"RupDateTime"},
				editoptions:{
					datetimepicker:true,
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true,
					showSecond: false,
					dateFormat: "dd/mm/yyyy",
					timeFormat: 'hh:mm'
					
				}
				
			},
			{ name: "fechaBaja",
				label: "fechaBaja",
				index: "fechaBaja",
				width: "150",
				editable: true,
				rupType: "date",
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
			},
			{ name: "horaEntrada",
				label: "horaEntrada",
				index: "horaEntrada",
				width: "150",
				editable: false,
				rupType: "time",
				formatter: "rup_time",
				formatoptions:{newformat:"RupTime"},
				editoptions:{
					showTime: true,
					ampm : false,
//					showSecond:true,
					timeFormat: 'hh:mm'
				}
			}
//			,
//			{ name: "imagenPerfil",
//				label: "imagenPerfil",
//				index: "imagenPerfil",
//				width: "150",
//				editable: true,
//				edittype:"file"
//			}
        ]
		
	});
	

	$("#perfilUsuario").rup_maint({
		jQueryGrid: "GRID_perfilUsuario",
		primaryKey: "id",
		modelObject: "PerfilUsuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true
	});
	
});