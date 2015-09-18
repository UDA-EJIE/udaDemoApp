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
	$("#x21aMantenimientosWar_migas").rup_breadCrumb({
		breadCrumb: {
			"simple" : { "i18nCaption" : "simple" },
			"multi" : { "i18nCaption" : "multi" },
			"edlinea" : { "i18nCaption" : "edlinea" }
		}
	});
	//idioma
	$("#x21aMantenimientosWar_language").rup_language({languages: ["es", "eu", "en", "fr"]});
	
	$("#x21aMantenimientosWar_menu").rup_menu({
		display: (vertical ? 'vertical' : 'horizontal'),
		menu: [
		       	{"i18nCaption":"patrones", "url": "../x21aPilotoPatronesWar/"},
				{"i18nCaption":"inicio", "url": "" },
				{"i18nCaption":"simple", "url": "usuario/simple"},
				{"i18nCaption":"multi", "url": "usuario/multi"},
				{"i18nCaption":"edlinea", "url": "usuario/edlinea"}
		]
	});
	
	$.rup.i18n.base.rup_combo.blankNotDefined = "----";
});