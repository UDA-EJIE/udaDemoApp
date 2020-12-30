/*!
 * Copyright 2020 E.J.I.E., S.A.
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
        // LOCAL
    	$('#abueloLocal').rup_autocomplete({
    		source: ['Ayuntamiento','Diputación','Policía','Bomberos'],
            contains: false,
            combobox: true
        });	
        
        $('#padreLocal').rup_autocomplete({
        	source: ['Álava','Vizcaya','Gipúzcoa'],
            contains: false,
            combobox: true
        });
        
        $('#hijoLocal').rup_autocomplete({
        	parent: ['abueloLocal', 'padreLocal'],
            source: {
                'Ayuntamiento@@Álava'	:['Ayuntamiento de Álava'],
                'Ayuntamiento@@Vizcaya'	:['Ayuntamiento de Vizcaya'],
                'Ayuntamiento@@Gipúzcoa':['Ayuntamiento de Gipúzcoa'],
                'Diputación@@Álava'		:['Diputación de Álava'],
                'Diputación@@Vizcaya'	:['Diputación de Vizcaya'],
                'Diputación@@Gipúzcoa'	:['Diputación de Gipúzcoa'],
                'Policía@@Álava'		:['Policía de Álava'],
                'Policía@@Vizcaya'		:['Policía de Vizcaya'],
                'Policía@@Gipúzcoa'		:['Policía de Gipúzcoa'],
                'Bomberos@@Álava'		:['Bomberos de Álava'],
                'Bomberos@@Vizcaya'		:['Bomberos de Vizcaya'],
                'Bomberos@@Gipúzcoa'	:['Bomberos de Gipúzcoa']
            },
            multiValueToken: '@@',
            contains: false,
            combobox: true
        });
        
        // REMOTO
        $('#abueloRemoto').rup_autocomplete({
        	source : 'autocomplete/remoteEnlazadoMultipleDepartamento',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#padreRemoto').rup_autocomplete({
        	source : 'autocomplete/remoteEnlazadoMultipleProvincia',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        $('#hijoRemoto').rup_autocomplete({
        	parent: ['abueloRemoto', 'padreRemoto'],
        	source : 'autocomplete/remoteEnlazadoMultipleDepartamentoProvincia',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });

        $('.contenedor').addClass('show');
    });
});