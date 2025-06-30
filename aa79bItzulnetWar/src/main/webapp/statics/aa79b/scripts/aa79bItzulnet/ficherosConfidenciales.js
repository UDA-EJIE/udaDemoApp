var capaFichConfiGen = '';
var entidadCheckeadaFicheros = "-1";
var entidadFicheros = "-1";
var codigoFicheros = "-1";
var autocompleteFicherosCreado = false;

/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function irAListadoDocumentos(){
	bloquearPantalla();
    $.rup_ajax({
            url: '/aa79bItzulnetWar/servicios/trataFicherosConfi/ficherosConfidenciales/detalleFichConfidenciales/documentos/'+anyoExpediente+'/'+idExpediente
            ,success:function(data, textStatus, jqXHR){
            	capaFichConfiGen = $('#divFicherosConfidencialesGeneral').detach();
                $("#divFicherosConfidencialesCapa").html(data);
                desbloquearPantalla();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown){
            	alert('Error recuperando datos del paso');
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

function crearComboTipoExpediente(id){
	$('#'+id).rup_combo({
		loadFromSelect: true
		,width: "100%"
		,orderedByValue: true
		,rowStriping: true
		,open: function(){
			jQuery('#'+id+'-menu').width(jQuery('#'+id+'-button').innerWidth());
		}
	});
}

function crearEstadoExpCombo(){
	$('#idEstadoExp_filter_table').rup_combo({
		loadFromSelect: true,
		width: "100%",
		ordered: true,
		rowStriping: true,
		open : function() {
			jQuery('#idEstadoExp_filter_table-menu').width(jQuery('#idEstadoExp_filter_table-button').innerWidth());
		}
	});
}

function showDetalleFicheros(anyo,numeroExp){
	idExpediente = numeroExp;
	anyoExpediente = anyo;
	
	irAListadoDocumentos();
}

function crearComboEntidadSolicitanteFicheros(valEntidad){
	codigoFicheros = "-1";
	$('#idEntidadSolicitante_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/entidad/expConfidenciales/"+valEntidad,
		sourceParam : {
			value: "codigoCompleto",
			label : $.rup.lang === 'es' ? "descEs"
					: "descEu"
		},
		blank:"",
		width: "100%",
		getText: false,
		rowStriping: true,
		onLoadSuccess: function(){
			//si no carga ningun valor, destruir el desplegable del autocomplete si tiene algun valor
			if($('#idEntidadSolicitante_filter_table').rup_combo("isDisabled")){
				if($('#contactoGestor_filter_table_menu').length){
					$('#contactoGestor_filter_table_menu').remove();
					$('#contactoGestor_filter_table_label').val("");
				}
			}
		},
		open : function() {
			jQuery('#idEntidadSolicitante_filter_table-menu').width(jQuery('#idEntidadSolicitante_filter_table-button').innerWidth());
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_filter_table').innerWidth());
			$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
		}
	});
}

function crearAutocompleteGestorFicheros(){
	$('#contactoGestor_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/solicitante/findGestoresExpConfidenciales/"+entidadFicheros+"/"+codigoFicheros,
		sourceParam:{
			value : "dni",
			label : "nombreCompleto" 
		},
		getText: false,
		width: "100%",
		open : function() {
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("max-width", $('#contactoGestor_filter_table').width());
		}
	});
}

function anyadirEventoChangeAComboSolicitanteFicheros(){
	jQuery('select[id=idEntidadSolicitante_filter_table]').change(function(){
 		if(autocompleteFicherosCreado){
 			 var codigoCompleto =  $('#idEntidadSolicitante_filter_table').rup_combo("getRupValue");
          	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
          		codigoFicheros = -1;
          		entidadFicheros = entidadCheckeadaFicheros; 
          	 }else{
          		var datosEntidadSeleccionada = codigoCompleto.split("_");	
          		codigoFicheros = datosEntidadSeleccionada[1];
          		entidadFicheros = datosEntidadSeleccionada[0];
          	 }
          	 //destruimos y volvemos a crear el autocomplete
          	$('#contactoGestor_filter_table').rup_autocomplete("destroy");
          	$("#autocompleteContainer_contactoGestor_filter_table").remove();
          	var autocompleteGestor = $('<div id="autocompleteContainer_contactoGestor_filter_table" ><label for="contactoGestor_filter_table" class="control-label" data-i18n="label.gestor">'+$.rup.i18n.app.label.gestor+'</label><input id="contactoGestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni" /></div>');
          	autocompleteGestor.appendTo("#div_contactoGestor_filter_table");
          	crearAutocompleteGestorFicheros();
 		}
 		if(!autocompleteFicherosCreado){
 			autocompleteFicherosCreado=true;
 		}
 	});
}

function cambiarLabelTipoEntidadFicherosBE(tipoEntidad){
	if('B'.localeCompare(tipoEntidad)===0){
		$('#labelEntidadSolicitante_filter_table').text($.rup.i18n.app.label.entidad);
	}else if('E'.localeCompare(tipoEntidad)===0){
		$('#labelEntidadSolicitante_filter_table').text($.rup.i18n.app.label.departamento);
	}else if('L'.localeCompare(tipoEntidad)===0){
		$('#labelEntidadSolicitante_filter_table').text($.rup.i18n.app.label.empresa);
	}else{
		$('#labelEntidadSolicitante_filter_table').text($.rup.i18n.app.label.todasTipoEntidad);
	}
}


/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	$('#busquedaFicherosConfi_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	 $("#busquedaFicherosConfi_filter_form").rup_validate({
			feedback: $('#busquedaFicherosConfi_feedback'),
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
	
	crearComboTipoExpediente('idTipoExpediente_filter_table');
	crearEstadoExpCombo();
	crearComboEntidadSolicitanteFicheros("");
	anyadirEventoChangeAComboSolicitanteFicheros();
	crearAutocompleteGestorFicheros();
	// Filtro - Cargar combo de entidad en funcion del tipo de entidad seleccionado
	$('input[name=tipoEntidad]:first').click();
	$('input[name=tipoEntidad]').change(function(){
		entidadCheckeadaFicheros = $(this).val();
 		if("".localeCompare(entidadCheckeadaFicheros)==0){
 			entidadCheckeadaFicheros = "-1";
		}
 		$('#idEntidadSolicitante_filter_table').rup_combo("setRupValue","");
 		entidadFicheros = $(this).val();
		if("".localeCompare(entidadFicheros)==0){
			entidadFicheros = "-1";
		}
 		//valor del campo para filtrar por entidad
		$('#gestorExpedienteEntidadTipo').val($(this).val());
		crearComboEntidadSolicitanteFicheros($(this).val());
		anyadirEventoChangeAComboSolicitanteFicheros();
		cambiarLabelTipoEntidadFicherosBE($(this).val());
	});
	
	$("#busquedaFicherosConfi").rup_table({
		url: "/aa79bItzulnetWar/servicios/trataFicherosConfi/ficherosConfidenciales/busquedaexpconf",
		toolbar:{
			id: "busquedaFicherosConfi_toolbar"
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
			$.rup.i18n.app.label.gestorExpediente,
			$.rup.i18n.app.label.fechaYHoraEntrega,
			$.rup.i18n.app.label.estado
		],
		colModel: [
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
				align: "center", 
				index: "ANYONUMEXPCONCATENADO",
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return '<b><a href="#" onclick="showDetalleFicheros(' + rowObject.anyo + ',' + rowObject.numExp + ')">' + cellvalue + '</a></b>';
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
				width: "270", 
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
				align: "left",
				index: $.rup.lang === 'es' ? "GESTORCOLORDERES"
						: "GESTORCOLORDEREU",
				width: "300", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "expedienteTradRev.fechaHoraFinalSolic", 
			 	label: "label.fechaYHoraEntrega",
				align: "center", 
				index: "FECHAFINALIZO",
				width: "140", 
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
				width: "90", 
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
       		 "filter",
       		 "report"
         	],
        primaryKey: ["anyo", "numExp"],
		loadOnStartUp: false,
		sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "asc",
		multiplePkToken:"-",
		filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$("#busquedaFicherosConfi").rup_table("hideFilterForm");
			}
		},
		report : {
			buttons : [{
				id : "report_busquedaFicherosConfi",
				i18nCaption : $.rup.i18n.app.comun.exportar,right : true,
				buttons : [
					{i18nCaption : $.rup.i18n.app.tabla.excel,
						divId : "report_busquedaFicherosConfi",
						css : "fa fa-file-excel-o",
						url : "/aa79bItzulnetWar/servicios/trataFicherosConfi/ficherosConfidenciales/xlsxReport",
						click : function(event) {
							if(!$("#busquedaFicherosConfi_filter_form").valid()){
					 			event.preventDefault();
					 			event.stopImmediatePropagation();
					 		}
						}
					},
					{ i18nCaption:$.rup.i18nParse($.rup.i18n.app, "tabla.pdf"),  
						divId : "report_busquedaFicherosConfi",
						css : "fa fa-file-pdf-o",
						url: "/aa79bItzulnetWar/servicios/trataFicherosConfi/ficherosConfidenciales/pdfReport", 
						click : function(event) {
							if(!$("#busquedaFicherosConfi_filter_form").valid()){
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
	
	$("#busquedaFicherosConfi_filter_cleanLinkModificado").click(function(){
		$("#busquedaFicherosConfi").rup_table("cleanFilterForm");
		$("#busquedaFicherosConfi").rup_table("filter");
	});
	
});