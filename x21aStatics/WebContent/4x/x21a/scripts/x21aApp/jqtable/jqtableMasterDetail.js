/*!

 * Copyright 2011 E.J.I.E., S.A.
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

    $('#comarca').rup_jqtable({
        url: '../jqGridComarca',
        colNames: [
            'code',
            'descEs',
            'descEu',
            'css',
            'Provincia',
            'Provincia'
        ],
        colModel: [{
            name: 'code',
            label: 'code',
            index: 'code',
            width: '150',
            editable: true,
            edittype: 'text',
            key: true
        },
        {
            name: 'descEs',
            label: 'descEs',
            index: 'descEs',
            width: '150',
            editable: true,
            edittype: 'text'
        },
        {
            name: 'descEu',
            label: 'descEu',
            index: 'descEu',
            width: '150',
            editable: true,
            edittype: 'text'
        },
        {
            name: 'css',
            label: 'css',
            index: 'css',
            width: '150',
            editable: true,
            edittype: 'text'
        },
        {
            name: 'provincia.code',
            label: 'provincia.code',
            index: 'provincia.code',
            editable: true,
            hidden: false,
            edittype: 'text',
            rupType: 'combo',
            editoptions: {
                source: '../jqGridComarca/provincia',
                sourceParam: {
                    label: 'descEs',
                    value: 'code'
                },
                blank: '',
                edithidden: true
            },
            editrules: {
                edithidden: true
            }
        },
        {
            name: 'provincia.descEs',
            label: 'provincia.descEs',
            index: 'provincia.descEs',
            editable: false
        }
        ],
        primaryKey: ['code'],
        usePlugins: [
            'formEdit',
            'feedback',
            'toolbar',
            'contextMenu',
            'responsive',
            'filter',
            'search'
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: 'code',
        toolbar: {
            showOperations: {
                operacion2: false
            }
        },
        formEdit: {
            detailForm: '#comarca_detail_div',
            validate: {
                rules: {
                    'code': {
                        required: true
                    }
                }
            }
        }

    });

    var options_role_combo = {
        source: [{
            label: '---',
            value: ''
        },
        {
            label: 'Alava',
            value: '1'
        },
        {
            label: 'Vizcaya',
            value: '2'
        },
        {
            label: 'Gipuzcoa',
            value: '3'
        }
        ]
    };

    jQuery('#provinciaRemote').rup_combo(options_role_combo);

    $('#localidad').rup_jqtable({
        url: '../jqGridLocalidad',
        sortorder: 'asc',
        sortname: 'code',
        colNames: [
            'code',
            'codeComarca',
            'descEs',
            'descEu',
            'css'
        ],
        colModel: [{
            name: 'code',
            label: 'code',
            index: 'code',
            width: '150',
            editable: true,
            edittype: 'text',
            key: true
        },
        {
            name: 'comarca.code',
            label: 'codeComarca',
            index: 'codeComarca',
            width: '150',
            editable: true,
            edittype: 'text'
        },
        {
            name: 'descEs',
            label: 'descEs',
            index: 'descEs',
            width: '150',
            editable: true,
            edittype: 'text'
        },
        {
            name: 'descEu',
            label: 'descEu',
            index: 'descEu',
            width: '150',
            editable: true,
            edittype: 'text'
        },
        {
            name: 'css',
            label: 'css',
            index: 'css',
            width: '150',
            editable: true,
            edittype: 'text'
        }
        ],
        usePlugins: ['formEdit',
            'feedback',
            'toolbar',
            'contextMenu',
            'fluid',
            'filter',
            'search',
            'masterDetail'
        ],
        loadOnStartUp: false,
        editOptions: {
            fillDataMethod: 'clientSide'
        },
        formEdit: {
            detailForm: '#localidad_detail_div',
            validate: {
                rules: {
                    'code': {
                        required: true
                    }
                }
            }
        },
        primaryKey: 'code',
        masterDetail: {
            master: '#comarca',
            masterPrimaryKey: 'comarca.code'
        }
    });
});