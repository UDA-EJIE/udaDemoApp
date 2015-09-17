jQuery(document).ready(function () {

	$("#btnDialog").bind("click", function () {
		$("#idDialog").rup_dialog({
			type: $.rup.dialog.DIV,
			autoOpen: false,
			modal: true,
			resizable: true,
			title: "titulo del dialog",
			buttons: [
			    { text: "Aceptar", click: function () { $("#idDialog").dialog("close"); } },
			    { text: "Enviar", click: function () { $("#idDialog").dialog("close"); } },
			    { text: "Abandonar", click: function () { $("#idDialog").dialog("close"); }, btnType: $.rup.dialog.LINK }
			]
		});
	});

	$("#btnAjaxDialog").bind("click", function () {
		$.rup_dialog({
			type: $.rup.dialog.AJAX,
			url: $.rup.APP_STATICS + "/resources/ajaxDiv.htm",
			autoOpen: true,
			modal: true,
			width: "650",
			resizable: true,
			title: "Mensaje de Sistema",
			buttons: [{
				text: "Aceptar", click: function () { $(this).dialog("close"); }					
			}]
		});	
	});
	
	$("#btnTextDialog").bind("click", function () {
		$.rup_dialog({
			type: $.rup.dialog.TEXT,
			autoOpen: true,
			modal: true,
			resizable: true,
			title: "titulo del dialog",
			message: "Se esta creando un div con el mensaje puesto por parametro."
		});
	});
	$("#btnTextDialog2").bind("click", function () {
		$.rup_dialog({
			type: $.rup.dialog.TEXT,
			autoOpen: true,
			modal: true,
			resizable: true,
			title: "titulo del segundo dialogo",
			message: "Mensaje del segundo dialogo."
		});
	});


});