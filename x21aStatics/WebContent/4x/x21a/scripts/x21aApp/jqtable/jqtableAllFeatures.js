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
jQuery(function ($) {
    $('#GRID_simple').rup_jqtable({
        url: '../table',
        colNames: ['id', 'nombre', 'apellido1', 'apellido2', 'ejie', 'fechaAlta', 'fechaBaja'],
        colModel: [{
            name: 'id',
            index: 'id',
            editable: true,
            formoptions: {
                rowpos: 3,
                colpos: 1
            }
        },
        {
            name: 'nombre',
            index: 'nombre',
            editable: true,
            formoptions: {
                rowpos: 4,
                colpos: 1
            }
        },
        {
            name: 'apellido1',
            index: 'apellido1',
            editable: true,
            formoptions: {
                rowpos: 2,
                colpos: 1
            }
        },
        {
            name: 'apellido2',
            index: 'apellido2',
            editable: true,
            formoptions: {
                rowpos: 1,
                colpos: 1
            }
        },
        {
            name: 'ejie',
            index: 'ejie',
            editable: true,
            rupType: 'combo',
            editoptions: {
                source: [{
                    label: $.rup.i18n.app['GRID_simple##ejie']['0'],
                    value: '0'
                },
                {
                    label: $.rup.i18n.app['GRID_simple##ejie']['1'],
                    value: '1'
                }
                ]
            },
            formoptions: {
                rowpos: 3,
                colpos: 2
            }
        },
        {
            name: 'fechaAlta',
            index: 'fechaAlta',
            editable: true,
            rupType: 'date',
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
            editable: true,
            rupType: 'date',
            editoptions: {
                labelMaskId: 'fecha-mask',
                showButtonPanel: true,
                showOtherMonths: true,
                noWeekend: true
            },
            formoptions: {
                rowpos: 1,
                colpos: 2
            }
        }
        ],
        usePlugins: ['formEdit'],
        editOptions: {
            fillDataMethod: 'clientSide'
        },
        fluid: {
            baseLayer: '#simple'
        },
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#pager',
        primaryKey: 'id',
        sortname: 'id',
        feedback: {
            id: '#tableFeedback'
        },
        filter: {
            id: 'searchForm',
            filterButton: 'filterButton',
            cleanLink: 'cleanLink',
            collapsableLayerId: 'FIELDSET_SEARCH_GRID_simple',
            collapseButtonId: 'toggle_search_form',
            collapseLabel: 'toggle_search_form_label',
            filterCriteriasId: 'filter_params'
        },
        toolbar: {
            id: 'toolbar',
            newButtons: [{
                obj: {
                    i18nCaption: 'Mostrar/Ocultar columnas',
                    css: 'rup-icon rup-icon-filter',
                    index: 7
                },
                json_i18n: $.rup.i18n.app.simpelMaint,
                click: function () {
                    $('#GRID_simple').jqGrid('columnChooser', {
                        modal: true
                    });

                }
            }, {
                obj: {
                    i18nCaption: 'Reordenar filas',
                    css: 'rup-icon rup-icon-filter',
                    index: 7
                },
                json_i18n: $.rup.i18n.app.simpelMaint,
                click: function () {
                    jQuery('#GRID_simple').jqGrid('sortableRows', {
                        cursor: '.handle'
                    });
                }
            }]


        }
    });

    //Formulario de filtrado
    $('#ejie_search').rup_combo({
        source: [{
            i18nCaption: '0',
            value: '0'
        },
        {
            i18nCaption: '1',
            value: '1'
        }
        ],
        i18nId: 'GRID_simple##ejie',
        width: 120,
        blank: ''
    });

    $('#fechaAlta_search').rup_date();
    $('#fechaBaja_search').rup_date();


    $('.contenedor').addClass('show');
});