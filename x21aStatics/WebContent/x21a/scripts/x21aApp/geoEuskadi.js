/*!
 * Copyright 2012 E.J.I.E., S.A.
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
function confirm_t17i_form(O_O) {
  	if(O_O !== undefined && O_O.portal !== undefined){
    let options = {
            //Definimos el icono
            'icon': 'https://www.geo.euskadi.eus/bisorea/v3/img/markerXY.png',
            //Definimos la opacidad del icono
            'markersOpacity': 1,
            //Definimos el nivel de zoom
            'zoomToMarker': 7,
        };
    let coor = O_O.portal.dxEtrs89  + ", " +O_O.portal.dyEtrs89;
    window.COREProxy.api.interactions.addMarker(coor, options);
  	}
  	
}
jQuery(function ($) {
	
    loadApp();
    var options = {
        //Definimos el icono
        'icon': 'https://www.geo.euskadi.eus/bisorea/v3/img/markerXY.png',
        //Definimos la opacidad del icono
        'markersOpacity': 1,
        //Definimos el nivel de zoom
        'zoomToMarker': 7,
    };
    $('#marcador').rup_button({
        iconCss: 'mdi mdi-bookmark',
        click: function () {
            window.COREProxy.api.interactions.addMarker('555661, 4793971', options);
        }
    });

    $('#centrar').rup_button({
        iconCss: 'mdi mdi-map-marker-radius',

        click: function () {
            window.COREProxy.api.interactions.zoomToCoordinates(['555661', '4793971'], 10, true, 2500);
        }
    });
    
    $('#buscar').rup_button({
        iconCss: 'mdi mdi-map-marker-radius',

        click: function () {
        	fncBuscarDireccion();
        }
    });
    
    function fncBuscarDireccion() {

    	var tipo = $('#input_tipoNora').val();
    	var nora = {
    		t17_nora_i18n: $.rup.lang,
    		t17_nora_tipo: 3,
    		title: $.rup.i18nParse($.rup.i18n.app, "nora.seleccionarDireccion"),
    		url: "https://www1.geo.jakina.ejiedes.net" + "/sidl/sidljQuery/dynamic_modal_form3.html"
    	};

    	if (tipo == 0) {
    		$.extend(nora, {
    			t17_nora_portal_id: $("#input_portalId").val(),
    			t17_nora_escalera: $("#input_escalera").val(),
    			t17_nora_piso: $("#input_piso").val(),
    			t17_nora_mano: $("#input_mano").val(),
    			t17_nora_puerta: $("#input_puerta").val(),
    			t17_nora_aprox_postal: $("#input_aproxPostal").val()
    		});
    	} else if (tipo == 1 || tipo == 2) {
    		$.extend(nora, {
    			t17_nora_localidad_id: $("#input_localidadId").val(),
    			t17_nora_calle: $("#input_calle").val(),
    			t17_nora_portal_numero: $("#input_portal").val(),
    			t17_nora_portal_cp: $("#input_cp").val(),
    			t17_nora_escalera: $("#input_escalera").val(),
    			t17_nora_piso: $("#input_piso").val(),
    			t17_nora_mano: $("#input_mano").val(),
    			t17_nora_puerta: $("#input_puerta").val(),
    			t17_nora_aprox_postal: $("#input_aproxPostal").val()
    		});
    	} else if (tipo == 3) {
    		$.extend(nora, {
    			t17_nora_pais_id: $("#input_paisId").val(),
    			t17_nora_provincia: $("#input_desc_provincia").val(),
    			t17_nora_municipio: $("#input_desc_municipio").val(),
    			t17_nora_calle: $("#input_calle").val(),
    			t17_nora_portal_cp: $("#input_cp").val()
    		});
    	} else {
    		$.extend(nora, {
    			t17_nora_escalera: $("#input_escalera").val(),
    			t17_nora_piso: $("#input_piso").val(),
    			t17_nora_mano: $("#input_mano").val(),
    			t17_nora_puerta: $("#input_puerta").val(),
    			t17_nora_aprox_postal: $("#input_aproxPostal").val()
    		});
    	}
    	openNoraModalDynamicForm(nora);
    	$('[aria-labelledby$="formulario_Nora"] .ui-dialog-titlebar-help').hide();
    	$('[aria-labelledby$="formulario_Nora"] .ui-dialog-titlebar-maximize').hide();
    }
    
    function loadApp() {
        window.mapDiv = 'mi_mapa';
        new window.Parser_c({
            'useLoadingSplash': true
        });
        //Nos registramos al evento de que se ha cargado la aplicacion
        window.COREProxy.register({
            onAppLoaded: function () {
                //Todo lo que está dentro de esta función se va a ejecutar una vez que el visor ha sido cargado.
                //Por ejemplo: la carga de capas, cambiar las capas base, mostrar u ocultar plugins, dibujar puntos en el mapa, ...

                //Plugin de capas base
                window.COREProxy.api.plugins.enableOne(window.COREProxy.api.plugins.SELECTOR_CAPA_BASE);
                //Plugin de localización
                window.COREProxy.api.plugins.enableOne(window.COREProxy.api.plugins.MI_POSICION);
                //Plugin de selector de capas
                window.COREProxy.api.plugins.enableOne(window.COREProxy.api.plugins.SELECTOR_DE_CAPAS);
                //Plugin de pantalla completa
                window.COREProxy.api.plugins.enableOne(window.COREProxy.api.plugins.PANTALLA_COMPLETA);
                //Plugin de transparencia de capas
                window.COREProxy.api.plugins.enableOne(window.COREProxy.api.plugins.TRANSPARENCIA_DE_CAPAS);

               // window.COREProxy.api.layers.addKMLLayer('Hoteles', '/x21aAppWar/integracion/geoEuskadiLayer.kml');
                window.COREProxy.api.layers.addGeoJSONLayer('Centro btt', 'https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/centros_btt_bicicleta_montana/opendata/centros-btt.json');

                $('.contenedor').addClass('show');
            }
        });
    }
});