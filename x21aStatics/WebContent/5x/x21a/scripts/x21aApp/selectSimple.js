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
            	id: 'php',
            	text: 'php_value'
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
            }
            ],
            placeholder: '[Seleccione un elemento]',
            allowClear: false
         });
        
        $('#selectRemoto').rup_select({
        	url:'comboSimple/remote',
            placeholder: '[Seleccione un elemento]',
            allowClear: true
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
            selected: 'joseph',
            width: '98%',
            multiple:false,
            format: 'default',
            change: function () {
                console.log('comboLargo:::Changed');
            },
            i18nId:'comboLargo'
        });


    });
    $('.contenedor').addClass('show');
});