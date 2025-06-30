/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */
function volverATareasExpedientes(){
	$("#divEstudioEstimado").detach();
	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
	$("#divDetalleExpediente").html(capaTareasExpediente);
	
	tareasSeleccionadas = [];
	$("#tareasExpedientesForm").rup_table('filter');
	$("#tareasExpedientesForm").rup_table("resetSelection");
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
function fnDatosEstimacion(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	var jsonObject = 
	{
		"anyo": anyoExpediente, 
		"numExp": idExpediente,
		"idTipoExpediente": tipoExp
	};
	
	$.ajax({

        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/datosEstimacion' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(jsonObject) 
        ,async: false 
        ,success:function(data){
        	
        	if (data != null){
        		
        		$("#fechaInicioEstudio_detail_table").val(obtenerValorCampo(data.fechaInicio));
    			$("#horaInicioEstudio_detail_table").val(obtenerValorCampo(data.horaInicio));
    			$("#fechaFinEstudio_detail_table").val(obtenerValorCampo(data.fechaFin));
    			$("#horaFinEstudio_detail_table").val(obtenerValorCampo(data.horaFin));
        		
    			if (data.datosTareaTrados != null){
    				$("#numTotalPal_filter").val(obtenerValorCampo(data.datosTareaTrados.numTotalPal));
        			$("#numPalConcor084_filter").val(obtenerValorCampo(data.datosTareaTrados.numPalConcor084));
        			$("#numPalConcor8594_filter").val(obtenerValorCampo(data.datosTareaTrados.numPalConcor8594));
        			$("#numPalConcor9599_filter").val(obtenerValorCampo(data.datosTareaTrados.numPalConcor9599));
        			$("#numPalConcor100_filter").val(obtenerValorCampo(data.datosTareaTrados.numPalConcor100));
    			}
    			
        	}
        	
        	desbloquearPantalla();
        	
        }
		,error: function (){
	    	$('#estudioEstimado_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
	    	desbloquearPantalla();
	    }
	});
	
}

function fnCrearComboTipoTarea(){
	$('#tipoTareaEstudio_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea/findTiposTareaEstimacion/"+tipoExp
		,sourceParam:{
			value : "id015",
			label : $.rup.lang === 'es' ? "descEs015"
					: "descEu015", 
		}
		,blank: ""
		,width: "auto"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
	});
}

function fncNivelUsuarioCombo(){
	$("#nivelUsuarioEstudio_detail_table").rup_combo({
		loadFromSelect: true
		,blank: ""
		,width: "100%"
		,orderedByValue: false
		,rowStriping: true
		,open: function(){
			jQuery('#nivelUsuarioEstudio_detail_table-menu').width(jQuery('#nivelUsuarioEstudio_detail_table-button').innerWidth());
		}
	});
}

function fnCrearComboTipoRelevancia(){
	$('#tipoRelevancia_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/estado/A"
		,sourceParam:{
			value : "idTipoRelevancia",
			label : $.rup.lang === 'es' ? "descRelevanciaEs"
					: "descRelevanciaEu", 
		}
		,blank: ""
		,width: "auto"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
	});
}

function fnObtenerEstudio(){
	
	if ($("#estudioEstimado_filter_form").valid()){	
		$("#estudioEstimado").rup_table('filter');
		return true;
	} else {
		return false;
	}
	
}

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	bitacoraUpdate(false);
	
	$('#estudioEstimado_feedback').rup_feedback({
		block : false
	});
	
	fnFechaDesdeHasta("fechaInicioEstudio_detail_table", "fechaFinEstudio_detail_table");
	
	fnCrearComboTipoTarea();
	fncNivelUsuarioCombo();
	fnCrearComboTipoRelevancia();
	fnDatosEstimacion();
	
	$("#estudioEstimado_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volverATareas"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					volverATareasExpedientes();
				}
			}
		]
	});
	
	$("#estudioEstimado").rup_table({	
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/estimado",
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
			$.rup.i18n.app.label.recurso,
			$.rup.i18n.app.label.numRecursos,
			$.rup.i18n.app.label.costeHoras,
			$.rup.i18n.app.label.trabajoDia
		],
		colModel: [
			{ 	name: "recurso", 
			 	label: "label.recurso",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
			,{ 	name: "numRecursos", 
			 	label: "label.numRecursos",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
			,{ 	name: "costeHoras", 
			 	label: "label.costeHoras",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
			,{ 	name: "costeHorasRecurso", 
			 	label: "label.trabajoDia",
				align: "center", 
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
        ],
        model:"EstudioEstimado",
        usePlugins:[
        	 "toolbar",
       		 "filter",
       		 "fluid",
       		 "responsive"
         	],
        primaryKey: [""],
		loadOnStartUp: false
	});
	
	$("#estudioEstimado_filter_form").rup_validate({
		feedback: $('#estudioEstimado_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"tiposTarea.id015": { required: true },
			"fechaInicio": { required: true, date: true },
			"horaInicio": { required: true, hora: true, maxlength: 5 },
			"fechaFin": { required: true, date: true, fechaHastaMayor: "fechaInicio" },
			"horaFin": { required: true, hora: true, maxlength: 5, horaFechaHastaMayor: ["fechaInicio","fechaFin","horaInicio"] },
			"nivUsuario": {required: true},
			"idTipoRelevancia": {required: true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	$("#estudioEstimado_button").click(function(){
		fnObtenerEstudio();
	});
	
});