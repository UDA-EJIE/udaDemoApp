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
$(function() {
    
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
    
    $('#autocompleteField').rup_autocomplete({
        source : [
            {i18nCaption: 'asp', value:'asp_value'},
            {i18nCaption: 'c', value:'c_value'},
            {i18nCaption: 'c++', value:'c++_value'},
            {i18nCaption: 'coldfusion', value:'coldfusion_value'},
            {i18nCaption: 'groovy', value:'groovy_value'},
            {i18nCaption: 'haskell', value:'haskell_value'},
            {i18nCaption: 'java', value:'java_value'},
            {i18nCaption: 'javascript', value:'javascript_value'},
            {i18nCaption: 'perl', value:'perl_value'},
            {i18nCaption: 'php', value:'php_value'},
            {i18nCaption: 'python', value:'python_value'},
            {i18nCaption: 'ruby', value:'ruby_value'},
            {i18nCaption: 'scala', value:'scala_value'}
        ],
        defaultValue : 'java',
        contains : false
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
    
});