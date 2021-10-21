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

        $('#selectPadre').rup_select({
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
                }, {
                    id: 'g3',
                    text: 'g3_text'
                }]

            },
            selected: 'b1',
            placeholder: '[Seleccione]'
        });

        $('#selectHijo').rup_select({
            parent: ['selectPadre'],
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
            selected: '2',
            change: function () {
                console.log('selectAbueloRemoto:::Changed');
            }
        });

        $('#selectPadreRemoto').rup_select({
            parent: 'selectAbueloRemoto',
            url: 'comboEnlazadoSimple/remoteEnlazadoComarca',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            placeholder: '',
            selected: '7'
        });

        $('#selectHijoRemoto').rup_select({
            parent: 'selectPadreRemoto',
            url: 'comboEnlazadoSimple/remoteEnlazadoLocalidad',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            placeholder: '',
            selected: '8'
        });


        //MIXTO I
        /*$('#mixto_comboAbueloRemoto').rup_combo({
            source: 'comboEnlazadoSimple/remoteEnlazadoProvincia',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            selected: 2,
            blank: '0',
            width: '99%',
            customClasses: ['select-material']
        });

        $('#mixto_comboPadre').rup_combo({
            parent: ['mixto_comboAbueloRemoto'],
            source: {
                '1': [{
                    id: 'a1',
                    text: '1'
                }, {
                    id: 'a2',
                    text: '2'
                }, {
                    id: 'a3',
                    text: '3'
                }],
                '2': [{
                    id: 'b1',
                    text: '7'
                }, {
                    id: 'b2',
                    text: '8'
                }, {
                    id: 'b3',
                    text: '9'
                }],
                '3': [{
                    id: 'g1',
                    text: '4'
                }, {
                    id: 'g2',
                    text: '5'
                }, {
                    id: 'g3',
                    text: '6'
                }]
            },
            width: '99%',
            customClasses: ['select-material']
        });

        $('#mixto_comboHijoRemoto').rup_combo({
            parent: ['mixto_comboPadre'],
            source: 'comboEnlazadoSimple/remoteEnlazadoLocalidad',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            width: '99%',
            customClasses: ['select-material']
        });*/


        //MIXTO II
        /*$('#mixto2_comboAbuelo').rup_combo({
            source: [{
                id: 'a',
                text: '1'
            },
            {
                id: 'b',
                text: '2'
            },
            {
                id: 'g',
                text: '3'
            }
            ],
            selected: 2,
            blank: '0',
            width: '98%',
            customClasses: ['select-material']
        });

        $('#mixto2_comboPadreRemoto').rup_combo({
            parent: ['mixto2_comboAbuelo'],
            source: 'comboEnlazadoSimple/remoteEnlazadoComarca',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            width: '98%',
            customClasses: ['select-material']
        });

        $('#mixto2_comboHijo').rup_combo({
            parent: ['mixto2_comboPadreRemoto'],
            source: {
                '7': ['Bilbao', 'Basauri', 'Galdakao'],
                '8': ['Leioa', 'Las Arenas', 'Getxo'],
                '9': ['Sestao', 'Barakaldo', 'Portu']
            },
            width: '98%',
            customClasses: ['select-material']
        });*/

/*
        // Remote group
        $('#remoteGroup_comboPadre').rup_combo({
            source: 'comboEnlazadoSimple/remoteEnlazadoProvincia',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            selected: 2,
            blank: '0',
            width: '98%',
            customClasses: ['select-material']
        });

        $('#remoteGroup_comboHijo').rup_combo({
            sourceGroup: 'comboSimple/remoteGroupEnlazado',
            parent: ['remoteGroup_comboPadre'],
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            //width: '99%',
            customClasses: ['select-material'],
            multiselect: true
        });
        */
    });
    $('.contenedor').addClass('show');
});