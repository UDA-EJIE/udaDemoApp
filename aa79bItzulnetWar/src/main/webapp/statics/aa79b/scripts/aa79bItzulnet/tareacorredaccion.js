var horasReales;
var esHoraOblig = false;
var datosFormularioDoc ='';
//var listaTiposDoc;
var initDocumentoForm = '';
var extension = "";
var subidaTarea;
var idDocOriginal;
var reqEncriptado;
var tipoErrorDoc = 0;

function conversionKB( valor ){
	var num = parseFloat( (valor / 1024).toFixed(2));
	return num.toLocaleString('es-ES');
}

function comprobarCambiosFormulario(){
	return true;
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

function PrefinalizarTarea(){
	bloquearPantalla();
	var iFirmaNecesaria = 0;
	// validar documentos antes de finalizar (0: firma no necesaria)
	$.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/ejecuciontarea/validarDocumentosTareaFinalizarEntregaTrad/'+idTarea+'/'+iFirmaNecesaria
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,async: false
	     ,cache: false
	   	 ,success:function(documentosValidos){
	   		 if (documentosValidos){
	   			$.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.app,"label.documentosTradAccesibles"),
					OKFunction: function(){
						finalizarTarea();
					},
					CANCELFunction: function(e){
					}
				});
	   		}else{
	   			$('#ejecutarTareaDialog_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionFaltanFicheros, "error");
	   		}
	   		desbloquearPantalla();
	   	 }
  	 	,error: function(){
  	 		$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.tareaTraduccionErrorValidandoDocumentos, "error");
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

function clickPidButtonFinal(idDocO, laExtension, elReqEncriptado, nomDocOriginal){
	extension = laExtension;
	idDocOriginal = idDocO;
	nombreDocOriginal = nomDocOriginal;
	$('#reqEncriptadoTarea').val(elReqEncriptado);
	reqEncriptado = elReqEncriptado;
	$('#ficheroFinal').click();
}

function cambiarVisibilidad92(idDocOriginal, estado){
	var jsonObject = 
	{
		"idTarea": idTarea, 
		"idDocOriginal": idDocOriginal,
		"indVisible": (estado?"S":"N")
	};
	
	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/documentosgeneral/cambiarvisibilidad92' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(jsonObject) 
        ,async: false 
        ,success:function(){
        	$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
        }
    	,error: function(){
	   		$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		if(estado){
				$(this).bootstrapSwitch('setState', false);
			}else{
				$(this).bootstrapSwitch('setState', true);
			}
	   	 }
	});
}


function confirmEliminarDocFinal(){
	var selectedRows = $("#docsTraducir").rup_table('getSelectedRows');
	if (isEmpty(selectedRows)){
		$.rup_messages("msgAlert", {
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	 }else{
		 $.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
				OKFunction: function(){
					var elIdDoc = selectedRows[0].split(",");
					eliminarDocFinal(elIdDoc[1]);
				}
			});
	 }
}

function eliminarDocFinal(elIdDocOriginal){
	
	$.ajax({
        type: 'DELETE' 
        ,url: '/aa79bItzulnetWar/documentosgeneral/eliminarDocFinal/'+idTarea+'/'+elIdDocOriginal
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,beforeSend: function() {
        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.eliminando"));
        }
        ,success:function(){
        	$("#docsTraducir").rup_table('filter');
        	$("#docsTraducir").rup_table("resetSelection");
        	$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.borradoCorrecto, "ok");
        	desbloquearPantalla();
        }
		,error: function(error){
			$('#docsTraducir_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
			
			desbloquearPantalla();
	   	 }
	});
}


function mostrarInfoDocOriginal(cellval, opts, rowObject){
	var celda = '<p class="document-fileAndIcon"><a href="#" onclick="descargarDocumento('+rowObject.documentoOriginal.idDoc+')" class="document-eusk iconSameLine" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoOriginal.ficheros[0].nombre + '</a>';
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
	if(tituloYNombreDiferentes(rowObject.documentoOriginal.titulo,rowObject.documentoOriginal.ficheros[0].nombre )){
		celda+= '<span class="input-group-link aa79bInfoIconPopover aui"><a data-id="'+rowObject.documentoOriginal.idDoc+'" href="#" onclick="mostrarTitulo(\''+rowObject.documentoOriginal.titulo+'\',this, event)"><i class="fa fa-info-circle" aria-hidden="true"></i></a></span>';
	}
	celda += '</p>';
	return celda;	
}

function mostrarInfoDocFinal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',\''+rowObject.documentoOriginal.ficheros[0].nombre+'\')" class="btnPID ui-button ui-corner-all ui-widget" >'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';	
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="document-cast iconSameLine" >';
		celda+= rowObject.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>'
	}
	celda+='</div>';
	return celda;
} 

function mostrarColumnaVisible(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaContenidoVisible_'+ rowObject.documentoOriginal.idDoc +'" class="form-group no-padding col-xs-12">';
	if (rowObject.documentoAdjunto.idFichero != null && rowObject.documentoOriginal.claseDocumento !== 4){
		var esVisible = "";
		if(rowObject.documentoAdjunto.indVisible === "S"){
			esVisible = rowObject.documentoAdjunto.indVisible;
		}
		celda+='<input type="checkbox" name="indVisible" class="form-control" id="indVisibleFinal_'+ rowObject.documentoOriginal.idDoc +'" value="'+ esVisible + '" data-switch-pestana="true" />';	
	}
	celda+='</div>';
	return celda;
} 

function mostrarIconoOriginal(idClaseDocumento){
	if(idClaseDocumento){
		if(idClaseDocumento == 1){
			//clase documento traducir
			return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file"></i></span><span class="oculto">'+idClaseDocumento+'</span>';
		}else if(idClaseDocumento == 4){
			//clase documento trabajo
			return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file-text"></i></span><span class="oculto">'+idClaseDocumento+'</span>';
		}
	}
	return '<span class="ico-ficha-encriptado" title="'+$.rup.i18n.app.label.docOriginal+'"><i class="fa fa-file"></i></span><span class="oculto">'+idClaseDocumento+'</span>';
}

function volcarContenidoInfoDocFinal(docAdjunto){
	var contenidoCelda= '<div class="displayAsTable"><button id="pidButtonFinal_'+docAdjunto.idDocOriginal+'" type="button" onclick="clickPidButtonFinal('+docAdjunto.idDocOriginal+',\''+docAdjunto.extension+'\',\''+expedienteConfidencial+'\',\''+nombreDocOriginal+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:-4px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	contenidoCelda+='</div>';	
	if (docAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		contenidoCelda+= '<a href="#" onclick="descargarDocumentoGeneral('+ docAdjunto.idFichero +')" class="document-cast iconSameLine">';
		contenidoCelda+= docAdjunto.nombre + '</a>';
		contenidoCelda+= ' <span class="ico-ficha-encriptado" title="'+ ((docAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((docAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	return contenidoCelda;
}
function volcarContenidoColVisible(docAdjunto){
	var contenidoCelda= '';	
	if (docAdjunto.idFichero != null){
		contenidoCelda+='<input type="checkbox" name="indVisible" class="form-control" id="indVisibleFinal_'+ docAdjunto.idDocOriginal +'" value="S" data-switch-pestana="true" />';	
	}
	return contenidoCelda;
}





jQuery(function($) {
	//De finalizarTarea
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
	//Propio....
	$('#docsTraducir_feedback').rup_feedback(opcionesFeedbacks);
	
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
							if (isEmpty($("#docsXliff").rup_table("getDataIDs"))){
		                    	comprobacionPreviaXliff(anyoExpediente,idExpediente,"ejecutarTareaDialog_feedback","PrefinalizarTarea");
		                	} else {
		                		PrefinalizarTarea();
		                	}
						}
                }
			}
		]
	});
	$('#ejecutarTareaDialog_feedback').rup_feedback({
		block : false,
		delay: 3000
	})
	
	
	/* TABS Y TOOLBARS de los tabs */
	$("#tabsModalCorredaccion").rup_tabs({
		tabs : [
			{i18nCaption: labelDocsTraducir, layer:"#pestDocsTraducir"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});
	
	$("#docsTraducir_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.anadirDoc
				,click : 
					function(e){
                    e.preventDefault();
                    e.stopImmediatePropagation();
                    anadirDocumento();
				}
			},
			{
				i18nCaption: $.rup.i18n.app.boton.eliminarDoc
				,click : 
					function(e){
	                    e.preventDefault();
	                    e.stopImmediatePropagation();
	                    confirmEliminarDocFinal();
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
	
	/* RUP_TABLES */
	$("#docsTraducir").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/corredactar",
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
			labelDocFinal +" ("+(idiomaDestino=="1"?labelCastellano:labelEuskera)+")",
			"<i class='fa fa-eye' aria-hidden='true' title='"+labelvisibleClte+"'></i>"
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
			{ 	name: "documentoOriginal.claseDocumento", 
				hidden: false,
				width: "20", 
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
				width: "500",
				editable: false, 
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocFinal(cellval, opts, rowObject);
				}
			},
			{ 	name: "", 
				align: "center", 
				width: "100",
				editable: false, 
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarColumnaVisible(cellval, opts, rowObject);
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
					$("[id^='pidButton']").hide();
					$("#actualizarFhIZO").hide();
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
						cambiarVisibilidad92(elIdDocSplit,true);
					}else{
						cambiarVisibilidad92(elIdDocSplit,false);
					}
				});
			});
			
			habilitarPager('docsTraducir');
			$('#pg_docsTraducir_pager').find('td.pagControls select').hide();
		}
	});
	

	
	
	
	// SUBIDA PIF/PID T56 (la de siempre)
	
	$("[id^='pidButton']").unbind("click");
	$("[id^='pidButton']").click(function(event) {
		if(this.id==='pidButton_1') $("#idBotonUpload").val("1");
		else if(this.id==='pidButton_2') $("#idBotonUpload").val("2");//caso doc T56
		else $("#idBotonUpload").val("0");
		$('#fichero').click();
	});
	
	$('#reqEncriptado').val(expedienteConfidencial);
	
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
                     ,beforeSend: function () {
                            desbloquearPantalla();
                            bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardandoDoc"));
                        }
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
	                            $('#documentosexpediente_detail_feedback').rup_feedback("set", data.error, "error");        
	                        }
                            vaciarFileYDesbloquear();
                        }
                        , error: function(data){
                        $('#documentosexpediente_detail_feedback').rup_feedback("set", data.error, "error");
                        vaciarFileYDesbloquear();
                     }
                });
            }else{
                $('#documentosexpediente_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
                vaciarFileYDesbloquear();
            }    
        }
        , error: function(archivoPIF){
            $('#documentosexpediente_detail_feedback').rup_feedback("set", archivoPIF.error, "error");
            vaciarFileYDesbloquear();
        }            
	});
	
	 $("#fichero").change(function(){
	        $('#documentosexpediente_detail_feedback').rup_feedback("hide");
	        if ($("#fichero").val() !== ''){
	            if (comprobarExtensionValida($('#fichero').val())){
	                $("#subidaFicheroPid_form").submit();
	            }else{
	                $('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	            }
	        }
	    });
	
	
	

	// SUBIDA PIF/PID T88 (NUEVA TABLA DE DOCUMENTOS)
	function vaciarFileYDesbloquearFichFinal(){
			$("#ficheroFinal").val('');
			desbloquearPantalla();
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
				   					"idTablaIntermedia": tablaIntermedia.tabla92,
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
				   			    	 desbloquearPantalla();
				   			    	$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
				   			    	$("#fichero").val('');
				   			    	var elContenido = volcarContenidoInfoDocFinal(data);
				   					$('#capaCeldaDocFinal_'+ data.idDocOriginal).html(elContenido);
				   					
				   					//Para volcar o no el switch dependiendo de la clase del doc (trabajo no)
				   					var idFilaSel = $("#docsTraducir").rup_table("getSelectedRows");
				   					var inputClaseNum = $("#docsTraducir").rup_table("getCol", idFilaSel, "documentoOriginal.claseDocumento");
				   					var laClaseDoc = $(inputClaseNum).find("oculto").prevObject[1].innerHTML;
				   					if ( laClaseDoc !== '4'){
				   						var elContenidoColVisible = volcarContenidoColVisible(data);
					   					$('#capaCeldaContenidoVisible_'+ data.idDocOriginal).html(elContenidoColVisible);
					   					
					   					jQuery('#indVisibleFinal_'+data.idDocOriginal)
					   						.bootstrapSwitch()
					   						.bootstrapSwitch('setSizeClass', 'switch-small')
					   						.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
					   						.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
					   					if(data.indVisible === 'S'){
					   						$('#indVisibleFinal_'+data.idDocOriginal).bootstrapSwitch('setState', true);
					   					}else{
					   						$('#indVisibleFinal_'+data.idDocOriginal).bootstrapSwitch('setState', false);
					   					}
					   					$('#indVisibleFinal_'+data.idDocOriginal).on('switch-change', function(event, state) {
					   						var res = (event.currentTarget.id).split("_");
					   						elIdDocSplit = res[1];
					   						if (state.value){ //Activando
					   							cambiarVisibilidad92(elIdDocSplit,true);
					   						}else{
					   							cambiarVisibilidad92(elIdDocSplit,false);
					   						}
					   					});
					   					
				   					}
				   					
				   			     },error: function (e){
				   			    	desbloquearPantalla();
				   			    	$('#docsTraducir_feedback').rup_feedback("set", data.error, "error");
				   			     }
				   			});
				   			
				   			
				   		}else{
				   			desbloquearPantalla();
				   			$('#docsTraducir_feedback').rup_feedback("set", data.error, "error");
						}
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
				   		$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   	 }
				 });
			}else{//subida incorrecta
	   			desbloquearPantalla();
				$('#docsTraducir_feedback').rup_feedback("set", archivoPIF.error, "error");
			}
			vaciarFileYDesbloquearFichFinal();
		 }
		, error: function(data){
			$('#docsTraducir_feedback').rup_feedback("set", data.error, "error");
			vaciarFileYDesbloquearFichFinal();
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
	    			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18n.app.mensajes.errorFormatoZipDiferente, "error");
	    		}else{
	    			mostrarMensajeFeedback("ejecutarTareaDialog_feedback", $.rup.i18nParse($.rup.i18n.base,"rup_upload.acceptFileTypesError"), "error");
	    		}
	    		desbloquearPantalla();
	    	}
		}
	});
	
	
	
	
	/*FECHA FINAL IZO .......................     */
	
	
	
	$('#fechaFinalIzo_exp').val(fechaFinalIZO);
	$('#horaFinalIzo_exp').val(horaFinalIZO);
	$('#fechaFinalIzo_exp').rup_date();
	datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
	jQuery('#indVisible056_detail_table')
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	
	$("#datosExpedienteTradRev_form").rup_validate({
		feedback: $('#docsTraducir_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"fechaFinalIzo": {required: true, date: true},
			"horaFinalIzo": {required: true, hora: true,maxlength:5}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});		
	
	$("#actualizarFhIZO").click(function() {
		var jsonObject = 
		{
			"anyo": anyoExpediente,
			"numExp": idExpediente,
			"fechaFinalIzo": $('#fechaFinalIzo_exp').val(),
			"horaFinalIzo":  $('#horaFinalIzo_exp').val()
		};		
		if ($("#datosExpedienteTradRev_form").valid()){
			$.ajax({
			   	 type: 'PUT'
			   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expedientetradrev/guardarFFinalIzo'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(jsonObject)			 	
			     ,async: false
			   	 ,success:function(){			   		
			   		$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
			   		datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
			   		//Actualizar bitácora + cabecera
					bitacoraUpdate(true);
			   	 }
		   	 	,error: function(){		   	 	
			   		$('#docsTraducir_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
			   	 }
			 });
		}	
		
	});
	
	function comprobarCambiosFormFecha(){
		if ( datosFormularioDoc === $("#datosExpedienteTradRev_form").serialize()) {
			 return true;
		} else {
			return false;
		}
	}
	
	
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
		   			$("#documentosexpediente_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.numPalMaximoExcedido, "error");
		   		}
		   	 }
	   	 	,error: function(){
		   		$('#documentosexpediente_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  false;
		   	 }
		 });
		return error;
	}
	function anadirDocumento(){	
		$("#documentosexpediente_detail_div").rup_dialog("open");
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		$("#documentosexpediente_detail_form").rup_form('clearForm', true);
		clearValidation('#documentosexpediente_detail_form');		
		$("#documentosexpediente_detail_feedback").rup_feedback("close");
   		
   		$('#anyo056_detail_table').val(anyoExpediente);
   		$('#numExp056_detail_table').val(idExpediente);
   		
   		$('#indVisible056_detail_table').bootstrapSwitch('setState', true);
   		$('#enlaceDescargaDetalle_0').html('');
   		$('#enlaceDescargaDetalle_1').html('');
   		$('#capaVersiones_0').html('');
   		$('#capaVersiones_1').html('');
   		$('#pidButton_0').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
   		$('#pidButton_1').text($.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero"));
   		$('#claseDocumento056_detail_table').rup_combo("disable");
   		$("#nombreFicheroInfo_0").rules("add", "required");
		$("#nombreFicheroInfo_1").rules("add", "required");
		$("#titulo056_detail_table").rules("remove", "required");
   		
   		
		$('#capaDetClasificacion').show();
		$('#capaDetTipo').show();
		$('.capaDetContacto').show();
   		
   		initDocumentoForm = $("#documentosexpediente_detail_form").rup_form("formSerialize");
   		desbloquearPantalla();
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
			   			$("#documentosexpediente_detail_feedback").rup_feedback("set",$.rup.i18n.app.mensajes.errorDocsNoEncriptados, "error");
			   			retorno = false;
			   		}
		   		}else{
		   			$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorCargandoDatos, "error");
		   			retorno = false;
		   		}	
		   	 }
		 	,error: function(){	  	 		
		 		$('#datosExpedienteTradRev_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
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
				bloquearPantalla();
			}
			return vuelta;
		},
		success: function(data){
			if (data != null){
				//error de extensión no permitida
				if (( data.ficheros[0].error != null) || ((data.ficheros[1]!=null)&&(data.ficheros[1].error != null) )){
					if ( data.ficheros[0].error != null){
						$("#documentosexpediente_detail_feedback").rup_feedback("set", data.ficheros[0].error, "error");
						$('#nombreFicheroInfo_0').val('');
					}
					if ((data.ficheros[1]!=null)&&(data.ficheros[1].error != null) ){
						$("#documentosexpediente_detail_feedback").rup_feedback("set", data.ficheros[1].error, "error");
						$('#nombreFicheroInfo_0').val('');
						$('#nombreFicheroInfo_1').val('');
					}
				}else{ 
					//archivos permitidos
					//llamar al filter para que recargue la tabla con el nuevo registro
					$("#documentosexpediente_detail_div").rup_dialog("close");
					$("#docsTraducir").rup_table('filter');
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
				"ficheros[0].contacto.telefono":{digits:true, maxlength: 15},				
				"ficheros[0].oid":{ required:true},
				"ficheros[0].nombreUpload":{ required:true},
				
				"ficheros[1].contacto.persona":{ maxlength: 150},				
				"ficheros[1].contacto.email":{ maxlength: 256, email:true},				
				"ficheros[1].contacto.direccion":{ maxlength: 256},				
				"ficheros[1].contacto.entidad":{ maxlength: 150},				
				"ficheros[1].contacto.telefono":{digits:true, maxlength: 15},				
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
	//        showLoading:true,
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
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiposdocumento/soloalta/"+expedienteConfidencial,
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
				message: $.rup.i18n.app.comun.warningSeleccion
			});	
			return false;
		 }else{
			 $.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
					OKFunction: function(){
						var laFilaSeleccionada = $("#docsXliff").rup_table("getRowData", selectedRows[0]);
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
	        	$("#docsXliff").rup_table('filter');
	        	$("#docsXliff").rup_table("resetSelection");
	        	$('#docsXliff_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.borradoCorrecto, "ok");
	        	desbloquearPantalla();
	        }
			,error: function(error){
				$('#docsXliff_feedback').rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
				desbloquearPantalla();
		   	 }
		});
		
	}
	function mostrarInfoDocXliff(cellval, opts, rowObject){
		var celda =	'<div class="form-group no-padding col-xs-12">';
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
	$('#docsXliff_feedback').rup_feedback(opcionesFeedbacks);
	$('#idTarea_filter_Xliff').val(idTarea);
	
	$("#docsXliff").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/corredactar/xliff",
		colNames: [
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
			{ 	name: "documentoAdjunto.titulo", 
//			 	label: "label.gestorExp",
				align: "left", 
				width: "550",
				editable: false, 
				fixed: true, 
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
		primaryKey: ["idTarea", "documentoAdjunto.idFichero"],
		multiplePkToken:",",
		sortname : "ID_FICHERO_XLIFF_096",
		sortorder : "asc",
		rowNum: 5,
		gridComplete: function () {
			habilitarPager('docsXliff');
			$('#pg_docsXliff_pager').find('td.pagControls select').hide();
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
				   					"idDocOriginal":idDocOriginal,
				   					"idTarea": idTarea,
				   					"idTablaIntermedia": tablaIntermedia.tabla96,
				   					"idIdiomaDest":$('#idiomaDest').val() ,
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
										$("#docsXliff").rup_table('filter');
							        	$("#docsXliff").rup_table("resetSelection");
							        	$("#docsXliff_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
							        	$("#ficheroXliff").val('');
							        	desbloquearPantalla();
				   			    	 
				   			     },error: function (e){
				   			    	desbloquearPantalla();
				   			    	$('#docsXliff_feedback').rup_feedback("set", data.error, "error");
				   			     }
				   			});
				   			
				   			
				   		}else{
				   			desbloquearPantalla();
				   			$('#docsXliff_feedback').rup_feedback("set", data.error, "error");
						}
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
				   		$('#docsXliff_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
				   	 }
				 });
			}else{//subida incorrecta
	   			desbloquearPantalla();
				$('#docsXliff_feedback').rup_feedback("set", archivoPIF.error, "error");
			}
			
		 }
		, error: function(data){
			$('#docsXliff_feedback').rup_feedback("set", data.error, "error");
			desbloquearPantalla();
		}
	});
	
	$("#ficheroXliff").change(function(){
		bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
		if ($("#ficheroXliff").val() !== ''){
			if (comprobarExtensionValidaXliff($('#ficheroXliff').val())){
				$("#subidaFicheroPidXliff_form").submit();
	    	}else{ 
	    		$('#docsXliff_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"validaciones.extXliffTmx"), "error");
	    		desbloquearPantalla();
	    	}
		}
	});
	
	
});