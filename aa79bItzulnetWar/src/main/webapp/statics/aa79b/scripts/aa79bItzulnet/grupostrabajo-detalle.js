var datosFormulario = "";
var valorEstadoInicial = "";


var configCombo = {
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			var id = $(this).attr("id");
	        $("#"+id+"-menu").width($("#"+id+"-button").width());
	    }
	};

jQuery(function($){
	
	var id = $("#id_detail_table").val();
	var esAlta = id == "";
	var desactivarEntidades = false;
	var desactivarTraductores = false;
	var tablasCreadas = false;
	var comboInterpretes = true;
	
	function fncVolver() {
		$("#dniResponsable_filter_table").rup_combo("reload");
		$("#codTrabajador_filter_table").rup_combo("reload");
		$("#grupostrabajo").rup_table("reloadGrid", true);
		goTo($("#divDetalle"), $("#divGruposTrabajo"));

		//Destruimos las tablas para que no den problemas con los dialogos al volver a entrar
	 	if(	$("#entidades_detail_div").parent().is("[role=dialog]")){
 			$("#entidades_detail_div").rup_dialog('destroy');
 		}
	 	if(	$("#traductores_detail_div").parent().is("[role=dialog]")){
	 		$("#traductores_detail_div").rup_dialog('destroy');
	 	}
 		jQuery("#divDetalle").empty();
 		$("#entidades").jqGrid('GridUnload');
 		$("#traductores").jqGrid('GridUnload');
	}
	
	$('#detalle_feedback').rup_feedback({
		block : false
	});
	
	$("#detalle_toolbar").rup_toolbar({
		buttons : [ 
			{
				id : "volver",
				i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.volver"),
				css : "fa fa-arrow-left",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					if (datosFormulario != $("#detalle_form").serialize()) {
						$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
							OKFunction: function(){
								fncVolver();
							},
							CANCELFunction: function(event, ui){}
						});	
					} else {
						fncVolver();
					}
				}
			},
			{
				id : "guardar",
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
				css : "fa fa-floppy-o",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					
					if (valorEstadoInicial === "B" && $("#estado_detail_table").rup_combo('getRupValue') === "A"){
						$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.app,"rup_table.deleteEntidades"),
							OKFunction: function(){
								jQuery.ajax({
									type: 'POST',
									url: '/aa79bItzulnetWar/administracion/entidadesgrupostrabajo/removeWhereID/'+$("#id_detail_table").val(),
									contentType: 'application/json',
									dataType: 'json',
									async: false, cache: false
								});
								
								$("#detalle_form").submit();
							},
							CANCELFunction: function(){
								$("#estado_detail_table").rup_combo('setRupValue',"B");
							}
						});
					} else {
						var error = validarDatosDetalle();
						if (!error && $("#detalle_form").valid()){
							$("#detalle_form").submit();
						}
					}
					
				}
			}
		]
	});
	
	function validarDatosDetalle(){
		var error = false;
		eliminarMensajesValidacion();
		if ($("#idTipoExpediente_detail_table").rup_combo('getRupValue') !== "0" 
			&& !$("#indBopv_detail_table").bootstrapSwitch("state") ){
			
			var palabrasDesde = $("#palabrasDesde_detail_table").val();
			var palabrasHasta = $("#palabrasHasta_detail_table").val();
			var msgRequerido = $.rup.i18nParse($.rup.i18n.base,"rup_validate.messages.required");
			var msgMayorQue = $.rup.i18nParse($.rup.i18n.app,"validaciones.mayorQue");
			
			if (palabrasDesde === ""){
				error = true;
				$("#palabrasDesde_detail_table").addClass('error');
				$("#palabrasDesdeDiv").append('<label class="error" id="palabrasDesde_detail_table-error" for="palabrasDesde_detail_table">'+ msgRequerido  +'</label>');
				$("#palabrasDesde_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			}
			if (palabrasHasta === ""){
				error = true;
				$("#palabrasHasta_detail_table").addClass('error');
				$("#palabrasHastaDiv").append('<label class="error" id="palabrasHasta_detail_table-error" for="palabrasHasta_detail_table">'+ msgRequerido  +'</label>');
				$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			}
			
			if(palabrasDesde !== "" && palabrasHasta !== "" && parseInt(palabrasDesde) > parseInt(palabrasHasta)){
				error = true;
				$("#palabrasHasta_detail_table").addClass('error');
				$("#palabrasHastaDiv").append('<label class="error" id="palabrasHasta_detail_table-error" for="palabrasHasta_detail_table">'+ msgMayorQue  +'</label>');
				$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			}
		}
		
		return error;
	}
	
	$("#detalle_form").rup_form({
		url: "/aa79bItzulnetWar/administracion/grupostrabajo"
		, type: "PUT"
		, feedback: $("#detalle_feedback")
		, dataType: 'json'
		, contentType: 'application/json'
		, success: function(xhr) {
			$("#detalle_feedback").rup_feedback('option',{delay:5000, gotoTop:true});
			$("#detalle_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), 'ok');
			
			$("#id_detail_table").val(xhr.id);
			$("#fechaAlta_detail_table").val(xhr.fechaAlta);
			$("#fechaBaja_detail_table").val(xhr.fechaBaja);
			$("#idGrupoTraductores").val(xhr.id);
			$("#idGrupoEntidades").val(xhr.id);
			$("#divTablas").addClass("in");
			if(!tablasCreadas) {
				crearTablas();
			} else {
				$("#entidades").rup_table("filter", true);
				$("#traductores").rup_table("filter", true);
			}
			
			//Quitamos la validación en modo edición
//			$("#idTipoExpediente_detail_table").rules("remove", "existeInterpretacion");
			$("#idTipoExpediente_detail_table").rup_combo("disable");

			$("#palabrasDesde_detail_table").rules("remove", "required");
			$("#palabrasHasta_detail_table").rules("remove", "required");
			datosFormulario = $("#detalle_form").serialize();
		}
		, error: function() {
			$("#detalle_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"), 'error');
		}
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			ignore: "",
			rules: {
				"idTipoExpediente": {required: true, existeInterpretacion: true}
				, "descEu": {required: true}
				, "responsable.dni": {required: true}
				, "palabrasDesde": {required: true, digits: true}
				, "palabrasHasta": {required: true, digits: true, mayorQue: "palabrasDesde"}
				, "_indBopv": {existeBOPV: true}
			}
		}
	});
	
	
	jQuery.validator.addMethod("existeInterpretacion", function(valor) {
		if (valor != 0) {
			return true
		} else {
			var existe = true;
			jQuery.ajax({
				type: 'POST',
				url: '/aa79bItzulnetWar/administracion/grupostrabajo/existeInterpretacion',
				contentType: 'application/json',
				data: JSON.stringify({id: id}),
				dataType: 'json',
				async: false, cache: false,
				success : function(data) {
					existe = data;
				}
			});
			return !existe;
		}
	  }
	, $.rup.i18nParse($.rup.i18n.app,"validaciones.existeInterpretacion"));
	
	jQuery.validator.addMethod("existeBOPV", function(valor) {
		if (!$("#indBopv_detail_table").bootstrapSwitch("state")) {
			return true;
		} else {
			var existe = true;
			jQuery.ajax({
				type: 'POST',
				url: '/aa79bItzulnetWar/administracion/grupostrabajo/existeBOPV',
				contentType: 'application/json',
				data: JSON.stringify({id: id}),
				dataType: 'json',
				async: false, cache: false,
				success : function(data) {
					existe = data;
				}
			});
			return !existe;
		}
	}
	, $.rup.i18nParse($.rup.i18n.app,"validaciones.existeBOPV"));
	
	$("#idTipoExpediente_detail_table").rup_combo($.extend(true, {}, configCombo, {change: function(event, opcion) {
		if (opcion.value === "0") {
			$("#indBopv_detail_table").bootstrapSwitch("setDisabled", true).bootstrapSwitch('setState', false);
			$("#palabrasDesde_detail_table").attr("readonly", true).val("");
			$("#palabrasHasta_detail_table").attr("readonly", true).val("");
			$("#palabrasDesde_detail_table").rules("remove", "required");
			$("#palabrasHasta_detail_table").rules("remove", "required");
			$("label[for=palabrasDesde_detail_table]").removeClass("asteriscoDosPuntos").addClass("dosPuntos");
			$("label[for=palabrasHasta_detail_table]").removeClass("asteriscoDosPuntos").addClass("dosPuntos");
			$("#palabrasDesde_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			desactivarEntidades = true;
			comboInterpretes = true;
		} else {
			$("#indBopv_detail_table").bootstrapSwitch("setDisabled", false);
			comboInterpretes = false;
			
			if (!$("#indBopv_detail_table").bootstrapSwitch("state")){
				$("#palabrasDesde_detail_table").attr("readonly", false);
				$("#palabrasHasta_detail_table").attr("readonly", false);
				$("#palabrasDesde_detail_table").rules("add", "required");
				$("#palabrasHasta_detail_table").rules("add", "required");
				$("label[for=palabrasDesde_detail_table]").removeClass("dosPuntos").addClass("asteriscoDosPuntos");
				$("label[for=palabrasHasta_detail_table]").removeClass("dosPuntos").addClass("asteriscoDosPuntos");
				$("#palabrasDesde_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
				$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
				desactivarEntidades = false;
			}else{
				$("#palabrasDesde_detail_table").attr("readonly", true).val("");
				$("#palabrasHasta_detail_table").attr("readonly", true).val("");
				$("#palabrasDesde_detail_table").rules("remove", "required");
				$("#palabrasHasta_detail_table").rules("remove", "required");
				$("label[for=palabrasDesde_detail_table]").removeClass("asteriscoDosPuntos").addClass("dosPuntos");
				$("label[for=palabrasHasta_detail_table]").removeClass("asteriscoDosPuntos").addClass("dosPuntos");
				$("#palabrasDesde_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
				$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
				$("[id=entidades_toolbar##asociarEntidad]").button("disable");
				$("[id=entidades_toolbar##desasociarEntidad]").button("disable");
				desactivarEntidades = true;
				$("#msgEntidadesObligatorias").addClass('oculto');
			}
			
			
		}
	}}));
	$("#estado_detail_table").rup_combo({
			loadFromSelect: true
			,width: "100%"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").width());
		    }
	});
	$("#dniResponsable_detail_table").rup_combo(configCombo);
	
	$("#indBopv_detail_table").bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOffLabel', $.rup.i18nParse($.rup.i18n.app, "comun.no"))
	.bootstrapSwitch('setOnLabel',  $.rup.i18nParse($.rup.i18n.app, "comun.si"));
	
	$("#indBopv_detail_table").on("change", function(event){
		if ($("#indBopv_detail_table").bootstrapSwitch("state")){
			$("#palabrasDesde_detail_table").attr("readonly", true).val("");
			$("#palabrasHasta_detail_table").attr("readonly", true).val("");
			$("#palabrasDesde_detail_table").rules("remove", "required");
			$("#palabrasHasta_detail_table").rules("remove", "required");
			$("label[for=palabrasDesde_detail_table]").removeClass("asteriscoDosPuntos").addClass("dosPuntos");
			$("label[for=palabrasHasta_detail_table]").removeClass("asteriscoDosPuntos").addClass("dosPuntos");
			$("#palabrasDesde_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			$("[id=entidades_toolbar##asociarEntidad]").button("disable");
			$("[id=entidades_toolbar##desasociarEntidad]").button("disable");
			desactivarEntidades = true;
			$("#msgEntidadesObligatorias").addClass('oculto');
		} else {
			$("#palabrasDesde_detail_table").attr("readonly", false);
			$("#palabrasHasta_detail_table").attr("readonly", false);
			$("#palabrasDesde_detail_table").rules("add", "required");
			$("#palabrasHasta_detail_table").rules("add", "required");
			$("label[for=palabrasDesde_detail_table]").removeClass("dosPuntos").addClass("asteriscoDosPuntos");
			$("label[for=palabrasHasta_detail_table]").removeClass("dosPuntos").addClass("asteriscoDosPuntos");
			$("#palabrasDesde_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			$("#palabrasHasta_detail_table-error").removeClass("asteriscoDosPuntos").removeClass("dosPuntos");
			$("[id=entidades_toolbar##asociarEntidad]").button("enable");
			$("[id=entidades_toolbar##desasociarEntidad]").button("enable");
			desactivarEntidades = false;
			if ($("#entidades").rup_table("getGridParam","reccount")== 0){
				//comprobamos si la tabla tiene datos
				$("#msgEntidadesObligatorias").removeClass('oculto');
			}
		}
	});
	
function crearTablas() {
	$("#traductores").rup_table({
		url: "../trabajadoresgrupostrabajo",
		feedback:{
			id:"detalle_feedback"
		},
		toolbar: {
			 id: "traductores_toolbar"
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
					i18nCaption: $.rup.i18nParse($.rup.i18n.app,"boton.asociar")
					,css: "fa fa-file-o"
					,index: 1
					, disabled: desactivarTraductores
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : function(e){
						 $("#traductores").rup_table("newElement");		
						 $(".ui-dialog-titlebar-close").hide();
					}
				}
	            ,{obj : {
	            		i18nCaption: $.rup.i18nParse($.rup.i18n.app, "boton.desasociar")
	            		,css: "fa fa-trash-o"
	            		,index: 2
						, disabled: desactivarTraductores
		            }
		            ,json_i18n : $.rup.i18n.app.simpelMaint
		            ,click : function(e){
		            	if(!$('#traductores').rup_table("isSelected")){
		            		e.preventDefault();
		            		alert($.rup.i18n.app.comun.warningSeleccion);
		            		return false;
		            	}else{
		            		$('#traductores').rup_table("deleteElement");
		            		$("#traductores").rup_table("reloadGrid", true);
		            	}
		            }
	       }]
		},
		colNames: ['',
			$.rup.i18nParse($.rup.i18n.app, "label.dni"),
			$.rup.i18nParse($.rup.i18n.app, "label.apellidosNombre")
		],
		colModel: [
			{ 	name: "codigoCompleto"
				, hidden: true
			},
			{ 	name: "dniCompleto", 
			 	label: "label.tipoExpediente",
			 	index: "dniCompleto",
				align: "left", 
				width: 10
			},
			{ 	name: "nombreCompleto", 
			 	label: "label.descripcion",
			 	index: "nombreCompleto",
				align: "left", 
				width: 20,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
		],
		model:"TrabajadoresGruposTrabajo",
		usePlugins:[
			"formEdit",
			"feedback",
			"toolbar",
//			"contextMenu",
			"responsive",
			"fluid"
			,"filter"
//			, "multiselection"
		 	],
		primaryKey: "codigoCompleto",
		sortname: "dniCompleto",
		sortorder: "asc",
		width:"600px",
		multiselection:{headerContextMenu_enabled:true},
		formEdit:{
			detailForm: "#traductores_detail_div",
			validate:{
    			showErrorsInFeedback: true,
    			showFieldErrorAsDefault: true,
    			showFieldErrorsInFeedback: false,
    			ignore: "",
    			rules:{
    				"dni":{required: true, existeTraductor: true}
				}
         	},
			addOptions: {
				reloadAfterSubmit: true
			},
			deleteOptions: {
				reloadAfterSubmit: true
			}
		},
		loadComplete: function (data){
			if (data.records == 0){
				$("#msgTraductoresObligatorios").removeClass('oculto');
			}else{
				$("#msgTraductoresObligatorios").addClass('oculto');
			}
		}
	});
	
	$("#entidades").rup_table({
		url: "../entidadesgrupostrabajo",
		feedback:{
			id:"detalle_feedback"
		},
		toolbar: {
			id: "entidades_toolbar"
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
					, disabled: desactivarEntidades
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : function(e){
						 $("#entidades").rup_table("newElement");		
						 $(".ui-dialog-titlebar-close").hide();
					}
				}
	            ,{obj : {
		            	id: "desasociarEntidad",
		            	i18nCaption: $.rup.i18nParse($.rup.i18n.app, "boton.desasociar")
		            	,css: "fa fa-trash-o"
	            		,index: 2
						, disabled: desactivarEntidades
		            }
		            ,json_i18n : $.rup.i18n.app.simpelMaint
		            ,click : function(e){
		            	if(!$('#entidades').rup_table("isSelected")){
		            		e.preventDefault();
		            		alert($.rup.i18n.app.comun.warningSeleccion);
		            		return false;
		            	}else{
		            		$('#entidades').rup_table("deleteElement");
		            	}
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
		model:"EntidadesGruposTrabajo",
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
			detailForm: "#entidades_detail_div",
			validate:{
    			showErrorsInFeedback: true,
    			showFieldErrorAsDefault: true,
    			showFieldErrorsInFeedback: false,
    			rules:{
    				"codigoCompleto":{required: true, existeEntidad: true}
				}
         	},
			addOptions: {
				reloadAfterSubmit: true
			},
			deleteOptions: {
				reloadAfterSubmit: true
			}
		},
		loadComplete: function (data){
			if ($("#idTipoExpediente_detail_table").val() == tipoExpedienteTradRev){	
				if (data.records == 0){
					if (!$("#indBopv_detail_table").bootstrapSwitch("state")){
						$("#msgEntidadesObligatorias").removeClass('oculto');
					}else{
						$("#msgEntidadesObligatorias").addClass('oculto');
					}
				}else{
					$("#msgEntidadesObligatorias").addClass('oculto');
				}
			}else{
				$("#msgEntidadesObligatorias").addClass('oculto');
			}
		}
	});
	
	jQuery.validator.addMethod("existeEntidad", function(valor) {
		if (valor == "") {
			return true
		} else {
			var existe = true;
			jQuery.ajax({
				type: 'POST',
				url: '/aa79bItzulnetWar/administracion/entidadesgrupostrabajo/existeEntidad',
				contentType: 'application/json',
				data: JSON.stringify({idGrupo: $("#idEntidades").val(), codigoCompleto: valor}),
				dataType: 'json',
				async: false, cache: false,
				success : function(data) {
					existe = data;
				}
			});
			return !existe;
		}
	  }
	, $.rup.i18nParse($.rup.i18n.app,"validaciones.existeEntidad"));
	
	jQuery.validator.addMethod("existeTraductor", function(valor) {
		if (valor == "") {
			return true
		} else {
			var existe = true;
			jQuery.ajax({
				type: 'POST',
				url: '/aa79bItzulnetWar/administracion/trabajadoresgrupostrabajo/existeTraductor',
				contentType: 'application/json',
				data: JSON.stringify({idGrupo: $("#idTraductores").val(), dni: valor}),
				dataType: 'json',
				async: false, cache: false,
				success : function(data) {
					existe = data;
				}
			});
			return !existe;
		}
	  }
	, $.rup.i18nParse($.rup.i18n.app,"validaciones.existeTraductor"));
	
	$("#entidades").off('dblclick');
	$("#traductores").off('dblclick');
	
	$("#entidades").on("jqGridAddEditAfterShowForm.rupTable.formEditing", function(){
		$('#idEntidades').val($('#id_detail_table').val());
		$("#tipoEntidad_detail_T").click();
	});
	$("#traductores").on("jqGridAddEditAfterShowForm.rupTable.formEditing", function(){
		$('#idTraductores').val($('#id_detail_table').val());
	});

	$('#idEntidadSolicitante_detail_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestAlta",
		sourceParam : {
			value: "codigoCompleto",
			label : "desc" + $.rup_utils.capitalizedLang()
		},
		getText: false,
		open : function(event, object, arg) {
			 var tam = parseFloat(jQuery('#idEntidadSolicitante_detail_table').css("padding-left"))+ parseFloat(jQuery('#idEntidadSolicitante_detail_table').css("padding-right"));
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_detail_table').innerWidth());
			$("#idEntidadSolicitante_detail_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idEntidadSolicitante_detail_table_menu").removeClass("ui-front");
		}
	});
	
	jQuery('#entidades_detail_form input[name=tipoEntidad]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadSolicitante_detail_table").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadSolicitante_detail_table").rup_autocomplete("set", "", "");
		$('#idEntidadSolicitante_detail_table_label').removeData("tmp.loadObjects.term");
	});
	jQuery('input[name=tipo]:first').click();

	
	$("#codTrabajador_detail_table").rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/" + (comboInterpretes ? "interprete" : "traductor"),
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
		}
		, select: function(event, option) {
			$("#dniTraductor").val(option.value);
		}
	});
	tablasCreadas = true;
}
	
	if(!esAlta) {
		$("#idTipoExpediente_detail_table").rup_combo("change");
		$("#idTipoExpediente_detail_table").rup_combo("disable");
		$("#indBopv_detail_table:checked").change();
		if (id == 0) {
			$("#detalle_form").rup_form("disable", "#dniResponsable_detail_table");
			desactivarEntidades = true;
			desactivarTraductores = false;
		}
		
		crearTablas();
		$("#divTablas").addClass("in");
	}
	$("#trabajadoresgrupostrabajo_filter_fieldset input").css("font-weight", "normal");	
	$("#trabajadoresgrupostrabajo_filter_fieldset input").css("color", "black");
	
	valorEstadoInicial = $("#estado_detail_table").rup_combo('getRupValue');
	
	datosFormulario = $("#detalle_form").serialize();
	
	$("#traductores_detail_link_cancel").on("click", function(){
		$("#traductores_detail_div").rup_dialog("close");
		
	});
	
	$("#entidades_detail_link_cancel").on("click", function(){
		$("#entidades_detail_div").rup_dialog("close");
	});
	
});