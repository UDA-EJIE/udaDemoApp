var configFeedbackBitacora = {
	block : false,
	delay: 3000
};
var tamanoFichero = 0;

// configuracion tiny con subida de imagenes a blob
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




function bitacoraUpdate(bitacoraCollapse) {
	if(!bitacoraCollapse && $('#collapseBitacora').hasClass('in')){
		$('#collapseBitacora_button').click();
	}
	
	var jsonObj = {
		"anyo" : anyoExpediente,
		"numExp" : idExpediente
	};

	//Hiddens para añadir un nuevo registro de acciones
	$("#anyoExp_bitacora").val(anyoExpediente);
	$("#numExp_bitacora").val(idExpediente);
	
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/bitacoraexpediente/getCabeceraBitacora",
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		cache : false,
		success : function(data) {
			
			//Se setean los datos de la cabecera
			cabeceraUpdate(data);
			
			//Aqui recuperar indAplicacionComunicaciones y mostrar/ocultar el boton comunicaciones
			if ( "S" === data.indAplicacionComunicaciones){
				$("#comunicacionesExpediente_button").removeClass("oculto");
				if (null != data.expedienteTradRev){
					$('#refTramitagune_comunic_bitacora').val(data.expedienteTradRev.refTramitagune);
				}
			}else{
				$("#comunicacionesExpediente_button").addClass("oculto");
			}
			
			espedientearenOharrakMarcado();
			
			//Se escribe la bitácora de forma dinámica
			$("#bitacoraExpediente_div").empty();
			if (data.listaBitacoraExpediente !== null
					&& data.listaBitacoraExpediente.length > 0) {
				for (var i = 0; i < data.listaBitacoraExpediente.length; i++) {
					var esActivo = (data.listaBitacoraExpediente.length - 1) === i
					var bitacora = data.listaBitacoraExpediente[i];
					var item = '<div id="bitacora_div_'+ bitacora.idEstadoBitacora + '" data-id="'+ bitacora.idEstadoBitacora + '" class="bitacora-box buttonRegistroAcciones"> \
						<div class="box '+ bitacora.estadoExp.classStyle +'"> \
						<div class="row box-cabecera"> \
							<div class="col-md-2"> \
								<span class="box-cabecera-ico"> \
									<i class="fa fa-folder-open fa-3x" aria-hidden="true">';
										if(bitacora.numAcciones !== 0){
											item += '<span class="label label-warning">' + bitacora.numAcciones + '</span>';							
										}
							item += '</i> \
								</span> \
							</div> \
						<div class="col-md-10"> \
							<p class="box-cabecera-tit">'+ bitacora.estadoExp.descEu.toUpperCase() +'</p> \
							<p class="box-cabecera-date">'+ bitacora.fechaAlta +'</p> \
						</div> \
					</div> \
					<div class="triangle-down"></div> \
						<div class="row box-content"> \
							<div class="col-md-12"> \
								<p class="box-content-estado">'+ bitacora.faseExp.descEu.toUpperCase() +'</p>';
					if(bitacora.datoAdic != null){
						item += '<p class="box-content-tipo">'+ bitacora.datoAdic.toUpperCase() +'</p>';
					}
					if(bitacora.infoAdic != null){
						item += '<p class="box-content-tipo-def">'+ bitacora.infoAdic.toUpperCase() +'</p>'	
					}
					item += '</div> \
						</div> \
					</div>';
					if(esActivo){
						item += '<div class="box-activa">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.activo") + '</div>';		
						//Hidden para qué id estado bitácora es el activo
						$("#idEstadoBitacora_bitacora").val(bitacora.idEstadoBitacora);
					}
					item += '</div>';
					
					var itemDiv = $(item);
					
					itemDiv.appendTo("#bitacoraExpediente_div");
				}
				$(".buttonRegistroAcciones").each(function() {
					$(this).on("click", function() {
						loadRegistroAcciones($(this).data("id"));
					});
				});
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
				itemDiv.appendTo("#bitacora_expediente");
				
				//creacion de TinyMCE
				tinymce.init(conf);
				
			$(".close-button").each(function() {
				$(this).on("click", function() {
					tinymce.activeEditor.setContent('');
					tinymce.remove('#anadirNotaTiny');
					$("#notasExpediente_div").remove();
					$("#bitacora_expediente .ui-widget-overlay.ui-front").remove();
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
					jQuery
					.ajax({
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

function espedientearenOharrakMarcado(){
	var laURL='';
	//Expediente de traduccion o revision llegan con idExpediente y anyoExpediente vacio porque se carga la bitacora sin haberse cargado estudioexpedientes.js, q es donde se inicializan estas vbles.
	//En ese caso cogemos numExp y anyo, q se cargan en consultaexpedientes.js, antes de llegar aqui
	if ( idExpediente !='' || anyoExpediente !='' ){
		laURL = "/aa79bItzulnetWar/notasexpedientes/tieneNotasExpediente/"+idExpediente+"/"+anyoExpediente
	}else if ( numExp =='' || anyo =='' ){
		laURL = "/aa79bItzulnetWar/notasexpedientes/tieneNotasExpediente/"+numExp+"/"+anyo
	}
	if ( laURL !='' ){
		jQuery.ajax({
			type: "GET",
			url: laURL,
			dataType: "json",
			async: false,
			cache: false,
			success:function(data){
				if (data) {
					$("#notasExpediente_button").attr('style', 'background-color:#B0FF57');
				}else{
					$("#notasExpediente_button").attr('style', 'background-color:transparent');
				}
			}
		});
	}
}


function loadRegistroAcciones(idEstadoBitacora){
	
	var jsonObj = {
		"anyo" : anyoExpediente,
		"numExp" : idExpediente,
		"idEstadoBitacora" : idEstadoBitacora
	};
	
	$("#registroAcciones_div").remove();
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/registroacciones/getBitacoraAcciones",
		cache : false,
		dataType : "json",
		contentType : 'application/json',
		data : $.toJSON(jsonObj),
		success : function(data) {
			
			if (data !== null
					&& data.length > 0) {
				var item = '<div id="registroAcciones_div"> \
					  <div class="triangulo_sup"></div> \
					  <div class="bitacora-modal"> \
					    <div class="modal-header"> \
					      <h2>'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.modificacionesEstado") + '</H2> \
					      <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">+</a> \
					    </div> \
					    <div class="tabla-mods"> \
					      <div class="row cabecera-mods"> \
					        <div class="col-md-2 col1BitacoraModal">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.fechaYHora") + '</div> \
					        <div class="col-md-2 col2BitacoraModal">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.usuario") + '</div> \
					        <div class="col-md-3 col3BitacoraModal">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.accionRealizada") + '</div> \
					        <div class="col-md-5 col4BitacoraModal">'+ $.rup.i18nParse($.rup.i18n.app,"bitacora.cambioRealizado") + '</div> \
					      </div>';
				for (var i = 0; i < data.length; i++) {
					var accion = data[i];
					item += '<div class="row body-mods"> \
				        <div class="col-md-2 col1BitacoraModal first">'+ accion.fechaHoraRegistro +'</div> \
				        <div class="col-md-2 col2BitacoraModal">'+ accion.usuarioRegistro +'</div> \
				        <div class="col-md-3 col3BitacoraModal">'+ accion.accionDescEu +'</div> \
				        <div class="col-md-5 col4BitacoraModal">'+ accion.observ +'</div> \
				      </div>'; 
				}	
				
				item += '</div> \
				  </div> \
					<div class="ui-widget-overlay ui-front" style="z-index: 100;"></div> \
				</div>';
				
				var itemDiv = $(item);
				itemDiv.appendTo("#bitacora_expediente");
			}
			$(".close-button").each(function() {
				$(this).on("click", function() {
					$("#registroAcciones_div").remove();
				});
			});
	
		}
	});
}

function comunicacionesExpediente(){
	$('#anyoExp_comunic_bitacora').val(anyoExpediente);
	$('#numExp_comunic_bitacora').val(idExpediente);
	
	if ( undefined == $("#listaComunicaciones").rup_table("getColModel") ){
		crearRupsComunicaciones();
	}
	$('#listaComunicaciones_feedback').rup_feedback("close");
	$("#listaComunicaciones").rup_table("filter");
	$("#listaComunicaciones_dialog").rup_dialog("open");
}

function nuevaComunicacion(){
	$('#ficheroFileComunic').val("");
	$('#enlaceNombre_nuevaComunic_bitacora').html('');
	
	$("#subidaFicheroComunic_form").rup_form('clearForm', true);
	clearValidation('#subidaFicheroComunic_form');		
	$("#nuevaComunicacion_feedback").rup_feedback("close");
	eliminarMensajesValidacionPorCapa('nuevaComunicacion_feedback');

	
	$('#refTramitagune_nuevaComunic_bitacora').val($('#refTramitagune_comunic_bitacora').val());
	$('#anyoExp_nuevaComunic_bitacora').val(anyoExpediente);
	$('#numExp_nuevaComunic_bitacora').val(idExpediente);
	
	$("#pidButtonComunic").unbind("click");
	$("#pidButtonComunic").click(function(event) {
		$("#idBotonUpload").val("0");
		$('#ficheroFileComunic').click();
	});
	$("#nuevaComunicacion_dialog").rup_dialog("open");
	
}


//FUNCION PARA DESCARGAR EL ARCHIVO DE LA PLANTILLA
function descargarPlantilla(idFichero) {
	window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/2/'+idFichero);
}
function mostrarComunicacion(idfila) {
	$('#capaMostrarFecha').html($("#listaComunicaciones").rup_table("getCol", idfila, "fecha"));
	$('#capaMostrarRemitente').html($("#listaComunicaciones").rup_table("getCol", idfila, "remitente"));
	$('#capaMostrarAsunto').html($("#listaComunicaciones").rup_table("getCol", idfila, "asunto"));
	$('#capaMostrarMensaje').html($("#listaComunicaciones").rup_table("getCol", idfila, "mensaje"));
	
	
	if ( $("#listaComunicaciones").rup_table("getCol", idfila, "codigo") != '0'){
		$('#capaAdjunto').removeClass("oculto");
		$('#enlaceAdjunto').text($("#listaComunicaciones").rup_table("getCol", idfila, "nombre"));
		$("#enlaceAdjunto").unbind("click");
		$("#enlaceAdjunto").click(function(event) {
			window.open('/aa79bItzulnetWar/ficheros/descargarDocumento/2/'+$("#listaComunicaciones").rup_table("getCol", idfila, "codigo"));
		});
	}else{
		$('#capaAdjunto').addClass("oculto");
	}
	$("#verComunicacion_dialog").rup_dialog("open");
}



function crearTablaComunicaciones(){
	
	$('input[name="tipoBusqueda"]').click(function(){ $("#listaComunicaciones").rup_table("filter") });
	
	$('#listaComunicaciones_feedback').rup_feedback(configFeedbackBitacora);
	$('#listaComunicaciones_detail_feedback').rup_feedback(configFeedbackBitacora);
	$("#listaComunicaciones").rup_table({
		url : "/aa79bItzulnetWar/comunicaciones",
		toolbar : {
			id : "listaComunicaciones_toolbar",
			newButtons : [ 
				{obj : {
						i18nCaption : $.rup.i18nParse($.rup.i18n.app,"boton.nuevo"),
						css : "fa fa-file-o",
						index : 0
					},
					json_i18n : $.rup.i18n.app.simpelMaint,
					click : function(e) {
						nuevaComunicacion();
					}
				},
				{obj : {
						i18nCaption : $.rup.i18nParse($.rup.i18n.app,"label.ver"),
						css : "fa fa-eye",
						index : 0
					},
					json_i18n : $.rup.i18n.app.simpelMaint,
					click : function(e) {
						if (!$('#listaComunicaciones').rup_table("isSelected")) {
							e.preventDefault();
							$.rup_messages("msgAlert", {message : $.rup.i18n.app.comun.warningSeleccion});
							return false;
						} else {
							mostrarComunicacion($('#listaComunicaciones').rup_table("getSelectedRows"));
						}
					}
				}
			],
			defaultButtons : {
				add : false,
				edit : false,
				cancel : false,
				save : false,
				clone : false,
				"delete" : false,
				filter : true
			}
		},
		colNames : [ "",$.rup.i18n.app.bitacora.fechaYHora, $.rup.i18n.app.label.tipo, $.rup.i18n.app.label.remitente, $.rup.i18n.app.label.asunto,"","","" ],
		colModel : [
				{name : "id0D4", hidden : true
				},
				{
					name : "fecha",
					label : "label.fechaHora",
					index : "FECHA_0D4",
					align : "left",
					fixed : true,
				}, 
				{
					name : "tipo",
					label : "label.tipo",
					index : "TIPO_0D4",
					align : "left",
					fixed : true,
					formatter: function(cellval, opts, rowObject) {
						if ( "R" == cellval ){
							return $.rup.i18n.app.tipoComunicacion.recibido
						}else{
							return $.rup.i18n.app.tipoComunicacion.enviado
						}
					}
				}, 
				{
					name : "remitente",
					label : "label.remitente",
					index : "REMITENTE_0D4",
					align : "left",
					fixed : true
				}, 
				{
					name : "asunto",
					label : "label.asunto",
					index : "ASUNTO_0D4",
					align : "left",
					fixed : true
				}
				,{name : "codigo", hidden : true}
				,{name : "nombre", hidden : true}
				,{name : "mensaje", hidden : true}
		],
		model : "Comunicaciones",
		usePlugins : [ "feedback", "toolbar", "responsive", "filter" ],
		primaryKey : [ "id0D4" ],
		sortname : "FECHA_0D4",
		sortorder : "desc",
		loadOnStartUp : false,
	});

}
//Fin metodo crearTablaComunicaciones

function guardarNuevaComunicacion(){
		$("#mensaje_nuevaComunic_bitacora").tinymce().save();
		$("#asunto_nuevaComunic_bitacora").rules("add", "required");
		$("#mensaje_nuevaComunic_bitacora").rules("add", "required");
		if ($("#subidaFicheroComunic_form").valid()){
			var comunicacion = {
					"contentType" : $('#contentType_nuevaComunic_bitacora').val(),
					"encriptado" : $('#encriptado_nuevaComunic_bitacora').val(),
					"extension" : $('#extension_nuevaComunic_bitacora').val(),
					"nombre" : $('#nombre_nuevaComunic_bitacora').val(),
					"oidFichero" : $('#oidFichero_nuevaComunic_bitacora').val(),
					"tamano" : $('#tamano_nuevaComunic_bitacora').val()	,
					"anyo": $('#anyoExp_nuevaComunic_bitacora').val(),
					"numExp": $('#numExp_nuevaComunic_bitacora').val(),
					"refTramitagune": $('#refTramitagune_nuevaComunic_bitacora').val(),
					"asunto": $('#asunto_nuevaComunic_bitacora').val(),
					"mensaje": $('#mensaje_nuevaComunic_bitacora').val()
				};
			
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/comunicaciones'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			     ,data: $.toJSON(comunicacion)
			     ,beforeSend: function() {
		        	bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.guardando"));
		        }
			   	 ,success:function(){
			   		$("#nuevaComunicacion_dialog").rup_dialog('close');
					$("#listaComunicaciones").rup_table("filter");
					mostrarMensajeFeedback("listaComunicaciones_feedback",$.rup.i18n.app.mensajes.guardadoCorrecto,"ok");
					desbloquearPantalla();
			   	}
		   	 	,error: function(){	  
					mostrarMensajeFeedback("nuevaComunicacion_feedback",$.rup.i18n.app.mensajes.errorGuardandoDatos,"error"); 	
					desbloquearPantalla();
			   	 }
			 });
		}		
}

function crearRupsComunicaciones(){
	// CREACION DE LOS COMPONENTES RUP DE La PARTE DE COMUNICACIONES
	// PARA NO SOBRECARGAR INNECESARIAMENTE, LOS CREAMOS LA PRIMERA VEZ QUE SE PULSA EL BOTON COMUNICACIONES
	// DIALOGO LISTADO COMUNICACIONES
	$("#listaComunicaciones_dialog").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        width: "915",
        title: $.rup.i18nParse($.rup.i18n.app,"bitacora.comunicacionesExped"),
	});
	
	// NUEVA COMUNICACION ...
	$('#nuevaComunicacion_feedback').rup_feedback(configFeedbackBitacora);
	$("#nuevaComunicacion_dialog").rup_dialog({
		type: $.rup.dialog.DIV,
		autoOpen: false,
		modal: true,
		resizable: false,
		width: "915",
		title: $.rup.i18nParse($.rup.i18n.app,"label.nuevoMensaje"),
		buttons: [{
				text: $.rup.i18nParse($.rup.i18n.base,"rup_global.save"),
				click: function () {
					guardarNuevaComunicacion();
				}
			},
			{
				text: $.rup.i18nParse($.rup.i18n.base,"rup_global.cancel"),
				click: function () { 
					$("#nuevaComunicacion_dialog").rup_dialog('close');
				},
				btnType: $.rup.dialog.LINK
		}],
	});
	
	tinyMCE.init({
		selector : '#mensaje_nuevaComunic_bitacora'
		, menubar: false
		,plugins: 'lists link'
		, toolbar: [
			"undo redo |  bold italic underline | link | numlist bullist"
		]
		,setup:function(ed){
			 ed.on('change', function(e) {
		          cambios = true;
		       });
		}
	});
	
	
	$.validator.addMethod("checkFileSize", function(value, element, params) {
		if (tamanoFichero > 15000000){
			return false;
		}else{
			return true;
		}
	},$.rup.i18n.app.validaciones.checkFileSize);

	
	//FORMULARIO DE ENVIO DE FICHERO Y DE COMUNICACION
	$('#subidaFicheroComunic_form').rup_form({
		url: "/aa79bItzulnetWar/ficheros/subidaFichero"
		, feedback:$("#nuevaComunicacion_feedback")
		, dataType: "json"
		, beforeSend: function () {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.subiendo"));
			return "skip";
		},
		validate:{ 
			ignore: [],
			rules:{
				"ficheroNombre": {
					checkFileSize: tamanoFichero
				}
			},
			showFieldErrorAsDefault: false,
	        showErrorsInFeedback: true,
	        showFieldErrorsInFeedback: false,
		}
		, success: function(archivoPIF){
			if (archivoPIF.error === null){ 
				//Subida correcta
				$.ajax({
				   	 type: 'POST'
				   	 ,url: '/aa79bItzulnetWar/ficheros/pasoPIFaPID'
				 	 ,dataType: 'json'
				 	 ,contentType: 'application/json' 
				     ,data: $.toJSON(archivoPIF)			
				   	 ,success:function(data){
				   		if (data.error === null){
				   			$('#extension_nuevaComunic_bitacora').val(data.extension);
				   			$('#contentType_nuevaComunic_bitacora').val(data.contentType);
				   			$('#tamano_nuevaComunic_bitacora').val(data.tamano);
				   			$('#oidFichero_nuevaComunic_bitacora').val(data.oid);
				   			$('#nombre_nuevaComunic_bitacora').val(data.nombre);
				   			$('#encriptado_nuevaComunic_bitacora').val(data.encriptado);		
				   			$("#ficheroNombre_nuevaComunic_bitacora").val(data.nombre);
				   			$("#enlaceNombre_nuevaComunic_bitacora").hide();
				   		}else{
							mostrarMensajeFeedback("nuevaComunicacion_feedback",data.error,"error"); 
						}
			   			desbloquearPantalla();
				   	 }
			   	 	,error: function(){
			   	 		desbloquearPantalla();
						mostrarMensajeFeedback("nuevaComunicacion_feedback",$.rup.i18n.app.validaciones.errorLlamadaAjax,"error"); 
				   	 }
				 });
			}else{
				//subida incorrecta
	   			desbloquearPantalla();
				mostrarMensajeFeedback("nuevaComunicacion_feedback",archivoPIF.error,"error"); 
			}
		 }
		, error: function(archivoPIF){
			desbloquearPantalla();
			mostrarMensajeFeedback("nuevaComunicacion_feedback",archivoPIF.error,"error"); 
		}
	});	
	
	//TRIGGER PARA EL BOTON DE EXAMINAR
	$("#ficheroFileComunic").change(function() {
		if ($("#ficheroFileComunic").val() !== ''){
				tamanoFichero = this.files[0].size;
				$("#asunto_nuevaComunic_bitacora").rules("remove", "required");
				$("#mensaje_nuevaComunic_bitacora").rules("remove", "required");
				$("#subidaFicheroComunic_form").submit();
		}
	});
	// DIALOGO VER COMUNICACION
	$("#verComunicacion_dialog").rup_dialog({
		type: $.rup.dialog.DIV,
		autoOpen: false,
		modal: true,
		resizable: false,
		width: "915",
		title: $.rup.i18nParse($.rup.i18n.app,"label.verMensaje"),
		buttons: [
			{
				text: $.rup.i18nParse($.rup.i18n.base,"rup_global.cancel"),
				click: function () { 
					$("#verComunicacion_dialog").rup_dialog('close');
				},
				btnType: $.rup.dialog.LINK
		}],
	});
	
	crearTablaComunicaciones();
}

jQuery(function($) {
	
	$("#addRegistro_button").on("click", function(){
		$("#observ").change();
		//Se abre el diálogo
		$("#addRegistro_dialog").rup_dialog("open");
	});
	
	$("#observ").on("change", function() {
		auto_grow(this);
	});
	
	$("#observ").on("keyup", function() {
		auto_grow(this);
	});
	
	$("#notasExpediente_button").on("click",function(){
		crearTablaNotas();	
	});

	$("#addRegistro_dialog").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: false,
        width: "915",
        title: $.rup.i18nParse($.rup.i18n.app,"bitacora.registrarAccion"),
        buttons: [{
			text: $.rup.i18nParse($.rup.i18n.base,"rup_global.save"),
			click: function () {
				$('#addRegistro_form').submit();
			}
		},
		{
			text: $.rup.i18nParse($.rup.i18n.base,"rup_global.cancel"),
			click: function () { 
				$("#addRegistro_dialog").rup_dialog('close');
			},
			btnType: $.rup.dialog.LINK
		}],
		open : function() {
			$("#addRegistro_form").validate().resetForm();
		}
	});
	
	$('#addRegistro_form').rup_form({
		url: "/aa79bItzulnetWar/registroacciones"
		, type: "POST"
		, dataType: "json"
		, validate: {
			rules:{
				"observ" : {required: true}
			}
		}
		, success: function(data){
			$("#addRegistro_dialog").rup_dialog('close');
			bitacoraUpdate(true);
			if ($('#collapseBitacora_button').attr('aria-expanded') !== "true"){
				$('#collapseBitacora_button').click();
			}
		 }
	});
	
	
	// COMUNICACIONES ...
	$("#comunicacionesExpediente_button").on("click",function(){
		comunicacionesExpediente();	
	});
	
	
});