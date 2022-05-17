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
	window.initRupI18nPromise.then(function () {
	    $('#multiSelect').rup_select({
	        data : [
	            {i18nCaption: 'rubya', id:'ruby_value'},
	            {i18nCaption: 'c', id:'c_value'},
	            {i18nCaption: 'scala', id:'scala_value'},
	            {i18nCaption: 'javascript', id:'javascript_value'},
	            {i18nCaption: 'c++', id:'c++_value'},
	            {i18nCaption: 'haskell', id:'haskell_value'},
	            {i18nCaption: 'asp', id:'asp_value'},
	            {i18nCaption: 'java', id:'java_value'},
	            {i18nCaption: 'php', id:'php_value'},
	            {i18nCaption: 'groovy', id:'groovy_value'},
	            {i18nCaption: 'python', id:'python_value'},
	            {i18nCaption: 'coldfusion', id:'coldfusion_value'},
	            {i18nCaption: 'perl', id:'perl_value'}
	        ],
	        selected: ['perl_value', 'javascript_value', 'ruby_value'], //value && index
	        ordered: false,
	        multiple: true,
	        udaSkill: false,
	        //placeholder: '',// para que no venga una seleccionado, si no esta el selected y no es multiple
	        closeOnSelect : false,
	        change: function () {
	            console.log('selectMultiLocal:::Changed');
	        }
	    }); 
		
    $('#multiSelectRemoto').rup_select({
	        url : 'comboSimple/remote',
	        sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
	        customClasses: ['select-material'],
	        multiple: true,
	        //placeholder: '[Seleccione gracias..]',
	        closeOnSelect : false,
	        change: function () {
	            console.log('selectMultiRemoto:::Changed');
	        }
	    });
   			
	    $('#multiSelectGrupos').rup_select({
            dataGroups: [{
            	'id':'1',
            	'text': 'futbol',
                'children': [{
                    i18nCaption: 'alaves',
                    id: 'alaves_value',
                    style: 'garage',
                    stylePosition:'A' //B - Before , M - middle , A - After
                },
                {
                    i18nCaption: 'ath',
                    id: 'ath_value',
                    style: 'heart text-danger'
                },
                {
                    i18nCaption: 'real',
                    id: 'real_value',
                    style:'delete',
                    stylePosition:'B'
                }
                ],style:'soccer'
            },
            {
            	'id':'2',
            	'text':'baloncesto',
                'children': [{
                    i18nCaption: 'laboral',
                    id: 'laboral_value'
                },
                {
                    i18nCaption: 'bilbao',
                    id: 'bilbao_value'
                },
                {
                    i18nCaption: 'lagun aro',
                    id: 'lagun aro_value'
                }
                ],style:'basketball'
            },
            {
            	'id':'3',
            	'text':'formula1',
            	'children': [{
                    i18nCaption: 'falonso',
                    id: 'falonso_value'
                    
                },
                {
                    i18nCaption: 'hamilton',
                    id: 'hamilton_value'
                },
                {
                    i18nCaption: 'vettel',
                    id: 'vettel_value'
                }
                ],
                style: 'car'
            }
            ],
	        height: 300,
	        customClasses: ['select-material'],
	        placeholder: '[Seleccione gracias..]',
	        //allowClear: true,
	        multiple: true,
	        change: function () {
	            console.log('selectMultiGroups:::Changed');
	        }
	    });
	    
	    $("#multiSelectGruposRemoto").rup_select(
	    	{
	            url : 'comboSimple/remoteGroupEnlazado',
	            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
	            customClasses: ['select-material'],
	            multiple: true,
	            //placeholder: '[Seleccione gracias..]',
	            closeOnSelect : false,
	            groups:true,
	            change: function () {
	                console.log('multicomboGruposRemoto:::Changed');
	            },
	            selected: ['4Prov']
	    	});
	        
	    

	    $('#multiSelectLoadFromSelect').rup_select({
	        loadFromSelect: true,
	        customClasses: ['select-material'],
	        multiple: true,
	        closeOnSelect : false,
	        change: function () {
	            console.log('selectMultiLoadFromSelect:::Changed');
	        }
	    });	    
	});
	$('.contenedor').addClass('show');
});
