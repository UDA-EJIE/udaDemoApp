var esHoraOblig = false;
var cambios;
var confidencial = false;
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};
var documentosSelect = '';
var initFormCorreccionProv = '';
var initFormNoConformidad = '';
var idTareaRelNoConformidad = 0;
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var tablaIntermediaDestino;
var idTareaFormatter;
var listaTitulos = {};
var listaTitulosDocs = {};
var tipoErrorDoc = 0;
var necesitaFirmarDocs = false;
var necesitaEnviarP43 = false;
var idiomaOrigen = '';
var docOriginal = false;
var zip = false;
var nombreDocOriginal = "";
var comprobarNombre = false;

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


function mostrarServicioEncriptacion(){
	if(!confidencial){
		$('#ejecutarTareaDialog_toolbar-rightButtons').hide();
	}
}

function cambiarVisibilidad93(idDocOriginal, estado, codTarea, idRupTable){
	var jsonObject = 
	{
		"idTarea": codTarea, 
		"idDocOriginal": idDocOriginal,
		"indVisible": (estado?"S":"N")
	};
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/documentosgeneral/cambiarvisibilidad93' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(jsonObject) 
        ,async: false 
        ,success:function(){
        	$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
        }
    	,error: function(){
	   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		if(estado){
				$(this).bootstrapSwitch('setState', false);
			}else{
				$(this).bootstrapSwitch('setState', true);
			}
	   	 }
	});
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
			   		$('#docsOriginales').trigger("reloadGrid");
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
//?TODO?justificante documento revisado no va a borrarlo - comprobar
function eliminarDocumentoRevisado(idDocOriginal){
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
			   		$('#docsRevisionEntregaClte').trigger("reloadGrid");
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
function pasarDocumentoARevisarAFinal(idDocOriginal,idDocRevisado, event){
	event.preventDefault();
	event.stopImmediatePropagation();
	pasarDocARevisarAFinal(idDocOriginal,idDocRevisado,idTarea,'docsOriginales');
}

/**
 * Guarda el documento a revisar a documento revisado final
 * @param elIdDoc
 * @returns
 */
function pasarDocRevisadoARevisarAFinal(idDocOriginal,idDocRevisado, elIdTarea){
//	var ids = $("#docsRevisados").rup_table("getSelectedRows");
	pasarDocAFinal(idDocOriginal,idDocRevisado,idTarea,'docsOriginales');
}
function pasarDocARevisarAFinal(idDocOriginal,idDocRevisado,codTarea,idRupTable){
	if(!ejecutarTareaConsulta){
		if(!idDocRevisado){
			idDocRevisado = idDocOriginal;
		}
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/documentosgeneral/pasarDocARevisarAFinal/'+idDocOriginal+'/'+idDocRevisado+'/'+codTarea+'/'+$('#idiomaDest').val()
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,async: false
		     ,cache: false
		   	 ,success:function(extensionValida){
		   		$('#'+idRupTable).trigger("reloadGrid");
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
		   	 }
	  	 	,error: function(){
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		extensionOK =  false;
		   	 }
		 });
	}
}

function pasarDocAFinal(idDocOriginal,idDocRevisado,codTarea,idRupTable){
	if(!ejecutarTareaConsulta){
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/documentosgeneral/pasarDocAFinal/'+idDocOriginal+'/'+idDocRevisado+'/'+codTarea+'/'+$('#idiomaDest').val()
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,async: false
		     ,cache: false
		   	 ,success:function(extensionValida){
		   		$('#'+idRupTable).trigger("reloadGrid");
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
		   	 }
	  	 	,error: function(){
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		extensionOK =  false;
		   	 }
		 });
	}
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

/**
 * Muestra links de columna 'documento a revisar' de documentos originales
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocOriginal(cellval, opts, rowObject, isTarea){
	var celda = '<div style="min-height: 25px;">';
	celda += '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumento('+rowObject.documentoTarea.documentoOriginal.idDoc+')" class="document-eusk iconSameLine" style="margin-bottom:4px;" data-id="'+ rowObject.documentoTarea.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre + '</a>';
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
	if(tituloYNombreDiferentes(rowObject.documentoTarea.documentoOriginal.titulo,rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre )){
		if(isTarea){
			celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.idTarea+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoTarea.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
		} else{
			celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.documentoTarea.documentoOriginal.idDoc+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoTarea.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
		}
		
	}	
	celda += '</p></div>';
	if (rowObject.documentoTarea.documentoOriginal.ficheros[1].idDocRel){
		celda += '<p class="document-fileAndIcon">';
		celda+='<a href="#" onclick="descargarDocumento('+rowObject.documentoTarea.documentoOriginal.ficheros[1].idDocRel+')" class="document-eusk iconSameLine" data-id="'+ rowObject.documentoTarea.documentoOriginal.idDoc +'">';
		celda+= rowObject.documentoTarea.documentoOriginal.ficheros[1].nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoOriginal.ficheros[1].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoOriginal.ficheros[1].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda += '</p>';
	}
	if(rowObject.documentoTarea.documentoOriginal.ficheros[0].encriptado === 'S'){
		confidencial = true;
	}
	return celda;
}

function mostrarInfoDocFinalOriginal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoTarea.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal__" type="button" onclick="clickDocOriginalAFinal('+rowObject.documentoTarea.documentoOriginal.idDoc+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.docRevisionFinal")+'</button>';
	celda+='</div>';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoTarea.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonDocOriginal('+rowObject.documentoTarea.documentoOriginal.idDoc+',\''+rowObject.documentoTarea.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',event,\''+rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:4px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarDocumento")+'</button>';	
	celda+='</div>';
	if (rowObject.documentoTarea.documentoFinalOriginal.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoFinalOriginal.idFichero +')" class="document-eusk iconSameLine" >';
		celda+= rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;	
}

/**
 * Muestra botones de columna 'documento revisado' y en el caso de que hubiera un fichero, un link de descarga del mismo
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocFinal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoTarea.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding" style="clear: both;">';
	if(esTecnicoIzo){
		celda+='<div class="displayAsTable">';
		celda+= '<button id="pidButtonFinal__" type="button" onclick="pasarDocumentoARevisarAFinal('+rowObject.documentoTarea.documentoOriginal.idDoc+','+rowObject.documentoTarea.documentoOriginal.ficheros[1].idDocRel+',event)" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.docARevisarAFinal")+'</button>';
		celda+='</div>';
	}
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoTarea.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoTarea.documentoOriginal.idDoc+',\''+rowObject.documentoTarea.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',event,\''+rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" >'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';
	if (rowObject.documentoTarea.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
		if (rowObject.documentoTarea.documentoAdjunto.nombre != null){
			celda+= rowObject.documentoTarea.documentoAdjunto.nombre + ' </a>';
		} else if(rowObject.documentoTarea.documentoAdjunto.titulo != null){
			celda+= rowObject.documentoTarea.documentoAdjunto.titulo + ' </a>';
		}else {
			celda+=  + ' </a>';
		}
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
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
function mostrarInfoJustifRevision(cellval, opts, rowObject){
	var celda = '<div class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal____" type="button" onclick="clickPidButtonFinalJust('+rowObject.documentoTarea.documentoOriginal.idDoc+',event)" class="btnPID ui-button ui-corner-all ui-widget no-obligatorio">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	if(rowObject.documentoTarea.documentoJustificante.idFichero != null){
		celda+= '<a id="buttonEliminarJust" href="#" onclick="eliminarJustificante('+rowObject.documentoTarea.documentoOriginal.idDoc+')" class="flotaIzda rup-enlaceCancelar" >'+$.rup.i18n.app.comun.eliminar+'</a>';
		celda+='</div>';	
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoJustificante.idFichero +')" class="document-eusk iconSameLine">';
		celda+= rowObject.documentoTarea.documentoJustificante.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoJustificante.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoJustificante.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		var esVisible = "";
		if(rowObject.documentoTarea.documentoJustificante.indVisible === "S"){
			esVisible = rowObject.documentoTarea.documentoJustificante.indVisible;
		}
		celda+='<div class="">'+labelvisibleClte+'<input type="checkbox" name="indVisible" class="form-control" id="indVisibleFinal_'+ rowObject.documentoTarea.documentoOriginal.idDoc +'" value="'+ esVisible + '" data-switch-pestana="true" /></div>';
		celda+='</div>';	
	} else {
		celda+= '</div>';
	}
	celda+='</div>';
	return celda;
}

/**
 * Muestra botones de columna 'documento revisado' y en el caso de que hubiera un fichero, un link para eliminar el mismo
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocumentoRevisado(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoTarea.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	if(rowObject.documentoTarea.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine">';
		celda+= rowObject.documentoTarea.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
		celda+='<div class="displayAsTable">';
		celda+= '<button id="pidButtonFinal__" type="button" onclick="pasarDocRevisadoARevisarAFinal('+rowObject.documentoTarea.documentoOriginal.idDoc+','+rowObject.documentoTarea.documentoAdjunto.idFichero+','+rowObject.documentoTarea.idTarea+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.pasarAFinal")+'</button>';
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
function mostrarInfoDocRevJustifRevision(cellval, opts, rowObject){
	var celda = '<div class="form-group col-lg-12 no-padding">';
	if(rowObject.documentoTarea.documentoJustificante.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoJustificante.idFichero +')" class="document-eusk iconSameLine">';
		celda+= rowObject.documentoTarea.documentoJustificante.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoJustificante.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoJustificante.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';	
	}
	celda+='</div>';
	return celda;
}

function clickDocOriginalAFinal(idDocOriginal){
	pasarDocOriginalAFinal(idDocOriginal,idTarea,'docsOriginales');
}

function pasarDocOriginalAFinal(idDocOriginal,codTarea,idRupTable){
	if(!ejecutarTareaConsulta){
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/documentosgeneral/pasarDocOriginalAFinal/'+idDocOriginal+'/'+codTarea+'/'+$('#idiomaDest').val()
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,async: false
		     ,cache: false
		   	 ,success:function(extensionValida){
		   		if(extensionValida.error === "S"){
		   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.docuNoTarea, "error");
		   	 	} else {
			   	 	$('#docsOriginales').trigger("reloadGrid");
			   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
		   	 	}
		   	 }
	  	 	,error: function(){
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		extensionOK =  false;
		   	 }
		 });
	}
}

function clickPidButtonDocOriginal(idDocO, laExtension, elReqEncriptado, event, nomDocOriginal){
	docOriginal = true;
	clickPidButtonFinal(idDocO, laExtension, elReqEncriptado, event, nomDocOriginal);
}

function clickPidButtonFinal(idDocO, laExtension, elReqEncriptado, event, nomDocOriginal){
	event.preventDefault();
	event.stopImmediatePropagation();
	extension = laExtension;
	nombreDocOriginal = nomDocOriginal;
	comprobarNombre = true;
	tablaIntermediaDestino = tablaIntermedia.tabla92;
	$('#reqEncriptado').val(elReqEncriptado);
	idDocOriginal = idDocO;
	$('#ficheroFinal').click();
}

function clickPidButtonFinalJust(idDocO, event){
	event.preventDefault();
	event.stopImmediatePropagation();
	tablaIntermediaDestino = tablaIntermedia.tabla93;
	idDocOriginal = idDocO;
	comprobarNombre = false;
	$('#ficheroFinal').click();
}

function comprobarExtensionValida( nombreFichero){
	tipoErrorDoc = 0;
	var extensionAdjunto = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	var extensionOK = false;
	if(tablaIntermedia.tabla92 == tablaIntermediaDestino){
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
				   					"docOriginal": docOriginal
				   			}

				   			if (docOriginal) {
					   			$.ajax({
					   				type: 'POST'
					   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroOriginal'
					   			 	 ,dataType: 'json'
					   			 	 ,contentType: 'application/json' 
					   			     ,data: $.toJSON(subidaTarea)		
					   			     ,success: function (data){
					   			    	$('#docsOriginales').trigger("reloadGrid");
					   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
					   					cambios = true;
					   					docOriginal = false;
					   					$("#fichero").val('');
					   					desbloquearPantalla();
					   			     },error: function (e){
					   			    	$("#fichero").val('');
					   			    	desbloquearPantalla();
					   			    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
					   			     }
					   			});
				   			} else {
				   				$.ajax({
					   				type: 'POST'
					   			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/subidaFicheroFinal'
					   			 	 ,dataType: 'json'
					   			 	 ,contentType: 'application/json' 
					   			     ,data: $.toJSON(subidaTarea)		
					   			     ,success: function (data){
					   			    	$('#docsOriginales').trigger("reloadGrid");
					   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
					   					cambios = true;
					   					$("#fichero").val('');
					   					desbloquearPantalla();
					   			     },error: function (e){
					   			    	$("#fichero").val('');
					   			    	desbloquearPantalla();
					   			    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
					   			     }
					   			});
				   			}
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

$("#ficheroFinal").change(function(){
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
			$("#ficheroFinal").val('');
    		desbloquearPantalla();
    	}
	}
});

function comprobarCambiosFormulario(){
	return true;
}

function llamadaWSIzoberri(){
		//llamada al WS de Boletin...
   		$.ajax({
	   	   	 type: 'GET'		   	 
	   	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/comprobarWSIzoberri/R/'+idTarea
	   	 	 ,dataType: 'json'
	   	 	 ,contentType: 'application/json' 
	   	     //,async: false
	   	     ,cache: false
	   	     ,beforeSend: function(){
		    	 bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"mensajes.enviandoInfoP43"));
		     }
	   	   	 ,success:function(data){
	   	   		 desbloquearPantalla();
	   	   		 if (data === "0"){
	   	   			 //Correcto
	   	   			 finalizarTarea();
	   	   		 } else if (data === "1") {
	   	   			 
		   	   		$.rup_messages("msgConfirm", {
			   	 		title: $.rup.i18nParse($.rup.i18n.base,"rup_message.alert"),
			   	 		message: $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioExpediente"),
			   	 		OKFunction: function(){
			   	 			finalizarTarea();
			   	 		},
			   	 		CANCELFunction: function(e){
			   	 		}
			   	 	});
	   	   			 
	   	   		 } else {
	   	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaFirmaWSP43 + "(" + data + ")", "error");
	   	   		 }
		   	}
    	 	,error: function(){
    	 		desbloquearPantalla();
    	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionErrorValidandoDocumentos, "error");
		   	}
   		});
}

function comprobarFirmas(){
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/comprobarFirmaDocumentos/R/'+idTarea
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     //,async: false
	     ,cache: false
	     ,beforeSend: function(){
	    	 desbloquearPantalla();
	    	 bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"mensajes.comprobandoFirmas"));
	     }
	   	 ,success:function(numArchivosFirmadosCorrectosEnZips){
	   		if (numArchivosFirmadosCorrectosEnZips){
	   			mostrarMensajeComprobarIzoBerri();	   				
	   		}else{
	   			desbloquearPantalla();
	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorComprobandoZIPfirmas, "error");
	   		}
	   	 }
 	 	,error: function(){
 	 		desbloquearPantalla();
 	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionErrorValidandoDocumentos, "error");
	   	 }
	 });	
}

function finalizarTarea(){
	if($("#confirmartarea").length){
			$("#confirmartarea").remove();
			$("#confirmartarea").rup_dialog('destroy');		
			$("#divTareasExpedientes").append('<div id="confirmartarea" style="display:none"></div>');
		}
		
		$("#confirmartarea").confirmar_tarea({esHoraOblig:esHoraOblig , tieneHora: true, modalSelector: "ejecutarTareaDialog"});
		$("#confirmartarea").confirmar_tarea("open");
}

function mostrarMensajeFinalizacionTarea(){
	desbloquearPantalla();
	$.rup_messages("msgConfirm", {
		title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		message: $.rup.i18nParse($.rup.i18n.app,"label.documentosTradAccesibles"),
		OKFunction: function(){
			finalizarTarea();
		},
		CANCELFunction: function(e){
		}
	});
}
function mostrarMensajeComprobarIzoBerri(){
	desbloquearPantalla();
	$.rup_messages("msgConfirm", {
		title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		message: $.rup.i18nParse($.rup.i18n.app,"label.documentosTradAccesibles"),
		OKFunction: function(){
			llamadaWSIzoberri();
		},
		CANCELFunction: function(e){
		}
	});
}
function PrefinalizarTarea(){
	var iFirmaNecesaria = 0;
	if(necesitaFirmarDocs){
		iFirmaNecesaria = 1;
	}
	// validar documentos antes de finalizar
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/validarDocumentosTareaFinalizarEntregaTrad/'+idTarea+'/'+iFirmaNecesaria
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     //,async: false
	     ,cache: false
	     ,beforeSend: function(){
	    	 bloquearPantalla();
	     }
	   	 ,success:function(documentosValidos){
	   		if (documentosValidos){
	   			if (!necesitaEnviarP43){
	   				mostrarMensajeFinalizacionTarea();
	   			}else{
	   				comprobarFirmas();
	   			}
	   		}else if(necesitaFirmarDocs){
	   			desbloquearPantalla();
	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionFaltanFicherosOFirmas, "error");
   			}else{
   				desbloquearPantalla();
   				$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionFaltanFicheros, "error");
   			}
	   	 }
  	 	,error: function(){
  	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionErrorValidandoDocumentos, "error");
  			desbloquearPantalla();
	   	 }
	 });		
}

function crearDialogoNoConformidad(){
	$("#generarNoConformidad_detail_toolbar").empty();
	if(	$("#generarNoConformidad_detail_div").parent().is("[role=dialog]")){
		
		
		$("#generarNoConformidad_detail_div").rup_dialog('destroy');
	}
	
	$("#generarNoConformidad_detail_div").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: true,
	    width: 1000,
	    title: $.rup.i18n.app.label.generarNoConformidad
	});
	
	fnFechaDesdeHasta("fechaIniNoConformidad_detail_table", "fechaFinNoConformidad_detail_table");
	
	$('#generarNoConformidad_detail_feedback').rup_feedback(opcionesFeedbacks);
	
	$("#generarNoConformidad_detail_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.cancelar
				,css: "fa fa-arrow-left"
				,click : 
					 function(e){
					 	e.preventDefault();
	                    e.stopImmediatePropagation();
	                    if (initFormNoConformidad !== $("#generarNoConformidad_detail_form").rup_form("formSerialize")){
							
							$.rup_messages("msgConfirm", {
	             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	             				OKFunction: function(){
	             					closeDialogGenerarNoConformidad();
	             				}
	             			});
							
						} else {
							closeDialogGenerarNoConformidad();
						}
	                    
					}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-save"
				,click : 
					 function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    generarNoConformidad();
					}
			}
		]
	});
	
}

function dialogGenerarNoConformidad(){
	crearDialogoNoConformidad();
	obtenerDocumentosExpNoConformidad();
	
	$("#generarNoConformidad_detail_form").rup_validate({
		feedback: $('#generarNoConformidad_detail_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"fechaIni": { required: true, date: true },
			"horaIni": { required: true, hora: true, maxlength: 5 },
			"fechaFin": { required: true, date: true, fechaHastaMayorPorId: "fechaIniNoConformidad_detail_table" },
			"horaFin": { required: true, hora: true, maxlength: 5, horaFechaHastaMayorPorId: ["fechaIniNoConformidad_detail_table","fechaFinNoConformidad_detail_table","horaIniNoConformidad_detail_table"] },
			"horasPrevistas": { required: true, horasPrevistas: true, maxlength: 5 },
			"observaciones": { required: true, maxlength: 2000 },
			"documentosSelectNoConformidad": { validateDocsNoConformidad: true }
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	$('#fechaHoraFinPrevista').html(fechaHoraFin);
	documentosSelect = '';
	
	initFormNoConformidad = $("#generarNoConformidad_detail_form").rup_form("formSerialize");
	
	$("#generarNoConformidad_detail_div").rup_dialog('open');
}

function generarNoConformidad(){
	
	if ($("#generarNoConformidad_detail_form").valid()){
		
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		
		var lotes = {
	        "idLote": $("#idLote_detail_table").val() 
	    };
				
	    var Tareas = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente,
	        "orden": parseInt(ordenTarea) - 1,
	        "fechaIni": $("#fechaIniNoConformidad_detail_table").val(),
	        "horaIni": $("#horaIniNoConformidad_detail_table").val(),
	        "fechaFin": $("#fechaFinNoConformidad_detail_table").val(),
	        "horaFin": $("#horaFinNoConformidad_detail_table").val(),
	        "horasPrevistas": $("#horaPrevistaNoConformidad_detail_table").val(),
	        "observaciones": $("#obsNoConformidad_detail_table").val(),
	        "idTareaRel": idTareaRelNoConformidad,
	        "lotes": lotes
	    };

		var fichero = {
			"contentType" : $('#contentType056_1').val(),
			"encriptado" : $('#indEncriptado056_1').val(),
			"extension" : $('#extensionDoc056_1').val(),
			"nombre" : $('#nombre_1').val(),
			"oid" : $('#oidFichero056_1').val(),
			"tamano" : $('#tamanoDoc056_1').val()
		}
		
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/noConformidadRevEntregaClte/'+ordenTarea
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json'
	 		 ,data: $.toJSON({
		   			"tarea" : Tareas,
		   			"documentosSelect" : documentosSelect,
					"fichero": fichero
		   	 })
		     ,async: false
		     ,cache: false
		   	 ,success:function(data){
		   		 if (data === 1){
		   			desbloquearPantalla();
					
					$.rup_messages("msgAlert", {
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
						message: $.rup.i18n.app.mensajes.tareaNoConformidadProvSinEjec
					});
		   		 } else {
		   			 $('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
		   			 $("[id='ejecutarTareaDialog_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"boton.finalizarTarea")+"']").button("disable");
		   			 if (tablaSelector != null){
		   				 $("#"+tablaSelector).trigger("reloadGrid");
		   			 } else {
		   				 $('#detalleTarea').trigger("reloadGrid");
		   			 }
		   			 $("#docsRevisados").trigger("reloadGrid");
		   			 closeDialogGenerarNoConformidad();
		   			 desbloquearPantalla();
		   		 }
		   	 }
	  	 	,error: function(){
	  	 		$('#generarNoConformidad_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
	  			desbloquearPantalla();
		   	 }
		 });
		
	}
	
}

function closeDialogGenerarNoConformidad(){
	cerrarDialogo('generarNoConformidad', 'NoConformidad');
	$('#enlaceDescargaDetalle_1').html("");
	$('#obsNoConformidad_detail_table-error').remove();
	$('#obsNoConformidad_detail_table').removeClass('error');
}

function cerrarDialogo(selector, selector2){
	$('#' + selector + '_detail_div').rup_dialog('close');
	$('#' + selector + '_detail_form').rup_form("clearForm");
	$('#' + selector + '_detail_feedback').rup_feedback("close");
	$('#divDocsSeleccionados' + selector2).css("height","0px");
	eliminarMensajesValidacion();
	$('#' + selector + '_detail_div').rup_dialog('destroy');
}

function crearDialogoCorreccionProveedor(){
	
	$("#notificarCorreccionProv_detail_div").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: true,
	    width: 1000,
	    title: $.rup.i18n.app.label.notificarCorreccionProveedor
	});

	$('#notificarCorreccionProv_detail_feedback').rup_feedback(opcionesFeedbacks);
	
	$("#notificarCorreccionProv_detail_toolbar").empty();
	$("#notificarCorreccionProv_detail_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.cancelar
				,css: "fa fa-arrow-left"
				,click : 
					 function(e){
					 	e.preventDefault();
	                    e.stopImmediatePropagation();
	                    
	                    if (initFormCorreccionProv !== $("#notificarCorreccionProv_detail_form").rup_form("formSerialize")){
							
							$.rup_messages("msgConfirm", {
	             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	             				OKFunction: function(){
	             					closeDialogNotificarCorreccionesProv();
	             				}
	             			});
							
						} else {
							closeDialogNotificarCorreccionesProv();
						}
	                    
					}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
				,click : 
					 function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    notificarCorreccionProveedor();
					}
			}
		]
	});
	
}

function findTareaRevisionExterna(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
	ocultarMenusDesplegables();
	
	$.ajax({

        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/findTareaRevisionExterna/'+anyoExpediente+'/'+idExpediente
        ,dataType: 'json' 
        ,async: false 
        ,success:function(data){
        	
        	if (data != null) {
        		dialogGenerarNoConformidad();
        	} else {
        		$.rup_messages("msgAlert", {
        			title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
        			message: $.rup.i18n.app.mensajes.noExisteTareaRevExt
        		});
        	}
        	
        	desbloquearPantalla();
        	
        }
		,error: function(error){
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
	
}


function dialogNotificarCorreccionProveedor(){
	crearDialogoCorreccionProveedor();
	
	$("#notificarCorreccionProv_detail_form").rup_validate({
		feedback: $('#notificarCorreccionProv_detail_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"observaciones": { required: true, maxlength: 2000 },
			"ficheros[0].nombreUpload" : { required: true }
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	documentosSelect = '';
	
	initFormCorreccionProv = $("#notificarCorreccionProv_detail_form").rup_form("formSerialize");
	
	$("#notificarCorreccionProv_detail_div").rup_dialog('open');
}

function notificarCorreccionProveedor(){
	
	if ($("#notificarCorreccionProv_detail_form").valid()){
		
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		
		var lotes = {
	        "idLote": $("#idLote_detail_table").val() 
	    };
					
	    var Tareas = 
	    {
	        "anyo": anyoExpediente, 
	        "numExp": idExpediente,
	        "orden": ordenTarea,
	        "observaciones": $("#obsCorreccionProv_detail_table").val(),
	        "idTareaRel": idTarea,
	        "lotes": lotes
	    };
	    
	    var fichero = {
			"contentType" : $('#contentType056_0').val(),
			"encriptado" : $('#indEncriptado056_0').val(),
			"extension" : $('#extensionDoc056_0').val(),
			"nombre" : $('#nombre_0').val(),
			"oid" : $('#oidFichero056_0').val(),
			"tamano" : $('#tamanoDoc056_0').val()
		}
		
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/notificarCorreccionProveedor'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json'
	 		 ,data: $.toJSON({
		   			"tarea" : Tareas,
		   			"fichero" : fichero 
		   	 })
		     ,async: false
		     ,cache: false
		   	 ,success:function(data){
		   		if (data === -1){
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.faltaDirEmail"), "alert");
				} else if (data === -2){
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorEnvioEmail"), "error");
				} else {
					// (data === 0)
					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.datosCargadosOK"), "ok");
			   		if (tablaSelector != null){
		   				$("#"+tablaSelector).trigger("reloadGrid");
		   			}
			   		closeDialogNotificarCorreccionesProv();
				}
		   		desbloquearPantalla();
		   	 }
	  	 	,error: function(){
	  	 		$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
	  			desbloquearPantalla();
		   	 }
		 });
		
	}
	
}

function closeDialogNotificarCorreccionesProv(){
	cerrarDialogo('notificarCorreccionProv', 'CorreccionProv');
	$('#enlaceDescargaDetalle_0').html("");
	$('#obsCorreccionProv_detail_table-error').remove();
	$('#obsCorreccionProv_detail_table').removeClass('error');
}

function obtenerDocumentosExpNoConformidad(){
	obtenerDocumentosExp("listaDocsNoConformidad", "docsNoConformidad");
}

function obtenerDocumentosExp(selector, selector2){
	
	var DocumentosExpediente = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente
    };
	
	$.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/listaDocuAsociados",
		dataType : "json",
		contentType : 'application/json',
		data: $.toJSON({
	   		  "docuExp":DocumentosExpediente
	   	}),			
		cache : false,
    	success: function(data)
    	{
    		$("#" + selector).rup_tree("destroy");
			$("#" + selector + " > ul").empty();
			var sufijoIdioma = "";
			if (idiomaDestino == "1"){
				sufijoIdioma= labelES;
			}else{
				sufijoIdioma= labelEU;
			}
			
			//Carga del árbol
			for(var i = 0 ; i < data.length; i++){
				$("#" + selector + " > ul").append(
						"<li id='checkDocumento" + data[i].idDoc
								+ "' value = '" + data[i].idDoc + "'>");
				var inputClaseDoc = "<input type='hidden' id='claseDocumento"
						+ data[i].idDoc
						+ "' value='"
						+ data[i].claseDocumento + "'>";
				var descClaseDoc = "";
				if (data[i].claseDocumentoDesc != null
						&& data[i].claseDocumentoDesc !== "") {
					descClaseDoc = " (" + data[i].claseDocumentoDesc
							+ ")";
				}
				var nombreDocu = "<a href='#' value ='" + data[i].idDoc + "'>" + data[i].ficheros[0].nombre;
				nombreDocu = nombreDocu + descClaseDoc	+ "</a>";
				
				$("#" + selector + " > ul > li").last().append(nombreDocu).append(inputClaseDoc);
				$("#" + selector + " > ul").append("</li>");
				
				$("[id='checkDocumento"+data[i].idDoc+"'] > a").on('click', function(){
					
					var idDocSeleccionado = ($(this).parent()).val();
					var documentoSeleccionado = false;
					documentosSelect = '';
					
					if (($(this).parent()).hasClass(
							'jstree-unchecked')) {
						documentoSeleccionado = true;
					}
					
					// Recogemos los ids de los documentos checkeados   
					$("#" + selector2 + " ul li.jstree-checked").each(function(){
						
						if ($(this).val() !== idDocSeleccionado){
							if (documentosSelect === ''){
								documentosSelect += $(this).val();
							} else {
								documentosSelect += ','+$(this).val();
							}
						}
						
				    });
					
					if (documentoSeleccionado){
						if (documentosSelect === ''){
							documentosSelect += idDocSeleccionado;
						} else {
							documentosSelect += ','+idDocSeleccionado;
						}
					}
					
				});
				
			}
			$("#" + selector).rup_tree({
				"checkbox" : {
				      "enable" : true
				   }
			});
			
			setTimeout(function() {
				$("#" + selector + " > ul > li > ins.jstree-icon").remove();
				$("#" + selector + " > ul > li > a > ins.jstree-icon").remove();
			}, 500);
    	}
	});
}

function servicioEncriptar(){
	window.open('/aa79bItzulnetWar/servicios/trataFicherosConfi/encripFicheros/maint');
}

function crearToolbarEjecutarTareaDialog(){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	
//	var ids = $("#tareasExpedientesForm").rup_table("getSelectedRows");
//	var codTarea = ids[0];
//	
//	var orden = ordenTarea;
			
    var Tareas = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente,
        "orden": ordenTarea
    };
	$.ajax({
	   	 type: 'POST'		   	 
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/findLoteTareaRevisionEjec'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json'
 		 ,data: $.toJSON({
	   			"tarea" : Tareas
	   	 })
	     ,async: false
	     ,cache: false
	   	 ,success:function(data){
	   		 
			 if (data !== 0){
				// En caso de que la tarea de revisión esté asignada a un recurso externo, 
				// la toolbar del dialogo ejecutar tarea se creará con el botón generar no conformidad
				$("#docsRevisados_toolbar").removeClass('oculto');
				
				$("#docsRevisados_toolbar").rup_toolbar({
					buttons:[
						{
							i18nCaption: $.rup.i18n.app.boton.generarNoConformidad
							,click : 
								 function(e){
									e.preventDefault();
				                    e.stopImmediatePropagation();
				                    ocultarObservaciones(e);
				                    findTareaRevisionExterna();
								}
						},
						{
							i18nCaption: $.rup.i18n.app.boton.notificarCorreccionProveedor
							,click : 
								 function(e){
									e.preventDefault();
				                    e.stopImmediatePropagation();
				                    ocultarObservaciones(e);
				                    dialogNotificarCorreccionProveedor();
								}
						}
					]
				});
			 }
	   		
	   		$("#ejecutarTareaDialog_toolbar").rup_toolbar({
   				buttons: [
					{
						i18nCaption: $.rup.i18n.app.boton.volver
						,id: "volver"	
					,css: "fa fa-arrow-left"
					,click : 
						 function(e){
						 	e.preventDefault();
			                e.stopImmediatePropagation();
			                ocultarObservaciones(e);
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
				                    var valor = parseInt($("#porcentajePenalizacion_revEntregaClte").val());
				                    if(valor!= '' && valor < 0 || valor > 100){
				                		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajeMenorCien, "error");
				                		$("#porcentajePenalizacion_revEntregaClte").addClass("error");
				                    } else if ($("#porPenalizacionSwitch_revEntregaClte").is(':checked') && parseInt($('#porcentajePenalizacion_revEntregaClte').val()) > parseInt($("#retrasoVal_revEntregaClte").val())) {
				                    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajePenalMayorQueLote, "error");
				                		$("#porcentajePenalizacion_revEntregaClte").addClass("error");
				                    } else {
				                    	if ($('#porcentajePenalizacion_revEntregaClte').val() === '' && $("#porPenalizacionSwitch_revEntregaClte").is(':checked')) {
					                    	$("#porcentajePenalizacion_revEntregaClte").val(0);
					                    }
				                    	ocultarObservaciones(e);
					                    if (($('#obligarXliff_detail_table').bootstrapSwitch('state')?'S':'N' === "S") || obligarXliff === "S"){ 
					                		comprobacionPreviaXliffAlinearPostEntrega(idTarea,"ejecutarTareaDialog_feedback","PrefinalizarTarea");
					            		}else{
						                    PrefinalizarTarea();
					            		}
				                    }
								}
							}
					},
					{
						i18nCaption: $.rup.i18n.app.boton.servicioDeEncriptacion,right : true
						,click:
							function(e){
								e.preventDefault();
			                    e.stopImmediatePropagation();
			                    ocultarObservaciones(e);
			                    servicioEncriptar();
						}
					}
				]
   			});
	   		
	   		$("#idLote_detail_table").val(data);
	   		
	   		desbloquearPantalla();
	   	 }
  	 	,error: function(){
  	 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
  			desbloquearPantalla();
	   	 }
	 });
	
}

function mostrarInfoGroupBy(cellval, opts, rowObject){
	groupIdPrefix = opts.gid + "ghead_";
    groupIdPrefixLength = groupIdPrefix.length;
    prefix = opts.rowId.substr(0, groupIdPrefixLength);
    var cell;
    
    if("docsRevisadosghead_" !== prefix){
    	idTareaFormatter = rowObject.idTareaAgrupacion;
    	cell = "";
    	var split = rowObject.groupByText.split("#");
    	var groupByText = split[1];
    	if (groupByText.length > 118){
    		groupByText = groupByText.substring( 0, 118 ).concat("...");
    	}
		cell = cell.concat('<b>').concat(groupByText).concat(' </b>');
		var indObservaciones = "";
		if (rowObject.ejecucionTareas != null && rowObject.ejecucionTareas.indObservaciones != null){
			indObservaciones = rowObject.ejecucionTareas.indObservaciones;
		}
		if (indObservaciones === "S"){
			cell += '<span class="input-group-link dcha"><a href="#" onclick="getObservaciones(' + rowObject.idTarea  +',this, event)" style="background-color:#B0FF57"><b>' + tituloObservaciones + '</b></a></span>';
		}
		
		listaTitulos[idTareaFormatter] = { value: cell };
		
		return cell;
    }else{
    	cell = listaTitulos[cellval].value;
    }
	
    return cell;

}

function getObservaciones(idTarea, elementA, e) {
	e.preventDefault();
    e.stopImmediatePropagation();
    $('[data-toggle="popover"],[data-original-title]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
        }

    });
	if ($("#observaciones_"+idTarea).length === 0){
		$(elementA).attr("id", "observaciones_"+idTarea);
		
		$.ajax({
			url : '/aa79bItzulnetWar/ejecuciontarea/tareas/findTarea/' + idTarea,
			success : function(data) {
				if (data != null){
					var newData = JSON.parse(data);
					if (newData.ejecucionTareas != null && newData.ejecucionTareas.indObservaciones === datos.activo.si){
						$("#observaciones_"+idTarea).popover({
					        title: tituloObservaciones,
							content: newData.observacionesTareas.obsvEjecucion,
					        placement: 'bottom',
					        trigger: 'click'
                        }).on("show.bs.popover", function() {
                            $($(this).data("bs.popover").getTipElement()).addClass('grupoTarea');
                        });

						
						$("#observaciones_"+idTarea).popover('show');
					}
				}
			},
			error: function(){
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		   	}
		});
	} else {
		$("#observaciones_"+idTarea).popover('toggle');
	}
	
}

//INICIO Subida del fichero asociado a la tarea de notificación de corrección al proveedor

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function comprobarExtensionValidaFichero( nombreFichero){
	var extension = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	var extensionOK = false;
	var jsonObject = 
	{
		"formato011": extension
	};	
	
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/comprobarextension'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: jsonObject	
	     ,async: false
	     ,cache: false
	   	 ,success:function(extensionValida){
	   		if (extensionValida > 0) {
	   			extensionOK =  true;
	   		}
	   	 }
   	 	,error: function(){
	   		$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		extensionOK =  false;
	   	 }
	 });			
	return extensionOK;	
}

function vaciarFileYDesbloquear(){
	$("#fichero").val('');
	desbloquearPantalla();
}

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
			   	 ,success:function(data){
			   		if (data.error === null){ 
				   		var destinoUpload = $("#idBotonUpload").val();
				   		$('#nombreFicheroInfo_'+destinoUpload).val(data.nombre);
						$('#nombre_'+destinoUpload).val(data.nombre);
						$('#extensionDoc056_'+destinoUpload).val(data.extension);
						$('#tamanoDoc056_'+destinoUpload).val(data.tamano);
						$('#contentType056_'+destinoUpload).val(data.contentType);
						$('#oidFichero056_'+destinoUpload).val(data.oid);
						$('#indEncriptado056_'+destinoUpload).val(data.encriptado);
						var enlace = '<span>'+data.nombre+'</span> ('+data.extension+' , '+conversionKB(data.tamano)+' KB.) ';
						enlace+=' <span class="ico-ficha-encriptado" title="'+((data.encriptado==='S')?labelEncrip:labelNoEncrip)+'"><i class="fa fa-'+((data.encriptado==='S')?"":"un")+'lock" aria-hidden="true"></i></span>';
						$('#enlaceDescargaDetalle_'+destinoUpload).html(enlace);
						
					}else{
						$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", data.error, "error");		
					}
			   		vaciarFileYDesbloquear();
			   	 }
			   	 , error: function(data){
					$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", data.error, "error");
					vaciarFileYDesbloquear();
				 }
			});
		}else{
			$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
			vaciarFileYDesbloquear();
		}	
	}
	, error: function(archivoPIF){
		$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
		vaciarFileYDesbloquear();
	}			
});

$("#fichero").change(function(){
	var destinoUpload = $("#idBotonUpload").val();
	if ('1' === destinoUpload) {
		$('#generarNoConformidad_detail_feedback').rup_feedback("hide");
	} else if ('0' === destinoUpload) {
		$('#notificarCorreccionProv_detail_feedback').rup_feedback("hide");
	}
	if ($("#fichero").val() !== ''){
		if (comprobarExtensionValidaFichero($('#fichero').val())){
			$("#subidaFicheroPid_form").submit();
    	}else{ 
    		$('#notificarCorreccionProv_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
    	}
	}
});

$("#pidButton_0").unbind("click");
$("#pidButton_0").click(function(event) {
	$("#idBotonUpload").val("0");
	$('#fichero').click();
});

// FIN Subida del fichero asociado a la tarea de notificación de corrección al proveedor

// INICIO Subida del fichero asociado a la tarea de notificacion de no Conformidad Proveedor
$("#pidButton_1").unbind("click");
$("#pidButton_1").click(function(event) {
	$("#idBotonUpload").val("1");
	$('#fichero').click();
});
// FIN Subida del fichero asociado a la tarea de notificacion de no Conformidad Proveedor



/* funcionalidad firma */

// formatter de la columna de firma de la rup_table
function mostrarInfoDocumentoFirmado(cellval, opts, rowObject){
	var celda = '';
	if (isEmpty( rowObject.documentoTarea.documentoAdjunto.idFichero )){
		celda+= '-';
	}else{
		var txtEstadoFirma = estadoFirmaLabel.sinFirmar;
		switch (rowObject.documentoTarea.documentoFinalFirmado.estadoFirma){
			case estadoFirma.sinFirmar:
				txtEstadoFirma = estadoFirmaLabel.sinFirmar;
				break;
			case estadoFirma.firmando:
				txtEstadoFirma = estadoFirmaLabel.firmando;
				break;
			case estadoFirma.error:
				txtEstadoFirma = estadoFirmaLabel.error;
				break;
			case estadoFirma.firmado:
				txtEstadoFirma = '<a href="#" onclick="descargarDocumentoGeneral('+rowObject.documentoTarea.documentoFinalFirmado.idFichero+')" class="flotaIzda document-eusk txtTablaTarea">';
				txtEstadoFirma += estadoFirmaLabel.firmado +'</a>';
				break;
		}
		celda+= txtEstadoFirma;
//		if (rowObject.documentoTarea.documentoFinalFirmado.estadoFirma == estadoFirma.sinFirmar || rowObject.documentoTarea.documentoFinalFirmado.estadoFirma == estadoFirma.error ){
//			celda+=	'<button id="firmarButton_" type="button" onclick="llamadaFirmarDocumento('+rowObject.idTarea+','+rowObject.documentoTarea.documentoOriginal.idDoc+','+rowObject.documentoTarea.documentoAdjunto.idFichero+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"boton.firmar")+'</button>';
//		}else if (rowObject.documentoTarea.documentoFinalFirmado.estadoFirma == estadoFirma.firmado && isNotEmpty(rowObject.documentoTarea.documentoFinalFirmado.idFichero) ){
//			celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoFinalFirmado.idFichero +')" class="flotaIzda document-eusk txtTablaTarea" >'+$.rup.i18nParse($.rup.i18n.app,"label.ver")+'</a>';	
//		}
	}
	
	
	return celda;
}

// consulta si se debe mostrar o no la columna de firma
function comprobarNecesidadFirmaDocs(){
	
	$.ajax({
        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/comprobarNecesidadFirmaDocs/'+anyoExpediente+'/'+idExpediente
        ,dataType: 'json' 
        ,async: false 
        ,success:function(data){
        	/* 0 -> No hace falta firmar los docs del expediente
        	 * 1 o 2-> Es necesario firmar los docs del expediente
        	 *  3 -> Es necesario firmar los docs del expediente, y
        	 *         hacer las comprobaciones posteriores y llamar al WS de IzoBerri
        	*/
        	if (data === 0) {
         		necesitaFirmarDocs = false;
         		necesitaEnviarP43 = false;
         	}else{
         		necesitaFirmarDocs = true;
         		if (data === 3) {
         			necesitaEnviarP43 = true;
         		}
         		$('#notaFirma').show();
         	} 
        	crearTablaDocumentos();
        }
		,error: function(error){
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
}


function crearTablaDocumentos(){
	$("#docsOriginales").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/docsRevisionEntregaCliente",
		toolbar: {
   		 id: "docsOriginales_toolbar",
   		 	defaultButtons:{
   		 		add: false,
   		 		edit: false,
   		 		cancel: false,
   		 		save: false,
   		 		clone: false,
   		 		"delete": false,
   		 		filter: true
   		 	},
   		 	newButtons:[
   		 		{obj: {
   		 			i18nCaption: $.rup.i18n.app.boton.refrescar
   		 			,css: "fa fa-refresh"
   					,index: 0
   					, right: true
       		 		}
       		 		,json_i18n : $.rup.i18n.app.simpelMaint
       		 		,click : 
      					function(){
       		 				$("#docsOriginales").trigger("reloadGrid");
       		 				$("#docsOriginales").rup_table("resetSelection");
      					}	
   		 		},
   		 		{obj: {
   		 			i18nCaption: $.rup.i18n.app.boton.firmar
   		 			,css: "fa fa-pencil-square-o"
   					,index: 0
   					, right: true
       		 		}
       		 		,json_i18n : $.rup.i18n.app.simpelMaint
       		 		,click : 
      					 function(){
       		 			var expedientesSeleccionados = [];
       		 			var selectedRows = [];
       		 			var mostrarMensaje = false;
       		 			selectedRows = $("#docsOriginales").rup_table("getSelectedRows");
					 	for(var i=0;i<selectedRows.length;i++){
     						 var j = selectedRows[i];
     						 if ( $("#docsOriginales").rup_table("getCol", j, "documentoTarea.documentoFinalFirmado.idFichero") !== '-' ) {
     							 if ((bopv || tramitagune) && $("#docsOriginales").rup_table("getCol", j, "documentoTarea.documentoFinalOriginal.idFichero") == "" && !zip) {
     								 mostrarMensaje = true;
		     						 
	     						 } else {
	     							expedientesSeleccionados.push({
	         							idTarea: selectedRows[i].substr(0,selectedRows[i].indexOf(',')),
	         							idDocOriginal: selectedRows[i].substr(selectedRows[i].indexOf(',')+1,selectedRows[i].length)
	         						 });
	     						 }
     						 }
				 		}

  						var jsonObject = {
  								listaDocumentoTareaAdjunto: expedientesSeleccionados
  			        	};
  						if(expedientesSeleccionados.length===0){
                			$('#docsOriginales_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionDocumento, "error");
  						}else{
  							if (mostrarMensaje) {
  								$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.adjuntarAmbosFicheros, "error");
  							} else {
	  							jQuery.ajax({
	  			    		    	type: "POST",
	  			    		    	url: "/aa79bItzulnetWar/firmadocumentos/firmar",
	  			    		    	dataType: "json",
	  			    		    	contentType: 'application/json',
	  			    		    	data: $.toJSON(jsonObject),
	  			    		    	beforeSend: function() { 
	  			    		        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	  			    		        },
	  			    		    	cache: false,
	  			    		    	success: function (data){
	  			    		    		desbloquearPantalla();
	  			    		    		$("#docsOriginales").trigger('reloadGrid');
	  			    		    		$("#docsOriginales").rup_table("resetSelection");
	  			    					$('#docsOriginales_feedback').rup_feedback("set",  $.rup.i18n.app.mensajes.firmaSolicitada, "ok");
	
	  			    		    	},
	  			    		    	error: function (){
	  			    		    		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSolicitandoFirma, "error");
	  			    		    	}
	  			    		    });
  							} 
  						}
  						
  					}	
   		 		}
   		 		
   		 	]
   	 	}, 
		
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			labelARevisar +" ("+(idiomaOrigen=="1"?labelEuskera:labelCastellano)+")",
			labelUltimoEnRevisar,
			"",
			labelRevisado,
			"",
			labelFirmado,
			labelJustificanteRev
		],
		colModel: [
			{ 	name: "idTarea", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.idDoc", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.titulo", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.ficheros[0].encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.ficheros[1].idDocRel", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.ficheros[1].encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoAdjunto.idFichero", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoAdjunto.titulo", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoAdjunto.encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.idFichero", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.titulo", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.indVisible", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.claseDocumento", 
				hidden: false,
				width: "50", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarIconoOriginal(cellval);
				}
			},			
			{ 	name: "documentoTarea.docOriginalConcatenado" ,
				align: "left", 
				width: "250", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocOriginal(cellval, opts, rowObject, false);
				}
			},
			{ 	name: "documentoTarea.docFinalOriginal", 
				hidden: false,
				width: "600", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					if (rowObject.documentoTarea.documentoOriginal.ficheros[0].extension != "zip") {
						zip = false;
						return mostrarInfoDocFinalOriginal(cellval, opts, rowObject);
					} else {
						zip = true;
					}
					return "";
				}
			},
			{ 	name: "documentoTarea.documentoFinalOriginal.idFichero", 
				align: "center", 
				width: "100",
				editable: false, 
				fixed: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "documentoTarea.docFinalConcatenado", 
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
			
			
			{ 	name: "documentoTarea.documentoFinalFirmado.estadoFirma", 
				align: "left", 
				width: "100",
				editable: false, 
				fixed: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "documentoTarea.documentoFinalFirmado.idFichero", 
				align: "center", 
				width: "100",
				editable: false, 
				fixed: true, 
				hidden: !necesitaFirmarDocs, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocumentoFirmado(cellval, opts, rowObject);
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
					return mostrarInfoJustifRevision(cellval, opts, rowObject);
				}
			}
        ],
        model:"Tareas",
     	usePlugins:(necesitaFirmarDocs && !ejecutarTareaConsulta) ? 
        	["feedback",
    			"toolbar",
    			"responsive",
            	"filter",
            	"multiselection",
            	"fluid",
            	"responsive"
         	] : 
         	["feedback",
    			"toolbar",
    			"responsive",
            	"filter",
            	"fluid",
            	"responsive"
            ],
    	multiselection:{
 			headerContextMenu_enabled: false
		},
        primaryKey: ["idTarea", "documentoTarea.documentoOriginal.idDoc"],
		multiplePkToken:",",
		sortname : "IDTAREA",
		sortorder : "asc",
		rowNum: 5,
		loadComplete: function(){
			if(bopv || tramitagune){
     			$("#docsOriginales").rup_table("showCol", "documentoTarea.docFinalOriginal");
			}else{
				$("#docsOriginales").rup_table("hideCol", "documentoTarea.docFinalOriginal");
			}
			habilitarPager('docsOriginales');
     		$('#pg_docsOriginales_pager').find('td.pagControls select').hide();
        },
		beforeSelectRow: function (){
			return necesitaFirmarDocs && !ejecutarTareaConsulta;
		},
		gridComplete: function () {
			$('#docsOriginales input:checkbox[data-switch-pestana="true"]').each(function(index, element){
				$(element)
				.bootstrapSwitch()
				.bootstrapSwitch('setSizeClass', 'switch-small')
				.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
				.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
				
				if(ejecutarTareaConsulta){
					var parentIndJustVisible = $(element).parent().parent();
					parentIndJustVisible.addClass("disabled");
					$(element).attr('disabled','disabled');
				}
			});
			
			$("[id^='indVisibleFinal_']").each(function(){
				if($(this).val() === 'S'){
					$(this).bootstrapSwitch('setState', true);
				}else{
					$(this).bootstrapSwitch('setState', false);
				}
				$(this).on('switch-change', function(event, state) {
					var res = (event.currentTarget.id).split("_");
					elIdDocSplit = res[1];
					if (state.value){ //Activando
						cambiarVisibilidad93(elIdDocSplit, true, idTarea, 'docsOriginales');
					}else{
						cambiarVisibilidad93(elIdDocSplit, false, idTarea, 'docsOriginales');
					}
				});
			});
			
//			ocultarBotonesTareaEjec();
			if (ejecutarTareaConsulta ){
		    	$('[id^="pidButtonFinal"]').hide();
		    	$('[id^="buttonEliminarJust"]').hide();
		    }
			if (necesitaFirmarDocs && !ejecutarTareaConsulta){
				$('#docsOriginales_toolbar').show();
			}
			
			
		}
	});
}

function cargarDatosLote() {
	var jsonObject = {
		"idTarea" : $("#idTarea_filter").val()
	};

	$.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/ejecuciontarea/cargarDatosTareaAsignadasRevisionExterna",
		dataType : 'json',
		contentType : 'application/json',
		data : $.toJSON(jsonObject),
		async : false,
		cache : false,
		success : function(data) {
			if (data.lotes != null) {
				$("#indFacturable_form_revEntregaClte").val(true);
				$("#retraso_revEntregaClte").text(data.lotes.porPenalizacion + "%");
				$("#retrasoVal_revEntregaClte").val(data.lotes.porPenalizacion);
				
				if (data.datosPagoProveedores != null) {
					if (data.datosPagoProveedores.indPenalizacion === "S") {
						$("#porPenalizacionSwitch_revEntregaClte").bootstrapSwitch('setState', true);
						$("#porcentajePenalizacion_revEntregaClte").val(data.datosPagoProveedores.porPenalizacion);
					} else {
						$("#porPenalizacionSwitch_revEntregaClte").bootstrapSwitch('setState', false);
					}
					
					if (data.datosPagoProveedores.nivelCalidad != null) {
						$("#calidad_combo_revEntregaClte").rup_combo("setRupValue", data.datosPagoProveedores.nivelCalidad);
					}
				} else {
					$("#porPenalizacionSwitch_revEntregaClte").bootstrapSwitch('setState', false);
				}
			} else {
				$("#datosProveedor").hide();
				$("#indFacturable_form_revEntregaClte").val(false);
			}
		}
	});
}

/* FIN funcionalidad firma */
jQuery(function($) {
	cambios = false;
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}
	
	if (bopv || tramitagune) {
		$("#notaDocsFirma").show();
	} 
	
	$('#docsOriginales_feedback').rup_feedback(opcionesFeedbacks);
	$('#docsRevisados_feedback').rup_feedback(opcionesFeedbacks);
	
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
			$("#indPenalizacion_form_revEntregaClte").val($("#porPenalizacionSwitch_revEntregaClte").is(':checked') ? datos.activo.si : datos.activo.no)
			$("#porPenalizacion_form_revEntregaClte").val($("#porcentajePenalizacion_revEntregaClte").val());	
			$("#nivelCalidad_form_revEntregaClte").val($("#calidad_combo_revEntregaClte").rup_combo("getRupValue"));	
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
	$('#anyo_filter_Revisados').val(anyoExpediente);
	$('#numExp_filter_Revisados').val(idExpediente);
	$('#idTarea_filter_Revisados').val(idTarea);
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalRevEntregaClte").rup_tabs({
		tabs : [
			{i18nCaption: labelDocumentosOriginales, layer:"#pestDocsOriginales"},
			{i18nCaption: labelRevisionesRealizadas, layer:"#pestDocsRevisados"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
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
	
	$('#docsRevisionEntregaClte_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	crearToolbarEjecutarTareaDialog();
	
	/* RUP_TABLES */
	comprobarNecesidadFirmaDocs();

	$("#docsRevisados").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/docsRevisadosEntregaClte",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			labelDocumentoARevisar +" ("+(idiomaOrigen=="1"?labelEuskera:labelCastellano)+")",
			labelDocumentoRevisado,
			labelJustificanteRev
		],
		colModel: [
			{ 	name: "idTarea", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.idDoc", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.titulo", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.ficheros[0].encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.ficheros[1].idDocRel", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoOriginal.ficheros[1].encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoAdjunto.idFichero", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoAdjunto.titulo", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoAdjunto.encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.idFichero", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.titulo", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.encriptado", 
				hidden: true
			},
			{ 	name: "documentoTarea.documentoJustificante.indVisible", 
				hidden: true
			},
			{ 	name: "idTareaAgrupacion", 
				hidden: false,
				width: "20", 
				align: "center",
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoGroupBy(cellval, opts, rowObject);
				}
			},
			{ 	name: "documentoTarea.documentoOriginal.claseDocumento", 
				hidden: false,
				width: "10", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarIconoOriginal(cellval);
				}
			},			
			{ 	name: "documentoTarea.docOriginalConcatenado" ,
				align: "left", 
				width: "200", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocOriginal(cellval, opts, rowObject, true);
				}
			},
			{ 	name: "documentoTarea.docFinalConcatenado", 
				align: "left", 
				width: "300",
				editable: false, 
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocumentoRevisado(cellval, opts, rowObject);
				}
			},
			{
				name: "",
				align: "left",
				width: "300",
				editable: false,
				fixed: true,
				hidden: false,
				resizable: true,
				sortable: false,
				formatter: function (cellval, opts, rowObject, action){
					return mostrarInfoDocRevJustifRevision(cellval, opts, rowObject);
				}
			}
        ],
        model:"Tareas",
        usePlugins:[
        	"feedback",
        	"toolbar",
       		 "filter",
       		 "fluid"
         	],   
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
		loadComplete: function(){
			habilitarPager('docsRevisados');
     		$('#pg_docsRevisados_pager').find('td.pagControls select').hide();
        },
		beforeSelectRow: function (){
			return false;
		},
		gridComplete: function () {
			$('#docsRevisados input:checkbox[data-switch-pestana="true"]').each(function(index, element){
				$(element)
				.bootstrapSwitch()
				.bootstrapSwitch('setSizeClass', 'switch-small')
				.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
				.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
				
				if(ejecutarTareaConsulta){
					var parentIndJustVisible = $(element).parent().parent();
					parentIndJustVisible.addClass("disabled");
					$(element).attr('disabled','disabled');
				}
			});
			
			$("[id^='indVisible_']").each(function(){
				if($(this).val() === 'S'){
					$(this).bootstrapSwitch('setState', true);
				}else{
					$(this).bootstrapSwitch('setState', false);
				}
				$(this).on('switch-change', function(event, state) {
					var res = (event.currentTarget.id).split("_");
					elIdDocSplit = res[1];
					var codTarea = res[2];
					if (state.value){ //Activando
						cambiarVisibilidad93(elIdDocSplit, true, codTarea, 'docsRevisados');
					}else{
						cambiarVisibilidad93(elIdDocSplit, false, codTarea, 'docsRevisados');
					}
				});
			});
			
//			ocultarBotonesTareaEjec();
			if (ejecutarTareaConsulta ){
		    	$('[id^="pidButtonFinal"]').hide();
		    	$('[id^="buttonEliminarJust"]').hide();
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
			celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
			celda+= rowObject.documentoAdjunto.titulo + '</a>';
			celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
			celda+= '</p>';
			celda+='</div>';
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
		url: "/aa79bItzulnetWar/ejecuciontarea/entregacltetrad/xliff",
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
	
	// Validación de documentos seleccionados
	$.validator.addMethod("validateDocsNoConformidad", function(valor) {
		
		var documentoSeleccionado = false;
		
    	if(documentosSelect){
    		
			var documentosSeleccionados = documentosSelect.split(',');
			var idDoc;
			var claseDocumento;
				
			for (var i = 0; i < documentosSeleccionados.length; i++) {
				
				idDoc = documentosSeleccionados[i];
				claseDocumento = $('#claseDocumento'+idDoc).val();

				if (claseDocumento === datosDocumentos.clasificacion.revision 
						|| claseDocumento === datosDocumentos.clasificacion.trabajo) {
					documentoSeleccionado = true;
				}
				
			}
			
			if (!documentoSeleccionado){
				$.validator.messages.validateDocsNoConformidad = $.rup.i18nParse($.rup.i18n.app,"mensajes.seleccionDocTradRev");
				$('#divDocsSeleccionadosNoConformidad').css("height","auto");
			} else {
				$('#divDocsSeleccionadosNoConformidad').css("height","0px");
			}

    	} else {
    		$('#divDocsSeleccionadosNoConformidad').css("height","auto");
    		$.validator.messages.validateDocsNoConformidad = $.rup.i18nParse($.rup.i18n.app,"mensajes.seleccionDocumentos");
    	}
    	
    	return documentoSeleccionado;
    	
	},$.validator.messages.validateDocsNoConformidad);
	
	// Validación de documentos seleccionados
	$.validator.addMethod("validateDocsCorreccionProv", function(valor) {
		
		var documentoSeleccionado = false;
		
    	if(documentosSelect){
    		documentoSeleccionado = true;
    		$('#divDocsSeleccionadosCorreccionProv').css("height","0px");
    	} else {
    		$('#divDocsSeleccionadosCorreccionProv').css("height","auto");
    	}
    	
    	return documentoSeleccionado;
    	
	},$.rup.i18n.app.mensajes.seleccionDocumentos);
	
	$('#divDocsSeleccionadosNoConformidad').css("height","0px");
	$('#divDocsSeleccionadosCorreccionProv').css("height","0px");
	
	mostrarServicioEncriptacion();
	
	$(document).on('click', function (e) {
		ocultarObservaciones(e);
	});
	
	$("#subidaFicheroPid").find("#reqEncriptado").val(expedienteConfidencial);
	reqEncriptado = expedienteConfidencial;
	
	$("#porPenalizacionSwitch_revEntregaClte").bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOffLabel', $.rup.i18n.app.comun.no)
	.bootstrapSwitch('setOnLabel', $.rup.i18n.app.comun.si);
	
	$("#porPenalizacionSwitch_revEntregaClte").on('switch-change', function(event, state) {
		if (state.value){ //Activando
			$("#porcentajePenalizacion_revEntregaClte").attr("disabled", false);
			$("#porcentajePenalizacion_revEntregaClte").rules("add", "required");
		}else{
			$("#porcentajePenalizacion_revEntregaClte").attr("disabled", true);
			$("#porcentajePenalizacion_revEntregaClte").rules("remove", "required");
			$('#porcentajePenalizacion_revEntregaClte').val("");
		}
	});
	
	$("#calidad_combo_revEntregaClte").rup_combo({
    	source: "/aa79bItzulnetWar/administracion/datosmaestros/nivelesdecalidad",
	    sourceParam : {
	        value: "nivel",
	        label : "descRango",
	        style : "css"
	    }
		,width: "100%"	
		,rowStriping: true
		,ordered: false
		,disabled: ejecutarTareaConsulta ? true : false
		,open: function(){
			jQuery('#calidad_combo_revEntregaClte-menu').width(jQuery('#calidad_combo_revEntregaClte-button').innerWidth());
		}
	});
	
	cargarDatosLote();
	
});

function ocultarObservaciones(e){
	$('[data-toggle="popover"],[data-original-title]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
        }

	});
}
