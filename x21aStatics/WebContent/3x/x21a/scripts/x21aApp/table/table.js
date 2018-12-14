/*!
 * Copyright 2012 E.J.I.E., S.A.
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
	
	//FILTRO Y DETALLE
	var combo = [
		   {rol: "---", codTipoSubsanacion:""},
		   {rol: "Administrador", codTipoSubsanacion:"administrador"},
		   {rol: "Desarrollador", codTipoSubsanacion:"desarrollador"},
		   {rol: "Espectador", codTipoSubsanacion:"espectador"},
		   {rol: "Informador", codTipoSubsanacion:"informador"},
		   {rol: "Manager", codTipoSubsanacion:"manager"}
		];

	var tableColModels = [
			{ name: "id", index: "id", editable:true, hidden:false, width: 80
				, formoptions:{rowpos:1, colpos:1}
			},
			{ name: "nombre", index: "nombre", editable:true, hidden:false
				, formoptions:{rowpos:2, colpos:1}
			},
			{ name: "apellido1", index: "apellido1", editable:true, hidden:false
				, formoptions:{rowpos:3, colpos:1}
				, classes:'ui-ellipsis'
			},
			{ name: "ejie", index: "ejie", editable:true, hidden:false, width: 60,
				edittype: "checkbox",
				formatter: "checkbox",
				rwdClasses:"hidden-xs hidden-sm hidden-md",
				align: "center",
				editoptions: {
					value:"1:0"
				},
				searchoptions:{
					rupType: "combo",
					source : [
					   {label: "---", value:""},
					   {label: "Si", value:"1"},
					   {label: "No", value:"0"}
					]
				}
				, formoptions:{rowpos:5, colpos:1}
			},
			{ name: "fechaAlta",  index: "fecha_alta", editable:true, hidden:false, width: 120,
				rupType: "date",
				rwdClasses:"hidden-xs hidden-sm hidden-md",
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
				, formoptions:{rowpos:2, colpos:2}
			},
			{ name: "fechaBaja", index: "fecha_baja", editable:false, width: 120,
				rupType: "date",
				rwdClasses:"hidden-xs hidden-sm hidden-md",
				editoptions:{
					labelMaskId : "fecha-mask",
					showButtonPanel : true,
					showOtherMonths : true,
					noWeekend : true
				}
				, formoptions:{rowpos:3, colpos:2}
			},
			{ name: "rol", index: "rol", editable:true, hidden:false, width: 140,
				rupType: "combo",
				rwdClasses:"hidden-xs hidden-sm hidden-md",
				formatter: "rup_combo",
				editoptions: {
					source: $.map(combo, function(elem){
						return {
							label: elem.rol,
							value: elem.codTipoSubsanacion
						};
						
					})
				}
				, formoptions:{rowpos:3, colpos:2}
			}
     ],
     options_ejie_combo = {
			source : [
			   {label: "---", value:""},
			   {i18nCaption: "0", value:"0"},
			   {i18nCaption: "1", value:"1"}
			],
			i18nId: "GRID_simple##ejie",
			width: 120
		},	
		options_role_combo = {
			source : [
			   {label: "---", value:""},
			   {label: $.rup.i18n.app["GRID_simple##rol"]["administrador"], value:"administrador"},
			   {label: $.rup.i18n.app["GRID_simple##rol"]["desarrollador"], value:"desarrollador"},
			   {label: $.rup.i18n.app["GRID_simple##rol"]["espectador"], value:"espectador"},
			   {label: $.rup.i18n.app["GRID_simple##rol"]["informador"], value:"informador"},
			   {label: $.rup.i18n.app["GRID_simple##rol"]["manager"], value:"manager"}
			]
		};
	

	//Formulario de filtrado
	jQuery("#ejie_filter_table").rup_combo(options_ejie_combo);
	jQuery('#rol_filter_table').rup_combo(options_role_combo);

	jQuery("#fechaAlta_filter_table").rup_date();
	jQuery("#fechaBaja_filter_table").rup_date();
	
	//Formulario de detalle
	jQuery("#fechaAlta_detail_table").rup_date();
	jQuery("#fechaBaja_detail_table").rup_date();
	
	jQuery("#rol_detail_table").rup_combo(options_role_combo);
	
	var listaPlugins = 'inlineEdit,colReorder,multiSelection,seeker,buttons,';
	
	var allowedPluginsBySelecionType = {
		multiSelection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'multiSelection','multiFilter','triggers','inlineEdit'],
		selection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'selection','multiFilter','triggers','inlineEdit'],
		noSelection: ['colReorder', 'seeker', 'groups', 'noSelection','multiFilter','triggers']
	};
	
	 
	function loadTable(){
		$('#example').rup_datatable(loadPlugins());
	}
	
	function loadPlugins(){

		if(localStorage.plugins === undefined){//si esta undefined es que es la primera vez.
			localStorage.plugins = listaPlugins;
		}
		
		var plugins = {};
        
		var fixedHeader = {
            footer: false,
            header:true
        };
	    plugins.fixedHeader = fixedHeader;
	  plugins.selector = 'td';
	/*    plugins.responsive =  {
            details: {
            	type: 'column',
            	target: 'td'
            }
        };*/
	    
	    var filter = {
		    	  id:"example_filter_form",
		    	  filterToolbar:"example_filter_toolbar",
		    	  collapsableLayerId:"example_filter_fieldset"
		    }
		    plugins.filter = filter;
	    
		if(localStorage.plugins.indexOf('multiSelection') > -1){
		    var multiSelect = {
	            style: 'multi'
	        };
		    plugins.multiSelect = multiSelect;
		    $('#noSelection').prop('checked', false);
		    $('#selection').prop('checked', false);
		    $('#multiSelection').prop('checked', true);

		}else{
			$('#multiSelection').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('selection') > -1){
		    var select = {
	            activate: true
	        };
		    plugins.select = select;
		    $('#noSelection').prop('checked', false);
		    $('#selection').prop('checked', true);
		}else{
			$('#selection').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('noSelection') > -1){
			$('#noSelection').prop('checked', true);
		}else{
			$('#noSelection').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('editForm') > -1){
	        var formEdit = {
	        	detailForm: "#example_detail_div",
	        	validate:{
	    			rules:{
	    				"id":{required:true},
	    				"nombre":{required:true},
	    				"apellido1":{required:true},
	    				"fechaAlta":{date:true},
	    				"fechaBaja":{date:true}
	    			}
	    		},
	    		titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')
	        }
		    plugins.formEdit = formEdit;

		    $('#editForm').prop('checked', true);
		}else{
			$('#selection').prop('checked', false);
			$('#editForm').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('inlineEdit') > -1){
	        var formEdit = {
	        	detailForm: "#example_detail_div",
	        	validate:{
	    			rules:{
	    				"id":{required:true},
	    				"nombre":{required:true},
	    				"apellido1":{required:true},
	    				"fechaAlta":{date:true},
	    				"fechaBaja":{date:true}
	    			}
	    		},
	    		titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')
	        }
	        var inlineEdit = {
		        	deselect: true,
		        	validate:{
		    			rules:{
		    				"id":{required:true},
		    				"nombre":{required:true},
		    				"apellido1":{required:true},
		    				"fechaAlta":{date:true},
		    				"fechaBaja":{date:true}
		    			}
		    		}
		        }
		    plugins.inlineEdit = inlineEdit;

		    $('#inlineEdit').prop('checked', true);
		}else{
			$('#inlineEdit').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('buttons') > -1){
		    var buttons = {
		            activate:    true
		        };
		    plugins.buttons = buttons;
		    $('#buttons').prop('checked', true);

		}else{
			$('#buttons').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('seeker') > -1){
		    var seeker = {
		    		colModel: tableColModels
		        };
		    plugins.seeker = seeker;
		    $('#seeker').prop('checked', true);
		}else{
			$('#seeker').prop('checked', false);
		}

		
		if(localStorage.plugins.indexOf('colReorder') > -1){
		    var colReorder = {
		    		fixedColumnsLeft: 1
		        };
		    plugins.colReorder = colReorder;
		    $('#colReorder').prop('checked', true);
		}else{
			$('#colReorder').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('groups') > -1){
		    var rowGroup = {
		    		startRender:false,
		    		endRender: function ( rows, group ) {
		 
		                return $('<tr/>')
		                    .append( '<td colspan="8"><b>'+group+' - '+rows[0].length+' Elemento(s) </b></td>' );
		            },
		    		dataSrc: 'nombre'
		        };
		    plugins.rowGroup = rowGroup;
		    $('#groups').prop('checked', true);
		}else{
			$('#groups').prop('checked', false);
		}
		
		if(localStorage.plugins.indexOf('multiFilter') > -1){
			plugins.multiFilter = { idFilter:"generated",labelSize:255,userFilter:"udaPruebas"};
			$('#multiFilter').prop('checked', true);
		}else{
			$('#multiFilter').prop('checked', false);
		}
		
		if(localStorage.plugins !== undefined && localStorage.plugins.indexOf('triggers') > -1){
			cargarPruebasTriggers();
			$('#triggers').prop('checked', true);
		}else{
			$('#triggers').prop('checked', false);
		}
		
		localStorage.clear();
		return plugins;
	}
	
	$("#example_aplicar").click(function(){
		if(localStorage.plugins === undefined){
			localStorage.plugins = '';
		}
		
		var selectionType = $("input[name = example_seleccionTabla]:checked")[0].id;
		
		$.each($("#example_tableConfiguration .pluginsControl input"), function() {
			if($('#'+this.id).prop('checked') && allowedPluginsBySelecionType[selectionType].indexOf(this.id) > -1){
				localStorage.plugins = localStorage.plugins+this.id+",";
			}
		});
		
		location.reload();
	});
	
	loadTable();	

});