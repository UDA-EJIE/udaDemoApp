/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */
function volverATareasDelExpediente(mostrarMsgOk){
	$("#divReasignarTareas").detach();
	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
	$("#divDetalleExpediente").html(capaTareasExpediente);
	
	tareasSeleccionadas = [];
	$("#tareasExpedientesForm").rup_table('filter');
	$("#tareasExpedientesForm").rup_table("resetSelection");
	
	$("#buscadorIZO").remove();
	$("#buscadorIZO").rup_dialog('destroy');
	
	if (mostrarMsgOk){
		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasReasignadasCorrectamente"), "ok");
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

function reasignarTareas(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	var dni = $('#dniReasignacion').val();
	var idEntidad = $('#idEntidad').val() === "" ? 0 : $('#idEntidad').val();
	var tipoEntidad = $('#tipoEntidad').val();
	var idTarea;
	var listSelectedIds = "";
	
	for (var i = 0; i < tareasSeleccionadas.length; i++) {
		idTarea = tareasSeleccionadas[i];
		
		if (i !== 0){
			listSelectedIds += ",";
		}
		
		listSelectedIds += idTarea;
	}
	
	var jsonObject = {"dni" : dni, "idEntidad" : idEntidad, "tipoEntidad" : tipoEntidad, "listSelectedIds" : listSelectedIds};
	
	$.ajax({

        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/reasignarTareas' 
        ,dataType: 'json' 
        ,data: jsonObject 
        ,async: false 
        ,success:function(data){
        	
        	$("#asignadoA").val("");
			$("#dniReasignacion").val("");
			$("#tipoEntidad").val("");
			$("#idEntidad").val("");
        	
			volverATareasDelExpediente(true);
			
			if (data === -1){
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
			} else if (data === -2){
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
			} else if (data === -3){
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.registroCalendarioPersonal"), "error");
			}
			
			desbloquearPantalla();
        	
        }
		,error: function(error){
			
			$('#reasignarTareas_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			
			desbloquearPantalla();
			
	   	 }
	});
	
}

function autoasignarTarea(){
	
	$.ajax({

        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/autoasignartareas'
        ,dataType: 'json' 
        ,contentType: 'application/json'
        ,async: false 
        ,success:function(data){
        	
        	if (data !== null){
        		$('#dniReasignacion').val(data.dni);
        		$('#asignadoA').val(data.nombre);
        		$('#idEntidad').val('');
        		$('#tipoEntidad').val('');
        	}
        	
        }
		,error: function(error){
			$('#reasignarTareas_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
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
	
	$('#reasignarTareas_feedback').rup_feedback({
		block : false
	});
	
	bitacoraUpdate(false);
	
	$("#reasignarTareas_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverATareasExp"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if ($('#dniReasignacion').val() !== ""){
						$.rup_messages("msgConfirm", {
	         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	         				OKFunction: function(){
	         					volverATareasDelExpediente(false);
	         				}
	         			});
					} else {
						volverATareasDelExpediente(false);
					}
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,id: "reasignarTareas"	
				,css: "fa fa-floppy-o"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if ($('#dniReasignacion').val() !== ""){
						reasignarTareas();
					} else {
						$.rup_messages("msgAlert", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
							message: $.rup.i18nParse($.rup.i18n.app,"mensajes.reasignacionTareasIncorrecta")
						});
					}
				}
			}
		]
	});
	
	$("#buscadorIZO").buscador_personas({destino:'I' , multiselect:false, anyo: anyoExpediente, numExp: idExpediente, callback: seleccionarPersona});
	
	function seleccionarPersona(obj, personas){
		if(obj!=null && obj.length>0){
			for(var i=0; i<obj.length; i++){
				var persona = obj[i];
				
				$("#asignadoA").val(persona.nombreCompleto);
				$("#dniReasignacion").val(persona.dni);
				$("#tipoEntidad").val(persona.entidad.tipo);
				$("#idEntidad").val(persona.entidad.codigo);
				
			}
		}
		
	}
	
	$("#autoasignarLink").on("click", function (event){
		event.preventDefault();
		autoasignarTarea();
	});
	
	$("#personalIzoLink").on("click", function (event){
		event.preventDefault();
		$("#buscadorIZO").buscador_personas("open");
	});
	
});