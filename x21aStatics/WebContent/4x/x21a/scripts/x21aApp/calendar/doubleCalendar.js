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
jQuery(function($){

	// funcion que ejecutarán los eventos.
	actions = function(n) {
		console.info('' + n + ' ha sido pulsado');
	}
	
	getDay = () => {
        var days_array = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var m = (new Date()).getMonth();
        var m_limit = days_array[m];
        var d = Math.floor(Math.random() * m_limit) + 1;
        return new Date(new Date().getFullYear(), m, d).getTime();
    };
    
    days1 = [
        getDay(), getDay(), getDay(), getDay()
    ];
    
    days2 = [
        getDay(), getDay(), getDay(), getDay()
    ];
	
	// Opciones calendario 1
	var options1 = {
        events_source : function() {
            return [
                    {
                        "id" : "51469",
                        "title" : "<span><strong>REVISION DE AUTORIZACION DE VERTIDO DE AGUAS RESIDUALES PROCEDENTES DEL BARRIO DE NAFARRATE, EN LA LOCALIDAD DE URRUNAGA, T.M. DE LEGUTIANO (ARABA) (Rev. 2010-S-0285)</strong><ul class='pl-3'><li>Nºexp.:&nbsp;RAV-A-2016-0054</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li><li>Requiere muestra&nbsp;</li></ul></span>",
                        "start" : days1[0],
                        "class" : " vertido muestreo",
                        "end" : days1[0]+1,
                        "url" : "javascript:actions(51469)"
                    },
                    {
                        "id" : "49164",
                        "title" : "<span><strong>AUTORIZACIÓN DE VERTIDO DE AGUAS RESIDUALES PROCEDENTES DE LA POBLACION DE AMARITA, T.M. DE VITORIA-GASTEIZ (ALAVA).</strong><ul class='pl-3'><li>Nºexp.:&nbsp;VDP-A-2012-0268</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li></ul></span>",
                        "start" : days1[1],
                        "class" : " vertido vigilancia",
                        "end" : days1[1]+1,
                        "url" : "javascript:actions(49164)"
                    },
                    {
                        "id" : "47488",
                        "title" : "<span><strong>REVISION DE AUTORIZACION DE VERTIDO DE AGUAS RESIDUALES PROCEDENTES DE LA POBLACION DE GAMARRA MENOR, T.M. DE VITORIA-GASTEIZ (ARABA) (Rev. 2013-S-0095)</strong><ul class='pl-3'><li>Nºexp.:&nbsp;RAV-A-2016-0055</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li></ul></span>",
                        "start" : days1[2],
                        "class" : " vertido vigilancia",
                        "end" : days1[2]+1,
                        "url" : "javascript:actions(47488)"
                    },
                    {
                        "id" : "49143",
                        "title" : "<span><strong>AUTORIZACION DE VERTIDO DE AGUAS RESIDUALES PROCEDENTES DE UNA GRANJA EXPERIMENTAL DE AVES EN LA LOCALIDAD DE CASTILLO, EN EL T.M. DE VITORIA-GASTEIZ (ALAVA).</strong><ul class='pl-3'><li>Nºexp.:&nbsp;VDP-A-2012-0379</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li></ul></span>",
                        "start" : days1[3],
                        "class" : " vertido vigilancia",
                        "end" : days1[3]+1,
                        "url" : "javascript:actions(49143)"
                    } ];
        },
        rupAfterViewLoad : function(view) {
            $('.page-header h3', $('.cal-1')).text(this.getTitle());
            $('.btn-group button', $('.cal-1')).removeClass('active');
            $('button[data-calendar-view="' + view + '"]', $('.cal-1')).addClass('active');
        }
	};
	var options2 = {
        events_source : function() {
            return [
			{
				"id" : "48605",
				"title" : "<span><strong>AUTORIZACIÓN DE VERTIDO DE AGUAS RESIDUALES PROCEDENTES DE ESTACIÓN DE SERVICIO Nº 7338 EN ARKAUTE, T.M .DE VITORIA-GASTEIZ</strong><ul class='pl-3'><li>Nºexp.:&nbsp;VDP-A-2012-0382</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li><li>Requiere muestra&nbsp;</li><li><i class='mdi mdi-check pr-1' aria-hidden='true'></i>COMPLETADA</li></ul></span>",
				"start" : days2[0],
				"class" : " vertido muestreo completada",
				"end" : days2[0]+1,
				"url" : "javascript:actions(48605)"
			},
			{
				"id" : "47278",
				"title" : "<span><strong>REVISION DE LA AUTORIZACION DE VERTIDO DE AGUAS RESIDUALES DE LA LOCALIDAD DE ILARRATZA (2 P. VERTIDO), T.M. DE VITORIA-GASTEIZ. (REV. 2009-S-0372)</strong><ul class='pl-3'><li>Nºexp.:&nbsp;RAV-A-2014-0058</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li></ul></span>",
				"start" : days2[1],
				"class" : " vertido vigilancia",
				"end" : days2[1]+1,
				"url" : "javascript:actions(47278)"
			},
			{
				"id" : "49203",
				"title" : "<span><strong>AUTORIZACION DE VERTIDO DE AGUAS RESIDUALES EN ESTACION DE SERVICIO (FECALES, HIDROCARBUROS Y LAVADO DE VEHICULOS EN OTXANDIO</strong><ul class='pl-3'><li>Nºexp.:&nbsp;VDP-A-2012-0164</li><li>P.vertido:&nbsp;PV1</li><li>Id.flujo:&nbsp;F1</li></ul></span>",
				"start" : days2[2],
				"class" : " vertido vigilancia",
				"end" : days2[2]+1,
				"url" : "javascript:actions(49203)"
			},
			{
				"id" : "47275",
				"title" : "<span><strong>REVISION DE LA AUTORIZACION DE VERTIDO DE AGUAS RESIDUALES DE LA LOCALIDAD DE ILARRATZA (2 P. VERTIDO), T.M. DE VITORIA-GASTEIZ. (REV. 2009-S-0372)</strong><ul class='pl-3'><li>Nºexp.:&nbsp;RAV-A-2014-0058</li><li>P.vertido:&nbsp;PV2</li><li>Id.flujo:&nbsp;F2</li></ul></span>",
				"start" : days2[3],
				"class" : " vertido vigilancia",
				"end" : days2[3]+1,
				"url" : "javascript:actions(47275)"
			}];
        },
        rupAfterViewLoad : function(view) {
            $('.page-header h3', $('.cal-2')).text(this.getTitle());
            $('.btn-group button', $('.cal-2')).removeClass('active');
            $('button[data-calendar-view="' + view + '"]', $('.cal-2')).addClass('active');
        }
	};

	$('#calendar1').rup_calendar(options1);
	$('#calendar2').rup_calendar(options2);

	// Se establece la funcionalidad de los botones de navegacion
	$('.btn-group span[data-calendar-nav]', $('.cal-1')).each(function(i, e) {
        var $elem = $(e);
        $elem.click(function() {
			$('#calendar1').rup_calendar('navigate', $elem.data('calendar-nav'));
        });
	});
	$('.btn-group span[data-calendar-nav]', $('.cal-2')).each(function(i, e) {
        var $elem = $(e);
        $elem.click(function() {
			$('#calendar2').rup_calendar('navigate', $elem.data('calendar-nav'));
        });
	});
	
	// Se establece la funcionalidad de los botones de vista
	$('.btn-group span[data-calendar-view]', $('.cal-1')).each(function(i, e) {
		var $elem = $(e);
		$elem.click(function() {
			$('#calendar1').rup_calendar('setView', $elem.data('calendar-view'));
			$('.btn-group span[data-calendar-view].active', $('.cal-1'))
					.removeClass('active');
			$elem.addClass('active');
		});
	});
	$('.btn-group span[data-calendar-view]', $('.cal-2')).each(function(i, e) {
		var $elem = $(e);
		$elem.click(function() {
			$('#calendar2').rup_calendar('setView', $elem.data('calendar-view'));
			$('.btn-group span[data-calendar-view].active', $('.cal-2'))
					.removeClass('active');
			$elem.addClass('active');
		});
	});

});