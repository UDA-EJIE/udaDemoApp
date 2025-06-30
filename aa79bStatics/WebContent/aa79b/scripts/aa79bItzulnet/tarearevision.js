var esHoraOblig = false;
var confidencial = false;
var comprobarNombre = false;
var extension = "";
var nombreDocOriginal = "";
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var tablaIntermediaDestino;
var oidDocRevisado = "-1";
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

function descargarDocumento(elIdDoc){			
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+elIdDoc);
}

function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}

function eliminarJustificante(idDocOriginal){
	$.rup_messages("msgConfirm", {
		title: $.rup.i18n.app.comun.eliminar,
		message: $.rup.i18n.app.mensajes.preguntaEliminarFichero,
		OKFunction: function(){
			bloquearPantalla();
			$.ajax({
			   	 type: 'POST'		   	 
			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/eliminarJustificante/'+idDocOriginal+'/'+idTarea
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,async: false
			     ,cache: false
			   	 ,success:function(extensionValida){
			   		 desbloquearPantalla();
			   		$('#docsRevisar').rup_table("filter");
			   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.eliminadoCorrecto, "ok");
			   	 }
		 	 	,error: function(){
		 	 		desbloquearPantalla();
			   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
			   		extensionOK =  false;
			   	 }
			 });
		},
		CANCELFunction: function(e){
			return false;
		}
	});
	
}

/**
 * Guarda el documento a revisar a documento revisado final
 * @param elIdDoc
 * @returns
 */
function pasarDocumentoARevisarAFinal(idDocOriginal,idDocRevisado){
	if(!ejecutarTareaConsulta){
		//si no tiene idDocRevisado es porque el documento original es un zip. Pasamos a final el original
		if(!idDocRevisado){
			idDocRevisado = idDocOriginal;
		}
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/documentosgeneral/pasarDocumentoARevisarAFinal/'+idDocOriginal+'/'+idDocRevisado+'/'+idTarea+'/'+$('#idiomaDest').val()
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,async: false
		     ,cache: false
		   	 ,success:function(extensionValida){
		   		$('#docsRevisar').rup_table("filter");
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
		   	 }
	  	 	,error: function(){
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		extensionOK =  false;
		   	 }
		 });
	}
}

/**
 * Muestra links de columna 'documento a revisar' de documentos originales
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocOriginal(cellval, opts, rowObject){
	var celda = '<div style="min-height: 25px;">';
	celda += '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumento('+rowObject.documentoOriginal.idDoc+')" class="document-eusk iconSameLine" style="margin-bottom:4px;" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoOriginal.ficheros[0].nombre + '</a>';
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' ;
	if(tituloYNombreDiferentes(rowObject.documentoOriginal.titulo,rowObject.documentoOriginal.ficheros[0].nombre )){
		celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.documentoOriginal.idDoc+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
	}
	celda += '</p></div>';
	if (rowObject.documentoOriginal.ficheros[1].idDocRel){
		celda += '<p class="document-fileAndIcon">';
		celda+='<a href="#" onclick="descargarDocumento('+rowObject.documentoOriginal.ficheros[1].idDocRel+')" class="document-eusk iconSameLine" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
		celda+= rowObject.documentoOriginal.ficheros[1].nombre + '</a>' ;
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[1].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[1].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' ;
		celda += '</p>';
	}
	if(rowObject.documentoOriginal.ficheros[0].encriptado === 'S'){
		confidencial = true;
	}
	return celda;
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

/**
 * Muestra botones de columna 'documento revisado' y en el caso de que hubiera un fichero, un link de descarga del mismo
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocFinal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding" style="clear: both;">';
	if(esTecnicoIzo){
		celda+='<div class="displayAsTable">';
		celda+= '<button id="pidButtonFinal__" type="button" onclick="pasarDocumentoARevisarAFinal('+rowObject.documentoOriginal.idDoc+','+rowObject.documentoOriginal.ficheros[1].idDocRel+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.docARevisarAFinal")+'</button>';
		celda+='</div>';
	}
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',\''+rowObject.documentoOriginal.ficheros[1].oid+'\',\''+rowObject.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" >'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
		celda+= rowObject.documentoAdjunto.nombre + ' </a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' ;
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;
}


/**
 * Muestra botones de columna 'justificante de revision' y en el caso de que hubiera un fichero, un link para eliminar el mismo
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarinfoJustifRevision(cellval, opts, rowObject){
	var celda = '<div class="form-group col-lg-12 no-padding" style="clear: both;display:table;" >';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal____" type="button" onclick="clickPidButtonFinalJust('+rowObject.documentoOriginal.idDoc+')" class="btnPID ui-button ui-corner-all ui-widget no-obligatorio" >'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	if(rowObject.documentoJustificante.idFichero != null){
		celda+= '<a href="#" onclick="eliminarJustificante('+rowObject.documentoOriginal.idDoc+')" class="flotaIzda rup-enlaceCancelar eliminarFichero" style="margin-top: 3px;" >'+$.rup.i18n.app.comun.eliminar+'</a>';
		celda+='</div>';	
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoJustificante.idFichero +')" class="document-eusk iconSameLine">';
		celda+= rowObject.documentoJustificante.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoJustificante.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoJustificante.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';	
		celda+='</div>';	
	} else{
		celda+= '</div>';
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

function clickPidButtonFinal(idDocO, laExtension, elReqEncriptado, elOid, nomDocOriginal){
	oidDocRevisado = elOid;
	extension = laExtension;
	nombreDocOriginal = nomDocOriginal;
	tablaIntermediaDestino = tablaIntermedia.tabla87;
	idDocOriginal = idDocO;
	comprobarNombre = true;
	$('#ficheroFinal').click();
}

function clickPidButtonFinalJust(idDocO){
	oidDocRevisado = "-1";
	tablaIntermediaDestino = tablaIntermedia.tabla93;
	idDocOriginal = idDocO;
	comprobarNombre = false;
	$('#ficheroFinal').click();
}


function comprobarExtensionValida( nombreFichero){
	tipoErrorDoc = 0;
	var extensionOK = false;
	var extensionAdjunto = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	if(tablaIntermedia.tabla87 == tablaIntermediaDestino){
		if("zip" === extension.toLowerCase()){
			if(extension.toLowerCase() == extensionAdjunto.toLowerCase()){
				extensionOK =  true;
			}else{
				//tipo de error: el documento adjunto tiene que tener la misma extensión que el original(.zip)
				tipoErrorDoc = 1;
			}
		}
	}
	if(tipoErrorDoc != 1){
		if(comprobarExtensionConBBDD(extensionAdjunto, "ejecutarTareaDialog_feedback")){
			extensionOK =  true;
		}else{
   			//el documento no esta admitido
   			tipoErrorDoc = 2;
		}
	}
	return extensionOK;	
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
				   					"idDocOriginal":idDocOriginal,
				   					"idTarea": idTarea,
				   					"idTablaIntermedia": tablaIntermediaDestino,
				   					"idIdiomaDest":$('#idiomaDest').val() ,
				   					"fichero": jsonObjectFichero,
				   					"oidDocRevisado": oidDocRevisado 
				   			}
				   			$.ajax({
				   				type: 'POST'
				   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroFinal'
				   			 	 ,dataType: 'json'
				   			 	 ,contentType: 'application/json' 
				   			     ,data: $.toJSON(subidaTarea)		
				   			     ,success: function (data){
				   			    	desbloquearPantalla();
				   			    	$('#docsRevisar').rup_table("filter");
				   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
				   			    	 
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
			$('#ficheroFinal').val(""); 
			desbloquearPantalla();
		 }
		, error: function(data){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
			desbloquearPantalla();
		}
});

$("#ficheroFinal").change(function(){
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	if ($("#ficheroFinal").val() !== ''){
		if (comprobarExtensionValida($('#ficheroFinal').val())){
			if(comprobarNombre){
				if(esNombreDocumentoTradRevValido("ficheroFinal",nombreDocOriginal, idiomaDestino)){
					$("#subidaFicheroPidFinal_form").submit();
				} else{
					mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.errorNombreDocTradRev"), concatenarNombreDocOriginalIdiomaDestYExt(nombreDocOriginal,idiomaDestino,"ficheroFinal")), "error");
					$("#ficheroFinal").val('');
	    			desbloquearPantalla();
				}	
			} else {
				$("#subidaFicheroPidFinal_form").submit();
			}
    	}else{ 
    		if(tipoErrorDoc === 1){
    			//documento adjunto debe tener la misma extension que opriginal (zip)
    			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.zipObligatorio, "error");
    		}else{
    			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
    		}
    		desbloquearPantalla();
    	}
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
	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionFaltaFicheroTraduccion, "error");
	   		}
	   		desbloquearPantalla();
	   	 }
  	 	,error: function(){
  	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionErrorValidandoDocumentos, "error");
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
	            			finalizarTarea();
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
		idiomaOrigen = 2;
		$('#idiomaDest').val(labelES);
	}else{
		idiomaOrigen = 1;
		$('#idiomaDest').val(labelEU);
	}
	
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	$('#docsRevisar_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalRevision").rup_tabs({
		tabs : [
			{i18nCaption: labelDocusRevisar, layer:"#pestDocsRevisar"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});	
	
	/* RUP_TABLES */
	$("#docsRevisar").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/revisar",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			labelDocRevisar +" ("+(idiomaOrigen=="1"?labelEuskera:labelCastellano)+")",
			labelDocRevisado,
			labelJustificanteRevision
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
				width: "10", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarIconoOriginal(cellval);
				}
			},			
			{ 	name: "docOriginalConcatenado" ,
				align: "left", 
				width: "100", 
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
			},
			{
				name: "",
				align: "left",
				width: "200",
				editable: false,
				fixed: true,
				hidden: false,
				resizable: true,
				sortable: false,
				formatter: function (cellval, opts, rowObject, action){
					return mostrarinfoJustifRevision(cellval, opts, rowObject);
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
		primaryKey: ["idTarea", "documentoOriginal.idDoc"],
		multiplePkToken:",",
		sortname : "IDDOC",
		sortorder : "asc",
		rowNum: 5,
		loadComplete: function(){ 
			habilitarPager('docsRevisar');
     		$('#pg_docsRevisar_pager').find('td.pagControls select').hide();
        },
		beforeSelectRow: function (){
			return false;
		},
		gridComplete: function () {
			if(ejecutarTareaConsulta){
				//ocultar los botones de adjuntar archivo
				ocultarBotonesPid();
				//ocultar enlaces de eliminar ficheros
				$('.eliminarFichero').hide();
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
			celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
			celda+= rowObject.documentoAdjunto.titulo + '</a>';
			celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
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
	$('#reqEncriptado').val(expedienteConfidencial);
	reqEncriptado = expedienteConfidencial;
});
