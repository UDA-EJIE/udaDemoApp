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
jQuery(function($) {
    window.initRupI18nPromise.then(function(){
        var $feedbackColumns = jQuery('#feedbackColumns').rup_feedback({
            type: 'error',
            closeLink: true,
            block:false
        });

        $('#formColumns').rup_validate({
            feedback: $feedbackColumns,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback:true,
            rules:{
                'nombre': {required: true},
                'apellido1': {required: true},
                'apellido2': {required: true}
            }
        });

        var $feedbackColumnsRequired = jQuery('#feedbackColumnsRequired').rup_feedback({
            type: 'error',
            closeLink: true,
            block:false
        });

        $('#formColumnsRequired').rup_validate({
            feedback: $feedbackColumnsRequired,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback:true,
            rules:{
                'nombre': {required: true},
                'apellido1': {required: true},
                'apellido2': {required: true}
            }
        });

        // MATERIAL
        var $feedbackHorizontalMaterial = jQuery('#feedbackHorizontalMaterial').rup_feedback({
            type: 'error',
            closeLink: true,
            block:false
        });

        $('#formHorizontalMaterial').rup_validate({
            adapter: 'validate_material',
            feedback: $feedbackHorizontalMaterial,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback:true,
            rules:{
                'nombre': {
                    required: true
                },
                'apellido1': {
                    required: true
                },
                'apellido2': {
                    required: true
                },
                'alertDay': {
                    required: true
                },
                'alertEdad[]': {
                    required: true
                },
                'rol': {
                    required: true
                },
                'autocomplete': {
                    required: true
                },
                'rolName2': {
                    required: true
                },
                'autocompleteName2': {
                    required: true
                },
                'autocompleteCombobox': {
                    required: true
                },
                'autocompleteCombobox2': {
                    required: true
                },
                'textarea': {
                    required: true
                }
            },
            messages:{
                'nombre': {
                    required: $.rup.i18n.app.validacion.nombre
                },
                'apellido1': {
                    required: $.rup.i18n.app.validacion.apellido1
                },
                'apellido2': {
                    required: $.rup.i18n.app.validacion.apellido2
                },
                'alertDay[]': {
                    required: $.rup.i18n.app.validacion.alertDay
                }
            },
            labels:{
                'alertDay[]': '#alertDayError'
            },
            icons:{
                'alertDay[]': '#alertDayErrorLabel'
            }
        });

        $('#rol_detail_table').rup_select({
            data: [
                {i18nCaption: 'asp', id: 'asp_value'},
                {i18nCaption: 'c', id: 'c_value'},
                {i18nCaption: 'c++', id: 'c++_value'},
                {i18nCaption: 'coldfusion', id: 'coldfusion_value'},
                {i18nCaption: 'groovy', id: 'groovy_value'}
            ],
            blank: '',
            rowStriping: true
        });


        $('#autocomplete').rup_select({
            data: [
                {i18nCaption: 'asp', id:'asp_value'},
                {i18nCaption: 'c', id:'c_value'},
                {i18nCaption: 'c++', id:'c++_value'},
                {i18nCaption: 'coldfusion', id:'coldfusion_value'},
                {i18nCaption: 'groovy', id:'groovy_value'}
            ],
            defaultValue: '',
            contains: false
        });

        $('#rolName2').rup_select({
            data: [
                {i18nCaption: 'asp', id:'asp_value'},
                {i18nCaption: 'c', id:'c_value'},
                {i18nCaption: 'c++', id:'c++_value'},
                {i18nCaption: 'coldfusion', id:'coldfusion_value'},
                {i18nCaption: 'groovy', id:'groovy_value'}
            ],
            blank : '',
            rowStriping : true
        });

        $('#autocompleteName2').rup_select({
            data: [
                {i18nCaption: 'asp', id:'asp_value'},
                {i18nCaption: 'c', id:'c_value'},
                {i18nCaption: 'c++', id:'c++_value'},
                {i18nCaption: 'coldfusion', id:'coldfusion_value'},
                {i18nCaption: 'groovy', id:'groovy_value'}
            ],
            defaultValue: '',
            contains: false
        });

        $('#autocompleteCombobox').rup_select({
            data: [
                {i18nCaption: 'asp', id:'asp_value'},
                {i18nCaption: 'c', id:'c_value'},
                {i18nCaption: 'c++', id:'c++_value'},
                {i18nCaption: 'coldfusion', id:'coldfusion_value'},
                {i18nCaption: 'groovy', id:'groovy_value'}
            ],
            defaultValue: '',
            combo: true,
            contains: false
        });

        $('#autocompleteCombobox2').rup_select({
            data: [
                {i18nCaption: 'asp', id:'asp_value'},
                {i18nCaption: 'c', id:'c_value'},
                {i18nCaption: 'c++', id:'c++_value'},
                {i18nCaption: 'coldfusion', id:'coldfusion_value'},
                {i18nCaption: 'groovy', id:'groovy_value'}
            ],
            defaultValue: '',
            combo: true,
            contains: false
        });
    });


    $('.contenedor').addClass('show');
});