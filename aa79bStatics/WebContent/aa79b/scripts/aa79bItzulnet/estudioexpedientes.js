var dataOrigen;
var anyo;
var numExp;
var origen;
var buscadorRecCreado = false;
var detalleSub = false;
var subrayado = false;
var capaReceptores;
var camposSeleccionados = ''; 
var docusSeleccionados = '';
var initFormRechazarExp = '';
var newDate;
var listaDocusRevisar = [];
var listaDocusTodos = [];
var bopvChecked = false;
var confidencialChecked = false;
var indNuevosDocus = 'N';
var isGestorVip = 'N';
var expedienteConfidencial= 'N';
var entidadCheckeadaEstudio = "-1";
var entidadEstudio = "-1";
var codigoEstudio = "-1";
var autocompleteEstudioCreado = false;
var filterFormObjectEstudExp;
var listIdObjects = [];
var expedientesSeleccionados = [];

function volverAEstudioExpediente(){
	//quitamos el sticky de la cabecera del expediente
	$('#subcabecera').removeClass('substicky');
	$("#bitacora_expediente").removeClass('subcabeceraspace');
	
	ocultarDiv('detalleExpediente');
	$("#tabsExpediente").rup_tabs("destroy");
	$("#tabsExpediente").remove();
	$("#detalleExpediente_div").append("<div id='tabsExpediente'></div>");
	mostrarCapaExpedienteMYO('divEstudioExpedientes');
	datosFormulario = '';
	datosFormularioDoc = '';
	omitirComprobacionFormularios = true;
	visualizarObservaciones = false;
	$("#estudioExpedientes").trigger("reloadGrid"); 
}

function guardarGestoresEstudioExp(){
	expedientesSeleccionados = listIdObjects;
	var dniTecnico = $('#reasignarTecnicoAsignado').val();
	if(dniTecnico!=null && "".valueOf()!== dniTecnico.valueOf() && "noDni".valueOf()!== dniTecnico.valueOf()){
		var jsonObject = {
    			listaExpediente: []
    	};
		for(var k=0;k<expedientesSeleccionados.length;k++) {    

		    var item = expedientesSeleccionados[k];
		    
		    var jsonObjTecnico = { 
    		        "dni" : dniTecnico
    		    };

		    jsonObject.listaExpediente.push({ 
		        "anyo" : item.anyo,
		        "numExp"  : item.numExp,
		        "tecnico" : jsonObjTecnico
		    });
		}
    	jQuery.ajax({
    		type: "POST",
    		url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/asignarTecnicoAExpedientes",
    		dataType: "json",
    		contentType: 'application/json', 
            data: $.toJSON(jsonObject),
    		cache: false,
    		success: function (data) {
	           if (data != null && data > 0) {
	        	   for(var l=0;l<expedientesSeleccionados.length;l++){
	        		  
	        		   var exp = expedientesSeleccionados[l];
	        		   $('#estudioExpedientes').rup_table("setCell",exp.anyo+","+exp.numExp,"tecnico.nombreCompleto",$('#reasignarTecnicoAsignado').rup_combo("value"));
	        	   }
	        	   
	               volverAEstudioExpediente();
	           		$('#estudioExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tecnicoReasignado, "ok");
	           }
	           desbloquearPantalla();
    	     },
    	     error: function (){
    	    	 $('#estudioExpedientesReasignarTecnico_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
    	    	 desbloquearPantalla();
    	     }
    	});
    	
    	
	}else{
		$('#estudioExpedientesReasignarTecnico_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.seleccioneTecnicoAReasignar, "error");
		desbloquearPantalla();
	}
}

function irADetalleDesdeConsulta(){
	//vamos al detalle directamente
	showDetalleExpediente(anyo,numExp,'C',dataOrigen);
	//eliminamos clases de elementos contenedores para ajuste de pantalla
	eliminarClaseDeElemento("detalleExpediente","container-fluid");
	eliminarClaseDeElemento("rechazarAjusteConsExp","container-fluid");
	eliminarClaseDeElemento("requerirSubExpAjusteConsExp","container-fluid");
	eliminarClaseDeElemento("divExpedientesRelacionados","container-fluid");
	eliminarClaseDeElemento("divReceptoresAutorizados","container-fluid");
	eliminarClaseDeElemento("divContactoFacturacion","container-fluid");
	eliminarClaseDeElemento("divCategorizacionExpediente","container-fluid");
	$('.aa79b-content').addClass('in');
	desbloquearPantalla();
}

function ocultarMenuDesplegableBotonOtros(){
	$("[id='detalleExpediente_toolbar##otrosToolbarExpediente-mbutton-container']").hide();
}

function crearArbolDocumentos(data){
	jQuery("#listaDocuAsociados").rup_tree("destroy");
	$("#listaDocuAsociados > ul").empty();
	
	//Carga del árbol
	for(var i = 0 ; i < data.length; i++){
		
		listaDocusTodos.push(data[i].idDoc);
		if(data[i].claseDocumento == claseDocuEnum.traduccion || data[i].claseDocumento == claseDocuEnum.revision){
			
			listaDocusRevisar.push(data[i].idDoc);
		}
		
		$("#listaDocuAsociados > ul").append("<li id='"+data[i].idDoc+"' value = '"+data[i].idDoc+"'>");

		if(data[i].tipoDocumentoDesc != null){
			
			$("#listaDocuAsociados > ul > li").last().append("<a href='#' value ='"+data[i].idDoc+"'>"+data[i].titulo + " ("+ data[i].claseDocumentoDesc+" - "+data[i].tipoDocumentoDesc+")</a>");
		} else {
			
			$("#listaDocuAsociados > ul > li").last().append("<a href='#' value ='"+data[i].idDoc+"'>"+data[i].titulo+ " ("+ data[i].claseDocumentoDesc+")</a>");
		}
		
		$("#listaDocuAsociados > ul").append("</li>");
	}
	
	$("#listaDocuAsociados").rup_tree({
		
		"select" : {
		      "select_limit" : "1"
		   }
		,"checkbox" : {
		      "enable" : true
		   }
	});
	
	setTimeout(function() {
		
		$("#listaDocuAsociados > ul > li > ins.jstree-icon").remove();
		$("#listaDocuAsociados > ul > li > a > ins.jstree-icon").remove();
	}, 10);
}

function requiereRevisionDocumentos(data,bopvChecked){
	for(var i=0;i<data.length;i++){
		
		if (bopvChecked) {
			
			$("#listaDocuAsociados li#"+data[i]+"").removeClass("jstree-unchecked");
			$("#listaDocuAsociados li#"+data[i]+"").addClass("jstree-checked");
			$("#listaDocuAsociados li#"+data[i]+"").css("pointer-events","none");
			$("#listaDocuAsociados li#"+data[i]+"").addClass("jstree-disabled");
		} else {
			
			$("#listaDocuAsociados li#"+data[i]+"").css("pointer-events","");
			$("#listaDocuAsociados li#"+data[i]+"").removeClass("jstree-disabled");
		}
	}
}

function requiereAdjuntarDocus(data,confidencialChecked){
	
	for(var i=0;i<data.length;i++){
		
		if (confidencialChecked) {
			
			$("#listaDocuAsociados li#"+data[i]+"").addClass("jstree-unchecked");
			$("#listaDocuAsociados li#"+data[i]+"").removeClass("jstree-checked");
			$("#listaDocuAsociados li#"+data[i]+"").css("pointer-events","none");
			$("#listaDocuAsociados li#"+data[i]+"").addClass("jstree-disabled");
		} else {
			
			$("#listaDocuAsociados li#"+data[i]+"").css("pointer-events","");
			$("#listaDocuAsociados li#"+data[i]+"").removeClass("jstree-disabled");
		}
	}
}

function campoBopvSelect(data){

	if (data === 11 || data === 22) {
		
		if(confidencialChecked){
			
			confidencialChecked = false;
			requiereAdjuntarDocus(listaDocusTodos, confidencialChecked);
			
			if ($("#treeCamposSub li[value=2]").hasClass("jstree-checked")) {
				
				bopvChecked = true;
				requiereRevisionDocumentos(listaDocusRevisar, bopvChecked);
			} else {
				
				bopvChecked = false;
				requiereRevisionDocumentos(listaDocusRevisar, bopvChecked);
			}
		} else {
			
			confidencialChecked = true;
			requiereAdjuntarDocus(listaDocusTodos, confidencialChecked);
		}
	}
	
	if (data === 2 && !confidencialChecked) {
		
		if (bopvChecked) {
			
			bopvChecked = false;
			requiereRevisionDocumentos(listaDocusRevisar, bopvChecked);
		} else {
			
			bopvChecked = true;
			requiereRevisionDocumentos(listaDocusRevisar, bopvChecked);
		}
	}
}

function subrayar(){
	var Expediente = new Object();
    Expediente.anyo=anyoExpediente;
    Expediente.numExp=idExpediente;
    
	$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/comprobarCamposSeleccionados'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: jQuery.toJSON({
		   		  "expediente":Expediente
		   	 })		
		     ,async: false
		   	 ,success:function(data){
		   		var campoSelect;
		   		
	   		   	for(var i=0;i<data.length;i++){
	   		   		campoSelect = data[i].bitacoraExpediente.subsanacionExp.camposSelecSub.camposSubsanacion.nameaa79078;
	   		   		$("label[for="+ campoSelect +"]:first").addClass( "reqSubsanacion" );
	   		   		camposSeleccionados += campoSelect+';';
				}
		   	 }
   	});
	
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/comprobarDocumentosSeleccionados'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: jQuery.toJSON({
	   		  "expediente":Expediente
	   	 })		
	     ,async: false
	   	 ,success:function(data){
	   		var docuSelect;
	   		
		   	for(var i=0;i<data.length;i++){
		   		if(data[i].bitacoraExpediente.subsanacionExp.docuSelecSub != null){
			   		docuSelect = data[i].bitacoraExpediente.subsanacionExp.docuSelecSub.documentosExpediente.idDoc;
			   		$("div[id=filaDoc"+ docuSelect +"]").children().addClass( "reqSubsanacion" );
			   		docusSeleccionados += docuSelect+';';
		   		}
			}
	   	 }
	});
	
}

function quitarSubrayado(){
	
	var docu = docusSeleccionados.split(";");
	for(var i=0;i < docu.length;i++){ 
		if (docu[i] != "") {
			$("div[id=filaDoc"+ docu[i] +"]").removeClass( "reqSubsanacion" );
			$("div[id=filaDoc"+ docu[i] +"]").children().removeClass( "reqSubsanacion" );
		}
	}
	
	var campos = camposSeleccionados.split(";");
	for(var f=0;f < campos.length;f++){ 
		if (campos[f] != "") {
			$("label[for="+ campos[f] +"]").removeClass( "reqSubsanacion" );
		}
	}
	
}

function comprobarEstadoSubsanacion(anyoExpediente, idExpediente){
	var Expediente = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente
    };
	 $.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/comprobarEstadoSubsanacion'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: jQuery.toJSON({"expediente":Expediente})		
	     ,async: false
	   	 ,success:function(data){
	   		 bitacoraUpdate(false);
	   		 if(parseInt(fase) == data.faseExp.id && parseInt(estado) == data.estadoExp.id && parseInt(estadoSub) == data.subsanacionExp.estado){
	   		 	detalleSub = true;
	   			mostrarDiv('detalleSubsanacion');
	   			
	   			jQuery('#detalleSubsanacion').find('#idSub')
				.val(data.subsanacionExp.id);
	   			
		     	jQuery('#detFechaLimite').rup_date({		
		     		labelMaskId : "fecha-mask",
		     		minDate: new Date(),
		     		showButtonPanel : true,
		     		showOtherMonths : true
		     	});	
		     	
	   			jQuery('#detalleSubsanacion').find('#detFechaLimite')
				.val(data.subsanacionExp.fechaLimite);
	   			
	   			jQuery('#detalleSubsanacion').find('#detHoraLimite')
	   			.val(data.subsanacionExp.horaLimite);
	   			//si venimos de consulta de expedientes hay que inicializar el switch
	   			if(jQuery('#detalleSubsanacion').find('#detIndSubsanado').length && jQuery('#detalleSubsanacion').find('#detIndSubsanado').is(':disabled')
	   				&& !jQuery('#detalleSubsanacion').find('#detIndSubsanado').parent().parent().hasClass('has-switch')){
	   				$('#detIndSubsanado').attr('disabled', false);
	   				$('#detIndSubsanado').bootstrapSwitch()
	          		.bootstrapSwitch('setSizeClass', 'switch-small')
	          		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	          		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	   				$('#detIndSubsanado').attr('disabled', true);
	   				jQuery('#detalleSubsanacion').find('#detIndSubsanado').parent().parent().addClass('disabled');
	   			}
	   			var chkIndSubsanacion = jQuery('#detalleSubsanacion').find('#detIndSubsanado');
	   			chkIndSubsanacion
	   					.bootstrapSwitch('setState', 
	   							data.subsanacionExp.indSubsanado == chkIndSubsanacion.attr('value'));
	   			
	   			jQuery('#detalleSubsanacion').find('#detDetalleSubsanacion')
				.val(data.subsanacionExp.detalle);
	   			
	   			esSubsanado = data.subsanacionExp.indSubsanado;
	   			
	   			if(esTecnico !== 'C'){
	   				if(data.subsanacionExp.indSubsanado == "S") {
		   				subrayado = true;
		   				jQuery('#detalleSubsanacion').find('#detFechaLimite').rup_date("disable");
		   				jQuery('#detalleSubsanacion').find('#detHoraLimite').prop("disabled", true);
		   				jQuery('#detalleSubsanacion_link_cancel').show();
		   				
		   				//subrayar();
		   				
		   			} else if (data.subsanacionExp.indSubsanado == 'N') {
		   				jQuery('#detalleSubsanacion').find('#detFechaLimite').rup_date("enable"); 
		   				jQuery('#detalleSubsanacion').find('#detHoraLimite').prop("disabled", false);
		   				jQuery('#detalleSubsanacion_link_cancel').hide();
		   			}	   				
	   			}
	   			
	   			$("[id='detalleExpediente_toolbar##"+ "requerirSub"+"']").button("disable");
	   		 } else {
	   			ocultarDiv('detalleSubsanacion');
	   			$("[id='detalleExpediente_toolbar##"+ "requerirSub"+"']").button("enable");
	   			subrayado = false;
	   		 }
	   	 }
	 });
}



function showDetalleExpediente(anyo,numeroExp,origenExp,dataOrigen){
	bloquearPantalla();
	idExpediente = numeroExp;
	anyoExpediente = anyo;
	origen = origenExp;
	if (dataOrigen == origenEnum.origen.oficio){
		$("[id='detalleExpediente_toolbar##"+ "oficio"+"'] > span").text($.rup.i18n.app.boton.finAltaOficio );
	} else {
		$("[id='detalleExpediente_toolbar##"+ "oficio"+"'] > span").text($.rup.i18n.app.boton.finEstudio );
	}
	esTecnico = '';
	esSubsanado = '';
	
	//Actualizar bitácora + cabecera
	bitacoraUpdate(false);
	jQuery.ajaxSetup({cache: false });
	$("#tabsExpediente").rup_tabs({
		tabs : [
			{i18nCaption: "pestdatosg", url:"/aa79bItzulnetWar/datosgeneralesexpediente/datosexpedienteview?"+$.now()},
			{i18nCaption: "pestdocumentos", url:"/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/listado/"+anyoExpediente+"/"+idExpediente+"/"+origen+"?"+$.now()}
		]
	,cache: false
	,select: function(e){
		eliminarDialogs("[aria-describedby='addRegistro_dialog'],[aria-describedby='listaComunicaciones_dialog'],[aria-describedby='nuevaComunicacion_dialog'],[aria-describedby='verComunicacion_dialog'],[aria-describedby='buscadorPersonasReceptores'],[aria-describedby='buscadorExpedientesRelacionados'],[aria-describedby^='rup_msgDIV_'],[aria-describedby='contactofacturacionexp_detail_div'],[aria-describedby='delmodcontactofacturacionexp']");
		
		if (!omitirComprobacionFormularios && comprobarFormularios && !comprobarFormulariosPestanas()){
			e.preventDefault();
            e.stopImmediatePropagation();
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					
					if ($('#capaPestanaCompletaAlta').length > 0){
						datosFormulario = $("#datosgeneralesexpedienteform").serialize();
						$("#tabsExpediente").rup_tabs("selectTab",{
							idTab: "tabsExpediente",
							position: 1
						});
						
					} else {
						datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
						$("#tabsExpediente").rup_tabs("selectTab",{
							idTab: "tabsExpediente",
							position: 0
						});
					}
				}
			});
		} else {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			omitirComprobacionFormularios = false;
		}
	}
	,fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
		  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		 ] 
	});
	
	//Recarga de pestaña con año y número de expediente
//	$("#tabsExpediente").rup_tabs("loadTab",{
//		idTab: "tabsExpediente",
//		position: 0
//	});
//	
//	$("#tabsExpediente").rup_tabs("selectTab",{
//		idTab: "tabsExpediente",
//		position: 0
//	});
	//Ponemos el enlace a la pestaña de el documento concreto 
	$("[id='#pestdocumentos']").attr('href', "/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/listado/"+anyoExpediente+"/"+idExpediente+"/"+origen)	
	
	mostrarCapaExpedienteMYO('detalleExpediente_div,detalleExpediente');

 	$('#anyoreceptoresAutorizados_filter_table').val(anyoExpediente);
 	$('#numExpreceptoresAutorizados_filter_table').val(idExpediente);
 	$('#anyo_categorizacion_expediente').val(anyoExpediente);
 	$('#numExp_categorizacion_expediente').val(idExpediente);
	comprobarEstadoSubsanacion(anyoExpediente, idExpediente);
//	$("[id='detalleExpediente_toolbar##"+"reports"+"']").classList.add('detailReportLeft');
//	$("[id='detalleExpediente_toolbar##"+"reports"+"']").classList.add('fa-cog');
	if(dataOrigen != origenEnum.origen.solicitante) {
		$("#detalleExpediente_toolbar-rightButtons").hide();
	} else {
		$("#detalleExpediente_toolbar-rightButtons").show();
	}
}

function crearComboEntidadGestoraEstudio(valEntidad){
	codigoEstudio = "-1";
	$('#idEntidadSolicitante_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/entidad/expEstudio/"+valEntidad,
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
				if($('#contactoGestorEstudio_filter_table_menu').length){
					$('#contactoGestorEstudio_filter_table_menu').remove();
					$('#contactoGestorEstudio_filter_table_label').val("");
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
	$('#labelEntidadSolicitante_filter_table').text($.rup.i18n.app.label.todasTipoEntidad);
}

function anyadirEventoChangeAComboEntidadGestoraEstudio(){
	jQuery('select[id=idEntidadSolicitante_filter_table]').change(function(){
 		if(autocompleteEstudioCreado){
 			 var codigoCompleto =  $('#idEntidadSolicitante_filter_table').rup_combo("getRupValue");
          	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
          		codigoEstudio = -1;
          		entidadEstudio = entidadCheckeadaEstudio; 
          	 }else{
          		var datosEntidadSeleccionada = codigoCompleto.split("_");	
          		codigoEstudio = datosEntidadSeleccionada[1];
          		entidadEstudio = datosEntidadSeleccionada[0];
          	 }
          	 //destruimos y volvemos a crear el autocomplete
          	$('#contactoGestorEstudio_filter_table').rup_autocomplete("destroy");
          	$("#autocompleteContainer_contactoGestorEstudio_filter_table").remove();
          	var autocompleteGestorEstudio = $('<div id="autocompleteContainer_contactoGestorEstudio_filter_table" ><label for="contactoGestorEstudio_filter_table" class="control-label" data-i18n="label.gestor">'+$.rup.i18n.app.label.contactoGestorExpEnEstudio+'</label><input id="contactoGestorEstudio_filter_table" class="form-control" name="gestorExpediente.solicitante.dni" /></div>');
          	autocompleteGestorEstudio.appendTo("#div_contactoGestorEstudio_filter_table");
       		crearAutocompleteGestorEstudio();
 		}
 		if(!autocompleteEstudioCreado){
 			autocompleteEstudioCreado=true;
 		}
 	});
}

function crearAutocompleteGestorEstudio(){
	if($('#contactoGestorEstudio_filter_table_menu').length){
		$('#contactoGestorEstudio_filter_table_menu').remove();
	}
	$('#contactoGestorEstudio_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/solicitante/findGestoresDeExpCEntidad/"+entidadEstudio+"/"+codigoEstudio,
		sourceParam:{
			value : "dni",
			label : "nombreCompleto" 
		},
		getText: false,
		width: "100%",
		open : function() {
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("max-width", $('#contactoGestorEstudio_filter_table').width());
		}
	});
}

function cambiarLabelTipoEntidadEstudioBE(tipoEntidad){
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

jQuery(function($){
	
	jQuery.ajaxSetup({cache: false });
	
//	Cargar vista Estudio de Expediente - INICIO
	
	//Para enlazar dfesde Dashboard
	//El botón volver sólo se mostrará si esta página está incrustada en otra
	var cargaInicial = false;
	if (typeof(formatoPestana) === "undefined"){
		cargaInicial = true;
	}
	if (typeof(desdeConsultaExpedientes) != "undefined"){
		$('.aa79b-content').removeClass('in');
		cargaInicial = false;
	}
	
	
	$("#estudioExpedientes").rup_table({
		
		url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/filtroExpedienteEstadoEnEstudio",
		toolbar:{
			id: "estudioExpedientes_toolbar"
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
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,index: 1
				,right: false
			 }
				,i18nCaption: $.rup.i18n.app.simpelMaint
				,id: "volverOrigenPestana"	
				,css: "fa fa-arrow-left"
				,click : function(e){
					e.preventDefault();
					e.stopImmediatePropagation();
					volverACapaGeneralDashboard();
				}
			},
			{obj : {
				i18nCaption: $.rup.i18n.app.boton.reasignarTecnico
				,css: "fa fa-retweet"
				,index: 2
				,right: false
			 }
			 ,json_i18n : $.rup.i18n.app.simpelMaint
			 ,click : 
				 function(e){
				 if(!$('#estudioExpedientes').rup_table("isSelected")){
						e.preventDefault();
						$.rup_messages("msgAlert", {
							message: $.rup.i18n.app.comun.warningSeleccion
						});	
						return false;
					 }else{
						 $('#reasignarTecnicoAsignado').rup_combo("reload");
						 mostrarReasignarTecnico();
					 }
				}
			}
		]
		},
		colNames: [
			txtNumExp,
			txtTipo,
			txtGestorExp,
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			txtEstadoFase,
			txtFechaHoraSol,
			txtFechaHoraFinSol,
			txtNumPalabras,
			txtBopv,
			txtPresupuesto,
			txtSubsanacionAport,
			txtTecnicoAsignado
		],
		colModel: [
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.anyoNumExpConcatenado",
				align: "center", 
				width: "80", 
				index: "ANYONUMEXPCONCATENADO",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					dataOrigen = rowObject.origen;
					return '<b><a href="#" onclick="showDetalleExpediente(' + rowObject.anyo + ',' + rowObject.numExp + ',\'C\',\'' + rowObject.origen + '\')">' + cellvalue + '</a></b>';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipoExpediente",
				align: "center",
				index: $.rup.lang === 'es' ? "TIPO_EXPEDIENTE_ES"
						: "TIPO_EXPEDIENTE_EU",
				width: "45", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "vipEntidadGestorEs"
					: "vipEntidadGestorEu", 
			 	label: "label.gestorExp",
				align: "center", 
				width: "300",
				index: $.rup.lang === 'es' ? "GESTORCOLORDERES"
						: "GESTORCOLORDEREU",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{
				name: "anyo",
				hidden: true
			},
			{
				name: "numExp",
				hidden: true
			},
			{
				name: "origen",
				hidden: true
			},
			{
				name: "bitacoraExpediente.subsanacionExp.indSubsanado",
				hidden: true
			},
			{
				name: "gestorExpediente.solicitante.dniCompleto",
				hidden: true
			},
			{
				name: "gestorExpediente.solicitante.nombreCompleto",
				hidden: true
			},
			{
				name: $.rup.lang === 'es' ? "gestorExpediente.solicitante.gestorExpedientesVIPDescEs"
						: "gestorExpediente.solicitante.gestorExpedientesVIPDescEu",
				hidden: true
			},
			{
				name: "gestorExpediente.entidad.tipo",
				hidden: true
			},
			{
				name: "gestorExpediente.entidad.codigo",
				hidden: true
			},
			{ 	name: $.rup.lang === 'es' ? "gestorExpediente.solicitante.gestorExpedientesVIPDescEs"
					: "gestorExpediente.solicitante.gestorExpedientesVIPDescEu", 
				index: $.rup.lang === 'es' ? "GESTOREXPEDIENTESVIPDESCES"
						: "GESTOREXPEDIENTESVIPDESCEU",
				hidden: true,
				sortable: true
			},
			
			{ 	name: $.rup.lang === 'es' ? "bitacoraExpediente.faseExp.descAbrEs"
					: "bitacoraExpediente.faseExp.descAbrEu", 
			 	label: "label.faseExpediente",
				align: "center", 
				index: $.rup.lang === 'es' ? "FASEEXPEDIENTEDESCABRNORMES"
						: "FASEEXPEDIENTEDESCABRNORMEU",
				width: "110", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraAlta", 
			 	label: "label.fechaIni",
				align: "center", 
				index: "FECHA_ALTA_051",
				width: "140", 
				isDate: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraFin", 
				label: "label.fechaFin",
				align: "center", 
				width: "170",
				isDate: true,
				index:"FECHA_FINAL_IZO_053",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "expedienteTradRev.numTotalPalIzo", 
				label: "label.numPalabras",
				align: "right", 
				index:"NUM_TOTAL_PAL_IZO_053",
				width: "95", 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.publicarBopvDescEs"
					: "expedienteTradRev.publicarBopvDescEu", 
				label: "label.bopv",
				align: "center", 
				width: "50", 
				index: $.rup.lang === 'es' ? "PUBLICARBOPVDESCES"
						: "PUBLICARBOPVDESCEU",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "expedienteTradRev.estadoPresupuestoDescEs"
					: "expedienteTradRev.estadoPresupuestoDescEu", 
				label: "label.presupuesto",
				align: "center", 
				width: "90",
				index: $.rup.lang === 'es' ? "PRESUPUESTOTRDESCES"
						: "PRESUPUESTOTRDESCEU",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: $.rup.lang === 'es' ? "bitacoraExpediente.subsanacionExp.subsanacionDescEs"
					: "bitacoraExpediente.subsanacionExp.subsanacionDescEu", 
				label: "label.subsanacionAport",
				index: $.rup.lang === 'es' ? "SUBSANACIONDESCES"
						: "SUBSANACIONDESCEU",
				align: "center", 
				width: "150", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tecnico.nombreCompleto", 
				label: "label.dniTecnico",
				align: "left", 
				index: "DNI_TECNICO_051",
				width: "300", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
        ],
        model:"Expediente",
        usePlugins:[
        	"feedback",
        	"toolbar",
        	"filter",
        	"multiselection",
        	"report",
        	"fluid"
         	],
        primaryKey: ["anyo", "numExp"],
		multiSort: true,
		sortname: "ID_FASE_EXPEDIENTE_059, FECHA_FIN_052, IND_VIP_054, IND_PUBLICAR_BOPV_053, ID_REQUERIMIENTO_059, FECHA_LIMITE_064 ",
		sortorder: "asc, asc, desc, desc, asc",
		shrinkToFit: false,
		loadOnStartUp: cargaInicial,
		multiplePkToken:",",
		multiselection:{
			headerContextMenu:{
				selectAllPage: false,
				deselectAllPage: false,
				separator: false
			}
		}
		,filter:{
			clearSearchFormMode:"reset"
			,beforeFilter: function(){
				$("#estudioExpedientes").rup_table("hideFilterForm");
				$("#estudioExpedientes").rup_table("resetSelection");
			}
		}
		,report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/xlsxReport", click:
								function(event){
						 		if(!jQuery("#estudioExpedientes_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						}
					 ]}
				]
		},
		title: false,
		loadComplete: function(data){ 
			filterFormObjectEstudExp = obtenerFiltrosTabla('estudioExpedientes');
		}
	});
	
	//Para enlazar desde Dashboard
	//El botón volver sólo se mostrará si esta página está incrustada en otra
	//y hay que hacerlo desdpués de la creación dela tabla pq si no no existe el toolbar
	if (typeof(formatoPestana) === "undefined"){
		jQuery('#estudioExpedientes_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).hide();
	}
	
	function rechExpediente(){	
	
		var motivoRechazo = $('#motivoRechazo').rup_combo("getRupValue");
		var motivoRechazoDesc = $('#motivoRechazo').rup_combo("label");
		var descRechazo = $('#descRechazo').val();
		
		var Expediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };
		var ObservacionRechazo = 
	    {
	        "obsvRechazo068": descRechazo
	    };
		var RechazoExpediente = 
	    {
	        "idMotivoRechazo": motivoRechazo,
	        "motivoRechazoDesc": motivoRechazoDesc,
	        "observacionesRechazo": ObservacionRechazo
	    };
		
	    jQuery.ajax({
			type: "POST",
			url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/rechazarExp/E",
			dataType: "json",
			contentType: 'application/json', 
			data: jQuery.toJSON({
		   		"expediente":Expediente,
		   		"rechazoExp":RechazoExpediente,
		   	}),
			cache: false,
			success: function (data) {
				if (typeof(desdeConsultaExpedientes) != "undefined"){
					clean();
					volverACapaGeneralConsulta();
	 				if (data === -1){
						$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.faltaDirEmailRechExp, "ok");
					} else if (data === -2){
						$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.errorEnvioEmail, "ok");
					} else {
						$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado, "ok");
					}
	 				
                }else{
                	clean();
                	volverAEstudioExpediente();
                	if (data === -1){
    					$('#estudioExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.faltaDirEmailRechExp, "ok");
    				} else if (data === -2){
    					$('#estudioExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado + " " + $.rup.i18n.app.mensajes.errorEnvioEmail, "ok");
    				} else {
    					$('#estudioExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expRechazado, "ok");
    				}
                }
				
				
		     },
		     error: function (){
		    	 $('#rechazarExp_feedback').rup_feedback("set", "ErrorAlGuardar", "error");
		     }
	    });
		
	}
	
	function preFinalizarEstudio(){
	    var Expediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };
	    $.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/comprobarRequisitosDocu'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: jQuery.toJSON({"expediente":Expediente})		
		     ,async: false
		   	 ,success:function(data){
		   		 if(data == 0){ //Documentos correctos
		   			$.rup_messages("msgConfirm", {
                        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                        message: $.rup.i18nParse($.rup.i18n.app,"rup_table.finEstudio"),
                        OKFunction: function(){
                                finalizarEstudio();
                        }
	   			  });
		   		 } else if(data == 1){ //Falta la documentacion obligatoria
		   			$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.docuIncorrecto, "error");
		   		 } else if(data == 2){ //No se ha determinado el numero totla de palabras IZO
			   		$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.numPalabrasIZODocIncorrecto, "error");
		   		 } else if(data == 4){ //No se ha determinado la fecha final IZO
			   		$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.fechaFinalIZOIncorrecta, "error");
		   		 }else if(data == 5){ //Exped NO confidencial con ficheros encriptados
			   		$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorDocsEncriptadosFinAlta, "error");
		   		 }else if(data == 6){ //Exped confidencial con ficheros NO encriptados
		   			$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorDocsNoEncriptadosFinAlta, "error");
		   		 } else{ //No se ha determinado el numero de palabras IZO de todos los documentos
		   			$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.numPalabrasIZOIncorrecto, "error");
		   		 }
		   		 if(data != 0){
		   			$("#tabsExpediente").rup_tabs("selectTab",{
						idTab: "tabsExpediente",
						position: 1
					});
		   		 }
		   	 }
		 });
	}
	
	
	
	function finalizarEstudio(){
	    var Expediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };
   		jQuery.ajax({
			type: "POST",
			url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/finEstudio",
			dataType: "json",
			contentType: 'application/json', 
			data: jQuery.toJSON({
		   		"expediente":Expediente
		   	}),
			cache: false,
			success: function () {
				if (typeof(desdeConsultaExpedientes) != "undefined"){
	 				volverACapaGeneralConsulta();
	 				$('#consultaExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.estudioFinalizado, "ok");
                }else{
                	volverAEstudioExpediente();
                	$('#estudioExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.estudioFinalizado, "ok");
                }
				
				
				
	           	
			}
	    });
	}
	
	if ($('#idTipoExpediente_filter_table-menu')){
		$('#idTipoExpediente_filter_table-menu').remove();
	}
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
	});
	if ($('#subsanacion_filter_table-menu')){
		$('#subsanacion_filter_table-menu').remove();
	}
	$("#subsanacion_filter_table").rup_combo({
			loadFromSelect: true
			,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#subsanacion_filter_table-menu').width(jQuery('#subsanacion_filter_table-button').innerWidth());
			}
	});
	if ($('#presupuesto_filter_table-menu')){
		$('#presupuesto_filter_table-menu').remove();
	}
	$("#presupuesto_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#presupuesto_filter_table-menu').width(jQuery('#presupuesto_filter_table-button').innerWidth());
		}
	});
	if ($('#bopv_filter_table-menu')){
		$('#bopv_filter_table-menu').remove();
	}
	$("#bopv_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#bopv_filter_table-menu').width(jQuery('#bopv_filter_table-button').innerWidth());
		}
	});
	if ($('#fases_filter_table-menu')){
		$('#fases_filter_table-menu').remove();
	}
	$("#fases_filter_table").rup_combo({
		 source: "/aa79bItzulnetWar/fasesexpediente/getFasesExpediente/2",
		 sourceParam : {
				value: "id",
				label : jQuery.rup_utils.capitalizedLang()==="Es"?"descAbrEs":"descAbrEu"
			}
				,blank: ""
				,width: "100%"	
				,rowStriping: true
				,open: function(){
					jQuery('#fases_filter_table-menu').width(jQuery('#fases_filter_table-button').innerWidth());
				}
			});
	
	crearComboEntidadGestoraEstudio("");
//	Filtro - Cargar combo de fase entidad en funcion del tipo de entidad seleccionado
	jQuery('input[name=tipoEntidad]:first').click();
	jQuery('input[name=tipoEntidad]').change(function(){
			entidadCheckeadaEstudio = $(this).val();
	 		if("".localeCompare(entidadCheckeadaEstudio)==0){
	 			entidadCheckeadaEstudio = "-1";
			}
	 		$('#idEntidadSolicitante_filter_table').rup_combo("setRupValue","");
			entidadEstudio = $(this).val();
			if("".localeCompare(entidadEstudio)==0){
				entidadEstudio = "-1";
			}
	 		//valor del campo para filtrar por entidad
			$('#gestorExpedienteEntidadTipoEstudio').val($(this).val());
			crearComboEntidadGestoraEstudio($(this).val());
			anyadirEventoChangeAComboEntidadGestoraEstudio();
			cambiarLabelTipoEntidadEstudioBE($(this).val());
			
		});
	crearAutocompleteGestorEstudio();
	
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
	
	if ($('#idEntidadSolicitante_filter_table-menu')){
		$('#idEntidadSolicitante_filter_table-menu').remove();
	}
	
	if ($('#reasignarTecnicoAsignado-menu')){
		$('#reasignarTecnicoAsignado-menu').remove();
	}
	$('#reasignarTecnicoAsignado').rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/tecnTrad",
		sourceParam:{
			value : "dni",
			label : "nombreCompleto" 
		}
		,blank: ""
		,width: "100%"
		,rowStriping: true
		,open : function() {
			jQuery('#reasignarTecnicoAsignado-menu').width(jQuery('#reasignarTecnicoAsignado-button').innerWidth());
		}
	});

	$('#reasignarTecnicoAsignado').rup_combo("select", 0);
	$('#reasignarTecnicoAsignado').rup_combo("clear");
	$('#reasignarTecnicoAsignado').rup_combo("setRupValue","");
	
	if ($('#motivoRechazo-menu')){
		$('#motivoRechazo-menu').remove();
	}
	$("#motivoRechazo").rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/motivosrechazo/mostrarMotivosRechazo",
		sourceParam:{
			value : "id013",
			label : $.rup.lang === 'es' ? "descEs013" : "descEu013"
		}
		,blank: ""
		,width: "100%"
		,rowStriping: true
		,open : function() {
			jQuery('#motivoRechazo-menu').width(jQuery('#motivoRechazo-button').innerWidth());
		}
	});
	$('#motivoRechazo').rup_combo("select", 0);
	$('#motivoRechazo').rup_combo("clear");
	$('#motivoRechazo').rup_combo("setRupValue","");
	
//	Funcion para mostrar varios datos en celda Gestor expediente de tabla
	
	$('#estudioExpedientes_filter_cleanLink').click(function(){
		$('#estadoExpEstudioExpedientes').val(2);
		jQuery('input[name=tipoEntidad]:first').click();
	});
	
	setFocusFirstInput("#estudioExpedientes_filter_form");
    jQuery("#estudioExpedientes_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#estudioExpedientes_detail_form");
    });

//    Cargar vista Estudio de Expediente - FIN
    
//   Cargar vista Reasignar Tecnico - INICIO   
    
    $("#detalleSubsanacion_button_save").button().click(function(){
    	if(subrayado){
	    	$.rup_messages("msgConfirm", {
		         title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		         message: $.rup.i18nParse($.rup.i18n.app,"rup_table.aceptarSub"),
		         OKFunction: function(){
		        	 aceptarSubsanacion();
		         }
			});
    	} else {
		    aceptarSubsanacion();
    	}
    });
    
    $("#detalleSubsanacion_link_cancel").button().click(function(){
		 $.rup_messages("msgConfirm", {
	         title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	         message: $.rup.i18nParse($.rup.i18n.app,"rup_table.rechSub"),
	         OKFunction: function(){
	             rechazarSubsanacion();
	         }
		 });
    });
    
    $("#estudioExpedientesReasignarTecnico_detail_link_cancel").click(function(){
    	
    	volverAEstudioExpediente();
    });
    
//    Cargar vista Reasignar Tecnico - FIN
    
//    Inicializar feedbacks - INICIO
    
    $('#estudioExpedientes_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
    $('#estudioExpedientesReasignarTecnico_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
    $('#rechazarExp_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
    $('#requerirSubExp_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
    $('#detalleExpediente_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
//    Inicializar feedbacks - FIN
    
//    Navegacion entre vistas de pantalla - INICIO
    
   
    function aceptarSubsanacion(){
    	var Expediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };
		var SubsanacionExpediente = 
	    {
	        "id": $('#idSub').val(),
	        "fechaLimite": $('#detFechaLimite').val(),
	        "horaLimite": $('#detHoraLimite').val()
	        
	    };
		
		if (detalleSub && subrayado){
	    
			jQuery.ajax({
				type: "POST",
				url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/aceptarSubsanacion",
				dataType: "json",
				contentType: 'application/json', 
				data: jQuery.toJSON({
			   		"expediente":Expediente,
			   		"subsanacionExpediente":SubsanacionExpediente
			   	}),
				cache: false,
				success: function () {
		           	volverDetalleExp();
		           	$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.subAceptada, "ok");
		           	comprobarEstadoSubsanacion(anyoExpediente,idExpediente);
		           	$(".reqSubsanacion").removeClass("reqSubsanacion");
		           	//camposSeleccionados = ''; 
		           	//docusSeleccionados = ''; 
			     },
			     error: function (){
			    	 $('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
			     }
		    });
		    
		} else {
			
			jQuery.ajax({
				type: "POST",
				url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/updateFechaLimite",
				dataType: "json",
				contentType: 'application/json', 
				data: jQuery.toJSON({
			   		"expediente":Expediente,
			   		"subsanacionExpediente":SubsanacionExpediente
			   	}),
				cache: false,
				success: function () {
					volverDetalleExp();
					$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.subFechaLimite, "ok");
					comprobarEstadoSubsanacion(anyoExpediente,idExpediente);
			     },
			     error: function (){
			    	 $('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
			     }
		    });
		}
    }
    
    function rechazarSubsanacion(){
    	var Expediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };
		var SubsanacionExpediente = 
	    {
	        "id": $('#idSub').val()
	    };
	    
	    jQuery.ajax({
			type: "POST",
			url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/rechazarSubsanacion",
			dataType: "json",
			contentType: 'application/json', 
			data: jQuery.toJSON({
		   		"expediente":Expediente,
		   		"subsanacionExpediente":SubsanacionExpediente
		   	}),
			cache: false,
			success: function () {
				volverDetalleExp();
				comprobarEstadoSubsanacion(anyoExpediente,idExpediente);
				$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.subRechazada, "ok");
				$(".reqSubsanacion").removeClass("reqSubsanacion");
				//quitarSubrayado();
				//camposSeleccionados = ''; 
				//docusSeleccionados = ''; 
		     },
		     error: function (){
		    	 $('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
		     }
	    });
    }
    
    function mostrarReasignarTecnico(){
    	mostrarCapaExpedienteMYO('estudioExpedientesReasignarTecnico');
    	$('#reasignarTecnicoAsignado').rup_combo("select", 0);
    	$('#reasignarTecnicoAsignado').rup_combo("clear");
    	$('#reasignarTecnicoAsignado').rup_combo("setRupValue","");
    }
    
     function mostrarRechazarExp(){
    	mostrarCapaExpedienteMYO('rechazarExp');
    	$('#motivoRechazo').rup_combo("select", 0);
    	$('#motivoRechazo').rup_combo("clear");
    	$('#motivoRechazo').rup_combo("setRupValue","");
    	initFormRechazarExp = $("#rechazarExpform").rup_form("formSerialize");
    }
    
    
    function mostrarRequerirSubExp(){
    	mostrarCapaExpedienteMYO('requerirSubExp');
    	$('#motivoRechazo').rup_combo("select", 0);
    	$('#motivoRechazo').rup_combo("clear");
    	$('#motivoRechazo').rup_combo("setRupValue","");
    	    	
    	jQuery('#fechaLimite').rup_date({		
    		labelMaskId : "fecha-mask",
     		minDate: new Date(),
    		showButtonPanel : true,
    		showOtherMonths : true
    	});	
    	
    	jQuery.ajax({
    		type: "POST",
			url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/findAllSubsanciones",
			dataType : "json",
			contentType : 'application/json',
			cache : false,
			data: jQuery.toJSON({
				"anyo": anyoExpediente, 
		        "numExp": idExpediente,
		   		"tipoExp":valorInicialTipoExpediente
		   	}),	
			success:function(data){
				
				jQuery("#treeCamposSub").rup_tree("destroy");
				$("#treeCamposSub > ul").empty();
				
				//Carga del árbol
				for(var i = 0 ; i < data.length; i++){
					var titulo = $.rup.lang === 'es' ? data[i].desces078 : data[i].desceu078;
					$("#treeCamposSub > ul").append("<li id='"+titulo+"' value = '"+data[i].id078+"'>");
					$("#treeCamposSub > ul > li").last().append("<a href='#' onclick='campoBopvSelect("+data[i].id078+")' value ='"+data[i].id078+"'>"+titulo+"</a>");
					$("#treeCamposSub > ul").append("</li>");
					if((data[i].id078 === 11 || data[i].id078 === 22) && expedienteConfidencial=='S'){
						$("#treeCamposSub li[value="+data[i].id078+"]").addClass("jstree-unchecked");
						$("#treeCamposSub li[value="+data[i].id078+"]").removeClass("jstree-checked");
						$("#treeCamposSub li[value="+data[i].id078+"]").css("pointer-events","none");
						$("#treeCamposSub li[value="+data[i].id078+"]").addClass("jstree-disabled");
					} else {
						
						$("#treeCamposSub li[value="+data[i].id078+"]").css("pointer-events","");
						$("#treeCamposSub li[value="+data[i].id078+"]").removeClass("jstree-disabled");
					}
				}
				
				$("#treeCamposSub").rup_tree({
					"select" : {
					      "select_limit" : "1"
					   }
					,"checkbox" : {
					      "enable" : true
					   }
				});
				
				setTimeout(function() {
					$("#treeCamposSub > ul > li > ins.jstree-icon").remove();
					$("#treeCamposSub > ul > li > a > ins.jstree-icon").remove();
				}, 10);
			}
		});
    	
    	var DocumentosExpediente = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente
	    };
    	
    	jQuery.ajax({
			type : "POST",
			url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/listaDocuAsociados",
			dataType : "json",
			contentType : 'application/json',
			data: jQuery.toJSON({
		   		  "docuExp":DocumentosExpediente
		   	}),		
			cache : false,
	    	success: function(data)
	    	{
	    		crearArbolDocumentos(data);
	    	}
    	});
    }
    
    function mostrarReceptoresAutorizados(){
    	
     	$("#receptoresAutorizados").rup_table("filter");
    	mostrarCapaExpedienteMYO('divReceptoresAutorizados');
    	var anyoReceptor = $('#anyoreceptoresAutorizados_filter_table').val();
    	var numExpReceptor = $('#numExpreceptoresAutorizados_filter_table').val();
		$("#buscadorPersonasReceptores").buscador_personas({destino:'R' , multiselect:true , anyo: anyoReceptor, numExp:numExpReceptor, callback: seleccionarPersonasReceptoras});
	}
    
    function mostrarCategorizacionExpediente(){
    	
    	mostrarCapaExpedienteMYO('divCategorizacionExpediente');
    	
    	var anyoCategorizacion = $('#anyo_categorizacion_expediente').val();
    	var numExpCategorizacion = $('#numExp_categorizacion_expediente').val();
    	cargarMetadatosExpediente(anyoCategorizacion, numExpCategorizacion);
    }
    
    function idiomaEs(){
		$.rup.lang === 'es' ? 
				true:false;
	}
    
    $("#requerirSubExpform").rup_validate({
			feedback: $('#requerirSubExp_feedback'),
			liveCheckingErrors: false,				
			block:false,
			delay: 3000,
			gotoTop: true, 
			rules:{
				"detSubsanacion": {required: true},
				"fechaLimite": {required: true, date: true},
				"horaLimite": {required: true, hora: true, maxlength: 5, horaMin: true},
				"camposSelect": {validateSelectList:true}
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
	 		showFieldErrorsInFeedback: false
	});
    
    $("#rechazarExpform").rup_validate({
		feedback: $('#rechazarExp_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"motivoRechazo": {required: true},
			"descRechazo": {validateMotivoRech: true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
    });
    
    jQuery.validator.addMethod("validateMotivoRech", function(valor) {
    	
    	var motivoRechazo = $('#motivoRechazo').val();
    	var descRechazo = $('#descRechazo').val();
    	
    	if(motivoRechazo === "1" && descRechazo === ""){
    		return false;
    	}else{
    		return true;
    	}
	},$.rup.i18n.app.mensajes.errorDescVacio);
    
    //Validacion para numeros decimales
    jQuery.validator.addMethod("validateSelectList", function(valor) {  
    	var camposSubSelect = '';
    	$('#treeCamposSub ul li.jstree-checked').each(function(){
    		camposSubSelect += $(this).val()+';';
        }); 
    	var docuSubSelect = '';
    	$('#docuAsociados ul li.jstree-checked').each(function(){
    		docuSubSelect += $(this).val()+';';
        }); 
    	
    	if(camposSubSelect || docuSubSelect){
    		return true;
    	}else{
    		return false;    		
    	}
	},$.rup.i18n.app.mensajes.rellenaCampos);
    
    jQuery.validator.addMethod("horaMin", function(valor) {  
    	if($("#horaLimite").val() != "" && $("#fechaLimite").rup_date("getDate") == dateFormatSinHora(newDate,$.rup.lang)){
    		var d = parseDate($("#fechaLimite").rup_date("getDate"), $("#horaLimite").val(), $.rup.lang);
    		if(d.getTime() >= newDate.getTime()){
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return true;
    	}
    },$.rup.i18n.app.mensajes.fechaMinPasada);
    
    $("#detalleExpediente_toolbar").rup_toolbar({
        //adapter: "toolbar_jqueryui",
            buttons:[
                    {
                            i18nCaption: $.rup.i18n.app.boton.volver
                            ,css: "fa fa-arrow-left"
                            ,click : 
                                    function(e){
                                            e.preventDefault();
                                            e.stopImmediatePropagation();
                                            ocultarMenuDesplegableBotonOtros();
                                            if (typeof(desdeConsultaExpedientes) != "undefined"){
                        		 				volverACapaGeneralConsulta();
                                            }
                                            else {
                                            	if (comprobarFormulariosPestanas()){
                                                    
                                            		volverAEstudioExpediente();
                                            	} else {                                                
                                                    $.rup_messages("msgConfirm", {
                                                        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                                                        message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
                                                        OKFunction: function(){
                                                                volverAEstudioExpediente();
                                                        }
                                                    });
                                            	}
                                            }
                            	}
                    	},
                    {
                    	id : "rechazar",    
                    	i18nCaption: $.rup.i18n.app.boton.rechazarExp
                            ,css: "fa fa-times-circle"
                        ,click : 
                            function(e){
                                e.stopImmediatePropagation();
                                ocultarMenuDesplegableBotonOtros();
                                if (comprobarFormulariosPestanas()){
                                             mostrarRechazarExp();
                                    } else {                                                
                                            $.rup_messages("msgConfirm", {
                                                title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                                                message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
                                                OKFunction: function(){
                                                        mostrarRechazarExp();
                                                }
                                        });
                                    }
                        }
                    },
                    {
                    	id : "oficio",    
                    	i18nCaption: $.rup.i18n.app.boton.finEstudio
                            ,css: "fa fa-check-circle"
                        ,click : 
                            function(e){
                                e.stopImmediatePropagation();
                                ocultarMenuDesplegableBotonOtros();
                                if (comprobarFormulariosPestanas()){
                                	 preFinalizarEstudio();
                                } else {
                                    $.rup_messages("msgConfirm", {
                                        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                                        message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
                                        OKFunction: function(){
                                                preFinalizarEstudio();
                                        }
                                	});
                                }
                        }
                    },
                    {
                        id : "requerirSub",    
                    	i18nCaption: $.rup.i18n.app.boton.requerirSub
                            ,css: "fa fa-eye"
                        ,click : 
                            function(e){
                                e.stopImmediatePropagation();
                                ocultarMenuDesplegableBotonOtros();
                                if (comprobarFormulariosPestanas()){
                                             mostrarRequerirSubExp();
                                    } else {
                                            $.rup_messages("msgConfirm", {
                                                title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                                                message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
                                                OKFunction: function(){
                                                        mostrarRequerirSubExp();
                                                }
                                        });
                                    }
                        }
                    },
                    {
                        id : "otrosToolbarExpediente",
                        click: function(event){
        					$("[id='detalleExpediente_toolbar##otrosToolbarExpediente-mbutton-container']").show();
        				},
                        i18nCaption : $.rup.i18n.app.boton.otros,
                            buttons : [{
		                            i18nCaption: $.rup.i18n.app.boton.expedientesRelacionados
		                            ,css: "fa fa-link"
		                            ,click : function(e){
                                            e.preventDefault();
                                            e.stopImmediatePropagation();
                                            if (comprobarFormulariosPestanas()){
                                            	mostrarCapaExpedientesRelacionados();
                                            	ocultarMenuDesplegableBotonOtros();
                                            } else {
                                            	$.rup_messages("msgConfirm", {
	                                                title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	                                                message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	                                                OKFunction: function(){
	                                                		mostrarCapaExpedientesRelacionados();
	                                                       ocultarMenuDesplegableBotonOtros();
	                                                }
                                            	});
                                            }
		                            }
                            },
                    {
                            i18nCaption: $.rup.i18n.app.boton.receptoresAutorizados
                            ,css: "fa fa-users"
                            ,click : function(e){
                                        e.preventDefault();
		                                e.stopImmediatePropagation();
		                                if (comprobarFormulariosPestanas()){
		                                        mostrarReceptoresAutorizados();
		                                        ocultarMenuDesplegableBotonOtros();
		                                } else {
		                                        $.rup_messages("msgConfirm", {
		                                                        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		                                                        message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
		                                                        OKFunction: function(){
		                                                                mostrarReceptoresAutorizados();
		                                                                ocultarMenuDesplegableBotonOtros();
		                                                        }
		                                                });
		                                }
                                    }
                    }
                    ,
                    {
                            i18nCaption: $.rup.i18n.app.boton.contactoFacturacionExpediente
                            ,css: "fa fa-ticket"
                            ,click : function(e){
	                                        e.preventDefault();
			                                e.stopImmediatePropagation();
			                                if (comprobarFormulariosPestanas()){
			                                        mostrarContactoFacturacion();
			                                        ocultarMenuDesplegableBotonOtros();
			                                } else {
			                                        $.rup_messages("msgConfirm", {
			                                                        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			                                                        message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
			                                                        OKFunction: function(){
			                                                                mostrarContactoFacturacion();
			                                                                ocultarMenuDesplegableBotonOtros();
			                                                        }
			                                                });
			                                }
	                        		}
                    }
                    ,
                    {
                            i18nCaption: $.rup.i18n.app.boton.categorizacionExpediente
                            ,css: "fa fa-tags"       
                            ,click : 
                                            function(e){
                                                    e.preventDefault();
                                e.stopImmediatePropagation();
                                if (comprobarFormulariosPestanas()){
                                        mostrarCategorizacionExpediente();
                                        ocultarMenuDesplegableBotonOtros();
                                } else {
                                        $.rup_messages("msgConfirm", {
                                                        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                                                        message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
                                                        OKFunction: function(){
                                                                mostrarCategorizacionExpediente();
                                                                ocultarMenuDesplegableBotonOtros();
                                                        }
                                                });
                                }
                                    }
                    }
                    
            ]
                    
        }
        ,
		{id:"reports",
        	i18nCaption:$.rup.i18n.app.boton.justificanteSolicitud,
		   	right:true,
		   	css: "fa fa-file-pdf-o",
		   	click :
				function(){
					window.location.href='/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/informes/descargarJustificanteSolicitud/'+anyoExpediente+'/'+idExpediente;
				}
        }
        ]
    });
    
    $("[id='detalleExpediente_toolbar##otrosToolbarExpediente-mbutton-container']").addClass('centrado');
   
    
    $("#rechazarExp_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    if (initFormRechazarExp !== $("#rechazarExpform").rup_form("formSerialize")){
							$.rup_messages("msgConfirm", {
		         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
		         				OKFunction: function(){
		         					volverDetalleExp();
		         				}
		         			});
						} else {
							volverDetalleExp();
						}
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(e){
			    	e.stopImmediatePropagation();
			    	if($("#rechazarExpform").valid()){
				    	$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.app,"rup_table.rechExp"),
							OKFunction: function(){
								rechExpediente();
							}
						});
			    	}
			    }
			}
		]
	});
    
    $("#estudioExpedientesReasignarTecnico_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();	
	                    volverAEstudioExpediente();
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(event){
			    	event.preventDefault();
		 			event.stopImmediatePropagation();
		 			expedientesSeleccionados = [];
		 			listIdObjects = [];
					bloquearPantalla();
					obtenerIdsSeleccionadosRupTable("estudioExpedientes", filterFormObjectEstudExp, "guardarGestoresEstudioExp");
			    }
			}
		]
	});
    
    $("#requerirSubExp_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"   
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();	
	                    
	                    var camposSubSelect = '';    
				    	$('#treeCamposSub ul li.jstree-checked').each(function(){
				    		camposSubSelect += $(this).val()+';';
				    		
				        }); 
				    	
				    	var docuSubSelect = '';  
				    	$('#docuAsociados ul li.jstree-checked').each(function(){
				    		docuSubSelect += $(this).val()+';';
				        }); 
	                    
	                    if(docuSubSelect != "" || camposSubSelect != "" || $('#detSubsanacion').val() != "" || $('#fechaLimite').val() != ""){
	                    	$.rup_messages("msgConfirm", {
	           		         title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	           		         message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	           		         OKFunction: function(){
	           		        	volverDetalleExp();
	           		         }
	                    	});
	                    } else {
	                    	volverDetalleExp();
	                    }
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(e){
			    	e.stopImmediatePropagation();
			    	var camposSubSelect = '';    
			    	$('#treeCamposSub ul li.jstree-checked').each(function(){
			    		camposSubSelect += $(this).val()+';';
			    		
			        }); 
			    	
			    	var docuSubSelect = '';  
			    	$('#docuAsociados ul li.jstree-checked').each(function(){
			    		docuSubSelect += $(this).val()+';';
			        }); 

			    	if (confidencialChecked) {
			    		
			    		indNuevosDocus = 'S';
			    	} else {
			    		
			    		indNuevosDocus = 'N';
			    	}
			    	
				    var Expediente = 
				    {
				        "anyo": anyoExpediente, 
				        "numExp": idExpediente
				    };
					var SubsanacionExpediente = 
				    {
				        "detalle": $('#detSubsanacion').val(),
				        "fechaLimite": $('#fechaLimite').val(),
				        "horaLimite": $('#horaLimite').val(),
				        "indDocNuevos": indNuevosDocus
				    };
				    
				    
			    	if ($("#requerirSubExpform").valid()){	
			    		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					    jQuery.ajax({
							type: "POST",
							url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/registrarSubsanacion",
							dataType: "json",
							contentType: 'application/json', 
							data: jQuery.toJSON({
						   		"expediente":Expediente,
						   		"camposSelectSub":camposSubSelect,
						   		"docuSelecSub":docuSubSelect,
						   		"subsanacionExp": SubsanacionExpediente 
						   	}),
							cache: false,
							success: function () {
								volverDetalleExp();
								clean();
								comprobarEstadoSubsanacion(anyoExpediente,idExpediente);
								desbloquearPantalla();
								$('#detalleExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.subRegistrada, "ok");
						     },
						     error: function (){
						    	 $('#requerirSubExp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
						     }
					    });
			    	}
			    }
			}
		]
	});
    
    
    
    $('.rup-feedback').each(function(){
		$(this).rup_feedback('close');
	});
    
    $("#horaLimite").blur(function() { 
		 newDate = new Date();
	 });

    //si venimos de consulta, esperamos a que carguen los js para ir al detalle
	if (typeof(desdeConsultaExpedientes) != "undefined"){
		llamadasFinalizadas('irADetalleDesdeConsulta');
		
	}
	
});

