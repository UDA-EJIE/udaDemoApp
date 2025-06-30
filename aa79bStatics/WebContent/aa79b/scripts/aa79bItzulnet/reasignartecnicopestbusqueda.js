var asignador;
var trabajadorIzo;
function recargarPlanificacion(){
	if(asignador){
		$('#asignador_filter_table').rup_combo("select",asignador);
	}
	if(trabajadorIzo){
		$('#asignador_IZO_filter_table').rup_combo("select",trabajadorIzo);
	}
	$('#busquedaGeneral').rup_table("filter");
		$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.asignadorReasignado, "ok");
}

/*
 * ****************************
 * CREACIÓN COMBOS - INICIO
 * ****************************
 */
function cargarComboReasignarTecnicoPestBusqueda(){
	$('#reasignarTecnicoAsignadoPestBusqueda').rup_combo({
			source : "../../../../personalIZO/asignador",
			sourceParam:{
				value : "dni",
				label : "nombreCompleto" 
			}
			,blank: ""
			,width: "100%"
			,rowStriping: true
			,open : function() {
				jQuery('#reasignarTecnicoAsignadoPestBusqueda-menu').width(jQuery('#reasignarTecnicoAsignadoPestBusqueda-button').innerWidth());
			}
		});
	
	
	$('#reasignarTecnicoAsignadoPestBusqueda').rup_combo("select", 0);
	$('#reasignarTecnicoAsignadoPestBusqueda').rup_combo("clear");
	$('#reasignarTecnicoAsignadoPestBusqueda').rup_combo("setRupValue","");
}

function eliminarComboReasignarTecnico(){
	  //eliminar el combo para que se reajuste al crearlo otra vez
    if($('#reasignarTecnicoAsignadoPestBusqueda').length){
		$('#reasignarTecnicoAsignadoPestBusqueda').remove();	
	}
}

/*
 * ****************************
 * CREACIÓN COMBOS - FIN
 * ****************************
 */
jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	cargarComboReasignarTecnicoPestBusqueda();
	
	 $('#reasignarTecnicoPestBusqueda_feedback').rup_feedback({
	    	block : false,
	    	gotoTop: true, 
	    	delay: 3000
	    });
	 
	 $("#reasignarTecnicoPestBusqueda_toolbar").rup_toolbar({
			buttons:[
				{
					i18nCaption: $.rup.i18n.app.boton.volver
					,css: "fa fa-arrow-left"
					,click : 
						function(e){
							e.preventDefault();
		                    e.stopImmediatePropagation();
		                    eliminarComboReasignarTecnico();
//		    	        	vaciar variable de expedientes seleccionados
		    	        	expedientesSeleccionados = [];
		                    volverACapaGeneral();
		                }
				},
				{
					i18nCaption: $.rup.i18n.app.boton.guardar
					,css: "fa fa-floppy-o"
				    ,click : 
					function(e){
				    	e.stopImmediatePropagation();
				    	var dniTecnico = $('#reasignarTecnicoAsignadoPestBusqueda').val();
				    	if(dniTecnico!=null && "".valueOf()!== dniTecnico.valueOf() && "noDni".valueOf()!== dniTecnico.valueOf()){
				    		 //obtener de variable ya cargada desde pestBusqueda 
				    		 var selectedRows = expedientesSeleccionados;
				    		 var expedientesIds = [];
							 for(var i=0;i<selectedRows.length;i++){
								 var j = selectedRows[i];
								 expedientesIds.push({
									 anyo: selectedRows[i].substr(0,selectedRows[i].indexOf('-')),
									 numExp: selectedRows[i].substr(selectedRows[i].indexOf('-')+1,selectedRows[i].length)
								 });
							 }
				    		var jsonObject = {
				        			listaExpediente: []
				        	};
				    		for(var k=0;k<expedientesIds.length;k++) {    

				    		    var item = expedientesIds[k];
				    		    
				    		    var jsonObjTecnico = { 
				        		        "dni" : dniTecnico
				        		    };

				    		    jsonObject.listaExpediente.push({ 
				    		        "anyo" : item.anyo,
				    		        "numExp"  : item.numExp,
				    		        "tecnico" : jsonObjTecnico
				    		    });
				    		}
				        	jQuery.ajax({
				        		type: "POST",
				        		url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/asignarAsignadorAExpedientes",
				        		dataType: "json",
				        		contentType: 'application/json', 
				                data: $.toJSON(jsonObject),
				        		cache: false,
					    		success: function (data) {
				    	           if (data != null && data > 0) {
										expedientesSeleccionados = [];
										eliminarComboReasignarTecnico();
										volverACapaGeneral();
										//escoger seleccionados en combos asignador y trabajador izo
										asignador = $('#asignador_filter_table').rup_combo("getRupValue");
										trabajadorIzo = $('#asignador_IZO_filter_table').rup_combo("getRupValue");
										$('#asignador_filter_table').rup_combo("reload");
										$('#asignador_IZO_filter_table').rup_combo("reload");
										llamadasFinalizadas("recargarPlanificacion")
				    	           }
					    	     },
					    	     error: function (){
					    	    	 $('#reasignarTecnicoPestBusqueda_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
					    	     }
				        	});
				        	
				        	
				    	}else{
				    		$('#reasignarTecnicoPestBusqueda_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.seleccioneTecnicoAReasignar, "error");
				    	}
				    }
				}
			]
		});
	
});