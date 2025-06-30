var initForm = '';
var initFormComentarioEntidad = '';

function showDetallesEmpresa(idEmpresaProv,tipoEmpresa){
	$('#divLotes').removeClass('in');
	$('#divLotesDetalleForm').removeClass('in');
	setTimeout(function() {
		$('#divLotes').addClass('collapsed');
		$('#divLotesDetalleForm').addClass('collapsed');
		$('#divLotesDetalle').removeClass('collapsed');
		$('#divLotesDetalle').addClass('in');
		}, 500);
	$('#idEmpresaProv_detail_filter_table').val(idEmpresaProv);
	$('#tipoEmpresaProv_detail_filter_table').val(tipoEmpresa);
	$('#proveedores').rup_table('filter');
	$('#idEmpresaProv_detail_filter').val(idEmpresaProv);
	$('#tipoEmpresaProv_detail_filter').val(tipoEmpresa);
	$('#lotesdetalle').trigger('reloadGrid');
	
	var jsonObject = 
	{
		"codigo": idEmpresaProv,
		"tipo": tipoEmpresa
	};
	
	jQuery.ajax({
		type: "POST",
		url: "../empresasproveedoras/find",
		dataType: "json",
		contentType: 'application/json', 
        data: $.toJSON(jsonObject),
		cache: false,
		success:function(data){
						
			$('#cif_detalle_empresa').val(data.cif);
			
			var nombreEmpresa = $.rup.lang == 'es' ? data.descAmpEs
					: data.descAmpEu;
			
			$('#nombre_detalle_empresa').val(nombreEmpresa);
			if (data.direccion != null){
				$('#calle_detalle_empresa').val(data.direccion.txtCalle);
				$('#portal_detalle_empresa').val(data.direccion.txtPortal);
				$('#escalera_detalle_empresa').val(data.direccion.escalera);
				$('#piso_detalle_empresa').val(data.direccion.piso);
				$('#mano_detalle_empresa').val(data.direccion.mano);
				$('#puerta_detalle_empresa').val(data.direccion.puerta);
				$('#codPostal_detalle_empresa').val(data.direccion.codPostal);
				$('#localidad_detalle_empresa').val(data.direccion.txtLocalidad);
				$('#municipio_detalle_empresa').val(data.direccion.txtMunicipio);
				$('#provincia_detalle_empresa').val(data.direccion.txtProvincia);
			}
			
			$('#situacion_detalle_empresa').val(data.estadoDesc);
			$('#esEmpresaProveedora').val(data.esProveedora);
			
			if ($('#esEmpresaProveedora').val() === '0'){
				$("[id='lotesdetalle_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"label.anadirLote")+"']").button("disable");
				$("[id='lotesdetalle_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"label.modificarLote")+"']").button("disable");
				$("#msgEmpresaNoProveedora").removeClass('oculto');
			} else {
				$("[id='lotesdetalle_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"label.anadirLote")+"']").button("enable");
				$("[id='lotesdetalle_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"label.modificarLote")+"']").button("enable");
				$("#msgEmpresaNoProveedora").addClass('oculto');
			}
			
			if(data.facturable === 'S'){
				$('#facturable_detalle_empresa').bootstrapSwitch('setState', true);
			}else{
				$('#facturable_detalle_empresa').bootstrapSwitch('setState', false);
			}
			
			if(data.iva === 'S'){
				$('#iva_detalle_empresa').bootstrapSwitch('setState', true);
			}else{
				$('#iva_detalle_empresa').bootstrapSwitch('setState', false);
			}
			
		}
	});
	
}

function showLotes(){
	$('#divLotesDetalle').removeClass('in');
	setTimeout(function() {
		$('#divLotesDetalle').addClass('collapsed');
		$('#divLotes').removeClass('collapsed');
		$('#divLotes').addClass('in');
		$("#lotes").trigger('reloadGrid');
		}, 500);
	setFocusFirstInput("#lotes_filter_form");
}

function showLotesDetalleForm(id){
	clearValidation('#lotesdetalleform');
	$('#lotesdetalleform :input:visible:enabled:first').focus();
	$("#lotesdetalleform_feedback").rup_feedback("close");
	$('#idLote_detail_form').val('');
	if(id !== ''){
		jQuery.ajax({
			type: "GET",
			url:"../../lotes/"+id,
			dataType: "json",
			cache: false,
			async: false,
			success:function(data){
				if(data.indTramitacionRapida === 'S'){
					$('#ind_tramitacion_rapida').bootstrapSwitch('setState', true);
				}else{
					$('#ind_tramitacion_rapida').bootstrapSwitch('setState', false);
				}
				crearComboContacto(data,data.empresasProveedoras.codigo,data.empresasProveedoras.tipo);
				// asignamos el id del lote al formulario de busqueda de la tabla de entidades lote y la mostramos
				$('#idLoteEntidadesLote').val(id);
				$('#divTablaEntidadesLote').show();
				$('#entidadesLote').rup_table("filter");
			}
		});
		enableButton("lotesdetalleform_toolbar-rightButtons");
	}else{
		crearComboContacto(null,$('#idEmpresaProv_detail_filter_table').val(),$('#tipoEmpresaProv_detail_filter_table').val());
		$('#idiomasCombo').rup_combo('setRupValue',"1");
		$('#indRecargoApremio_detail_form').bootstrapSwitch('setState', false);
		disableButton("lotesdetalleform_toolbar-rightButtons");
		// ocultar tabla de asignar entidades a lote ya que todavia no hay lote al que asignar
		$('#idLoteEntidadesLote').val('');
		$('#divTablaEntidadesLote').hide();
	}
	$('#cif_detail_form').val($('#cif_detalle_empresa').val());
	$('#empresa_detail_form').val($('#nombre_detalle_empresa').val());
	$('#idEmpresaProv_detail_form').val($('#idEmpresaProv_detail_filter_table').val());
	$('#tipoEmpresaProv_detail_form').val($('#tipoEmpresaProv_detail_filter_table').val());
	$('#empresaProveedora_detail_form').val($('#esEmpresaProveedora').val());
	
	if($('#empresaProveedora_detail_form').val() === '0'){
		$("[id='lotesdetalleform_toolbar##lotesdetalleform_detail_button_save']").button("disable");
		$("input:not(:button)").attr('disabled', 'disabled');
		$("#idiomasCombo").rup_combo("disable");
		if($('#indRecargoApremio_detail_form').val() === 'S'){
			$('#porRecargoApremio_detail_form').attr('disabled', 'disabled');
		}
		var parentIndRecargoApremio = $('#indRecargoApremio_detail_form').parent().parent();
		parentIndRecargoApremio.addClass("disabled");
		
		$("#fechaInicio_detail_form").rup_date("disable");
		$("#fechaFin_detail_form").rup_date("disable");
		
		$("#resolucion_detail_form").attr('disabled', 'disabled');
		$("#idTipoPenalizacion_detail_form").attr('disabled', 'disabled');
		
		$('#contacto_verDetalleLink_div').hide();
	} else {
		$("[id='lotesdetalleform_toolbar##lotesdetalleform_detail_button_save']").button("enable");
		$("input:not(:button)").removeAttr('disabled');
		$("#idiomasCombo").rup_combo("enable");
		if($('#indRecargoApremio_detail_form').val() === 'S'){
			$('#porRecargoApremio_detail_form').removeAttr('disabled');
		}
		var parentIndRecargoApremio = $('#indRecargoApremio_detail_form').parent().parent();
		parentIndRecargoApremio.removeClass("disabled");
		
		$("#fechaInicio_detail_form").rup_date("enable");
		$("#fechaFin_detail_form").rup_date("enable");
		
		$("#resolucion_detail_form").removeAttr('disabled');
		$("#idTipoPenalizacion_detail_form").removeAttr('disabled');
	}
	
	$("#cif_detail_form").attr('disabled', 'disabled');
	$("#empresa_detail_form").attr('disabled', 'disabled');
	$("#importePrevisto_detail_form").attr('disabled', 'disabled');
	//$("#importeRestante_detail_form").attr('disabled', 'disabled');
	$("#importeDisponible_detail_form").attr('disabled', 'disabled');
}

function crearComboContacto(data,codigoEmpresa,tipoEmpresa){
	$('#idiomasCombo').rup_combo("reload");
	
	$("#contacto_nombre_detail_form").rup_combo({
		source : "../../proveedores/" + codigoEmpresa + "/" + tipoEmpresa,
		sourceParam : {
			label : "nombreCompleto",
			value : "dni",
			style : "css"
		},
		blank: "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		},
		onLoadSuccess: function() {
			if (data != null){
				$('#idLote_detail_form').val(data.idLote);
				$('#nombreLote_detail_form').val(data.nombreLote);
				$('#descLoteEu_detail_form').val(data.descLoteEu);
				$('#descLoteEs_detail_form').val(data.descLoteEs);
				if (data.contacto != null && data.contacto.dni != null){
					$('#contacto_nombre_detail_form').rup_combo("setRupValue",data.contacto.dni);
				}
				$('#fechaInicio_detail_form').val(data.fechaInicio);
				$('#fechaFin_detail_form').val(data.fechaFin);
				$('#importeAsignado_detail_form').val(data.importeAsignado);
				$('#importeConsumido_detail_form').val(data.importeConsumido);
				$('#importePrevisto_detail_form').val(data.importePrevisto);
				$('#importeDisponible_detail_form').val(data.importeDisponible);
				$('#idiomasCombo').rup_combo('setRupValue',data.idIdiomaDestino);
				$('#importePalabra_detail_form').val(data.importePalabra);
				$('#porPagoPalConcor8594_detail_form').val(data.porPagoPalConcor8594);
				$('#porPagoPalConcor95100_detail_form').val(data.porPagoPalConcor95100);
				$('#porRecargoFormato_detail_form').val(data.porRecargoFormato);
				$('#porRevision_detail_form').val(data.porRevision);
				$('#porRecargoApremio_detail_form').val(data.porRecargoApremio);
				$('#idTipoPenalizacion_detail_form').val(data.idTipoPenalizacion);
				$('#porPenalizacion_detail_form').val(data.porPenalizacion);
				$('#resolucion_detail_form').val(data.resolucion);
				
				if(data.indRecargoApremio === 'S'){
					$('#indRecargoApremio_detail_form').bootstrapSwitch('setState', true);
				}else{
					$('#indRecargoApremio_detail_form').bootstrapSwitch('setState', false);
				}
				
				if($('#empresaProveedora_detail_form').val() === '0'){
					$('#idiomasCombo').rup_combo('disable');
				}
			}
			
			initForm = $("#lotesdetalleform").rup_form("formSerialize");
		},
		change: function(){					
			if ($('#contacto_nombre_detail_form').rup_combo("getRupValue") != ""){
				$('#contacto_verDetalleLink_div').show();
			} else {
				$('#contacto_verDetalleLink_div').hide();
			}
		}
	});
	
	$('#divLotesDetalle').removeClass('in');
	setTimeout(function() {
		$('#divLotesDetalle').addClass('collapsed');
		$('#divLotesDetalleForm').removeClass('collapsed');
		$('#divLotesDetalleForm').addClass('in');
		}, 500);
	
}

function detalleProveedor(dni){
	var detalle = "&detalle="+dni;
	var destino ="&destino=detalleTrabajador"; 
	window.open(x54j_url+detalle+destino, '_blank');
}

function comprobarCambiosFormulario(){
	if(initForm !== $("#lotesdetalleform").rup_form("formSerialize")){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
			OKFunction: function(){
				initForm = "";
				showDetallesEmpresa($('#idEmpresaProv_detail_form').val(),$('#tipoEmpresaProv_detail_form').val());
			}
		});			
	}else{
		initForm = "";
		showDetallesEmpresa($('#idEmpresaProv_detail_form').val(),$('#tipoEmpresaProv_detail_form').val());
	}
}

function actualizarImportes(){
	if ($('#importeAsignado_detail_form, #importeConsumido_detail_form').valid()) {
		var importeTotal = $("#importeAsignado_detail_form").getImporte();
		var importeConsumido = $("#importeConsumido_detail_form").getImporte();
		var importePrevisto = $("#importePrevisto_detail_form").getImporte();
		var importeRestante = importeTotal - importeConsumido + importePrevisto;
		$("#importeDisponible_detail_form").setImporte(importeRestante);
	}
}

function inicializarValidateEsEntidadAsociada(){
	jQuery.validator.addMethod("esEntidadAsociada", function(valor) {
		if (valor == "") {
			return true
		} else {
			var existe = true;
			jQuery.ajax({
				type: 'POST',
				url: '/aa79bItzulnetWar/administracion/entidadeslote/esEntidadAsociada',
				contentType: 'application/json',
				data: JSON.stringify({codigoCompleto: valor}),
				dataType: 'json',
				async: false, cache: false,
				success : function(data) {
					existe = data;
				}
			});
			return !existe;
		}
	  }
	, $.rup.i18nParse($.rup.i18n.app,"validaciones.existeEntidadAsociadaALote"));
}

function inicializarTablaEntidadesLote(){
	$("#entidadesLote").rup_table({
		url: "/aa79bItzulnetWar/administracion/entidadeslote",
		feedback:{
			id:"lotesdetalleform_feedback"
		},
		toolbar: {
			id: "entidadesLote_toolbar"
			,defaultButtons:{
				add : false
				,edit : false
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : false
			}
			,newButtons : [      
	            {obj : {
	            	id: "asociarEntidad",
					i18nCaption: $.rup.i18nParse($.rup.i18n.app,"boton.asociar")
					,css: "fa fa-file-o"
					,index: 1
					, disabled: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : function(e){
						 $("#entidadesLote").rup_table("newElement");		
						 $(".ui-dialog-titlebar-close").hide();
					}
				}
	            ,{obj : {
		            	id: "desasociarEntidad",
		            	i18nCaption: $.rup.i18nParse($.rup.i18n.app, "boton.desasociar")
		            	,css: "fa fa-trash-o"
	            		,index: 2
						, disabled: false
		            }
		            ,json_i18n : $.rup.i18n.app.simpelMaint
		            ,click : function(e){
		            	if(!$('#entidadesLote').rup_table("isSelected")){
		            		e.preventDefault();
		            		alert($.rup.i18n.app.comun.warningSeleccion);
		            		return false;
		            	}else{
		            		$('#entidadesLote').rup_table("deleteElement");
		            	}
		            }
	       		},{obj : {
		            	id: "anyadirComentarioAEntidad",
		            	i18nCaption: $.rup.i18nParse($.rup.i18n.app, "boton.anyadirComentarioEntidad")
		            	,css: "fa fa-pencil-o"
	            		,index: 2
						, disabled: false
		            }
		            ,json_i18n : $.rup.i18n.app.simpelMaint
		            ,click : function(e){
						bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"), abrirDialogAnyadirComentarioEntidad);
		            }
	       }]
		},
		colNames: ['',
			$.rup.i18nParse($.rup.i18n.app, "label.tipo"),
			$.rup.i18nParse($.rup.i18n.app, "label.entidad")
			],
		colModel: [
			{ 	name: "codigoCompleto", 
				hidden: true
			},
			{ 	name: "tipoDesc", 
				label: "label.tipoExpediente",
				index: "tipoDesc",
				align: "left", 
				width: 10
			},
			{ 	name: "desc"+ $.rup_utils.capitalizedLang(), 
				label: "label.tipoExpediente",
				index: "desc"+ $.rup_utils.capitalizedLang(),
				align: "left", 
				width: 30,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
		],
		model:"EntidadesLote",
		usePlugins:[
			"formEdit",
			"feedback",
			"toolbar",
			"responsive",
			"fluid"
			,"filter"
			],
		primaryKey: "codigoCompleto",
		sortname: "desc"+ $.rup_utils.capitalizedLang(),
		sortorder: "asc",
		multiselection:{headerContextMenu_enabled:true},
		formEdit:{
			detailForm: "#entidadesLote_detail_div",
			validate:{
    			showErrorsInFeedback: true,
    			showFieldErrorAsDefault: true,
    			showFieldErrorsInFeedback: false,
    			rules:{
    				"codigoCompleto":{required: true, esEntidadAsociada: true}
				}
         	},
			addOptions: {
				reloadAfterSubmit: true
			},
			deleteOptions: {
				reloadAfterSubmit: true
			}
		},
		loadOnStartUp : false
	});
	$("#entidadesLote").on("jqGridAddEditAfterShowForm.rupTable.formEditing", function(){
		$('#idLoteEntidadesLoteDetail').val($('#idLoteEntidadesLote').val());
		$('div#entidadesLote_detail_div input[name=tipoEntidad]:first').click();
	});
	$("#entidadesLote").off('dblclick');
}

function inicializarAutoCompleteDetalleEntidadesLote(){
	$('#entidadesLoteIdEntidadSolicitante_detail_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestAlta",
		sourceParam : {
			value: "codigoCompleto",
			label : "desc" + $.rup_utils.capitalizedLang()
		},
		getText: false,
		open : function(event, object, arg) {
			 var tam = parseFloat(jQuery('#entidadesLoteIdEntidadSolicitante_detail_table').css("padding-left"))+ parseFloat(jQuery('#entidadesLoteIdEntidadSolicitante_detail_table').css("padding-right"));
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#entidadesLoteIdEntidadSolicitante_detail_table').innerWidth());
			$("#entidadesLoteIdEntidadSolicitante_detail_table_menu").css("z-index", $("#entidadesLote_detail_div").parent().css("z-index")+1);
			$("#entidadesLoteIdEntidadSolicitante_detail_table_menu").removeClass("ui-front");
		}
	});
	
	$('#entidadesLote_detail_form input[name=tipoEntidad]').change(function(){
		var parametros = $(this).val();
		$("#entidadesLoteIdEntidadSolicitante_detail_table").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#entidadesLoteIdEntidadSolicitante_detail_table").rup_autocomplete("set", "", "");
		$('#entidadesLoteIdEntidadSolicitante_detail_table_label').removeData("tmp.loadObjects.term");
	});
	$('div#entidadesLote_detail_div input[name=tipoEntidad]:first').click();
}

function anyadirFuncionalidadBotonesAnyadirEntidad(){
	// boton cancelar
	$("#entidadesLote_detail_link_cancel").on("click", function(){
		$("#entidadesLote_detail_div").rup_dialog("close");
	});
	
}

function inicializarDialogComentarioEntidad(){
	$("#comentarioEntidad_dialog").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: false,
	    width: "500",
	    title: $.rup.i18nParse($.rup.i18n.app,"boton.anyadirComentarioEntidad"),
	    buttons: [{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"guardar"),
			click: function (event) {
				event.preventDefault();
				event.stopImmediatePropagation();
				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"), guardarComentarioEntidad);
			}
		},
		{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"cancelar"),
			click: function () { 
				comprobarCambiosFormularioComentarioEntidad();
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$('#idEntidadComentario').val($("#entidadesLote").rup_table("getSelectedRows")[0]);
			$('#descEntidadComentario').text($("#entidadesLote").rup_table("getCol", $("#entidadesLote").rup_table("getSelectedRows")[0], "desc"+ $.rup_utils.capitalizedLang()));
			initFormComentarioEntidad = $("#comentarioEntidad_dialog_form").rup_form("formSerialize");
			$('#comentarioEntidad').rules( "add", {  required: true });
		},
		close: function(event, ui) {
			$("#comentarioEntidad_dialog").validate().resetForm();
		},
		beforeClose: function(event, ui){
		}
	});
	$("#comentarioEntidad_dialog_form").valid();
}

function comprobarCambiosFormularioComentarioEntidad(){
	if(initFormComentarioEntidad !== $("#comentarioEntidad_dialog_form").rup_form("formSerialize")){
		$.rup_messages("msgConfirm", {
			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
			message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
			OKFunction: function(){
				initFormComentarioEntidad = "";
				$("#comentarioEntidad_dialog").rup_dialog('close');
			}
		});			
	}else{
		initFormComentarioEntidad = "";
		$("#comentarioEntidad_dialog").rup_dialog('close');
	}
}

function abrirDialogAnyadirComentarioEntidad(){
	if(!$('#entidadesLote').rup_table("isSelected")){
		e.preventDefault();
		alert($.rup.i18n.app.comun.warningSeleccion);
		return false;
	}else{
		// obtener comentario
		$.ajax({
			type: 'POST',
			url: '/aa79bItzulnetWar/administracion/entidadeslote/obtenerComentarioEntidad',
			contentType: 'application/json',
			data: JSON.stringify({codigoCompleto: $("#entidadesLote").rup_table("getSelectedRows")[0]}),
			dataType: 'json',
			async: false, cache: false,
			success : function(data) {
				if (data){
					// tiene comentario
					$('#comentarioEntidad').val(data.comentarioEntidad);
				} else {
					// no tiene comentario
					$('#comentarioEntidad').val('');
				}
				// abrir dialog
				$("#comentarioEntidad_dialog").rup_dialog("open");
				desbloquearPantalla();
			},
			error : function(e){
				// mostrar mensaje error
				$.rup_messages("msgError", {
					title: $.rup.i18n.base.rup_ajax.errorTitle,
					message: $.rup.i18nParse($.rup.i18n.app,"mensajes.obtenerComentarioEntidadError")
				});	
				desbloquearPantalla();
			}
		});
	}
}

function guardarComentarioEntidad(){
	$('#comentarioEntidad').rules( "add", {  required: true });
	// validar formulario
	if(!$("#comentarioEntidad_dialog_form").valid()){
		//comentario no valido
		desbloquearPantalla();
		return false;
	}
		
	// guardar comentario de entidad
	$.ajax({
		type: 'POST',
		url: '/aa79bItzulnetWar/administracion/entidadeslote/anyadirComentarioEntidad',
		contentType: 'application/json',
		data: JSON.stringify({codigoCompleto: $('#idEntidadComentario').val(), comentarioEntidad: $('#comentarioEntidad').val()}),
		dataType: 'json',
		async: false, cache: false,
		success : function(data) {
			if (data){
				// mostrar mensaje ok
				$("#comentarioEntidad_dialog").rup_dialog('close');
				$.rup_messages("msgOK", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.app,"mensajes.comentarioGuardadoOk")
				});
			} else {
				// mostrar mensaje error
				$.rup_messages("msgError", {
					title: $.rup.i18n.base.rup_ajax.errorTitle,
					message: $.rup.i18nParse($.rup.i18n.app,"mensajes.comentarioGuardadoError")
				});	
			}
			desbloquearPantalla();
		},
		error : function(e){
			// mostrar mensaje error
			$.rup_messages("msgError", {
				title: $.rup.i18n.base.rup_ajax.errorTitle,
				message: $.rup.i18nParse($.rup.i18n.app,"mensajes.comentarioGuardadoError")
			});	
			desbloquearPantalla();
		}
	});
}

jQuery(function($) {

	$("#lotes").rup_table({
		url : "../empresasproveedoras",
		colNames : [ 
			"", 
			txtCodEmpresa, 
			txtCif, 
			txtNombre,
			txtLotesVigentes
			],
		colModel : [
			{
				name : "tipo",
				label : "",
				index: "TIPO",
				align : "",
				width : 0,
				editable : false,
				fixed : false,
				hidden : true,
				resizable : false,
				sortable : false
			},
			{
				name : "codigo",
				label : "label.codEmpresa",
				index: "CODIGO",
				align : "right",
				width : 150,
				editable : false,
				fixed : false,
				formatter: function (cellvalue, options, rowObject) {
					return '<b><a href="#" onclick="showDetallesEmpresa(' + rowObject.codigo + ',\'' + rowObject.tipo + '\')">' + cellvalue + '</a></b>';
				}
			}, 
			{
				name : "cif",
				label : "label.cif",
				index: "CIF",
				align : "",
				width : 150,
				editable : false,
				fixed : false
			},
			{
				name : $.rup.lang == 'es' ? "descAmpEs"
						: "descAmpEu",
				label : "label.nombre",
				index: $.rup.lang == 'es' ? "DESCAMPES"
						: "DESCAMPEU",
				align : "",
				width : 150,
				editable : false,
				fixed : false
			},
			{
				name : "lotesVigentesDesc",
				label : "label.lotesVigentes",
				index: "LOTESVIGENTESDESC",
				align : "",
				width : 150,
				editable : false,
				fixed : false
			}
		],
		model : "EmpresasProveedoras",
		usePlugins : [ 
			/*"toolbar",*/
			"responsive", 
			"fluid", 
			"filter"
			],
		primaryKey : "codigo,tipo",
		sortname : "codigo,tipo",
		sortorder : "asc",
		loadOnStartUp : true
	});
	
	$("#proveedores").rup_table({
		url : "/aa79bItzulnetWar/proveedores",
		colNames : [ 
			"",
			txtNif, 
			txtNombreAp
			],
		colModel : [ 
			{
				name : "dni",
				label : "",
				index: "DNI",
				align : "",
				width : 0,
				editable : false,
				fixed : false,
				hidden : true,
				resizable : false,
				sortable : false
			},
			{
				name : "dniCompleto",
				label : "label.nif",
				index: "DNICOMPLETO",
				align : "left",
				width : 50,
				editable : false,
				fixed : false,
				formatter: function (cellvalue, options, rowObject) {
					return '<a href="#" onclick="detalleProveedor(\'' + rowObject.dni + '\')">' + cellvalue + '</a>';
				}
			}, 
			{
				name : "nombreCompleto",
				label : "label.nombreapellidos",
				index: "NOMBRECOMPLETO",
				align : "center",
				width : 50,
				editable : false,
				fixed : false
			}
		],
		model : "Proveedor",
		usePlugins : [ 
			"toolbar",
			"responsive", 
			"fluid",
			"filter"
			],
		primaryKey : "dni",
		sortname : "dni",
		sortorder : "asc",
		loadOnStartUp : false
	});
	
	$("#lotesdetalle").rup_table({
		url : "../../lotes",
		toolbar: {
			 id: "lotesdetalle_toolbar"
			 ,newButtons : [      
			       {obj : {
						i18nCaption: $.rup.i18n.app.boton.volver
						,css: "fa fa-arrow-left"
						,index: 0
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						 function(){
						 	showLotes();
						}
					},
					{obj : {
						i18nCaption:  $.rup.i18nParse($.rup.i18n.app,"label.anadirLote")
						,css: "fa fa-file-o"
						,index: 0
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						 function(){
						 	showLotesDetalleForm('');
						}
					}
				]
			 ,defaultButtons:{
				 add : false
				,edit : false
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : true
			 }
			},
		colNames : [ 
			txtCod,
			txtNombre, 
			txtFechaIni, 
			txtFechaFin,
			txtImporteAsig,
			txtImporteConsumido,
			txtPrevisto,
			txtDisponible
			],
		colModel : [ 
			{
				name : "idLote",
				label : "label.id",
				index: "IDLOTE",
				align : "center",
				width : 50,
				formatter: function (cellvalue, options, rowObject) {
				    return '<a href="#" onclick="showLotesDetalleForm(' + rowObject.idLote + ')">' + cellvalue + '</a>';
				}
			},
			{
				name : "nombreLote",
				label : "label.nombre",
				index: "NOMBRELOTE",
				align : "left",
				width : 50
			}, 
			{
				name : "fechaInicio",
				label : "label.fechaIni",
				index: "FECHAINICIO",
				align : "center",
				width : 50
			} ,
			{
				name : "fechaFin",
				label : "label.fechaFin",
				index: "FECHAFIN",
				align : "center",
				width : 50
			} ,
			{
				name : "importeAsignado",
				label : "label.importeAsignado",
				index: "IMPORTEASIGNADO",
				align : "right",
				width : 50
			} ,
			{
				name : "importeConsumido",
				label : "label.importeConsumido",
				index: "IMPORTECONSUMIDO",
				align : "right",
				width : 50
			} ,
			{
				name : "importePrevisto",
				label : "label.importePrevisto",
				index: "IMPORTEPREVISTO",
				align : "right",
				width : 50
			} ,
			{
				name : "importeDisponible",
				label : "label.importeDisponible",
				index: "IMPORTEDISPONIBLE",
				align : "right",
				width : 50
			} 
		],
		model : "Lotes",
		usePlugins : [ 
			"toolbar",
			"responsive", 
			"fluid",
			"filter"
			],
		primaryKey : "idLote",
		sortname : "idLote",
		sortorder : "asc",
		loadOnStartUp : false,
		formEdit:{
        	detailForm: "#lotesdetalle_detail_div"
		}
	});
	
	//traemos delante el botón de volver
	jQuery('#lotesdetalle_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	//Arreglo para fluid en ie
	jQuery('.ui-jqgrid-hbox').css({width:'100%'});
	
	//Formulario detalle
	$("#lotesdetalleform").rup_form({
		url: "../../lotes",
		dataType: "json",
		feedback:$("#lotesdetalleform_feedback"),
		type: "POST",
		success: function(){
			showDetallesEmpresa($('#idEmpresaProv_detail_form').val(),$('#tipoEmpresaProv_detail_form').val());
		},
		validate:{ 
			rules:{
				"nombreLote":{ required: true, maxlength: 50 },
				"descLoteEs":{ required: true, maxlength: 250 },
				"descLoteEu":{ required: true, maxlength: 250 },
				"fechaInicio":{ required: true, date: true },
				"fechaFin":{ required: true, date: true, fechaHastaMayor: "fechaInicio" },
				"importeAsignado":{ required: true, customDecimal: ["0", "9.999.999,99", 0, 9999999.99] },
				"importeConsumido":{ customDecimal: ["0", "9.999.999,99", 0, 9999999.99] },
//				"importeAsignado":{ required: true, importe: [7, 2]},
//				"importeConsumido":{ importe: [7, 2] },
				"idIdiomaDestino":{ required: true },
				"importePalabra":{ required: true, customDecimal: ["0", "9,99999", 0, 9.99999] },
//				"importePalabra":{ required: true, importe: [1, 5] },
				"porPagoPalConcor8594":{ required: true, integer: true, range: [0, 100] },
				"porPagoPalConcor95100":{ required: true, integer: true, range: [0, 100] },
				"porRevision":{ required: true, integer: true, range: [0, 100] },
				"porRecargoFormato":{ required: true, integer: true, range: [0, 100] },
				"indRecargoApremio":{ required: true },
				"porRecargoApremio":{ integer: true, range: [0, 100] },
				"idTipoPenalizacion":{ required: true },
				"porPenalizacion":{ required: true, integer: true, range: [0, 100] },
				"resolucion":{ maxlength: 4000 }
				},
				showFieldErrorAsDefault: false,
		        showErrorsInFeedback: true,
		        showFieldErrorsInFeedback: false
		        , liveCheckingErrors: false
		}
	});
	
	$('#lotesdetalleform_feedback').rup_feedback({
		block : false
	});
	
	
	$("#lotesdetalleform_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
	                    e.preventDefault();
	                    e.stopImmediatePropagation();
	                    comprobarCambiosFormulario();
	                }
			},
			
			{
				id:"lotesdetalleform_detail_button_save",
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save")
				,css : "fa fa-floppy-o"
				,click : 
					function(event){
						event.preventDefault();
						event.stopImmediatePropagation();
						$("#lotesdetalleform").submit();
					}
			},
			{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
				buttons:[
					{ i18nCaption: $.rup.i18n.app.tabla.pdf
						,css: "fa fa-file-pdf-o"
							,right:true
							,click :
								function(){
									var idLote = $('#idLote_detail_form').val();
									window.location.href="/aa79bItzulnetWar/lotes/generarPdf/"+idLote;
								}
					}
				 ]},
				 {
						i18nCaption: $.rup.i18n.app.boton.albaranes
						 , right:true
						,css: "fa fa-file"
						,click : 
							function(event){
								event.preventDefault();
								event.stopImmediatePropagation();
							 	var idEmp = $('#idEmpresaProv_detail_form').val();
							 	var idLote = $('#idLote_detail_form').val();
							 	window.open("/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/albaranes/consulta?idemp="+idEmp+"&idlote="+idLote);
							}
					},
		]
	});
	
	//traemos delante el botón de volver
	jQuery('#lotesdetalleform_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	
	$("#lotesdetalleform_detail_link_cancel").click(function(){
		comprobarCambiosFormulario();
    });
	
	$('#importeAsignado_detail_form').change(actualizarImportes);
	$('#importeConsumido_detail_form').change(actualizarImportes);
	
	fnFechaDesdeHasta("fechaInicio_detail_form", "fechaFin_detail_form");
	
	var lotesVigentesOption = {
			loadFromSelect: true
			,width: "100"
			,ordered: false	
			,rowStriping: true
		};
	jQuery('#idLote_filter_table').rup_combo(lotesVigentesOption);
	
	crearComboIdiomaDestino('idiomasCombo');
	
	$("#idEmpresaProv_filter_table").rup_combo({
		source : "../empresasproveedoras/suggest",
		sourceParam : {
			label : $.rup.lang === 'es' ? "descAmpEs"
					: "descAmpEu",
			value : "codigo",
			style : "css"
		},
		blank: "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
	});
	
	$("#contacto_verDetalleLink").on("click", function (){
		detalleProveedor($('#contacto_nombre_detail_form').rup_combo("getRupValue"));
	});
		
	$('#indRecargoApremio_detail_form').on('change', function(event, state) {
		
		if($('#empresaProveedora_detail_form').val() !== '0'){
			if (event.target.checked){
				$("#porRecargoApremio_detail_form").rules("add", "required");
				$("#porRecargoApremio_detail_form").removeAttr('disabled');
			} else {
				$("#porRecargoApremio_detail_form").rules("remove", "required");
				$("#porRecargoApremio_detail_form").val("");
				$("#porRecargoApremio_detail_form").attr('disabled','disabled');
			}
		}
		
	});
	
	$("#linkGestionRoles").on("click", function (){
		window.open(x54j_url+"&destino=busquedaTrabajador", '_blank');
	});
	
	inicializarValidateEsEntidadAsociada();
	inicializarTablaEntidadesLote();
	inicializarAutoCompleteDetalleEntidadesLote();
	anyadirFuncionalidadBotonesAnyadirEntidad();
	inicializarDialogComentarioEntidad();
	
	setFocusFirstInput("#lotes_filter_form");
	
});