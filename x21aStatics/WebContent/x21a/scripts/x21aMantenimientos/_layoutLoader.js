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
	
	// compatibilidad RUP v2.x
	$.fn.rup_button.defaults.adapter = "button_jqueryui";
	$.fn.rup_date.defaults.adapter = "date_jqueryui";
	$.fn.rup_table.plugins.core.defaults.adapter = "table_jqueryui";
	$.fn.rup_time.defaults.adapter = "time_jqueryui";
	$.fn.rup_toolbar.defaults.adapter = "toolbar_jqueryui";
	$.fn.rup_upload.defaults.adapter = "upload_jqueryui";
	$.fn.rup_validate.defaults.adapter = "validate_jqueryui";
	
	$("#rup_dept_logo").attr("src", $.rup.APP_STATICS + "/images/dept_logo_" + $.rup.lang + ".gif");
	var vertical = false, mixto = false;
	if ($.rup.LAYOUT === "vertical") {
		vertical = true;
	} else if ($.rup.LAYOUT === "mixto") {
		mixto = true;
	}

	//ThemeRoller
//	jQuery('#themeroller').themeswitcher({
//	     imgpath: jQuery.rup.STATICS + "/themeswitcher/images/"
//	});
//	jQuery("#borrar_themeroller").show().click(function(){
//		$.rup_utils.setCookie("jquery-ui-theme", "", { path: '/' });
//		window.location.reload();
//	});
	
	//rastro de migas
	$("#x21aMantenimientosWar_migas").rup_breadCrumb({
		breadCrumb: {
			"multi" : { "i18nCaption" : "multi" },
			"uda" : {"i18nCaption" : "uda"},
			"administracion" : {
				//Literal
				"i18nCaption" : "administracion",
				//Elementos (url)
				"alumno":{"i18nCaption":"alumno"},
				//Submenu
				"subLevel":[
					{"i18nCaption":"alumno", "url": "/x21aMantenimientosWar/administracion/alumno" }
				]
			}
			
		},
		logOutUrl: "/x21aMantenimientosWar/logout"
	});
	//idioma
	$("#x21aMantenimientosWar_language").rup_language({
		languages: $.rup.AVAILABLE_LANGS_ARRAY,
		modo: "portal"
	});
	
	
	$("#x21aMantenimientosWar_menu").rup_menu({
		display: (vertical ? 'vertical' : 'horizontal')
	});
	
	$.extend(true, jQuery.rup.i18n.base.rup_combo, { blankNotDefined : "----" });
	
	//pie
	$(".footer [title]").rup_tooltip();
	
	//Evitar CABECERA y PIE en PORTAL
	if (jQuery.rup_utils.aplicatioInPortal()){
		jQuery(".cabecera").remove();
		jQuery(".footer").remove();
	}
});