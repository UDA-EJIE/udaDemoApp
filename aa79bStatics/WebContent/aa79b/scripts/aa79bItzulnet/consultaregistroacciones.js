
function asociarEventosDetalleRegistro(){
	$( "a.abrirModalRegistro").unbind( "click" );
	
	$('a.abrirModalRegistro').click(function(event){
		event.preventDefault();	
		var elIdReg =  $(this).data("id");
		abrirModalReg(elIdReg);
	});
}

function abrirModalReg(id){

	$.rup_ajax({
		type: "GET",
		url: "/aa79bItzulnetWar/registroacciones/obtenerObservacionesRegistro/" + id,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(data){	
			$("#detalleAccion_dialog").rup_dialog('open');
			$("#detalleAccion_detail_table").val(data);
			$("#detalleAccion_detail_table").each(function() {
		        $(this).height($(this).prop('scrollHeight'));
		    });
		}
	});
}

jQuery(function($) {
	
	$('#consultaRegistroAcciones_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#consultaRegistroAcciones_filter_form").rup_validate({
		feedback: $('#consultaRegistroAcciones_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
	    	"fechaRegistroDesde": { date: true },
			"horaRegistroDesde": { hora: true, maxlength: 5 },
			"fechaRegistroHasta": { date: true, fechaHastaMayor: "fechaRegistroDesde" },
			"horaRegistroHasta": { hora: true, maxlength: 5, horaFechaHastaMayorFitros: ["fechaRegistroDesde","fechaRegistroHasta","horaRegistroDesde"] },
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	fnFechaDesdeHasta("fechaRegistroDesde_filter", "fechaRegistroHasta_filter");
	
	$("#detalleAccion_dialog").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: false,
	    width: "900",
	    title: $.rup.i18nParse($.rup.i18n.app,"boton.detalleAccion"),
	    buttons: [{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"aceptar"),
			click: function () {
				$("#detalleAccion_dialog").rup_dialog('close');
			}
		}],
	});
	
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
		,change: function(){
			if($('#idTipoExpediente_filter_table').rup_combo("getRupValue")==='I'){
				//si selecciona tipo de interpretacion, como no tiene indConfidencial, seleccionamos la opcion todos y deshabilitamos el combo
				$("#indConfidencial").rup_combo("select","");
				$("#indConfidencial").rup_combo("disable");
			} else if($("#indConfidencial").rup_combo("isDisabled")){
				//si selecciona todos o tipo trad o rev, si esta deshabilitado, lo habilitamos
				$("#indConfidencial").rup_combo("enable");
				
			}
		}
	});
	
	$("#accion_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#accion_filter_table-menu').width(jQuery('#accion_filter_table-button').innerWidth());
		}
	});
	
	$("#indConfidencial").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#indConfidencial-menu').width(jQuery('#indConfidencial-button').innerWidth());
		}
	});
	
	$('#ptoAplicacion_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/registroacciones/getPtoDeMenu/A",
		sourceParam :{
			label: $.rup.lang === 'es' ? "descEs"
					: "descEu", 
			value: "idPuntoMenu",
			style:"css"
		},
		blank : "",
		getText: false,
		rowStriping: true,
		open : function() {
			jQuery('#ptoAplicacion_filter_table-menu').width(jQuery('#ptoAplicacion_filter_table-button').innerWidth());
		}
	});
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	});
	
	$("#consultaRegistroAcciones").rup_table({
		url: "/aa79bItzulnetWar/registroacciones",
		toolbar:{
			id: "consultaRegistroAcciones_toolbar"
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
			$.rup.i18n.app.label.fechaHoraRegistro,
			$.rup.i18n.app.label.usuario,
			$.rup.i18n.app.label.ptoAplicacion,
			$.rup.i18n.app.label.accion,
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.confidencial,
			$.rup.i18n.app.boton.detalle
		],
		colModel: [
			{ 	name: "idRegistroAccion", 
				label: "label.idRegistroAcciones",
				index: "IDREGISTROACCION043",
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraRegistro", 
			 	label: "label.fechaRegistro",
			 	index: "FECHAREGISTRO043",
			 	align: "center", 
			 	width: "120", 
			 	isDate: true,
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "usuarioRegistro", 
			 	label: "label.usuario",
			 	index: "USUARIOREGISTRO043",
			 	align: "center", 
				width: "100", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name: $.rup.lang === 'es' ? "puntosMenuAplicacion.descEs":"puntosMenuAplicacion.descEu", 
				label: "label.ptoAplicacion",
				index: $.rup.lang === 'es' ? "IDPUNTOMENUDESCES":"IDPUNTOMENUDESCEU",
				align: "left", 
				width: "280", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "accionDescEs":"accionDescEu",
			 	label: "label.accion",
			 	index: $.rup.lang === 'es' ? "ACCIONDESCES":"ACCIONDESCEU",
				align: "center", 
				width: "100", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.anyoNumExpConcatenado",
				align: "center", 
				width: "100", 
				index: "ANYONUMEXPCONCATENADO",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.indConfidencialDescEs":"expedienteTradRev.indConfidencialDescEu", 
			 	label: "label.indConfidencial",
				align: "center", 
				width: "80", 
				index: $.rup.lang === 'es' ? "INDCONFIDENCIALDESCES":"INDCONFIDENCIALDESCEU",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "observ", 
				label: "label.detalleReqSub",
				align: "left", 
				width: "300", 
				index: "OBSERV043",
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue,options, rowObject){
					if (cellvalue != null) {
						return '<b><a href="#" class="abrirModalRegistro" data-id="'+ rowObject.idRegistroAccion +'">' + cellvalue.slice(0, 30) + '...' + '</a></b>';
					} else {
						return '';
					}
				}
			}
        ],
        model:"RegistroAcciones",
        usePlugins:[
        	"toolbar",
       		"filter",
       		"report"
         	],
        multiSort: false,
     	sortname : "IDREGISTROACCION043",
		sortorder : "desc",
        primaryKey: ["idRegistroAccion"],
		loadOnStartUp: true,
		report : {
			buttons : [{
				id : "report_registroAcciones",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_registroAcciones",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/registroacciones/xlsxReport",
						click : function(event) {
						}
					},
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_registroAcciones", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/registroacciones/pdfReport", 
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
				$('[id^="hora"][id$="error"]').remove();
				if(!$("#consultaRegistroAcciones_filter_form").valid()){
					return false;
				}
				$("#consultaRegistroAcciones").rup_table("resetSelection");
			}
		}, gridComplete: function () {
			asociarEventosDetalleRegistro();
	   	 }
	});
	
});