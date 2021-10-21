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
            allowClear: false,
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
            allowClear: false,
            change: function () {
                console.log('selectRemoto:::Changed');
            },
            clean: function (e) {
                console.log('selectRemoto:::clean');
            }
         });
        
        $('#selectLargoMulti').rup_select({
        	data: [{
                i18nCaption: 'jon_doe',
                id: 'jon'
            },
            {
                i18nCaption: 'jane_doe',
                id: 'jane'
            },
            {
                i18nCaption: 'joseph_doe',
                id: 'joseph'
            },
            {
                i18nCaption: 'mad_doe',
                id: 'mad'
            }
            ],
            selected: 'joseph',
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
                i18nCaption: 'jon_doe',
                id: 'jon'
            },
            {
                i18nCaption: 'jane_doe',
                id: 'jane'
            },
            {
                i18nCaption: 'joseph_doe',
                id: 'joseph'
            },
            {
                i18nCaption: 'mad_doe',
                id: 'mad'
            }
            ],
            placeholder: '[Seleccione un elemento]',
            allowClear: false,
            i18nId:'comboLargo'
        });  
        
        //GROUPS
        $('#selectGrupos').rup_select({
            dataGroups: [{
            	
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
            selected: 'ath_value',
            customClasses: ['select-material'],
            rowStriping: true,
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
            selected: '7',
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
            change: function () {
                console.log('comboImgs:::Changed');
            },
            width: '100%'
        });
        
       $('#SelectLoadFromSelect').rup_select({
            change: function (e) {
                console.log('SelectLoadFromSelect:::Changed');
            }
         });


    });
    $('.contenedor').addClass('show');
});