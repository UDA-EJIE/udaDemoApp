 var anyoFormatter = "";
 var numExpFormatter = "";
 var tipoExpFormatter = "";
 var listaTitulos = {};
 var reajustarTabla = false;
 var countLoadComplete = 0;

/*
 * FUNCIONALIDADES - INICIO
 */

function irADetalleTareas(idTareaRow,anyoRow,numExpRow){
	idTarea = idTareaRow;
	anyoExpediente = anyoRow;
	idExpediente = numExpRow;
	cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/maint');
}

function mostrarCeldaTipoTarea(cellval, rowObject){
	return '<b><a href="#" onclick="irADetalleTareas('+rowObject.tarea.idTarea+','+rowObject.anyo+','+rowObject.numExp+')">' + cellval + '</a></b>'
}


function mostrarCeldaAgrupacionTareas(cellval, opts, rowObject){
	groupIdPrefix = opts.gid + "ghead_";
    groupIdPrefixLength = groupIdPrefix.length;
    prefix = opts.rowId.substr(0, groupIdPrefixLength);
    
    if("busquedaDatosCargaghead_" !== prefix){
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
    		
    		return '<b>' + cellval + ' '+ cell + '</b>';
        }
    }else{
    	var split = cellval.split("/");
    	anyoFormatter = "20" + split[0];
    	numExpFormatter = split[1].replace(/^0+/, '');
    	var numExpWithoutZeros = numExpFormatter.replace(/^0+/, '');
    	cell = listaTitulos[anyoFormatter+"/"+numExpWithoutZeros].value;
    }
    
	return '<b>' + cellval + ' '+ cell + '</b>';
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
function columnasPorTareas() {	  
    var myGrid = $('#busquedaDatosCarga');
    myGrid.jqGrid('showCol','fechaHoraFin');
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
	 $("#busquedaDatosCarga").jqGrid('groupingGroupBy', "anyoNumExpConcatenado", {
		 groupColumnShow : [false]
	 });
	 columnasPorTareas();
}

/*
 * FUNCIONALIDADES - FIN
 */

jQuery(function($){
	$('#busquedaDatosCarga_feedback').rup_feedback({
		block : false
	});
	
	$("#busquedaDatosCarga").rup_table({
		url: "/aa79bItzulnetWar/dashboard/datos/dashboardCarga",
		toolbar:{
			id: "busquedaDatosCarga_toolbar"
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
			txtTipoTarea,
			txtRecursoAsignado,
			"",
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
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarCeldaAgrupacionTareas(cellval, opts, rowObject);
				}
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
				formatter: function (cellvalue, options, rowObject) {
					return mostrarCeldaTipoTarea(cellvalue, rowObject);
				},
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
			{ 	name: "tarea.idTarea", 
			 	label: "label.tarea",
				align: "center", 
				width: "160", 
				editable: false,
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
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
       		 "filter",
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
				feedback: $('#busquedaDatosCarga_feedback'),
				rules:{
					"filtroDatosCarga":{required:true}
				},
				showFieldErrorAsDefault: false,
				showErrorsInFeedback: true,
				showFieldErrorsInFeedback: false  
			}
		},
		loadComplete: function(){
			if(countLoadComplete){
				if(reajustarTabla){
					if($('#contenedorTablaBusquedaDatos').width()<1214){
						$('#busquedaDatosCarga').jqGrid("setGridWidth", $('#gbox_busquedaDatosCarga').width(), false);
					}else{
						$('#busquedaDatosCarga').jqGrid("setGridWidth", $('#gbox_busquedaDatosCarga').width(),true);
					}
				}else{
					$('#busquedaDatosCarga').jqGrid("setGridWidth", $('#gbox_busquedaDatosCarga').width(), false);
				}
				//Mostrar los valores de los filtros en formato txt
				if (!isEmpty(txtFiltroCarga)){
					$('#capaTxtFiltro').show();
					$('#txtFiltro').text(txtFiltroCarga);
				}
				$('#busquedaDatosCarga_filter_summary').text("");
			}
			countLoadComplete++;
		}
	});
	
	$("#busquedaDatosCarga_filter_form").rup_validate({
		feedback: $('#busquedaDatosCarga_feedback'),
		rules:{
			"filtroDatosCarga":{required:true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false
	});
 
	 $('#busquedaDatosCarga_filter_filterButton').click(function(){
		 var error = $("#busquedaDatosCarga_filter_form").valid();
		 if(error){
			 mostrarTabla();
		 }
		 
		 return error;
	 });
	
	 $("#busquedaDatosCarga_filter_cleanLink").on("click",function(){
		$("#filtro_combo-button").removeClass("multiple_combo-error");
		$('#asignador_combo').rup_combo('enable');
   		$("#busquedaDatosCarga").rup_table("resetForm", $("#busquedaDatosCarga_filter_form"));
       	$("#busquedaDatosCarga").rup_table("clearGridData",true);
       	eliminarMensajesValidacion();
       	$('#busquedaDatosCarga_feedback').rup_feedback("close");
     });
	 
	 columnasPorTareas();
	 $('#busquedaDatosCarga_feedback').rup_feedback("close");
      
	function ajustarTabla(){
		if(reajustarTabla){
			if($('#contenedorTablaBusquedaDatos').width()<1214){
				$('#busquedaDatosCarga').jqGrid("setGridWidth", $('#gbox_busquedaDatosCarga').width(), false);
			}else{
				$('#busquedaDatosCarga').jqGrid("setGridWidth", $('#gbox_busquedaDatosCarga').width(),true);
			}
		}else{
			$('#busquedaDatosCarga').jqGrid("setGridWidth", $('#gbox_busquedaDatosCarga').width(), false);
		}
	}
	window.addEventListener("resize", ajustarTabla);
	
	$('#filtroDatosCarga').val(filtroDatosCarga);
	$('#busquedaDatosCarga_filter_filterButton').click();
	
});
