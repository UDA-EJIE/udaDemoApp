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
	
	
	// Evitar conflictos entre Bootstrap y jQueryUI
	$.fn.bootstrapBtn = $.fn.button.noConflict();
	
	jQuery("#rup_dept_logo").attr("src", jQuery.rup.APP_STATICS + "/images/dept_logo_" + jQuery.rup.lang + ".gif");
	var vertical = false, mixto = false;
	if (jQuery.rup.LAYOUT === "vertical") {
		vertical = true;
	} else if (jQuery.rup.LAYOUT === "mixto") {
		mixto = true;
	}
	
	// ThemeRoller
	jQuery('#themeroller').themeswitcher({
	     imgpath: jQuery.rup.STATICS + "/themeswitcher/images/"
	});
	jQuery("#borrar_themeroller").show().click(function(){
		$.rup_utils.setCookie("jquery-ui-theme", "", { path: '/' });
		window.location.reload();
	});
	
	//rastro de migas
	jQuery("#x21aPilotoPatronesWar_migas").rup_breadCrumb({
		logOutUrl: "/x21aAppWar/logout",
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
                "contextMenu" : { "i18nCaption" : "contextMenu" },
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
				    {"i18nCaption":"all", "url": "/x21aAppWar/patrones/all" },
				    {"i18nCaption":"accordion", "url": "/x21aAppWar/patrones/accordion" },
	                {"i18nCaption":"treeDAD", "url": "/x21aAppWar/patrones/treeDAD" },
//	                {"i18nCaption":"treePanel", "url": "/x21aAppWar/patrones/trees" },
	                {"i18nCaption":"trees", "url": "/x21aAppWar/patrones/trees" },
//	                {"i18nCaption":"treesFormats", "url": "/x21aAppWar/patrones/trees" },
				    {"i18nCaption":"autocomplete", "url": "/x21aAppWar/patrones/autocomplete" },
                    {"i18nCaption":"toolbar", "url": "/x21aAppWar/patrones/toolbar" },
                  	{"i18nCaption":"comboSimple", "url": "/x21aAppWar/patrones/comboSimple", "newWindow": true },
                  	{"i18nCaption":"comboEnlazadoSimple", "url": "/x21aAppWar/patrones/comboEnlazadoSimple" },
                  	{"i18nCaption":"comboEnlazadoMulti", "url": "/x21aAppWar/patrones/comboEnlazadoMultiple" },
                  	{"i18nCaption":"multicombo", "url": "/x21aAppWar/patrones/multicombo" },
                  	{"i18nCaption":"dialog", "url": "/x21aAppWar/patrones/dialog" },
                  	{"i18nCaption":"date", "url": "/x21aAppWar/patrones/date" },
                  	{"i18nCaption":"feedback", "url": "/x21aAppWar/patrones/feedback" },
                  	{"i18nCaption":"form", "url": "/x21aAppWar/patrones/form" },
                  	{"i18nCaption":"time", "url": "/x21aAppWar/patrones/time" },
                  	{"i18nCaption":"message", "url": "/x21aAppWar/patrones/message" },
                  	{"i18nCaption":"contextMenu", "url": "/x21aAppWar/patrones/contextMenu" },
                   	{"i18nCaption":"menu", "url": "/x21aAppWar/patrones/menu" },
                  	{"i18nCaption":"menuVertical", "url": "/x21aAppWar/patrones/menuVertical" },
                  	{"i18nCaption":"menuMixto", "url": "/x21aAppWar/patrones/menuMixto" },
                 	{"i18nCaption":"tabsStatic", "url": "/x21aAppWar/patrones/tabsStatic" },
                	{"i18nCaption":"tabsAjax", "url": "/x21aAppWar/patrones/tabsAjax" },
                	{"i18nCaption":"tabsMixto", "url": "/x21aAppWar/patrones/tabsMixto" },
                	{"i18nCaption":"maintTab", "url": "/x21aAppWar/patrones/maintTab" },
					{"i18nCaption":"grid", "url": "/x21aAppWar/patrones/grid" },
					{"i18nCaption":"tooltip", "url": "/x21aAppWar/patrones/tooltip" },
					{"i18nCaption":"upload", "url": "/x21aAppWar/patrones/upload" },
					{"i18nCaption":"validate", "url": "/x21aAppWar/patrones/validate" },
	                {"i18nCaption":"wizardA", "url": "/x21aAppWar/patrones/wizard" },
	                {"i18nCaption":"wizardB", "url": "/x21aAppWar/patrones/wizard_includeFile" },
	                {"i18nCaption":"wizardC", "url": "/x21aAppWar/patrones/wizard_jspInclude" },
	                {"i18nCaption":"wizardD", "url": "/x21aAppWar/patrones/wizard_jstlImport" },
	                {"i18nCaption":"wizardE", "url": "/x21aAppWar/patrones/wizard_dinamico" }
				]
			},
			"experimental" : {
				//Literal
				"i18nCaption" : "experimental"
				//Elementos (url)

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
					{"i18nCaption":"z-index", "url": "/x21aAppWar/integracion/z-index" },
					{"i18nCaption":"nora", "url": "/x21aAppWar/integracion/nora" },
					{"i18nCaption":"tiny", "url": "/x21aAppWar/integracion/tiny" }
				]
			},
			"uda" : {
				//Literal
				"i18nCaption" : "uda"
			}
		}
	});
	//idioma
	jQuery("#x21aApp_language").rup_language({languages: jQuery.rup.AVAILABLE_LANGS_ARRAY});
	
	//NAVBAR Menu
	$.fn.rup_navbar({
		sticky:false
	});
	
	jQuery.extend(true, jQuery.rup.i18n.base.rup_combo, { blankNotDefined : "----" });

	//pie
	$(".footer [title]").rup_tooltip();
	
	//Evitar CABECERA y PIE en PORTAL
	if (jQuery.rup_utils.aplicatioInPortal()){
		jQuery("header").remove();
		jQuery("footer").remove();
	}
});