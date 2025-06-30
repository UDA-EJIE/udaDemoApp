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
var filtroDatos = "";
var filtroTypeDatos = "";
var idiomaDestino = ""
var	fechaFinalIZO="";
var	horaFinalIZO="";
var expedienteConfidencial = 'N';
var esCorredaccion;
var	fechaIniInter="";
var	horaIniInter="";
var	fechaFinInter="";
var	horaFinInter=""; 
var pestDatos = false;
var	obligarXliff= 'N';

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
function irCargaTrabajo(){
	window.open('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/maint');
}

function irAsignado(){
	window.open('/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/maint');
}

function volverACapaGeneral(){
	$("#divPlanificacionCapa").detach();
	$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
	$("#divPlanificacionCapa").html(capaPestGeneral);
	if ($($("#tabsPlantificacion ul li")[1]).hasClass("ui-tabs-active")){
		//intentos para reconfigurar el multifilter.
		var configMultifilter = $("#busquedaGeneral").data('settings');
		$("#busquedaGeneral_filter_filterButton_dropdown").remove();
		$("#busquedaGeneral_multifilter_combo_label").remove();
		$("#busquedaGeneral_multifilter_combo").remove();
		$("#busquedaGeneral_multifilter_dropdownDialog").remove();
		$("#busquedaGeneral").rup_table ("preConfigureMultifilter",configMultifilter );
		$("#busquedaGeneral").rup_table ("configureMultifilter",configMultifilter );
		$("#busquedaGeneral").rup_table ("postConfigureMultifilter",configMultifilter );
		//
		$("#busquedaGeneral").trigger("reloadGrid");
	}else{
		$("#busquedaDatos").trigger("reloadGrid");
	}
	
}
function volverACapaDesgloseTareas(){
	$("#divPlanificacionCapa").detach();
	$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
	$("#divPlanificacionCapa").html(capaDesgloseTareas);
	$("#desgloseTareas").rup_table("filter");
	capaDesgloseTareas = "";
}
/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	if (typeof(desdeConsultaExpedientes) != "undefined"){
		eliminarDialogs();
		$.rup_ajax({
		   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/detalleexpediente/maint' 
		   	 ,success:function(data, textStatus, jqXHR){
		   		 $('#divPlanificacionExpedientesGeneral').detach();
		   		 $("#divPlanificacionCapa").html(data);
		   		desbloquearPantalla();
		   	 },
		   	 error: function (XMLHttpRequest, textStatus, errorThrown){
				alert($.rup.i18n.app.mensajes.errorGenericoCapas);
		   	 }
		 });
	}else{
		//Sistema de pestañas
		$("#tabsPlantificacion").rup_tabs({
			tabs : [
				{i18nCaption:"pestSituacionActual", tabs: [
					{i18nCaption:"pestResumen", url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/resumen/maint"},
					{i18nCaption:"pestDatos", url:"/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/datos/maint"}
					]
				},
				{i18nCaption:"pestBusqueda", url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/busquedageneral/maint"}
			],
			cache: false,
			fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
				  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
			]
		});
		
		$("#planificacionExpedientes_toolbar").rup_toolbar({
			buttons:[
				{
					i18nCaption: $.rup.i18n.app.boton.asignadoProveedores
					,right:true
					,css: "fa fa-eye"
						,click : 
							function(e){
								e.preventDefault();
					 			e.stopImmediatePropagation();
								irAsignado();
			         }
				},
				{
					i18nCaption: $.rup.i18n.app.boton.cargarIZO
					,right:true
					,css: "fa"
					,click : 
						function(e){
							e.preventDefault();
				 			e.stopImmediatePropagation();
							irCargaTrabajo();
		                }
				}
				
			]
		});

	}
	
});
