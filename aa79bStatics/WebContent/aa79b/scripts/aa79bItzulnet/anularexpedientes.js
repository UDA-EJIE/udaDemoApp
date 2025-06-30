var initFormAnulExp = '';

/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function volverAGestAnul(mostrarMsgOk){
	$("#divGestionAnulacionesGeneral").detach();
	$("#divGestionAnulacionesCapa").html("<div id='divGestionAnulacionesGeneral'></div>");
	$("#divGestionAnulacionesGeneral").html(capaGestAnulGen);
	
	expedientesSeleccionados = [];
	$("#busquedaGestAnul").trigger('reloadGrid');
	$("#busquedaGestAnul").rup_table("resetSelection");
	
	if (mostrarMsgOk){
		$('#busquedaGestAnul_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
	}
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

function fnComboMotivosAnulacion(id){
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/administracion/datosmaestros/motivosanulacion/findMotivosAnulacionAlta",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descEs012"
					: "descEu012",
			value: "id012"
		}
		,blank: ""
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
		,change: function(){					
			fnCambioMotivoAnulacion($(this).attr("id"));
		}
	});
}

function fnCambioMotivoAnulacion(id){
	if (datosAnulacionExp.motivosAnulacion.otros === $("#"+id).rup_combo("getRupValue")){
		validarCampos();
	} else {
		$('#anularExpedientes_feedback').rup_feedback('close');
		eliminarMensajesValidacion();
		eliminarValidacionesCampos();
	}
}

function validarCampos(){
	$("#observaciones_detail_table").rules("add", "required");
}

function eliminarValidacionesCampos(){
	$("#observaciones_detail_table").rules("remove", "required");
}

function anularExpedientes(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	$("#anularExpedientes_feedback").rup_feedback("close");
	
	var listaExpedientes = new Array();
	var anyoNumExp;
	var anyoNumExpediente;
	var anyo;
	var numExp;
		
	for (var i = 0; i < expedientesSeleccionados.length; i++) {
		anyoNumExp = expedientesSeleccionados[i];
		
		anyoNumExpediente = anyoNumExp.split("-");	
		anyo = anyoNumExpediente[0];
		numExp = anyoNumExpediente[1];
		
		listaExpedientes.push( {
    		"anyo": anyo,
    		"numExp": numExp
    	} );
	}
	
	var observacionesAnulExp = {
		"obsvAnulacion": $("#observaciones_detail_table").val()
	};
	
	var anulacionExpediente = 
    {
        "idMotivoAnulacion": $("#idMotivosAnulacion_detail_table").val(), 
        "descMotivoAnulacion": $("#idMotivosAnulacion_detail_table option:selected").text(),
        "observacionesAnulExp": observacionesAnulExp
    };
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/anulacionExpedientes'
        ,dataType: 'json'
        ,contentType: 'application/json'
        ,data: $.toJSON({
	   		"anulacionExpediente" : anulacionExpediente,
	   		"listaExpedientes" : JSON.stringify(listaExpedientes) 
	   	})
        ,async: false 
        ,success:function(){
        	volverAGestAnul(true);
	        desbloquearPantalla();
        }
		,error: function(error){
			$('#anularExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
	
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
		
	$('#anularExpedientes_feedback').rup_feedback({
		block : false
	});
	
	$("#anularExpedientes_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverAGestAnul"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if (initFormAnulExp !== $("#anularExpedientes_filter_form").rup_form("formSerialize")){
						$.rup_messages("msgConfirm", {
	         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
	         				OKFunction: function(){
	         					volverAGestAnul(false);
	         				}
	         			});
					} else {
						volverAGestAnul(false);
					}
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,id: "anularExpedientes"	
				,css: "fa fa-floppy-o"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if ($("#anularExpedientes_filter_form").valid()){
						anularExpedientes();
					}
				}
			}
		]
	});
	
	fnComboMotivosAnulacion('idMotivosAnulacion_detail_table');
	
	$("#anularExpedientes_filter_form").rup_validate({
		feedback: $('#anularExpedientes_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"idMotivoAnulacion": { required: true },
			"observacionesAnulExp.obsvAnulacion": { maxlength: 4000 }
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	initFormAnulExp = $("#anularExpedientes_filter_form").rup_form("formSerialize");
	
});