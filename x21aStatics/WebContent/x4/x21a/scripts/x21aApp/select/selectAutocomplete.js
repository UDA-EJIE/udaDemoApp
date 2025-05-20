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
            deleteOnDeselect: true,
            defaultValue:"c++",
            minimumResultsForSearch:2,
            width: '100%'
        });	
    
            $('#selectboxRemoto').rup_select({
            	url : 'autocomplete/remote',
                sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code'},
                placeholder: "Select an option", 
                allowClear: true,
                autocomplete: true,
                defaultValue:"ayuntamiento",
                minimumResultsForSearch:2,
                combo:true,
                deleteOnDeselect: true,
                width: '100%'
            });	
        
        $('#selectRemoto').rup_select({
            url : 'autocomplete/remoteEnlazadoProvincia',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            selected: '3',
            width: '99%',
            combo: true,
            autocomplete: true,
            searchZero: true,
            customClasses: ['select-material'],
            change: function () {
                console.log('selectRemote:::Changed');
            },
            select : function() {
                var valor = $('#'+this.id).rup_select('getRupValue');
                $('#autocompleteGet').prop("readonly", false);
                $('#autocompleteGet').rup_select('setSource','autocomplete/remote?codProvincia='+valor);
            }
        });	
        
        $('#autocompleteGet').rup_select({
            url : 'autocomplete/remote',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code'},
            menuMaxHeight: 200,
            minLength: 3,
            combo: true,
            autocomplete: true
        });
        
         
        $('#autocomplete').rup_select({
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
                {text: 'scala', id:'scala_value'},
                {text: 'scál2a', id:'scala_value'}
            ],
            menuMaxHeight: 40,
            defaultValue : 'java',
            contains : false,
            minimumResultsForSearch: 1,
            autocomplete:true,
            select:function(){
                let value = $('#autocomplete').rup_select('getRupValue');
                let datos = $('#autocomplete').rup_select('getDataSelected');
                alert('Seleccionado: '+value + ' Datos:' + datos.text);
            }
        });
        
        $('#autocompleteNotAccent').rup_select({
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
                {text: 'scala', id:'scala_value'},
                {text: 'scál2a', id:'scala_value2'},
                {text: 'scáLa4', id:'scala_value3'},
                {text: 'scála3', id:'scala_value4'}
            ],
            selected : 'java_value',
            autocomplete:true,
            minimumResultsForSearch: 2,
            accentFolding:false,
            placeholder: "Select an option",
            allowClear: true,
            searchZero:true,
            deleteOnDeselect: true,
            width: '100%'
        });
        
        $('#patron').rup_select({
            url : 'autocomplete/remote',
            cache: false,
            autocomplete:true,
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code'},
            minimumResultsForSearch: 4,
            select:function(){
                var value=$('#patron').rup_select('getRupValue');
                console.log('Seleccionado: '+value);
                return true;
            }
        });


        $('.contenedor').addClass('show');
    });
});