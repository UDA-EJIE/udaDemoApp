
var refAlbaran;
var idLoteTxt;
var idEstado;
var importePrevistoAlb;
var selectedRefAlbaran;
var estadoAlbaranSinPagar = false;
var sinTareasAsociadas = false;
var filterFormObjectActAlb;
var listIdObjects = [];

function borrarAlbaran(selectedRefAlbaran){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/servicios/actualizacionalbaran/borrarAlbaran/'+selectedRefAlbaran,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	
			$("#actualizarAlbaran").rup_table("filter");
			$('#actualizarAlbaran_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.albaranBorrado, "ok");
			desbloquearPantalla();
		}
	});
}

function comprobarAlabaranSelec(selectedRefAlbaran){
	$.rup_ajax({
		 type: "GET",
		 url: '/aa79bItzulnetWar/servicios/actualizacionalbaran/comprobarEstadoAlbaranTareas/' + selectedRefAlbaran,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	
			 if(data === 1){
				 $.rup_messages("msgAlert", {
				     title: $.rup.i18nParse($.rup.i18n.app,"label.borrarAlbaran"),
					 message: $.rup.i18nParse($.rup.i18n.app,"mensajes.estadoPagado"),
			     });	
				desbloquearPantalla();
			 } else if(data === 2){
				 $.rup_messages("msgAlert", {
				     title: $.rup.i18nParse($.rup.i18n.app,"label.borrarAlbaran"),
					 message: $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAsociadas"),
			     });
				 desbloquearPantalla();
			 } else {
				 desbloquearPantalla();
				 $.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.app,"label.borrarAlbaran"),
					message: $.rup.i18nParse($.rup.i18n.app,"mensajes.borrarAlbaran"),
					OKFunction: function(){
	                   bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	                   borrarAlbaran(selectedRefAlbaran);
					}
				 });
			 }
		 }
	 });          
}

function volcarIdsSeleccionadosAlb(){
	volcarListaAStringAlbaranesSeleccionados(",");
	if(null != selectedRefAlbaran && selectedRefAlbaran.length > 0){
         comprobarAlabaranSelec(selectedRefAlbaran);
     } else {
	 	 $.rup_messages("msgAlert", {
	 		 title: $.rup.i18nParse($.rup.i18n.app,"label.borrarAlbaran"),
			 message: $.rup.i18n.app.comun.warningSeleccion
		 });
	 	 desbloquearPantalla();
     }
}

function detalleAlbaran(id) {
	bloquearPantalla();
	var datos = {
		idAlbaran : id,
	};
	$.rup_ajax({
		type : 'POST',
		url : '/aa79bItzulnetWar/servicios/actualizacionalbaran/detalleAlbaran',
		contentType : "application/json",
		dataType : 'html',
		data : JSON.stringify(datos),
		async : false,
		cache : false,
		success : function(data) {
			capaPestGeneral = $('#actualizarAlbaranGeneralDiv').detach();
	   		$("#divActualizarAlbaranGeneral").html(data);
		},
		complete : function() {
			desbloquearPantalla();
		}
	});
}

function creaComboLotes(){
	$("#nombreLote_filter_table").rup_combo({
		parent : [ "empresaProv_filter_table" ],
		sourceGroup : "/aa79bItzulnetWar/lotes/getLotesEntidad",
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
			jQuery('#nombreLote_filter_table-menu').width(jQuery('#nombreLote_filter_table-button').innerWidth());
		}
	});	
	
	
}

jQuery(function($){
	
	$('#actualizarAlbaran_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#empresaProv_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/administracion/empresasproveedoras/empresasProveedorasConTareasNoPagadasYPagadas",
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
		}
	});
	
	$("#actualizarAlbaran").rup_table({
		url: "/aa79bItzulnetWar/servicios/actualizacionalbaran",
		toolbar:{
			id: "actualizarAlbaran_toolbar"
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
					i18nCaption: $.rup.i18n.app.label.borrarAlbaran
					,css: "fa fa-times"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click: function(event){
						event.preventDefault();
			 			event.stopImmediatePropagation();
			 			listIdObjects = [];
						bloquearPantalla();
						obtenerIdsSeleccionadosRupTable("actualizarAlbaran", filterFormObjectActAlb, "volcarIdsSeleccionadosAlb");
					}
				}
			]
		},
		colNames: [
			"",
			$.rup.i18n.app.label.refAlbaran,
			$.rup.i18n.app.label.fechaCreacion,
//			"",
			"",
			$.rup.i18n.app.label.estadoAlbaran,
			txtImpPrevisto, txtImpFactura
		],
		colModel: [
			{ 	name: "idAlbaran", 
				hidden: true, 
			},
			{ 	name: "refAlbaran", 
			 	label: "label.refalbaran",
			 	index: "REFALBARAN",
			 	align: "left", 
				width: "250", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return '<b><a href="#" onclick="detalleAlbaran(' + rowObject.idAlbaran + ')">' + cellvalue + '</a></b>';
				}
			},
			{ 	name: "fechaHoraAlta", 
			 	label: "label.dtcreacion",
			 	index: "FECHAALTA",
			 	align: "center", 
			 	width: "150", 
			 	isDate: true,
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "estado", 
				label: "label.estado",
				editable: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "descEstadoEs":"descEstadoEu",
			 	label: "label.estadoAceptTarea",
			 	index: $.rup.lang === 'es' ? "DESCES":"DESCEU",
			 	align: "left", 
				width: "150", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "importePrevisto", 
				label: "label.impprevisto",
				index: "IMPORTEPREVISTO",
				align: "right", 
				width: "250",
				isNumeric: true
			},
			{ 	name: "importeFactura", 
			 	label: "label.imppagado",
			 	index: "IMPORTEFACTURA",
			 	align: "right", 
			 	width: "250",
			 	isNumeric: true
			}			
        ],
        model:"Albaran",
        usePlugins:[
        	"feedback",
        	"toolbar",
        	"filter",
        	"multiselection",
        	"report"
         	],
        multiSort: true,
     	sortname : "REFALBARAN",
		sortorder : "asc",
        primaryKey: ["idAlbaran"],
		loadOnStartUp: false,
		multiselect: true,
		multiselection:{
			headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		},
		report : {
			buttons : [{
				id : "report_actualizacionAlbaran",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_actualizacionAlbaran",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/servicios/actualizacionalbaran/xlsxReport",
						click : function(event) {
							if(!$('#actualizarAlbaran_filter_form').valid()){
								event.preventDefault();
								event.stopImmediatePropagation();
								return false;
							}
						}
					},
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_actualizacionAlbaran", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/servicios/actualizacionalbaran/pdfReport", 
						click : function(event){
							if(!$('#actualizarAlbaran_filter_form').valid()){
								event.preventDefault();
								event.stopImmediatePropagation();
								return false;
							}
						}
					} 
				]}
			]
		},
		filter : {
			validate : {
				feedback : $("#actualizarAlbaran_feedback"),
				showFieldErrorAsDefault : false,
				showErrorsInFeedback : true,
				showFieldErrorsInFeedback : false,
				     rules : {
					      "lotes.empresasProveedoras.codigo" : { required : true},
					      "lotes.idLote" : { required : true}
				}
			},beforeFilter: function(){
				$("#actualizarAlbaran").rup_table("resetSelection");
			}
		},
		title: false,
		loadComplete: function(data){ 
			filterFormObjectActAlb = obtenerFiltrosTabla('actualizarAlbaran');
		}
	});
	
	$("#actualizarAlbaran_filter_cleanLink_boton").on("click", function(){
		$("#actualizarAlbaran").rup_table('resetForm', $('#actualizarAlbaran_filter_form'));
		$("#actualizarAlbaran").rup_table('clearGridData', true);
		$('[id$="error"]').remove();
		ocultarMensajeFeedback('actualizarAlbaran_feedback');
	});
});