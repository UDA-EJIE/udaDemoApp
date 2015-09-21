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
(function ($) {
	
	//****************************************************************************************************************
	// DEFINICIÓN BASE DEL PATRÁN (definición de la variable privada que contendrá los métodos y la función de jQuery)
	//****************************************************************************************************************
	
	var rup_multicombo = {};
	
	//Se configura el arranque de UDA para que alberge el nuevo patrón 
	$.extend($.rup.iniRup, $.rup.rupSelectorObjectConstructor("rup_multicombo", rup_multicombo));
	
	//*******************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//*******************************
	$.fn.rup_multicombo("extend",{
		getRupValue : function(param){
			return $(this).rup_multicombo("value");
		},
		setRupValue : function(param){
			$.data(this[0],"setRupValue",param.toString());
			$(this).rup_multicombo("select",param.toString());
		},
		clear : function(){
			$(this).rup_multicombo("select");
		},
		select : function(param){
		},
		selectLabel : function(param){
		},
		value : function(){
		},
		label : function(){
		},
		index : function(){
		}, 
		disable : function(){
		},
		enable : function(){
		},
		isDisabled : function(){
		},
		//Funcion que refresca los valores asociados al combo
		refresh : function(){
		},
		//Funcion encargada de recargar los combos
		reload: function (id){
		},
		order: function (orderedByValue, orderAsNumber, skipFirst){
		}
	});
	
	//*******************************
	// DEFINICIÓN DE MÉTODOS PRIVADOS
	//*******************************
	$.fn.rup_multicombo("extend", {
			_init : function(args){
				if (args.length > 1) {
					$.rup.errorGestor($.rup.i18nParse($.rup.i18n.base,"rup_global.initError") + $(this).attr("id"));
				} else {
					//Sobreescribir literales por defecto
					$.extend($.ech.multiselect.prototype.options, $.extend({}, $.rup.i18n.base.rup_multicombo, args[0]));
					
					var settings = $.extend({}, $.fn.rup_multicombo.defaults, args[0]),
						html;
					
					//Contenido combo
					html = $("<select>").attr({"id" : $(this).attr("id"), "name" : settings.name, "ruptype":"combo"}).addClass("rup_combo");
					if ($(this).hasClass("validableElem")){
						html.addClass("validableElem");
					}
					
					this.multiselect(settings);
					
				}
			}
		});
		
	//******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//******************************************************
	$.fn.rup_multicombo.defaults = {
		
	};	
	
	
})(jQuery);