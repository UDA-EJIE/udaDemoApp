var today = new Date();
var anyoActual = today.getFullYear();

var y = today.getFullYear();

var arrJornada = new Array();
var arrFestivos = new Array();

var arrVacaciones = new Array();
var arrFormacion = new Array();
var arrBaja = new Array();
var arrOtros = new Array();
var arrInterpretacion = new Array();

//Para generar el PDF
var img = new Image();
img.src= $.rup.APP_STATICS+"/images/ivap.png";
var form = $('#previsualizardiv');

//para validar que no hay cambios en la info inicial del primer form...
var initCalPersonal = $("#calendariopersonal_detail_form").rup_form("formSerialize");

function confirmEliminarAusencia(){
	var selectedRows = $("#ausencias").rup_table('getSelectedRows');
	if (isEmpty(selectedRows)){
		$.rup_messages("msgAlert", {
			title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	 }else{
		 var elTipo = $("#ausencias").rup_table("getCol", selectedRows, "tipoJornadaTxt").substring(0,1);
		 if (elTipo === "I"){
			 $('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.ausenciaInterpNoEliminable, "error");
		 }else{
			 $.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
					OKFunction: function(){
						eliminarAusencia(selectedRows[0]);
					}
				});
		 }
	 }
}

function eliminarAusencia(idAusencia){
	$.ajax({
        type: 'DELETE' 
        ,url: '/aa79bItzulnetWar/ausenciascalendariopersonal/'+idAusencia
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,beforeSend: function() {
        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"));
        }
        ,success:function(){
        	$("#ausencias").rup_table('filter');
        	$("#ausencias").rup_table("resetSelection");
        	$('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.borradoCorrecto, "ok");
        	desbloquearPantalla();
        }
		,error: function(error){
			$('#ausencias_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
			
			desbloquearPantalla();
	   	 }
	});
}


jQuery(function($){

	
	$("#indTeletrabajo_detail_table").bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
		.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);
	
	
	function leerDNI(elDNI){
		if ( elDNI !== '' ){
			$.ajax({
			   	 type: 'GET'
			   	 ,url: '../calendariopersonal/'+elDNI
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     //,data: $.toJSON($('#anyoCalendario').val())			
			     ,async: false
			     ,cache: false
			   	 ,success:function(CalendarioPersonal){
			   		if (CalendarioPersonal !== null){ //existe el registro
				   		$('#observHorario_detail_table').val(CalendarioPersonal.observHorario);
				   		$('#horasGestionExp_detail_table').val(CalendarioPersonal.horasGestionExp);
				   		if(CalendarioPersonal.indTeletrabajo === 'S'){
							$('#indTeletrabajo_detail_table').bootstrapSwitch('setState', true);
						}else{
							$('#indTeletrabajo_detail_table').bootstrapSwitch('setState', false);
						}
				   		$('#existe').val('true');
				   		//Para leer la tala de ausencias
				   		$('#dni025_filter_table').val(CalendarioPersonal.dni);
				   		$('#anyo025_filter_table').val(anyoActual);
				   		mostrarAusencias();
				   		ocultarCalendario();
				   		$('#ausencias').rup_table('filter');
				   		
			   		}else{
			   			$('#existe').val('false');
			   			$('#observHorario_detail_table').val("");
				   		$('#horasGestionExp_detail_table').val(horasGestionExp);
				   		$('#indTeletrabajo_detail_table').bootstrapSwitch('setState', false);
				   		$('#ausencias').rup_table('filter');
				   		ocultarAusencias();
				   		ocultarCalendario();
			   		}		
			   		initCalPersonal = $("#calendariopersonal_detail_form").rup_form("formSerialize");
			   		$('#botoneraCalendario').show();
			   	 }
		  	 	,error: function(){	  	 		
		  	 		$('#calendariopersonal_detail_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioErrorInicializar, "error");
			   	 }
			 });	
		}else{
			$('#existe').val('false');
   			$('#observHorario_detail_table').val("");
	   		$('#horasGestionExp_detail_table').val("");
	   		$('#indTeletrabajo_detail_table').bootstrapSwitch('setState', false);	   		
	   		ocultarAusencias();
	   		ocultarCalendario();
	   		initCalPersonal = $("#calendariopersonal_detail_form").rup_form("formSerialize");
	   		$('#botoneraCalendario').hide();
		}
	}	
	
		
	
	//Formulario detalle calendario personal
	$("#calendariopersonal_detail_form").rup_form({
		url: "/aa79bItzulnetWar/administracion/calendariopersonal",
		dataType: "json",
		feedback:$("#calendariopersonal_detail_feedback"),
		type: "POST",		
		success: function(){			
			$('#calendariopersonal_detail_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
			if ($('#existe').val() === 'false'){
				leerDNI($('#dni_detail_table').val());
			}
		},
		validate:{ 
			rules:{
				"dni": {required: true},
				"observHorario": {required: true, maxlength: 250},
				"horasGestionExp": { maxlength: 2, integer: true}
				},
				showFieldErrorAsDefault: false,
		        showErrorsInFeedback: true,
		        showFieldErrorsInFeedback: false
		}
	});
	$('#calendariopersonal_detail_link_cancel').click(function() {
		leerDNI($('#dni_detail_table').val());				
	});
	$('#calendariopersonal_detail_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	
	$('#calendario_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	$('#ausencias_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	//Ocultar y mostrar el boton de previsualizar
	$('#anyo025_filter_table').keydown(function() {		
		$('#ausencias_toolbar-rightButtons').hide();
	});
	$('#ausencias').on('rupTable_beforeFilter', function(){$('#ausencias_toolbar-rightButtons').show(); });
	
	$("#calendariopersonal_detail_button_save").button().click(function(){
		initCalPersonal = $("#calendariopersonal_detail_form").rup_form("formSerialize");
		$("#calendariopersonal_detail_form").submit();
    });
	

	
	
	var cmbActivo = "";
	var xxx = "";
	var dniOption = { 
			source : "/aa79bItzulnetWar/personalIZO/todos",
			sourceParam : {
				label : "nombreCompleto",
				value : "dni",
				style : "css"
			},
			blank: "",
			width : "100%",
			ordered : true,
			rowStriping : true,
			//selected: dniUsuSesion+'',
			onLoadSuccess : function() {
				$("#dni_detail_table").rup_combo("select", dniUsuSesion+'');
				//Aquí seleccionar el dni del usuario en sesión
				leerDNI($('#dni_detail_table').val());
				cmbActivo = $('#dni_detail_table').rup_combo('value');
				if(!esTecnico){
					$('#horasGestionExp_detail_table').attr('readonly','readonly');		
					$("#dni_detail_table").rup_combo("disable");	
				}
				
			},
			open : function() {
				xxx = $("#calendariopersonal_detail_form").rup_form("formSerialize");
				var id = $(this).attr("id");
				$("#"+id+"-menu").width($("#"+id+"-button").width());
			},
			select:function(){
				var cmbNuevo = $('#dni_detail_table').rup_combo('value');
				if(cmbActivo !== '' && cmbActivo !== cmbNuevo && initCalPersonal !== xxx){
						$.rup_messages("msgConfirm", {
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
							message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
							OKFunction: function(){
								if (cmbNuevo===""){
									$("#calendariopersonal_detail_form").rup_validate('resetForm');
									ocultarAusencias();
									ocultarCalendario();
									cmbActivo="";
									initCalPersonal = $("#calendariopersonal_detail_form").rup_form("formSerialize");
									leerDNI('');
								}else{
									cmbActivo = cmbNuevo;
									var tempo = cmbNuevo;
									//quitar los posibles campos con error y mensajes
									$("#calendariopersonal_detail_form").rup_validate('resetForm');
									$('#dni_detail_table').val(tempo);
									leerDNI(cmbNuevo);		
								}					
							},
							CANCELFunction: function(event, ui){
								$('#dni_detail_table').rup_combo('select', cmbActivo);
							}
						});	
				}else if(cmbActivo === cmbNuevo){
					// no hace nada
				}else{
					if (cmbNuevo===""){
						$("#calendariopersonal_detail_form").rup_validate('resetForm');
						ocultarAusencias();
						ocultarCalendario();
						cmbActivo="";
						initCalPersonal = $("#calendariopersonal_detail_form").rup_form("formSerialize");
						leerDNI('');
					}else{
						cmbActivo = $('#dni_detail_table').rup_combo('value');
						var tempo = cmbActivo;
						//quitar los posibles campos con error y mensajes
						$("#calendariopersonal_detail_form").rup_validate('resetForm');
						$('#dni_detail_table').val(tempo);
						leerDNI(cmbActivo);
					}
				}
			}			
		};
	jQuery('#dni_detail_table').rup_combo(dniOption);
	//$("#dni_detail_table").rup_combo("select", dniUsuSesion+"");
	ocultarAusencias();
	ocultarCalendario();
	
	
	 $('#ausencias_detail_feedback').rup_feedback({
			block : false,
			delay: 3000
		});
	
	 
	 
	 function envioFormAjax(){
			var datos = jQuery("#ausencias_detail_form").rup_form().formToJson();
			var tipoEnvio = 'POST';
			if ( isNotEmpty($('#idAusencia_detail_table').val()) && $('#idAusencia_detail_table').val()!="0"  ){
				//es modificacion
				tipoEnvio = 'PUT';
			}
			 $.ajax({
			   	 type: tipoEnvio
			   	 ,url: '/aa79bItzulnetWar/ausenciascalendariopersonal'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(datos)			
			     ,async: false
			   	 ,success:function(objAusencia){
			   		$("#ausencias_detail_div").rup_dialog('close');
			   		if (objAusencia.numTareasConflicto > 0){
			   			if (objAusencia.rdoEnvioEmails == "0"){
			   				$('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareasNotificadasAsignador, "ok");
			   			}else{
			   				$('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorTareasNotificadasAsignador, "ok");
			   			}
			   		}else{
			   			$('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
			   		}
			   		$("#ausencias").rup_table("filter");
			   		
			   	 }
		   	 	,error: function(){
			   		$('#ausencias_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   	 }
			 });
	 }
	 
	 function envioFormAjaxSoloEmailsInterpretacion(){
		 var datos = jQuery("#ausencias_detail_form").rup_form().formToJson();
		 var tipoEnvio = 'POST';
		 $.ajax({
			 type: tipoEnvio
			 ,url: '/aa79bItzulnetWar/ausenciascalendariopersonal/enviarEmailsTareasInterp'
				 ,dataType: 'json'
					 ,contentType: 'application/json' 
						 ,data: $.toJSON(datos)			
						 ,async: false
						 ,success:function(objAusencia){
							 $("#ausencias_detail_div").rup_dialog('close');
							 if (objAusencia.numTareasConflicto > 0){
								 if (objAusencia.rdoEnvioEmails == "0"){
									 $('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.solicitadaDesasignacionTareas, "ok");
								 }else{
									 $('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorTareasNotificadasAsignador, "error");
								 }
							 }else{
								 $('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
							 }
						 }
		 ,error: function(){
			 $('#ausencias_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		 }
		 });
	 }
	 
	 
	 function comprobarTareasEnElRango(){
		 var hayEnvio = true;
		 
		 var personaAsignada={
				 "dni": $('#dni025_detail_table').val()
		 };
		 var jsonObject = 
			{
				"fechaIni": $('#fechaDesde_detail_table').val(),
				"fechaFin": $('#fechaHasta_detail_table').val(),
				"personaAsignada": personaAsignada
			};
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findCountTareasRangoFechas'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(jsonObject)			
		     ,async: false
		     ,cache: false
		   	 ,success:function(numTareas){
		   		if (numTareas[0] > 0) {
		   			//Hay tareas de interpretación
		   			hayEnvio = false;
		   			$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"label.aviso"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasInterpretacionEnFecha"),
						OKFunction: function(){
							$("#numTareasConflicto").val(numTareas[0]);
							envioFormAjaxSoloEmailsInterpretacion();
						}
					});
		   		}else if (numTareas[1] > 0) {
		   			//	si hay tareas que no sean de interpretacion se lanza un aviso antes de aceptar la ausencia 
		   			//devuelvo false para que no se envíe y espere. Luego lanzo yo el submit si dan a "aceptar" 
		   			hayEnvio = false;
		   			$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"label.aviso"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.avisoTareasPlanificadas"),
						OKFunction: function(){
							$("#numTareasConflicto").val(numTareas[1]);
							envioFormAjax();
						}
					});
		   		} else {
		   			hayEnvio = true;
		   		}
		   	 }
	   	 	,error: function(){
		   		$('#ausencias_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		hayEnvio = false;
		   	 }
		 });
		if (hayEnvio){
			return [true];
		}else{
			return [false];
		}
	 }
	 
	 
	 
	 function comprobarTipoInterpretacion(){
		 	var error = [true];
			if ($('#tipoJornada_detail_table').rup_combo("getRupValue") === "I"){
				//Si es ausencia por tarea de interpretación no se puede modificar. Punto.
				$('#ausencias_detail_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.ausenciaInterpNoModificable, "error");
				error = [false];
			}else{
				if ($("#ausencias_detail_form").valid()){		
					//Si es otro tipo de ausencia que se está creando o modificando, 
					//hay que comprobar si hay tareas en ese rango de fechas con una llamada de ajax
					error = comprobarTareasEnElRango();
				} else{
					error = [false];
				}//valid
			}
			return error;
		}
	 
	 $("#ausencias").rup_table({		
		url: "/aa79bItzulnetWar/ausenciascalendariopersonal",
		toolbar: {
			 id: "ausencias_toolbar"	
				 ,newButtons : [      
				       {obj : {
							i18nCaption: $.rup.i18n.app.boton.preview
							,css: "fa fa-calendar"
							,index: 0
							,right: true
							,id : "btnPreview"
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(){
							 	leerCalendario($('#anyo025_filter_table').val());
							}						 
						},
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
			                    confirmEliminarAusencia();
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
			"",
			"",
			"",
			txtFechaDesde,
			txtFechaHasta,
			txtTipoJornada
		],
		colModel: [
			{ 	name: "dni", 
			 	label: "label.dni",
				align: "", 
				hidden: true 
			},
			{ 	name: "anyo", 
			 	label: "label.anyo",
				align: "", 
				hidden: true
			},
			{ 	name: "idAusencia", 
			 	label: "idAusencia025",
				align: "", 
				hidden: true
			},
			{ 	name: "fechaDesde", 
			 	label: "label.fechaDesde",
			 	index: "FECHADESDE025",
				align: "", 
				width: 150, 				
				fixed: false
			},
			{ 	name: "fechaHasta", 
			 	label: "label.fechaHasta",
			 	index: "FECHAHASTA025",
				align: "", 
				width: 150, 
				fixed: false
			},
			{ 	name: "tipoJornadaTxt", 
			 	label: "label.tipoJornada",
			 	index: "TIPOJORNADATXT",
				align: "", 
				width: 150, 
				fixed: false 
			}
        ],

        model:"AusenciasCalendarioPersonal",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",        	
        	"filter",        	
         	],
		primaryKey: ["idAusencia"],
		sortname: "FECHADESDE025",
		sortorder: "asc",
		loadOnStartUp: false,
        formEdit:{
        	detailForm: "#ausencias_detail_div",			
         	validate:{ 
         		feedback: $("#ausencias_detail_feedback")   	 		
    		},    		
    		addEditOptions:{
				 width:800,
				 fillDataMethod: "serverSide",
				 reloadAfterSubmit: true,
				 beforeSubmit: function(){	
					 return comprobarTipoInterpretacion();
				 },
	    		 beforeShowForm: function(){
	    			 $("#numTareasConflicto").val('0');
	    			 $("label[generated='true']" ).remove();
	    			 $("div.error").remove();
	    			 $("label.error").remove();
	    			 eliminarMensajesValidacion();
	    			 if ($('#tipoJornada_detail_table').rup_combo("getRupValue") === "I"){
	    				 $("#tipoJornada_detail_table").rup_combo("disable");
	    			 }else{
	    				 $("#tipoJornada_detail_table").rup_combo("enable");
	    			 }
	    			 
	    		 }
    		},
			editOptions: {
				 mtype: "POST",
				 fillDataMethod: "serverSide",
				 reloadAfterSubmit : true,
				 ajaxEditOptions:{
					 contentType: 'application/json'
				}
			 },
			 addOptions: {
				 mtype: "POST",
				 reloadAfterSubmit : true,
				 ajaxEditOptions:{
					 contentType: 'application/json'
				}
			 }
        },
        filter:{
			clearSearchFormMode:"reset"				
	    	,validate:{
	    		rules:{
	    			"anyo":{ required: true,integer: true, minlength: 4, maxlength: 4 }
    				
    			}
	    	}
		 }
	});
 
	 
	 
	 
 $("#ausencias_detail_form").rup_validate({
		feedback: $('#ausencias_detail_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"dni":{	required: true },
			"anyo":{ required: true, integer: true, min: 2016 },
			
			"fechaDesde":{ required: true, date: true,rangoValido: true, rangoAnyo: true},
			"fechaHasta":{ required: true, date: true, fechaHastaMayor:"fechaDesde", rangoAnyo: true},
			"tipoJornada":{ required: true},
			"observDia":{ required: true, maxlength: 250}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	 
	 
	 
//poner la validación en la 2ª hora (fin)
	$.validator.addMethod("rangoValido", function(){
		var error = false;
		var jsonObject = 
		{
			"dni": $('#dni025_detail_table').val(),
			"idAusencia": $('#idAusencia_detail_table').val(),
			"fechaDesde": $('#fechaDesde_detail_table').val(),
			"fechaHasta": $('#fechaHasta_detail_table').val()
		};	
		
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/ausenciascalendariopersonal/comprobarRangoAusencias'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(jsonObject)			
		     ,async: false
		     ,cache: false
		   	 ,success:function(existe){
		   		if (existe) {
		   			$("#ausencias_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.rangoAusenciaConflicto, "error");			   			
		   			error =  false;
		   		} else {
		   			error =  true;
		   		}
		   	 }
	   	 	,error: function(){
		   		$('#ausencias_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  false;
		   	 }
		 });			
		return error;		

	}, $.rup.i18n.app.validaciones.rangoAusenciaConflicto);	
 
	
	$.validator.addMethod("rangoAnyo", function(value){
			if( (new Date(value).getTime() >= new Date( $('#anyo025_filter_table').val()+"/01/01").getTime()) 
				&& (new Date(value).getTime() <= new Date( $('#anyo025_filter_table').val()+"/12/31").getTime())  ){
				return true;
			}else{ 
				return false;
			}		
	}, $.rup.i18n.app.validaciones.fechaNoAnyo);	
	
	
	function mostrarAusencias(){
		
		setTimeout(function() {
			$('#divAusencias').removeClass('collapsed');
			$('#divAusencias').addClass('in');
			
			}, 500);
	}
	function ocultarAusencias(){
		
		setTimeout(function() {
			$('#divAusencias').addClass('collapsed');
			$('#divAusencias').removeClass('in');
			
			}, 500);
	}
	function mostrarCalendario(){
		ocultarAusencias();
		setTimeout(function() {
			$('#divCalendario').removeClass('collapsed');
			$('#divCalendario').addClass('in');
			
			}, 500);
	}
	function ocultarCalendario(){
		
		setTimeout(function() {
			$('#divCalendario').addClass('collapsed');
			$('#divCalendario').removeClass('in');
			
			}, 500);
	}
	
	$('#tipoJornada_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/tiposausencia",
		sourceParam :{
			label: $.rup.lang === 'es' ? "descEs040"
					: "descEu040", 
			value: "id040",
			style:"css"
		}
		,width: "170"
		,ordered: false	
		,rowStriping: true
		,open: function(){
            jQuery('#tipoJornada_detail_table-menu').width(jQuery('#tipoJornada_detail_table-button').width());
        }
	});

	fnFechaDesdeHasta("fechaDesde_detail_table", "fechaHasta_detail_table");
		
	/* ...CALENDARIO ...*/
	$("#calendario_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					 function(){
						ocultarCalendario();
						mostrarAusencias();
					}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.imprimir
				,css: "fa fa-file-pdf-o"
					,right:true
				,click : obtenerPdf
					
			}
		]
	});
	
	
	/*IMPRESION DE CALENDARIO..................*/
	function creaCalendario(){
		var mdy;
		var estilos="";
		$('#previewcalendario').rup_date({ //maxPicks: 6,		
			multiSelect: 8,
			changeMonth : false,
			changeYear	: false,			
			numberOfMonths: [3,4],
			defaultDate: y+'/1/1',
			dateFormat: "yy/mm/dd",
			minDate: y+'/1/1',
			maxDate: y+'/31/12'
			,beforeShowDay: function (date){
				mdy = date.getFullYear() + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' + ("0" + (date.getDate())).slice(-2);
				estilos="";
				if ($.inArray(mdy, arrFestivos) > -1) {		
					estilos = 'ui-state-highlight';
				} else if ($.inArray(mdy, arrJornada) > -1) {			    	
			    	estilos = 'jornadaI';
				} 
				if ($.inArray(mdy, arrVacaciones) > -1) {			    	
					estilos+= ' vacaciones';
				} else if ($.inArray(mdy, arrFormacion) > -1) {			    	
					estilos+= ' formacion';
				} else if ($.inArray(mdy, arrBaja) > -1) {			    	
					estilos+= ' baja';
				} else if ($.inArray(mdy, arrOtros) > -1) {			    	
					estilos+= ' otros';
				} else if ($.inArray(mdy, arrInterpretacion) > -1) {			    	
					estilos+= ' interpretacion';
			    } 
				return [false, estilos];
			}
		});		
		
		
		$('.ui-datepicker-current-day').removeClass('ui-datepicker-current-day');
		$('.ui-datepicker-today').removeClass('ui-datepicker-today');		
		$("a.ui-state-highlight").removeClass('ui-state-highlight');		
		$(".ui-datepicker-prev, .ui-datepicker-next").remove();
	}
	
	
function leerCalendario(paramAnio){		
		var errorAjax = false;
		if ( paramAnio !== undefined)
			y = paramAnio;

		if ($('#previewcalendario div').length >0){//si está el rup
			$('#previewcalendario').rup_date("setRupValue","");
			$('#previewcalendario').rup_date("destroy");
			$('#previewcalendario').multiDatesPicker('destroy');
			var laCapa = $('.calendarioServicio div.calendarioSinActive')[0];	
			$('div.calendarioServicio div.rup-date-input-group').remove();
			$('.calendarioServicio').append(laCapa);
			$('.calendarioServicio').removeAttr('ruptype');
			
		}
		arrJornada = new Array(); //Vacio el array de dias de jornada intensiva
		arrFestivos = new Array();
		arrVacaciones = new Array();
		arrFormacion = new Array();
		arrBaja = new Array();
		arrOtros = new Array();
		arrInterpretacion = new Array();
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/administracion/calendarioservicio/cargaCalendario'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(y)			
		     ,async: false
		     ,cache: false
		   	 ,success:function(calendarioServ){		   		
		   		if (calendarioServ.anyoCalendario!=null) {
		   				//Colorear todos los días de jornada intensiva...
		   			var fechaInicioJornada = new Date(calendarioServ.veranoDesde);
		   			var fechaFinJornada   = new Date(calendarioServ.veranoHasta);
		   			while(fechaFinJornada.getTime() >= fechaInicioJornada.getTime()){
		   				arrJornada.push(fechaInicioJornada.getFullYear() + '/' + ("0" + (fechaInicioJornada.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioJornada.getDate())).slice(-2));		   				
		   				fechaInicioJornada.setDate(fechaInicioJornada.getDate() + 1);		   			
		   			}
		   			
		   			//Carga de los dias festivos de bbdd		   			
		   			calendarioServ.festivosCalendarioServicios.forEach(function(unFestivo) {
		   				arrFestivos.push( unFestivo.diaCalendario023 );
		   			});
		   			mostrarCalendario();		   			
					
		   		} else {
		   			errorAjax = true;
		   			$('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioPersonalAnioSinDatos, "alert");
		  	 		desbloquearPantalla();
		   		}		   				   	
		   	 }
	  	 	,error: function(){
	  	 		errorAjax = true;
	  	 		$('#ausencias_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
	  	 		desbloquearPantalla();
		   	 }
		 });
		if (!errorAjax){
			$.ajax({
			   	 type: 'GET'
			   	 ,url: '/aa79bItzulnetWar/ausenciascalendariopersonal?dni='+$('#dni025_filter_table').val()+'&anyo='+$('#anyo025_filter_table').val()
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     //,data: $.toJSON(jsonObject)			
			     ,async: false
			     ,cache: false
			   	 ,success:function(listaAusencias){		  
			   		 
			   		if (listaAusencias.length>0) {
			   			listaAusencias.forEach(function(ausencia) {
			   				var fechaInicioAusencia = new Date(ausencia.fechaDesde);
				   			var fechaFinAusencia   = new Date(ausencia.fechaHasta);
				   			
			   				if (ausencia.tipoJornada === 'V'){
			   					while(fechaFinAusencia.getTime() >= fechaInicioAusencia.getTime()){					   						   			
					   				arrVacaciones.push(fechaInicioAusencia.getFullYear() + '/' + ("0" + (fechaInicioAusencia.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioAusencia.getDate())).slice(-2));		   				
					   				fechaInicioAusencia.setDate(fechaInicioAusencia.getDate() + 1);		   			
					   			}
			   				}else if (ausencia.tipoJornada === 'F'){
			   					while(fechaFinAusencia.getTime() >= fechaInicioAusencia.getTime()){					   						   			
					   				arrFormacion.push(fechaInicioAusencia.getFullYear() + '/' + ("0" + (fechaInicioAusencia.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioAusencia.getDate())).slice(-2));		   				
					   				fechaInicioAusencia.setDate(fechaInicioAusencia.getDate() + 1);		   			
					   			}
			   				}else if (ausencia.tipoJornada === 'B'){
			   					while(fechaFinAusencia.getTime() >= fechaInicioAusencia.getTime()){					   						   			
					   				arrBaja.push(fechaInicioAusencia.getFullYear() + '/' + ("0" + (fechaInicioAusencia.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioAusencia.getDate())).slice(-2));		   				
					   				fechaInicioAusencia.setDate(fechaInicioAusencia.getDate() + 1);		   			
					   			}
			   				}else if (ausencia.tipoJornada === 'O'){
			   					while(fechaFinAusencia.getTime() >= fechaInicioAusencia.getTime()){					   						   			
					   				arrOtros.push(fechaInicioAusencia.getFullYear() + '/' + ("0" + (fechaInicioAusencia.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioAusencia.getDate())).slice(-2));		   				
					   				fechaInicioAusencia.setDate(fechaInicioAusencia.getDate() + 1);		   			
					   			}
				   			}else if (ausencia.tipoJornada === 'I'){
				   				while(fechaFinAusencia.getTime() >= fechaInicioAusencia.getTime()){					   						   			
				   					arrInterpretacion.push(fechaInicioAusencia.getFullYear() + '/' + ("0" + (fechaInicioAusencia.getMonth() + 1)).slice(-2) + '/' + ("0" + (fechaInicioAusencia.getDate())).slice(-2));		   				
				   					fechaInicioAusencia.setDate(fechaInicioAusencia.getDate() + 1);		   			
				   				}
				   			}
			   			});
			   			 									
			   		} else {
			  	 		$('#calendario_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.calendarioPersonalAnioSinAusencias, "alert");
			  	 		desbloquearPantalla();
			   		}
			   		creaCalendario();												   
			   		desbloquearPantalla();
			   	 }
		  	 	,error: function(){
		  	 		errorAjax = true;
		  	 		$('#calendario_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		  	 		desbloquearPantalla();
			   	 }
			 });
		}
	}
	
	
	function createPDF() {
        getCanvas().then(function(canvas) {
        var
          cal = canvas.toDataURL('image/png'),
          doc = new jsPDF({
              orientation:'landscape',
              unit: 'px',
              format: 'a4',
          });        
         doc.addImage(cal, 'PNG', 25, 65);
         doc.text($.rup.i18nParse($.rup.i18n.app,"aa79bItzulnetWar_migas.calendariopersonal")+" "+anyoActual+" - " + $('#dni_detail_table').rup_combo("label"), 115, 40);
         doc.addImage(img, 'PNG', 25, 15);
         doc.save("calendario.pdf");         
         $('#previsualizardiv').remove();
         desbloquearPantalla();
        });
    }

	// create canvas object
	function getCanvas() {
	        return html2canvas(form, {
	         removeContainer: true
	        });
    }	
	
	function obtenerPdf(event){
        event.preventDefault();
        event.stopImmediatePropagation(); 
        bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.generandoPDF"));
        
        jQuery('.calendarioServicio').append('<div id="previsualizardiv"></div>');        
        $('#previsualizardiv').append($('#previewcalendario').html());
        $('#previsualizardiv').addClass('calendarPDF');
        $('#previsualizardiv').addClass('personal');   
        $('#previsualizardiv').addClass('calendarioSinActive');   
        $('#previsualizardiv').addClass('hasDatepicker');   
        $('#previsualizardiv').append('<div id="prevLeyendaCal" style="border: 1px solid #ccc; padding: 5px 0;height:41px"></div>');
        $('#prevLeyendaCal').append($('#leyendaCal').html());
        
        form = $('#previsualizardiv');         
        createPDF();        
    }
	
	
	
	
	jQuery("#ausencias").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		$('#observDia_detail_table').focus();
		$('#dni025_detail_table').val($('#dni_detail_table').val());
		$('#anyo025_detail_table').val($('#anyo025_filter_table').val());
		
		$("#ausencias").rup_table("updateSavedData", function(savedData){
			savedData["dni"]=$('#dni025_detail_table').val();
			savedData["anyo"]=$('#anyo025_detail_table').val();
		});
	});
	
	
	
	
	
	
	
	
	
	
	//Para quitar el foco del date
	jQuery("#ausencias").off("jqGridAddEditAfterShowForm.rupTable.formEditing");
	jQuery("#ausencias").on("jqGridAddEditAfterShowForm.rupTable.formEditing", function(event){
		 var $self = $(this), settings = $self.data('settings');
       // Ubicamos el foco en el primer elemento del formulario
//       $self.rup_table('hideFormErrors', settings.formEdit.$detailForm);
       jQuery('input:focusable:not(.hasDatepicker):enabled:not([readonly]):first', settings.formEdit.$detailForm).focus();		
	});

});