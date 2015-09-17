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
		]
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