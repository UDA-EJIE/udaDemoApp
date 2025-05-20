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
    	$('#abueloLocal').rup_select({
    		data: ['Ayuntamiento','Diputación','Policía','Bomberos'],
            contains: false,
            combo: true,
            autocomplete:true
        });	
        
        $('#padreLocal').rup_select({
        	data: ['Álava','Vizcaya','Gipúzcoa'],
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoLocal').rup_select({
        	parent: ['abueloLocal', 'padreLocal'],
            data: [{
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
            }],
            multiValueToken: '@@',
            contains: false,
            combo: true,
            autocomplete:true
        });
       
        // REMOTO
        $('#abueloRemoto').rup_select({
        	url : 'autocomplete/departamentoRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });	
        
        $('#padreRemoto').rup_select({
        	url : 'autocomplete/provinciaRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoRemoto').rup_select({
        	parent: ['abueloRemoto', 'padreRemoto'],
        	url : 'autocomplete/dptoProvRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: true,
            combo: true,
            autocomplete:true
        });
         
        // MIXTO I (LOCAL, LOCAL y REMOTO)
        $('#abueloMixtoI').rup_select({
        	data: [
                {text: 'Ayuntamiento', id: '1'},
                {text: 'Diputación', id: '2'},
                {text: 'Policía', id: '3'},
                {text: 'Bomberos', id: '4'}
            ],
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete: true
        });	
        
        $('#padreMixtoI').rup_select({
        	data: [
                {text: 'Alava', id: '1'},
                {text: 'Vizcaya', id: '2'},
                {text: 'Gipuzcoa', id: '3'}
            ],
            contains: false,
            combo: true,
            autocomplete: true
        });
        
        $('#hijoMixtoI').rup_select({
        	parent: ['abueloMixtoI', 'padreMixtoI'],
        	url : 'autocomplete/dptoProvRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete: true
        });
        
        // MIXTO II (REMOTO, REMOTO y LOCAL)
        $('#abueloMixtoII').rup_select({
        	url : 'autocomplete/departamentoRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });	
        
        $('#padreMixtoII').rup_select({
        	url : 'autocomplete/provinciaRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoMixtoII').rup_select({
        	parent: ['abueloMixtoII', 'padreMixtoII'],
        	data: [{
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
            }],
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        // MIXTO III (REMOTO, LOCAL y REMOTO)
        $('#abueloMixtoIII').rup_select({
        	url : 'autocomplete/departamentoRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });	
        
        $('#padreMixtoIII').rup_select({
        	data: [
                {text: 'Alava', id: '1'},
                {text: 'Vizcaya', id: '2'},
                {text: 'Gipuzcoa', id: '3'}
            ],
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoMixtoIII').rup_select({
        	parent: ['abueloMixtoIII', 'padreMixtoIII'],
        	url : 'autocomplete/dptoProvRemote',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });

        $('.contenedor').addClass('show');
    });
});