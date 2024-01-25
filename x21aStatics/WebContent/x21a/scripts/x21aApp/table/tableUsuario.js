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

    $('#usuario').rup_table({

        primaryKey: 'id',
        loadOnStartUp: true,
		enableDynamicForms: true,
        filter: {
            id: 'usuario_filter_form',
            filterToolbar: 'usuario_filter_toolbar',
            collapsableLayerId: 'usuario_filter_fieldset'
        },
        formEdit: {
            detailForm: '#usuario_detail_div',
            fillDataMethod: 'clientSide',
            validate: {
                rules: {
                    'id': {
                        required: true
                    },
                    'nombre': {
                        required: false
                    },
                    'apellido1': {
                        required: false
                    },
                    'apellido2': {
                        required: false
                    },
                    'ejie': {
                        required: false
                    },
                    'fechaAlta': {
                        required: false
                    },
                    'fechaBaja': {
                        required: false
                    },
                    'rol': {
                        required: false
                    },
                    'fechaModif': {
                        required: false
                    }
                }
            },
            titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, 'rup_jqtable.edit.editCaption')
        },
        colReorder: {
            fixedColumnsLeft: 1
        },
        seeker: {
            activate: true
        },
        buttons: {
            activate: true
        },
        multiSelect: {
            style: 'multi'
        }
    });


    $('.contenedor').addClass('show');
});