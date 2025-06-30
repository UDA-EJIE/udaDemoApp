
var rGCAutocompleteCreado = false;
var rGCEntidad = "-1";
var rGCCodigo = -1;
var rGCEntidadCheckeada = "-1";

/*
 * ****************************
 * FEEDBACK - INICIO
 * ****************************
 */
function crearFeedbackReasignarGestorConsulta(){
	$('#reasignarGestorConsulta_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
}
/*
 * ****************************
 * FEEDBACK - FIN
 * ****************************
 */

/*
 * ****************************
 * TOOLBAR - INICIO
 * ****************************
 */
function crearToolbarReasignarGestorConsulta(){
	$("#reasignarGestorConsulta_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    eliminarComborGCIdEntidadSolicitante_filter_table();
//	    	        	vaciar variable de expedientes seleccionados
	    	        	expedientesSeleccionados = [];
	    	        	volverACapaGeneralConsulta();
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(e){
			    	e.stopImmediatePropagation();
			    	var dniTecnico = $('#rGCContactoGestor_filter_table').rup_autocomplete("getRupValue");
			    	if(dniTecnico!=null && "".valueOf()!== dniTecnico.valueOf() && "noDni".valueOf()!== dniTecnico.valueOf()){
			    		 //obtener de variable ya cargada desde consulta 
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
			    		    
			    		    var jsonSolicitante = { 
			        		        "dni" : dniTecnico
			        		    };
			    		    var jsonGestor = {
			    		    	"solicitante" : jsonSolicitante	
			    		    }

			    		    jsonObject.listaExpediente.push({ 
			    		        "anyo" : item.anyo,
			    		        "numExp"  : item.numExp,
			    		        "gestorExpediente" : jsonGestor
			    		    });
			    		}
			        	jQuery.ajax({
			        		type: "POST",
			        		url: "/aa79bItzulnetWar/consultas/asignarGestorAExpedientes",
			        		dataType: "json",
			        		contentType: 'application/json', 
			                data: $.toJSON(jsonObject),
			        		cache: false,
				    		success: function (data) {
			    	           if (data != null && data > 0) {
									expedientesSeleccionados = [];
									eliminarComborGCIdEntidadSolicitante_filter_table();
									volverACapaGeneralConsulta();
									$('#consultaExpedientes').trigger('reloadGrid');
			    	           		$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.gestorReasignado, "ok");
			    	           }
				    	     },
				    	     error: function (){
				    	    	 $('#reasignarGestorConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
				    	     }
			        	});
			        	
			        	
			    	}else{
			    		$('#reasignarGestorConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.seleccioneTecnicoAReasignar, "error");
			    	}
			    }
			}
		]
	});
}
/*
 * ****************************
 * TOOLBAR - FIN
 * ****************************
 */

/*
 * ****************************
 * COMBOS - INICIO
 * ****************************
 */

function rGCCrearComboEntidadGestora(valEntidad){
	rGCCodigo="-1";
	$('#rGCIdEntidadSolicitante_filter_table').rup_combo({
			source : "/aa79bItzulnetWar/entidad/entidadesConGestorActivo/"+valEntidad,
			sourceParam : {
				value: "codigoCompleto",
				label : $.rup.lang === 'es' ? "descEs"
						: "descEu"
			},
			blank:"",
			width: "100%",
			getText: false,
			rowStriping: true,
			open : function() {
				var id = $(this).attr("id");
				$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
			},
			onLoadSuccess: function(){
				$('#rGCIdEntidadSolicitante_filter_table').rup_combo("setRupValue","");
			}
		});
}

function rGCCrearAutocompleteContactoGestor(){
	$('#rGCContactoGestor_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/personalIZO/gestoresActivos/"+rGCEntidad+"/"+rGCCodigo,
		sourceParam:{
			value : "dni",
			label : "nombreCompleto" 
		},
		getText: false,
		width: "100%",
		open : function() {
			var id = $(this).attr("id");
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("max-width", $("#"+id).width());
		}
	}); 
}

function rGCAnyadirEventoChangeAComboEntidadGestora(){
	$('select[id=rGCIdEntidadSolicitante_filter_table]').change(function(){
 		if(autocompleteCreado){
 			 var codigoCompleto =  $('#rGCIdEntidadSolicitante_filter_table').rup_combo("getRupValue");
          	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
          		rGCCodigo = -1;
          		rGCEntidad = rGCEntidadCheckeada; 
          	 }else{
          		var datosEntidadSeleccionada = codigoCompleto.split("_");	
          		rGCCodigo = datosEntidadSeleccionada[1];
          		rGCEntidad = datosEntidadSeleccionada[0];
          	 }
          	 //destruimos y volvemos a crear el autocomplete
          	$('#rGCContactoGestor_filter_table').rup_autocomplete("destroy");
          	$("#rGCAutocompleteContainer_contactoGestor_filter_table").remove();
          	var autocompleteGestor = $('<div id="rGCAutocompleteContainer_contactoGestor_filter_table" ><label for="rGCContactoGestor_filter_table" class="control-label" data-i18n="label.gestor">'+$.rup.i18n.app.label.gestor+'</label><input id="rGCContactoGestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni" /></div>');
          	autocompleteGestor.appendTo("#rGCDiv_contactoGestor_filter_table");
          	rGCCrearAutocompleteContactoGestor();
 		}
 		if(!rGCAutocompleteCreado){
 			rGCAutocompleteCreado=true;
 		}
 	});
}

function rGCFuncionalidadRelacionadaComponentes(){
	$('input[name=tipoEntidad]').change(function(){
		//al clickar en un radiobutton debemos volver a cargar el combo de entidades 
		//y el autocomplete de gestores
		rGCEntidadCheckeada = $(this).val();
 		 //si el valor de la entidad es todos, lo ponemos a -1 para poder controlarlo 
 		 //en las consultas del combo y autocomplete
 		if("".localeCompare(rGCEntidadCheckeada)==0){
 			rGCEntidadCheckeada = "-1";
 			rGCEntidad = "-1";
		}
 		//valor del campo para filtrar por entidad
		$('#rGCGestorExpedienteEntidadTipo').val($(this).val());
		rGCCrearComboEntidadGestora($(this).val());
		rGCAnyadirEventoChangeAComboEntidadGestora();
	});
}


function eliminarComborGCIdEntidadSolicitante_filter_table(){
	  //eliminar el combo para que se reajuste al crearlo otra vez
  if($('#rGCIdEntidadSolicitante_filter_table').length){
		$('#rGCIdEntidadSolicitante_filter_table').remove();	
	}
}
/*
 * ****************************
 * COMBOS - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	/* ENTIDAD GESTORA */
	rGCCrearComboEntidadGestora("");
	rGCFuncionalidadRelacionadaComponentes();
	rGCCrearAutocompleteContactoGestor();
	
	// Filtro - Cargar combo de entidad en funcion del tipo de entidad seleccionado
	$('input[name=tipoEntidad]:first').click();
	crearFeedbackReasignarGestorConsulta();
	crearToolbarReasignarGestorConsulta();
});