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


    var disable = 1;

    // No pueden resolverse resources i18n de rup hasta que haya terminado de cargarlos
    window.initRupI18nPromise.then(function () {
        $('#table').rup_jqtable({
            url: '../jqGridUsuario',
            colNames: window.tableColNames,
            colModel: window.tableColModels,
            primaryKey: ['id'],

            usePlugins: [
                'formEdit',
                'feedback',
                'toolbar',
                'contextMenu',
                'responsive',
                'filter',
                'search',
                'report'
                //"multifilter"
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            sortname: 'id',
            core: {
                operations: {
                    'operacion1': {
                        name: 'Operación 1',
                        icon: 'rup-icon rup-icon-new',
                        enabled: function () {
                            return (disable++ % 2) === 0;
                        },
                        callback: function () {
                            alert('Operación 1');
                        }
                    },
                    'operacion2': {
                        name: 'Operación 2',
                        icon: 'rup-icon rup-icon-new',
                        enabled: function () {
                            return true;
                        },
                        callback: function () {
                            alert('Operación 1');
                        }
                    }
                }
            },
            toolbar: {
                showOperations: {
                    operacion2: false
                }
            },
            contextMenu: {
                colNames: ['nombre', 'apellido1', 'apellido2', 'ejie', 'fechaAlta'],
                items: {
                    'sep1': '---------',
                    'opContextual1': {
                        name: 'Op. contextual 1',
                        icon: 'edit',
                        disabled: false,
                        colNames: ['fechaAlta', 'fechaBaja', 'rol']
                    },
                    'opContextual2': {
                        name: 'Op. contextual 2',
                        icon: 'cut',
                        disabled: true
                    },
                    'opContextual3': {
                        name: 'Op. contextual 3',
                        icon: 'cut',
                        colNames: ['nombre', 'apellido1'],
                        items: {
                            'subOpContextual1': {
                                name: 'Sub Op. contextual 1',
                                icon: 'edit',
                                disabled: false
                            },
                            'opContextual2': {
                                name: 'Sub Op. contextual 2',
                                icon: 'cut',
                                disabled: true
                            }
                        }
                    }
                },
                showOperations: {
                    operacion1: false,
                    operacion2: ['nombre', 'apellido1']
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
            filter: {
                validate: {
                    rules: {
                        'fechaAlta': {
                            date: true
                        },
                        'fechaBaja': {
                            date: true
                        }
                    }
                },
            },
            search: {
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
            report: window.options_table_report 
        });
    });


    $('.contenedor').addClass('show');
});