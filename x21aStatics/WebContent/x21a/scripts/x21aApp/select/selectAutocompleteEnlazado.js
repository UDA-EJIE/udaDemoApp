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
    		data: [{
                	text: 'a',
                	id: '01'
            	},
            	{
            		text: 'b',
            		id: '02'
	            },
	            {
	                text: 'g',
	                id: '03'
	            }
            ],
            contains: false,
            combo: true,
            autocomplete:true
        });	
        
        $('#padreLocal').rup_select({
        	parent: ['abueloLocal'],
            data: {
                '01': [{
                    text: 'a1',
                    id: 'a1_id'
                }, {
                    text: 'a2',
                    id: 'a2_id'
                }, {
                    text: 'a3',
                    id: 'a3_id'
                }],
                '02': [{
                    text: 'b1',
                    id: 'b1_id'
                }, {
                    text: 'b2',
                    id: 'b2_id'
                }, {
                    text: 'b3',
                    id: 'b3_id'
                }],
                '03': [{
                    text: 'g1',
                    id: 'g1_id'
                }, {
                    text: 'g2',
                    id: 'g2_id'
                }, {
                    text: 'g3',
                    id: 'g3_id'
                }]

            },
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoLocal').rup_select({
        	parent: ['padreLocal'],
            data: {
                'b1_id': [{
                    text: 'Bilbao',
                    id: 'b1_1_id'
                }, {
                    text: 'Basauri',
                    id: 'b1_2_id'
                }, {
                    text: 'Galdakao',
                    id: 'b1_3_id'
                }],
                'b2_id': [{
                    text: 'Leioa',
                    id: 'b2_1_id'
                }, {
                    text: 'Las Arenas',
                    id: 'b2_2_id'
                }, {
                    text: 'Getxo',
                    id: 'b2_3_id'
                }],
                'b3_id': [{
                    text: 'Sestao',
                    id: 'b3_1_id'
                }, {
                    text: 'Barakaldo',
                    id: 'b3_2_id'
                }, {
                    text: 'Portu',
                    id: 'b3_3_id'
                }]

            },
            contains: false,
            combo: true,
            autocomplete:true
        });
      
        // REMOTO
        $('#abueloRemoto').rup_select({
        	url : 'autocomplete/remoteEnlazadoProvincia',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true,
            customClasses: ['select-material']
        });	
        
        $('#padreRemoto').rup_select({
        	parent: ['abueloRemoto'],
        	url : 'autocomplete/remoteEnlazadoComarca',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoRemoto').rup_select({
        	parent: ['padreRemoto'],
        	url : 'autocomplete/remoteEnlazadoLocalidad',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });
         
        // MIXTO
        $('#abueloMixto').rup_select({
        	url : 'autocomplete/remoteEnlazadoProvincia',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        });	
        
        $('#padreMixto').rup_select({
        	parent: ['abueloMixto'],
        	data: {
                '1': [{
                    text: 'Vitoria',
                    id: '5'
                }],
                '2': [{
                    text: 'Getxo',
                    id: '6'
                }, {
                    text: 'Portugalete',
                    id: '8'
                }],
                '3': [{
                    text: 'Donosti',
                    id: '10'
                }, {
                    text: 'Sansebastian',
                    id: '112'
                }]
            },
            contains: false,
            combo: true,
            autocomplete:true
        });
        
        $('#hijoMixto').rup_select({
        	parent: ['padreMixto'],
        	url : 'autocomplete/remoteEnlazadoLocalidad',
        	sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            contains: false,
            combo: true,
            autocomplete:true
        }); 

        $('.contenedor').addClass('show');
    });
});