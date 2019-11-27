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

    //Feedback
    $('#x21aAppWar_feedback').rup_feedback({block:false});
    
    //LOCAL
    $('#departamento').rup_combo({
        source: ['Ayuntamiento','Diputación','Policía','Bomberos'],
        blank:'-1',
        selected:1,
        width: '99%',
        customClasses: ['select-material'],
        change: function () {
            console.log('comboDepartamento:::Changed');
        }
    });
    $('#provincia').rup_combo({
        source: ['Álava','Vizcaya','Gipúzcoa'],
        width: '99%',
        customClasses: ['select-material'],
        change: function () {
            console.log('comboProvincia:::Changed');
        }
    });
    $('#dptoProv').rup_combo({
        parent: [ 'departamento', 'provincia' ],
        source: {
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
        },
        multiValueToken: '@@',
        width: '99%',
        customClasses: ['select-material'],
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