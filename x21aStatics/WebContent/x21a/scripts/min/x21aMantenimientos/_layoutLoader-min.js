/*
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
jQuery(document).ready(function(){$.fn.rup_button.defaults.adapter="button_jqueryui";$.fn.rup_date.defaults.adapter="date_jqueryui";$.fn.rup_table.plugins.core.defaults.adapter="table_jqueryui";$.fn.rup_time.defaults.adapter="time_jqueryui";$.fn.rup_toolbar.defaults.adapter="toolbar_jqueryui";$.fn.rup_upload.defaults.adapter="upload_jqueryui";$.fn.rup_validate.defaults.adapter="validate_jqueryui";$.fn.rup_feedback.defaults.adapter="feedback_jqueryui";$("#rup_dept_logo").attr("src",$.rup.APP_STATICS+"/images/dept_logo_"+$.rup.lang+".gif");var b=false,a=false;if($.rup.LAYOUT==="vertical"){b=true}else{if($.rup.LAYOUT==="mixto"){a=true}}$("#x21aMantenimientosWar_migas").rup_breadCrumb({breadCrumb:{multi:{i18nCaption:"multi"},uda:{i18nCaption:"uda"},administracion:{i18nCaption:"administracion",alumno:{i18nCaption:"alumno"},subLevel:[{i18nCaption:"alumno",url:"/x21aMantenimientosWar/administracion/alumno"}]}},logOutUrl:"/x21aMantenimientosWar/logout"});$("#x21aMantenimientosWar_language").rup_language({languages:$.rup.AVAILABLE_LANGS_ARRAY,modo:"portal"});$("#x21aMantenimientosWar_menu").rup_menu({display:(b?"vertical":"horizontal")});$.extend(true,jQuery.rup.i18n.base.rup_combo,{blankNotDefined:"----"});$(".footer [title]").rup_tooltip();if(jQuery.rup_utils.aplicatioInPortal()){jQuery(".cabecera").remove();jQuery(".footer").remove()}});