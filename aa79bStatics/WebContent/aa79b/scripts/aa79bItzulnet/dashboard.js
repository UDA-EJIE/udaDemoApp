/*
 * ****************************
 * VARIABLE GLOBALES - INICIO
 * ****************************
 */
var anyoExpediente;
var idExpediente;
var tipoExp;
var estadoExp;
var faseExp;
var idTarea;
var idTipoTarea;
var ejecutarTareaConsulta = false;
var capaPestGeneral = "";
var capaDetalleExpediente = "";
var capaTareasExpediente = "";
var capaDesgloseTareas = "";


/* las que voy necesitando para que funcione la navegacion que ya está hecha ... */
var capaDesgloseTareas = "";
/* las que voy necesitando para que funcione la navegacion que ya está hecha ... */
var pestSeleccionada = 0;

var formatoPestana = true;
var capaDashboardGraficos = "";
var funcionCallback = "";

var filtroDatos = "";
var filtroTypeDatos = "";

//parámetro de la pestaña de carga de trabajo
var filtroDatosCarga = "";
var txtFiltroCarga="";
/*
 * ****************************
 * VARIABLE GLOBALES - FIN
 * ****************************
 */

/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function volverACapaGeneral(){
	$("#divPlanificacionCapa").detach();
	$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
	$("#divPlanificacionCapa").html(capaPestGeneral);
	//recarga generica de la rup_tablew que exista....
	$(".ui-jqgrid-btable").trigger('reloadGrid');
}

function volverACapaGeneralDashboard(){
	
	//el regreso normal con el detach()
	$("#divDashboardCapa").detach();
	$("#divDashboard").append("<div id='divDashboardCapa' class='rup-table-container'></div>");
	$("#divDashboardCapa").html(capaDashboardGraficos);
	
	//para provocar la recarga de la pestaña activa al volver
	$("#tabsDashboard").rup_tabs("selectTab",{
		idTab: "tabsDashboard",
		position: parseInt(pestSeleccionada)
	});
	$("#tabsDashboard").rup_tabs("loadTab",{
		idTab: "tabsDashboard",
		position: parseInt(pestSeleccionada)
	});
	
}

	// la primera es la que incluye todo
	// la segunda es sobre la que se hace el detach
	// las siguientes son capas intermedias necesarias para la navegación de las páginas que estoy incrustando
	// la última es en la que se mete el contenido que devuelve el ajax
	function cambiarCapaGeneralDashboard(url,arrayCapas){
		bloquearPantalla();
		eliminarDialogs();
		$.rup_ajax({
			url: url 
			,async: false 
			,success:function(data, textStatus, jqXHR){
				capaDashboardGraficos = $('#divDashboardGeneral').detach();
				
				if (typeof(arrayCapas) === "undefined" || arrayCapas.length === 0){
					$('#divDashboardCapa').html(data);
				}else{
					var estructuraDeCapas = "<div id='"+arrayCapas[0]+"' class='container-fluid'><div id='"+arrayCapas[1]+"'>";
					for(var i=2; i<arrayCapas.length; i++){
						estructuraDeCapas +="<div id='"+arrayCapas[i]+"'>"; 
					}
					for(var i=0; i<arrayCapas.length; i++){
						estructuraDeCapas += "</div>"; 
					}
					$('#divDashboardCapa').append(estructuraDeCapas);
					$('#'+arrayCapas[arrayCapas.length-1]).html(data);
				}
				desbloquearPantalla();
			},
			error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
			}
		});
	}
	
	/* Funciones de callback para los filtros que tienen que esperar a que los componentes estén creados y cargados */
	
	function callbackEstudioPdteTramitacion(){
		$('#fases_filter_table').rup_combo("setRupValue","4");
		$("#estudioExpedientes").rup_table("filter");
	}
	
	/* FIN Funciones de callback  */
	
	
	
	
	
	function enlazarDestinoGrafica(idGrafica, idx, tipo, label){
		switch (idGrafica) { 
			case 'graficoEstudioExpedientesBar':
				switch (idx) {
					case 0:
						//en estudio
						cambiarCapaGeneralDashboard('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/enPestana');	
						if (tipo === 1){					
							//expeds del tecnico de sesión
							$('#tecnicoAsignado_filter_table').rup_autocomplete("set",dniUsuSesion,nombreUsuSesion);
						}
						$("#estudioExpedientes").rup_table("filter");
						break;
					case 1:
						//Fase Pdte tramitación clte
						cambiarCapaGeneralDashboard('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/enPestana');	
						if (tipo === 1){					
							//expeds del tecnico de sesión
							$('#tecnicoAsignado_filter_table').rup_autocomplete("set",dniUsuSesion,nombreUsuSesion);
						}
						llamadasFinalizadas("callbackEstudioPdteTramitacion");
						break;
					case 2: 
						//Pdtes de anulacion
						cambiarCapaGeneralDashboard('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/enPestana');	
						if (tipo === 1){	
							//hacer en la página de destino un nuevo buscador por técnico
							//expeds del tecnico de sesión
							$('#tecnicoAsignado_filter_table').rup_autocomplete("set",dniUsuSesion,nombreUsuSesion);
						}
						$("#busquedaGestAnul").rup_table("filter");
						break;
				}
				break;
				
			case 'graficoPlanificacionExpedientesBar':
				//AQUI TENDRÍA QUE HACER LA EQUIVALENCIA ENTRE MIS idx Y LOS DE PESTRESUMEN.JS
				//Se rellena el filtroDatos con el valor de la fila seleccionada 
	    	   if(idx !== 3){
	    		   filtroDatos = "expedientes-" + (idx + 1);   
	    	   }else{
	    		   filtroDatos = "expedientes-" + (idx + 2);
	    	   }
	    	   //Se rellena el filtroTypeDatos con el valor del tipo de fila seleccionada: mis expedientes o tods
		       filtroTypeDatos = tipo;
		       cambiarCapaGeneralDashboard('/aa79bItzulnetWar/dashboard/planificacion/datos/maint',['divPlanificacion','divPlanificacionCapa','divPlanificacionExpedientesGeneral']);	
				
		       break;
			case 'graficoPlanificacionTareasBar':	
				//En dashboard, como hay una nueva barra y es la primera, no hace falta el +1
				filtroDatos = "tareas-" + (idx);
				//Se rellena el filtroTypeDatos con el valor del tipo de fila seleccionada: mis expedientes o tods
			    filtroTypeDatos = tipo;
			    cambiarCapaGeneralDashboard('/aa79bItzulnetWar/dashboard/planificacion/datos/maint',['divPlanificacion','divPlanificacionCapa','divPlanificacionExpedientesGeneral']);	
				break;
			case 'graficoCargaTareasBar':	
				filtroDatosCarga = idx+1;
				txtFiltroCarga = label;
				cambiarCapaGeneralDashboard('/aa79bItzulnetWar/dashboard/cargatrabajo/datos/maint',['divPlanificacion','divPlanificacionCapa','divPlanificacionExpedientesGeneral']);	
				break;
			default:
				alert('Se ha producido un error al enlazar el contenido.');
				break;
		}
		desbloquearPantalla();
	}	
	
	
/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false, global: true });
	//Sistema de pestañas
	$("#tabsDashboard").rup_tabs({
		tabs : [
			{i18nCaption:"pestEstudio", url: "/aa79bItzulnetWar/dashboard/estudio/maint"},
			{i18nCaption:"pestPlanificacion", url: "/aa79bItzulnetWar/dashboard/planificacion/maint"},
			{i18nCaption:"pestCargatrabajo", url: "/aa79bItzulnetWar/dashboard/cargatrabajo/maint"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		]
		,select: function(e){
			//index de la pestaña seleccionada, para poderla recargar cuando sea necesario
			if (!isEmpty(e.currentTarget)){
				pestSeleccionada = e.currentTarget.attributes.ruplevel.nodeValue;
			}
			
		}
	});
	if (!esTecnicoIzo && !esTraductorOInterprete && !esAsignador){
		window.location.href = "/aa79bItzulnetWar/dashboard/welcome/maint";
	}else{
		if (!esTecnicoIzo && !esTraductorOInterprete){
			$("#tabsDashboard").rup_tabs("removeTab",{ idTab: "tabsDashboard", position: $("#tabsDashboard ul li").length});
		}
		if (!esAsignador){
			$("#tabsDashboard").rup_tabs("removeTab",{ idTab: "tabsDashboard", position: $("#tabsDashboard ul li").length - 1});
		}
		if (!esTecnicoIzo){
			$("#tabsDashboard").rup_tabs("removeTab",{ idTab: "tabsDashboard", position: 1});
		}
	}
	
	
});
