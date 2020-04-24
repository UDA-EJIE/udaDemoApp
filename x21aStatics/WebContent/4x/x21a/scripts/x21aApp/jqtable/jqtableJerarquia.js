/*!
 * Copyright 2013 E.J.I.E., S.A.
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

    // No pueden resolverse resources i18n de rup hasta que haya terminado de cargarlos
    window.initRupI18nPromise.then(function () {
        $('#table').rup_jqtable({
            url: '../jqGridUsuarioJerarquia',
            colNames: window.tableColNames,
            colModel: window.tableColModels,
            primaryKey: ['id'],
            usePlugins: [
                'formEdit',
                'feedback',
                'toolbar',
                'contextMenu',
                'fluid',
                'filter',
                'search',
                'jerarquia',
                'multifilter'
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            sortname: 'id',
            filter: {
                url: '../jqGridUsuarioJerarquia/jerarquia/filter',
                childrenUrl: '../jqGridUsuarioJerarquia/jerarquiaChildren',
                validate: {
                    rules: {
                        'fechaAlta': {
                            date: true
                        },
                        'fechaBaja': {
                            date: true
                        }
                    }
                }
            },
            formEdit: {
                detailForm: '#table_detail_div',
                validate: {
                    rules: {
                        'nombre': {
                            required: true
                        },
                        'apellido1': {
                            required: true
                        },
                        'fechaAlta': {
                            date: true
                        },
                        'fechaBaja': {
                            date: true
                        }
                    }
                }
            },
            jerarquia: {
                //token: '###',
                parentProp: 'idPadre',
                column: 'nombre',
                resetEvents: {
                    click: ['table_filter_filterButton', 'table_filter_cleanLink'],
                    keydown: [function (event) {
                        if (event.keyCode === 13) {
                            return false;
                        }
                    }, 'table_filter_form']
                },
                multifilter: {
                    idFilter: 'jerarquiaNormal',
                    labelSize: 255
                },
                contextMenu: true //(default)
            }

        });
    });


    jQuery('#idPadre_detailForm_table').rup_combo({
        source: '../jqGridUsuarioJerarquia',
        sourceParam: {
            label: 'nombre',
            value: 'id'
        },
        blank: ''

    });


    $('.contenedor').addClass('show');
});