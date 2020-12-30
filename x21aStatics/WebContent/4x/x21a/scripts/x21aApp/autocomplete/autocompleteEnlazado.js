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
    		source: [{
                	i18nCaption: 'a',
                	value: '01'
            	},
            	{
            		i18nCaption: 'b',
            		value: '02'
	            },
	            {
	                i18nCaption: 'g',
	                value: '03'
	            }
            ],
            contains: false,
            combobox: true
        });	
        
        $('#padreLocal').rup_autocomplete({
        	parent: ['abueloLocal'],
            source: {
                '01': [{
                    i18nCaption: 'a1',
                    value: 'a1_value'
                }, {
                    i18nCaption: 'a2',
                    value: 'a2_value'
                }, {
                    i18nCaption: 'a3',
                    value: 'a3_value'
                }],
                '02': [{
                    i18nCaption: 'b1',
                    value: 'b1_value'
                }, {
                    i18nCaption: 'b2',
                    value: 'b2_value'
                }, {
                    i18nCaption: 'b3',
                    value: 'b3_value'
                }],
                '03': [{
                    i18nCaption: 'g1',
                    value: 'g1_value'
                }, {
                    i18nCaption: 'g2',
                    value: 'g2_value'
                }, {
                    i18nCaption: 'g3',
                    value: 'g3_value'
                }]

            },
            contains: false,
            combobox: true
        });
        
        $('#hijoLocal').rup_autocomplete({
        	parent: ['padreLocal'],
            source: {
                'b1_value': [{
                    i18nCaption: 'Bilbao',
                    value: 'b1_1_value'
                }, {
                    i18nCaption: 'Basauri',
                    value: 'b1_2_value'
                }, {
                    i18nCaption: 'Galdakao',
                    value: 'b1_3_value'
                }],
                'b2_value': [{
                    i18nCaption: 'Leioa',
                    value: 'b2_1_value'
                }, {
                    i18nCaption: 'Las Arenas',
                    value: 'b2_2_value'
                }, {
                    i18nCaption: 'Getxo',
                    value: 'b2_3_value'
                }],
                'b3_value': [{
                    i18nCaption: 'Sestao',
                    value: 'b3_1_value'
                }, {
                    i18nCaption: 'Barakaldo',
                    value: 'b3_2_value'
                }, {
                    i18nCaption: 'Portu',
                    value: 'b3_3_value'
                }]

            },
            contains: false,
            combobox: true
        });
        
        // REMOTO
        $('#abueloRemoto').rup_autocomplete({
        	source : 'autocomplete/remoteEnlazadoProvincia',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#padreRemoto').rup_autocomplete({
        	parent: ['abueloRemoto'],
        	source : 'autocomplete/remoteEnlazadoComarca',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        $('#hijoRemoto').rup_autocomplete({
        	parent: ['padreRemoto'],
        	source : 'autocomplete/remoteEnlazadoLocalidad',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });
        
        // MIXTO
        $('#abueloMixto').rup_autocomplete({
        	source : 'autocomplete/remoteEnlazadoProvincia',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });	
        
        $('#padreMixto').rup_autocomplete({
        	parent: ['abueloMixto'],
        	source: {
                '1': [{
                    i18nCaption: 'a1',
                    value: 'a1_value'
                }, {
                    i18nCaption: 'a2',
                    value: 'a2_value'
                }, {
                    i18nCaption: 'a3',
                    value: 'a3_value'
                }],
                '2': [{
                    i18nCaption: 'b1',
                    value: 'b1_value'
                }, {
                    i18nCaption: 'b2',
                    value: 'b2_value'
                }, {
                    i18nCaption: 'b3',
                    value: 'b3_value'
                }],
                '3': [{
                    i18nCaption: 'g1',
                    value: 'g1_value'
                }, {
                    i18nCaption: 'g2',
                    value: 'g2_value'
                }, {
                    i18nCaption: 'g3',
                    value: 'g3_value'
                }]
            },
            contains: false,
            combobox: true
        });
        
        $('#hijoMixto').rup_autocomplete({
        	parent: ['padreMixto'],
        	source : 'autocomplete/remoteEnlazadoLocalidad',
        	sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            contains: false,
            combobox: true
        });

        $('.contenedor').addClass('show');
    });
});