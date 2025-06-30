var countAutocompleteExpRel = 0;
var entidadExpRel = "-1";
var codigoExpRel = -1;
var entidadCheckeadaExpRel = "-1";

function eliminarMensajesDeError(){
	if(!($("#fechaHastaSolExp_filter_table")[0].parentNode.childNodes[2] == null)){
			$("#fechaHastaSolExp_filter_table")[0].parentNode.removeChild($("#fechaHastaSolExp_filter_table")[0].parentNode.childNodes[1]);
		}
		if(!($("#fechaDesdeSolExp_filter_table")[0].parentNode.childNodes[2] == null)){
			$("#fechaDesdeSolExp_filter_table")[0].parentNode.removeChild($("#fechaDesdeSolExp_filter_table")[0].parentNode.childNodes[1]);
		}
		if(!($("#fechaHastaEntrega_filter_table")[0].parentNode.childNodes[2] == null)){
			$("#fechaHastaEntrega_filter_table")[0].parentNode.removeChild($("#fechaHastaEntrega_filter_table")[0].parentNode.childNodes[1]);
		}
		if(!($("#fechaDesdeEntrega_filter_table")[0].parentNode.childNodes[2] == null)){
			$("#fechaDesdeEntrega_filter_table")[0].parentNode.removeChild($("#fechaDesdeEntrega_filter_table")[0].parentNode.childNodes[1]);
		}
}
function showDetalleExpedienteRelacionado(anyo,numexp){
	bloquearPantalla();
	var Expediente = 
    {
        "anyo": anyo, 
        "numExp": numexp
    };
	$.ajax({
		type: 'POST',
		url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/detalleExpediente',
		contentType: "application/json",
		dataType: 'html',
		data: jQuery.toJSON({"expediente":Expediente}),
		async: false, cache: false,
		success : function(data) {
			$("#divDetalleExpediente").html(data);
			goTo($("#capaPestanaCompletaAlta"), $("#divDetalleExpediente"));
		}
		, error: function() {
			alert($.rup.i18nParse($.rup.i18n.base,"rup_message.tituloError"));
		}
		, complete: function() {
			desbloquearPantalla();
		}
	});
	
}

function cambiarLabelTipoEntidadExpRelBE(tipoEntidad){
	if('B'.localeCompare(tipoEntidad)===0){
		$('#labelIdEntidadGestoraBusc_filter_table').text($.rup.i18n.app.label.entidad);
	}else if('E'.localeCompare(tipoEntidad)===0){
		$('#labelIdEntidadGestoraBusc_filter_table').text($.rup.i18n.app.label.departamento);
	}else if('L'.localeCompare(tipoEntidad)===0){
		$('#labelIdEntidadGestoraBusc_filter_table').text($.rup.i18n.app.label.empresa);
	}else{
		$('#labelIdEntidadGestoraBusc_filter_table').text($.rup.i18n.app.label.todasTipoEntidad);
	}
}

/**
 * crea el autocomplete de gestores
 * @returns
 */
function crearAutocompleteGestorExpRel(){
	  $('#gestor_filter_table').rup_autocomplete({
   		source : "/aa79bItzulnetWar/solicitante/findGestoresDeExpCEntidad/"+entidadExpRel+"/"+codigoExpRel,
   		sourceParam:{
   			value : "dni",
   			label : "nombreCompleto" 
   		},
   		getText: false,
   		width: "auto",
   		open : function() {
   			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#gestor_filter_table').innerWidth());
   			$("#gestor_filter_table_menu").css("z-index", 999);
   			$("#gestor_filter_table_menu").removeClass("ui-front");
   		}
   	});  
}

/**
 * anyade el evento change al combo de entidades para actualizar el valor a pasar al autocomplete de gestores
 * @param elId
 * @returns
 */
function anyadirEventoChangeAComboEntidadGestoraExpRel(elId){
	jQuery('select[id=idEntidadGestoraBusc_filter_table]').change(function(){
 		if(countAutocompleteExpRel>=1){
 			 var codigoCompleto =  $('#idEntidadGestoraBusc_filter_table').rup_combo("getRupValue");
          	 if(!codigoCompleto || "".localeCompare(codigoCompleto)==0){
          		 codigoExpRel = -1;
          		 entidadExpRel = entidadCheckeadaExpRel; 
          	 }else{
          		var datosEntidadSeleccionada = codigoCompleto.split("_");	
          		codigoExpRel = datosEntidadSeleccionada[1];
          		entidadExpRel = datosEntidadSeleccionada[0];
          	 }
          	 //destruimos y volvemos a crear el autocomplete
          	$('#gestor_filter_table').rup_autocomplete("destroy");
          	$("#"+elId+"autocompleteGestorFilterTable").remove();
          	var autocompleteGestor = $('<div id="'+elId+'autocompleteGestorFilterTable" ><label for="gestor_filter_table" class="control-label" data-i18n="label.gestor">'+$.rup.i18n.app.label.gestor+'</label><input id="gestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni" style=""/></div>');
          	autocompleteGestor.appendTo("#"+elId+"divAutocompleteGestorFilterTable");
       		crearAutocompleteGestorExpRel();
 		}
 		countAutocompleteExpRel++;
 	});
}

/**
 * crea un nuevo combo de entidades
 * @param valEntidad
 * @returns
 */
function crearComboEntidadGestoraExpRel(valEntidad){
	codigoExpRel="-1";
	$('#idEntidadGestoraBusc_filter_table').rup_combo({
			source : "/aa79bItzulnetWar/entidad/exprel/"+valEntidad,
			sourceParam : {
				value: "codigoCompleto",
				label : $.rup.lang === 'es' ? "descEs"
						: "descEu"
			},
			blank:"",
			width: "362",
			getText: false,
			open : function() {
				$("#idEntidadGestoraBusc_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
				$("#idEntidadGestoraBusc_filter_table_menu").css("min-width", jQuery('#idEntidadGestoraBusc_filter_table').width());
				$("#idEntidadGestoraBusc_filter_table_menu").removeClass("ui-front");
			}
		});
}

(function($){
	
	$.fn.buscador_expedientesRelacionados = function(options){
		
		var buscador_expedientes_relacionados_methods = {
			open : function(){
				this.rup_dialog("open");
				//eliminar labels de mensaje de error si existe
				eliminarMensajesDeError();
			}	
		};
		
		var tipoEntidad = {
			    ENTIDAD: 'B',
			    DEPARTAMENTO: 'E',
			    EMPRESA: 'L'
			};
		
		var permiso = {
				SI: 'S',
				NO: 'N'
		}
		
		if(buscador_expedientes_relacionados_methods[options]){
			return buscador_expedientes_relacionados_methods[options].apply(this, Array.prototype.slice.call( arguments, 1 ));
		}else if(typeof options === 'object' || ! options){
			
			
			var settings = $.extend({
				anyo: options.anyo,
				numExp: options.numExp,
				guardarOnartu: options.guardarOnartu,
				permisoBOPV: "S",
				callback: function(event, object){
					
				}
			}, options);
			
			var expediente = {
					anyo: settings.anyo,
					numExp: settings.numExp
			}
			
			var id=this.attr("id");
			var name = this.attr("name");
			 var html="<div class='aa79b-content-modal'>";
	             html+="<div id='"+id+"buscadorexprel_div' class='rup-table-container'>";		
	             html+="<div id='buscadorexprel_toolbar'></div>";	
	             html+="<div id='"+id+"buscadorexprel_feedback'></div>";										
		             html+="<div id='contenFormularios' class='filterForm'>";
			             html+="<form id='"+id+"buscadorexprel_filter_form' Class='form-horizontal'>";				
			             html+="<div id='"+id+"buscadorexprel_filter_toolbar' class='formulario_legend'></div>";	
			             html+="<fieldset id='"+id+"buscadorexprel_filter_fieldset' class='rup-table-filter-fieldset'>";
			             html+="<div class='p-2'>";
			          		html+="<input type='hidden' name='anyo' class='form-control numeric' id='"+id+"anyoExpRel'/>";
			          		html+="<input type='hidden' name='numExp' class='form-control numeric' id='"+id+"numExpExpRel'/>";
				             html+="<input type='hidden' name='gestorExpediente.entidad.tipo' id='"+id+"entidadTipoExpRel'></input>";
				             html+="<input type='hidden' name='expedienteRelacionado.permisoBOPV' id='"+id+"bopv_filter_table' value='N'/>"; 
				             html+="<input type='hidden' name='rol' id='rolDatosPersonaExpRel'></input>";
				             html+="<div class='row'>";
				            	html+="<div class='form-group col-xs-12 col-md-3'>";
						             	html+="<label for='expedienteRelacionadonumExpExpRel_filter_table' class='control-label' data-i18n='label.numExp'>".concat($.rup.i18n.app.label.numExp,"</label>");
						             	html+="<div>";
						             		html+="<input type='text' name='expedienteRelacionado.anyoExpRel' class='numeric' style='width: 25%;' id='expedienteRelacionadoAnyoExpRel_filter_table' maxlength='2'/>";
						             		html+="<label for='expedienteRelacionadoAnyoExpRel_filter_table' class='control-label' style='width: 3%; margin-right: 2%; margin-left: 2%;'> / </label>";
						             		html+="<input type='text' name='expedienteRelacionado.numExpRel' class='numeric' style='width: 59%;' id='expedienteRelacionadonumExpExpRel_filter_table' maxlength='6'/>";
						             	html+="</div>";
				         		html+="</div>";
				         		html+="<div class='form-group col-xs-12 col-md-6'>";
	         						html+="<label for='tituloExpRel_filter_table' class='control-label' data-i19n='label.titulo'>".concat($.rup.i18n.app.label.titulo,"</label>");
			             			html+="<input type='text' name='titulo' class='form-control' id='tituloExpRel_filter_table' maxlength='150'/>";
			             		html+="</div>";
			             	 	html+="<div id='divBopvCheckbox' class='form-group col-xs-12 col-md-2'>";
			 	            	 		html+="<label for='bopvCheckbox' class='control-label' data-i18n='label.bopv'>".concat($.rup.i18n.app.label.bopv,"</label>");
			 	            	 			html+="<input type='checkbox' name='bopvCheckbox' id='bopvCheckbox' value='N' data-switch-modal='true'/>";
			             	 	html+="</div>";
			         		html+="</div>";
			         		html+="<div class='row'>";
			         		html+="<div class='col-md-10'>";
			         		html+="<label class='control-label col-lg-12 p-0' data-i18n='label.fechaEntregaIZO'>".concat($.rup.i18n.app.label.solicitudExpediente,":</label>");
			         		html+="<div class='form-group col-lg-5 p-0'>";
			         		html+="	<label for='fechaDesdeSolExp_filter_table' class='control-label col-md-4 p-0 valFecha' data-i18n='label.desde'>".concat($.rup.i18n.app.label.fechaDesde,"</label>");
			         			html+="<div class='form-group  col-md-8 p-0 grupoFechaHora grupoFecha'>";
			         			html+="<input type='text' id='fechaDesdeSolExp_filter_table' name='expedienteRelacionado.fechaDesdeSolExp'  class='form-control'  >";
			         			html+="</div>";
			         		html+="</div>";
			         		html+="<div class='form-group col-lg-7 p-0'>";
			         		html+="<label for='fechaHastaSolExp_filter_table' class='control-label col-md-3 p-0 valFecha' data-i18n='label.hasta'>".concat($.rup.i18n.app.label.fechaHasta,"</label>");
			         		html+="<div class='form-group  col-md-8 p-0 grupoFechaHora grupoFecha'>";
			         		html+="<input type='text' id='fechaHastaSolExp_filter_table' name='expedienteRelacionado.fechaHastaSolExp'  class='form-control' >";
			         		html+="</div>";
			         		html+="</div>";
			         		html+="</div>";
			         		
			         		html+="<div class='col-md-10'>";
			         		html+="<label class='control-label col-lg-12 p-0' data-i18n='label.fechaEntregaIZO'>".concat($.rup.i18n.app.label.entrega,":</label>");
			         		html+="<div class='form-group col-lg-5 p-0'>";
			         		html+="	<label for='fechaDesdeEntrega_filter_table' class='control-label col-md-4 p-0 valFecha' data-i18n='label.desde'>".concat($.rup.i18n.app.label.fechaDesde,"</label>");
			         		html+="<div class='form-group  col-md-8 p-0 grupoFechaHora grupoFecha'>";
			         		html+="<input type='text' id='fechaDesdeEntrega_filter_table' name='expedienteRelacionado.fechaDesdeEntrega'  class='form-control'  >";
			         		html+="</div>";
			         		html+="</div>";
			         		html+="<div class='form-group col-lg-7 p-0'>";
			         		html+="<label for='fechaHastaEntrega_filter_table' class='control-label col-md-3 p-0 valFecha' data-i18n='label.hasta'>".concat($.rup.i18n.app.label.fechaHasta,"</label>");
			         		html+="<div class='form-group  col-md-8 p-0 grupoFechaHora grupoFecha'>";
			         		html+="<input type='text' id='fechaHastaEntrega_filter_table' name='expedienteRelacionado.fechaHastaEntrega'  class='form-control' >";
			         		html+="</div>";
			         		html+="</div>";
			         		html+="</div>";
						     html+="</div>";
			            	 html+="<div class='row'>";
				             	html+="<div class='form-group col-xs-12 col-md-7'>";
					             	html+="<div>";
						             	html+="<label for='tiposEntidad_filter_table' class='control-label ' data-i18n='label.tipoEntidadGestora'>".concat($.rup.i18n.app.label.tipoEntidad,"</label>");
						             	html+="<div id='tiposEntidad_filter_table'>";
							             	html+="<div class='col-md-3'>";
								             	html+="<input type='radio' name='"+id+"tipoEntidadBuscadorExpRel' id='tipoEntidad_A' value='' data-on-text='<spring:message code='label.todas'/>";
								             	html+="<label for='tipoEntidad_A' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.todos,"</label>");
							             	html+="</div>";
							             	html+="<div class='col-md-3'>";
								             	html+="<input type='radio' name='"+id+"tipoEntidadBuscadorExpRel' id='tipoEntidad_B' value=".concat(tipoEntidad.ENTIDAD,"  data-on-text='<spring:message code='${tipoEntidad.label}'/>");
								             	html+="<label for='tipoEntidad_B' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.entidad,"</label>");
							             	html+="</div>";
							             	html+="<div class='col-md-3'>";
								             	html+="<input type='radio' name='"+id+"tipoEntidadBuscadorExpRel' id='tipoEntidad_E' value=".concat(tipoEntidad.DEPARTAMENTO,"  data-on-text='<spring:message code='${tipoEntidad.label}'/>");
								             	html+="<label for='tipoEntidad_E' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.departamento,"</label>");
							             	html+="</div>";
							             	html+="<div class='col-md-3'>";
								             	html+="<input type='radio' name='"+id+"tipoEntidadBuscadorExpRel' id='tipoEntidad_L' value=".concat(tipoEntidad.EMPRESA,"  data-on-text='<spring:message code='${tipoEntidad.label}'/>");
								             	html+="<label for='tipoEntidad_L' class='radio-inline rup-table-filter-fieldset' data-i18n='label.tipoEntidad'>".concat($.rup.i18n.app.label.empresa,"</label>");
							             	html+="</div>";
					             		html+="</div>";
			            			html+="</div>";
			            		html+="</div>";
			            		html+="<div class='form-group col-xs-5'>";
		            	 		html+="<div>";
		            	 			html+="<label id='labelIdEntidadGestoraBusc_filter_table' for='idEntidadGestoraBusc_filter_table' class='control-label' data-i18n='label.entidadGestora'>".concat($.rup.i18n.app.label.todasTipoEntidad,"</label>");
		            	 				html+="<select id='idEntidadGestoraBusc_filter_table' class='form-control' name='gestorExpediente.entidad.codigoCompleto'></select>";
		            	 		html+="</div>";
		            	 	html+="</div>";
			            	 html+="</div>";
			            	 html+="<div class='row'>";
			            	 	html+="<div id='"+id+"divAutocompleteGestorFilterTable' class='form-group col-xs-5'>";
				            	 	html+="<div id='"+id+"autocompleteGestorFilterTable'>";
					            	 	html+="<label for='gestor_filter_table' class='control-label' data-i18n='label.gestor'>".concat($.rup.i18n.app.label.gestor,"</label>");
					            	 		html+="<input id='gestor_filter_table' class='form-control' name='gestorExpediente.solicitante.dni'/>";
				            	 	html+="</div>";
			            	 	html+="</div>";
			            	 html+="</div>";
						 html+="</div>";
			             html+="<div class='form-group'>";
			             html+="<div class='pull-left'>";
			             html+="<legend> (*)".concat($.rup.i18n.app.mensajes.expedientesRelacionadosAsignados,"</legend>");
			             html+="</div>";
			             html+="<div id='"+id+"buscadorexprel_filter_buttonSet' class='pull-right'>";
			             html+="<button id='"+id+"buscadorexprel_filter_filterButton' type='button' class='ui-button ui-widget ui-state-default ui-corner-all'>".concat($.rup.i18n.app.boton.filtrar,"</button>");
			             html+="<a id='"+id+"limpiarBuscExpRel' href='javascript:void(0)' class='rup-enlaceCancelar'>".concat($.rup.i18n.app.boton.limpiar,"</a>");
			             html+="</div>";
			             html+="</div>";
			             html+="</div>";
			             html+="</fieldset>";
			             html+="</form>";
		             html+="</div>";
		    		 html+="<div id='contenedor_tabla_buscadorexprel' class='table pb-2 '>";
		             html+="<table id='"+id+"buscadorexprel'></table>";
		             html+="</div>";
		             html+="<div id='"+id+"buscadorexprel_pager'></div>";
	             html+="</div>";
	             html+="<div id='"+id+"detalleexprel_div' class='container-fluid aa79b-fade collapsed'>";
	             	html+="<input id='detalleexprel_anyoNumExp' class='form-control'/>";
	             html+="</div>";
             html+="</div>";
             this.append(html);
			
             //seteamos los valores del expediente para mapearlos correctamente
             $('#'+id+'anyoExpRel').val(expediente.anyo);
             $('#'+id+'numExpExpRel').val(expediente.numExp);
             
             
             /*
              * Inicializacion de componentes - INICIO 
              */
          
             fnFechaDesdeHasta("fechaDesdeSolExp_filter_table", "fechaHastaSolExp_filter_table");
             fnFechaDesdeHasta("fechaDesdeEntrega_filter_table", "fechaHastaEntrega_filter_table");
             crearComboEntidadGestoraExpRel("");
             
             jQuery('input[id=tituloExpRel_filter_table').focusout(function() {
            	 var retorno=$("#tituloExpRel_filter_table").val().replace(/^\s+/g,'');
            	 retorno=retorno.replace(/\s+$/g,'');
            	 jQuery('input[id=tituloExpRel_filter_table').val(retorno);
             });
             
             jQuery('input[name='+id+'tipoEntidadBuscadorExpRel]:first').click();
         	 jQuery('input[name='+id+'tipoEntidadBuscadorExpRel]').change(function(){
         		entidadCheckeadaExpRel = $(this).val();
         		if("".localeCompare(entidadCheckeadaExpRel)==0){
         			entidadCheckeadaExpRel = "-1";
     			}
     			$('#idEntidadGestoraBusc_filter_table').rup_combo("setRupValue","");
 				entidadExpRel = $(this).val();
 				if("".localeCompare(entidadExpRel)==0){
     				entidadExpRel = "-1";
     			}
 				$('#'+id+'entidadTipoExpRel').val($(this).val());
     			crearComboEntidadGestoraExpRel($(this).val());
     			anyadirEventoChangeAComboEntidadGestoraExpRel(id);
     			cambiarLabelTipoEntidadExpRelBE($(this).val());
         		});
         	anyadirEventoChangeAComboEntidadGestoraExpRel(id);
         	
         	crearAutocompleteGestorExpRel();
        	
         	jQuery('input:checkbox[data-switch-modal="true"]').each(function(index, element){
          		jQuery(element)
          		.bootstrapSwitch()
          		.bootstrapSwitch('setSizeClass', 'switch-small')
          		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
          		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
          		});
         	if(permiso.SI.localeCompare(settings.permisoBOPV)===0){
         		 $('#bopvCheckbox').on('switch-change', function (e, data) {
                	 if(data.value){
                		 $('#'+id+'bopv_filter_table').val('S');
                	 }else{
                		 $('#'+id+'bopv_filter_table').val('N');
                	 }   
                 });
         	}
             
             $("#"+id+"buscadorexprel_filter_form").rup_validate({
		   			feedback: $('#'+id+'buscadorexprel_feedback'),
		   			liveCheckingErrors: false,				
		   			block:false,
		   			delay: 3000,
		   			gotoTop: true, 
		   			rules:{
		   				"expedienteRelacionado.fechaHastaSolExp": {required: false, date: true,fechaHastaMayor:"expedienteRelacionado.fechaDesdeSolExp"},
		   				"expedienteRelacionado.fechaHastaEntrega": {required: false, date: true,fechaHastaMayor:"expedienteRelacionado.fechaDesdeEntrega"},
		   				"expedienteRelacionado.fechaDesdeSolExp":{date:true},
		   				"expedienteRelacionado.fechaDesdeEntrega":{date:true},
		   			},
		   			showFieldErrorAsDefault: false,
		   			showErrorsInFeedback: true,
		   	 		showFieldErrorsInFeedback: false
		   		});
             
             $('#'+id+'buscadorexprel_filter_filterButton').click(function(){
            	 $("#"+id+"buscadorexprel_filter_form").valid();
             });
             
             $("#"+id+"buscadorexprel").rup_table({
            	 url:"/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/obtenerExpedientesNoRelacionados",
            	 resizable: false,
            	 rowNum: 10,
            	 toolbar: {
            		 id: "buscadorexprel_toolbar",
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
            		 			i18nCaption: $.rup.i18n.app.boton.cancelar
            		 			,css: "fa fa-arrow-left"
            					,index: 0
	            		 		}
	            		 		,json_i18n : $.rup.i18n.app.simpelMaint
	            		 		,click : 
	           					 function(){
	            		 			$("#"+id).rup_dialog("close");
	           					}	
            		 		},
            		 		{obj: {
            		 			i18nCaption: $.rup.i18n.app.boton.seleccionar
            		 			,css: "fa fa-floppy-o"
            					,index: 0
	            		 		}
	            		 		,json_i18n : $.rup.i18n.app.simpelMaint
	            		 		,click : 
	           					 function(){
	            		 			var expedientesSeleccionados = [];
	            		 			var selectedRows = [];
	            		 			selectedRows = $("#"+id+"buscadorexprel").rup_table('getSelectedRows');
	           					 	for(var i=0;i<selectedRows.length;i++){
		           						 var j = selectedRows[i];
		           						 expedientesSeleccionados.push({
		           							 anyo: selectedRows[i].substr(0,selectedRows[i].indexOf(',')),
		           							 numExp: selectedRows[i].substr(selectedRows[i].indexOf(',')+1,selectedRows[i].length)
	           						 });
	           					 	}
	           						var jsonObject = {
	           			        			listaExpediente: []
	           			        	};
	           						if(expedientesSeleccionados.length===0){
	                         			$('#'+id+'buscadorexprel_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionExpediente, "error");
	           						}else{
	           							if(settings.guardarOnartu){
		           							for(var k=0;k<expedientesSeleccionados.length;k++) {    
	
			           			    		    var item = expedientesSeleccionados[k];
			           			    		    
			           			    		    var jsonObjExpedienteRelacionado = { 
			           		        		        "anyoExpRel" : item.anyo,
			           		        		        "numExpRel" : item.numExp
			           		        		    };
			           			    		    
			           			    		    
			           			    		    jsonObject.listaExpediente.push({ 
			           			    		        "anyo" : settings.anyo,
			           			    		        "numExp"  : settings.numExp,
			           			    		        "expedienteRelacionado" : jsonObjExpedienteRelacionado
			           			    		    });
			           						}
			           						var expedientes = [];
		           							jQuery.ajax({
		           			    		    	type: "POST",
		           			    		    	url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/relacionarExpedientes",
		           			    		    	dataType: "json",
		           			    		    	contentType: 'application/json',
		           			    		    	data: $.toJSON(jsonObject),
		           			    		    	cache: false,
		           			    		    	success: function (data){
		           			    		    		if(data!= null && data.listaExpediente!=null && data.listaExpediente.length>0){
	           			    		    				for(var i=0;i<data.listaExpediente.length;i++){
															var aux = data.listaExpediente[i];
															expedientes.push(aux);
	           			    		    				}
		           			    		    			if(typeof options.callback == 'function'){
															options.callback.call(this, expedientes);
															$("#"+id).rup_dialog("close");
															
		           			    		    			}
		           			    		    		}else{
		        	                         			$('#'+id+'buscadorexprel_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionExpediente, "error");
		           			    		    		}
		           			    		    	},
		           			    		    	error: function (){
		           			    		    		$('#'+id+'buscadorexprel_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorSeleccionExpediente, "error");
		           			    		    	}
		           			    		    });
		           						}else{
		           							expedientesRelSeleccionados = [];
		           							for(var k=0;k<expedientesSeleccionados.length;k++) {    
		           								
			           			    		    var item = expedientesSeleccionados[k];
			           			    		    
			           			    		    var jsonObjExp = { 
			           		        		        "anyo" : item.anyo,
			           		        		        "numExp" : item.numExp
			           		        		    };
			           			    		    
			           			    		    
			           			    		 expedientesRelSeleccionados.push({ 
			           			    		        "anyo" : jsonObjExp.anyo,
			           			    		        "numExp"  : jsonObjExp.numExp,
			           			    		        "numExpFormated" : concatenarAnyoNumExp(jsonObjExp.anyo,jsonObjExp.numExp)
			           			    		    });
			           						}
											$("#"+id).rup_dialog("close");
		           						}
		           			    		    
	           						}
	           						
	           					}	
            		 		}
            		 		
            		 	]
            	 }, 
            	 colNames: [
            		 $.rup.i18nParse($.rup.i18n.app, "label.numExp"),
            		 $.rup.i18nParse($.rup.i18n.app, "label.titulo"),
            		 $.rup.i18nParse($.rup.i18n.app, "label.fechaHoraSolicitud"),
            		 $.rup.i18nParse($.rup.i18n.app, "label.fechaHoraEntrega")
            	 ],
            	 colModel:[
            		 { 	name: "anyoNumExpConcatenado", 
         			 	label: "label.anyoNumExpConcatenado",
         				align: "", 
         				width: "145", 
         				index: "ANYONUMEXPCONCATENADO",
         				editable: false, 
         				fixed: true, 
         				hidden: false, 
         				resizable: true, 
         				sortable: true,
         				formatter: function (cellvalue, options, rowObject) {
         					return '<b><a target="_blank" href="/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/'+rowObject.anyo+'/'+rowObject.numExp+'">' + cellvalue + '</a></b>';
         				}
         			}, 
         			{
         				name:"titulo",
         				label: "label.titulo",
         				width: "300",
         				index: "TITULO_051",
         				align: "left",
         				editable: false,
         				fixed: true,
         				hidden: false,
         				resizable: true,
         				sortable: true
         			}, 
         			{
         				name:"fechaHoraAlta",
         				label: "label.fechaAlta",
         				width: "160",
         				index: "FECHA_ALTA_051",
         				align: "right",
         				editable: false,
         				fixed: true,
         				hidden: false,
         				resizable: true,
         				sortable: true
         			}, 
         			{
         				name:"expedienteTradRev.fechaHoraFinalIZO",
         				label: "label.fechafinal",
         				width: "160",
         				index: "FECHA_FINAL_IZO_053",
         				align: "right",
         				editable: false,
         				fixed: true,
         				hidden: false,
         				resizable: true,
         				sortable: true
         			}
            	 ],
            	 model: "Expediente",
            	 usePlugins:[
            		 "feedback",
            		 "toolbar",
            		 "responsive",
            		 "filter",
            		 "multiselection",
            		 "fluid"
            	 ],
            	 primaryKey:["anyo", "numExp"],
            	 sortname: "ANYONUMEXPCONCATENADO",
            	 sortorder: "asc",
            	 loadOnStartUp: false,
            	 multiplePkToken: ",",
            	 multiselection:{
     				headerContextMenu_enabled: false
     				},
            	 filter:{
            		 validate:{
            			 rules:{
            				 "expedienteRelacionado.fechaDesdeSolExp":{date:true},
            				 "expedienteRelacionado.fechaHastaSolExp":{date:true},
            				 "expedienteRelacionado.fechaDesdeEntrega":{date:true},
            				 "expedienteRelacionado.fechaHastaEntrega":{date:true}
            			 }
            		 },beforeFilter: function(){
 						$("#"+id+"buscadorexprel").rup_table("resetSelection");
 					}
            	 },
        	 	loadComplete:function(){
           	 		if(!settings.guardarOnartu && expedientesRelSeleccionados && expedientesRelSeleccionados.length > 0){
           	 			seleccionarExpedientesDeLista($("#"+id+"buscadorexprel"),expedientesRelSeleccionados)
           	 		}
           	 	}
             });
             
             $('#'+id+'buscadorexprel_feedback').rup_feedback({
          		block : false,
         		delay: 1000,
         		gotoTop: false
          	});
             
             if($('#'+id+'buscadorexprel_feedback').length){
            	 $('#'+id+'buscadorexprel_feedback').css("display", "none");
             }
             
             /*
              * Inicializacion de componentes - FIN 
              */
             
             this.rup_dialog({
            	type: $.rup.dialog.DIV,
            	autoOpen: false,
            	width: 900,
	         	maxWidth: 900,
         		minWidth: 900,
         		height: 750,
         		maxHeight:750,
         		minHeight:750,
         		dialogClass:"buscadorExpedientesRelacionados",
         		modal: true,
         		resizable: false,
         		title: $.rup.i18n.app.comun.buscadorExpedientesRelacionados,
         		close: function(){
         			mostrarExpedientesRelacionados();
         		}
             });
             
             /** Al limpiar los campos de filtrado, tenemos que volver a asignar anyo y numExp a campos ocultos*/
             $("#"+id+"limpiarBuscExpRel").on("click",function(){

              	$("#"+id+"buscadorexprel").rup_table("resetForm", $("#"+id+"buscadorexprel_filter_form"));
             	$("#"+id+"anyoExpRel").val(settings.anyo);
             	$("#"+id+"numExpExpRel").val(settings.numExp);
             	jQuery('input[name='+id+'tipoEntidadBuscadorExpRel]:first').click();
             	if(permiso.SI.localeCompare(settings.permisoBOPV)===0){
             		$('#bopvCheckbox').bootstrapSwitch('setState', false);
                	 $('#'+id+'bopv_filter_table').val('N');
             	}
             	eliminarMensajesDeError()
             	$("#"+id+"buscadorexprel").rup_table("reloadGrid", true);
             	
             	
             })
             if(settings.guardarOnartu){
            	  bloquearPantalla();
           		$.ajax({
           		   	 type: 'GET'
           		   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/getPermisosBopv/'+options.anyo+'/'+options.numExp
           		 	 ,dataType: 'json'
           		 	 ,contentType: 'application/json' 
           		     ,cache: false 
           		   	 ,success:function(data){
           		   		settings.permisoBOPV = data;
           		   		if(permiso.NO.localeCompare(settings.permisoBOPV)===0){
           		   			$("#divBopvCheckbox").remove();
           		   			//borrar tambien el filtro oculto
           		   			$("#"+id+"bopv_filter_table").val("N");
           		   		}
           		   		desbloquearPantalla();
           		   	 }
           		});
             }
		}else {
            $.error( 'Method ' +  methodOrOptions + ' does not exist on jQuery.tooltip' );
        }
	};
}( jQuery ));

