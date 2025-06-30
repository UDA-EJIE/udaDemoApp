var esHoraOblig = false;
var cambios;
var confidencial = false;
var extension = "";
var nombreDocOriginal = "";
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var tipoErrorDoc = 0;
var listaTitulosDocs = {};

function ocultarToolbarXliff(ocultar){
	if(ocultar){
		ocultarElementoPorId("docsXliff_toolbar");
	}
}

function ocultarElementoPorId(id){
	$('#'+id)[0].setAttribute("style","display:none");
}

function mostrarInfoDocOriginal(cellval, opts, rowObject){
	var celda = '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumentoGeneral('+rowObject.documentoOriginal.idDoc+')" class="document-eusk iconSameLine" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoOriginal.ficheros[0].nombre + '</a>';
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
	if(rowObject.documentoOriginal.ficheros[0].encriptado === 'S'){
		confidencial = true;
	}
	if(tituloYNombreDiferentes(rowObject.documentoOriginal.titulo,rowObject.documentoOriginal.ficheros[0].nombre )){
		celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.documentoOriginal.idDoc+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
	}
	celda += '</p>';
	return celda;
}

function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+idFichero);//56  1 TiposubidaEnum
}

function descargarFicheroTarea(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);//88  2
}

function mostrarServicioEncriptacion(){
	if(!confidencial){
		$('#ejecutarTareaDialog_toolbar-rightButtons').hide();
	}
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

function mostrarInfoDocFinal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',\''+rowObject.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:3px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';	
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoAdjunto.idFichero +')" class="document-cast iconSameLine" >';
		celda+= rowObject.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;
}

function mostrarIconoOriginal(idClaseDocumento){
	if(idClaseDocumento){
		if(idClaseDocumento == 1){
			//clase documento traducir
			return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file"></i></span>';
		}else if(idClaseDocumento == 4){
			//clase documento trabajo
			return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file-text"></i></span>';
		}
	}
	return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file"></i></span>';
}

function clickPidButtonFinal(idDocO, laExtension, elReqEncriptado, nomDocOriginal){
	extension = laExtension;
	idDocOriginal = idDocO;
	nombreDocOriginal = nomDocOriginal;
	$('#reqEncriptado').val(elReqEncriptado);
	reqEncriptado = elReqEncriptado;
	$('#fichero').click();
}

function comprobarExtensionValida( nombreFichero){
	tipoErrorDoc = 0;
	var extensionAdjunto = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	var extensionOK = false;
	if("zip" === extension.toLowerCase()){
		if(extension.toLowerCase() == extensionAdjunto.toLowerCase()){
			extensionOK =  true;
		}else{
			//tipo de error: el documento adjunto tiene que tener la misma extensión que el original(.zip)
			tipoErrorDoc = 1;
		}
	}else{
		if(comprobarExtensionConBBDD(extensionAdjunto, "ejecutarTareaDialog_feedback")){
			extensionOK =  true;
		}else{
   			//el documento no esta admitido
   			tipoErrorDoc = 2;
		}
	}
	return extensionOK;	
}

$("#fichero").change(function(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	if ($("#fichero").val() !== ''){
		if (comprobarExtensionValida($('#fichero').val())){
			if(esNombreDocumentoTradRevValido("fichero",nombreDocOriginal, idiomaDestino)){
				$("#subidaFicheroPid_form").submit();
			} else{
				mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.errorNombreDocTradRev"), concatenarNombreDocOriginalIdiomaDestYExt(nombreDocOriginal,idiomaDestino,"fichero")), "error");
				$("#fichero").val('');
    			desbloquearPantalla();
			}
    	}else{ 
    		if(tipoErrorDoc === 1){
    			//documento adjunto debe tener la misma extension que opriginal (zip)
    			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.zipObligatorio, "error");
    		}else{
    			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
    		}
			$("#fichero").val('');
    		desbloquearPantalla();
    	}
	}
});

/* PID PIF - INICIO */
$('#subidaFicheroPid_form').rup_form({
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
			   					"idDocOriginal":idDocOriginal,
			   					"idTarea": idTarea,
			   					"idTablaIntermedia": tablaIntermedia.tabla87,
			   					"idIdiomaDest":$('#idiomaDest').val() ,
			   					"fichero": jsonObjectFichero
			   			}
			   			
			   			
			   			
			   			$.ajax({
			   				type: 'POST'
			   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroFinal'
			   			 	 ,dataType: 'json'
			   			 	 ,contentType: 'application/json' 
			   			     ,data: $.toJSON(subidaTarea)		
			   			     ,success: function (data){
			   			    	 mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
			   			    	 $("#docsTraducir").rup_table("filter");
			   			    	 
			   			     },error: function (e){
			   			    	desbloquearPantalla();
			   			    	mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   			     }
			   			});
			   			
			   			
			   		}else{
			   			desbloquearPantalla();
			   			alert("subida incorrecta al pid: " + data.error);
					}
			   	 }
		   	 	,error: function(){
		   	 		desbloquearPantalla();
		   	 		mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   	 }
			 });
		}else{//subida incorrecta
   			desbloquearPantalla();
   			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", archivoPIF.error, "error");
		}
		$("#fichero").val(""); 
	 }
	, error: function(archivoPIF){
		desbloquearPantalla();
		mostrarMensajeFeedback("ejecutarTareaDialog_feedback", archivoPIF.error, "error");
	}
});

function comprobarCambiosFormulario(){
	return true;
}

function finalizarTarea(){
	bloquearPantalla();
	// validar documentos antes de finalizar
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/validarDocumentosTareaFinalizar/'+idTarea+'/'+idTipoTarea
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,async: false
	     ,cache: false
	   	 ,success:function(documentosValidos){
	   		if (documentosValidos){
	   			if($("#confirmartarea").length){
	   				$("#confirmartarea").remove();
	   				$("#confirmartarea").rup_dialog('destroy');		
	   				$("#divTareasExpedientes").append('<div id="confirmartarea" style="display:none"></div>');
	   			}
	   			
	   			$("#confirmartarea").confirmar_tarea({esHoraOblig:esHoraOblig , tieneHora: true, modalSelector: "ejecutarTareaDialog"});
	   			$("#confirmartarea").confirmar_tarea("open");
	   		}else{
	   			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.tareaTraduccionFaltaFicheroTraduccion, "error");
	   		}
	   		desbloquearPantalla();
	   	 }
  	 	,error: function(){
  	 		mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.tareaTraduccionErrorValidandoDocumentos, "error");
  			desbloquearPantalla();
	   	 }
	 });		
}

function servicioEncriptar(){
	window.open('/aa79bItzulnetWar/servicios/trataFicherosConfi/encripFicheros/maint');
}

jQuery(function($) {
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}
	cambios = false;
	$("#ejecutarTareaDialog_form").rup_form({
		url: "/aa79bItzulnetWar/ejecuciontarea/finalizarTarea",
		feedback: $('#ejecutarTareaDialog_feedback'),
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false,
		dataType: "json",
		type: "POST",
		beforeSubmit: function(){
			$("#anyo_form").val(anyoExpediente);
			$("#numExp_form").val(idExpediente);
			$("#idTarea_form").val(idTarea);
			$("#idTipoTarea_form").val(idTipoTarea);
		},
		success: function(){
			//Cerrar el diálogo, mostrar feedback y recargar la pantalla o la tabla
			ejecutarTareaReturn(false, "ejecutarTareaDialog", tablaSelector, null, "tareasExpedientes_feedback");
			desbloquearPantalla();
		},
		error: function(error){
			//mostrar feedback de error y no cerrar la pestaña
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null, null, null);
			desbloquearPantalla();
		}
	});
	
	$('#idTarea_filter').val(idTarea);
	
	$("#ejecutarTareaDialog_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,id: "volver"	
				,css: "fa fa-arrow-left"
				,click : 
					 function(e){
					 	e.preventDefault();
	                    e.stopImmediatePropagation();
	                	$("#ejecutarTareaDialog").rup_dialog("close");
					}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.finalizarTarea
				,css: "fa fa-save"
				,click : 
					 function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
						// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
						if (comprobarTareaNoEjecutada(idTarea)){
		                    comprobacionPreviaXliffAlinearPostEntrega(idTarea, "ejecutarTareaDialog_feedback", "finalizarTarea");
						}
					}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.servicioDeEncriptacion,right : true
				,click:
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    servicioEncriptar();
				}
			}
		]
	});
	idiomaOrigen = '';
	if (idiomaDestino == "1"){
		idiomaOrigen = 1;
		$('#idiomaDest').val(labelES);
	}else{
		idiomaOrigen = 2;
		$('#idiomaDest').val(labelEU);
	}
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$('#docsTraducir_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalTraduccion").rup_tabs({
		tabs : [
			{i18nCaption: labelDocusEntregar, layer:"#pestDocsTraducir"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});	
	
	/* RUP_TABLES */
	$("#docsTraducir").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/traducir",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			labelDocTraducir +" ("+(idiomaOrigen=="1"?labelEuskera:labelCastellano)+")",
			labelDocTraducido +" ("+(idiomaDestino=="2"?labelEuskera:labelCastellano)+")"
		],
		colModel: [
			{ 	name: "idTarea", 
				hidden: true
			},
			{ 	name: "documentoOriginal.idDoc", 
				hidden: true
			},
			{ 	name: "documentoOriginal.titulo", 
				hidden: true
			},
			{ 	name: "documentoOriginal.ficheros[0].encriptado", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.idFichero", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.titulo", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.encriptado", 
				hidden: true
			},
			{ 	name: "documentoOriginal.claseDocumento", 
				hidden: false,
				width: "15", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarIconoOriginal(cellval);
				}
			},			
			{ 	name: "docOriginalConcatenado" ,
				align: "left", 
				width: "300", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocOriginal(cellval, opts, rowObject);
				}
			},
			{ 	name: "docFinalConcatenado", 
				align: "left", 
				width: "300",
				editable: false, 
				fixed: true,  
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocFinal(cellval, opts, rowObject);
				}
			}
        ],
        model:"DocumentoTarea",
        usePlugins:[
        	"feedback",
        	"toolbar",
       		 "filter",
       		 "fluid"
         	],        
		primaryKey: ["ID_TAREA_083", "ID_DOC_083"],
		multiplePkToken:",",
		sortname : "ID_DOC_056",
		sortorder : "asc",
		rowNum: 5,
		loadComplete: function(){
			habilitarPager('docsTraducir');
     		$('#pg_docsTraducir_pager').find('td.pagControls select').hide();
     		desbloquearPantalla();
        },
		shrinkToFit:false,
		beforeSelectRow: function (){
			return false;
		},
		gridComplete: function () {
			if(ejecutarTareaConsulta){
				//ocultar los botones de adjuntar archivo
				ocultarBotonesPid();
			}
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
		 } else {
			 $.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
					OKFunction: function(){
						eliminarDocXliff(laFilaSeleccionada["documentoAdjunto.idFichero"], laFilaSeleccionada["documentoAdjunto.oid"]);
					}
				});
		 }
	}

	function eliminarDocXliff(elIdDocXliff, elOid){
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
			celda+='<div class="displayAsTable">';
			celda+= '<p class="document-fileAndIcon">';
			celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
			celda+= rowObject.documentoAdjunto.titulo + '</a>';
			celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' ;
			celda+= '</p>';
			celda+='</div>';
		}
		celda+='</div>';
		return celda;

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
	
	mostrarServicioEncriptacion();
});