var horasReales;
var esHoraOblig = false;
var listaTiposDoc;
var confidencial = false;
var initDocumentoForm = '';
var extension = "";
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var idTareaFormatter;
var listaTitulos = {};
var idTareaFormat;
var listaTitulosDocs = {};
var tipoErrorDoc = 0;
var necesitaFirmarDocs = false;
var necesitaEnviarP43 = false;
var idiomaOrigen = '';
var docOriginal = false;
var zip = false;
var nombreDocOriginal = "";

function ocultarToolbarXliff(ocultar){
	if(ocultar){
		ocultarElementoPorId("docsXliff_toolbar");
	}
}

function ocultarElementoPorId(id){
	$('#'+id)[0].setAttribute("style","display:none");
}

function mostrarServicioEncriptacion(){
	if(!confidencial){
		$('#ejecutarTareaDialog_toolbar-rightButtons').hide();
	}
}

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function comprobarCambiosFormulario(){
	return true;
}

function llamadaWSIzoberri(){
	//llamada al WS de Boletin...
		$.ajax({
   	   	 type: 'GET'		   	 
   	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/comprobarWSIzoberri/T/'+idTarea
   	 	 ,dataType: 'json'
   	 	 ,contentType: 'application/json' 
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
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/comprobarFirmaDocumentos/T/'+idTarea
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
//	     ,async: false
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
		$("#confirmartarea").confirmar_tarea({esHoraOblig: esHoraOblig , tieneHora: true, documentos: false, modalSelector: "ejecutarTareaDialog"});
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
//	     ,async: false
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

var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};

function descargarDocumento(elIdDoc){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+elIdDoc);
}
function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);
}

function clickPidButtonFinal(idDocO){
	clickPidButtonFinal(idDocO,"");
}

function clickPidButtonFinal(idDocO,laExtension, elReqEncriptado, event, nomDocOriginal){
	event.preventDefault();
	event.stopImmediatePropagation();
	extension = laExtension;
	idDocOriginal = idDocO;
	nombreDocOriginal = nomDocOriginal;
	reqEncriptado = elReqEncriptado;
	$("#reqEncriptadoFinal").val(elReqEncriptado);
	$('#ficheroFinal').click();
}

function clickPidButtonDocOriginal(idDocO,laExtension, elReqEncriptado, event, nomDocOriginal){
	docOriginal = true;
	clickPidButtonFinal(idDocO,laExtension, elReqEncriptado, event, nomDocOriginal);
}

function pasarDocTraducidoATraducirAFinal(idDocOriginal,idDocTraducido){
	pasarDocATraducirAFinal(idDocOriginal,idDocTraducido,idTarea,'docsTraducidos');
}

function pasarDocATraducirAFinal(idDocOriginal,idDocTraducido,codTarea,idRupTable){
	if(!ejecutarTareaConsulta){
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/documentosgeneral/pasarDocATraduccionAFinal/'+idDocOriginal+'/'+idDocTraducido+'/'+codTarea+'/'+$('#idiomaDest').val()
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,async: false
		     ,cache: false
		   	 ,success:function(extensionValida){
		   		if(extensionValida.error === "S"){
		   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.docuNoTarea, "error");
		   	 	} else {
			   	 	$('#docsTraducir').trigger("reloadGrid");
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

function clickDocOriginalAFinal(idDocOriginal){
	pasarDocOriginalAFinal(idDocOriginal,idTarea,'docsTraducidos');
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
			   	 	$('#docsTraducir').trigger("reloadGrid");
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

function volcarContenidoInfoDocFinal(docAdjunto){
	var contenidoCelda= '<button id="pidButtonFinal_'+docAdjunto.idDocOriginal+'" type="button" onclick="clickPidButtonFinal('+docAdjunto.idDocOriginal+',\''+docAdjunto.extension+'\',\''+expedienteConfidencial+'\',event,\''+nombreDocOriginal+'\'); return false;" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:-4px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';	
	if (docAdjunto.idFichero != null){
		contenidoCelda+= '<a href="#" onclick="descargarDocumentoGeneral('+ docAdjunto.idFichero +')" class="flotaIzda document-eusk">';
		contenidoCelda+= docAdjunto.titulo;
		contenidoCelda+= ' <span class="ico-ficha-encriptado" title="'+ ((docAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((docAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';	
	}
	return contenidoCelda;
}

/* FORMATEAR CAMPOS RUP_TABLES */
function mostrarIconoOriginal(){
	return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file"></i></span>';
}

function mostrarInfoDocOriginal(cellval, opts, rowObject){
	var celda = '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumento('+rowObject.documentoOriginal.idDoc+')" class="document-eusk iconSameLine" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
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

function mostrarInfoDocFinalOriginal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal__" type="button" onclick="clickDocOriginalAFinal('+rowObject.documentoOriginal.idDoc+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.docTraduccionAFinal")+'</button>';
	celda+='</div>';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonDocOriginal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',event,\''+rowObject.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:4px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarDocumento")+'</button>';
	celda+='</div>';	
	if (rowObject.documentoFinalOriginal.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoFinalOriginal.idFichero +')" class="document-cast iconSameLine" >';
		celda+= rowObject.documentoOriginal.ficheros[0].nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;	
}

function mostrarInfoDocFinal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',event,\''+rowObject.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:4px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';	
	celda+='</div>';	
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="document-cast iconSameLine" >';
		celda+= rowObject.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;
} 

function mostrarIconoOriginalTraducido(idClaseDocumento){
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

function mostrarInfoDocOriginalTraducido(cellval, opts, rowObject){
	var celda = '<div style="min-height: 25px;">';
	celda += '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumento('+rowObject.documentoTarea.documentoOriginal.idDoc+')" class="document-cast iconSameLine" style="margin-bottom:4px;" data-id="'+ rowObject.documentoTarea.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre + '</a>';
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
	if(tituloYNombreDiferentes(rowObject.documentoTarea.documentoOriginal.titulo,rowObject.documentoTarea.documentoOriginal.ficheros[0].nombre )){
		celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.idTarea+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoTarea.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
	}
	celda += '</p>';
	celda += '</div>';
	return celda;
}

function mostrarInfoDocumentoTraducido(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoTarea.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	if(rowObject.documentoTarea.documentoAdjunto.idFichero != null){
		
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoTarea.documentoAdjunto.idFichero +')" class="document-cast iconSameLine">';
		celda+= rowObject.documentoTarea.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoTarea.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
		celda+='<div class="displayAsTable">';
		if(rowObject.documentoTarea.documentoOriginal.claseDocumento == 1){
			celda+= '<button id="pidButtonFinal__" type="button" onclick="pasarDocTraducidoATraducirAFinal('+rowObject.documentoTarea.documentoOriginal.idDoc+','+rowObject.documentoTarea.documentoAdjunto.idFichero+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.pasarAFinal")+'</button>';
		}
		celda+='</div>';
		if(rowObject.documentoTarea.documentoAdjunto.encriptado === 'S'){
			confidencial = true;
		}
	}
	celda+='</div>';
	return celda;
}

function servicioEncriptar(){
	window.open('/aa79bItzulnetWar/servicios/trataFicherosConfi/encripFicheros/maint');
}

function mostrarInfoGroupBy(cellval, opts, rowObject){
	groupIdPrefix = opts.gid + "ghead_";
    groupIdPrefixLength = groupIdPrefix.length;
    prefix = opts.rowId.substr(0, groupIdPrefixLength);
    var cell;
    
    if("docsTraducidosghead_" !== prefix){
    	idTareaFormatter = rowObject.idTareaAgrupacion;
    	cell = "";
    	var groupByText = rowObject.groupByText;
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

function ocultarObservaciones(e){
	$('[data-toggle="popover"],[data-original-title]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
        }

    });
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

$('#anyo_filter_Traducidos').val(anyoExpediente);
$('#numExp_filter_Traducidos').val(idExpediente);
$('#idTarea_filter_Traducidos').val(idTarea);
$('#reqEncriptado').val(expedienteConfidencial);







/* funcionalidad firma */

//formatter de la columna de firma de la rup_table
function mostrarInfoDocumentoFirmado(cellval, opts, rowObject){
	var celda = '';
	if (isEmpty( rowObject.documentoAdjunto.idFichero )){
		celda+= '-';
	}else{
		var txtEstadoFirma = estadoFirmaLabel.sinFirmar;
		switch (rowObject.documentoFinalFirmado.estadoFirma){
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
				txtEstadoFirma = '<a href="#" onclick="descargarDocumentoGeneral('+rowObject.documentoFinalFirmado.idFichero+')" class="flotaIzda document-eusk txtTablaTarea">';
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

//consulta si se debe mostrar o no la columna de firma
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
	
	$("#docsTraducir").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/traducirEntregaCliente",
		toolbar: {
	   		 id: "docsTraducir_toolbar",
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
	       		 				$("#docsTraducir").trigger('reloadGrid');
		    		    		$("#docsTraducir").rup_table("resetSelection");
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
	       		 			selectedRows = $("#docsTraducir").rup_table("getSelectedRows");

	       		 			for(var i=0;i<selectedRows.length;i++){
	     						 var j = selectedRows[i];
	     						 if ( $("#docsTraducir").rup_table("getCol", j, "documentoFinalFirmado.idFichero") !== '-'){
	     							 
	     							 if ((bopv || tramitagune) && $("#docsTraducir").rup_table("getCol", j, "documentoFinalOriginal.idFichero") == "" && !zip) {
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
                    			$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionDocumento, "error");
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
	      			    		    	cache: false,
	      			    		    	success: function (data){
	      			    		    		$("#docsTraducir").trigger('reloadGrid');
	      			    		    		$("#docsTraducir").rup_table("resetSelection");
	      			    					$('#docsTraducir_feedback').rup_feedback("set",  $.rup.i18n.app.mensajes.firmaSolicitada, "ok");
	
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
			labelDocTraducir +" ("+(idiomaOrigen=="1"?labelEuskera:labelCastellano)+")",
			labelUltimoEnTraducir,
			"",
			labelDocFinal +" ("+(idiomaDestino=="2"?labelEuskera:labelCastellano)+")",
			"",
			labelFirmado,
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
			{ 	name: "documentoAdjunto.indVisible", 
				hidden: true
			},
			{ 	name: "documentoAdjunto.oid", 
				hidden: true
			},
			{ 	name: "", 
				hidden: false,
				width: "50", 
				align: "center", 
				formatter: function () {
					return mostrarIconoOriginal();
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
			{ 	name: "docFinalOriginal", 
				hidden: false,
				width: "600", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					if (rowObject.documentoOriginal.ficheros[0].extension != "zip") {
						zip = false;
						return mostrarInfoDocFinalOriginal(cellval, opts, rowObject);
					} else {
						zip = true;
					}
					return "";
				}
			},
			{ 	name: "documentoFinalOriginal.idFichero", 
				align: "center", 
				width: "100",
				editable: false, 
				fixed: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
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
			{ 	name: "documentoFinalFirmado.estadoFirma", 
				align: "left", 
				width: "100",
				editable: false, 
				fixed: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "documentoFinalFirmado.idFichero", 
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
        ],
        model:"DocumentoTarea",
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
     	beforeSelectRow: function (){
			return necesitaFirmarDocs && !ejecutarTareaConsulta;
		},
     	loadComplete: function(){ 
     		if(bopv || tramitagune){
     			$("#docsTraducir").rup_table("showCol", "docFinalOriginal");
			}else{
				$("#docsTraducir").rup_table("hideCol", "docFinalOriginal");
			}
     		habilitarPager('docsTraducir');
        	$('#pg_docsTraducir_pager').find('td.pagControls select').hide();
        },
		primaryKey: ["idTarea", "documentoOriginal.idDoc"],
		multiplePkToken:",",
		sortname : "ID_DOC_056",
		sortorder : "asc",
		rowNum: 5,
		gridComplete: function () {
			$('#docsTraducir input:checkbox[data-switch-pestana="true"]').each(function(index, element){
				$(element)
				.bootstrapSwitch()
				.bootstrapSwitch('setSizeClass', 'switch-small')
				.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
				.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
				
				if(ejecutarTareaConsulta){
					var parentIndCorredaccion = $(element).parent().parent();
					parentIndCorredaccion.addClass("disabled");
					$(element).attr('disabled','disabled');
				}
			});
			//ocultarElementoPorId("docsTraducir_feedback");
			if(ejecutarTareaConsulta){
				//ocultar los botones de adjuntar archivo
				ocultarBotonesPid();
			}
			if (necesitaFirmarDocs && !ejecutarTareaConsulta){
				$('#docsTraducir_toolbar').show();
			}
		}
	});
	
}


jQuery(function($) {
	//De finalizarTarea
	if(horasObligatorias === 'S'){
		esHoraOblig = true;
	}
	
	if (bopv || tramitagune) {
		$("#notaDocsFirma").show();
	} 
	
	$('#docsTraducir_feedback').rup_feedback(opcionesFeedbacks);
	
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
                    	ocultarObservaciones(e);
						if(idTipoTarea==19){
							if (isEmpty($("#docsXliff").rup_table("getDataIDs"))){
								comprobacionPreviaXliff(anyoExpediente,idExpediente,"ejecutarTareaDialog_feedback","PrefinalizarTarea");
							}else{
								PrefinalizarTarea();
							}
						}
						else{
							comprobacionPreviaXliffAlinearPostEntrega(idTarea, "ejecutarTareaDialog_feedback", "PrefinalizarTarea");
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
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	})
	
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalEntregaCliente").rup_tabs({
		tabs : [
			{i18nCaption: labelDocusEntregar, layer:"#pestDocsEntregar"},
			{i18nCaption: labelTraducRealizadas, layer:"#pestDocsTraducidos"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
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
	
	/* RUP_TABLES */
	comprobarNecesidadFirmaDocs();
	
	$("#docsTraducidos").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/docsTraducidosEntregaClte",
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
			labelDocumentoATraducir +" ("+(idiomaOrigen=="1"?labelEuskera:labelCastellano)+")",
			labelDocumentoTraducido +" ("+(idiomaDestino=="2"?labelEuskera:labelCastellano)+")"
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
				width: "50", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarIconoOriginalTraducido(cellval);
				}
			},			
			{ 	name: "documentoTarea.docOriginalConcatenado" ,
				align: "left", 
				width: "475", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocOriginalTraducido(cellval, opts, rowObject);
				}
			},
			{ 	name: "documentoTarea.docFinalConcatenado", 
				align: "left", 
				width: "475",
				editable: false, 
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					
					return mostrarInfoDocumentoTraducido(cellval, opts, rowObject);
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
        loadComplete: function(){ 
        	habilitarPager('docsTraducidos');
        	$("#pg_docsTraducidos_pager").find('td.pagControls select').hide();
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
		sortname: "IDTAREAAGRUPACION",
		sortorder : "asc",
		rowNum: 5,
		beforeSelectRow: function (){
			return false;
		},
		gridComplete: function () {
			ocultarElementoPorId("docsTraducidos_feedback");
			if(ejecutarTareaConsulta){
				//ocultar los botones de adjuntar archivo
				ocultarBotonesPid();
			}
		}
	});
	
	// SUBIDA PIF/PID T56 (la de siempre)
	
	function comprobarExtensionValida(nombreFichero){
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
	
	// SUBIDA PIF/PID T88 (NUEVA TABLA DE DOCUMENTOS)
	
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
					   					"idTablaIntermedia": tablaIntermedia.tabla92,
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
						   			    	var elContenido = volcarContenidoInfoDocFinal(data);
						   					$('#capaCeldaDocFinal_'+ data.idDocOriginal).html(elContenido);			
						   					$("#docsTraducir").trigger("reloadGrid");
						   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
						   					docOriginal = false;
						   					desbloquearPantalla();
						   			     },error: function (e){
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
						   			    	var elContenido = volcarContenidoInfoDocFinal(data);
						   					$('#capaCeldaDocFinal_'+ data.idDocOriginal).html(elContenido);			
						   					$("#docsTraducir").trigger("reloadGrid");
						   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
						   					desbloquearPantalla();
						   			     },error: function (e){
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
				if(esNombreDocumentoTradRevValido("ficheroFinal",nombreDocOriginal, idiomaDestino)){
					$("#subidaFicheroPidFinal_form").submit();
				} else{
					mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.errorNombreDocTradRev"), concatenarNombreDocOriginalIdiomaDestYExt(nombreDocOriginal,idiomaDestino,"ficheroFinal")), "error");
					$("#ficheroFinal").val('');
	    			desbloquearPantalla();
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
		
	/* FUNCIONALIDAD DE AÑADIR DOC A LA 56 (DIALOGO DOCS) */
	function obtenerListaTiposDoc(){
		$.ajax({
		   	 type: 'GET'		   	 
		   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,cache: false
			 ,ordered: false	
		   	 ,success:function(data){
		   		if (!isEmpty(data)) {
		   			listaTiposDoc = data;
		   		}
		   	 }
	  	 	,error: function(){
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   	 }
		 });
	}
	obtenerListaTiposDoc();
	
	function validaNumPalabrasMaxExp(){
		var datos = jQuery("#documentosexpediente_detail_form").rup_form("formToJson");
		var error = true;
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/validaNumPalabrasMaxExp'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(datos)			
		     ,async: false
		   	 ,success:function(vueltaOk){
		   		if (!vueltaOk){
		   			error =  false;
		   			$("#ejecutarTareaDialog_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.numPalMaximoExcedido, "error");
		   		}
		   	 }
	   	 	,error: function(){
		   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  false;
		   	 }
		 });
		return error;
	}
	
	//FORMULARIO DE DETALLE
	function comprobarFicherosEncriptados(){
		var retorno = true;
		$.ajax({
		   	 type: 'GET'
		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/comprobarencriptados/'+anyoExpediente+"/"+idExpediente
		 	 ,dataType: 'json' 	 	 	
		 	 ,cache: false
		 	 ,async: false
		   	 ,success:function(data){
		   		if (data !== null){	   			
			   		if (data > 0){
			   			$("#ejecutarTareaDialog_feedback").rup_feedback("set",$.rup.i18n.app.mensajes.errorDocsNoEncriptados, "error");
			   			retorno = false;
			   		}
		   		}else{
		   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
		   			retorno = false;
		   		}	
		   	 }
		 	,error: function(){	  	 		
		 		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
		 		retorno = false;
		   	 }
		 });
		return retorno;
	}
	function actualizarT53ConfidencialSI(){
		var retorno = true;
		$.ajax({
			type: 'POST'
			,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/actualizarT53ConfidencialSI/'+anyoExpediente+'/'+idExpediente
			,dataType: 'json' 	 	 	
			,cache: false
			,async: false
			,success:function(){
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.expedConfidencialSI, "ok");
		   		expedienteConfidencial ='S';
		   		$('#reqEncriptadoXliff').val(expedienteConfidencial);
		   		$('#reqEncriptado').val(expedienteConfidencial);
			}
			,error: function(){	
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
				retorno = false;
			}
		});
		return retorno;
	}
	
	$("#documentosexpediente_detail_form").rup_form({
		url: "/aa79bItzulnetWar/documentosgeneral/docoriginal/"+idTarea,
		dataType: "json",			
		feedback:$("#documentosexpediente_detail_feedback"),		
		type: "POST",
		beforeSubmit: function(){ 
			var vuelta = validaNumPalabrasMaxExp();
			if (vuelta){
				//comprobar en el doc actual
				var tipoDocAux = listaTiposDoc.filter( function (x){ return x.id == $('#tipoDocumento056_detail_table').rup_combo('value') } );
				if	( (!($.isEmptyObject(tipoDocAux))  ) && (tipoDocAux[0].indConfidencial === "S")){
					if ( $('#indEncriptado056_0').val() == 'N' || (!isEmpty($('#indEncriptado056_1').val()) && $('#indEncriptado056_1').val() == 'N' ) ){
						$("#ejecutarTareaDialog_feedback").rup_feedback("set",$.rup.i18n.app.mensajes.tipoDocConfidencialFich, "error");
						vuelta = false;
					}else if (comprobarFicherosEncriptados()){
						vuelta = actualizarT53ConfidencialSI();
					}else{
						vuelta = false;
					}
				}
			}
			if (vuelta){
				bloquearPantalla();
			}
			return vuelta;
		},
		success: function(data){
			if (data != null){
				//error de extensión no permitida
				if (( data.ficheros[0].error != null) || ((data.ficheros[1]!=null)&&(data.ficheros[1].error != null) )){
					if ( data.ficheros[0].error != null){
						$("#ejecutarTareaDialog_feedback").rup_feedback("set", data.ficheros[0].error, "error");
						$('#nombreFicheroInfo_0').val('');
					}
					if ((data.ficheros[1]!=null)&&(data.ficheros[1].error != null) ){
						$("#documentosexpediente_detail_feedback").rup_feedback("set", data.ficheros[1].error, "error");
						$('#nombreFicheroInfo_0').val('');
						$('#nombreFicheroInfo_1').val('');
					}
				}else{ //archivos permitidos
					
					
					//llamar al filter para que recargue la tabla con el nuevo registro
					$("#documentosexpediente_detail_div").rup_dialog("close");
					$("#docsTraducir").trigger("reloadGrid");
		        	$("#docsTraducir").rup_table("resetSelection");
		        	$("#docsTraducir_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
				}
				desbloquearPantalla();
			}else{
				desbloquearPantalla();
				$("#documentosexpediente_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" data es null"), "error");		
			}
		},
		error: function(){
			desbloquearPantalla();
			$("#documentosexpediente_detail_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"+" error no success"), "error");
		},
		validate:{ 
			rules:{
				"claseDocumento":{ required:true},	//EXPED rev o trad	
				"tipoDocumento":{ required:true}, //clase de doc rev o trad
				"numPalIzo":{ required:true, maxlength: 6,integer: true},	//clase de doc rev o trad		
				"titulo":{ maxlength: 256},				
					
				"ficheros[0].contacto.persona":{ maxlength: 150},				
				"ficheros[0].contacto.email":{ maxlength: 256, email:true},				
				"ficheros[0].contacto.direccion":{ maxlength: 256},				
				"ficheros[0].contacto.entidad":{ maxlength: 150},				
				"ficheros[0].contacto.telefono":{ maxlength: 15},				
				"ficheros[0].oid":{ required:true},
				"ficheros[0].nombreUpload":{ required:true},
				
				"ficheros[1].contacto.persona":{ maxlength: 150},				
				"ficheros[1].contacto.email":{ maxlength: 256, email:true},				
				"ficheros[1].contacto.direccion":{ maxlength: 256},				
				"ficheros[1].contacto.entidad":{ maxlength: 150},				
				"ficheros[1].contacto.telefono":{ maxlength: 15},				
				"ficheros[1].oid":{ required:true},
				"ficheros[1].nombreUpload":{ required:true}
				},
				showFieldErrorAsDefault: false,
		        showErrorsInFeedback: true,
		        showFieldErrorsInFeedback: false
		}
	});	
	
	
	function fnCrearDialogDetalleDocumento(){
		$("#documentosexpediente_detail_div").rup_dialog({
	        type: $.rup.dialog.DIV,
	        autoOpen: false,
	        modal: true,
	        resizable: true,
	        width: 950,
	        title: $.rup.i18n.app.label.infoDocumento
		});
	}
	if ($( "div[aria-describedby='documentosexpediente_detail_div']" ).length){
		$( "div[aria-describedby='documentosexpediente_detail_div']" ).remove();
	}
	fnCrearDialogDetalleDocumento();
	
	$("#documentosexpediente_detail_button_save").button().click(function(){
		if ($("#documentosexpediente_detail_form").valid()){		
			$("#documentosexpediente_detail_form").submit();
		}
    });
	
	$("#documentosexpediente_detail_link_cancel").button().click(function(){
		if (initDocumentoForm !== $("#documentosexpediente_detail_form").rup_form("formSerialize")){
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					setTimeout(function() {
						$("#documentosexpediente_detail_div").rup_dialog("close");
						eliminarMensajes();
						}, 20);
				}
			});
		}else{
			$("#documentosexpediente_detail_div").rup_dialog("close");
		}
    });	
	
		
	//Elementos del detalle de doc
	$('#documentosexpediente_detail_feedback').rup_feedback(opcionesFeedbacks);
	$('#tipoDocumento056_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta",
		sourceParam :{
			label: $.rup.lang === 'es' ? "descConRelevanciaEs":"descConRelevanciaEu", 
			value: "id",
			style:"css"
		}
		,blank: ""
		,width: "250"
		,ordered: false	
		,rowStriping: true
		,open: function(){
	        jQuery('#tipoDocumento056_detail_table-menu').width(jQuery('#tipoDocumento056_detail_table-button').width());
	    }
		,onLoadSuccess: function() {
			crearClaseDocumentoCombo();
		}
	});
	
	
	
	
	function crearClaseDocumentoCombo(){
		$('#claseDocumento056_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/clasificaciondocumentos/tipoexpediente/"+tipoExpedienteTrad,
			sourceParam :{
				label: $.rup.lang === 'es' ? "descEs075":"descEu075", 
						value: "id075",
						style:"css"
			}
			,width: "170"
			,ordered: false	
			,rowStriping: true
			,open: function(){
				jQuery('#claseDocumento056_detail_table-menu').width(jQuery('#claseDocumento056_detail_table-button').width());
			}
			,change: function(){
				switch (this.value){
					case '1': //Traducción, esto mas lo del 1
						$('#capaPidButton_0').hide();
						$('#capaPidButton_0').hide();
						$('#capaDivPid_0').hide();
						$('#capaFichero_0').show();
						$('#capaFichero_1').hide();
						$('#legendFichero_0').hide();
						$('#legendFichero_1').hide();
						$('#capaDetTipo').show();
						if (origen==='C') $('#capaNumPalSolic').show();
						else $('#capaNumPalSolic').hide();
						$('.capaDetContacto').show();
						$("#tipoDocumento056_detail_table").rules("add", "required");
						break;
					default: //Apoyo
						break;
				}
			}
		});
	}
	
	
	
	
	
	
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
	
	mostrarServicioEncriptacion();
	
	$(document).on('click', function (e) {
		ocultarObservaciones(e);
	});
	
});
