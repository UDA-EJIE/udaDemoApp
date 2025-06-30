var capaListadoAlaranes ="";

var configCombo = {
	loadFromSelect : true,
	width : "100%",
	ordered : false,
	rowStriping : true,
	open : function() {
		var id = $(this).attr("id");
		jQuery("#" + id + "-menu").width(jQuery("#" + id + "-button").innerWidth());
	}
};

function showDetalleDetach(id) {
	bloquearPantalla();
	var datos = {
		idAlbaran : id,
		idLote : $('#nombreLote029_filter_table').rup_combo("getRupValue")
	};
	$.rup_ajax({
				type : 'POST',
				url : '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/detalle',
				contentType : "application/json",
				dataType : 'html',
				data : JSON.stringify(datos),
				async : false,
				cache : false,
				success : function(data) {
					capaListadoAlaranes = $('#divPagamentosGeneral').detach();
					$('#divPagamentosCapa').html(data);
				},
				complete : function() {
					desbloquearPantalla();
				}
			});
}
function volverACapaPagamentos(){
	$("#divDetallePagamentos").detach();
	$("#divPagamentosCapa").append("<div id='divPagamentosGeneral'></div>");
	$("#divPagamentosGeneral").html(capaListadoAlaranes);
	$("#pagamentos").trigger("reloadGrid");
}


function creaComboLotes(){
	$("#nombreLote029_filter_table").rup_combo({
		parent : [ "empresaProv_filter_table" ],
		source : "/aa79bItzulnetWar/lotes/getLotesNoPagados",
		sourceParam : {
			label : "nombreLote",
			value : "idLote",
			style : "css"
		},
		blank : "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			jQuery('#nombreLote029_filter_table-menu').width(jQuery('#nombreLote029_filter_table-button').innerWidth());
		}
	});	
}
function creaComboAlbaranes(){
	$("#refalbaran_filter_table").rup_combo({
		parent : [ "nombreLote029_filter_table" ],
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/albaranes/getAlbaranesLoteNoPagado",
		sourceParam : {
			label : "refAlbaran",
			value : "idAlbaran",
			style : "css"
		},
		blank : "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			jQuery('#refalbaran_filter_table-menu').width(jQuery('#refalbaran_filter_table').innerWidth());
		}
	});	
}


jQuery(function($) {
	$("#estado099_filter_table").rup_combo(configCombo);
	$("#tipoTarea_filter_table").rup_combo(configCombo);
	
	$("#pagamentos_feedback").rup_feedback({
		block: false,
		delay: 3000
	});

	$("#pagamentos").rup_table({
		url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos",
		colNames : [ "codEntidad", "idLote", "idAlbaran",
				txtRefAlbaran, txtDtCreacion, txtNumTareas,
				txtNumExpAsociados, txtEstadoAlbaran,
				txtImpTotal ],
		colModel : [
				{
					name : "codEntidad",
					label : "label.codEntidad",
					align : "",
					width : 50,
					editable : true,
					fixed : false,
					hidden : true,
					resizable : true,
					sortable : true
				},
				{
					name : "idLote",
					label : "idLote  ",
					align : "",
					width : 50,
					editable : true,
					fixed : false,
					hidden : true,
					resizable : true,
					sortable : true
				},
				{
					name : "idAlbaran",
					label : "idAlbaran",
					align : "",
					width : 50,
					editable : true,
					fixed : false,
					hidden : true,
					resizable : true,
					sortable : true
				},

				{
					name : "refAlbaran",
					label : "label.refalbaran",
					index: "REFALBARANNORM",
					align : "",
					width : 60,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					formatter : function(cellvalue, options,
							rowObject) {
						if (rowObject.estadoAlbaran != 1
								|| rowObject.idAlbaran == 0) {
							return '<b><a href="#" onclick="showDetalleDetach('
									+ rowObject.idAlbaran
									+ ')">'
									+ cellvalue
									+ '</a></b>';
						} else {
							return '<b>' + cellvalue + '</b>';
						}
					}
				},
				{
					name : "fechaAlta",
					label : "label.fechaAlta",
					align : "center",
					width : 50,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					isDate: true
				},
				{
					name : "ntareas",
					label : "label.ntareas",
					align : "right",
					width : 50,
					isNumeric: true,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "nexpedientes",
					label : "label.nexpedientes",
					align : "right",
					width : 50,
					isNumeric: true,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "descEstadoAlbaran",
					index : "DESCESTADOALBARAN",
					label : txtEstadoAlbaran,
					align : "",
					width : 70,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					cellattr: function (rowId, tv, rawObject, cm, rdata) { 
					    return 'style="white-space: normal;"';
					}
				},
				{
					name : "importeTotal",
					label : txtImpTotal,
					align : "right",
					width : 50,
					isNumeric: true,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				} ],

		model : "Pagamentos",
		usePlugins : [ "feedback", "toolbar", "responsive","filter", "report"],
		report : {
			buttons : [ {
				id : "reports_listado",
				i18nCaption : $.rup.i18nParse($.rup.i18n.app, "comun.exportar"),
				right : true,
				buttons : [
						{
							i18nCaption : $.rup.i18nParse($.rup.i18n.app,"tabla.excel"),																										
							divId : "reports_listado",
							css : "fa fa-file-excel-o",
							url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/xlsxReport",
							click : function(event) {
								if (!$("#pagamentos_filter_form").valid()){
									event.preventDefault();
									event.stopImmediatePropagation();														
								}
							}
						},
						{
							i18nCaption : $.rup.i18nParse($.rup.i18n.app,"tabla.pdf"),																										
							divId : "reports_listado",
							css : "fa fa-file-pdf-o",
							url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/pagamentos/pdfReport",
							click : function(event) {
								if (!$("#pagamentos_filter_form").valid())				
							 { event.preventDefault();
							   event.stopImmediatePropagation()																										
								}
							}
						}, ]
			} ]
		},

		primaryKey : "idAlbaran",
		sortname : "idAlbaran",
		sortorder : "asc",
		loadOnStartUp : false,
		formEdit : {
			detailForm : "#pagamentos_detail_div"
		},
		filter : {
			validate : {
				feedback : $("#pagamentos_feedback"),
				showFieldErrorAsDefault : false,
				showErrorsInFeedback : true,
				showFieldErrorsInFeedback : false,
				     rules : {
					      "codEntidad" : { required : true},
					      "idLote" : { required : true}
				}
			}
			,beforeFilter: function(){
				$("#pagamentos").rup_table("hideFilterForm");
			}
		}
	});
	
	
	$("#empresaProv_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/administracion/empresasproveedoras/empresasProveedorasConTareasNoPagadas",
		sourceParam : {
			label : "descEu",
			value : "codigo",
			style : "css"
		},
		blank : "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			jQuery('#empresaProv_filter_table-menu').width(jQuery('#empresaProv_filter_table-button').innerWidth());
		},
		onLoadSuccess : function() {
			creaComboLotes();
			creaComboAlbaranes();
		}
	});
	
	
	$("#pagamentos_filter_limpiar").click(function(event){
		event.preventDefault();
		event.stopImmediatePropagation();	
		$("#empresaProv_filter_table").rup_combo('clear');
		$("#estado099_filter_table").rup_combo('clear');
		$("#tipoTarea_filter_table").rup_combo('clear');
		$("#idTarea_filter_table").val('');
		$("#numexp1_filter_table").val('');
		$("#numexp2_filter_table").val('');
		$("#pagamentos_feedback").hide();
		eliminarMensajesValidacion();
	});

	jQuery('.ui-jqgrid-hbox').css({width:'100%'});
});