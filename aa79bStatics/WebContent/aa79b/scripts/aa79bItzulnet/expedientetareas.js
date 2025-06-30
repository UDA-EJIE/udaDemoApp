/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

var tareasSeleccionadas;
var initFormTarea = '';
var documentosSelect = '';
var modificarTareaConsulta = false;
var configTareaProveedor = false;
var ids;
var ordenTarea;

function irAEstudioEstimado() {

	ocultarMenusDesplegables();
	
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/estudioestimado/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaTareasExpediente = $('#divTareasExpedientes').detach();
	   		$("#divCapaDetalle").append(data);
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

function irAReasignacionDeTareas(){
	
	tareasSeleccionadas = $("#tareasExpedientesForm").rup_table('getSelectedRows');
	ocultarMenusDesplegables();
	$('#tareasExpedientes_feedback').rup_feedback('close');
	
	$("#buscadorIZO").remove();
	$("#buscadorIZO").rup_dialog('destroy');
	$("#buscadorPersonas").remove();
	$("#buscadorPersonas").rup_dialog('destroy');
	$("#buscadorPersonasIZO").remove();
	$("#buscadorPersonasIZO").rup_dialog('destroy');
	$("#buscadorLotes").remove();
	$("#buscadorLotes").rup_dialog('destroy');
	
	var idTarea;
	var estadoEjecucion;
	var recursoAsignado;
	var reasignacionValida = true;
	
	for (var i = 0; i < tareasSeleccionadas.length && reasignacionValida; i++) {
		idTarea = tareasSeleccionadas[i];
		estadoEjecucion = $("#tareasExpedientesForm").rup_table("getCol", idTarea, "estadoEjecucion");
		recursoAsignado = $("#tareasExpedientesForm").rup_table("getCol", idTarea, "recursoAsignado");
		codTipoTarea = $("#tareasExpedientesForm").rup_table("getCol", idTarea, "tipoTarea.id015");
		
		if (estadoEjecucion === datosTareas.estadoEjecucion.ejecutada || 
				recursoAsignado === '' || codTipoTarea === datosTareas.tiposTarea.noConformidadProveedor){
			reasignacionValida = false;
		}
		
	}
	
	if (reasignacionValida){
		$.rup_ajax({
		   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/reasignartareas/maint' 
		   	 ,success:function(data, textStatus, jqXHR){
		   		capaTareasExpediente = $('#divTareasExpedientes').detach();
		   		$("#divCapaDetalle").append(data);
		   	 },
		   	 error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
		   	 }
		 });
	} else {
		$.rup_messages("msgAlert", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
			message: $.rup.i18n.app.mensajes.reasignacionIncorrecta
		});
	}
	
}

function irAReordenacionDeTareas(){
	
	ocultarMenusDesplegables();
	$('#tareasExpedientes_feedback').rup_feedback('close');
	
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/reordenartareas/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaTareasExpediente = $('#divTareasExpedientes').detach();
	   		$("#divCapaDetalle").append(data);
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

function preReabrirTarea(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	ocultarMenusDesplegables();
	
	$.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/preReabrirTarea/"+idTarea,
		dataType: 'json',
		cache: false,
		success : function(data) {
			if (data === 1){
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.msgReaperturaTarea"), "error");
			} else {
				var tipoTarea = $("#tareasExpedientesForm").rup_table("getCol", idTarea, "tipoTarea.id015");
				if (estado == datosExpediente.estado.cerrado && (tipoTarea == datosTareas.tiposTarea.entregaClienteTraduccion || tipoTarea == datosTareas.tiposTarea.tradEntregaClienteTraduccion || tipoTarea == datosTareas.tiposTarea.entregaClienteRevision)){
					$.rup_messages("msgConfirm", {
         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
         				message: $.rup.i18nParse($.rup.i18n.app,"mensajes.reaperturaTarea")+' <br/>'+$.rup.i18nParse($.rup.i18n.app,"mensajes.reaperturaTareaEntregaClte")+' <br/>'+$.rup.i18nParse($.rup.i18n.app,"label.deseaContinuar"),
         				OKFunction: function(){
         					irAReabrirTarea();
         				}
         			});
				} else {
					$.rup_messages("msgConfirm", {
         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
         				message: $.rup.i18nParse($.rup.i18n.app,"mensajes.reaperturaTarea")+' <br/>'+$.rup.i18nParse($.rup.i18n.app,"label.deseaContinuar"),
         				OKFunction: function(){
         					irAReabrirTarea();
         				}
         			});
				}
				
			}
			desbloquearPantalla();
		},
		error: function() {
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
			desbloquearPantalla();
		}
	});
	
}

function irAReabrirTarea(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	ocultarMenusDesplegables();
	
	$.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/reabrirTarea/"+idTarea,
		dataType: 'json',
		cache: false,
		success : function(data) {
			if (data===1){
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.msgReaperturaTarea"), "error");
			}else{
				if (data === -1){
					$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
				} else if (data === -2){
					$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
				}
				$("#tareasExpedientesForm").trigger('reloadGrid');
	        	$("#tareasExpedientesForm").rup_table("resetSelection");
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
				//refresco la bitácora
				bitacoraUpdate(false);
				fnDatosPlanificacion();
			}
			desbloquearPantalla();
		},
		error: function() {
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
			desbloquearPantalla();
		}
	});
	
}


function volverADetalleExpediente(){
	$("#divTareasExpedientes").detach();
	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
	$("#divDetalleExpediente").html(capaDetalleExpediente);
	//refresco la bitácora
	bitacoraUpdate(false);
	eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
	if ($('#capaPestanaCompletaAlta').length){
		//está activa la pestaña de datos generales
		$("#tabsExpediente").rup_tabs("loadTab",{
			idTab: "tabsExpediente",
			position: 0
		});
	}else{
		//está activa la pestaña de documentación
		//Comprobar si hay presupuesto visible para ee usuario (puede haber cambiado tras la ejecución de tareas)
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/datosgeneralesexpediente/datosexpediente/"+idExpediente+"/"+anyoExpediente+"/"+origen,
			dataType: "json", 
			cache: false,
			success:function(data){
				esPresupuestoVisibleParaUsuario = data.presupuestoVisibleUsuario;
				esTradosConRequerimiento = data.tradosConRequerimiento;
				tieneTareasEjecutadas = data.tieneTareasEjecutadas;
				$("#tabsExpediente").rup_tabs("loadTab",{
					idTab: "tabsExpediente",
					position: 1
				});
			}
		});
		
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
function addTarea(tareas){
    $.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/addTarea",
		dataType: "json",
		contentType: 'application/json', 
		data: $.toJSON({
	   		"tarea" : tareas,
	   		"documentosSelect" : documentosSelect,
	   		"ts": new Date().getTime()
	   	}),
	   	cache: false,
		success: function (data) {
			
			if (data === 1){
				desbloquearPantalla();
				
				$.rup_messages("msgAlert", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
					message: $.rup.i18n.app.mensajes.expEnCursoPdteProyTrados
				});	
				return false;
			} else if (data === 2) {
				desbloquearPantalla();
				
				$.rup_messages("msgAlert", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
					message: $.rup.i18n.app.mensajes.noAsigTareaExpEnCursoPdteTramitClte
				});	
				return false;
			} else if (data === 3) {
				desbloquearPantalla();
				
				$.rup_messages("msgAlert", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
					message: $.rup.i18n.app.mensajes.expConTareaRevisionExterna
				});	
				return false;
			} else {
				
				$("#tareasExpedientesForm").trigger('reloadGrid');
	        	$("#tareasExpedientesForm").rup_table("resetSelection");
	        	comprobarFechaFinTareasNoSuperaFechaFinExp();
				
				$("#crearConfigurarDialog").rup_dialog('close');
				
				if (data === -1){
					$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
				} else if (data === -2){
					$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
				} else if (data === -3){
					$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.registroCalendarioPersonal"), "error");
				} else {
					$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
				}
				bitacoraUpdate(false);
				fnDatosPlanificacion();
				desbloquearPantalla();
				
				
			}
			
	    },
	    error: function (jqXHR, exception){
	    	$('#crearConfigurar_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
	    	desbloquearPantalla();
	    }
    });
}


function fnCrearConfigurarDialog(){
	$("#crearConfigurarDialog").rup_dialog({
		 type: $.rup.dialog.DIV,
		 title: $.rup.i18n.app.boton.crearConfigurar,
		 autoOpen: false,
		 modal: true,
		 resizable: false,
		 width: 900,
		 buttons: [{
			 	id:"btnGuardar",
			 	text: $.rup.i18nParse($.rup.i18n.app.boton,"guardar"),
				click: function () {
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"), guardarTarea)
				}
			},
			{
				text: $.rup.i18nParse($.rup.i18n.app.boton,"cancelar"),
				click: function () { 
					
					if (initFormTarea !== $("#crearConfigurar_filter_form").rup_form("formSerialize")){
						
						$.rup_messages("msgConfirm", {
             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
             				OKFunction: function(){
             					setTimeout(function() {
             						cerrarModalCrearTarea();
             						}, 50);
             				}
             			});
						
					} else {
						cerrarModalCrearTarea();
					}
					
				},
				btnType: $.rup.dialog.LINK
			}]
		
	
	});
}

function guardarTarea(){
	var tipoTarea = {
		"id015": $('#tipoTarea_detail_table').rup_combo("getRupValue")	
	}
	
	var tipoRevision = {
		"id018": $("#tipoRevision_detail_table").rup_combo("getRupValue")
	}
	
	var entidad = {
		"tipo": $("#tipoEntidad").val(), 
		"codigo": $("#idEntidad").val()	
	}
	
	var personaAsignada = {
		"dni": $("#dniAsignacion").val(),
	    "entidad": entidad	
	}
	
	var lotes = {
		"idLote": $("#idLote").val()
	}
	
	var datosPagoProveedores = {
		"indRecargoFormato": $("#recargaPorFormatoSwitch_tareaConfig").is(':checked') ? datos.activo.si : datos.activo.no,
		"numPalRecargoFormato":	$("#sobreNumPal_tareaConfig").val(),
		"indRecargoApremio": $("#recargoPorSwitch_tareaConfig").is(':checked') ? datos.activo.si : datos.activo.no,
		"porRecargoApremio": $("#porcentajeRecarga_tareaConfig").val(),
		"indPenalizacion": datos.activo.no,
	    "porPenalizacion": 0,
		
	}
	
    var tareas = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente,
        "idTarea": $("#idTarea_detail_table").val(),
        "tipoTarea": tipoTarea,
        "tipoRevision": tipoRevision,
        "indFacturacion": $("#facturable_detail_table").is(':checked') ? datos.activo.si : datos.activo.no,
        "orden": $("#orden_detail_table").val(),
        "fechaIni": $("#fechaInicio_detail_table").val(),
        "horaIni": $("#horaInicio_detail_table").val(),
        "fechaFin": $("#fechaFin_detail_table").val(),
        "horaFin": $("#horaFin_detail_table").val(),
        "horasPrevistas": $("#horaPrevista_detail_table").val(),
        "personaAsignada": personaAsignada,
        "lotes": lotes,
        "observaciones": $("#observaciones_detail_table").val(),
        "recursoAsignacion": $("#recursoAsignacion").val(),
        "datosPagoProveedores": configTareaProveedor ? datosPagoProveedores : null,
		"indReqRevision": $("#revisarTrad_detail_table").is(':checked') ? datos.activo.si : datos.activo.no,
		"indMostrarNotasATrad": $("#mostrarNotas_detail_table").is(':checked') ? datos.activo.si : datos.activo.no,
		"indObligarXliff": $("#obligarXliff_detail_table").is(':checked') ? datos.activo.si : datos.activo.no
    };
	
	if ($("#crearConfigurar_filter_form").valid()){	
		addTarea(tareas);
	} else {
		desbloquearPantalla();
	}
}

function cerrarModalCrearTarea(){
	$("#crearConfigurarDialog").rup_dialog('close');
}

function ocultarMenusDesplegables(){
	$("[id='tareasExpedientes_toolbar##tareas-mbutton-container']").hide();
	$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-container']").hide();
}

function validarCampos(){
	$("#orden_detail_table").rules("add", "required");
	$("#fechaInicio_detail_table").rules("add", "required");
	$("#horaInicio_detail_table").rules("add", "required");
	$("#fechaFin_detail_table").rules("add", "required");
	$("#horaFin_detail_table").rules("add", "required");
}

function fnCrearEjecutarTareaDialog(){
	$("#ejecutarTareaDialog").rup_dialog({
		 type: $.rup.dialog.DIV,
		 title: $.rup.i18n.app.boton.ejecutarTareas,
		 autoOpen: true,
		 modal: true,
		 resizable: false,
		 width: 980
		 ,beforeClose:function(event, ui){
			return comprobarCambiosFormulario();
		}
	});
}

function eliminarTareas(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	ocultarMenusDesplegables();
	
	var selectedRows = $("#tareasExpedientesForm").rup_table('getSelectedRows');
        		
	$.ajax({

        type: 'DELETE' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/removeTareas' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(selectedRows) 
        ,async: false 
        ,success:function(){
        	fnDatosPlanificacion();
        	$("#tareasExpedientesForm").trigger('reloadGrid');
        	$("#tareasExpedientesForm").rup_table("resetSelection");
        	$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasEliminadasCorrectamente"), "ok");
        	bitacoraUpdate(false);
        	desbloquearPantalla();
        }
		,error: function(error){
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
			desbloquearPantalla();
	   	 }
	});
        		
			
	desbloquearPantalla();
	
}

function preeliminarTareas(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	ocultarMenusDesplegables();
	
	var selectedRows = $("#tareasExpedientesForm").rup_table('getSelectedRows');
	var listSelectedIds = "";
	var codTarea;
	
	for (var i = 0; i < selectedRows.length; i++) {
		codTarea = selectedRows[i];
		
		if (i !== 0){
			listSelectedIds += ",";
		}
		
		listSelectedIds += codTarea;
	}
	
	var jsonObject = {"listSelectedIds" : listSelectedIds, "tipoExpediente" : tipoExp};
	
	$.ajax({

        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarTareasAEliminar' 
        ,dataType: 'json' 
        ,data: jsonObject 
        ,async: false 
        ,success:function(data){
        	
        	if (data === 0){
        		
        		$.rup_messages("msgConfirm", {
     				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
     				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
     				OKFunction: function(){
     					eliminarTareas();
     				}
     			});
        		
        	} else if (data === 1) {
        		$.rup_messages("msgAlert", {
        			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
        			message: $.rup.i18n.app.mensajes.eliminacionTareas
        		});
        	} else if (data === 2) {
        		$.rup_messages("msgAlert", {
        			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
        			message: $.rup.i18n.app.mensajes.msgEliminarTareas
        		});
        	}
			
			desbloquearPantalla();
        	
        }
		,error: function(error){
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
	
}

function fnComprobarTareaAsignador(idTarea,idTipoTarea){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	ocultarMenusDesplegables();
	$('#tareasExpedientes_feedback').rup_feedback('close');
	
	$.ajax({

        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarTareaAsignador/'+idTarea+'/'+anyoExpediente+'/'+idExpediente+'/'+idTipoTarea
        ,dataType: 'json' 
        ,async: false 
        ,success:function(data){
        	
        	if (data != null) {
        		if (data === 0) {
            		fnEjecutarTarea(idTipoTarea);
            	} else if (data === 1) {
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.asignacionTarea"), "error");
            	} else if (data === 2) {
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"), "error");
            	} else if (data === 3) {
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.expEnCursoPdteTramClte"), "error");
            	} else {
            		// data === 4
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareaSinAceptar"), "error");
            	}
        	}
        	
        	desbloquearPantalla();
        	
        }
		,error: function(error){
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
	
}

function fnTareaEjecutada(codTarea,codTipoTarea,elOrden,codTareaRel){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	ejecutarTareaConsulta = true;
	ordenTarea = elOrden;
	ocultarMenusDesplegables();
	idTarea = codTarea;
	idTipoTarea = codTipoTarea;
	idTareaRel = codTareaRel;
	fnEjecutarTarea(codTipoTarea);
	desbloquearPantalla();
}

function fnPreComprobarTareaNoEjecutada(codTareaAux, idTipoTarea){
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerEstadoEjecucionTarea/' + anyoExpediente + '/' + idExpediente + '/' + codTareaAux
		,dataType: 'json'  
	   	,success:function(data, textStatus, jqXHR){
			if (data && data.estadoEjecucion){
//				ejecutarTareaConsulta = datosTareas.estadoEjecucion.ejecutada != data.estadoEjecucion;
				// se puede ejecutar si no esta en estado ejecutada
				if (datosTareas.estadoEjecucion.ejecutada != data.estadoEjecucion) {
					ejecutarTareaConsulta = false;
					fnComprobarTareaAsignador(codTareaAux,idTipoTarea)
				} else {
					ejecutarTareaConsulta = true;
					// tarea ejecutada
					//Utilizamos msgConfirm ya que el evento beforeClose de msgAlert no funciona
					$.rup_messages("msgConfirm", { 
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
							message: $.rup.i18n.app.mensajes.ejecucionTareaEjecutada,
							OKFunction : function(){
								$('#tareasExpedientesForm').trigger('reloadGrid');
							}
					});
					//Retocamos msgConfirm para que sea idéntico a msgAlert
					// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
					$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
					// ocultamos el close de la ventana
					$(".ui-dialog-titlebar-close").hide();
					// eliminamos el boton cancelar
					$(".rup-enlaceCancelar").remove();
					// sustituimos el icono del confirm por el de alert
					$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
					return true;
				}
			} else {
				// no se puede ejecutar
				$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"), "error");
				$('#tareasExpedientesForm').trigger('reloadGrid');
				ejecutarTareaConsulta = false;
				return false;
			}
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
}

function fnEjecutarTarea(idTipoTarea){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/ejecutarTarea/'+ idTipoTarea  
	   	,success:function(data, textStatus, jqXHR){
	   		$("#ejecutarTareaDialog").remove();
	   		$("#ejecutarTareaDialog").rup_dialog('destroy');
	   		$("#divTareasExpedientes").append('<div id="ejecutarTareaDialog" style="display:none"></div>');
	   		$("#ejecutarTareaDialog").html(data);
	   	    fnCrearEjecutarTareaDialog();
		   	
	   	    dialogoSoloConsulta(ejecutarTareaConsulta, "ejecutarTareaDialog");
	   	    desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	
}

function fnTareaCorreccionesProv(codTarea){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	idTarea = codTarea;
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/tareacorreccionesprov/maint'  
	   	,success:function(data, textStatus, jqXHR){
	   		$("#ejecutarTareaDialog").remove();
	   		$("#ejecutarTareaDialog").rup_dialog('destroy');
	   		$("#divTareasExpedientes").append('<div id="ejecutarTareaDialog" style="display:none"></div>');
	   		$("#ejecutarTareaDialog").html(data);
	   	    fnCrearEjecutarTareaDialog();
		   	
	   	    dialogoSoloConsulta(ejecutarTareaConsulta, "ejecutarTareaDialog");
	   	    desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	
}

function fnCrearConfTareas(){
	bloquearPantalla();
	
	$("#buscadorIZO").remove();
	$("#buscadorIZO").rup_dialog('destroy');
	$("#buscadorPersonas").remove();
	$("#buscadorPersonas").rup_dialog('destroy');
	$("#buscadorPersonasIZO").remove();
	$("#buscadorPersonasIZO").rup_dialog('destroy');
	$("#buscadorLotes").remove();
	$("#buscadorLotes").rup_dialog('destroy');
	
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/crearconftareas/maint'  
	   	,success:function(data){
	   		$("#crearConfigurarDialog").remove();
	   		$("#crearConfigurarDialog").rup_dialog('destroy');
	   		$("#divTareasExpedientes").append('<div id="crearConfigurarDialog" style="display:none"></div>');
	   		$("#crearConfigurarDialog").html(data);
	   		fnCrearConfigurarDialog();
			// TODO comprobar funcionamiento correcto de tareas
//	   	    desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
}

function fnCrearTareasDefecto(){
	
	var jsonObject = 
	{
		"anyo": anyoExpediente, 
		"numExp": idExpediente
	};
	
	$.ajax({
    	type: "POST",
    	url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/crearTareasDefecto",
    	dataType: "json",
    	contentType: 'application/json',
    	data: $.toJSON(jsonObject),
    	cache: false,
    	success:function(){
    		ocultarMenusDesplegables();
    		fnDatosPlanificacion();
    		$("#tareasExpedientesForm").trigger('reloadGrid');
    		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasCreadas"), "ok");
    		//refresco la bitácora
    		bitacoraUpdate(false);
    		desbloquearPantalla();
        }, 
   	 	error: function(){
   	 		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
   	 		desbloquearPantalla();
   	 	}
    });
	
}

/**
* comprobar si alguna de las fechas fin de las tareas supera la fecha fin de expediente 
 */
function comprobarFechaFinTareasNoSuperaFechaFinExp(){
	var jsonObject = 
	{
		"anyo": anyoExpediente, 
		"numExp": idExpediente
	};
	$.ajax({
    	type: "POST",
    	url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarFechaFinTareasNoSuperaFechaFinExp",
    	dataType: "json",
    	contentType: 'application/json',
    	data: $.toJSON(jsonObject),
    	cache: false,
    	success:function(data){
    		if(data){
				$.rup_messages("msgAlert", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.fechaFinTareasSuperaFechaFinExp
				});
			}
        }, 
   	 	error: function(){
   	 		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
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
	
	$('#tareasExpedientes_feedback').rup_feedback({
		block : false
	});
	
	$('#crearConfigurar_feedback').rup_feedback({
		block : false
	});
	
	bitacoraUpdate(false);
	fnDatosPlanificacion();
	
	$("#tareasExpedientes_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverABusqueda"	
				,css: "fa fa-arrow-left"
				,click : function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			if (typeof(detalleModoConsulta) != "undefined"){
		 				eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
		 				volverADetalleConsulta();
		 			}else{
		 				volverADetalleExpediente();
		 			}
				}
			}
			,{
				i18nCaption: $.rup.i18n.app.boton.tareas
				,id:"tareas"
				,css: "fa fa-list-ul"
				,click: function(event){
					$("[id='tareasExpedientes_toolbar##tareas-mbutton-container']").show();
					$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-container']").hide();
					$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-container']").removeClass('rup-mbutton-open');
					
					if (estado == datosExpediente.estado.cerrado){
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##crearTareaDefecto']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##reordenarTareas']").button("disable");
					} else if (estado == datosExpediente.estado.encurso){
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##crearTareaDefecto']").button("enable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##reordenarTareas']").button("enable");
					}else{
						//Deshabilitamos en el resto de estados
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##crearTareaDefecto']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##reordenarTareas']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##modificarTarea']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##crearConfigurar']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##eliminarTarea']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##reasignarTareas']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##reabrirTarea']").button("disable");
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-group##ejecutarTareas']").button("disable");
					}
				}
				,buttons:[
					{ i18nCaption:$.rup.i18n.app.boton.crearTareaDefecto, 
						css: "fa  fa-file-text",
						id:"crearTareaDefecto"
						,click : function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							if (estado == datosExpediente.estado.encurso && fase == datosExpediente.fase.pdteTram){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.mensajes.noCrearTareaExpEnCursoPdteTramitClte
								});	
							}else{
								fnCrearTareasDefecto();
							}
							
						}
					}
					,{ i18nCaption:$.rup.i18n.app.boton.crearConfigurar, 
						css: "fa fa-file-o",
						id:"crearConfigurar"
						,click : function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							ids = null;
							if (estado == datosExpediente.estado.encurso && fase == datosExpediente.fase.pdteTram){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.mensajes.noCrearTareaExpEnCursoPdteTramitClte
								});	
							}else{
								fnCrearConfTareas();
							}
							
						}
						
					}
					,{ i18nCaption:$.rup.i18n.app.boton.modificarTarea, 
						id:"modificarTarea"
							,css: "fa fa-pencil-square-o"
						,click : function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							ocultarMenusDesplegables();
							if(!$('#tareasExpedientesForm').rup_table("isSelected")){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.comun.warningSeleccion
								});	
								return false;
							} else {
								ids = $("#tareasExpedientesForm").rup_table("getSelectedRows");
								
								if (ids.length === 1){
									//Alex
									fnCrearConfTareas();
								} else {
									$.rup_messages("msgAlert", {
										title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
										message: $.rup.i18n.app.comun.warningSelectOnlyOne
									});	
									return false;
								}								
							}
						}
						
					}
					,{ i18nCaption:$.rup.i18n.app.boton.eliminarTarea, 
						css: "fa fa-trash-o",
						id:"eliminarTarea"
						,click : function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							if(!$('#tareasExpedientesForm').rup_table("isSelected")){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.comun.warningSeleccion
								});	
								return false;
							} else {
								e.preventDefault();
    							e.stopImmediatePropagation();
    							preeliminarTareas();
							}
						}
					}
					,{ i18nCaption:$.rup.i18n.app.boton.reasignarTareas, 
						css:"fa  fa-retweet",
						id:"reasignarTareas"
						,click: function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							if(!$('#tareasExpedientesForm').rup_table("isSelected")){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.comun.warningSeleccion
								});	
								return false;
							} else {
								irAReasignacionDeTareas();
							}
						}
					}
					,{ i18nCaption:$.rup.i18n.app.boton.reordenarTareas, 
						css:"fa fa-sort-numeric-asc",
						id:"reordenarTareas"
						,click: function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							irAReordenacionDeTareas();
						}
					}
					,{ i18nCaption:$.rup.i18n.app.boton.reabrirTarea, 
						css: "fa fa-share-square-o",
						id:"reabrirTarea"
						,click: function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							ocultarMenusDesplegables();
							if(!$('#tareasExpedientesForm').rup_table("isSelected")){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.comun.warningSeleccion
								});	
								return false;
							} else {
								var ids = $("#tareasExpedientesForm").rup_table("getSelectedRows");
								
								if (ids.length === 1){
									idTarea = ids[0];
									var estadoEjecucion = $("#tareasExpedientesForm").rup_table("getCol", idTarea, "estadoEjecucion");
									
									if (estadoEjecucion === datosTareas.estadoEjecucion.ejecutada){
										preReabrirTarea();
										
									} else {
										$.rup_messages("msgAlert", {
											title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
											message: $.rup.i18n.app.mensajes.tareaEjecutada
										});	
										return false;
									}
								} else {
									$.rup_messages("msgAlert", {
										title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
										message: $.rup.i18n.app.comun.warningSelectOnlyOne
									});	
									return false;
								}
								
							}
						}
					}
					,{ i18nCaption:$.rup.i18n.app.boton.ejecutarTareas, 
						css:"fa fa-check-square-o",
						id:"ejecutarTareas"
						,click : function(e){
							e.preventDefault();
							e.stopImmediatePropagation();
							if(!$('#tareasExpedientesForm').rup_table("isSelected")){
								$.rup_messages("msgAlert", {
									title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
									message: $.rup.i18n.app.comun.warningSeleccion
								});	
								return false;
							} else {
								var ids = $("#tareasExpedientesForm").rup_table("getSelectedRows");
								
								if (ids.length === 1){
									var codTarea = ids[0];
									idTarea = parseInt(codTarea);
									idTareaRel = $("#tareasExpedientesForm").rup_table("getCol", codTarea, "idTareaRel");
									idTipoTarea = $("#tareasExpedientesForm").rup_table("getCol", codTarea, "tipoTarea.id015");
									ordenTarea = $("#tareasExpedientesForm").rup_table("getCol", codTarea, "orden");
									fechaHoraFin = $("#tareasExpedientesForm").rup_table("getCol", codTarea, "fechaHoraFin")
									
									fnPreComprobarTareaNoEjecutada(codTarea, idTipoTarea);
								} else {
									$.rup_messages("msgAlert", {
										title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
										message: $.rup.i18n.app.comun.warningSelectOnlyOne
									});	
									return false;
								}
								
							}
						}
					}
				 ]
			}
			,{
				i18nCaption: $.rup.i18n.app.boton.otrasOpciones
				,id:"otrasOpciones"	
					,click: function(event){
						$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-container']").show();
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-container']").hide();
						$("[id='tareasExpedientes_toolbar##tareas-mbutton-container']").removeClass('rup-mbutton-open');
					}
				,buttons:[
					{ i18nCaption:$.rup.i18n.app.boton.estudiarEstimacion, 
						id:"estudiarEstimacion"
						,css: "fa fa-eye"
						,click: function(e){
							 e.preventDefault();
                             e.stopImmediatePropagation();
							 irAEstudioEstimado();
							 $("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-container']").removeClass('rup-mbutton-open');
						}
					}
				]
			}
		]
	});
	
	$("#tareasExpedientesForm").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/tareasExpedientesForm",
		toolbar:{
			id: "tareasExpedientesForm_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
		},
		colNames: [
			""
			,""
			,""
			,""
			,""
			,$.rup.i18n.app.label.tipoTarea
			,$.rup.i18n.app.label.personaAsignada
			,$.rup.i18n.app.label.estadoAsignado
			,$.rup.i18n.app.label.estadoEjecucion
			,$.rup.i18n.app.label.horaPrevista
			,$.rup.i18n.app.label.fechaFin
			,$.rup.i18n.app.label.orden
			
		],
		colModel: [
			{
				name : "idTarea",
				hidden : true
			}
			,{
				name : "tipoTarea.id015",
				hidden : true
			}
			,{
				name : "estadoEjecucion",
				hidden : true
			}
			,{
				name : "recursoAsignacion",
				hidden : true
			}
			,{
				name : "idTareaRel",
				hidden : true
			}
			,{ 	name: $.rup.lang === 'es' ? "tipoTarea.descEs015"
					: "tipoTarea.descEu015", 
			 	label: "label.tipoTarea",
			 	index: $.rup.lang === 'es' ? "TIPOTAREAES" 
			 			: "TIPOTAREAEU",
				align: "left", 
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
			,{
				name : "recursoAsignado", 
			 	label: "label.nombreCompleto",
				align: "left", 
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					var resultado = "";
					if (cellvalue !== null){
						resultado = cellvalue;
						if(rowObject.recursoDisponible != "S"){
							resultado = resultado + ' ' + ' <i class="fa fa-exclamation-circle" aria-hidden="true"></i>';
						}
						if(rowObject.tieneOtrasTareas === "S"){
							resultado = resultado + ' ' + ' <i class="fa fa-info-circle" aria-hidden="true"></i>';
						}
					}
					return  resultado;
					
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
				
			}
			,{ 	name: "estadoAsignadoDesc", 
			 	label: "label.estadoAsignado",
			 	index: "ESTADOASIGNACIONDESC",
				align: "center", 
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
			,{ 	name: "estadoEjecucionDesc", 
			 	label: "label.estadoEjecucion",
			 	index: "ESTADOEJECUCIONDESC",
				align: "center", 
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false, 
				formatter: function (cellvalue, options, rowObject) {
					var resultado;
					if(rowObject.estadoEjecucion == datosTareas.estadoEjecucion.ejecutada){
						// posibilidad de ver pantalla de ejecución para tareas trad, rev y no conformidad proveedor ejecutadas por un proveedor - v3.9.70
						if (rowObject.recursoAsignacion === datosTareas.tipoRecurso.interno
								|| (rowObject.recursoAsignacion === datosTareas.tipoRecurso.externo && 
									(rowObject.tipoTarea.id015 == datosTareas.tiposTarea.traducir) ||
									(rowObject.tipoTarea.id015 == datosTareas.tiposTarea.revisar) || 
									(rowObject.tipoTarea.id015 == datosTareas.tiposTarea.noConformidadProveedor))){
							resultado = "<a href='#' onclick='fnTareaEjecutada(" + rowObject.idTarea + "," + rowObject.tipoTarea.id015 + "," + rowObject.orden + "," + rowObject.idTareaRel + ");' id='estadoEjec_" + rowObject.idTarea + "' class='rup-enlaceCancelar linkRupTable'>" + cellvalue + "</a>";
						} else if (typeof(detalleModoConsulta) == "undefined" && rowObject.tipoTarea.id015 == datosTareas.tiposTarea.interpretar) {
							resultado = "<a href='#' onclick='fnTareaEjecutada(" + rowObject.idTarea + "," + rowObject.tipoTarea.id015 + "," + rowObject.orden + "," + rowObject.idTareaRel + ");' id='estadoEjec_" + rowObject.idTarea + "' class='rup-enlaceCancelar linkRupTable'>" + cellvalue + "</a>";
						} else if (typeof(detalleModoConsulta) == "undefined" && rowObject.tipoTarea.id015 == datosTareas.tiposTarea.notifCorreccionesProv){
							resultado = "<a href='#' onclick='fnTareaCorreccionesProv(" + rowObject.idTarea + ");' id='estadoEjec_" + rowObject.idTarea + "' class='rup-enlaceCancelar linkRupTable'>" + cellvalue + "</a>";
						} else {
							resultado = cellvalue;
						}
					} else {
						resultado = cellvalue;
					}
					return  resultado;
				}
			}
			,{ 	name: "horasPrevistas", 
			 	label: "label.horaPrevista",
			 	index: "HORASPREVISTAS",
				align: "center", 
				width: "50", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					var horasPrevistas = "";
					
					if (cellvalue !== null){
						horasPrevistas = cellvalue +" "+ $.rup.i18n.app.comun.hora;
					}
					
					return horasPrevistas;
				}
			}
			,{ 	name: "fechaHoraFin", 
			 	label: "label.fechaHoraFin",
			 	index: "FECHAFIN",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellvalue, options, rowObject) {
					var resultado = "";
					var fechaFinExp = newDateHourFromString($("#fechaEnteIZO_filter").text());
					if (cellvalue !== ""){
						resultado = newDateHourFromString(cellvalue);
						if(fechaFinExp && resultado.getTime() > fechaFinExp.getTime()){
							return cellvalue + ' ' + ' <i class="fa fa-exclamation-triangle" aria-hidden="true" style="color: red;"></i>';
						}else{
							return cellvalue;
						}
					}
					return  resultado;
					
				}
			}
			,{ 	name: "orden", 
			 	label: "label.oredn",
			 	index: "ORDEN",
				align: "right", 
				width: "30", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
        ],
        model:"Tareas",
        usePlugins:typeof(detalleModoConsulta) != "undefined"?[
        	 "toolbar",
       		 "filter",
       		 "responsive",
       		 "fluid"
         	]
			:["toolbar",
      		 "filter",
       		 "multiselection",
       		 "responsive",
       		 "fluid"],
        primaryKey: ["idTarea"],
        multiselection:{
			headerContextMenu_enabled: false
			},
		loadOnStartUp: false,
		filter:{
			beforeFilter: function(){
				comprobarFechaFinTareasNoSuperaFechaFinExp();
			}
		}
	});
	
	$("[id='tareasExpedientes_toolbar##tareas-mbutton-container']").addClass('centrado');
	$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-container']").addClass('centrado');
	
	$("#linkDetalleExpediente").on('click', function(e){
		var url = "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/"+anyoExpediente+"/"+idExpediente;
        window.open(url);
	});
		
	if (tipoExp === datosExp.tipoExp.interpretacion){
		$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-group']").hide();
	} else {
		$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-group']").show();
	}
	
    $('#anyo_detail_filter_table').val(anyoExpediente);
	$('#numExp_detail_filter_table').val(idExpediente);
	
	$("#tareasExpedientesForm").rup_table('filter');
	if (typeof(detalleModoConsulta) != "undefined"){
		$("[id='tareasExpedientes_toolbar##tareas']").hide();
		$("[id='tareasExpedientes_toolbar##otrasOpciones-mbutton-group']").hide();
	}
	
	
});
