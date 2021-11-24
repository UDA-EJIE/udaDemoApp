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
          //  selected: '2',
            change: function () {
                console.log('selectAbueloRemoto:::Changed');
            }
        });

        $('#selectPadreRemoto').rup_select({
            parent: ['selectAbueloRemoto'],
            url: 'comboEnlazadoSimple/remoteEnlazadoComarca',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            placeholder: '[Seleccionar]',
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
            placeholder: '[Seleccionar]',
            selected: '8'
        }); 

        // Remote group
        $('#remoteGroup_selectPadre').rup_select({
            url: 'comboEnlazadoSimple/remoteEnlazadoProvincia',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css',
                imgStyle: 'css'
            },
            selected: 2,
            blank: '0'
        });

        $('#remoteGroup_selectHijo').rup_select({
            url: 'comboSimple/remoteGroupEnlazado',
            parent: 'remoteGroup_selectPadre',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            groups: true
        }); 
    	
        //Local -> Remote
    	  $('#selectLocalAbuelo').rup_select({
	        data: [{
	        	text: 'Alava',
	            id: '1'
	        },
	        {
	        	text: 'Bizkaia',
	            id: '2'
	        },
	        {
	        	text: 'Gipuzcoa',
	            id: '3'
	        },
	        {
	        	text: 'Burgos',
	            id: '4'
	        },
	        {
	        	text: 'Cantabria',
	            id: '5'
	        },
	        {
	        	text: 'La rioja',
	            id: '6'
	        }
	        ],
	        selected: '2',
	        placeholder: ''
    	});
    	
        $('#selectRemotoPadre').rup_select({
            parent: 'selectLocalAbuelo',
            url: 'comboEnlazadoSimple/remoteEnlazadoComarca',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            placeholder: '',
            selected: 7,
            cache: true
        });
        
      //Remote -> Local
        
        $('#selectRemotoAbuelo').rup_select({
            url: 'comboEnlazadoSimple/remoteEnlazadoProvincia',
            sourceParam: {
                text: 'desc' + $.rup_utils.capitalizedLang(),
                id: 'code',
                style: 'css'
            },
            placeholder: '[Seleccionar]',
            selected: '2',
            change: function () {
                console.log('selectRemotoAbuelo:::Changed');
            }
        });
        
     	  $('#selectLocalPadre').rup_select({
     		parent: 'selectRemotoAbuelo',
  		  data: {
              '1': [{
                  id: 'a1',
                  text: 'Alavesa'
              }, {
                  id: 'a2',
                  text: 'Oyonesa'
              }, {
                  id: 'a3',
                  text: 'Gamarresa'
              }],
              '2': [{
                  id: 'b1',
                  text: 'Pequeño Bilbao'
              }, {
                  id: 'b2',
                  text: 'Las Playas'
              }, {
                  id: 'b3',
                  text: 'Gran Bilbao'
              }],
              '3': [{
                  id: 'g1',
                  text: 'Komarca 1'
              }, {
                  id: 'g2',
                  text: 'Komarca 2'
              }, {
                  id: 'g3',
                  text: 'Komarca 3'
              }],
              '4': [{
                  id: 'g1',
                  text: 'Burgos'
              }, {
                  id: 'g2',
                  text: 'Aranda de Duero'
              }, {
                  id: 'g3',
                  text: 'Miranda de Ebro'
              }],
              '5': [{
                  id: 'g1',
                  text: 'Noja'
              }, {
                  id: 'g2',
                  text: 'Sanatander'
              }, {
                  id: 'g3',
                  text: 'Ajo'
              }],
              '6': [{
                  id: 'g1',
                  text: 'Portal de Haro'
              }, {
                  id: 'g2',
                  text: 'La calzado'
              }, {
                  id: 'g3',
                  text: 'Casalareina'
              }]

  		  		},
  	        selected: 'b2',
  	        placeholder: ''
      	});
        
    });
    $('.contenedor').addClass('show');
});