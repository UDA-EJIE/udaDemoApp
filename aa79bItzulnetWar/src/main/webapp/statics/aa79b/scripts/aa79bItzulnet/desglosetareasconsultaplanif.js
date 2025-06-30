var sIdsExpedientes = '';
var tituloExp = '';
var listaTitulos = {};
var estado;
var origen;
var pestDatos;
var desdeDesgloseTareasConsPlanif = true;
var kvArray = [];

/*
 * FUNCIONALIDADES - INICIO
 */

function volverACapaConsultaPlanif(){
	$("#divDesgloseTareasConsultaPlanif").detach();
	$("#divConsultaPlanificacionExp").html(capaPestGeneralConsultaPlanif);
	$("#consultaPlanificacionExp").trigger("reloadGrid");
	$("#consultaPlanificacionExp").rup_table("deselectAllRows");
}

function volverADesgloseTareasConsultaPlanif(){
	$("#desgloseTareasConsultaPlanif_div").detach();
	$("#divDesgloseTareasConsultaPlanif").html("<div id='desgloseTareasConsultaPlanif_div'></div>");
	$("#desgloseTareasConsultaPlanif_div").html(capaDesgloseTareasConsultaPlanif);
	desdeConsultaPlanifExpedientes = true;
}

function irADetalleTareas(idTareaRow,anyoRow,numExpRow){
	idTarea = idTareaRow;
	anyoExpediente = anyoRow;
	idExpediente = numExpRow;
	cambiarCapaDetalle('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/maint');
}

function cambiarCapaDetalle(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaDesgloseTareasConsultaPlanif = $('#desgloseTareasConsultaPlanif_div').detach();
	   		$("#divDesgloseTareasConsultaPlanif").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
}


function showConsultaExpediente(elAnyo, elNumero){
	anyo = elAnyo;
	numExp = elNumero;
	// ir a detalle en modo consulta
//	window.open('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/'+anyo+'/'+numExp);
	// ir a detalle en modo consulta
	cambiarCapaDetalle('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExp/'+elAnyo+'/'+elNumero);
	desdeConsultaPlanifExpedientes = false;
}

function showDetallePlanificacion(elAnyo, elNumero){
	anyoExpediente = elAnyo;
	idExpediente = elNumero;
	cambiarCapaDetalle('/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/detalleexpediente/maint');
		desdeConsultaPlanifExpedientes = false;
}

function mostrarCeldaRecurso(cellvalue, rowObject){
    var cell = cellvalue;
    if(cell && rowObject.tarea !== undefined && rowObject.tarea !== null && rowObject.tarea.recursoDisponible === 'N'){
    	cell = cell.concat(" ");
    	cell = cell.concat('<i class="fa fa-exclamation-circle" aria-hidden="true"></i>');
    }
    return cell;
}

function mostrarCeldaAgrupacionTareas(cellval, opts, rowObject){
	groupIdPrefix = opts.gid + "ghead_";
    groupIdPrefixLength = groupIdPrefix.length;
    prefix = opts.rowId.substr(0, groupIdPrefixLength);
   
    if("desgloseTareasConsultaPlanifghead_" !== prefix){
    	estado = rowObject.bitacoraExpediente.estadoExp.id;
    	origen = rowObject.origen;
    	
    	anyoFormatter = rowObject.anyo;
    	numExpFormatter = rowObject.numExp;
    	tipoExpFormatter = rowObject.idTipoExpediente;
    	//guardamos en un array el estado del expediente para poner el enlace a detalle correcto
    	 if(kvArray.length){
	    	//comprobar que hemos metido el estado del numexp
    		 var estaEnArray = kvArray.filter(function(x) { return (x.anyo == rowObject.anyo) && ( x.numExp == rowObject.numExp) });
	    	if($.isEmptyObject(estaEnArray)){
	    		//si no está, lo metemos
	    		kvArray.push( {
	        		"anyo": rowObject.anyo,
	        		"numExp": rowObject.numExp,
	        		"estado": rowObject.bitacoraExpediente.estadoExp.id
	        	} );
	    	}
	    }else{
	    	kvArray.push( {
        		"anyo": rowObject.anyo,
        		"numExp": rowObject.numExp,
        		"estado": rowObject.bitacoraExpediente.estadoExp.id
        	} );
	    }
    	
    	
    	cell = "";
		cell = cell.concat(" - ");
		if (rowObject.gestorExpediente.solicitante.gestorExpedientesVIP === 'S'){
			  cell += '<span class="vip">VIP</span> - ';
		}
		cell = cell.concat(rowObject.titulo);
		listaTitulos[anyoFormatter+"/"+numExpFormatter] = { value: cell };
		
		if(consExp.estadoExpediente.cerrado.localeCompare(estado.toString())===0 || consExp.estadoExpediente.enCurso.localeCompare(estado.toString())===0){
			return '<b><a href="#" onclick="showDetallePlanificacion(' + anyoFormatter + ', ' + numExpFormatter+ ')">' + cellval + '</a>'+ cell + '</b>';
		} else {
			return '<b><a href="#" onclick="showConsultaExpediente(' + anyoFormatter + ', ' + numExpFormatter+ ')">' + cellval + '</a>'+ cell + '</b>';
    	}
		
    }else{
    	var split = cellval.split("/");
    	anyoFormatter = "20" + split[0];
    	numExpFormatter = split[1].replace(/^0+/, '');
    	var numExpWithoutZeros = numExpFormatter.replace(/^0+/, '');
    	cell = listaTitulos[anyoFormatter+"/"+numExpWithoutZeros].value;
    	 var estaEnArrayPorEstado = kvArray.filter(function(x) { return (x.anyo == anyoFormatter) && ( x.numExp == numExpFormatter) });
	    	if(!$.isEmptyObject(estaEnArrayPorEstado)){
	    		estado = estaEnArrayPorEstado[0].estado;
	    	}
    }
	
	if(consExp.estadoExpediente.cerrado.localeCompare(estado.toString())===0 || consExp.estadoExpediente.enCurso.localeCompare(estado.toString())===0){
		return '<b><a href="#" onclick="showDetallePlanificacion(' + anyoFormatter + ', ' + numExpFormatter+ ')">' + cellval + '</a>'+ cell + '</b>';
	} else {
		return '<b><a href="#" onclick="showConsultaExpediente(' + anyoFormatter + ', ' + numExpFormatter+ ')">' + cellval + '</a>'+ cell + '</b>';
	}

}

/*
 * FUNCIONALIDADES - FIN
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
  var ids = '';
  for(var i = 0; i<expedientesSeleccionados.length;i++){
  	ids =  ids + '_' + expedientesSeleccionados[i];
  }
  var sIdsExpedienteswithComma = ids.substring(1);
  sIdsExpedientes = sIdsExpedienteswithComma.replace(new RegExp(',', 'g'), '_');
	$("#desgloseTareasConsultaPlanif_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverABusqueda"	
				,css: "fa fa-arrow-left"
				,click : function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			expedientesSeleccionados = [];
		 			listaTitulos = {};
		 			volverACapaConsultaPlanif();
				}
			}
		]
	});

	$("#desgloseTareasConsultaPlanif").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/desgloseTareas/"+sIdsExpedientes,
		toolbar:{
			id: "desgloseTareasConsultaPlanif_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:false
			}
		},
		colNames: [
			"","","",
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
			{ 	name: "anyoNumExpConcatenado" ,
				label: "label.numExp",
				align: "center", 
				width: "70", 
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
				width: "300", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				},
				formatter: function (cellvalue, options, rowObject) { 
					return '<b><a href="#" onclick="irADetalleTareas('+rowObject.tarea.idTarea+','+rowObject.anyo+','+rowObject.numExp+')">' + cellvalue + '</a></b>'
				}
			},
			{ 	name: "gestorExpediente.solicitante.nombreCompleto", 
			 	label: "label.recursoAsignado",
				align: "left", 
				width: "225", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
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
				width: "120", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.estadoAsignadoDesc", 
			 	label: "label.estadoAceptacion",
				align: "center", 
				width: "100", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.estadoEjecucionDesc", 
			 	label: "label.estadoEjecucion",
				align: "center", 
				width: "100", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.horasPrevistas", 
			 	label: "label.horasPrevistas",
				align: "center", 
				width: "80", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "tarea.fechaHoraFin", 
			 	label: "comun.fechaHoraFinalizacion",
				align: "center", 
				width: "100", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "fechaHoraFin", 
				label: "label.fechaHoraEntrega",
				align: "center", 
				width: "100",
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
        ],
        model:"ExpTareasResumen",
        usePlugins:[
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
		primaryKey: ["anyo", "numExp"],
		multiplePkToken:",",
		sortname : "ANYONUMEXPCONCATENADO, ORDEN",
		sortorder : "asc, asc",
		loadOnStartUp: false,
		onPaging: function(){
			listaTitulos = {};
		}
	});
    
	 var selectedRows = expedientesSeleccionados;
	 var expedientesIds = [];
	 for(var i=0;i<selectedRows.length;i++){
		 var j = selectedRows[i];
		 expedientesIds.push({
			 anyo: selectedRows[i].substr(0,selectedRows[i].indexOf(',')),
			 numExp: selectedRows[i].substr(selectedRows[i].indexOf(',')+1,selectedRows[i].length)
		 });
	 }
	var jsonObject = {
			listaExpediente: []
	};
	for(var k=0;k<expedientesIds.length;k++) {    

	    var item = expedientesIds[k];
	    
	    jsonObject.listaExpediente.push({ 
	        "anyo" : item.anyo,
	        "numExp"  : item.numExp
	    });
	}
    
    $("#desgloseTareasConsultaPlanif").rup_table("filter");
    
});