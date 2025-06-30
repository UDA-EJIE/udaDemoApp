var hayCambios = false;
var tipoErrorDoc = 0;
var initDocumentoForm;
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};

/**
 */
function fncGuardarDatosAuditoria(){
	// si campos validos guardamos, si no, mostramos mensaje de error
	if (validarCampos()){
		obtenerYGuardarDatosAuditoria();
	} else {
		mostrarMensajeFeedback("detalleAuditoria_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorCamposAuditoria"), "error");
		desbloquearPantalla();
	}
}

/**
 */
function obtenerYGuardarDatosAuditoria(){
	// obtenemos los datos de la auditoria
	var auditoria = $('#auditoria_datos_form').rup_form("formToJson");
	auditoria.indEnviado = $('#auditoria_visible').bootstrapSwitch('state') ? auditoriaViewData.activo.si : auditoriaViewData.activo.no;
	var secciones = new Array();
	// buscamos las secciones que no estan deshabilitadas
	$('div[id^=seccion_container_]:not(.ind-respuesta)').each(function(){
		var tipoSeccion = $(this).data('tiposeccion');
		var idSeccion = $(this).data('idseccion');
		var seccion = {};
		seccion.idAuditoria = auditoria.idAuditoria;
		seccion.idSeccion = idSeccion;
		seccion.tipoSeccion = tipoSeccion;
		seccion.indObservaciones = $('#indObservaciones_' + idSeccion).val();
		if (auditoriaViewData.tipoSeccion.valoracion == tipoSeccion){
			// seccion de tipo 1
			seccion.resulAuditoria = $('#campo_sumatorio-' + idSeccion).val();
		}
		if (auditoriaViewData.tipoSeccion.valoracion == tipoSeccion
			|| auditoriaViewData.tipoSeccion.condicion == tipoSeccion){
			// seccion de tipo 1 o 2
			var rupTableId = 'auditoria_seccion_'+tipoSeccion+'_'+idSeccion;
			var rowIds = $('#'+rupTableId).rup_table("getDataIDs");
			var arrCampos = new Array();
			rowIds.forEach(function(rowId){
				var campo = {};
				var rowData = $('#'+rupTableId).rup_table("getRowData", rowId);
				campo.idAuditoria = auditoria.idAuditoria;
				campo.idCampo = rowData.idCampo;
				campo.idSeccionPadre = idSeccion;
				campo.tipoCampo = rowData.tipoCampo;
				campo.indObservaciones = rowData.indObservaciones;
				if (auditoriaViewData.tipoSeccion.valoracion == tipoSeccion) {
					// seccion de tipo 1
					campo.porNivelCalidad = $('#campo_niveles_selected_value-' + campo.idCampo).val();
					campo.observ = $('#campo_observaciones-' + campo.idCampo).val();
				} else if (auditoriaViewData.tipoSeccion.condicion == tipoSeccion) {
					// seccion de tipo 2
					campo.observ = $('#campo_observaciones-' + campo.idCampo).val();
					if ($('#check_ind_marcado-'+rowData.idCampo).prop( "checked")){
						campo.indMarcado = auditoriaViewData.activo.si;
					}else{
						campo.indMarcado = auditoriaViewData.activo.no;
					}
				}
				arrCampos.push(campo);
			});
			seccion.lCamposSeccion = arrCampos;
			
		} 
		// comun a las secciones
		if (auditoriaViewData.activo.si == $('#indObservaciones_' + idSeccion).val()){
			seccion.observ = $('#auditoria_seccion_observ-' + idSeccion).val();
		}
		secciones.push(seccion)
	});
	auditoria.lAuditoriaSeccionExpediente = secciones;
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/auditoria/detalle/guardarDatosAuditoria' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(auditoria) 
        ,async: false 
        ,success:function(data){
        	if (data){
				// guardado ok.
				if (auditoriaViewData.activo.si == data.indEnviado) {
					// auditoria enviada. Hay que volcar fecha de envio y estado de la auditoria
					$('#auditoria_datos_form_fechaEnvio').val(data.fechaEnvio);
					if ($.rup.lang === "es"){
						$('#auditoria_datos_form_estado_desc').val(data.estadoDescEs);
					} else if ($.rup.lang === "eu") {
						$('#auditoria_datos_form_estado_desc').val(data.estadoDescEu);
					}
					
					
					// auditoria enviada. Hay que deshabilitar/ocultar campos/botones
					$('#auditoria_datos_form_estado').val(data.indEnviado);
					fncComprobarEstadoAuditoria();
					// ocultar boton guardar de toolbar
					if ($.rup.lang === "es"){
						$("[id='detalleAuditoria_toolbar##Guardar']").hide();
					} else if ($.rup.lang === "eu"){
						$("[id='detalleAuditoria_toolbar##Gorde']").hide();
					}
					
					//comprobamos si el email ha sido enviado correctamente
					var messageText = "";
					var messageType = "";
					if (data.resulEmail == auditoriaViewData.estadoEnvioEmail.ok){
						// email enviado correctamente
						messageText = $.rup.i18n.app.mensajes.guardadoDatosAuditoriaOkYEmailEnviado;
						messageType = "msgOK";
					} else if (data.resulEmail == auditoriaViewData.estadoEnvioEmail.sinDestinatarios){
						// no se han encontrado destinatarios para el envio del email
						messageText = $.rup.i18n.app.mensajes.guardadoDatosAuditoriaOkYEmailNoEnviadoNoDestinatario;
						messageType = "msgAlert";
					} else if (data.resulEmail == auditoriaViewData.estadoEnvioEmail.error){
						// error al enviar email
						messageText = $.rup.i18n.app.mensajes.guardadoDatosAuditoriaOkYEmailNoEnviadoError;
						messageType = "msgAlert";
					} else {
						// error inesperado
						messageText = $.rup.i18n.app.mensajes.guardadoDatosAuditoriaOkYEmailNoEnviadoError;
						messageType = "msgAlert";
					}
					$.rup_messages(messageType, {
	    				message: messageText,
	    				title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
	    			});
				} else {
					$.rup_messages("msgOK", {
	    				message: $.rup.i18n.app.mensajes.guardadoDatosAuditoriaOk,
	    				title: $.rup.i18nParse($.rup.i18n.base,"rup_global.save")
	    			});
				}
				hayCambios=false;
				desbloquearPantalla();
        	} else {
				$.rup_messages("msgError", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.errorGuardandoDatos
				});
				desbloquearPantalla();
			}
        }
		,error:function(e){
			$.rup_messages("msgError", {
				title: $.rup.i18n.app.label.aviso,
				message: $.rup.i18n.app.mensajes.errorGuardandoDatos
			});
			desbloquearPantalla();
			return false;
		}
	});	
}

/**
 * 
 */
function validarCampos(){
	var guardar = true;
	// buscamos en las secciones que no esten deshabilitadas
	$('div[id^=seccion_container_]:not(.ind-respuesta)').each(function(){
		// buscamos en los campos ocultos de los combos los que son obligatorios
		$(this).find("input[id^=campo_niveles_ind_obligatorio-][value='"+auditoriaViewData.activo.si+"']").each(function(){
			var idCampoComboIndOblig = $(this).attr('id');
			var idCampoCombo = idCampoComboIndOblig.substring(idCampoComboIndOblig.indexOf('-')+1,idCampoComboIndOblig.length);
			// miramos si el combo existe y si no esta deshabilitado
			if ($('#campo_combo_niveles-'+idCampoCombo).length && !$('#campo_combo_niveles-'+idCampoCombo).rup_combo("isDisabled")){
				if ("" == $('#campo_combo_niveles-'+idCampoCombo).rup_combo("getRupValue")){
					// si no hay seleccion lo marcamos como erroneo
					$('#label_combo_niveles-'+idCampoCombo).addClass("detalle-auditoria-error");
					guardar = false;
				} else if ($('#label_combo_niveles-'+idCampoCombo).hasClass("detalle-auditoria-error")) {
					// si hay seleccion y tiene error de antes, lo quitamos
					$('#label_combo_niveles-'+idCampoCombo).removeClass("detalle-auditoria-error");
				}
			}
		});
		// buscamos en los campos ocultos de los checks los que son obligatorios
		$(this).find("input[id^=campo_indMarcado_ind_obligatorio-][value='"+auditoriaViewData.activo.si+"']").each(function(){
			var idCampoCheckIndOblig = $(this).attr('id');
			var idCampoCheck = idCampoCheckIndOblig.substring(idCampoCheckIndOblig.indexOf('-')+1,idCampoCheckIndOblig.length);
			// miramos si el check existe y si no esta deshabilitado
			if ($('#check_ind_marcado-'+idCampoCheck).length && "disabled" != $('#check_ind_marcado-'+idCampoCheck).attr("disabled")){
				if (!$('#check_ind_marcado-'+idCampoCheck).prop( "checked")){
					// si no esta checkeado lo marcamos como erroneo
					$('#label_check_ind_marcado-'+idCampoCheck).addClass("detalle-auditoria-error");
					guardar = false;
				} else if ($('#label_check_ind_marcado-'+idCampoCheck).hasClass("detalle-auditoria-error")) {
					// si esta checkeado y tiene error de antes, lo quitamos
					$('#label_check_ind_marcado-'+idCampoCheck).removeClass("detalle-auditoria-error");
				}
			}
		});
	});
	return guardar;
}

/**
 * deshabilitar campos que no pueden ser editados y que no esten deshabilitados
 */
function deshabilitarCamposNoEditables(){
	$('.ind-respuesta').find('input:not(:disabled)').each(function(){
		$(this).attr('disabled','disabled');
		$(this).attr('readonly','readonly');
	});
	$('.ind-respuesta').find('select.rup_combo:not(.ui-selectmenu-disabled.ui-state-disabled)').each(function() {
		$(this).rup_combo('disable');
	});
}

/**
 * inicializar el switch de marcar la auditoria visible
 */
function inicializarSwitchAuditoriaVisible(){
	// si esta creado el input, el estado de la auditoria es sin enviar, con lo que inicializamos el switch
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
        jQuery(element)
        .bootstrapSwitch()
        .bootstrapSwitch('setSizeClass', 'switch-small')
        .bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
        .bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
    });
	// inicializamos el valor del switch con el del campo de estado de auditoria
	// si el estado es sin enviar, lo inicializamos a false, si no (enviada o confirmada) lo inicializamos a true
	$('#auditoria_visible').bootstrapSwitch('setState', auditoriaViewData.estadoAuditoria.sinEnviar != $('#auditoria_datos_form_estado').val());
}

/**
 * muestra el icono de seccion correspondiente comparando la suma con los valores minimos de aprobado y peligro
 */
function mostrarIcono(idSeccion,suma,valorMinAprobado,valorMinPeligro){
	var icono = '';
	if (suma >= valorMinAprobado){
		icono += '<i class="fa fa-check ok-color" aria-hidden="true"></i>';
	} else if (suma >= valorMinPeligro) {
		icono += '<i class="fa fa-exclamation warning-color" aria-hidden="true"></i>';
	} else {
		icono += '<i class="fa fa-times errorColor" aria-hidden="true"></i>';
	}
	$('#campo_suma_icono-'+idSeccion).html(icono);
}


/***********CAMPOS TABLA - INICIO**************** */
/**
 * comprobacion inicial de valor de resultado de auditoria para mostrar el icono correspondiente en tabla de tipo de seccion 1
 */
function comprobarValoracionParaIcono(idSeccion){
	var valorMinAprobado = parseDecimal($('#campo_valor_min_aprobado-'+idSeccion).val());
	var valorMinPeligro = parseDecimal($('#campo_valor_min_peligro-'+idSeccion).val());
	var suma = parseDecimal($('#campo_sumatorio-'+idSeccion).val());
	if(isNaN(suma)){
		suma = 0;
		$('#campo_sumatorio-'+idSeccion).val(suma);
		$('#campo_sumatorio_porc-'+idSeccion).text(suma + " %");
	}
	mostrarIcono(idSeccion,suma,valorMinAprobado,valorMinPeligro);
}
/**
 * (para las secciones de tipo 1)
 * suma los valores de los porcentajes de niveles de calidad de los campos
 */
function actualizarValoresCampos(idSeccion){
	var suma = parseDecimal("0");
	$("#auditoria_seccion_1_"+idSeccion).find("input[id^=campo_niveles_selected_value-]").each(function() {
		var valor =  parseDecimal($(this).val());
		if(!isNaN(valor)){
			// si es valor valido lo sumamos
			suma = suma + valor;
			if(isNaN(suma)){
				suma = 0;
			}
		}
		
	});
	$('#campo_sumatorio-'+idSeccion).val(suma.toFixed(2).replace(".",","));
	$('#campo_sumatorio_porc-'+idSeccion).text($('#campo_sumatorio-'+idSeccion).val() + " %");
	comprobarValoracionParaIcono(idSeccion);
}

/**
 * (para las secciones de tipo 1)
 * al inicializar la rup_table, dejamos la estructura html necesaria para la creacion de los combos
 */
function crearEstructuraComboNivelesCampo(campo, indRespuesta){
	var celda = '<div>';
	celda += '<input id="campo_niveles_ind_obligatorio-'+ campo.idCampo+'" type="hidden" value="'+ campo.indObligatorio+'"> ';
	celda += '<input id="campo_niveles_selected_value-'+ campo.idCampo+'" type="hidden" value="'+ campo.porNivelCalidad+'">';
	celda += '<div class="form-group detalle-auditoria-combo-container">'
	
	celda += '<select id="campo_combo_niveles-'+ campo.idCampo+'" class="form-control" data-idseccion="'+campo.idSeccionPadre+'" data-initialized="false">';
	celda += '<option value="'+ campo.porNivel0+'">'+auditoriaViewData.porNivelCalidadCampo.cero+'</option>';
	celda += '<option value="'+ campo.porNivel1+'">'+auditoriaViewData.porNivelCalidadCampo.uno+'</option>';
	celda += '<option value="'+ campo.porNivel3+'">'+auditoriaViewData.porNivelCalidadCampo.tres+'</option>';
	celda += '<option value="'+ campo.porNivel5+'">'+auditoriaViewData.porNivelCalidadCampo.cinco+'</option>';
	
	celda+= '</select>';
	if(auditoriaViewData.activo.si === campo.indObligatorio && auditoriaViewData.activo.no === indRespuesta){
		celda += '<label id="label_combo_niveles-'+ campo.idCampo+'" for="campo_combo_niveles-'+ campo.idCampo+'" class="control-label detalle-auditoria-obligatorio"> *</label>';
	}
	celda+= '</div>';
	celda+= '</div>';
	return celda;
}

/**
 * (para las secciones de tipo 1)
 * una vez creada la rup_table inicializamos los combos de niveles de calidad de campos
 */
function inicializarCombosNivelesCampo(){
	$("select[id^=campo_combo_niveles-]").each(function(){
		// si el combo no esta inicializado lo inicializamos
		if(!$(this).data("initialized")){
			var idCombo = $(this).attr('id');
			var idCampo = idCombo.substring(idCombo.indexOf('-')+1,idCombo.length);
			$(this).rup_combo({
				rowStriping: true, 
				blank: "", 
				loadFromSelect:true,
				open : function() {
					jQuery('#'+idCombo+'-menu').width(jQuery('#'+idCombo+'-button').innerWidth());
				},
				change: function(){
					var valor = $(this).rup_combo("getRupValue");
					var idSeccion = $(this).data("idseccion");
					$('#campo_niveles_selected_value-'+idCampo).val(valor);
					actualizarValoresCampos(idSeccion);
				}
			});
			if ($('#campo_niveles_selected_value-'+idCampo).val() && $('#campo_niveles_selected_value-'+idCampo).val() !== 'null'){
				$(this).rup_combo("select",$('#campo_niveles_selected_value-'+idCampo).val());
			} else {
				$(this).rup_combo("select","");
			}
			$(this).data('initialized', true); 
		}
	});
}

/**
 * (para las secciones de tipo 2)
 * una vez creada la rup_table inicializamos los checks de ind Marcado de los campos
 */
function crearEstructuraChecksIndMarcado(campo, indRespuesta){
	var celda = '<div>';
	celda += '<input id="campo_indMarcado_ind_obligatorio-'+ campo.idCampo+'" type="hidden" value="'+ campo.indObligatorio+'"> ';
	celda += '<input id="valor_indMarcado-'+ campo.idCampo+'" type="hidden" value="'+ campo.indMarcado+'"> ';
	celda += '<input type="checkbox" id="check_ind_marcado-'+campo.idCampo+'" name="indMarcado" value="S" data-initialized="false">';
	if(campo.indMarcado && campo.indMarcado != 'null'){
		$('#check_ind_marcado-'+campo.idCampo).prop( "checked", auditoriaViewData.activo.si === campo.indMarcado);
	}
	if(auditoriaViewData.activo.si === campo.indObligatorio && auditoriaViewData.activo.no === indRespuesta){
		celda += '<label  id="label_check_ind_marcado-'+ campo.idCampo+'" for="campo_combo_niveles-'+ campo.idCampo+'" class="control-label detalle-auditoria-obligatorio"> *</label>';
	}
	celda += '</div>';
	return celda;
}

/**
 * (para las secciones de tipo 2)
 * una vez creada la rup_table creamos los labels de no/obligatorio
 */
function crearEstructuraLabelsIndMarcado(campo) {
	var mensajeNoOk = campo.notaNoOk ? campo.notaNoOk : '-';
	var mensajeOk = campo.notaOk ? campo.notaOk : '-';
	var celda = '<div>';
	celda += '<span id="label_noOk_desc_ind_marcado-'+ campo.idCampo +'" class="txtTablaTarea errorColor">' + mensajeNoOk + '</span>';
	celda += '<span id="label_ok_desc_ind_marcado-'+ campo.idCampo +'" class="txtTablaTarea ok-color">' + mensajeOk + '</span>';
	return celda;
}

/**
 * (para las secciones de tipo 2)
 * mostrar / ocultar labels de ind marcado ok / noOk
 */
function mostrarOcultarLabelsIndMarcado(marcado, idCampo) {
	if (marcado) {
		$('#label_noOk_desc_ind_marcado-'+ idCampo).hide();
		$('#label_ok_desc_ind_marcado-'+ idCampo).show();
	} else {
		$('#label_noOk_desc_ind_marcado-'+ idCampo).show();
		$('#label_ok_desc_ind_marcado-'+ idCampo).hide();
	}
}

/**
 * (para las secciones de tipo 2)
 * minicializar checks ind marcado
 */
function inicializarChecksIndMarcado(){
	$("input[id^=check_ind_marcado-]").each(function(){
		// utilizamos el initiallized para saber si tiene el valor volcado o no
		if(!$(this).data("initialized")){
			var idCheck = $(this).attr('id');
			var idCampo = idCheck.substring(idCheck.indexOf('-')+1,idCheck.length);
			var valorIndMarcado = $('#valor_indMarcado-' + idCampo).val();
			if(valorIndMarcado && valorIndMarcado != 'null'){
				$(this).prop( "checked", auditoriaViewData.activo.si === valorIndMarcado);
				mostrarOcultarLabelsIndMarcado(auditoriaViewData.activo.si === valorIndMarcado, idCampo);
				anyadirOnchangeACheck($(this));
			}
			$(this).data('initialized', true); 
		}
	});
}


function inicializarObservaciones(){
	$("textarea[id^=campo_observaciones-]").each(function(){
		// utilizamos el initiallized para saber si tiene el valor volcado o no
		$(this).on("change", function() {
			var idCheck = $(this).attr('id');
			var idCampo = idCheck.substring(idCheck.indexOf('-')+1,idCheck.length);	
			if ($("#check_ind_marcado-"+ idCampo).length > 0){
				$("#check_ind_marcado-"+ idCampo).prop("checked", true);
			}
		});
	});
}


/**
 * (para las secciones de tipo 2)
 * onchange de checks para mostrar/ocultar labels asociados a su valor
 */
function anyadirOnchangeACheck(check){
	check.on('change', function() {
		var idCheck = $(this).attr('id');
		var idCampo = idCheck.substring(idCheck.indexOf('-')+1,idCheck.length);
		mostrarOcultarLabelsIndMarcado($(this).prop( "checked"), idCampo);
	});
}

/**
 */
function crearEstructuraCampoObservaciones(campo){
	var celda = '<div>';
	celda += '<textarea id="campo_observaciones-'+ campo.idCampo+'" maxlength="4000" class="form-control" style="width: 100%;">';
	celda += campo.observ ? campo.observ : "";
	celda += '</textarea>';
	celda += '</div>';
										
	return celda;
}

/***********CAMPOS TABLA - FIN**************** */

/**
 * (para las secciones de tipo 1 o 2)
 * inicializar la rup_table, pasandole como parametro el tipo de seccion para ocultar/mostrar las columnas correspondientes
 */
function inicializarTablasSeccion(){
	// inicializamos tablas de seccion de tipo 1 y 2
	$("table[id^=auditoria_seccion_1_],table[id^=auditoria_seccion_2_]").each(function(){
		var idTabla = $(this).attr('id');
		var tipoSeccion = $("#"+idTabla).data("tiposeccion");
		var indRespuesta =$("#"+idTabla).data("indrespuesta");
		$("#"+idTabla).rup_table({
			url: "/aa79bItzulnetWar/auditoria/detalle/camposSeccion",
			colNames: [
				"","","","","",
				$.rup.i18n.app.auditoria.tabla.campos.toUpperCase(),
				"",
				"",
				$.rup.i18n.app.auditoria.tabla.cumplido.toUpperCase(),
				auditoriaViewData.activo.no == indRespuesta?$.rup.i18n.app.auditoria.tabla.observIZO.toUpperCase():""
			],
			colModel: [
				{
					name: "idAuditoria",
					hidden : true
				},
				{
					name: "idSeccionPadre",
					hidden : true
				},
				{
					name: "idCampo",
					hidden : true
				},
				{
					name: "tipoCampo",
					hidden : true
				},
				{
					name: "indObservaciones",
					hidden : true
				},
				{ 	name: "nombreEu", 
					index: "NOMBREEU",
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: false,
					formatter: function (cellvalue, options, rowObject){
						if(cellvalue){
							return "<span class='txtTablaTarea'>" + cellvalue + "</span>";
						}else{
							return "-";
						}
						
					}
				},
				{
					name: "notaNoOk",
					index: "NOTANOOK",
					editable: false,
					hidden: true,
					resizable: true,
					sortable: false,
					formatter: function (cellvalue, options, rowObject){
						if(rowObject && (rowObject.notaNoOk || rowObject.notaOk)){
							return crearEstructuraLabelsIndMarcado(rowObject);
						}else{
							return "";
						}
						
					}
				},
				{
					name: "indMarcado",
					index: "INDMARCADO",
					editable: false,
					align: 'center',
					hidden: true,
					resizable: true,
					sortable: false,
					width:20,
					formatter: function (cellvalue, options, rowObject) {
						// si seccion tipo 2 creamos check, si no no creamos nada porque no se va a mostrar esta columna
						return auditoriaViewData.tipoSeccion.condicion == tipoSeccion ? crearEstructuraChecksIndMarcado(rowObject, indRespuesta) : '';
					}
				},
				{ 	name: "porNivelCalidad", 
					index: "PORNIVELCALIDAD",
					editable: false, 
					hidden: false, 
					resizable: false,
					align: "center", 
					sortable: false,
					width: 70,
					formatter: function (cellvalue, options, rowObject) {
						// si seccion tipo 1 creamos combo, si no no creamos nada porque no se va a mostrar esta columna
						return auditoriaViewData.tipoSeccion.valoracion == tipoSeccion ? crearEstructuraComboNivelesCampo(rowObject,indRespuesta) : '';
						
					}
				},
				{ 	name: "observ", 
					index: "OBSERV",
					editable: true, 
					hidden: false, 
					resizable: true, 
					sortable: false,
					formatter: function (cellvalue, options, rowObject){
						// si seccion tipo 1 y indobservaciones a si, creamos el campo observaciones
						return (auditoriaViewData.tipoSeccion.valoracion == tipoSeccion ||  auditoriaViewData.tipoSeccion.condicion == tipoSeccion)
								&& auditoriaViewData.activo.si == rowObject.indObservaciones ? crearEstructuraCampoObservaciones(rowObject) : '';
						
					}
				},
	        ],
	        model:"AuditoriaCampoSeccionExpediente",
	        multiSort: true,
	        sortname: "IDCAMPO",
	        sortorder: "asc",
	        usePlugins:[
	       		 "fluid",
	       		 "filter",
	       		 "responsive"
	         	]
	     	, primaryKey: ["idCampo"],
			loadOnStartUp: true,
			beforeSelectRow: function(rowid, e) {
			    return false;
			},
			hoverrows:false,
			gridComplete: function () {
				$("#"+idTabla+"_pager").hide();
				if (auditoriaViewData.tipoSeccion.valoracion == tipoSeccion){
					// si tipo seccion 1 inicializamos combos y el icono del total
					inicializarCombosNivelesCampo();
					comprobarValoracionParaIcono($("#"+idTabla).data("seccion"));
				} else if (auditoriaViewData.tipoSeccion.condicion == tipoSeccion){
					// si tipo seccion 2 inicializamos checks y ocultamos/mostramos las columnas necesarias
					inicializarChecksIndMarcado();
					
					$("#"+idTabla).rup_table("hideCol", "porNivelCalidad");
					$("#"+idTabla).rup_table("showCol", "notaNoOk");
					$("#"+idTabla).rup_table("showCol", "indMarcado");
				}
				if (auditoriaViewData.activo.si == indRespuesta){
					$("#"+idTabla).rup_table("hideCol", "observ");
				}
				// deshabilitamos los campos de las secciones no editables
				inicializarObservaciones();
				deshabilitarCamposNoEditables();
			}
		});
	
	});
}

/**
 * (para las secciones de tipo 3)
 * inicializar la rup_table, pasandole como parametro el tipo de seccion 
 */
function inicializarTablasDocumentos(){
	// inicializamos tablas de documentos de tipo 3
	$("table[id^=auditoria_seccion_3_]").each(function(){
		var idTabla = $(this).attr('id');
		var tipoSeccion = $("#"+idTabla).data("tiposeccion");
		var seccion = $("#"+idTabla).data("seccion");
		var indRespuesta =$("#"+idTabla).data("indrespuesta");
		$("#"+idTabla).rup_table({
			url: "/aa79bItzulnetWar/auditoria/detalle/documentosSeccion",
			toolbar:{
				id: idTabla + "_toolbar", 
				defaultButtons:{
					add:false,
					edit:false,
					cancel:false,
					save:false,
					clone:false,
					"delete":false,
					filter:true
				},
				newButtons : [     
					{obj: {
	    		 			i18nCaption: $.rup.i18n.app.boton.adjuntar
	    		 			,css: "fa fa-file-o"
	    					,index: 0
    		 			  },
						  json_i18n : $.rup.i18n.app.simpelMaint,
        		 		  click : 
	       					 function(e){
	        		 			e.preventDefault();
			                    e.stopImmediatePropagation();
								abrirModalAnyadirDocumentoSeccionAuditoria(seccion, idTabla);
	       					}	
    		 		},
					{obj: {
        		 			i18nCaption: $.rup.i18n.app.comun.eliminar
        		 			,css: "fa fa-trash-o"
        					,index: 0
        		 		  },
						  json_i18n : $.rup.i18n.app.simpelMaint,
						  click : 
           					 function(e){
            		 			e.preventDefault();
			                    e.stopImmediatePropagation();
								var selectedRows = $("#"+idTabla).rup_table('getSelectedRows');
								if (isEmpty(selectedRows)){
									$.rup_messages("msgAlert", {
										title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
										message: $.rup.i18n.app.comun.warningSeleccion
									});	
									return false;
								 }
								var laFilaSeleccionada = $("#"+idTabla).rup_table("getRowData", selectedRows[0]);
								 $.rup_messages("msgConfirm", {
									title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
									message: $.rup.i18n.app.mensajes.preguntaEliminarFichero,
									OKFunction: function(){
										var param = {
											idFicheroInterno: laFilaSeleccionada["idFicheroInterno"],
											seccion: seccion,
											idTabla: idTabla
										};
										bloquearPantallaCallbackParams($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"),eliminarDocAuditoria, JSON.stringify(param));
									}
								});
           					}	
    		 		} 
				]
			},
			colNames: [
				"","","","","","",
				$.rup.i18n.app.auditoria.tabla.docsParaProveedor.toUpperCase(),
			],
			colModel: [
				{
					name: "idAuditoria",
					hidden : true
				},
				{
					name: "idSeccion",
					hidden : true
				},
				{
					name: "idFicheroInterno",
					hidden : true
				},
				{
					name: "idCampo",
					hidden : true
				},
				{
					name: "tipoCampo",
					hidden : true
				},
				{
					name: "indObservaciones",
					hidden : true
				},
				{ 	name: "nombre", 
					index: "NOMBREFICHERO",
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: false,
					formatter: function (cellval, options, rowObject){
						if(cellval){
							return mostrarEnlaceDocumento(cellval, options, rowObject);
						}else{
							return "-";
						}
					}
				},
	        ],
	        model:"AuditoriaDocumentoSeccionExpediente",
	        multiSort: true,
	        sortname: "IDFICHERO",
	        sortorder: "asc",
	        usePlugins:[
        			"toolbar",
	       			"fluid",
	       			"filter",
	       			"responsive"
	         	]
	     	, primaryKey: ["idCampo"],
			loadOnStartUp: true,
			gridComplete: function () {
				// deshabilitamos los campos de las secciones no editables
				inicializarObservaciones();
				deshabilitarCamposNoEditables();
				// anyadir funcionalidad de descarga de documentos a enlaces de tabla de documentos
				$('a.descargaDocAuditoria').click(function(event){
					event.preventDefault();	
			    	event.stopImmediatePropagation();
					var elIdDoc =  $(this).data("id");
					descargarDocumentoAuditoria(elIdDoc);
				});
			}
		});
	
	});
}

/**
 * (para las secciones de tipo 3)
 * inicializar dialog de subida de documento
 */
function inicializarDialogDocumentoSeccionAuditoria(){
	$("#documentoSeccionAuditoria_detail_div").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: true,
        width: 950,
        title: $.rup.i18n.app.label.infoDocumento
	});
	$('#documentoSeccionAuditoria_detail_feedback').rup_feedback(opcionesFeedbacks);
}

/**
 * (para las secciones de tipo 3)
 * inicializar contenido de dialog de subida de documento
 */
function abrirModalAnyadirDocumentoSeccionAuditoria(seccion, idTabla){
	$("#documentoSeccionAuditoria_detail_div").rup_dialog("open");
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	$("#documentoSeccionAuditoria_detail_form").rup_form('clearForm', true);
	clearValidation('#documentoSeccionAuditoria_detail_form');		
	$("#documentoSeccionAuditoria_detail_feedback").rup_feedback("close");
   		
	$('#enlaceDescargaDetalle_0').html('');
	$('#pidButton_0').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
	
	$("#nombreFicheroInfo_0").rules("add", "required");
	$("#tituloDocumentoSeccionAuditoria_detail_table").rules("remove", "required");
	
	initDocumentoForm = $("#documentoSeccionAuditoria_detail_form").rup_form("formSerialize");

	$("[id^='pidButton']").unbind("click");
	$("[id^='pidButton']").click(function(event) {
		$('#ficheroAuditoria').click();
	});
	// introducimos valores necesarios para guardado de documento y refresco de tabla correspondiente
    $("#idDocAuditoria").val($('#auditoria_datos_form_id').val());
    $("#idSeccionAuditoria").val(seccion);
    $("#idTablaDocAuditoria").val(idTabla);
	desbloquearPantalla();
}

/**
 * (para las secciones de tipo 3)
 * anyadir funcionalidad a botones de guardar y eliminar de contenido de dialog de subida de documento
 */
function fncAnyadirOnClickABotonesDocumentosSeccion(){
	$("#documentoSeccionAuditoria_detail_button_save").button().click(function(){
		if ($('#tituloDocumentoSeccionAuditoria_detail_table').val()){
			// si se ha introducido titulo al documento, lo validamos
			$('#tituloDocumentoSeccionAuditoria_detail_table').rules("add", {maxlength: 200});
		} else {
			// no se ha introducido titulo al documento, no lo validamos
			$("#tituloDocumentoSeccionAuditoria_detail_table").rules("remove", 'maxlength');
		}
		if ($("#documentoSeccionAuditoria_detail_form").valid()){
			// documento valido --> guardamos		
			$("#documentoSeccionAuditoria_detail_form").submit();
		}
    });

	$("#documentoSeccionAuditoria_detail_link_cancel").button().click(function(){
		if (initDocumentoForm !== $("#documentoSeccionAuditoria_detail_form").rup_form("formSerialize")){
			// hay cambios .-> mostramos dialogo para confirmar
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					setTimeout(function() {
						// cerramos dialogo
						$("#documentoSeccionAuditoria_detail_div").rup_dialog("close");
						eliminarMensajes();
						}, 20);
					
				}
			});
		}else{
			// no ha habido cambios --> cerramos el dialogo directamente
			$("#documentoSeccionAuditoria_detail_div").rup_dialog("close");
		}
    });	
}

/**
 * (para las secciones de tipo 3)
 * inicializar formulario de guardado de documento. El documento ya estaria guardado en el pid y aqui se guarda en tablas correspondientes
 * de documentos(88) y documentos de auditoria(c5)
 */
function fncInicializarFormAnyadirDocumentoAuditoria(){
	$('#documentoSeccionAuditoria_detail_form').rup_form({
		url: "/aa79bItzulnetWar/auditoria/detalle/anyadirDocumento",	
		type: "POST",
		dataType: "json",	
		feedback:$("#documentosexpediente_detail_feedback"),
		beforeSubmit: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
		},	
		success: function(data){
			if (data != null){
				//Utilizamos msgConfirm ya que el evento beforeClose de msgOk no funciona
				 $.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.anadirDoc"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.documentoAnyadido"),
						OKFunction: function(){
							setTimeout(function() {
								$("#documentoSeccionAuditoria_detail_div").rup_dialog("close");
								// cerramos dialogo y refrescamos tabla de documentos
	                          	bloquearPantalla();
								eliminarMensajes();
								// refrescamos tabla de documentos correspondiente
								$("#"+ $("#idTablaDocAuditoria").val()).trigger("reloadGrid");
			        			$("#"+ $("#idTablaDocAuditoria").val()).rup_table("resetSelection");
								desbloquearPantalla();
							}, 30);
							
						}
					 });
				//Retocamos msgConfirm para que sea identico a msgOk
				$(".rup-message .ui-dialog-titlebar-close").hide();
				$(".rup-message .rup-enlaceCancelar").remove();
				$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-ok");
				
				desbloquearPantalla();
				
			}else{
				// mostramos mensaje de error
				$.rup_messages("msgAlert", {
        	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
					message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorDocumentoAnyadido"),
				});	
				desbloquearPantalla();
			}
		},
		error: function(){
			// mostramos mensaje de error general
			$.rup_messages("msgAlert", {
    	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
				message: $.rup.i18n.app.mensajes.errorLlamadaAjax,
			});	
			desbloquearPantalla();
		},
		validate:{ 
			rules:{
				"oid":{ required:true},
				},
			showFieldErrorAsDefault: false,
	        showErrorsInFeedback: true,
	        showFieldErrorsInFeedback: false
		}
	});
}

/**
 * (para las secciones de tipo 3)
 * inicializar formulario de guardado de subida de fichero al pif y pid
 */
function fncInicializarFormSubidaFicheroAuditoria(){
	$('#subidaFicheroPidAuditoria_form').rup_form({
		url: "/aa79bItzulnetWar/ficheros/subidaFichero"
		, dataType: "json"
		, beforeSend: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
			return "skip";
		}
		, success: function(archivoPIF){
			if (archivoPIF.error === null){ //Subida correcta
				$.ajax({
				   	 type: 'POST'
				   	 ,url: '/aa79bItzulnetWar/ficheros/pasoPIFaPID'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(archivoPIF)			
				     ,beforeSend: function () {
				    	 	desbloquearPantalla();
							bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardandoDoc"));
						}
				   	 ,success:function(data){
				   		if (data.error === null){ 
							// volcar documento subido a pid a form oculto de modal
							$('#nombre_0').val(data.nombre);
							$('#nombreFicheroInfo_0').val(data.nombre);
							$('#extensionDoc_0').val(data.extension);
							$('#tamanoDoc_0').val(data.tamano);
							$('#contentType_0').val(data.contentType);
							$('#oidFichero_0').val(data.oid);
							$('#indEncriptado_0').val(data.encriptado);
				   			desbloquearPantalla();
				   			
				   		}else{
				   			desbloquearPantalla();
							// mostramos mensaje de error
							$.rup_messages("msgAlert", {
			        	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
								message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorSubirDocumentoAPID"),
							});	
						}
				   	 }
			   	 	,error: function(){
						// mostramos mensaje de error general
						$.rup_messages("msgAlert", {
			    	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
							message: $.rup.i18n.app.mensajes.errorLlamadaAjax,
						});	
			   	 		desbloquearPantalla();
				   	 }
				 });
			}else{//subida incorrecta
	   			desbloquearPantalla();
	   			// mostramos mensaje de error
				$.rup_messages("msgAlert", {
	    	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
					message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorSubirDocumentoAPIF"),
				});	
			}
			// valor a vacio por si se quiere volver a subir un documento con el mismo nombre
			$("#ficheroAuditoria").val(''); 
		 }
		, error: function(archivoPIF){
			desbloquearPantalla();
			// valor a vacio por si se quiere volver a subir un documento con el mismo nombre
			$("#ficheroAuditoria").val(''); 
			// mostramos mensaje de error
			$.rup_messages("msgAlert", {
		 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
				message: archivoPIF.error,
			});	
		}
	});
}

/**
 * (para las secciones de tipo 3)
 * anyadir onchange a formulario de input de obtener fichero
 */
function fncAnyadirOnChangeSubirFicheroAuditoria(){
	$("#ficheroAuditoria").change(function(){
		if ($("#ficheroAuditoria").val() !== ''){
			if (comprobarExtensionValida($('#ficheroAuditoria').val())){
				$("#subidaFicheroPidAuditoria_form").submit();
	    	}else{ 
				// extension de fichero no valido
				mostrarMensajeFeedback("documentoSeccionAuditoria_detail_feedback",$.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"),"error");
				// valor a vacio por si se quiere volver a subir un documento con el mismo nombre
				$("#ficheroAuditoria").val(''); 
	    		desbloquearPantalla();
	    	}
		}
	});
}

/**
 * (para las secciones de tipo 3)
 * eliminar documento de auditoria
 */
function eliminarDocAuditoria(param){
	// obtenemos datos necesarios para eliminar documento
	var jParams = JSON.parse(param); 
	var jsonObject = {
		idAuditoria: $('#auditoria_datos_form_id').val(),
		idSeccion: jParams.seccion,
		idFicheroInterno : jParams.idFicheroInterno
	};
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/auditoria/detalle/eliminarDocumento'
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(jsonObject) 
        ,async: false 
        ,success:function(){
			bloquearPantalla();
			// refrescamos tabla de documentos correspondiente
        	$("#"+jParams.idTabla).trigger("reloadGrid");
        	$("#"+jParams.idTabla).rup_table("resetSelection");
        	desbloquearPantalla();
        }
		,error: function(error){
			desbloquearPantalla();
			// mostramos mensaje de error
			$.rup_messages("msgAlert", {
    	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_global.error"),
				message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorSubirDocumentoAPIF"),
			});	
	   	 }
	});
}

/**
* comprobar extension de fichero a subir con los permitidos de la aplicacion (11)
*/
function comprobarExtensionValida( nombreFichero){
	tipoErrorDoc = 0;
	var extensionAdjunto = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	var extensionOK = false;
	if(comprobarExtensionConBBDD(extensionAdjunto, "documentoSeccionAuditoria_detail_feedback")){
		extensionOK =  true;
	}else{
		//el documento no esta admitido
		tipoErrorDoc = 2;
	}
	return extensionOK;	
}

// conversion a KB
function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

/**
* deshabilitar campos
*/
function deshabilitarCamposAuditoria(){
	// buscamos secciones que no esten deshabilitadas
	$('div[id^=seccion_container_]:not(.ind-respuesta)').each(function(){
		// buscamos combos para deshabilitarlos
		$(this).find("select[id^=campo_combo_niveles-]").each(function(){
			$(this).rup_combo('disable');
		});
		// buscamos checks para deshabilitarlos
		$(this).find("input[id^=check_ind_marcado-]").each(function(){
			$(this).attr('disabled','disabled');
			$(this).attr('readonly','readonly');
		});
		// buscamos textareas para deshabilitarlos
		$(this).find("textarea[id^=campo_observaciones-],textarea[id^=auditoria_seccion_observ-]").each(function(){
			$(this).attr('disabled','disabled');
			$(this).attr('readonly','readonly');
		});
		// buscamos toolbar de documentos para ocultarlos
		$(this).find("div[id^=auditoria_seccion_][id$=_toolbar]").each(function(){
			$(this).hide();
		});
	});
	$('#auditoria_visible').bootstrapSwitch('toggleDisabled',true, true);
}

function fncComprobarEstadoAuditoria(){
	// TODO GONTZAL_1 //
	// - 2 - que puedan entrar al detalle pero que no puedan introducir datos. Esto es, que entren en modo consulta
	// anyadir la siguiente condicion
	// TODO GONTZAL_1 //
	// si estado de auditoria enviada o confirmada, deshabilitamos combos, checks, textareas
	if(auditoriaViewData.estadoAuditoria.sinEnviar != $('#auditoria_datos_form_estado').val()){
		deshabilitarCamposAuditoria();
	}
	// TODO GONTZAL_1 //
	// primero miramos si es asignador, porque si no lo es, no debe comprobar si el usuario es auditor
	 else if (esAsignador){
		// comprobamos si el usuario conectado no es el auditor
		 if(!usuarioAuditor) {
			// deshabilitamos campos de auditoria y boton guardar si esta visible
			deshabilitarCamposAuditoria();
			// ocultar boton guardar de toolbar
			if ($.rup.lang === "es" && $("[id='detalleAuditoria_toolbar##Guardar']").length){
				$("[id='detalleAuditoria_toolbar##Guardar']").hide();
			} else if ($.rup.lang === "eu" && $("[id='detalleAuditoria_toolbar##Gorde']").length){
				$("[id='detalleAuditoria_toolbar##Gorde']").hide();
			}
		}
	}
	// control de cambios//
	$("#divDetalleAuditoria").find(":input").on("change", function() {
			hayCambios = true;			
	});
	
	desbloquearPantalla();
}

/**
 */
function fncAuditoriaDetalleToolbar(){
	var botonesDetalleToolbar = [
				{
					i18nCaption: $.rup.i18n.app.boton.volver
					,css: "fa fa-arrow-left"
					,click : 
						function(e){
					    	e.preventDefault();
					    	e.stopImmediatePropagation();
					    	if (!hayCambios){
					    		fncVolverDeDetalleAuditoria();
					    	}else{
					    		$.rup_messages("msgConfirm", {
			         				title: $.rup.i18n.app.mensajes.cambiosEnPantalla,
					    			message: $.rup.i18n.app.mensajes.cambiosCategorizacion,
			         				OKFunction: function(){
			         					fncVolverDeDetalleAuditoria();
			         				}
			         			});
					    	}
		                    	
		                }
				}
			];
	// si la auditoria tiene estado sin enviar, mostramos el boton de guardar
	if(auditoriaViewData.estadoAuditoria.sinEnviar == auditoriaViewData.estadoActualAuditoria){
		botonesDetalleToolbar.push(
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
				,click : 
					function(e){
				    	e.preventDefault();
				    	e.stopImmediatePropagation();
						bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"),fncGuardarDatosAuditoria);
            	}
			}
		);
	}
	$("#detalleAuditoria_toolbar").rup_toolbar({
			buttons:botonesDetalleToolbar
		});
}

/**
 */
function fncInicializarAuditoriaDetalleFeedback(){
	$('#detalleAuditoria_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
}

/**
* anyade atributo title con valor del input a los campos generales de auditoria
*/
function fncAnyadirTitleAInputs(){
	$('.detalle-auditoria-form-field input').each(function() {
        $(this).attr("title",$(this).val());
    });
}

/**
* funcion de descarga de documento
*/
function descargarDocumentoAuditoria(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+auditoriaViewData.tipoSubida.docTareas+'/'+idFichero);//88  2
}

/**
* crea enlace de descarga de documento en tabla de documentos
*/
function mostrarEnlaceDocumento(cellval, options, rowObject){
	var nombreFicheroAMostrar = rowObject.nombre;
	if (rowObject.tituloFichero){
		nombreFicheroAMostrar = rowObject.tituloFichero;
	}
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.idFicheroInterno +'" class="form-group col-lg-12 no-padding">';
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" class="document-cast iconSameLine descargaDocAuditoria" data-id="'+ rowObject.idFicheroInterno +'">';
		celda+= nombreFicheroAMostrar + '</a>';
		celda+='</p>';
		celda+='</div>';
	celda+='</div>';
	return celda;
}

jQuery(function($){
	
	fncAuditoriaDetalleToolbar();
	fncInicializarAuditoriaDetalleFeedback();
	inicializarTablasSeccion();
	inicializarTablasDocumentos();
	inicializarDialogDocumentoSeccionAuditoria();
	fncInicializarFormSubidaFicheroAuditoria();
	fncInicializarFormAnyadirDocumentoAuditoria();
	fncAnyadirOnChangeSubirFicheroAuditoria();
	fncAnyadirOnClickABotonesDocumentosSeccion();
	inicializarSwitchAuditoriaVisible();
	fncAnyadirTitleAInputs();
	
	// cuando finalicen todas las llamadas desbloqueamos la pantalla
	llamadasFinalizadas("fncComprobarEstadoAuditoria");
});