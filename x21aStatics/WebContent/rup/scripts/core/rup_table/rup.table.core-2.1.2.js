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
	

	//*****************************************************************************************************************
	// DEFINICIÓN BASE DEL PATRÓN (definición de la variable privada que contendrá los métodos y la función de jQuery)
	//*****************************************************************************************************************
	
	var rup_table = {};
	rup_table.plugins=[];
	
	jQuery.rup_table = jQuery.rup_table || {};
	jQuery.extend(jQuery.rup_table,{
		registerPlugin: function(name, settings){
			if (jQuery.inArray(name, rup_table.plugins)===-1){
				rup_table.plugins.push(name);
				rup_table.plugins[name]=settings;
			}
		}
	});
	
	//Se configura el arranque de UDA para que alberge el nuevo patrón 
	jQuery.extend(jQuery.rup.iniRup, jQuery.rup.rupSelectorObjectConstructor("rup_table", rup_table));
	
	
	/*
	 * SOBREESCITURAS
	 * Funciones extendidas (SOBREESCRITAS) del componente jqGrid
	 * 
	 * Los métodos aquí indicados han sido extendidos y su implementación sustituida por completo.
	 * La extensión ha sido realizada para ajustar el comportamiento del componente jqGrid a los requisitos exigidos.
	 * 
	 * Los métodos extendidos para su modificación son los siguientes:
	 * 
	 * - createModal
	 * - hideModal
	 * - viewModal
	 */ 
	jQuery.extend(jQuery.jgrid,{
		createModal : function(aIDs, content, p, insertSelector, posSelector, appendsel, css) {
			// aIDs: Identificadores de la modal
			// -- aIDs.modalcontent :
			// -- aIDs.modalhead :
			// -- aIDs.scrollelm :
			// -- aIDs.themodal :
			// content: Contenido HTML del díalogo
			// p: parámetros de configuración del diálogo 
			// insertSelector: selector que corresponde al elemento despues del que se va a insertar la modal
			// posSelector: elemento base sobre el que se calcula la posición
			var $divModal = jQuery("<div/>").attr("id",aIDs.themodal).append($(content));
			var $scrollelm = $divModal.find("#"+aIDs.scrollelm);
			
			$divModal.insertBefore($(insertSelector));
			/* TODO : Añadir los parametros de configruación que puedan añadirse al rup_dialog. */
			$divModal.rup_dialog({
				type: $.rup.dialog.DIV,
				autoOpen: false,
				modal: true,
				resizable: p.resize,
				title: p.caption,
				width: p.width,
				buttons: p.buttons
			});
			
			// Eliminamos los eventos del boton de cerrar para mostrar el gestor de cambios
			
			if (jQuery.isFunction(p.onClose)){
				jQuery(".ui-dialog-titlebar-close, a:has(#closeText_" +$divModal.first()[0].id+")", $divModal.parent()).off("click").on("click", function(event){
					p.onClose.call(event);
				});
				// Se elimina el evento de cerrar al texto de cierre del dialogo y se asigna el evento de la gestion de cambios. 
//				prop.detailDiv.parent().find("#closeText_" + prop.detailDiv.first()[0].id).parent().unbind('click').bind("click", function () {
//					self._checkDetailFormModifications(function(){
//						prop.detailDiv.rup_dialog("close");
//					});
//				});
				
				// Se elimina el evento de cerrar al icono de cierre del dialogo y se asigna el evento de la gestion de cambios.
//				prop.detailDiv.parent().find(".ui-dialog-titlebar-close").unbind('click').bind("click", function () {
//					self._checkDetailFormModifications(function(){
//						prop.detailDiv.rup_dialog("close");
//					});
//				});
			}
			
			jQuery("#"+aIDs.scrollelm+"_2").addClass("botoneraModal");
			
			jQuery(".fm-button","#"+aIDs.scrollelm+"_2").on({
				focusin:function(){jQuery(this).addClass('ui-state-focus');},
				focusout:function(){jQuery(this).removeClass('ui-state-focus');}
			});
			
			if (p.linkStyleButtons!==undefined){
				for (var i=0;i<p.linkStyleButtons.length;i++){
					jQuery(p.linkStyleButtons[0]).addClass("botonEnlace");
				}
			}
		},
		hideModal : function (selector,o) {
			jQuery(selector).rup_dialog("close");
		},
		viewModal: function(selector,o){
			jQuery(selector).rup_dialog("open");
		}
		
	});
	
	
	jQuery.extend(jQuery.rup_table,{
		proxyAjax:function(ajaxOptions, identifier){
			jQuery.rup_ajax(ajaxOptions);
		}
	});
	
	/* ******************************
	 * FUNCIONES DE CONFIGURACION
	 * ******************************/
	jQuery.fn.rup_table("extend",{
		preConfigureCore: function(settings){
			var $self = this, colModel, colModelObj;
			
			// Se almacena el identificador del objeto en la propiedad settings.id
			settings.id=$self.attr("id");
			
			colModel = settings.colModel;
			
			// Configuración del colModel para los campos sobre los que se debe de configurar un componente RUP
			for (var i=0;i<colModel.length;i++){
				colModelObj = colModel[i];
				// Se comprueba para cada uno de las entradas en el colModel si se debe de crear un componente RUP
				if (colModelObj.rupType!==undefined && colModelObj.rupType!==null){
					// En caso de tratarse de un componente RUP
					// Se indica como edittype="custom" para que jqGrid lo trate como un componente personalizado
					colModelObj.edittype = "custom";
					
					// Si no se ha especificado una funcion custom_element se asigna la función genérica correspondiente a un componente RUP
					if (!jQuery.isFunction(colModelObj.editoptions.custom_element)){
						colModelObj.editoptions.custom_element = function(value, options){
							return $("<input>").attr({
								"type":"text",
								"id":options.id,
								"name":options.name,
								"class": "FormElement formulario_linea_input customelement",
								"style": "width:98%",
								"value": value
							})[0];
						};
					}
					// Si no se ha especificado una funcion custom_value se asigna la función genérica correspondiente a un componente RUP
					if (!jQuery.isFunction(colModelObj.editoptions.custom_value)){
						colModelObj.editoptions.custom_value = function($elem, operation, value){
							var ruptype = $elem.attr("ruptype");
							if (ruptype!==undefined){
								if (operation === "set"){
									$elem["rup_"+ruptype]("setRupValue",value);
								}else if (operation === "get"){
									return $elem["rup_"+ruptype]("getRupValue");
								}
							}
						};
					}
				}
			}
			
			$self.on({
				"jqGridBeforeRequest":function(){
					jQuery.set_uda_ajax_mode_on();
				}
			});
		},
		postConfigureCore: function(settings){
			var $self = this;
			
			// Se configura la funcionalidad de redimensionado de la tabla.
			if (settings.resizable !== false){
				$self.rup_table('gridResize', (jQuery.isPlainObject(settings.resizable)? settings.resizable:{}));
			}
			
			// Configruación pager
			$self.rup_table("configurePager",settings);
			
			// Implementación del ellipsis en las cabeceras de las columnas de la tabla
			jQuery($self.rup_table("getGridParam","colModel")).each (function (index, element){
				var $headerLabel;
				
				//Si la columna define ellipsis...
				if (element.classes === "ui-ellipsis"){
					//Añadirle estilos para ellipsis al div que está dentro de la cabecera
					jQuery("[id='jqgh_" + settings.id + "_" + element.name+"']")
						.css("display", "block")
						.css("text-overflow", "ellipsis");

				}
				
				//Sustituir DIV del literal de la cabecera por SPAN (para que funcione ellipsis)
				$headerLabel = jQuery("[id='jqgh_" + settings.id + "_" + element.name+"']").children("div");
				$headerLabel.replaceWith(jQuery("<span>").text($headerLabel.text()).css("cursor","pointer"));
			});
		}
	});
	
	
	
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	jQuery.fn.rup_table("extend",{
		getColModel: function(){
			return $(this).jqGrid("getGridParam","colModel");
		},
		getGridParam : function (pName) {
			return $(this).jqGrid("getGridParam", pName);
		},
		gridResize : function (options){
			return $(this).jqGrid('gridResize', options);
		},
		reloadGrid: function(async){
			var page = $self.rup_table("getGridParam", "page");
			var ajaxOptions = $self.jqGrid("getGridParam", "ajaxGridOptions");
			var ajaxOptionsAsync =  ajaxOptions.async;
			ajaxOptions.async = false;
			var ajaxOptions = $self.jqGrid("setGridParam", {ajaxGridOptions:ajaxOptions});
			
			$self.jqGrid("setGridParam", {page: parseInt(page,10)+1});
			$self.trigger("reloadGrid");
			ajaxOptions.async = true;
			var ajaxOptions = $self.jqGrid("setGridParam", {ajaxGridOptions:ajaxOptions});
			var nextPagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])();
			$self.jqGrid("setSelection",nextPagePos[1][0]);
		},
		resetForm: function($form){
			var $self = this;
			// Se eliminan los estilos de errores de validacion
			if ($form.data("validator") != undefined){
				var errorClass = $form.data("validator").settings.errorClass;
				$("."+errorClass,$form).removeClass(errorClass);
			}
			// Se realiza el reset de los campos ordinarios
			//form.resetForm();
			$("input[type!='button']", $form).val("");
			// Se realiza el reset de los rup_combo
			jQuery.each($("select.rup_combo",$form), function(index,elem){
				$(elem).rup_combo("clear");
			});
			//Vaciar los autocompletes
			$("[ruptype='autocomplete']", $form).each(function (index, element) {
				$(element).val("");
			});
			
			return $self;
		},
		setGridParam : function (newParams) {
			$(this).jqGrid("setGridParam", newParams);
			return $(this);
		},
//		search : function(async){
//			var $self = this, 
//				props = $self[0].p,
//				settings = $self.data("settings"); 
//			
//			jQuery.extend (props.postData,settings.$searchForm.serializeObject());
//			var postDataAux = {};
//			jQuery.each (props.postData, function(a,b){
//			    if (b!==''){
//			    	postDataAux[a]=b;
//			    }
//			});
//			props.postData = postDataAux;
//			
//			$self.trigger("reloadGrid");
//		},
		highlightRowAsSelected: function($row){
			var $self = this, self = $self[0], internalProps = self.p, row = $row[0], 
			froz = internalProps.frozenColumns === true ? internalProps.id + "_frozen" : "";
			
			if(!$row.hasClass("ui-subgrid") && !$row.hasClass("jqgroup") && !$row.hasClass('ui-state-disabled')){
				$("#jqg_"+$.jgrid.jqID(internalProps.id)+"_"+$.jgrid.jqID(row.id) )[internalProps.useProp ? 'prop': 'attr']("checked",true);
				$row.addClass("ui-state-highlight").attr("aria-selected","true");  
				internalProps.selarrrow.push(row.id);
				internalProps.selrow = row.id;
				if(froz) {
					$("#jqg_"+$.jgrid.jqID(internalProps.id)+"_"+$.jgrid.jqID(row.id), self.grid.fbDiv )[internalProps.useProp ? 'prop': 'attr']("checked",true);
					$("#"+$.jgrid.jqID(row.id), self.grid.fbDiv).addClass("ui-state-highlight");
				}
				$self.trigger("rupTableHighlightRowAsSelected");
			}
		},
		updateDetailPagination : function(currentRowNumArg, totalRowNumArg){
			var $self = this, settings = $self.data("settings"), tableId = settings.id, currentRowNum, totalRowNum;
			currentRowNum = (currentRowNumArg!==undefined ? currentRowNumArg : $.proxy(settings.getDetailCurrentRowCount,$self)());
			totalRowNum = (totalRowNumArg!==undefined ? totalRowNumArg : $.proxy(settings.getDetailTotalRowCount,$self)());
			
			if (currentRowNum===1){
				$("#first_"+tableId+", #back_"+tableId, settings.$detailFormDiv).addClass("ui-state-disabled");
			}else{
				$("#first_"+tableId+", #back_"+tableId, settings.$detailFormDiv).removeClass("ui-state-disabled");
			}
			if (currentRowNum === totalRowNum){
				$("#forward_"+tableId+", #last_"+tableId, settings.$detailFormDiv).addClass("ui-state-disabled");
			}else{
				$("#forward_"+tableId+", #last_"+tableId, settings.$detailFormDiv).removeClass("ui-state-disabled");
			}
				
			$("#rup_maint_selectedElements_"+$self.attr("id")).text(jQuery.jgrid.format(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.defaults.detailForm_pager"),currentRowNum, totalRowNum));
		}
	});
	
	
	jQuery.fn.rup_table("extend", {
		configurePager: function(settings){
			var $self = this, 
				pagerName,
				$pagerCenter,
				pagerLeft,
				pagerRight;
			
			if (settings.pager!==undefined && settings.pager!==null){
				settings.$pager = $((settings.pager.indexOf("#")===0?settings.pager:'#'+settings.pager));
				pagerName = settings.$pager.attr("id");

				settings.$pager.css('height','auto'); //Posibilitar redimensionar paginador
				
				//Añadir clase a cada parte del paginador
				$pagerLeft = $('#' + pagerName + '_left');
				$pagerCenter = $('#' + pagerName + '_center');
				$pagerRight = $('#' + pagerName + '_right');
				
				$pagerLeft.addClass("pager_left");
				$pagerCenter.addClass("pager_center");
				$pagerRight.addClass("pager_right");
				
				//pager_left
				//**********
				//Quitar posibles botones del paginador (y dejar la parte izquierda vacía)
				$pagerLeft.html("");
			
				//Contador de seleccionados
				if (settings.multiselect === true){
					$pagerLeft.append( $('<div/>').addClass('ui-paging-selected').html("0 " + jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.pager.selected")));
				} 
				
				// Pager center
				
				$(".pager_center table td",settings.$pager).addClass('pagControls');
	
				//Cambiar flechas paginación por literales
				$('#first_'+ pagerName, $pagerCenter)
					.html($('<a/>').attr("href","javascript:void(0)").html(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.pager.primPag")).addClass('linkPaginacion'))
					.removeClass('ui-pg-button');
				$('#prev_'+ pagerName, $pagerCenter)
					.html($('<a/>').attr("href","javascript:void(0)").html(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.pager.anterior")).addClass('linkPaginacion'))
					.removeClass('ui-pg-button');
				$('#next_'+ pagerName, $pagerCenter)
					.html($('<a/>').attr("href","javascript:void(0)").html(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.pager.siguiente")).addClass('linkPaginacion'))
					.removeClass('ui-pg-button');
				$('#last_'+ pagerName, $pagerCenter)
					.html($('<a/>').attr("href","javascript:void(0)").html(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_grid.pager.ultiPag")).addClass('linkPaginacion'))
					.removeClass('ui-pg-button');
			}
			
		}

	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PRIVADOS
	//********************************

	jQuery.fn.rup_table("extend", {
		_init : function(args) {
			if (args.length > 1) {
				jQuery.rup.errorGestor(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_global.initError") + $(this).attr("id"));
			}else {
				var $self = this, 
					settings = {};
					
				
				/* *************************
				 * CONFIGURACION
				 * *************************/
				var defaultPugins = (jQuery.isArray(args[0].defaultPlugins)?args[0].defaultPlugins:jQuery.fn.rup_table.defaults.defaultPlugins),
				userPlugins = jQuery.merge([], args[0].usePlugins),
				configuredPlugins = jQuery.merge(defaultPugins, userPlugins);
				
				/* *********************************************************
				 * SE PROCESAN LAS CONFIGURACIONES POR DEFECTO DE LOS PLUGINS
				 * *********************************************************
				 */
				$.each(configuredPlugins, function(index, name){
					if (rup_table.plugins[name] !== undefined && jQuery.fn.rup_table.plugins[name] !== undefined){
						settings = $.extend(true,{}, settings, jQuery.fn.rup_table.plugins[name].defaults);
					}
				});
				
				// Se sobreescribe la configuración por defecto con la especificada por el usaurio
				settings = jQuery.extend(true, {}, jQuery.fn.rup_table.defaults, settings, args[0]);
				
				/* *********************************************************
				 * EJECUCION DE LA POSTCONFIGURACION DEL CORE
				 * *********************************************************/
				
				$self.rup_table("preConfigureCore",settings);
				
				
				/* *********************************************************
				 * EJECUCION DE FUNCIONES DE PRECONFIGURACION DE LOS PLUGINS
				 * *********************************************************
				 */
				
				$.each(configuredPlugins, function(index, name){
					if (jQuery.isFunction(rup_table.plugins[name].preConfiguration)){
						jQuery.proxy(rup_table.plugins[name].preConfiguration, $self)(settings);
					}
				});
				
				/*
				 * INVOCACIÓN al plugin subyacente jqGrid
				 */ 
				$self.jqGrid(settings);
				
				/* *********************************************************
				 * EJECUCION DE LA POSTCONFIGURACION DEL CORE
				 * *********************************************************/
				
				$self.rup_table("postConfigureCore",settings);
				
				/* *********************************************************
				 * EJECUCION DE FUNCIONES DE POSTCONFIGURACION DE LOS PLUGINS
				 * *********************************************************/
				$.each(configuredPlugins, function(index, name){
					if (jQuery.isFunction(rup_table.plugins[name].postConfiguration)){
						jQuery.proxy(rup_table.plugins[name].postConfiguration, $self)(settings);
					}
				});
				
				// Se almacenan los settings medainte el data para ser accesibles en las invocaciones a los métodos públicos.
				$self.data("settings",settings);
			}
		}
	});
	
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	jQuery.fn.rup_table.plugins = {};
	
	// Parámetros de configuración por defecto del componente rup_table.
	jQuery.fn.rup_table.defaults = {
			altRows: true,
			altclass: "rup-grid_oddRow",
			datatype: "json",					// Tipo de dato esperado para representar los registros de la tabla (jqGrid)
			editable: false,					// Determina si la tabla permite la edición en línea (rup_table)
			height: "auto", 					// Ajusta la altura de la tabla al contenido (jqGrid)
			jsonReader : {repeatitems: false},	// Parámetros de configuración que describen la estructura del json esperado (jqGrid)
			resizable: false,					// Determina si la tabla puede ser redimensionada mediante el ratón (jqGrid)
			sortable: true,						// Determina si se puede realizar drag&drop con las columnas de la tabla (jqGrid)
			viewrecords: true,					// Muestra el rango de elementos que se están mostrando en la tabla (jqGrid)
			// Callback ejecutado en las peticiones AJAX de la tabla
			loadBeforeSend: function rup_table_defaults_loadBeforeSend(xhr, settings){
				// Se modifica la request para incluir las siguientes cabeceras:
				// Se añade la cabecera JQGridModel para indicar que la petición ha sido realizada por el componente rup_table
				xhr.setRequestHeader("JQGridModel", "true");
				// Se indica que el tipo de contenido enviado en la cabecera es application/jsons
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			validate:{},
			defaultPlugins:["filter", "feedback", "toolbar", "fluid"],
			dataProxy: jQuery.rup_table.proxyAjax
		};
	
})(jQuery);