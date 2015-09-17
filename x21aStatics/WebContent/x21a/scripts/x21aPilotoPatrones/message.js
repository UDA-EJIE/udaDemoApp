jQuery(document).ready(function () {

	//f(x) extra
	function acceptPulsed() { alert("Aceptar"); }	
	
	
	$("#btnError").bind("click", function () {
		$.rup_messages("msgError", {
			title: "Error grave",
			message: "<p>Se ha producido un error a la hora de intentar guardar el registro.<br>Consulte con el administrador.</p>"
		});
	});
	
	$("#btnConfirm").bind("click", function () {
		$.rup_messages("msgConfirm", {
			message: "¿Está seguro que desea cancelar?",
			title: "Confirmación",
			OKFunction : acceptPulsed
		});
	});
	
	$("#btnOK").bind("click", function () {
		$.rup_messages("msgOK", {
			title: "Correcto",
			message: "Todo ha ido OK."
		});
	});
	
	$("#btnAlert").bind("click", function () {
		$.rup_messages("msgAlert", {
			title: "Alerta",
			message: "Esto es una alerta."
		});
	});
	
	$("#btnAlertJS").bind("click", function () {
		alert("esto es un alert de javascript puro");
	});
	
});