var capaFactExpGen = "";
var tipoExpediente = "";
var tipoEntidadSeleccionada = "";
var idEntidadSeleccionada = 0; 
var dniContactoSeleccionado = ""; 

function mostrarServicioActualizacionDatosFacturacion(){
	window.open('/aa79bItzulnetWar/servicios/actDatosFacturacion/maint');
}

/**
 * ir a confeccionar factura
 * @returns
 */
function irConfeccionarFactura(){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/confeccionarfactura/maint' 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaFactExpGen = $('#divFacturacionGeneral').detach();
	   		 $("#divFacturacionCapa").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
	   		desbloquearPantalla();
			alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });

}

/**
 * volver a consulta expedientes sin facturar
 * @returns
 */
function volverACapaGeneral(){
	$("#divFacturacionGeneral").detach();
	$("#divFacturacionCapa").html("<div id='divFacturacionGeneral'></div>");
	$("#divFacturacionGeneral").html(capaFactExpGen);
	$("#busquedaFactExp").trigger('reloadGrid');
}

/*
 * ****************************
 * CREACIÓN COMBOS Y FECHAS - INICIO
 * ****************************
 */
/**
 * carga combo tipos expediente
 * @returns 0: interpretacion - 1: trad/rev
 */
function fncTipoExpedienteCombo(){
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,orderedByValue: false
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
	});
}

function fncFechasSolicitud(){
	fnFechaDesdeHasta("fechaSolicitudDesde_filter", "fechaSolicitudHasta_filter");
}

function fncFechasEntregaReal(){
	fnFechaDesdeHasta("fechaEntregaRealDesde_filter", "fechaEntregaRealHasta_filter");
}

function fncEntidadSolicitanteCombo(valueTipoSolicitante){
	crearEntidadSolicitanteCombo(valueTipoSolicitante);
}

function crearEntidadSolicitanteCombo(tipoEntidadSolicitante){
	$('#idEntidadSolicitante_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/entidadesSolicitantes?tipo="+tipoEntidadSolicitante,
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
			jQuery('#idEntidadSolicitante_filter_table-menu').width(jQuery('#idEntidadSolicitante_filter_table-button').innerWidth());
		},
		select: function(){
			crearContactoSolicitanteCombo($('input[name=tipoEntidadSolicitante]:checked').val(), $('#idEntidadSolicitante_filter_table').rup_combo("getRupValue"));
		},
   		onLoadSuccess: function(){
   			$('#idEntidadSolicitante_filter_table').rup_combo("select","");
   		}
	});
	
}


function crearContactoSolicitanteCombo(tipoSolSeleccionada, entidadSolSeleccionada){
	var tipoEntidadSolSeleccionada = ""; // tipo
	var idEntidadSolSeleccionada = ""; // tipo + id
	if(entidadSolSeleccionada){
		//entidad seleccionada
		var datosEntSolSeleccionada = entidadSolSeleccionada.split("_");	
		tipoEntidadSolSeleccionada = datosEntSolSeleccionada[0];
		$('#entidadSolicitanteTipo').val(tipoEntidadSolSeleccionada);
		idEntidadSolSeleccionada = datosEntSolSeleccionada[1];	
		$('#idEntidadSolicitante').val(idEntidadSolSeleccionada);
	}else if(tipoSolSeleccionada){
		//tipo entidad seleccionada
		tipoEntidadSolSeleccionada = tipoSolSeleccionada;
		$('#entidadSolicitanteTipo').val(tipoEntidadSolSeleccionada);
	}else{
		$('#idEntidadSolicitante').val("");
	}
	
	
	$('#contactoSolicitante_filter_table').rup_combo({
   		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/contactosSolicitantes?tipo="+tipoEntidadSolSeleccionada+"&codigo="+idEntidadSolSeleccionada,
   		sourceParam:{
   			value : "dni",
   			label : "nombreCompleto" 
   		},
   		blank: "",
   		getText: false,
   		width: "auto",
   		open : function() {
   			jQuery('#contactoSolicitante_filter_table-menu').width(jQuery('#contactoSolicitante_filter_table-button').innerWidth());
   		},
   		onLoadSuccess: function(){
   			$('#contactoSolicitante_filter_table').rup_combo("select","");
   		}
   	}); 
}

function fncEntidadAFacturarCombo(valueTipoSolicitante){
	crearEntidadAFacturarCombo(valueTipoSolicitante);
}

function crearEntidadAFacturarCombo(tipoEntidadSolicitante){
	$('#idEntidadFactExpediente_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/entidadesFacturar?tipo="+tipoEntidadSolicitante,
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
			jQuery('#idEntidadFactExpediente_filter_table-menu').width(jQuery('#idEntidadFactExpediente_filter_table-button').innerWidth());
		},
		select: function(){
			crearContactoAFacturarCombo($('input[name=tipoEntidadFactExpediente]:checked').val(), $('#idEntidadFactExpediente_filter_table').rup_combo("getRupValue"));
		},
   		onLoadSuccess: function(){
   			$('#idEntidadFactExpediente_filter_table').rup_combo("select","");
   		}
	});
	
}


function crearContactoAFacturarCombo(tipoFactSeleccionada, entidadFactSeleccionada){
	var tipoEntidadFactSeleccionada = "";
	var idEntidadFactSeleccionada = "";
	if(entidadFactSeleccionada){
		//entidad seleccionada
		var datosEntFactSeleccionada = entidadFactSeleccionada.split("_");	
		tipoEntidadFactSeleccionada = datosEntFactSeleccionada[0];
		$('#entidadFactExpedienteTipo').val(tipoEntidadFactSeleccionada);
		idEntidadFactSeleccionada = datosEntFactSeleccionada[1];	
		$('#idEntidadFactExpediente').val(idEntidadFactSeleccionada);
	}else if(tipoFactSeleccionada){
		//tipo entidad seleccionada
		tipoEntidadFactSeleccionada = tipoFactSeleccionada;
		$('#entidadFactExpedienteTipo').val(tipoEntidadFactSeleccionada);
	}else{
		$('#idEntidadFactExpediente').val("");
	}
	
	$('#contactoFactExpediente_filter_table').rup_combo({
   		source : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/contactosFacturar?tipo="+tipoEntidadFactSeleccionada+"&codigo="+idEntidadFactSeleccionada,
   		sourceParam:{
   			value : "dni",
   			label : "nombreCompleto" 
   		},
   		blank:"",
   		getText: false,
   		width: "auto",
   		open : function() {
   			jQuery('#contactoFactExpediente_filter_table-menu').width(jQuery('#contactoFactExpediente_filter_table-button').innerWidth());
   		},
   		onLoadSuccess: function(){
   			$('#contactoFactExpediente_filter_table').rup_combo("select","");
   		}
   	}); 
}




function fncToolbarFacturacionExpedientes(){
	$("#busquedaFactExp_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.confeccionarFactura
				,id:"confeccionarFacturaNavigate"
				,css: "fa fa-file-o"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			 if(!$('#busquedaFactExp').rup_table("isSelected")){
							$.rup_messages("msgAlert", {
								message: $.rup.i18n.app.comun.warningSeleccion
							});	
							return false;
						 }else{
		 					 var elementoSeleccionado = $('#busquedaFactExp').rup_table("getSelectedRows");
		 					 var datosFila = $('#busquedaFactExp').rup_table("getRowData",elementoSeleccionado);
		 					 dniContactoSeleccionado = datosFila["contactoFacturacionExpediente.contacto.dni"];
		 					 idEntidadSeleccionada = datosFila["contactoFacturacionExpediente.entidadSolicitante.codigo"];
		 					 tipoEntidadSeleccionada = datosFila["contactoFacturacionExpediente.entidadSolicitante.tipo"];
		 					 irConfeccionarFactura();
						 }
					}
			}
		]
	});
}


/*
 * ****************************
 * CREACIÓN COMBOS Y FECHAS - FIN
 * ****************************
 */

jQuery(function($){
	
	fncTipoExpedienteCombo();
	fncFechasSolicitud();
	fncFechasEntregaReal();
	fncEntidadSolicitanteCombo($('#tipoEntidadSolicitante_T').val());
	$('input[name=tipoEntidadSolicitante]').change(function(){
		crearEntidadSolicitanteCombo($(this).val());
		crearContactoSolicitanteCombo($(this).val(),null);
		$('#entidadSolicitanteTipo').val($(this).val());
		$('#idEntidadSolicitante').val("");
	});
	crearContactoSolicitanteCombo(null,null);
	
	fncEntidadAFacturarCombo($('#tipoEntidadSolicitante_T').val());
	$('input[name=tipoEntidadFactExpediente]').change(function(){
		crearEntidadAFacturarCombo($(this).val());
		crearContactoAFacturarCombo($(this).val(),null);
		$('#entidadFactExpedienteTipo').val($(this).val());
		$('#idEntidadFactExpediente').val("");
	});
	crearContactoAFacturarCombo(null,null);
	
	fncToolbarFacturacionExpedientes();

	$('#busquedaFactExp_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#busquedaFactExp").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/emisiondefacturas/facturacionExpedientes",
		toolbar:{
			id: "busquedaFactExp_toolbar"
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
			"","","",
			$.rup.i18n.app.label.entidadFacturar,
			$.rup.i18n.app.label.contactoFacturar,
			$.rup.i18n.app.label.centroOrganico,
			$.rup.i18n.app.label.iva,
			$.rup.i18n.app.label.numExpFacturar
		],
		colModel: [
			{ 	name: "contactoFacturacionExpediente.entidadSolicitante.codigo", 
				hidden: true
			}
			,
			{ 	name: "contactoFacturacionExpediente.entidadSolicitante.tipo", 
				hidden: true
			}
			,
			{ 	name: "contactoFacturacionExpediente.contacto.dni", 
				hidden: true
			}
			,
			{ 	name: "contactoFacturacionExpediente.entidadSolicitante.descEu", 
			 	label: "label.anyoNumExpConcatenado",
				align: "center", 
				index: "DESCENTIDADEU",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject){
					if(cellvalue){
						return "<span class='txtTablaTarea'>" + cellvalue + "</span>"
					}else{
						return "-";
					}
				}
			}
			,
			{ 	name: "contactoFacturacionExpediente.contacto.nombreCompleto", 
				label: "label.contactoFacturar",
				align: "center", 
				index: "NOMBRE",
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject){
					if(cellvalue){
						if(!rowObject.contactoVinculado){
							return "<span class='txtTablaTarea'>" + cellvalue + " <i class='fa fa-exclamation-triangle' aria-hidden='true'></i></span>";
						}else{
							return "<span class='txtTablaTarea'>" + cellvalue + "</span>";
						}
					}else{
						return "-";
					}
					
				}
			}
			,
			{ 	name: "contactoFacturacionExpediente.contacto.centroOrganico.descAmp", 
				label: "label.centroOrganico",
				align: "center", 
				index: $.rup.i18n.app.label.BDCentroOrganico,
				width: "80", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject){
					if (cellvalue) {
						return "<span class='txtTablaTarea'>" + cellvalue + "</span>";
					}else{
						return '-';
					}
					
				}
			}
			,
			{ 	name:"contactoFacturacionExpediente.entidadSolicitante.iva", 
			 	label: "label.tipo",
				align: "center", 
				index: "IVA",
				width: "20", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue, options, rowObject){
					if("S".localeCompare(cellvalue)==0){
						return $.rup.i18n.app.comun.si;
					}
					return $.rup.i18n.app.comun.no;
				}
			}			
			,
			{ 	name:"expedientesCount", 
				label: "label.tipo",
				align: "center", 
				index: "COUNTEXPEDIENTES",
				width: "70", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}			
        ],
        model:"ConsultaFacturacionExpediente",
        usePlugins:[
        	 "toolbar",
       		 "filter",
       		 "responsive"
         	],
        primaryKey: ["id058"],
		sortname: "DESCENTIDADEU,NOMBRE",
		sortorder: "asc,asc",
		loadOnStartUp: false,
		filter:{
			clearSearchFormMode:"reset",
			beforeFilter: function(){
				$("#busquedaFactExp").rup_table("hideFilterForm");
				eliminarMensajesValidacion();
			},
			validate: {
				feedback: $('#busquedaFactExp_feedback'),
				liveCheckingErrors: false,				
				block:false,
				delay: 3000,
				gotoTop: true, 
				rules:{
					"fechaDesdeSolicitud":{date:true},
					"fechaHastaSolicitud":{date:true, fechaHastaMayor: "fechaDesdeSolicitud"},
					"fechaDesdeEntregaReal":{date:true},
					"fechaHastaEntregaReal":{date:true, fechaHastaMayor: "fechaDesdeEntregaReal"}
				},
				showFieldErrorAsDefault: false,
				showErrorsInFeedback: true,
				showFieldErrorsInFeedback: false
			}
		},
		loadComplete: function(data){ 
			 tipoExpediente = $("#idTipoExpediente_filter_table").rup_combo("getRupValue");
		},
		title: false
	});
	
	
	$("#busquedaFactExp_filter_cleanLinkModificado").click(function(){
		$("#busquedaFactExp").rup_table("cleanFilterForm");
		$("#idTipoExpediente_filter_table").val(tipoExpTradRev);
		$("#idTipoExpediente_filter_table").rup_combo("setRupValue", tipoExpTradRev);
		$("#tipoEntidadSolicitante_T").click();
		$("#tipoEntidadFactExpediente_T").click();
		$("#busquedaFactExp").rup_table("filter");
	});
	$("#busquedaFactExp").rup_table("filter");
	
});