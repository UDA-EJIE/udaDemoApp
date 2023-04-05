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

function _init() {
    'use strict';
    
    //FILTRO Y DETALLE
    const allowedPluginsBySelecionType = {
    	multiSelection: ['colReorder', 'seeker', 'buttons', 'groups', 'multiFilter', 'simpleFilter', 'noFilter', 'multiSelection', 'editForm', 'inlineEdit', 'editFormTargetBlank', 'noEdit', 'triggers', 'multipart'],
    	selection: ['colReorder', 'seeker', 'buttons', 'groups', 'multiFilter', 'simpleFilter', 'noFilter', 'selection', 'editForm', 'inlineEdit', 'editFormTargetBlank', 'noEdit', 'triggers', 'multipart'],
    	noSelection: ['colReorder', 'seeker', 'groups', 'multiFilter', 'simpleFilter', 'noFilter', 'noSelection', 'noEdit', 'triggers']
    };

    function listaPlugins(num) {
    	if (num == '2') {
    		return 'colReorder2,seeker2,buttons2,simpleFilter2,multiSelection2,editForm2,';
    	} else {
    		return 'colReorder,seeker,buttons,simpleFilter,multiSelection,editForm,';
    	}
    }

    function loadTable(num) {
        num = (num ? num : '') + '';

        $('#aplicarCambios, #aplicarCambios2').click(function () {
        	for (let iteration = 1; iteration < 3; iteration++) {
        		const num = iteration === 1 ? '' : iteration + '';
        		
        		if (localStorage['plugins' + num] === undefined) {
                    localStorage['plugins' + num] = '';
                }

                let selectionType = $('#example_tableConfiguration' + num + ' input[name=\'selectionType\']:checked')[0].id;

                if (num.length > 0) {
                    // Comprobar si el identificador lleva el número al final y eliminarlo en caso de que lo lleve para que no falle al obtener la propiedad del objeto "allowedPluginsBySelecionType"
                    if (new RegExp(num + "$", "").test(selectionType)) {
                    	selectionType = selectionType.slice(0, -1);
                    }
                    
                    $.each($('#example_tableConfiguration' + num + ' .pluginsControl input'), function () {
                        if ($('#' + this.id).prop('checked') && allowedPluginsBySelecionType[selectionType].indexOf(this.id.substring(0, this.id.length - 1)) > -1) {
                            localStorage['plugins' + num] = localStorage['plugins' + num] + this.id + ',';
                        }
                    });
                } else {
                    $.each($('#example_tableConfiguration' + num + ' .pluginsControl input'), function () {
                        if ($('#' + this.id).prop('checked') && allowedPluginsBySelecionType[selectionType].indexOf(this.id) > -1) {
                            localStorage['plugins' + num] = localStorage['plugins' + num] + this.id + ',';
                        }
                    });
                }
        	}

            location.reload();
        });

        $('#example' + num).rup_table(loadPlugins(num));
    }


    function loadPlugins(num) {
        if (localStorage['plugins' + num] === undefined) { //si esta undefined es que es la primera vez.
            localStorage['plugins' + num] = listaPlugins(num);
        }

        var plugins = {};

        plugins.fixedHeader = {
            footer: false,
            header: true
        };
        plugins.selector = 'td';
        
        // FILTROS
        plugins.filter = {
            rules: {
                fechaAlta: 'date',
                fechaBaja: 'date'
            }
        };
        
        // Se usan comas para asegurarse de que la opción se busca de manera exacta.
        if (localStorage['plugins' + num].indexOf(',multiFilter' + num + ',') > -1) {
            plugins.multiFilter = {
                idFilter: 'generated',
                labelSize: 255,
                userFilter: 'udaPruebas'
            };
            
            if (num == "2") {
            	plugins.multiFilter.url = './../multiFilter';
            }
            
            $('#multiFilter' + num).prop('checked', true);
        } else {
            $('#multiFilter' + num).prop('checked', false);
        }
        
        if (localStorage['plugins' + num].indexOf(',simpleFilter' + num + ',') > -1) {
            $('#simpleFilter' + num).prop('checked', true);
        } else {
            $('#simpleFilter' + num).prop('checked', false);
        }
        
        if (localStorage['plugins' + num].indexOf(',noFilter' + num + ',') > -1) {
           $('#example' + num + '_filter_form').remove();
           $('#example' + num).attr('data-filter-form', '#example' + num + '_noFilter_form');
           $('#noFilter' + num).prop('checked', true);
           plugins.filter = 'noFilter';
        } else {
        	plugins.filter.id = 'example' + num + '_filter_form';
        	plugins.filter.filterToolbar = 'example' + num + '_filter_toolbar';
        	plugins.filter.collapsableLayerId = 'example' + num + '_filter_fieldset';
            $('#noFilter' + num).prop('checked', false);
        }
        
        // SELECCIÓN
        if (localStorage['plugins' + num].indexOf(',multiSelection' + num + ',') > -1) {
        	plugins.multiSelect = {
                style: 'multi'
            };
            $('#noSelection' + num).prop('checked', false);
            $('#selection' + num).prop('checked', false);
            $('#multiSelection' + num).prop('checked', true);

        } else {
            $('#multiSelection' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf(',selection' + num + ',') > -1) {
        	plugins.select = {
                activate: true
            };
            $('#noSelection' + num).prop('checked', false);
            $('#selection' + num).prop('checked', true);
        } else {
            $('#selection' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf(',noSelection' + num + ',') > -1) {
            $('#noSelection' + num).prop('checked', true);
        } else {
            $('#noSelection' + num).prop('checked', false);
        }
        
        // BOTONERA Y EDICIÓN
        if (localStorage['plugins' + num].indexOf(',buttons' + num + ',') > -1) {
        	plugins.buttons = {
                activate: true
            };
            $('#buttons' + num).prop('checked', true);
            
            //Reports
            plugins.buttons.report = {};
            plugins.buttons.report.reportsParams = [];
            plugins.buttons.report.reportsParams.push({
                'isInline': false
            });
            
            if (localStorage['plugins' + num].indexOf(',editForm' + num + ',') > -1) {
            	let formEdit = {
                    detailForm: '#example' + num + '_detail_div',
                    url: num == '2' ? './editFormDouble' : undefined,
                    validate: {
                        rules: {}
                    },
                    saveContinueEdit: false //true pasa a edición; false seguir añadiendo
                };
                formEdit.validate.rules['nombre' + num] = {
                    required: true
                };
                formEdit.validate.rules['apellido1' + num] = {
                    required: true
                };
                formEdit.validate.rules['fechaAlta' + num] = {
                    required: true
                };
                formEdit.validate.rules['fechaBaja' + num] = {
                    date: true
                };
                
                plugins.formEdit = formEdit;

                $('#editForm' + num).prop('checked', true);
            } else {
                $('#editForm' + num).prop('checked', false);
            }

            if (localStorage['plugins' + num].indexOf(',inlineEdit' + num + ',') > -1) {
            	let inlineEdit = {
            		url: num == '2' ? './inlineEditDouble' : undefined,
                    deselect: true,
                    validate: {
                        rules: {}
                    }
                };
                inlineEdit.validate.rules['nombre' + num] = {
                    required: true
                };
                inlineEdit.validate.rules['apellido1' + num] = {
                    required: true
                };
                inlineEdit.validate.rules['fechaAlta' + num] = {
                    required: true
                };
                inlineEdit.validate.rules['fechaBaja' + num] = {
                    date: true
                };
                plugins.inlineEdit = inlineEdit;

                $('#inlineEdit' + num).prop('checked', true);
            } else {
                $('#inlineEdit' + num).prop('checked', false);
            }
            
            if (localStorage['plugins' + num].indexOf(',editFormTargetBlank' + num + ',') > -1) {
            	// Crear botón para añadir registros desde una nueva pestaña
                const optionButtonEditFormTargetBlankAdd = {
                    text: function () {
                        return 'Añadir registro desde nueva pestaña';
                    },
                    id: 'example' + num + 'EditFormTargetBlankAdd', // Campo obligatorio si se quiere usar desde el contextMenu
                    className: 'btn-material-primary-high-emphasis table_toolbar_btnAdd',
                    displayRegex: /^\d+$/, // Se muestra siempre que sea un numero positivo o neutro
                    insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
                    type: 'add',
                    action: function () {
                    	window.open($('#editFormTargetBlank' + num).data('addNewWindowUrl'), '_blank');
                    }
                };
                
            	// Crear botón para editar registros desde una nueva pestaña
                const optionButtonEditFormTargetBlankEdit = {
                    text: function () {
                        return 'Editar registro desde nueva pestaña';
                    },
                    id: 'example' + num + 'EditFormTargetBlankEdit', // Campo obligatorio si se quiere usar desde el contextMenu
                    className: 'btn-material-primary-high-emphasis table_toolbar_btnEdit',
                    displayRegex: /^[1-9][0-9]*$/, // Se muestra siempre que sea un numero mayor a 0
                    insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
                    type: 'edit',
                    action: function (e, dt) {
                    	const ctx = dt.context[0];
	                    	
						window.open($('#editFormTargetBlank' + num).data('editNewWindowUrl') + ctx.multiselection.lastSelectedId, '_blank');
                    }
                };
                
                plugins.noEdit = true;
                
                if (plugins.buttons.myButtons == undefined) {
                	plugins.buttons.myButtons = [];
                }
                plugins.buttons.myButtons.push(optionButtonEditFormTargetBlankAdd, optionButtonEditFormTargetBlankEdit);
                
                $('#editFormTargetBlank' + num).prop('checked', true);
            } else {
                $('#editFormTargetBlank' + num).prop('checked', false);
            }
        	
            if (localStorage['plugins' + num].indexOf(',noEdit' + num + ',') > -1) {
            	plugins.noEdit = true;
                $('#noEdit' + num).prop('checked', true);
            } else {
                $('#noEdit' + num).prop('checked', false);
            }
            
            if (localStorage['plugins' + num].indexOf(',multipart' + num + ',') > -1 && localStorage['plugins' + num].indexOf(',editForm' + num + ',') > -1) {
            	plugins.formEdit.direct = true;
            	plugins.formEdit.multipart = true;
            	plugins.formEdit.url = num == '2' ? './editFormDoubleMultipart' : './editFormMultipart';
            	plugins.formEdit.addUrl = '/addMultipart';
            	plugins.formEdit.editUrl = '/editMultipart';
            	plugins.formEdit.data = {};
                
                $('#multipart' + num).prop('checked', true);
            } else {
            	$('#multipart' + num).prop('checked', false);
            }
        } else {
            $('#buttons' + num).prop('checked', false);
        	$('#noEdit' + num).prop('checked', true);
        	$('#multipart' + num).prop('checked', false);
        }
        
        // BUSCADOR
        if (localStorage['plugins' + num].indexOf(',seeker' + num + ',') > -1) {
        	plugins.seeker = {
                activate: true
            };
            $('#seeker' + num).prop('checked', true);
        } else {
            $('#seeker' + num).prop('checked', false);
        }

        // REORDENACIÓN DE COLUMNAS
        if (localStorage['plugins' + num].indexOf('colReorder' + num + ',') > -1) {
        	plugins.colReorder = {
                fixedColumnsLeft: 1
            };
            $('#colReorder' + num).prop('checked', true);
        } else {
            $('#colReorder' + num).prop('checked', false);
        }

        // AGRUPACIÓN
        if (localStorage['plugins' + num].indexOf(',groups' + num + ',') > -1) {
        	plugins.rowGroup = {
                startRender: false,
                endRender: function (rows, group) {

                    return $('<tr/>')
                        .append('<td colspan="8"><b>' + group + ' - ' + rows[0].length + ' Elemento(s) </b></td>');
                },
                dataSrc: 'nombre'
            };
            $('#groups' + num).prop('checked', true);
        } else {
            $('#groups' + num).prop('checked', false);
        }

        // TRIGGERS
        if (localStorage['plugins' + num].indexOf(',triggers' + num + ',') > -1) {
            window.cargarPruebasTriggers();
            $('#triggers' + num).prop('checked', true);
        } else {
            $('#triggers' + num).prop('checked', false);
        }

        plugins.columnDefs = [
        	{
	            'targets': [plugins.multiSelect !== undefined ? 5 : 4],
	            'render': function (data) {
	                if (data !== undefined && data !== null) {
	                    return data.replace(new RegExp('/', 'g'), '-');
	                }
	            }
	        }
        ];

        plugins.colModel = [
        	{
	            name: 'nombre',
	            index: 'nombre',
	            editable: true,
	            hidden: false
	        },
	        {
	            name: 'apellido1',
	            index: 'apellido1',
	            editable: true,
                hidden: false
	        },
	        { 
            	name: 'apellido2', 
            	index: 'apellido2', 
            	editable: true, 
            	hidden: true,
                rupType: 'autocomplete',
                editoptions: {
                	source : './apellidos',
                    sourceParam : {label: 'label', value: 'value'},
                    menuMaxHeight: 200,
                    minLength: 3,
                    combobox: true,
                    contains: true
                }
            },
	        {
	            name: 'ejie',
	            index: 'ejie',
	            editable: true,
	            hidden: false,
	            edittype: 'checkbox'
	        },
	        {
	            name: 'fechaAlta',
	            index: 'fechaAlta',
	            editable: true,
	            hidden: false,
	            rupType: 'date',
	            editoptions: {
	                labelMaskId: 'fecha-mask',
	                showButtonPanel: true,
	                showOtherMonths: true,
	                noWeekend: true
	            }
	        },
	        {
	            name: 'fechaBaja',
	            index: 'fechaBaja',
	            editable: true,
	            hidden: false,
	            rupType: 'date',
	            editoptions: {
	                labelMaskId: 'fecha-mask',
	                showButtonPanel: true,
	                showOtherMonths: true,
	                noWeekend: true
	            }
	        },
	        {
	            name: 'rol',
	            index: 'rol',
	            editable: true,
                hidden: false,
                rupType: 'combo',
                editoptions: {
                    source : './roles',
                    sourceParam : {label: 'label', value: 'value'},
                    blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                }
	        }
        ];

        delete localStorage['plugins' + num];
        return plugins;
    }

    jQuery(function ($) {
        //Formulario de filtrado
        $('[id="fechaAlta_filter_table"]').rup_date();
        $('[id="fechaBaja_filter_table"]').rup_date();
	    //--------------------------------------------
        $('[id="fechaAlta_filter_table2"]').rup_date();
        $('[id="fechaBaja_filter_table2"]').rup_date();
        
        window.initRupI18nPromise.then(function () {
        	//Formulario de filtrado
            $('#apellido2_filter_table').rup_autocomplete({
            	source : './apellidos',
            	sourceParam : {label: 'label', value: 'value'},
            	menuMaxHeight: 175,
            	minLength: 3,
            	combobox: true,
            	contains: true,
            	showDefault: true
            });
            //--------------------------------------------
            $('#apellido2_filter_table2').rup_autocomplete({
            	source : './apellidos',
            	sourceParam : {label: 'label', value: 'value'},
            	menuMaxHeight: 175,
            	minLength: 3,
            	combobox: true,
            	contains: true,
            	showDefault: true
            });
            
	        loadTable();
	        loadTable(2);
	
	        $('.contenedor').addClass('show');
        });
    });
}

_init();
