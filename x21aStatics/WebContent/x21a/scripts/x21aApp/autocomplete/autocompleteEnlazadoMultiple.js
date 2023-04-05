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
        $('#departamentoRemote').rup_autocomplete({
        	source : 'autocomplete/departamentoRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#provinciaRemote').rup_autocomplete({
        	source : 'autocomplete/provinciaRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        $('#dptoProvRemote').rup_autocomplete({
        	parent: ['abueloRemoto', 'padreRemoto'],
        	source : 'autocomplete/dptoProvRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        // MIXTO I (LOCAL, LOCAL y REMOTO)
        $('#abueloMixtoI').rup_autocomplete({
        	source: [
                {i18nCaption: 'Ayuntamiento', value: '1'},
                {i18nCaption: 'Diputación', value: '2'},
                {i18nCaption: 'Policía', value: '3'},
                {i18nCaption: 'Bomberos', value: '4'}
            ],
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#padreMixtoI').rup_autocomplete({
        	source: [
                {i18nCaption: 'Alava', value: '1'},
                {i18nCaption: 'Vizcaya', value: '2'},
                {i18nCaption: 'Gipuzcoa', value: '3'}
            ],
            contains: false,
            combobox: true
        });
        
        $('#hijoMixtoI').rup_autocomplete({
        	parent: ['abueloMixtoI', 'padreMixtoI'],
        	source : 'autocomplete/dptoProvRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        // MIXTO II (REMOTO, REMOTO y LOCAL)
        $('#abueloMixtoII').rup_autocomplete({
        	source : 'autocomplete/departamentoRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#padreMixtoII').rup_autocomplete({
        	source : 'autocomplete/provinciaRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        $('#hijoMixtoII').rup_autocomplete({
        	parent: ['abueloMixtoII', 'padreMixtoII'],
        	source: {
                '1##1'	:['Ayuntamiento de Álava'],
                '1##2'	:['Ayuntamiento de Vizcaya'],
                '1##3'	:['Ayuntamiento de Gipúzcoa'],
                '2##1'	:['Diputación de Álava'],
                '2##2'	:['Diputación de Vizcaya'],
                '2##3'	:['Diputación de Gipúzcoa'],
                '3##1'	:['Policía de Álava'],
                '3##2'	:['Policía de Vizcaya'],
                '3##3'	:['Policía de Gipúzcoa'],
                '4##1'	:['Bomberos de Álava'],
                '4##2'	:['Bomberos de Vizcaya'],
                '4##3'	:['Bomberos de Gipúzcoa']
            },
            contains: false,
            combobox: true
        });
        
        // MIXTO III (REMOTO, LOCAL y REMOTO)
        $('#abueloMixtoIII').rup_autocomplete({
        	source : 'autocomplete/departamentoRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#padreMixtoIII').rup_autocomplete({
        	source: [
                {i18nCaption: 'Alava', value: '1'},
                {i18nCaption: 'Vizcaya', value: '2'},
                {i18nCaption: 'Gipuzcoa', value: '3'}
            ],
            contains: false,
            combobox: true
        });
        
        $('#hijoMixtoIII').rup_autocomplete({
        	parent: ['abueloMixtoIII', 'padreMixtoIII'],
        	source : 'autocomplete/dptoProvRemote',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });

        $('.contenedor').addClass('show');
    });
});