/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */
var comboCount = 0;
var expedientesSeleccionados = [];
var palTrados;
var tipoExp = "";
var tipoExpNum = ""; 
var numPalDesde;
var numPalHasta;
var gruposTrabajoResponsableUsuario = [];
var cargaInicialComboTipoExpediente;
var tablaCreada = false;
var camposReport = '<ul><li><input type="checkbox" checked="checked" id="7" value="prioridadDescEu" name="Le">Le</li><li><input type="checkbox" checked="checked" id="8" value="anyoNumExpConcatenado" name="Esp.-zk.">Esp.-zk.</li><li><input type="checkbox" checked="checked" id="9" value="tipoExpedienteDescEu" name="Mota">Mota</li><li><input type="checkbox" checked="checked" id="10" value="expedienteTradRev.numTotalPalConTramosPerfectMatch" name="Hitz-kopurua (IZO)">Hitz-kopurua (IZO)</li><li><input type="checkbox" checked="checked" id="12" value="vipEntidadGestorEu" name="Espediente-kudeatzailea.">Espediente-kudeatzailea.</li><li><input type="checkbox" checked="checked" id="13" value="grupoTrabajo" name="Lantaldea">Lantaldea</li><li><input type="checkbox" checked="checked" id="14" value="lotes.nombreLote" name="Sorta">Sorta</li><li><input type="checkbox" checked="checked" id="15" value="responsable" name="Arduraduna">Arduraduna</li><li><input type="checkbox" checked="checked" id="16" value="expedienteTradRev.fechaHoraFinalIZO" name="IZO entrega<br>Data/Ordua">IZO entrega<br>Data/Ordua</li><li><input type="checkbox" checked="checked" id="22" value="fechaHoraPrevistaInicio" name="Aurreikusitako hasiera-data/ordua">Aurreikusitako hasiera-data/ordua</li><li><input type="checkbox" checked="checked" id="17" value="fechaHoraPrevistaEntrega" name="Aurreikusitako amaiera-data/ordua">Aurreikusitako amaiera-data/ordua</li><li><input type="checkbox" checked="checked" id="19" value="tecnico.nombreCompleto" name="Administratiboa">Administratiboa</li><li><input type="checkbox" checked="checked" id="20" value="estadoTareasAsignadasEu" name="Esleitutako atazen egoera">Esleitutako atazen egoera</li><li><input type="checkbox" checked="checked" id="21" value="asignador.nombreCompleto" name="Nork esleitua">Nork esleitua</li><li><input type="checkbox" checked="checked" id="23" value="expedienteTradRev.reqPresupuestoDescEu" name="Aurrekontua behar du">Aurrekontua behar du</li><li><input type="checkbox" checked="checked" id="24" value="expedienteTradRev.reqProyectoTradosDescEu" name="Datuak behar dira">Datuak behar dira</li><li><input type="checkbox" checked="checked" id="25" value="titulo" name="Izenburua">Izenburua</li></ul>';
var aDetalle = false;
var filterFormObjectPestBusq;
var listIdObjects = [];
var codSolicitanteBOE = 0;

function ocultarColumnasReport(columnIds){
	var columnas = columnIds.split(',');
	for(var i=0;i<columnas.length;i++){
		$("ul[id^='busquedaGeneral_toolbar'][id $='report_busquedaGeneral-mbutton-container'] input[id='"+columnas[i]+"']").parent().attr('hidden','hidden');
		$("ul[id^='busquedaGeneral_toolbar'][id $='report_busquedaGeneral-mbutton-container'] input[id='"+columnas[i]+"']").attr('checked',false);
	}
}

function mostrarLeyendasAyuda(mostrar){
	if(mostrar){
		$('#leyendaUrgente').show();
		$('#leyendaFechaRechazada').show();
		$('#leyendaPrioridad').show();
		$('#leyendaPlanificacion').show();
		$('#leyendaVacia').show();
	}else{
		$('#leyendaUrgente').hide();
		$('#leyendaFechaRechazada').hide();
		$('#leyendaPrioridad').hide();
		$('#leyendaPlanificacion').hide();
		$('#leyendaVacia').hide();
	}
}

function comprobarEstadoPresupuestoCerrado(){
	var valorEstado = $('#presupuesto_filter_table').rup_combo("getRupValue");
	if(valorEstado && "3" == valorEstado){
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.comun.warningExpAnulados
		});
		$('#presupuesto_filter_table').rup_combo("select","");
	}
	
}

function resetearCampos(){
	//combo tipoExpediente
	if(cargaInicialComboTipoExpediente>0){
		//si el resultado es mayor que 0 significa que el/los grupos de trabajo de los que es responsable es/son solo de expedientes de tipo interpretacion
		$('#idTipoExpediente_filter_table').rup_combo("select","I");
	 }else{
		 $('#idTipoExpediente_filter_table').rup_combo("select","T"); 
	 }
	
	$('#anyo_filter_table').val("");
	$('#numExp_filter_table').val("");
	$('#fases_filter_table').rup_combo("clear");
	$("#fases_filter_table").rup_combo("select", [busqGen.faseExp.pdteTramCliente,busqGen.faseExp.pdteEjectTareas,busqGen.faseExp.pdteTrados]);
	$('#prioridad_filter_table').rup_combo("select","");
	$('#gestorVIP_filter_table').rup_combo("select","");
	$('#grupotrabajo_filter_table').rup_combo("clear");
	$('#asignador_filter_table').rup_combo("select","");
	$('#numPalabrasDesde_filter').val("");
	$('#numPalabrasHasta_filter').val("");
	$('#fechaEntregaDesde_filter').val("");
	$('#fechaEntregaHasta_filter').val("");
	$('#requierePresupuesto_filter_table').rup_combo("select","");
	$('#requiereNegociacion_filter_table').rup_combo("select","");
	$('#tipoTareaBG_detail_table').rup_combo("select","");
	$('#estadoAceptTareaPestBusq_detail_table').rup_combo("select","");
	$('#estadoEjecTarea_detail_table').rup_combo("select","");
	$('#fechaPlanTareaDesde_filter').val("");
	$('#fechaPlanTareaHasta_filter').val("");
    $('#busquedaGeneral_multifilter_combo_label').val("");
    $('#busquedaGeneral_multifilter_dropdownDialog_feedback_closeDiv').click();
	$('#asignador_IZO_filter_table').rup_combo("select","");
	$('#loteAsignado_detail_table').rup_combo("select","");
	numPalDesde = 0;
	numPalHasta = 999999;
	$.validator.addMethod("rangoDesde", function(){
		return true;
	},"rangoNoValidoDesde");
	$.validator.addMethod("rangoHasta", function(){
		return true;
	},"rangoNoValidoHasta");
	$('#palTrados').val(palTrados);	// cambiado por palTrados
	
	$('#BOPV_filter_table').rup_combo("select", "");
	seleccionarGruposTrabajoUsuario(true);
}

function irADesgloseTareas(){
	volcarListaIdsAExpSeleccionados("-");
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/desglosetareas/maint');
}

function irAReasignarTecnicoPestBusqueda(){
	volcarListaIdsAExpSeleccionados("-");
	bloquearPantalla();
	jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/comprobarEstadoExpedientesEnCurso",
		dataType: "json",
		contentType: 'application/json', 
        data: $.toJSON(expedientesSeleccionados),
		cache: false,
		success: function (data) {
           if(data){
        	   $.rup_messages("msgAlert", {
	       			title: $.rup.i18n.app.label.aviso,
	       			message: $.rup.i18n.app.comun.warningExpCerrados
       			});
        	   desbloquearPantalla();
        	   return false;
           }else{
        	   cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/reasignartecnicopestbusqueda/maint');   
           }
	     },
	     error: function (){
	    	desbloquearPantalla();
			alert($.rup.i18n.app.mensajes.errorComprobarEstadoExpedientesSeleccionadosEnCurso + expedientesSeleccionados);
	     }
	});
}

function descargarIcs(){
	volcarListaIdsAExpSeleccionados("-");
	window.location.href = '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/descargarCalendarioICS/'+expedientesSeleccionados+'/'+tipoExpNum;
	desbloquearPantalla();
}

function irDetalleExpedientes(elAnyo, elNumExp, elEstado, laFase){
	anyoExpediente = elAnyo;
	idExpediente = elNumExp;
	estado = elEstado;
	fase = laFase;
	pestDatos = false;
	aDetalle = true;
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/detalleexpediente/maint');
}

function cambiarCapaGeneral(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#divPlanificacionExpedientesGeneral').detach();
	   		 $("#divPlanificacionCapa").html(data);
	   		
	   		if (!aDetalle){
	   			desbloquearPantalla();
	   		}else{
	   			aDetalle = false;
	   		}
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
	   		aDetalle = false;
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
}


/**
 * deshabilita/habilita campos de filtros relacionados con Traduccion/Revision en funcion
 * de lo que se elija en el combo
 */
function actualizarCamposFiltro(){
	var comboValue = $('#idTipoExpediente_filter_table').rup_combo("getRupValue");
	
	if("I".localeCompare(comboValue)==0){
		seleccionInterpretacion();
	} else if ("T".localeCompare(comboValue)==0) {
		$('#numPalabrasDesde_filter').val("");
		$('#numPalabrasHasta_filter').val("");
		seleccionTradRev("T");
	} else if ("R".localeCompare(comboValue)==0) {
		$('#numPalabrasDesde_filter').val("");
		$('#numPalabrasHasta_filter').val("");
		seleccionTradRev("R");
	} else {
		seleccionTradRev("IT&BE");
	}
}

/**
 * Cambiar prioridad de expediente
 * @param anyoAux
 * @param numExpAux
 * @param prioridadAux
 * @returns
 */
function cambiarPrioridad(anyoAux, numExpAux, object){
	bloquearPantalla();
	$.rup_ajax({
		type: "POST"
		,dataType: "json"
     	 ,contentType: 'application/json'
		,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/cambiarPrioridadExpediente/'+anyoAux+'/'+numExpAux+''
		,success:function(data, textStatus, jqXHR){
			//modificar estilo estrella
			if('N'.localeCompare(data)==0){
				$("#busquedaGeneral").rup_table("setCell",anyoAux+","+numExpAux,"prioridad",'N');
				$("#busquedaGeneral").rup_table("setCell",anyoAux+","+numExpAux,"prioridadDescEu",$.rup.i18n.app.comun.no);
				object.classList.remove("fa-star");
				object.classList.add("fa-star-o");
				object.classList.remove("rojoImportant");
			}else{
				$("#busquedaGeneral").rup_table("setCell",anyoAux+","+numExpAux,"prioridad",'S');
				$("#busquedaGeneral").rup_table("setCell",anyoAux+","+numExpAux,"prioridadDescEu",$.rup.i18n.app.comun.si);
				object.classList.remove("fa-star-o");
				object.classList.add("fa-star");
				object.classList.add("rojoImportant");
			}
			
			desbloquearPantalla();
			
		}
		,error:function(XMLHttpRequest, textStatus, errorThrown){
			desbloquearPantalla();
			alert($.rup.i18n.app.mensajes.errorCambioPrioridadExp + anyoAux + ',' + numExpAux);
		}
	});
	
}

/**
 * comprueba los valores del rango de numero de palabras para validarlos correctamente
 * @returns
 */
function comprobarRangoNumPalabras(){
	if($('#numPalabrasHasta_filter').val()){
		numPalHasta = parseInt($('#numPalabrasHasta_filter').val());
	}else{
		numPalHasta = 999999;
	}
	if($('#numPalabrasDesde_filter').val()){
		numPalDesde = parseInt($('#numPalabrasDesde_filter').val());
	}else{
		numPalDesde = 0;
	}
	$.validator.addMethod("rangoDesde", function(){
		if($('#numPalabrasDesde_filter').val()){
			return ($('#numPalabrasDesde_filter').val()<=numPalHasta);
		}else{
			return true;
		}
		},$.rup.i18n.app.mensajes.valorCampoMenorOIgual + numPalHasta);
	$.validator.addMethod("rangoHasta", function(){
		if($('#numPalabrasHasta_filter').val()){
			return ($('#numPalabrasHasta_filter').val()>=numPalHasta);
		}else{
			return true;
		}
	},$.rup.i18n.app.mensajes.valorCampoMayorOIgual + numPalDesde);
	
}

/**
 * deshabilitar campos Trad/Rev
 */
function seleccionInterpretacion(){
	
	$('#numPalabrasDesde_filter').val("");
	$('#numPalabrasDesde_filter')[0].disabled=true;
	$('#numPalabrasHasta_filter').val("");
	$('#numPalabrasHasta_filter')[0].disabled=true;
	$('#fechaEntregaDesde_filter').rup_date("setRupValue","");
	$('#fechaEntregaHasta_filter').rup_date("setRupValue","");
	$('#fechaEntregaDesde_filter').rup_date("disable");
	$('#fechaEntregaHasta_filter').rup_date("disable");
	$('#BOPV_filter_table').rup_combo("setRupValue", "");
	$('#BOPV_filter_table').rup_combo("disable");
	fnCrearTipoTareaComboBG("I");
	
}

/**
 * habilitar campos Trad/Rev
 */
function seleccionTradRev(tipo){
	
	$('#numPalabrasDesde_filter')[0].disabled=false;
	$('#numPalabrasHasta_filter')[0].disabled=false;
	$('#fechaEntregaDesde_filter').rup_date("enable");
	$('#fechaEntregaHasta_filter').rup_date("enable");
	if($('#BOPV_filter_table').rup_combo("isDisabled")){
		$('#BOPV_filter_table').rup_combo("enable");
	}
	fnCrearTipoTareaComboBG(tipo);
}

/**
 * 
 * carga de datos incial de pantalla
 */
function cargaInicialDatosPestBusqueda(){
	$.rup_ajax({
		type: 'GET'
		,dataType: "json"
		,contentType: 'application/json'
	   	,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/cargaInicialDatosPestBusqueda' 
	   	,success:function(data, textStatus, jqXHR){
	   		cargaInicialComboTipoExpediente = data[0]; 
	   		if(cargaInicialComboTipoExpediente > 0){
//	   			 si el resultado es mayor que 0 significa que el/los grupos de trabajo de los que es responsable es/son solo de expedientes de tipo interpretacion
	   			$('#idTipoExpediente_filter_table').rup_combo("select","I");
	   		 }else{
	   			$('#idTipoExpediente_filter_table').rup_combo("select","T"); 
	   		 }
	   		palTrados = data[1];		//  palTrados
	   		numPalDesde = 0;
	   		numPalHasta = 999999;
	   		$.validator.addMethod("rangoDesde", function(){
	   			return true;
	   		},"rangoNoValidoDesde");
	   		$.validator.addMethod("rangoHasta", function(){
	   			return true;
	   		},"rangoNoValidoHasta");
	   		$('#palTrados').val(palTrados); 		//  cambiado por palTrados
	   		//cargamos el combo de grupos de trabajo desde aqui para asegurarnos de que ha cargado datos de inicio
	   		fncGrupoTrabajoCombo();
	   		codSolicitanteBOE = data[2];

	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorObtenerGruposTrabajoInterUsuario, "error");
	   	}
	 });
}

/**
 * reestablece los campos deshabilitados por la eleccion de un grupo de trabajo
 */
function limpiarCamposSegunGrupoTrabajo(){
	//tipo de expediente
	$('#idTipoExpediente_filter_table').rup_combo("enable");
	
	var comboValue = $('#idTipoExpediente_filter_table').rup_combo("getRupValue");
	if("I".localeCompare(comboValue)==0){//interpretacion
		seleccionInterpretacion();
	} else if ("T".localeCompare(comboValue)==0) {
		$('#numPalabrasDesde_filter').val("");
		$('#numPalabrasHasta_filter').val("");
		seleccionTradRev("T");
	} else if ("R".localeCompare(comboValue)==0) {
		$('#numPalabrasDesde_filter').val("");
		$('#numPalabrasHasta_filter').val("");
		seleccionTradRev("R");
	} else {
		$('#numPalabrasDesde_filter').val("");
		$('#numPalabrasHasta_filter').val("");
		seleccionTradRev("IT&BE");
	}
}

/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */


function crearTabla(filter){
	if(!tablaCreada){
		if($("[aria-describedby='busquedaGeneral_multifilter_dropdownDialog']").length){
			$("[aria-describedby='busquedaGeneral_multifilter_dropdownDialog']").remove();
		}
		$("#busquedaGeneral").rup_table({
			url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/busquedageneral",
			toolbar:{
				id: "busquedaGeneral_toolbar"
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
				"","","","",
				$.rup.i18n.app.label.priori,
				$.rup.i18n.app.label.numExpediente,
				$.rup.i18n.app.label.tipo,
				$.rup.i18n.app.label.numPalabraIZO,
				"",
				$.rup.i18n.app.label.gestorExpediente,
				$.rup.i18n.app.label.grupoTrabajo,
				$.rup.i18n.app.label.lote,
				$.rup.i18n.app.label.responsable,
				$.rup.i18n.app.label.entregaIZO + '<br>' + $.rup.i18n.app.label.fechaHora,
				$.rup.i18n.app.label.fechaInicio,
				$.rup.i18n.app.label.fechaFin,
				"",
				$.rup.i18n.app.label.administrativo,
				$.rup.i18n.app.label.estadoTareasAsignadas,
				$.rup.i18n.app.label.asignador,
				$.rup.i18n.app.label.requierePresupuesto,
				$.rup.i18n.app.label.proyectoTrados,
				$.rup.i18n.app.label.titulo,
				""
			],
			colModel: [
				{ 	name: "tipoTarea", hidden:true},
				{ 	name: "expedienteTradRev.indUrgente", hidden:true},
				{ 	name: "idTipoExpediente", hidden:true},
				{ 	name: "gestorExpediente.solicitante.gestorExpedientesVIP", hidden:true},
				{ 	name: "prioridadDescEu", 
				 	label: "label.prioridad",
				 	index:"PRIORITARIO",
					align: "center", 
					width: "20",  
					hidden: false, 
					editable: true, 
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject) {
						if("S".localeCompare(rowObject.prioridad) == 0){
							return '<i onclick="event.stopPropagation();cambiarPrioridad('+options.rowId.split('-')[0]+','+options.rowId.split('-')[1]+',this);" class="fa fa-star prioridad-star rojoImportant" aria-hidden="true"></i>';
						}else{
	                        return '<i onclick="event.stopPropagation();cambiarPrioridad('+options.rowId.split('-')[0]+','+options.rowId.split('-')[1]+',this);" class="fa fa-star-o prioridad-star" aria-hidden="true"></i>';
						}
					}
				}
				,
				{ 	name: "anyoNumExpConcatenado", 
				 	label: "label.anyoNumExpConcatenado",
					align: "center", 
					index: "ANYONUMEXPCONCATENADO",
					width: "90", 
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject) {
						anyoExpediente = rowObject.anyo;
						idExpediente = rowObject.numExp;
						estado = rowObject.bitacoraExpediente.estadoExp.id;
						fase = rowObject.bitacoraExpediente.faseExp.id;
						let enlace = '';
						if(rowObject.expedienteTradRev!=null){
							if('N'.localeCompare(rowObject.expedienteTradRev.indTareasEntrega)==0){
								//si el expediente tiene tareas planficadas con recursos asignados pero no tiene ninguna de las de entrega a cliente
								enlace += '<b style="display: block;"><a href="#" onclick="irDetalleExpedientes('+anyoExpediente+','+idExpediente+','+estado+','+fase+')">' + cellvalue + '</a> <i class="fa fa-exclamation-triangle" aria-hidden="true"></i></b>';
							}else{
								enlace += '<b style="display: block;"><a href="#" onclick="irDetalleExpedientes('+anyoExpediente+','+idExpediente+','+estado+','+fase+')">' + cellvalue + '</a></b>';
							}
							if (esExpedienteParaBoletin(rowObject.expedienteTradRev)){
								enlace += '<b style="display: block;font-style: italic;">'+$.rup.i18n.app.label.bopv+'</b>';
							}
						}else{
							enlace += '<b style="display: block;"><a href="#" onclick="irDetalleExpedientes('+anyoExpediente+','+idExpediente+','+estado+','+fase+')">' + cellvalue + '</a></b>';
						}
						return enlace;
					}
				}
				,
				{ 	name:$.rup.lang === 'es' ? "tipoExpedienteDescEs"
						: "tipoExpedienteDescEu", 
				 	label: "label.tipo",
					align: "center", 
					index: "IDTIPOEXPEDIENTE",
					width: "50", 
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
				}
				,
				{ 	name: "expedienteTradRev.numTotalPalConTramosPerfectMatch", 
				 	label: "label.numPalabraIZO",
					align: "center", 
					index: "NUMPALCOLORDER",
					width: "170", 
					editable: true,  
					hidden: false, 
					resizable: true, 
					sortable: true
				}
				,
				{ 	name: "gestorExpediente.solicitante.gestorExpedientesVIPDescEu",
					label: "label.vip",
					hidden:true
				},
				
				{ 	name: $.rup.lang === 'es' ? "vipEntidadGestorEs"
						: "vipEntidadGestorEu", 
				 	label: "label.gestorExpediente",
					align: "left",
					index: $.rup.lang === 'es' ? "GESTORCOLORDERES"
							: "GESTORCOLORDEREU",
					width: "200", 
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true
				}
				,
				{ 	name: "grupoTrabajo", 
				 	label: "label.grupoTrabajo",
					align: "left", 
					index: "GRUPOTRABAJO",
					width: "80", 
					editable: true, 
					hidden: false, 	
					resizable: true, 
					sortable: true,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				,
				{ 	name: "lotes.nombreLote", 
				 	label: "label.lote2",
					align: "left", 
					index: "NOMBRELOTE",
					width: "80", 
					editable: true, 
					hidden: false, 	
					resizable: true, 
					sortable: true,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				,
				{ 	name: "responsable", 
				 	label: "label.responsable",
					align: "left", 
					index: "RESPONSABLE",
					width: "120", 
					editable: true,  
					hidden: false, 
					resizable: true, 
					sortable: true,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				,
				{ 	name: "expedienteTradRev.fechaHoraFinalIZO", 
				 	label: "label.fechaHoraEntregaIZO",
					align: "center", 
					index: "FECHAFINALIZO",
					width: "90", 
					isDate: true,
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject){
						if (tipExpInterpretacion.localeCompare(rowObject.idTipoExpediente) == 0){ 
							
							return cellvalue;
						} else {
							
							return rowObject.expedienteTradRev.fechaFinalIzo + "<br>" + rowObject.expedienteTradRev.horaFinalIzo;
						}
						
					}
					
				}
				,
				{ 	name: "fechaHoraPrevistaInicio", 
				 	label: "label.fechaHoraInicioPrevista",
					align: "center", 
					index: "FECHAINICIOPREVISTA",
					width: "180", 
					isDate: true,
					editable: true,  
					hidden: false, 
					resizable: true, 
					sortable: true
				}
				,
				{ 	name: "fechaHoraPrevistaEntrega", 
				 	label: "label.fechaHoraFinPrevista",
					align: "center", 
					index: "FECHAFINPREVISTA",
					width: "90", 
					isDate: true,
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject) {
						if(cellvalue){
							return "<p class='noMargin txtTablaTarea pr1'>" + rowObject.fechaPrevistaEntrega + "<br>" + rowObject.horaPrevistaEntrega + "</p>";
						}else{
							return "<p class='noMargin txtTablaTarea pr1' style='text-align: left;'>"+$.rup.i18n.app.mensajes.sinFechaFinPrevista+"</p>";
						}
						
					}
				}
				,
				{ 	name: $.rup.lang === 'es' ? "bitacoraExpediente.faseExp.descAbrEs":"bitacoraExpediente.faseExp.descAbrEu" , 
					hidden: true
				}
				,
				{ 	name: "tecnico.nombreCompleto", 
				 	label: "label.administrativo",
					align: "left", 
					index: "NOMBRETECNICO",
					width: "200", 
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function(cellvalue, options, rowObject){
						if(rowObject.tecnico.dni){
							return mostrarCeldaAsignador(rowObject.tecnico.nombreCompleto);
						}else{
							return mostrarCeldaAsignador($.rup.i18n.app.mensajes.sinAdministrativo);
						}
					},
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				,
				{ 	name: "estadoTareasAsignadasEu", 
				 	label: "label.estadoTareasAsignadas",
					align: "center", 
					index: "IDESTADOTAREASASIGNADAS",
					width: "80", 
					editable: true, 
					hidden: false, 	
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject) {
						if (rowObject.idEstadoTareasAsignadas === 1) {
							
							return '<i class="fa fa-minus-circle" aria-hidden="true"></i>';
						} else if (rowObject.idEstadoTareasAsignadas === 2) {
							
							return '<i class="fa fa-exclamation-circle" aria-hidden="true"></i>';
						} else if (rowObject.idEstadoTareasAsignadas === 3) {
							
							return '<i class="fa fa-check-circle" aria-hidden="true"></i>';
						} else if (rowObject.idEstadoTareasAsignadas === 4) {
							
							return '<i class="fa fa-times-circle" aria-hidden="true"></i>';
						} else {
							
							return '<i class="fa fa-circle" aria-hidden="true"></i>';
						}
					}
				}
				,
				{ 	name: "asignador.nombreCompleto", 
				 	label: "label.asignador",
					align: "left", 
					index: "NOMBREASIGNADOR",
					width: "200", 
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function(cellvalue, options, rowObject){
						if(rowObject.asignador.dni){
							return mostrarCeldaAsignador(rowObject.asignador.nombreCompleto);
						}else{
							return mostrarCeldaAsignador($.rup.i18n.app.mensajes.sinAsignador);
						}
					},
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				,
				{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.reqPresupuestoDescEs"
						: "expedienteTradRev.reqPresupuestoDescEu", 
				 	label: "label.requierePresupuesto",
					align: "left", 
					index: "INDPRESUPUESTO",
					width: "200", 
					editable: true,  
					hidden: false, 
					resizable: true, 
					sortable: true
				}
				,
				{ 	name: "expedienteTradRev.reqProyectoTradosDescEu", 
				 	label: "label.proyectoTrados",
					align: "left",
					index: "REQUIERETRADOS",
					width: "190", 
					editable: true,  
					hidden: false,  
					resizable: true, 
					sortable: true
				}
				,
				{ 	name: "titulo", 
				 	label: "label.titulo",
					align: "left",
					index: "TITULO",
					width: "200", 
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function(cellvalue, options, rowObject){
						return "<p class='noMargin txtTablaTarea pr1'>"+cellvalue+"</p>";
					},
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				}
				,
				{ 	name: "idEstadoNegociacion", hidden:true}
				
	        ],
	        model:"ExpedientePlanificacion",
	        usePlugins:[
	        	 "toolbar",
	       		 "filter",
	       		 "report",
	       		 "multiselection",
                 "multifilter"
	         	],
	        primaryKey: ["anyo", "numExp"],
			loadOnStartUp: true,
			sortname : "ANYONUMEXPCONCATENADO",
			sortorder : "desc",
			multiplePkToken:"-",
			multiselection:{
				headerContextMenu:{
					selectAllPage: false,
					deselectAllPage: false,
					separator: false
				}
			},
			filter:{
				clearSearchFormMode:"reset"
				,beforeFilter: function(){
					// TODO no funciona para la llamada 
					// http://local.ejiedes.net:7001/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/busquedageneral/multiFilter/getDefault?filterSelector=busquedaGeneral&user=&_=1599217438453
					// pendiente de que al hacer esa carga, no se guarde palTrados en bbdd ya que no es un valor configurable desde los filtros de la tabla
					// y puede conllevar a confusion
					$('#palTrados').val(palTrados);
					$('[id^="fecha"][id$="error"]').remove();
					comprobarRangoNumPalabras();
					if(!$("#busquedaGeneral_filter_form").valid()){
						return false;
					}
					$("#busquedaGeneral").rup_table("resetSelection");
					$("#busquedaGeneral").rup_table("hideFilterForm");
					// modificar el orden de la tabla dinamicamente dependiendo del tipo de expediente seleccionado v3.9.50
					if("I".localeCompare($('#idTipoExpediente_filter_table').rup_combo("getRupValue"))==0){
						$('#busquedaGeneral').jqGrid('sortGrid', 'fechaHoraPrevistaInicio', false, 'asc');
					}else {
						$('#busquedaGeneral').jqGrid('sortGrid', 'ANYONUMEXPCONCATENADO', false, 'desc');
					}
				}
			},
			multifilter:{labelSize:40},
			report : {
				buttons : [{ 
					id : "report_busquedaGeneral",
					i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
					buttons : [
						{i18nCaption : $.rup.i18n.app.tabla.excel,
							divId : "report_busquedaGeneral",
							css : "fa fa-file-excel-o",
							url : "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/xlsxReport",
							limiteRegistros: 500,
							click : function(event) {
								
							}
						} 
					]}
				]
			}
		    ,title: false,
		    loadComplete: function(data){ 
		    	if($('#idTipoExpediente_filter_table')){
		    		//cargamos todas las columnas en el report y dependiendo del tipo de expediente ocultaremos/mostraremos las necesarias
		    		$("ul[id^='busquedaGeneral_toolbar'][id $='report_busquedaGeneral-mbutton-container'] div[id='print_div_report_busquedaGeneral']").html(camposReport);
		    		if("I".localeCompare($('#idTipoExpediente_filter_table').rup_combo("getRupValue"))==0){
		    			//interpretacion
						$("#busquedaGeneral").rup_table("hideCol", "prioridadDescEu");
						$("#busquedaGeneral").rup_table("hideCol", "expedienteTradRev.numTotalPalConTramosPerfectMatch");
						$("#busquedaGeneral").rup_table("hideCol", "lotes.nombreLote");
						$("#busquedaGeneral").rup_table("hideCol", "responsable");
						$("#busquedaGeneral").rup_table("hideCol", "expedienteTradRev.fechaHoraFinalIZO");
						$("#busquedaGeneral").rup_table("hideCol", "expedienteTradRev.reqPresupuestoDescEu");
						$("#busquedaGeneral").rup_table("hideCol", "expedienteTradRev.reqProyectoTradosDescEu");
						$("#busquedaGeneral").rup_table("showCol", "fechaHoraPrevistaInicio");
						ocultarColumnasReport("7,10,14,15,16,23,24");
						mostrarLeyendasAyuda(false);
					}else{
						//trad/trev
						$("#busquedaGeneral").rup_table("showCol", "prioridadDescEu");
						$("#busquedaGeneral").rup_table("showCol", "expedienteTradRev.fechaHoraFinalIZO");
						$("#busquedaGeneral").rup_table("showCol", "expedienteTradRev.numTotalPalConTramosPerfectMatch");
						$("#busquedaGeneral").rup_table("showCol", "lotes.nombreLote");
						$("#busquedaGeneral").rup_table("showCol", "responsable");
						$("#busquedaGeneral").rup_table("showCol", "expedienteTradRev.reqPresupuestoDescEu");
						$("#busquedaGeneral").rup_table("showCol", "expedienteTradRev.reqProyectoTradosDescEu");
						$("#busquedaGeneral").rup_table("hideCol", "fechaHoraPrevistaInicio");
						ocultarColumnasReport("22");
						hightlightGridRows($("#busquedaGeneral"), "expedienteTradRev.indUrgente", "S", "gridHighlightUrgente"); 
						hightlightGridRows($("#busquedaGeneral"), "idEstadoNegociacion", busqGen.estadoRequerimiento.rechazado,"gridHighlightFechaRechazada"); 
						mostrarLeyendasAyuda(true);
					}
		    		if($('#idTipoExpediente_filter_table').rup_combo("getRupValue").localeCompare(tipoExpNum) != 0){
		    			tipoExpNum = $('#idTipoExpediente_filter_table').rup_combo("getRupValue");
		    			expedientesSeleccionados = [];
		    		}
		    		filterFormObjectPestBusq = obtenerFiltrosTabla('busquedaGeneral');
		    		
		    	}
                $('#busquedaGeneral_filter_filterButton_dropdown').removeClass('rup-dropdown-button');
                $('#busquedaGeneral_filter_filterButton_dropdown').addClass('ui-button ui-widget ui-state-default ui-corner-all rup-button rup-dropdown');
                $('#busquedaGeneral_filter_filterButton_dropdown').on('click',function(){
            		$('#busquedaGeneral_multifilter_dropdownDialog_feedback_closeDiv').click();
            	});
		    },
		});
		tablaCreada = true;
		$("#busquedaGeneral_multifilter_combo_menu").css("width", "100px !important;");
	}else if(filter){
		$("#busquedaGeneral").rup_table("filter");
	}
}

/*
 * ****************************
 * CREACIÓN COMBOS - INICIO
 * ****************************
 */
function fncTipoExpedienteCombo(){
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,orderedByValue: false
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
		,onLoadSuccess: function(){
			$("#idTipoExpediente_filter_table").change(function(){
				actualizarCamposFiltro();
			});
		}
	});
}

function fncFaseCombo(){	
	$("#fases_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/fasesexpediente/getFasesExpedientePlanificacion",
	    sourceParam : {
	        value: ""+"id"+"",
	        label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
	    }
	   ,submitAsString: true
	   ,rowStriping: true
	   ,multiselect: true
	   ,open: function(){
	       jQuery('#fases_filter_table-menu').width(jQuery('#fases_filter_table-button').innerWidth());
	   },onLoadSuccess: function(data){
		   $("#fases_filter_table").rup_combo("select", [busqGen.faseExp.pdteTramCliente,busqGen.faseExp.pdteEjectTareas,busqGen.faseExp.pdteTrados]);
	   }	   
	});
}

function fncPrioridadCombo(){
	$("#prioridad_filter_table").rup_combo({
		loadFromSelect: true
		,width: "150"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#prioridad_filter_table-menu').width(jQuery('#prioridad_filter_table-button').innerWidth());
		}
	});
}

function  fncGestorVipCombo(){
	$("#gestorVIP_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#gestorVIP_filter_table-menu').width(jQuery('#gestorVIP_filter_table-button').innerWidth());
		}
	});
}

function anyadirOnChangeAComboGrupoTrabajo(){
	//evento para capturar seleccion en combo multiseleccion
	$('#grupotrabajo_filter_table').change(function(){
			a = $("#grupotrabajo_filter_table").rup_combo("index");
			if(a){
				if(a.length==1){
					id= $('#grupotrabajo_filter_table').rup_combo("getRupValue");
//					llamada y cargar campos
					$.rup_ajax({
					   	 url: "/aa79bItzulnetWar/administracion/grupostrabajo/informarCamposGrupoTrabajo/"+id+"" 
					   	 ,dataType: "json"
					   	 ,contentType: 'application/json'
					   	 ,success:function(data, textStatus, jqXHR){
					   		//tipo de expediente
					   		 if(data.idTipoExpediente==0){
					   			$("#idTipoExpediente_filter_table").rup_combo("select","I");
					   		 }else{
					   			if ($("#idTipoExpediente_filter_table").val()== "I"){
					   				$("#idTipoExpediente_filter_table").rup_combo("select","IT&BE"); 
					   			}
					   		 }
					   		
					   		//$('#idTipoExpediente_filter_table').rup_combo("disable");
					   		//rango palabras IZO
					   		if("S" == data.indBopv){
					   			$('#numPalabrasDesde_filter').val(null);
						   		$('#numPalabrasHasta_filter').val(null);
					   		}else{
					   			if("0".localeCompare(data.idTipoExpediente)==0){//interpretación
					   				$('#numPalabrasDesde_filter').val(null);
							   		$('#numPalabrasHasta_filter').val(null);
							   		$('#numPalabrasDesde_filter')[0].disabled=true;
							   		$('#numPalabrasHasta_filter')[0].disabled=true;
					   			}else{
					   				$('#numPalabrasDesde_filter').val(data.palabrasDesde);
							   		$('#numPalabrasHasta_filter').val(data.palabrasHasta);
					   			}
					   		}
					   	 },
					   	 error: function (XMLHttpRequest, textStatus, errorThrown){
							alert($.rup.i18n.app.mensajes.errorGenericoCapas);
					   	 }
					 });
					fncPersIZOAsignadorCombo(id);
				}else{
					fncPersIZOAsignadorCombo("");
					limpiarCamposSegunGrupoTrabajo();
				}
			}
		
	});
}

function seleccionarGruposTrabajoUsuario(filter){
	//llamamos al filter de la tabla desde aqui para asegurarnos de que al hacerlo, esten cargados los datos de los filtros
	 if(gruposTrabajoResponsableUsuario.length){
		 //seleccionamos el combo multiselection por el label porque pasando el id nos selecciona por la posicion
		 $("#grupotrabajo_filter_table").rup_combo( "selectLabel",gruposTrabajoResponsableUsuario );
		   a = $("#grupotrabajo_filter_table").rup_combo("index");
			if(a){
				if(a.length==1){
					id= $('#grupotrabajo_filter_table').rup_combo("getRupValue");
//					llamada y cargar campos
					$.rup_ajax({
					   	 url: "/aa79bItzulnetWar/administracion/grupostrabajo/informarCamposGrupoTrabajo/"+id+"" 
					   	 ,dataType: "json"
					   	 ,contentType: 'application/json'
					   	 ,success:function(data, textStatus, jqXHR){
					   		//tipo de expediente
					   		if(0==data.idTipoExpediente){
					   			 $("#idTipoExpediente_filter_table").rup_combo("select","I");
					   		}else{
					   			$("#idTipoExpediente_filter_table").rup_combo("select","T");
					   		}
					   		//$('#idTipoExpediente_filter_table').rup_combo("disable");
					   		//rango palabras IZO
					   		if("S" == data.indBopv){
					   			$('#numPalabrasDesde_filter').val(null);
						   		$('#numPalabrasHasta_filter').val(null);
					   		}else{
					   			if("0".localeCompare(data.idTipoExpediente)==0){//interpretación
					   				$('#numPalabrasDesde_filter').val(null);
							   		$('#numPalabrasHasta_filter').val(null);
							   		$('#numPalabrasDesde_filter')[0].disabled=true;
							   		$('#numPalabrasHasta_filter')[0].disabled=true;
					   			}else{
					   				$('#numPalabrasDesde_filter').val(data.palabrasDesde);
							   		$('#numPalabrasHasta_filter').val(data.palabrasHasta);
					   			}
					   		}
					   		//$('#numPalabrasDesde_filter')[0].disabled=true;
					   		//$('#numPalabrasHasta_filter')[0].disabled=true;
					   		//BOPV - PrevistoBOPV
					   		fncPersIZOAsignadorCombo(id);
					   		crearTabla(filter);
					   	 },
					   	 error: function (XMLHttpRequest, textStatus, errorThrown){
							alert($.rup.i18n.app.mensajes.errorGenericoCapas);
					   	 }
					 });
				}else{
					fncPersIZOAsignadorCombo("");
					crearTabla(filter);
				}
			}
	   }else{
		   fncPersIZOAsignadorCombo("");
		   crearTabla(filter);
	   }
}
	
	


function fncGrupoTrabajoCombo(){
	//obtenemos grupos de trabajo del usuario en sesion
	$.rup_ajax({
	   	 url: "/aa79bItzulnetWar/administracion/grupostrabajo/findAllGruposTrabajo" 
	   	 ,dataType: "json"
	   	 ,contentType: 'application/json'
	   	 ,success:function(data, textStatus, jqXHR){
	   		if(data && data.length){
	   			for(var i = 0; i < data.length ; i++){
	   				gruposTrabajoResponsableUsuario.push(data[i].descEu);
	   			}
	   		}
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
	//obtenemos todos los grupos de trabajo
	$("#grupotrabajo_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/administracion/grupostrabajo/listComboNoBOE/A",
	    sourceParam : {
	        value: ""+"id"+"",
	        label : "descEu"
	    }
	   ,submitAsString: true
	   ,rowStriping: true
	   ,multiselect: true
	   ,open: function(){
	       jQuery('#grupotrabajo_filter_table-menu').width(jQuery('#grupotrabajo_filter_table-button').innerWidth());
	   },onLoadSuccess: function(data){
		   //anyadimos el on change al combo y seleccionamos en el combo los grupos de trabajo del usuario
		   //no coge el change desde aqui asi que al seleccionar los grupos de trabajo inicialmente, los cargamos y lanzamos la busqueda
		   anyadirOnChangeAComboGrupoTrabajo();
		   seleccionarGruposTrabajoUsuario(false);
	   }
	});
}

function fncAsignadoCombo(){
	$("#asignador_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/asignadoresPlanificacion",
		sourceParam : {
			label : "nombreCompleto",
			value : "dni",
			style : "css"
		},
		blank: "",
		width : "100%",
		orderedByValue : true,
		rowStriping : true,
		open : function() {
			$("#asignador_filter_table-menu").width($("#asignador_filter_table-button").width());
		}
	});
}

function fncBopvCombo(){
	$("#BOPV_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#BOPV_filter_table-menu').width(jQuery('#BOPV_filter_table-button').innerWidth());
		}
	});
}

function fncReqPresupuestoCombo(){
	$("#requierePresupuesto_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#requierePresupuesto_filter_table-menu').width(jQuery('#requierePresupuesto_filter_table-button').innerWidth());
		}
	});
	
	$('#requierePresupuesto_filter_table').change(function(){
		var requierePresupuesto = $('#requierePresupuesto_filter_table').rup_combo("getRupValue");
		if("S".localeCompare(requierePresupuesto)==0){
			if($('#presupuesto_filter_table').rup_combo("isDisabled")){
				$('#presupuesto_filter_table').rup_combo("enable");
			}
		}else{
			if(!$('#presupuesto_filter_table').rup_combo("isDisabled")){
				$('#presupuesto_filter_table').rup_combo("select", "");
				$('#presupuesto_filter_table').rup_combo("disable");
			}
		}
	});
}

function fncPresupuestoCombo(){
	$("#presupuesto_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#presupuesto_filter_table-menu').width(jQuery('#presupuesto_filter_table-button').innerWidth());
		}
	});
}

function fncReqNegociacionCombo(){
	$("#requiereNegociacion_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#requiereNegociacion_filter_table-menu').width(jQuery('#requiereNegociacion_filter_table-button').innerWidth());
		}
	});
	
	$('#requiereNegociacion_filter_table').change(function(){
		var requierePresupuesto = $('#requiereNegociacion_filter_table').rup_combo("getRupValue");
		if("S".localeCompare(requierePresupuesto)==0){
			if($('#negociacion_filter_table').rup_combo("isDisabled")){
				$('#negociacion_filter_table').rup_combo("enable");
			}
		}else{
			if(!$('#negociacion_filter_table').rup_combo("isDisabled")){
				$('#negociacion_filter_table').rup_combo("select", "");
				$('#negociacion_filter_table').rup_combo("disable");
			}
		}
	});
}

function fncNegociacionCombo(){
	$("#negociacion_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#negociacion_filter_table-menu').width(jQuery('#negociacion_filter_table-button').innerWidth());
		}
	});
}

function fnCrearTipoTareaComboBG(tipoExpediente){
	jQuery('#tipoTareaBG_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/tipoTareaPorTipoExpediente/"+tipoExpediente,
		sourceParam:{
			value : "id015",
			label : $.rup.lang === 'es' ? "descEs015"
					: "descEu015", 
		}
		,blank: ""
		,width: "100%"
		,rowStriping: true
		,open : function() {
			jQuery('#tipoTareaBG_detail_table-menu').width(jQuery('#tipoTareaBG_detail_table-button').innerWidth());
		}
	
	});
}

function fncEstadoAceptTareasCombo(){
	$("#estadoAceptTareaPestBusq_detail_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoAceptTareaPestBusq_detail_table-menu').width(jQuery('#estadoAceptTareaPestBusq_detail_table-button').innerWidth());
		}
	});
}

function fncEstadoEjecTareasCombo(){
	$("#estadoEjecTarea_detail_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoEjecTarea_detail_table-menu').width(jQuery('#estadoEjecTarea_detail_table-button').innerWidth());
		}
	});
}

function fncPersIZOAsignadorCombo(ids){
	//controlar si viene vacio
	if("".localeCompare(ids)==0){
		ids = "-1";
	}
	idsFormatted = ids.replace(/,/g , "_");
	
	$("#asignador_IZO_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/asignadorGrupoTrabajo/"+idsFormatted+"",
		sourceParam : {
			label : "nombreCompleto",
			value : "dni"
		},
		blank: "",
		width : "100%",
		rowStriping : true,
		open : function() {
			$("#asignador_IZO_filter_table-menu").width($("#asignador_IZO_filter_table-button").width());
		}
	});
}

function fncLoteAsignadoCombo(){
	$("#loteAsignado_detail_table").rup_combo({
		source: "/aa79bItzulnetWar/lotes/lotesPorExpedientePlanificacion"
		,sourceParam : {
			label : "nombreLote",
			value : "idLote",
			style : "css"
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			jQuery('#loteAsignado_detail_table-menu').width(jQuery('#loteAsignado_detail_table-button').innerWidth());
		}
	});
	
}

/*
 * ****************************
 * CREACIÓN COMBOS - FIN
 * ****************************
 */

/*
 * ****************************
 * FORMATEADORES - INICIO
 * ****************************
 */

function mostrarCeldaAsignador(nombreApellidos){
	var celda = '<span><p class="txtTablaTarea pr1 noMargin">';
	celda = celda.concat(nombreApellidos);
	celda = celda.concat('</p></span>');
	return celda;
}


fnFechaDesdeHasta("fechaEntregaDesde_filter", "fechaEntregaHasta_filter");
fnFechaDesdeHasta("fechaPlanTareaDesde_filter", "fechaPlanTareaHasta_filter");

/*
 * ****************************
 * FORMATEADORES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
    jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
        jQuery(element)
        .bootstrapSwitch()
        .bootstrapSwitch('setSizeClass', 'switch-small')
        .bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
        .bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
    });

    $('#indGruposBOE').on('switch-change', function(event, state) {
        if (state.value){
                $("#grupotrabajo_filter_table").rup_combo("clear");
                $("#grupotrabajo_filter_table").rup_combo("disable");
            }else{
            	anyadirOnChangeAComboGrupoTrabajo();
      		   seleccionarGruposTrabajoUsuario(false);
                $("#grupotrabajo_filter_table").rup_combo("enable");
            }    
        
    });
    

    
	$('#busquedaGeneral_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	 $("#busquedaGeneral_filter_form").rup_validate({
			feedback: $('#busquedaGeneral_feedback'),
			liveCheckingErrors: false,				
			block:false,
			delay: 3000,
			gotoTop: true, 
			rules:{
				"expedienteTradRev.fechaEntregaIzoDesde": {date: true},
				"expedienteTradRev.fechaEntregaIzoHasta": {date: true, fechaHastaMayor: "expedienteTradRev.fechaEntregaIzoDesde" },
				"expedienteTradRev.numPalIzoDesde":{rangoDesde:true},
				"expedienteTradRev.numPalIzoHasta":{rangoHasta:true},
				"filterTarea.fechaDesdePlanifTarea": {date: true},
				"filterTarea.fechaHastaPlanifTarea": {date: true, fechaHastaMayor: "filterTarea.fechaDesdePlanifTarea" }
				
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
	 		showFieldErrorsInFeedback: false
	});
	
	
	
	$("#busquedaGeneral_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.reasignarExpedientes
				,id:"reasignarExpedientes"
					,css:"fa fa-retweet"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			expedientesSeleccionados = [];
					listIdObjects = [];
					bloquearPantalla();
					obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectPestBusq, "irAReasignarTecnicoPestBusqueda");
				}
			}
			,{
				i18nCaption: $.rup.i18n.app.boton.desgloseTareas
				,css: "fa fa-list-ul"
				,id:"desgloseTareas"	
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
					expedientesSeleccionados = [];
					listIdObjects = [];
					bloquearPantalla();
					obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectPestBusq, "irADesgloseTareas");
				}
			}
			,{
				i18nCaption: $.rup.i18n.app.boton.descargarCalendario
				,css: "fa fa-calendar"
				,id:"descargarCalendario"	
					,click: function(event){
						event.preventDefault();
			 			event.stopImmediatePropagation();
						expedientesSeleccionados = [];
						listIdObjects = [];
						bloquearPantalla();
						obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectPestBusq, "descargarIcs");
					}
			}
		]
	});
	
	fncFaseCombo();
	fncPrioridadCombo();
	fncGestorVipCombo();
	fncAsignadoCombo();
	fncBopvCombo();
	fncTipoExpedienteCombo();
	fncPresupuestoCombo();
	fncReqPresupuestoCombo();
	$("#presupuesto_filter_table").rup_combo("disable");
	fncNegociacionCombo();
	fncReqNegociacionCombo();
	$("#negociacion_filter_table").rup_combo("disable");
	fnCrearTipoTareaComboBG("T");
	fncEstadoAceptTareasCombo();
	fncEstadoEjecTareasCombo();
	fncLoteAsignadoCombo();
	
	$("#selectTodos").on ('click',function (event) {
	    if($('#selectTodos').is(":checked")){
	        $("#busquedaGeneral_filter_fieldset input[type=checkbox]").prop("checked",true);
	    }else{
	        $("#busquedaGeneral_filter_fieldset input[type=checkbox]").prop("checked",false);
	    }
	});
	
	$("#busquedaGeneral_filter_fieldset input[type=checkbox]").on ('click',function (event) {
	    if(!$(this).is(":checked")){
	        $("#selectTodos").prop("checked",false);
	    }
	});
	
	$('#busquedaGeneral_filter_cleanLinkModificado').on('click',function(event){
		tipoExpNum =$("#idTipoExpediente_filter_table").rup_combo("getRupValue");
		expedientesSeleccionados = [];
		
		// TODO No funciona el cleanFIlterForm.
		// Parece que es por un tema de un combo multiselection, pero es a nivel de componente.
		// Se deja comentado para que siga funcionando
//		var valorFiltro = $("#busquedaGeneral").rup_table("getSelectedFilter");
//		if (valorFiltro == undefined){
			resetearCampos();
//		}else{
//			try{
//				$("#busquedaGeneral").rup_table('cleanFilterForm');
//			}catch(err) {
//				$("#busquedaGeneral").rup_table('cleanFilterForm');
//			}
//			$("#busquedaGeneral").triggerHandler('rupTable_multifilter_fillForm',valorFiltro);
//			var xhrArray = $.rup_utils.jsontoarray(valorFiltro);// evento
//			$.rup_utils.populateForm(xhrArray, $('#busquedaGeneral_filter_form'));// $self._fillForm(filtroNuevo);
//			$("#busquedaGeneral").rup_table('filter');
//		}
	});
	
	$('#BOPV_filter_table').rup_combo("select", "");
	
	cargaInicialDatosPestBusqueda();
});
