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
        $('#selectboxLocal').rup_select({
            data : [
                {text: 'asp', id:'asp_value'},
                {text: 'c', id:'c_value'},
                {text: 'c++', id:'c++_value'},
                {text: 'coldfusion', id:'coldfusion_value'},
                {text: 'groovy', id:'groovy_value'},
                {text: 'haskell', id:'haskell_value'},
                {text: 'java', id:'java_value'},
                {text: 'javascript', id:'javascript_value'},
                {text: 'perl', id:'perl_value'},
                {text: 'php', id:'php_value'},
                {text: 'python', id:'python_value'},
                {text: 'ruby', id:'ruby_value'},
                {text: 'scala', id:'scala_value'}
            ],
            placeholder: "Select an option",
            autocomplete: true,
            allowClear: true,
            searchZero:true,
            combo:true,
            selected:"php_value",
            minimumResultsForSearch:2,
            width: '100%'
        });	
    
            $('#selectboxRemoto').rup_select({
            	url : 'autocomplete/remote',
                sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code'},
                placeholder: "Select an option", 
                allowClear: true,
                autocomplete: true,
                minimumResultsForSearch:2,
                combo:true,
                width: '100%'
            });	
        
 /*       $('#autocompleteGet').rup_autocomplete({
            source : 'autocomplete/remote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code'},
            menuMaxHeight: 200,
            minLength: 3,
            combobox: true,
            contains: true,
            showDefault: true,
            accentFolding:true
        });
        
        $('#comboRemoto').rup_combo({
            source : 'comboSimple/remote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            selected: '3',
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboRemote:::Changed');
            },
            select : function() {
                var valor = $('#comboRemoto').rup_combo('getRupValue');
                $('#autocompleteGet').prop("readonly", false);
                $('#autocompleteGet').rup_autocomplete({
                    source : 'autocomplete/remote?codProvincia='+valor,
                    sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code'},
                    minLength: 4
                });
                jQuery('#autocompleteGet_label').data('tmp.loadObjects.term','notLoad');
            }
        });	
        
        $('#autocomplete').rup_autocomplete({
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
                {i18nCaption: 'scala', value:'scala_value'},
                {label: 'scál2a', value:'scala_value'}
            ],
            menuMaxHeight: 40,
            defaultValue : 'java',
            contains : false,
            select:function(){
                var value=$('#autocomplete').rup_autocomplete('getRupValue');
                alert('Seleccionado: '+value);
            }
        });
        
        $('#autocompleteNotAccent').rup_autocomplete({
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
                {i18nCaption: 'scala', value:'scala_value'},
                {label: 'scál2a', value:'scala_value'},
                {label: 'scáLa4', value:'scala_value'},
                {label: 'scála3', value:'scala_value'}
            ],
            defaultValue : 'java',
            contains : false,
            accentFolding:false
        });
        
        $('#patron').rup_autocomplete({
            source : 'autocomplete/remote',
            disabledCache: true,
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code'},
            minLength: 4,
            select:function(){
                var value=$('#patron').rup_autocomplete('getRupValue');
                console.log('Seleccionado: '+value);
                return true;
            }
        });

*/
        $('.contenedor').addClass('show');
    });
});