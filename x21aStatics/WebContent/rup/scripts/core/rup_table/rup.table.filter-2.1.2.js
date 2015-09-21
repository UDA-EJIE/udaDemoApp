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

	jQuery.rup_table.registerPlugin("filter",{
		loadOrder:1,
		postConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("postConfigureFilter", settings);
			
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
		postConfigureFilter: function(settings){
			var $self = this, filterFormId, filterSettings;
			
			
			if (settings.filter!==undefined && settings.filter.id!==undefined){
				filterSettings = settings.filter;
				
				filterFormId = (filterSettings.id[0]==="#"?filterSettings.id:"#"+filterSettings.id);
				if (jQuery(filterFormId).length === 0){
					alert("El identificador especificado para el fomulario de búsqueda no existe.");
				}else{
					
					/*
					 * Se almacena la referencia de los diferentes componentes
					 * 
					 * $filterContainer : Contenedor del formulario de filtrado
					 * $filterButton : Botón que realiza el filtrado
					 * $cleanLink : Enlace para limpiar el formulario
					 * $collapsableLayer : Capa que puede ser ocultada/mostrada
					 * $collapseButtonId : Control que oculta muestra el fomulario 
					 * $filterCriterias : Contenedor donde se especifican los criterios de filtrado
					 */
					filterSettings.$filterContainer = jQuery("#"+filterSettings.id);
					filterSettings.$filterButton = jQuery("#"+filterSettings.filterButtonId);
					filterSettings.$cleanLink = jQuery("#"+filterSettings.cleanLinkId);
					filterSettings.$collapsableLayer = jQuery("#"+filterSettings.collapsableLayerId);
					filterSettings.$collapseButton = jQuery("#"+filterSettings.collapseButtonId);
					filterSettings.$collapseLabel = jQuery("#"+filterSettings.collapseLabelId);
					filterSettings.$filterCriterias = jQuery("#"+filterSettings.filterCriteriasId);
					
					/*
					 * TODO: Comprobar que la configruación es correcta
					 */
					
					if (filterSettings.$filterContainer.prop("tagName")==="FORM"){
						filterSettings.$filterContainer.ajaxForm();
					}
					
					// Se utiliza el plugin ajaxForm de jQuery para configurar el formualario de busqueda como AJAX.
					// Se redimensiona el formulario de busqueda al tamanyo del grid.
					filterSettings.$filterContainer.parent().css("width",$self.rup_table("getGridParam", "width"));
					
					// Se almacena en las propiedades la url utilizada para la busqueda a partir de la especificada en el grid.
					settings.searchURL = $self.rup_table("getGridParam", "url");
					
					
					// Se asigna a la tecla ENTER la funcion de busqueda. 
					filterSettings.$filterContainer.bind("keydown", function(evt) {
						if (evt.keyCode == 13) {
							/*
							 * TODO : Implementar búsqueda formulario
							 */
							$self.rup_table("filter");
						}
					});
					
					// Creacion del boton de busqueda.
					filterSettings.$filterButton.bind("click", function () {
						// TODO: Control cambios
						/*
						 * TODO : Implementar búsqueda formulario
						 */
						$self.rup_table("filter");
					});
					
					// Creacion del enlace de limpiar formulario.
					filterSettings.$cleanLink.bind("click", function () {
						$self.rup_table("cleanFilterForm").rup_table("filter");
					});
					
					// Creacion del enlace de mostrar/ocultar el formulario
					filterSettings.$collapseButton.add(filterSettings.$collapseLabel).on("click", function(){
						$self.rup_table("toggleFilterForm");
					});
				}
			}
		}
	});
	
	
	jQuery.fn.rup_table("extend",{
		cleanFilterForm : function () {
			var $self = this, 
				settings = $self.data("settings");
			
			$self.rup_table("resetForm", settings.filter.$filterContainer);
			
			return $self;
		},
		filter : function(async){
			var $self = this, 
				props = $self[0].p,
				settings = $self.data("settings"); 
			
			jQuery.extend (props.postData,settings.filter.$filterContainer.serializeObject());
			var postDataAux = {};
			jQuery.each (props.postData, function(a,b){
			    if (b!==''){
			    	postDataAux[a]=b;
			    }
			});
			props.postData = postDataAux;
			
			$self.trigger("reloadGrid");
		},
		toggleFilterForm: function(capa, filterCriteriaLoad, hiddenCallback, visibleCallback){
			var $self = $(this), settings = $self.data("settings"), filterSettings = settings.filter, postData, searchFormPostData = {}, $searchFormLayer, $titleSearch, $field,
			searchString = "", initialHeight, height, tmp = searchString, tooltip = false;
			
//			if (settings.$searchForm === null) {//si no hay formulario de busqueda no hacemos nada
//				return false;
//			}else{
				
				
				if (filterSettings.$collapsableLayer.is(":hidden") && filterCriteriaLoad===undefined) {
					// Se muestra el formulario de búsqueda
					filterSettings.$collapsableLayer.show({
						duration: "slow",
						effect: "blind"
					});
					
					// Restauramos el literal del resumen de criterios de búsqueda
//					jQuery("#titleSearch_" + settings.id)
//					.text($.rup.i18nParse($.rup.i18n.base,"rup_maint.searchOptions"))
//					.removeClass("rup-maint_searchCriteria ");
				
					// Anadido el foco al primer campo del formulario
					jQuery("input:first", filterSettings.$filterContainer).focus();
				
					filterSettings.$collapseButton.removeClass("ui-icon-triangle-1-e");
					filterSettings.$collapseButton.addClass("ui-icon-triangle-1-s");
					
					//Eliminar tooltip
//					$titleSearch.rup_tooltip("destroy");
					
				}else{
					filterSettings.$collapsableLayer.hide({
						duration: "slow",
						effect: "blind"
					});
					
					filterSettings.$collapseButton.removeClass("ui-icon-triangle-1-s");
					filterSettings.$collapseButton.addClass("ui-icon-triangle-1-e");
					
//					// Obtenemos los parámetros enviados en la última consulta realizada
//					postData = $self.rup_table("getGridParam","postData");
//					
//					jQuery.each(postData, function(name, value){
//		
//						$field = jQuery("[name='"+name+"']", settings.$searchForm);
//					    if ($field.length!==0){
//					        searchFormPostData[name]=value;
//					        
//					        var formFieldLabel = $self._getSearchFormFieldLabel($field, settings.$searchForm),
//					        formFieldValue = $self._getSearchFormFieldValue($field, settings.$searchForm);
//							
//							//Si no tiene NAME sacar solo el valor
//							if (formFieldLabel === "" && formFieldValue.indexOf(" = ")!==-1){
//								formFieldValue = formFieldValue.substring(2, formFieldValue.length); 
//							}
//							
//							//Si no tiene NAME ni VALUE omitir
//							if (formFieldLabel !== "" || $.trim(formFieldValue) !== ""){
//								searchString = searchString + formFieldLabel + formFieldValue + ", ";
//							}
//					    }
//					});
//					
//					//Contiene criterios
//					if (searchString.length>1){
//						// Se elimina el caracter ',' y el espacio final
//						searchString = searchString.substring(0, searchString.length-2);
//						
//						initialHeight = $titleSearch.css("height");
//						tmp = searchString;
//						tooltip = false;
//
//						//Añadir criterios
//						while(true){
//							$titleSearch.html($.rup.i18nParse($.rup.i18n.base,"rup_maint.searchOptions")+"<i>" + tmp + "</i>");
//							height = $titleSearch.css("height");
//							if (height === initialHeight){
//								break;
//							}
//							tmp = tmp.substring(0, tmp.lastIndexOf(",")) + " <b>...</b>";
//							tooltip = true;
//						}
//
//						
//						//Añadir estilo criterios
//						$titleSearch.addClass("rup-maint_searchCriteria");
//						
//						//Tooltip con criterios
//						if (tooltip){
//							$titleSearch
//								.rup_tooltip({
//									content: {
//										text: searchString.substring(1)
//									},
//									position: {
//										my: 'bottom center',
//										at: 'top center'
//									}
//								});
//						}
//					} 
				}
//			}
			
			return $self;
		}
	});
	
	jQuery.fn.rup_table("extend",{
		_getSearchFormFieldLabel: function($field, $form){
			var fieldId = $field.attr("id"), $label, formFieldLabel="", rupType = $field.attr("ruptype");
			
			if (rupType !== "combo"){
				$label = jQuery("label[for='"+fieldId+"']", $form);
			}else{
				$label = jQuery("label[for='"+fieldId+"-button']", $form);
			}
			
			if ($label.length>0){
				// <label for='xxx' />
				formFieldLabel = $label.html();
			} else {
				// <div />
				// <div />
				if ($field.attr("ruptype") !== "combo"){
					//fieldName= $("[name='" + aux[i].name + "']",searchForm).prev('div').html();
					formFieldLabel= $("[name='" + name + "']", $form).prev('div').find('label').first().html();
				} else {
					//fieldName= $("[name='" + aux[i].name + "']",searchForm).parent().prev('div').html();
					formFieldLabel= $("[name='" + name + "']", $form).parent().prev('div').find('label').first().html();
				}
			}
			
			// Eliminamos los caracteres ':' y '=' que puedan existir en el label
			formFieldLabel = formFieldLabel.replace(/[:=]/g,"");;
			
			return formFieldLabel;
		},
		_getSearchFormFieldValue: function($field, $form){
			var fieldValue = " = ", filterMulticombo = [], numSelected;
			
			//VALUE
			switch($field.prop("tagName")){
				case "INPUT":
					fieldValue = fieldValue + $field.val();
					if ($field.attr("type") === "checkbox" || $field.attr("type") === "radio"){
						fieldValue = "";
					}
					break;
				case "SELECT":
					if ($field.next(".ui-multiselect").length==0){
						fieldValue = fieldValue + $("option[value='"+$field.rup_combo("getRupValue")+"']", $field).html();
					} else {
						if ($.inArray($field.attr("id"), filterMulticombo)===-1){
							numSelected = $field.rup_combo("value").length;
							if (numSelected !== 0){
								fieldValue += numSelected; 
							} else {
								fieldName = "";
								fieldValue = "";
							}
							filterMulticombo.push($field.attr("id"));
						} else {
							fieldName = "";
							fieldValue = "";
						}
					}
					break;
			}
			
			//Controlar rup.combo con valor vacío
			while (fieldValue.indexOf("&amp;nbsp;")!==-1){
				fieldValue = fieldValue.replace ("&amp;nbsp;","");
			}
			
			return fieldValue;
		}
		
	});
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	
		
	// Parámetros de configuración por defecto para la acción de eliminar un registro.
	jQuery.fn.rup_table.plugins.filter = {};
	jQuery.fn.rup_table.plugins.filter.defaults = {
//			bSubmit: jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_message.aceptar"),
//			cancelicon:[true, "left", "icono_cancelar"],
//			delicon:[false],
//			linkStyleButtons: ["#eData"],
//			msg: '<div id="rup_msgDIV_msg_icon" class="rup-message_icon-confirm"></div><div id="rup_msgDIV_msg" class="rup-message_msg-confirm white-space-normal">'+jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint.deleteAll")+'</div>',
//			mtype:"DELETE",
//			reloadAfterSubmit:false, 
//			resize:false
	};
	
		
	
})(jQuery);