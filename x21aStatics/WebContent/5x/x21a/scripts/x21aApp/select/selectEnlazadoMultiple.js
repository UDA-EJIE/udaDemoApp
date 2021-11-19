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
            selected:1,
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboDepartamento:::Changed');
            }
        });
        $('#provincia').rup_select({
            data: [
            	{id:'Alava',text:'Álava'},
            	{id:'Gipuzcoa',text:'Gipúzcoa'},
            	{id:'Vizcaya',text:'Vizcaya'}
            	],
            change: function () {
                console.log('comboProvincia:::Changed');
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
                console.log('comboDptoProv:::Changed');
            }
        });

        //REMOTE
        $('#departamentoRemote').rup_combo({
            source : 'comboEnlazadoMultiple/departamentoRemote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            blank: '-1',
            selected:1,
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboDepartamentosRemote:::Changed');
            }
        });
        $('#provinciaRemote').rup_combo({
            source : 'comboEnlazadoMultiple/provinciaRemote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboProvinciaRemote:::Changed');
            }
        });
        $('#dptoProvRemote').rup_combo({
            parent: [ 'departamentoRemote', 'provinciaRemote' ],
            source : 'comboEnlazadoMultiple/dptoProvRemote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboDptoProvRemote:::Changed');
            }
        });
        
        $('#dptoProvRemote').on('change', function(){
            console.log('change');
        });
        
        //MIXTO I
        $('#mixto_departamentoRemote').rup_combo({
            source : 'comboEnlazadoMultiple/departamentoRemote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            blank: '-1',
            selected:1,
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboMixtoDepartamentoRemote:::Changed');
            }
        });
        $('#mixto_provincia').rup_combo({
            //source: ["Álava","Vizcaya","Gipúzcoa"]
            source: [
                {i18nCaption: 'a', value:'1'},
                {i18nCaption: 'b', value:'2'},
                {i18nCaption: 'g', value:'3'}
            ],
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboMixtoProvincia:::Changed');
            }
        });
        $('#mixto_dptoProvRemote').rup_combo({
            parent: [ 'mixto_departamentoRemote', 'mixto_provincia' ],
            source : 'comboEnlazadoMultiple/dptoProvRemote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboMixtoDptoProvRemote:::Changed');
            }
        });
        
        
        //MIXTO II
        $('#mixto2_departamento').rup_combo({
            source: [
                {i18nCaption: 'ayto', value:'1'},
                {i18nCaption: 'dipu', value:'2'},
                {i18nCaption: 'poli', value:'3'},
                {i18nCaption: 'bomb', value:'4'}
            ],
            blank: '-1',
            selected:1,
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboMixto2Departamento:::Changed');
            }
        });
        $('#mixto2_provinciaRemote').rup_combo({
            source : 'comboEnlazadoMultiple/provinciaRemote',
            sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboMixto2ProvinciaRemote:::Changed');
            }
        });
        $('#mixto2_dptoProv').rup_combo({
            parent: [ 'mixto2_departamento', 'mixto2_provinciaRemote' ],
            source: {
                '1##1':['Ayuntamiento de Álava'],
                '1##2':['Ayuntamiento de Vizcaya'],
                '1##3':['Ayuntamiento de Gipúzcoa'],
                '2##1':['Diputación de Álava'],
                '2##2':['Diputación de Vizcaya'],
                '2##3':['Diputación de Gipúzcoa'],
                '3##1':['Policía de Álava'],
                '3##2':['Policía de Vizcaya'],
                '3##3':['Policía de Gipúzcoa'],
                '4##1':['Bomberos de Álava'],
                '4##2':['Bomberos de Vizcaya'],
                '4##3':['Bomberos de Gipúzcoa']
            },
            width: '99%',
            customClasses: ['select-material'],
            change: function () {
                console.log('comboMixto2DptoProv:::Changed');
            }
        });
    });
    $('.contenedor').addClass('show');
});