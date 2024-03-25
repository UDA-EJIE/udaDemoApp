/*!
 * Copyright 2019 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, multipk_aplicar").click(function(){
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
jQuery(function($){
	initRupI18nPromise.then(function(){
        var combo = [
            {rol: "---", codTipoSubsanacion:""},
            {rol: "Administrador", codTipoSubsanacion:"administrador"},
            {rol: "Desarrollador", codTipoSubsanacion:"desarrollador"},
            {rol: "Espectador", codTipoSubsanacion:"espectador"},
            {rol: "Informador", codTipoSubsanacion:"informador"},
            {rol: "Manager", codTipoSubsanacion:"manager"}
         ];

         var tableColModelsSimple = [
 	        { name: "id", index: "id", editable:true, hidden:false
 	        },
 	        { name: "nombre", index: "nombre", editable:true, hidden:false
 	        },
 	        { name: "apellido1", index: "apellido1", editable:true, hidden:false
 	        },
 	    /*	{ name: "apellido2", index: "apellido2", editable:true, hidden:false
 	        },*/
 	        { name: "ejie", index: "ejie", editable:true, hidden:false/*,
 	            searchoptions:{
 	                rupType: "combo",
 	                source : [
 	                   {label: "---", value:""},
 	                   {label: "Si", value:"1"},
 	                   {label: "No", value:"0"}
 	                ]
 	            }*/
 	        },
 	        { name: "fechaAlta",  index: "fechaAlta", editable:true, hidden:false,
 	            rupType: "date",
 	            editoptions:{
 	                labelMaskId : "fecha-mask",
 	                showButtonPanel : true,
 	                showOtherMonths : true,
 	                noWeekend : true
 	            }
 	        },
 	        { name: "fechaBaja", index: "fechaBaja", editable:false, hidden:false,
 	            rupType: "date",
 	            editoptions:{
 	                labelMaskId : "fecha-mask",
 	                showButtonPanel : true,
 	                showOtherMonths : true,
 	                noWeekend : true
 	            }
 	        },
 	        { name: "rol", index: "rol", editable:true, hidden:false,
 	            rupType: "combo",
 	            editoptions: {
 	                data: $.map(combo, function(elem){
 	                    return {
 	                        text: elem.rol,
 	                        id: elem.codTipoSubsanacion
 	                    };
 	
 	                })
 	            }
 	        }
         ],
 		options_ejie_select = {
 		    data: [
 		       {text: "---", id: ""},
 		       {i18nCaption: "0", id: "0"},
 		       {i18nCaption: "1", id: "1"}
 		    ],
 		    i18nId: "GRID_simple##ejie"
         },
         options_role_select = {
             data : [
                {text: "---", value: ""},
                {text: $.rup.i18n.app["GRID_simple##rol"]["administrador"], id: "administrador"},
                {text: $.rup.i18n.app["GRID_simple##rol"]["desarrollador"], id: "desarrollador"},
                {text: $.rup.i18n.app["GRID_simple##rol"]["espectador"], id: "espectador"},
                {text: $.rup.i18n.app["GRID_simple##rol"]["informador"], id: "informador"},
                {text: $.rup.i18n.app["GRID_simple##rol"]["manager"], id: "manager"}
             ]
         };


         //Formulario de filtrado
        
         //jQuery("#ejie_filter_table").rup_select(options_ejie_select);
         //jQuery('#rol_filter_table').rup_select(options_role_select);

         //jQuery("#fechaAlta_filter_table").rup_date();
         //jQuery("#fechaBaja_filter_table").rup_date();

         //Formulario de detalle
         //jQuery("#fechaAlta_detail_table").rup_date();
         //jQuery("#fechaBaja_detail_table").rup_date();

         //jQuery("#rol_detail_table").rup_select(options_role_select)	
		
	var tableColModels = [
		{ name: "ida", index: "ida", editable:true, hidden:false
		},
		{ name: "idb", index: "idb", editable:true, hidden:false
		},
		{ name: "nombre", index: "nombre", editable:true, hidden:false
		},
		{ name: "apellido1", index: "apellido1", editable:true, hidden:false
		},
		{ name: "apellido2", index: "apellido2", editable:true, hidden:false
		}
	];
         
     function loadTable(){
         $('#example').rup_table(loadPluginsSimple());
      }
	
	function loadTableMultiPk(){
		$('#MultiPk').rup_table(loadPlugins());
	}
	
    function loadPluginsSimple(){


        var plugins = {};
            
		plugins.enableDynamicForms = true;

        var fixedHeader = {
            footer: false,
            header:true
        };
        plugins.fixedHeader = fixedHeader;
      plugins.selector = 'td';
      
      plugins.feedback = {
    	      closeLink: true,
    	      delay: 2000,
    	      block: false,
    	      gotoTop: false
    	    };
      //se puede añadir una funcion
      //plugins.feedback.customGoTo = function prueba (){return $('#example_containerToolbar').offset().top}

        var filter = {
                  id:"example_filter_form",
                  filterToolbar:"example_filter_toolbar",
                  collapsableLayerId:"example_filter_fieldset"
            }
            plugins.filter = filter;


            var multiSelect = {
                style: 'multi'
            };
            plugins.multiSelect = multiSelect;

        	function cancelClicked() { console.log("Ha cancelado eliminar."); };
      
            var formEdit = {
                detailForm: "#example_detail_div",
                validate:{
                    rules:{
                        "id":{required:true},
                        "nombre":{required:true},
                        "apellido1":{required:true},
                        "fechaAlta":{required:true},
                        "fechaBaja":{date:true}
                    }
                },
                titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_jqtable.edit.editCaption'),
                cancelDeleteFunction:cancelClicked
            }
            plugins.formEdit = formEdit;


            var buttons = {
                    activate:    true
                };
            plugins.buttons = buttons;



            var seeker = {
                    activate: true
                };
            plugins.seeker = seeker;



        //Col model es obligatorio,se mete como generico
        plugins.colModel = tableColModelsSimple;


        return plugins;
    }
	
	function loadPlugins(){

		
		var plugins = {};
		
		plugins.primaryKey = "ida;idb";
		plugins.loadOnStartUp = true;
        
		var fixedHeader = {
            footer: false,
            header:true
        };
	    plugins.fixedHeader = fixedHeader;
	    
	    var filter = {
		    	  id:"MultiPk_filter_form",
		    	  filterToolbar:"MultiPk_filter_toolbar",
		    	  collapsableLayerId:"MultiPk_filter_fieldset"
		    }
		plugins.filter = filter;
	    

		    var multiSelect = {
	            style:    'multi'
	        };
		    plugins.multiSelect = multiSelect;

	        var formEdit = {
	            	detailForm: "#MultiPk_detail_div",
	    			fillDataMethod: "clientSide",
	             	validate:{ 
	        			rules:{
	        				"ida":{
	    						required: true
	        					},
	        				"idb":{
	    						required: true
	        					},
	        				"nombre":{
	    						required: false
	        					},
	        				"apellido1":{
	    						required: false
	        					},
	        				"apellido2":{
	    						required: false
	        					}
	        				}
	        		   }
	        		 ,titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_jqtable.edit.editCaption')  
	        		}
		    plugins.formEdit = formEdit;

		
		    var buttons = {
		            activate:    true
		        };
		    plugins.buttons = buttons;

		
		    var seeker = {
		    		activate: true
		        };
		    plugins.seeker = seeker;


		

		    var colReorder = {
		    		fixedColumnsLeft: 1
		        };
		    plugins.colReorder = colReorder;

		
		//Col model es obligatorio,se mete como generico
		plugins.colModel = tableColModels;
		

		return plugins;
	}
	
	
	loadTableMultiPk();
	loadTable();

	$('.contenedor').addClass('show');
	});

});