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
jQuery(function($) {

    
    window.initRupI18nPromise.then(function () {
        //LOCAL
       $('#departamento').rup_select({
            data: [
            	{id:'Ayuntamiento',text:'Ayuntamiento'},
            	{id:'Diputacion',text:'Diputación'},
            	{id:'Bomberos',text:'Bomberos'},
            	{id:'Policia',text:'Policía'}
            	],
            blank:'-1',
            selected:'Ayuntamiento',
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('selectDepartamento:::Changed');
            }
        });
        $('#provincia').rup_select({
            data: [
            	{id:'Alava',text:'Álava'},
            	{id:'Gipuzcoa',text:'Gipúzcoa'},
            	{id:'Vizcaya',text:'Vizcaya'}
            	],
            selected:'Vizcaya',
            change: function () {
                console.log('selectProvincia:::Changed');
            }
        });
        $('#dptoProv').rup_select({
            parent: [ 'departamento', 'provincia' ],
            data: [{
                'Ayuntamiento@@Alava': [{
                    id: 'Ayuntamiento de Álava1',
                    text: 'Ayuntamiento de Álava1'
                }, {
                    id: 'Ayuntamiento de Álava2',
                    text: 'Ayuntamiento de Álava2'
                }],
                'Ayuntamiento@@Vizcaya': [{id: 'Ayuntamiento de Vizcaya',text: 'Ayuntamiento de Vizcaya'}],
                'Ayuntamiento@@Gipuzcoa': [{id: 'Ayuntamiento de Gipúzcoa',text: 'Ayuntamiento de Gipúzcoa'}],
                'Diputacion@@Alava': [{id: 'Diputación de Álava',text: 'Diputación de Álava'}],
                'Diputacion@@Vizcaya': [{id: 'Diputación de Vizcaya',text: 'Diputación de Vizcaya'}],
                'Diputacion@@Gipuzcoa': [{id: 'Diputación de Gipúzcoa',text: 'Diputación de Gipúzcoa'}],
                'Bomberos@@Alava': [{id: 'Bomberos de Álava',text: 'Bomberos de Álava'}],
                'Bomberos@@Vizcaya': [{id: 'Bomberos de Vizcaya',text: 'Bomberos de Vizcaya'}],
                'Bomberos@@Gipuzcoa': [{id: 'Bomberos de Gipúzcoa',text: 'Bomberos de Gipúzcoa'}],
                'Policia@@Alava': [{id: 'Policía de Álava',text: 'Policía de Álava'}],
                'Policia@@Vizcaya': [{id: 'Policía de Vizcaya',text: 'Policía de Vizcaya'}],
                'Policia@@Gipuzcoa': [{id: 'Policía de Gipúzcoa',text: 'Policía de Gipúzcoa'}]
            }],
            multiValueToken: '@@',
            change: function () {
                console.log('selectDptoProv:::Changed');
            }
        });
         
        //REMOTE
        $('#departamentoRemote').rup_select({
        	url : 'comboEnlazadoMultiple/departamentoRemote',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            blank: '-1',
            selected:1,
            change: function () {
                console.log('comboDepartamentosRemote:::Changed');
            }
        });
        $('#provinciaRemote').rup_select({
        	url : 'comboEnlazadoMultiple/provinciaRemote',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            change: function () {
                console.log('comboProvinciaRemote:::Changed');
            }
        });
        $('#dptoProvRemote').rup_select({
            parent: [ 'departamentoRemote', 'provinciaRemote' ],
            url : 'comboEnlazadoMultiple/dptoProvRemote',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            change: function () {
                console.log('comboDptoProvRemote:::Changed');
            }
        });
        
        
        
        //MIXTO I
        $('#mixto_departamentoRemote').rup_select({
            url : 'comboEnlazadoMultiple/departamentoRemote',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            blank: '-1',
            selected:1,
            change: function () {
                console.log('comboMixtoDepartamentoRemote:::Changed');
            }
        });
        $('#mixto_provincia').rup_select({
            data: [
                {i18nCaption: 'a', id:'1'},
                {i18nCaption: 'b', id:'2'},
                {i18nCaption: 'g', id:'3'}
            ],
            change: function () {
                console.log('comboMixtoProvincia:::Changed');
            }
        });
        $('#mixto_dptoProvRemote').rup_select({
            parent: [ 'mixto_departamentoRemote', 'mixto_provincia' ],
            url : 'comboEnlazadoMultiple/dptoProvRemoteNoParam',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            change: function () {
                console.log('comboMixtoDptoProvRemote:::Changed');
            }
        });
        
        
        //MIXTO II
        $('#mixto2_departamento').rup_select({
            data: [
                {i18nCaption: 'ayto', id:'1'},
                {i18nCaption: 'dipu', id:'2'},
                {i18nCaption: 'poli', id:'3'},
                {i18nCaption: 'bomb', id:'4'}
            ],
            blank: '-1',
            selected:1,
            change: function () {
                console.log('comboMixto2Departamento:::Changed');
            }
        });
        $('#mixto2_provinciaRemote').rup_select({
            url : 'comboEnlazadoMultiple/provinciaRemote',
            sourceParam : {text:'desc'+$.rup_utils.capitalizedLang(), id:'code', style:'css'},
            selected:1,
            change: function () {
                console.log('comboMixto2ProvinciaRemote:::Changed');
            }
        });
        $('#mixto2_dptoProv').rup_select({
            parent: [ 'mixto2_departamento', 'mixto2_provinciaRemote' ],
            data: [{
                '1##1':[{id: 'Ayuntamiento de Álava',text: 'Ayuntamiento de Álava'}],
                '1##2':[{id:'Ayuntamiento de Vizcaya',text: 'Ayuntamiento de Vizcaya'}],
                '1##3':[{id:'Ayuntamiento de Gipúzcoa',text: 'Ayuntamiento de Gipúzcoa'}],
                '2##1':[{id:'Diputación de Álava',text: 'Diputación de Álava'}],
                '2##2':[{id:'Diputación de Vizcaya',text: 'Diputación de Vizcaya'}],
                '2##3':[{id:'Diputación de Gipúzcoa',text: 'Diputación de Gipúzcoa'}],
                '3##1':[{id:'Policía de Álava',text: 'Policía de Álava'}],
                '3##2':[{id:'Policía de Vizcaya',text: 'Policía de Vizcaya'}],
                '3##3':[{id:'Policía de Gipúzcoa',text: 'Policía de Gipúzcoa'}],
                '4##1':[{id:'Bomberos de Álava',text: 'Bomberos de Álava'}],
                '4##2':[{id:'Bomberos de Vizcaya',text: 'Bomberos de Vizcaya'}],
                '4##3':[{id:'Bomberos de Gipúzcoa',text: 'Bomberos de Gipúzcoa'}],
            }],
            multiValueToken: '##',
            change: function () {
                console.log('comboMixto2DptoProv:::Changed');
            }
        }); 
    });
    $('.contenedor').addClass('show');
});