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
        $('#comboAbuelo').rup_select({
            source: [{
                id: 'a',
                text: '01'
            },
            {
                id: 'b',
                text: '02'
            },
            {
                id: 'g',
                text: '03'
            }
            ],
            selected: '02',
            width: '99%',
            customClasses: ['select-material'],
            blank: ''
        });

        $('#comboPadre').rup_select({
            parent: ['comboAbuelo'],
            source: {
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
            selected: 'b1_text',
            width: '99%',
            customClasses: ['select-material']
        });

        $('#comboHijo').rup_select({
            parent: ['comboPadre'],
            source: {
                'b1_text': [{
                    id: 'Bilbao',
                    text: 'b1_1_text'
                }, {
                    id: 'Basauri',
                    text: 'b1_2_text'
                }, {
                    id: 'Galdakao',
                    text: 'b1_3_text'
                }],
                'b2_text': [{
                    id: 'Leioa',
                    text: 'b2_1_text'
                }, {
                    id: 'Las Arenas',
                    text: 'b2_2_text'
                }, {
                    id: 'Getxo',
                    text: 'b2_3_text'
                }],
                'b3_text': [{
                    id: 'Sestao',
                    text: 'b3_1_text'
                }, {
                    id: 'Barakaldo',
                    text: 'b3_2_text'
                }, {
                    id: 'Portu',
                    text: 'b3_3_text'
                }]

            },
            selected: 'b1_2_text',
            width: '99%',
            customClasses: ['select-material']

        });


        //REMOTO
        $('#comboAbueloRemoto').rup_combo({
            source: 'comboEnlazadoSimple/remoteEnlazadoProvincia',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            blank: '',
            selected: '2',
            width: '99%',
            customClasses: ['select-material']
        });

        $('#comboPadreRemoto').rup_combo({
            parent: ['comboAbueloRemoto'],
            source: 'comboEnlazadoSimple/remoteEnlazadoComarca',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            blank: '',
            selected: '7',
            width: '99%',
            customClasses: ['select-material']
        });

        $('#comboHijoRemoto').rup_combo({
            parent: ['comboPadreRemoto'],
            source: 'comboEnlazadoSimple/remoteEnlazadoLocalidad',
            sourceParam: {
                label: 'desc' + $.rup_utils.capitalizedLang(),
                text: 'code',
                style: 'css'
            },
            blank: '',
            selected: '8',
            width: '99%',
            customClasses: ['select-material']
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
    });
    $('.contenedor').addClass('show');
});