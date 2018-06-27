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
	
	$("[title]").rup_tooltip({"applyToPortal": true});
	
	$("#codeTooltip").rup_tooltip({
		content: {
			text: "Esto es un ejemplo de tooltip sobre imagen"
		},
		position: {
			my: 'top center',
			at: 'bottom center',
			target: $("#code")
		},
		show: {
			event: 'click'
		}
	});
	
	$("#idTooltip").rup_tooltip({
		content: {
			text: 'Esto es un ejemplo de tooltip modal sobre imagen',
			title: {
				text: 'Tooltip modal'
			}
		},
		position: {
			my: 'bottom center',
			at: 'top center', 
			target: $("#identificador")
		},
		show: {
			event: 'click',
			modal: true
		},
		hide: {
			event: 'click'
		}
	});
	
	$("#htmlTooltip").rup_tooltip({
		content: {
			text: $("#accordionExample1")
//			title: {
//				text: 'Tooltip modal'
//			}
		},
		style:{
			width:900
		},
		position: {
			my: 'bottom center',
			at: 'top center', 
			target: $("#htmlTooltip")
		},
		show: {
			event: 'click',
		},
		hide: {
			event: 'click'
		}
	});
	
	$("#button").click(function() {
		$(".qtip").rup_tooltip('option', 'style.widget', true);
	});
	
	$(".rup_accordion").rup_accordion({
		animated: "bounceslide",
		active: false,
		autoHeight: false,
		collapsible: true
	});
	
});