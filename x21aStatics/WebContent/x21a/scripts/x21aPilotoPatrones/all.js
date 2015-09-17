jQuery(function($){
	
	$("#grid").rup_grid({
		hasMaint: true,
		width: 550,
		url: "../patrones/usuario",
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja"],
		colModel: [
			{ id:"grid_id", name: "id", label: "id", index: "id", width: "75", editable: true },
			{ name: "nombre", label: "nombre", index: "nombre", width: "150", editable: true },
			{ name: "apellido1", label: "apellido1", index: "apellido1", width: "150", editable: true },
			{ name: "apellido2", label: "apellido2", index: "apellido2", width: "150", editable: true },
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
	
	$("#maint").rup_maint({
		jQueryGrid: "grid",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true,
		showFeedback: false
	});
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "grid##ejie",
		width: 120,
		blank:""
	});
	$("#fechaAlta_search").rup_date();
	$("#fechaBaja_search").rup_date();
	
	
	$("#feedback").rup_feedback({ type: "ok", message:"Este es un ejemplo de <b>Feedback</b>"});
	
	$("#date").rup_date({
		changeMonth : false,
		changeYear	: false,
		numberOfMonths : 1
	});
	
	$("#time").rup_time({});
	
	$('#comboProvincia').rup_combo({
		source : "comboEnlazadoSimple/remoteEnlazadoProvincia",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		selected: 3
	});
	$('#comboComarca').rup_combo({
		parent: ["comboProvincia"],
		source : "comboEnlazadoSimple/remoteEnlazadoComarca",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"}
	});
	
	$("#autocomplete").rup_autocomplete({
		source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]
	});
	
	$("#tooltip").rup_tooltip(); 
	
	$("#tabs").rup_tabs({
		tabs : [
			{i18nCaption:"pestana0", tabs: [
				{i18nCaption:"sub01", url:"fragmento1"},
				{i18nCaption:"sub02", url:"fragmento1"}
			]},
			{i18nCaption:"pestana1", tabs: [
				{i18nCaption:"sub10", url:"tab2Fragment"},
				{i18nCaption:"sub11", url:"tab3Fragment"}
			]}
		]
	});
	
	//dialog
	$("#dialog").bind("click", function () {
		$(document).rup_dialog({
			type: jQuery.rup.dialog.AJAX,
			url: "../patrones/allDialog" ,
				autoOpen: true,
				modal: true,
				width: "1200",
				height: "720",
				resizable: true,
				title: "Todos los patrones",
				buttons: [{
					text: "Aceptar",
					click: function () { 
						$(this).dialog("close");
					}					
				}],
			open : function(event, ui) { 
				//Configurar dialog
				$("#gridDialog").rup_grid({
					hasMaint: true,
					width: "550",
					url: "../patrones/usuario",
					pagerName: "pagerDialog",
					rowNum: "10",
					sortorder: "asc",
					sortname: "id",
					colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja"],
					colModel: [
						{ name: "id", label: "id", index: "id", width: "75", editable: true },
						{ name: "nombre", label: "nombre", index: "nombre", width: "150", editable: true },
						{ name: "apellido1", label: "apellido1", index: "apellido1", width: "150", editable: true },
						{ name: "apellido2", label: "apellido2", index: "apellido2", width: "150", editable: true },
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
				
				$("#maintDialog").rup_maint({
					jQueryGrid: "gridDialog",
					primaryKey: "id",
					modelObject: "Usuario",
					detailButtons: $.rup.maint.detailButtons.SAVE,
					searchForm: "searchFormDialog",
					showMessages: true,
					showFeedback: false
				});
				
				//Formulario de filtrado
				$('#dialog_ejie_search').rup_combo({
					source : [
					   {i18nCaption: "0", value:"0"},
					   {i18nCaption: "1", value:"1"}
					],
					i18nId: "gridDialog##ejie",
					width: 155,
					blank:""
				});
				$("#dialog_fechaAlta_search").rup_date();
				$("#dialog_fechaBaja_search").rup_date();
				
				
				$("#feedbackDialog").rup_feedback({ type: "ok", message:"Este es un ejemplo de <b>Feedback</b>"});
				
				$("#dateDialog").rup_date({
					changeMonth : false,
					changeYear	: false,
					numberOfMonths : 1
				});
				
				$("#timeDialog").rup_time({});
				
				$('#comboDialogProvincia').rup_combo({
					source : "comboEnlazadoSimple/remoteEnlazadoProvincia",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					selected: 3
				});
				$('#comboDialogComarca').rup_combo({
					parent: ["comboDialogProvincia"],
					source : "comboEnlazadoSimple/remoteEnlazadoComarca",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"}
				});
				
				$("#autocompleteDialog").rup_autocomplete({
					source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]
				});
				
				$("#tooltipDialog").rup_tooltip(); 
				
				$("#tabsDialog").rup_tabs({
					tabs : [
						{i18nCaption:"pestana0", tabs: [
							{i18nCaption:"sub01", url:"fragmento1"},
							{i18nCaption:"sub02", url:"fragmento1"}
						]},
						{i18nCaption:"pestana1", tabs: [
							{i18nCaption:"sub10", url:"tab2Fragment"},
							{i18nCaption:"sub11", url:"tab3Fragment"}
						]}
					]
				});
			}
		});	
	});
	
});