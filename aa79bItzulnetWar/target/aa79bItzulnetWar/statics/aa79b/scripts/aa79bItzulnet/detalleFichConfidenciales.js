/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function volverAFichConfi(){
	$("#passwordFichero").remove();
	$("#passwordFichero").rup_dialog('destroy');
	$("#addRegistro_dialog").rup_dialog('destroy');
	$("#divFicherosConfidencialesGeneral").detach();
	$("#divFicherosConfidencialesCapa").html("<div id='divFicherosConfidencialesGeneral'></div>");
	$("#divFicherosConfidencialesGeneral").html(capaFichConfiGen);	
}

/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function descargarDocumento(elIdDoc){			
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/1/'+elIdDoc);
}
function descargarDocumentoGeneral(elIdDoc){			
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/2/'+elIdDoc);
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	// Actualizar bitácora + cabecera
	bitacoraUpdate(false);
		
	$('#detalleFichConfi_feedback').rup_feedback({
		block : false
	});
	
	$("#detalleFichConfi_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverAFichConfi"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					volverAFichConfi();
				}
			}
		]
	});
	
	$('#passwordEncriptar').keypress(function (e) {
		var key = e.which;
		if(key === 13) {
			$('#enviarPasswordButton').click();
		}
	});   

	$("[id^='desencriptar_button']").on("click", function (){	
		$('#nombreFicheroAttach').val($('#nombreFicheroAttach_'+$(this).parents("a").data("id")).val());
		$('#tamanoFichero').val($('#tamanoFichero_'+$(this).parents("a").data("id")).val());
		$('#contentTypeFichero').val($('#contentTypeFichero_'+$(this).parents("a").data("id")).val());
		$('#oidFichero').val($('#oidFichero_'+$(this).parents("a").data("id")).val());
		$('#idFichero').val($(this).parents("a").data("id"));
		
		if($("#detalleFichConfi_filter_form").valid()){
			$("#passwordFichero").rup_dialog("open");
		}
	});
	
	$('a.descargarDoc').find(":not([id^='desencriptar_button'])").click(function(event){
		event.preventDefault();
		event.stopPropagation();
		var elIdDoc = $(this).parents("a").data("id");
		descargarDocumento(elIdDoc);
	});
	$('a.descargarDocGeneral').find(":not([id^='desencriptar_button'])").click(function(event){
		event.preventDefault();
		event.stopPropagation();
		var elIdDoc = $(this).parents("a").data("id");
		descargarDocumentoGeneral(elIdDoc);
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
	
	$('#detalleFichConfi_filter_form').rup_validate({
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
	
	$('#enviarPasswordButton').on("click", function(){
		if($("#passwordFichero_form").valid()){
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		    $("#password").val($("#passwordEncriptar").val());
		    
		    var Fichero = 
		    {
		    	"codigo": $('#idFichero').val(),
		    	"nombre": $("#nombreFicheroAttach").val(), 
		        "tamano": $("#tamanoFichero").val(),
		        "contentType": $("#contentTypeFichero").val(),
		        "oid": $("#oidFichero").val(),
		        "encriptado": $("#password").val()
		    };
		    
		    $.ajax({
				type: "POST",
				url: "/aa79bItzulnetWar/servicios/trataFicherosConfi/ficherosConfidenciales/desencriptarFichero/"+anyoExpediente+"/"+idExpediente,
				dataType: "json",
				contentType: 'application/json', 
				data: $.toJSON({
			   		"fichero" : Fichero 
			   	}),
				cache: false,
				success: function (data) {
					
					if (data.error !== null
							&& data.error !== ''){
						$.rup_messages("msgError", {
							message: data.error,
							title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error")
						});
						
						desbloquearPantalla();
					} else {
						window.open("/aa79bItzulnetWar/servicios/abrirFicheroEncriptado");
						$("#detalleFichConfi_filter_form").rup_form("clearForm");
						$("#passwordFichero_form").rup_form("clearForm");
						$('#passwordFichero').rup_dialog("close");
						//Actualizar bitácora + cabecera
						bitacoraUpdate(false);
						
						desbloquearPantalla();
					}
					
			    },
			    error: function (){
			    	$('#detalleFichConfi_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
			    	desbloquearPantalla();
			    }
		    });
		    
		}
	});
	
});