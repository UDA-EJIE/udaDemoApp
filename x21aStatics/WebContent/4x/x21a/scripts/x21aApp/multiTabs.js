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

$(function() {
	
	$("#tabs").rup_tabs({
		tabs : [
			{i18nCaption:"pestana0", tabs: [
				{i18nCaption:"sub00", tabs: [
					{i18nCaption:"sub000", url:"tab2Fragment"},
					{i18nCaption:"sub001", tabs: [
						{i18nCaption:"sub0010", url:"tab3Fragment"},
						{i18nCaption:"sub0011", tabs: [
							{i18nCaption:"sub00110", url:"tab2Fragment"},
							{i18nCaption:"sub00111", url:"fragmento1"}
						]}
					]},
					{i18nCaption:"sub002", url:"fragmento1"}
				]},
				{i18nCaption:"sub01", url:"fragmento1"},
				{i18nCaption:"sub02", url:"fragmento1"}
			]},
			{i18nCaption:"pestana1", tabs: [
				{i18nCaption:"sub10", url:"tab2Fragment"},
				{i18nCaption:"sub11", url:"tab3Fragment"}
			]},
			{i18nCaption:"pestana2", url:"tab3Fragment"}
			
		]
		, disabled : {tabs: 2, pestana0: 1, pestana1: 1, sub001: 1}
	});
});