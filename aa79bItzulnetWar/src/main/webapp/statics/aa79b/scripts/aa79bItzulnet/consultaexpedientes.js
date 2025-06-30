nora_response();
var autocompleteCreado = false;
var entidad = "-1";
var codigo = -1;
var entidadCheckeada = "-1";
var expedientesRelSeleccionados = [];
var expedientesSeleccionados = [];
var capaConsultaExp = "";
var desdeConsultaExpedientes = true;
var filterFormObjectConsExp;
var listIdObjects = [];

var listEtiquetasSeleccionadas = [];
var anyoActualFilter = true;
var bopv = false;
var tramitagune = false;
/*
 * ****************************
 * FUNCIONES - INICIO
 * ****************************
 */
function cambiarCapaGeneral(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaConsultaExp = $('#divConsultaExpedientes').detach();
	   		 $("#divConsultaExpedientesCapa").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
			desbloquearPantalla();
	   	 }
	 });
}

function volverACapaGeneralConsulta(){
	$("#divConsultaExpedientesCapa").detach();
	$("#divConsultaExpedientesGeneral").html("<div id='divConsultaExpedientesCapa'></div>");
	$("#divConsultaExpedientesCapa").html(capaConsultaExp);
	$("#consultaExpedientes").rup_table("resetSelection");
	$("#consultaExpedientes").trigger("reloadGrid");
}

function irADetalleExpediente(elAnyo, elNumero, elEstado, elOrigen){
	if(consExp.estadoExpediente.enEstudio.localeCompare(elEstado.toString())===0 && esTecnicoIzo){
		// ir a detalle estudio de expedientes
		anyo = elAnyo;
		numExp = elNumero;
		dataOrigen = elOrigen;
		bloquearPantalla();
		cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/enPestana');
	}else{
		// ir a detalle en modo consulta
		cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExp/'+elAnyo+'/'+elNumero);
	}
}

function irAIncluirMetadatosBusqueda(){
	volcarListaIdsAExpSeleccionados("-");
	cambiarCapaGeneral('/aa79bItzulnetWar/consultas/metadatosbusquedaconsulta/maint');
}

function irAReasignarNuevoGestor(){
	volcarListaIdsAExpSeleccionados("-");
	cambiarCapaGeneral('/aa79bItzulnetWar/consultas/reasignargestorconsulta/maint');
}

function habilitarFiltrosComunes(){
	habilitarCombo('publicarBOPVConsulta_filter_table', true, consExp.activo.todos);
	habilitarCombo('previstoParaBOPVConsulta_filter_table', true, consExp.activo.todos);
	habilitarCombo('idiomaDestinoConsulta_filter_table', true, consExp.activo.todos);
	habilitarCombo('indUrgenteConsulta_filter_table', true, consExp.activo.todos);
	habilitarCombo('indPublicadoBoeConsulta_filter_table', true, consExp.activo.todos);
	habilitarCombo('tipoDocumentoExpediente_filter_table', true, consExp.activo.todos);
	habilitarCampoFecha("fechaDesdeEntregaConsulta_filter", true, "");
	habilitarCampoFecha("fechaHastaEntregaConsulta_filter", true, "");
	if(esTecnicoIzo){
		habilitarCombo('reqPresupuestoConsulta_filter_table', true, consExp.activo.todos);
		habilitarCombo('reqNegociacionConsulta_filter_table', true, consExp.activo.todos);
		habilitarCombo('indConfidencialConsulta_filter_table', true, consExp.activo.todos);
		habilitarCombo('indFacturableConsulta_filter_table', true, consExp.activo.todos);
		$('#grupotrabajoConsulta_filter_table').rup_combo('clear');
		$('#grupotrabajoConsulta_filter_table').rup_combo('enable');
	}
}

function deshabilitarFiltrosComunes(){
	deshabilitarCampoFecha("fechaDesdeEntregaConsulta_filter", true, "");
	deshabilitarCampoFecha("fechaHastaEntregaConsulta_filter", true, "");
	deshabilitarCombo('indCorredaccionConsulta_filter_table', true, consExp.activo.todos);
	deshabilitarCombo('publicarBOPVConsulta_filter_table', true, consExp.activo.todos);
	deshabilitarCombo('previstoParaBOPVConsulta_filter_table', true, consExp.activo.todos);
	deshabilitarCombo('idiomaDestinoConsulta_filter_table', true, consExp.activo.todos);
	deshabilitarCombo('indUrgenteConsulta_filter_table', true, consExp.activo.todos);
	deshabilitarCombo('indPublicadoBoeConsulta_filter_table', true, consExp.activo.todos);
	deshabilitarCombo('tipoDocumentoExpediente_filter_table', true, consExp.activo.todos);
	if(esTecnicoIzo){
		deshabilitarCombo('reqPresupuestoConsulta_filter_table', true, consExp.activo.todos);
		deshabilitarCombo('reqNegociacionConsulta_filter_table', true, consExp.activo.todos);
		deshabilitarCombo('indConfidencialConsulta_filter_table', true, consExp.activo.todos);
		deshabilitarCombo('indFacturableConsulta_filter_table', true, consExp.activo.todos);
		$('#grupotrabajoConsulta_filter_table').rup_combo('clear');
		$('#grupotrabajoConsulta_filter_table').rup_combo('disable');
		
	}
}

/*
 * ****************************
 * FUNCIONES - FIN
 * ****************************
 */

/*
 * ****************************
 * CREACION COMBOS - INICIO
 * ****************************
 */
function fncTipoExpedienteCombo(){
	$("#idTipoExpedienteConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,orderedByValue: false
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpedienteConsulta_filter_table-menu').width(jQuery('#idTipoExpedienteConsulta_filter_table-button').innerWidth());
		},
		select: function(){
			var tipoExpSeleccionado = $("#idTipoExpedienteConsulta_filter_table").rup_combo("getRupValue");
			if(consExp.tipoExpediente.traduccion === tipoExpSeleccionado || consExp.tipoExpediente.tradrev === tipoExpSeleccionado){
				//si es de tipo traduccion habilitar combo corredaccion y seleccionarlo a 'No' por defecto
				habilitarCombo('indCorredaccionConsulta_filter_table', true, consExp.activo.no);
				habilitarFiltrosComunes();
				
			}else if(consExp.tipoExpediente.interpretacion === tipoExpSeleccionado){
				//si es de tipo interpretacion deshabilitamos los campos que no tengan relacion con el tipo de expediente
				deshabilitarFiltrosComunes();
			}else{
				//en los demas casos deshabilitar el combo con seleccion a todos
				deshabilitarCombo('indCorredaccionConsulta_filter_table', true, consExp.activo.todos);
				habilitarFiltrosComunes();
			}
			
		}
	});
}

function fncEstadosCombo(){
	$("#estadosConsulta_filter_table").rup_combo({
		 source: "/aa79bItzulnetWar/estadosexpediente/findAllEstadosExpSinAltaConEstadoEliminado",
		 sourceParam : {
			 value: "id",
			 label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
		 }
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		},onLoadSuccess: function(){
			var id = $(this).attr("id");
			$("#"+id).rup_combo("select",0);
			fncFasesCombo();
			$("#"+id).rup_combo("setRupValue","");
		}
	});
}

function fncFasesCombo(){
	$("#fasesConsulta_filter_table").rup_combo({
		parent: ["estadosConsulta_filter_table"],
		source: "/aa79bItzulnetWar/fasesexpediente/remoteEstado",
		sourceParam : {
			 value: "id",
			 label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
	});
}

function crearComboEntidadGestoraConsulta(valEntidad){
	codigo="-1";
	$('#idEntidadGestoraConsulta_filter_table').rup_combo({
			source : "/aa79bItzulnetWar/entidad/exprel/"+valEntidad,
			sourceParam : {
				value: "codigoCompleto",
				label : $.rup.lang === 'es' ? "descEs"
						: "descEu"
			},
			blank:"",
			width: "362",
			getText: false,
			rowStriping: true,
			onLoadSuccess: function(){
				//si no carga ningun valor, destruir el desplegable del autocomplete si tiene algun valor
				if($('#idEntidadGestoraConsulta_filter_table').rup_combo("isDisabled")){
					if($('#contactoGestorConsulta_filter_table_menu').length){
						$('#contactoGestorConsulta_filter_table_menu').remove();
						$('#contactoGestorConsulta_filter_table_label').val("");
					}
				}
			},
			open : function() {
				jQuery('#idEntidadGestoraConsulta_filter_table-menu').width(jQuery('#idEntidadGestoraConsulta_filter_table-button').innerWidth());
			}
		});
	$('#labelEntidadGestoraConsulta_filter_table').text($.rup.i18n.app.label.todasTipoEntidad);
}

function fncPublicarBopvCombo(){
	$("#publicarBOPVConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#publicarBOPVConsulta_filter_table-menu').width(jQuery('#publicarBOPVConsulta_filter_table-button').innerWidth());
		}
	});
}

function fncPrevistoParaBopvCombo(){
	$("#previstoParaBOPVConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#previstoParaBOPVConsulta_filter_table-menu').width(jQuery('#previstoParaBOPVConsulta_filter_table-button').innerWidth());
		}
	});
}

function fncIdiomaDestinoCombo(){
	$("#idiomaDestinoConsulta_filter_table").rup_combo({
		 source: "/aa79bItzulnetWar/idiomadestino/idiomasAlta",
		 sourceParam : {
			 value: "idIdioma",
			 label : "descIdiomaEu"
		 }
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,orderedByValue: true
		,open: function(){
			jQuery('#idiomaDestinoConsulta_filter_table-menu').width(jQuery('#idiomaDestinoConsulta_filter_table-button').innerWidth());
		}
	});
}

function fncindCorredaccionCombo(){
	$("#indCorredaccionConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#indCorredaccionConsulta_filter_table-menu').width(jQuery('#indCorredaccionConsulta_filter_table-button').innerWidth());
		},onLoadSuccess: function(){
			//en la carga inicial, tipo de expediente es todos, asi que deshabilitamos el combo, porque solo esta disponible para los de tipo traduccion
			$("#indCorredaccionConsulta_filter_table").rup_combo("setRupValue",consExp.activo.todos);
			$("#indCorredaccionConsulta_filter_table").rup_combo("disable");
		}
	});
}

function fncindUrgenteCombo(){
	$("#indUrgenteConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#indUrgenteConsulta_filter_table-menu').width(jQuery('#indUrgenteConsulta_filter_table-button').innerWidth());
		}
	});
}

function fncindPublicadoBoeCombo(){
	$("#indPublicadoBoeConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#indPublicadoBoeConsulta_filter_table-menu').width(jQuery('#indPublicadoBoeConsulta_filter_table-button').innerWidth());
		}
	});
}

function fncSubsRequeridaCombo(){
	$("#subsRequeridaConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,rowStriping: true
			,open: function(){
				jQuery('#subsRequeridaConsulta_filter_table-menu').width(jQuery('#subsRequeridaConsulta_filter_table-button').innerWidth());
			}
	});
}

function estadoInicialSubsRequeridaEstadoCombo(){
	// en la carga inicial de la pantalla lo deshabilitamos ya que el combo de subsanacion requerida tiene el valor de todos
	$('#subsRequeridaEstadoConsulta_filter_table').rup_combo("disable");
}

function fncSubsRequeridaEstadoCombo(){
	$("#subsRequeridaEstadoConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#subsRequeridaEstadoConsulta_filter_table-menu').width(jQuery('#subsRequeridaEstadoConsulta_filter_table-button').innerWidth());
		}
	});
	estadoInicialSubsRequeridaEstadoCombo();
	//anyadimos el onchange al combo de subsanacion requerida para habilitar o deshabilitar el de estado
	$('#subsRequeridaConsulta_filter_table').change(function(){
		//obtenemos el valor del combo subsanacion requerida
		var subsRequerida = $('#subsRequeridaConsulta_filter_table').rup_combo("getRupValue");
		if("S".localeCompare(subsRequerida)==0){
			//si esta deshabilitado lo habilitamos
			if($('#subsRequeridaEstadoConsulta_filter_table').rup_combo("isDisabled")){
				//habilitamos el combo
				$('#subsRequeridaEstadoConsulta_filter_table').rup_combo("enable");
				//seleccionamos el valor todos
				$('#subsRequeridaEstadoConsulta_filter_table').rup_combo("select","");
			}
		}else{
			if(!$('#subsRequeridaEstadoConsulta_filter_table').rup_combo("isDisabled")){
				//seleccionamos el valor todos
				$('#subsRequeridaEstadoConsulta_filter_table').rup_combo("select","");
				//si esta habilitado lo deshabilitamos
				$('#subsRequeridaEstadoConsulta_filter_table').rup_combo("disable");
			}
		}
	});
}

//function fncMetadatosBusquedaCombo(){
//	$('#metadatosBusquedaConsulta_filter_table').rup_combo({
//		source : "/aa79bItzulnetWar/administracion/datosmaestros/metadatosbusqueda/metadatosAlta",
//		sourceParam : {
//	        value: ""+"id"+"",
//	        label : "descEu"
//	    }
//	   	,submitAsString: true,
//		selected : [], // value && index
//		ordered : false,
//		width: "350",
//		multiselect : true,
//		rowStriping : true,
//		open : function() {
//			//ocultar campo de seleccionados
//			$('#metadatosBusqueda-preview').hide();
//			//eliminar contenido
//			$('#metadatosBusqueda-preview fieldset div .badge').remove();
//		},
//		close : function() {
//			//obtener el String con los ids de los metadatos seleccionados
//			var stringValues = $(this).rup_combo("getRupValue");
//			if(stringValues){
//				//si no esta vacio crear un array con los ids
//				var values = stringValues.split(",");
//				//por cada valor pintar su elemento contenedor
//				$.each(values, function(i, e) {
//					$('#metadatosBusqueda-preview fieldset div').append('<span id="metadatosBusqueda_'+ e + '" class="badge badge-pill badge-default itzulnet-pill">'
//							+ $('select[id="metadatosBusquedaConsulta_filter_table"] option[value="'+ e+ '"]').text()
//							+ '<button type="button" class="btn"><i class="fa fa-times" aria-hidden="true"></i></button>'
//							+ '</span>');
//				});
//				//mostrar  campo de seleccionados
//				$('#metadatosBusqueda-preview').show();
//			}else {
//				//ocultar campo de seleccionados si no hay seleccionados
//				$('#metadatosBusqueda-preview').hide();
//			}
//
//			// Funcionalidad de quitar badges
//			$('#metadatosBusqueda-preview fieldset div .badge-pill button').each(function(i,e){
//				$(e).on('click',function(){
//					//obtener string de ids de metadatos seleccionados
//					var stringSelected = $('#metadatosBusquedaConsulta_filter_table').rup_combo("getRupValue");
//					//array de ids
//					var arrSelected = stringSelected.split(",");
//					//id de elemento a eliminar
//					var codTema = $(this).parent().attr('id').split('_')[1];
//					//eliminar del array de seleccionados si esta
//					arrSelected.splice($.inArray(codTema, arrSelected), 1);
//					//volver a seleccionar en el combo los elementos del array 
//					$('#metadatosBusquedaConsulta_filter_table').rup_combo("clear");
//					$('#metadatosBusquedaConsulta_filter_table').rup_combo("select", arrSelected);
//					//eliminar el elemento
//					$(this).parent().remove();
//					//si al eliminar el elemento no hay mas seleccionados, ocultar el campo de seleccionados
//					if(arrSelected.length==0){
//						$('#metadatosBusqueda-preview').hide();
//					}
//				});
//			});
//		}
//	});
//}

function fncTiposDocumentoCombo(){
	$('#tipoDocumentoExpediente_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento",
		sourceParam :{
			label: $.rup.lang === 'es' ? "descConRelevanciaEs":"descConRelevanciaEu", 
			value: "id",
			style:"css"
		}
		,blank: ""
		,width: "250"
		,ordered: false	
		,rowStriping: true
		,open: function(){
	        jQuery('#tipoDocumentoExpediente_filter_table-menu').width(jQuery('#tipoDocumentoExpediente_filter_table-button').width());
	    }
		,onLoadSuccess: function(){
			$('#tipoDocumentoExpediente_filter_table').rup_combo('select','');
		}
	});
}

function fncReqPresupuestoConsultaCombo(){
	$("#reqPresupuestoConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#reqPresupuestoConsulta_filter_table-menu').width(jQuery('#reqPresupuestoConsulta_filter_table-button').innerWidth());
		}
	});
}

function estadoInicialEstadoAceptacionPresupuestoConsultaCombo(){
	// en la carga inicial de la pantalla lo deshabilitamos ya que el combo de presupuesto requerido tiene el valor de todos
	$('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("disable");
}

function fncEstadoAceptacionPresupuestoConsultaCombo(){
	$("#estadoAceptacionPresupuestoConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoAceptacionPresupuestoConsulta_filter_table-menu').width(jQuery('#estadoAceptacionPresupuestoConsulta_filter_table-button').innerWidth());
		}
	});
	estadoInicialEstadoAceptacionPresupuestoConsultaCombo();

	//anyadimos el onchange al combo de presupuesto requerido para habilitar o deshabilitar el de estado
	$('#reqPresupuestoConsulta_filter_table').change(function(){
		//obtenemos el valor del combo presupuesto requerido
		var presRequerido = $('#reqPresupuestoConsulta_filter_table').rup_combo("getRupValue");
		if("S".localeCompare(presRequerido)==0){
			//si esta deshabilitado lo habilitamos
			if($('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("isDisabled")){
				//habilitamos el combo
				$('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("enable");
				//seleccionamos el valor todos
				$('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("select","");
			}
		}else{
			if(!$('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("isDisabled")){
				//seleccionamos el valor todos
				$('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("select","");
				//si esta habilitado lo deshabilitamos
				$('#estadoAceptacionPresupuestoConsulta_filter_table').rup_combo("disable");
			}
		}
	});
}

function fncReqNegociacionConsultaCombo(){
	$("#reqNegociacionConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,rowStriping: true
			,open: function(){
				jQuery('#reqNegociacionConsulta_filter_table-menu').width(jQuery('#reqNegociacionConsulta_filter_table-button').innerWidth());
			}
	});
}

function estadoInicialEstadoNegociacionPresupuestoConsultaCombo(){
	// en la carga inicial de la pantalla lo deshabilitamos ya que el combo de negociacion requerida tiene el valor de todos
	$('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("disable");
}

function fncEstadoNegociacionPresupuestoConsultaCombo(){
	$("#estadoNegociacionPresupuestoConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#estadoNegociacionPresupuestoConsulta_filter_table-menu').width(jQuery('#estadoNegociacionPresupuestoConsulta_filter_table-button').innerWidth());
			}
	});
	estadoInicialEstadoNegociacionPresupuestoConsultaCombo();
	
	//anyadimos el onchange al combo de negociacion requerida para habilitar o deshabilitar el de estado
	$('#reqNegociacionConsulta_filter_table').change(function(){
		//obtenemos el valor del combo presupuesto requerido
		var presRequerido = $('#reqNegociacionConsulta_filter_table').rup_combo("getRupValue");
		if("S".localeCompare(presRequerido)==0){
			//si esta deshabilitado lo habilitamos
			if($('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("isDisabled")){
				//habilitamos el combo
				$('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("enable");
				//seleccionamos el valor todos
				$('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("select","");
			}
		}else{
			if(!$('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("isDisabled")){
				//seleccionamos el valor todos
				$('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("select","");
				//si esta habilitado lo deshabilitamos
				$('#estadoNegociacionPresupuestoConsulta_filter_table').rup_combo("disable");
			}
		}
	});
}

function fncIndConfidencialCombo(){
	$("#indConfidencialConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,rowStriping: true
		,open: function(){
			jQuery('#indConfidencialConsulta_filter_table-menu').width(jQuery('#indConfidencialConsulta_filter_table-button').innerWidth());
		}
	});
}

function fncIndFacturableCombo(){
	$("#indFacturableConsulta_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,rowStriping: true
			,open: function(){
				jQuery('#indFacturableConsulta_filter_table-menu').width(jQuery('#indFacturableConsulta_filter_table-button').innerWidth());
			}
	});
}

function fncGrupoTrabajoCombo(){
	$("#grupotrabajoConsulta_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/administracion/grupostrabajo/listCombo/A",
	    sourceParam : {
	        value: ""+"id"+"",
	        label : "descEu"
	    }
	   ,submitAsString: true
	   ,rowStriping: true
	   ,multiselect: true
	   ,open: function(){
	       jQuery('#grupotrabajoConsulta_filter_table-menu').width(jQuery('#grupotrabajoConsulta_filter_table-button').innerWidth());
	   }
	});
}

/*
 * ****************************
 * CREACION COMBOS - FIN
 * ****************************
 */

/*
 * ****************************
 * CREACION AUTOCOMPLETE - INICIO
 * ****************************
 */
function crearAutocompleteGestorConsulta(){
	$('#contactoGestorConsulta_filter_table').rup_autocomplete({
   		source : "/aa79bItzulnetWar/solicitante/findGestoresDeExpCEntidad/"+entidad+"/"+codigo,
   		sourceParam:{
   			value : "dni",
   			label : "nombreCompleto" 
   		},
   		getText: false,
   		width: "auto",
   		open : function() {
   			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#contactoGestorConsulta_filter_table').innerWidth());
   			$("#gestor_filter_table_menu").css("z-index", 999);
   			$("#gestor_filter_table_menu").removeClass("ui-front");
   		}
   	});  
}
/*
 * ****************************
 * CREACION AUTOCOMPLETE - INICIO
 * ****************************
 */

/*
 * ****************************
 * CREACION FECHAS - INICIO
 * ****************************
 */
function fncFechasSolicitud(){
	fnFechaDesdeHasta("fechaDesdeSolicitudConsulta_filter", "fechaHastaSolicitudConsulta_filter");
}
function fncFechasEntregaIzo(){
	fnFechaDesdeHasta("fechaDesdeEntregaConsulta_filter", "fechaHastaEntregaConsulta_filter");
}
/*
 * ****************************
 * CREACION FECHAS - FIN
 * ****************************
 */

/*
 * ****************************
 * TABLA - INICIO
 * ****************************
 */
function crearTabla(){
	$("#consultaExpedientes").rup_table({
		url: "/aa79bItzulnetWar/consultas/consultaexpedientes",
		toolbar:{
			id: "consultaExpedientes_toolbar"
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
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.gestorExpediente,
			$.rup.i18n.app.label.entidadDeGestor,
			$.rup.i18n.app.label.gestorExpediente,
			$.rup.i18n.app.label.fechaHoraSolicitud,
			$.rup.i18n.app.label.fechaHoraEntregaIZO,			
			$.rup.i18n.app.label.fechaHoraEntregaReal,			
			$.rup.i18n.app.label.numPalabraIZO,
			$.rup.i18n.app.label.tramosConcor084,
			$.rup.i18n.app.label.tramosConcor8594,
			$.rup.i18n.app.label.tramosConcor9599,
			$.rup.i18n.app.label.tramosConcor100,
			$.rup.i18n.app.label.numPalabraIZO,
			$.rup.i18n.app.label.fechaInterpretacion,
			$.rup.i18n.app.label.estado,
			$.rup.i18n.app.label.bopv,
			$.rup.i18n.app.label.numFactura,
			$.rup.i18n.app.label.tipoDocumento
		],
		colModel: [
			{ 
				name: "anyoNumExpConcatenado", 
			 	label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "ANYONUMEXPCONCATENADO",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					anyoExpediente = rowObject.anyo;
					idExpediente = rowObject.numExp;
					estado = rowObject.bitacoraExpediente.estadoExp.id;
					fase = rowObject.bitacoraExpediente.faseExp.id;
					elOrigen = rowObject.origen;
					return '<b style="display: block;"><a href="#" onclick="irADetalleExpediente('+anyoExpediente+','+idExpediente+','+estado+',\''+elOrigen+'\')">' + cellvalue + '</a></b>';
				}
				
			},
			{
				name:$.rup.lang === 'es' ? "tipoExpedienteDescEs"
						: "tipoExpedienteDescEu", 
			 	label: "label.tipo",
				align: "center", 
				index: $.rup.lang === 'es' ? "TIPOEXPEDIENTEDESCES": "TIPOEXPEDIENTEDESCEU",
				width: "50", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
				align: "left",
				index: "TITULO",
				width: "220", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue, options, rowObject){
					return "<p class='noMargin txtTablaTarea pr1'>"+cellvalue+"</p>";
				}
			},
			{
				name: $.rup.lang === 'es' ? "entidadDeGestorEs"
		   								  : "entidadDeGestorEu",
				label: "label.entidadDelGestor",
				hidden: true
			},
			{
				name: $.rup.lang === 'es' ? "gestorExpedienteEs"
		   								  : "gestorExpedienteEu",
				label: "label.gestorExpediente",
				hidden: true
			},
			{ 	name: $.rup.lang === 'es' ? "vipEntidadGestorEs"
					: "vipEntidadGestorEu", 
			 	label: "label.gestorExpediente",
				align: "center",
				index: $.rup.lang === 'es' ? "GESTORCOLORDERES"
						: "GESTORCOLORDEREU",
				width: "300", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				noprint: true
			},
			{ 	name: "fechaHoraAlta", 
			 	label: "label.fechaHoraSolicitud",
				align: "center", 
				index: "FECHAALTA",
				width: "150", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
				
			},
			{ 	name: "expedienteTradRev.fechaHoraFinalIZO", 
			 	label: "label.fechaHoraEntregaIZO",
				align: "center", 
				index: "FECHAFINALIZO",
				width: "150", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue, options, rowObject){
					if(rowObject.expedienteTradRev.fechaHoraFinalIZO){
						return rowObject.expedienteTradRev.fechaHoraFinalIZO;
					}else{
						return "-";
					}
				}
				
			},
			{ 	name: "expedienteTradRev.fechaHoraEntregaReal", 
			 	label: "label.fechaHoraEntregaReal",
				align: "center", 
				index: "FECHAENTREGAREAL",
				width: "150", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue, options, rowObject){
					if(rowObject.expedienteTradRev.fechaHoraEntregaReal){
						return rowObject.expedienteTradRev.fechaHoraEntregaReal;
					}else{
						return "-";
					}
				}
				
			},
			{
				name: "palabrasIzo",
				label: "label.numPalabraIZO",
				hidden: true,
				isNumeric: true
			},
			{
				name: "tradosConcor084",
				label: "comun.tramosConcor1",
				hidden: true,
				isNumeric: true
			},
			{
				name: "tradosConcor8594",
				label: "comun.tramosConcor2",
				hidden: true,
				isNumeric: true
			},
			{
				name: "tradosConcor9599",
				label: "comun.tramosConcor9599",
				hidden: true,
				isNumeric: true
			},
			{
				name: "tradosConcor100",
				label: "comun.tramosConcor100",
				hidden: true,
				isNumeric: true
			},
			{ 	name: "numPalTramosPerfectMatch", 
			 	label: "label.numPalabraIZO",
				align: "center", 
				index: "NUMPALCOLORDER",
				width: "200", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true,
				noprint: true
			},
			{ 	name: "expedienteInterpretacion.fechaHoraInicioFin", 
			 	label: "label.fechaInterpretacion",
				align: "center", 
				index: "FECHA_INI_052 ",
				width: "150", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue, options, rowObject){
					if(rowObject.expedienteInterpretacion.fechaHoraInicioFin){
						return rowObject.expedienteInterpretacion.fechaHoraInicioFin;
					}else{
						return "-";
					}
				}
				
			},
			{ 	name: "bitacoraExpediente.estadoExp.descAbrEu", 
			 	label: "label.faseExpediente",
				align: "left", 
				index: "ESTADOEXPEDIENTEDESCABREU",
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "expedienteTradRev.publicarBopvDescEu", 
			 	label: "label.bopv",
				align: "center", 
				index: "INDPUBLICARBOPV",
				width: "70", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					if(rowObject.expedienteTradRev.publicarBopvDescEu){
						return rowObject.expedienteTradRev.publicarBopvDescEu;
					}else{
						return "-";
					}
				}
			},
			{ 	name: "filterFactura.numEstadoFacturas", 
				label: "comun.numFactura",
				align: "left",
				index: "NUMESTADOFACTURAS",
				width: "300", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return "<p class='noMargin txtTablaTarea pr1'>"+cellvalue+"</p>";
				}
			},
			
			{
				name: "tiposDocumentoListagg",
				label: "label.tipoDocumento",
				width: "150", 
				hidden: true
				}
				
		],
        model:"ExpedienteConsulta",
        usePlugins:[
       	 	"toolbar",
			"filter",
			"report",
			"multiselection"
        	],
    	primaryKey: ["anyo", "numExp"],
		loadOnStartUp: false,
		sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "asc",
		multiplePkToken:"-",
		multiselection:{
			headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		},
		filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$('[id^="fecha"][id$="error"]').remove();
				if(!$("#consultaExpedientes_filter_form").valid()){
					return false;
				}
				$("#consultaExpedientes").rup_table("resetSelection");
				$("#consultaExpedientes").rup_table("hideFilterForm");
			}
		},
		report : {
			buttons : [{
				id : "report_consultaExpedientes",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_consultaExpedientes",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/consultas/consultaExpedientesXlsxReport",
						click : function(event) {
							
						}
					} ,
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_consultaExpedientes", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/consultas/consultaExpedientesPdfReport", 
						click : function(event){
						}
					} 
				]}
			],
			columns:[{
						hidden: true
			}]
		},
		title: false,
		loadComplete: function(data){ 
			anyoActual("anyoConsulta_filter_table");
			filterFormObjectConsExp = obtenerFiltrosTabla('consultaExpedientes');
		}
	});
}
/*
 * ****************************
 * TABLA - FIN
 * ****************************
 */

/*
 * **************************** 
 * TOOLBAR - INICIO
 * ****************************
 */
function crearToolbar(){
	$("#consultaExpedientes_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.incluirMetadatosBusqueda
				,css: "fa fa-tags"
				,id:"incluirMetadatosBusqueda"
				,click: function(event){
						event.preventDefault();
			 			event.stopImmediatePropagation();
			 			expedientesSeleccionados = [];
			 			listIdObjects = [];
						bloquearPantalla();
						obtenerIdsSeleccionadosRupTable("consultaExpedientes", filterFormObjectConsExp, "irAIncluirMetadatosBusqueda");
					}
			}
			,{
				i18nCaption: $.rup.i18n.app.boton.reasignarNuevoGestor
				,css: "fa fa-retweet"
				,id:"reasignarNuevoGestor"	
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			expedientesSeleccionados = [];
		 			listIdObjects = [];
					bloquearPantalla();
					obtenerIdsSeleccionadosRupTable("consultaExpedientes", filterFormObjectConsExp, "irAReasignarNuevoGestor");
				}
			}
		]
	});
	if(!esTecnicoIzo){
		$("[id='consultaExpedientes_toolbar##reasignarNuevoGestor']").hide();
	}
}


/*
 * **************************** 
 * TOOLBAR - INICIO
 * ****************************
 */

/*
 * **************************** 
 * FUNCIONALIDAD - INICIO
 * ****************************
 */

function anyadirEventoChangeAComboEntidadGestoraConsulta(){
	jQuery('select[id=idEntidadGestoraConsulta_filter_table]').change(function(){
 		if(autocompleteCreado){
 			 var codigoCompleto =  $('#idEntidadGestoraConsulta_filter_table').rup_combo("getRupValue");
          	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
          		 codigo = -1;
          		 entidad = entidadCheckeada; 
          	 }else{
          		var datosEntidadSeleccionada = codigoCompleto.split("_");	
          		codigo = datosEntidadSeleccionada[1];
          		entidad = datosEntidadSeleccionada[0];
          	 }
          	 //destruimos y volvemos a crear el autocomplete
          	$('#contactoGestorConsulta_filter_table').rup_autocomplete("destroy");
          	$("#autocompleteContainer_contactoGestorConsulta_filter_table").remove();
          	var autocompleteGestor = $('<div id="autocompleteContainer_contactoGestorConsulta_filter_table" ><label for="contactoGestorConsulta_filter_table" class="control-label" data-i18n="label.gestor">'+$.rup.i18n.app.label.gestor+'</label><input id="contactoGestorConsulta_filter_table" class="form-control" name="gestorExpediente.solicitante.dni" /></div>');
          	autocompleteGestor.appendTo("#div_contactoGestorConsulta_filter_table");
       		crearAutocompleteGestorConsulta();
 		}
 		if(!autocompleteCreado){
 			autocompleteCreado=true;
 		}
 	});
}

function cambiarLabelTipoEntidadConsultaBE(tipoEntidad){
	if('B'.localeCompare(tipoEntidad)===0){
		$('#labelEntidadGestoraConsulta_filter_table').text($.rup.i18n.app.label.entidad);
	}else if('E'.localeCompare(tipoEntidad)===0){
		$('#labelEntidadGestoraConsulta_filter_table').text($.rup.i18n.app.label.departamento);
	}else if('L'.localeCompare(tipoEntidad)===0){
		$('#labelEntidadGestoraConsulta_filter_table').text($.rup.i18n.app.label.empresa);
	}else{
		$('#labelEntidadGestoraConsulta_filter_table').text($.rup.i18n.app.label.todasTipoEntidad);
	}
}

function funcionalidadRelacionadaComponentes(){
	jQuery('input[name=tipoEntidad]:first').click();
	jQuery('input[name=tipoEntidad]').change(function(){
 		entidadCheckeada = $(this).val();
 		if("".localeCompare(entidadCheckeada)==0){
 			entidadCheckeada = "-1";
		}
 		$('#idEntidadGestoraConsulta_filter_table').rup_combo("setRupValue","");
		entidad = $(this).val();
		if("".localeCompare(entidad)==0){
			entidad = "-1";
		}
 		//valor del campo para filtrar por entidad
		$('#gestorExpedienteEntidadTipoConsulta').val($(this).val());
		crearComboEntidadGestoraConsulta($(this).val());
		anyadirEventoChangeAComboEntidadGestoraConsulta();
		cambiarLabelTipoEntidadConsultaBE($(this).val());
	});
}

function mostrarBuscadorExpedientesRelacionados(){
	
	$("#buscadorExpedientesRelacionados").remove();
	$("#expRelacionadosDiv").html("<div id='buscadorExpedientesRelacionados' class='oculto'></div>");
	
	$("#buscadorExpedientesRelacionados").buscador_expedientesRelacionados({
		anyo :"",
		numExp :"",
		guardarOnartu: false,
		callback : mostrarExpedientesRelacionados
	});
	$("#buscadorExpedientesRelacionados").buscador_expedientesRelacionados('open');
	desbloquearPantalla();
}

function mostrarExpedientesRelacionados(){
	//si hay expedientes seleccionados volvemos a cargar el campo de seleccionados
	if(expedientesRelSeleccionados && expedientesRelSeleccionados.length){
		//vaciar campo de seleccionados
		$('#expedientesRelacionados-preview fieldset div')[0].innerHTML = "";
		//borramos el valor de los seleccionados anterior
		$('#idsExpedientesRelacionados').val("");
		//por cada expediente seleccionado pintamos su elemento contenedor y anyadirlo al valor de seleccionados para el filtro
		$.each(expedientesRelSeleccionados, function(i, e) {
			$('#expedientesRelacionados-preview fieldset div').append('<span id="expedientesRelSeleccionado_'+ e.numExpFormated+ '" class="badge badge-pill badge-default itzulnet-pill">'
					+ e.numExpFormated
					+ '<button type="button" class="btn"><i class="fa fa-times" aria-hidden="true"></i></button>'
					+ '</span>');
			if(i==0){
				$('#idsExpedientesRelacionados_filter_table').val($('#idsExpedientesRelacionados_filter_table').val() + e.anyo +","+e.numExp );  
			}else{
				$('#idsExpedientesRelacionados_filter_table').val($('#idsExpedientesRelacionados_filter_table').val() + "_" + e.anyo +","+e.numExp );  
			}
		});
		//mostrar campo de seleccionados
		$('#expedientesRelacionados-preview').show();
	}else {
		//ocultar y vaciar campo de seleccionados si no hay seleccionados
		$('#expedientesRelacionados-preview fieldset div')[0].innerHTML = "";
		$('#expedientesRelacionados-preview').hide();
	}
	// Funcionalidad de quitar badges
	$('#expedientesRelacionados-preview fieldset div .badge-pill button').each(function(i,e){
		$(e).on('click',function(){
			//obtener array de ids
			var arrSelected = expedientesRelSeleccionados;
			// id del elemento a eliminar
			var codTema = $(this).parent().attr('id').split('_')[1];
			//posicion del elemento a eliminar en el array
			var index = arrSelected.map(function(e) { return e.numExpFormated; }).indexOf(codTema);
			//eliminar elemento del array
			arrSelected.splice(index, 1);
			//borramos el valor de los seleccionados anterior
			$('#idsExpedientesRelacionados_filter_table').val("");
			//por cada expediente anyadirlo al valor de seleccionados para el filtro
			$.each(expedientesRelSeleccionados, function(a, exp) {
				if(a==0){
					$('#idsExpedientesRelacionados_filter_table').val($('#idsExpedientesRelacionados_filter_table').val() + exp.anyo +","+exp.numExp );  
				}else{
					$('#idsExpedientesRelacionados_filter_table').val($('#idsExpedientesRelacionados_filter_table').val() + "_" + exp.anyo +","+exp.numExp );  
				}
			});
			//actualizar valores de array de seleccionados para enviar al buscador
			expedientesRelSeleccionados = [];
			expedientesRelSeleccionados = arrSelected;
			//eliminar elemento
			$(this).parent().remove();
			//si al eliminar el elemento no hay mas seleccionados, ocultar el campo de seleccionados
			if(arrSelected.length==0){
				$('#expedientesRelacionados-preview').hide();
			}
		});
	});
}

function funcionalidadBotonBuscExpRel(){
	$("#buttonMostrarBuscExpRel").click(function(){
		mostrarBuscadorExpedientesRelacionados();
	});
}

function clearBotonModificado(){
	$('#consultaExpedientes_filter_cleanLink_modificado').on('click',function(event){
		$('#consultaExpedientes_filter_form').rup_form('clearForm');
		anyoActualFilter = true;
		anyoActual("anyoConsulta_filter_table");
		$('#contactoGestorConsulta_filter_table').val('');
		$('input[name=tipoEntidad]:first').click();
		//reiniciar valores expedientes relacionados
		expedientesRelSeleccionados = [];
		$('#idsExpedientesRelacionados_filter_table').val("");
		$('#expedientesRelacionados-preview fieldset div .badge').remove();
		$('#expedientesRelacionados-preview').hide();
		//reiniciar valores metadatos
		listEtiquetasSeleccionadas = [];
		$('#metadatosBusquedaConsulta_filter_table').val("");
		//ocultar campo de seleccionados
		$('#metadatosBusqueda-preview').hide();
		//eliminar contenido
		$('#metadatosBusqueda-preview fieldset div .badge').remove();
		estadoInicialSubsRequeridaEstadoCombo();
		if(esTecnicoIzo){
			habilitarFiltrosComunes();
		}
		$("#consultaExpedientes").rup_table("filter");
	});
}

function fncEstadoFactura(){
	$('#estadoFactura_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/consultafacturas/estadosFacturaSinBaja/false",
		sourceParam : {
			value: "indEstadoFactura",
			label : $.rup.lang === 'es' ? "estadoFacturaDescEs"
					: "estadoFacturaDescEu"
		},
		blank:"",
		ordered : false,
		rowStriping : true,
		open : function() {
			jQuery('#estadoFactura_filter_table-menu').width(jQuery('#estadoFactura_filter_table-button').innerWidth());
		}
	});	
}

/*
 * **************************** 
 * FUNCIONALIDAD - FIN
 * ****************************
 */

/*
 * **************************** 
 * VALIDACIONES FORMULARIO BUSQUEDA - INICIO
 * ****************************
 */
function validacionesFormularioBusqueda(){
	$("#consultaExpedientes_filter_form").rup_validate({
		feedback: $('#consultaExpedientes_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"fechaAltaDesde": {date: true},
			"fechaAltaHasta": {date: true, fechaHastaMayor: "fechaAltaDesde" },
			"expedienteTradRev.fechaEntregaIzoDesde": {date: true},
			"expedienteTradRev.fechaEntregaIzoHasta": {date: true, fechaHastaMayor: "expedienteTradRev.fechaEntregaIzoDesde" }
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
			showFieldErrorsInFeedback: false
	});
}

/*
 * **************************** 
 * VALIDACIONES FORMULARIO BUSQUEDA - FIN
 * ****************************
 */

/*
 * **************************** 
 * FEEDBACK - INICIO
 * ****************************
 */

function inicializarFeedback(){
	$('#consultaExpedientes_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
}

function seleccionarEtiquetaConsulta(obj, etiquetas) {
	//añadimos el obj a una lista de etiqutas seleccionadas y es la que nos recorreremos para añadir
	$.each(obj, function(i, e) {
		var idEtiqueta = obj[i].id;
		var encontradaEtiqueta = false;
		$.each(listEtiquetasSeleccionadas, function(x, e) {
			if (listEtiquetasSeleccionadas[x].id == idEtiqueta){
				encontradaEtiqueta = true;
			}
		});
		if (!encontradaEtiqueta){
			listEtiquetasSeleccionadas.push(obj[i]);
		}
	});
	$('#metadatosBusqueda-preview').hide();
	//eliminar contenido
	$('#metadatosBusqueda-preview fieldset div .badge').remove();
		var seleccionadas = '';
		listEtiquetasSeleccionadas.sort(sortByDescConsulta);
		$.each(listEtiquetasSeleccionadas, function(i, e) {
			$('#metadatosBusqueda-preview fieldset div').append('<span id="metadatosBusqueda_'+ listEtiquetasSeleccionadas[i].id + '" class="badge badge-pill badge-default itzulnet-pill">'
					+ listEtiquetasSeleccionadas[i].descEu
					+ '<button type="button" class="btn"><i class="fa fa-times" aria-hidden="true"></i></button>'
					+ '</span>');
			//actualizamos el hidden que se enviará al darle a buscar, como filtro del formulario.
			seleccionadas = seleccionadas + (seleccionadas != ''?',':'') + listEtiquetasSeleccionadas[i].id; 
		});
		
		$('#metadatosBusquedaConsulta_filter_table').val(seleccionadas)
		//mostrar  campo de seleccionados
		$('#metadatosBusqueda-preview').show();
		
		// Funcionalidad de quitar badges
		$('#metadatosBusqueda-preview fieldset div .badge-pill button').each(function(i,e){
			clickEliminarEtiquetaConsulta(e);
		});
	
	
//	if (obj != null && obj.length > 0) {
//		for (var i = 0; i < obj.length; i++) {
//			var persona = obj[i];
//
//			fnLimpiarCamposLote();
//			fnMostrarAsignadoAPersona();
//			cambiarValorSwitchRevisarTraduccion(false,false);
//			$("#personaAsignada_detail_table").val(persona.nombreCompleto);
//			$("#dniAsignacion").val(persona.dni);
//			$("#tipoEntidad").val(persona.entidad.tipo);
//			$("#idEntidad").val(persona.entidad.codigo);
//			$('#recursoAsignacion').val(datosTareas.tipoRecurso.interno);
//			validarCampos();
//			fnCalcularHorasPrevistasTraduccion();
//			
//
//		}
//	}

}

function clickEliminarEtiquetaConsulta(elem){
	$(elem).on('click',function(){
		var codTema = $(this).parent().attr('id').split('_')[1];
		
		$.each(listEtiquetasSeleccionadas, function(x, elem) {
			if (listEtiquetasSeleccionadas[x].id == codTema){
				//listEtiquetasSeleccionadas[x].remove();
				listEtiquetasSeleccionadas.splice(x,1);
				return false;
			}
		});
		
		$('#metadatosBusqueda-preview fieldset div .badge').remove();
		var seleccionadas = '';
		listEtiquetasSeleccionadas.sort(sortByDescConsulta);
		$.each(listEtiquetasSeleccionadas, function(i, e) {
			$('#metadatosBusqueda-preview fieldset div').append('<span id="metadatosBusqueda_'+ listEtiquetasSeleccionadas[i].id + '" class="badge badge-pill badge-default itzulnet-pill">'
					+ listEtiquetasSeleccionadas[i].descEu
					+ '<button type="button" class="btn"><i class="fa fa-times" aria-hidden="true"></i></button>'
					+ '</span>');
			//actualizamos el hidden que se enviará al darle a buscar, como filtro del formulario.
			seleccionadas = seleccionadas + (seleccionadas != ''?',':'') + listEtiquetasSeleccionadas[i].id; 
		});
		
		$('#metadatosBusquedaConsulta_filter_table').val(seleccionadas)
		
		//si al eliminar el elemento no hay mas seleccionados, ocultar el campo de seleccionados
		if(listEtiquetasSeleccionadas.length==0){
			$('#metadatosBusqueda-preview').hide();
		}else{
			$('#metadatosBusqueda-preview fieldset div .badge-pill button').each(function(i,e){
				clickEliminarEtiquetaConsulta(e);
			});
		}
	});
}


function inicializarBuscadorEtiquetas(){
	$("#etiquetas_filter_table")
	.click(
			function() {
				$("#buscadorEtiquetasConsulta").remove();
				$("#expRelacionadosDiv")
						.append(
								'<div id="buscadorEtiquetasConsulta" style="display:none"></div>');
				$("#buscadorEtiquetasConsulta").buscador_etiquetas({
					//destino : 'P',
					multiselect : false,
					callback : seleccionarEtiquetaConsulta
				});
				$("#buscadorEtiquetasConsulta").buscador_etiquetas("open");
			});
}

//This will sort your array
function sortByDescConsulta(a, b){
	var aDesc = a.descEu.toLowerCase();
  var bDesc = b.descEu.toLowerCase(); 
  return ((aDesc < bDesc) ? -1 : ((aDesc > bDesc) ? 1 : 0));
}


/*
 * **************************** 
 * FEEDBACK - FIN
 * ****************************
 */



jQuery(function($) {
	
	inicializarFeedback();
	fncTipoExpedienteCombo();
	fncEstadosCombo();
	fncFechasSolicitud();
	fncFechasEntregaIzo();
	// al cargar la pantalla clickamos el primer valor (todos) de los radiobuttons
	crearComboEntidadGestoraConsulta("");
	fncPublicarBopvCombo();
	fncPrevistoParaBopvCombo();
	fncIdiomaDestinoCombo();
	fncindCorredaccionCombo();
	fncindUrgenteCombo();
	fncindPublicadoBoeCombo();
	fncSubsRequeridaCombo();
	fncSubsRequeridaEstadoCombo();
	//fncMetadatosBusquedaCombo();
	fncTiposDocumentoCombo();
	crearTabla();
	crearToolbar();
	validacionesFormularioBusqueda();
	funcionalidadRelacionadaComponentes();
	funcionalidadBotonBuscExpRel();
	anyadirEventoChangeAComboEntidadGestoraConsulta();
	crearAutocompleteGestorConsulta();
	if(esTecnicoIzo){
		fncReqPresupuestoConsultaCombo();
		fncEstadoAceptacionPresupuestoConsultaCombo();
		fncReqNegociacionConsultaCombo();
		fncEstadoNegociacionPresupuestoConsultaCombo();
		fncIndConfidencialCombo();
		fncIndFacturableCombo();
		fncGrupoTrabajoCombo();
	}
	clearBotonModificado();
	fncEstadoFactura();
	inicializarBuscadorEtiquetas();
	
});