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

function irAReabrirTarea(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	ocultarMenusDesplegables();
	
	var selectedRows = $("#tareasTrabajoForm").rup_table('getSelectedRows');
	$.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/reabrirTarea/"+selectedRows[0],
		dataType: 'json',
		cache: false,
		success : function(data) {
			if (data===1){
				$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.msgReaperturaTarea"), "error");
			}else{
				if (data === -1){
					$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
				} else if (data === -2){
					$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
				}
				$("#tareasTrabajoForm").trigger('reloadGrid');
	        	$("#tareasTrabajoForm").rup_table("resetSelection");
				$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
				//volvemos a habilitar los botones ya que el trabajo ya no está finalizado
				$("[id='tareasTrabajo_toolbar##modificarTarea']").button("enable");
		    	$("[id='tareasTrabajo_toolbar##crearConfigurar']").button("enable");
		    	$("[id='tareasTrabajo_toolbar##eliminarTarea']").button("enable");
		    	$("#fechaFin_filter").text('');
			}
			desbloquearPantalla();
		},
		error: function() {
			$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
			desbloquearPantalla();
		}
	});
	
}


function volverATrabajo(){
	$("#divTareasTrabajo").detach();
	$("#divCapaTrabajos").append("<div id='divTrabajos'></div>");
	$("#divTrabajos").html(capaTrabajos);
	$("#otrostrabajos").trigger('reloadGrid');
	
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
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
    $.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/addTarea",
		dataType: "json",
		contentType: 'application/json', 
		data: $.toJSON({
	   		"tarea" : tareas,
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
				
				$("#tareasTrabajoForm").trigger('reloadGrid');
	        	$("#tareasTrabajoForm").rup_table("resetSelection");
				desbloquearPantalla();
				
				$("#crearConfigurarDialog").rup_dialog('close');
				
				if (data === -1){
					$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
				} else if (data === -2){
					$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
				} else if (data === -3){
					$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.registroCalendarioPersonal"), "error");
				} else {
					$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
				}
				
				
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
			    	var tipoTarea = {
			    		"id015": $('#tipoTareaTrabajo_detail_table').rup_combo("getRupValue")	
			    	}
			    	
			    	var trabajo = {
			    			"idTrabajo": trabajoSeleccionado.idTrabajo	
			    	}
			    	
			    	var personaAsignada = {
			    		"dni": $("#dniAsignacion").val(),
			    	}
			    	
			    	var documentoEntrada = {
			    			"idFichero":$("#idFichero_0").val(),
			    			"rutaPif": $("#rutaPif_0").val(),
			    			"nombre": $("#nombre_0").val(),
			    			"extension": $("#extensionDoc_0").val(),
			    			"tamano": $("#tamanoDoc_0").val(),
			    			"contentType": $("#contentType_0").val(),
			    			"oid": $("#oidFichero_0").val(),
			    			"encriptado": $("#encriptado_0").val()
			    	}
			    	
				    var tareas = 
				    {
			    		"trabajo": trabajo,
				        "idTarea": $("#idTarea_detail_table").val(),
				        "tipoTarea": tipoTarea,
				        "orden": $("#ordenTarea_detail_table").val(),
				        "fechaInicio": $("#fechaInicioTarea_detail_table").val(),
				        "horaInicio": $("#horaInicioTarea_detail_table").val(),
				        "fechaFin": $("#fechaFinTarea_detail_table").val(),
				        "horaFin": $("#horaFinTarea_detail_table").val(),
				        "personaAsignada": personaAsignada,
				        "observaciones": $("#observacionesTarea_detail_table").val(),
				        "documentoEntrada": documentoEntrada
				    };
					
			    	if ($("#crearConfigurar_filter_form").valid()){	
						if (ids && initFormTarea === $("#crearConfigurar_filter_form").rup_form("formSerialize")){
							// no se han realizado cambios en la tarea
							$.rup_messages("msgAlert", {
			    				title: $.rup.i18n.app.label.aviso,
			    				message: $.rup.i18n.app.mensajes.tareaSinCambios
			    			});
						} else {
							// tarea nueva o tarea editada
			    			addTarea(tareas);
						}
			    	}
					
					desbloquearPantalla();
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

function cerrarModalCrearTarea(){
	$("#crearConfigurarDialog").rup_dialog('close');
}

function ocultarMenusDesplegables(){
	$("[id='tareasTrabajo_toolbar##tareas-mbutton-container']").hide();
	$("[id='tareasTrabajo_toolbar##otrasOpciones-mbutton-container']").hide();
}

function validarCampos(){
	$("#orden_detail_table").rules("add", "required");
	$("#fechaInicio_detail_table").rules("add", "required");
	$("#horaInicio_detail_table").rules("add", "required");
	$("#fechaFin_detail_table").rules("add", "required");
	$("#horaFin_detail_table").rules("add", "required");
}

function fnCrearEjecutarTareaDialog(){
	$("#ejecutarTareaTrabajoDialog").rup_dialog({
		 type: $.rup.dialog.DIV,
		 title: $.rup.i18n.app.boton.ejecutarTareas,
		 autoOpen: true,
		 modal: true,
		 resizable: false,
		 width: 980
		 ,beforeClose:function(event, ui){
//			return comprobarCambiosFormulario();
		}
	});
}

function eliminarTareas(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	var selectedRows = $("#tareasTrabajoForm").rup_table('getSelectedRows');
        		
	$.ajax({

        type: 'DELETE' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/'+selectedRows[0]  
		,dataType: 'json' 
        ,async: false 
        ,success:function(){
        	$("#tareasTrabajoForm").trigger('reloadGrid');
        	$("#tareasTrabajoForm").rup_table("resetSelection");
        	$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasEliminadasCorrectamente"), "ok");
        	desbloquearPantalla();
        }
		,error: function(error){
			$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
			desbloquearPantalla();
	   	 }
	});
        		
			
	desbloquearPantalla();
	
}

function preeliminarTareas(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	ocultarMenusDesplegables();
	var selectedRows = $("#tareasTrabajoForm").rup_table('getSelectedRows');
	var listSelectedIds = "";
	var jsonObject = {"idTarea" : selectedRows[0]};
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/comprobarTareaAEliminar' 
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
        			message: $.rup.i18n.app.mensajes.msgEliminarTareasTrabajo
        		});
        	}
			desbloquearPantalla();
        	
        }
		,error: function(error){
			$('#tareasTrabajo_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
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
	   		$("#divTareasTrabajo").append('<div id="ejecutarTareaTrabajoDialog" style="display:none"></div>');
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



function fnTareaEjecutada(codTarea,codTipoTarea,elOrden){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	ejecutarTareaConsulta = true;
	ordenTarea = elOrden;
	ocultarMenusDesplegables();
	idTarea = codTarea;
	idTipoTarea = codTipoTarea;
	fnEjecutarTareaTrabajo(codTipoTarea);
	desbloquearPantalla();
}

function fnTareaCorreccionesProv(codTarea){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	idTarea = codTarea;
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes//planificacionexpediente/tareacorreccionesprov/maint'  
	   	,success:function(data, textStatus, jqXHR){
	   		$("#ejecutarTareaDialog").remove();
	   		$("#ejecutarTareaDialog").rup_dialog('destroy');
	   		$("#divTareasTrabajo").append('<div id="ejecutarTareaDialog" style="display:none"></div>');
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
	
	$("#buscadorPersonasIZO").remove();
	$("#buscadorPersonasIZO").rup_dialog('destroy');
	
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/crearconftareas/maint'  
	   	,success:function(data){
	   		$("#crearConfigurarDialog").remove();
	   		$("#crearConfigurarDialog").rup_dialog('destroy');
	   		$("#divTareasTrabajo").append('<div id="crearConfigurarDialog" style="display:none"></div>');
	   		$("#crearConfigurarDialog").html(data);
	   		fnCrearConfigurarDialog();
	   	    
	   	    desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
}

function fnDatosTrabajo(){
	$("#idTrabajo_filter").text(trabajoSeleccionado.idTrabajo);
	$("#descTrabajo_filter").text(trabajoSeleccionado.descTrabajo);
	$("#fechaInicio_filter").text(trabajoSeleccionado.fechaInicio);
	$("#fechaFinPrevista_filter").text(trabajoSeleccionado.fechaFinPrevista);
	$("#fechaFin_filter").text(trabajoSeleccionado.fechaFin);
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */
jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	$('#tareasTrabajo_feedback').rup_feedback({
		block : false
	});
	
	$('#crearConfigurar_feedback').rup_feedback({
		block : false
	});
	
	fnDatosTrabajo();
	
	$("#tareasTrabajo_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverATrabajo"	
				,css: "fa fa-arrow-left"
				,click : function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
	 				volverATrabajo();
				}
			},
			{ i18nCaption:$.rup.i18n.app.boton.crearConfigurar, 
				css: "fa fa-file-o",
				id:"crearConfigurar"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					ids = null;
					fnCrearConfTareas();
					
				}
				
			},
			{ i18nCaption:$.rup.i18n.app.boton.modificarTarea, 
			id:"modificarTarea"
				,css: "fa fa-pencil-square-o"
			,click : function(e){
				e.preventDefault();
				e.stopImmediatePropagation();
				if(!$('#tareasTrabajoForm').rup_table("isSelected")){
					$.rup_messages("msgAlert", {
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
						message: $.rup.i18n.app.comun.warningSeleccion
					});	
					return false;
				} else {
					ids = $("#tareasTrabajoForm").rup_table("getSelectedRows")[0];
					fnCrearConfTareas();
				}
			}
			
			}
			,{ i18nCaption:$.rup.i18n.app.boton.reabrirTarea, 
				css: "fa fa-share-square-o",
				id:"reabrirTarea"
				,click: function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					ocultarMenusDesplegables();
					if(!$('#tareasTrabajoForm').rup_table("isSelected")){
						$.rup_messages("msgAlert", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
							message: $.rup.i18n.app.comun.warningSeleccion
						});	
						return false;
					} else {
						var ids = $("#tareasTrabajoForm").rup_table("getSelectedRows");
						idTarea = ids[0];
						var estadoEjecucion = $("#tareasTrabajoForm").rup_table("getCol", ids[0], "estadoEjecucion");
						if (estadoEjecucion === datosTareas.estadoEjecucion.ejecutada){
							irAReabrirTarea();
						} else {
							$.rup_messages("msgAlert", {
								title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
								message: $.rup.i18n.app.mensajes.tareaEjecutada
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
					if(!$('#tareasTrabajoForm').rup_table("isSelected")){
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
		]
	});
	
	$("#tareasTrabajoForm").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo",
		toolbar:{
			id: "tareasTrabajoForm_toolbar"
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
			,$.rup.i18n.app.label.tipoTarea
			,$.rup.i18n.app.label.personaAsignada
			,$.rup.i18n.app.label.estadoAsignado
			,$.rup.i18n.app.label.estadoEjecucion
			,$.rup.i18n.app.label.fechaHoraInicio
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
				name : "personaAsignada.nombreCompleto", 
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
						resultado = "<a href='#' onclick='fnTareaEjecutada(" + rowObject.idTarea + "," + rowObject.tipoTarea.id015 + "," + rowObject.orden + ");' id='estadoEjec_" + rowObject.idTarea + "' class='rup-enlaceCancelar linkRupTable'>" + cellvalue + "</a>";
					} else {
						resultado = cellvalue;
					}
					return  resultado;
				}
			}
			,{ 	name: "fechaHoraInicio", 
			 	label: "label.fechaHoraFin",
			 	index: "FECHAINICIO",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
			,{ 	name: "fechaHoraFin", 
			 	label: "label.fechaHoraFin",
			 	index: "FECHAFIN",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
			,{ 	name: "orden", 
			 	label: "label.orden",
			 	index: "ORDEN",
				align: "right", 
				width: "30", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
        ],
        model:"TareasTrabajo",
        usePlugins:[
        	 "toolbar",
       		 "filter",
       		 "responsive",
       		 "fluid"
         	],
        primaryKey: ["idTarea"],
		sortname:"ORDEN",
		sortorder: "asc",
		loadOnStartUp: false,
		filter:{
			beforeFilter: function(){
			}
		}
	});
	
	
    $('#idTrabajo_detail_filter_table').val(trabajoSeleccionado.idTrabajo);
	
    //deshabilitamos los botones no disponibles si el trabajo está finalizado
    if (trabajoSeleccionado.fechaFin != ''){
    	$("[id='tareasTrabajo_toolbar##modificarTarea']").button("disable");
    	$("[id='tareasTrabajo_toolbar##crearConfigurar']").button("disable");
    	$("[id='tareasTrabajo_toolbar##eliminarTarea']").button("disable");
    }
    
	$("#tareasTrabajoForm").rup_table('filter');

	
	
});