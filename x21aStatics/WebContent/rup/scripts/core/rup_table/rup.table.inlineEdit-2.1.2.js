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
	
	jQuery.rup_table.registerPlugin("inlineEdit",{
		loadOrder:6,
		preConfiguration: function(settings){
			var $self = this;
			
			$self.rup_table("preConfigureInlineEdit",settings);
		},
		postConfiguration: function(settings){
			var $self = this;
			
			$self.rup_table("postConfigureInlineEdit",settings);
		}
	});
	
	/**
	 * Extensión del componente rup_table para permitir la edición en línea de los registros visualizados.
	 * 
	 * Los métodos implementados son:
	 * 
	 * configureInlineEdit(settings): Realiza la configuración interna necesaria para la gestión correcta de la edición en línea.
	 * editRow(rowId, options): Activa el modo edicón en línea para un registro determinado.
	 * saveRow(rowId, options): Realiza el guardado de un registo modificado mediante la edición en línea.
	 * 
	 * Las propiedades de esta extensión almacenadas en el settings son las siguientes:
	 * 
	 * settings.$inlineForm : Referencia al formulario utilizado para enviar los datos del registro que está siendo editado.
	 *  
	 */
	jQuery.fn.rup_table("extend",{
		preConfigureInlineEdit: function(settings){
			var $self = $(this),
				formId = "inlineForm_" + settings.id,
				userBeforeSend,
				$inlineForm =$("<form>").attr({"id":"inlineForm_" + settings.id});
			
			settings.editable = true;
			// Arropamos la estructura de la tabla en un formulario para poder realizar el envío de los campos
			$self.wrap($inlineForm);
			// Almacenamos la referencia al formulario.
			settings.inlineEdit.$inlineForm = $("#"+formId);
			
			if (settings.inlineEdit.addEditOptions.url===undefined){
				settings.inlineEdit.addEditOptions.url=settings.url;
			}
			
			settings.inlineEdit.deleteOptions.ajaxDelOptions = $.extend(true, settings.inlineEdit.deleteOptions.ajaxDelOptions, {
				success: function(){
					settings.$feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.deletedOK"), "ok");
				}
			});
			
			/*
			 * Configuración del evetno beforeSend. Se sustituye el existente (en caso de haber)
			 * por el implementado a continuación. El objetivo es realizar la operación AJAX medainte
			 * el componente rup_formulario en vez del sistema por defecto del jqGrid.
			 * 
			 * El método beforeSend indicado por el usuario se seguirá ejecutanto de manera normal.
			 */
			// Se almancena en una variable temporal el método beforeSend especificado por el usuario
			userBeforeSend = settings.inlineEdit.beforeSend;
			settings.inlineEdit.addEditOptions.ajaxRowOptions.beforeSend = function(jqXHR, ajaxOptions){
				// Se añade la configuración de validaciones, la función userBeforeSend indicada por el usuario y el feedback utilzado por el compoennte.
				jQuery.extend(true, ajaxOptions, {
					validate: settings.validate,
					beforeSend:(jQuery.isFunction(userBeforeSend)?userBeforeSend:null),
					feedback: settings.$feedback
				});

				// Handler del evento rupValidate_formValidationError. Se lanza cuando se produce un error de validación en el formulario.
				settings.inlineEdit.$inlineForm.on("rupValidate_formValidationError.inlineEditing", function(event, obj){
					$self.off("rupValidate_formValidationError.inlineEditing");
					// Se elimina la capa de bloqueo de la tabla.
					$("#lui_"+$.jgrid.jqID(settings.id)).hide();
				});
				// Se realiza el envío del fomulario
				settings.inlineEdit.$inlineForm.rup_form("ajaxSubmit", ajaxOptions);
				
				// Se retorna false para evitar que se realice la petición AJAX del plugin subyacente.
				return false;
			};
			
			// Configuración de edit/add
			// Se procede a añadir sobre los settings de configuración los correspondientes a la edición en línea.
			settings.inlineEdit.addOptions = $.extend(true,{}, settings.inlineEdit.addEditOptions, settings.inlineEdit.addOptions);
			settings.inlineEdit.editOptions = $.extend(true,{}, settings.inlineEdit.addEditOptions, settings.inlineEdit.editOptions);
			
			
			// Fuerza la configuración para que solo se pueda seleccionar mediante el checkbox
			settings.multiboxonly = true;
			
			settings.getRowForEditing = function(){
				var $self = this,
				selrow=$self.jqGrid('getGridParam','selrow');
				
				return (selrow===null?false:selrow);
			};
			
			
			
			/* =======
			 * EVENTOS
			 * =======
			 */
			// Campturador del evento jqGridInlineAfterSaveRow.
			$self.on({
				"jqGridInlineAfterSaveRow.rupTable.inlineEditing": function(event, rowid, res, tmp, options){
					// Una vez se haya realizado el guardado del registro se muestra el mensaje correspondiente en el feedback dependiendo del modo en el que se encuentra.
					if (options.oper === 'edit') {
						settings.$feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.modifyOK"), "ok");
					} else {
						settings.$feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_maint.insertOK"), "ok");
					}
				},
				"jqGridInlineEditRow.rupTable.inlineEditing": function oneditfunc_default(event, rowId){
					var self = this, $self = $(self),
					settings = $self.data("settings"),
					colModel = self.p.colModel,
					ind = $self.jqGrid('getInd', rowId, true),
					cellColModel, colModelName, editOptions, $elem;
				
					// Se procesan las celdas editables
					$("td[role='gridcell']",ind).each( function(i) {
						cellColModel = colModel[i];
						
						if(cellColModel.editable===true){
							colModelName = cellColModel.name;
							$elem = $("[name='"+colModelName+"']",ind);
							// Se añade el title de los elementos de acuerdo al colname
							$elem.attr({
								"title": self.p.colNames[i],
								"class": "editable customelement"
							});
						
							// En caso de tratarse de un componente rup, se inicializa de acuerdo a la configuracón especificada en el colModel
							if(cellColModel.rupType!==undefined) {
								editOptions = cellColModel.editoptions;
								
								/*
								 * PRE Configuración de los componentes RUP
								 */ 
								switch(cellColModel.rupType){
								case "combo":
									editOptions = $.extend({menuWidth:$elem.width()}, editOptions, {width:"auto"});
									break;
								}
								
								// Invocación al componente RUP
								$elem["rup_"+cellColModel.rupType](editOptions);
								
								/*
								 * POST Configuración de los componentes RUP
								 */
								switch(cellColModel.rupType){
								case "date":
									// TODO: Aplicarlo con estilos
									$elem.css("width","88%");
									break;
								}
							}
						}
					});
					
					settings.inlineEditingRow = rowId;
					
					// Se almacena el contenido del los campos de la línea editable
					// TODO: Externalizar la obtención de los datos para comprobar los cambios 
					$self.data("initialFormData",settings.inlineEdit.$inlineForm.rup_form("formSerialize"));
					// Se añaden los eventos de teclado
					jQuery(ind).on({
						"keydown": function(event) {
							if (event.keyCode === 27) {
								$self.jqGrid("restoreRow",rowid, settings.afterrestorefunc);
								return false;
							}
							if (event.keyCode === 13) {
								var ta = event.target;
								if(ta.tagName == 'TEXTAREA') { 
									return true; 
								}
								$self.rup_table("saveRow", rowId);
								return false;
							}
						}
					});
					
					jQuery("input,select", jQuery(ind)).on({
						"focus": function(event){
							var $row = $(this).parent().parent();
							
							settings.inlineEditingRow  = $row.attr("id");
							$self.rup_table("setSelection",$row.attr("id"));
						}
					});
				},
				"jqGridDblClickRow.rupTable.inlineEdit": function (rowid, iRow, iCol, e){
					if (!settings.inlineEdit.autoEditRow){
						$self.rup_table('editRow', iRow);
					}else{
						return false;
					}
				}
			});
			if (settings.inlineEdit.autoEditRow){
				$self.on({
					"jqGridBeforeSelectRow.rupTable.inlineEditing": function(event, rowid, event){
						var $self = $(this),
						settings = $self.data("settings"),
						editableRows = $("tr[editable=1]", $self);
						
						/*
						 * Se comprueba si existen registros que estén siendo editados en línea.
						 * Del mismo modo se comprueba si el registro seleccionado es diferente del que se está editando en ese momento.
						 */ 
						if (editableRows.length > 0 && (settings.inlineEditingRow!== undefined && settings.inlineEditingRow !== rowid)){
							// Se comprueba si se han realizado cambios en el registro en edición
							// TODO: Utilizar un método para comprobar los cambios en el formulario
							if ($self.data("initialFormData") !== settings.inlineEdit.$inlineForm.rup_form("formSerialize")){
								// En caso de que se hayan realizado cambios se debera de realizar el guardado de los mismos.
								
								// Se confiura un handler para el evento jqGridInlineSuccessSaveRow que indica que se ha completado con exito el guardado del registro modificado.
								$self.on("jqGridInlineSuccessSaveRow.inlineEditing_beforeSelectRow", function(event){
									// Una vez se haya realizado correctamente el guardado del registo se procede a seleccionar el registro solicitado por el usuario.
									$self.rup_table("setSelection",rowid);
									// Se elimina el handler del evento para evitar duplicidades
									$self.off("jqGridInlineSuccessSaveRow.inlineEditing_beforeSelectRow");
								});
								
								// Se procede a realizar el guardado de los registros editados
								for (var i=0; i<editableRows.length;i++){
									$self.rup_table("saveRow", editableRows[0].id);
								}
								
								// Se retorna un false para deterner la selección del registro y permitir que se realice antes la gestión del guardado. 
								return false;
							}
						}
						
						// En caso de no necesitarse guardar el registro en edición se continúa con la gestión de la selección de manera normal.
						return true;
					},
					"jqGridCellSelect.rupTable.inlineEditing": function (event, rowid, iCol, cellcontent, obj){
		//				var $self = $(this); 
		//
		//				$self.rup_table("editRow", rowid);
					},
					"jqGridSelectRow.rupTable.inlineEditing": function (event, rowid, status, event){
						var $self = $(this), settings = $self.data("settings"), 
						editableRows;
						
						editableRows = $("tr[editable=1]", $self);
						
						// En caso de que existan registros en modo edición se restauran
						if (editableRows.length > 0){
							jQuery.each($("tr[editable=1]", $self), function(index, elem){
								if ($(elem).attr("id")!==rowid){
									$self.jqGrid("restoreRow", $(elem).attr("id"));
								}
							});
						}
						
						// Se procede a entrar en modo edición en la línea seleccionada.
						$self.rup_table("editRow", rowid);
					}
				});
			}
			
		},
		postConfigureInlineEdit:function(settings){
			var $self = this;
			
			/*
			 * CALLBACKS PARA LOS ESTADOS DE LA TABLA
			 */
			settings.isOnEdit = function(){
				var $self = this,
				selrow=$self.jqGrid('getGridParam','selrow'),
				newRow;
				
				// Existe una fila seleccionada?
				selrow = (selrow===null?false:selrow);
				selrow = selrow && (selrow.indexOf("jqg")===-1);
				
				// Existe una fila en modo nuevo?
				newRow = jQuery("tr[editable='1'].jqgrid-new-row", $self).length===0;
				
				return selrow && newRow;
			};
			
			settings.isOnAdd = function(){
				var $self = this;
				return jQuery("tr[editable='1']", $self).length===0;
			};
			
			settings.isOnDelete = function(){
				var $self = this,
				selrow=$self.jqGrid('getGridParam','selrow');
				
				selrow = (selrow===null?false:selrow);

				return jQuery("tr[editable='1']", $self).length>0 || selrow;
			};
			
			settings.isOnCancel = function(){
				var $self = this;
				return jQuery("tr[editable='1']", $self).length>0;
			};
			
			settings.isOnSave = function(){
				var $self = this;
				return jQuery("tr[editable='1']", $self).length>0;
			};
			
			/*
			 * CALLBACKS PARA LAS FUNCIONES DE LA BOTONERA
			 */
			
			settings.toolbar.addButtonFnc = function ($button, event) {
				var $self = this;
				$self.rup_table("addRow");
			};
			
			settings.toolbar.saveButtonFnc = function ($button, event) {
				var $self = this;
				$self.rup_table("saveRow");
			};
			
			settings.toolbar.editButtonFnc = function ($button, event) {
				var $self = this, settings = $self.data("settings");
				$self.rup_table("editRow", jQuery.proxy(settings.getRowForEditing,$self)());
			};
			
			settings.toolbar.deleteButtonFnc = function ($button, event) {
				var $self = this;
				$self.rup_table("deleteRow");
			};
			
			$self.on({
				"jqGridLoadComplete.rupTable.formEditing": function(data){
					var $self = $(this), settings = $self.data("settings"), nPos;
					
					if (settings.inlineEdit.autoselectFirstRecord){
						nPos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])();
						$self.rup_table("highlightRowAsSelected", jQuery($self.jqGrid("getInd", nPos[1][0],true)));
					}
				}
			});
		},
		addRow: function(options){
			var $self = this, 
			settings = $self.data("settings");
			
			/*
			 * TODO: Ajustar el paso de parámetros
			 */
			var auxOptions = {addRowParams:$.extend({},settings.inlineEdit.addOptions,options)};
			
			$self.jqGrid('addRow', $.extend({},auxOptions));
			
			return $self;
		},
		editRow: function (rowId, options){
			var $self = this, 
				settings = $self.data("settings"),
				selectedRow = (rowId===undefined?$self.jqGrid('getGridParam','selrow'):rowId);
			$self.jqGrid('editRow', selectedRow, $.extend({},settings.inlineEdit.editOptions,options));
			
			return $self;
		},
		deleteRow: function (rowId, options){
			var $self = this, 
				settings = $self.data("settings"),
//				deleteOptions = jQuery.extend(true, {}, jQuery.fn.rup_table.defaults.deleteOptions, options),
				deleteOptions = jQuery.extend(true, {}, settings.inlineEdit.deleteOptions, options),
				selectedRow = (rowId===undefined?$self.jqGrid('getGridParam','selrow'):rowId);

			// En caso de especificarse el uso del método HTTP DELETE, se anyade el identificador como PathParameter
			if (deleteOptions.mtype==="DELETE"){
				deleteOptions.url = settings.url+"/"+selectedRow;
			}
			
			$self.jqGrid('delGridRow',selectedRow, deleteOptions);
			
			return $self;
		},
		saveRow : function(rowId, options){
			var $self = this, self = $self[0], 
			settings = $self.data("settings"),
			selectedRow = (rowId===undefined?$self.jqGrid('getGridParam','selrow'):rowId);
			
//			var userBeforeSend = settings.ajaxRowOptions.beforeSend;
//			self.p.ajaxRowOptions.beforeSend = function(jqXHR, ajaxOptions){
//				var rupFormSettings = {};
//				jQuery.extend(true, rupFormSettings, ajaxOptions, {validate: settings.validation});
//				if (jQuery.isFunction(userBeforeSend)){
//					rupFormSettings.beforeSend = userBeforeSend;
//				}else{
//					rupFormSettings.beforeSend = null;
//				}
//				settings.$inlineForm.rup_form("ajaxSubmit", rupFormSettings);
//				return false;
//			};
			
			if(selectedRow.indexOf("jqg")!==-1){
				$self[0].p.ajaxRowOptions = settings.inlineEdit.addOptions.ajaxRowOptions;
				$self.jqGrid('saveRow', selectedRow, settings.inlineEdit.addOptions);
			}else{
				$self[0].p.ajaxRowOptions = settings.inlineEdit.editOptions.ajaxRowOptions;
				$self.jqGrid('saveRow', selectedRow, settings.inlineEdit.editOptions);
			}
			
			return $self;
		},
		restoreRow: function(rowId){
			var $self = this,
			rowToRestore = (rowId===undefined?$self.jqGrid('getGridParam','selrow'):rowId);
				
				
			$self.jqGrid("restoreRow",rowToRestore);
		}
	});
	
	
	
	
	
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************

	/**
	 * Parametros de configuración de los settings para el caso particular de configuración del componente en el caso de funcionar en modo edición en linea.
	 * 	
	 * Los métodos para los que se proporciona una implementación son los siguientes.
	 * 
	 * beforeSelectRow: 
	 * onCellSelect:
	 * onSelectRow:
	 */
	jQuery.fn.rup_table.config = jQuery.fn.rup_table.config || {};
	jQuery.fn.rup_table.config.inlineEdit = {
	};
	
	jQuery.fn.rup_table.plugins.inlineEdit = {};
	jQuery.fn.rup_table.plugins.inlineEdit.defaults = {
			inlineEdit:{
				autoselectFirstRecord: true,
				autoEditRow:false
			}
	};	
		
//	jQuery.fn.rup_table.defaults.ajaxRowOptions = {
	jQuery.fn.rup_table.plugins.inlineEdit.defaults.inlineEdit.addEditOptions = {
		contentType: 'application/json',
		type:"PUT",
		dataType: 'json',
		ajaxRowOptions:{
			contentType: 'application/json',
			dataType: 'json'
		}
	};
	
	// Parámetros de configruación específicos para la acción de añadir un registro
	jQuery.fn.rup_table.plugins.inlineEdit.defaults.inlineEdit.addOptions = {
			mtype: "POST",
			ajaxRowOptions:{
				type:"POST"
			}
	};
	
	// Parámetros de configruación específicos para la acción de editar un registro
	jQuery.fn.rup_table.plugins.inlineEdit.defaults.inlineEdit.editOptions = {
			mtype: "PUT",
			ajaxRowOptions:{
				type:"PUT"
			}
	};
	
	jQuery.fn.rup_table.plugins.inlineEdit.defaults.inlineEdit.deleteOptions = {
		bSubmit: jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_message.aceptar"),
		cancelicon:[true, "left", "icono_cancelar"],
		delicon:[false],
		linkStyleButtons: ["#eData"],
		msg: '<div id="rup_msgDIV_msg_icon" class="rup-message_icon-confirm"></div><div id="rup_msgDIV_msg" class="rup-message_msg-confirm white-space-normal">'+jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint.deleteAll")+'</div>',
		mtype:"DELETE",
		reloadAfterSubmit:false, 
		resize:false
	};
	
	/**
	 * Extensión de las propiedades por defecto del jqGrid para el modo de edición en línea
	 */
	jQuery.jgrid.inlineEdit = {
		keys:false
	};
	
})(jQuery);