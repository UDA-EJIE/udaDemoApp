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
    var combo = [{
        rol: '---',
        codTipoSubsanacion: ''
	    },
	    {
	        rol: 'Administrador',
	        codTipoSubsanacion: 'administrador'
	    },
	    {
	        rol: 'Desarrollador',
	        codTipoSubsanacion: 'desarrollador'
	    },
	    {
	        rol: 'Espectador',
	        codTipoSubsanacion: 'espectador'
	    },
	    {
	        rol: 'Informador',
	        codTipoSubsanacion: 'informador'
	    },
	    {
	        rol: 'Manager',
	        codTipoSubsanacion: 'manager'
    }];
    const options_ejie_select = {
        source: [{
            text: '---',
            id: ''
        },
        {
            i18nCaption: '0',
            id: '0'
        },
        {
            i18nCaption: '1',
            id: '1'
        }
        ],
        i18nId: 'GRID_simple##ejie'
    };
    let options_role_select;
    window.initRupI18nPromise.then(function () {
	    options_role_select = {
	        source: [{
	            text: '---',
	            id: ''
	        },
	        {
	            text: $.rup.i18n.app['GRID_simple##rol'].administrador,
	            id: 'administrador'
	        },
	        {
	            text: $.rup.i18n.app['GRID_simple##rol'].desarrollador,
	            id: 'desarrollador'
	        },
	        {
	            text: $.rup.i18n.app['GRID_simple##rol'].espectador,
	            id: 'espectador'
	        },
	        {
	            text: $.rup.i18n.app['GRID_simple##rol'].informador,
	            id: 'informador'
	        },
	        {
	            text: $.rup.i18n.app['GRID_simple##rol'].manager,
	            id: 'manager'
	        }]
	    };
    });
    var allowedPluginsBySelecionType = {
        multiSelection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'multiSelection', 'multiFilter', 'triggers', 'inlineEdit', 'multiPart'],
        selection: ['editForm', 'colReorder', 'seeker', 'buttons', 'groups', 'selection', 'multiFilter', 'triggers', 'inlineEdit', 'multiPart'],
        noSelection: ['colReorder', 'seeker', 'groups', 'noSelection', 'multiFilter', 'triggers', 'multiPart']
    };


    function copyPluginsForm(num) {
        var $formPlugins = $('#example_tableConfiguration').clone();
        $formPlugins.find('[id]').each(function (i, e) {
            $(e).attr('id', $(e).attr('id') + num);
        });
        $formPlugins.find('[for]').each(function (i, e) {
            $(e).attr('for', $(e).attr('for') + num);
        });
        $('#example_tableConfiguration' + num).append($formPlugins.html());
    }

    function copyFormEdit(num) {
        var $formDetail = $('#example_detail_div').clone();
        $formDetail.attr('id', 'example' + num + '_' + $formDetail.attr('id').split('example_')[1]);
        $formDetail.find('[id^="example_"]').each(function (i, e) {
            var id = $(e).attr('id');
            $(e).attr('id', 'example' + num + '_' + id.split('example_')[1]);
        });
        $formDetail.find('input').each(function (i, e) {
            $(e).attr('id', $(e).attr('id') + num);
            $(e).attr('name', $(e).attr('name') + num);
        });
        $formDetail.find('label [for]').each(function (i, e) {
            $(e).attr('for', $(e).attr('for') + num);
        });
        $('#example_tableConfiguration' + num).before($formDetail);
    }

    function listaPlugins(num) {
        switch (num) {
        case '2':
            return 'editForm,colReorder,multiSelection,seeker,buttons,';
        default:
            return 'editForm,colReorder,multiSelection,seeker,buttons,';
        }
    }

    function loadTable(num) {
        num = (num ? num : '') + '';

        $('#example_aplicar' + num).click(function () {
            if (localStorage['plugins' + num] === undefined) {
                localStorage['plugins' + num] = '';
            }

            var selectionType = $('#example_tableConfiguration' + num + ' input[name=\'tipoSeleccionTabla\']:checked')[0].id;

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

            location.reload();
        });

        $('#example' + num).rup_table(loadPlugins(num));
    }


    function loadPlugins(num) {

        if (localStorage['plugins' + num] === undefined) { //si esta undefined es que es la primera vez.
            localStorage['plugins' + num] = listaPlugins(num);
        }

        var plugins = {};
            
		plugins.enableDynamicForms = true;

        var fixedHeader = {
            footer: false,
            header: true
        };
        plugins.fixedHeader = fixedHeader;
        plugins.selector = 'td';

        var filter = {
            id: 'example' + num + '_filter_form',
            filterToolbar: 'example' + num + '_filter_toolbar',
            collapsableLayerId: 'example' + num + '_filter_fieldset'
        };
        plugins.filter = filter;

        if (localStorage['plugins' + num].indexOf('multiSelection') > -1) {
            var multiSelect = {
                style: 'multi'
            };
            plugins.multiSelect = multiSelect;
            $('#noSelection' + num).prop('checked', false);
            $('#selection' + num).prop('checked', false);
            $('#multiSelection' + num).prop('checked', true);

        } else {
            $('#multiSelection' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('selection') > -1) {
            var select = {
                activate: true
            };
            plugins.select = select;
            $('#noSelection' + num).prop('checked', false);
            $('#selection' + num).prop('checked', true);
        } else {
            $('#selection' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('noSelection') > -1) {
            $('#noSelection' + num).prop('checked', true);
        } else {
            $('#noSelection' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('editForm') > -1) {
            window.initRupI18nPromise.then(function () {
                var formEdit = {
                    detailForm: '#example' + num + '_detail_div',
                    validate: {
                        rules: {}
                    },
                    titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, 'rup_table.edit.editCaption'),
                    saveContinueEdit: false //true pasa a edición; false seguir añadiendo
                };
                formEdit.validate.rules['id' + num] = {
                    required: true
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
            });
        } else {
            $('#editForm' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('inlineEdit') > -1) {
            var inlineEdit = {
                deselect: true,
                validate: {
                    rules: {}
                }
            };
            inlineEdit.validate.rules['id' + num] = {
                required: true
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

        if (localStorage['plugins' + num].indexOf('buttons') > -1) {
            var buttons = {
                activate: true
            };
            plugins.buttons = buttons;
            $('#buttons' + num).prop('checked', true);
            if (localStorage['plugins' + num] !== undefined && localStorage['plugins' + num].indexOf('multiPart') > -1) {
                //Crear boton para editar con multiPart PUT
                var optionButtonEdit = {
                    text: function () {
                        return 'Editar con MultiPart';
                    },
                    id: 'example' + num + 'editMultiPart_1', // Campo obligatorio si se quiere usar desde el contextMenu
                    className: 'datatable_toolbar_btnEdit',
                    displayRegex: /^[1-9][0-9]*$/, // Se muestra siempre que sea un numero mayor a 0
                    insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
                    type: 'edit',
                    action: function (e, dt, node, config) {
                        var ctx = dt.context[0];
                        ctx.oInit.formEdit.multiPart = true;
                        ctx.oInit.urlBase = './editar';
                        ctx.oInit.formEdit.direct = true;
                        $('#divImagenAlumno' + num).css('display', 'block');
                        $('#imagenAlumno' + num).prop('disabled', false);
                        dt.buttons.actions(dt, config);
                    }
                };
                plugins.buttons.myButtons = [];
                plugins.buttons.myButtons.push(optionButtonEdit);

                $('#example' + num).on('tableEditFormSuccessCallSaveAjax', function (event) {
                    var dt = $('#example' + num).DataTable();
                    var ctx = dt.context[0];
                    if (ctx.oInit.formEdit !== undefined && ctx.oInit.formEdit.multiPart) {
                        console.log('---Trigger--- ' + event.type);
                        ctx.oInit.formEdit.multiPart = undefined;
                        ctx.oInit.urlBase = ctx.oInit.urlBase = '.';
                        $('#divImagenAlumno' + num).css('display', 'none');
                        $('#imagenAlumno' + num).prop('disabled', true);
                    }
                });
                $('#example' + num + '_detail_div').on('dialogbeforeclose', function (event) {
                    var dt = $('#example' + num).DataTable();
                    var ctx = dt.context[0];
                    if (ctx.oInit.formEdit !== undefined && ctx.oInit.formEdit.multiPart) {
                        console.log('---Trigger--- ' + event.type);
                        ctx.oInit.formEdit.multiPart = undefined;
                        ctx.oInit.urlBase = ctx.oInit.urlBase = '.';
                        $('#divImagenAlumno' + num).css('display', 'none');
                        $('#imagenAlumno' + num).prop('disabled', true);
                    }
                });
                // FIN MULTIPART
                $('#multiPart' + num).prop('checked', true);
            } else {
                $('#multiPart' + num).prop('checked', false);
            }
            //Reports
            plugins.buttons.report = {};
            plugins.buttons.report.reportsParams = [];
            plugins.buttons.report.reportsParams.push({
                'isInline': false
            });


        } else {
            $('#buttons' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('seeker') > -1) {
            var seeker = {
                activate: true
            };
            plugins.seeker = seeker;
            $('#seeker' + num).prop('checked', true);
        } else {
            $('#seeker' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('colReorder') > -1) {
            var colReorder = {
                fixedColumnsLeft: 1
            };
            plugins.colReorder = colReorder;
            $('#colReorder' + num).prop('checked', true);
        } else {
            $('#colReorder' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('groups') > -1) {
            var rowGroup = {
                startRender: false,
                endRender: function (rows, group) {

                    return $('<tr/>')
                        .append('<td colspan="8"><b>' + group + ' - ' + rows[0].length + ' Elemento(s) </b></td>');
                },
                dataSrc: 'nombre'
            };
            plugins.rowGroup = rowGroup;
            $('#groups' + num).prop('checked', true);
        } else {
            $('#groups' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num].indexOf('multiFilter') > -1) {
            plugins.multiFilter = {
                idFilter: 'generated',
                labelSize: 255,
                userFilter: 'udaPruebas'
            };
            $('#multiFilter' + num).prop('checked', true);
        } else {
            $('#multiFilter' + num).prop('checked', false);
        }

        if (localStorage['plugins' + num] !== undefined && localStorage['plugins' + num].indexOf('triggers') > -1) {
            window.cargarPruebasTriggers();
            $('#triggers' + num).prop('checked', true);
        } else {
            $('#triggers' + num).prop('checked', false);
        }

        plugins.columnDefs = [{
            'targets': [5],
            'render': function (data) {
                if (data !== undefined && data !== null) {
                    return data.replace('/', '/');
                }
            }
        },
        {
            'name': 'Nombre',
            'targets': 'Nombre',
            'render': function (data) {
                return data.replace('a', '.');
            }
        }
        ];

        // Para cualquier tabla que no sea la primera se oculta el nombre
        if (num) {
            plugins.columnDefs.push({
                'className': 'never',
                'targets': [2] }
            );
        }

        plugins.colModel = [{
            name: 'id' + num,
            editable: true,
            hidden: false
        },
        {
            name: 'nombre' + num,
            editable: true,
            hidden: false
        },
        {
            name: 'apellido1' + num,
            editable: true,
            hidden: false
        },
        {
            name: 'ejie' + num,
            editable: true,
            hidden: false
        },
        {
            name: 'fechaAlta' + num,
            editable: true,
            hidden: false
        },
        {
            name: 'fechaBaja' + num,
            editable: false,
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
            name: 'rol' + num,
            editable: true,
            hidden: false,
            rupType: 'select',
            editoptions: {
                data: $.map(combo, function (elem) {
                    return {
                        text: elem.rol,
                        id: elem.codTipoSubsanacion
                    };

                })
            },
            searchoptions: {
                    url : './roles',
                    sourceParam : {text: 'label', id: 'value'},
                    blank: ''
                }
        }
        ];

        delete localStorage['plugins' + num];
        return plugins;
    }


    jQuery(function ($) {
        copyPluginsForm(2);
        copyFormEdit(2);
        
        //Formulario de filtrado
        $('[id="fechaAlta_filter_table"]').rup_date();
        $('[id="fechaBaja_filter_table"]').rup_date();
        
        //Formulario de detalle
        $('[id="fechaAlta_detail_table"]').rup_date();
        $('[id="fechaBaja_detail_table"]').rup_date();
        window.initRupI18nPromise.then(function () {
	        //Formulario de filtrado
	        $('[id="ejie_filter_table"]').rup_select(options_ejie_select);
	        $('[id="rol_filter_table"]').rup_select(options_role_select);
	        
	        //Formulario de detalle
	        $('[id="rol_detail_table"]').rup_select(options_role_select);
        });
	    //--------------------------------------------
        
        //Formulario de filtrado
        $('[id="fechaAlta_filter_table2"]').rup_date();
        $('[id="fechaBaja_filter_table2"]').rup_date();

        //Formulario de detalle
        $('[id="fechaAlta_detail_table2"]').rup_date();
        $('[id="fechaBaja_detail_table2"]').rup_date();
        window.initRupI18nPromise.then(function () {
	        //Formulario de filtrado
	        $('[id="ejie_filter_table2"]').rup_select(options_ejie_select);
	        $('[id="rol_filter_table2"]').rup_select(options_role_select);
	        
	        //Formulario de detalle
	        $('[id="rol_detail_table2"]').rup_select(options_role_select);
	
	        loadTable();
	        loadTable(2);
	
	        $('.contenedor').addClass('show');
        });
    });
}

_init();
