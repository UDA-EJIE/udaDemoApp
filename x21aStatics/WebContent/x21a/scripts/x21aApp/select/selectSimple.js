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
$(function () {
    window.initRupI18nPromise.then(function () {
        $('#selectSimple').rup_select({
            data: [{
                id: 'asp',
                text: 'asp_value'
            },
            {
            	id: 'c',
            	text: 'c_value'
            },
            {
            	id: 'c++',
            	text: 'c++_value'
            },
            {
            	id: 'coldfusion',
            	text: 'coldfusion_value'
            },
            {
            	id: 'groovy',
            	text: 'groovy_value'
            },
            {
                id: 'haskell',
                text: 'haskell_value'
            },
            {
            	id: 'java',
            	text: 'java_value'
            },
            {
            	id: 'javascript',
            	text: 'javascript_value'
            },
            {
            	id: 'perl',
            	text: 'perl_value'
            },

            {
                id: 'python',
                text: 'python_value'
            },
            {
                id: 'ruby',
                text: 'ruby_value'
            },
            {
            	id: 'scala',
                text: 'scala_value'
            },
            {
            	id: 'php',
            	text: 'php_value'
            }
            ],
            placeholder: '[Seleccione un elemento]',
            allowClear: true,
            deleteOnDeselect: true,
            closeOnSelect : false,
            tags: true,
            selected:'ruby',
            multiple:false,
            change: function (e) {
                console.log('selectSimple:::Changed');
            }
         }); 
        
        $('#selectRemoto').rup_select({
        	url:'comboSimple/remote',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            placeholder: '[Seleccione un elemento]',
            allowClear: true,
            selected:"3",
            multiple:false,
            change: function () {
                console.log('selectRemoto:::Changed');
            },
            clean: function (e) {
                console.log('selectRemoto:::clean');
            }
         });
        
        $('#selectLargoMulti').rup_select({
        	data: [{
                text: 'jon_doe',
                id: 'jon'
            },
            {
                text: 'jane_doe',
                id: 'jane'
            },
            {
                text: 'joseph_doe',
                id: 'joseph'
            },
            {
                text: 'mad_doe',
                id: 'mad'
            }
            ],
            selected: ['jane','mad'],
            placeholder: '[Seleccione un elemento]',    
            width: '98%',
            multiple:true,
            format: 'default',
            change: function () {
                console.log('comboLargo:::Changed');
            },
            i18nId:'comboLargo'
        }); 
        
        $('#selectLargo').rup_select({
        	data: [{
                text: 'jon_doe',
                id: 'jon'
            },
            {
                text: 'jane_doe',
                id: 'jane'
            },
            {
                text: 'joseph_doe',
                id: 'joseph'
            },
            {
                text: 'mad_doe',
                id: 'mad'
            }
            ],
            placeholder: '[Seleccione un elemento]',
            allowClear: true,
            i18nId:'comboLargo'
        });  
        
        //GROUPS
        $('#selectGrupos').rup_select({
            dataGroups: [{

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
             placeholder: '[Seleccione un elemento]',
            allowClear: true,
            selected: 'ath_value',
            customClasses: ['select-material'],
            rowStriping: true,
            groups: true,
            change: function () {
                console.log('selectGrupos:::Changed');
            }
        });
        
        $('#selectGruposRemoto').rup_select({
            url: 'comboSimple/remoteGroup',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
             placeholder: '[Seleccione un elemento]',
            allowClear: true,
            selected: ['200'],//haro
            change: function () {
                console.log('selectGruposRemoto:::Changed');
            },
            groups: true
        });
        
        $('#selectImgs').rup_select({
        	data: [
        			{id:'Borrar',text:'Borrar',style:'delete',imgStyle:true},
        			{id:'Filtrar',text:'Filtrar',style:'filter',imgStyle:true},
        			{id:'Imprimir',text:'Imprimir',style:'printer',imgStyle:false}],
            selected: 'Filtrar',
             placeholder: '[Seleccione un elemento]',
            allowClear: true,
            change: function () {
                console.log('comboImgs:::Changed');
            },
            width: '100%'
        });
        
       $('#SelectLoadFromSelect').rup_select({
	
			placeholder: '[Seleccione un elemento]',
            allowClear: true,
            change: function (e) {
                console.log('SelectLoadFromSelect:::Changed');
            }
         }); 


    });
    $('.contenedor').addClass('show');
});