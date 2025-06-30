/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function volverALasTareasDelExpediente(mostrarMsgOk){
	$("#divReordenarTareasExp").detach();
	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
	$("#divDetalleExpediente").html(capaTareasExpediente);
	
	$("#tareasExpedientesForm").rup_table('filter');
	$("#tareasExpedientesForm").rup_table("resetSelection");
	
	if (mostrarMsgOk){
		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
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

function reordenarTareas(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	$("#reordenarTareasExp_feedback").rup_feedback("close");
	
	var ids = $("#reordenarTareasExpForm").rup_table("getDataIDs");
	var listaTareas = new Array();
	var idTarea;
	var tipoTarea;
	var indReqCierre;
	var estadoEjecucion;
	var orden;
	var objTipoTarea;
	var ordenValido = true;
	var idTareaRel;
	
	for (var i = 0; i < ids.length && ordenValido; i++) {
		idTarea = ids[i];
		estadoEjecucion = $("#reordenarTareasExpForm").rup_table("getCol", idTarea, "estadoEjecucion");
		tipoTarea = $("#reordenarTareasExpForm").rup_table("getCol", idTarea, "tipoTarea.id015");
		indReqCierre = $("#reordenarTareasExpForm").rup_table("getCol", idTarea, "tipoTarea.indReqCierre015");
		idTareaRel = $("#reordenarTareasExpForm").rup_table("getCol", idTarea, "idTareaRel");
		
		if (estadoEjecucion === datosTareas.estadoEjecucion.ejecutada || 
				tipoTarea === datosTareas.tiposTarea.proyectoTrados || 
				tipoTarea === datosTareas.tiposTarea.gestionInterpretacion || 
				tipoTarea === datosTareas.tiposTarea.estudiarSubsanacion){
			orden = $("#reordenarTareasExpForm").rup_table("getCol", idTarea, "orden");
		} else {
			orden = $("#orden_"+idTarea).val();
			
			if(orden === ""){
				ordenValido = false;
				$('#reordenarTareasExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_validate.messages.required"), "error");
				desbloquearPantalla();
			} else if(isNaN(parseInt(orden))){
				ordenValido = false;
				$('#reordenarTareasExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"validaciones.ordenNumerico"), "error");
				desbloquearPantalla();
			} else if (parseInt(orden) === 0){
				ordenValido = false;
				$('#reordenarTareasExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"validaciones.ordenSuperiorACero"), "error");
				desbloquearPantalla();
			}
		}
		
		objTipoTarea = {
	    		"id015": parseInt(tipoTarea),
	    		"indReqCierre015": indReqCierre
	    	}
		
		listaTareas.push( { "idTarea": parseInt(idTarea), "tipoTarea": objTipoTarea, "estadoEjecucion": parseInt(estadoEjecucion), "orden": parseInt(orden), "idTareaRel": parseInt(idTareaRel) });
	}
	
	if (ordenValido){
		var jsonObject = {"listaTareas" : JSON.stringify(listaTareas)}
		
		$.ajax({

	        type: 'POST' 
	        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/reordenarTareas'
	        ,dataType: 'json' 
	        ,data: jsonObject
	        ,async: false 
	        ,success:function(data){

	        	if (data === 1){
	        		$('#reordenarTareasExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"validaciones.ordenNoCorrecto"), "error");
					desbloquearPantalla();
	        	} else {
	        		volverALasTareasDelExpediente(true);
		        	desbloquearPantalla();
	        	}

	        }
			,error: function(error){
				$('#reordenarTareasExp_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
				desbloquearPantalla();
		   	 }
		});
	}
	
}

function isCambiosFormulario(){
	
	$("#reordenarTareasExp_feedback").rup_feedback("close");
	
	var ids = $("#reordenarTareasExpForm").rup_table("getDataIDs");
	var idTarea;
	var orden;
	var ordenInicial;
	var cambiosOrden = false;
	
	for (var i = 0; i < ids.length && !cambiosOrden; i++) {
		idTarea = ids[i];
		ordenInicial = $("#reordenarTareasExpForm").rup_table("getCol", idTarea, "orden");
		orden = $("#orden_"+idTarea).val();
		
		if(ordenInicial !== orden){
			cambiosOrden = true;
		}
		
	}
	
	return cambiosOrden;
	
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	$('#reordenarTareasExp_feedback').rup_feedback({
		block : false
	});
	
	bitacoraUpdate(false);
	
	$("#reordenarTareasExpForm").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/reordenarTareasExpForm",
		toolbar:{
			id: "reordenarTareasExpForm_toolbar"
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
			,$.rup.i18n.app.label.fechaHoraInicioPrevista
			,$.rup.i18n.app.label.fechaHoraFinPrevista
			,$.rup.i18n.app.label.horaPrevista
			,$.rup.i18n.app.label.ordenObligatorio
			
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
				name : "tipoTarea.indReqCierre015",
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
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
			,
			{
				name : "fechaHoraIni", 
			 	label: "label.fechaHoraInicioPrevista",
			 	index: "FECHAINI",
				align: "center", 
				width: "70", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,{ 	name: "fechaHoraFin", 
			 	label: "label.fechaHoraFinPrevista",
			 	index: "FECHAFIN",
				align: "center", 
				width: "70", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,{ 	name: "horasPrevistas", 
			 	label: "label.horaPrevista",
			 	index: "HORASPREVISTAS",
				align: "center", 
				width: "50", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					var horasPrevistas = "";
					
					if (cellvalue !== null){
						horasPrevistas = cellvalue +" "+ $.rup.i18n.app.comun.hora;
					}
					
					return horasPrevistas;
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
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					var resultado;
					if(rowObject.estadoEjecucion == datosTareas.estadoEjecucion.ejecutada || 
							rowObject.tipoTarea.id015 == datosTareas.tiposTarea.proyectoTrados || 
							rowObject.tipoTarea.id015 == datosTareas.tiposTarea.gestionInterpretacion || 
							rowObject.tipoTarea.id015 == datosTareas.tiposTarea.estudiarSubsanacion){
						resultado = cellvalue;
					} else {
						resultado = " <input type='text' id='orden_" + rowObject.idTarea + "' name='orden' class='numeric' value='" + cellvalue + "' size='1' maxlength='2' style='text-align:right'>";
					}
					return  resultado;
				}
			}
        ],
        model:"Tareas",
        usePlugins:[
        	 "toolbar",
       		 "filter",
       		 "responsive",
       		 "fluid",
         	],
        primaryKey: ["idTarea"],
		loadOnStartUp: false,
		rowNum: 30
	});
	
	$("#reordenarTareasExp_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverATareasExp"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					if (isCambiosFormulario()){
						$.rup_messages("msgConfirm", {
	         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	         				OKFunction: function(){
	         					volverALasTareasDelExpediente(false);
	         				}
	         			});
					} else {
						volverALasTareasDelExpediente(false);
					}
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,id: "reordenarTareas"	
				,css: "fa fa-floppy-o"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					reordenarTareas();
				}
			}
		]
	});

    $('#anyo_detail_filter_table').val(anyoExpediente);
	$('#numExp_detail_filter_table').val(idExpediente);
	
	$("#reordenarTareasExpForm").rup_table('filter');
	
	$("#reordenarTareasExpForm_pager_left").addClass('oculto');
	$("#reordenarTareasExpForm_pager_center").addClass('oculto');
	$("#reordenarTareasExpForm_pager_right").addClass('oculto');
    
});