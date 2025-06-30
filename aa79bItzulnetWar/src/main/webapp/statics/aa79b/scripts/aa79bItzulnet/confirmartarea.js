/* HACER USO DEL CONFIRMAR TAREA - INICIO 
 * 
 * 1 - Incluir en el -includes.jsp (o si no la tiene en la misma jsp): 
 *		<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/confirmartarea.js" type="text/javascript"></script>
 *
 * 2 - En la jsp, inlcuir:
 * 		<div id="confirmartarea" style="display:none"></div>
 * 
 * 3 - En la js de la pantalla incluir:
 * 
 * 		$("#confirmartarea").confirmar_tarea(); -- crea el buscador
 * 		
 * 
 * 		$("#confirmartarea").confirmar_tarea("open"); -- abre el buscador 
 *   
 * HACER USO DEL CONFIRMAR TAREA - FIN */
var id;	
var modalSelector;
var tieneHora;
var esHoraOblig;
var documentos;
var idTrabajo;

function cambiarLabelHoras(esHoraOblig){
	if(esHoraOblig){
		$('#'+id+'_horasSpan').text($.rup.i18n.app.mensajes.horasRealesOblig);	
	}else{
		$('#'+id+'_horasSpan').text($.rup.i18n.app.mensajes.horasRealesOpt);
	}
}

function validarHoraConfirmarTarea(value){
	var error = "";
	if(esHoraOblig && value === ''){
		error = $.rup.i18n.base.rup_validate.messages.required;
	}
	if(esHoraOblig ||(!esHoraOblig && value != '') ){
		if(error === ''){
			if(value.indexOf(":") == -1
					|| value.split(':').length > 2){
				error = $.rup.i18n.base.rup_validate.messages.pattern;
			}else{
				var hora = value.split(':');
				if(hora[0] !== "" && hora[1] !== "" && hora[0].length >= 1 && hora[1].length == 2){
					var hh = parseInt(hora[0],10);
					var mm = parseInt(hora[1],10);
					if (hh < 0 || hh > 9999999) error =  $.rup.i18n.base.rup_validate.messages.pattern;
					if (mm < 0 || mm > 59) error = $.rup.i18n.base.rup_validate.messages.pattern;	
				}else{
					error = $.rup.i18n.base.rup_validate.messages.pattern;	
				}
			}
		}
	}

	return error;
}

function aceptar(){
	eliminarMensajesValidacion();
	if(tieneHora){
		var error = validarHoraConfirmarTarea($("#horasReales").val());
		if(error === ''){
			bloquearPantalla();
			if($("#horasReales").val()){
				$("#horasTarea_form").val($("#horasReales").val());
			}
			
			$("#"+id).rup_dialog("close");
			$("#"+modalSelector+"_form").submit();
		}else{
			//Crear el error - feedback y error		
			$("#horasReales").addClass("error custom-error");
			$("#"+id+"_horasDiv").append('<label class="error" id="horasReales-error" for="horasReales">'+ error  +'</label>');
		}
	}else{
		bloquearPantalla();
		$("#"+id).rup_dialog("close");	
		$("#"+modalSelector+"_form").submit();
	}
}

(function ( $ ) {
 
	$.fn.confirmar_tarea = function( options ) {
 
		var confirmar_tarea_methods = {
		        open : function( ) {  
		        	this.rup_dialog("open");
		        	$(".confirmartarea .ui-dialog-titlebar-close").hide();
		        },
		    };
		
		if ( confirmar_tarea_methods[options] ) {
            return confirmar_tarea_methods[ options ].apply( this, Array.prototype.slice.call( arguments, 1 ));
        } else if ( typeof options === 'object' || ! options ) {
        	// This is the easiest way to have default options.
            var settings = $.extend({
                // These are the defaults.
            	esHoraOblig: options.esHoraOblig,
                tieneHora: options.tieneHora,
                documentos: options.documentos,
                modalSelector: options.modalSelector,
				idTrabajo: options.idTrabajo,
                callback: function(event, object) {
                	
                }
            }, options );
            id=this.attr("id");
            var name=this.attr("name");
            modalSelector = options.modalSelector;
            tieneHora = options.tieneHora;
            esHoraOblig = options.esHoraOblig;
            documentos = options.documentos;
			idTrabajo = options.idTrabajo;
            var html = '<div class="container-fluid aa79b-fade in" id="divEjecutarTareaConfirmCapa">';
        		html += '<div class="row">';
        			html += '<div class="col-xs-12">';
    					html += $.rup.i18n.app.mensajes.continuarFinTarea;
					html += '</div>';
				html += '</div>';
				if(tieneHora){
					if(documentos){
						html += '<div class="row">';
			    			html += '<div class="col-xs-12">';
								html += $.rup.i18n.app.mensajes.docsTraducidosAcc;
							html += '</div>';
						html += '</div>';					
					}
					html += '<div class="row">';
						html += '<div class="col-xs-12">';
							html+= "<span id='"+id+"_horasSpan'></span>";
						html += '</div>';
					html += '</div>';
					html += '<div id="'+id+'_horasDiv"  class="row">';
						html += '<div class="col-xs-12">';
							html += $.rup.i18n.app.mensajes.horasReales;
						html += '</div>';
						html += '<div class="col-xs-12">';
							html += '<input id="horasReales" name="horasReales" class="form-control campohora10" maxlength="10" placeholder="hh:mm" />';
						html += '</div>';
					html += '</div>';
				}
            html += '</div>';
            
            this.append(html);
            
            cambiarLabelHoras(options.esHoraOblig);
             
            this.rup_dialog({
        		type: $.rup.dialog.DIV,
        		autoOpen: false,
        		width: 500,
        		dialogClass:"confirmartarea",
        		modal: true,
        		resizable: false,
        		closeOnEscape: false,
        		title: $.rup.i18n.app.boton.finalizarTarea,
        		buttons: [{
        			text: $.rup.i18n.app.boton.aceptar,
        			click: function () { 
						// comprobamos que la tarea no este ejecutada y que las tareas previas esten ejecutadas
						if (idTrabajo && comprobarTareaTrabajoEjecutada(idTarea, idTrabajo)){
							aceptar();
						} else if (comprobarTareaNoEjecutada(idTarea)){
        					aceptar();
						} else {
							$("#"+id).rup_dialog("close");
						}
        			}					
        		}/*,
        		{
        			text: $.rup.i18n.app.boton.cancelar,
        			click: function () { 
        				$("#"+id).rup_dialog("close");
        			},
        			btnType: $.rup.dialog.LINK
        		}*/] 
        	});
            
            return this;
        } else {
            $.error( 'Method ' +  methodOrOptions + ' does not exist on jQuery.tooltip' );
        }  
    };

   
}( jQuery ));