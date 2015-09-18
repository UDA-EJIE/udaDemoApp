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
jQuery(document).ready(function(){
	jQuery("#rup_dept_logo").attr("src", jQuery.rup.APP_STATICS + "/images/dept_logo_" + jQuery.rup.lang + ".gif");
	var vertical = false, mixto = false;
	if (jQuery.rup.LAYOUT === "vertical") {
		vertical = true;
	} else if (jQuery.rup.LAYOUT === "mixto") {
		mixto = true;
	}
	
	//ThemeRoller
	jQuery('#themeroller').click(jQuery.rup.themeRoller);
	
	//rastro de migas
	jQuery("#x21aPilotoPatronesWar_migas").rup_breadCrumb({
		breadCrumb: {
			"patrones" : {
				//Literal
				"i18nCaption" : "patrones",
				//Elementos (url)
				"all" : { "i18nCaption" : "all" },
				"accordion" : { "i18nCaption" : "accordion" },
				"autocomplete" : { "i18nCaption" : "autocomplete" }, 
				"toolbar" : { "i18nCaption" : "toolbar" },
				"comboSimple" : {"i18nCaption":"comboSimple" },
                "comboEnlazadoSimple" : { "i18nCaption":"comboEnlazadoSimple" },
                "comboEnlazadoMultiple" : { "i18nCaption":"comboEnlazadoMulti" },
				"dialog" : { "i18nCaption" : "dialog" },
				"date" : { "i18nCaption" : "date" },
				"feedback" : { "i18nCaption" : "feedback" },
				"time" : { "i18nCaption" : "time" },
				"message" : { "i18nCaption" : "message" },
				"menu" : {"i18nCaption":"menu"},	
                "menuVertical" : {"i18nCaption":"menuVertical"},
                "menuMixto" : {"i18nCaption":"menuMixto"},
				"tabs" : { "i18nCaption" : "tabs" },
				"grid" : { "i18nCaption" : "grid" },
				"tooltip" : { "i18nCaption" : "tooltip" },
				//Submenu
				"subLevel":[
				    {"i18nCaption":"all", "url": "/x21aPilotoPatronesWar/patrones/all" },
				    {"i18nCaption":"accordion", "url": "/x21aPilotoPatronesWar/patrones/accordion" },
				    {"i18nCaption":"autocomplete", "url": "/x21aPilotoPatronesWar/patrones/autocomplete" },
                    {"i18nCaption":"toolbar", "url": "/x21aPilotoPatronesWar/patrones/toolbar" },
                  	{"i18nCaption":"comboSimple", "url": "/x21aPilotoPatronesWar/patrones/comboSimple", "newWindow": true },
                  	{"i18nCaption":"comboEnlazadoSimple", "url": "/x21aPilotoPatronesWar/patrones/comboEnlazadoSimple" },
                  	{"i18nCaption":"comboEnlazadoMulti", "url": "/x21aPilotoPatronesWar/patrones/comboEnlazadoMultiple" },
                  	{"i18nCaption":"dialog", "url": "/x21aPilotoPatronesWar/patrones/dialog" },
                  	{"i18nCaption":"date", "url": "/x21aPilotoPatronesWar/patrones/date" },
                  	{"i18nCaption":"feedback", "url": "/x21aPilotoPatronesWar/patrones/feedback" },
                  	{"i18nCaption":"time", "url": "/x21aPilotoPatronesWar/patrones/time" },
                  	{"i18nCaption":"message", "url": "/x21aPilotoPatronesWar/patrones/message" },
                   	{"i18nCaption":"menu", "url": "/x21aPilotoPatronesWar/patrones/menu" },
                  	{"i18nCaption":"menuVertical", "url": "/x21aPilotoPatronesWar/patrones/menuVertical" },
                  	{"i18nCaption":"menuMixto", "url": "/x21aPilotoPatronesWar/patrones/menuMixto" },
                  	{"i18nCaption":"tabs", "submenu":[
                     	{"i18nCaption":"tabsStatic", "url": "patrones/tabsStatic" },
                    	{"i18nCaption":"tabsAjax", "url": "patrones/tabsAjax" },
                    	{"i18nCaption":"tabsMixto", "url": "patrones/tabsMixto" },
                    	{"i18nCaption":"maintTab", "url": "patrones/maintTab" }
                    ]},
                    {"i18nCaption":"grid", "url": "/x21aPilotoPatronesWar/patrones/grid" },
					{"i18nCaption":"tooltip", "url": "/x21aPilotoPatronesWar/patrones/tooltip" },
					{"i18nCaption":"upload", "url": "/x21aPilotoPatronesWar/patrones/upload" }
				]
			},
			"experimental" : {
				//Literal
				"i18nCaption" : "experimental",
				//Elementos (url)
				"generic_object" : { "i18nCaption" : "generic_object" },
				"maestro_detalle" : { "i18nCaption" : "maestro_detalle" },
				"nora" : {"i18nCaption" : "nora" },
				//Submenu
				"subLevel":[
				 	{"i18nCaption":"generic_object", "url": "/x21aPilotoPatronesWar/experimental/generic_object" },
					{"i18nCaption":"maestro_detalle", "url": "/x21aPilotoPatronesWar/experimental/maestro_detalle" },
					{"i18nCaption":"z-index", "url": "/x21aPilotoPatronesWar/experimental/z-index" },
					{"i18nCaption":"mant_multi_entidad", "url": "/x21aPilotoPatronesWar/experimental/mant_multi_entidad" },
					{"i18nCaption":"mant_clave_compuesta", "url": "/x21aPilotoPatronesWar/experimental/mant_clave_compuesta" },
					{"i18nCaption":"mant_clave_compuesta_multi", "url": "experimental/mant_clave_compuesta_multi" },
					{"i18nCaption":"mant_clave_compuesta_edlinea", "url": "experimental/mant_clave_compuesta_edlinea" }
				]
			},
			"uda" : {
				//Literal
				"i18nCaption" : "uda"
			}
		}
	});
	//idioma
	jQuery("#x21aPilotoPatronesWar_language").rup_language({languages: ["es", "eu", "en", "fr"]});
	
	jQuery("#x21aPilotoPatronesWar_menu").rup_menu({
		display: (vertical ? 'vertical' : 'horizontal'),
		menu: [
		       	{"i18nCaption":"mantenimientos", "url": "../x21aMantenimientosWar/"},
				{"i18nCaption":"inicio", "url": "", "newWindow": true },
				{"i18nCaption":"patrones", "submenu":[
				    {"i18nCaption":"all", "pathUrl": "/x21aPilotoPatronesWar/patrones/all", "newWindow": true },
				    {"i18nCaption":"accordion", "pathUrl": "/x21aPilotoPatronesWar/patrones/accordion" },
				    {"i18nCaption":"autocomplete", "url": "patrones/autocomplete" },
                    {"i18nCaption":"toolbar", "url": "patrones/toolbar" },
                    {"i18nCaption":"combo", "submenu":[
                      	{"i18nCaption":"comboSimple", "url": "patrones/comboSimple", "newWindow": true },
                      	{"i18nCaption":"comboEnlazadoSimple", "url": "patrones/comboEnlazadoSimple" },
                      	{"i18nCaption":"comboEnlazadoMulti", "url": "patrones/comboEnlazadoMultiple" }
                  	]},
                  	{"i18nCaption":"dialog", "url": "patrones/dialog" },
                  	{"i18nCaption":"date", "url": "patrones/date" },
                  	{"i18nCaption":"feedback", "url": "patrones/feedback" },
                  	{"i18nCaption":"time", "url": "patrones/time" },
                  	{"i18nCaption":"message", "url": "patrones/message" },
                    {"i18nCaption":"menu", "submenu":[
                       	{"i18nCaption":"menuHorizontal", "url": "patrones/menu" },
                      	{"i18nCaption":"menuVertical", "url": "patrones/menuVertical" },
                      	{"i18nCaption":"menuMixto", "url": "patrones/menuMixto" }
                    ]}, 
                    {"i18nCaption":"tabs", "submenu":[
                     	{"i18nCaption":"tabsStatic", "url": "patrones/tabsStatic" },
                    	{"i18nCaption":"tabsAjax", "url": "patrones/tabsAjax" },
                    	{"i18nCaption":"tabsMixto", "url": "patrones/tabsMixto" },
                    	{"i18nCaption":"maintTab", "url": "patrones/maintTab" }
                    ]},
					{"i18nCaption":"grid", "url": "patrones/grid" },
					{"i18nCaption":"tooltip", "url": "patrones/tooltip" },
					{"i18nCaption":"upload", "url": "patrones/upload" }
				]},
				{"i18nCaption":"experimental", "submenu":[
				    {"i18nCaption":"generic_object", "url": "experimental/generic_object" },                             
				    {"i18nCaption":"maestro_detalle", "url": "experimental/maestro_detalle" },
				    {"i18nCaption":"z-index", "url": "experimental/z-index" },
				    {"i18nCaption":"mant_multi_entidad", "url": "experimental/mant_multi_entidad" },
					{"i18nCaption":"mant_clave_compuesta", "submenu":[
                     	{"i18nCaption":"mant_clave_compuesta_multi", "url": "experimental/mant_clave_compuesta_multi" },
                      	{"i18nCaption":"mant_clave_compuesta_edlinea", "url": "experimental/mant_clave_compuesta_edlinea" }
                    ]},
    				{"i18nCaption":"nora", "url": "experimental/nora"}
				]},
				{"i18nCaption":"uda", "pathUrl": "http://code.google.com/p/uda/", "newWindow": true}
		]
	});
	
	if (mixto) {
		jQuery("#x21aPilotoPatronesWar_menu_mixto").rup_menu({
			display: 'vertical',
			menu: [
			       	{"i18nCaption":"mantenimientos", "url": "../x21aMantenimientosWar/"},
					{"i18nCaption":"inicio", "url": "" },
					{"i18nCaption":"patrones", "submenu":[
					    {"i18nCaption":"all", "url": "patrones/all" },                  
					    {"i18nCaption":"accordion", "pathUrl": "/x21aPilotoPatronesWar/patrones/accordion" },
					    {"i18nCaption":"autocomplete", "url": "patrones/autocomplete" },
	                    {"i18nCaption":"toolbar", "url": "patrones/toolbar" },
	                    {"i18nCaption":"combo", "submenu":[
	                      	{"i18nCaption":"comboSimple", "url": "patrones/comboSimple" },
	                      	{"i18nCaption":"comboEnlazadoSimple", "url": "patrones/comboEnlazadoSimple" },
	                      	{"i18nCaption":"comboEnlazadoMulti", "url": "patrones/comboEnlazadoMultiple" }
	                  	]},
	                  	{"i18nCaption":"dialog", "url": "patrones/dialog" },
	                  	{"i18nCaption":"date", "url": "patrones/date" },
	                  	{"i18nCaption":"feedback", "url": "patrones/feedback" },
	                  	{"i18nCaption":"time", "url": "patrones/time" },
	                  	{"i18nCaption":"message", "url": "patrones/message" },
	                    {"i18nCaption":"menu", "submenu":[
	                       	{"i18nCaption":"menuHorizontal", "url": "patrones/menu" },
	                      	{"i18nCaption":"menuVertical", "url": "patrones/menuVertical" },
	                    	{"i18nCaption":"menuMixto", "url": "patrones/menuMixto" }
	                    ]}, 
	                    {"i18nCaption":"tabs", "submenu":[
	                        {"i18nCaption":"tabsStatic", "url": "patrones/tabsStatic" },
							{"i18nCaption":"tabsAjax", "url": "patrones/tabsAjax" },
							{"i18nCaption":"tabsMixto", "url": "patrones/tabsMixto" },
	                    	{"i18nCaption":"maintTab", "url": "patrones/maintTab" }
						]},
						{"i18nCaption":"grid", "url": "patrones/grid" },
						{"i18nCaption":"tooltip", "url": "patrones/tooltip" },
						{"i18nCaption":"upload", "url": "patrones/upload" }
					]},
					{"i18nCaption":"experimental", "submenu":[
						{"i18nCaption":"generic_object", "url": "experimental/generic_object" },                             
						{"i18nCaption":"maestro_detalle", "url": "experimental/maestro_detalle" },
						{"i18nCaption":"z-index", "url": "experimental/z-index" },
						{"i18nCaption":"mant_multi_entidad", "url": "experimental/mant_multi_entidad" },
						{"i18nCaption":"mant_clave_compuesta", "submenu":[
                              {"i18nCaption":"mant_clave_compuesta_multi", "url": "experimental/mant_clave_compuesta_multi" },
                              {"i18nCaption":"mant_clave_compuesta_edlinea", "url": "experimental/mant_clave_compuesta_edlinea" }
                        ]}
					]}, 
					{"i18nCaption":"uda", "pathUrl": "http://code.google.com/p/uda/", "newWindow": true}
			]
		});
	};
	
	jQuery.rup.i18n.base.rup_combo.blankNotDefined = "----";
});