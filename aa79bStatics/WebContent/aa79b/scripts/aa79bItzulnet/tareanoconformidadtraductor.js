var esHoraOblig = false;
var cambios;
var confidencial = false;
var reqEncriptado;
var subidaTarea;
var idDocOriginal;
var idTareaFormatter;
var listaTitulos = {};
var tipoErrorDoc = 0;
var idTareaFormat;
var listaTitulosDocs = {};
var opcionesFeedbacks = {block:false, delay: 3000, gotoTop: false};
var nombreDocOriginal = "";

function descargarDocumento(elIdDoc){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+elIdDoc);
	
}
function ocultarToolbarXliff(ocultar){
	if(ocultar){
		ocultarElementoPorId("docsXliff_toolbar");
	}
}
function ocultarElementoPorId(id){
	$('#'+id)[0].setAttribute("style","display:none");
}
function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+idFichero);
}
function descargarFicheroTarea(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);//88  2
}

function mostrarServicioEncriptacion(){
	if(!confidencial){
		$('#ejecutarTareaDialog_toolbar-rightButtons').hide();
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

/**
 * Muestra links de columna 'documento a revisar' de documentos originales
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocOriginal(cellval, opts, rowObject){
	var celda = '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumentoGeneral('+rowObject.documentoOriginal.idDoc+')" class="document-eusk iconSameLine" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoOriginal.ficheros[0].nombre + '</a>';
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' ;
	if(rowObject.documentoOriginal.ficheros[0].encriptado === 'S'){
		confidencial = true;
	}
	if(tituloYNombreDiferentes(rowObject.documentoOriginal.titulo,rowObject.documentoOriginal.ficheros[0].nombre )){
		celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.documentoOriginal.idDoc+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
	}
	celda += '</p>';
	return celda;
}

/**
 * Muestra botones de columna 'documento revisado' y en el caso de que hubiera un fichero, un link de descarga del mismo
 * @param cellval
 * @param opts
 * @param rowObject
 * @returns
 */
function mostrarInfoDocumentoTraducido(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+= '<p class="document-fileAndIcon"><a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
		celda+= rowObject.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda += '</p>';
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
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal____" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',\''+rowObject.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	if(rowObject.documentoJustificante.idFichero != null){
		celda+= '<a href="#" onclick="eliminarDocumentoRevisado('+rowObject.documentoOriginal.idDoc+')" class="flotaIzda rup-enlaceCancelar tarRevTradEliminar" >'+$.rup.i18n.app.comun.eliminar+'</a>';
		celda+='</div>';	
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoJustificante.idFichero +')" class="document-cast iconSameLine">';
		celda+= rowObject.documentoJustificante.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoJustificante.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoJustificante.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	} else {
		celda+= '</div>';
	}
	celda+='</div>';
	return celda;
}

function eliminarDocumentoRevisado(idDocOriginal){
	$.rup_messages("msgConfirm", {
		title: $.rup.i18n.app.comun.eliminar,
		message: $.rup.i18n.app.mensajes.preguntaEliminarFichero,
		OKFunction: function(){
			bloquearPantalla();
			$.ajax({
			   	 type: 'POST'		   	 
			   	 ,url: '/aa79bItzulnetWar/documentosgeneral/eliminarSoloFicheroFinal/'+idDocOriginal+'/'+idTarea
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,async: false
			     ,cache: false
			   	 ,success:function(extensionValida){
			   		 desbloquearPantalla();
			   		$('#docsNoConformidadTrad').rup_table("filter");
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
	$('#reqEncriptado').val(elReqEncriptado);
	reqEncriptado = elReqEncriptado;
	idDocOriginal = idDocO;
	nombreDocOriginal = nomDocOriginal;
	extension = laExtension;
	$('#ficheroFinal').click();
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
				   			    	$("#docsNoConformidadTrad").rup_table("filter");
				   					$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
				   					cambios = true;
				   					$("#ficheroFinal").val('');
				   					desbloquearPantalla();
				   			     },error: function (e){
				   			    	desbloquearPantalla();
				   			    	$("#ficheroFinal").val('');
				   			    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
				   			     }
				   			});
				   			
				   			
				   		}else{
				   			desbloquearPantalla();
				   			$("#ficheroFinal").val('');
				   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
						}
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
			   	 		$("#ficheroFinal").val('');
				   		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   	 }
				 });
			}else{//subida incorrecta
	   			desbloquearPantalla();
	   			$("#ficheroFinal").val('');
				$('#ejecutarTareaDialog_feedback').rup_feedback("set", archivoPIF.error, "error");
			}
			
		 }
		, error: function(data){
			$("#ficheroFinal").val('');
			$('#ejecutarTareaDialog_feedback').rup_feedback("set", data.error, "error");
			desbloquearPantalla();
		}
});
$("#ficheroFinal").change(function(){
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
			$("#ficheroFinal").val('');
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
function mostrarInfoGroupBy(cellval, opts, rowObject){
	groupIdPrefix = opts.gid + "ghead_";
    groupIdPrefixLength = groupIdPrefix.length;
    prefix = opts.rowId.substr(0, groupIdPrefixLength);
    var cell;
    
    if("docsRevisarTradIntghead_" !== prefix){
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


$("#pidButton_0").unbind("click");
$("#pidButton_0").click(function(event) {
	$("#idBotonUpload").val("0");
	$('#fichero').click();
});
jQuery(function($) {
	cambios = false;
	$('#docsNoConformidadTrad_feedback').rup_feedback(opcionesFeedbacks);
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
			ejecutarTareaReturn(false, "ejecutarTareaDialog", "tareasExpedientesForm", null, "tareasExpedientes_feedback");
			desbloquearPantalla();
		},
		error: function(error){
			//mostrar feedback de error y no cerrar la pestaña
			ejecutarTareaReturn(true, "ejecutarTareaDialog", null, null, null);
			desbloquearPantalla();
		}
	});
	
	$('#idTarea_filter').val(idTarea);
	
	$('#reqEncriptadoXliff').val(expedienteConfidencial);
	$('#idTarea_filter_Xliff').val(idTarea);
	$('#idTareaRel_filter_Xliff').val(idTareaRel);
	$('#anyo_filter_Xliff').val(anyoExpediente);
	$('#numExp_filter_Xliff').val(idExpediente);
	
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
							var valor = parseInt($("#porcentajePenalizacion_tradInt").val());
		                    if(valor!= '' && valor < 0 || valor > 100){
		                		$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajeMenorCien, "error");
		                    } else if ($("#porPenalizacionSwitch_tradInt").is(':checked') && parseInt($('#porcentajePenalizacion_tradInt').val()) > parseInt($("#retrasoVal_tradInt").val())) {
		                    	$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.porcentajePenalMayorQueLote, "error");
		                    } else {
			                    ocultarObservaciones(e);
			                    finalizarTarea();
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
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalNoConformidadTrad").rup_tabs({
		tabs : [
			{i18nCaption: labelDocusRevisarTradInt, layer:"#pestDocsNoConformidadTrad"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			 {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});
	
	/* RUP_TABLES */
	$("#docsNoConformidadTrad").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/docsNoConformidadTraductor",
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
			labelDocTraducir +" ("+(idiomaOrigen=="1"?labelCastellano:labelEuskera)+")",
			labelDocTraducido +" ("+(idiomaDestino=="1"?labelCastellano:labelEuskera)+")",
			labelDocRevisado +" ("+(idiomaDestino=="1"?labelCastellano:labelEuskera)+")"
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
			{ 	name: "documentoOriginal.ficheros[0].extension", 
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
				hidden: true,
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
				width: "200", 
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
				width: "250",
				editable: false, 
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocumentoTraducido(cellval, opts, rowObject);
				}
			},
			{
				name: "",
				align: "left",
				width: "350",
				editable: false,
				fixed: true,
				hidden: false,
				resizable: true,
				sortable: false,
				formatter: function (cellval, opts, rowObject, action){
					return mostrarInfoDocumentoRevisado(cellval, opts, rowObject);
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
     		habilitarPager('docsRevisarTradInt');
     		$('#pg_docsRevisarTradInt_pager').find('td.pagControls select').hide();
        },
        primaryKey: ["idDoc"],
		multiplePkToken:",",
		sortname : "IDDOC",
		sortorder : "asc",
		rowNum: 5,
		beforeSelectRow: function (){
			return false;
		},
		gridComplete: function () {
			ocultarElementoPorId("docsNoConformidadTrad_toolbar");
			if(ejecutarTareaConsulta){
				//ocultar los botones de adjuntar archivo
				ocultarBotonesPid();
				$('a.tarRevTradEliminar').remove();
			}
		}
	});
	
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
			return false;
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
     		$('#pg_docsXliff_pager').find('td.pagControls select').hide();
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
	
	$("#subidaFicheroPid").find("#reqEncriptado").val(expedienteConfidencial);
	reqEncriptado = expedienteConfidencial;
});
