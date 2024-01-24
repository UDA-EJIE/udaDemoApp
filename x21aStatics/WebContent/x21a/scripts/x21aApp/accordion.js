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
jQuery(function($) {
    
    $('.rup_accordion').rup_accordion({
        animate: 'linear',
        active: false,
        autoHeight: false,
        collapsible: true
    });
    
    $('#accordionExample2').rup_accordion('option','change', function(event, ui){
        //section rup_select
        if(ui.options.active === 0){
            $('#autocomplete').rup_select('search', 'java');
        //seccion mantenimiento
        } else if (ui.options.active === 1){
            
            //////////////////////////
            //seccion Mantenimiento
            //////////////////////////
            
            if($('#mockPageContent').length > 0){
                $('#mockPageContent').remove();
            }
            
            $.rup_ajax({
                url: '/x21aAppWar/calendar/noTemplate',
                //Cabecera RUP
                success: function(data) {
                    $('#maintGroup').html('');
                    $('#maintGroup').append(data);
                },
                error: function (){
                    alert('Se ha producido un error al recuperar los datos del servidor');
                }
            });
        } else if (ui.options.active === 2){
            
            /////////////////////
            //seccion pestañas
            /////////////////////
            
            if($('#mockPageContent').length > 0){
                $('#mockPageContent').remove();
            }
        
            if(!$('#maintTab').hasClass('ui-tabs')){
                $('#maintTab').rup_tabs({
                    tabs:[
                        {i18nCaption:'maint1', url:'/x21aAppWar/calendar/noTemplate'},
                        {i18nCaption:'maint2', url:'/x21aAppWar/calendar/noTemplate'},
                        {i18nCaption:'maint3', url:'/x21aAppWar/calendar/noTemplate'},
                        {i18nCaption:'edit1', url:'/x21aAppWar/lista/noTemplate'},
                        {i18nCaption:'multi1', url:'/x21aAppWar/lista/noTemplate'}],
                    load: function(){
                        if($('#mockPageContent').length > 0){
                            $('#maintTab').rup_tabs('disableTabs', {
                                idTab: 'maintTab',
                                position: [1,2,3,4]
                            });
                            $('#mockPageContent #loginButtonObject').on('click', function (){
                                $('#maintTab').rup_tabs('enableTabs', {
                                    idTab: 'maintTab',
                                    position: [1,2,3,4]
                                });
                            });
                        }
                    }
                });
            } else {
                $('#maintTab').rup_tabs('loadTab', {
                    idTab: 'maintTab',
                    position: 0
                });
                $('#maintTab').rup_tabs('selectTab', {
                    idTab: 'maintTab',
                    position: 0
                });
            }
        }
    });
    
    $('#accordionExample2').rup_accordion('option','changestart', function(event, ui){
        //section rup_select
        if (ui.options.active === 1){
            $('#maintGroup').html('');
        }
    });
    
    /////////////////////////
    //section rup_select
    /////////////////////////
    $('#autocomplete').rup_select({
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
        contains: false
    });
    
    $('#patron').rup_select({
        url: 'autocomplete/remote',
        sourceParam: {text:'desc'+$.rup_utils.capitalizedLang(), id:'code'}
    });
    
    ///////////////////
    //seccion upload
    ///////////////////
    
    // Creacion de los componentes feedback
    $('#feedback_fileupload_form_multiple').rup_feedback();
    
    // Se utiliza jquery.form para realizar el submit de los formularios por AJAX
    $('#usuarioFormSimple').ajaxForm(function(){
        $('#feedback_fileupload_form_multiple').rup_feedback('set','Los datos se han enviado correctamente');
    });
    
    // Dos controles Upload intergrados en un mismo formulario
    $('#fileupload_file_form_padre').rup_upload({
        form:'fileupload_form_multiple',
        fileInput: $('#file_form_padre'),
        submitFormButton: $('#sendButtonMultiple'),
        maxFileSize: 5000000,
        singleFileUploads:true,
        submitInForm:true
    });
     
    $('#fileupload_file_form_madre').rup_upload({
        form:'fileupload_form_multiple',
        fileInput: $('#file_form_madre'),
        submitFormButton: $('#sendButtonMultiple'),
        maxFileSize: 5000000,
        singleFileUploads:true,
        submitInForm:true
    });


    $('.contenedor').addClass('show');
});