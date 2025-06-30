
jQuery(function($) {
	
	/* PID PIF - INICIO */

	$('#cargaSolicitudes_feedback').rup_feedback({
		block : false
	});
	
	$("#pidButton").on("click", function() {
		$("#subidaFicheroPid").rup_dialog("open");
	});
	
	$('#subidaFicheroPid_form').rup_form({
		url: "/aa79bItzulnetWar/pruebas/subidaFichero"
		, dataType: "json"
		, validate: {
			feedback:$("#subidaFicheroPid_feedback"),
			rules:{
				"fichero":{required: true}
			}
		}
		, beforeSend: function (xhr, ajaxOptions) {
			//Ponemos esto para que no falle el submit del formulario con fichero en Chrome
			return "skip";
		}
		, success: function(data){
			$("#divFichero").hide();
			$("#divNombreFichero").show();
			$("#nombreFichero").val($("#fichero").val().split('\\').pop());
		 }
		, error: function(data){
			$('#subidaFicheroPid_feedback').rup_feedback("set", "ERROR: "+data.responseText, "error");
		}
	});
	
	$('#cargaSolicitudes_form').rup_validate({
		feedback:$("#subidaFicheroPid_feedback")
		, showErrorsInFeedback:false
	});
	
	$('#subidaFicheroPid_form').rup_validate({
		feedback:$("#subidaFicheroPid_feedback"),
		rules:{
		}
		, showErrorsInFeedback:false
	});
	
	$("#fichero").change(function(){
		$('#subidaFicheroPid_feedback').rup_feedback("hide");
		$("#subidaFicheroPid_form").submit();
	});
	
	$("#subidaFicheroPid_feedback").rup_feedback({
		block: false
	});
	$("#subidaFicheroPid").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        width: "700",
        title: "Subir nuevo fichero",
        buttons: [{
			text: "Cargar",
			click: function () {
				if ($('#subidaFicheroPid_form').valid()) {
					$('#cargaSolicitudes_form').submit();
				}
			}
		},
		{
			text: "Cancelar",
			click: function () { 
				$("#subidaFicheroPid").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$("#divFichero").show();
			$("#divNombreFichero").hide();
			$("#subidaFicheroPid_form").validate().resetForm();
			$("#cargaSolicitudes_form").rup_form("clearForm");
			$('#subidaFicheroPid_feedback').rup_feedback("hide");
			$("#cargaSolicitudes_form").validate().resetForm();
		}
	});
	
	$("#boton_modificar").click(function() {
		$("#divFichero").show();
		$("#divNombreFichero").hide();
		$("#subidaFicheroPid_form").validate().resetForm();
		$("#cargaSolicitudes_form").validate().resetForm();
	});
	
	$('#cargaSolicitudes_form').rup_form({
		url: "/aa79bItzulnetWar/pruebas/cargarSolicitudes"
		, success: function(data){
			$("#subidaFicheroPid").rup_dialog('close');
		}
		, error: function(data){
			$('#subidaFicheroPid_feedback').rup_feedback("set", "Error subida fichero", "error");
		}
	});
	
	/* PID PIF - FIN */
	
	/* ENCRIPTAR - INICIO */
	
	$("#encriptarButton").on("click", function() {
		$("#ficheroEncriptar").rup_dialog("open");	
	});
	
	$("#ficheroEncriptar_form").rup_form({
		url: "/aa79bItzulnetWar/pruebas/procesarFichero"
		, dataType: "json"
		, type: "POST"
		, validate: {
			feedback:$("#ficheroEncriptar_feedback"),
			rules:{
				"ficheroFile":{required: true}
			}
		}
		, success: function(data){
			if(data.error !== null
					&& data.error !== ''){
				alert(data.error);
			}else{
				window.open("/aa79bItzulnetWar/pruebas/pruebaAbrir");
			}
		}
		, beforeSend: function (xhr, ajaxOptions) {
			//Ponemos esto para que no falle el submit del formulario con fichero en Chrome
			return "skip";
		}
	});
	
	$("#ficheroEncriptar").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        width: "700",
        title: "Subir nuevo fichero",
		open : function() {
			$("#divFicheroEncriptar").show();
			$("#divNombreFicheroEncriptar").hide();
			$("#ficheroEncriptar_form").validate().resetForm();
			$('#ficheroEncriptar_feedback').rup_feedback("hide");
		}
	});
	
	$("#ficheroEncriptar_feedback").rup_feedback({
		block: false
	});
	
	$('#ficheroEncriptar_form').rup_validate({
		feedback:$("#ficheroEncriptar_feedback"),
		rules:{
		}
		, showErrorsInFeedback:false
	});
	
	$('#cancelarButton').on("click", function(){
		$('#ficheroEncriptar').rup_dialog("close");
	});
	
	$('#enviarButton').on("click", function(){
		$("#ficheroEncriptar_form").submit();
	});
	/* ENCRIPTAR - FIN */
	
	/* ENVIAR MAIL PRUEBAS - INICIO */
	$("#sendMailButton").on("click", function() {
		$.rup_messages("msgConfirm", {
			title: "Envío de mail de prueba",
			message: "¿Está seguro de realizar el envío de un e-mail de prueba?",
			OKFunction: function(event, ui){
				jQuery.ajax({
					type: 'POST',
					url: '/aa79bItzulnetWar/pruebas/sendMail',
					async: false, 
					cache: false
				});
			}
		});	
	});
	/* ENVIAR MAIL PRUEBAS - FIN */
	
	/* LIBRO REGISTRO - INICIO */
	$("#libroRegistroButton").on("click", function() {
		$.rup_messages("msgConfirm", {
			title: "Libro de registro", 
			message: "¿Está seguro de realizar una entrada en el libro de registro?",
			OKFunction: function(event, ui){
				jQuery.ajax({
					type: 'POST',
					url: '/aa79bItzulnetWar/pruebas/libroRegistro',
					async: false, 
					cache: false
				});
			}
		});	
	});
	/* LIBRO REGISTRO - FIN */
	
	/* BATCH - INICIO */
	$("#batchButton").on("click", function() {
		$.rup_messages("msgConfirm", {
			title: "Batch", 
			message: "¿Está seguro de realizar una petición O75?",
			OKFunction: function(event, ui){
				jQuery.ajax({
					type: 'GET',
					url: '/aa79bItzulnetWar/pruebas/procesoBatch',
					async: false, 
					cache: false,
					success: function(data){
						alert(data);
					}
				});
			}
		});	
	});
	/* BATCH - FIN */
	
	/* ICS - INICIO */
	$("#icsButton").on("click", function() {
		$.rup_messages("msgConfirm", {
			title: "Descargar ICS", 
			message: "¿Está seguro de descargar el archivo ics?",
			OKFunction: function(event, ui){
				window.location.href = "/aa79bItzulnetWar/pruebas/descargarIcs";
			}
		});	
	});
	/* ICS - FIN */
	
	/* BUSCADOR PERSONAS - INICIO */
	$("#buscadorPersonasButton").on("click", function() {
		$("#buscadorPersonas").buscador_personas("open");
	});
	
	// valores de destino : 
//		- 'S' --> Solicitante
//		- 'R' --> Receptor
//		- 'I' --> Personal IZO
//		- 'P' --> Proveedores externos
	
	$("#buscadorPersonas").buscador_personas({destino:'I' , multiselect:false , callback: seleccionarPersona});
	
	function seleccionarPersona(obj, personas){
		if(obj!=null && obj.length>0){
			var datosPersonas = "";
			for(var i=0; i<obj.length; i++){
				var persona = obj[i];
				datosPersonas = datosPersonas + "persona ["+i+"]: ";
				datosPersonas = datosPersonas + persona.dni + " , ";
				datosPersonas = datosPersonas + persona.nombre + " , ";
				datosPersonas = datosPersonas + persona.entidad["tipo"] + "; ";
				
			}
		}
		
	}
	
	/* BUSCADOR PERSONAS - FIN */
	
	/* BUSCADOR LOTES - INICIO */
	$("#buscadorLotesButton").on("click", function() {
		$("#buscadorLotes").buscador_lotes("open");
	});
	
	$("#buscadorLotes").buscador_lotes({multiselect:false, anyo: 2018, numExp: 4, callback: seleccionarLotes});
	
	function seleccionarLotes(obj, lotes){
		if(obj!=null && obj.length>0){
			var datosLotes = "";
			for(var i=0; i<obj.length; i++){
				var lote = obj[i];
				datosLotes = datosLotes + "lote ["+i+"]: ";
				datosLotes = datosLotes + lote.idLote + " , ";
				datosLotes = datosLotes + lote.nombre + " , ";
				
			}
		}
	}
	
	/* BUSCADOR LOTES - FIN */
	
	
	$("#descargarOidButton").on("click", function() {
		window.open('/aa79bItzulnetWar/pruebas/descargarOid?nombre='+encodeURIComponent("documentodescargadocorrectamente.zip")+'&oid='+encodeURIComponent("0902966e801e3f89"));
	});
	
	
});