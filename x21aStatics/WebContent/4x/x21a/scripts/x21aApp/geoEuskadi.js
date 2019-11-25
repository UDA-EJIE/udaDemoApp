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

                window.COREProxy.api.layers.addKMLLayer('Hoteles', 'https://www.geo.euskadi.eus/bisorea/v3/demos/ficheros_datos/hoteles.kml');
                window.COREProxy.api.layers.addGeoJSONLayer('Centro btt', 'https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/centros_btt_bicicleta_montana/opendata/centros-btt.json');
            }
        });
    }
});