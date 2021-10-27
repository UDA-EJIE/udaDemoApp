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
    
    window.initRupI18nPromise.then(function () {
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
    });
    $('.contenedor').addClass('show');
});