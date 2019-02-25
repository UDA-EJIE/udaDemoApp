/*
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
jQuery(function(c){a();var b={icon:"http://www.geo.euskadi.eus/bisorea/v3/img/markerXY.png",markersOpacity:1,zoomToMarker:7,};c("#marcador").rup_button({iconCss:"fa fa-bookmark",click:function(){COREProxy.api.interactions.addMarker("555661, 4793971",b)}});c("#centrar").rup_button({iconCss:"fa  fa-search-plus",click:function(){COREProxy.api.interactions.zoomToCoordinates(["555661","4793971"],10,true,2500)}});function a(){mapDiv="mi_mapa";new Parser_c({useLoadingSplash:true});COREProxy.register({onAppLoaded:function(){COREProxy.api.plugins.enableOne(COREProxy.api.plugins.SELECTOR_CAPA_BASE);COREProxy.api.plugins.enableOne(COREProxy.api.plugins.MI_POSICION);COREProxy.api.plugins.enableOne(COREProxy.api.plugins.SELECTOR_DE_CAPAS);COREProxy.api.plugins.enableOne(COREProxy.api.plugins.PANTALLA_COMPLETA);COREProxy.api.plugins.enableOne(COREProxy.api.plugins.TRANSPARENCIA_DE_CAPAS);COREProxy.api.layers.addKMLLayer("Hoteles","http://www.geo.euskadi.eus/bisorea/v3/demos/ficheros_datos/hoteles.kml");COREProxy.api.layers.addGeoJSONLayer("Centro btt","http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/centros_btt_bicicleta_montana/opendata/centros-btt.json")}})}});