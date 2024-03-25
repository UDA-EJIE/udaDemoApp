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
    
    var $feedbackDate = jQuery('#feedbackDate').rup_feedback({ 
        type: 'ok',
        closeLink: true,
        block:false
    });
    
    $('#dateField').rup_date({
        
    });
    
    $('#formDate').rup_validate({
        feedback: $feedbackDate,
        liveCheckingErrors: false,
        showFieldErrorAsDefault: true,
        showErrorsInFeedback: true,
        showFieldErrorsInFeedback:true, 
        rules:{
            'dateField':{required:true}
            
        }
    });
    
    var $feedbackAutocomplete = jQuery('#feedbackAutocomplete').rup_feedback({ 
        type: 'ok',
        closeLink: true,
        block:false
    });
    
    $('#autocompleteField').rup_select({
        data: [
            {i18nCaption: 'asp', id:'asp_value'},
            {i18nCaption: 'c', id:'c_value'},
            {i18nCaption: 'c++', id:'c++_value'},
            {i18nCaption: 'coldfusion', id:'coldfusion_value'},
            {i18nCaption: 'groovy', id:'groovy_value'},
            {i18nCaption: 'haskell', id:'haskell_value'},
            {i18nCaption: 'java', id:'java_value'},
            {i18nCaption: 'javascript', id:'javascript_value'},
            {i18nCaption: 'perl', id:'perl_value'},
            {i18nCaption: 'php', id:'php_value'},
            {i18nCaption: 'python', id:'python_value'},
            {i18nCaption: 'ruby', id:'ruby_value'},
            {i18nCaption: 'scala', id:'scala_value'}
        ],
        defaultValue: 'java',
        contains: false
    });
    
    $('#formAutocomplete').rup_validate({
        feedback: $feedbackAutocomplete,
        liveCheckingErrors: false,
        showFieldErrorAsDefault: true,
        showErrorsInFeedback: true,
        showFieldErrorsInFeedback:true, 
        rules:{
            'autocompleteField':{required:true}
            
        }
    });


    $('.contenedor').addClass('show');
});