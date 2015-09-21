/*!
 * Copyright 2013 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

(function ($) {

	jQuery.rup_table.registerPlugin("feedback",{
		loadOrder:2,
		preConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("preConfigureFeedback", settings);
			
		},
		postConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("postConfigureFeedback", settings);
			
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	/**
	 * Extensión del componente rup_table para permitir la edición de los registros mediante un formulario. 
	 * 
	 * Los métodos implementados son:
	 * 
	 * configureDetailForm(settings): Realiza la configuración interna necesaria para la gestión correcta de la edición mediante un formulario.
	 * deleteElement(rowId, options): Realiza el borrado de un registro determinado.
	 * editElement(rowId, options): Lanza la edición de un registro medainte un formulario de detalle.
	 * newElement(): Inicia el proceso de inserción de un nuevo registro.
	 * showServerValidationFieldErrors(errors): Función encargada de mostrar los errores producidos en la gestión de los datos del mantenimiento.
	 * 
	 * Las propiedades de esta extensión almacenadas en el settings son las siguientes:
	 * 
	 * settings.$detailForm : Referencia al formulario de detalle mediante el que se realizan las modificaciones e inserciones de registros.
	 * settings.$detailFormDiv : Referencia al div que arropa el formulario de detalle y sobre el que se inicializa el componente rup_dialog. 
	 *  
	 */
	jQuery.fn.rup_table("extend",{
		/*
		 * Realiza la configuración interna necesaria para la gestión correcta de la edición mediante un formulario.
		 * 
		 * TODO: internacionalizar mensajes de error.
		 */
		preConfigureFeedback: function(settings){
			var $self = this;
			$self.rup_table("configureFeedback",settings);
		},
		postConfigureFeedback: function(settings){
			
			// Definición del feedback interno del mantenimiento
			settings.$internalFeedback = $("<div/>").attr("id", "rup_feedback_" + settings.id).insertBefore('#gbox_' + settings.id);
			settings.$internalFeedback.rup_feedback(settings.feedback.internalFeedbackConfig);
		}
	});
	
	jQuery.fn.rup_table("extend",{
		configureFeedback: function(settings){
			var $self = this, $feedback, feedbackId;
			if (settings.feedback && settings.feedback.id){
				feedbackId = (settings.feedback.id[0]==="#"?settings.feedback.id:"#"+settings.feedback.id);
				$feedback = jQuery(feedbackId);
				if ($feedback.length === 0){
					alert("El identificador especificado para el feedback no existe.");
				}else{
					settings.$feedback = $feedback;
					settings.$feedback.rup_feedback(settings.feedback.config).attr("ruptype","feedback");
				}
			}
		}
	});
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	
		
	// Parámetros de configuración por defecto para la acción de eliminar un registro.
	jQuery.fn.rup_table.plugins.feedback = {};
	jQuery.fn.rup_table.plugins.feedback.defaults = {
			feedback:{
				id: "feedback",
				config:{
					type: "ok",
					closeLink: true,
					gotoTop: false,
					block: true
				},
				internalFeedbackConfig:{
					type: "ok",
					closeLink: true,
					gotoTop: false,
					block: false
				}
			}
	};
	
		
	
})(jQuery);