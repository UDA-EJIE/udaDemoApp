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
		
		 //LOCAL
		$('#selectAbuelo').rup_select({
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
		     selected: '02',
		     placeholder: ''
		 });

		 $('#multiselectPadre').rup_select({
		     parent: ['selectAbuelo'],
		     data: {
		         '01': [{
		             id: 'a1',
		             text: 'a1_text'
		         }, {
		             id: 'a2',
		             text: 'a2_text'
		         }, {
		             id: 'a3',
		             text: 'a3_text'
		         }],
		         '02': [{
		             id: 'b1',
		             text: 'b1_text'
		         }, {
		             id: 'b2',
		             text: 'b2_text'
		         }, {
		             id: 'b3',
		             text: 'b3_text'
		         }],
		         '03': [{
		             id: 'g1',
		             text: 'g1_text'
		         }, {
		             id: 'g2',
		             text: 'g2_text'
		         }]

		     },
		     selected: 'b1',
		     placeholder: '[Seleccione]'
			 ,multiple:true
		 });

		 $('#selectHijo').rup_select({
		     parent: ['multiselectPadre'],
		     data: {
		         'b1': [{
		             text: 'Bilbao',
		             id: 'b1_1_text'
		         }, {
		         	text: 'Basauri',
		             id: 'b1_2_text'
		         }, {
		         	text: 'Galdakao',
		             id: 'b1_3_text'
		         }],
		         'b2': [{
		         	text: 'Leioa',
		             id: 'b2_1_text'
		         }, {
		         	text: 'Las Arenas',
		             id: 'b2_2_text'
		         }, {
		         	text: 'Getxo',
		             id: 'b2_3_text'
		         }],
		         'b3': [{
		         	text: 'Sestao',
		             id: 'b3_1_text'
		         }, {
		         	text: 'Barakaldo',
		             id: 'b3_2_text'
		         }, {
		         	text: 'Portu',
		             id: 'b3_3_text'
		         }],
				 'g1': [{
				 	text: 'Miranda g1',
				     id: 'g1_1_text'
				 }],
				 'g2': [{
				 	text: 'Girona g2',
				     id: 'g2_1_text'
				 }]

		     },
		     selected: 'b1_2_text'
		 }); 
		 
		 //REMOTO
		 $('#selectAbueloRemoto').rup_select({
		     url: 'comboEnlazadoSimple/remoteEnlazadoProvincia',
		     sourceParam: {
		         text: 'desc' + $.rup_utils.capitalizedLang(),
		         id: 'code',
		         style: 'css'
		     },
		     placeholder: '[Seleccionar]',
		   //  selected: '2',
		     change: function () {
		         console.log('selectAbueloRemoto:::Changed');
		     }
		 });

		 $('#multiselectPadreRemoto').rup_select({
		     parent: ['selectAbueloRemoto'],
		     url: 'comboEnlazadoSimple/remoteEnlazadoComarca',
		     sourceParam: {
		         text: 'desc' + $.rup_utils.capitalizedLang(),
		         id: 'code',
		         style: 'css'
		     },
		     placeholder: '[Seleccionar]',
		     selected: '7'	,
			 multiple:true		
		 });

		 $('#selectHijoRemoto').rup_select({
		     parent: 'multiselectPadreRemoto',
		     url: 'comboEnlazadoSimple/remoteEnlazadoLocalidadMultiple',
		     sourceParam: {
		         text: 'desc' + $.rup_utils.capitalizedLang(),
		         id: 'code',
		         style: 'css'
		     },
		     placeholder: '[Seleccionar]',
		     selected: '8'
		 });   
	});
	$('.contenedor').addClass('show');
});
