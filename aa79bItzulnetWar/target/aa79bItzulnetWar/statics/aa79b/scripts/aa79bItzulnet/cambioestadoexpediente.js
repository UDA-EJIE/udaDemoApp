var capaPestGeneral = '';
var autocompleteCreado = false;
var entidad = "-1";
var codigo = -1;
var entidadCheckeada = "-1";
var expedientesSeleccionados = '';
var faseExp = "";

/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function irDetalleExpediente(anyo, numExp){
	// ir a detalle en modo consulta
	window.open('/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/'+anyo+'/'+numExp);
}

function volverACambioEstadoExp(){
	$("#divBusquedaGeneralCapa").detach();
	$("#divBusquedaGeneral").append("<div id='divBusquedaGeneralCapa'></div>");
	$("#divBusquedaGeneralCapa").html(capaPestGeneral);
}

function modificarEstadoFase(){
	bloquearPantalla();
	
	eliminarDialogs();
	$.rup_ajax({
	   	 url: "/aa79bItzulnetWar/servicios/cambioEstadoExpediente/detalleCambioEstadoExp" 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#divBusqueda').detach();
	   		$("#divBusquedaGeneral").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   		desbloquearPantalla();
	   	 }
	 });
	
}

/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function fncTipoExpedienteCombo(){
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
	});
}

function fncEstadosCombo(){
	$("#estado_filter_table").rup_combo({
		 source: "/aa79bItzulnetWar/estadosexpediente/findAllEstadosExpSinAlta",
		 sourceParam : {
			 value: "id",
			 label : jQuery.rup_utils.capitalizedLang()==="Es"?"descEs":"descEu"
		 }
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,orderedByValue: true
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
	$("#fase_filter_table").rup_combo({
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
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
	});
}


function crearComboEntidadGestora(valEntidad){
	codigo="-1";
	$('#idEntidadSolicitante_filter_table').rup_combo({
			source : "/aa79bItzulnetWar/entidad/exprel/"+valEntidad,
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
			}
		});
}

function crearAutocompleteContactoGestor(){
	$('#contactoGestor_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/solicitante/findGestores/"+entidad+"/"+codigo,
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

function anyadirEventoChangeAComboEntidadGestora(){
	$('select[id=idEntidadSolicitante_filter_table]').change(function(){
 		if(autocompleteCreado){
 			 var codigoCompleto =  $('#idEntidadSolicitante_filter_table').rup_combo("getRupValue");
          	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
          		 codigo = -1;
          		 entidad = entidadCheckeada; 
          	 }else{
          		var datosEntidadSeleccionada = codigoCompleto.split("_");	
          		codigo = datosEntidadSeleccionada[1];
          		entidad = datosEntidadSeleccionada[0];
          	 }
          	 //destruimos y volvemos a crear el autocomplete
          	$('#contactoGestor_filter_table').rup_autocomplete("destroy");
          	$("#autocompleteContainer_contactoGestor_filter_table").remove();
          	var autocompleteGestor = $('<div id="autocompleteContainer_contactoGestor_filter_table" ><label for="contactoGestor_filter_table" class="control-label" data-i18n="label.gestor">'+$.rup.i18n.app.label.gestor+'</label><input id="contactoGestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni" /></div>');
          	autocompleteGestor.appendTo("#div_contactoGestor_filter_table");
          	crearAutocompleteContactoGestor();
 		}
 		if(!autocompleteCreado){
 			autocompleteCreado=true;
 		}
 	});
}

function funcionalidadRelacionadaComponentes(){
	$('input[name=tipoEntidad]').change(function(){
		//al clickar en un radiobutton debemos volver a cargar el combo de entidades 
		//y el autocomplete de gestores
 		 entidadCheckeada = $(this).val();
 		 //si el valor de la entidad es todos, lo ponemos a -1 para poder controlarlo 
 		 //en las consultas del combo y autocomplete
 		if("".localeCompare(entidadCheckeada)==0){
 			entidadCheckeada = "-1";
 			entidad = "-1";
		}
 		//valor del campo para filtrar por entidad
		$('#gestorExpedienteEntidadTipo').val($(this).val());
		crearComboEntidadGestora($(this).val());
		anyadirEventoChangeAComboEntidadGestora();
	});
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	$('#busquedaGeneral_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	fncTipoExpedienteCombo();
	fncEstadosCombo();
	
	/* ENTIDAD GESTORA */
	crearComboEntidadGestora("");
	funcionalidadRelacionadaComponentes();
	crearAutocompleteContactoGestor();
	
	// Filtro - Cargar combo de entidad en funcion del tipo de entidad seleccionado
	$('input[name=tipoEntidad]:first').click();
	
	/* FIN ENTIDAD GESTORA */
	
	$("#busquedaGeneral_filter_form").rup_validate({
		feedback: $('#busquedaGeneral_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"expedienteTradRev.fechaEntregaIzoDesde": {date: true},
			"expedienteTradRev.fechaEntregaIzoHasta": {date: true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	fnFechaDesdeHasta("fechaEntregaDesde_filter", "fechaEntregaHasta_filter");
		
	$('#busquedaGeneral_filter_cleanLinkModificado').on('click',function(event){
		$("#busquedaGeneral_filter_form").rup_form("clearForm");
		$("#busquedaGeneral").rup_table("resetSelection");
		$('input[name=tipoEntidad]:first').click();
		$("#busquedaGeneral").rup_table("filter");
	});
	
	$("#busquedaGeneral").rup_table({
		url: "/aa79bItzulnetWar/servicios/cambioEstadoExpediente",
		toolbar:{
			id: "busquedaGeneral_toolbar"
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
			"",
			"",
			"",
			"",
			"",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.gestorExpediente,
			$.rup.i18n.app.label.fechaHoraFin,
			$.rup.i18n.app.label.estado,
			$.rup.i18n.app.label.fase
		],
		colModel: [
			{ 	name: "idTipoExpediente", 
				hidden:true
			},
			{ 	name: "anyo", 
				hidden:true
			},
			{ 	name: "numExp", 
				hidden:true
			},
			{ 	name: "bitacoraExpediente.estadoExp.id", 
				hidden:true
			},
			{ 	name: "bitacoraExpediente.faseExp.id", 
				hidden:true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
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
					return '<b style="display: block;"><a href="#" onclick="irDetalleExpediente('+anyoExpediente+','+idExpediente+')">' + cellvalue + '</a></b>';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipo",
				align: "center", 
				index: "IDTIPOEXPEDIENTE",
				width: "60", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
				align: "left",
				index: "TITULO",
				width: "380", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					return 'style="white-space: normal;"';
				}
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
				sortable: true
			},
			{ 	name: "expedienteTradRev.fechaHoraFinalSolic", 
			 	label: "label.fechaHoraFin",
				align: "center", 
				index: "FECHAFINAL",
				width: "150", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "bitacoraExpediente.estadoExp.descAbrEu", 
			 	label: "label.estado",
				align: "left", 
				index: "ESTADOEXPEDIENTEDESCABREU",
				width: "150", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "bitacoraExpediente.faseExp.descAbrEu", 
			 	label: "label.fase",
				align: "left", 
				index: "FASEEXPEDIENTEDESCABREU",
				width: "150", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],
        model:"Expediente",
        usePlugins:[
        	 "feedback",
        	 "toolbar",
       		 "filter"
         	],
        primaryKey: ["anyo", "numExp"],
		loadOnStartUp: false,
		sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "asc",
		multiplePkToken:"-",
		filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$("#busquedaGeneral").rup_table("hideFilterForm");
				$('[id^="fecha"][id$="error"]').remove();
				$("#busquedaGeneral").rup_table("resetSelection");
				if(!$("#busquedaGeneral_filter_form").valid()){
					return false;
				}
			}
		}
	    ,title: false,
	    loadComplete: function(data){ 
	    	ocultarCheckAllRupTable(this.id);
		}
	});
	
	$("#busquedaGeneral_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.modificarEstadoFase
				,id: "modificarEstadoExp"	
				,css: "fa fa-exchange"
				,click : function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			
		 			if(!$('#busquedaGeneral').rup_table("isSelected")){
		 				$.rup_messages("msgAlert", {
		 					title: $.rup.i18n.app.label.aviso,
							message: $.rup.i18n.app.comun.warningSeleccion
						});	
						return false;
		 			}else if(1 != $('#busquedaGeneral').rup_table("getSelectedRows").length){
		 				$.rup_messages("msgAlert", {
 							title: $.rup.i18n.app.label.aviso,
 							message: $.rup.i18nParse($.rup.i18n.app,"validaciones.seleccionarSoloUno"),
 							OKFunction: function(){
 							}
 						});
		 				return false;
		 			} else {
		 				expedientesSeleccionados = $('#busquedaGeneral').rup_table("getSelectedRows");
		 				anyoExpediente = $("#busquedaGeneral").rup_table("getCol", expedientesSeleccionados, "anyo");
		 				idExpediente = $("#busquedaGeneral").rup_table("getCol", expedientesSeleccionados, "numExp");
		 				tipoExp = $("#busquedaGeneral").rup_table("getCol", expedientesSeleccionados, "idTipoExpediente");
		 				estadoExp = $("#busquedaGeneral").rup_table("getCol", expedientesSeleccionados, "bitacoraExpediente.estadoExp.id");
		 				faseExp = $("#busquedaGeneral").rup_table("getCol", expedientesSeleccionados, "bitacoraExpediente.faseExp.id");
		 				modificarEstadoFase();
		 			}
				}
			}
		]
	});
	
});
