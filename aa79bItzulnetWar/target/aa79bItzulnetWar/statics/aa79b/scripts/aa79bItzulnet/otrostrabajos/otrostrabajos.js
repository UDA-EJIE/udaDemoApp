var capaTrabajos = "";
var trabajoSeleccionado = {};

function confirmEliminarTrabajo(){
	var selectedRows = $("#otrostrabajos").rup_table('getSelectedRows');
	if (isEmpty(selectedRows)){
		$.rup_messages("msgAlert", {
			title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	 }else{
		 $.ajax({
				type : 'GET',
				url : '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tieneTareas/'
						+ selectedRows[0],
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				success : function(data) {
					if (data == false) {
						 $.rup_messages("msgConfirm", {
								title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
								message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
								OKFunction: function(){
									eliminarTrabajo(selectedRows[0]);
								}
							});
					}else{
						$.rup_messages("msgAlert", {
							title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
							message: $.rup.i18n.app.comun.warningTrabajoTareas
						});	
					}
					desbloquearPantalla();
				},
				error : function() {
					desbloquearPantalla();
				}
		 });
	 }
}

function eliminarTrabajo(idTrabajo){
	$.ajax({
        type: 'DELETE' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/'+idTrabajo
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,beforeSend: function() {
        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"));
        }
        ,success:function(){
        	$("#otrostrabajos").rup_table('filter');
        	$('#otrostrabajos_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.borradoCorrecto, "ok");
        	desbloquearPantalla();
        }
		,error: function(error){
			$('#otrostrabajos_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
			
			desbloquearPantalla();
	   	 }
	});
}

function clonarTrabajo(){
	var selectedRows = $("#otrostrabajos").rup_table('getSelectedRows');
	if (isEmpty(selectedRows)){
		$.rup_messages("msgAlert", {
			title: $.rup.i18nParse($.rup.i18n.app,"boton.clonarTrabajo"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	 }else{
		 var jsonTrabajo = 
		{
		    "idTrabajo": selectedRows[0]
		};
		$.ajax({
	        type: 'POST' 
	        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/clonarTrabajo'
	        ,dataType: "json",
			contentType: 'application/json', 
			data: jQuery.toJSON({"otrosTrabajos":jsonTrabajo}),
	        beforeSend: function() {
	        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.copiando"));
	        }
	        ,success:function(){
	        	$("#otrostrabajos").rup_table('filter');
	        	$('#otrostrabajos_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.copiadoCorrecto, "ok");
	        	desbloquearPantalla();
	        }
			,error: function(error){
				$('#otrostrabajos_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.copyError"), "error");
				desbloquearPantalla();
		   	 }
		});
	 }
}





function fnLimpiarAsignacion() {
	fnLimpiarCamposPersona();
}

function fnLimpiarCamposPersona() {
	$("#personaAsignada_filter_table").val("");
	$("#dniAsignacion").val("");
}

function seleccionarPersona(obj, personas) {
	if (obj != null && obj.length > 0) {
		for (var i = 0; i < obj.length; i++) {
			var persona = obj[i];
			$("#personaAsignada_filter_table").val(persona.nombreCompleto);
			$("#dniAsignacion").val(persona.dni);
		}
	}

}

function irATareasTrabajos(){
	var selectedRows = $("#otrostrabajos").rup_table('getSelectedRows');
	if (isEmpty(selectedRows)){
		$.rup_messages("msgAlert", {
			title: $.rup.i18nParse($.rup.i18n.app,"boton.clonarTrabajo"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	 }else{
		 //montamos objeto con los datos del trabajo.
		 trabajoSeleccionado = {
					'idTrabajo' : selectedRows[0],
					'descTrabajo': $("#otrostrabajos").rup_table("getCol", selectedRows[0], "descTrabajo"),
					'fechaInicio': $("#otrostrabajos").rup_table("getCol", selectedRows[0], "fechaHoraInicio"),
					'fechaFinPrevista':$("#otrostrabajos").rup_table("getCol", selectedRows[0], "fechaHoraFinPrevista") , 
					'fechaFin': $("#otrostrabajos").rup_table("getCol", selectedRows[0], "fechaHoraFin")
			};
		 $.rup_ajax({
		   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/tareastrabajo/maint' 
		   	 ,success:function(data, textStatus, jqXHR){
		   		capaTrabajos = $('#divTrabajos').detach();
		   		$("#divCapaTrabajos").append(data);
		   	 },
		   	 error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
		   	 }
		 });
	 }
	
}

function inicializarAyudas() {
	
	new Cleave('#fechaInicio_detail_table', {
	    date: true,
	    datePattern: ['Y', 'm', 'd']
	});
	
	new Cleave('#horaInicio_detail_table', {
	    time: true,
	    timePattern: ['h', 'm']
	});
	
	new Cleave('#fechaFinPrevista_detail_table', {
	    date: true,
	    datePattern: ['Y', 'm', 'd']
	});
	
	new Cleave('#horaFinPrevista_detail_table', {
	    time: true,
	    timePattern: ['h', 'm']
	});
}

jQuery(function($){

	jQuery('#tipoTareaTrabajo').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tipostarea?indVisibleTrabajo015=S",
		sourceParam:{
			value : "id015",
			label : $.rup.lang === 'es' ? "descEs015"
					: "descEu015", 
		}
		,blank: ""
		,width: "100%"	
		,rowStriping: true
		,open : function() {
			jQuery('#tipoTareaTrabajo-menu').width(jQuery('#tipoTareaTrabajo-button').innerWidth());
		}
	});
	
	$("#tieneSinAsignar_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#tieneSinAsignar_filter_table-menu').width(jQuery('#tieneSinAsignar_filter_table-button').innerWidth());
		}
	});
	
	$("#tieneSinEjecutar_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#tieneSinEjecutar_filter_table-menu').width(jQuery('#tieneSinEjecutar_filter_table-button').innerWidth());
		}
	});
	
	$("#indicadorEstado_filter").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#indicadorEstado_filter-menu').width(jQuery('#indicadorEstado_filter-button').innerWidth());
		}
	});
	
	fnFechaDesdeHasta("fechaInicioDesde_filter", "fechaInicioHasta_filter");
	fnFechaDesdeHasta("fechaFinPrevistaDesde_filter", "fechaFinPrevistaHasta_filter");
	
	$('#otrostrabajos_feedback').rup_feedback({
		block : false
	});
	
	
	//inicializamos formulario de edición
	$.rup_date({
		from : "fechaInicio_detail_table",
		to : "fechaFinPrevista_detail_table",
		fixFocusIE : false,
		onSelect : function(dateText, inst) {
			this.fixFocusIE = true;
		},
		onClose : function(dateText, inst) {
			this.fixFocusIE = true;
			if (($.browser.msie || (!!window.MSInputMethodContext && !!document.documentMode))
					&& !inst._keyEvent) {
				$("#" + inst.id).next().focus();
			}

		},
		beforeShow : function(input, inst) {
			var result = $.browser.msie
					|| (!!window.MSInputMethodContext && !!document.documentMode) ? !this.fixFocusIE
					: true;
			this.fixFocusIE = false;
			return result;
		}
	});
	
	jQuery('#fechaFin_detail_table').rup_date({		
 		labelMaskId : "fecha-mask",
 		minDate: new Date(),
 		showButtonPanel : true,
 		showOtherMonths : true
 	});	
	deshabilitarCampoFecha('fechaFin_detail_table', false, '');
	
	inicializarAyudas();
	
	
	 $("#otrostrabajos_filter_form").rup_validate({
			feedback: $('#otrostrabajos_feedback'),
			liveCheckingErrors: false,				
			block:false,
			delay: 3000,
			gotoTop: true, 
			rules:{
				"fechaInicioDesde": {date: true},
				"fechaInicioHasta": {date: true, fechaHastaMayor: "fechaInicioDesde" },
				"fechaFinPrevistaDesde": {date: true},
				"fechaFinPrevistaHasta": {date: true, fechaHastaMayor: "fechaFinPrevistaDesde" }
			},
			showFieldErrorAsDefault: false,
			showErrorsInFeedback: true,
	 		showFieldErrorsInFeedback: false
	});
	
	$("#otrostrabajos").rup_table({
		url: "../otrostrabajos",
		toolbar: {
			 id: "otrostrabajos_toolbar"
				 ,newButtons : [      
						{obj : {
							i18nCaption: $.rup.i18n.app.comun.eliminar
							,css: "fa fa-trash-o"
							,index: 0
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(e){
			                    e.preventDefault();
			                    e.stopImmediatePropagation();
			                    confirmEliminarTrabajo();
							}					 
						},
						{obj : {
							i18nCaption: $.rup.i18n.app.boton.clonarTrabajo
							,css: "fa fa-clone"
							,index: 0
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(e){
			                    e.preventDefault();
			                    e.stopImmediatePropagation();
			                    clonarTrabajo();
							}					 
						},
						{obj : {
							i18nCaption:  $.rup.i18n.app.boton.configTarea
							,css: "fa fa-list-ul"
							,index: 0
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(e){
			                    e.preventDefault();
			                    e.stopImmediatePropagation();
			                    irATareasTrabajos();
							}					 
						}
					]
				 ,defaultButtons:{
					 add : true
					,edit : true
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : true
					
				 }
		},
		colNames: [
				txtid,
				$.rup.i18n.app.label.titulo,
				$.rup.i18n.app.label.fechaHoraInicio,
				$.rup.i18n.app.label.fechaFin,
				$.rup.i18n.app.label.fechaHoraFin
		],
		colModel: [
			{	name: "idTrabajo", 
			 	label: "label.idTrabajo",
			 	index: "IDTRABAJO",
				align: "right", 
				width: 60, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name: "descTrabajo", 
			 	label: "label.titulo",
			 	index: "DESCTRABAJO",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraInicio", 
			 	label: "comun.fechaHoraInicio",
				align: "center", 
				index: "FECHAINICIO",
				width: "180", 
				isDate: true,
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraFinPrevista", 
			 	label: "label.fechaHoraFinPrevista",
				align: "center", 
				index: "FECHAFINPREVISTA",
				width: "180", 
				isDate: true,
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "fechaHoraFin", 
			 	label: "comun.fechaHoraFinalizacion",
				align: "center", 
				index: "FECHAFIN",
				width: "180", 
				isDate: true,
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],

        model:"OtrosTrabajos",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter",
        	"report"
         	],
     	report : {
			buttons : [ {
				id : "reports",
				i18nCaption : $.rup.i18nParse($.rup.i18n.app, "comun.exportar"),
				css : "fa fa-file-excel-o",
				right : true,
				buttons : [ {
					i18nCaption : $.rup.i18nParse(
							$.rup.i18n.app, "tabla.excel"),
					divId : "reports",
					css : "fa fa-file-excel-o",
					//url : "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/albaranes/excelReportTareas",
					url : "/aa79bItzulnetWar/tramitacionexpedientes/otrostrabajos/excelReportTrabajosTareas",
					click : function(event) {
						if (!$("#otrostrabajos_filter_form").valid()) {
							event.preventDefault();
							event.stopImmediatePropagation();
						}
					}
				} ]
			} ]
		},
		loadComplete: function(){
			$("[id='otrostrabajos_toolbar##reports-mbutton-container']").addClass("oculto");
			$("[id='otrostrabajos_toolbar##reports']").unbind("click");
			$("[id='otrostrabajos_toolbar##reports'] > i").addClass("fa fa-file-excel-o")
			$("[id='otrostrabajos_toolbar##reports']").on("click", function(){
				$("[id='otrostrabajos_toolbar##reports-mbutton-group##EXCEL']").click();
			});
		},
		primaryKey: ["idTrabajo"],
		sortname:"idTrabajo",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#otrostrabajos_detail_div",
         	validate:{ 
         		showFieldErrorAsDefault : false,
				showErrorsInFeedback : true,
				showFieldErrorsInFeedback : false,
    			rules:{
    				"idTrabajo":{
						required: false
    					},
    				"descTrabajo":{
						required: true
    					},
    				"fechaInicio":{
						required: true, date:true
    					},
    				"horainicio":{
						required: true, hora:true, maxlength: 5
    				},
    				"fechaFinPrevista" : {
    					date : true,
    					fechaHastaMayorPorId : "fechaInicio_detail_table"
    				},
    				"horaFinPrevista" : {
    					hora : true,
    					maxlength : 5,
    					horaFechaHastaMayorPorId : [
    							"fechaInicio_detail_table",
    							"fechaFinPrevista_detail_table",
    							"horaInicio_detail_table" ]
    				}
    			}
    		},
    		addEditOptions:{
   			 width:800,
   			 fillDataMethod: "serverSide",
   			 reloadAfterSubmit: true
   		 },
   		editOptions: {
   			 mtype: "POST",
   			 fillDataMethod: "serverSide",
   			 ajaxEditOptions:{
   				 contentType: 'application/json'
   			}
   		 },
   		 addOptions: {
   			 mtype: "POST",
   			 ajaxEditOptions:{
   				 contentType: 'application/json'
   			}
   		 }
        },
        filter:{
			clearSearchFormMode:"reset"
	    	,validate:{
	    		rules:{
    			}
	    	}
		 }
	});

	
	setFocusFirstInput("#otrostrabajos_filter_form");
    jQuery("#otrostrabajos").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
    	setFocusFirstInput("#otrostrabajos_detail_form");
    	if ($("#idTrabajo_detail_table").val()== ''){
    		$("#fechaInicio_detail_table").val(dateActual());
    		$("#horaInicio_detail_table").val(hourActual());
    		
    	}
    });
    
    jQuery("#otrostrabajos").on("jqGridAddEditAfterShowForm.rupTable.formEditing", function(event){
    	if ($("#fechaFin_detail_table").val()!= ''){
    		deshabilitarCampoFecha('fechaInicio_detail_table', false, '');
    		deshabilitarCampoFecha('fechaFinPrevista_detail_table', false, '');
    		deshabilitarCampoFecha('fechaFin_detail_table', false, '');
    		$('#horaInicio_detail_table').attr('disabled', 'disabled');
    		$('#horaFinPrevista_detail_table').attr('disabled', 'disabled');
    		$('#horaFin_detail_table').attr('disabled', 'disabled');
    		$('#horaFin_detail_table').attr('readonly', 'readonly');
    		$('#descTrabajo_detail_table').attr('disabled', 'disabled');
    		$('#otrostrabajos_detail_button_save').hide();
    	}else{
    		habilitarCampoFecha('fechaInicio_detail_table', false, '');
    		habilitarCampoFecha('fechaFinPrevista_detail_table', false, '');
    		$('#horaInicio_detail_table').removeAttr('disabled');
    		$('#horaFinPrevista_detail_table').removeAttr('disabled');
    		$('#descTrabajo_detail_table').removeAttr('disabled');
    		$('#otrostrabajos_detail_button_save').show();
    		if($("#tareasCerradas_detail_table").val() == 'S'){
    			habilitarCampoFecha('fechaFin_detail_table', false, '');
    			$('#horaFin_detail_table').removeAttr('disabled');
    			$('#horaFin_detail_table').removeAttr('readonly');
    		}else{
    			deshabilitarCampoFecha('fechaFin_detail_table', false, '');
    			$('#horaFin_detail_table').attr('disabled', 'disabled');
        		$('#horaFin_detail_table').attr('readonly', 'readonly');
    		}
    	}
    });
    
    $("#autoasignar_detail_table").click(function(event) {
    	event.preventDefault();
        event.stopImmediatePropagation(); 
    	fnAutoasignarTarea();
        recursoExterno = false;
    });

    $("#eliminar_detail_table").click(function(event) {
    	event.preventDefault();
        event.stopImmediatePropagation(); 
    	fnLimpiarAsignacion();
    });

    $("#recursoInterno_detail_table")
    .click(
    		function() {
    			// Es necesario eliminar buscadorPersonas para que
    			// buscadorPersonasIZO que carga
    			// los datos del personal IZO se cargue correctamente
    			$("#buscadorPersonas").remove();
    			$("#buscadorPersonasIZO").remove();
    			$("#divTrabajos")
    					.append(
    							'<div id="buscadorPersonasIZO" style="display:none"></div>');
    			$("#buscadorPersonasIZO").buscador_personas({
    				destino : 'I',
    				multiselect : false,
    				callback : seleccionarPersona
    			});
    			$("#buscadorPersonasIZO").buscador_personas("open");
    		});
    
    
});