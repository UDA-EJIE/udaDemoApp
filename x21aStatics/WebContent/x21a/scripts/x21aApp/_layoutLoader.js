/*!
 * Copyright 2019 E.J.I.E., S.A.
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

<<<<<<< HEAD
jQuery(document).ready(function(){
	//rastro de migas
	$("#x21aAppWar_migas").rup_breadCrumb({
		breadCrumb: {
		    'areas' : {
		        //Literal
		        'i18nCaption' : 'Areas',
				},
			'comarca' : {
				    //Literal
				    'i18nCaption' : 'Comarca',
					}	
		},
		i18nId: "x21aApp_migas"
=======
            },
            'integracion' : {
                //Literal
                'i18nCaption' : 'integracion',
                //Elementos (url)
                'geoEuskadi' : {'i18nCaption' : 'geoEuskadi' },
                //"z-index" : {"i18nCaption" : "z-index" },
                //"nora" : {"i18nCaption" : "nora" },
                'tiny' : {'i18nCaption' : 'tiny' },
                'webdav' : {'i18nCaption' : 'webdav' },
                'cache' : {'i18nCaption' : 'cache' },
                //Submenu
                'subLevel':[
                    {'i18nCaption':'geoEuskadi', 'url': '/x21aAppWar/integracion/geoEuskadi' },
                    //{"i18nCaption":"z-index", "url": "/x21aAppWar/integracion/z-index" },
                    //{"i18nCaption":"nora", "url": "/x21aAppWar/integracion/nora" },
                    {'i18nCaption':'tiny', 'url': '/x21aAppWar/integracion/tiny' },
                    {'i18nCaption':'webdav', 'url': '/x21aAppWar/integracion/webdav' },
                    {'i18nCaption':'cache', 'url': '/x21aAppWar/integracion/cache/view' }
                ]
            },
            'uda' : {
                //Literal
                'i18nCaption' : 'uda'
            }
        }
    });
    //idioma
    jQuery('#x21aApp_language').rup_language({languages: jQuery.rup.AVAILABLE_LANGS_ARRAY});
    
    //NAVBAR Menu
    if($('#navbarResponsive').length > 0){
        $('#navbarResponsive').rup_navbar({
            sticky:false
        }).find('a:not([href^=#])').on('click', function(){
            $('.contenedor').removeClass('show');
        });
    }

    window.initRupI18nPromise.then(function() {
        jQuery.extend(true, jQuery.rup.i18n.base.rup_select, { blankNotDefined : '----' });
    });

    //pie
    jQuery('.footer [title]').rup_tooltip();
    
    //Evitar CABECERA y PIE en PORTAL
    if (jQuery.rup_utils.aplicatioInPortal()){
        jQuery("header[data-belongs-to-portal='false']").remove();
        jQuery("footer[data-belongs-to-portal='false']").remove();
    }
    
	$(document).on("ajaxError", function(event, jqXHR, ajaxSettings, thrownError) {
		if (jqXHR.status === 401 || jqXHR.status === 403) {
			window.location.href = jqXHR.getResponseHeader("LOCATION");
		}
>>>>>>> d44df81a2a491d15840ffa069698ac4925455141
	});
	
	//idioma
	$("#x21aAppWar_language").rup_language({languages: $.rup.AVAILABLE_LANGS_ARRAY, modo: "default"});
	
	//NAVBAR Menu
	if($("#navbarResponsive").length > 0){
		$("#navbarResponsive").rup_navbar({
			sticky:false
		});
	}
	
	window.initRupI18nPromise.then(function() {
		jQuery.extend(true, jQuery.rup.i18n.base.rup_select, { blankNotDefined : "----" });
	});
	
	//pie
	$(".footer [title]").rup_tooltip();
	
	//Evitar CABECERA y PIE en PORTAL
	if (jQuery.rup_utils.aplicatioInPortal()){
		jQuery("header").remove();
		jQuery("footer").remove();
	}
});