 var tipoFiltro = "";
 var anyoFormatter = "";
 var numExpFormatter = "";
 var tipoExpFormatter = "";
 var listaTitulos = {};
 var gruposTrabajoResponsableUsuario = [];
 var reajustarTabla = false;
 var countLoadComplete = 0;

/*
 * FUNCIONALIDADES - INICIO
 */

function seleccionarGruposTrabajoUsuario(){
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
	   		if(gruposTrabajoResponsableUsuario.length){
			   $("#grupoTrabajo_combo").rup_combo( "selectLabel",gruposTrabajoResponsableUsuario );
			   a = $("#grupoTrabajo_combo").rup_combo("index");
			}
	   		fncComboAsignador();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
	
}
function showDetalleExpediente(anyoFormatter, numExpFormatter, tipoExpFormatter){
	tipoExp = "'"+tipoExpFormatter+"'";
	anyoExpediente = anyoFormatter;
	idExpediente = numExpFormatter;
	pestDatos = true;
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/detalleexpediente/maint');
}


function mostrarCeldaAgrupacionTareas(cellval, opts, rowObject){
	groupIdPrefix = opts.gid + "ghead_";
    groupIdPrefixLength = groupIdPrefix.length;
    prefix = opts.rowId.substr(0, groupIdPrefixLength);
    if("busquedaDatosghead_" !== prefix){
    	anyoFormatter = rowObject.anyo;
    	numExpFormatter = rowObject.numExp;
    	tipoExpFormatter = rowObject.idTipoExpediente;
    	cell = "";
        if(rowObject.tarea !== undefined && rowObject.tarea !== null){
    		if (rowObject.gestorExpediente.solicitante.gestorExpedientesVIP == 'S'){
    			cell = cell.concat(" - ");
    			cell = cell.concat('<span class="vip">VIP</span>');
    		}
    		cell = cell.concat(" - ");
    		cell = cell.concat(rowObject.titulo);
    		
    		listaTitulos[anyoFormatter+"/"+numExpFormatter] = { value: cell };
    		
    		return '<b><a href="#" onclick="showDetalleExpediente(' + anyoFormatter + ', ' + numExpFormatter+ ', \'' + tipoExpFormatter +'\')">' + cellval + '</a>'+ cell + '</b>';
        }
    }else{
    	var split = cellval.split("/");
    	anyoFormatter = "20" + split[0];
    	numExpFormatter = split[1].replace(/^0+/, '');
    	var numExpWithoutZeros = numExpFormatter.replace(/^0+/, '');
    	cell = listaTitulos[anyoFormatter+"/"+numExpWithoutZeros].value;
    }
    
	return '<b><a href="#" onclick="showDetalleExpediente(' + anyoFormatter + ', ' + numExpFormatter + ', \'' + tipoExpFormatter +'\')">' + cellval + '</a>'+ cell + '</b>';
}

function cambiarCapaGeneral(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#divPlanificacionExpedientesGeneral').detach();
	   		 $("#divPlanificacionCapa").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

function mostrarCeldaRecurso(cellvalue, rowObject){
    var cell = cellvalue;
    if(rowObject.tarea !== undefined && rowObject.tarea !== null && rowObject.tarea.recursoDisponible === 'N'){
    	cell = cell.concat(" ");
    	cell = cell.concat('<i class="fa fa-exclamation-circle" aria-hidden="true"></i>');
    }
    return cell;
}

function columnasPorExpedientes() {	 
    var myGrid = $('#busquedaDatos');
    myGrid.jqGrid('showCol','anyoNumExpConcatenado');
    myGrid.jqGrid('showCol',$.rup.lang === 'es' ? "tipoExpedienteDescEs"
			: "tipoExpedienteDescEu");
    	myGrid.jqGrid('showCol','vipEntidadGestorEu');
    myGrid.jqGrid('showCol','fechaHoraAlta');
    myGrid.jqGrid('showCol','fechaHoraFin');
    myGrid.jqGrid('showCol','conformidad');
    myGrid.jqGrid('showCol','sinAsignar');
    myGrid.jqGrid('hideCol','bitacoraExpediente.subsanacionExp.tipoRequerimientoDescEu');
    myGrid.jqGrid('hideCol','bitacoraExpediente.subsanacionExp.fechaHoraLimite');
    myGrid.jqGrid('hideCol','bitacoraExpediente.subsanacionExp.estadoDescEu');
    myGrid.jqGrid('hideCol', 'tarea.tipoTarea.descEu015');
    myGrid.jqGrid('hideCol', 'gestorExpediente.solicitante.nombreCompleto');
    myGrid.jqGrid('hideCol', 'tarea.fechaHoraAsignacion');
    myGrid.jqGrid('hideCol', 'tarea.estadoAsignadoDesc');
    myGrid.jqGrid('hideCol', 'tarea.estadoEjecucionDesc');
    myGrid.jqGrid('hideCol', 'tarea.horasPrevistas');
    myGrid.jqGrid('hideCol', 'tarea.fechaHoraFin');
    reajustarTabla = false;
}
function columnasPorTareas() {	  
    var myGrid = $('#busquedaDatos');
    myGrid.jqGrid('hideCol','anyoNumExpConcatenado');
    myGrid.jqGrid('hideCol',$.rup.lang === 'es' ? "tipoExpedienteDescEs"
			: "tipoExpedienteDescEu");
   	myGrid.jqGrid('hideCol','vipEntidadGestorEu');
    myGrid.jqGrid('hideCol','fechaHoraAlta');
    myGrid.jqGrid('showCol','fechaHoraFin');
    myGrid.jqGrid('hideCol','conformidad');
    myGrid.jqGrid('hideCol','sinAsignar');
    myGrid.jqGrid('hideCol','bitacoraExpediente.subsanacionExp.tipoRequerimientoDescEu');
    myGrid.jqGrid('hideCol','bitacoraExpediente.subsanacionExp.fechaHoraLimite');
    myGrid.jqGrid('hideCol','bitacoraExpediente.subsanacionExp.estadoDescEu');
    myGrid.jqGrid('showCol', 'tarea.tipoTarea.descEu015');
    myGrid.jqGrid('showCol', 'gestorExpediente.solicitante.nombreCompleto');
    myGrid.jqGrid('showCol', 'tarea.fechaHoraAsignacion');
    myGrid.jqGrid('showCol', 'tarea.estadoAsignadoDesc');
    myGrid.jqGrid('showCol', 'tarea.estadoEjecucionDesc');
    myGrid.jqGrid('showCol', 'tarea.horasPrevistas');
    myGrid.jqGrid('showCol', 'tarea.fechaHoraFin');
    reajustarTabla = true;
}


function mostrarTabla(){
	if(tipoFiltro === "expedientes" || tipoFiltro === "expedientesSin"){
		 $("#busquedaDatos").jqGrid('groupingRemove', true);
		 columnasPorExpedientes();
		 if(tipoFiltro === "expedientesSin"){
			 $('#asignador_combo').rup_combo("select","");
			 $('#asignador_combo').rup_combo('disable');
		 }
		 $('#leyendaRecurso').hide();
	 }else if(tipoFiltro === "tareas"){
		 $("#busquedaDatos").jqGrid('groupingGroupBy', "anyoNumExpConcatenado", {
			 groupColumnShow : [false]
		 });
		 columnasPorTareas();
		 $('#leyendaUrgente').hide();
	 }		

}

/*
 * FUNCIONALIDADES - FIN
 */

/*
 * COMBOS - INICIO 
 */
function fncComboGruposTrabajo(){
	$("#grupoTrabajo_combo").rup_combo({
		 source: "/aa79bItzulnetWar/administracion/grupostrabajo/listCombo/A",
		 sourceParam : {
			 value: "id"+"",
			 label : "descEu"
		 }
		,submitAsString: true
		,rowStriping: true
		,multiselect: true
		,open: function(){
			jQuery('#grupoTrabajo_combo-menu').width(jQuery('#grupoTrabajo_combo-button').innerWidth());
		}
		,onLoadSuccess: function(){
			if((filtroDatos === "expedientesSin-6" && filtroTypeDatos === 1) || (filtroTypeDatos ===2)){
				seleccionarGruposTrabajoUsuario();
				$("#grupoTrabajo_combo").rup_combo("setRupValue",dniUsuSesion);
			}else{
				fncComboAsignador();
			}
		}
	});
}

function fncComboAsignador(){
	$("#asignador_combo").rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/asignador",
		sourceParam : {
			label : "nombreCompleto",
			value : "dni",
			style : "css"
		},
		blank: "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			$("#asignador_combo-menu").width($("#asignador_combo-button").width());
		}
		,onLoadSuccess: function(){
			if(filtroDatos !== "expedientesSin-6"
				&& filtroTypeDatos === 1){
				$("#asignador_combo").rup_combo("setRupValue",dniUsuSesion);
			}
			fncFiltroCombo();
		}
	});
}

function controlaComboAsignador(){
	if($('#ui-multiselect-filtro_combo-option-5').prop('checked')){
		$('#asignador_combo').rup_combo("select","");
		$('#asignador_combo').rup_combo('disable');
	}else{
		$('#asignador_combo').rup_combo('enable');
	}
}

function fncFiltroCombo(){
	$("#filtro_combo").rup_combo({
		sourceGroup : [
			{"expedientes" : [
				{i18nCaption: $.rup.i18n.app.planifExpedientesTipos.pendientesEntrega, value:"expedientes-1"},
				{i18nCaption: $.rup.i18n.app.planifExpedientesTipos.hoy, value:"expedientes-2"},
				{i18nCaption: $.rup.i18n.app.planifExpedientesTipos.sieteDias, value:"expedientes-3"},
				{i18nCaption: $.rup.i18n.app.planifExpedientesTipos.noConformidad, value:"expedientes-4"},
				{i18nCaption: $.rup.i18n.app.planifExpedientesTipos.sinPlanificar, value:"expedientes-5"}
			]},
			{"expedientesSin" : [
				{i18nCaption: $.rup.i18n.app.planifExpedientesTipos.sinAsignador, value:"expedientesSin-6"}
			]},	
			{"tareas" : [//Añado uno nuevo 
				{i18nCaption: "Sin asignar en exp que finalizan hoy", value:"tareas-0"},
				{i18nCaption: $.rup.i18n.app.planifTareasTipos.sinAsignar, value:"tareas-1"},
				{i18nCaption: $.rup.i18n.app.planifTareasTipos.asignadas, value:"tareas-2"},
				{i18nCaption: $.rup.i18n.app.planifTareasTipos.finalizanHoy, value:"tareas-3"},
				{i18nCaption: $.rup.i18n.app.planifTareasTipos.retrasadas, value:"tareas-4"}
			]},
			{"tramites" : [
				{i18nCaption: $.rup.i18n.app.planifTramitesTipos.pendientes, value:"tramites-1"},
				{i18nCaption: $.rup.i18n.app.planifTramitesTipos.aceptados, value:"tramites-2"},
				{i18nCaption: $.rup.i18n.app.planifTramitesTipos.rechazados, value:"tramites-3"}
			]}
		]
		,width: 500	
		,rowStriping: true
		,multiselect: true
		,onLoadSuccess: function(){
			if(filtroDatos !== ""){
				$("#filtro_combo").rup_combo("select", [filtroDatos]);
				$('#busquedaDatos_filter_filterButton').click();
			}else{
				$("#filtro_combo").rup_combo("clear");
			}
			
			$('#ui-multiselect-filtro_combo-option-5').click( function(){ controlaComboAsignador()});
		}
	});
}


/*
 * COMBOS - FIN 
 */

jQuery(function($){

	$("#busquedaDatos").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/datos/busquedaGeneral",
		toolbar:{
			id: "busquedaDatos_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
		 ,newButtons : [   
			  {obj : {
					i18nCaption: $.rup.i18n.app.boton.volver
					,css: "fa fa-arrow-left"
					,index: 1
					,right: false
				 }
					,i18nCaption: $.rup.i18n.app.simpelMaint
					,id: "volverOrigenPestana"	
					,css: "fa fa-arrow-left"
					,click : function(e){
						e.preventDefault();
						e.stopImmediatePropagation();
						volverACapaGeneralDashboard();
					}
				}
		]},
		colNames: [
			"",
			"",
			"",
			txtNumExp,
			txtTipo,
			txtGestorExp,
			txtFechaHoraSol,
			txtConformidad,
			txtTareasSinAsignar,
			txtRequerimiento,
			txtFechaHoraExpiracionPlazo,
			txtEstadoReq,
			txtTipoTarea,
			txtRecursoAsignado,
			txtFechaHoraAsignacion,
			txtEstadoAceptacion,
			txtEstadoEjecucion,
			txtHorasPrevistas,
			txtFechaHoraFinalizacion,
			txtFechaHoraEntrega
		],
		colModel: [
			{ 	name: "anyo", 
				hidden: true
			},
			{ 	name: "numExp", 
				hidden: true
			},
			{ 	name: "indUrgente", hidden:true},
			{ 	name: "anyoNumExpConcatenado" ,
				label: "label.numExp",
				align: "center", 
				width: "100", 
				autoResizing: { minColWidth: 100 },
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarCeldaAgrupacionTareas(cellval, opts, rowObject);
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipo",
				align: "center",
				index: $.rup.lang === 'es' ? "TIPO_EXPEDIENTE_ES"
						: "TIPO_EXPEDIENTE_EU",
				width: "45", 
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "vipEntidadGestorEu", 
			 	label: "label.gestorExp",
				align: "", 
				width: "300",
				editable: false, 
				index: "GESTORCOLORDEREU",
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraAlta", 
			 	label: "label.fechaHoraSol",
				align: "center", 
				width: "120", 
				editable: false, 
				index: "FECHA_ALTA_051",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "conformidad", 
				label: "label.conformidad",
				align: "center", 
				width: "80",
				editable: false, 
				index: "CONFORMIDAD",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "sinAsignar", 
				label: "label.tareasSinAsignar",
				align: "center", 
				width: "140",
				editable: false, 
				index: "TAREASSINASIGNAR",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "bitacoraExpediente.subsanacionExp.tipoRequerimientoDescEu", 
				label: "label.requerimiento",
				align: "center", 
				width: "160",
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "bitacoraExpediente.subsanacionExp.fechaHoraLimite", 
				label: "label.fechaHoraExpiracionPlazo",
				align: "center", 
				width: "120",
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "bitacoraExpediente.subsanacionExp.estadoDescEu", 
				label: "label.estadoRequerimiento",
				align: "center", 
				width: "160",
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.tipoTarea.descEu015", 
			 	label: "comun.tipoDeTarea",
				align: "left", 
				width: "150", 
				editable: false,
				index: "TAREAEU",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "gestorExpediente.solicitante.nombreCompleto", 
			 	label: "label.recursoAsignado",
				align: "left", 
				width: "225", 
				editable: false,
				index: "NOMBRECOMPLETO",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return mostrarCeldaRecurso(cellvalue, rowObject);
				},
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "tarea.fechaHoraAsignacion", 
			 	label: "label.fechaHoraAsignacion",
				align: "center", 
				width: "160", 
				editable: false,
				index: "FECHA_ASIGNACION_081",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.estadoAsignadoDesc", 
			 	label: "label.estadoAceptacion",
				align: "center", 
				width: "130", 
				editable: false,
				index: "ESTADO_ASIGNACION_081",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.estadoEjecucionDesc", 
			 	label: "label.estadoEjecucion",
				align: "center", 
				width: "140", 
				editable: false,
				index: "ESTADOEJECUCIONEU",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.horasPrevistas", 
			 	label: "label.horasPrevistas",
				align: "center", 
				width: "160", 
				editable: false,
				index: "HORASPREVISTASNUMBER",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tarea.fechaHoraFin", 
			 	label: "comun.fechaHoraFinalizacion",
				align: "center", 
				width: "130", 
				editable: false,
				index: "FECHA_FIN_081",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraFin", 
				label: "label.fechaHoraEntrega",
				align: "center", 
				width: "130",
				editable: false, 
				index: "FECHA_FINAL",
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],
        model:"ExpTareasResumen",
        usePlugins:[
        	"feedback",
        	"toolbar",
       		 "filter"
        	,
       		 "fluid",
       		 "responsive"
         	],
        grouping: true,
		groupingView: {
		    groupField: ["anyoNumExpConcatenado"],
		    groupColumnShow : [false],
		    isInTheSameGroup: function (x, y) {
		        return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		  },
		  beforeSelectRow: function (){
				return false;
			},
		primaryKey: ["anyo", "numExp"],
		multiplePkToken:",",
		sortname : "ANYONUMEXPCONCATENADO",
		shrinkToFit: false,
		sortorder : "asc",
		loadOnStartUp: false,
		filter:{
			validate:{
				feedback: $('#busquedaDatos_feedback'),
				rules:{
					"filtroDatos":{required:true, filtrosValido: true}
				},
				showFieldErrorAsDefault: false,
				showErrorsInFeedback: true,
				showFieldErrorsInFeedback: false,
				invalidHandler: function(form, validator) {
					$("#filtro_combo-button").addClass("multiple_combo-error");
				}
			}
		},
		loadComplete: function(){
			if(countLoadComplete){
				if(reajustarTabla){
					if($('#contenedorTablaBusquedaDatos').width()<1214){
						$('#busquedaDatos').jqGrid("setGridWidth", $('#gbox_busquedaDatos').width(), false);
					}else{
						$('#busquedaDatos').jqGrid("setGridWidth", $('#gbox_busquedaDatos').width(),true);
					}
				}else{
					$('#busquedaDatos').jqGrid("setGridWidth", $('#gbox_busquedaDatos').width(), false);
				}
				//Mostrar los valores de los filtros en formato txt
				if (!isEmpty($("#filtro_combo").rup_combo("label"))){
					$('#capaTxtFiltro').show();
					$('#txtFiltro').text($("#filtro_combo").rup_combo("label"));
				}
				if (!isEmpty($("#grupoTrabajo_combo").rup_combo("label"))){
					$('#capaGrupoTrabajo').show();
					$('#txtGrupoTrabajo').text($("#grupoTrabajo_combo").rup_combo("label"));
				}
				if (!isEmpty($("#asignador_combo").rup_combo("getRupValue"))){
					$('#capaTxtAsignador').show();
					$('#txtAsignador').text($("#asignador_combo").rup_combo("label"));
				}
				$('#busquedaDatos_filter_summary').text("");
				//resaltar exped urgentes
				hightlightGridRows($("#busquedaDatos"), "indUrgente", "S","gridHighlightUrgente"); 
			}
			countLoadComplete++;
			
			
		}
	});
	
	
	//Para enlazar desde Dashboard
	//El boton volver solo se mostrara si esta pagina esta incrustada en otra
	//y hay que hacerlo desdpues de la creacion dela tabla pq si no no existe el toolbar
	if (typeof(formatoPestana) === "undefined"){
		jQuery('#busquedaDatos_toolbar').hide();
	}
	
	
	$("#busquedaDatos_filter_form").rup_validate({
		feedback: $('#busquedaDatos_feedback'),
		rules:{
			"filtroDatos":{required:true, filtrosValido: true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false
	});
 
	 $('#busquedaDatos_filter_filterButton').click(function(){
		 var error = $("#busquedaDatos_filter_form").valid();
		 if(error){
			 $("#filtro_combo-button").removeClass("multiple_combo-error");
			 mostrarTabla();
		 }
		 
		 return error;
	 });
	
	 $("#busquedaDatos_filter_cleanLink").on("click",function(){
		$("#filtro_combo-button").removeClass("multiple_combo-error");
		$('#asignador_combo').rup_combo('enable');
   		$("#busquedaDatos").rup_table("resetForm", $("#busquedaDatos_filter_form"));
       	$("#busquedaDatos").rup_table("clearGridData",true);
       	eliminarMensajesValidacion();
       	$('#busquedaDatos_feedback').rup_feedback("close");
     });
	 
		
		
	 columnasPorTareas();
	 $('#busquedaDatos_feedback').rup_feedback("close");
      
	// Validacion para filtro de búsqueda
	jQuery.validator.addMethod("filtrosValido", function(value, element, params) {
		var anterior = "";
		var error = true;
		tipoFiltro = "";
		for (i = 0; i < value.length; ++i) {
			var valor = value[i].split('-');
			if(valor[0] !== ''){
				if(anterior === ""){
					anterior = valor[0];
					tipoFiltro = valor[0];
				}else if(anterior !== valor[0]){
					error = false;
					tipoFiltro = "";
				}
			}
		}
		
		return error;
	},$.rup.i18n.app.validaciones.grupoOptCombo);
	
	
	fncComboGruposTrabajo();
	function ajustarTabla(){
		if(reajustarTabla){
			if($('#contenedorTablaBusquedaDatos').width()<1214){
				$('#busquedaDatos').jqGrid("setGridWidth", $('#gbox_busquedaDatos').width(), false);
			}else{
				$('#busquedaDatos').jqGrid("setGridWidth", $('#gbox_busquedaDatos').width(),true);
			}
		}else{
			$('#busquedaDatos').jqGrid("setGridWidth", $('#gbox_busquedaDatos').width(), false);
		}
	}
	window.addEventListener("resize", ajustarTabla);
	
});
