

(function ($) {

	$.extend($.rup_utils, {
		/**
         * Devuelve un string Con los caracteres sencillos
         *
         * @name jQuery.rup_utils#normalize
         * @function
         * @param {string} cadena - Cadena de caracteres inicial.
         * @returns {string} - Cadena de caracteres sin accentFolding.
         * @example
         * // Convierte los caracteres de la cadena "áéíóu" -> "aeiou"
         * $.rup_utils.normalize("áéíóu");
         */
		normalize: function (texto) {
			var accentMap = {
					 'á':'a',
					 'é':'e',
					 'í':'i',
					 'ó':'o',
					 'ú':'u',
					 'Á':'A',
					 'É':'E',
					 'Í':'I',
					 'Ó':'O',
					 'Ú':'U'
			};
			var cadena = '';
			 	for ( var i = 0; i < texto.length; i++ ) {
			 		cadena += accentMap[ texto.charAt(i) ] || texto.charAt(i);
			 	}
			 return cadena;
		}
	});
	
	//****************************************************************************************************************
	// DEFINICIÓN BASE DEL PATRÓN (definición de la variable privada que contendrá los métodos y la función de jQuery)
	//****************************************************************************************************************
	
	$.fn.rup_autocomplete("extend", {
		_parseResponse: function _parseResponse(term, label, value, category) {
			if (category === undefined) {
				var labelAux = $.rup_utils.normalize(label);
				var termAux = $.rup_utils.normalize(term);
				var iInicio = labelAux.search(new RegExp('(?![^&;]+;)(?!<[^<>]*)(' + $.ui.autocomplete.escapeRegex(termAux) + ')(?![^<>]*>)(?![^&;]+;)', 'gi'));
				var iFin = iInicio + term.length;
				var a = label.substring(0, iInicio);
				var b = label.substring(iInicio, iFin);
				var c = label.substring(iFin);
				return {
					label: a + '<strong>' + b + '</strong>' + c,
					value: value
				};
			} else {
				return {
					label: label.replace(new RegExp('(?![^&;]+;)(?!<[^<>]*)(' + $.ui.autocomplete.escapeRegex(term) + ')(?![^<>]*>)(?![^&;]+;)', 'gi'), '<strong>$1</strong>'),
					value: value,
					category: category
				};
			}
		},
		_renderItem: function (ul, item) {
			// Replace the matched text with a custom span. This
			// span uses the class found in the "highlightClass" option.
			var re = new RegExp('(?![^&;]+;)(?!<[^<>]*)(' + $.ui.autocomplete.escapeRegex(this.term) + ')(?![^<>]*>)(?![^&;]+;)', 'gi'),
				cls = 'matched-text',
				template = '<span class=\'' + cls + '\'>$1</span>',
				label = item.label.replace(re, template),
				$li = $('<li/>').appendTo(ul);

			// Create and return the custom menu item content.
			$('<a/>').attr('href', '#')
				.html(label)
				.appendTo($li);
			var settings = this.options;
			$li.find('a').mousedown(function(event){
				var ui = {item:{label:item.label,value:item.value}};
			    settings.select(event,ui);
			 });
			return $li;

		}
	});
	
	//*******************************
	// DEFINICIÓN DE MÉTODOS PRIVADOS
	//*******************************
	
	
	$.fn.rup_autocomplete("extend", {
	_sourceLOCAL: function (request, response) {
		var settings, loadObjects = {},
			returnValue, stock;

		if (this.element.data('settings') !== undefined) {
			settings = this.element.data('settings');
		} else {
			settings = this.options;
		}

		if (settings.loadObjects !== undefined) {
			stock = settings.loadObjects;
		} else {
			stock = settings.id;
		}

		var textoOrigen = request.term;
		if(settings.accentFolding){
			textoOrigen = $.rup_utils.normalize(request.term);
		}
		var data, matcher = settings.contains ? $.ui.autocomplete.escapeRegex(textoOrigen) : '^' + $.ui.autocomplete.escapeRegex(textoOrigen),
			json_i18n = $.rup.i18n.app[settings.i18nId];

		matcher = new RegExp(matcher, 'i');
		data = $.map(settings.data, function (item) {
			var label = item,
				value = item,
				category;
			if (typeof item === 'object') { //multi-idioma
				if (item.i18nCaption !== undefined) {
					label = $.rup.i18nParse(json_i18n, item.i18nCaption);
				} else if (item.label !== undefined) {
					label = item.label;
				} else {
					label = item.value;
				}
				value = item.value;
				if (settings.category)
					category = item.category;
			}
			var labelLimpio = label;
			if(settings.accentFolding){
				labelLimpio = $.rup_utils.normalize(label);
			}
			if (!request.term || matcher.test(labelLimpio)) {
				var termLimpio = $.rup_utils.normalize(request.term);
				if (settings.category)
					returnValue = settings._parseResponse(termLimpio, label, value, category);
				else
					returnValue = settings._parseResponse(termLimpio, label, value);
				loadObjects[returnValue.label.replace(/<strong>/g, '').replace(/<\/strong>/g, '')] = returnValue.value;
				
//				if(settings.accentFolding && labelLimpio !== label){//limpiar acentos y mayúsculas
//					//parte delantera
//					//returnValue.label = literal.substr(0,nDelante)+label.substr(0,termLimpio.length)+literal.substr(nDelante+termLimpio.length);
//					
//					var literal = returnValue.label;
//					var nDelante = literal.indexOf(termLimpio);
//					var n = labelLimpio.indexOf(termLimpio);
//					returnValue.label = literal.substr(0,nDelante)+item.label.substr(n,termLimpio.length)+literal.substr(nDelante+termLimpio.length);
//					//parte trasera
//					//var nAtras = literal.indexOf(labelLimpio.substr(termLimpio.length));
//					
//					var nAtras = literal.indexOf("</strong>")+9;
//					literal = returnValue.label;
//					returnValue.label = literal.substr(0,nAtras)+label.substr(n+termLimpio.length);
//				}
				return returnValue;
			}
		});

		//Se almacenan los datos cargados
		$('#' + stock).data('loadObjects', loadObjects);

		//Eliminar elementos vacíos
		data = $.grep(data, function (value) {
			return value != undefined;
		});
		response(data);
	},
	_sourceREMOTE: function (request, response) {
		//Se escapan los comodines/wildcards de BD
		var $self = this.element,
			settings, loadObjects = {},
			returnValue, stock, term, data, lastTerm, bckData, $stock;

		if (this.element.data('settings') !== undefined) {
			settings = this.element.data('settings');
		} else {
			settings = this.options;
		}

		if (settings.loadObjects !== undefined) {
			stock = settings.loadObjects;
		} else {
			stock = settings.id;
		}

		$stock = jQuery('#' + stock);

		term = request.term.replace(/%/g, '\\%').replace(/_/g, '\\_');
		data = $.extend({
			q: term,
			c: this.options.contains
		}, this.options.extraParams);

		// Comprobar si se puede cachear
		lastTerm = $stock.data('tmp.loadObjects.term');

		if (term.indexOf(lastTerm) === 0) {

			$stock.data('tmp.loadObjects.term', term);

			bckData = settings.data;

			settings.data = $stock.data('tmp.data');
			jQuery.proxy(settings.$self._sourceLOCAL, this, request, response)();
			settings.data = bckData;

		} else {

			$.rup_ajax({
				url: settings.data,
				data: data,
				dataType: 'json',
				contentType: 'application/json',
				//Cabecera RUP
				beforeSend: function (xhr) {
					//LOADING...
					$('#' + settings.id + '_label').addClass('rup-autocomplete_loading');

					xhr.setRequestHeader('RUP', $.toJSON(settings.sourceParam));
				},
				/**
                 *
                 * @fires  {[event]} rupAutocomplete_beforeLoadComplete [description]
                 */
				success: function (data) {

					$self.triggerHandler('rupAutocomplete_beforeLoadComplete', [data]);
					//Si no hay datos en el autocomplete que se cierre
					if (data.length == 0) {
						jQuery('#' + settings.id + '_label').autocomplete('close');
						return null;
					}
					response($.map(data, function (item) {
						if (settings.category == true) returnValue = settings._parseResponse(request.term, item.label, item.value, item.category);else returnValue = settings._parseResponse(request.term, item.label, item.value);

						loadObjects[returnValue.label.replace(/<strong>/g, '').replace(/<\/strong>/g, '')] = returnValue.value;
						return returnValue;
					}));

					//se almacenan los datos cargados
					$stock.data('loadObjects', loadObjects);
					$stock.data('tmp.loadObjects.term', term);
					$stock.data('tmp.data', data);

					$self.triggerHandler('rupAutocomplete_loadComplete', [data]);
				},
				error: function (xhr, textStatus, errorThrown) {
					if (settings.onLoadError !== null && typeof settings.onLoadError === 'function') {
						jQuery(settings.onLoadError(xhr, textStatus, errorThrown));
					} else {
						$.rup.showErrorToUser($.rup.i18n.base.rup_autocomplete.ajaxError);
					}
				},
				complete: function () {
					//UNLOADING...
					$('#' + settings.id + '_label').removeClass('rup-autocomplete_loading');
				}
			});

		}
	},
	_init: function (args) {

		if (args.length > 1) {
			$.rup.errorGestor($.rup.i18nParse($.rup.i18n.base, 'rup_global.initError') + $(this).attr('id'));
		} else {
			//Se recogen y cruzan las paremetrizaciones del objeto
			var $self = $(this),
				settings = $.extend({}, $.fn.rup_autocomplete.defaults, args[0]),
				name = $(this).attr('name'),
				selected_value;

			$(this).attr('ruptype', 'autocomplete');

			//Recopilar datos necesarios
			settings.id = $(this).attr('id');
			settings.loadObjects = settings.id;
			settings.data = settings.source; //Guardar los datos en "data" ya que source la emplea autocomplete internamente
			settings._parseResponse = this._parseResponse; //Guardar referencia a rup.autocomplete para invocar las funciones privadas
			settings._sourceLOCAL = this._sourceLOCAL; //Guardar referencia a rup.autocomplete para invocar las funciones privadas
			settings.$self = this; //Guardar referencia a rup.autocomplete para invocar las funciones privadas

			//Guardar valor del INPUT
			settings.loadValue = $('#' + settings.id).attr('value');

			//Si no se recibe identificador para el acceso a literales se usa el ID del objeto
			if (!settings.i18nId) {
				settings.i18nId = settings.id;
			}

			//Eventos
			//*******
			//Guardar referencia
			settings._change = settings.change;
			settings._select = settings.select;
			settings._focus = settings.focus;

			//Sobrecargar tratamiento
			settings.change = function (event, ui) {
				if (selected_value != null) { //Puede que se ejecute este evento sin ejecutarse el select. Con esta condición nos aseguramos
					$('#' + event.target.id).val(selected_value);
					$('#' + event.target.id).focus();
				}
				selected_value = null;
				if (settings._change !== undefined) {
					settings._change(event, ui);
				}
				$self.triggerHandler('rupAutocomplete_change');
			};
			settings.select = function (event, ui) {
				selected_value = ui.item.label.replace(/<strong>/g, '').replace(/<\/strong>/g, '');
				if (settings._select !== undefined) {
					settings._select(event, ui);
				}
				$('#' + settings.id).attr('rup_autocomplete_label', selected_value);
				$('#' + settings.id).data('selected', true);
				$self.triggerHandler('rupAutocomplete_select', [ui]);
				$('#' + settings.id).val(ui.item.value);
				return false;
			};
			settings.focus = function (event, ui) {
				if (ui.item != undefined) {
					$('#' + event.target.id).val(ui.item.label.replace(/<strong>/g, '').replace(/<\/strong>/g, ''));
					if (settings._focus !== undefined) {
						settings._focus(event, ui);
					}
				}
				
				return false; //Evitar acciones jquery.autocomplete.js
			};


			//Generación de campo oculto para almacenar 'value' (en el input definido se guarda el 'label')
			var $hidden = $('<input>').attr({
				type: 'hidden',
				id: settings.id + '_value',
				name: (settings.valueName === null ? name : settings.valueName),
				ruptype: 'autocomplete'
			});

			$('#' + settings.id).after($hidden)
				.attr('name', (settings.labelName === null ? name + '_label' : settings.labelName))
				.addClass('rup-autocomplete_label');

			//					settings.$hidden = $hidden;

			if (typeof settings.source === 'object') {
				//LOCAL
				settings.source = this._sourceLOCAL;
			} else {
				//REMOTO
				//Nos aseguramos que el número mínimo de teclas para búsquedas sea 3
				settings.minLength = settings.minLength > 3 ? settings.minLength : 3;
				settings.source = this._sourceREMOTE;
			}

			//Se prepara el almacenaje de datos
			$('#' + settings.id).data('loadObjects', {});

			if (settings.combobox === true) {
				$('#' + settings.id).addClass('rup-combobox-input ui-corner-left');
				settings.minLength = 0;
			}


			jQuery(settings.appendTo).addClass('ui-front');

			//Invocación al componente subyacente
			jQuery('#' + settings.id).autocomplete(settings);

			//Se anyade un id al menu desplegable del autocomplete
			settings.$menu = jQuery('#' + settings.id).data('ui-autocomplete').menu.element.attr('id', settings.id + '_menu');

			/* Nuevo parche 23/11/18 */
			// Evita en IE que el input pierda el foco al hacer click en el scroll de la capa de resultados
            settings.$menu.on('scroll', function(){
            	jQuery('#' + settings.id + '_label').focus();
            	settings.$menu.show();
            });

            
            // Lanza el evento de change al seleccionar una de las opciones del menú
            settings.$menu.on('click', function(e){
                if ($(e.target).hasClass('ui-menu-item-wrapper')) {
                	$('#' + settings.id + '_label').triggerHandler('blur');
                	settings.$menu.triggerHandler('blur');
                }
            });
            /* Fin nuevo parche */
            
			if (settings.combobox === true) {
				this._createShowAllButton(settings);
			}



			// Altura del menu desplegable
			/*if (settings.menuMaxHeight !== false) {
				jQuery('#' + settings.id).on('autocompleteopen', function () {
					settings.$menu.css('overflow-y', 'auto')
						.css('overflow-x', 'hidden')
						.css('max-height', settings.menuMaxHeight+'px')
						.css('width', jQuery('#' + settings.id + '_label').innerWidth());
				});
			}*/
			
			jQuery('#' + settings.id).on('autocompleteopen', function () {
				if (settings.menuMaxHeight !== false) {
					settings.$menu.css('overflow-y', 'auto')
						.css('overflow-x', 'hidden')
						.css('max-height', settings.menuMaxHeight + 'px')
						.css('width', jQuery('#' + settings.id + '_label').innerWidth());
				}
				jQuery('#' + settings.id + '_menu').css('z-index', '1000');
				jQuery('#' + settings.id + '_menu').removeClass('ui-front');
			});

			//Buscar el UL del autocomplete y colocarlo tras el elemento sobre el que debe ir
			//$("#"+settings.id).after($("body > .ui-autocomplete"));


			//Buscar el UL del autocomplete y colocarlo tras el elemento sobre el que debe ir

			if (settings.menuAppendTo !== null) {
				if (jQuery(settings.menuAppendTo).length === 0) {
					alert('Es necesario especificar un selector válido para la propiedad menuAppendTo');
				} else {
					jQuery(settings.menuAppendTo).append(settings.$menu);
				}
			} else {
				if ($.rup_utils.aplicatioInPortal()) {
					//						$("div.r01gContainer").append($("body > .ui-autocomplete"));
					$('div.r01gContainer').append(settings.$menu);
				}
			}

			//Deshabilitar
			// if (settings.disabled===true) { $("#"+settings.id).rup_autocomplete("disable");
			//     if (settings.combobox)
			//         $('span').has('#'+settings.id+'_label').find("a").attr("style","display:none");
			//
			// }else if (settings.disabled===false){ //habilitar
			//     $("#"+settings.id).rup_autocomplete("enable");
			//     if (settings.combobox){
			//         $('span').has('#'+settings.id+'_label').find("a").removeAttr("style");
			//     }
			// }

			//Valor por defecto
			if (settings.defaultValue) {
				$('#' + settings.id).rup_autocomplete('search', settings.defaultValue);
			}

			//Valor pre-cargado
			if (settings.loadValue) {
				$('#' + settings.id).val(settings.loadValue);
				$('#' + settings.id + '_value').val(settings.loadValue);
			}

			// Modificar identificadores
			settings.loadObjects = settings.id + '_label';
			$('#' + settings.id).attr('id', settings.id + '_label');
			$('#' + settings.id + '_value').attr('id', settings.id);


			//eventos internos de borrado y perdida de foco
			$('#' + settings.id + '_menu').on('mousedown', function (event) {
				var selected = $('#' + settings.id).data('selected'),
					isShowingMenu = $('.ui-autocomplete:visible').length > 0 ? true : false;
				if (!selected && isShowingMenu) {
					$('#' + settings.id).data('ieIssueScrollVisible', true);
					event.preventDefault();
				}

			});

			$('#' + settings.id + '_label').bind('keydown', function () {
				$('#' + settings.id).data('ieIssueScrollVisible', false);
			});



			$('#' + settings.id + '_label').bind('blur', function (event) {
				//Obtener datos de si viene de seleccionar elemento o si el menú de selección está desplegado
				var selected = $('#' + settings.id).data('selected'),
					isShowingMenu = $('.ui-autocomplete:visible').length > 0 ? true : false;
				//Borrar índicador de que viene de seleccionar elemento
				$('#' + settings.id).data('selected', false);
				//Si es un evento de teclado pero no es ENTER, omitir esta función
				if (event.type === 'keydown' && event.keyCode !== 13) {
					return true;
				}
				if ($('#' + settings.id).data('ieIssueScrollVisible') === true) {
					$('#' + settings.id).focus();
					event.stopPropagation();
					$('#' + settings.id).data('ieIssueScrollVisible', false);
					return true;
				}



				var autoCompObject = $(event.currentTarget),
					loadObjects = $('#' + settings.loadObjects).data('loadObjects');

				if (settings.getText == true) {
					if (loadObjects[autoCompObject.val()] !== undefined) {
						$('#' + settings.id).val(autoCompObject.val());
						$('#' + settings.id).attr('rup_autocomplete_label', autoCompObject.val());
					} else {
						$('#' + settings.id).val(autoCompObject.val());
						$('#' + settings.id).attr('rup_autocomplete_label', autoCompObject.val());
					}
				} else {
					if (loadObjects[autoCompObject.val()] !== undefined) {
						$('#' + settings.id).val(loadObjects[autoCompObject.val()]);
						$('#' + settings.id).attr('rup_autocomplete_label', loadObjects[$.rup_utils.normalize(autoCompObject.val())]);
					} else {

						$('#' + settings.id).val('');
						$('#' + settings.id).attr('rup_autocomplete_label', '');
						autoCompObject.val('');
						autoCompObject.autocomplete('close');
					}
				}
				//Si el evento es ENTER y viene de seleccionar un elemento o el menú se estaba mostrando, omitir resto de funciones (ej. buscar)
				if (event.type === 'keydown' && event.keyCode === 13 && (selected || isShowingMenu)) {
					return false;
				}
			});

			//se guarda la configuracion general (settings) del componente
			$('#' + settings.id + '_label').data('settings', settings);
			$('#' + settings.id + '_label').data('rup.autocomplete', {
				$hiddenField: $('#' + settings.id)
			});
			$('#' + settings.id).data('rup.autocomplete', {
				$labelField: $('#' + settings.id + '_label')
			});


			//Deshabilitar
			if (settings.disabled === true) {
				$('#' + settings.id).rup_autocomplete('disable');

			} else if (settings.disabled === false) { //habilitar
				$('#' + settings.id).rup_autocomplete('enable');
			}
		}

	}
	});
	
	
	//******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON
	//******************************************************
	
	$.fn.rup_autocomplete.defaults = {
			onLoadError: null,
			contains: true,
			valueName: null,
			labelName: null,
			getText: false,
			combobox: false,
			menuMaxHeight: false,
			menuAppendTo: null,
			disabled: false,
			accentFolding: true
		};
	
})(jQuery);