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
jQuery(function($){
	
	
	$("#idioma").rup_language();
	
	$("#menu-horizontal").rup_menu({
		display: 'horizontal',
		menu: [
		       	{"i18nCaption":"", "url": "" },
				{"i18nCaption":"patrones", "submenu":[
				    {"i18nCaption":"all", "url": "patrones/all" },  
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
                    {"i18nCaption":"tabs", "url": "patrones/tabs" },
					{"i18nCaption":"grid", "url": "patrones/grid" },
					{"i18nCaption":"tooltip", "url": "patrones/tooltip" }
				]}
		]
	});
	
	$("#breadCrumb").rup_breadCrumb({
		breadCrumb: {
			"patrones" : {
				//Literal
				"i18nCaption" : "patrones",
				//Submenu
				"subLevel":[
				    {"i18nCaption":"all", "url": "/x21aPilotoPatronesWar/patrones/all" },
				    {"i18nCaption":"autocomplete", "url": "/x21aPilotoPatronesWar/patrones/autocomplete" },
                    {"i18nCaption":"toolbar", "url": "/x21aPilotoPatronesWar/patrones/toolbar" },
                  	{"i18nCaption":"comboSimple", "url": "/x21aPilotoPatronesWar/patrones/comboSimple" },
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
                    {"i18nCaption":"tabs", "url": "/x21aPilotoPatronesWar/patrones/tabs" },
					{"i18nCaption":"grid", "url": "/x21aPilotoPatronesWar/patrones/grid" },
					{"i18nCaption":"tooltip", "url": "/x21aPilotoPatronesWar/patrones/tooltip" }
				]
			}
		}
	});
	
	$("#idioma_2").rup_language();
	
	$("#menu-vertical").rup_menu({
		display: 'vertical',
		menu: [
				{"i18nCaption":"patrones", "submenu":[
				    {"i18nCaption":"all", "url": "patrones/all" },  
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
                    {"i18nCaption":"tabs", "url": "patrones/tabs" },
					{"i18nCaption":"grid", "url": "patrones/grid" },
					{"i18nCaption":"tooltip", "url": "patrones/tooltip" }
				]}
		]
	});
	
	$("#breadCrumb_2").rup_breadCrumb({
		breadCrumb: {
			"patrones" : {
				//Literal
				"i18nCaption" : "patrones",
				//Submenu
				"subLevel":[
				    {"i18nCaption":"all", "url": "/x21aPilotoPatronesWar/patrones/all" },
				    {"i18nCaption":"autocomplete", "url": "/x21aPilotoPatronesWar/patrones/autocomplete" },
                    {"i18nCaption":"toolbar", "url": "/x21aPilotoPatronesWar/patrones/toolbar" },
                  	{"i18nCaption":"comboSimple", "url": "/x21aPilotoPatronesWar/patrones/comboSimple" },
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
                    {"i18nCaption":"tabs", "url": "/x21aPilotoPatronesWar/patrones/tabs" },
					{"i18nCaption":"grid", "url": "/x21aPilotoPatronesWar/patrones/grid" },
					{"i18nCaption":"tooltip", "url": "/x21aPilotoPatronesWar/patrones/tooltip" }
				]
			}
		}
	});
	//Elimira lo que no es el menú
	$("#breadCrumb_2 .rup-breadCrumbs_span").remove();
	$("#breadCrumb_2 li:nth-child(1)").remove();
	
	$("#date").rup_date({
		changeMonth : false,
		changeYear	: false,
		numberOfMonths : 1
	});
	
	$("#time").rup_time({});
	
	$('#combo').rup_combo({
		source : "../patrones/comboEnlazadoSimple/remoteEnlazadoProvincia",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		selected: 3
	});
	$("#autocomplete").rup_autocomplete({
		source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]
	});
	
	$("[title]").rup_tooltip(); 
});