/*!
 * Copyright 2019 E.J.I.E., S.A.
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
jQuery(function ($) {
	$('#example').on('tableEditFormAddEditBeforeShowForm', function (event, ctx) {
		console.log('Este es un ejemplo de trigger, para ver más activar la opción "Activar Triggers en Consola". ' + ctx.oInit.formEdit.actionType)
	});
	
    window.initRupI18nPromise.then(function () {
        //FILTRO Y DETALLE
        const tableColModels = [
        	{
                name: 'nombre',
                editable: true,
                hidden: false,
                orderable: false,
				editoptions: {
					maxlength: 20
				},
				searchoptions: {
					maxlength: 20
				}
            },
            {
                name: 'apellido1',
                editable: true,
                hidden: false,
            	rupType: 'select',
                editoptions: {
                	url: './apellidos',
                    sourceParam: {text: 'label', id: 'value'},
					autocomplete: true,
                    contains: true,
                    combo: true
                },
                searchoptions: {
                	url: './apellidos',
                    sourceParam: {text: 'label', id: 'value'},
					blank: '',
					autocomplete: true,
					searchZero: true,
					combo: true
                }
            },
            { 
            	name: "apellido2",
            	editable: true, 
            	hidden: false,
            	rupType: 'select',
                editoptions: {
                	url: './apellidos',
                    sourceParam: {text: 'label', id: 'value'},
					autocomplete: true,
                    contains: true,
                    combo: true
                },
                searchoptions: {
					parent: ['apellido1_example_seeker'],
                	url : './apellidos',
                    sourceParam : {text: 'label', id: 'value'},
					blank: '',
					autocomplete: true,
					searchZero: true,
					combo: true
                }
            },
            {
                name: 'ejie',
                editable: true,
                hidden: false,
                edittype: 'checkbox'
            },
            {
                name: 'fechaAlta',
                editable: true,
                hidden: false,
                rupType: 'date',
                editoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                },
                searchoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                }
            },
            {
                name: 'fechaBaja',
                editable: false,
                hidden: false,
                rupType: 'date',
                editoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                },
                searchoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                }
            },
            {
                name: 'rol',
                editable: true,
                hidden: false,
                rupType: 'select',
                editoptions: {
                    url : './roles',
                    sourceParam : {text: 'label', id: 'value'},
                    blank: ''
                },
                searchoptions: {
                    url : './roles',
                    sourceParam : {text: 'label', id: 'value'},
                    blank: ''
                }
            }
        ];

        const listaPlugins = 'colReorder,seeker,buttons,simpleFilter,multiSelection,editForm,';

        const allowedPluginsBySelecionType = {
            multiSelection: ['colReorder', 'seeker', 'buttons', 'groups', 'multiFilter', 'simpleFilter', 'noFilter', 'multiSelection', 'editForm', 'inlineEdit', 'editFormTargetBlank', 'noEdit', 'triggers', 'multipart'],
            selection: ['colReorder', 'seeker', 'buttons', 'groups', 'multiFilter', 'simpleFilter', 'noFilter', 'selection', 'editForm', 'inlineEdit', 'editFormTargetBlank', 'noEdit', 'triggers', 'multipart'],
            noSelection: ['colReorder', 'seeker', 'groups', 'multiFilter', 'simpleFilter', 'noFilter', 'noSelection', 'noEdit', 'triggers']
        };


        function loadTable() {
            $('#example').rup_table(loadPlugins());
        }

        function loadPlugins() {

            if (localStorage.plugins === undefined) { //si esta undefined es que es la primera vez.
                localStorage.plugins = listaPlugins;
            }

            var plugins = {};
            
            plugins.enableDynamicForms = true;

            plugins.fixedHeader = {
            	footer: false,
            	header: true
            };
            plugins.selector = 'td';
            /*plugins.responsive =  {
            	details: {
            		type: 'column',
            		target: 'td'
            	}
            };*/
            
            // FILTROS
            plugins.filter = {
                rules: {
                    fechaBaja: 'date'
                }
            }
            
            // Se usan comas para asegurarse de que la opción se busca de manera exacta.
            if (localStorage.plugins.indexOf(',multiFilter,') > -1) {
                plugins.multiFilter = {
                    idFilter: 'generated',
                    labelSize: 255,
                    userFilter: 'udaPruebas',
                    url: "/multiFilter"
                };
                $('#multiFilter').prop('checked', true);
            } else {
                $('#multiFilter').prop('checked', false);
            }

            if (localStorage.plugins.indexOf(',simpleFilter,') > -1) {
                $('#simpleFilter').prop('checked', true);
            } else {
                $('#simpleFilter').prop('checked', false);
            }
            
            if (localStorage.plugins.indexOf(',noFilter,') > -1) {
               $('#example_filter_form').remove();
               $('#example').attr('data-filter-form', '#example_noFilter_form');
               $('#noFilter').prop('checked', true);
               plugins.filter = 'noFilter';
            } else {
            	plugins.filter.id = 'example_filter_form';
            	plugins.filter.filterToolbar = 'example_filter_toolbar';
            	plugins.filter.collapsableLayerId = 'example_filter_fieldset';
                $('#noFilter').prop('checked', false);

				// Formulario de filtrado.
				$('#id_filter_table').rup_select({
					url: './allIds',
					sourceParam: { text: 'id', id: 'id' },
					autocomplete: true,
					combo: true
				});
				$('#apellido1_filter_table').rup_select({
					url: './apellidos',
					sourceParam: { text: 'label', id: 'value' },
					blank: '',
					placeholder: '[Seleccionar por favor...]',
					allowClear: true
				});
				$('#apellido2_filter_table').rup_select({
					url: './apellidos',
					sourceParam: { text: 'label', id: 'value' },
					blank: '',
					autocomplete: true,
					contains: true,
					combo: true
				});
				$('#fechaAlta_filter_table').rup_date({
					labelMaskId: 'fecha-mask',
					showButtonPanel: true,
					showOtherMonths: true,
					noWeekend: true
				});
				$('#fechaBaja_filter_table').rup_date();
            }
            
            // SELECCIÓN
            if (localStorage.plugins.indexOf(',multiSelection,') > -1) {
                plugins.multiSelect = {
                    style: 'multi'
                };
                $('#noSelection').prop('checked', false);
                $('#selection').prop('checked', false);
                $('#multiSelection').prop('checked', true);

            } else {
                $('#multiSelection').prop('checked', false);
            }

            if (localStorage.plugins.indexOf(',selection,') > -1) {
            	plugins.select = {
                    activate: true
                };
                $('#noSelection').prop('checked', false);
                $('#selection').prop('checked', true);
            } else {
                $('#selection').prop('checked', false);
            }

            if (localStorage.plugins.indexOf(',noSelection,') > -1) {
                $('#noSelection').prop('checked', true);
            } else {
                $('#noSelection').prop('checked', false);
            }
            
            // BOTONERA Y EDICIÓN
            if (localStorage.plugins.indexOf(',buttons,') > -1) {
            	plugins.buttons = {
                    activate: true,
                    //contextMenu: true
                };
                $('#buttons').prop('checked', true);
                
                // Reports
                plugins.buttons.report = {
                    title: 'Exportación (título personalizado)',
                    message: 'Su archivo está siendo generado... (mensaje personalizado)',
                    columns: ["id", "nombre", "apellido1", "ejie", "fechaAlta"],
                    columnsName: ["id2", "nombre2", "apellido1", "ejie", "fechaAlta"],
                    reportsParams: {
                        "isInline": false
                    }
                };

	            if (localStorage.plugins.indexOf(',editForm,') > -1) {
	            	const formEdit = {
	                    detailForm: {
							id: '#example_detail_div',
							customDialog: {
								width: 1000
							}
						},
	                    validate: {
	                        rules: {
	                            'nombre': {
	                                required: true
	                            },
	                            'apellido1': {
	                                required: true
	                            },
	                            'fechaAlta': {
	                                required: true
	                            },
	                            'fechaBaja': {
	                                date: true
	                            }
	                        }
	                    },
	                    customTitle: $.rup.i18nParse($.rup.i18n.app, 'table.sampleTitle'),
	                    cancelDeleteFunction: function () {
		                    console.log('Ha cancelado eliminar.');
		                }
	                };
	                plugins.validarModificarContinuar = function customGuardar(ctx){
	                	if($('#apellido1_detail_table').val() !== 'Ruiz'){
		                	//Ejemplo de validación personalizada
		                	 let idTableDetail = ctx.oInit.formEdit.detailForm.$dialog;
		                	 let feedback = idTableDetail.find('#' + ctx.sTableId + '_detail_feedback');
		                     try {//Se destruye para asegurar la inicialización.
		                         feedback.rup_feedback('destroy');
		                     } catch (ex) {
		                      
		                     }
		
		                     feedback.rup_feedback({
		                         message: $.rup.i18nParse($.rup.i18n.app, 'table.sampleValidate'),
		                         type: 'error',
		                         block: false,
		                         gotoTop: false,
		                         delay: 2000
		                     });
		                	return true;//no paso la validacion
	                	}
	                	return false;//Paso la validacion
	                };
	                
	                plugins.formEdit = formEdit;

                    // Introducir propiedad dropdownParent de rup_select para evitar que el diálogo solape el desplegable.
                    $.each(tableColModels, function (index, element) {
                        if (element.rupType === 'select' && element.editoptions) {
                            element.editoptions.dropdownParent = '.rup-dialog';
                        }
                    });
	
	                $('#editForm').prop('checked', true);
					$('#multipart').prop('disabled', false);
	            } else {
	                $('#editForm').prop('checked', false);
					$('#multipart').prop('disabled', true);
	            }
	
	            if (localStorage.plugins.indexOf(',inlineEdit,') > -1) {
	            	const inlineEdit = {
	                    deselect: true,
	                    validate: {
	                        rules: {
	                            'nombre': {
	                                required: true
	                            },
	                            'apellido1': {
	                                required: true
	                            },
	                            'fechaAlta': {
	                                required: true
	                            },
	                            'fechaBaja': {
	                                date: true
	                            }
	                        },
	                        messages: {
	                            required: 'Campo requerido'
	                        }
	                    }
	                };
	            	
	                plugins.inlineEdit = inlineEdit;
	
	                $('#inlineEdit').prop('checked', true);
	            } else {
	                $('#inlineEdit').prop('checked', false);
	            }
	            
	            if (localStorage.plugins.indexOf(',editFormTargetBlank,') > -1) {
	            	// Crear botón para añadir registros desde una nueva pestaña
	                const optionButtonEditFormTargetBlankAdd = {
	                    text: function () {
	                        return 'Añadir registro desde nueva pestaña';
	                    },
	                    id: 'exampleEditFormTargetBlankAdd', // Campo obligatorio si se quiere usar desde el contextMenu
	                    className: 'btn-material-primary-high-emphasis table_toolbar_btnAdd',
	                    displayRegex: /^\d+$/, // Se muestra siempre que sea un numero positivo o neutro
	                    insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
	                    type: 'add',
	                    action: function () {
	                    	window.open($('#editFormTargetBlank').data('addNewWindowUrl'), '_blank');
	                    }
	                };
	                
	            	// Crear botón para editar registros desde una nueva pestaña
	                const optionButtonEditFormTargetBlankEdit = {
	                    text: function () {
	                        return 'Editar registro desde nueva pestaña';
	                    },
	                    id: 'exampleEditFormTargetBlankEdit', // Campo obligatorio si se quiere usar desde el contextMenu
	                    className: 'btn-material-primary-high-emphasis table_toolbar_btnEdit',
	                    displayRegex: /^[1-9][0-9]*$/, // Se muestra siempre que sea un numero mayor a 0
	                    insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
	                    type: 'edit',
	                    action: function (e, dt) {
	                    	const ctx = dt.context[0];
	                    	
	                    	window.open($('#editFormTargetBlank').data('editNewWindowUrl') + ctx.multiselection.lastSelectedId, '_blank');
	                    }
	                };
	                
	                plugins.noEdit = true;
	                
	                if (plugins.buttons.myButtons == undefined) {
	                	plugins.buttons.myButtons = [];
	                }
	                plugins.buttons.myButtons.push(optionButtonEditFormTargetBlankAdd, optionButtonEditFormTargetBlankEdit);
	                
	                $('#editFormTargetBlank').prop('checked', true);
	            } else {
	                $('#editFormTargetBlank').prop('checked', false);
	            }
	
	            if (localStorage.plugins.indexOf(',noEdit,') > -1) {
	            	plugins.noEdit = true;
	                $('#noEdit').prop('checked', true);
	            } else {
	                $('#noEdit').prop('checked', false);
	            }
                
	            if (localStorage.plugins.indexOf(',multipart,') > -1 && localStorage.plugins.indexOf(',editForm,') > -1) {
                	plugins.formEdit.direct = true;
                	plugins.formEdit.multipart = true;
                	plugins.formEdit.url = './editFormMultipart';
                	plugins.formEdit.addUrl = '/addMultipart';
                	plugins.formEdit.editUrl = '/editMultipart';
                	plugins.formEdit.data = {};
                    
                    $('#multipart').prop('checked', true);
                } else {
                	$('#multipart').prop('checked', false);
                }
            } else {
            	$('#buttons').prop('checked', false);
            	$('#noEdit').prop('checked', true);
            	$('#multipart').prop('checked', false);
            }
            
            // BUSCADOR
            if (localStorage.plugins.indexOf(',seeker,') > -1) {
            	plugins.seeker = {
                    activate: true
                };
                $('#seeker').prop('checked', true);
            } else {
                $('#seeker').prop('checked', false);
            }
            
            // REORDENACIÓN DE COLUMNAS
            if (localStorage.plugins.indexOf('colReorder,') > -1) {
            	plugins.colReorder = {
                    fixedColumnsLeft: 1
                };
                $('#colReorder').prop('checked', true);
            } else {
                $('#colReorder').prop('checked', false);
            }
            
            // AGRUPACIÓN
            if (localStorage.plugins.indexOf(',groups,') > -1) {
            	plugins.rowGroup = {
            		startRender: function (rows, group) {
            			return $('<tr/>').append('<td colspan="8"><b>' + group + ' - ' + rows[0].length + ' Elemento(s) </b></td>');
            		},
            		endRender: false,
            		dataSrc: 'apellido1'
                };
                $('#groups').prop('checked', true);
            } else {
                $('#groups').prop('checked', false);
            }
            
            // TRIGGERS
            if (localStorage.plugins.indexOf(',triggers,') > -1) {
                window.cargarPruebasTriggers();
                $('#triggers').prop('checked', true);
            } else {
                $('#triggers').prop('checked', false);
            }
            
            // El colModel es obligatorio y se mete como genérico
            plugins.colModel = tableColModels;

            localStorage.clear();
            return plugins;
        }

        $('#example_aplicar').click(function () {
            if (localStorage.plugins === undefined) {
                localStorage.plugins = '';
            }

            var selectionType = $('#example_tableConfiguration input[name = selectionType]:checked')[0].id;

            $.each($('#example_tableConfiguration .pluginsControl input'), function () {
                if ($('#' + this.id).prop('checked') && allowedPluginsBySelecionType[selectionType].indexOf(this.id) > -1) {
                    localStorage.plugins = localStorage.plugins + this.id + ',';
                }
            });

            location.reload();
        });

        loadTable();
    });


    $('.contenedor').addClass('show');
});