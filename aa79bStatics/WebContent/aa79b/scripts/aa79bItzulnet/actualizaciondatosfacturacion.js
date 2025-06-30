var capaPestGeneral = '';
var indBoe = "";
var urlBoe = "";
var formBoe = "";
var boeSwitchDisabled = false;
var selectedAnyo;
var selectedNumExp;
var filterFormObjectActDatFact;
var listIdObjects = [];
var anyoActualFilter = true;

/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

function guardarDatosBoeExpedientes(){
	var listaExpedienteObject = {
			listaExpediente: []
	};
	for(var i=0;i<listIdObjects.length;i++){
		listaExpedienteObject.listaExpediente.push({ 
	        "anyo" : listIdObjects[i].anyo,
	        "numExp"  : listIdObjects[i].numExp
	    });
	}
	var sendObject = {
			"listaExpediente" : listaExpedienteObject
	}
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/servicios/actDatosFacturacion/guardarDatosBoe' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(sendObject) 
        ,async: false 
        ,success:function(data){
        	if(data){
        		// datos guardados, recarga de tabla y mensaje de ok 
        		$("#busquedaGeneral").trigger('reloadGrid');
        		$("#busquedaGeneral").rup_table("resetSelection");
        		desbloquearPantalla();
        		mostrarMensajeFeedback("busquedaGeneral_feedback", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok")
        		
        	}else{
        		$.rup_messages("msgAlert", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.errorGuardandoDatos
				});
				desbloquearPantalla();
				return false;
        	}
        }
	,error:function(e){
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.mensajes.errorCargandoDatos
		});
		desbloquearPantalla();
		return false;
	}
	});	
}

function comprobarDatosBoeExpedientes(){
	//comprobar que los seleccionados tienen todos el indboe a no
	var listaExpedienteObject = {
			listaExpediente: []
	};
	for(var i=0;i<listIdObjects.length;i++){
		listaExpedienteObject.listaExpediente.push({ 
	        "anyo" : listIdObjects[i].anyo,
	        "numExp"  : listIdObjects[i].numExp
	    });
	}
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/servicios/actDatosFacturacion/comprobarExpSelBoeANo' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(listaExpedienteObject) 
        ,async: false 
        ,success:function(data){
        	if(data){
        		// todos los expedientes con indboe a no 
        		guardarDatosBoeExpedientes();
        		
        	}else{
        		$.rup_messages("msgAlert", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.seleccionIncorrectaIndBoe
				});
				desbloquearPantalla();
				return false;
        	}
        }
	,error:function(e){
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.mensajes.errorCargandoDatos
		});
		desbloquearPantalla();
		return false;
	}
	});	
}

function irDetalleExpediente(elAnyo, elNumExp, tipoExpediente){
	
	bloquearPantalla();
	
	anyoExpediente = elAnyo;
	idExpediente = elNumExp;
	
	var urlDetalleExp;
	if (tipoExpediente === datosExp.tipoExp.interpretacion){
		urlDetalleExp = "/aa79bItzulnetWar/servicios/actDatosFacturacion/detalleDatosFactInter";
	} else {
		urlDetalleExp = "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detallecontactofacturacion/maint";
	}
	
	$.rup_ajax({
	   	 url: urlDetalleExp 
	   	 ,success:function(data, textStatus, jqXHR){
	   		capaPestGeneral = $('#divBusqueda').detach();
	   		$("#divBusquedaGeneral").html(data);
	   		desbloquearPantalla();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert($.rup.i18n.app.mensajes.errorGenericoCapas);
	   	 }
	 });
}

function volverARevisionDatosFacturacion(){
	$("#divBusquedaGeneralCapa").detach();
	$("#divBusquedaGeneral").append("<div id='divBusquedaGeneralCapa'></div>");
	$("#divBusquedaGeneralCapa").html(capaPestGeneral);
}

function mostrarDialogBoe(elAnyo, elNumExp, elIndBoe){
	bloquearPantalla();
	selectedAnyo = elAnyo;
	selectedNumExp = elNumExp;
	if("S" == elIndBoe){
		//cargar datos de boe
		var jsonObject = {
				"anyo" : elAnyo,
				"numExp" : elNumExp
			};

			$.ajax({
		        type: 'POST' 
		        ,url: '/aa79bItzulnetWar/servicios/actDatosFacturacion/obtenerDatosBoeExp' 
		        ,dataType: 'json' 
		        ,contentType: 'application/json' 
		        ,data: $.toJSON(jsonObject) 
		        ,async: false 
		        ,success:function(data){
		        	if(data){
		        		indBoe = data.expedienteTradRev.indPublicadoBoe;
			        	urlBoe = data.expedienteTradRev.urlBoe;
			        	$("#indBoe_dialog").rup_dialog('open');
		        	}else{
		        		$.rup_messages("msgAlert", {
							title: $.rup.i18n.app.label.aviso,
							message: $.rup.i18n.app.mensajes.errorCargandoDatos
						});
						desbloquearPantalla();
						return false;
		        	}
		        }
			,error:function(e){
				$.rup_messages("msgAlert", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.errorCargandoDatos
				});
				desbloquearPantalla();
				return false;
			}
			});		
	}else{
		$("#indBoe_dialog").rup_dialog('open');
	}
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

function fncInicializarDialogBoe(){
	$("#indBoe_dialog").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: false,
	    width: "500",
	    title: $.rup.i18nParse($.rup.i18n.app,"label.modificarIndboe"),
	    buttons: [{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"guardar"),
			click: function (event) {
				event.preventDefault();
				event.stopImmediatePropagation();
				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
				if($('#urlBoeDialog').val()){
					$('#urlBoeDialog').rules( "add", {  url: true,  maxlength: 250 });
					if(!$("#indBoe_dialog_form").valid()){
						//url no valida
						desbloquearPantalla();
						return false;
					}
				}
				//guardar en bbdd
				var listaExpedienteObject = {
	        			listaExpediente: []
	        	};
				listaExpedienteObject.listaExpediente.push({ 
    		        "anyo" : selectedAnyo,
    		        "numExp"  : selectedNumExp
    		    });
				var sendObject = {
						"listaExpediente" : listaExpedienteObject,
						"urlBoe" : $('#urlBoeDialog').val(),
						"indBoe" : $('#indPublicadoBoeDialog').bootstrapSwitch('state')?"S":"N"
				}
				
				$.ajax({
			        type: 'POST' 
			        ,url: '/aa79bItzulnetWar/servicios/actDatosFacturacion/guardarDatosBoe'
			        ,data: JSON.stringify(sendObject)
			        ,dataType: 'json'
			        ,contentType: 'application/json'
			        ,cache: false 
			        ,success:function(data){
			        	//mensaje de guardado correcto y cerrar dialog
			        	if(boeSwitchDisabled && $('#indPublicadoBoeDialog').prop('disabled')){
							$('#indPublicadoBoeDialog').bootstrapSwitch('toggleDisabled',true, true);
						}
			        	formBoe = $("#indBoe_dialog_form").rup_form("formSerialize");
			        	$("#indBoe_dialog").rup_dialog('close');
			        	$("#busquedaGeneral").trigger('reloadGrid');
		        		$("#busquedaGeneral").rup_table("resetSelection");
		        		desbloquearPantalla();
		        		mostrarMensajeFeedback("busquedaGeneral_feedback",$.rup.i18n.app.mensajes.guardadoCorrecto, "ok")
			        }
					,error: function(error){
						$.rup_messages("msgAlert", {
							title: $.rup.i18n.app.label.aviso,
							message: $.rup.i18n.app.mensajes.errorGuardandoDatos
						});
						desbloquearPantalla();
						return false;
				   	}
				});
			}
		},
		{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"cancelar"),
			click: function () { 
				$("#indBoe_dialog").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			formBoe="";
			$('#urlBoeDialog').val("");
			if(indBoe && "S" == indBoe){
				//poner a true el check y deshabilitarlo
				$('#indPublicadoBoeDialog').bootstrapSwitch('setState', true);
				$('#urlBoeDialog').attr("disabled", false);
				//poner la url
				if(urlBoe){
					$('#urlBoeDialog').val(urlBoe);
				}
				//control de cambios
				formBoe = $("#indBoe_dialog_form").rup_form("formSerialize");
			}else{
				//el check a no y habilitado
				$('#indPublicadoBoeDialog').bootstrapSwitch('setState', false);
				$('#urlBoeDialog').attr("disabled", true);
				//control de cambios
				formBoe = $("#indBoe_dialog_form").rup_form("formSerialize");
				if($('#indPublicadoBoeDialog').prop('disabled')){
					$('#indPublicadoBoeDialog').bootstrapSwitch('toggleDisabled',true, true);
				}
			}
			$('#labelNumExp').text(" : " + concatenarAnyoNumExp(selectedAnyo,selectedNumExp));
			desbloquearPantalla();
		},
		close: function(event, ui) {
			indBoe = "";
			url = ""; 
		},
		beforeClose: function(event, ui){
			//si el switch esta deshabilitado, lo habilitamos para la serializacion y lo volvemos a dejar como estaba
			if(boeSwitchDisabled && $('#indPublicadoBoeDialog').prop('disabled')){
				$('#indPublicadoBoeDialog').bootstrapSwitch('toggleDisabled',true, true);
			}
			if(formBoe != $("#indBoe_dialog_form").rup_form("formSerialize")){
				event.preventDefault();
				event.stopImmediatePropagation();
				//han habido cambios
				$.rup_messages("msgConfirm", {
     				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
     				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
     				OKFunction: function(e){
     					//igualamos la variable del formulario para guardar y descartar los cambios
     					formBoe = $("#indBoe_dialog_form").rup_form("formSerialize");
     					if(boeSwitchDisabled && $('#indPublicadoBoeDialog').prop('disabled')){
     						$('#indPublicadoBoeDialog').bootstrapSwitch('toggleDisabled',true, true);
     					}
     					setTimeout(function() {
     						$("#indBoe_dialog").rup_dialog('close');
						}, 20);
     				},
     				CANCELFunction: function(e){
     					//volvemos a habilitar el switch si lo estaba
     					if(boeSwitchDisabled && !$('#indPublicadoBoeDialog').prop('disabled')){
     						$('#indPublicadoBoeDialog').bootstrapSwitch('toggleDisabled',true, true);
     					}
     					return false;
     				}
     			});
			}
			if($('#urlBoeDialog').hasClass("error")){
				$('[id$="error"]').remove();
				$('#urlBoeDialog').removeClass("error");
			}
		}
	});
	
	jQuery('#indPublicadoBoeDialog')
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	
	$('#indPublicadoBoeDialog').on('switch-change', function(event, state) {
		if (state.value){ 
			//Activando ... habilitar input url boe
			$('#urlBoeDialog').attr("disabled", false);
		}else{
			//Desactivando ... limpiar y deshabilitar input url boe
			$('#urlBoeDialog').val("");
			$('#urlBoeDialog').attr("disabled", true);
		}
	});
	
	$("#indBoe_dialog_form").rup_validate({
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: false,
		showFieldErrorsInFeedback: false
	});
}

function fncTipoExpedienteCombo(){
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			$('#idTipoExpediente_filter_table-menu').width($('#idTipoExpediente_filter_table-button').innerWidth());
		}
		,change: function(){
			if ($('#'+$(this).attr("id")).rup_combo("getRupValue") === datosExp.tipoExp.interpretacion){
				$("#indPublicarBopv").rup_combo('disable');
				$("#indPublicarBopv").rup_combo("setRupValue","");
				$("#indPublicadoBoe").rup_combo('disable');
				$("#indPublicadoBoe").rup_combo("setRupValue","");
				$("#idiomaDestino").rup_combo('disable');
				$("#idiomaDestino").rup_combo("setRupValue","");
				$("#expFacturable_filter_table").rup_combo('disable');
				$("#expFacturable_filter_table").rup_combo("setRupValue","");
			} else {
				$("#indPublicarBopv").rup_combo('enable');
				$("#indPublicadoBoe").rup_combo('enable');
				$("#idiomaDestino").rup_combo('enable');
				$("#expFacturable_filter_table").rup_combo('enable');
			}
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
	});
}

function fncPublicarBopvCombo(){
	$("#indPublicarBopv").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
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
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
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
				var id = $(this).attr("id");
				$("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
			}
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

/* CONTACTO SOLICITANTE */
function creaComboContactoFiltro(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
	if ( $('#contactoGestor_filter_table-button').length   ){
		$('#contactoGestor_filter_table').rup_combo("clear");
	}
	
	$('#contactoGestor_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/solicitante/findSolicitanteActDatosFact/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
		sourceParam : {
			value: "dni",
			label : "nombreCompleto" 
		},
	    rowStriping: true,
	    blank: "",
		orderedByValue: false,
		open: function(){
			var id = $(this).attr("id");
	        $("#"+id+"-menu").width($("#"+id+"-button").width());
	    },
	    onLoadSuccess: function(){ 
	    	if (typeof(valorSeleccionar) !== "undefined"){
	    		$("#contactoGestor_filter_table").rup_combo("select", valorSeleccionar+'');
	    	}
	    }
	});	
}

/* CONTACTO A FACTURAR */
function creaComboContactoFFiltro(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
	if ( $('#dniContactoEntidadSolicitanteF-button').length   ){
		$('#dniContactoEntidadSolicitanteF').rup_combo("clear");
	}
	
	$('#dniContactoEntidadSolicitanteF').rup_combo({
		source : "/aa79bItzulnetWar/solicitante/findSolicitanteAFacturarActDatosFact/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
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
 * UTILIDADES - FIN
 * ****************************
 */

/*
 * ****************************
 * FORMATEADORES - INICIO
 * ****************************
 */

function mostrarCeldaDatosFacturacionConcatenados(cellvalue, rowObject){
	var celda = '';
	if (cellvalue != undefined){
		celda = '<p class="dest-facturacion">';
		cellvalue = cellvalue.replace(/###/g,'<br />');
		celda = celda.concat(cellvalue +'</p>');
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
	fncExpFacturableCombo()
	fncPublicarBopvCombo();
	fncPublicadoBoeCombo();
	crearComboIdiomaDestinoConTodos('idiomaDestino');
	fncEntidadFacturableCombo();
	creaComboContactoFiltro('-1','-1');
	creaComboContactoFFiltro('-1','-1');
	fncInicializarDialogBoe();
	
	/* ENTIDAD GESTORA */
	$('#idEntidadSolicitante_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/entidad/actDatosFacturacion",
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
			$('#idEntidadSolicitante_filter_table-menu').width($('#idEntidadSolicitante_filter_table-button').innerWidth());
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", $('#idEntidadSolicitante_filter_table').innerWidth());
			$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
		},
		select: function(){
			
			if ($('#idEntidadSolicitante_filter_table').val() != ''){
				var comp = $('#idEntidadSolicitante_filter_table').val().split("_");
				creaComboContactoFiltro(comp[0] , comp[1]);
			}else{
				creaComboContactoFiltro($('#gestorExpedienteEntidadTipo').val() , -1);
			}
		}
	});
	
	//	Filtro - Cargar combo de fase entidad gestora en funcion del tipo de entidad seleccionado
	$('input[name=tipoEntidad]:first').click();
	$('input[name=tipoEntidad]').change(function(){
			$('#idEntidadSolicitante_filter_table').rup_combo({
				source : "/aa79bItzulnetWar/entidad/actDatosFacturacion/"+$(this).val(),
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
					$('#idEntidadSolicitante_filter_table-menu').width($('#idEntidadSolicitante_filter_table-button').innerWidth());
					$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", $('#idEntidadSolicitante_filter_table').innerWidth());
					$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
					$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
				},
				select: function(){
					
					if ($('#idEntidadSolicitante_filter_table').val() != ''){
						var comp = $('#idEntidadSolicitante_filter_table').val().split("_");
						creaComboContactoFiltro(comp[0] , comp[1]);
					}else{
						creaComboContactoFiltro($('#gestorExpedienteEntidadTipo').val() , -1);
					}
					
				},
			    onLoadSuccess: function(){ 
			    		$("#idEntidadSolicitante_filter_table").rup_combo("select", -1);
			    }
			});
			$('#gestorExpedienteEntidadTipo').val($(this).val());
			
			if($(this).val()){
				creaComboContactoFiltro($(this).val(), '-1');
			}else{
				creaComboContactoFiltro('-1', '-1');
			}
			
		});
	
	/* FIN ENTIDAD GESTORA */
	
	/* ENTIDAD A LA QUE SE FACTURA */
	
	$('#idEntidadSolicitanteF').rup_combo({
		source : "/aa79bItzulnetWar/entidad/entAFacturarActDatosFacturacion/",
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
			$('#idEntidadSolicitanteF-menu').width($('#idEntidadSolicitanteF-button').innerWidth());
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", $('#idEntidadSolicitanteF').innerWidth());
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
	$('input[name=tipoEntidadF]:first').click();
	$('input[name=tipoEntidadF]').change(function(){
			$('#idEntidadSolicitanteF').rup_combo({
				source : "/aa79bItzulnetWar/entidad/entAFacturarActDatosFacturacion/"+$(this).val(),
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
					$('#idEntidadSolicitanteF-menu').width($('#idEntidadSolicitanteF-button').innerWidth());
					$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", $('#idEntidadSolicitanteF').innerWidth());
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
		$("#busquedaGeneral_filter_form").rup_form("clearForm");
		$("#busquedaGeneral").rup_table("resetSelection");
		$('input[name=tipoEntidad]:first').click();
		$('input[name=tipoEntidadF]:first').click();
		anyoActualFilter = true;
		anyoActual("anyo_filter_table");
		$("#busquedaGeneral").rup_table("filter");
	});
	
	$("#busquedaGeneral").rup_table({
		url: "/aa79bItzulnetWar/servicios/actDatosFacturacion",
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
			},newButtons : [      
				{obj : {  
					i18nCaption: $.rup.i18n.app.label.modificarIndboe
					,css: "fa fa-bookmark-o"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click: function(event){
						event.preventDefault();
			 			event.stopImmediatePropagation();
			 			listIdObjects = [];
						bloquearPantalla();
						obtenerIdsSeleccionadosRupTable("busquedaGeneral", filterFormObjectActDatFact, "comprobarDatosBoeExpedientes");
					}
				}
			]
		},
		colNames: [
			"",
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.gestorExpediente,
			$.rup.i18n.app.label.numTotalPalabras,
			$.rup.i18n.app.label.bopv,
			$.rup.i18n.app.label.boe,
			$.rup.i18n.app.boton.contactoFacturacionExpediente
		],
		colModel: [
			{ 	name: "idTipoExpediente",width: "1", hidden:true},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numexpediente",
				align: "center", 
				index: "ANYONUMEXPCONCATENADO",
				width: "90", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					tipoExp = "'"+rowObject.idTipoExpediente+"'";
					anyoExpediente = rowObject.anyo;
					idExpediente = rowObject.numExp;
					return '<b style="display: block;"><a href="#" onclick="irDetalleExpediente('+anyoExpediente+','+idExpediente+','+tipoExp+')">' + cellvalue + '</a></b>';
	
				}
			},
			{ 	name:$.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipo",
				align: "center", 
				index: "IDTIPOEXPEDIENTE",
				width: "52", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
				align: "left",
				index: "TITULO",
				width: "205", 
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
				align: "center",
				index: $.rup.lang === 'es' ? "GESTORCOLORDERES"
						: "GESTORCOLORDEREU",
				width: "300", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "expedienteTradRev.numTotalPalConTramosPerfectMatch", 
			 	label: "label.documento.numTotalPal",
				align: "right", 
				index: "NUMPALCOLORDER",
				width: "178", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name:$.rup.lang === 'es' ? "expedienteTradRev.publicarBopvDescEs" : "expedienteTradRev.publicarBopvDescEu", 
			 	label: "label.bopv",
				align: "center", 
				index: "INDPUBLICARBOPV",
				width: "61", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name:$.rup.lang === 'es' ? "expedienteTradRev.indPublicadoBoeDescEs"
					: "expedienteTradRev.indPublicadoBoeDescEu", 
			 	label: "titulo.boe",
				align: "center", 
				index: "INDPUBLICADOBOE",
				width: "52", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue != '-'){
						return '<b style="display: block;"><a href="#" onclick="event.stopPropagation();event.preventDefault();mostrarDialogBoe('+anyoExpediente+','+idExpediente+',\''+rowObject.expedienteTradRev.indPublicadoBoe+'\')">' + cellvalue + '</a></b>';
					} else {
						return cellvalue;
					}
					
				}
			},
			{   name:$.rup.lang === 'es' ? "entidadYContactoFacturacionConcatenadosEs" : "entidadYContactoFacturacionConcatenadosEu", 
			 	label: "titulo.entidadContactoFacturacion",
				align: "left", 
				index: "DATOSFACTURACIONCONCATENADOSEU",
				width: "610", 
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
        	"feedback",
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
						url : "/aa79bItzulnetWar/servicios/actDatosFacturacion/xlsxReport",
						click : function(event) {
							
						}
					},
					{ i18nCaption : $.rup.i18n.app.tabla.pdf,
						divId:"report_busquedaGeneral", 
						css:"fa fa-file-pdf-o", 
						url: "/aa79bItzulnetWar/servicios/actDatosFacturacion/pdfReport", 
						click : function(event){
				 			
						}
					}  
				]}
			]
		}
	    ,title: false,
		loadComplete: function(data){ 
			anyoActual("anyo_filter_table");
			filterFormObjectActDatFact = obtenerFiltrosTabla('busquedaGeneral');
			if(filterFormObjectActDatFact.tipoEntidadF){
				delete filterFormObjectActDatFact.tipoEntidadF;
			}
		}
	});
	
});
