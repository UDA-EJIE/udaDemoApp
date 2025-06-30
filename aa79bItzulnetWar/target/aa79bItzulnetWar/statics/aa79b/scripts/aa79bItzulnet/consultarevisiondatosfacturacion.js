
var filterFormObjectRevDatFact;
var expedientesSeleccionados = [];
var listIdObjects = [];
var anyoActualFilter = true;

function irAReasignarTecnicoPestBusqueda(){
	bloquearPantalla();
	eliminarDialogs();
	volcarListaIdsAExpSeleccionados("-");
	$.rup_ajax({
	   	 url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/entidadcontactofacturacion/maint"
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#busquedaGeneral_div').detach();
	   		 $("#divBusquedaGeneral").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

function actualizarEntidadyContactoFact(){
	var expedientesIds = listIdObjects;
	jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/comprobarEstadoPresupuestos/"+busqGen.estadoPresupuesto.aceptado,
		dataType: "json",
		contentType: 'application/json', 
        data: $.toJSON(expedientesIds),
		cache: false,
		success: function (data) {
           if(data){
        	   //mensaje de error porque hay al menos un expediente con estado presupuesto a onartuta
        	   desbloquearPantalla();
        	  $.rup_messages("msgAlert", {
					title: $.rup.i18nParse($.rup.i18n.app,"boton.actualizarEntidadyContactoFact"),
					message: $.rup.i18n.app.mensajes.deseleccionarExpedientesConEstPresupAceptado
				});	
				return false;
           }else{
//		        	  no hay ningun expediente con estado presupuesto aceptado
        	   desbloquearPantalla();
        	   irAReasignarTecnicoPestBusqueda();
           }
	     },
	     error: function (){
	    	desbloquearPantalla();
	    	mostrarMensajeFeedback("busquedaGeneral_feedback",$.rup.i18n.app.mensajes.errorComporobExpedientesConEstPresupAceptado,"error");
	     }
	});
}

function irDetalleExpedientes(elAnyo, elNumExp){
	anyoExpediente = elAnyo;
	idExpediente = elNumExp;
	bloquearPantalla();
	eliminarDialogs();
	$.rup_ajax({
	   	 url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detallecontactofacturacion/maint" 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#busquedaGeneral_div').detach();
	   		$("#divBusquedaGeneral").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}


function fncTipoExpedienteCombo(){
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
	});
}
function fncPublicarBopvCombo(){
	$("#indPublicarBopv").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#indPublicarBopv-menu').width(jQuery('#indPublicarBopv-button').innerWidth());
		}
	});
}
function fncPublicadoBoeCombo(){
	$("#indPublicadoBoe").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#indPublicadoBoe-menu').width(jQuery('#indPublicadoBoe-button').innerWidth());
			}
	});
}
function fncEntidadFacturableCombo(){
	$("#indEntidadFacturable").rup_combo({
		loadFromSelect: true
		,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#indEntidadFacturable-menu').width(jQuery('#indEntidadFacturable-button').innerWidth());
			}
	});
}
function fncExpFacturableCombo(){
	$("#expFacturable_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		}
		,disabled:true
	});
}
function crearComboIdiomaDestinoConTodos(id){
	$('#'+id).rup_combo({
		source: "/aa79bItzulnetWar/idiomadestino",
		sourceParam: {
			label: $.rup.lang === 'es' ? "descIdiomaEs"
					: "descIdiomaEu",
			value: "idIdioma"
		}
		,blank:""
		,width: "170"
		,ordered: false	
		,rowStriping: true
		,open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
	});
}

/* CONTACTO A FACTURAR */

function creaComboContactoFFiltro(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
	if ( $('#dniContactoEntidadSolicitanteF-button').length   ){
		$('#dniContactoEntidadSolicitanteF').rup_combo("clear");
	}
	
	$('#dniContactoEntidadSolicitanteF').rup_combo({
		source : "/aa79bItzulnetWar/solicitante/findRevisionContactoFacturarConExpedientes/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
		sourceParam : {
			value: "dni",
			label : "nombreCompleto" 
		},
	    rowStriping: true,
	    blank: "",
		open: function(){
			var id = $(this).attr("id");
	        $("#"+id+"-menu").width($("#"+id+"-button").width());
	    },
	    onLoadSuccess: function(){ 
	    	if (typeof(valorSeleccionar) !== "undefined"){
	    		$("#dniContactoEntidadSolicitanteF").rup_combo("select", valorSeleccionar+'');
	    	}
	    }
	});	
}	
/*
 * ****************************
 * FORMATEADORES - INICIO
 * ****************************
 */

function mostrarCeldaDatosFacturacionConcatenados(cellvalue, rowObject){
	var celda = '';
	if (cellvalue != undefined){
		//celda = '<p class="dest-facturacion">';
		celda = cellvalue.replace(/###/g,'');
		//celda = celda.concat(cellvalue +'</p>');
	}
	return celda;
}

/*
 * ****************************
 * FORMATEADORES - FIN
 * ****************************
 */


jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	
	$("#tituloConsultaRevisionDatosFacuracion").text($.rup.i18n.app.label.revisionDatosFact);
	
	$('#busquedaGeneral_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	fncTipoExpedienteCombo();
	fncPublicarBopvCombo();
	fncPublicadoBoeCombo();
	fncExpFacturableCombo();
	crearComboIdiomaDestinoConTodos('idiomaDestino');
	fncEntidadFacturableCombo();
	creaComboContactoFFiltro('-1','-1');
	
	
	/* ENTIDAD GESTORA */
	$('#idEntidadSolicitante_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/entidad/entGestorasExpFasePdteRevisarFact/",
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
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_filter_table').innerWidth());
			$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
		}
		
	});
	
	//	Filtro - Cargar combo de fase entidad gestora en funcion del tipo de entidad seleccionado
	jQuery('input[name=tipoEntidad]:first').click();
	jQuery('input[name=tipoEntidad]').change(function(){
			$('#idEntidadSolicitante_filter_table').rup_combo({
				source : "/aa79bItzulnetWar/entidad/entGestorasExpFasePdteRevisarFact/"+$(this).val(),
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
					jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_filter_table').innerWidth());
					$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
					$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
				},
			    onLoadSuccess: function(){ 
			    		$("#idEntidadSolicitante_filter_table").rup_combo("select", -1);
			    }
			});
			
			jQuery('#gestorExpedienteEntidadTipo').val($(this).val());
		});
	
	
	/* FIN ENTIDAD GESTORA */
	
	
	/* ENTIDAD A LA QUE SE FACTURA */
	
	$('#idEntidadSolicitanteF').rup_combo({
		source : "/aa79bItzulnetWar/entidad/entAFacturarExpFasePdteRevisarFact/",
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
				source : "/aa79bItzulnetWar/entidad/entAFacturarExpFasePdteRevisarFact/"+$(this).val(),
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
	
	/* FIN entidad a la q se factura...*/
	
	
	
	
	
	$("#busquedaGeneral_filter_form").rup_validate({
		feedback: $('#busquedaGeneral_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"expedienteTradRev.fechaEntregaIzoDesde": {date: true},
			"expedienteTradRev.fechaEntregaIzoHasta": {date: true},
			"fechaAltaDesde": {date: true},
			"fechaAltaHasta": {date: true}
			
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	$("#busquedaGeneral_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.actualizarEntidadyContactoFact
				,css:"fa fa-ticket"
				,id:"actualizarEntidadyContactoFact"
				,click: function(event){
					event.preventDefault();
		 			event.stopImmediatePropagation();
		 			expedientesSeleccionados = [];
		 			listIdObjects = [];
					bloquearPantalla();
					obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectRevDatFact, "actualizarEntidadyContactoFact");
				}
			}			
		]
	});
	
	fnFechaDesdeHasta("fechaEntregaDesde_filter", "fechaEntregaHasta_filter");
	fnFechaDesdeHasta("fechaAltaDesde", "fechaAltaHasta");
	
	/* checks */
	
	$("#selectTodos").on ('click',function (event) {
	    if($('#selectTodos').is(":checked")){
	        $("#busquedaGeneral_filter_fieldset input[type=checkbox]").prop("checked",true);
	    }else{
	        $("#busquedaGeneral_filter_fieldset input[type=checkbox]").prop("checked",false);
	    }
	});
	
	$("#busquedaGeneral_filter_fieldset input[type=checkbox]").on ('click',function (event) {
	    if(!$(this).is(":checked")){
	        $("#selectTodos").prop("checked",false);
	    }
	});
	
	$('#busquedaGeneral_filter_cleanLinkModificado').on('click',function(event){
//		tipoExp =$("#idTipoExpediente_filter_table").rup_combo("getRupValue");
		$("#busquedaGeneral_filter_form").rup_form("clearForm");
//		$("#idTipoExpediente_filter_table").rup_combo("select",tipoExp);
		anyoActualFilter = true;
		anyoActual("anyo_filter_table");
		expedientesSeleccionados = [];
		$("#busquedaGeneral").rup_table("resetSelection");
		jQuery('input[name=tipoEntidad]:first').click();
		jQuery('input[name=tipoEntidadF]:first').click();
		$("#busquedaGeneral").rup_table("filter");
	});
	
	$("#busquedaGeneral").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/busquedarevision",
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
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.gestorExpediente,
			$.rup.i18n.app.label.numTotalPalabras,
			$.rup.i18n.app.label.bopv,
			$.rup.i18n.app.boton.contactoFacturacionExpediente
		],
		colModel: [
			{ 	name: "idTipoExpediente",width: "1", hidden:true},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
				align: "center", 
				index: "ANYONUMEXPCONCATENADO",
				width: "95", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					tipoExp = "'"+rowObject.idTipoExpediente+"'";
					anyoExpediente = rowObject.anyo;
					idExpediente = rowObject.numExp;
					return '<b style="display: block;"><a href="#" onclick="irDetalleExpedientes('+anyoExpediente+','+idExpediente+')">' + cellvalue + '</a></b>';
	
				}
			}
			,
			{ 	name:$.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipo",
				align: "center", 
				index: "IDTIPOEXPEDIENTE",
				width: "50", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			}
			,
			{ 	name: "titulo", 
			 	label: "label.titulo",
				align: "left",
				index: "TITULO",
				width: "200", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
			,
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
			}
			
			,
			{ 	name: "expedienteTradRev.numTotalPalConTramosPerfectMatch", 
			 	label: "label.numPalabraIZO",
				align: "right", 
				index: "NUMPALCOLORDER",
				width: "175", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{ 	name:$.rup.lang === 'es' ? "expedienteTradRev.publicarBopvDescEs" : "expedienteTradRev.publicarBopvDescEu", 
			 	label: "label.bopv",
				align: "center", 
				index: "INDPUBLICARBOPV",
				width: "60", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			,
			{   name:$.rup.lang === 'es' ? "entidadYContactoFacturacionConcatenadosEs" : "entidadYContactoFacturacionConcatenadosEu", 
			 	label: "titulo.entidadContactoFacturacion",
				align: "left", 
				index: "DATOSFACTURACIONCONCATENADOSEU",
				width: "450", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true/*,
				formatter: function (cellvalue, options, rowObject){
						return mostrarCeldaDatosFacturacionConcatenados(cellvalue, rowObject);
				}*/
			}
			
        ],
        model:"ExpedienteFacturacion",
        usePlugins:[
        	 "toolbar",
       		 "filter",
       		 "report",
       		 "multiselection"
         	],
        primaryKey: ["anyo", "numExp"],
		loadOnStartUp: false,
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
				$("#busquedaGeneral").rup_table("hideFilterForm");
				$('[id^="fecha"][id$="error"]').remove();
				$("#busquedaGeneral").rup_table("resetSelection");
				expedientesSeleccionados = [];
				if(!$("#busquedaGeneral_filter_form").valid()){
					return false;
				}
			}
		},
		report : {
			buttons : [{
				id : "report_busquedaGeneral",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_busquedaGeneral",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/xlsxReport",
						click : function(event) {
							
						}
					},
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_busquedaGeneral", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/pdfReport", 
						click : function(event){
				 			
						}
					}  
				]}
			]
		}
	    ,title: false,
		loadComplete: function(data){ 
			anyoActual("anyo_filter_table");
			filterFormObjectRevDatFact = obtenerFiltrosTabla('busquedaGeneral');
			if(filterFormObjectRevDatFact.tipoEntidadF){
				delete filterFormObjectRevDatFact.tipoEntidadF;
			}
		}
	});
	
});
