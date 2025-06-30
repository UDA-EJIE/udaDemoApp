var horasReales;
var esHoraOblig = false;
var indFicheroDoc = false;
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var cargaInicial = true;
var listaTitulosDocs = {};
/*
 * UTILIDADES - INICIO
 */
/**
 * Comprueba si hay cambios en la modal. Si no los hay, se cierra la modal.
 */

function ocultarToolbarXliff(ocultar){
	if(ocultar){
		ocultarElementoPorId("docsXliff_toolbar");
	}
}

function ocultarElementoPorId(id){
	$('#'+id)[0].setAttribute("style","display:none");
}

function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}

function cargarDatosInforme(datosInforme){
	
	if(ejecutarTareaConsulta){
		$('#divFicheroFileCorreciones').hide();
	}

	$('#idFichero').val(datosInforme.idFichero);
	var enlace = '<a href="#" class="descargarFicheroFileCorreciones" >'+datosInforme.nombre+'</a>';	   	
	$('#enlaceDescargaDetalle').show();
		$('#enlaceDescargaDetalle').html(enlace);
		$('a.descargarFicheroFileCorreciones').click(function(event){
			event.preventDefault();	
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			descargarDocumentoGeneral(datosInforme.idFichero);
			desbloquearPantalla();
		});
}


function actualizarValorIndCorrecciones(){
	if(!cargaInicial){
		var indCorrecciones = $('#ind_correcciones_filter').bootstrapSwitch('state')?'S':'N';
		$.rup_ajax({
			type: 'POST'               
	        ,url: '/aa79bItzulnetWar/documentosgeneral/actualizarValorIndCorreccionesAlinear/' + idTarea + '/' + indCorrecciones
	        ,dataType: 'json'
	        ,contentType: 'application/json' 
	        ,async: false
	        ,cache: false
			,success : function(data) {
				
				if(1 != data){
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorActualizandoIndiceCorrecciones, "error");
				}
				
			},
			error : function() {
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorActualizandoIndiceCorrecciones, "error");
			}
		});
	}
}

function formConsulta(){

	
	$.ajax({
		url : '/aa79bItzulnetWar/ejecuciontarea/tareas/findTarea/'+ idTarea,
		success : function(data, textStatus, jqXHR) {
			var newData = JSON.parse(data);
			if (isNotEmpty(newData.observacionesTareas)){
				$("#contenido_form").val(newData.observacionesTareas.obsvEjecucion);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('Error recuperando datos del paso');
		}
	});
	
	$.rup_ajax({
		type: 'GET'               
            ,url: '/aa79bItzulnetWar/documentosgeneral/consultaFicherosAlinear/'+idTarea
          ,dataType: 'json'
          ,contentType: 'application/json' 
         ,async: false
         ,cache: false
		,success : function(data) {
			if(data){
				if(data.ficheroLerroko.idFichero){
					cargarDatosInforme(data.ficheroLerroko);

				}
				
				if (data.indCorreciones == 'N') {
					$("#ind_correcciones_filter").bootstrapSwitch('setState',false);
				} else {
					$("#ind_correcciones_filter").bootstrapSwitch('setState',true);
				}
				cargaInicial = false;
			}
		},
		error : function() {
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", "Error recuperando datos de tarea", "error");
		}
	});
}

function comprobarCambiosFormulario(){
	return true;
}

function finalizarTarea() {
	if ($("#confirmartarea").length) {
		$("#confirmartarea").remove();
		$("#confirmartarea").rup_dialog('destroy');
		$("#divTareasExpedientes").append(
				'<div id="confirmartarea" style="display:none"></div>');
	}

	$("#confirmartarea").confirmar_tarea({
		esHoraOblig : esHoraOblig,
		tieneHora : true,
		documentos : false,
		modalSelector : "ejecutarTareaDialog"
	});
	$("#confirmartarea").confirmar_tarea("open");
}

/*
 * UTILIDADES - FIN
 */

// Ekaitz
function comprobarExtensionValida(nombreFichero) {
	var extension = nombreFichero.substring(nombreFichero.lastIndexOf(".") + 1,
			nombreFichero.length);
	var extensionOK = false;
	
	var jsonObject = {
		"formato011" : extension
	};
	$.ajax({
		type : 'GET',
		url : '/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/comprobarextension',
		dataType : 'json',
		contentType : 'application/json',
		data : jsonObject,
		async : false,
		cache : false,
		success : function(extensionValida) {
			
			if (extensionValida > 0) {
				extensionOK = true;
			}
		},
		error : function() {
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			extensionOK = false;
		}
	});
	return extensionOK;
}

function subirFichero(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));

	var laExtension  = $("#ficheroFinal").val().substring($("#ficheroFinal").val().lastIndexOf('.')+1, $("#ficheroFinal").val().length);
	
	if (comprobarExtensionValida($('#ficheroFinal').val())) {
		$("#subidaFicheroPidFinal_form").submit();
	}else{ 
		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
		desbloquearPantalla();
	}
	
}

$('#subidaFicheroPidFinal_form').rup_form({
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
				   			//insercion en bbdd 
				   			var jsonObjectFichero ={
				   					"contentType" : data.contentType,
				   					"encriptado" : data.encriptado,
				   					"extension" : data.extension,
				   					"nombre" : data.nombre,
				   					"oid" : data.oid,
				   					"tamano" : data.tamano
				   			} 
				   			subidaTarea = {
				   					"idDocOriginal":0,
				   					"idTarea": idTarea,
				   					"idIdiomaDest":$('#idiomaDest').val() ,
				   					"indFicheroDoc":indFicheroDoc ,
				   					"indCorrecciones":$('#ind_correcciones_filter').bootstrapSwitch('state')?'S':'N' ,
				   					"fichero": jsonObjectFichero
				   			}
				   			$.ajax({
				   				type: 'POST'
				   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroLerrokoa'
				   			 	 ,dataType: 'json'
				   			 	 ,contentType: 'application/json' 
				   			     ,data: $.toJSON(subidaTarea)		
				   			     ,success: function (data){
			   						cargarDatosInforme(data);
			   						$('#nombreFichero').rules("remove","required");
				   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");

				   					$("#fichero").val('');
				   					desbloquearPantalla();
				   			    	 
				   			     },error: function (data){
				   			    	desbloquearPantalla();
				   			    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
				   			     }
				   			});
				   		}else{
				   			desbloquearPantalla();
				   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
						}
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
				   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   	 }
				 });
			}else{//subida incorrecta
	   			desbloquearPantalla();
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", archivoPIF.error, "error");
			}
			
		 }
		, error: function(data){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
			desbloquearPantalla();
		}
});
// fin Ekaitz

jQuery(function($) {
//	
//	$("#ejecutarTareaDialog_form").rup_validate({
//		feedback: $('#ejecutarTareaDialog_feedback'),
//		showFieldErrorAsDefault: false,
//		showErrorsInFeedback: false,
//		showFieldErrorsInFeedback: false,
//		rules:{
//			"nombreFichero": {required:true}
//		}
//	});
	
	$("#ind_correcciones_filter").on('switch-change', function(event, state) {
	    actualizarValorIndCorrecciones();
	});
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons : [ {
			id: "volver",			
			i18nCaption : $.rup.i18n.app.boton.volver,
			css : "fa fa-arrow-left",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				$("#ejecutarTareaDialog").rup_dialog("close");
			}
		}, {
			i18nCaption : $.rup.i18n.app.boton.finalizarTarea,
			css: "fa fa-save", 
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				bloquearPantalla();
				// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
				if (comprobarTareaNoEjecutada(idTarea)){
					if($("#ejecutarTareaDialog_form").valid()){
						if ($('#ind_correcciones_filter').bootstrapSwitch('state')?'S':'N' === "S"){
	                		comprobacionPreviaXliffAlinearPostEntrega(idTarea,"ejecutarTareaDialog_feedback","finalizarTarea");
	            		}else{
	            			finalizarTarea();
	            		}
					}
				}
			}
		} ]
	});

	if (horasObligatorias === 'S') {
		esHoraOblig = true;
	}
	
	$("#confirmartarea").confirmar_tarea({
		esHoraOblig : esHoraOblig,
		tieneHora : true,
		documentos : false,
		modalSelector : "ejecutarTareaDialog"
	});
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalTareaMejorasPostEntrega").rup_tabs({
		tabs : [
			{i18nCaption: labelLerrokoa, layer:"#pestLerrokoa"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});	

	$("#ind_correcciones_filter").bootstrapSwitch().bootstrapSwitch(
			'setSizeClass', 'switch-small').bootstrapSwitch('setOffLabel',
			$.rup.i18n.app.comun.no).bootstrapSwitch('setOnLabel',
			$.rup.i18n.app.comun.si);
	
	$("#ficheroFinal").change(function(){
		if ($("#ficheroFinal").val() !== ''){
			subirFichero();
		}
	});
	
	$("#pidButtonFile").on("click", function() {
		$('#ficheroFinal').click();
	});
	
	$("#ejecutarTareaDialog_form").rup_form({
		url : "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		feedback : $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault : false,
		showErrorsInFeedback : true,
		showFieldErrorsInFeedback : false,
		dataType : "json",
		type : "POST",
		beforeSubmit : function() {
			$("#anyo_form").val(anyoExpediente);
			$("#numExp_form").val(idExpediente);
			$("#idTarea_form").val(idTarea);
			$("#idTipoTarea_form").val(idTipoTarea);
			
		},
		success : function() {
			ejecutarTareaConsulta = true;
			ejecutarTareaReturn(false, "ejecutarTareaDialog",
					tablaSelector, null,
					"tareasExpedientes_feedback");
			desbloquearPantalla();
		},
		error : function() {
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null,
					null, null);
			desbloquearPantalla();
		}
	});
	
	/* PESTAÑA XLIFF */
	$("#docsXliff_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.adjuntar
				,css: "fa fa-file-o"
				,click : 
					function(e){
                    e.preventDefault();
                    e.stopImmediatePropagation();
                    $("#idTareaXliff").val(idTarea);
                	$('#ficheroXliff').click();
				}
			},
			{
				i18nCaption: $.rup.i18n.app.comun.eliminar
				,css: "fa fa-trash-o"
				,click : 
					function(e){
                    e.preventDefault();
                    e.stopImmediatePropagation();
                    confirmEliminarDocXliff();
				}
			}
		]
	});
	
	function confirmEliminarDocXliff(){
		var selectedRows = $("#docsXliff").rup_table('getSelectedRows');
		if (isEmpty(selectedRows)){
			$.rup_messages("msgAlert", {
				title: $.rup.i18nParse($.rup.i18n.app,"comun.eliminar"),
				message: $.rup.i18n.app.comun.warningSeleccion
			});	
			return false;
		 }
		 var laFilaSeleccionada = $("#docsXliff").rup_table("getRowData", selectedRows[0]);
		 if(laFilaSeleccionada["idTarea"] != idTarea){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.docuXliffNoPerteneceATarea, "error");
		 } else{
			 $.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
					OKFunction: function(){
						eliminarDocXliff( laFilaSeleccionada["documentoAdjunto.idFichero"], laFilaSeleccionada["documentoAdjunto.oid"]);
					}
				});
		 }
	}
	
	function eliminarDocXliff( elIdDocXliff, elOid){
		$.ajax({
	        type: 'DELETE' 
	        ,url: '/aa79bItzulnetWar/documentosgeneral/eliminarDocXliff/'+idTarea+'/'+elIdDocXliff+'/'+elOid
	        ,dataType: 'json' 
	        ,contentType: 'application/json' 
	        ,beforeSend: function() {
	        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"));
	        }
	        ,success:function(){
	        	$("#docsXliff").trigger("reloadGrid");
	        	$("#docsXliff").rup_table("resetSelection");
	        	$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.borradoCorrecto, "ok");
	        	desbloquearPantalla();
	        }
			,error: function(error){
				$('#ejecutarTareaDialog_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
				desbloquearPantalla();
		   	 }
		});
	}
	
	function mostrarInfoDocXliff(cellval, opts, rowObject){
		var celda =	'<div class="form-group col-lg-12 no-padding">';
		
		if (rowObject.documentoAdjunto.idFichero != null){
			celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="flotaIzda document-eusk" >';
			celda+= rowObject.documentoAdjunto.titulo;
			celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
		}
		celda+='</div>';
		return celda;

	} 
	
	function mostrarGroupByDocsXliff(cellval, opts, rowObject){
		groupIdPrefix = opts.gid + "ghead_";
	    groupIdPrefixLength = groupIdPrefix.length;
	    prefix = opts.rowId.substr(0, groupIdPrefixLength);
	    var cell;
	    
	    if("docsXliffghead_" !== prefix){
	    	idTareaFormat = rowObject.idTareaAgrupacion;
	    	cell = "";
	    	var groupByText = rowObject.groupBy;
			cell = cell.concat('<b>').concat(groupByText).concat(' </b>');
			
			listaTitulosDocs[idTareaFormat] = { value: cell };
			
			return cell;
	    }else{
	    	cell = listaTitulosDocs[cellval].value;
	    }
		
	    return cell;

	}
	
	$('#reqEncriptadoXliff').val(expedienteConfidencial);
	$('#idTarea_filter_Xliff').val(idTarea);
	$('#anyo_filter_Xliff').val(anyoExpediente);
	$('#numExp_filter_Xliff').val(idExpediente);

	$("#docsXliff").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/traducir/xliff",
		toolbar:{
			id: "docsXliff_toolbar"
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
			"",
			"",
			labelDocXliff
		],
		colModel: [
			{ 	name: "idTarea", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.idFichero", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.encriptado", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.oid", 
				hidden: true
			},
			{ 	name: "idTareaAgrupacion", 
				hidden: false,
				width: "20", 
				align: "center",
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarGroupByDocsXliff(cellval, opts, rowObject);
				}
			},
			{ 	name: "documentoAdjunto.titulo", 
				index: "TITULO_FICHERO_088",
				align: "left", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocXliff(cellval, opts, rowObject);
				}
			}
        ],
        model:"DocumentoTarea",
        usePlugins:[
        	"feedback",
        	"toolbar",
       		 "filter",
       		 "fluid",
       		 "responsive"
       		 ],  
     	loadComplete: function(){ 
     		habilitarPager('docsXliff');
        	$("#pg_docsXliff_pager").find('td.pagControls select').hide();
        },
        grouping: true,
		groupingView: {
			groupField: ["idTareaAgrupacion"],
		    groupColumnShow : [false],
		    isInTheSameGroup: function (x, y) {
		        return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		},
		primaryKey: ["idTarea"],
		multiplePkToken:",",
		sortname : "IDTAREAAGRUPACION",
		sortorder : "asc",
		rowNum: 5,
		gridComplete: function () {
			ocultarElementoPorId("docsXliff_feedback");
			//ocultar toolbar xliff si estamos en modo consulta
			ocultarToolbarXliff(ejecutarTareaConsulta);
		}
	});
	
	// SUBIDA PIF/PID T96 (DOCS XLIFF)
	
	$('#subidaFicheroPidXliff_form').rup_form({
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
					   			//insercion en bbdd 
					   			var jsonObjectFichero ={
					   					"contentType" : data.contentType,
					   					"encriptado" : data.encriptado,
					   					"extension" : data.extension,
					   					"nombre" : data.nombre,
					   					"oid" : data.oid,
					   					"tamano" : data.tamano
					   			} 
					   			
					   			subidaTarea = {
					   					"idTarea": idTarea,
					   					"fichero": jsonObjectFichero
					   			}
					   			$.ajax({
					   				type: 'POST'
					   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroXliff'
					   			 	 ,dataType: 'json'
					   			 	 ,contentType: 'application/json' 
					   			     ,data: $.toJSON(subidaTarea)		
					   			     ,success: function (data){
					   			    	//Subida correcta
											//llamar al filter para que recargue la tabla con el nuevo registro
											$("#docsXliff").trigger("reloadGrid");
								        	$("#docsXliff").rup_table("resetSelection");
								        	$("#ejecutarTareaDialog_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
								        	$("#ficheroXliff").val('');
								        	desbloquearPantalla();
					   			    	 
					   			     },error: function (e){
					   			    	desbloquearPantalla();
					   			    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
					   			     }
					   			});
					   			
					   			
					   		}else{
					   			desbloquearPantalla();
					   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
							}
					   	 }
				   	 	,error: function(){
				   	 		desbloquearPantalla();
					   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
					   	 }
					 });
				}else{//subida incorrecta
		   			desbloquearPantalla();
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", archivoPIF.error, "error");
				}
				desbloquearPantalla();
			 }
			, error: function(data){
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
				desbloquearPantalla();
			}
	});
	
	$("#ficheroXliff").change(function(){
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		if ($("#ficheroXliff").val() !== ''){
			if (comprobarExtensionValidaXliff($('#ficheroXliff').val())){
				$("#subidaFicheroPidXliff_form").submit();
	    	}else{ 
	    		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"validaciones.extXliffTmx"), "error");
	    		desbloquearPantalla();
	    	}
		}
	});
	
	//Para obligar a que sean encriptados o no en función de la confidenciallidad del expediente
	$('#reqEncriptado').val(expedienteConfidencial);
		
	formConsulta();
});