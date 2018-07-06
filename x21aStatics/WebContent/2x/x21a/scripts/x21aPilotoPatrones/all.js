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
			{ name: "nombre", label: "nombre", index: "nombre", width: "150", editable: true, classes: "context-menu_cur" },
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
		,pagerInline: false
		,loadComplete: function() {
            $("tr.jqgrow", $("#"+$("tr.jqgrow").parents("table").attr("id"))).contextMenu('contextMenu', {
                bindings: {
                    'menu_1': function(row, cell) { alert('menu_1'); },
                    'menu_2': function(row, cell) { alert('menu_2'); },
                    'menu_3': function(row, cell) { 
                    	//Gestión opción deshabilitada
                    	if ($('#menu_3').attr("disabled") === "disabled"){
                    		$("#jqContextMenu").show(); //Siempre es el mismo ID
                    		return false;
                    	}
                    	//Acción por defecto
                    	alert('menu_3'); }
                },
                onContextMenu: function(event) {
                	//Gestionar click sobre la fila que se muestra el menú	
                	var gridID = $(event.target).parents("table").attr("id");
            			$row = $(event.currentTarget),
            			rowId = $row.attr("id"),
                		rowData = $("#"+gridID).jqGrid("getRowData", rowId),
            			tdData = $(event.target).text(),
            			//control multiselect
            			checkbox = $row.find("input[id='jqg_"+gridID+"_"+rowId+"']");
            			
            		//Elemento sobre el que se despliega el menú siempre seleccionado
           			if (checkbox.length<0 || checkbox.attr("checked")===undefined){
           				$("#"+gridID).setSelection(rowId, true);
           			}

           			//Solo mostrar menú en columna "nombre"
                	if (rowData.nombre !== tdData){
                		$("#jqContextMenu").next().hide();
                		$("#jqContextMenu").hide();
                		return false;
                	}
                	
                	//Deshabilitar en filas pares
                    if ($row.index()%2 === 0) {
                        $('#menu_3').attr("disabled","disabled").addClass('ui-state-disabled');
                    } else {
                        $('#menu_3').removeAttr("disabled").removeClass('ui-state-disabled');
                    }
                    
                    //Gestionar eventos
                    $("#jqContextMenu").mouseleave (function(){ $(this).next().hide(); $(this).hide(); });
                    return true;
                }
            });
           	$('[aria-describedby="grid_nombre"]').css("cursor", "url("+$.rup.RUP+"/basic-theme/cursors/context-menu.cur),default" );

           	//Modificar tooltip
            $.each ($('[aria-describedby="grid_nombre"]'), function(index, object){
            	$(object).attr("rup_tooltip", $(object).attr("rup_tooltip") + "<br/>Esta columna tiene menú contextual asociado");
            });
		}
	});
	$('[aria-describedby="grid_nombre"]').rup_tooltip("option", "content.text", "pruebas");
	
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
	
	$('#multicombo_search').rup_combo({
		source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		ordered: false,
		width: 200,
		multiselect: true
	});
	
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
	
	$('#multicomboGruposRemoto').rup_combo({
		sourceGroup : "comboSimple/remoteGroup",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		width: 500,
		multiselect: true
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
		$("#maint").css("width","auto");
		$(document).rup_dialog({
			type: jQuery.rup.dialog.AJAX,
			url: "../patrones/allDialog" ,
				autoOpen: true,
				modal: true,
				width: "1200",
				height: "750",
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
						{ name: "nombre", label: "nombre", index: "nombre", width: "150", editable: true, classes: "context-menu_cur" },
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
					,pagerInline: false
					,loadComplete: function() {
			            $("tr.jqgrow", $("#"+$("tr.jqgrow").parents("table").attr("id"))).contextMenu('contextMenu', {
			                bindings: {
			                    'menu_1': function(row, cell) { alert('menu_1'); },
			                    'menu_2': function(row, cell) { alert('menu_2'); },
			                    'menu_3': function(row, cell) { 
			                    	//Gestión opción deshabilitada
			                    	if ($('#menu_3').attr("disabled") === "disabled"){
			                    		$("#jqContextMenu").show(); //Siempre es el mismo ID
			                    		return false;
			                    	}
			                    	//Acción por defecto
			                    	alert('menu_3'); }
			                },
			                onContextMenu: function(event) {
			                	//Gestionar click sobre la fila que se muestra el menú	
			                	var gridID = $(event.target).parents("table").attr("id");
			            			$row = $(event.currentTarget),
			            			rowId = $row.attr("id"),
			                		rowData = $("#"+gridID).jqGrid("getRowData", rowId),
			            			tdData = $(event.target).text(),
			            			//control multiselect
			            			checkbox = $row.find("input[id='jqg_"+gridID+"_"+rowId+"']");
			            			
			            		//Elemento sobre el que se despliega el menú siempre seleccionado
			           			if (checkbox.length<0 || checkbox.attr("checked")===undefined){
			           				$("#"+gridID).setSelection(rowId, true);
			           			}

			           			//Solo mostrar menú en columna "nombre"
			                	if (rowData.nombre !== tdData){
			                		$("#jqContextMenu").next().hide();
			                		$("#jqContextMenu").hide();
			                		return false;
			                	}
			                	
			                	//Deshabilitar en filas pares
			                    if ($row.index()%2 === 0) {
			                        $('#menu_3').attr("disabled","disabled").addClass('ui-state-disabled');
			                    } else {
			                        $('#menu_3').removeAttr("disabled").removeClass('ui-state-disabled');
			                    }
			                    
			                    //Gestionar eventos
			                    $("#jqContextMenu").mouseleave (function(){ $(this).next().hide(); $(this).hide(); });
			                    return true;
			                }
			            });
		            	$('[aria-describedby="grid_nombre"]').css("cursor", "url("+$.rup.RUP+"/basic-theme/cursors/context-menu.cur),default" );

			            //Modificar tooltip
			            $.each ($('[aria-describedby="grid_nombre"]'), function(index, object){
			            	$(object).attr("rup_tooltip", $(object).attr("rup_tooltip") + "<br/>Esta columna tiene menú contextual asociado");
			            });
					}
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
				
				$('#dialog_multicombo_search').rup_combo({
					source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
					ordered: false,
					width: 200,
					multiselect: true
				});
				
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
				
				$('#comboDialogComarcaDialog').rup_combo({
					parent: ["comboDialogProvincia"],
					source : "comboEnlazadoSimple/remoteEnlazadoComarca",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"}
				});
				
				$('#multicomboGruposRemotoDialog').rup_combo({
					sourceGroup : "comboSimple/remoteGroup",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					width: 500,
					multiselect: true
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
	
	$("#maint").css("width","auto");
});