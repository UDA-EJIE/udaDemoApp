var filterFormObjectConsPlanif;
var tareaAsigA;
var idTipoExp;
var desdeConsultaPlanifExpedientes = true;
var anyoExpediente;
var idExpediente;
var estado;
var fase;
var pestDatos;
var aDetalle;
var idiomaDestino = "";
var expedientesSeleccionados = [];

var listEtiquetasSeleccionadas = [];
var listIdObjects = [];
var bopv = false;
var tramitagune = false;

function irADesgloseTareas(){
	volcarListaIdsAExpSeleccionados("-");
	desdeConsultaPlanifExpedientes = true;
	cambiarCapaGeneral('/aa79bItzulnetWar/consultaplanificacionexpediente/desglosetareasconsultaplanif/maint');
}

function cambiarComboTipoTarea(elTipoExpediente){
	jQuery('#tipoTareaCargaTrabajo').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/tipoTareaPorTipoExpediente/"+elTipoExpediente,
		sourceParam:{
			value : "id015",
			label : $.rup.lang === 'es' ? "descEs015"
					: "descEu015", 
		}
		,blank: ""
		,width: "100%"
		,rowStriping: true
		,orderedByValue: true
		,open : function() {
			jQuery('#tipoTareaCargaTrabajo-menu').width(jQuery('#tipoTareaCargaTrabajo-button').innerWidth());
		}
	
	});
}

function cambiarCapaGeneral(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneralConsultaPlanif = $('#consultaPlanificacionExpGeneralDiv').detach();
	   		$("#divConsultaPlanificacionExp").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
}

function volverACapaGeneralConsultaPlanifExp(){
	$("#divCapaDetalle").detach();
	$("#divConsultaPlanificacionExp").html("<div id='consultaPlanificacionExpGeneralDiv'></div>");
	$("#consultaPlanificacionExpGeneralDiv").html(capaPestGeneralConsultaPlanif);
	$("#consultaPlanificacionExp").trigger("reloadGrid");
}

function irADetalleExpedienteConsPlanif(elAnyo, elNumExp, elEstado, laFase){
	if(datosExpediente.estado.cerrado == elEstado || datosExpediente.estado.encurso == elEstado){
		// ir a detalle planificacion expedientes
		anyoExpediente = elAnyo;
		idExpediente = elNumExp;
		estado = elEstado;
		fase = laFase;
		pestDatos = false;
		aDetalle = true;
		cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/detalleexpediente/maint');
	}else{
		// ir a detalle en modo consulta
		cambiarCapaGeneral('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExp/'+elAnyo+'/'+elNumExp);
	}
}

function ocultarColumnasReport(columnIds){
	var columnas = columnIds.split(',');
	for(var i=0;i<columnas.length;i++){
		$("ul[id^='consultaPlanificacionExp_toolbar'][id $='report_consultaPlanifTareas-mbutton-container'] input[id='"+columnas[i]+"']").parent().attr('hidden','hidden');
		$("ul[id^='consultaPlanificacionExp_toolbar'][id $='report_consultaPlanifTareas-mbutton-container'] input[id='"+columnas[i]+"']").attr('checked',false);
	}
}

function crearComboAsignadoA(tareaAsigA,idTipoExp){
	if($('#asignadaA_filter_table-menu').length){
		$('#asignadaA_filter_table-menu').remove();
	}

	if (typeof(tareaAsigA) === "undefined" || tareaAsigA === "") {
		tareaAsigA = "-1";
		if(!$("#lote_filter_table").rup_combo("isDisabled")){
			$("#lote_filter_table").rup_combo("select","");
		}
		$("#lote_filter_table").rup_combo("disable");
	}
	
	$("#asignadaA_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/consultaplanificacionexpediente/comboAsignadoa/"+ tareaAsigA + "/" + idTipoExp,
		sourceParam : {
			value: idTipoExp != datosTareas.idTipoExp.interpretacion && tareaAsigA === datosTareas.tipoRecurso.externo ? "entidad.codigoCompleto" : "dni",
			label : idTipoExp != datosTareas.idTipoExp.interpretacion && tareaAsigA === datosTareas.tipoRecurso.externo ? jQuery.rup_utils.capitalizedLang()==="Es"?"entidad.descEs":"entidad.descEu":"nombreCompleto"
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			jQuery('#asignadaA_filter_table-menu').width(jQuery('#asignadaA_filter_table-button').innerWidth());
		}
		,change: function(){
			if($("#asignadaA_filter_table").rup_combo("getRupValue") != "" && idTipoExp != datosTareas.idTipoExp.interpretacion && tareaAsigA === datosTareas.tipoRecurso.externo){
				creaComboLotesProveedor($("#asignadaA_filter_table").rup_combo("getRupValue"));
				$("#lote_filter_table").rup_combo("enable");
			} else {
				if(!$("#lote_filter_table").rup_combo("isDisabled")){
					$("#lote_filter_table").rup_combo("select","");
				}
				$("#lote_filter_table").rup_combo("disable");
			}
		}
		,onLoadSuccess: function(){
			$('#asignadaA_filter_table').rup_combo("select","");
		}
	});
}

function creaComboLotesProveedor(codigoCompleto){
	var codEntidad;
	var idEntidad = -1;
	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
		 codEntidad = -1;
		 idEntidad = -1; 
  	 }else{
  		var datosEntidadSeleccionada = codigoCompleto.split("_");	
  		codEntidad = datosEntidadSeleccionada[0];
  		idEntidad = datosEntidadSeleccionada[1];
  	 }
	if($('#lote_filter_table-menu').length){
		$('#lote_filter_table-menu').remove();
	}
	$("#lote_filter_table").rup_combo({
		sourceGroup :  "/aa79bItzulnetWar/lotes/getLotesEmpresaAgrupadosPorActivoParamEmpresa/" + codEntidad + "/"+idEntidad,
		sourceParam : {
			label : "nombreLote",
			value : "idLote",
			style : "css"
		},
		blank: "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			$('#lote_filter_table-menu').width(jQuery('#lote_filter_table-button').innerWidth());
		},onLoadSuccess: function(){
			$('#lote_filter_table').rup_combo("select","");
		}
	});
}


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
			deshabilitarCombo('tipoDocumentoExpediente_filter_table', true, "");
		}
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
				$("#modalesDiv")
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




jQuery(function($) {
	
	$('#consultaPlanificacionExp_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#consultaPlanificacionExp_filter_form").rup_validate({
		feedback: $('#consultaPlanificacionExp_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"filterTarea.fechaAsignacionDesde": {date: true},
			"filterTarea.fechaAsignacionHasta": {date: true, fechaHastaMayor: "filterTarea.fechaAsignacionDesde" },
			"filterTarea.fechaDesdePrevistaEjecucion": {date: true},
			"filterTarea.fechaHastaPrevistaEjecucion": {date: true, fechaHastaMayor: "filterTarea.fechaDesdePrevistaEjecucion" },
			"filterTarea.fechaDesdeRealEjecucion": {date: true},
			"filterTarea.fechaHastaRealEjecucion": {date: true, fechaHastaMayor: "filterTarea.fechaDesdeRealEjecucion" }
			
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	var cmbGT = false;
	
	fncTiposDocumentoCombo();
	
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: true	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
		,change: function(){
			idTipoExp = $("#idTipoExpediente_filter_table").rup_combo("getRupValue");
			if (cmbGT){
				crearComboAsignadoA(tareaAsigA,idTipoExp);
			}
			cambiarComboTipoTarea(idTipoExp);
			if (idTipoExp && idTipoExp == datosTareas.idTipoExp.interpretacion){
				deshabilitarCombo('tipoDocumentoExpediente_filter_table', true, "");
			} else {
				habilitarCombo('tipoDocumentoExpediente_filter_table', false, null);
			}
		}
	});
	
	$("#lote_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: true	
		,rowStriping: true
		,open: function(){
			jQuery('#lote_filter_table-menu').width(jQuery('#lote_filter_table-button').innerWidth());
		}
	});
	
	$("#tareaAsignadaA_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: true	
		,rowStriping: true
		,open: function(){
			jQuery('#tareaAsignadaA_filter_table-menu').width(jQuery('#tareaAsignadaA_filter_table-button').innerWidth());
		}
		,onLoadSuccess: function(){
			cmbGT = true;
			$("#tareaAsignadaA_filter_table").rup_combo("setRupValue",recursoInterno);
			tareaAsigA = $("#tareaAsignadaA_filter_table").rup_combo("getRupValue");
			crearComboAsignadoA(tareaAsigA,idTipoExp);
		}
		,change: function(){
			tareaAsigA = $("#tareaAsignadaA_filter_table").rup_combo("getRupValue");
			crearComboAsignadoA(tareaAsigA,idTipoExp);
		}
	});
	
	$("#asignador_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/tradInterp",
		sourceParam : {
			label : "nombreCompleto",
			value : "dni",
			style : "css"
		},
		blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			jQuery('#asignador_filter_table-menu').width(jQuery('#asignador_filter_table-button').innerWidth());
		}
	});
	
	$("#fases_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/fasesexpediente/getFasesExpediente/3",
	    sourceParam : {
			value: "id",
			label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			jQuery('#fases_filter_table-menu').width(jQuery('#fases_filter_table-button').innerWidth());
		}
	});
	
	function fncPlanifConsFasesCombo(){
		$("#fases_filter_table").rup_combo({
			parent: ["estado_filter_table"],
			source: "/aa79bItzulnetWar/fasesexpediente/remoteEstado",
		    sourceParam : {
				value: "id",
				label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
			}
			,blank: ""
			,width: "100%"	
			,rowStriping: true
			,open: function(){
				jQuery('#fases_filter_table-menu').width(jQuery('#fases_filter_table-button').innerWidth());
			}
		});
	}
	
	$("#estado_filter_table").rup_combo({
		source: "/aa79bItzulnetWar/consultaplanificacionexpediente/getEstadosExpediente",
		sourceParam : {
			value: "id",
			label : jQuery.rup_utils.capitalizedLang()==="Es"?"DescEs":"DescEu"
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open: function(){
			jQuery('#estado_filter_table-menu').width(jQuery('#estado_filter_table-button').innerWidth());
		},onLoadSuccess: function(){
			var id = $(this).attr("id");
			$("#"+id).rup_combo("select",0);
			fncPlanifConsFasesCombo();
			$("#"+id).rup_combo("setRupValue","");
		}
	});
	
	cambiarComboTipoTarea($("#idTipoExpediente_filter_table").rup_combo("getRupValue"));
	
	$("#estadoAceptTarea_detail_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoAceptTarea_detail_table-menu').width(jQuery('#estadoAceptTarea_detail_table-button').innerWidth());
		}
	});
	
	$("#estadoEjecTarea_detail_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: true	
		,rowStriping: true
		,open: function(){
			jQuery('#estadoEjecTarea_detail_table-menu').width(jQuery('#estadoEjecTarea_detail_table-button').innerWidth());
		}
	});
	
	fnFechaDesdeHasta("fechaPrevistaEjecucionDesde", "fechaPrevistaEjecucionHasta");
	fnFechaDesdeHasta("fechaRealEjecucionDesde", "fechaRealEjecucionHasta");
	fnFechaDesdeHasta("fechaAsignacionDesde", "fechaAsignacionHasta");
	
//	$('#metadatosBusquedaConsulta_filter_table').rup_combo({
//		source : "/aa79bItzulnetWar/administracion/datosmaestros/metadatosbusqueda/metadatosAlta",
//		sourceParam : {
//	        value: ""+"id"+"",
//	        label : "descEu"
//	    }
//	   	,submitAsString: true,
//		selected : [], // value && index
//		ordered : false,
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
	
	$("#consultaPlanificacionExp").rup_table({
		url: "/aa79bItzulnetWar/consultaplanificacionexpediente",
		toolbar:{
			id: "consultaPlanificacionExp_toolbar"
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
					i18nCaption: $.rup.i18n.app.boton.desgloseTareas
					,css: "fa fa-list-ul"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(e){
					 	event.preventDefault();
			 			event.stopImmediatePropagation();
						expedientesSeleccionados = [];
						listIdObjects = [];
						bloquearPantalla();
						obtenerIdsSeleccionadosRupTable("consultaPlanificacionExp", filterFormObjectConsPlanif, "irADesgloseTareas");
				 	 }
				}
			]
		},
		colNames: [
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.fechaHoraInicioPrevista,
			$.rup.i18n.app.label.fechaHoraFinPrevista,
			$.rup.i18n.app.label.fechaHoraSolicitud,
			$.rup.i18n.app.label.fechaHoraEntregaIZO,			
			$.rup.i18n.app.label.fechaHoraEntregaReal,
			$.rup.i18n.app.label.numPalabraIZO,
			$.rup.i18n.app.label.estado
		],
		colModel: [
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
			 	index: "ANYONUMEXPCONCATENADO",
			 	align: "center", 
				width: "90", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					anyoExpediente = rowObject.anyo;
					idExpediente = rowObject.numExp;
					estado = rowObject.bitacoraExpediente.estadoExp.id;
					fase = rowObject.bitacoraExpediente.faseExp.id;
					tipoExp = rowObject.idTipoExpediente;
					return '<b><a href="#" onclick="irADetalleExpedienteConsPlanif('+anyoExpediente+','+idExpediente+','+estado+','+fase+')">' + cellvalue + '</a></b>';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs":"tipoExpedienteDescEu",
			 	label: "label.tipoExpediente",
			 	index: $.rup.lang === 'es' ? "TIPOEXPEDIENTEDESCES":"TIPOEXPEDIENTEDESCEU",
			 	align: "center", 
				width: "60", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
			 	index: "TITULO",
			 	align: "left", 
				width: "300", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "fechaHoraPrevistaInicio", 
				label: "label.fechaHoraInicioPrevista",
				align: "center", 
				index: "FECHAINICIOPREVISTA",
				width: "200", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraPrevistaFin",
				label: "label.fechaHoraFinPrevista",
				align: "center", 
				index: "FECHAFINPREVISTA",
				width: "200", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraAlta", 
			 	label: "label.fechaHoraSolicitud",
				align: "center", 
				index: "FECHAALTA",
				width: "200", 
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
				width: "200", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
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
					if(rowObject.expedienteTradRev && rowObject.expedienteTradRev.fechaHoraEntregaReal){
						return rowObject.expedienteTradRev.fechaHoraEntregaReal;
					}else{
						return "-";
					}
				}
				
			},
			{ 	name: "numPalTramosPerfectMatch", 
			 	label: "label.numPalabraIZO",
				align: "right", 
				index: "NUMPALCOLORDER",
				width: "190", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
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
			}
        ],
        model:"ExpedientePlanificacion",
        usePlugins:[
        	"toolbar",
       		"filter",
       		"multiselection",
       		"report"
         	],
        multiSort: true,
     	sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "desc",
        primaryKey: ["anyo", "numExp"],
        multiplePkToken:"-",
		loadOnStartUp: true,
		multiselect:true,
		multiselection:{
			headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		},
		loadComplete: function(data){ 
			$("ul[id^='consultaPlanificacionExp_toolbar'][id $='report_consultaPlanifTareas-mbutton-container'] div[id='print_div_report_consultaPlanifTareas']").html(camposReport);
			if($("#idTipoExpediente_filter_table").rup_combo("getRupValue") === 'I'){
				$("#consultaPlanificacionExp").rup_table("hideCol", "fechaHoraAlta");
				$("#consultaPlanificacionExp").rup_table("hideCol", "expedienteTradRev.fechaHoraFinalIZO");
				$("#consultaPlanificacionExp").rup_table("hideCol", "expedienteTradRev.fechaHoraEntregaReal");
				$("#consultaPlanificacionExp").rup_table("hideCol", "numPalTramosPerfectMatch");
				$("#consultaPlanificacionExp").rup_table("showCol", "fechaHoraPrevistaInicio");
				$("#consultaPlanificacionExp").rup_table("showCol", "fechaHoraPrevistaFin");
				ocultarColumnasReport("8,9,10,11");
			}else{
				$("#consultaPlanificacionExp").rup_table("showCol", "fechaHoraAlta");
				$("#consultaPlanificacionExp").rup_table("showCol", "expedienteTradRev.fechaHoraFinalIZO");
				$("#consultaPlanificacionExp").rup_table("showCol", "expedienteTradRev.fechaHoraEntregaReal");
				$("#consultaPlanificacionExp").rup_table("showCol", "numPalTramosPerfectMatch");
				$("#consultaPlanificacionExp").rup_table("hideCol", "fechaHoraPrevistaInicio");
				$("#consultaPlanificacionExp").rup_table("hideCol", "fechaHoraPrevistaFin");
				ocultarColumnasReport("6,7");
			}
			$("#consultaPlanificacionExp").rup_table("hideFilterForm");
			filterFormObjectConsPlanif = obtenerFiltrosTabla('consultaPlanificacionExp');
	    },
		report : {
			buttons : [{
				id : "report_consultaPlanifTareas",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_consultaPlanifTareas",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/consultaplanificacionexpediente/xlsxReport",
						click : function(event) {
						}
					},
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_consultaPlanifTareas", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/consultaplanificacionexpediente/pdfReport", 
						click : function(event){
						}
					} 
				]}
			]
		},
		filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$('[id^="fecha"][id$="error"]').remove();
				if(!$("#consultaPlanificacionExp_filter_form").valid()){
					return false;
				}
				$("#consultaPlanificacionExp").rup_table("resetSelection");
				$("#consultaPlanificacionExp").rup_table("hideFilterForm");
			}
		}
	});
	
	$('#consultaPlanificacionExp_filter_cleanLinkModificado').on('click',function(event){
		event.preventDefault();
		event.stopImmediatePropagation();
		$('#anyo_filter_table').val("");
		$('#numExp_filter_table').val("");
		$('#fechaAsignacionDesde').val("");
		$('#fechaAsignacionHasta').val("");
		$('#fechaPrevistaEjecucionDesde').val("");
		$('#fechaPrevistaEjecucionHasta').val("");
		$('#fechaRealEjecucionDesde').val("");
		$('#fechaRealEjecucionHasta').val("");

		$('#idTipoExpediente_filter_table').rup_combo("select", "I", true);
		$('#estado_filter_table').rup_combo("select","");
		$('#fases_filter_table').rup_combo("select","");
		$('#tipoTareaCargaTrabajo').rup_combo("select","");
		$('#estadoAceptTarea_detail_table').rup_combo("select","");
		$('#estadoEjecTarea_detail_table').rup_combo("select","");
		$('#estadoEjecTarea_detail_table').rup_combo("select","");
		$('#asignadaA_filter_table').rup_combo("select","");
		$('#lote_filter_table').rup_combo("select","");
		$('#tareaAsignadaA_filter_table').rup_combo("select",recursoInterno);
		$('#asignador_filter_table').rup_combo("select","");
		$('#tipoDocumentoExpediente_filter_table').rup_combo("select","");
		
		
		listEtiquetasSeleccionadas = [];
		$('#metadatosBusquedaConsulta_filter_table').val("");
		$('#metadatosBusqueda-preview').hide();
		$('#metadatosBusqueda-preview fieldset div .badge').remove();
		
		$("#consultaPlanificacionExp").rup_table("hideFilterForm");
		
		eliminarMensajesValidacion();
		$("#consultaPlanificacionExp_feedback").rup_feedback("close");
		$("#consultaPlanificacionExp").rup_table("filter");
	});
	inicializarBuscadorEtiquetas();
	
});
