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
	jQuery('#themeroller').themeswitcher({
	     imgpath: jQuery.rup.STATICS + "/themeswitcher/images/"
	});
	jQuery("#borrar_themeroller").show().click(function(){
		$.rup_utils.setCookie("jquery-ui-theme", "", { path: '/' });
		window.location.reload();
	});
	
	//rastro de migas
	jQuery("#x21aPilotoPatronesWar_migas").rup_breadCrumb({
		logOutUrl: "/x21aPilotoPatronesWar/logout",
		breadCrumb: {
			"patrones" : {
				//Literal
				"i18nCaption" : "patrones",
				//Elementos (url)
				"all" : { "i18nCaption" : "all" },
				"accordion" : { "i18nCaption" : "accordion" },
				"treeDAD" : {"i18nCaption":"treeDAD" },
//				"treePanel" : {"i18nCaption":"treePanel" },
				"trees" : {"i18nCaption":"trees" },
//				"treesFormats" : {"i18nCaption":"treesFormats" },
				"autocomplete" : { "i18nCaption" : "autocomplete" }, 
				"toolbar" : { "i18nCaption" : "toolbar" },
				"comboSimple" : {"i18nCaption":"comboSimple" },
                "comboEnlazadoSimple" : { "i18nCaption":"comboEnlazadoSimple" },
                "comboEnlazadoMultiple" : { "i18nCaption":"comboEnlazadoMulti" },
                "multicombo" : { "i18nCaption":"multicombo" },
				"dialog" : { "i18nCaption" : "dialog" },
				"date" : { "i18nCaption" : "date" },
				"feedback" : { "i18nCaption" : "feedback" },
				"form" : { "i18nCaption" : "form" },
				"time" : { "i18nCaption" : "time" },
				"message" : { "i18nCaption" : "message" },
				"menu" : {"i18nCaption":"menu"},	
                "menuVertical" : {"i18nCaption":"menuVertical"},
                "menuMixto" : {"i18nCaption":"menuMixto"},
                "tabsStatic" : {"i18nCaption":"tabsStatic"},
            	"tabsAjax" : {"i18nCaption":"tabsAjax"},
            	"tabsMixto" : {"i18nCaption":"tabsMixto"},
            	"maintTab" : {"i18nCaption":"maintTab"},
				"grid" : { "i18nCaption" : "grid" },
				"tooltip" : { "i18nCaption" : "tooltip" },
				"upload" : { "i18nCaption" : "upload" },
				"validate" : { "i18nCaption" : "validate" },
				"wizard" : {"i18nCaption":"wizardA" },
				"wizard_includeFile" : {"i18nCaption":"wizardB" },
				"wizard_jspInclude" : {"i18nCaption":"wizardC" },
				"wizard_jstlImport" : {"i18nCaption":"wizardD" },
				"wizard_dinamico" : {"i18nCaption":"wizardE" },
				//Submenu
				"subLevel":[
				    {"i18nCaption":"all", "url": "/x21aPilotoPatronesWar/patrones/all" },
				    {"i18nCaption":"accordion", "url": "/x21aPilotoPatronesWar/patrones/accordion" },
	                {"i18nCaption":"treeDAD", "url": "/x21aPilotoPatronesWar/patrones/treeDAD" },
//	                {"i18nCaption":"treePanel", "url": "/x21aPilotoPatronesWar/patrones/trees" },
	                {"i18nCaption":"trees", "url": "/x21aPilotoPatronesWar/patrones/trees" },
//	                {"i18nCaption":"treesFormats", "url": "/x21aPilotoPatronesWar/patrones/trees" },
				    {"i18nCaption":"autocomplete", "url": "/x21aPilotoPatronesWar/patrones/autocomplete" },
                    {"i18nCaption":"toolbar", "url": "/x21aPilotoPatronesWar/patrones/toolbar" },
                  	{"i18nCaption":"comboSimple", "url": "/x21aPilotoPatronesWar/patrones/comboSimple", "newWindow": true },
                  	{"i18nCaption":"comboEnlazadoSimple", "url": "/x21aPilotoPatronesWar/patrones/comboEnlazadoSimple" },
                  	{"i18nCaption":"comboEnlazadoMulti", "url": "/x21aPilotoPatronesWar/patrones/comboEnlazadoMultiple" },
                  	{"i18nCaption":"multicombo", "url": "/x21aPilotoPatronesWar/patrones/multicombo" },
                  	{"i18nCaption":"dialog", "url": "/x21aPilotoPatronesWar/patrones/dialog" },
                  	{"i18nCaption":"date", "url": "/x21aPilotoPatronesWar/patrones/date" },
                  	{"i18nCaption":"feedback", "url": "/x21aPilotoPatronesWar/patrones/feedback" },
                  	{"i18nCaption":"form", "url": "/x21aPilotoPatronesWar/patrones/form" },
                  	{"i18nCaption":"time", "url": "/x21aPilotoPatronesWar/patrones/time" },
                  	{"i18nCaption":"message", "url": "/x21aPilotoPatronesWar/patrones/message" },
                   	{"i18nCaption":"menu", "url": "/x21aPilotoPatronesWar/patrones/menu" },
                  	{"i18nCaption":"menuVertical", "url": "/x21aPilotoPatronesWar/patrones/menuVertical" },
                  	{"i18nCaption":"menuMixto", "url": "/x21aPilotoPatronesWar/patrones/menuMixto" },
                 	{"i18nCaption":"tabsStatic", "url": "/x21aPilotoPatronesWar/patrones/tabsStatic" },
                	{"i18nCaption":"tabsAjax", "url": "/x21aPilotoPatronesWar/patrones/tabsAjax" },
                	{"i18nCaption":"tabsMixto", "url": "/x21aPilotoPatronesWar/patrones/tabsMixto" },
                	{"i18nCaption":"maintTab", "url": "/x21aPilotoPatronesWar/patrones/maintTab" },
					{"i18nCaption":"grid", "url": "/x21aPilotoPatronesWar/patrones/grid" },
					{"i18nCaption":"tooltip", "url": "/x21aPilotoPatronesWar/patrones/tooltip" },
					{"i18nCaption":"upload", "url": "/x21aPilotoPatronesWar/patrones/upload" },
					{"i18nCaption":"validate", "url": "/x21aPilotoPatronesWar/patrones/validate" },
	                {"i18nCaption":"wizardA", "url": "/x21aPilotoPatronesWar/patrones/wizard" },
	                {"i18nCaption":"wizardB", "url": "/x21aPilotoPatronesWar/patrones/wizard_includeFile" },
	                {"i18nCaption":"wizardC", "url": "/x21aPilotoPatronesWar/patrones/wizard_jspInclude" },
	                {"i18nCaption":"wizardD", "url": "/x21aPilotoPatronesWar/patrones/wizard_jstlImport" },
	                {"i18nCaption":"wizardE", "url": "/x21aPilotoPatronesWar/patrones/wizard_dinamico" }
				]
			},
			"experimental" : {
				//Literal
				"i18nCaption" : "experimental",
				//Elementos (url)
				"maestro_detalle" : { "i18nCaption" : "maestro_detalle" },
				"mant_multi_entidad": { "i18nCaption" : "mant_multi_entidad" },
				"mant_clave_compuesta_multi" : { "i18nCaption" : "mant_clave_compuesta_multi" },
				"mant_clave_compuesta_edlinea" : { "i18nCaption" : "mant_clave_compuesta_edlinea" },
				//Submenu
				"subLevel":[
					{"i18nCaption":"maestro_detalle", "url": "/x21aPilotoPatronesWar/experimental/maestro_detalle" },
					{"i18nCaption":"mant_multi_entidad", "url": "/x21aPilotoPatronesWar/experimental/mant_multi_entidad" },
					{"i18nCaption":"mant_clave_compuesta_multi", "url": "/x21aPilotoPatronesWar/experimental/mant_clave_compuesta_multi" },
					{"i18nCaption":"mant_clave_compuesta_edlinea", "url": "/x21aPilotoPatronesWar/experimental/mant_clave_compuesta_edlinea" }
				]
			},
			"integracion" : {
				//Literal
				"i18nCaption" : "integracion",
				//Elementos (url)
				"z-index" : {"i18nCaption" : "z-index" },
				"nora" : {"i18nCaption" : "nora" },
				"tiny" : {"i18nCaption" : "tiny" },
				//Submenu
				"subLevel":[
					{"i18nCaption":"z-index", "url": "/x21aPilotoPatronesWar/integracion/z-index" },
					{"i18nCaption":"nora", "url": "/x21aPilotoPatronesWar/integracion/nora" },
					{"i18nCaption":"tiny", "url": "/x21aPilotoPatronesWar/integracion/tiny" }
				]
			},
			"uda" : {
				//Literal
				"i18nCaption" : "uda"
			}
		}
	});
	//idioma
	jQuery("#x21aPilotoPatronesWar_language").rup_language({languages: jQuery.rup.AVAILABLE_LANGS_ARRAY});
	
	jQuery("#x21aPilotoPatronesWar_menu").rup_menu({
		display: (vertical ? 'vertical' : 'horizontal'),
		verticalWidth: "16.5em",
		position : {"within" : $(".contenedor")}
	});
	
	if (mixto) {
		jQuery("#x21aPilotoPatronesWar_menu_mixto").rup_menu({
			display: 'vertical',
			menu: [
					{"i18nCaption":"mantenimientos", "url": "../x21aMantenimientosWar/"},
					{"i18nCaption":"inicio", "url": "", "newWindow": true, "disabled": true},
					{"i18nCaption":"patrones", "submenu":[
					    {"i18nCaption":"all", "pathUrl": "/x21aPilotoPatronesWar/patrones/all", "newWindow": true },
					    {"divider":true},
					    {"divider":true, "i18nCaption":"titulo-notifi"},
					    {"i18nCaption":"feedback", "url": "patrones/feedback" },
					    {"i18nCaption":"tooltip", "url": "patrones/tooltip" },
					    {"i18nCaption":"message", "url": "patrones/message" },
					    {"i18nCaption":"dialog", "url": "patrones/dialog" },
					  	{"divider":true},
					  	{"divider":true, "i18nCaption":"titulo-nave"},
					  	{"i18nCaption":"menu", "submenu":[
					  	    {"i18nCaption":"menuHorizontal", "url": "patrones/menu" },
					        {"i18nCaption":"menuVertical", "url": "patrones/menuVertical" },
					        {"i18nCaption":"menuMixto", "url": "patrones/menuMixto" }
					    ]}, 
					    {"i18nCaption":"toolbar", "url": "patrones/toolbar" },
					  	{"divider":true},
					  	{"divider":true, "i18nCaption":"titulo-estru"},
					    {"i18nCaption":"accordion", "pathUrl": "/x21aPilotoPatronesWar/patrones/accordion" },
					    {"i18nCaption":"tabs", "submenu":[
					     	{"i18nCaption":"tabsStatic", "url": "patrones/tabsStatic" },
					    	{"i18nCaption":"tabsAjax", "url": "patrones/tabsAjax" },
					    	{"i18nCaption":"tabsMixto", "url": "patrones/tabsMixto" },
					    	{"i18nCaption":"maintTab", "url": "patrones/maintTab" }
					    ]},
						{"i18nCaption":"grid", "submenu":[
							    {"i18nCaption":"grid_grupo", "url": "patrones/gridGroup" },
								{"i18nCaption":"grid_base", "url": "patrones/grid" },
								{"i18nCaption":"grid_arbol", "url": "patrones/gridTree" }
							]},
							{"i18nCaption":"wizard", "submenu":[
							 	{"i18nCaption":"wizardA", "url": "patrones/wizard" },
							 	{"i18nCaption":"wizardB", "url": "patrones/wizard_includeFile" },
							 	{"i18nCaption":"wizardC", "url": "patrones/wizard_jspInclude" },
							 	{"i18nCaption":"wizardD", "url": "patrones/wizard_jstlImport" },
							 	{"i18nCaption":"wizardE", "url": "patrones/wizard_dinamico" }
							]},
							{"i18nCaption":"tree", "submenu":[
							 	{"i18nCaption":"trees", "url": "patrones/trees" },
//							 	{"i18nCaption":"trees_formats", "url": "patrones/trees" },
							 	{"i18nCaption":"treeDAD", "url": "patrones/treeDAD" },
//							 	{"i18nCaption":"tree_panel", "url": "patrones/trees" },
							]},
							{"divider":true},
							{"divider":true, "i18nCaption":"titulo-inser"},
							{"i18nCaption":"autocomplete", "url": "patrones/autocomplete" },
					    {"i18nCaption":"combo", "submenu":[
					      	{"i18nCaption":"comboSimple", "url": "patrones/comboSimple", "newWindow": true },
					      	{"i18nCaption":"comboEnlazadoSimple", "url": "patrones/comboEnlazadoSimple" },
					      	{"i18nCaption":"comboEnlazadoMulti", "url": "patrones/comboEnlazadoMultiple" }
					      	,{"i18nCaption":"multicombo", "url": "patrones/multicombo" }
					  	]},
							{"i18nCaption":"date", "url": "patrones/date" },
							{"i18nCaption":"time", "url": "patrones/time" },
						{"i18nCaption":"upload", "url": "patrones/upload" },
						{"i18nCaption":"validate", "url": "patrones/validate" }
					]},
					{"i18nCaption":"experimental", "submenu":[
					    {"i18nCaption":"maestro_detalle", "url": "experimental/maestro_detalle" },
					    {"i18nCaption":"mant_multi_entidad", "url": "experimental/mant_multi_entidad" },
						{"i18nCaption":"mant_clave_compuesta", "submenu":[
					     	{"i18nCaption":"mant_clave_compuesta_multi", "url": "experimental/mant_clave_compuesta_multi" },
					      	{"i18nCaption":"mant_clave_compuesta_edlinea", "url": "experimental/mant_clave_compuesta_edlinea" }
					    ]},
					]},
					{"i18nCaption":"integracion", "submenu":[
					    {"i18nCaption":"z-index", "url": "integracion/z-index" },
						{"i18nCaption":"nora", "url": "integracion/nora"},
						{"i18nCaption":"tiny", "url": "integracion/tiny"}
					]},
					{"i18nCaption":"uda", "pathUrl": "http://code.google.com/p/uda/", "newWindow": true}
			],
			verticalWidth: "16.5em"
				
		});
	};
	
	jQuery.extend(true, jQuery.rup.i18n.base.rup_combo, { blankNotDefined : "----" });

	//pie
	$(".footer [title]").rup_tooltip();
	
	//Evitar CABECERA y PIE en PORTAL
	if (jQuery.rup_utils.aplicatioInPortal()){
		jQuery(".cabecera").remove();
		jQuery(".footer").remove();
	}
});