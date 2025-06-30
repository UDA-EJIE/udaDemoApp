var auditoriaDetalle;
// TODO GONTZAL_1 // 
var esAsignador;
var usuarioAuditor; 
// TODO GONTZAL_1 // 


/**
 */
function fncPantallaAuditoriaDetalle(){
	bloquearPantalla();
	var jsonObject = {
				"anyo" : auditoriaDetalle.anyo,
				"numExp" : auditoriaDetalle.numExp,
				"idTarea": auditoriaDetalle.idTarea,
				"idTareaAuditar": auditoriaDetalle.idTareaAuditar
			};
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/auditoria/detalle/' + auditoriaDetalle.anyo + '/' + auditoriaDetalle.numExp + '/' + auditoriaDetalle.idTarea + '/' + auditoriaDetalle.idTareaAuditar, 
		success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#divAuditoria').detach();
	   		 $("#divAuditoriaGeneral").html(data);
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	 }
	 });
}

/**
 */
function fncVolverDeDetalleAuditoriaCallback(){
	// volver de detalle
	jQuery('#divDetalleAuditoria').detach();
	jQuery('#divAuditoriaGeneral').html(capaPestGeneral);
	if ($("#auditoria").length){
		$("#auditoria").trigger('reloadGrid');
	}
	// destruimos el dialog de adjuntar documento para que al volver a entrar al detalle se cree correctamente
	if ($("#documentoSeccionAuditoria_detail_div").length){
		$("#documentoSeccionAuditoria_detail_div").rup_dialog("destroy");
	}
	
	desbloquearPantalla();
}

/**
 */
function fncVolverDeDetalleAuditoria(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"),fncVolverDeDetalleAuditoriaCallback);
	
}


/**
 */
function crearComboLotes(){
	$("#nombreLote_filter_table").rup_combo({
		parent : [ "empresaProv_filter_table" ],
		sourceGroup : "/aa79bItzulnetWar/lotes/getLotesEntidad",
		sourceParam : {
			label : "nombreLote",
			value : "idLote",
			style : "css"
		},
		blank : "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			jQuery('#nombreLote_filter_table-menu').width(jQuery('#nombreLote_filter_table-button').innerWidth());
		}
	});	
}

/**
 */
function crearComboTrabajador(asignador){
	$("#trabajador_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/administracion/trabajadoresgrupostrabajo/comboTrabajadoresSinGrupo"
		,sourceParam : {
			 value: "dni",
			 label : "nombreCompletoSinComas"
		}
		,width: 250	
		,rowStriping: true
		,multiselect: true
		,disable: false
		,onLoadSuccess: function(){
			// al cargar el combo de auditor se crea la tabla
			// ya que es necesario tenerla creada por si el usuario conectado
			// no es asignador. en ese caso se selecciona el asignador en el combo, se oculta el mismo y se 
			// crea la tabla para que al filtrar tenga el filtro del usuario metido
			if(!asignador){
				$('#comboTrabajador').hide();
				$('#trabajador_filter_table').rup_combo("select", [usuarioConectado]);
			}
			// TODO GONTZAL_1 // 
			 esAsignador = asignador;
			// TODO GONTZAL_1 // 
			fncTabla();
		}
	});
	
}

/**
 */
function comprobarEsAsignador(){
	// comprobar permiso asignador 
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/getEsAsignador',
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		success: function(data){
			crearComboTrabajador(data);
		}
	});
	
}

/**
 */
function fncAuditoriaFeedback(){
	$('#auditoria_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
}

/**
 */
function fncAuditoriaToolbar(){
	$("#auditoria_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.verCrearAuditoria
				,css: "fa fa-tags"
				,id:"verCrearAuditoria"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
					var selectedRows = $("#auditoria").rup_table('getSelectedRows');
					if (isEmpty(selectedRows)){
						// no hay filas seleccionadas
						$.rup_messages("msgAlert", {
							title: $.rup.i18n.app.label.aviso,
							message: $.rup.i18n.app.comun.warningSeleccion
						});	
						return false;
				 	} else {
						var selectedRowData = $("#auditoria").rup_table("getRowData", selectedRows[0]);
						// para ir a auditoria el usuario conectado debe coincidir con la persona asignada de la tarea seleccionada
						// TODO GONTZAL_1 // 
						// casos 2 o 3: en caso de que el usuario conectado sea asignador:
						// - 2 - que puedan entrar al detalle pero que no puedan introducir datos. Esto es, que entren en modo consulta
						// - 3 - que puedan entrar al detalle y si no está enviada que puedan introducir datos.
						
						 //if(!esAsignador){
						// TODO GONTZAL_1 // 
							if (selectedRowData["auditorAsignado.dni"] === usuarioConectado) {
								auditoriaDetalle = selectedRowData;
								usuarioAuditor = true;
								// si vamos al detalle cerramos el feedback, para que al volver no este visible
								if ($('#auditoria_feedback').css('visibility') != 'hidden') {
									$('#auditoria_feedback').rup_feedback("close");
								}
				 				fncPantallaAuditoriaDetalle();
				 			} else {
				 				if(!esAsignador){
									mostrarMensajeFeedback("auditoria_feedback", $.rup.i18n.app.mensajes.usuarioConectadoNoAuditor, "error");
				 				}else{
				 					if (selectedRowData["estado"] === auditoriaData.estadoAuditoria.sinEnviar){
										mostrarMensajeFeedback("auditoria_feedback", $.rup.i18n.app.mensajes.usuarioConectadoNoAuditor, "error");
				 					}else{
				 						auditoriaDetalle = selectedRowData;
										usuarioAuditor = selectedRowData["auditorAsignado.dni"] === usuarioConectado;
						 				fncPantallaAuditoriaDetalle();
				 					}
				 				}
				 			}
						// TODO GONTZAL_1 // 
//						} else {
//							auditoriaDetalle = selectedRowData;
//							usuarioAuditor = selectedRowData["auditorAsignado.dni"] /*=== usuarioConectado*/;
//			 				fncPantallaAuditoriaDetalle();
//			 				}
						// TODO GONTZAL_1 // 
					}
				}
			}
			
			
		]
	});
}

/**
 */
function fncComboTipoExpediente(){
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
	});
}

/**
 */
function fncComboEstado(){
	$("#estado_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#estado_filter_table-menu').width(jQuery('#estado_filter_table-button').innerWidth());
		}
	});
}

/**
 */
function fncComboLote(){
	$("#empresaProv_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/administracion/empresasproveedoras/empresasProveedorasConTareasNoPagadasYPagadas",
		sourceParam : {
			label : "descEu",
			value : "codigo",
			style : "css"
		},
		blank : "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			jQuery('#empresaProv_filter_table-menu').width(jQuery('#empresaProv_filter_table-button').innerWidth());
		},
		onLoadSuccess : function() {
			crearComboLotes();
		}
	});
}

/**
 */
function fncTabla(){
	$("#auditoria").rup_table({
		url: "/aa79bItzulnetWar/auditoria",
		colNames: [
			"","","","","","","","",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tareaRevTrabajo,
			$.rup.i18n.app.label.aQuienSeAudita,
			$.rup.i18n.app.label.fechaEnvio,
			$.rup.i18n.app.label.fechaConfirmacion
		],
		colModel: [
			{
				name: "anyo",
				hidden : true
			},
			{
				name: "numExp",
				hidden : true
			},
			{
				name: "idTarea",
				hidden : true
			},
			{
				name: "idTareaAuditar",
				hidden : true
			},
			{ 	name: "auditorAsignado.nombreCompletoSinComas", 
				index: "NOMBRE_AUDITOR",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "auditorAsignado.dni", 
				index: "DNI_AUDITOR",
				editable: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "estado", 
				align: "center", 
				index: "ESTADO_AUDITORIA",
				width: "20", 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if (cellvalue === 3) {
						return '<b style="display: block;"><i class="fa fa-check-square-o" aria-hidden="true"></i></b>';
					} else if (cellvalue === 2) {
						return '<b style="display: block;"><i class="fa fa-share-square-o" aria-hidden="true"></b>';
					} else {
						return '<b style="display: block;"><i class="fa fa-square-o" aria-hidden="true"></b>';
					}
				}
			},
			{ 	name: "estado", 
				hidden : true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
			 	index: "ANYONUMEXPCONCATENADO",
				align: "center", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "tipoTarea.descEs015":"tipoTarea.descEu015", 
			 	label: "label.tareaRevTrabajo",
			 	index: "ID_TAREA",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return rowObject.idTarea + ' - ' + cellvalue;
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "lotes.descLoteEs":"lotes.descLoteEu", 
			 	label: "label.aQuienAudita",
			 	index: $.rup.lang === 'es' ? "DESC_LOTE_ES":"DESC_LOTE_EU",
				align: "left", 
				width: "120", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "fechaHoraEnvio", 
			 	label: "label.fechaEnvio",
			 	index: "FECHA_ENVIO",
				align: "center", 
				width: "50", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue ? cellvalue : "-";
				}
			},
			{ 	name: "fechaHoraConfirmacion", 
			 	label: "label.fechaConfirmacion",
			 	index: "FECHA_CONFIRMACION",
				align: "center", 
				width: "50", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue ? cellvalue : "-";
				}
			},
        ],
        model:"Auditoria",
        multiSort: true,
        sortname: "NUM_EXP",
        sortorder: "asc",
        usePlugins:[
       		 "fluid",
       		 "filter",
       		 "responsive"
         	]
     	, grouping: true
		, groupingView: {
		    groupField: ["auditorAsignado.nombreCompletoSinComas"],
		    groupColumnShow : [false],
		    groupText : ['<b>{0}</b>'],
		    isInTheSameGroup: function (x, y) {
		        return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		  }
        , primaryKey: ["anyo", "numExp"],
		loadOnStartUp: true
	});
}

jQuery(function($){
	bloquearPantalla();
	fncAuditoriaFeedback();
	fncAuditoriaToolbar();
	fncComboTipoExpediente();
	fncComboEstado();
	comprobarEsAsignador();
	fncComboLote();
	llamadasFinalizadas("desbloquearPantalla");
});