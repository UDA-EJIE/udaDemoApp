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
	window.initRupI18nPromise.then(function () {
	    // Definicion de las pestanas
	    $('#tabsFormulario').rup_tabs({
	        tabs : [ {
	            i18nCaption : 'ejemploYFunciones',
	            layer : '#divformHttpSubmit'
	        }, {
	            i18nCaption : 'multiplesEntidades',
	            layer : '#divMultiplesEntidades'
	        }, {
	            i18nCaption : 'subidaArchivos',
	            layer : '#divSubidaFicheros'
	        }
	        ]
	    });
	    
	    /*
	     * Funcion para crear el objeto de configuracion del componente formulario y una vez realizado crear el componente. 
	     */
	    function configureForm(){
	        
	        // Objeto de configuracion del componente.
	        var configSettings = {};
	        
	        // Propiedades de configuracion por defecto comunes a todas las posibilidades de configuracion.
	        var defaultConfigSettings = {
	            feedback:$('#feedbackMensajes'),
	            success:function(xhr){
	                $('#feedbackMensajes').rup_feedback('set',$.rup_utils.printMsg($.parseJSON($.parseJSON(xhr))),'ok');
	            }
	        };
	        
	        // Creacion de la configuracion del componente de validaciones.
	        var validationConfigSettings = {
	            validate:{
	                liveCheckingErrors:true,
	                showFieldErrorAsDefault:true,
	                showErrorsInFeedback:true,
	                showErrorsDetailsInFeedback:true,
	                feedback:$('#feedbackMensajes'),
	                messages:{
	                    'password_confirm':$.rup.i18n.app.validaciones.password_confirm,
	                    'email_confirm':$.rup.i18n.app.validaciones.email_confirm
	                },
	                rules:{
	                    'nombre':{required:true},
	                    'apellido1':{required:true},
	                    'usuario':{required:true,minlength:4},
	                    'password':{required:true,minlength:4},
	                    'password_confirm': {
	                        equalTo: '#password'
	                    },
	                    'email':{email: true},
	                    'email_confirm': {
	                        equalTo: '#email'
	                    },
	                    'dni':{dni:true}
	                }
	            }
	        };
	        
	        // Creacion de la configuracion del componente de formulario
	        var formConfigSettings = {
	            ajaxForm:{
	                url:$.rup.CTX_PATH+'patrones/form/ajax',
	                success:function(){
	                    $('#feedbackMensajes').rup_feedback('set','El formulario se ha enviado correctamente mediante un submit AJAX', 'ok');
	                }
	            }
	        };
	        
	        
	        // Se conforma el objeto de configuracion final
	        configSettings = $.extend(true, configSettings, defaultConfigSettings, validationConfigSettings,
	            ($('#formTypeCombo').val()==='ajax'?formConfigSettings:{})
	        );
	        
	        // Se realiza la creacion del componente formulario
	        $('#formHttpSubmit').rup_form(configSettings);
	    }
	    
	    /*
	     * CREACION DE LOS COMPONENTES DEL FORMULARIO
	     */
	    
	    // Feedback
	    jQuery('#feedbackMensajes').rup_feedback({ 
	        type: 'ok',
	        closeLink: true,
	        delay: 1000,
	        fadeSpeed: 500,
	        block:true
	    });
	    
	    // Combo sexo
	    jQuery('#sexo').rup_select({
	        data: [
	            {i18nCaption: 'masculino', id:'M'},
	            {i18nCaption: 'femenino', id:'F'}
	        ],
	        i18nId:'sexo'
	    });
	    
	    // Combo pais
	    jQuery('#pais').rup_select({
	        url: '../nora/pais',
	        sourceParam : {text:'dsO', id:'id'},
	        blank : '0'
	    });
	    
	    // Combo autonomia
	    jQuery('#autonomia').rup_select({
	        url: '../nora/autonomia',
	        sourceParam : {text:'dsO', id:'id'},
	        blank : ''
	    });
	    
	    //	jQuery("#formTypeCombo").rup_select({
	    //		data: [{text:"AJAX submit", id:"ajax"},{text:"HTTP submit", id:"http"}]
	    //	});
	    
	    // Combo provincia
	    jQuery('#provincia').rup_select({
	        parent: ['autonomia'],
	        source : '../nora/provincia',
	        firstLoad:[{'value':'01','label':'Alava/Araba'},{'value':'20','label':'Gipuzkoa'},{'value':'48','label':'Bizkaia'}],
	        sourceParam : {label:'dsO', value:'id'},
	        blank : ''
	    });
	    
	    // Autocomplete municipio
	    jQuery('#municipio').rup_select({
	        url: '../nora/municipio',
	        sourceParam: {text:'dsO', id:'id'},
	        autocomplete: true
	    });
	    
	    // Autocomplete calle
	    jQuery('#calle').rup_select({
	        url: '../nora/calle',
	        sourceParam: {text:'dsO', id:'id'},
	        autocomplete: true
	    });
	    
	    // Fecha
	    jQuery('#fechaNacimiento').rup_date({
	    	labelMaskId : 'fecha-mask',
	        showButtonPanel : true,
	        showOtherMonths : true,
	        noWeekend : true,
	        datetimepicker : true,
	        placeholderMask : true,
	        mask: 'dd/mm/yyyy hh:mm:ss',
	        showSecond: true,
	        dateFormat: 'dd/mm/yy',
	        timeFormat: 'hh:mm:ss',
	        timezoneOffset:120
	    });
	    
	    // Fecha
	    jQuery('#fechaNacimiento3').rup_date({
	    	labelMaskId : 'fecha-mask',
	        showButtonPanel : true,
	        showOtherMonths : true,
	        noWeekend : true,
	        datetimepicker : true,
	        placeholderMask : true,
	        mask: 'dd/mm/yyyy hh:mm:ss',
	        showSecond: true,
	        dateFormat: 'dd/mm/yy',
	        timeFormat: 'hh:mm:ss',
	        timezoneOffset:120
	    });
	    
	    $('#fechaNacimiento').rup_date('setDate', jQuery('#fechaNacimiento').val());
	
	    
	    
	    /*
	     * FUNCIONES
	     */
	    
	    // Se muestra un feedback el caso de que se haya realizado un envio http del formulario
	    if (window.respuesta==='true'){
	        $('#feedbackMensajes').rup_feedback('set','El formulario se ha enviado correctamente mediante un submit http', 'ok');
	    }
	
	    // Se realiza la reconfiguracion del componente de acuerdo a los valores indicados en la seccion de configuracion
	    jQuery('#botonConfiguracion').bind('click',function(){
	        $('#formHttpSubmit').rup_form('destroy');
	        $('label.error',$('#formHttpSubmit')).remove();
	        $('img.error.rup-maint_validateIcon',$('#formHttpSubmit')).remove();
	        $('input.error',$('#formHttpSubmit')).removeClass('error');
	        $('#feedbackMensajes').hide();
	        
	        configureForm();
	    });
	    
	    
	    /*
	     * INICIALIZACION DEL FORMULARIO
	     */
	    configureForm();
	    
	    
	    
	    /*
	     * EJEMPLOS DE METODOS
	     */
	    
	    $('#botonFormSerialize').bind('click',function(){
	        var serializedForm = $('#formHttpSubmit').rup_form('formSerialize');
	        $('#resultadoFormSerialize').text(serializedForm);
	    });
	    
	    $('#botonFieldSerialize').bind('click',function(){
	        var serializedFields = $('input',$('#formHttpSubmit')).rup_form('fieldSerialize');
	        $('#resultadoFieldSerialize').text(serializedFields);
	    });
	    
	    $('#botonFieldValue').bind('click',function(){
	        var serializedFields = $('input',$('#formHttpSubmit')).rup_form('fieldValue');
	        $('#resultadoFieldValue').text(serializedFields.toString());
	    });
	    
	    $('#botonResetForm').bind('click',function(){
	        $('#formHttpSubmit').rup_form('resetForm');
	    });
	    
	    $('#botonClearForm').bind('click',function(){
	        $('#formHttpSubmit').rup_form('clearForm');
	    });
	    
	    $('#botonClearFields').bind('click',function(){
	        $('input',$('#formHttpSubmit')).rup_form('clearFields');
	    });
	    
	    
	    $('#formMultientidades').rup_form({
	        url:$.rup.CTX_PATH+'patrones/form/multientidades',
	        feedback:$('#feedbackMensajes'),
	        success:function(xhr){
	            $('#feedbackMensajes').rup_feedback('set',$.rup_utils.printMsg($.parseJSON($.parseJSON(xhr))),'ok');
	        },
	        validate:{
	            rules:{
	                'departamento.code':{digits:true}
	            }
	        }
	    
	    });
	    
	    $('#formMultientidadesMismoTipo').rup_form({
	        url:$.rup.CTX_PATH+'patrones/form/multientidadesMismoTipo',
	        feedback:$('#feedbackMensajes'),
	        success:function(xhr){
	            $('#feedbackMensajes').rup_feedback('set',$.rup_utils.printMsg($.parseJSON($.parseJSON(xhr))),'ok');
	        },
	        validate:{
	            rules:{
	                'comarca1.code':{digits:true},
	                'comarca2.code':{digits:true},
	                'comarca3.code':{digits:true},
	                'comarca3.provincia.code':{digits:true}
	            }
	        }
	    });
	    
	    $('#formSubidaArchivos').rup_form({
	        url:$.rup.CTX_PATH+'patrones/form/subidaArchivos',
	        feedback:$('#feedbackMensajes'),
	        dataType: 'json',
	        success:function(xhr){
	            $('#feedbackMensajes').rup_feedback('set',$.rup_utils.printMsg(xhr),'ok');
	        }
	    });
    });

    $('.contenedor').addClass('show');
});