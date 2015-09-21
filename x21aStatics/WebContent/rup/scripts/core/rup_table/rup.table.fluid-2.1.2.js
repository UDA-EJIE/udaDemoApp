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

	jQuery.rup_table.registerPlugin("fluid",{
		loadOrder:4,
		postConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("postConfigureFluid", settings);
			
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	/**
	 * Extensión del componente rup_table para permitir la edición de los registros mediante un formulario. 
	 * 
	 * Los métodos implementados son:
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
		postConfigureFluid: function(settings){
			var $self = this, fluidBaseLayerId;
			if (settings.fluid && settings.fluid.baseLayer){
				fluidBaseLayerId = (settings.fluid.baseLayer[0]==="#"?settings.fluid.baseLayer:"#"+settings.fluid.baseLayer);
				if (jQuery(fluidBaseLayerId).length === 0){
					alert("El identificador especificado para el fomulario de búsqueda no existe.");
				}else{
					var $fluidBaseLayer = settings.$fluidBaseLayer = $(fluidBaseLayerId);
					
					// Tratamiento del evento de redimiensionado del diseño líquido de la tabla
					$self.bind("fluidWidth.resize", function(event, previousWidth, currentWidth){
						$self.setGridWidth(currentWidth);
						
						// Se redimensionan las capas contenidas en el mantenimiento
						$fluidBaseLayer.children().width(currentWidth);
//						prop.searchForm.parent().width(currentWidth+3)
						// Se redimensiona el feedback
						/*
						 * TODO: Redimensionar feedback
						 */
						var feedBackPaddingLeft = parseInt(settings.$feedback.css("padding-left")),
							feedBackPaddingRight = parseInt(settings.$feedback.css("padding-right"));
						settings.$feedback.width(currentWidth - (feedBackPaddingLeft+feedBackPaddingRight));
						
						// Se redimensiona la toolbar
						var toolbarPaddingLeft = parseInt(settings.$toolbar.css("padding-left")),
						toolbarPaddingRight = parseInt(settings.$toolbar.css("padding-right"));
						settings.$toolbar.width(currentWidth - (toolbarPaddingLeft+toolbarPaddingRight));
						settings.$toolbar.css("width", currentWidth - (toolbarPaddingLeft+toolbarPaddingRight));
//						}
						
					});

					$self.fluidWidth({
						fluidBaseLayer:settings.fluid.baseLayer,
						minWidth: 100,
						maxWidth: 2000,
						fluidOffset : 0
					});
				}
			}
		}
	});
	
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	
		
	// Parámetros de configuración por defecto para la acción de eliminar un registro.
	jQuery.fn.rup_table.plugins.fluid = {};
	jQuery.fn.rup_table.plugins.fluid.defaults = {
			fluid:{
				
			}
	};
	
		
	
})(jQuery);