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
	
	var $feedbackLeftAligned = jQuery("#feedbackLeftAligned").rup_feedback({ 
		type: "ok",
		closeLink: true,
		block:false
	});
	
	$("#formLeftAligned").rup_validate({
		feedback: $feedbackLeftAligned,
		liveCheckingErrors: false,
		showFieldErrorAsDefault: true,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback:true, 
		rules:{
			"nombre": {required: true},
			"apellido1": {required: true},
			"apellido2": {required: true}
		}
	});
	
	var $feedbackHorizontal = jQuery("#feedbackHorizontal").rup_feedback({ 
		type: "ok",
		closeLink: true,
		block:false
	});
	
	$("#formHorizontal").rup_validate({
		feedback: $feedbackHorizontal,
		liveCheckingErrors: false,
		showFieldErrorAsDefault: true,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback:true, 
		rules:{
			"nombre": {
				required: true
			},
			"apellido1": {
				required: true
			},
			"apellido2": {
				required: true
			},
			"alertDay[]": {
				required: true
			}
		},
		messages:{
			"nombre": {
				required: "Debe de especificar un nombre obligatoriamente."
			},
			"apellido1": {
				required: "Debe de especificar un primer apellido obligatoriamente."
			},
			"apellido2": {
				required: "Debe de especificar un segundo apellido obligatoriamente."
			},
			"alertDay[]": {
				required: "Debe de especificar al menos una opción entre las mostradas."
			}
		},
		labels:{
//			"nombre": {
//				required: "Debe de especificar un nombre obligatoriamente."
//			},
//			"apellido1": {
//				required: "Debe de especificar un primer apellido obligatoriamente."
//			},
//			"apellido2": {
//				required: "Debe de especificar un segundo apellido obligatoriamente."
//			},
			"alertDay[]": "#alertDayError"
		},
		icons:{
//			"nombre": {
//				required: "Debe de especificar un nombre obligatoriamente."
//			},
//			"apellido1": {
//				required: "Debe de especificar un primer apellido obligatoriamente."
//			},
//			"apellido2": {
//				required: "Debe de especificar un segundo apellido obligatoriamente."
//			},
			"alertDay[]": "#alertDayErrorLabel"
		}
	});
	
});