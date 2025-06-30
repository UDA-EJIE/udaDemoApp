
/*
 * ****************************
 * VARIABLE GLOBALES - INICIO
 * ****************************
 */
var idTarea;
var capaPestGeneral;
var anyoExpediente;
var numExp;
var estadoAsignacion;
var dniAsignada;
var comboCount;
var busquedaGeneralFilter;
var tareasSeleccionadas = [];
var multiSelectedList;
var selectedAnyo;
var selectedNumExp;
var selectedIdTarea;
var ocultarFiltros = false;
var detalle = false;
var filterFormObjectCargTrab;
var listIdObjects = [];
var bopv = false;
var tablaCreada = false;
var tablaTrabajoCreada = false;
var tramitagune= false;
/*
 * ****************************
 * VARIABLE GLOBALES - FIN
 * ****************************
 */

function comprobarTareasPendientes(){

	if (!esAsignador) {
		//Modificar para crear el metodo, consultar las tareas asignadas pendietes y no rechazadas
		$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/comprobarTareasPendientes',
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){	
				 if (data){
					$("#avisoTareasPendientes").show();
					$("#tradRevPendiente").show();
					$("#comaTareasPendiente").show();
					$("#yTareasPendiente").show();
					$("#interPendiente").show();
					$("#yTareasPendiente2").show();
					$("#trabajosPendiente").show();
					$("#pestTareasTrabajo").find(".rup-tabs_title").html($.rup.i18n.app.tabsCargaTrabajo.pestTareasTrabajo +' <i class="fa fa-exclamation-triangle iconoAvisoPestana" aria-hidden="true"></i>')
					var algunaAnterior = false;
					var pendientes = data.split(",");
					if (pendientes[0]=="0" && pendientes[1]=="0" && pendientes[2]=="0"){
						$("#avisoTareasPendientes").hide();
						$("#pestTareasTrabajo").find(".rup-tabs_title").html($.rup.i18n.app.tabsCargaTrabajo.pestTareasTrabajo)
					}else{
						if (pendientes[0] =="0") {//tareas trad rev
							$("#tradRevPendiente").hide();
						}else{
							algunaAnterior = true;
						}
						
						if (pendientes[1] =="0") {//tareas inter
							$("#interPendiente").hide();
							$("#yTareasPendiente").hide();
							$("#comaTareasPendiente").hide();
						}else if (algunaAnterior){
							if (pendientes[2] =="1"){
								$("#yTareasPendiente").hide();
							}else{
								$("#comaTareasPendiente").hide();
							}
						}else{
							$("#yTareasPendiente").hide();
							$("#comaTareasPendiente").hide();
							algunaAnterior = true;
						}
						
						if (pendientes[2] =="0") {//tareas trabajo
							$("#trabajosPendiente").hide();
							$("#yTareasPendiente2").hide();
							$("#pestTareasTrabajo").find(".rup-tabs_title").html($.rup.i18n.app.tabsCargaTrabajo.pestTareasTrabajo)
						}else if (algunaAnterior){
							$("#yTareasPendiente2").show();
						}
					}
	            }
				desbloquearPantalla();
			 }
		 });  
	}else{
		$("#avisoTareasPendientes").hide();
		$("#pestTareasTrabajo").find(".rup-tabs_title").html($.rup.i18n.app.tabsCargaTrabajo.pestTareasTrabajo);
		var tipoTarea = {
				"id015": $('#tipoTareaCargaTrabajo').val()
		};
		var tarea = {
				"tipoTarea": tipoTarea,
				"estadoAsignado": $('#estadoAceptTarea_detail_table').val(),
				"estadoEjecucion": $('#estadoEjecTarea_detail_table').val(),
				"fechaIni": $('#fechaFinTareaDesde').val(),
				"fechaFin": $('#fechaFinTareaHasta').val()
		}
		var filtro = {
				"filtroDatos": $('#trabajador_combo').val(),
				"tarea": tarea
		};
		$.rup_ajax({
			 type: "POST",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/comprobarTareasPendientesAsignador',
			 data: $.toJSON(filtro),
			 contentType: "application/json",
			 dataType: "json",
			 success: function(data){	
				if(data !== "0"){
					$("#pestTareasTrabajo").find(".rup-tabs_title").html($.rup.i18n.app.tabsCargaTrabajo.pestTareasTrabajo +' <i class="fa fa-exclamation-triangle iconoAvisoPestana" aria-hidden="true"></i>')
				}
				desbloquearPantalla();
			 }
		 });
	}
}

function comprobarTipoTarea(){
	var idTipoTarea = $("#tipoTareaCargaTrabajo").rup_combo("getRupValue");
	
	if (idTipoTarea != "") {
		$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/comprobarTipoTarea/' + idTipoTarea,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){	
				if (data){
					let active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
					if (active === 2){
						// estamos en la pestanya de tareas de trabajo
						// si no es tarea de trabajo, comprobamos a que tipo de expediente pertenece
						if (data.indVisibleTrabajo015 != "S"){
							if (data.indInterpretacion015 === "S"){
								// tarea interpretacion
								$("#tabsCargaTrabajo").rup_tabs("selectTab",{
									idTab: "tabsCargaTrabajo",
									position: 1,
								});
							} else {
								// tarea trad/rev
								$("#tabsCargaTrabajo").rup_tabs("selectTab",{
				            		idTab: "tabsCargaTrabajo",
				            		position: 0,
				        		});	
							}
						}
					} else if (active === 1){
						// estamos en la pestanya de tareas de interpretacion
						// si no es tarea de interpretacion, vamos a la pestanya trad/rev directamente
						if (data.indInterpretacion015 != "S"){
							// tarea trad/rev
							$("#tabsCargaTrabajo").rup_tabs("selectTab",{
			            		idTab: "tabsCargaTrabajo",
			            		position: 0,
			        		});	
						}
						
					} else if (active === 0 && data.indInterpretacion015 === "S"){
						// estamos en la pestanya de tareas de trad/rev y la tarea seleccionada es de interpretacion
						// cambiamos a tareas de interpretacion
						$("#tabsCargaTrabajo").rup_tabs("selectTab",{
							idTab: "tabsCargaTrabajo",
							position: 1,
						});
					}
	            } else {
	            	$("#tabsCargaTrabajo").rup_tabs("selectTab",{
	            		idTab: "tabsCargaTrabajo",
	            		position: 0,
	        		});	
	            }
				desbloquearPantalla();
			 }
		 });  
	}
}

function aceptarAsignacionCargTrab(){
	volcarListaAStringTareasSeleccionadas(",");
	if(null != selectedIdTarea && "" != selectedIdTarea){
		$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoTarea/' + selectedIdTarea,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){	
				if (data){
					comprobarConflictoFechas();
	            } else {
	             	$.rup_messages("msgAlert", {
	        	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
					});	
	            }
				desbloquearPantalla();
			 }
   		 });                    
     } else {
    	 	$.rup_messages("msgAlert", {
    	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
				message: $.rup.i18n.app.comun.warningSeleccion
			});	
    	 	desbloquearPantalla();
     }
}

function aceptarAsignacionCargTrabTareaTrabajo(){
	volcarListaAStringTareasSeleccionadas(",");
	if(null != selectedIdTarea && "" != selectedIdTarea){
		$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoTareaTrabajo/' + selectedIdTarea,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){	
				if (data){
					comprobarConflictoFechasTareasTrabajo();
	            } else {
	             	$.rup_messages("msgAlert", {
	        	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
					});	
	            }
				desbloquearPantalla();
			 }
   		 });                    
     } else {
    	 	$.rup_messages("msgAlert", {
    	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
				message: $.rup.i18n.app.comun.warningSeleccion
			});	
    	 	desbloquearPantalla();
     }
}

function rechazarAsignacionCargTrab(){
	volcarListaAStringTareasSeleccionadas(",");
	if(null != selectedIdTarea && "" != selectedIdTarea){
		$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoTarea/' + selectedIdTarea,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){
				if (data){
					detalle = false;
	         		$("#rechazarAsignacion_dialog").rup_dialog('open');
	            } else {
	             	$.rup_messages("msgAlert", {
	        	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
					});	
	            }
				desbloquearPantalla();
			 }
		});                    
     } else {
	 	$.rup_messages("msgAlert", {
	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
	 	desbloquearPantalla();
     }
}

function rechazarAsignacionCargTrabTareaTrabajo(){
	volcarListaAStringTareasSeleccionadas(",");
	if(null != selectedIdTarea && "" != selectedIdTarea){
		$.rup_ajax({
			 type: "GET",
			 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoTareaTrabajo/' + selectedIdTarea,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){
				if (data){
					detalle = false;
	         		$("#rechazarAsignacion_dialog").rup_dialog('open');
	            } else {
	             	$.rup_messages("msgAlert", {
	        	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
					});	
	            }
				desbloquearPantalla();
			 }
		});                    
     } else {
	 	$.rup_messages("msgAlert", {
	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
	 	desbloquearPantalla();
     }
}

function irAReasignarTareasCargTrab(){
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/reasignarTareasCargaTrabajo/maint');
}

function irAReasignarTareasTrabajoCargTrab(){
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/reasignarTareasTrabajoCargaTrabajo/maint');
}

function reasignarTareasCargTrabComprobarEstado(){
	if(listIdObjects && listIdObjects.length){
		tareasSeleccionadas = listIdObjects;
	}
	volcarListaAStringTareasSeleccionadas(",");
	$.rup_ajax({
		 type: "GET",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoEjecucionTarea/' + selectedIdTarea,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	
			if (data){
				//tareas reasignables
				irAReasignarTareasCargTrab();
	       } else {
	    	   //tareas no reasignables
	    	   $.rup_messages("msgAlert", {
	   			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
	   			message: $.rup.i18n.app.mensajes.reasignacionEnEstadoPendienteDeEj
	   		});
	       }
			desbloquearPantalla();
		 }
	 });  
}

function reasignarTareasTrabajoCargTrabComprobarEstado(){
	if(listIdObjects && listIdObjects.length){
		tareasSeleccionadas = listIdObjects;
	}
	volcarListaAStringTareasSeleccionadas(",");
	$.rup_ajax({
		 type: "GET",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoEjecucionTareaTrabajo/' + selectedIdTarea,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	
			if (data){
				//tareas reasignables
				irAReasignarTareasTrabajoCargTrab();
	       } else {
	    	   //tareas no reasignables
	    	   $.rup_messages("msgAlert", {
	   			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
	   			message: $.rup.i18n.app.mensajes.reasignacionEnEstadoPendienteDeEj
	   		});
	       }
			desbloquearPantalla();
		 }
	 });
}

function irADetalleTareas(idTareaRow,anyoRow,numExpRow,idTareaRelRow,fechaHoraFinRow){
	idTarea = idTareaRow;
	anyoExpediente = anyoRow;
	idExpediente = numExpRow;
	idTareaRel = idTareaRelRow;
	fechaHoraFin = fechaHoraFinRow;
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/maint');
}

function irADetalleTareasTrabajo(idTareaRow){
	idTarea = idTareaRow;
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasTrabajoDetalle/maint/' + idTarea);
}

function cambiarCapaGeneral(url){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#divCargaTrabajo').detach();
	   		 $("#divCargaTrabajoGeneral").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

function descargarIcs(){
	volcarListaAStringTareasSeleccionadas(",");
	window.location.href = '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/descargarCalendarioICS/'+selectedIdTarea;
	desbloquearPantalla();
}

function descargarIcsTareasTrabajo(){
	volcarListaAStringTareasSeleccionadas(",");
	window.location.href = '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/descargarCalendarioICSTareasTrabajo/'+selectedIdTarea;
	desbloquearPantalla();
}

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function crearTabla(){
	$("#busquedaGeneral").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasTradRev",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.tipoTarea,
			$.rup.i18n.app.label.numPalabraIZO,
			$.rup.i18n.app.label.grupoTrabajo,
			$.rup.i18n.app.label.fechaFinTarea,
			$.rup.i18n.app.label.fechaHoraEntregaIZO,
			$.rup.i18n.app.label.estadoAsignado,
			$.rup.i18n.app.label.estadoEjecucion,
			$.rup.i18n.app.label.dependeDeOtros
		],
		colModel: [
			{ 	name: "tarea.personaAsignada.nombreCompletoSinComas", 
				index: "NOMBRERECURSO",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.idTarea", 
				hidden: true
			},
			{ 	name: "tarea.idTareaRel", 
				hidden: true
			},
			{ 	name: "tarea.estadoAsignado", 
				hidden: true
			},
			{ 	name: "tarea.estadoEjecucion", 
				hidden: true
			},
			{ 	name: "tarea.personaAsignada.dni", 
				hidden: true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
			 	index: "ANYONUMEXPCONCATENADO",
				align: "center", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					let enlace = '<p style="display:block;margin-top: 1rem;';
					if (rowObject.expedienteTradRev!=null &&
							esExpedienteParaBoletin(rowObject.expedienteTradRev)){
						enlace += 'margin-bottom: 0;';		
					} 
					enlace += '">';
					if(rowObject.indPrioritario === "S"){
						enlace += cellvalue + ' <i class="fa fa-star" aria-hidden="true" style="color: #ba1944;"></i>'+ '</p>';
					}else{
						enlace += cellvalue + '</p>';
					}
					if (rowObject.expedienteTradRev!=null &&
							esExpedienteParaBoletin(rowObject.expedienteTradRev)){
						enlace += '<p style="display:block;font-style: italic;margin-bottom: 1rem;">'+$.rup.i18n.app.label.bopv+'</p>';		
					}
					return enlace;
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs":"tipoExpedienteDescEu",
			 	label: "label.tipo",
			 	index: $.rup.lang === 'es' ? "TIPOEXPEDIENTEDESCES":"TIPOEXPEDIENTEDESCEU",
				align: "center", 
				width: "40", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "tarea.tipoTarea.descEs015":"tarea.tipoTarea.descEu015", 
			 	label: "comun.tipoDeTarea",
			 	index: $.rup.lang === 'es' ? "TIPOTAREAES":"TIPOTAREAEU",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.tarea.esRecursoDisponible == "S"){
						return '<b><a href="#" onclick="irADetalleTareas('+rowObject.tarea.idTarea+','+rowObject.anyo+','+rowObject.numExp+','+rowObject.tarea.idTareaRel+',\''+rowObject.tarea.fechaHoraFin+'\')">' + cellvalue + '</a></b>'
					}else{
						return '<b><a href="#" onclick="irADetalleTareas('+rowObject.tarea.idTarea+','+rowObject.anyo+','+rowObject.numExp+','+rowObject.tarea.idTareaRel+',\''+rowObject.tarea.fechaHoraFin+'\')">' + cellvalue + '</a></b>' + ' ' + ' <i class="fa fa-exclamation-circle" aria-hidden="true"></i>';
					}
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "expedienteTradRev.numTotalPalConTramosPerfectMatch", 
			 	label: "comun.tipoDeTarea",
			 	index: "NUMPALCOLORDER",
				align: "right", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "grupoTrabajo", 
			 	label: "label.grupoTrabajo",
			 	index: "GRUPOTRABAJO",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.fechaHoraFin", 
			 	label: "comun.fechaFinTarea",
			 	index: "FECHAFIN",
				align: "left", 
				width: "90", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "expedienteTradRev.fechaHoraFinalIZO", 
				label: "label.fechaEntregaIZO",
				index: "FECHAFINALIZO",
				align: "left", 
				width: "90", 
				editable: false, 
				hidden: esAsignador?false:true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.estadoAsignadoDesc", 
			 	label: "label.estadoAceptacion",
			 	index: "ESTADOASIGNACIONDESC",
				align: "left", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.estadoEjecucionDesc", 
			 	label: "label.estadoEjecucion",
			 	index: "ESTADOEJECUCIONDESC",
				align: "left", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.orden", 
			 	label: "label.dependeDeOTras",
			 	index: "ESTADOTAREASDEPENDIENTES",
				align: "left", 
				width: "70", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.tarea.estadoTareasDependientes === 0){
						return $.rup.i18n.app.comun.no;
					}else if (rowObject.tarea.estadoTareasDependientes === 1){
						return $.rup.i18n.app.label.dependeOtrosPdteEjecucion;
					} else {
						return $.rup.i18n.app.label.dependeOtrosFin;
					}
				}
			}
        ],
        model:"ExpTareasResumen",
        multiSort: true,
        sortname: "FECHAFIN",
        sortorder: "asc",
        usePlugins:[
       		 "fluid",
       		 "filter",
       		 "responsive",
       		 "multiselection"
         	]
		, loadComplete: function(){ 
			var trAnterior = $("tr[id=null]").prev('tr');
			trAnterior.find('span').remove();
			trAnterior.find('td').css('padding-left','16px');
			$("tr[id=null]").remove();
			if(ocultarFiltros){
				accionBotonToolbar("busquedaGeneral_toolbar", null, "reasignarTareasCargTrab", 0);
				$("#busquedaGeneral_filter_summary").text("");
				$("#filtrosAsignador").hide();
			}
//			$("#busquedaGeneral").rup_table("deselectAllRows");
			filterFormObjectCargTrab = obtenerFiltrosTabla('busquedaGeneral');
	    },multiselection:{
	    	headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		}
     	, grouping: true
		, groupingView: {
		    groupField: ["tarea.personaAsignada.nombreCompletoSinComas"],
		    groupColumnShow : [false],
		    groupText : ['<b>{0}</b>'],
		    isInTheSameGroup: function (x, y) {
		        return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		  },
        primaryKey: ["tarea.idTarea"],
		loadOnStartUp: true,
		multiselect:true,
		filter:{
			beforeFilter: function(){
				$("#busquedaGeneral").rup_table("resetSelection");
			}
		}
	});
	
	$("#busquedaGeneral").on("rupTable_beforeFilter", function(){ 
		$('[id^="fecha"][id$="error"]').remove();
		if(!$("#busquedaGeneral_filter_form").valid()){
			return false;
		} else {
			comprobarTipoTarea();
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 0){
				$('#busquedaGeneral_filter_div').html($("#busquedaGeneral_filter_form").clone());	
			}
		}
	});
}

function crearTablaInter(){
	$("#busquedaGeneralInter").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasInterpretacion",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.tipoTarea,
			$.rup.i18n.app.label.numPalabraIZO,
			$.rup.i18n.app.label.grupoTrabajo,
			$.rup.i18n.app.label.fechaInicio,
			$.rup.i18n.app.label.fechaFin,
			$.rup.i18n.app.label.estadoAsignado,
			$.rup.i18n.app.label.estadoEjecucion,
			$.rup.i18n.app.label.dependeDeOtros
		],
		colModel: [
			{ 	name: "tarea.personaAsignada.nombreCompletoSinComas", 
				index: "NOMBRERECURSO",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.idTarea", 
				hidden: true
			},
			{ 	name: "tarea.idTareaRel", 
				hidden: true
			},
			{ 	name: "tarea.estadoAsignado", 
				hidden: true
			},
			{ 	name: "tarea.estadoEjecucion", 
				hidden: true
			},
			{ 	name: "tarea.personaAsignada.dni", 
				hidden: true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
			 	index: "ANYONUMEXPCONCATENADO",
				align: "center", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.indPrioritario === "S"){
						return cellvalue + ' <i class="fa fa-star" aria-hidden="true" style="color: #ba1944;"></i>';
					}else{
						return cellvalue;
					}
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs":"tipoExpedienteDescEu",
			 	label: "label.tipo",
			 	index: $.rup.lang === 'es' ? "TIPOEXPEDIENTEDESCES":"TIPOEXPEDIENTEDESCEU",
				align: "center", 
				width: "40", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "tarea.tipoTarea.descEs015":"tarea.tipoTarea.descEu015", 
			 	label: "comun.tipoDeTarea",
			 	index: $.rup.lang === 'es' ? "TIPOTAREAES":"TIPOTAREAEU",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.tarea.esRecursoDisponible == "S"){
						return '<b><a href="#" onclick="irADetalleTareas('+rowObject.tarea.idTarea+','+rowObject.anyo+','+rowObject.numExp+','+rowObject.tarea.idTareaRel+',\''+rowObject.tarea.fechaHoraFin+'\')">' + cellvalue + '</a></b>'
					}else{
						return '<b><a href="#" onclick="irADetalleTareas('+rowObject.tarea.idTarea+','+rowObject.anyo+','+rowObject.numExp+','+rowObject.tarea.idTareaRel+',\''+rowObject.tarea.fechaHoraFin+'\')">' + cellvalue + '</a></b>' + ' ' + ' <i class="fa fa-exclamation-circle" aria-hidden="true"></i>';
					}
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "expedienteTradRev.numTotalPalConTramosPerfectMatch", 
			 	label: "comun.tipoDeTarea",
			 	index: "NUMPALCOLORDER",
				align: "right", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "grupoTrabajo", 
			 	label: "label.grupoTrabajo",
			 	index: "GRUPOTRABAJO",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "expedienteInterpretacion.fechaHoraPrevistaInicio", 
			 	label: "label.fechaHoraInicioPrevista",
				align: "center", 
				index: "FECHAINICIOPREVISTA",
				width: "90", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name: "expedienteInterpretacion.fechaHoraPrevistaEntrega", 
			 	label: "label.fechaHoraFinPrevista",
				align: "center", 
				index: "FECHAFINPREVISTA",
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(cellvalue){
						return "<p class='noMargin txtTablaTarea pr1'>" + rowObject.expedienteInterpretacion.fechaPrevistaEntrega + "<br>" + rowObject.expedienteInterpretacion.horaPrevistaEntrega + "</p>";
					}else{
						return "<p class='noMargin txtTablaTarea pr1' style='text-align: left;'>"+$.rup.i18n.app.mensajes.sinFechaFinPrevista+"</p>";
					}
					
				}
			},
			{ 	name: "tarea.estadoAsignadoDesc", 
			 	label: "label.estadoAceptacion",
			 	index: "ESTADOASIGNACIONDESC",
				align: "left", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.estadoEjecucionDesc", 
			 	label: "label.estadoEjecucion",
			 	index: "ESTADOEJECUCIONDESC",
				align: "left", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.orden", 
			 	label: "label.dependeDeOTras",
			 	index: "ESTADOTAREASDEPENDIENTES",
				align: "left", 
				width: "70", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.tarea.estadoTareasDependientes === 0){
						return $.rup.i18n.app.comun.no;
					}else if (rowObject.tarea.estadoTareasDependientes === 1){
						return $.rup.i18n.app.label.dependeOtrosPdteEjecucion;
					} else {
						return $.rup.i18n.app.label.dependeOtrosFin;
					}
				}
			}
        ],
        model:"ExpTareasResumen",
        multiSort: true,
        sortname: "FECHAINICIOPREVISTA",
        sortorder: "asc",
        usePlugins:[
       		 "fluid",
       		 "filter",
       		 "responsive",
       		 "multiselection"
         	]
		, loadComplete: function(){ 
			var trAnterior = $("tr[id=null]").prev('tr');
			trAnterior.find('span').remove();
			trAnterior.find('td').css('padding-left','16px');
			$("tr[id=null]").remove();
			if(ocultarFiltros){
				accionBotonToolbar("busquedaGeneral_toolbar", null, "reasignarTareasCargTrab", 0);
				$("#busquedaGeneralInter_filter_summary").text("");
				$("#filtrosAsignador").hide();
			}
//			$("#busquedaGeneral").rup_table("deselectAllRows");
			filterFormObjectCargTrab = obtenerFiltrosTabla('busquedaGeneralInter');
			tablaCreada = true;
	    },multiselection:{
	    	headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		}
     	, grouping: true
		, groupingView: {
		    groupField: ["tarea.personaAsignada.nombreCompletoSinComas"],
		    groupColumnShow : [false],
		    groupText : ['<b>{0}</b>'],
		    isInTheSameGroup: function (x, y) {
		        return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		  },
        primaryKey: ["tarea.idTarea"],
		loadOnStartUp: true,
		multiselect:true,
		filter:{
			beforeFilter: function(){
				$("#busquedaGeneralInter").rup_table("resetSelection");
			}
		}
	});
	
	$("#busquedaGeneralInter").on("rupTable_beforeFilter", function(){ 
		$('[id^="fecha"][id$="error"]').remove();
		if(!$("#busquedaGeneral_filter_form").valid()){
			return false;
		} else {
			comprobarTipoTarea();
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 1){
				$('#busquedaGeneralInter_filter_fieldset').html($("#fieldFormu").clone(true));
			}
		}
	});
}

function crearTablaTrabajo(){
	$("#busquedaGeneralTrabajo").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasTrabajo",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			$.rup.i18n.app.label.numTrabajo,
			$.rup.i18n.app.label.tipoTarea,
			$.rup.i18n.app.label.fechaInicio,
			$.rup.i18n.app.label.fechaFin,
			$.rup.i18n.app.label.estadoAsignado,
			$.rup.i18n.app.label.estadoEjecucion,
		],
		colModel: [
			{ 	name: "tarea.personaAsignada.nombreCompletoSinComas", 
				index: "NOMBRERECURSO",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.idTarea", 
				hidden: true
			},
			{ 	name: "tarea.estadoAsignado", 
				hidden: true
			},
			{ 	name: "tarea.estadoEjecucion", 
				hidden: true
			},
			{ 	name: "tarea.personaAsignada.dni", 
				hidden: true
			},
			{
				name: "otrosTrabajos.idTrabajo",
				label: "label.tipo",
			 	index: "IDTRABAJO",
				align: "center", 
				width: "40", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "tarea.tipoTarea.descEs015":"tarea.tipoTarea.descEu015", 
			 	label: "comun.tipoDeTarea",
			 	index: $.rup.lang === 'es' ? "TIPOTAREAES":"TIPOTAREAEU",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.tarea.esRecursoDisponible == "S"){
						return '<b><a href="#" onclick="irADetalleTareasTrabajo('+rowObject.tarea.idTarea+')">' + cellvalue + '</a></b>'
					}else{
						return '<b><a href="#" onclick="irADetalleTareasTrabajo('+rowObject.tarea.idTarea+')">' + cellvalue + '</a></b>' + ' ' + ' <i class="fa fa-exclamation-circle" aria-hidden="true"></i>';
					}
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "tarea.fechaIni", 
			 	label: "label.fechaHoraInicioPrevista",
				align: "center", 
				index: "FECHAINICIO",
				width: "90", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(cellvalue){
						return "<p class='noMargin txtTablaTarea pr1'>" + rowObject.tarea.fechaIni + " " + rowObject.tarea.horaIni + "</p>";
					}else{
						return "<p class='noMargin txtTablaTarea pr1' style='text-align: left;'>"+$.rup.i18n.app.mensajes.sinFechaFinPrevista+"</p>";
					}
					
				}
			}
			,
			{ 	name: "tarea.horaFin", 
			 	label: "label.fechaHoraFinPrevista",
				align: "center", 
				index: "FECHAFINPREVISTA",
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(cellvalue){
						return "<p class='noMargin txtTablaTarea pr1'>" + rowObject.tarea.fechaFin + " " + rowObject.tarea.horaFin + "</p>";
					}else{
						return "<p class='noMargin txtTablaTarea pr1' style='text-align: left;'>"+$.rup.i18n.app.mensajes.sinFechaFinPrevista+"</p>";
					}
					
				}
			},
			{ 	name: "tarea.estadoAsignadoDesc", 
			 	label: "label.estadoAceptacion",
			 	index: "ESTADOASIGNACIONDESC",
				align: "left", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.estadoEjecucionDesc", 
			 	label: "label.estadoEjecucion",
			 	index: "ESTADOEJECUCIONDESC",
				align: "left", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
        ],
        model:"ExpTareasResumen",
        multiSort: true,
        sortname: "FECHAFIN",
        sortorder: "asc",
        usePlugins:[
       		 "fluid",
       		 "filter",
       		 "responsive",
       		 "multiselection"
         	]
		, loadComplete: function(){ 
			var trAnterior = $("tr[id=null]").prev('tr');
			trAnterior.find('span').remove();
			trAnterior.find('td').css('padding-left','16px');
			$("tr[id=null]").remove();
			if(ocultarFiltros){
				accionBotonToolbar("busquedaGeneral_toolbar", null, "reasignarTareasCargTrab", 0);
				$("#busquedaGeneralTrabajo_filter_summary").text("");
				$("#filtrosAsignador").hide();
			}
//			$("#busquedaGeneral").rup_table("deselectAllRows");
			filterFormObjectCargTrab = obtenerFiltrosTabla('busquedaGeneralTrabajo');
			tablaTrabajoCreada = true;
	    },multiselection:{
	    	headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		}
     	, grouping: true
		, groupingView: {
		    groupField: ["tarea.personaAsignada.nombreCompletoSinComas"],
		    groupColumnShow : [false],
		    groupText : ['<b>{0}</b>'],
		    isInTheSameGroup: function (x, y) {
		        return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		  },
        primaryKey: ["tarea.idTarea"],
		loadOnStartUp: true,
		multiselect:true,
		filter:{
			beforeFilter: function(){
				$("#busquedaGeneralTrabajo").rup_table("resetSelection");
			}
		}
	});
	
	$("#busquedaGeneralTrabajo").on("rupTable_beforeFilter", function(){ 
		$('[id^="fecha"][id$="error"]').remove();
		if(!$("#busquedaGeneral_filter_form").valid()){
			return false;
		} else {
			comprobarTipoTarea();
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 2){
				$('#busquedaGeneralTrabajo_filter_fieldset').html($("#fieldFormu").clone(true));
			}
		}
	});
}

function comprobarConflictoFechas(){
	$.rup_ajax({
		 type: "POST",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarConflictoFechas/'+selectedIdTarea,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	 
			 if(data != 0){
				 $.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.aceptarAsignacionConflictoFechas"),
						OKFunction: function(){
                          bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
                          aceptarAsignacion(estadoAceptacion.value.aceptada, selectedIdTarea);
						}
					 });
			 } else {
				 $.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.aceptarAsignacion"),
						OKFunction: function(){
                          bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
                          aceptarAsignacion(estadoAceptacion.value.aceptada, selectedIdTarea);
						}
					 });
			 }
		 }
	 });
}

function comprobarConflictoFechasTareasTrabajo(){
	$.rup_ajax({
		 type: "POST",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarConflictoFechasTareasTrabajo/'+selectedIdTarea,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	 
			 if(data != 0){
				 $.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.aceptarAsignacionConflictoFechas"),
						OKFunction: function(){
                          bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
                          aceptarAsignacionTareaTrabajo(estadoAceptacion.value.aceptada, selectedIdTarea);
						}
					 });
			 } else {
				 $.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.aceptarAsignacion"),
						OKFunction: function(){
                          bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
                          aceptarAsignacionTareaTrabajo(estadoAceptacion.value.aceptada, selectedIdTarea);
						}
					 });
			 }
		 }
	 });
}

function aceptarAsignacion(estado){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/aceptarAsignacion/'+selectedIdTarea+'/'+estado,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	 
			desbloquearPantalla();
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 0){
				$("#busquedaGeneral").trigger('reloadGrid');
			}
			if(active ==1){
				$("#busquedaGeneralInter").trigger('reloadGrid');
			}
			mostrarMensajeFeedback("busquedaGeneral_feedback", $.rup.i18n.app.mensajes.aceptadaLaAsignacion, "ok");
		}
	});
}

function aceptarAsignacionTareaTrabajo(estado){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/aceptarAsignacionTareaTrabajo/'+selectedIdTarea+'/'+estado,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	 
			desbloquearPantalla();
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active ==2){
				$("#busquedaGeneralTrabajo").trigger('reloadGrid');
			}
			mostrarMensajeFeedback("busquedaGeneral_feedback", $.rup.i18n.app.mensajes.aceptadaLaAsignacion, "ok");
		}
	});
}

function rechazarAsignacion(estado, motivo){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/rechazarAsignacion/'+selectedIdTarea+'/'+estado+'/'+motivo,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	
			if(detalle){
				volverACapaGeneral();
			}
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 0){
				$("#busquedaGeneral").trigger('reloadGrid');
			}
			if(active ==1){
				$("#busquedaGeneralInter").trigger('reloadGrid');
			}
			mostrarMensajeFeedback("busquedaGeneral_feedback", $.rup.i18n.app.mensajes.rechazadaLaAsignacion, "ok");
			comprobarTareasPendientes()
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
			}
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active ==2){
				$("#busquedaGeneralTrabajo").trigger('reloadGrid');
			}
			mostrarMensajeFeedback("busquedaGeneral_feedback", $.rup.i18n.app.mensajes.rechazadaLaAsignacion, "ok");
			comprobarTareasPendientes()
		}
	});
}

function fncComboAsignador(){

	$("#trabajador_combo").rup_combo({
		source: "/aa79bItzulnetWar/administracion/trabajadoresgrupostrabajo/comboTrabajadoresSinGrupo"
		,sourceParam : {
			 value: "dni",
			 label : "nombreCompletoSinComas"
		}
		,width: 250	
		,rowStriping: true
		,multiselect: true
		,disable: false
	});
	$('#trabajador_combo').unbind('change');
	//evento para capturar seleccion en combo multiseleccion
    $('#trabajador_combo').on('change', function() {
    	if($("#trabajador_combo").rup_combo("getRupValue").length > 0){
			//$('#recursosSinTareas_combo').bootstrapSwitch('setState', true);
			if (!$('#recursosSinTareas_combo').prop( "disabled")){
				$('#recursosSinTareas_combo').bootstrapSwitch('toggleDisabled',true,true);
				$('#recursosSinTareas_combo').prop( "disabled", true );
			}
		} else {
			//$('#recursosSinTareas_combo').bootstrapSwitch('setState', false);
			if ($('#recursosSinTareas_combo').prop( "disabled")){
				$('#recursosSinTareas_combo').bootstrapSwitch('toggleDisabled',true,true);
				$('#recursosSinTareas_combo').prop( "disabled", false );
			}
		}
    });
}

function changeTrabajadoresCombo(){

	if ( $('#trabajador_combo-menu').length   ){
		$('#trabajador_combo-menu').remove();
	}

	if ($("#grupoTrabajo_combo").rup_combo("getRupValue") != ""){
		
		$("#trabajador_combo").rup_combo({
			source: "/aa79bItzulnetWar/administracion/trabajadoresgrupostrabajo/comboTrabajadores?idGrupo=" + $("#grupoTrabajo_combo").rup_combo("getRupValue")
			,sourceParam : {
				 value: "dni",
				 label : "nombreCompletoSinComas"
			}
			,width: 200	
			,rowStriping: true
			,multiselect: true
			,disable: false
		});
	} else {
		
		$("#trabajador_combo").rup_combo({
			source: "/aa79bItzulnetWar/administracion/trabajadoresgrupostrabajo/comboTrabajadoresSinGrupo"
			,sourceParam : {
				 value: "dni",
				 label : "nombreCompletoSinComas"
			}
			,width: 200	
			,rowStriping: true
			,multiselect: true
			,disable: false
		});
	}
}

function cargaValores(){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/getEsAsignador',
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		success: function(data){
			desbloquearPantalla();
			if (data){
				$("#filtrosAsignador").show();
				$('#recursosSinTareas_combo').bootstrapSwitch('setState', true);
				ocultarFiltros = false;
				$("#esAsignador").val(true);
			} else {
				$('#recursosSinTareas_combo').bootstrapSwitch('setState', false);
				ocultarFiltros = true;
				$("#esAsignador").val(false);
			}
			crearTabla();
		}
	});
	
	comprobarTareasPendientes();
}

function cargaValoresInter(){
	/*$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/getEsAsignador',
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		success: function(data){
			desbloquearPantalla();
			if (data){
				$("#filtrosAsignador").show();
				$('#recursosSinTareas_combo').bootstrapSwitch('setState', true);
				ocultarFiltros = false;
				$("#esAsignador").val(true);
			} else {
				$('#recursosSinTareas_combo').bootstrapSwitch('setState', false);
				ocultarFiltros = true;
				$("#esAsignador").val(false);
			}
			crearTablaInter();
		}
	});*/
	crearTablaInter();
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	$('#busquedaGeneral_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#busquedaGeneral_toolbar").rup_toolbar({
		buttons:[      
			{
				i18nCaption: $.rup.i18n.app.boton.aceptarAsignacion
				,css: "fa fa-check"
				,index: 1
				,right: false
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
				function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			listIdObjects = [];
					bloquearPantalla();
					var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
					if(active == 0){
						obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectCargTrab, "aceptarAsignacionCargTrab");
					}
					if(active ==1){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralInter", filterFormObjectCargTrab, "aceptarAsignacionCargTrab");
					}
					if(active == 2){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralTrabajo", filterFormObjectCargTrab, "aceptarAsignacionCargTrabTareaTrabajo");
					}
				}
			},
			{ 
				i18nCaption: $.rup.i18n.app.boton.rechazarAsignacion
				,css: "fa fa-times"
				,index: 1
				,right: false
			    ,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			listIdObjects = [];
					bloquearPantalla();
					var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
					if(active == 0){
						obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectCargTrab, "rechazarAsignacionCargTrab");
					}
					if(active ==1){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralInter", filterFormObjectCargTrab, "rechazarAsignacionCargTrab");
					}
					if(active == 2){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralTrabajo", filterFormObjectCargTrab, "rechazarAsignacionCargTrabTareaTrabajo");
					}
             	}
			},
			{  
				i18nCaption: $.rup.i18n.app.boton.descargarACalendario
				,css: "fa fa-calendar"
				,index: 1
				,right: false
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			listIdObjects = [];
					bloquearPantalla();
					var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
					if(active == 0){
						obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectCargTrab, "descargarIcs");
					}
					if(active ==1){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralInter", filterFormObjectCargTrab, "descargarIcs");
					}
					if(active ==2){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralTrabajo", filterFormObjectCargTrab, "descargarIcsTareasTrabajo");
					}
             	}
			},
			{  
				i18nCaption: $.rup.i18n.app.boton.reasignarTareas
				,id: "reasignarTareasCargTrab"
				,css: "fa fa-retweet"
				,index: 1
				,right: false
				,json_i18n : $.rup.i18n.app.simpelMaint
				,click : 
					function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			listIdObjects = [];
					bloquearPantalla();
					var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
					if(active == 0){
						obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectCargTrab, "reasignarTareasCargTrabComprobarEstado");
					}
					if(active == 1){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralInter", filterFormObjectCargTrab, "reasignarTareasCargTrabComprobarEstado");
					}
					if(active == 2){
						obtenerIdsSeleccionadosRupTable("busquedaGeneralTrabajo", filterFormObjectCargTrab, "reasignarTareasTrabajoCargTrabComprobarEstado");
					}
             	}
			}
			
		]
	});

	//Sistema de pestañas
	$("#tabsCargaTrabajo").rup_tabs({
		tabs : [
			{i18nCaption:"pestTareasTradRev", layer: "#capaTareasTradRev"},
			{i18nCaption:"pestTareasInter", layer: "#capaTareasInterpretacion"},
			{i18nCaption:"pestTareasTrabajo", layer: "#capaTareasTrabajo"},
			{i18nCaption:"pestRecurso", url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/recurso/maint"},
			{i18nCaption:"pestExpediente", url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/expediente/maint"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		],
		select: function(){
			$("#cargaTrabajoBusqueda_filter_filterButton").unbind("click");
		},
		show: function(){
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 0){
				$('#busquedaGeneral_filter_div').html($("#busquedaGeneral_filter_form").clone());
				$("#busquedaGeneral").rup_table("filter");
				
				$("#cargaTrabajoBusqueda_filter_filterButton").on("click", function(){
					$('#busquedaGeneral_filter_div').html($("#busquedaGeneral_filter_form").clone());
					$("#busquedaGeneral").rup_table("filter");
					comprobarTareasPendientes();
				});
			}
			
			if(active == 1){
				$('#busquedaGeneralTrabajo_filter_fieldset').html("");
				$('#busquedaGeneralInter_filter_fieldset').html($("#fieldFormu").clone(true));
				
				$("#cargaTrabajoBusqueda_filter_filterButton").on("click", function(){
					$('#busquedaGeneralInter_filter_fieldset').html($("#fieldFormu").clone(true));
					$("#busquedaGeneralInter").rup_table("filter");
					comprobarTareasPendientes();
				});
				
				if (!tablaCreada) {
					llamadasFinalizadas("cargaValoresInter");
				} else {
					$("#busquedaGeneralInter").rup_table("filter");
				}
			}
			
			if (active == 2){
				$('#busquedaGeneralTrabajo_filter_fieldset').html("");
				$('#busquedaGeneralTrabajo_filter_fieldset').html($("#fieldFormu").clone(true));
				
				$("#cargaTrabajoBusqueda_filter_filterButton").on("click", function(){
					$('#busquedaGeneralTrabajo_filter_fieldset').html($("#fieldFormu").clone(true));
					$("#busquedaGeneralTrabajo").rup_table("filter");
					comprobarTareasPendientes();
				});
				
				if (!tablaTrabajoCreada) {
					llamadasFinalizadas("crearTablaTrabajo");
				} else {
					$("#busquedaGeneralTrabajo").rup_table("filter");
				}
				
			}
		}
	});
	
	$("#busquedaGeneral_filter_form").rup_validate({
		feedback: $('#busquedaGeneral_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"tarea.fechaIni": {date: true},
			"tarea.fechaFin": {date: true, fechaHastaMayor: "tarea.fechaIni"}
			
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	jQuery('#tipoTareaCargaTrabajo').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea",
		sourceParam:{
			value : "id015",
			label : $.rup.lang === 'es' ? "descEs015"
					: "descEu015", 
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open : function() {
			jQuery('#tipoTareaCargaTrabajo-menu').width(jQuery('#tipoTareaCargaTrabajo-button').innerWidth());
		}
	});
	
	fnFechaDesdeHasta("fechaFinTareaDesde", "fechaFinTareaHasta");
	
	var cmbGT = false;
	
	$("#grupoTrabajo_combo").rup_combo({
		 source: "/aa79bItzulnetWar/administracion/grupostrabajo/listComboCargaTrabajo/A",
		 sourceParam : {
			 value: "id",
			 label: "descEu"
		 }
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			jQuery('#grupoTrabajo_combo-menu').width(jQuery('#grupoTrabajo_combo-button').innerWidth());
		}
		,onLoadSuccess: function(){
			fncComboAsignador();
			cmbGT = true;
		}
		,change: function(){
			if (cmbGT){
				changeTrabajadoresCombo();
			}
			
		}
		
	});
	
	$("#estadoAceptTarea_detail_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: true	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoAceptTarea_detail_table-menu').width(jQuery('#estadoAceptTarea_detail_table-button').innerWidth());
		}
	});
	
	$("#estadoEjecTarea_detail_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: true	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoEjecTarea_detail_table-menu').width(jQuery('#estadoEjecTarea_detail_table-button').innerWidth());
		}
	});
	
	$("#recursosSinTareas_combo").bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
	.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);
	
	$("#rechazarAsignacion_form").rup_validate({
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"motivoRechazo_detail_table": {required: true},
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
    });
	
	$("#rechazarAsignacion_dialog").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: false,
	    width: "900",
	    title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
	    buttons: [{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"guardar"),
			click: function () {
				if($("#rechazarAsignacion_form").valid()){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
					if(active == 0 || active == 1){
						rechazarAsignacion(estadoAceptacion.value.rechazada, $("textarea#motivoRechazo_detail_table").val());
					} else if(active == 2){
						rechazarAsignacionTareaTrabajo(estadoAceptacion.value.rechazada, $("textarea#motivoRechazo_detail_table").val());
					}
					desbloquearPantalla();
					$("textarea#motivoRechazo_detail_table").val("");
					$("#rechazarAsignacion_dialog").rup_dialog('close');
					comprobarTareasPendientes();
				}
			}
		},
		{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"cancelar"),
			click: function () { 
				$("textarea#motivoRechazo_detail_table").val("");
				$("#rechazarAsignacion_dialog").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$("#rechazarAsignacion_dialog").validate().resetForm();
		},
		close: function(event, ui) {
			$("#rechazarAsignacion_dialog").validate().resetForm();
		}
	});
	
	$("#cargaTrabajoBusqueda_filter_filterButton").on("click", function(){
		$('[id^="fecha"][id$="error"]').remove();
		if(!$("#busquedaGeneral_filter_form").valid()){
			return false;
		} else {
			var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
			if(active == 0){
				$('#busquedaGeneral_filter_div').html($("#busquedaGeneral_filter_form").clone());
				$("#busquedaGeneral").rup_table("filter");							
			}
			
			if(active == 1){
				$('#busquedaGeneralInter_filter_fieldset').html($("#fieldFormu").clone(true));
				$("#busquedaGeneralInter").rup_table("filter");			
			}
			
			if(active == 2){
				$('#busquedaGeneralTrabajo_filter_fieldset').html($("#fieldFormu").clone(true));
				$("#busquedaGeneralTrabajo").rup_table("filter");			
			}
		}
		if(esAsignador){
			comprobarTareasPendientes();
		}
		
	});	
	
	$("#cargaTrabajoBusqueda_filter_cleanLink").on("click", function(){
		$("#tipoTareaCargaTrabajo").rup_combo("clear");
		$("#estadoAceptTarea_detail_table").rup_combo("clear");
		$("#estadoEjecTarea_detail_table").rup_combo("clear");
		$("#fechaFinTareaDesde").val("");
		$("#fechaFinTareaHasta").val("");
		if(!ocultarFiltros){
			$("#grupoTrabajo_combo").rup_combo('clear');
			$("#trabajador_combo").rup_combo('clear');
		}
		$("#cargaTrabajoBusqueda_filter_filterButton").click();
	});
	
	llamadasFinalizadas("cargaValores");
	
});

//para pintar la exclamación en la pestaña

//$("#pestTareasTrabajo").find(".rup-tabs_title").html('<i class="fa  fa-exclamation-triangle" aria-hidden="true"></i>')
