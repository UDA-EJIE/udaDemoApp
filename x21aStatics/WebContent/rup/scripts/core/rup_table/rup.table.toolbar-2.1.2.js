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

	jQuery.rup_table.registerPlugin("toolbar",{
		loadOrder:3,
		preConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("preConfigureToolbar", settings);
			
		},
		postConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("postConfigureToolbar", settings);
			
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
		preConfigureToolbar: function(settings){
			var $self = this, toolbarSettings = settings.toolbar;
			
			if (toolbarSettings!==undefined && toolbarSettings.id!==null){
				settings.$toolbar=(toolbarSettings.id[0]==="#"?$(toolbarSettings.id):$("#"+toolbarSettings.id));
				if (!settings.$toolbar.hasClass("rup-toolbar")){
					settings.$toolbar.rup_toolbar({
						width: 796
					});
				}
				
//				toolbarSettings.self=$(toolbarSettings);
			}else{
				// En caso de no indicarse un toolbar, se crea un toolbar por defecto.
				// FIXME: Contemplar la posibilidad de no generar una toolbar por defecto
				toolbarSettings = {};
				toolbarSettings.id = "rup-maint_toolbar-" + settings.id;
				toolbarSettings.self = $("<div/>").attr("id", toolbarSettings.id);
				$self.prepend(toolbarSettings.self);
				toolbarSettings.self.rup_toolbar({
					width: 796
				});
			}
			
			toolbarSettings.$toolbar = settings.$toolbar;
			
			// autoAjustToolbar: Realiza el autoajuste del toolbar al tamanyo del grid.
			if (toolbarSettings.autoAjustToolbar) {
				settings.$toolbar.css("width", $self.rup_table("getGridParam", "width") - 5);//-5 para ajustar el ancho
			}
			
			// createDefaultToolButtons: Determina la creacion de los botones basicos por defecto del toolbar.
			if (toolbarSettings.createDefaultToolButtons) {
				
				toolbarSettings.addButtonFnc = (jQuery.isFunction(toolbarSettings.addButtonFnc)?toolbarSettings.addButtonFnc:function () {
					$self.rup_table("newElement");
				});
				
				toolbarSettings.cancelButtonFnc = (jQuery.isFunction(toolbarSettings.cancelButtonFnc)?toolbarSettings.cancelButtonFnc:function () {
					$self.rup_table("restoreRow");
				});
				
				toolbarSettings.editButtonFnc = (jQuery.isFunction(toolbarSettings.editButtonFnc)?toolbarSettings.editButtonFnc:function () {
					$self.rup_table("editElement");
				});
				
				toolbarSettings.saveButtonFnc = (jQuery.isFunction(toolbarSettings.saveButtonFnc)?toolbarSettings.saveButtonFnc:function () {
					// Guardar
				});
				
				toolbarSettings.deleteButtonFnc = (jQuery.isFunction(toolbarSettings.deleteButtonFnc)?toolbarSettings.deleteButtonFnc:function () {
					$self.rup_table("deleteElement");
				});
				
				toolbarSettings.filterButtonFnc = (jQuery.isFunction(toolbarSettings.filterButtonFnc)?toolbarSettings.filterButtonFnc:function () {
					$self.rup_table("toggleSearchForm");
				});
				
				// Boton anadir un nuevo elemento .
				if(toolbarSettings.defaultAdd){
					toolbarSettings.btnNew = settings.$toolbar.addButton({
						i18nCaption: "new",
						css: "rup-maint_new",
						index: 1
					}, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint")).bind("click", function(event){
						jQuery.proxy(toolbarSettings.addButtonFnc,$self)($(this), event);
					});
				}
				
				// Boton editar un elemento (solo en los mantenimientos de edicion simple y multiseleccion).  
				if(toolbarSettings.defaultEdit){
					toolbarSettings.btnEdit = settings.$toolbar.addButton({
						i18nCaption: "edit",
						css: "rup-maint_edit",
						index: 2
					}, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint")).bind("click", function(event){
						jQuery.proxy(toolbarSettings.editButtonFnc,$self)($(this), event);
					});
				}
				
				// Boton guardar un elemento (solo en los mantenimientos de edicion simple y multiseleccion).  
				if(toolbarSettings.defaultSave){
					toolbarSettings.btnSave = settings.$toolbar.addButton({
						i18nCaption: "save",
						css: "rup-maint_save",
						index: 3
					}, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint")).bind("click", function(event){
						jQuery.proxy(toolbarSettings.saveButtonFnc,$self)($(this), event);
					});
				}
				
				// Boton cancelar la edición un elemento.
				if(toolbarSettings.defaultCancel){
					toolbarSettings.btnCancel = settings.$toolbar.addButton({
						i18nCaption: "cancel",
						css: "rup-maint_cancel",
						index: 4
					}, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint")).bind("click",  function(event){
						jQuery.proxy(toolbarSettings.cancelButtonFnc,$self)($(this), event);
					});
				}
				
				// Boton eliminar un elemento. 
				if(toolbarSettings.defaultDelete){
					toolbarSettings.btnDelete = settings.$toolbar.addButton({
						i18nCaption: "delete",
						css: "rup-maint_delete",
						index: 5
					}, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint")).bind("click", function(event){
						jQuery.proxy(toolbarSettings.deleteButtonFnc,$self)($(this), event);
					});
				}
				
				// Boton filter para mostrar/ocultar el formulario de busqueda.
				if(toolbarSettings.defaultFilter){
					toolbarSettings.btnFilter = settings.$toolbar.addButton({
						i18nCaption: "filter", 
						css: "rup-maint_filter", 
						index: 6
					}, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint")).bind("click", function(event){
						jQuery.proxy(toolbarSettings.filterButtonFnc,$self)($(this), event);
					});
				}
			}
			
			//Se comprueba si hay nuevos botones definidos y se ejecuta la función addButton con la parametrizacion de los nuevos botones
			if (toolbarSettings.newButtons !== undefined && toolbarSettings.newButtons !== null){
				$.each(toolbarSettings.newButtons, function (index, object){
					if (object.json_i18n === undefined){
						object.json_i18n = {};
					}
					if (object.obj !== undefined && object.click !== undefined){
						settings.$toolbar.addButton(object.obj, object.json_i18n).bind("click", object.click);
					} else if (object.buttons !== undefined){
					 	 var mButton = settings.$toolbar.addMButton(object, object.json_i18n).bind("click", settings.$toolbar.showMButton);
					 	 settings.$toolbar.addButtonsToMButton(object.buttons, mButton, object.json_i18n);
					}else{
						$.rup.errorGestor($.rup.i18nParse($.rup.i18n.base,"rup_maint.toolbarNewButtonError"));
					} 
				});
			} 
			
			
			/*
			 * EVENTOS
			 */
//			$self.on({
				/*
				 * Capturador del evento jqGridSelectRow. 
				 * Se ejecuta cuando se selecciona una fila. 
				 * Realiza la gestión interna sobre la acción de (de)selección del usuario.
				 * 
				 * 	event: Objeto event.
				 * 	id: Identificador de la línea.
				 *  status: true en caso de selección, false en caso de deselección.
				 *  obj: Objeto interno del jqGrid.
				 */
//				"jqGridSelectRow.rupTable.toolbar": function(event, id, status, obj){
//					var $self = jQuery(this), settings = $self.data("settings");
//					debugger;
//					if (jQuery.proxy(settings.getRowForEditing, $self)()!==false){
//						// Existe elementos seleccionados para ser editados
//						
//						if (settings.toolbar.btnDelete!==undefined){
//							settings.toolbar.btnDelete.button("enable");
//						}
//						if (settings.toolbar.btnNew!==undefined){
//							settings.toolbar.btnNew.button("enable");
//						}
//						if (settings.toolbar.btnEdit!==undefined){
//							settings.toolbar.btnEdit.button("enable");
//						}
//						
//						
//					}else{
//						// No hay elementos seleccionados para ser editados
//						if (settings.toolbar.btnDelete!==undefined){
//							settings.toolbar.btnDelete.button("disable");
//						}
//						if (settings.toolbar.btnNew!==undefined){
//							settings.toolbar.btnNew.button("disable");
//						}
//						if (settings.toolbar.btnEdit!==undefined){
//							settings.toolbar.btnEdit.button("disable");
//						}
//					}
//				},
				/*
				 * Capturador del evento jqGridLoadComplete. 
				 * Se ejecuta una vez se haya completado la carga de la tabla.
				 * 
				 * 	data: Objeto que contiene la carga de la tabla.
				 * 
				 */
//				"jqGridLoadComplete.rupTable.toolbar": function(data){
//					var $self = jQuery(this), settings = $self.data("settings");
//					if (jQuery.proxy(settings.getRowForEditing, $self)()!==false){
//						// Existe elementos seleccionados para ser editados
//						
//						if (settings.toolbar.btnDelete!==undefined){
//							settings.toolbar.btnDelete.button("enable");
//						}
//						if (settings.toolbar.btnNew!==undefined){
//							settings.toolbar.btnNew.button("enable");
//						}
//						if (settings.toolbar.btnEdit!==undefined){
//							settings.toolbar.btnEdit.button("enable");
//						}
//						
//						
//					}else{
//						// No hay elementos seleccionados para ser editados
//						if (settings.toolbar.btnDelete!==undefined){
//							settings.toolbar.btnDelete.button("disable");
//						}
//						if (settings.toolbar.btnNew!==undefined){
//							settings.toolbar.btnNew.button("disable");
//						}
//						if (settings.toolbar.btnEdit!==undefined){
//							settings.toolbar.btnEdit.button("disable");
//						}
//					}
//					
//				}
//			});
		},
		postConfigureToolbar: function(settings){
			var $self = this, toolbarSettings = settings.toolbar;
			
			/*
			 * EVENTOS
			 */
			$self.on({
				/*
				 * Capturador del evento jqGridSelectRow. 
				 * Se ejecuta cuando se selecciona una fila. 
				 * Realiza la gestión interna sobre la acción de (de)selección del usuario.
				 * 
				 * 	event: Objeto event.
				 * 	id: Identificador de la línea.
				 *  status: true en caso de selección, false en caso de deselección.
				 *  obj: Objeto interno del jqGrid.
				 *  
				 *  jqGridSelectRow
				 *  jqGridLoadComplete
				 *  jqGridInlineEditRow
				 *  jqGridInlineAfterRestoreRow
				 *  rupTableHighlightRowAsSelected
				 *  rupTableSelectedRowNumberUpdated
				 *  jqGridInlineAfterSaveRow
				 *  
				 */
				"jqGridSelectRow.rupTable.toolbar jqGridLoadComplete.rupTable.toolbar jqGridInlineEditRow.rupTable.toolbar jqGridInlineAfterRestoreRow.rupTable.toolbar rupTableHighlightRowAsSelected.rupTable.toolbar rupTableSelectedRowNumberUpdated jqGridInlineAfterSaveRow": function(event, id, status, obj){
					var $self = jQuery(this), settings = $self.data("settings");
					// Existe elementos seleccionados para ser editados
							
					function processButton($button, enable){
						if ($button!==undefined){
							if (enable){
								$button.button("enable");
							}else{
								$button.button("disable");
							}
						}
					}
					
					processButton(settings.toolbar.btnEdit, jQuery.proxy(settings.isOnEdit, $self)());
					processButton(settings.toolbar.btnNew, jQuery.proxy(settings.isOnAdd, $self)());
					processButton(settings.toolbar.btnDelete, jQuery.proxy(settings.isOnDelete, $self)());
					processButton(settings.toolbar.btnCancel, jQuery.proxy(settings.isOnCancel, $self)());
					processButton(settings.toolbar.btnSave, jQuery.proxy(settings.isOnSave, $self)());
					
				}
			});
		}
	});
	
	
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	
		
	// Parámetros de configuración por defecto para la acción de eliminar un registro.
	jQuery.fn.rup_table.plugins.toolbar = {};
	jQuery.fn.rup_table.plugins.toolbar.defaults = {
			toolbar:{
				id: null,
				autoAjustToolbar: true,
				createDefaultToolButtons: true,
				defaultAdd : true,
				defaultEdit : true,
				defaultCancel : true,
				defaultSave : true,
				defaultDelete : true,
				defaultFilter : false
			}
	};
	
		
	
})(jQuery);