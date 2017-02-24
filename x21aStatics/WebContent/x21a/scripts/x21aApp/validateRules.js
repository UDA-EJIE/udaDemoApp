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
	
	var $feedbackRequiredRules = jQuery("#feedbackRequiredRules").rup_feedback({ 
		type: "ok",
		closeLink: true,
		block:false
	});
	
	$('#diaObligatorio').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
			{i18nCaption: "lunes", value:"1"},
			{i18nCaption: "martes", value:"2"},
			{i18nCaption: "miercoles", value:"3"},
			{i18nCaption: "jueves", value:"4"},
			{i18nCaption: "viernes", value:"5"},
			{i18nCaption: "sabado", value:"6"},
			{i18nCaption: "domingo", value:"7"}
		],
		width: 100,
		ordered:false
	});
	
	$("#formRequiredRules").rup_validate({
		feedback: $feedbackRequiredRules,
		liveCheckingErrors: false,
		showFieldErrorAsDefault: true,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback:true, 
		rules:{
			"campoObligatorio1":{required:true},
			"campoObligatorio2":{required:"#esObligatorio:checked"},
			"campoObligatorio3":{
				required:function(){
					return $("#diaObligatorio").val() > 5;
				}
			}
		}
	});
	
	// Validaciones numéricas
	
	var $feedbackNumeric = jQuery("#feedbackNumeric").rup_feedback({ 
		type: "ok",
		closeLink: true,
		block:false
	});
	
	$("#formNumeric").rup_validate({
		feedback: $feedbackNumeric,
		liveCheckingErrors: false,
		showFieldErrorAsDefault: true,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback:true, 
		rules:{
			"valorMinimo":{min:10},
			"valorMaximo":{max:30},
			"valorIntervalo":{range:[10,30]},
			"numeroDecimal":{number:true},
			"entero":{integer:true}
		}
	});
		
	// Validaciones texto
	
	var $feedbackText = jQuery("#feedbackText").rup_feedback({ 
		type: "ok",
		closeLink: true,
		block:false
	});
	
	$("#formText").rup_validate({
		feedback: $feedbackText,
		liveCheckingErrors: false,
		showFieldErrorAsDefault: true,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback:true, 
		rules:{
			"longitudMinima":{minlength:8},
			"longitudMaxima":{maxlength:20},
			"longitudIntervalo":{rangelength:[8,20]},
			"numeroDecimal":{number:true},
			"soloDigitos":{digits:true},
			"palabrasMaximo":{maxWords:6},
			"palabrasMinimo":{minWords:2},
			"palabrasIntervalo":{rangeWords:[2,6]},
			"letrasYPuntuacion":{letterswithbasicpunc:true},
			"alfanumerico":{alphanumeric:true},
			"soloLetras":{lettersonly:true},
			"sinEspacios":{nowhitespace:true},
			"patron":{pattern:'\\d'}
		}
	});
	
	
});