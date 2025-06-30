var selectedId;
var capaClonacionInicio ="";

function llamadaClonarExpediente(elAnyo,elNumExp){
	var jsonExpediente = 
	{
	    "anyoOrig": elAnyo, 
	    "numExpOrig": elNumExp
	};
	jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/servicios/clonacionexpedientes/clonarExpediente",
		dataType: "json",
		contentType: 'application/json', 
		data: jQuery.toJSON({"clonacion":jsonExpediente}),
		cache: false,
		async: false,
		success: function (data) {
			$('#clonarExpediente_feedback').rup_feedback("set",  $.rup.i18n.app.mensajes.clonacionSolicitada, "ok");
		},
		error: function(){		   	 	
	   		$('#clonarExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
		}
    });
}


function volverACapaGeneralClonacion(){
	$("#divClonacionCapa").detach();
	$("#divClonacion").html("<div id='divClonacionCapa' class='container-fluid'></div>");
	$("#divClonacionCapa").html(capaClonacionInicio);
}

function irProcesosClonacion(){
	cambiarCapaGeneral('/aa79bItzulnetWar/servicios/clonacionexpedientes/procesos/maint');
}

function cambiarCapaGeneral(url){
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: url 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaClonacionInicio = $('#divClonacionGeneral').detach();
	   		 $("#divClonacionCapa").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}
	



jQuery(function($){
	$('#clonarExpediente_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
	});
	$("#estadoX_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#estadoX_filter_table-menu').width(jQuery('#estadoX_filter_table-button').innerWidth());
			}
	});
	$("#estadoX_filter_table").rup_combo('disable');
	
	$("#clonarExpediente").rup_table({
		url: "/aa79bItzulnetWar/servicios/clonacionexpedientes",
		toolbar:{
			id: "clonarExpediente_toolbar"
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
					i18nCaption: $.rup.i18n.app.boton.clonar
					,css: "fa fa-clone"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(e){
					 	e.preventDefault();
						e.stopImmediatePropagation();   
						selectedId = $("#clonarExpediente").rup_table("getSelectedRows");
						if( isNotEmpty(selectedId) ){
							
							$.rup_messages("msgConfirm", {
								title: $.rup.i18nParse($.rup.i18n.app,"label.clonarExpediente"),
								message: $.rup.i18nParse($.rup.i18n.app,"mensajes.clonarExpediente")+' <br />'+$.rup.i18nParse($.rup.i18n.app,"label.deseaContinuar"),
								OKFunction: function(){
									setTimeout(function() {
										$("#excepcionfacturacion_detail_div").rup_dialog("close");
										var claveCompuesta = selectedId[0].split(",");
				                    	llamadaClonarExpediente(claveCompuesta[0], claveCompuesta[1]);
									}, 20);
								}
							});
							
						} else {
							$.rup_messages("msgAlert", {
	 							title: $.rup.i18nParse($.rup.i18n.app,"label.clonarExpediente"),
	 							message: $.rup.i18nParse($.rup.i18n.app,"validaciones.seleccionarUno"),
	 							OKFunction: function(){
	 							}
	 						});
						}
					}
				},
				{obj : {  
					i18nCaption: $.rup.i18n.app.boton.procesosClonacion
					,css: "fa fa-eye"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(e){
					 	e.preventDefault();
						e.stopImmediatePropagation();   
						irProcesosClonacion();
					}
				}
			]
		},
		colNames: [
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.entidadGestora,
			$.rup.i18n.app.label.contactoGestor,
			""
		],
		colModel: [
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
			 	index: "ANYONUMEXPCONCATENADO",
			 	align: "center", 
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return '<b style="display: block;">' + cellvalue + '</b>';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs":"tipoExpedienteDescEu",
			 	label: "label.tipoExpediente",
			 	index: $.rup.lang === 'es' ? "TIPO_EXPEDIENTE_ES":"TIPO_EXPEDIENTE_EU",
			 	align: "center", 
				width: "70", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
			 	index: "TITULO_051",
			 	align: "left", 
				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "gestorExpediente.entidad.descAmpEu", 
				label: "label.entidadGestora",
				index: "DESC_AMP_EU",
				align: "left", 
				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "vipGestor", 
				label: "label.gestorExpediente",
				index: "GESTORVIPCOLORDER",
				align: "left", 
				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "bitacoraExpediente.faseExp.id", 
				label: "label.faseExpediente",
				editable: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			}
			
        ],
        model:"Expediente",
        usePlugins:[
        	"toolbar",
       		 "filter"
         	],
        multiSort: true,
     	sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "asc",
        primaryKey: ["anyo", "numExp"],
        multiplePkToken:",",
		loadOnStartUp: true
	});
});