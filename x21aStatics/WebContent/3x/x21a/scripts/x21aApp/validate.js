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
	//Cambiamos los names para que funcionen
	$('[name="alertDay"]').attr('name', 'alertDay[]');
	$('[name="alertDayJqueryui"]').attr('name', 'alertDayJqueryui[]');
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
		, submitHandler : function () {
			alert('se ha enviado correctamente');
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
			},
			"rol": {
				required: true
			},
			"autocomplete": {
				required: true
			},
			"rolName2": {
				required: true
			},
			"autocompleteName2": {
				required: true
			}
		},
		messages:{
			"nombre": {
				required: $.rup.i18n.app.validacion.nombre
			},
			"apellido1": {
				required: $.rup.i18n.app.validacion.apellido1
			},
			"apellido2": {
				required: $.rup.i18n.app.validacion.apellido2
			},
			"alertDay[]": {
				required: $.rup.i18n.app.validacion.alertDay
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
	
	var $feedbackHorizontalJqueryui = jQuery("#feedbackHorizontalJqueryui").rup_feedback({ 
		type: "ok",
		closeLink: true,
		block:false
	});
	
	$("#formHorizontalJqueryui").rup_validate({
		adapter: 'validate_jqueryui',
		feedback: $feedbackHorizontalJqueryui,
		liveCheckingErrors: false,
		showFieldErrorAsDefault: true,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback:true, 
		rules:{
			"nombreJqueryui": {
				required: true
			},
			"alertDayJqueryui[]": {
				required: true
			},
			"rolJqueryui": {
				required: true
			},
			"autocompleteJqueryui": {
				required: true
			},
			"rolName2Jqueryui": {
				required: true
			},
			"autocompleteName2Jqueryui": {
				required: true
			}
		},
		messages:{
			"nombreJqueryui": {
				required: $.rup.i18n.app.validacion.nombre
			},
			"alertDayJqueryui[]": {
				required: $.rup.i18n.app.validacion.alertDay
			}
		},
		labels:{
			"alertDayJqueryui[]": "#alertDayError"
		},
		icons:{
			"alertDayJqueryui[]": "#alertDayErrorLabel"
		}
	});
	
	$('#rol_detail_table').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		selected: "coldfusion_value",
		width: 300,
		blank : "",
		rowStriping : true,
		inputText:true
	});
	
	//$("#rol_detail_table").rup_combo("disable");
	
	$("#autocomplete").rup_autocomplete({
		/*source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]*/
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		defaultValue : "",
		contains : false
	});

	$('#rolName').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		selected: "c_value",
		width: 300,
		blank : "",
		rowStriping : true,
		inputText:true
	});
	
	$("#autocompleteName").rup_autocomplete({
		/*source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]*/
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		defaultValue : "",
		contains : false
	});
	
	$('#rol_detail_tableJqueryui').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		selected: "coldfusion_value",
		width: 300,
		blank : "",
		rowStriping : true,
		inputText:true
	});
	
	
	$("#autocompleteJqueryui").rup_autocomplete({
		/*source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]*/
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		defaultValue : "",
		contains : false
	});

	$('#rolNameJqueryui').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		selected: "c_value",
		width: 300,
		blank : "",
		rowStriping : true,
		inputText:true
	});
	
	$("#autocompleteNameJqueryui").rup_autocomplete({
		/*source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]*/
		source : [
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "groovy", value:"groovy_value"}
		],
		defaultValue : "",
		contains : false
	});
	
});
