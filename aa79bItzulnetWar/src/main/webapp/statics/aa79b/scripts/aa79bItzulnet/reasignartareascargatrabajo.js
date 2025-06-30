
function reasignarTareasCargTrab(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	var dniCargTrab = $('#dniReasignacionCargTrab').val();
	var idEntidadCargTrab = $('#idEntidadCargTrab').val() === "" ? 0 : $('#idEntidadCargTrab').val();
	var tipoEntidadCargTrab = $('#tipoEntidadCargTrab').val();
	var idTarea;
	var listSelectedIdsCargTrab = "";
	for (var i = 0; i < tareasSeleccionadas.length; i++) {
		idTarea = tareasSeleccionadas[i].tarea.idTarea;
		
		if (i !== 0){
			listSelectedIdsCargTrab += ",";
		}
		
		listSelectedIdsCargTrab += idTarea;
	}
	
	var jsonObject = {"dni" : dniCargTrab, "idEntidad" : idEntidadCargTrab, "tipoEntidad" : tipoEntidadCargTrab, "listSelectedIds" : listSelectedIdsCargTrab};
	var tareaTrabajo = $('#tareaTrabajoCargTrab').val();
	if(tareaTrabajo && tareaTrabajo === '1'){
		jsonObject.tareaTrabajo = tareaTrabajo;
	}
	$.ajax({

        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/reasignarTareas' 
        ,dataType: 'json' 
        ,data: jsonObject 
        ,async: false 
        ,success:function(data){
        	
        	$("#asignadoACargTrab").val("");
			$("#dniReasignacionCargTrab").val("");
			$("#tipoEntidadCargTrab").val("");
			$("#idEntidadCargTrab").val("");
			volverATareasDeCargaTrabajo(true);
			
			if (data === -1){
				$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
			} else if (data === -2){
				$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
			} else if (data === -3){
				$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.registroCalendarioPersonal"), "error");
			}
			
			desbloquearPantalla();
        	
        }
		,error: function(error){
			
			$('#reasignarTareasCargaTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			
			desbloquearPantalla();
			
	   	 }
	});
}

function autoasignarTareasCargaTrabajo(){
	
	$.ajax({
        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/autoasignartareas'
        ,dataType: 'json' 
        ,contentType: 'application/json'
        ,async: false 
        ,success:function(data){
        	if (data !== null){
        		$('#dniReasignacionCargTrab').val(data.dni);
        		$('#asignadoACargTrab').val(data.nombre);
        		$('#idEntidadCargTrab').val('');
        		$('#tipoEntidadCargTrab').val('');
        	}
        	
        }
		,error: function(error){
			$('#reasignarTareasCargaTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
	   	 }
	});
	
}

function volverATareasDeCargaTrabajo(mostrarMensajeGuardado){
	$("#divReasignarTareasCargaTrabajo").detach();
	$("#divCargaTrabajoGeneral").html("<div id='divCargaTrabajo'></div>");
	$("#divCargaTrabajo").html(capaPestGeneral);
	// recargar la tabla de la pestaña de dashboard  de carga de datos si existe, por si se ha ejecutado la tarea
	
	var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
	if(active == 0){
		$("#busquedaGeneral").trigger('reloadGrid');
	}
	if(active ==1){
		$("#busquedaGeneralInter").trigger('reloadGrid');
	}
	if(active ==2){
		$("#busquedaGeneralTrabajo").trigger('reloadGrid');
	}
	
	
	$("#buscadorIZOCargTrab").remove();
	$("#buscadorIZOCargTrab").rup_dialog('destroy');
	
	if (mostrarMensajeGuardado){
		$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasReasignadasCorrectamente"), "ok");
	}
}

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	$('#reasignarTareasCargaTrabajo_feedback').rup_feedback({
		block : false
	});
	
	$("#reasignarTareasCargaTrabajo_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverATareasCargTrab"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if ($('#dniReasignacionCargTrab').val() !== ""){
						$.rup_messages("msgConfirm", {
	         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	         				OKFunction: function(){
	         					volverATareasDeCargaTrabajo(false);
	         				}
	         			});
					} else {
						volverATareasDeCargaTrabajo(false);
					}
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,id: "reasignarTareasCargTrab"	
				,css: "fa fa-floppy-o"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if ($('#dniReasignacionCargTrab').val() !== ""){
						reasignarTareasCargTrab();
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
	
	$("#buscadorIZOCargTrab").buscador_personas({destino:'I' , multiselect:false, anyo: null, numExp: null, callback: seleccionarPersonaCargaTrabajo});
	
	function seleccionarPersonaCargaTrabajo(obj, personas){
		if(obj!=null && obj.length>0){
			for(var i=0; i<obj.length; i++){
				var persona = obj[i];
				$("#asignadoACargTrab").val(persona.nombreCompleto);
				$("#dniReasignacionCargTrab").val(persona.dni);
				$("#tipoEntidadCargTrab").val(persona.entidad.tipo);
				$("#idEntidadCargTrab").val(persona.entidad.codigo);
				
			}
		}
	}
	
	$("#autoasignarCargTrabLink").on("click", function (event){
		event.preventDefault();
		autoasignarTareasCargaTrabajo();
	});
	
	$("#personalIzoCargTrabLink").on("click", function (event){
		event.preventDefault();
		$("#buscadorIZOCargTrab").buscador_personas("open");
	});
	
});