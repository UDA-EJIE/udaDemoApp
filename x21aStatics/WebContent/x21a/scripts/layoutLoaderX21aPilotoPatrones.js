jQuery(document).ready(function(){
	$("#rup_dept_logo").attr("src", $.rup.APP_STATICS + "/images/dept_logo_" + $.rup.lang + ".gif");
	var vertical = false, mixto = false;
	if ($.rup.LAYOUT === "vertical") {
		vertical = true;
	} else if ($.rup.LAYOUT === "mixto") {
		mixto = true;
	}
	
	//ThemeRoller
	$('#themeroller').click($.rup.themeRoller);
	
	//rastro de migas
	$("#x21aPilotoPatronesWar_migas").rup_breadCrumb({
		breadCrumb: {
			"patrones" : {
				//Literal
				"i18nCaption" : "patrones",
				//Elementos (url)
				"all" : { "i18nCaption" : "all" },
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
			},
			"experimental" : {
				//Literal
				"i18nCaption" : "experimental",
				//Elementos (url)
				"generic_object" : { "i18nCaption" : "generic_object" },
				"maestro_detalle" : { "i18nCaption" : "maestro_detalle" },
				//Submenu
				"subLevel":[
				 	{"i18nCaption":"generic_object", "url": "/x21aPilotoPatronesWar/experimental/generic_object" },
					{"i18nCaption":"maestro_detalle", "url": "/x21aPilotoPatronesWar/experimental/maestro_detalle" }
				]
			}
		}
	});
	//idioma
	$("#x21aPilotoPatronesWar_language").rup_language({languages: ["es", "eu", "en", "fr"]});
	
	$("#x21aPilotoPatronesWar_menu").rup_menu({
		display: (vertical ? 'vertical' : 'horizontal'),
		menu: [
		       	{"i18nCaption":"mantenimientos", "url": "../x21aMantenimientosWar/"},
				{"i18nCaption":"inicio", "url": "" },
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
				]},
				{"i18nCaption":"experimental", "submenu":[
				    {"i18nCaption":"generic_object", "url": "experimental/generic_object" },                             
				    {"i18nCaption":"maestro_detalle", "url": "experimental/maestro_detalle" }
				]}
		]
	});
	
	if (mixto) {
		$("#x21aPilotoPatronesWar_menu_mixto").rup_menu({
			display: 'vertical',
			menu: [
			       	{"i18nCaption":"mantenimientos", "url": "../x21aMantenimientosWar/"},
					{"i18nCaption":"inicio", "url": "" },
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
					]},
					{"i18nCaption":"experimental", "submenu":[
						{"i18nCaption":"generic_object", "url": "experimental/generic_object" },                             
						{"i18nCaption":"maestro_detalle", "url": "experimental/maestro_detalle" }                            
					]}
			]
		});
	}
});