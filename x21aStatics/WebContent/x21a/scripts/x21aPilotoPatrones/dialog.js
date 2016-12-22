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
$(document).ready(function () {

	$("#idDialog").rup_dialog({
		type: $.rup.dialog.DIV,
		autoOpen: false,
		modal: true,
		resizable: true,
		title: "Título del dialog (div)",
		buttons: [{
				text: "Aceptar",
				click: function () { 
					$("#idDialog").dialog("close"); 
				}
			},
			{
				text: "Enviar",
				click: function () { 
					$("#idDialog").dialog("close"); 
				}
			},
			{
				text: "Abandonar",
				click: function () { 
					$("#idDialog").dialog("close"); 
				},
				btnType: $.rup.dialog.LINK
			}
		],
		position: { my: "left top", at: "left bottom", of: $("#btnAjaxDialogWAR") } 
	});	
	$("#btnDialog").bind("click", function () {
		$("#idDialog").rup_dialog("open");
	});

	
	$("#btnAjaxDialogWAR").bind("click", function () {
		$(document).rup_dialog({
			type: $.rup.dialog.AJAX,
			url: "../patrones/dialogAjax" ,
			autoOpen: true,
			modal: true,
			width: "650",
			resizable: true,
			title: "Diálogo Ajax (War)",
			buttons: [{
				text: "Aceptar",
				click: function () { 
					$(this).dialog("close");
				}					
			}]
		});	
		

		$('#combo').rup_combo({
			//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
			source : [
				{i18nCaption: "asp", value:"asp_value"},
				{i18nCaption: "c", value:"c_value"},
				
			],
			selected: "perl_value",
			width: 300,
			blank : "0",
			rowStriping : true,
			inputText:true
		});
	});
	
	$("#btnAjaxDialogStatics").bind("click", function () {
		$(document).rup_dialog({
			type: $.rup.dialog.AJAX,
			url: $.rup.APP_STATICS + "/resources/ajaxDiv.htm",
			autoOpen: true,
			modal: true,
			width: "650",
			resizable: true,
			title: "Diálogo Ajax (Statics)",
			buttons: [{
				text: "Aceptar",
				click: function () { 
					$(this).dialog("close");
				}					
			}]
		});	
	});
	
	$("#btnTextDialog").bind("click", function () {
		$(document).rup_dialog({
			type: $.rup.dialog.TEXT,
			autoOpen: true,
			modal: true,
			resizable: true,
			title: "Título del dialog (text) ",
			message: "Se esta creando un div con el mensaje puesto por parametro."
		});
	});

});