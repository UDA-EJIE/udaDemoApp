/*!
 * Copyright 2021 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable json se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
const pathVariable = './' + $('#sendEntity').data('pathVariable');

function setEntityData (id) {	
	$.get(pathVariable + id, function(data) {
		const entity = JSON.parse(data);
		
		$("#id_detail_table").val(entity.id);
		$("#nombre_detail_table").val(entity.nombre);
		$("#apellido1_detail_table").val(entity.apellido1);
		$("#apellido2_detail_table").val(entity.apellido2);
		$("#fechaBaja_detail_table").val(entity.nombreBaja);
		$("#fechaAlta_detail_table").val(entity.fechaAlta);
		$("#ejie_detail_table").val(entity.ejie);
		$("#rol_detail_table").val(entity.rol);
	});
}

function serializeFormJSON (id) {
    let json = {};
    $.each($('#' + id).serializeArray(), function () {
        if (json[this.name]) {
            if (!json[this.name].push) {
                json[this.name] = [json[this.name]];
            }
            json[this.name].push(this.value || '');
        } else {
            json[this.name] = this.value || '';
        }
    });
    return json;
}

jQuery(function($) {
    window.initRupI18nPromise.then(function(){
		$('#closeEditFormWindow').on('click', function () {
			$('#closeEditFormWindowDialog').rup_dialog({
		        type: $.rup.dialog.TEXT,
		        autoOpen: true,
		        modal: true,
		        resizable: false,
		        title: 'Confirmación de cierre de ventana',
		        message: '¿Desea cerrar el formulario?',
		        buttons: [{
		            text: 'Aceptar',
		            click: function () { 
		            	window.close();
		            }					
		        }]
		    });
		});
		
		$('#fechaAlta_detail_table').rup_date();
        $('#fechaBaja_detail_table').rup_date();
		
		const $feedback = $('#example_detail_feedback').rup_feedback({
			type: 'error',
			closeLink: true,
			block: false
		});
		
		$('#example_detail_form').rup_validate({
		    feedback: $feedback,
		    liveCheckingErrors: false,
		    showFieldErrorAsDefault: true,
		    showErrorsInFeedback: true,
		    showFieldErrorsInFeedback: true,
		    rules: {
		        'nombre': {
		            required: true
		        },
		        'apellido1': {
		            required: true
		        },
		        'fechaAlta': {
		            required: true
		        },
		        'fechaBaja': {
		            date: true
		        }
		    },
		    onSubmitHandler: function () {
		    	const actionType = $('#sendEntity').data('actionType');
				
				$.ajax({
			        type: actionType,
			        url: pathVariable + (actionType == 'POST' ? 'add' : 'edit'),
			        data: JSON.stringify(serializeFormJSON('example_detail_form')),
			        contentType: 'application/json',
			        success: function() {
			        	$.rup_messages('msgOK', {
		                    title: 'Petición correcta',
		                    message: 'Petición correcta, se cerrará la ventana de edición.'
		                });
			        	
			        	setTimeout(function(){
			        		window.close();
			        	}, 3000);
			        },
			        error: function({responseJSON}) {			        	
			        	$('#example_detail_feedback').rup_feedback('set', responseJSON.message, 'error');
			        }
			  	})
			}
		});
    });
});