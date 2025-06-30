/*
 * ****************************
 * VARIABLE GLOBALES - INICIO
 * ****************************
 */
var ejecutarTareaConsulta = false;
var idTipoTarea = '';
var accionRechazo = false;
/*
 * ****************************
 * VARIABLE GLOBALES - FIN
 * ****************************
 */

function volverACapaGeneral(){
	if (typeof(formatoPestana) === "undefined"){
		$("#rechazarAsignacionDetalle_dialog").remove();
		$("#rechazarAsignacionDetalle_dialog").rup_dialog('destroy');
		$("#divTareasExpedientes").append('<div id="rechazarAsignacionDetalle_dialog" style="display:none"></div>');
		
		$("#divTareasExpedientes").detach();
		$("#divCargaTrabajoGeneral").html(capaPestGeneral);
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
		comprobarTareasPendientes();
    }else{
    	$("#divPlanificacionCapa").detach();
    	$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
    	$("#divPlanificacionCapa").html(capaPestGeneral);
    	// recargar la tabla de la pestaña de dashboard  de carga de datos si existe, por si se ha ejecutado la tarea
    	$(".ui-jqgrid-btable").trigger('reloadGrid');
    }
	
}

function actualizarBotonesToolbar(estadoAsignacionTarea,estadoEjecucionTarea, dniPersonaAsignada){
	// botones aceptar/rechazar asignacion
	if (estadoAsignacionTarea == estadoAceptacion.value.pdteAceptacion && dniUsuSesion === parseInt(dniPersonaAsignada)){
		// pendiente de asignacion --> habilitamos los dos botones
		$("[id=detalleTareaTrabajo_form_toolbar##aceptar]").prop('disabled', false);
		$("[id=detalleTareaTrabajo_form_toolbar##aceptar]").removeClass('ui-button-disabled ui-state-disabled');
		$("[id=detalleTareaTrabajo_form_toolbar##rechazar]").prop('disabled', false);
		$("[id=detalleTareaTrabajo_form_toolbar##rechazar]").removeClass('ui-button-disabled ui-state-disabled');
	} else {
		// rechazada || aceptada || dni de usuario sesion es diferente al de la persona asignada --> deshabilitamos los dos botones
		$("[id=detalleTareaTrabajo_form_toolbar##aceptar]").prop('disabled', true);
		$("[id=detalleTareaTrabajo_form_toolbar##aceptar]").addClass('ui-button-disabled ui-state-disabled');
		$("[id=detalleTareaTrabajo_form_toolbar##rechazar]").prop('disabled', true);
		$("[id=detalleTareaTrabajo_form_toolbar##rechazar]").addClass('ui-button-disabled ui-state-disabled');
	}
	// boton ejecutar tarea
	if ((estadoAsignacionTarea == estadoAceptacion.value.aceptada &&
			 (estadoEjecucionTarea == estadoEjecucion.value.pdteEjecucion || estadoEjecucionTarea == estadoEjecucion.value.retrasada))  &&
				 dniUsuSesion === parseInt(dniPersonaAsignada)){
		// estado asignacion aceptada, estado ejecucion pendiente o retrasada y dni de usuario de sesion es igual al de la persona
		// asignada --> habilitamos boton de ejecutar tarea
		$("[id=detalleTareaTrabajo_form_toolbar##ejecutar]").prop('disabled', false);
		$("[id=detalleTareaTrabajo_form_toolbar##ejecutar]").removeClass('ui-button-disabled ui-state-disabled');
	} else {
		// si no se cumplen las tres condiciones deshabilitamos boton ejecutar tarea
		$("[id=detalleTareaTrabajo_form_toolbar##ejecutar]").prop('disabled', true);
		$("[id=detalleTareaTrabajo_form_toolbar##ejecutar]").addClass('ui-button-disabled ui-state-disabled');
	}
}

function mostrarDocumentoEntradaTareaTrabajo(){
	$("#documentoTareaEntrada_detail_table").empty();
	if ($('#detTareaTrabajoDocEntradaTareaIdFichero').val()){
		// hay documento de entrada;  lo pintamos
		let encripDocEntrada = $('#detTareaTrabajoDocEntradaTareaEncriptadoFichero').val();
		var filaDocEntrada = '<p class="document-fileAndIcon">';
			filaDocEntrada += ' <a href="#" class="document-eusk iconSameLine descargarDocEntradaTareaTrabajo pl6 noTextDecoration" data-id="'+$('#detTareaTrabajoDocEntradaTareaIdFichero').val()+'">';
			filaDocEntrada += ''+$('#detTareaTrabajoDocEntradaTareaNombreFichero').val()+'</a>';	
			filaDocEntrada+= ' <span class="ico-ficha-encriptado" title="'+ ((encripDocEntrada === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((encripDocEntrada === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';	
			filaDocEntrada+= '</p>';	
		$("#documentoTareaEntrada_detail_table").append(filaDocEntrada);	
		funcionalidadDescargaDocumentoEntrada();
	}
}

function mostrarDocumentoSalidaTareaTrabajo(){
	$("#documentoTareaSalida_detail_table").empty();
	if ($('#detTareaTrabajoDocSalidaTareaIdFichero').val()){
		// hay documento de salida;  lo pintamos
		let encripDocSalida = $('#detTareaTrabajoDocSalidaTareaEncriptadoFichero').val();
		var filaDocSalida = '<p class="document-fileAndIcon">';
			filaDocSalida += ' <a href="#" class="document-eusk iconSameLine descargarDocSalidaTareaTrabajo pl6 noTextDecoration" data-id="'+$('#detTareaTrabajoDocSalidaTareaIdFichero').val()+'">';
			filaDocSalida += ''+$('#detTareaTrabajoDocSalidaTareaNombreFichero').val()+'</a>';	
			filaDocSalida+= ' <span class="ico-ficha-encriptado" title="'+ ((encripDocSalida === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((encripDocSalida === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';	
			filaDocSalida+= '</p>';	
		$("#documentoTareaSalida_detail_table").append(filaDocSalida);	
		funcionalidadDescargaDocumentoSalida();
	}
}

function inicializarCamposFormulario(){
	$("form#formDetalleTareaTrabajo input, form#formDetalleTareaTrabajo textarea").each(function(){
		$(this).addClass("form-control input-modo-consulta");
		$(this).attr('disabled', 'disabled');
	});
	// comprobamos si la tarea tiene documento de entrada y de salida
	mostrarDocumentoEntradaTareaTrabajo();
	mostrarDocumentoSalidaTareaTrabajo();
	
	// comprobaciones para habilitar/deshabilitar botones de toolbar
	actualizarBotonesToolbar($('#detTareaTrabajoEstadoAsignacion').val(), $('#detTareaTrabajoEstadoEjecucion').val(), $('#detTareaTrabajoDniAsignada').val());
}

function actualizarDatosTareaEnPantalla(){
	$.ajax({
	  	 type: 'GET'
	  	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/findTareaTrabajo/'+idTarea
		 ,dataType: 'json'
		 ,contentType: 'application/json' 	
	     ,async: false
	     ,cache: false
	  	 ,success:function(data){
	  		 if (data){
				let estadoAsignadoAux = data.estadoAsignado;
				let estadoEjecucionAux = data.estadoEjecucion;
				// actualizar botones toolbar
				actualizarBotonesToolbar(estadoAsignadoAux, estadoEjecucionAux, data.personaAsignada.dni);
				$('#detTareaTrabajoEstadoAsignacion').val(estadoAsignadoAux);
				$('#estadoAceptacion_detail_table').val(data.estadoAsignadoDesc);
				$('#detTareaTrabajoEstadoEjecucion').val(estadoEjecucionAux);
				$('#estadoEjecucion_detail_table').val(data.estadoEjecucionDesc);
				// si asignacion rechazada mostrar observaciones de rechazo
				if (estadoAceptacion.value.rechazada === estadoAsignadoAux){
					$('#motivoRechazo_detail_table').val('');
					$('#motivoRechazo_detail_table').val(data.obsvrechazo);
					$('#motivoRechazoDiv').show();
				}
				// si tarea ejecutada mostramos datos de ejecucion de tarea
				if (estadoEjecucionAux == estadoEjecucion.value.ejecutada ){
					// mostramos las observaciones de ejecucion
					$('#observEjecucion_detail_table').val(data.observacionesTareas.obsvEjecucion);
					if (data.documentoSalida && data.documentoSalida.idFichero){
						// si hay documento de salida lo mostramos
						$('#detTareaTrabajoDocSalidaTareaIdFichero').val(data.documentoSalida.idFichero);
						$('#detTareaTrabajoDocSalidaTareaEncriptadoFichero').val(data.documentoSalida.encriptado);
						$('#detTareaTrabajoDocSalidaTareaNombreFichero').val(data.documentoSalida.nombre);
						mostrarDocumentoSalidaTareaTrabajo();
					}
					$('#datosEjecTareaTrabajoDiv').show();
				}
				
			} else {
				mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			}
			 desbloquearPantalla();
	  	 }
	});
}

function mostrarDocumentoEntradaTabla(cellval, opts, rowObject){
	var celda =	'';
	if (rowObject.documentoEntrada && rowObject.documentoEntrada.idFichero){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocTareaTrabajo('+ rowObject.documentoEntrada.idFichero +')" class="document-cast iconSameLine noTextDecoration" >';
		celda+= rowObject.documentoEntrada.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoEntrada.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoEntrada.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	return celda;
}

function mostrarDocumentoSalidaTabla(cellval, opts, rowObject){
	var celda =	'';
	if (rowObject.documentoSalida && rowObject.documentoSalida.idFichero){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocTareaTrabajo('+ rowObject.documentoSalida.idFichero +')" class="document-cast iconSameLine noTextDecoration" >';
		celda+= rowObject.documentoSalida.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoSalida.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoSalida.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	return celda;
}

function comprobarEstadoTareaTrabajo(){
	$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoTareaTrabajo/' + idTarea,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){	
				if (data){
					if (accionRechazo){
						detalle = true;
		                selectedIdTarea = idTarea;
		                $("#rechazarAsignacionDetalleTareaTrabajo_dialog").rup_dialog('open');
					} else{
						$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
							message: $.rup.i18nParse($.rup.i18n.app,"mensajes.aceptarAsignacion"),
							OKFunction: function(){
								bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
								aceptarAsignacionDetalleTareaTrabajo(estadoAceptacion.value.aceptada);
							}
						});
					}
	            } else {
	             	$.rup_messages("msgAlert", {
	        	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
					});	
	            }
				desbloquearPantalla();
			 }
   		 });   
}

function aceptarAsignacionDetalleTareaTrabajo(estado){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/aceptarAsignacionTareaTrabajo/'+idTarea+'/'+estado,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	 
			desbloquearPantalla();
			mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18n.app.mensajes.aceptadaLaAsignacion, "ok");
			actualizarDatosTareaEnPantalla();
		}
	});
}

function inicializarToolbar(){
	$("#detalleTareaTrabajo_form_toolbar").rup_toolbar({
            buttons:[
                    {		id : "volver",    
                            i18nCaption: $.rup.i18n.app.boton.volver
                            ,css: "fa fa-arrow-left"
                            ,index: 1
                            ,right: false
                            ,click : 
                            function(e){
                                    e.preventDefault();
                                    e.stopImmediatePropagation();
                                    if(consultaPlanif){
                                    	volverADesgloseTareasConsultaPlanif();
                                    } else {
                                    	volverACapaGeneralDesdeTareaTrabajo();
                                    }
                    		}
                    },
                    {
                    	id : "aceptar" 
                    	,i18nCaption: $.rup.i18n.app.boton.aceptarAsignacion
                        ,css: "fa fa-check"
                        ,index: 1
                        ,disabled: true
                        ,click : 
                            function(e){
                        		e.preventDefault();
                                e.stopImmediatePropagation();
                                accionRechazo = false;
                                comprobarEstadoTareaTrabajo();
                        }
                    },
                    {
                    	id : "rechazar",    
                    	i18nCaption: $.rup.i18n.app.boton.rechazarAsignacion
                        ,css: "fa fa-times"
                        ,index: 1
            		    ,right: false
                        ,click : 
                            function(e){
                        		e.preventDefault();
                                e.stopImmediatePropagation();
                                accionRechazo = true;
                                comprobarEstadoTareaTrabajo();
                        }
                    },
                    {
                    	id : "ejecutar",    
                    	i18nCaption: $.rup.i18n.app.boton.ejecutarTareas
                            ,css: "fa fa-check-square-o"
                        ,click : 
                            function(e){
                        		e.preventDefault();
                                e.stopImmediatePropagation();
                                var codTarea = $('#detTareaTrabajoIdTarea').val();
								idTarea = parseInt(codTarea);
								idTipoTarea = $("#detTareaTrabajoIdTipoTarea").val();
								if (comprobarTareaTrabajoEjecutada(idTarea, $('#detTareaTrabajoIdTrabajo').val())) {
									fnComprobarTareaTrabajoAsignador($('#detTareaTrabajoIdTrabajo').val(), idTipoTarea)
								}
                        }
                    }
        ]
    });
}

function inicializarTabla(){
	// volcamos datos de tarea a filtros de tabla
	$('#idTarea_filter_table').val($('#detTareaTrabajoIdTarea').val());
	$('#idTrabajo_filter_table').val($('#detTareaTrabajoIdTrabajo').val());
	$('#orden_filter_table').val($('#ordenTarea_detail_table').val());
	$("#detalleTareaTrabajo").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDependientesTareaTrabajo",
		colNames: [
			$.rup.i18n.app.label.tipoTarea,
			$.rup.i18n.app.comun.asignadaA,
			$.rup.i18n.app.label.fechaFinTarea,
			$.rup.i18n.app.label.estadoEjecucion,
			$.rup.i18n.app.label.documentoEntrada,
			$.rup.i18n.app.label.observacionesEjecucion,
			$.rup.i18n.app.label.documentoSalida
		],
		colModel: [
				{ 	name: $.rup.lang === 'es' ? "tipoTarea.descEs015":"tipoTarea.descEu015", 
				 	label: "comun.tipoDeTarea",
					align: "left", 
					width: "90", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "personaAsignada.nombreCompletoSinComas", 
				 	label: "comun.asignadaA",
					align: "left", 
					width: "80", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "fechaHoraFin", 
				 	label: "comun.fechaFinTarea",
					align: "left", 
					width: "60", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "estadoEjecucionDesc", 
				 	label: "label.estadoEjecucion",
					align: "left", 
					width: "40", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "docFinalConcatenado", 
					align: "left", 
					width: "300",
					editable: false, 
					fixed: true,  
					hidden: false, 
					resizable: true, 
					sortable: false,
					formatter: function (cellval, opts, rowObject, action) {
						return mostrarDocumentoEntradaTabla(cellval, opts, rowObject);
					}
				},
				{ 	name: "observacionesTareas.obsvEjecucion", 
					align: "left", 
					width: "300",
					editable: false, 
					fixed: true,  
					hidden: false, 
					resizable: true, 
					sortable: false,
					formatter: function (cellval, opts, rowObject, action) {
						if (tareaEjecutada === rowObject.estadoEjecucion && cellval){
							return cellval;
						}
						return "";
					}
				},
				{ 	name: "docFinalConcatenado", 
					align: "left", 
					width: "300",
					editable: false, 
					fixed: true,  
					hidden: false, 
					resizable: true, 
					sortable: false,
					formatter: function (cellval, opts, rowObject, action) {
						if (tareaEjecutada === rowObject.estadoEjecucion){
							return mostrarDocumentoSalidaTabla(cellval, opts, rowObject);
						}
						return "";
						
					}
				}
        ],
        model:"TareasTrabajo",
        usePlugins:[
       		 "fluid",
       		 "responsive",
       		 "filter"
        ],
        loadComplete: function(){ 
        	$("#detalleTareaTrabajo_pager").hide();
        },
        sortname:"ORDEN",
        sortorder:"ASC",
        primaryKey: "idTarea",
		loadOnStartUp: true,
		multiselect:false
	});
}

function volverACapaGeneralDesdeTareaTrabajo(){
	if (typeof(formatoPestana) === "undefined"){
		$("#rechazarAsignacionDetalleTareaTrabajo_dialog").remove();
		$("#rechazarAsignacionDetalleTareaTrabajo_dialog").rup_dialog('destroy');
		$("#divTareasExpedientes").append('<div id="rechazarAsignacionDetalleTareaTrabajo_dialog" style="display:none"></div>');
		
		$("#divTareasExpedientes").detach();
		$("#divCargaTrabajoGeneral").html(capaPestGeneral);
		$("#busquedaGeneralTrabajo").trigger('reloadGrid');
		comprobarTareasPendientes();
    }else{
    	$("#divPlanificacionCapa").detach();
    	$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
    	$("#divPlanificacionCapa").html(capaPestGeneral);
    	// recargar la tabla de la pestaña de dashboard  de carga de datos si existe, por si se ha ejecutado la tarea
    	$(".ui-jqgrid-btable").trigger('reloadGrid');
    }
}

function funcionalidadDescargaDocumentoEntrada(){
	$('a.descargarDocEntradaTareaTrabajo').click(function(event){
		event.preventDefault();	
		var elIdDoc =  $(this).data("id");
		descargarDocTareaTrabajo(elIdDoc);
	});
}
function funcionalidadDescargaDocumentoSalida(){
	$('a.descargarDocSalidaTareaTrabajo').click(function(event){
		event.preventDefault();	
		var elIdDoc =  $(this).data("id");
		descargarDocTareaTrabajo(elIdDoc);
	});
}
function descargarDocTareaTrabajo(elIdDoc){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/2/'+elIdDoc);
}

function fnComprobarTareaTrabajoAsignador(idTrabajo, idTipoTarea){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	var d = new Date();
	$.ajax({
        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarTareaTrabajoAsignador/'+idTarea+'/'+idTrabajo+'/'+idTipoTarea+'?d='+d.getTime()
        ,dataType: 'json' 
        ,async: false
        ,cache: false
        ,success:function(data){
        	if (data != null) {
        		if (data === 0) {
            		fnEjecutarTareaTrabajo(idTipoTarea);
            	} else if (data === 1) {
					mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.asignacionTarea"), "error");
            	} else if (data === 2) {
					mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"), "error");
            	} else {
					mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.asignacionTareaAceptada"), "error");
            	}
        	}
        	
        	desbloquearPantalla();
        	
        }
		,error: function(error){
			mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
}
function fnEjecutarTareaTrabajo(idTipoTarea){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/ejecutarTareaTrabajo/' + idTarea + '/' + idTipoTarea
	   	,success:function(data, textStatus, jqXHR){
	   		$("#ejecutarTareaTrabajoDialog").remove();
	   		$("#ejecutarTareaTrabajoDialog").rup_dialog('destroy');
	   		$("#divTareasExpedientes").append('<div id="ejecutarTareaTrabajoDialog" style="display:none"></div>');
	   		$("#ejecutarTareaTrabajoDialog").html(data);
	   	    fnCrearEjecutarTareaDialog();
	   	    
	   	    dialogoSoloConsulta(ejecutarTareaConsulta, "ejecutarTareaTrabajoDialog");
	   	 	desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	
}

function rechazarAsignacionTareaTrabajo(estado, motivo){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/rechazarAsignacionTareaTrabajo/'+selectedIdTarea+'/'+estado+'/'+motivo,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	
			if(detalle){
				volverACapaGeneral();
			} else {
				actualizarDatosTareaEnPantalla();
				mostrarMensajeFeedback("detalleTareaTrabajo_feedback", $.rup.i18n.app.mensajes.rechazadaLaAsignacion, "ok");
			}
		}
	});
}

function fnCrearEjecutarTareaDialog(){
    $("#ejecutarTareaTrabajoDialog").rup_dialog({
		 type: $.rup.dialog.DIV,
		 title: $.rup.i18n.app.boton.ejecutarTareas,
		 autoOpen: true,
		 modal: true,
		 resizable: false,
		 width: 950
		 ,beforeClose:function(event, ui){
			// TODO
//			findTarea();			
//			return comprobarCambiosFormulario();
		}
	});
}

function inicializarDialogRechazarAsignacion(){
	$("#rechazarAsignacionDetalleTareaTrabajo_dialog").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: false,
	    width: "900",
	    title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
	    buttons: [{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"guardar"),
			click: function () {
				if($("#rechazarAsignacionDetalleTareaTrabajo_form").valid()){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					rechazarAsignacionTareaTrabajo(estadoAceptacion.value.rechazada, $("textarea#motivoRechazoDetalleTareaTrabajo_detail_table").val());
					desbloquearPantalla();
					$("textarea#motivoRechazoDetalleTareaTrabajo_detail_table").val("");
					$("#rechazarAsignacionDetalleTareaTrabajo_dialog").rup_dialog('close');
				}
			}
		},
		{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"cancelar"),
			click: function () { 
				$("textarea#motivoRechazoDetalleTareaTrabajo_detail_table").val("");
				$("#rechazarAsignacionDetalleTareaTrabajo_dialog").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$("#rechazarAsignacionDetalleTareaTrabajo_dialog").validate().resetForm();
		},
		close: function(event, ui) {
			$("#rechazarAsignacionDetalleTareaTrabajo_dialog").validate().resetForm();
		}
	});
}

jQuery(function($){
	$('#detalleTareaTrabajo_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	inicializarToolbar();
	inicializarCamposFormulario();
	inicializarTabla();
	inicializarDialogRechazarAsignacion();
});

