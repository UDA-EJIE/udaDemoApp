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

window.initRupI18nPromise.then(function () {
    window.combo = [{
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
    }
    ];

    window.tableColNames = [
        $.rup.i18n.app.table.id,
        $.rup.i18n.app.table.nombre,
        $.rup.i18n.app.table.apellido1,
        $.rup.i18n.app.table.apellido2,
        $.rup.i18n.app.table.ejie,
        $.rup.i18n.app.table.fechaAlta,
        $.rup.i18n.app.table.fechaBaja,
        $.rup.i18n.app.table.rol
    ];

    window.tableColModels = [{
        name: 'id',
        index: 'id',
        editable: true,
        width: 80,
        obligatorio: true,
        formoptions: {
            rowpos: 1,
            colpos: 1
        }
    },
    {
        name: 'nombre',
        index: 'nombre',
        editable: true,
        obligatorio: true,
        formoptions: {
            rowpos: 2,
            colpos: 1
        }
    },
    {
        name: 'apellido1',
        index: 'apellido1',
        editable: true,
        obligatorio: true,
        formoptions: {
            rowpos: 3,
            colpos: 1
        },
        classes: 'ui-ellipsis'
    },
    {
        name: 'apellido2',
        index: 'apellido2',
        editable: true,
        formoptions: {
            rowpos: 4,
            colpos: 1
        }
    },
    {
        name: 'ejie',
        index: 'ejie',
        editable: true,
        width: 60,
        edittype: 'checkbox',
        formatter: 'checkbox',
        rwdClasses: 'hidden-xs hidden-sm hidden-md',
        align: 'center',
        editoptions: {
            value: '1:0'
        },
        searchoptions: {
            rupType: 'combo',
            source: [{
                label: '---',
                value: ''
            },
            {
                label: 'Si',
                value: '1'
            },
            {
                label: 'No',
                value: '0'
            }
            ]
        },
        formoptions: {
            rowpos: 5,
            colpos: 1
        }
    },
    {
        name: 'fechaAlta',
        index: 'fecha_alta',
        editable: true,
        width: 120,
        obligatorio: true,
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
        index: 'fecha_baja',
        editable: true,
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
        width: 140,
        rupType: 'combo',
        rwdClasses: 'hidden-xs hidden-sm hidden-md',
        formatter: 'rup_combo',
        editoptions: {
            source: $.map(window.combo, function (elem) {
                return {
                    label: elem.rol,
                    value: elem.codTipoSubsanacion
                };

            })
        },
        formoptions: {
            rowpos: 3,
            colpos: 2
        }
    }
    ];

    window.options_table_report = {
        buttons: [{
            id: 'reports',
            i18nCaption: 'Informes',
            right: true,
            buttons: [{
                i18nCaption: 'CSV',
                css: 'csv',
                url: '../jqGridUsuario/csvReport'
            },
            {
                i18nCaption: 'XLS',
                css: 'xls',
                url: '../jqGridUsuario/xlsReport'
            },
            {
                i18nCaption: 'XLXS',
                css: 'xls',
                url: '../jqGridUsuario/xlsxReport'
            },
            {
                i18nCaption: 'ODS',
                css: 'ods',
                url: '../jqGridUsuario/odsReport'
            },
            {
                i18nCaption: 'PDF',
                css: 'pdf',
                url: '../jqGridUsuario/pdfReport'
            },
            {
                i18nCaption: 'PDF_inLine',
                css: 'pdf',
                url: '../jqGridUsuario/pdfReport',
                isInline: true
            }
            ]
        }]
    };

    window.options_ejie_combo = {
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
        width: 120
    };

    window.options_role_combo = {
        source: [{
            label: '---',
            value: ''
        },
        {
            label: $.rup.i18n.app['GRID_simple##rol'].administrador,
            value: 'administrador'
        },
        {
            label: $.rup.i18n.app['GRID_simple##rol'].desarrollador,
            value: 'desarrollador'
        },
        {
            label: $.rup.i18n.app['GRID_simple##rol'].espectador,
            value: 'espectador'
        },
        {
            label: $.rup.i18n.app['GRID_simple##rol'].informador,
            value: 'informador'
        },
        {
            label: $.rup.i18n.app['GRID_simple##rol'].manager,
            value: 'manager'
        }
        ]
    };

    window.optionalColumns = [{
        label: 'apellido2',
        value: '4'
    },
    {
        label: 'ejie',
        value: '5'
    },
    {
        label: 'fechaBaja',
        value: '7'
    },
    {
        label: 'rol',
        value: '8'
    }
    ];


    //Formulario de filtrado
    jQuery('#ejie_filter_table').rup_combo(window.options_ejie_combo);
    jQuery('#rol_filter_table').rup_combo(window.options_role_combo);

    jQuery('#fechaAlta_filter_table').rup_date();
    jQuery('#fechaBaja_filter_table').rup_date();

    //Formulario de detalle
    jQuery('#fechaAlta_detail_table').rup_date();
    jQuery('#fechaBaja_detail_table').rup_date();

    jQuery('#rol_detail_table').rup_combo(window.options_role_combo);


    $('.contenedor').addClass('show');
});