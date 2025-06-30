/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

var capaGestAnulGen = '';
var expedientesSeleccionados = '';
var tipoEntidadSeleccionada = "";
var idEntidadSeleccionada = 0;

function irAAnulacionExpedientes(){
    $.rup_ajax({
            url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/anularExpedientes/maint'
            ,success:function(data, textStatus, jqXHR){
            	capaGestAnulGen = $('#divGestionAnulacionesGeneral').detach();
                $("#divGestionAnulacionesCapa").html(data);
                desbloquearPantalla();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown){
            	alert('Error recuperando datos del paso');
            }
     });

}

function volverAGestionAnulaciones(){
    $("#divGestionAnulacionesGeneral").detach();
    $("#divGestionAnulacionesCapa").html("<div id='divGestionAnulacionesGeneral'></div>");
    $("#divGestionAnulacionesGeneral").html(capaGestAnulGen);
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

function crearComboMotivosAnulacion(id){
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/administracion/datosmaestros/motivosanulacion/findMotivosAnulacionGestAnul",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descEs012"
					: "descEu012",
			value: "id012"
		}
		,blank: ""
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
	});
}

function crearComboTipoExpediente(id){
	$('#'+id).rup_combo({
		loadFromSelect: true
		,width: "100%"
		,orderedByValue: true
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
	});
}




/* ENTIDAD GESTORA */
function creaComboContactoFFiltro(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
	if ( $('#dniContactoEntidadSolicitanteF-button').length   ){
		$('#dniContactoEntidadSolicitanteF').rup_combo("clear");
	}
	
	$('#dniContactoEntidadSolicitanteF').rup_combo({
		source : "/aa79bItzulnetWar/solicitante/findSolicitantesGestAnul/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
		
		sourceParam : {
			value: "dni",
			label : "nombreCompleto" 
		},
	    rowStriping: true,
	    blank: "",
		open: function(){
			var id = $(this).attr("id");
	        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
	    },
	    onLoadSuccess: function(){ 
	    	if (typeof(valorSeleccionar) !== "undefined"){
	    		$("#dniContactoEntidadSolicitanteF").rup_combo("select", valorSeleccionar+'');
	    	}
	    }
	});	
}	



$('#idEntidadSolicitanteF').rup_combo({
	source : "/aa79bItzulnetWar/entidad/entidadesGestAnulacion/-1",
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
		jQuery('#idEntidadSolicitanteF-menu').width(jQuery('#idEntidadSolicitanteF-button').innerWidth());
		jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitanteF').innerWidth());
		$("#idEntidadSolicitanteF_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
		$("#idEntidadSolicitanteF_menu").removeClass("ui-front");
	},
	select: function(){
		if ($('#idEntidadSolicitanteF').val() != ''){
			var comp = $('#idEntidadSolicitanteF').val().split("_");
			creaComboContactoFFiltro(comp[0] , comp[1]);
		}else{
			creaComboContactoFFiltro($('#entidadContactoFacturaEntidadTipo').val() , -1);
		}
	}
});

//	Filtro - Cargar combo de fase entidad en funcion del tipo de entidad seleccionado
jQuery('input[name=tipoEntidadF]:first').click();
jQuery('input[name=tipoEntidadF]').change(function(){
		$('#idEntidadSolicitanteF').rup_combo({
			source : "/aa79bItzulnetWar/entidad/entidadesGestAnulacion/"+$(this).val(),
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
				jQuery('#idEntidadSolicitanteF-menu').width(jQuery('#idEntidadSolicitanteF-button').innerWidth());
				jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitanteF').innerWidth());
				$("#idEntidadSolicitanteF_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
				$("#idEntidadSolicitanteF_menu").removeClass("ui-front");
			},
			select: function(){
				if ($('#idEntidadSolicitanteF').val() != ''){
					var comp = $('#idEntidadSolicitanteF').val().split("_");
					creaComboContactoFFiltro(comp[0] , comp[1]);
				}else{
					creaComboContactoFFiltro($('#entidadContactoFacturaEntidadTipo').val() , -1);
				}
				
			},
		    onLoadSuccess: function(){ 
	    		$("#idEntidadSolicitanteF").rup_combo("select", -1);
	    }
		});
		$('#entidadContactoFacturaEntidadTipo').val($(this).val());
		creaComboContactoFFiltro($(this).val(), '-1');
	});

/* FIN entidad GESTORA...*/


/*
 * ****************************
 * FORMATTERS CELDA - INICIO
 * ****************************
 */

function mostrarCeldaFechaEntrega(rowObject){
	var celda = '<div class="ta_left">';
	if (rowObject.idTipoExpediente === datosExp.tipoExp.interpretacion && rowObject.expedienteInterpretacion != null){
		celda = celda.concat($.rup.i18nParse($.rup.i18n.app,"label.inicio"));
		celda = celda.concat(': ');
		celda = celda.concat(rowObject.expedienteInterpretacion.fechaHoraIni);
		celda = celda.concat('</div>');
		celda = celda.concat('<div class="ta_left">');
		celda = celda.concat($.rup.i18nParse($.rup.i18n.app,"label.fin"));
		celda = celda.concat(': ');
		celda = celda.concat(rowObject.expedienteInterpretacion.fechaHoraFin);
	} else if(rowObject.expedienteTradRev != null){
		celda = celda.concat($.rup.i18nParse($.rup.i18n.app,"label.solicitado"));
		celda = celda.concat(': ');
		celda = celda.concat(rowObject.expedienteTradRev.fechaHoraFinalSolic);
		celda = celda.concat('</div>');
		celda = celda.concat('<div class="ta_left">');
		celda = celda.concat($.rup.i18nParse($.rup.i18n.app,"label.IZO"));
		celda = celda.concat(': ');
		celda = celda.concat(rowObject.expedienteTradRev.fechaHoraFinalIZO);
	}
	celda = celda.concat('</div>');
	
	return celda;
}

/*
 * ****************************
 * FORMATTERS CELDA - FIN
 * ****************************
 */

/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
		
	$('#busquedaGestAnul_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	 $("#busquedaGestAnul_filter_form").rup_validate({
			feedback: $('#busquedaGestAnul_feedback'),
			liveCheckingErrors: false,				
			block:false,
			delay: 3000,
			gotoTop: true, 
			rules:{
				
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
	 		showFieldErrorsInFeedback: false
	});
	
	$("#busquedaGestAnul_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,index: 1
				,right: false
				,id: "volverOrigenPestana"	
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					volverACapaGeneralDashboard();
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.anularExpedientes
				,id:"anularExpedientes"
				,css: "fa fa-ban"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			 if(!$('#busquedaGestAnul').rup_table("isSelected")){
							$.rup_messages("msgAlert", {
								message: $.rup.i18n.app.comun.warningSeleccion
							});	
							return false;
						 }else{
		 					 expedientesSeleccionados = $('#busquedaGestAnul').rup_table("getSelectedRows");
		 					 irAAnulacionExpedientes();
						 }
					}
			}
		]
	});
	
	crearComboTipoExpediente('idTipoExpediente_filter_table');
	crearComboMotivosAnulacion('idMotivosAnulacion_filter_table');
	
	creaComboContactoFFiltro('-1','-1');
	
	$("#busquedaGestAnul").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/busquedaexpaanular",
		toolbar:{
			id: "busquedaGestAnul_toolbar"
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
			$.rup.i18n.app.label.fechaYHoraEntrega,
			$.rup.i18n.app.label.requerimiento,
			$.rup.i18n.app.label.fechaYHoraLimite,
			$.rup.i18n.app.label.estadoRequerimiento
		],
		colModel: [
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
					return '<b><a target="_blank" href="/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/'+rowObject.anyo+'/'+rowObject.numExp+'">' + cellvalue + '</a></b>';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipo",
				align: "center", 
				index: $.rup.lang === 'es' ? "TIPOEXPEDIENTEDESCES"
						: "TIPOEXPEDIENTEDESCEU",
				width: "60", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
				align: "left",
				index: "TITULONORM",
				width: "200", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "fechaEntregaFormateada", 
			 	label: "label.fechaYHoraEntrega",
				align: "left", 
				index: "FECHAENTREGAFORMATEADA",
				width: "200", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return mostrarCeldaFechaEntrega(rowObject);
				}
			},
			{ 	name: $.rup.lang === 'es' ? "bitacoraExpediente.subsanacionExp.tipoRequerimientoDescEs"
					: "bitacoraExpediente.subsanacionExp.tipoRequerimientoDescEu", 
			 	label: "label.requerimiento",
				align: "left", 
				index: $.rup.lang === 'es' ? "TIPOREQUERIMIENTODESCES"
						: "TIPOREQUERIMIENTODESCEU",
				width: "200", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "bitacoraExpediente.subsanacionExp.fechaHoraLimite", 
			 	label: "label.fechaYHoraLimite",
				align: "center", 
				index: "FECHALIMITE",
				width: "150", 
				isDate: true,
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "bitacoraExpediente.subsanacionExp.estadoDescEs"
					: "bitacoraExpediente.subsanacionExp.estadoDescEu", 
			 	label: "label.estadoRequerimiento",
				align: "center", 
				index: $.rup.lang === 'es' ? "ESTADOSUBSANACIONDESCES"
						: "ESTADOSUBSANACIONDESCEU",
				width: "160", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],
        model:"Expediente",
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
			headerContextMenu_enabled: false
			},
		filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$("#busquedaGestAnul").rup_table("hideFilterForm");
				$("#busquedaGestAnul").rup_table("resetSelection");
			}
		},
		report : {
			buttons : [{
				id : "report_busquedaGestAnul",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_busquedaGestAnul",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/xlsxReport",
						click : function(event) {
							if(!$("#busquedaGestAnul_filter_form").valid()){
					 			event.preventDefault();
					 			event.stopImmediatePropagation();
					 		}
						}
					},
					{ i18nCaption:$.rup.i18nParse($.rup.i18n.app, "tabla.pdf"),  
						divId : "report_busquedaGestAnul",
						css : "fa fa-file-pdf-o",
						url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/gestionanulaciones/pdfReport", 
						click : function(event) {
							if(!$("#busquedaGestAnul_filter_form").valid()){
					 			event.preventDefault();
					 			event.stopImmediatePropagation();
					 		}
						}						
					}
				]}
			]
		}
	    ,title: false
	});
	
	
	//Para enlazar desde Dashboard
	//El botón volver sólo se mostrará si esta página está incrustada en otra
	//y hay que hacerlo desdpués de la creación dela tabla pq si no no existe el toolbar
	if (typeof(formatoPestana) === "undefined"){
		jQuery('#busquedaGestAnul_toolbar\\#\\#volverOrigenPestana').hide();
	}
	
	$('#tecnicoAsignado_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/personalIZO/filterTecnicoAsignadoAEstudio",
		sourceParam:{
			value : "dni",
			label : "nombreCompleto" 
		},
		getText: false,
		width: "auto",
		open : function() {
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("max-width", $('#tecnicoAsignado_filter_table').width());
		}
	});
	
	$("#busquedaGestAnul_filter_cleanLinkModificado").click(function(){
		$("#busquedaGestAnul").rup_table("cleanFilterForm");
		jQuery('input[name=tipoEntidadF]:first').click();
		$("#busquedaGestAnul").rup_table("filter");
	});
	
});