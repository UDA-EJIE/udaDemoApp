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
	
	
	$('#example').on('tableEditFormAddEditBeforeShowForm', function (event,ctx) {
		//ctx = $('#example').rup_table('getContext'); es el contexto de la tabla
		console.log('Este es un ejemplo de trigger, para ver más activar la opción "Activar Triggers en Consola". '+ctx.oInit.formEdit.actionType)
	});
    window.initRupI18nPromise.then(function () {
        //FILTRO Y DETALLE
        var combo = [{
            rol: '---',
            codTipoSubsanacion: ''
        },
        {
            rol: 'Administrador',
            codTipoSubsanacion: 'Administrador'
        },
        {
            rol: 'Desarrollador',
            codTipoSubsanacion: 'Desarrollador'
        },
        {
            rol: 'Espectador',
            codTipoSubsanacion: 'Espectador'
        },
        {
            rol: 'Informador',
            codTipoSubsanacion: 'Informador'
        },
        {
            rol: 'Manager',
            codTipoSubsanacion: 'Manager'
        }
        ];

        var tableColModels = [{
                name: 'id',
                index: 'id',
                editable: true,
                hidden: false,
                width: 80,
                formoptions: {
                    rowpos: 1,
                    colpos: 1
                }
            },
            {
                name: 'nombre',
                index: 'nombre',
                editable: true,
                hidden: false,
                formoptions: {
                    rowpos: 2,
                    colpos: 1
                }
            },
            {
                name: 'apellido1',
                index: 'apellido1',
                editable: true,
                hidden: false,
                formoptions: {
                    rowpos: 3,
                    colpos: 1
                },
                classes: 'ui-ellipsis'
            },
            /*	{ name: "apellido2", index: "apellido2", editable:true, hidden:false
                        , formoptions:{rowpos:4, colpos:1}
                        , classes:'ui-ellipsis'
                    },*/
            {
                name: 'ejie',
                index: 'ejie',
                editable: true,
                hidden: false,
                width: 60,
                edittype: 'checkbox',
                formatter: 'checkbox',
                rwdClasses: 'hidden-xs hidden-sm hidden-md',
                align: 'center',
                editoptions: {
                    value: '1:0'
                },
                formoptions: {
                    rowpos: 5,
                    colpos: 1
                }
            },
            {
                name: 'fechaAlta',
                index: 'fechaAlta',
                editable: true,
                hidden: false,
                width: 120,
                rupType: 'date',
                rwdClasses: 'hidden-xs hidden-sm hidden-md',
                editoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                },
                formoptions: {
                    rowpos: 2,
                    colpos: 2
                }
            },
            {
                name: 'fechaBaja',
                index: 'fechaBaja',
                editable: false,
                hidden: false,
                width: 120,
                rupType: 'date',
                rwdClasses: 'hidden-xs hidden-sm hidden-md',
                editoptions: {
                    labelMaskId: 'fecha-mask',
                    showButtonPanel: true,
                    showOtherMonths: true,
                    noWeekend: true
                },
                formoptions: {
                    rowpos: 3,
                    colpos: 2
                }
            },
            {
                name: 'rol',
                index: 'rol',
                editable: true,
                hidden: false,
                width: 140,
                rupType: 'combo',
                rwdClasses: 'hidden-xs hidden-sm hidden-md',
                formatter: 'rup_combo',
                editoptions: {
                    source: $.map(combo, function (elem) {
                        return {
                            label: elem.rol,
                            value: elem.codTipoSubsanacion
                        };

                    }),
                    width: '100%',
                    customClasses: ['select-material']
                },
                formoptions: {
                    rowpos: 3,
                    colpos: 2
                }
            }
            ],
            options_ejie_combo = {
                source: [{
                    label: '---',
                    value: ''
                },
                {
                    i18nCaption: '0',
                    value: '0'
                },
                {
                    i18nCaption: '1',
                    value: '1'
                }
                ],
                i18nId: 'GRID_simple##ejie',
                width: '100%',
                customClasses: ['select-material']
            },
            options_role_combo = {
                source: [{
                    label: '---',
                    value: ''
                },
                {
                    label: $.rup.i18n.app['GRID_simple##rol'].administrador,
                    value: 'Administrador'
                },
                {
                    label: $.rup.i18n.app['GRID_simple##rol'].desarrollador,
                    value: 'Desarrollador'
                },
                {
                    label: $.rup.i18n.app['GRID_simple##rol'].espectador,
                    value: 'Espectador'
                },
                {
                    label: $.rup.i18n.app['GRID_simple##rol'].informador,
                    value: 'Informador'
                },
                {
                    label: $.rup.i18n.app['GRID_simple##rol'].manager,
                    value: 'Manager'
                }
                ],
                width: '100%',
                customClasses: ['select-material']
            };


        // Formulario de filtrado.
        $('#id_filter_table').rup_autocomplete({
        	source : './allIds',
        	sourceParam : {label: 'nid', value: 'id'},
        	menuMaxHeight: 175,
        	combobox: true,
        	contains: true,
        	showDefault: true
        });
        $('#apellido2_filter_table').rup_autocomplete({
        	source : './apellidos',
        	sourceParam : {label: 'label', value: 'value'},
        	menuMaxHeight: 175,
        	minLength: 3,
        	combobox: true,
        	contains: true,
        	showDefault: true
        });
        $('#fechaAlta_filter_table').rup_date({
        	labelMaskId : 'fecha-mask',
            showButtonPanel : true,
            showOtherMonths : true,
            noWeekend : true,
            datetimepicker : true,
            placeholderMask : true,
            mask: 'dd/mm/yyyy hh:mm:ss',
            showSecond: true,
            dateFormat: 'dd/mm/yy',
            timeFormat: 'hh:mm:ss',
            timezoneOffset:120
        });
        $('#fechaBaja_filter_table').rup_date();

        jQuery('#fechaAlta_filter_table').rup_date();
        jQuery('#fechaBaja_filter_table').rup_date();

        //Formulario de detalle
/*        jQuery('#fechaAlta_detail_table').rup_date();
        jQuery('#fechaBaja_detail_table').rup_date();

        jQuery('#rol_detail_table').rup_combo(options_role_combo);*/

        var listaPlugins = 'editForm,colReorder,multiSelection,seeker,buttons,';

        var allowedPluginsBySelecionType = {
            multiSelection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'multiSelection', 'multiFilter', 'triggers', 'inlineEdit', 'multiPart','sinFiltro'],
            selection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'selection', 'multiFilter', 'triggers', 'inlineEdit', 'multiPart','sinFiltro'],
            noSelection: ['colReorder', 'seeker', 'groups', 'noSelection', 'multiFilter', 'triggers', 'multiPart','sinFiltro']
        };


        function loadTable() {
            $('#example').rup_table(loadPlugins());
        }

        function loadPlugins() {

            if (localStorage.plugins === undefined) { //si esta undefined es que es la primera vez.
                localStorage.plugins = listaPlugins;
            }

            var plugins = {};

            var fixedHeader = {
                footer: false,
                header: true
            };
            plugins.fixedHeader = fixedHeader;
            plugins.selector = 'td';
            /*plugins.responsive =  {
            	details: {
            		type: 'column',
            		target: 'td'
            	}
            };*/
            
            
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

            if (localStorage.plugins.indexOf('multiSelection') > -1) {
                var multiSelect = {
                    style: 'multi'
                };
                plugins.multiSelect = multiSelect;
                $('#noSelection').prop('checked', false);
                $('#selection').prop('checked', false);
                $('#multiSelection').prop('checked', true);

            } else {
                $('#multiSelection').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('selection') > -1) {
                var select = {
                    activate: true
                };
                plugins.select = select;
                $('#noSelection').prop('checked', false);
                $('#selection').prop('checked', true);
            } else {
                $('#selection').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('noSelection') > -1) {
                $('#noSelection').prop('checked', true);
            } else {
                $('#noSelection').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('editForm') > -1) {
                var cancelClicked = function () {
                    console.log('Ha cancelado eliminar.');
                };

                var formEdit = {
                    detailForm: '#example_detail_div',
                    validate: {
                        rules: {
                            'id': {
                                required: true
                            },
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
                    titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, 'rup_jqtable.edit.editCaption'),
                    cancelDeleteFunction: cancelClicked
                };
                plugins.validarModificarContinuar = function customGuardar(ctx){
                	if($('#apellido1_detail_table').val() !== 'ruiz'){
	                	//Ejemplo de validación personalizada
	                	 let idTableDetail = ctx.oInit.formEdit.detailForm;
	                	 let feedback = idTableDetail.find('#' + ctx.sTableId + '_detail_feedback');
	                     try {//Se destruye para asegurar la inicialización.
	                         feedback.rup_feedback('destroy');
	                     } catch (ex) {
	                      
	                     }
	
	                     feedback.rup_feedback({
	                         message: 'No validado, validación personal, solo se valida si el primer apellido es ruiz.',
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

                $('#editForm').prop('checked', true);
            } else {
                $('#editForm').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('inlineEdit') > -1) {
                /*      var formEdit = {
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
                          titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_jqtable.edit.editCaption')
                      }*/
                var inlineEdit = {
                    deselect: true,
                    validate: {
                        rules: {
                            'id': {
                                required: true
                            },
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

            if (localStorage.plugins.indexOf('buttons') > -1) {
                var buttons = {
                    activate: true
                };
                 var contextMenu = {
                    activate: false
                };

                plugins.buttons = buttons;
                plugins.buttons.contextMenu = contextMenu;
                $('#buttons').prop('checked', true);
                if (localStorage.plugins !== undefined && localStorage.plugins.indexOf('multiPart') > -1) {
                    //Crear boton para editar con multiPart PUT
                    var optionButtonEdit = {
                        text: function () {
                            return 'Editar con MultiPart';
                        },
                        id: 'exampleeditMultiPart_1', // Campo obligatorio si se quiere usar desde el contextMenu
                        className: 'btn-material-primary-high-emphasis table_toolbar_btnEdit',
                        displayRegex: /^[1-9][0-9]*$/, // Se muestra siempre que sea un numero mayor a 0
                        insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
                        type: 'edit',
                        action: function (e, dt, node, config) {
                            var ctx = dt.context[0];
                            ctx.oInit.formEdit.multiPart = true;
                            ctx.oInit.urlBase = './editar';
                            ctx.oInit.formEdit.direct = true;
                            $('#divImagenAlumno').parent().removeClass('d-none');
                            $('#imagenAlumno').prop('disabled', false);
                            dt.buttons.actions(dt, config);
                        }
                    };
                    plugins.buttons.myButtons = [];
                    plugins.buttons.myButtons.push(optionButtonEdit);

                    $('#example').on('tableEditFormSuccessCallSaveAjax', function (event) {
                        var dt = $('#example').DataTable();
                        var ctx = dt.context[0];
                        if (ctx.oInit.formEdit !== undefined && ctx.oInit.formEdit.multiPart) {
                            console.log('---Trigger--- ' + event.type);
                            ctx.oInit.formEdit.multiPart = undefined;
                            ctx.oInit.urlBase = ctx.oInit.urlBase = '.';
                            $('#divImagenAlumno').parent().addClass('d-none');
                            $('#imagenAlumno').prop('disabled', true);
                        }
                    });
                    $('#example_detail_div').on('dialogbeforeclose', function (event) {
                        var dt = $('#example').DataTable();
                        var ctx = dt.context[0];
                        if (ctx.oInit.formEdit !== undefined && ctx.oInit.formEdit.multiPart) {
                            console.log('---Trigger--- ' + event.type);
                            ctx.oInit.formEdit.multiPart = undefined;
                            ctx.oInit.urlBase = ctx.oInit.urlBase = '.';
                            $('#divImagenAlumno').parent().addClass('d-none');
                            $('#imagenAlumno').prop('disabled', true);
                        }
                    });
                    // FIN MULTIPART
                    $('#multiPart').prop('checked', true);
                } else {
                    $('#multiPart').prop('checked', false);
                }
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
	                    	let ctx = dt.context[0];
	                    	
	                    	let childWindowHandler = window.open($('#editFormTargetBlank').data('editNewWindowUrl'), '_blank');
	                    	childWindowHandler.addEventListener('load', function () {
	                    		childWindowHandler.setEntityData(ctx.multiselection.lastSelectedId);                  		
	                		}, false);
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
            }

            if (localStorage.plugins.indexOf('seeker') > -1) {
                var seeker = {
                    activate: true
                };
                plugins.seeker = seeker;
                $('#seeker').prop('checked', true);
            } else {
                $('#seeker').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('colReorder') > -1) {
                var colReorder = {
                    fixedColumnsLeft: 1
                };
                plugins.colReorder = colReorder;
                $('#colReorder').prop('checked', true);
            } else {
                $('#colReorder').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('groups') > -1) {
                var rowGroup = {
                    startRender: function (rows, group) {
                        return $('<tr/>')
                            .append('<td colspan="8"><b>' + group + ' - ' + rows[0].length + ' Elemento(s) </b></td>');
                    },
                    endRender: false,
                    dataSrc: 'nombre'
                };
                plugins.rowGroup = rowGroup;
                $('#groups').prop('checked', true);
            } else {
                $('#groups').prop('checked', false);
            }

            if (localStorage.plugins.indexOf('multiFilter') > -1) {
                plugins.multiFilter = {
                    idFilter: 'generated',
                    labelSize: 255,
                    userFilter: 'udaPruebas'
                };
                $('#multiFilter').prop('checked', true);
            } else {
                $('#multiFilter').prop('checked', false);
            }

            if (localStorage.plugins !== undefined && localStorage.plugins.indexOf('triggers') > -1) {
                window.cargarPruebasTriggers();
                $('#triggers').prop('checked', true);
            } else {
                $('#triggers').prop('checked', false);
            }
            
            if (localStorage.plugins !== undefined && localStorage.plugins.indexOf('sinFiltro') > -1) {
               $('#example_filter_form').remove();
               $('#sinFiltro').prop('checked', true);
               plugins.filter = 'noFilter';
            }else{
                var filter = {
                        id: 'example_filter_form',
                        filterToolbar: 'example_filter_toolbar',
                        collapsableLayerId: 'example_filter_fieldset',
                        rules: {
                            fechaAlta: 'date',
                            fechaBaja: 'date'
                        }
                    };
                plugins.filter = filter;
                $('#sinFiltro').prop('checked', false);
            }

            //Col model es obligatorio,se mete como generico
            plugins.colModel = tableColModels;

            localStorage.clear();
            return plugins;
        }

        $('#example_aplicar').click(function () {
            if (localStorage.plugins === undefined) {
                localStorage.plugins = '';
            }

            var selectionType = $('input[name = tipoSeleccionTabla]:checked')[0].id;

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