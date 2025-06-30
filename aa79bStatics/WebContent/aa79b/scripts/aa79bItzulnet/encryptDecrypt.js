jQuery(function($) {
	
	$("#encriptarButton").on("click", function() {
		$("#ficheroEncriptar").rup_dialog("open");	
	});
	
	$("#ficheroAttach").on("click", function() {
		$('#ficheroFile').click();
	});
	
	$('#ficheroFile').change(function(){
		if(this.files.length > 0){
			$("#nombreFicheroAttach").val(this.files[0].name);
		}
	});
	
	$("#ficheroEncriptar_form").rup_form({
		url: "/aa79bItzulnetWar/servicios/procesarFichero"
		, dataType: "json"
		, type: "POST"
		, success: function(data){
			if(data.error !== null
					&& data.error !== ''){
				alert(data.error);
			}else{
				window.open("/aa79bItzulnetWar/servicios/abrirFicheroEncriptado");
				$("#ficheroEncriptar_form").rup_form("clearForm");
				$("#passwordFichero_form").rup_form("clearForm");
				$('#passwordFichero').rup_dialog("close");
			}
			desbloquearPantalla();
		}
		, error: function(data){
			desbloquearPantalla();
		}
		, beforeSend: function (xhr, ajaxOptions) {
			//Ponemos esto para que no falle el submit del formulario con fichero en Chrome
			return "skip";
		}
	});
	
	$("#passwordFichero").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        width: "700",
        title: $.rup.i18nParse($.rup.i18n.app,"label.subirFichero"),
		open : function() {
		}
	});
	
	$("#ficheroEncriptar_feedback").rup_feedback({
		block: false, 
		delay: 3000
	});
	
	$("#passwordFichero_form").rup_validate({
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"passwordEncriptar": {required: true},
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
    });
	
	$('#ficheroEncriptar_form').rup_validate({
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"nombreFicheroAttach": {required: true},
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	$('#cancelarPasswordButton').on("click", function(){
		$("#passwordFichero_form").rup_form("clearForm");
		$('#passwordFichero').rup_dialog("close");
	});
	
	$('#enviarButton').on("click", function(){
		
		if($("#ficheroEncriptar_form").valid()){
			if($('#opcion_E')[0].checked || $('#opcion_D')[0].checked){
				$("#passwordFichero").rup_dialog("open");	
			}else{
				$('#ficheroEncriptar_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.seleccionEncriptODesencript, "error");
			}
		}
	});
	
	$('#enviarPasswordButton').on("click", function(){
		if($("#passwordFichero_form").valid()){
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
			$("#password").val($("#passwordEncriptar").val());
			$("#ficheroEncriptar_form").submit();
		}
	});
		
});