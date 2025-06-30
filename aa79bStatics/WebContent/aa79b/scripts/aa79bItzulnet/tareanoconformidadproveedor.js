var idTipoTareaOrigen;
var idTareaOrigen;
var idiomaOrigen = '';
//***************funciones comunes - INICIO *****************/
function comprobarCambiosFormulario(){
	return true;
}
function ocultarToolbarXliff(ocultar){
	if(ocultar){
		ocultarElementoPorId("docsXliff_toolbar");
		ocultarElementoPorId("docsXliffRevisar_toolbar");
	}
}
function descargarDocumentoGeneral(idFichero){	
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docExpediente+'/'+idFichero);//56  1 TiposubidaEnum
}
function descargarFicheroTarea(idFichero){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/'+tipoSubida.docTareas+'/'+idFichero);//88  2
}
function ocultarElementoPorId(id){
	$('#'+id)[0].setAttribute("style","display:none");
}
function obtenerOrigenNoConformidad(){
	$.ajax({
			type : 'POST',
			url : '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerOrigenNoConformidad/' + idTarea,
			dataType : 'json',
			contentType : 'application/json',
			cache : false,
			success : function(data) {
				 if(data){
		   			 //traducir 2 - Revisar 4
		   			idTipoTareaOrigen = data.tipoTarea.id015;
		   			idTareaOrigen = data.idTareaRelacionada;
					if(tipoTarea.traducir.localeCompare(+idTipoTareaOrigen)==0){
						//cargar e inicializar datos traduccion
						cargarDatosTraduccion();
					}else if(tipoTarea.revisar.localeCompare(+idTipoTareaOrigen)==0){
						//cargar e inicializar datos revision
						cargarDatosRevision();
					}
	   		 	 }	
				desbloquearPantalla();
			 },
			error: function(e){
				//TODO
				mostrarMensajeFeedback("ejecutarTareaDialog_feedback",$.rup.i18n.app.mensajes.errorActualizarTareasInterAnteriores,"error");
			}
		});
}
function cargarDatosTraduccion(){
	// anyadimos literal de tipo de tarea original
	$('#descTarea')[0].textContent += " ( "+labelTraduccion+" )";
	// ocultamos contenedor rev y mostramos contenedor trad
	$('#noConformidadRevisar').hide();
	$('#noConformidadTraducir').show();
	// introducimos id de tarea en filtro de tabla
	$('#idTareaTraducir_filter').val(idTarea);
	$('#idTarea_filter_XliffTraducir').val(idTarea);
	// inicializamos tablas traduccion
	inicializarTablasTraduccion();
}

function cargarDatosRevision(){
	// anyadimos literal de tipo de tarea original
	$('#descTarea')[0].textContent += " ( "+labelRevision+" )";
	// ocultamos contenedor rev y mostramos contenedor trad
	$('#noConformidadRevisar').show();
	$('#noConformidadTraducir').hide();
	// introducimos id de tarea en filtro de tabla
	$('#idTareaRevisar_filter').val(idTarea);
	$('#idTarea_filter_XliffRevisar').val(idTarea);
	// inicializamos tabla revision
	inicializarTablasRevision();
}
//***************funciones comunes - FIN *****************/
//***************origen TRADUCIR - INICIO *****************/
function inicializarTablasTraduccion(){
	// inicializar tabs
	$("#tabsModalNoConformidadProveedor").rup_tabs({
		tabs : [
			{i18nCaption: labelDocsTraducir, layer:"#pestDocsTraducir"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliff"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});		
	// inicializar rup tables
	// inicializar feedback
	$('#docsTraducir_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	/* RUP_TABLES */
	$("#docsTraducir").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/getDocumentosNoConformidadProveedorTrad",
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			labelDocTraducir +" ("+(idiomaOrigen=="2"?labelEuskera:labelCastellano)+")",
			labelDocTraducido +" ("+(idiomaDestino=="2"?labelEuskera:labelCastellano)+")",
			labelSubsanarDocumentoTraducido
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
			},
			{ 	name: "docSubsanadoConcatenado", 
				align: "left", 
				width: "310	",
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocSubsanadoTrad(cellval, opts, rowObject);
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
		primaryKey: ["documentoOriginal.idDoc"],
		multiplePkToken:",",
		sortname : "IDDOC",
		sortorder : "asc",
		rowNum: 5,
   	 	loadOnStartUp: false,
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
	
	$("#docsTraducir").rup_table("filter");
		
	$("#docsXliff").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/getDocumentosXliffNoConformidadProveedorTrad",
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
		primaryKey: ["documentoAdjunto.idFichero"],
		multiplePkToken:",",
		sortname : "IDFICHEROXLIFF",
		sortorder : "asc",
		rowNum: 5,
		gridComplete: function () {
			ocultarElementoPorId("docsXliff_feedback");
			//ocultar toolbar xliff si estamos en modo consulta
			ocultarToolbarXliff(ejecutarTareaConsulta);
		}
	});
	
	$("#docsXliff").rup_table("filter");
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

function mostrarInfoDocFinal(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:3px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';	
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoAdjunto.idFichero +')" class="document-cast iconSameLine" >';
		celda+= rowObject.documentoAdjunto.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;
}
function mostrarInfoDocSubsanadoTrad(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+='<div class="displayAsTable">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:3px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';		
	if (rowObject.documentoJustificante.idFichero != null){
		celda+='<div class="displayAsTable">';
		celda+='<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoJustificante.idFichero +')" class="document-cast iconSameLine" >';
		celda+= rowObject.documentoJustificante.nombre + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoJustificante.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoJustificante.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+='</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;
}

	
function mostrarInfoDocXliff(cellval, opts, rowObject){
	var celda =	'<div class="form-group col-lg-12 no-padding">';
	if (rowObject.documentoAdjunto.idFichero != null){
			celda+='<div class="displayAsTable">';
			celda+= '<p class="document-fileAndIcon">';
		celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoAdjunto.idFichero +')" class="document-eusk iconSameLine" >';
		celda+= rowObject.documentoAdjunto.titulo + '</a>';
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>';
		celda+= '</p>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;

} 
//***************origen TRADUCIR - FIN *****************/
//***************origen REVISAR - INICIO *****************/
function inicializarTablasRevision(){
	// inicializar tabs
	$("#tabsModalNoConformidadProveedorRevisar").rup_tabs({
		tabs : [
			{i18nCaption: labelDocsTraducir, layer:"#pestDocsRevisar"},
			{i18nCaption: labelDocsXliff, layer:"#pestDocsXliffRevisar"}
		],
		cache: false,
		fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
			  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		] 
	});	
	
	// inicializar feedback
	$('#docsRevisar_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	
	$("#docsRevisar").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/getDocumentosNoConformidadProveedorRev",
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
			labelDocRevisar +" ("+(idiomaDestino=="2"?labelEuskera:labelCastellano)+")",
			labelDocumentoRevision,
			labelSubsanarDocumento,
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
				hidden: false,
				width: "15", 
				align: "center", 
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarIconoOriginal(cellval);
				}
			},			
			{ 	name: "docOriginalConcatenado" ,
				align: "left", 
				width: "260", 
				editable: false,
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocOriginalRev(cellval, opts, rowObject);
				}
			},
			{ 	name: "docFinalConcatenado", 
				align: "left", 
				width: "190",
				editable: false, 
				fixed: true,  
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocFinalRev(cellval, opts, rowObject);
				}
			},
			{ 	name: "", 
				align: "left", 
				width: "240	",
				editable: false, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				formatter: function (cellval, opts, rowObject, action) {
					return mostrarInfoDocSubsanadoRev(cellval, opts, rowObject);
				}
			},
			{
				name: "",
				align: "left",
				width: "190",
				editable: false,
				fixed: true,
				hidden: false,
				resizable: true,
				sortable: false,
				formatter: function (cellval, opts, rowObject, action){
					return mostrarinfoJustifRevisionRev(cellval, opts, rowObject);
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
		primaryKey: ["documentoOriginal.idDoc"],
		multiplePkToken:",",
		sortname : "IDDOC",
		sortorder : "asc",
		rowNum: 5,
   	 	loadOnStartUp: false,
		loadComplete: function(){
			habilitarPager('docsRevisar');
     		$('#pg_docsRevisar_pager').find('td.pagControls select').hide();
     		desbloquearPantalla();
        },
		loadOnStartUp: false,
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
	$("#docsRevisar").rup_table("filter");
	
	$("#docsXliffRevisar").rup_table({
		url: "/aa79bItzulnetWar/ejecuciontarea/getDocumentosXliffNoConformidadProveedorTrad",
		toolbar:{
			id: "docsXliffRevisar_toolbar"
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
     		habilitarPager('docsXliffRevisar');
        	$("#pg_docsXliffRevisar_pager_pager").find('td.pagControls select').hide();
        },
		primaryKey: ["documentoAdjunto.idFichero"],
		multiplePkToken:",",
		sortname : "IDFICHEROXLIFF",
		sortorder : "asc",
		rowNum: 5,
		gridComplete: function () {
			ocultarElementoPorId("docsXliffRevisar_feedback");
			//ocultar toolbar xliff si estamos en modo consulta
			ocultarToolbarXliff(ejecutarTareaConsulta);
		}
	});
	
	$("#docsXliffRevisar").rup_table("filter");
	
}
function mostrarInfoDocOriginalRev(cellval, opts, rowObject){
	var celda = '<div style="min-height: 25px;">';
	celda += '<p><a href="#" onclick="descargarDocumento('+rowObject.documentoOriginal.idDoc+')" class="flotaIzda document-eusk txtTablaTarea" style="margin-bottom:4px;" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
	celda+= rowObject.documentoOriginal.titulo;
	celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[0].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a></p>';
	celda += '</div>';
	if (rowObject.documentoOriginal.ficheros[1].idDocRel){
		celda += '<div style="min-height: 25px;">';
		celda+='<a href="#" onclick="descargarDocumento('+rowObject.documentoOriginal.ficheros[1].idDocRel+')" class="flotaIzda document-eusk txtTablaTarea" data-id="'+ rowObject.documentoOriginal.idDoc +'">';
		celda+= rowObject.documentoOriginal.titulo + '_' + $('#idiomaDestRevisar').val() ;
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[1].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[1].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
		celda += '</div>';
	}
	if(rowObject.documentoOriginal.ficheros[0].encriptado === 'S'){
		confidencial = true;
	}
	return celda;
}
function mostrarInfoDocFinalRev(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding" style="clear: both;">';
	if(esTecnicoIzo){
		celda+='<div>';
		celda+= '<button id="pidButtonFinal__" type="button" onclick="pasarDocumentoARevisarAFinal('+rowObject.documentoOriginal.idDoc+','+rowObject.documentoOriginal.ficheros[1].idDocRel+')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-bottom:4px;">'+$.rup.i18nParse($.rup.i18n.app,"label.docARevisarAFinal")+'</button>';
		celda+='</div>';
	}
	celda+='<div>';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\',\''+rowObject.documentoOriginal.ficheros[1].oid+'\')" class="btnPID ui-button ui-corner-all ui-widget" >'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+='</div>';
	if (rowObject.documentoAdjunto.idFichero != null){
		celda+='<div>';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoAdjunto.idFichero +')" class="flotaIzda document-eusk txtTablaTarea" >';
		celda+= rowObject.documentoAdjunto.titulo;
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoAdjunto.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoAdjunto.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
		celda+='</div>';
	}
	celda+='</div>';
	return celda;
}
function mostrarInfoDocSubsanadoRev(cellval, opts, rowObject){
	var celda =	'<div id="capaCeldaDocFinal_'+ rowObject.documentoOriginal.idDoc +'" class="form-group col-lg-12 no-padding">';
	celda+= '<button id="pidButtonFinal_'+rowObject.documentoOriginal.idDoc+'" type="button" onclick="clickPidButtonFinal('+rowObject.documentoOriginal.idDoc+',\''+rowObject.documentoOriginal.ficheros[0].extension+'\',\''+expedienteConfidencial+'\')" class="btnPID ui-button ui-corner-all ui-widget" style="margin-top:3px">'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';	
	if (rowObject.documentoOriginal.ficheros[2].idDocRel != null){
		celda+= '<a href="#" onclick="descargarFicheroTarea('+ rowObject.documentoOriginal.ficheros[2].idDocRel +')" class="flotaIzda txtTablaTarea document-eusk" >';
		celda+= rowObject.documentoOriginal.ficheros[2].nombre;
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoOriginal.ficheros[2].encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoOriginal.ficheros[2].encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
	}
	celda+='</div>';
	return celda;
}
function mostrarinfoJustifRevisionRev(cellval, opts, rowObject){
	var celda = '<div class="form-group col-lg-12 no-padding" style="clear: both;">';
	celda+='<div>';
	celda+= '<button id="pidButtonFinal____" type="button" onclick="clickPidButtonFinalJust('+rowObject.documentoOriginal.idDoc+')" class="btnPID ui-button ui-corner-all ui-widget no-obligatorio" >'+$.rup.i18nParse($.rup.i18n.app,"label.adjuntarFichero")+'</button>';
	celda+= '</div>';
	if(rowObject.documentoJustificante.idFichero != null){
		celda+='<div >';
		celda+= '<a href="#" onclick="descargarDocumentoGeneral('+ rowObject.documentoJustificante.idFichero +')" class="flotaIzda document-eusk txtTablaTarea">';
		celda+= rowObject.documentoJustificante.nombre;
		celda+= ' <span class="ico-ficha-encriptado" title="'+ ((rowObject.documentoJustificante.encriptado === 'S')?labelEncrip:labelNoEncrip) +'"><i class="fa fa-'+ ((rowObject.documentoJustificante.encriptado === 'S')?"":"un") +'lock" aria-hidden="true"></i></span>' + '</a>';
		celda+='</div>';	
	}
	celda+='</div>';
	return celda;
}
//***************origen REVISAR - FIN *****************/


jQuery(function($) {
	idiomaOrigen = '';
	if (idiomaDestino == "1"){
		idiomaOrigen = 2;
		$('#idiomaDestTraducir').val(labelES);
		$('#idiomaDestRevisar').val(labelES);
	}else{
		idiomaOrigen = 1;
		$('#idiomaDestTraducir').val(labelEU);
		$('#idiomaDestRevisar').val(labelEU);
	}
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
			}
		]
	});
	obtenerOrigenNoConformidad();
	
});