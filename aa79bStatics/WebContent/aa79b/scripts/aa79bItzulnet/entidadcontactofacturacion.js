
function volverARevisionDatosFacturacion(){
	$("#divContactoFacturacion").detach();
	//si existe, eliminamos el desplegable del autocomplete para que al volver a crearlo no se duplique
	if($('#idEntidadAsociada_menu').length){
		$('#idEntidadAsociada_menu').remove();
	}
	$("#divBusquedaGeneral").append("<div id='divBusquedaGeneralCapa'></div>");
	$("#divBusquedaGeneralCapa").html(capaPestGeneral);
	$('#busquedaGeneral').rup_table('filter');
}

var tipoEntidadSolicitante = "";
var codEntidadSolicitante = "";
var elFormulario;
var idEntidadAsocAnterior = "";
var modificar;
var lineId = 0;
var entiDesc;
var idSeleccionado;
var nombreCompleto;
var cambios = false;

var configCombo = {
	loadFromSelect: true	
	,ordered: false	
	,rowStriping: true
	,open: function(){
		var id = $(this).attr("id");
        $("#"+id+"-menu").width($("#"+id+"-button").width());
    }
};

function limpiarDialog(){
	idSeleccionado = "";
	$("#idEntidadAsociada").rup_autocomplete("set", "", "");
	$('#idEntidadAsociada_label').removeData("tmp.loadObjects.term");
	$('#idEntidadAsoc058_detail_table').val('');
	$('#tipoEntidadAsoc058_detail_table').val('');	
	
	idEntidadAsocAnterior = "";
}

function datosEntidadesGrupoTrabajo(filtros){
	
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/datosEntidadesGrupoTrabajo'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(filtros)			
	     ,async: true
	   	 ,success: function(data){	
	   		 if(validarEntidadYaExiste(data)){
				if(validarPorFacturacion(data)){
					if(modificar != true){
						addContactoFacturacion(data);
						$('#contactofacturacionexp_detail_div').rup_dialog("close");
						cambios = true;
						
					} else {
						modificarContactoFacturacion(data);
						$('#contactofacturacionexp_detail_div').rup_dialog("close");
					}
					eliminarDialogo();
				} else {
					$("#contactofacturacionexp_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSupera100, "error");	
				}
			} else {
				$("#contactofacturacionexp_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.existeExcepcion, "error");
			}
		}
	 	,error: function(){
	   		$('#contactofacturacionexp_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   	}
	});
	
}

function validarExcepcionNueva(){
	$("div.error").remove();
	eliminarMensajesValidacion();
	
	var datos = jQuery("#contactofacturacionexp_detail_form").rup_form().formToJson();
	var error = true;
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/validarContactoFacturacion'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(datos)			
	     ,async: true
	   	 ,success:function(codigoVuelta){
	   		if (codigoVuelta === 4){
	   			$("#contactofacturacionexp_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSolicEntidaBaja, "error");	
	   			error = false;
	   		} else {
	   			datosEntidadesGrupoTrabajo(datos);
	   		}
	   	 }
   	 	,error: function(){
	   		$('#contactofacturacionexp_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		error = false;
   	 	}
	});
	return error;
}

function validarEntidadYaExiste(data){
	var tipoEntidadNueva = data.tipoEntidadAsoc058;
	var idEntidadNueva = data.idEntidadAsoc058;
	var dniContactoNueva = data.dniContacto058;
	
	if(tipoEntidadNueva != null && idEntidadNueva != null && dniContactoNueva === null){
		for (i = 0; i < listaEntidadFactu.length; i++) {
			tipoEntidad = listaEntidadFactu[i].tipoEntidadAsoc058;
			idEntidad = listaEntidadFactu[i].idEntidadAsoc058;
			dniContacto = listaEntidadFactu[i].dniContacto058;
			
			if(modificar === true && listaEntidadFactu[i].id058 != idSeleccionado && tipoEntidad === tipoEntidadNueva && idEntidad === idEntidadNueva){
				return false;
			} else if(modificar === false && tipoEntidad === tipoEntidadNueva && idEntidad === idEntidadNueva){
				return false;
			}
			
		}
	}
	
	if(tipoEntidadNueva != null && idEntidadNueva != null && dniContactoNueva != null){
		for (i = 0; i < listaEntidadFactu.length; i++) {
			tipoEntidad = listaEntidadFactu[i].tipoEntidadAsoc058;
			idEntidad = listaEntidadFactu[i].idEntidadAsoc058;
			dniContacto = listaEntidadFactu[i].dniContacto058;
			
			if(modificar === true && listaEntidadFactu[i].id058 != idSeleccionado && tipoEntidad === tipoEntidadNueva && idEntidad === idEntidadNueva && dniContacto === dniContactoNueva){
				return false;
			} else if(modificar === false && tipoEntidad === tipoEntidadNueva && idEntidad === idEntidadNueva && dniContacto === dniContactoNueva){
				return false;
			}
			
		}
	}
	
	return true;
}

function validarPorFacturacion(data){
	var porNueva = data.porFactura058;
	var porGuardada = 0;
	var total;
	for (i = 0; i < listaEntidadFactu.length; i++) {
		if(listaEntidadFactu[i].id058 != idSeleccionado){
			porGuardada = listaEntidadFactu[i].porFactura058 + porGuardada;
		}
	}
	total = porNueva + porGuardada;
	if(total > 100){
		return false;
	}
	return true;
}

function addContactoFacturacionList(data){
	listaEntidadFactu.push(data);
}

function addContactoFacturacion(data){
	lineId = lineId + 1;
	data.id058 = lineId;
	
	if($.rup.lang === 'es') {
		entiDesc = data.entidadGruposTrabajo.descEs;
	} else {
		entiDesc = data.entidadGruposTrabajo.descEu;
	}
	
	if(data.contacto != null){
		nombreCompleto = data.contacto.nombreCompleto;
	}else{
		nombreCompleto = "";
	}
	
	$("#contactofacturacionexp").rup_table("addRowData", lineId, {
		"tipoEntidadAsoc058":data.tipoEntidadAsoc058,
		"idEntidadAsoc058":data.idEntidadAsoc058,
		"dniContacto058":data.dniContacto058,
		"entidadGruposTrabajo.descAmpEu":entiDesc,
		"contacto.nombreCompleto":nombreCompleto,
		"entidadGruposTrabajo.facturable":data.entidadGruposTrabajo.facturable,
		"entidadGruposTrabajo.iva":data.entidadGruposTrabajo.iva,
		"entidadGruposTrabajo.facturableDesc":data.entidadGruposTrabajo.facturableDesc,
		"entidadGruposTrabajo.ivaDesc":data.entidadGruposTrabajo.ivaDesc,
		"porFactura058":data.porFactura058});
	
	addContactoFacturacionList(data);
	
}

function modificarContactoFacturacionList(data){
	listaEntidadFactu.splice( listaEntidadFactu.indexOf(listaEntidadFactu.filter(function(x) { return x.id058 == idSeleccionado })[0]), 1);	
	data.id058 = parseInt(idSeleccionado[0]);
	listaEntidadFactu.push(data);
}

function modificarContactoFacturacion(data){
	
	if($.rup.lang === 'es') {
		entiDesc = data.entidadGruposTrabajo.descEs;
	} else {
		entiDesc = data.entidadGruposTrabajo.descEu;
	}
	
	if(data.contacto != null){
		nombreCompleto = data.contacto.nombreCompleto;
	}else{
		nombreCompleto = "";
	}
	
	$("#contactofacturacionexp").rup_table("setRowData", idSeleccionado, {
		"tipoEntidadAsoc058":data.tipoEntidadAsoc058,
		"idEntidadAsoc058":data.idEntidadAsoc058,
		"dniContacto058":data.dniContacto058,
		"entidadGruposTrabajo.descAmpEu":entiDesc,
		"contacto.nombreCompleto":nombreCompleto,
		"entidadGruposTrabajo.facturable":data.entidadGruposTrabajo.facturable,
		"entidadGruposTrabajo.iva":data.entidadGruposTrabajo.iva,
		"entidadGruposTrabajo.facturableDesc":data.entidadGruposTrabajo.facturableDesc,
		"entidadGruposTrabajo.ivaDesc":data.entidadGruposTrabajo.ivaDesc,
		"porFactura058":data.porFactura058});
	
	modificarContactoFacturacionList(data);
}
 
function eliminarContactoFacturacionList(){
	listaEntidadFactu.splice( listaEntidadFactu.indexOf(listaEntidadFactu.filter(function(x) { return x.id058 == idSeleccionado })[0]), 1);	
}

function eliminarContactoFacturacion(){
	$("#contactofacturacionexp").rup_table("delRowData", idSeleccionado);
	eliminarContactoFacturacionList();
}

function guardar(){
	
	var listaExpedientes = new Array();
	var anyoNumExp;
	var anyoNumExpediente;
	var anyo;
	var numExp;
	
		
	for (var i = 0; i < expedientesSeleccionados.length; i++) {
		anyoNumExp = expedientesSeleccionados[i];
		
		anyoNumExpediente = anyoNumExp.split("-");	
		anyo = anyoNumExpediente[0];
		numExp = anyoNumExpediente[1];
		
		listaExpedientes.push( {
    		"anyo": anyo,
    		"numExp": numExp
    	});
	}
	
	//montamos obj expedientefacturar para insertarlos en bbdd
	var listaExpedienteFacturar = [];
	for(var j=0;j<listaEntidadFactu.length;j++){
		listaExpedienteFacturar.push({
			"tipoEntidadAsoc": listaEntidadFactu[j].tipoEntidadAsoc058,
			"idEntidadAsoc":listaEntidadFactu[j].idEntidadAsoc058 ,
			"dniContacto":listaEntidadFactu[j].contacto?listaEntidadFactu[j].contacto.dni:"" ,
			"porFactura":listaEntidadFactu[j].porFactura058 
		});
	}
	
	var jsonObject = {"listaExpedientes"  : JSON.stringify(listaExpedientes),
					  "listaEntidadFactu" : JSON.stringify(listaExpedienteFacturar)};
	
	$.rup_ajax({
		type: 'POST', 
		dataType: 'json', 
		data: jsonObject,
		async: false, 
		url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/entidadcontactofacturacion/guardarCambios',
		success: function(data, textStatus, jqXHR){	 
			desbloquearPantalla();
			$('#contactofacturacionexp_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.aceptadaLaAsignacion, "ok");
			cambios = false;
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert('error cargando datos');
		}
	});
}

function crearDialogo(){
	$("#contactofacturacionexp_detail_div").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        title: $.rup.i18nParse($.rup.i18n.base,"rup_table.edit.addCaption"),
		width:800,
		fillDataMethod: "clientSide",
		reloadAfterSubmit: true,
		close: function(event, ui) { 
			limpiarDialog();
		}
	});
}
function eliminarDialogo(){
	limpiarDialog();
	$('#contactofacturacionexp_detail_div').rup_dialog("close");
	$('#contactofacturacionexp_detail_div').rup_dialog("destroy");
}
	
jQuery(function($){

	$('#contactofacturacionexp_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	
	$('#contactofacturacionexp_detail_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	
	$("#contactofacturacionexp_detail_form").rup_validate({
		feedback: $('#contactofacturacionexp_detail_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"idEntidadAsoc058_autocomplete_label":{
				required: true
				},
			"dniContacto058":{
				required: false
				},
			"porFactura058":{ required: true, maxlength: 3, integer: true, range: [0, 100] }
		},
		showFieldErrorAsDefault: false,
        showErrorsInFeedback: true,
        showFieldErrorsInFeedback: false
	});
	
	$("#contactofacturacionexp_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			css : "fa fa-arrow-left",
			id : "volver",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				if(listaEntidadFactu.length != 0 && cambios === true){
					$.rup_messages("msgConfirm", {
	    				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	    				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	    				OKFunction: function(){
	    					volverARevisionDatosFacturacion();
	    				}
	    			});
				} else {
					volverARevisionDatosFacturacion();
				}
				
			}
		}, {
			i18nCaption : $.rup.i18n.app.boton.anyadir,
			css: "fa fa-file-o", 
			id : "anyadir",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation()
				modificar = false;
				crearDialogo();
				$("#dniContacto058_detail_table").rup_combo("disableChild");
				$('#porFactura058_detail_table').val('100');
				$("#contactofacturacionexp_detail_div").rup_dialog("open");
			}
		},{
			i18nCaption : $.rup.i18n.app.boton.modificar,
			css: "fa fa-pencil-square-o", 
			id : "modificar",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				crearDialogo();
				modificar = true;
				if(!$('#contactofacturacionexp').rup_table("isSelected")){
					e.preventDefault();
					$.rup_messages("msgAlert", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.modificar"),
						message: $.rup.i18n.app.comun.warningSeleccion
					});	
					return false;
				 }else{
					 idSeleccionado = $("#contactofacturacionexp").rup_table("getSelectedRows");
					 var datos = listaEntidadFactu.filter( function (x){ return x.id058 == idSeleccionado } )[0];
					 if($.rup.lang === 'es') {
					 	 entiDesc = datos.entidadGruposTrabajo.descEs;
					 } else {
						 entiDesc = datos.entidadGruposTrabajo.descEu;
					 }
					 $("#idEntidadAsociada").rup_autocomplete("set",datos.tipoEntidadAsoc058+"_"+datos.idEntidadAsoc058,entiDesc);
					 
						$('#idEntidadAsoc058_detail_table').val(datos.idEntidadAsoc058);
						$('#tipoEntidadAsoc058_detail_table').val(datos.tipoEntidadAsoc058);	
						
						
					 $("#porFactura058_detail_table").val(datos.porFactura058);
					 creaComboContacto(datos.tipoEntidadAsoc058,datos.idEntidadAsoc058,datos.dniContacto058);
					 $("#contactofacturacionexp_detail_div").rup_dialog("open");
				 }
				
			}
		},{
			i18nCaption : $.rup.i18n.base.rup_table.del.caption,
			css: "fa fa-trash-o", 
			id : "borrar",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				if(!$('#contactofacturacionexp').rup_table("isSelected")){
					e.preventDefault();
					$.rup_messages("msgAlert", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.eliminar"),
						message: $.rup.i18n.app.comun.warningSeleccion
					});	
					return false;
				 }else{
					 $.rup_messages("msgConfirm", {
         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
         				OKFunction: function(){
         					idSeleccionado = $("#contactofacturacionexp").rup_table("getSelectedRows");
         					eliminarContactoFacturacion();
         				}
         			});
				 }
			}
		},{
			i18nCaption : $.rup.i18n.app.boton.guardar,
			css: "fa fa-floppy-o", 
			id : "guardar",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
				guardar();
			}
		} ]
	});
	
	$("#contactofacturacionexp").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/entidadcontactofacturacion",
		toolbar:{
			id: "contactofacturacionexp_toolbar"
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
			"",
			"",
			txtEntidadAsoc,			
			txtContacto,	
			"",
			"",
			txtFacturable,
			txtIVA,
			txtPorcFact			
		],		
		colModel: [
			{ 	name: "tipoEntidadAsoc058", 
			 	label: "tipoEntidadAsoc058",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "idEntidadAsoc058", 
			 	label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "dniContacto058", 
			 	label: "label.contacto",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadGruposTrabajo.descAmpEu", 
			 	label: "label.entidadAsociada",
			 	index: "ENTIDADDESCAMP",
				align: "", 
				width:"100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "contacto.nombreCompleto", 
			 	label: "label.contacto",
			 	index: "CONTACTOAPEL1",
				align: "", 
				width:"100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadGruposTrabajo.facturable", 
			 	label: "label.entidadFacturable",
			 	index: "FACTURABLE",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadGruposTrabajo.iva", 
			 	label: "label.aplicaIva",
			 	index: "IVA",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadGruposTrabajo.facturableDesc", 
				label: "label.entidadFacturable",
				index: "FACTURABLEDESC",
				align: "center", 
				width:"70",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadGruposTrabajo.ivaDesc", 
				label: "label.aplicaIva",
				index: "IVADESC",
				align: "center", 
				width: "60",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "porFactura058", 
			 	label: "label.porcentajeFactAplica",
				align: "center", 
				width: "100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
        ],
        title: false,
        model:"ContactoFacturacionExpediente",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"fluid",
        	"filter"
         	],
		primaryKey: ["id058"],
		sortname: "id058",
		sortorder: "asc",
		loadOnStartUp: false
	});
	
	
	function creaComboContacto(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
		if ($('#dniContacto058_detail_table-button').length){
			$('#dniContacto058_detail_table').rup_combo("clear");
		}
		
		$('#dniContacto058_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/solicitante/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
			sourceParam : {
				value: "dni",
				label : "nombreCompleto" 
			},
		    rowStriping: true,
		    blank: "",
			open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").innerWidth());
		    },
		    onLoadSuccess: function(){ 
		    	if (typeof(valorSeleccionar) !== "undefined"){
		    		$("#dniContacto058_detail_table").rup_combo("select", valorSeleccionar+'');
		    	}
		    }
		});	
	}
	
	creaComboContacto('A','0');

	//Autocomplete Entidad asociada
	$('#idEntidadAsociada').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestEntidadesConContactosSolicitantes",
		sourceParam : {
			value: "codigoCompleto",
			label : "descAmp" + $.rup_utils.capitalizedLang()
		},
		menuMaxHeight: "300px",
		getText: false,
		open : function() {
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadAsociada').innerWidth());
			$("#idEntidadAsociada_menu").css("z-index", $("#contactofacturacionexp_detail_div").parent().css("z-index")+1);
			$("#idEntidadAsociada_menu").removeClass("ui-front");
		}
	});		
	
	$("#idEntidadAsociada_label").on("rupAutocomplete_change", function(event, data){
		if (idEntidadAsocAnterior !== $('#idEntidadAsociada').rup_autocomplete('getRupValue')){
			var datosEntAsociada = $('#idEntidadAsociada').rup_autocomplete('getRupValue').split("_");	
			$('#idEntidadAsoc058_detail_table').val(datosEntAsociada[1]);
			$('#tipoEntidadAsoc058_detail_table').val(datosEntAsociada[0]);		
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
			}else {
				creaComboContacto(datosEntAsociada[0],datosEntAsociada[1]);
				idEntidadAsocAnterior = $('#idEntidadAsociada').rup_autocomplete('getRupValue');
			}
		}else{
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
				idEntidadAsocAnterior = "";
			}
		}
	});
	 
	jQuery('input[name=tipoEntidadFact]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadAsociada").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadAsociada").rup_autocomplete("set", "", "");
		$('#idEntidadAsociada_label').removeData("tmp.loadObjects.term");
		$('#idEntidadAsoc058_detail_table').val('');
		$('#tipoEntidadAsoc058_detail_table').val('');	
		$("#dniContacto058_detail_table").rup_combo("disableChild");
		idEntidadAsocAnterior = "";
	});
	jQuery('input[name=tipoEntidadFact]:first').click();
	
	$('#contactofacturacionexp_detail_link_cancel').click(function() {
		eliminarDialogo();
	});
	
	$("#contactofacturacionexp_detail_button_save").button().click(function(){
		if($("#contactofacturacionexp_detail_form").valid()){
			validarExcepcionNueva();
		}
    });
	
	jQuery("#contactofacturacionexp_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#contactofacturacionexp_detail_form");
		elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
	});
	
	jQuery('#contactofacturacionexp_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
});
