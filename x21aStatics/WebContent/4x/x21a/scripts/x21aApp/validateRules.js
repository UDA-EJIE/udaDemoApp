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
        var $feedbackRequiredRules = jQuery('#feedbackRequiredRules').rup_feedback({
            type: 'error',
            closeLink: true,
            block:false
        });

        $('#diaObligatorio').rup_combo({
            source : [
                {i18nCaption: 'lunes', value:'1'},
                {i18nCaption: 'martes', value:'2'},
                {i18nCaption: 'miercoles', value:'3'},
                {i18nCaption: 'jueves', value:'4'},
                {i18nCaption: 'viernes', value:'5'},
                {i18nCaption: 'sabado', value:'6'},
                {i18nCaption: 'domingo', value:'7'}
            ],
            width: 100,
            ordered:false
        });

        $('#formRequiredRules').rup_validate({
            feedback: $feedbackRequiredRules,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback:true,
            rules:{
                'randomData[campoObligatorio1]':{required:true},
                'randomData[campoObligatorio2]':{required:'#esObligatorio:checked'},
                'randomData[campoObligatorio3]':{
                    required:function(){
                        return $('#diaObligatorio').val() > 5;
                    }
                }
            }
        });

        // Validaciones numéricas

        var $feedbackNumeric = jQuery('#feedbackNumeric').rup_feedback({
            type: 'error',
            closeLink: true,
            block:false
        });

        $('#formNumeric').rup_validate({
            feedback: $feedbackNumeric,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback:true,
            rules:{
                'randomData[valorMinimo]':{min:10},
                'randomData[valorMaximo]':{max:30},
                'randomData[valorIntervalo]':{range:[10,30]},
                'randomData[numeroDecimal]':{number:true},
                'randomData[entero]':{integer:true}
            }
        });

        // Validaciones texto

        var $feedbackText = jQuery('#feedbackText').rup_feedback({
            type: 'error',
            closeLink: true,
            block:false
        });

        $('#formText').rup_validate({
            feedback: $feedbackText,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback:true,
            rules:{
                'randomData[longitudMinima]':{minlength:8},
                'randomData[longitudMaxima]':{maxlength:20},
                'randomData[longitudIntervalo]':{rangelength:[8,20]},
                'randomData[numeroDecimal]':{number:true},
                'randomData[soloDigitos]':{digits:true},
                'randomData[palabrasMaximo]':{maxWords:6},
                'randomData[palabrasMinimo]':{minWords:2},
                'randomData[palabrasIntervalo]':{rangeWords:[2,6]},
                'randomData[letrasYPuntuacion]':{letterswithbasicpunc:true},
                'randomData[alfanumerico]':{alphanumeric:true},
                'randomData[soloLetras]':{lettersonly:true},
                'randomData[sinEspacios]':{nowhitespace:true},
                'randomData[patron]':{pattern:'\\d'}
            }
        });
    });


    $('.contenedor').addClass('show');
});