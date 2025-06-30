

/*
 * ****************************
 * VARIABLE GLOBALES - INICIO
 * ****************************
 */
var tareaObj;
var arrFicheros = [];
var zip = false;
var desactivarAsignaciones = false;
var ejecutarTareaConsulta = false;
var accionRechazo = false;
/*
 * ****************************
 * VARIABLE GLOBALES - FIN
 * ****************************
 */
/*
 * ****************************
 * CAMBIOS PANTALLA - INICIO
 * ****************************
 */

//configuracion tiny con subida de imagenes a blob
var conf = {
		selector : '#anadirNotaTiny',
		height : 300,
		theme : 'modern',
		body_class: 'aa79b-content',
//		language : 'es'	
		setup: function(ed) {ed.on('init', function(e) {});},		
		plugins: [
			'advlist autolink lists image charmap preview hr anchor pagebreak',
			'searchreplace wordcount visualblocks visualchars code fullscreen',
			'insertdatetime link nonbreaking save directionality',
			'emoticons template paste textcolor colorpicker textpattern imagetools table'
			],			 			
			menu: {    			
			edit: {title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall'},
			insert: {title: 'Insert', items: 'link  image | charmap hr pagebreak date'},
			view: {title: 'View', items: ' visualchars visualblocks visualaid | preview'},
			format: {title: 'Format', items: 'bold italic underline strikethrough | formats | removeformat | codesample'},
                        table: {title: 'Table', items: 'inserttable tableprops deletetable | cell row column'},		
			},  							
		toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link unlink image preview',
		image_advtab: true,
                table_advtab: false,
                table_cell_advtab: false,
                table_row_advtab: false,
                table_appearance_options: false,
		valid_elements:'*[*]',
		extended_valid_elements:'script[language|type|src|charset],',
		valid_children:'-p[script]',
		relative_urls: false,
		verify_html: false,
		allow_script_urls:true,			
		remove_script_host : false,				
		convert_urls : false,	
		code_dialog_width: 1024,
		code_dialog_height: 600,
                automatic_uploads: true,
                file_picker_types: 'image',
                file_picker_callback: (cb, value, meta) => {
                const input = document.createElement('input');
                input.setAttribute('type', 'file');
                input.setAttribute('accept', 'image/*');
                input.addEventListener('change', (e) => {
                const file = e.target.files[0];
                const reader = new FileReader();
                reader.addEventListener('load', () => {
        /*
          Note: Now we need to register the blob in TinyMCEs image blob
          registry. In the next release this part hopefully won't be
          necessary, as we are looking to handle it internally.
        */
                const id = 'blobid' + (new Date()).getTime();
                const blobCache =  tinymce.activeEditor.editorUpload.blobCache;
                const base64 = reader.result.split(',')[1];
                const blobInfo = blobCache.create(id, file, base64);
                blobCache.add(blobInfo);
        /* call the callback and populate the Title field with the file name */
               cb(blobInfo.blobUri(), { title: file.name });
                });
              reader.readAsDataURL(file);
             });

             input.click();
  },			
		formats : {			
			alignleft: [
						{selector : 'p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li,table', classes : 'r01AlignLeft'},
						{selector : 'img', classes : 'r01AlignImgLeft', ceFalseOverride: true},
						{selector: 'figure.image', collapsed: false, classes: 'align-left', ceFalseOverride: true},
						{selector: 'figure,p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li',
							styles: {textAlign: 'left'},
							inherit: false,
							defaultBlock: 'div'
						},
						{selector: 'img,table', collapsed: false, styles: {'float': 'left'}}
					],		
			aligncenter: [
							{selector : 'p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li,table,img', classes : 'r01AlignCenter'},							
							{selector: 'figure,p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li',
								styles: {textAlign: 'center'},
								inherit: false,
								defaultBlock: 'div'
							},
							{selector: 'figure.image', collapsed: false, classes: 'align-center', ceFalseOverride: true},
							{selector: 'img', collapsed: false, styles: {display: 'block', marginLeft: 'auto', marginRight: 'auto'}},
							{selector: 'table', collapsed: false, styles: {marginLeft: 'auto', marginRight: 'auto'}}
						],					
			alignright: [
			             	{selector : 'p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li,table', classes : 'r01AlignRight'},
			             	{selector : 'img', classes : 'r01AlignImgRight', ceFalseOverride: true},
							{selector: 'figure.image', collapsed: false, classes: 'align-right', ceFalseOverride: true},
							{selector: 'figure,p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li',
								styles: {textAlign: 'right'},
								inherit: false,
								defaultBlock: 'div'
							},
							{selector: 'img,table', collapsed: false, styles: {'float': 'right'}}
						],						
			alignjustify: [
			               	{selector : 'p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li,table', classes : 'r01AlignJustify'},
							{selector: 'figure,p,h1,h2,h3,h4,h5,h6,td,th,tr,div,ul,ol,li',
								styles: {textAlign: 'justify'},
								inherit: false,
								defaultBlock: 'div'
							}
						],
			valigntop: [
				{selector: 'td,th', classes: 'r01VAlignTop'},        
				{selector: 'td,th', styles: {'verticalAlign': 'top'}}
			],

			valignmiddle: [
				{selector: 'td,th', classes: 'r01VAlignMiddle'},           
				{selector: 'td,th', styles: {'verticalAlign': 'middle'}}
			],

			valignbottom: [
				{selector: 'td,th', classes: 'r01VAlignBottom'},           
				{selector: 'td,th', styles: {'verticalAlign': 'bottom'}}
			],			
			bold : [
				{inline: 'strong', remove: 'all'},
				{inline: 'span', classes: 'r01Bold'},
				{inline: 'span', styles: {fontWeight: 'bold'}},									
				{inline: 'b', remove: 'all'}
			],
			italic : [
				{inline: 'em', remove: 'all'},	
				{inline: 'span', classes : 'r01Italic'},
				{inline: 'span', styles: {fontStyle: 'italic'}},							
				{inline: 'i', remove: 'all'}								
			],
			underline : [					
				{inline: 'span', classes : 'r01Underline'},
				{inline: 'span', styles: {textDecoration: 'underline'}, exact: true},
				{inline: 'u', remove: 'all'}
			],
			strikethrough: [
				{inline: 'del', remove: 'all'},
				{inline: 'span', styles: {textDecoration: 'line-through'}, exact: true},
				{inline: 'strike', remove: 'all'}
			]			
		},
		image_class_list: [
			{title: '', value: ''},
			{title: 'Align left', value: 'r01AlignImgLeft'},
			{title: 'Align left (for list)', value: 'r01AlignImgLeftList'},
			{title: 'Align right', value: 'r01AlignImgRight'}
			],
		 templates: [
			    {title: 'Some title 1', description: 'Some desc 1', content: 'My content'},
			    {title: 'Some title 2', description: 'Some desc 2', url: '/comun/tinymce4/examples/development.html'}
			  ],
		 template_selected_content_classes: "selcontent selectedcontent",	  
		 content_css: [STATICS+"/aa79b/styles/aa79b.css",STATICS+"/itzulnet/css/itzulnet.css"]	
	};


function ocultarMenusDesplegables(){
	$("[id='detalleTarea_form_toolbar']").hide();
}

function fnCrearEjecutarTareaDialog(){
	$("#ejecutarTareaDialog").rup_dialog({
		 type: $.rup.dialog.DIV,
		 title: $.rup.i18n.app.boton.ejecutarTareas,
		 autoOpen: true,
		 modal: true,
		 resizable: false,
		 width: 950
		 ,beforeClose:function(event, ui){
			findTarea();			
			return comprobarCambiosFormulario();
		}
	});
}

function fnComprobarTareaEjecutada(){
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerEstadoEjecucionTarea/' + anyoExpediente + '/' + idExpediente + '/' + idTarea
		,dataType: 'json'  
	   	,success:function(data, textStatus, jqXHR){
			if (data && data.estadoEjecucion){
//				ejecutarTareaConsulta = datosTareas.estadoEjecucion.ejecutada != data.estadoEjecucion;
				// se puede ejecutar si no esta en estado ejecutada
				if (estadoEjecucion.value.ejecutada != data.estadoEjecucion) {
					ejecutarTareaConsulta = false;
					fnComprobarTareaAsignador(idTarea,idTipoTarea)
				} else {
					ejecutarTareaConsulta = true;
					// tarea ejecutada
					//Utilizamos msgConfirm ya que el evento beforeClose de msgAlert no funciona
					$.rup_messages("msgConfirm", { 
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
							message: $.rup.i18n.app.mensajes.ejecucionTareaEjecutada,
							OKFunction : function(){
								volverACapaGeneral();
							}
					});
					//Retocamos msgConfirm para que sea identico a msgAlert
					// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
					$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
					// ocultamos el close de la ventana
					$(".ui-dialog-titlebar-close").hide();
					// eliminamos el boton cancelar
					$(".rup-enlaceCancelar").remove();
					// sustituimos el icono del confirm por el de alert
					$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
					return false;
				}
			} else {
				// no se puede ejecutar, tareas anteriores pendientes
				$.rup_messages("msgConfirm", { 
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"),
						OKFunction : function(){
							volverACapaGeneral();
						}
				});
				//Retocamos msgConfirm para que sea identico a msgAlert
				// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
				$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
				// ocultamos el close de la ventana
				$(".ui-dialog-titlebar-close").hide();
				// eliminamos el boton cancelar
				$(".rup-enlaceCancelar").remove();
				// sustituimos el icono del confirm por el de alert
				$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
				ejecutarTareaConsulta = true;
				return false;
			}
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
}

function fnEjecutarTarea(idTipoTarea){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/ejecutarTarea/'+ idTipoTarea  
	   	,success:function(data, textStatus, jqXHR){
	   		$("#ejecutarTareaDialog").remove();
	   		$("#ejecutarTareaDialog").rup_dialog('destroy');
	   		$("#divTareasExpedientes").append('<div id="ejecutarTareaDialog" style="display:none"></div>');
	   		$("#ejecutarTareaDialog").html(data);
	   	    fnCrearEjecutarTareaDialog();
	   	    
	   	    dialogoSoloConsulta(ejecutarTareaConsulta, "ejecutarTareaDialog");
	   	 desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	
}

function fnComprobarTareaAsignador(idTarea,idTipoTarea){
	
	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
	var d = new Date();
	$.ajax({
        type: 'GET' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/comprobarTareaAsignador/'+idTarea+'/'+anyoExpediente+'/'+idExpediente+'/'+idTipoTarea+'?d='+d.getTime()
        ,dataType: 'json' 
        ,async: false
        ,cache: false
        ,success:function(data){
        	if (data != null) {
        		if (data === 0) {
            		fnEjecutarTarea(idTipoTarea);
            	} else if (data === 1) {
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.asignacionTarea"), "error");
            	} else if (data === 2) {
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"), "error");
            	} else {
            		// data === 3
            		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.expEnCursoPdteTramClte"), "error");
            	}
        	}
        	
        	desbloquearPantalla();
        	
        }
		,error: function(error){
			$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
			desbloquearPantalla();
	   	 }
	});
	
}

function comprobarEstadoTarea(){
	$.rup_ajax({
		 type: "GET",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/comprobarEstadoTarea/' + idTarea,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	
			if (data){
				if (accionRechazo){
					detalle = true;
	                selectedIdTarea = idTarea;
	                $("#rechazarAsignacionDetalle_dialog").rup_dialog('open');
				} else{
					$.rup_messages("msgConfirm", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.aceptarAsignacion"),
						OKFunction: function(){
							bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
							aceptarAsignacionDetalle(estadoAceptacion.value.aceptada);
						}
					});
				}
	       } else {
	    	   if (accionRechazo){
		           $.rup_messages("msgAlert", {
			   	 	   title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
					   message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
				   });	
	    	   } else {
	    		   $.rup_messages("msgAlert", {
			   	 	   title: $.rup.i18nParse($.rup.i18n.app,"boton.aceptarAsignacion"),
					   message: $.rup.i18nParse($.rup.i18n.app,"mensajes.sinEstarAsignada"),
				   });	
	    	   }
	       }
		 }
	 });              
}

function crearTablaNotas(){
	var jsonObj = {
			"anyo" : anyoExpediente,
			"numExp" : idExpediente
		};
	
	$("#notasExpediente_div").remove();
	jQuery.ajax({
			type : "POST",
			url : "/aa79bItzulnetWar/notasexpedientes/getNotasExpediente",
			cache : false,
			dataType : "json",
			contentType : 'application/json',
			data : $.toJSON(jsonObj),
			success : function(data) {
				
				var item = '<div id="notasExpediente_div"> \
					  <div class="triangulo_sup"></div> \
					  <div class="bitacora-modal"> \
					    <div class="modal-header"> \
					      <h2>'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.notas") + '</H2> \
					      <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">+</a> \
					    </div> \
					    <div class="tabla-mods" style="max-height: 90vh; overflow: auto;"> \
					      <div class="row cabecera-mods col-md-12"> \
					        <div class="col-md-3">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.quienCuando") + '</div> \
					        <div class="col-md-9">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.nota") + '</div> \
					      </div>';
					if (data !== null
							&& data.length > 0) {
					
						for (var i = 0; i < data.length; i++) {
							var notaExpediente = data[i];
							item += '<div class="row body-mods col-md-12" style="padding-right: 0;"> \
						        <div class="col-md-3 first">'+  notaExpediente.usuario +'<br />'+notaExpediente.fechaHoraNota+'</div> \
						        <div class="col-md-9 pr1">'+ notaExpediente.nota +'</div> \
						      </div>'; 
						}	
					}
					item += '<div class="row body col-md-12" style="margin: 0; padding: 0;"><hr><br/> \
						<div class="col-md-3 first"><button id="anadirNotaButton" class="ui-button ui-widget ui-state-default ui-corner-all" style="width:90%">'+ $.rup.i18nParse($.rup.i18n.app,"boton.anadir") + '</button> </div> \
						 <div class="col-md-9" style="float:right"> \
						 <textarea name="nota" id="anadirNotaTiny"></textarea> \
						</div> \
						 </div>';
							
						
					item += '</div> \
					  </div> \
						<div class="ui-widget-overlay ui-front" style="z-index: 100;"></div> \
					</div>';
					
					var itemDiv = $(item);
					itemDiv.appendTo("#divTareasExpedientes");
					
					//creacion de TinyMCE
					tinymce.init(conf);
					
				$(".close-button").each(function() {
					$(this).on("click", function() {
						tinymce.activeEditor.setContent('');
						tinymce.remove('#anadirNotaTiny');
						$("#notasExpediente_div").remove();
						$("#notasExpediente_div .ui-widget-overlay.ui-front").remove();
					});
				});
				
				$("#anadirNotaButton").on("click",function(){
					$("#anadirNotaTiny").tinymce().save();
					
					if($("#anadirNotaTiny").val()!=null &&  $("#anadirNotaTiny").val()!=""){
						var jsonObj = {
							"anyo" : anyoExpediente,
							"numExp" : idExpediente,
							"nota": $("#anadirNotaTiny").val()
						};
						jQuery.ajax({
							type : "POST",
							url : "/aa79bItzulnetWar/notasexpedientes",
							cache : false,
							dataType : "json",
							contentType : 'application/json',
							data : $.toJSON(jsonObj),
							beforeSend: function() {
					        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"));
					        },
							success : function(data) {
								tinymce.activeEditor.setContent('');
								tinymce.remove('#anadirNotaTiny');
								desbloquearPantalla();
								crearTablaNotas();
								//Resalto el boton notas por si se ha introducido la primera nota
								$("#notasExpediente_button").attr('style', 'background-color:#B0FF57');
							}
					
						});
					} else {
						$.rup_messages("msgAlert", {
							title: $.rup.i18n.app.label.aviso,
							message: $.rup.i18n.app.mensajes.errorDescVacio
						});
						desbloquearPantalla();
						return false;
					}
				});
			}
	});
}

function volverACapaGeneral(){
	if (typeof(formatoPestana) === "undefined"){
		$("#rechazarAsignacionDetalle_dialog").remove();
		$("#rechazarAsignacionDetalle_dialog").rup_dialog('destroy');
		$("#divTareasExpedientes").append('<div id="rechazarAsignacionDetalle_dialog" style="display:none"></div>');
		
		$("#divTareasExpedientes").detach();
		$("#divCargaTrabajoGeneral").html(capaPestGeneral);
		var active = $( "#tabsCargaTrabajo" ).tabs( "option", "active" );
		if(active == 0){
			$("#busquedaGeneral").trigger('reloadGrid');
		}
		if(active ==1){
			$("#busquedaGeneralInter").trigger('reloadGrid');
		}
		comprobarTareasPendientes();
    }else{
    	$("#divPlanificacionCapa").detach();
    	$("#divPlanificacion").html("<div id='divPlanificacionCapa'></div>");
    	$("#divPlanificacionCapa").html(capaPestGeneral);
    	// recargar la tabla de la pestaña de dashboard  de carga de datos si existe, por si se ha ejecutado la tarea
    	$(".ui-jqgrid-btable").trigger('reloadGrid');
    }
	
}

function editarVistaDetalle(){
	$("#detalleTarea_filter_toolbar").removeAttr("style").hide();
	$("#detalleTarea_filter_div").removeAttr("style").hide();
	$("#detalleTarea_form_div_fieldset label").css("border","none");
	
	//TOOLBAR
	if ($("#estadoAsignadaId").val() === estadoAceptacion.value.pdteAceptacion && dniUsuSesion === parseInt($("#dniAsignada").val())){
		$("[id=detalleTarea_form_toolbar##aceptar]").prop('disabled', false);
		$("[id=detalleTarea_form_toolbar##aceptar]").removeClass('ui-button-disabled ui-state-disabled');
		$("[id=detalleTarea_form_toolbar##rechazar]").prop('disabled', false);
		$("[id=detalleTarea_form_toolbar##rechazar]").removeClass('ui-button-disabled ui-state-disabled');
	} else {
		$("[id=detalleTarea_form_toolbar##aceptar]").prop('disabled', true);
		$("[id=detalleTarea_form_toolbar##aceptar]").addClass('ui-button-disabled ui-state-disabled');
		$("[id=detalleTarea_form_toolbar##rechazar]").prop('disabled', true);
		$("[id=detalleTarea_form_toolbar##rechazar]").addClass('ui-button-disabled ui-state-disabled');
	}
	if ($("#estadoAsignadaId").val() === estadoAceptacion.value.aceptada && ($("#estadoEjecId").val() === estadoEjecucion.value.pdteEjecucion || $("#estadoEjecId").val() === estadoEjecucion.value.retrasada)  && dniUsuSesion === parseInt($("#dniAsignada").val())){
		$("[id=detalleTarea_form_toolbar##ejecutar]").prop('disabled', false);
		$("[id=detalleTarea_form_toolbar##ejecutar]").removeClass('ui-button-disabled ui-state-disabled');
	} else {
		$("[id=detalleTarea_form_toolbar##ejecutar]").prop('disabled', true);
		$("[id=detalleTarea_form_toolbar##ejecutar]").addClass('ui-button-disabled ui-state-disabled');
	}
}

function aceptarAsignacionDetalle(estado){
	$.rup_ajax({
		 type: "GET",
		 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/aceptarAsignacion/'+idTarea+'/'+estado,
		 dataType: "json",
		 contentType: "application/json",
		 cache: false,	
		 success: function(data){	
			 desbloquearPantalla();
			 $('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.aceptadaLaAsignacion, "ok");
			 findTarea();
	   	 }
	 });
	
	
}

/*
 * ****************************
 * CAMBIOS PANTALLA - FIN
 * ****************************
 */

/*
 * ****************************
 * UTILIDADES - INICIO
 * ****************************
 */

function rechazarAsignacion(estado, motivo){
	$.rup_ajax({
		type: "GET",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/rechazarAsignacion/'+selectedIdTarea+'/'+estado+'/'+motivo,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		async: false,
		success: function(){	
			if(detalle){
				volverACapaGeneral();
			}
			$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.rechazadaLaAsignacion, "ok");
		}
	});
}

function modoConsulta(){
	if(consultaPlanif){
		$("[id='detalleTarea_form_toolbar##aceptar']").hide();
		$("[id='detalleTarea_form_toolbar##rechazar']").hide();
		$("[id='detalleTarea_form_toolbar##ejecutar']").hide();
	}
}

function findTarea(){
	$.ajax({
	  	 type: 'GET'
	  	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/findTarea/'+idTarea
		 ,dataType: 'json'
		 ,contentType: 'application/json' 	
	     ,async: false
	     ,cache: false
	  	 ,success:function(data){
			 tareaObj = data.tarea;
			 anyoExpediente = tareaObj.anyo;
			 idExpediente = tareaObj.numExp;
			 idiomaDestino = data.expedienteTradRev.idIdioma;
			 expedienteConfidencial = data.expedienteTradRev.indConfidencial;
			 origen = data.origen;
			 ordenTarea = tareaObj.orden;
			 estadoAsignacion = tareaObj.estadoAsignado;
			 fechaFinalIZO = data.expedienteTradRev.fechaFinalIzo;
			 horaFinalIZO = data.expedienteTradRev.horaFinalIzo;
			 tipoExp = data.idTipoExpediente;
			 obligarXliff = tareaObj.indObligarXliff;

			 if (tipoExp == datosTareas.idTipoExp.traduccion || tipoExp == datosTareas.idTipoExp.revision){
				 $("#descargarTodo_link").hide();
			 }
			 if(tareaObj.tipoTarea.id015 != tipoTareaInterpretacion) {
				 $("#modoInterDiv").hide();
			 }
			 
			 if (tipoExp == datosTareas.idTipoExp.interpretacion){
				 if(tareaObj.tipoTarea.id015 != tipoTareaInterpretacion) {
					 $("#modoInterDiv").hide();
				 }
				 $("#numPalTradosDiv").hide();
				 $("#tipoRevisionDiv").hide();
				 bopv = false;
			 }else{
				 $("#modoInterDiv").hide();
				 $("#numPalTradosDiv").show();
				 if(data.expedienteTradRev.indPublicarBopv === 'S'){
					 bopv = true;
				 } else {
					 bopv = false;
				 }
				 
				 if (data.tarea.tipoTarea.id015 === tipoTareaRevision || data.tarea.tipoTarea.id015 === tipoTareaEntregaClienteRev || data.tarea.tipoTarea.id015 === tipoTareaRevisarTraduccionExterna) {
					 $("#tipoRevisionDiv").show();
				 } else {
					 $("#tipoRevisionDiv").hide();
				 }
			 }
			 if(data.origen === 'W' && (data.aplicacionOrigen === "r02t" || data.aplicacionOrigen === "R02T")){
				 tramitagune = true;
			 }else{
				 tramitagune = false;
			 }
			 
			 if($.rup.lang === 'es'){
				 $("#tipoTarea_detail_table").text(tareaObj.tipoTarea.descEs015);
				 $("#modoInterpretacion_detail_table").text(data.expedienteInterpretacion.modoInterpretacionDescEs);
				 $("#tipoRevision_detail_table").text(data.tarea.tipoRevision.descEs018);
			 } else {
				 $("#tipoTarea_detail_table").text(tareaObj.tipoTarea.descEu015);
				 $("#modoInterpretacion_detail_table").text(data.expedienteInterpretacion.modoInterpretacionDescEu);
				 $("#tipoRevision_detail_table").text(data.tarea.tipoRevision.descEu018);
			 }

			 $("#numPalTrados_detail_table").html(data.expedienteTradRev.numTotalPalConTramosPerfectMatch);
			 $("#grupoTrabajo_detail_table").text(data.grupoTrabajo);
			 
			 $("#fechaHoraIni_detail_table").text(tareaObj.fechaHoraIni);
			 $("#fechaHoraFin_detail_table").text(tareaObj.fechaHoraFin);
			 
			 if(null != tareaObj.horasPrevistas){
				 $("#horaPrevista_detail_table").text(tareaObj.horasPrevistas+"h");
			 }
			 
			 $("#asignada_detail_table").text(tareaObj.personaAsignada.nombreCompleto);
			 $("#asignador_detail_table").text(tareaObj.personaAsignador.nombreCompleto);

			 if(estadoAsignacion === estadoAceptacion.value.aceptada && null != tareaObj.fechaAceptacion){
				 $("#estadoAceptacion_detail_table").text(tareaObj.estadoAsignadoDesc + " (" + tareaObj.fechaHoraAceptacion + ")");
			 } else {
				 $("#estadoAceptacion_detail_table").text(tareaObj.estadoAsignadoDesc);
			 }
			 
			 if(estadoAsignacion === estadoAceptacion.value.aceptada && null != tareaObj.ejecucionTareas.fechaEjecucion){
				 $("#estadoEjecucion_detail_table").text(tareaObj.estadoEjecucionDesc + " (" + tareaObj.ejecucionTareas.fechaHoraEjecucion + ")");
			 } else {
				 $("#estadoEjecucion_detail_table").text(tareaObj.estadoEjecucionDesc);
			 }
			 
			 $("#estadoAsignadaId").val(tareaObj.estadoAsignado);
			 $("#estadoEjecId").val(tareaObj.estadoEjecucion);
			 
			 if(tareaObj.observaciones != null){
				 $("#observaciones_detail_table").text(tareaObj.observaciones);
			 } else {
				 $("#observacionesDiv").hide();
			 }
			 
			 $("#motivoRechazo_detail_table").text(tareaObj.observacionesRechazo);
			 if(estadoAsignacion === estadoAceptacion.value.rechazada){
				 if(tareaObj.observacionesRechazo != null){
					 $("#motivoRechazoLabel").show();
					 $("#motivoRechazoDiv").show();
				 }
			 }
			 if(tareaObj.indMostrarNotasATrad == 'S' ){
				 mostrarSeccionObservaciones();
				 tieneObservacionesOFichero();
				 gestionarObservaciones();
			 }else{
				 ocultarSeccionObservaciones();
			 }
			 
			 $("#orden").val(tareaObj.orden);
			 $("#idTarea").val(idTarea);
			 $("#idTipoTarea").val(tareaObj.tipoTarea.id015);
			 $("#dniAsignada").val(tareaObj.personaAsignada.dni);
			 $(".documento-all-tarea").remove();
			 
			 var docusList = data.docuTareasList;
			 if(docusList.length != 0){
				 $('a.descargarDocTarea').off('click');
				 var laFila = "";
				 for(var i = 0; i < docusList.length; i++){
					 laFila += '<div class="documento-all-tarea" style="background-color:transparent !important;">';
					 if(docusList[i].documentoOriginal.ficheros != null){
						 laFila += '<div class="documento-first docTarea" style="border-radius: inherit;">';
						 laFila += '<div class="col-md-6">';

						 if(docusList[i].documentoOriginal.claseDocumentoDesc != null){
							laFila += '<p class="document-tit aa79bPEnDoc">'+labelTipo + ': ' + docusList[i].documentoOriginal.claseDocumentoDesc+'</p>';
						 }
						 laFila += '<a href="#" class="document-eusk descargarDocTarea" data-id="'+docusList[i].documentoOriginal.ficheros[0].idDocInsertado+'">';
						 
				 		 if(docusList[i].documentoOriginal.ficheros[1] != null && docusList[i].documentoOriginal.ficheros[1].idDocInsertado != null){
				 			 laFila += $.rup.i18nParse($.rup.i18n.app,"label.docOriginal") + ' - ';
						 }
				 		 laFila += ''+docusList[i].documentoOriginal.ficheros[0].nombre+'</a>';		
				 		 
				 		 if(docusList[i].documentoOriginal.ficheros[1] != null && docusList[i].documentoOriginal.ficheros[1].idDocInsertado != null){
				 			laFila += '<a href="#" class="document-eusk descargarDocTarea" data-id="'+docusList[i].documentoOriginal.ficheros[1].idDocInsertado+'">';
				 			laFila += $.rup.i18nParse($.rup.i18n.app,"label.docARevisar") + ' - ';
				 			laFila += ''+docusList[i].documentoOriginal.ficheros[1].nombre + '</a>';	
				 		 }
				 		 
						 if(docusList[i].documentoOriginal.titulo != '' && 
										docusList[i].documentoOriginal.titulo != (docusList[i].documentoOriginal.ficheros[0].nombre.substring( 0, docusList[i].documentoOriginal.ficheros[0].nombre.lastIndexOf (".") ))){
							laFila+= '<p class="document-tipo"> '+labelTituloAlt+": ";
							laFila+= docusList[i].documentoOriginal.titulo+'</p>';
						 }
				 		 laFila += '</div>';
				 		
				 		 laFila += '<div class="col-md-6">';
				 		 laFila += '<p class="document-tit aa79bPEnDoc">'+ $.rup.i18nParse($.rup.i18n.app,"label.autor") +'</p>';

				 		 if(docusList[i].documentoOriginal.autor.nombreApellidos != "-"){
				 			 laFila += '<p class="document-eusk">' + docusList[i].documentoOriginal.autor.nombreApellidos + ' '; 
					 		 laFila += $.rup.i18nParse($.rup.i18n.app,"label.tel") + ': ' + docusList[i].documentoOriginal.autor.telmovil031 + ' ';
					 		 laFila += $.rup.i18nParse($.rup.i18n.app,"label.correoElectronico") + ': ' + docusList[i].documentoOriginal.autor.email031 + '<p>';
				 		 } else {	 
					 		 laFila += '<p class="document-eusk">' + data.solicitante.nombreCompleto + ' '; 
					 		 laFila += $.rup.i18nParse($.rup.i18n.app,"label.tel") + ': ' + data.solicitante.datosContacto.telmovil031 + ' ';
					 		 laFila += $.rup.i18nParse($.rup.i18n.app,"label.correoElectronico") + ': ' + data.solicitante.datosContacto.email031 + '<p>';
				 		 }
				 		
				 		 laFila += '</div>';
				 		 laFila += '</div>';
					 }
					 laFila += '</br></div>';
				 }
				 $(".document-content").append(laFila);	
				 $('a.descargarDocTarea').click(function(event){
						event.preventDefault();	
						var elIdDoc =  $(this).data("id");
						descargarDocTarea(elIdDoc);
					});
			 } else {
				 $("#descargarTodo_link").hide();
			 }
			 editarVistaDetalle();
			 desbloquearPantalla();
	  	 }
	});
}

function descargarDocumentos(){
	
	var jsonObject = {
			anyo : anyoExpediente,
			numExp : idExpediente,
			idTarea: idTarea
		};
	
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDetalle/descargaDocTareasZip?R01HNoPortal=true'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(jsonObject)			 	
	     ,async: false
	     ,cache: false
	   	,success: function (data){
	   		if(data!=null){
   				if(data.ficheros.length){
   					var arrFicheros = new Array();
	   				var jsonObjectUnDoc;
	   				for(var i = 0; i< data.ficheros.length; i++){
	   					jsonObjectUnDoc = {
	   							nombre: data.ficheros[i].nombre,
	   							oid: data.ficheros[i].oid,
	   							tamano: data.ficheros[i].tamano
	   					};
	   					arrFicheros.push(jsonObjectUnDoc);
	   				}
	   				var jsonListaDocsObject = {
	   						anyo : anyoExpediente,
	   						numExp : idExpediente,
	   						ficheros : arrFicheros
	   					};
	   				var jsonDocsObject = {
	   						documentoOriginal : jsonListaDocsObject,
	   						idTarea : idTarea
	   				}
	   				$.ajax({
	   				   	 type: 'POST'
	   				   	 ,url: '/aa79bItzulnetWar/ficheros/descargarDocumentoConOid?R01HNoPortal=true'
	   				 	 ,dataType: 'json'
	   				 	 ,contentType: 'application/json' 
	   				     ,data: $.toJSON(jsonDocsObject)			 	
	   				     ,async: false
	   				     ,cache: false
	   				     ,beforeSend: function(){
	   				    	 bloquearPantalla();
	   				     }
	   				   	,success: function (data){
	   				   		window.open('/aa79bItzulnetWar/ficheros/abrirDocumentoDeSesion?R01HNoPortal=true');
	   			    	}
	   			    	,error: function (data){
	   			    		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
	   			    	}
	   				});
   				}
	   		}
	   		desbloquearPantalla();
	   	}
	   	,error: function (){
	   		$('#tareasExpedientes_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorGuardandoDatos"), "error");
	   	}
	});		
}

function descargarDocTarea(elIdDoc){
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/1/'+elIdDoc);
}

function mostrarDetalleObservaciones(){
	mostrarDiv('divDetalleObservaciones');
}

function ocultarDetalleObservaciones(){
	ocultarDiv('divDetalleObservaciones');
}

function mostrarLinkObservaciones(){
	$('#observaciones_mostrarLink_div').removeClass('oculto');
	$('#observaciones_mostrarLink_div').addClass('inline');
}

function ocultarLinkObservaciones(){
	$('#observaciones_mostrarLink_div').addClass('oculto');
	$('#observaciones_mostrarLink_div').removeClass('inline');
}

function mostrarSeccionObservaciones(){
	$('#divSeccionObservaciones').removeClass('oculto');
}

function ocultarSeccionObservaciones(){
	$('#divSeccionObservaciones').addClass('oculto');
}

function tieneObservacionesOFichero(){			
	jQuery.ajax({
		type: "GET",
		url: "/aa79bItzulnetWar/datosgeneralesexpediente/observacionesexpediente/"+idExpediente+"/"+anyoExpediente,
		dataType: "json",
		async: false,
		cache: false,
		success:function(data){
			if (data == null || (data.observaciones == null && data.oidFichero == null)) {
				ocultarSeccionObservaciones();
			}
		}
	});
}
function gestionarObservaciones(){
	if ($("#observacionesVisibles").val() === 'true'){
		mostrarDetalleObservaciones();
		ocultarLinkObservaciones();
		obtenerObservacionesExpediente();
	} else {
		ocultarDetalleObservaciones();
	}
}
//function obtenerObservacionesTarea(){
//	
//	jQuery.ajax({
//		type: "GET",
//		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/notasTarea/"+idExpediente+"/"+anyoExpediente+"/"+idTarea,
////		dataType: "json",
////		async: false,
////		cache: false,
//		success:function(data){
//			$("#notasTarea").val(data.oservaciones);
//		}
//	});
//}
//
//function mostrarDetalleObservaciones(){
//	mostrarDiv('divDetalleObservaciones');
//}
/*
 * ****************************
 * UTILIDADES - FIN
 * ****************************
 */
	
jQuery(function($) {

	$('#tareasExpedientes_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	 $("#detalleTarea_form_toolbar").rup_toolbar({
            buttons:[
                    {		id : "volver",    
                            i18nCaption: $.rup.i18n.app.boton.volver
                            ,css: "fa fa-arrow-left"
                            ,index: 1
                            ,right: false
                            ,click : 
                            function(e){
                                    e.preventDefault();
                                    e.stopImmediatePropagation();
                                    if(consultaPlanif){
                                    	volverADesgloseTareasConsultaPlanif();
                                    } else {
                                    	volverACapaGeneral();
                                    }
                    		}
                    },
                    {
                    	id : "aceptar" 
                    	,i18nCaption: $.rup.i18n.app.boton.aceptarAsignacion
                        ,css: "fa fa-check"
                        ,index: 1
                        ,disabled: true
                        ,click : 
                            function(e){
                        		e.preventDefault();
                                e.stopImmediatePropagation();
                                accionRechazo = false;
                                comprobarEstadoTarea();
                        }
                    },
                    {
                    	id : "rechazar",    
                    	i18nCaption: $.rup.i18n.app.boton.rechazarAsignacion
                        ,css: "fa fa-times"
                        ,index: 1
            		    ,right: false
                        ,click : 
                            function(e){
                        		e.preventDefault();
                                e.stopImmediatePropagation();
                                accionRechazo = true;
                                comprobarEstadoTarea();
                        }
                    },
                    {
                    	id : "ejecutar",    
                    	i18nCaption: $.rup.i18n.app.boton.ejecutarTareas
                            ,css: "fa fa-check-square-o"
                        ,click : 
                            function(e){
                        		e.preventDefault();
                                e.stopImmediatePropagation();
                                var codTarea = idTarea;
								idTarea = parseInt(codTarea);
								idTipoTarea = $("#idTipoTarea").val();
								fnComprobarTareaEjecutada();
                        }
                    }
        ]
    });
	 
	modoConsulta();
	findTarea();	
	tieneObservacionesOFichero();
	gestionarObservaciones();
	espedientearenOharrakMarcado();
	
	$("#detalleTarea").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/tareasDependientes",
		colNames: [
			$.rup.i18n.app.label.tipoTarea,
			$.rup.i18n.app.comun.asignadaA,
			$.rup.i18n.app.label.fechaFinTarea,
			$.rup.i18n.app.label.estadoEjecucion
		],
		colModel: [
				{ 	name: $.rup.lang === 'es' ? "tarea.tipoTarea.descEs015":"tarea.tipoTarea.descEu015", 
				 	label: "comun.tipoDeTarea",
					align: "left", 
					width: "90", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "tarea.personaAsignada.nombreCompletoSinComas", 
				 	label: "comun.asignadaA",
					align: "left", 
					width: "80", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true,
					formatter: function (cellvalue, options, rowObject) {
						if(rowObject.tarea.recursoAsignacion === tipoRecurso.externo){
							
							if(rowObject.tarea.lotes.nombreLote != null){
								return rowObject.tarea.lotes.nombreLote;
							} else {
								return cellvalue;
							}
						} else {
							return cellvalue;
						}
					}
				},
				{ 	name: "tarea.fechaHoraFin", 
				 	label: "comun.fechaFinTarea",
					align: "left", 
					width: "60", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				},
				{ 	name: "tarea.estadoEjecucionDesc", 
				 	label: "label.estadoEjecucion",
					align: "left", 
					width: "40", 
					editable: false, 
					hidden: false, 
					resizable: true, 
					sortable: true
				}
			
        ],
        model:"ExpTareasResumen",
        usePlugins:[
       		 "fluid",
       		 "responsive",
       		 "filter"
        ],
        loadComplete: function(){ 
        	$("#detalleTarea_pager").hide();
        },
        sortname:"ORDEN",
        sortorder:"ASC",
        primaryKey: "tarea.idTarea",
		loadOnStartUp: true,
		multiselect:false
	});
	
	
	$('a.descargarDocumentos').click(function(event){
		event.preventDefault();	
		descargarDocumentos();
	});
	
	$("#rechazarAsignacionDetalle_form").rup_validate({
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"motivoRechazo_detail_table": {required: true},
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
    });
	
	$("#rechazarAsignacionDetalle_dialog").rup_dialog({
	    type: $.rup.dialog.DIV,
	    autoOpen: false,
	    modal: true,
	    resizable: false,
	    width: "900",
	    title: $.rup.i18nParse($.rup.i18n.app,"boton.rechazarAsignacion"),
	    buttons: [{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"guardar"),
			click: function () {
				if($("#rechazarAsignacionDetalle_form").valid()){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					rechazarAsignacion(estadoAceptacion.value.rechazada, $("textarea#motivoRechazoDetalle_detail_table").val());
					desbloquearPantalla();
					$("textarea#motivoRechazoDetalle_detail_table").val("");
					$("#rechazarAsignacionDetalle_dialog").rup_dialog('close');
				}
			}
		},
		{
			text: $.rup.i18nParse($.rup.i18n.app.boton,"cancelar"),
			click: function () { 
				$("textarea#motivoRechazoDetalle_detail_table").val("");
				$("#rechazarAsignacionDetalle_dialog").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$("#rechazarAsignacionDetalle_dialog").validate().resetForm();
		},
		close: function(event, ui) {
			$("#rechazarAsignacionDetalle_dialog").validate().resetForm();
		}
	});
	
	$("#notasExpediente_button").on("click",function(){
		crearTablaNotas();	
	});
	
	if (visualizarObservaciones){
		$("#observacionesVisibles").val(true);
	} else {
		$("#observacionesVisibles").val(false);
	}
	
	$("#observaciones_mostrarLink_div").on("click", function (){
		obtenerObservacionesExpediente();
		mostrarDetalleObservaciones();
		$("#observacionesVisibles").val(true);
		visualizarObservaciones = true;
		ocultarLinkObservaciones();
		var focalizar = $("#observaciones_mostrarLink_div").offset().top;
		$('html,body').animate({scrollTop: focalizar}, 1000);
		datosFormulario = $("#datosgeneralesexpedienteform").serialize();
	});
	
	
	
	function obtenerObservacionesExpediente(){
		
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/datosgeneralesexpediente/observacionesexpediente/"+idExpediente+"/"+anyoExpediente,
			dataType: "json",
			async: false,
			cache: false,
			success:function(data){
				asignarObservaciones(data);
				datosFormulario = $("#datosgeneralesexpedienteform").serialize();
			}
		});
	}

	function asignarObservaciones(observaciones){
		$('#observacionesExpTradRev').val(getObservaciones(observaciones));
		
		$('#oidFichero056_2').val(getObservacionesOid(observaciones));
		$('#extensionDoc056_2').val(getObservacionesExtension(observaciones));
		$('#contentType056_2').val(getObservacionesContentType(observaciones));
		$('#nombre_2').val(getObservacionesNombre(observaciones));
		$('#tamanoDoc056_2').val(getObservacionesTamano(observaciones));
		
		if( $('#oidFichero056_2').val() !== '' ){
			var enlace = '<a href="#" class="descargarDocObservaciones" data-anyo="'+anyoExpediente+'" data-numexp="'+idExpediente+'" >'+getObservacionesNombre(observaciones)+'</a>';	   			
   			$('#enlaceDescargaDetalle_2').html(enlace);
   			$('a.descargarDocObservaciones').click(function(event){
   				event.preventDefault();	
   				bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
   				var elAnyo = $(this).data("anyo");
   				var elNumExp = $(this).data("numexp");
   				descargarDocumentoObservaciones(elAnyo,elNumExp);
   				desbloquearPantalla();
   			});
   			$('#btnEliminarObserv').show();
		}
		$('#nombreFicheroInfo_2').hide();
		$('#capaBtnPID').hide();
		$('#btnEliminarObserv').hide();
		$('#observacionesExpTradRev').attr('disabled', 'disabled');
		$('#observacionesExpTradRev').css('overflow', 'auto');
		$('#observacionesExpTradRev').css('cursor', 'default');
		
	}
	
	function descargarDocumentoObservaciones(elAnyo,elNumExp){			
		window.open('/aa79bItzulnetWar/ficheros/descargarDocumentoObservaciones/'+elAnyo+'/'+elNumExp);
	}
	
	function getObservaciones(obj){
		return obj == null ? "" : obj.observaciones;
	}
	function getObservacionesOid(obj){
		return obj == null ? "" : obj.oidFichero;
	}
	function getObservacionesExtension(obj){
		return obj == null ? "" : obj.extension;
	}
	function getObservacionesContentType(obj){
		return obj == null ? "" : obj.contentType;
	}
	function getObservacionesNombre(obj){
		return obj == null ? "" : obj.nombre;
	}
	function getObservacionesTamano(obj){
		return obj == null ? "" : obj.tamano;
	}
	
	function espedientearenOharrakMarcado(){
		jQuery.ajax({
			type: "GET",
			url: "/aa79bItzulnetWar/notasexpedientes/tieneNotasExpediente/"+idExpediente+"/"+anyoExpediente,
			dataType: "json",
			async: false,
			cache: false,
			success:function(data){
				if (data) {
					$("#notasExpediente_button").attr('style', 'background-color:#B0FF57');
				}else{
					$("#notasExpediente_button").attr('style', 'background-color:none');
				}
			}
		});
	}
	
});