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
	            {text: 'rubya', id:'ruby_value'},
	            {text: 'c', id:'c_value'},
	            {text: 'scala', id:'scala_value'},
	            {text: 'javascript', id:'javascript_value'},
	            {text: 'c++', id:'c++_value'},
	            {text: 'haskell', id:'haskell_value'},
	            {text: 'asp', id:'asp_value'},
	            {text: 'java', id:'java_value'},
	            {text: 'php', id:'php_value'},
	            {text: 'groovy', id:'groovy_value'},
	            {text: 'python', id:'python_value'},
	            {text: 'coldfusion', id:'coldfusion_value'},
	            {text: 'perl', id:'perl_value'}
	        ],
	       // selected: ['perl_value', 'javascript_value', 'ruby_value'], //value && index
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
	        placeholder: '[Seleccione gracias..]',
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
                    text: 'alaves',
                    id: 'alaves_value',
                    style: 'garage',
                    stylePosition:'A' //B - Before , M - middle , A - After
                },
                {
                    text: 'ath',
                    id: 'ath_value',
                    style: 'heart text-danger'
                },
                {
                    text: 'real',
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
                    text: 'laboral',
                    id: 'laboral_value'
                },
                {
                    text: 'bilbao',
                    id: 'bilbao_value'
                },
                {
                    text: 'lagun aro',
                    id: 'lagun aro_value'
                }
                ],style:'basketball'
            },
            {
            	'id':'3',
            	'text':'formula1',
            	'children': [{
                    text: 'falonso',
                    id: 'falonso_value'
                    
                },
                {
                    text: 'hamilton',
                    id: 'hamilton_value'
                },
                {
                    text: 'vettel',
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
	        groups:true,
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
